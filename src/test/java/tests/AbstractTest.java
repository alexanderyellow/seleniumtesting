package tests;

import org.testng.ITestContext;
import org.testng.TestRunner;
import org.testng.annotations.*;
import selenium.logger.CustomListener;
import selenium.logger.Logger;
import selenium.webconfigure.Browser;

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
        tr.addListener(CustomListener.getInstance());

        /*Element.setElementTimeout(Environment.get().getElementTimeout());
        Element.setElementTimeoutInterval(Environment.get().getElementTimeoutInterval());
        Element.addAjaxLoadManager(new AjaxProcessingManager());*/
    }

    @BeforeTest
    public void beforeTest(ITestContext iTestContext) {
        logger = Logger.init(iTestContext.getName());
        logger.startTestSession(iTestContext.getName());
    }

    @AfterTest
    public void afterTest(ITestContext iTestContext) {
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

   /* @BeforeTest
    public void beforeTest(ITestContext iTestContext) {
        TestDescription testDescription = new TestDescription()
                .setClassName(iTestContext.getName())
                .setDescription(null)
                .setMethodName(null)
                .setName(null);

        logger = Logger.init();
        logger.startTestSession(testDescription);
        browser = ExecutionContextManager.get().createContext().getBrowser();
        browser.maximize();
    //    browser.get("google.com");
    }

    @BeforeMethod
    public void beforeMethod(ITestContext iTestContext, Method method) {
        System.out.println("Method: " + method.getAnnotation(Test.class).description());
    }


    @AfterTest
    public void afterMethod() {
        *//*if (ExecutionContextManager.hasContext(method)) {
            ExecutionContextManager.releaseContext(ExecutionContextManager.createContext(method));
        }*//*
        browser.quit();
        logger.endTestSession();
    }*/

}