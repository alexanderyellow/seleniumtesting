package tests;

import common.constants.DataProviderKeys;
import dataproviders.DemoDataProvider;
import junit.framework.Assert;
import org.testng.annotations.Test;
import selenium.common.Language;
import selenium.ui.pages.MainPage;

/**
 * DemoTest
 */
public class DemoTest extends AbstractTest {

    /*@Test(description = "lala1")
    public void test1() {
        System.out.println("Test1 is in process!");
        Assert.assertTrue(true);
    }

    @Test(description = "lala2")
    public void test2() {
        System.out.println("Test2 is in process!");
        Assert.assertTrue(false);
    }*/

    @Test(description = "Change language",
            dataProvider = DataProviderKeys.GET_LANGUAGE, dataProviderClass = DemoDataProvider.class)
    public void changeLanguageTest(Language newLang, Language oldLang) {
        /*ComplexPageObject it's you page
        ComplexPageObject page = new ComplexPageObject("expected title", driver);
        PageFactory.initElements(driver, page);
        //PageFactory.initElements(new AjaxElementLocatorFactory(driver, 15), this);
        */

        /*Wait<WebDriver> wait = new FluentWait<WebDriver>(browser.getWebDriver())

                .withTimeout(30, TimeUnit.SECONDS)

                .pollingEvery(5, TimeUnit.SECONDS)

                .ignoring(NoSuchElementException.class);

        WebElement foo = wait.until(new Function<WebDriver, WebElement>() {

            public WebElement apply(WebDriver driver) {

                return driver.findElement(By.id("foo"));

            }

        });

        WebElement myDynamicElement = (new WebDriverWait(browser.getWebDriver(), 10))
                .until(ExpectedConditions.presenceOfElementLocated(By.id("myDynamicElement")));*/

        MainPage headerComponent = new MainPage(browser);
        headerComponent.changeLanguage(newLang);

        Assert.assertTrue("Language doesn't match!", headerComponent.isLangMatch(oldLang));
    }

    @Test(description = "")
    public void test4() {

    }

}