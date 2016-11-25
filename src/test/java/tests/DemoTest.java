package tests;

import common.constants.DataProviderKeys;
import dataproviders.DemoDataProvider;
import junit.framework.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.annotations.Test;
import selenium.common.Language;
import selenium.ui.pages.MainPage;
import selenium.ui.pages.MainPage.Sections;

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

    @Test(description = "Work with electronic section")
    public void electronicSectionTest() {
        MainPage mainPage = new MainPage(browser);
        Assert.assertTrue("Main page isn't opened!", mainPage.isOpened());

        mainPage.chooseElectronicSection(Sections.ELECTRONIC);
    }

}