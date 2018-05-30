package com.cheapflights.common.util;

import org.kohsuke.args4j.Option;

public class Settings {
    @Option(name = "--first", usage = "Set the first test to run", forbids = {"--random", "--all"})
    private boolean firstTest;

    @Option(name = "--random", usage = "Set random test to run", forbids = {"--first", "--all"})
    private boolean randomTest;

    @Option(name = "--all", usage = "Set all tests to run", forbids = {"--random", "--first"})
    private boolean allTests;


    public boolean isFirstTest() {
        return firstTest;
    }

    public boolean isRandomTest() {
        return randomTest;
    }

    public boolean isAllTests() {
        return allTests;
    }
}
