package com.csvfile;

public class CSVBuilderFactory {
    public static ICSVBuilder createCSVBuilder() {
        return new OpenCSVBuilder();
    }
    public static ICSVBuilder createCommonCSVBuilder() {
        return new CommonCSVBuilder();
    }
}
