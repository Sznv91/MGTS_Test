package test.mgts.utils;

/**
 * Определение charset на основании типа ОС.
 */
public class CharsetDetector {
    enum OsType {
        WINDOWS,
        OTHER,
    }

    public static String getCharsetConsole() {
        if (detectOs().equals(OsType.WINDOWS)) {
            return "cp866";
        } else return "UTF8";
    }

    public static String getCharsetFile() {
        if (detectOs().equals(OsType.WINDOWS)) {
            return "windows-1251";
        } else return "UTF8";
    }

    private static OsType detectOs() {
        String osName = System.getProperty("os.name");
        if (osName.startsWith("Windows")) {
            return OsType.WINDOWS;
        }
        return OsType.OTHER;
    }
}
