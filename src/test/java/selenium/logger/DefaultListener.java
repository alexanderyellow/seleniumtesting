package selenium.logger;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
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

        Logger.get().success("Test method '" + iTestResult.getMethod().getMethodName() + "' passed:", screenshot);
    }

    public void onTestFailure(ITestResult iTestResult) {
        ExecutionContext executionContext = ExecutionContextManager.get().getExecutionContext();
        String screenshot = executionContext.getBrowser().getScreenshot();

        synchronized ("synch") {
            Logger.get().fail("Test method '" + iTestResult.getMethod().getMethodName() + "' failed:", screenshot);
        }

        synchronized ("synch") {
            iTestResult.getThrowable().printStackTrace();
        }
    }

    public void onTestSkipped(ITestResult iTestResult) {
        ExecutionContext executionContext = ExecutionContextManager.get().getExecutionContext();
        String screenshot = executionContext.getBrowser().getScreenshot();

        synchronized ("synch") {
            Logger.get().fail("Test method '" + iTestResult.getMethod().getMethodName() + "' skipped:", screenshot);
        }

        synchronized ("synch") {
            iTestResult.getThrowable().printStackTrace();
        }
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
        System.out.println("--------onTestFailedButWithinSuccessPercentage");
    }

    public void onStart(ITestContext iTestContext) {
    }

    public void onFinish(ITestContext iTestContext) {
    }

}