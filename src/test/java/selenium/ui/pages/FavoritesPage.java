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

    @FindBys({@FindBy(css = "img.isfoto")})
    private List<WebElement> goodsLink;

    public FavoritesPage(Browser browser) {
        super(browser, "Favorites");
    }

    public boolean isOpened() {
        return super.isOpened(URL_PART);
    }

    public List<String> getGoodsDescription() {
        List<String> goodsImgLink = new ArrayList<String>();
        int size = goodsLink.size();

        for (int i = 0; i < size; i++) {
            goodsImgLink.add(goodsLink.get(i).getAttribute("src"));
        }

        return goodsImgLink;
    }

}
