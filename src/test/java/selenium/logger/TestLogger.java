package selenium.logger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.Appender;
import org.apache.logging.log4j.core.Layout;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.appender.ConsoleAppender;
import org.apache.logging.log4j.core.appender.FileAppender;
import org.apache.logging.log4j.core.config.*;
import org.apache.logging.log4j.core.layout.PatternLayout;

/**
 * Created by alexander on 13.11.16.
 */
public class TestLogger {

    private static final String LOG_CONFIG_FILE = "src/test/resources/log4j2-test.xml";
    private ConfigurationFactory factory;
    private ConfigurationSource configurationSource;
    private LoggerContext loggerContext;
    private Configuration configuration;
    private ConsoleAppender consoleAppender;
    private FileAppender fileAppender;
    private LoggerConfig consoleLoggerConfig;
    private LoggerConfig fileLoggerConfig;
    private Logger consoleLogger;
    private Logger fileLogger;

    public TestLogger() {
        /*factory = XmlConfigurationFactory.getInstance();

        try {
            configurationSource = new ConfigurationSource(new FileInputStream(new File(LOG_CONFIG_FILE)));
        } catch (IOException e) {
            e.printStackTrace();
        }


        loggerContext = (LoggerContext) LogManager.getContext(false);//new LoggerContext("DefaultLogger");

        configuration = factory.getConfiguration(loggerContext, configurationSource);*/

        final LoggerContext ctx = (LoggerContext) LogManager.getContext(false);
        final Configuration config = ctx.getConfiguration();

        final Layout layout = PatternLayout.createLayout(PatternLayout.SIMPLE_CONVERSION_PATTERN, null, config, null,
                null, true, true, null, null);

        Appender appender = ConsoleAppender.createDefaultAppenderForLayout(layout);/*createAppender("target/test.log", "false", "false", "File", "true",
                "false", "false", "4000", layout, null, "false", null, config);*/
        appender.start();

        config.addAppender(appender);

        AppenderRef ref = AppenderRef.createAppenderRef("Console", null, null);
        AppenderRef[] refs = new AppenderRef[]{ref};

        LoggerConfig loggerConfig = LoggerConfig.createLogger("false", "info", "org.apache.logging.log4j",
                "true", refs, null, config, null);

        loggerConfig.addAppender(appender, null, null);
        config.addLogger("org.apache.logging.log4j", loggerConfig);
        //    consoleAppender = (ConsoleAppender) configuration.getAppenders().get(LogAppenders.CONSOLE.value);
        //    fileAppender = (FileAppender)  configuration.getAppenders().get(LogAppenders.FILE.value);

        //  consoleLoggerConfig = configuration.getLoggerConfig("DefaultLogger");
        //  fileLoggerConfig = configuration.getLoggerConfig("TestLogger");

        /*PatternLayout layout= PatternLayout.createLayout("%m%n", null, null, Charset.defaultCharset(),false,false,null,null);
        Appender appender = ConsoleAppender.createAppender(layout, null, null, "CONSOLE_APPENDER", null, null);
        appender.start();

        appender = ConsoleAppender.createDefaultAppenderForLayout(PatternLayout.createDefaultLayout());
        consoleLoggerConfig = new LoggerConfig("com", Level.FATAL,false);
        consoleLoggerConfig.addAppender(appender,null,null);
        configuration.addLogger("com", loggerConfig);*/
    }

    public LoggerContext getLoggerContext() {
        return loggerContext;
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public ConsoleAppender getConsoleAppender() {
        return consoleAppender;
    }

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

    //TODO check appenders existing and create default appenders
    private enum LogAppenders {
        CONSOLE("DefaultAppender"),
        FILE("TestAppender");

        private String value;

        LogAppenders(String value) {
            this.value = value;
        }
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