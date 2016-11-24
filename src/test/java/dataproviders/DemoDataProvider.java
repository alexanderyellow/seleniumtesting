package dataproviders;

import common.constants.DataProviderKeys;
import org.testng.annotations.DataProvider;
import selenium.common.Language;

/**
 * Created by alexander.
 */
public class DemoDataProvider {

    @DataProvider(name = DataProviderKeys.GET_LANGUAGE)
    public static Object[][] getLanguage() {
        return new Object[][]{
                {Language.RU, Language.LV}
        };
    }

}
