package cn.ljserver.tool.weboffice.v3.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * 日期工具类
 */
public class DateUtils {

    private DateUtils() {
    }

    /**
     * 获取日期
     *
     * @return 日期
     */
    public static String date() {
        SimpleDateFormat sdf = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz", Locale.US);
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
        return sdf.format(new Date());
    }
}
