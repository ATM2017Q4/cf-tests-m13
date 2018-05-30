package com.cheapflights.core.runner;

import com.cheapflights.common.listeners.TestsListener;
import com.cheapflights.common.util.Settings;

import com.cheapflights.ui.tests.factory.AllTestsFactory;
import com.cheapflights.ui.tests.factory.FirstTestFactory;
import com.cheapflights.ui.tests.factory.RandomTestFactory;

import com.epam.reportportal.testng.ReportPortalTestNGListener;

import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;

import org.testng.ITestNGListener;
import org.testng.TestNG;

import java.util.ArrayList;
import java.util.List;


public class Runner {
    public static void main(String[] args) {
        Settings settings = new Settings();
        CmdLineParser parser = new CmdLineParser(settings);

        try {
            parser.parseArgument(args);
        } catch (CmdLineException e) {
            e.printStackTrace();
        }
        TestNG testNG = new TestNG();
        List<Class<? extends ITestNGListener>> listener = new ArrayList<>();
        listener.add(TestsListener.class);
        listener.add(ReportPortalTestNGListener.class);
        testNG.setDefaultSuiteName("Finding the cheapest flight");
        testNG.setListenerClasses(listener);


        if (settings.isFirstTest()) {
            testNG.setTestClasses(new Class[]{FirstTestFactory.class});
        } else if (settings.isAllTests()) {
            testNG.setTestClasses(new Class[]{AllTestsFactory.class});
        } else if (settings.isRandomTest()) {
            testNG.setTestClasses(new Class[]{RandomTestFactory.class});
        }
        testNG.run();
    }
}




