package selenium.logger;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.appender.ConsoleAppender;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.LoggerConfig;
import org.apache.logging.log4j.core.layout.PatternLayout;
import org.bouncycastle.util.encoders.Base64Encoder;
import org.openqa.selenium.WebDriverException;
import selenium.common.TestDescription;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * TestLogger
 */
public class TestLogger {

    /**
     * Folder where extended logs should be placed
     */
    private final String LOGS_FOLDER = "target/selenium-logs/";
    private final String TEST_LOGS_FOLDER = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(new Date()) + "/";

    /**
     * Substring of screenshot in message
     */
    private final String SCREEN_SHOT_ID = "[screenshot:";

    /**
     * Path to folder with screenshot images
     */
    private final String LOG_IMG_FOLDER = LOGS_FOLDER + "images/";

    private final String COMPONENT_MSG_ID = "[COMPONENT MSG]";
    private final int idx;
    private boolean hasFails = false;
    private boolean hasWarns = false;
    private Logger consoleLogger;

    TestLogger(TestDescription testDescription, int idx) {
        hasFails = false;
        hasWarns = false;
        this.idx = idx;

        String loggerName = "selenium.tests." + idx;

        //Order is important
        LoggerContext loggerContext = (LoggerContext) LogManager.getContext(true);
        Configuration configuration = loggerContext.getConfiguration();
        PatternLayout patternLayout = PatternLayout.createDefaultLayout(configuration);
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

    public int getIdx() {
        return idx;
    }

    /**
     * Were fails within testISF34 or not
     *
     * @return true if method fail was called at least once
     */
    public boolean hasFails() {
        return hasFails;
    }

    /**
     * Were warns within testISF34 or not
     *
     * @return true if method warn was called at least once
     */
    public boolean hasWarns() {
        return hasWarns;
    }

    /**
     * Logs in message with Component level id
     *
     * @param message Message
     * @see #COMPONENT_MSG_ID
     */
    public void componentInfo(String message) {
        debug(COMPONENT_MSG_ID + message);
    }

    /**
     * Logs in message with Component level id
     *
     * @param message Message
     * @see #COMPONENT_MSG_ID
     */
    public void componentInfo(String message, String screenShotBase64) {
        componentInfo(message + processScreenShot(screenShotBase64));
    }

    /**
     * logs info message
     *
     * @param message Message
     */
    public void info(String message) {
        consoleLogger.info(message);
    }

    /**
     * logs debug level message
     *
     * @param message Message
     */
    public void debug(String message) {
        consoleLogger.debug(message);
    }

    /**
     * logs warning message
     *
     * @param message Message
     */
    public void warn(String message) {
        hasWarns = true;
        consoleLogger.warn(message);
    }

    /**
     * logs error level message
     *
     * @param message Message
     */
    public void fail(String message) {
        hasFails = true;
        consoleLogger.error(message);
    }

    /**
     * logs fatal level message
     *
     * @param message Message
     */
    public void error(String message) {
        hasFails = true;
        consoleLogger.fatal(message);
        throw new UnloggingException(message);
    }

    /**
     * logs pass level message (the same as info level but in layout level will be transformed to PASS and highlighted with green)
     *
     * @param message Message
     */
    public void success(String message) {
        consoleLogger.info("[PASS]" + message);
    }

    /**
     * logs info level message and places screen shot in log
     *
     * @param message          Text message
     * @param screenShotBase64 Screen shot in BASE64 format
     * @see org.openqa.selenium.TakesScreenshot;
     */
    public void info(String message, String screenShotBase64) {
        info(message + processScreenShot(screenShotBase64));
    }

    /**
     * logs debug level message and places screen shot in log
     *
     * @param message          Text message
     * @param screenShotBase64 Screen shot in BASE64 format
     * @see org.openqa.selenium.TakesScreenshot;
     */
    public void debug(String message, String screenShotBase64) {
        debug(message + processScreenShot(screenShotBase64));
    }

    /**
     * logs warning level message and places screen shot in log
     *
     * @param message          Text message
     * @param screenShotBase64 Screen shot in BASE64 format
     * @see org.openqa.selenium.TakesScreenshot;
     */
    public void warn(String message, String screenShotBase64) {
        warn(message + processScreenShot(screenShotBase64));
    }

    /**
     * logs error level message and places screen shot in log
     *
     * @param message          Text message
     * @param screenShotBase64 Screen shot in BASE64 format
     * @see org.openqa.selenium.TakesScreenshot;
     */
    public void fail(String message, String screenShotBase64) {
        fail(message + processScreenShot(screenShotBase64));
    }

    /**
     * logs fatal level message and places screen shot in log
     *
     * @param message          Text message
     * @param screenShotBase64 Screen shot in BASE64 format
     * @see org.openqa.selenium.TakesScreenshot;
     */
    public void error(String message, String screenShotBase64) {
        error(message + processScreenShot(screenShotBase64));
    }

    /**
     * logs success level message and places screen shot in log
     *
     * @param message          Text message
     * @param screenShotBase64 Screen shot in BASE64 format
     * @see org.openqa.selenium.TakesScreenshot;
     * @see #success(String)
     */
    public void success(String message, String screenShotBase64) {
        success(message + processScreenShot(screenShotBase64));
    }

    /**
     * Processes screen shot info. Creates screen shot file in log folder
     *
     * @param screenShotBase64 Screen shot in BASE64 format
     * @return Screen shot id with file path info (should be formatted with layout)
     */
    private String processScreenShot(String screenShotBase64) {

        if (screenShotBase64 == null) return "\nUnable to get screenshot";

        try {
            File dir = new File(LOG_IMG_FOLDER);
            boolean successful = dir.mkdirs();
            if (!successful) {
                throw new FileNotFoundException();
            }
        } catch (FileNotFoundException e) {
            consoleLogger.error(LOG_IMG_FOLDER + " can't be created!");
            e.printStackTrace();
        }

        OutputStream stream = null;
        try {
            File logScreenshot = File.createTempFile("screenshot", ".png", new File(LOG_IMG_FOLDER));
            logScreenshot.createNewFile();
            stream = new FileOutputStream(logScreenshot);
            new Base64Encoder().decode(screenShotBase64, stream);
            return SCREEN_SHOT_ID + new File(LOG_IMG_FOLDER).getName() + "/" + logScreenshot.getName() + "]";
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