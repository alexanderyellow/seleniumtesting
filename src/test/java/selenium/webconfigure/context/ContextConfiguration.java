package selenium.webconfigure.context;

import org.openqa.selenium.Platform;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import selenium.webconfigure.Browser.BrowserName;
import selenium.webconfigure.BrowserConfig;

/**
 * Read context properties from config file and set ExecutionContext
 */
@Configuration
@PropertySource({"file:src/test/resources/browserconfig.properties", "file:src/test/resources/environment.properties"})
public class ContextConfiguration {

    private static final String BROWSER_NAME = "browser.name";
    private static final String WEBDRIVER = "webdriver.driver";
    private static final String PLATFORM = "platform";
    private static final String JAVASCRIPT_ENABLED = "javascript.enabled";

    @Autowired
    private Environment env;

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    @Scope("prototype")
    public BrowserConfig browserConfig() {
        BrowserName browserName = BrowserName.fromString(env.getProperty(BROWSER_NAME));
        String webdriver = env.getProperty(WEBDRIVER);
        Platform platform = Platform.fromString(env.getProperty(PLATFORM));
        boolean javascriptEnabled = Boolean.valueOf(env.getProperty(JAVASCRIPT_ENABLED));

        selenium.webconfigure.context.Environment environment = selenium.webconfigure.context.Environment.get();
        environment.setAppURL(env.getProperty("app.url"));
        environment.setElementTimeout(Integer.parseInt(env.getProperty("element.timeout")));
        environment.setPageTimeout(Integer.parseInt(env.getProperty("page.timeout")));
        environment.setElementTimeoutInterval(Integer.parseInt(env.getProperty("element.timeout.interval")));

        return new BrowserConfig(webdriver, browserName, javascriptEnabled, platform);
    }

}