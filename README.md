# [WebOffice 开放平台](https://solution.wps.cn) Java SDK

## 依赖

- JDK 8+
- Spring Framework 4.0+

## 使用说明
~~~xml
<dependency>
  <groupId>cn.ljserver.tool</groupId>
  <artifactId>web-office-v3</artifactId>
  <version>1.0.0</version>
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
│                           │   ├── AutoConfig.java
│                           ├── controller # 接口层
│                           │   ├── ExtendCapacityController.java
│                           │   ├── FileStorageController.java
│                           │   ├── PreviewController.java
│                           │   ├── ProviderBaseController.java
│                           │   ├── ProviderJsonApi.java
│                           │   └── UserController.java
│                           ├── exception # 异常定义
│                           │   ├── CustomError.java
│                           │   ├── FileNameConflict.java
│                           │   ├── FileNotExist.java
│                           │   ├── FileUploadNotComplete.java
│                           │   ├── FileVersionNotExist.java
│                           │   ├── InternalError.java
│                           │   ├── InvalidArgument.java
│                           │   ├── InvalidToken.java
│                           │   ├── NotImplementException.java
│                           │   ├── PermissionDenied.java
│                           │   ├── ProviderException.java
│                           │   ├── StorageNoSpace.java
│                           │   └── UserNotExist.java
│                           ├── model # 值对象，包括请求参数、返回值等
│                           │   ├── DigestType.java
│                           │   ├── DownloadInfo.java
│                           │   ├── FileInfo.java
│                           │   ├── FileRenameRequest.java
│                           │   ├── FileUploadMultiPhase.java
│                           │   ├── FileUploadSinglePhase.java
│                           │   ├── LocalDateTimeDeserializer.java
│                           │   ├── LocalDateTimeSerializer.java
│                           │   ├── ProviderRequestAttribute.java
│                           │   ├── ProviderResponseEntity.java
│                           │   ├── UserInfo.java
│                           │   ├── UserPermission.java
│                           │   └── Watermark.java
│                           └── service # 需要接入方实现的接口
│                               ├── ExtendCapacityService.java # 扩展能力接口，包括历史版本、重命名等功能
│                               ├── MultiPhaseFileStorageService.java # 文档三阶段保存接口，非必须，且与 SinglePhaseFileStorageService 互斥，实现一个即可 
│                               ├── PreviewService.java # 预览服务接口，必须实现，包括获取文档信息、下载地址、当前用户权限接口
│                               ├── SinglePhaseFileStorageService.java # 文档保存接口，非必须，且与 MultiPhaseFileStorageService 互斥，实现一个即可
│                               └── UserService.java # 获取用户信息，非必须
~~~

### 文档预览接口 (必须)

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

## 更多

关于回调接口的更多说明，请参考[WebOffice开放平台-WebOffice回调配置](https://solution.wps.cn/docs/callback/summary.html)。

