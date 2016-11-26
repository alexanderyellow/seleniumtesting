package dataproviders;

import common.constants.DataProviderKeys;
import common.constants.Language;
import common.constants.SearchingOptions;
import org.testng.annotations.DataProvider;

/**
 * Created by alexander.
 */
public class DemoDataProvider {

    private final static String SEARCHING_WORD = "Компьютер";
    private final static String MIN_PRICE = "1";
    private final static String MAX_PRICE = "10000";

    @DataProvider(name = DataProviderKeys.GET_LANGUAGE)
    public static Object[][] getLanguage() {
        return new Object[][]{
                {Language.RU, Language.LV}
        };
    }

    @DataProvider(name = DataProviderKeys.GET_SEARCHING_ELECTRONIC)
    public static Object[][] getSearchingElectronic() {
        return new Object[][]{
                {SEARCHING_WORD, MIN_PRICE, MAX_PRICE,
                        SearchingOptions.Subheadings.VARIA, SearchingOptions.Region.RIGA,
                        SearchingOptions.Period.ALL_ADS, SearchingOptions.SortedBy.PRICE}
        };
    }

}
