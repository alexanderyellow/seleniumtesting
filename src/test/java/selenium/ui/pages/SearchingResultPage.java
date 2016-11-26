package selenium.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import selenium.webconfigure.Browser;

import java.util.ArrayList;
import java.util.List;

/**
 * SearchingResultPage
 */
public class SearchingResultPage extends HeaderComponentPage {

    private final static String URL_PART = "search-result/";

    @FindBy(css = "table#page_main div.filter_second_line_dv > span:last-child > select")
    private WebElement transactionTypeElement;
    private Select transactionTypeSelect;

    @FindBy(css = "a.a19")
    private WebElement costLink;

    @FindBy(css = "td.td7 > a.a9a[href*='/search/']")
    private WebElement advancedSearchLink;

    @FindBy(css = "div.filter_second_line_dv + table")
    private WebElement mainTable;

    @FindBy(css = "a#a_fav_sel")
    private WebElement addLink;

    @FindBy(css = "div#alert_dv")
    private WebElement alertWindow;

    @FindBy(css = "a#alert_ok")
    private WebElement okAlertLink;

    private List<String> goodsText;

    public SearchingResultPage(Browser browser) {
        super(browser);

        transactionTypeSelect = new Select(transactionTypeElement);
        goodsText = new ArrayList<String>();
    }

    public boolean isOpened() {
        return super.isUrlEnding(URL_PART);
    }

    public SearchingResultPage sortBy(String transactionType) {
        super.selectByIndex(transactionTypeSelect, transactionType);
        super.waitElementToBeClickable(costLink);
        costLink.click();

        return this;
    }

    public SearchingResultPage sortByPrice() {
        super.waitElementToBeClickable(costLink);
        costLink.click();

        return this;
    }

    public SearchingPage openAdvancedSearchPage() {
        super.waitElementToBeClickable(advancedSearchLink);
        advancedSearchLink.click();

        return new SearchingPage(browser);
    }

    /**
     * Add random goods
     */
    public SearchingResultPage addIntoBookmarks(int goodsCount) {
        super.waitElementToBeEnabled(mainTable);

        WebElement checkbox;
        WebElement description;

        for (int i = 1; i < goodsCount + 1; i++) {
            checkbox = super.getTableCell(mainTable, i, 0).findElement(By.cssSelector("input[type='checkbox']"));
            description = super.getTableCell(mainTable, i, 2).findElement(By.cssSelector("div.d1 > a"));

            if (!checkbox.isSelected()) {
                goodsText.add(description.getText());
                checkbox.click();
            }
        }

        super.waitElementToBeClickable(addLink);
        addLink.click();

        super.waitElementToBeVisible(alertWindow);
        okAlertLink.click();

        return this;
    }

    public List<String> getGoodsDescription() {
        return goodsText;
    }

}