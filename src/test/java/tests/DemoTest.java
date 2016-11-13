package tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.testng.annotations.Test;
import selenium.webconfigure.BrowserConfig;
import selenium.webconfigure.context.ContextConfiguration;

/**
 * Created by alexander on 03.09.16.
 */
public class DemoTest {

    @Test
    public void test1() {
        //System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver");

        ApplicationContext context = new AnnotationConfigApplicationContext(ContextConfiguration.class);

        BrowserConfig browserConfig = (BrowserConfig) context.getBean("browserConfig");

        System.out.println("lalala: " + browserConfig.getBrowserName().toString());

        /* Get actual class name to be printed on */
        /*Logger log = LogManager.getLogger("lala");
        log.trace("Logger info");*/

        Logger logger = LogManager.getRootLogger();
        logger.trace("Configuration File Defined To Be :: " + System.getProperty("log4j.configurationFile"));

        /*DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        capabilities.setJavascriptEnabled(true);
        capabilities.setPlatform(Platform.MAC);
        capabilities.setBrowserName("chrome");

        *//*ChromeDriverService service = new ChromeDriverService.Builder()
                .usingAnyFreePort()
                .usingDriverExecutable()
                .build();*//*

        RemoteWebDriver webDriver = new ChromeDriver(capabilities);
    //    WebDriver webDriver = new RemoteWebDriver("http://127.0.0.1:9515", DesiredCapabilities.chrome());
        webDriver.get("http://www.google.com");

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        webDriver.close();
        webDriver.quit();*/

        /*Browser browser = new Browser();
        browser.createNew(BrowserNames.CHROME);*/
    }

}