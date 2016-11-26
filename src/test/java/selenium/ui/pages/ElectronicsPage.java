package selenium.ui.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import selenium.webconfigure.Browser;

/**
 * ElectronicsPage
 */
public class ElectronicsPage extends HeaderComponentPage {

    @FindBy(css = "a.a14")
    private WebElement electronicLink;

    protected ElectronicsPage(Browser browser) {
        super(browser);
    }

    public boolean isOpened() {
        return super.isOpened(electronicLink);
    }



}
