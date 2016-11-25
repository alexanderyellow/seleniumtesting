package selenium.ui.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import selenium.webconfigure.Browser;

/**
 * SearchPage
 */
public class SearchPage extends HeaderComponentPage {

    @FindBy(css = "input#ptxt")
    private WebElement searchingWordInput;



    protected SearchPage(Browser browser) {
        super(browser);
    }

}
