package tests;

import common.constants.DataProviderKeys;
import common.constants.Language;
import dataproviders.DemoDataProvider;
import junit.framework.Assert;
import org.testng.annotations.Test;
import selenium.ui.pages.ElectronicsPage;
import selenium.ui.pages.MainPage;
import selenium.ui.pages.MainPage.Sections;
import selenium.ui.pages.SearchingPage;
import selenium.ui.pages.SearchingResultPage;

/**
 * DemoTest
 */
public class DemoTest extends AbstractTest {

    @Test(description = "Change language",
            dataProvider = DataProviderKeys.GET_LANGUAGE, dataProviderClass = DemoDataProvider.class)
    public void changeLanguageTest(Language newLang, Language oldLang) {
        MainPage mainPage = new MainPage(browser);
        Assert.assertTrue("Main page isn't opened!", mainPage.isOpened());

        mainPage.changeLanguage(newLang);
        Assert.assertTrue("Language doesn't match!", mainPage.isLangMatch(oldLang));
    }

    @Test(description = "Work with electronic section",
            dataProvider = DataProviderKeys.GET_SEARCHING_ELECTRONIC, dataProviderClass = DemoDataProvider.class)
    public void electronicSectionTest(String searchingWord, String minPrice, String maxPrice, String subheading,
                                      String region, String period, String sortingValue) {
        MainPage mainPage = new MainPage(browser);
        Assert.assertTrue("Main page isn't opened!", mainPage.isOpened());

        ElectronicsPage electronicsPage = mainPage.chooseElectronicSection(Sections.ELECTRONIC);
        Assert.assertTrue("Electronics page isn't opened!", electronicsPage.isOpened());

        SearchingPage searchingPage = electronicsPage.openSearchingPage();
        Assert.assertTrue("Electronics page isn't opened!", searchingPage.isOpened());

        SearchingResultPage searchingResultPage = searchingPage.search(searchingWord, minPrice,
                maxPrice, subheading, region, period, sortingValue);
    }

}