package selenium.ui.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import selenium.logger.Logger;
import selenium.webconfigure.Browser;

/**
 * Starting page
 */
public class MainPage extends HeaderComponentPage {
    @FindBy(css = "div#page_main_full")
    private WebElement sectionTable;
    @FindBy(css = "div#dv_6 div.main_head a")
    private WebElement electronicCell;
    public MainPage(Browser browser) {
        super(browser, "Main");
    }

    public boolean isOpened() {
        return super.isOpened(sectionTable);
    }

    public ElectronicsPage chooseSection(Sections section) {
        Logger.get().debug("Choosing section.");
        switch (section) {
            case ELECTRONIC:
                electronicCell.click();
                break;
        }

        return new ElectronicsPage(browser);
    }

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

}