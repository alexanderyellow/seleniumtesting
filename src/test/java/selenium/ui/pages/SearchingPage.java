package selenium.ui.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import selenium.webconfigure.Browser;

/**
 * SearchingPage
 */
public class SearchingPage extends HeaderComponentPage {

    @FindBy(css = "h2.headtitle > b")
    private WebElement titleLabel;

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
    private Select regionSelect;

    @FindBy(css = "select[name='pr']")
    private Select periodSelect;

    @FindBy(css = "select[name='sort']")
    private Select sortSelect;

    @FindBy(css = "input#sbtn")
    private WebElement searchingButton;

    protected SearchingPage(Browser browser) {
        super(browser);

        subheadingSelect = new Select(subheadingElement);
    }

    public boolean isOpened() {
        return super.isOpened(titleLabel);
    }

    public SearchingResultPage search(String searchingWord, String minPrice, String maxPrice, String subheading,
                                      String region, String period, String sortingValue) {
        super.waitElementToBeClickable(searchingButton);

        searchingWordInput.clear();
        searchingWordInput.sendKeys(searchingWord);

        new Actions(driver).moveToElement(titleLabel).click(titleLabel).perform();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //titleLabel.click();

        /*try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/

        minPriceInput.clear();
        minPriceInput.sendKeys(minPrice);

        super.waitElementToBeEnabled(maxPriceInout);
        maxPriceInout.clear();
        maxPriceInout.sendKeys(maxPrice);

        //super.waitElementToBeEnabled(subheadingSelect);

        System.out.println("Sub: " + subheadingElement.isEnabled());
        subheadingElement.click();
        //subheadingSelect.selectByIndex(2);

        subheadingSelect.selectByValue(subheading);
        regionSelect.selectByValue(region);
        periodSelect.selectByValue(period);
        sortSelect.selectByValue(sortingValue);

        searchingButton.click();

        return new SearchingResultPage(browser);
    }

    private void selectByIndex() {
    }

}