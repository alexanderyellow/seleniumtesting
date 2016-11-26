package dataproviders;

import common.constants.DataProviderKeys;
import common.constants.Language;
import common.constants.SearchingOptions;
import org.testng.annotations.DataProvider;

/**
 * DemoDataProvider
 */
public class DemoDataProvider {

    private final static String SEARCHING_WORD = "Компьютер";
    private final static String MIN_PRICE = "0";
    private final static String MAX_PRICE = "300";
    private final static int GOODS_COUNT = 3;

    @DataProvider(name = DataProviderKeys.GET_LANGUAGE)
    public static Object[][] getLanguage() {
        return new Object[][]{
                {Language.RU, Language.LV}
        };
    }

    @DataProvider(name = DataProviderKeys.GET_SEARCHING_ELECTRONIC)
    public static Object[][] getSearchingElectronic() {
        return new Object[][]{
                {SEARCHING_WORD, "", "",
                        SearchingOptions.Subheadings.VARIA, SearchingOptions.Region.RIGA,
                        SearchingOptions.Period.ALL_ADS, SearchingOptions.SortedBy.PRICE}
        };
    }

    @DataProvider(name = DataProviderKeys.GET_SORT_BY)
    public static Object[][] getSortBy() {
        return new Object[][]{
                {SearchingOptions.TransactionType.SELLING}
        };
    }

    @DataProvider(name = DataProviderKeys.GET_ADVANCED_SEARCH)
    public static Object[][] getAdvancedSearch() {
        return new Object[][]{
                {null, MIN_PRICE, MAX_PRICE,
                        SearchingOptions.Subheadings.SELLING, null,
                        SearchingOptions.Period.ALL_ADS, null}
        };
    }

    @DataProvider(name = DataProviderKeys.GET_BOOKMARKS)
    public static Object[][] getBookmarks() {
        return new Object[][]{
                {GOODS_COUNT}
        };
    }

}
