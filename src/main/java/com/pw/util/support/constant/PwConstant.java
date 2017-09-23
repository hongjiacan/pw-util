package com.pw.util.support.constant;

/**
 * Created by PoemWhite on 2017/4/25.
 */
public class PwConstant {
    //成功\失败 常量
    public static final int SUCCESS = 1;
    public static final int FAILURE = 0;

    //分页常量
    public static final int PAGINATE_START_DEFAULT = 0;//分页默认偏移量
    public static final int PAGINATE_LIMIT_DEFAULT = 15;//分页默认条数
    public static final int PAGINATE_MODE_CHANGE_OFFSET = 10000;//分页SQL模式分割点——偏移量10000条数据


    /**
     * 获取当前使用的JDK版本
     */
    public static final String JAVA_VERSION = System.getProperty("java.version");

    public static final boolean JAVA_1_1 = JAVA_VERSION.startsWith("1.1.");

    public static final boolean JAVA_1_2 = JAVA_VERSION.startsWith("1.2.");

    public static final boolean JAVA_1_3 = JAVA_VERSION.startsWith("1.3.");

    public static final boolean JAVA_1_4 = JAVA_VERSION.startsWith("1.4.");

    public static final boolean JAVA_1_5 = JAVA_VERSION.startsWith("1.5.");

    public static final boolean JAVA_1_6 = JAVA_VERSION.startsWith("1.6.");

    public static final boolean JAVA_1_7 = JAVA_VERSION.startsWith("1.7.");

    /**
     * 获取当前使用的操作系统类型
     */
    public static final String OS_NAME = System.getProperty("os.name");

    public static final boolean LINUX = OS_NAME.startsWith("Linux");

    public static final boolean WINDOWS = OS_NAME.startsWith("Windows");

    public static final boolean SUN_OS = OS_NAME.startsWith("SunOS");
}
