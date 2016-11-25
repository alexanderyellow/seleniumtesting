package selenium.ui.pages;

import com.google.common.base.Function;
import com.google.common.primitives.Booleans;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import selenium.webconfigure.Browser;

import java.util.concurrent.TimeUnit;

/**
 * Starting page
 */
public class MainPage extends HeaderComponentPage {

    public enum Sections {
        ELECTRONIC("Electronic");

        String value;

        Sections(String val) {
            this.value = val;
        }

        public String toString() {
            return value;
        }
    }

    @FindBy(css = "div#page_main_full")
    private WebElement sectionTable;

    @FindBy(css = "div#dv_6 div.main_head a")
    private WebElement electronicCell;

    public MainPage(Browser browser) {
        super(browser);
    }

    public boolean isOpened() {
        Wait<WebElement> wait = new FluentWait<WebElement>(sectionTable)
                .withTimeout(elementTimeout, TimeUnit.SECONDS)
                .pollingEvery(5, TimeUnit.SECONDS)
                .ignoring(NoSuchElementException.class);

        return wait.until(new Function<WebElement, Boolean>() {
            public Boolean apply(WebElement driver) {
                return sectionTable.isEnabled();
            }
        });
    }

    public ElectronicsPage chooseElectronicSection(Sections section) {

        switch (section) {
            case ELECTRONIC:
        }

        return new ElectronicsPage(browser);
    }


}