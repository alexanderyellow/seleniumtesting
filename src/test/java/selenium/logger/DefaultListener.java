package selenium.logger;

import org.testng.IConfigurationListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import selenium.common.TestDescription;
import selenium.helpers.TestUtils;
import selenium.webconfigure.Browser;
import selenium.webconfigure.context.ExecutionContext;
import selenium.webconfigure.context.ExecutionContextManager;

/**
 * DefaultListener
 */
public class DefaultListener implements ITestListener, IConfigurationListener {

    private final static DefaultListener _instance = new DefaultListener();
    private Throwable lastKnownThrowable = null;

    public DefaultListener() {
    }

    public static DefaultListener getInstance() {
        return _instance;
    }

    public void onTestStart(ITestResult iTestResult) {
    }


    /**
     * On testISF34 success validate not critical errors
     *
     * @param iTestResult iTestResult in TestNG
     * @see org.testng.ITestListener
     */
    public void onTestSuccess(ITestResult iTestResult) {
        if (!Logger.get().hasFails()) {
            ExecutionContext executionContext = ExecutionContextManager.get().getExecutionContext();

            if (executionContext != null) {
                Browser browser = executionContext.getBrowser();
                Logger.get().success("Test PASS", browser.getScreenShot());
            }
        }
    }

    /**
     * Places message about critical error in Logger with page screenshot
     *
     * @param iTestResult iTestResult in TestNG
     * @see org.testng.ITestListener
     */
    public void onTestFailure(ITestResult iTestResult) {
        if (iTestResult.getMethod().isTest()) {
            ExecutionContext executionContext = ExecutionContextManager.get().getExecutionContext();
            Browser browser = executionContext.getBrowser();

//            printTrace();
            if (iTestResult.getThrowable() != null) {
                if (browser != null) {
                    Logger.get().debug("Page source on error:\n" + browser.getPageSource());
                }
            }

            Logger.getLogger().endTestSession();
            lastKnownThrowable = null;
        } else {
            lastKnownThrowable = iTestResult.getThrowable();
        }
    }

    /**
     * Places message about skipped test in Logger
     *
     * @param iTestResult iTestResult in TestNG
     * @see org.testng.ITestListener
     */
    public void onTestSkipped(ITestResult iTestResult) {

        TestDescription testDescription = new TestDescription();
        testDescription.setName((iTestResult.getTestName() == null ?
                iTestResult.getMethod().getConstructorOrMethod().getName() : iTestResult.getTestName()) + " SKIPPED");
        testDescription.setClassName(iTestResult.getTestClass().getRealClass().getName());
        testDescription.setDescription("NO DESCRIPTION AVAILABLE");
        Logger.getLogger().startNewTestSession(testDescription);

        printTrace();
        Logger.get().fail("Test " + iTestResult.getMethod().toString() + " is skipped\n" +
                TestUtils.getThrowableFullDescription(
                        (iTestResult.getThrowable()) == null ? lastKnownThrowable : iTestResult.getThrowable())
        );

        Logger.getLogger().endTestSession();
    }

    /**
     * Places message about errors in Logger with page screenshot
     *
     * @param iTestResult iTestResult in TestNG
     * @see org.testng.ITestListener
     */
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
        printTrace();

        ExecutionContext executionContext = ExecutionContextManager.get().getExecutionContext();
        Browser browser = executionContext.getBrowser();
        Logger.get().fail("There is critical error in test:\n" +
                        TestUtils.getThrowableFullDescription(iTestResult.getThrowable()),
                (browser != null ? browser.getScreenShot() : null));
    }

    public void onStart(ITestContext iTestContext) {
    }

    public void onFinish(ITestContext iTestContext) {
    }

    public void onConfigurationSuccess(ITestResult iTestResult) {
    }

    public void onConfigurationFailure(ITestResult iTestResult) {
        iTestResult.setStatus(ITestResult.FAILURE);
        onTestFailure(iTestResult);
    }

    public void onConfigurationSkip(ITestResult iTestResult) {
    }

    private void printTrace() {
        try {
            throw new RuntimeException();
        } catch (Throwable t) {
            Logger.get().debug("LISTENER INVOKED:\n" + TestUtils.getThrowableFullDescription(t));
        }
    }

}