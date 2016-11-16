package selenium.logger;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.Appender;
import org.apache.logging.log4j.core.Layout;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.appender.ConsoleAppender;
import org.apache.logging.log4j.core.appender.FileAppender;
import org.apache.logging.log4j.core.appender.FileManager;
import org.apache.logging.log4j.core.config.*;
import org.apache.logging.log4j.core.layout.PatternLayout;

/**
 * Created by alexander on 13.11.16.
 */
public class TestLogger {

    //TODO check appenders existing and create default appenders
    private enum LogAppenders {
        CONSOLE("DefaultAppender"),
        FILE("TestAppender");

        private String value;

        LogAppenders(String value) {
            this.value = value;
        }
    }

    private static final String LOG_CONFIG_FILE = "src/test/resources/log4j2-test.xml";
    /*private LoggerContext loggerContext;
    private Configuration configuration;*/
  //  private ConsoleAppender consoleAppender;
    private FileAppender fileAppender;
    private LoggerConfig consoleLoggerConfig;
    private LoggerConfig fileLoggerConfig;
    private Logger consoleLogger;
    private Logger fileLogger;

    public TestLogger() {
        //Order is important
        final LoggerContext loggerContext = (LoggerContext) LogManager.getContext(true);
        final Configuration configuration = loggerContext.getConfiguration();
        final PatternLayout layout = PatternLayout.createDefaultLayout(configuration);
        final ConsoleAppender consoleAppender = ConsoleAppender.createDefaultAppenderForLayout(layout);
        consoleAppender.start();
        configuration.addAppender(consoleAppender);

    //    fileAppender = new FileAppender("FileAppender", layout, null, null, "report.txt", false, false, null);

        //LoggerConfig name and Logger name must match
        consoleLoggerConfig = new LoggerConfig("name", Level.INFO, false);
        consoleLoggerConfig.addAppender(consoleAppender, null, null);

    //    consoleLoggerConfig.start();

        configuration.addLogger("name", consoleLoggerConfig);
        loggerContext.updateLoggers();

        // Get a reference for logger
        consoleLogger = loggerContext.getLogger("name");

        consoleLogger.info("Output");
    }

    /*public LoggerContext getLoggerContext() {
        return loggerContext;
    }

    public Configuration getConfiguration() {
        return configuration;
    }*/

  /*  public ConsoleAppender getConsoleAppender() {
        return consoleAppender;
    }*/

    public FileAppender getFileAppender() {
        return fileAppender;
    }

    public LoggerConfig getConsoleLoggerConfig() {
        return consoleLoggerConfig;
    }

    public LoggerConfig getFileLoggerConfig() {
        return fileLoggerConfig;
    }

    public Logger getConsoleLogger() {
        return consoleLogger;
    }

    public Logger getFileLogger() {
        return fileLogger;
    }

    /**
     * Processes screen shot info. Creates screen shot file in log folder
     *
     * @param screenShotBase64 Screen shot in BASE64 format
     * @return Screen shot id with file path info (should be formatted with layout)
     */
    /*private String processScreenShot(String screenShotBase64) {

        if (screenShotBase64 == null) return "\nUnable to get screenshot";

        OutputStream stream = null;
        try {
            File logScreenshot = File.createTempFile("screenshot", ".png", new File(LOG_IMG_FOLDER));
            logScreenshot.createNewFile();
            stream = new FileOutputStream(logScreenshot);
            stream.write(new Base64Encoder().decode(screenShotBase64));
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
    }*/

}