package cn.ljserver.tool.weboffice.v3.util;

import lombok.SneakyThrows;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @ClassName: FileUtils
 * @Description: 文件工具类
 */
public class FileUtils {
    private FileUtils() {
    }

    // 表格
    public static final String S = "s";
    // 文档
    public static final String W = "w";
    // 演示
    public static final String P = "p";
    // PDF
    public static final String F = "f";
    // 智能文档
    public static final String O = "o";
    // 智能文档
    public static final String D = "d";

    public static final String[] convertArrTypes = {S, W, P, D, O};

    public static final Map<String, String> officeTypes;

    static {
        final Map<String, String> map = new HashMap<>(48);

        // 表格
        Arrays.stream(new String[]{"et", "xls", "xlt", "xlsx", "xlsm", "xltx", "xltm", "csv", "ett"})
                .forEach(s -> map.put(s, S));

        // 文档
        Arrays.stream(new String[]{"doc", "docx", "txt", "dot", "wps", "wpt", "dotx", "docm", "dotm", "xml", "rtf", "mht", "html"})
                .forEach(s -> map.put(s, W));

        // 演示
        Arrays.stream(new String[]{"ppt", "pptx", "pptm", "pptm", "ppsm", "pps", "potx", "potm", "dpt", "dps", "pot", "ppsx"})
                .forEach(s -> map.put(s, P));

        // PDF
        Arrays.stream(new String[]{"pdf"})
                .forEach(s -> map.put(s, F));

        officeTypes = Collections.unmodifiableMap(map);
    }

    public static String fileExtension(String filename) {
        return Optional.ofNullable(filename)
                .filter(f -> f.contains("."))
                .map(f -> f.substring(filename.lastIndexOf(".") + 1))
                .orElse("");
    }

    public static boolean support(String filename) {
        final String ext = fileExtension(filename);
        return !ext.isEmpty() && officeTypes.containsKey(ext);
    }

    public static boolean support(Path path) {
        return Optional.ofNullable(path)
                .map(Path::getFileName)
                .map(Path::toString)
                .map(FileUtils::support)
                .orElse(false);
    }

    public static String officeType(String filename) {
        return Optional.of(fileExtension(filename))
                .filter(s -> !s.isEmpty())
                .map(officeTypes::get)
                .orElse("");
    }

    public static String officeType(Path path) {
        return Optional.ofNullable(path)
                .map(Path::getFileName)
                .map(Path::toString)
                .map(FileUtils::officeType)
                .orElse("");
    }

    @SneakyThrows
    public static byte[] readAllBytes(Path path) {
        return Files.readAllBytes(path);
    }

    @SneakyThrows
    public static long size(Path path) {
        return Files.size(path);
    }

    @SneakyThrows
    public static void delete(Path path) {
        Files.delete(path);
    }

    public static String uuid() {
        // 使用 uuid 来生成临时文件的 id
        // 之前的方式会导致以下问题：
        // 重启后，文件都被重置了，会导致 Web Office 内出现错误
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
