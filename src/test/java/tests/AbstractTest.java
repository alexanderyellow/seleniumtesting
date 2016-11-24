package tests;

import org.testng.ITestContext;
import org.testng.TestRunner;
import org.testng.annotations.*;
import selenium.common.Environment;
import selenium.logger.DefaultListener;
import selenium.logger.Logger;
import selenium.ui.Element;
import selenium.webconfigure.Browser;
import selenium.webconfigure.context.ExecutionContext;
import selenium.webconfigure.context.ExecutionContextManager;

import java.lang.reflect.Method;

/**
 *
 */
public class AbstractTest {

    /**
     * Test browser
     */
    protected Browser browser;
    protected Logger logger;

    @BeforeSuite
    public void beforeSuite(ITestContext iTestContext) {

        TestRunner tr = (TestRunner) iTestContext;
        tr.addListener(DefaultListener.getInstance());

        /*Element.setElementTimeout(Environment.get().getElementTimeout());
        Element.setElementTimeoutInterval(Environment.get().getElementTimeoutInterval());*/
    }

    @BeforeTest
    public void beforeTest(ITestContext iTestContext) {
        ExecutionContext executionContext = ExecutionContextManager.get().createContext();
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