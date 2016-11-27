package selenium.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import selenium.logger.Logger;
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
    private WebElement priceLink;

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

    private List<String> goodsLink;

    public SearchingResultPage(Browser browser) {
        super(browser, "Searching result");

        transactionTypeSelect = new Select(transactionTypeElement);
        goodsLink = new ArrayList<String>();
    }

    public boolean isOpened() {
        return super.isOpened(URL_PART);
    }

    public SearchingResultPage sortBy(String transactionType) {
        Logger.get().debug("Sorting results by transport type and price.");
        super.selectByIndex(transactionTypeSelect, transactionType);
        super.waitElementToBeClickable(priceLink);
        priceLink.click();

        return this;
    }

    public SearchingResultPage sortByPrice() {
        Logger.get().debug("Sorting results price.");
        super.waitElementToBeClickable(priceLink);
        priceLink.click();

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
        Logger.get().debug("Adding goods into the bookmarks.");
        super.waitElementToBeEnabled(mainTable);

        WebElement checkbox;
        WebElement links;

        for (int i = 1; i < goodsCount + 1; i++) {
            checkbox = super.getTableCell(mainTable, i, 0).findElement(By.cssSelector("input[type='checkbox']"));
            links = super.getTableCell(mainTable, i, 1).findElement(By.cssSelector("a > img"));

            if (!checkbox.isSelected()) {
                goodsLink.add(links.getAttribute("src"));
                checkbox.click();
            }
        }

        super.waitElementToBeClickable(addLink);
        addLink.click();

        super.waitElementToBeVisible(alertWindow);
        okAlertLink.click();

        return this;
    }

    public List<String> getGoodsLik() {
        return goodsLink;
    }

}