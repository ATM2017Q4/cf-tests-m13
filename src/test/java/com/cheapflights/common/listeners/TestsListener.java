package com.cheapflights.common.listeners;

import com.cheapflights.ui.page.abstractpages.AbstractSearchPage;
import com.cheapflights.ui.utils.BrowserUtils;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;


public class TestsListener implements ITestListener {

    private Logger logger = LogManager.getLogger(this.getClass().getSimpleName());


    public void onTestStart(ITestResult iTestResult) {

    }

    public void onTestSuccess(ITestResult iTestResult) {
        logger.info(getTestMethodName(iTestResult) + " passed.");
        BrowserUtils.takeScreenshot(AbstractSearchPage.getDriver());
    }

    public void onTestFailure(ITestResult iTestResult) {
        logger.info("The execution of the test " + getTestMethodName(iTestResult) + " failed. Please, see the screenshot for more info.");
        BrowserUtils.takeScreenshot(AbstractSearchPage.getDriver());

    }

    public void onTestSkipped(ITestResult iTestResult) {

    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    public void onStart(ITestContext iTestContext) {

    }

    public void onFinish(ITestContext iTestContext) {

    }

    private static String getTestMethodName(ITestResult result) {
        return result.getMethod().getConstructorOrMethod().getName();

    }

}
