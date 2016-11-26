package selenium.ui.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import selenium.webconfigure.Browser;

/**
 * SearchingPage
 */
public class SearchingPage extends HeaderComponentPage {

    private final static String URL_PART = "search/";

    @FindBy(css = "input#ptxt")
    private WebElement searchingWordInput;

    @FindBy(css = "input[name='topt[8][min]']")
    private WebElement minPriceInput;

    @FindBy(css = "input[name='topt[8][max]']")
    private WebElement maxPriceInout;

    @FindBy(css = "select[name='sid']")
    private WebElement subheadingElement;
    private Select subheadingSelect;

    @FindBy(css = "select#s_region_select")
    private WebElement regionElement;
    private Select regionSelect;

    @FindBy(css = "select[name='pr']")
    private WebElement periodElement;
    private Select periodSelect;

    @FindBy(css = "select[name='sort']")
    private WebElement sortElement;
    private Select sortSelect;

    @FindBy(css = "input#sbtn")
    private WebElement searchingButton;

    protected SearchingPage(Browser browser) {
        super(browser);

        subheadingSelect = new Select(subheadingElement);
        regionSelect = new Select(regionElement);
        periodSelect = new Select(periodElement);
        sortSelect = new Select(sortElement);
    }

    public boolean isOpened() {
        return super.isUrlEnding(URL_PART);
    }

    public SearchingResultPage search(String searchingWord, String minPrice, String maxPrice, String subheading,
                                      String region, String period, String sortingValue) {
        super.waitElementToBeClickable(searchingButton);

        if (searchingWord != null) {
            searchingWordInput.clear();
            searchingWordInput.sendKeys(searchingWord);
        }

        if (minPrice != null) {
            minPriceInput.clear();
            minPriceInput.sendKeys(minPrice);
        }

        if (maxPrice != null) {
            maxPriceInout.clear();
            maxPriceInout.sendKeys(maxPrice);
        }

        selectByIndex(subheadingSelect, subheading);
        selectByIndex(regionSelect, region);
        selectByIndex(periodSelect, period);
        selectByIndex(sortSelect, sortingValue);

        searchingButton.click();

        return new SearchingResultPage(browser);
    }

}