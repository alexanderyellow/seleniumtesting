package tests;

import org.testng.ITestContext;
import org.testng.TestRunner;
import org.testng.annotations.*;
import selenium.logger.DefaultListener;
import selenium.logger.Logger;
import selenium.ui.pages.AbstractPage;
import selenium.webconfigure.Browser;
import selenium.webconfigure.context.Environment;
import selenium.webconfigure.context.ExecutionContext;
import selenium.webconfigure.context.ExecutionContextManager;

import java.lang.reflect.Method;

/**
 * AbstractTest
 */
public class AbstractTest {

    protected Browser browser;
    protected Logger logger;
    private ExecutionContext executionContext;

    @BeforeSuite
    public void beforeSuite(ITestContext iTestContext) {
        TestRunner tr = (TestRunner) iTestContext;
        tr.addListener(DefaultListener.getInstance());

        executionContext = ExecutionContextManager.get().createContext();
        AbstractPage.setElementTimeout(Environment.get().getElementTimeout());
        AbstractPage.setPageTimeout(Environment.get().getPageTimeout());
        AbstractPage.setIntervalTimeout(Environment.get().getElementTimeoutInterval());
    }

    @BeforeTest
    public void beforeTest(ITestContext iTestContext) {
        browser = executionContext.getBrowser();
        browser.setPageLoadTimeout(Environment.get().getPageTimeout());
        browser.maximize();
        browser.get(Environment.get().getAppURL());
        logger = Logger.init(iTestContext.getName());
        logger.startTestSession(iTestContext.getName());
    }

    @AfterTest
    public void afterTest(ITestContext iTestContext) {
        browser.quit();
        ExecutionContextManager.get().releaseExecutionContext();
        logger.endTestSession(iTestContext.getName());
        logger.release();
    }

    @BeforeMethod
    public void beforeMethod(ITestContext iTestContext, Method method) {
        logger.startTestMethod(method.getName());
    }

    @AfterMethod
    public void afterMethod(ITestContext iTestContext, Method method) {
        //logger.endTestMethod(method.getName());
    }

}