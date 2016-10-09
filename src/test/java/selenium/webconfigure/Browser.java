package selenium.webconfigure;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

/**
 * Configure working with the Browser.
 * <p>
 * Created by alexander on 20.09.16.
 */
public class Browser {

    private static WebDriver webDriver;
    private final DesiredCapabilities capabilities;

    public Browser(DesiredCapabilities capabilities) {
        this.capabilities = capabilities;
    }

    public static Browser createNew(BrowserConfig browserConfig) {

        switch (browserConfig.getBrowserName()) {
            case CHROME:
                //System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver");
                DesiredCapabilities capabilities = DesiredCapabilities.chrome();
                capabilities.setJavascriptEnabled(true);
                capabilities.setPlatform(Platform.MAC);
                capabilities.setBrowserName("chrome");

                webDriver = new ChromeDriver();

                System.out.println("Chrome");
                break;
            case IE:
                System.out.println("IE");
                break;
            case FF:
                System.out.println("FF");
                break;
            default:
                System.out.println("Chrome");
                break;
        }

        return null;
    }

    private void initBrowser() {
    }

}