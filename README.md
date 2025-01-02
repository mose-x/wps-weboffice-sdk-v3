# [WPS WebOffice 开放平台](https://solution.wps.cn) Java SDK V3

## 依赖

- JDK 8+
- Spring Framework 4.0+

## 使用说明
~~~xml
<dependency>
  <groupId>cn.ljserver.tool</groupId>
  <artifactId>web-office-v3</artifactId>
  <version>3.1.1</version>
</dependency>
~~~

## 主要结构及说明

~~~
├── java
│   └── cn
│       └── ljserver
│           └── tool
│               └── weboffice
│                       └── v3
│                           ├── config # 配置层
│                           └── 
│                           ├── controller # 接口层
│                           └── 
│                           ├── exception # 异常定义
│                           └── 
│                           ├── model # 值对象，包括请求参数、返回值等
│                           └── 
│                           └── util # 工具集合
│                           └── 
│                           └── service # 需要接入方实现的接口
│                           └── 
│                               ├── convert  # 此目录包含了所有文件转换的方法，可直接调用
│                               ├── 
│                               ├── ExtendCapacityService.java # 扩展能力接口，包括历史版本、重命名等功能
│                               ├── 
│                               ├── FileTemplateService.java # 获取文件模板接口，非必须（不知道为啥要用转换服务，先放这里）
│                               ├── 
│                               ├── MultiPhaseFileStorageService.java # 文档三阶段保存接口，非必须，且与 SinglePhaseFileStorageService 互斥，实现一个即可 
│                               ├── 
│                               ├── PreviewService.java # 预览服务接口，必须实现，包括获取文档信息、下载地址、当前用户权限接口
│                               ├── 
│                               ├── SinglePhaseFileStorageService.java # 文档保存接口，非必须，且与 MultiPhaseFileStorageService 互斥，实现一个即可（建议实现这个，简单）
│                               ├── 
│                               └── UserService.java # 获取用户信息，非必须
~~~

### <font color=Magenta>文档预览接口 (必须)</font>

如果仅使用开放平台提供的文档预览服务，可以只实现 `PreviewService` 接口，该接口是必须实现的，示例代码如下：

~~~java
@Service
public class PreviewServiceImpl implements PreviewService {
    @Override
    public FileInfo fetchFileInfo(String fileId) {
        return fetchFile(fileId).toFileInfo();
    }

    @Override
    public DownloadInfo fetchDownloadInfo(String fileId) {
        return fetchFileDownloadInfo(fileId);
    }

    @Override
    public UserPermission fetchUserPermission(String fileId) {
        return fetchFileUserPermission(fileId);
    }
}
~~~

### 文档保存接口 (可选)

##### <font color=Bisque>多阶段保存（较麻烦,但是目前官方推荐此方法,后续单阶段保存方式不再支持）</font>

如果要使用开发平台提供的文档编辑能力，需要近一步实现文档保存接口，`MultiPhaseFileStorageService` 或 `SinglePhaseFileStorageService` 这两个接口只需要实现一个即可，同时在开放平台配置您实现的是哪个接口

   * `MultiPhaseFileStorageService` 接口，其中包括如下三个步骤，该方式适用于文件元信息和文档内容是分开存储的，比如文档内容保存在某个云服务商，上传文件内容的流量直接走云服务商

      1. [准备阶段](https://solution.wps.cn/docs/callback/save.html#%E5%87%86%E5%A4%87%E4%B8%8A%E4%BC%A0%E9%98%B6%E6%AE%B5)
      2. [获取上传地址](https://solution.wps.cn/docs/callback/save.html#%E5%87%86%E5%A4%87%E4%B8%8A%E4%BC%A0%E9%98%B6%E6%AE%B5)
      3. [上传结果通知](https://solution.wps.cn/docs/callback/save.html#%E4%B8%8A%E4%BC%A0%E5%AE%8C%E6%88%90%E5%90%8E-%E5%9B%9E%E8%B0%83%E9%80%9A%E7%9F%A5%E4%B8%8A%E4%BC%A0%E7%BB%93%E6%9E%9C)
   
   * `SinglePhaseFileStorageService` 接口，将三阶段上传中的参数，通过一个 Form 提交到接入方的服务端，包括文件的元信息和文档内容

`MultiPhaseFileStorageService` 实现示例：

~~~java
@Service
public class MultiPhaseFileStorageServiceImpl implements MultiPhaseFileStorageService {
    // 准备阶段，获取校验文档内容的校验合计算方法，非必须，默认 SHA1
    @Override
    public List<DigestType> uploadPrepare(String s) {
        return Collections.singletonList(DigestType.SHA1);
    }

    // 获取上传地址，需要实现
    @Override
    public FileUploadMultiPhase.FileUploadAddress.Response uploadAddress(FileUploadMultiPhase.FileUploadAddress.Request request) {
        return fetchUploadAddress(request.getFileId());
    }

    // 通知上传结果
    @Override
    public FileInfo uploadComplete(FileUploadMultiPhase.FileUploadComplete.Request request) {
        maybeNeedLock();
        checkFileUploadComplete(request);
        return fetchFile(request.getRequest().getFileId());
    }
}
~~~

##### <font color=Cyan>单阶段保存（简单，建议实现这个(以前开通了此阶段保存接口),后续不再支持此阶段保存方式）</font>

`SinglePhaseFileStorageService` 实现示例：

~~~java
@Service
public class SinglePhaseFileStorageServiceImpl implements SinglePhaseFileStorageService {
    @Override
    @SneakyThrows
    public FileInfo uploadFile(FileUploadSinglePhase.Request request) {
        saveFileMeta(request);
        saveFileContent(request);
        return fetchFile(request.getFileId());
    }
}
~~~

### 用户接口 (可选) 

如果要显示当前用户信息（当前参与文档协作的用户等场景），需要实现 `UserService` 接口，示例代码如下：

~~~java
@Service
public class UserServiceImpl implements UserService {
    @Override
    public List<UserInfo> fetchUsers(List<String> userIds) {
        return fetchUserList(userIds);
    }
}
~~~

### 扩展能力接口 (可选) 

如果想使用更多开放平台提供的能力，需要选择性的实现 `ExtendCapacityService` 中的接口
  
接口定义中有默认实现（`default` 实现），方便接入方选择只实现其中的一部分功能，例如：

~~~java
public interface ExtendCapacityService {
    // 要使用文档重命名功能，需要实现该接口
    default void renameFile(String fileId, String name) {
        throw new NotImplementException();
    }

    // 如果要实现文档历史版本列表功能，要实现该接口
    default List<FileInfo> fileVersions(String fileId, int offset, int limit) {
        throw new NotImplementException();
    }

    // 如果要实现预览文档历史版本
    default FileInfo fileVersion(String fileId, int version) {
        throw new NotImplementException();
    }

    // 如果要实现预览文档历史版本，同上
    default DownloadInfo fileVersionDownload(String fileId, int version) {
        throw new NotImplementException();
    }

    // 如果要在文档预览/编辑的时候显示水印，需要实现该接口
    default Watermark fileWatermark(String fileId) {
        throw new NotImplementException();
    }
}
~~~

### <font color=LightSkyBlue>获取模板文件接口</font>

如果要使用开放平台提供的文档模板能力，只需要调用`TemplateService` 接口，前提是必须配置 WebOfficeProperties 
示例代码如下：（这个controller默认提供给你）
~~~java
@RestController
@RequestMapping("/v3/files/template")
public class FileTemplateController extends ProviderBaseController {

    @GetMapping("/{officeType}")
    @ProviderJsonApi
    public ProviderResponseEntity<?> fileTemplate(@PathVariable("officeType") String officeType) {
        return FileTemplateService.getFileTemplateResponse(officeType);
    }

}
~~~

**结果：**

~~~json
{
    "code": 0,
    "data": {
        "url": "https://solution-provider.ks3-cn-beijing.ksyun.com/office/template/empty.pptx?Expires=1714374668&KSSAccessKeyId=AKLTKVSHxfgqTr2XXElVZy9w&Signature=frBoO9PJsZkJUQzze55NrJzNe%2FE%3D",
        "name": "演示文稿.pptx"
    }
}
~~~


### <font color=LightSkyBlue>文档转换能力接口</font>

相关服务如下：
在service/convert目录中，实现了全部方法，安装WebOfficeProperties配置后即可使用。。

![img.png](img.png)

**示例代码：**

<font color=MediumTurquoise>**1. PDF转DOC**</font>
~~~java
@RestController
public class TestController extends ProviderBaseController {

     @GetMapping("a")
     public ConvertResponse a() {
          return PdfToDoc.convert("docx", "https://file.xxx.cn/temp/xxx.pdf");
     }

     @GetMapping("b/{task_id}")
     public ToDocResponse b(@PathVariable("task_id") String task_id) {
          return ToDocResult.get("docx", task_id);
     }

}
~~~

**结果：**
~~~json
{
    "code": 0,
    "data": {
        "task_id": "459d7ec404bf4a0a9b29ffc171171d89"
    }
}
~~~
~~~json
{
     "code": 0,
     "data": {
          "download_url": "http://zhai-platereduction.ks3-cn-beijing.ksyun.com/tmp/layout/pdfwriter/tmp/2024-04-29/459d7ec404bf4a0a9b29ffc171171d89.docx?Expires=1714453450&AWSAccessKeyId=AKLThacEYfpQEiYtqqtfXFZP&Signature=liRZVZHqTTmhgqU7Uk5BxRee5dc=",
          "status": 1,
          "duration": 1.693,
          "task_id": "459d7ec404bf4a0a9b29ffc171171d89",
          "progress": 100,
          "start_time": 1714367049215,
          "page_count": 3,
          "errMsgs": null
     }
}
~~~

<font color=MediumTurquoise>**2. JPG转PPTX：**</font>

~~~java
@RestController
public class TestController extends ProviderBaseController {

     @GetMapping("b/{task_id}")
     public ToDocResponse b(@PathVariable("task_id") String task_id) {
          return ToDocResult.get("docx", task_id);
     }

     @GetMapping("c")
     public ConvertResponse c() {
          return ImgToDoc.convert("pptx", "https://file.xxx.cn/upload/xxx.jpg");
     }

}
~~~

**结果：**

~~~txt
参考 PDF转DOC
~~~

<font color=MediumTurquoise>**3. DOC转PDF：**</font>

~~~java
@RestController
public class TestController extends ProviderBaseController {

     @GetMapping("d")
     public ConvertResponse d() {
          return DocToPdf.convert("https://file.xxx.cn/temp/test-doc.docx");
     }

     @GetMapping("e/{task_id}")
     public DocToResponse e(@PathVariable("task_id") String task_id) {
          return DocToResult.get(task_id);
     }
}
~~~

**结果：**

~~~json
{
   "code": 0,
   "data": {
      "task_id": "open:zqoxjlisjijqscyhuzvmmwlydetrcqf"
   }
}
~~~
~~~json
{
   "code": 0,
   "data": {
      "status": "success",
      "progress": 100,
      "message": null,
      "result": {
         "task": {
            "elapsed": 410,
            "resource_size": 11884
         },
         "pdfs": [
            {
               "url": "https://solution-provider.ks3-cn-beijing.ksyun.com/convert/pdf/a5c0526f6b5851d31b72dec38d05f4e493233020/JHHSmxPycY.pdf?Expires=1714373233&KSSAccessKeyId=AKLTKVSHxfgqTr2XXElVZy9w&Signature=ZLCGdS021qx67revFvsmPONntTM%3D&response-content-disposition=attachment%3Bfilename%2A%3DUTF-8%27%27JHHSmxPycY.pdf&response-content-type=application%2Fpdf",
               "size": 50790
            }
         ]
      }
   }
}
~~~

<font color=MediumTurquoise>**4. Excel类文件转xlsx：**</font>

~~~java
@RestController
public class TestController extends ProviderBaseController {

     @GetMapping("f")
     public ConvertResponse f() {
          return ExcelToXlsx.convert("https://file.xxx.cn/temp/123.xls");
     }

}
~~~

**结果：**

~~~json
{
     "code": 0,
     "data": {
          "task_id": "open:owphdgbiwyshnbdmdoqorbyhhmyubnr"
     }
}
~~~
~~~json
{
     "code": 0,
     "data": {
          "status": "success",
          "progress": 100,
          "message": null,
          "result": {
               "task": {
                    "elapsed": 397,
                    "resource_size": 57344
               },
               "url": "https://solution-provider.ks3-cn-beijing.ksyun.com/save_as_format/b43e9c65f1f426318bd2b0fe504fdd92e94f71a4/JXJYSdGcWJ.xlsx?Expires=1714404595&KSSAccessKeyId=AKLTKVSHxfgqTr2XXElVZy9w&Signature=JNueBpf%2FcQFjU0pU2t%2B0xBKK5uM%3D&response-content-disposition=attachment%3Bfilename%2A%3DUTF-8%27%27JXJYSdGcWJ.xlsx&response-content-type=application%2Fvnd.openxmlformats-officedocument.spreadsheetml.sheet",
               "size": 32606
          }
     }
}
~~~

### 实际效果

--- --
[docx 在线预览/编辑](https://qnfile.ljserver.cn/weboffice/docx.html)
-- -------------------------------------------
[pptx 在线预览/编辑](https://qnfile.ljserver.cn/weboffice/pptx.html)
-- -------------------------------------------
[xlsx 在线预览/编辑](https://qnfile.ljserver.cn/weboffice/xlsx.html)
-- -------------------------------------------
[pdf 在线预览/编辑](https://qnfile.ljserver.cn/weboffice/pdf.html)
--- --


## 更多

其它接口，请查阅<font color=LightSeaGreen>controller</font>或者<font color=LightSeaGreen>service</font>下的各种方法

关于接口的更多说明，请参考[WebOffice开放平台-WebOffice回调配置](https://solution.wps.cn/docs/callback/summary.html)。

