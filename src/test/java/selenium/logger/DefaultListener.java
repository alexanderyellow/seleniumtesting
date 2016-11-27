package selenium.logger;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import selenium.helpers.TestUtils;
import selenium.webconfigure.context.ExecutionContext;
import selenium.webconfigure.context.ExecutionContextManager;

/**
 * DefaultListener
 */
public class DefaultListener implements ITestListener {

    private final static DefaultListener _instance = new DefaultListener();

    private DefaultListener() {
    }

    public static DefaultListener getInstance() {
        return _instance;
    }

    public void onTestStart(ITestResult iTestResult) {
    }

    public void onTestSuccess(ITestResult iTestResult) {
        ExecutionContext executionContext = ExecutionContextManager.get().getExecutionContext();
        String screenshot = executionContext.getBrowser().getScreenshot();

        Logger.get().success("Test method '" + iTestResult.getMethod().getMethodName() + "':", screenshot);
        Logger.get().info("***************** PASSED *****************\n");
    }

    public void onTestFailure(ITestResult iTestResult) {
        ExecutionContext executionContext = ExecutionContextManager.get().getExecutionContext();
        String screenshot = executionContext.getBrowser().getScreenshot();

        Logger.get().fail("Test method '" + iTestResult.getMethod().getMethodName() + "' failed:", screenshot);
        Logger.get().fail(TestUtils.getThrowableFullDescription(iTestResult.getThrowable()));
        Logger.get().info("***************** FAILD *****************\n");
    }

    public void onTestSkipped(ITestResult iTestResult) {
        Logger.get().fail("Test method '" + iTestResult.getMethod().getMethodName() + "'");
        Logger.get().fail(TestUtils.getThrowableFullDescription(iTestResult.getThrowable()));
        Logger.get().info("***************** SKIPPED *****************\n");
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
    }

    public void onStart(ITestContext iTestContext) {
    }

    public void onFinish(ITestContext iTestContext) {
    }

}