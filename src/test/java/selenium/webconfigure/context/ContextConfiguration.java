package selenium.webconfigure.context;

import org.openqa.selenium.Platform;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import selenium.webconfigure.Browser;
import selenium.webconfigure.BrowserConfig;
import selenium.webconfigure.Browser.BrowserName;

import java.lang.reflect.Method;

/**
 * Created by alexander.
 * <p>
 * Read context properties from config file and set ExecutionContext
 */
@Configuration
@PropertySource("file:src/test/resources/browserconfig.properties")
public class ContextConfiguration {

    private static final String BROWSER_NAME = "browser.name";
    private static final String WEBDRIVER = "webdriver.driver";
    private static final String PLATFORM = "platform";
    private static final String JAVASCRIPT_ENABLED = "javascript.enabled";

    private ExecutionContext executionContext = null;

    @Autowired
    private Environment env;

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    @Scope("prototype")
    public ExecutionContext browserConfig(Method method) {
        executionContext = new ExecutionContext();

        BrowserName browserName = BrowserName.fromString(env.getProperty(BROWSER_NAME));
        String webdriver = env.getProperty(WEBDRIVER);
        Platform platform = Platform.fromString(env.getProperty(PLATFORM));
        boolean javascriptEnabled = Boolean.valueOf(env.getProperty(JAVASCRIPT_ENABLED));

        BrowserConfig browserConfig = new BrowserConfig(webdriver, browserName, javascriptEnabled, platform);
        Browser browser = new Browser(browserConfig);
        executionContext.setBrowser(browser);
        executionContext.setMethod(method);
        executionContext.setThread(Thread.currentThread());

        return executionContext;
    }

    /**
     * Check the test method whether has context
     * @param method test method
     * @return result
     */
    private boolean hasContext(Method method) {
        if(executionContext != null) {
            if (executionContext.getMethod() != null && executionContext.getMethod().equals(method)) {
                return true;
            }
        }

        return false;
    }

}