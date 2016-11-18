package selenium.logger;

import org.testng.IConfigurationListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

/**
 * Created by alexander.
 */
public class DefaultListener {//implements ITestListener, IConfigurationListener {

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
    /*public void onTestSuccess(ITestResult iTestResult) {
        if (Logger.getLogger().hasTestSession()) {
            if (!Logger.get().hasFails()) {
                ExecutionContext executionContext = ExecutionContextManager.getContext(iTestResult.getMethod()
                        .getConstructorOrMethod().getMethod());
                Browser browser = executionContext == null ? null : executionContext.getBrowser();
                Logger.get().success("Test PASS", browser == null ? null : browser.getScreenShot());
            }
        }
    }

    *//**
     * Places message about critical error in Logger with page screenshot
     *
     * @param iTestResult iTestResult in TestNG
     * @see org.testng.ITestListener
     *//*
    public void onTestFailure(ITestResult iTestResult) {
        if (iTestResult.getMethod().isTest()) {
            ExecutionContext executionContext = null;
            if (ExecutionContextManager.hasContext(iTestResult.getMethod()
                    .getConstructorOrMethod().getMethod())) {
                executionContext = ExecutionContextManager.getContext(iTestResult.getMethod()
                        .getConstructorOrMethod().getMethod());
            }

            Browser browser = executionContext == null ? null : executionContext.getBrowser();

            if (Logger.getLogger().hasTestSession()) {
                printTrace();
                if (iTestResult.getThrowable() != null) {

                    if (browser != null) {
                        Logger.get().debug("Page source on error:\n" + browser.getPageSource());
                    }

                    Logger.get().debug("There is critical error in test:\n" +
                            "\n" +
                            "Exception:\n" +
                            TestUtils.getThrowableFullDescription(iTestResult.getThrowable()));

                    if (iTestResult.getThrowable() instanceof UIInteractionException) {

                        if (!(iTestResult.getThrowable() instanceof UnloggingException)) {
                            Logger.get().fail("There is critical error in test:\n" +
                                            "\n" +
                                            "Exception:\n" +
                                            ((UIInteractionException) iTestResult.getThrowable()).describeThrowable(),
                                    (browser != null ? browser.getScreenShot() : null));
                        }
                    } else {
                        if (!(iTestResult.getThrowable() instanceof UnloggingException)) {
                            Logger.get().fail("There is critical error in test:\n" +
                                            "\n" +
                                            "Exception:\n" +
                                            TestUtils.getThrowableFullDescription(iTestResult.getThrowable()),
                                    (browser != null ? browser.getScreenShot() : null));
                        }
                    }
                }
                //Logger.getLogger().endTestSession();
            }
            lastKnownThrowable = null;
        } else {
            lastKnownThrowable = iTestResult.getThrowable();
        }

    }

    *//**
     * Places message about testISF34 skipped in Logger
     *
     * @param iTestResult iTestResult in TestNG
     * @see org.testng.ITestListener
     *//*
    public void onTestSkipped(ITestResult iTestResult) {

        Thread testThread;

        ExecutionContext executionContext = null;
        if (ExecutionContextManager.hasContext(iTestResult.getMethod()
                .getConstructorOrMethod().getMethod())) {
            executionContext = ExecutionContextManager.getContext(iTestResult.getMethod()
                    .getConstructorOrMethod().getMethod());
        }

        if (executionContext != null && executionContext.getThread() != null) {
            testThread = executionContext.getThread();
        } else {
            testThread = Thread.currentThread();
        }

        if (!Logger.getLogger().hasTestSession(testThread)) {
            TestDescription testDescription = new TestDescription();
            testDescription.setName((iTestResult.getTestName() == null ?
                    iTestResult.getMethod().getConstructorOrMethod().getName() : iTestResult.getTestName()) + " SKIPPED");
            testDescription.setClassName(iTestResult.getTestClass().getRealClass().getName());
            testDescription.setDescription("NO DESCRIPTION AVAILABLE");
            Logger.getLogger().startNewTestSession(testDescription);
        }
        printTrace();
        Logger.get(testThread).fail("Test " + iTestResult.getMethod().toString() + " is skipped\n" +
                TestUtils.getThrowableFullDescription(
                        (iTestResult.getThrowable()) == null ? lastKnownThrowable : iTestResult.getThrowable())
        );
        //Logger.getLogger().endTestSession(testThread);

    }

    *//**
     * Places message about errors in Logger with page screenshot
     *
     * @param iTestResult iTestResult in TestNG
     * @see org.testng.ITestListener
     *//*
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
        if (Logger.getLogger().hasTestSession()) {
            printTrace();
            ExecutionContext executionContext = null;
            if (ExecutionContextManager.hasContext(iTestResult.getMethod()
                    .getConstructorOrMethod().getMethod())) {
                executionContext = ExecutionContextManager.getContext(iTestResult.getMethod()
                        .getConstructorOrMethod().getMethod());
            }
            Browser browser = executionContext == null ? null : executionContext.getBrowser();
            Logger.get().fail("There is critical error in test:\n" +
                            TestUtils.getThrowableFullDescription(iTestResult.getThrowable()),
                    (browser != null ? browser.getScreenShot() : null));
        }
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
            //    Logger.get().debug("LISTENER INVOKED:\n" + TestUtils.getThrowableFullDescription(t));
        }
    }*/

}
