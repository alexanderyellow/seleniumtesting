package tests;

import org.testng.ITestContext;
import org.testng.TestRunner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import selenium.common.TestDescription;
import selenium.logger.Logger;
import selenium.webconfigure.Browser;
import selenium.webconfigure.context.ExecutionContextManager;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aliaksandr_Sheikin on 11/17/2016.
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
    //    tr.addListener(DefaultListener.getInstance());

        List<String> groups = new ArrayList<String>();


        TestDescription testDescription = new TestDescription()
                .setClassName(null)
                .setDescription(null)
                .setIsConfig(false)
                .setMethodName(null)
                .setName(null);

        logger = Logger.init();
        logger.startNewTestSession(testDescription);

        /*Element.setElementTimeout(Environment.get().getElementTimeout());
        Element.setElementTimeoutInterval(Environment.get().getElementTimeoutInterval());
        Element.addAjaxLoadManager(new AjaxProcessingManager());*/
    }

    /**
     * Init logging of current test
     */
    @BeforeMethod
    public void beforeMethod(Method method) {
    //    initLogger(method);
        //ExecutionContextManager.createContext(method);
        System.out.printf("create context");
        browser = ExecutionContextManager.createContext(method).getBrowser();
        browser.maximize();
    }

    /**
     * Finish logging of actual test
     */
    @AfterMethod
    public void afterMethod(Method method) {
        /*if (ExecutionContextManager.hasContext(method)) {
            ExecutionContextManager.releaseContext(ExecutionContextManager.getOrCreateContext(method));
        }*/
        logger.endTestSession();
    }

}