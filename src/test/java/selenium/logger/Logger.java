package selenium.logger;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.appender.ConsoleAppender;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.LoggerConfig;
import org.apache.logging.log4j.core.layout.PatternLayout;
import org.bouncycastle.util.encoders.Base64Encoder;
import org.openqa.selenium.WebDriverException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Logger
 */
public class Logger {

    /**
     * Instance of logger
     */
    private static Logger _instance = null;
    /**
     * Folder where extended logs should be placed
     */
    private final String LOGS_FOLDER = "target/selenium-logs/";
    /**
     * Substring of screenshot in message
     */
    private final String SCREEN_SHOT_ID = "[screenshot: ";
    /**
     * Path to folder with screenshot images
     */
    private final String LOG_IMG_FOLDER = LOGS_FOLDER + "images/";
    /**
     * log4j logger
     */
    private org.apache.logging.log4j.Logger consoleLogger;

    /**
     * Initialize logger
     */
    private Logger(String loggerName) {
        //Order is important
        LoggerContext loggerContext = (LoggerContext) LogManager.getContext(true);
        Configuration configuration = loggerContext.getConfiguration();
        PatternLayout patternLayout = PatternLayout.createLayout("%d{HH:mm:ss.SSS} %-5level %logger{36} - %msg%n",
                null, configuration, null, null, true, false, null, null);
        ConsoleAppender consoleAppender = ConsoleAppender.createDefaultAppenderForLayout(patternLayout);
        consoleAppender.start();
        configuration.addAppender(consoleAppender);

        //LoggerConfig name and Logger name must match
        LoggerConfig consoleLoggerConfig = new LoggerConfig(loggerName, Level.DEBUG, false);
        consoleLoggerConfig.addAppender(consoleAppender, null, null);

        configuration.addLogger(loggerName, consoleLoggerConfig);
        loggerContext.updateLoggers();

        consoleLogger = loggerContext.getLogger(loggerName);
    }

    /**
     * Initializes new logger
     *
     * @return instance of logger
     */
    public static Logger init(String loggerName) {
        if (_instance == null) {
            _instance = new Logger(loggerName);
        }

        return _instance;
    }

    public static Logger get() {
        if (_instance == null) throw new RuntimeException("Logger not initialized");
        return _instance;
    }

    /**
     * Release logger
     */
    public void release() {
        _instance = null;
    }

    public void startTestSession(String testName) {
        consoleLogger.info("Start test: " + testName);
    }

    public void endTestSession(String testName) {
        consoleLogger.info("End test: " + testName);
    }

    public void endTestMethod(String methodName, String status) {
        consoleLogger.info("End test method: " + methodName + " :[STATUS:" + status + "]");
    }

    public void startTestMethod(String methodName) {
        consoleLogger.info("********** Start test method: " + methodName + " **********");
    }

    public void info(String message) {
        consoleLogger.info(message);
    }

    public void debug(String message) {
        consoleLogger.debug(message);
    }

    public void error(String message) {
        consoleLogger.error(message);
    }

    public void fail(String message, String screenShotBase64) {
        consoleLogger.error(message + " " + processScreenShot(screenShotBase64));
    }

    public void fail(String message) {
        consoleLogger.error(message);
    }

    public void success(String message, String screenShotBase64) {
        consoleLogger.info(message + " " + processScreenShot(screenShotBase64));
    }

    /**
     * Processes screen shot info. Creates screen shot file in log folder
     *
     * @param screenShotBase64 Screen shot in BASE64 format
     * @return Screen shot id with file path info (should be formatted with layout)
     */
    private String processScreenShot(String screenShotBase64) {

        if (screenShotBase64 == null) return "\nUnable to get screenshot";
        File dir = new File(LOG_IMG_FOLDER);

        if (!dir.exists()) {
            dir.mkdirs();
        }

        OutputStream stream = null;
        try {
            File logScreenshot = File.createTempFile("screenshot", ".png", new File(LOG_IMG_FOLDER));
            logScreenshot.createNewFile();
            stream = new FileOutputStream(logScreenshot);
            new Base64Encoder().decode(screenShotBase64, stream);
            return SCREEN_SHOT_ID + LOG_IMG_FOLDER + logScreenshot.getName() + "]";
        } catch (IOException e) {
            throw new WebDriverException(e);
        } finally {
            if (stream != null) {
                try {
                    stream.close();
                } catch (IOException e) {
                    // Nothing to do
                }
            }
        }
    }

}