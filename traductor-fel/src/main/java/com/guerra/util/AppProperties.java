package com.guerra.util;

import java.io.File;

public class AppProperties {

    public static final String NAME_CONFIG_FILE = "configfel.properties";
    public static String getPathUser() {
        return System.getProperty("user.dir");
    }

    public static String getPathInputDirectory() {
        return AppProperties.getPathUser() + File.separator + "input";
    }

    public static String getPathResourceExternalDirectory() {
        return AppProperties.getPathUser() + File.separator + "resources";
    }

    public static String getPathToConfigFile() {
        return AppProperties.getPathResourceExternalDirectory() + File.separator + NAME_CONFIG_FILE;
    }
}
