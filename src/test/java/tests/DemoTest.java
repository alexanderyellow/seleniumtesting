package tests;

import common.constants.DataProviderKeys;
import common.constants.Language;
import dataproviders.DemoDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;
import selenium.ui.pages.*;
import selenium.ui.pages.MainPage.Sections;
import services.MainService;

import java.util.List;

/**
 * DemoTest
 */
public class DemoTest extends AbstractTest {

    @Test(description = "Change language",
            dataProvider = DataProviderKeys.GET_LANGUAGE, dataProviderClass = DemoDataProvider.class)
    public void changeLanguageTest(Language newLang, Language oldLang) {
        MainPage mainPage = new MainPage(browser);
        Assert.assertTrue(mainPage.isOpened(), "Main page isn't opened!");

        mainPage.changeLanguage(newLang);
        Assert.assertTrue(mainPage.isLangMatch(oldLang), "Language isn't changed!");
    }

    @Test(description = "Work with electronic section", dependsOnMethods = "changeLanguageTest",
            dataProvider = DataProviderKeys.GET_SEARCHING_ELECTRONIC, dataProviderClass = DemoDataProvider.class)
    public void electronicSectionTest(String searchingWord, String minPrice, String maxPrice, String subheading,
                                      String region, String period, String sortingValue) {
        MainPage mainPage = new MainPage(browser);
        Assert.assertTrue(mainPage.isOpened(), "Main page isn't opened!");

        ElectronicsPage electronicsPage = mainPage.chooseSection(Sections.ELECTRONIC);
        Assert.assertTrue(electronicsPage.isOpened(), "Electronics page isn't opened!");

        SearchingPage searchingPage = electronicsPage.openSearchingPage();
        Assert.assertTrue(searchingPage.isOpened(), "Searching page isn't opened!");

        searchingPage.search(searchingWord, minPrice,
                maxPrice, subheading, region, period, sortingValue);
    }

    @Test(description = "Sort result", dependsOnMethods = "electronicSectionTest",
            dataProvider = DataProviderKeys.GET_SORT_BY, dataProviderClass = DemoDataProvider.class)
    public void sortingTest(String transactionType) {
        SearchingResultPage searchingResultPage = new SearchingResultPage(browser);
        Assert.assertTrue(searchingResultPage.isOpened(), "Searching result page isn't opened!");

        searchingResultPage.sortBy(transactionType);
    }

    @Test(description = "Do advanced search", dependsOnMethods = "sortingTest",
            dataProvider = DataProviderKeys.GET_ADVANCED_SEARCH, dataProviderClass = DemoDataProvider.class)
    public void advancedSearchTest(String searchingWord, String minPrice, String maxPrice, String subheading,
                                   String region, String period, String sortingValue) {
        SearchingResultPage searchingResultPage = new SearchingResultPage(browser);
        Assert.assertTrue(searchingResultPage.isOpened(), "Searching result page isn't opened!");

        SearchingPage searchingPage = searchingResultPage.openAdvancedSearchPage();
        Assert.assertTrue(searchingPage.isOpened(), "Searching page isn't opened!");

        searchingResultPage = searchingPage.search(searchingWord, minPrice,
                maxPrice, subheading, region, period, sortingValue);
        Assert.assertTrue(searchingResultPage.isOpened(), "Searching result page isn't opened!");

        searchingResultPage.sortByPrice();
    }

    @Test(description = "Add favorites", dependsOnMethods = "advancedSearchTest",
            dataProvider = DataProviderKeys.GET_BOOKMARKS, dataProviderClass = DemoDataProvider.class)
    public void addIntoBookmarksTest(int goodsCount) {
        SearchingResultPage searchingResultPage = new SearchingResultPage(browser);
        Assert.assertTrue(searchingResultPage.isOpened(), "Searching result page isn't opened!");

        searchingResultPage = searchingResultPage.addIntoBookmarks(goodsCount);
        List<String> goodsLinkExp = searchingResultPage.getGoodsLik();

        FavoritesPage favoritesPage = searchingResultPage.openFavoritesPage();
        Assert.assertTrue(favoritesPage.isOpened(), "Favorites page isn't opened!");

        List<String> goodsLinkAct = favoritesPage.getGoodsDescription();

        MainService.compareTwoLists(goodsLinkAct, goodsLinkExp);
    }

}