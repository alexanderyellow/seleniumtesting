package selenium.ui.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import selenium.webconfigure.Browser;

/**
 * SearchPage
 */
public class SearchingPage extends HeaderComponentPage {

    @FindBy(css = "input#ptxt")
    private WebElement searchingWordInput;

    @FindBy(css = "input[name='topt[8][min]']")
    private WebElement minPriceInput;

    @FindBy(css = "topt[8][max]")
    private WebElement maxPriceInout;

    @FindBy(css = "topt[8][max]")
    private WebElement subheadingSelect;

    protected SearchingPage(Browser browser) {
        super(browser);
    }

}