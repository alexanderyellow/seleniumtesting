package selenium.ui.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import selenium.webconfigure.Browser;

import java.util.ArrayList;
import java.util.List;

/**
 * FavoritesPage
 */
public class FavoritesPage extends HeaderComponentPage {

    private final static String URL_PART = "favorites/";

    @FindBys({@FindBy(css = "a.am")})
    private List<WebElement> goodsDescriptionLink;

    public FavoritesPage(Browser browser) {
        super(browser);
    }

    public boolean isOpened() {
        return super.isUrlEnding(URL_PART);
    }

    public List<String> getGoodsDescription() {
        List<String> goodsDescription = new ArrayList<String>();
        int size = goodsDescriptionLink.size();

        for (int i = 0; i < size; i++) {
            goodsDescription.add(goodsDescriptionLink.get(i).getText());
        }

        return goodsDescription;
    }

}
