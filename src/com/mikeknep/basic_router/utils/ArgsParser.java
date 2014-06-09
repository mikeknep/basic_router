package com.mikeknep.basic_router.utils;

/**
 * Created by mrk on 6/2/14.
 */
public class ArgsParser {
    String rootDirectory;

    public ArgsParser(String[] args) {
        this.rootDirectory = args[0];
    }

    public String getRootDirectory() {
        return this.rootDirectory;
    }
}
