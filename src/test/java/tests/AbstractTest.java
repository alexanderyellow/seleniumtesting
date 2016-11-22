package tests;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestRunner;
import org.testng.annotations.*;
import selenium.common.TestDescription;
import selenium.logger.DefaultListener;
import selenium.logger.Logger;
import selenium.webconfigure.Browser;
import selenium.webconfigure.context.ExecutionContextManager;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

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
        Element.setElementTimeoutInterval(Environment.get().getElementTimeoutInterval());
        Element.addAjaxLoadManager(new AjaxProcessingManager());*/
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