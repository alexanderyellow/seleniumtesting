package tests;

import org.testng.ITestContext;
import org.testng.TestRunner;
import org.testng.annotations.*;
import selenium.logger.CustomListener;
import selenium.logger.Logger;
import selenium.webconfigure.Browser;

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
        Logger.init(iTestContext.getName());
    }

    @AfterTest
    public void afterTest() {
        Logger.get().release();
    }

   /* @BeforeTest
    public void beforeTest(ITestContext iTestContext) {
        TestDescription testDescription = new TestDescription()
                .setClassName(iTestContext.getName())
                .setDescription(null)
                .setMethodName(null)
                .setName(null);

        logger = Logger.init();
        logger.startNewTestSession(testDescription);
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