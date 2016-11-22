package selenium.logger;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.appender.ConsoleAppender;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.LoggerConfig;
import org.apache.logging.log4j.core.layout.PatternLayout;
import selenium.common.TestDescription;

/**
 * Created by alexander.
 */
public class Logger {

    static final String NEW_TEST_ID = "[NEW TEST]";
    static final String SELENIUM_PACKAGE = "selenium.tests";
    static final String END_TEST_ID = "[TEST END]";
    /**
     * Instance of logger
     */
    private static Logger _instance = null;
    private static TestLogger testLogger;
    private int idx = 1;
    /**
     * log4j logger
     */
    private org.apache.logging.log4j.Logger log4jLogger;

    /**
     * Initialize logger
     */
    private Logger() {
        String loggerName = "selenium.tests";

        //Order is important
        LoggerContext loggerContext = (LoggerContext) LogManager.getContext(true);
        Configuration configuration = loggerContext.getConfiguration();
        PatternLayout patternLayout = PatternLayout.createDefaultLayout(configuration);
        ConsoleAppender consoleAppender = ConsoleAppender.createDefaultAppenderForLayout(patternLayout);
        consoleAppender.start();
        configuration.addAppender(consoleAppender);

        //LoggerConfig name and Logger name must match
        LoggerConfig consoleLoggerConfig = new LoggerConfig(loggerName, Level.INFO, false);
        consoleLoggerConfig.addAppender(consoleAppender, null, null);

        configuration.addLogger(loggerName, consoleLoggerConfig);
        loggerContext.updateLoggers();

        log4jLogger = loggerContext.getLogger(loggerName);
    }

    /**
     * Initializes new logger
     *
     * @return instance of logger
     */
    public static Logger init() {
        if (_instance == null) {
            _instance = new Logger();
        }

        return _instance;
    }

    public static Logger getLogger() {
        if (_instance == null) throw new RuntimeException("Logger not initialized");
        return _instance;
    }

    /**
     * Get logger instance
     *
     * @return logger instance
     */
    public static TestLogger get() {
        return testLogger;
    }

    /**
     * Starts new session log
     *
     * @param testDescription Test description
     */
    public TestLogger startNewTestSession(TestDescription testDescription) {
        testLogger = new TestLogger(testDescription, idx++);

        String className = testDescription.getClassName();
        if (className != null) {
            int selStart = className.indexOf(SELENIUM_PACKAGE);

            if (selStart >= 0) {
                className = className.substring(selStart + SELENIUM_PACKAGE.length() + 1);
            }
        }

        log4jLogger.info(NEW_TEST_ID + ":[NAME:" + testDescription.getName() + "]:[CLASS:" + className + "]:" +
                "[DESC:" + testDescription.getDescription() + "]" +
                "[INDEX:" + testLogger.getIdx() + "]");

        return testLogger;
    }

    /**
     * Starts new session log
     */
    public synchronized void endTestSession() {
        TestLogger testLogger = get();

        String status = "pass";
        if (testLogger.hasFails()) {
            status = "fail";
        } else if (testLogger.hasWarns()) {
            status = "warn";
        }

        log4jLogger.info(END_TEST_ID + ":[STATUS:" + status + "]:[INDEX:" + testLogger.getIdx() + "]");
    }

}