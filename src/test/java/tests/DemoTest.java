package tests;

import junit.framework.Assert;
import org.testng.annotations.Test;

/**
 * Created by alexander on 03.09.16.
 */
public class DemoTest extends AbstractTest {

    @Test
    public void test1() {
        //System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver");

     //   BrowserConfig browserConfig = new BrowserConfig();
     //   Browser browser = new Browser();

    //    System.out.println("lalala: " + executionContext.getBrowserName().toString());

    //    TestLogger testLogger = new TestLogger("name");


        Assert.assertTrue(true);
    //    testLogger.getConsoleLogger().info("Lalala");

        /* Get actual class name to be printed on */
        /*LoggerConfiguration log = LogManager.getLogger("lala");
        log.trace("LoggerConfiguration info");*/

        //Logger logger = LogManager.getRootLogger();
        //logger.trace("LoggerConfiguration File Defined To Be :: " + System.getProperty("log4j.configurationFile"));

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