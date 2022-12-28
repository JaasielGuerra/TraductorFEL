package com.guerra.util;

import java.io.File;

public class AppProperties {
    public static String getPathUser() {
        return System.getProperty("user.dir");
    }

    public static String getPathInputDirectory() {
        return AppProperties.getPathUser() + File.separator + "input";
    }
}
