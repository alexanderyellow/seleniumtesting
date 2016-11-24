package tests;

import junit.framework.Assert;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

/**
 * DemoTest
 */
public class DemoTest extends AbstractTest {

    @Test(description = "lala1")
    public void test1() {
        System.out.println("Test1 is in process!");
        Assert.assertTrue(true);
    }

    @Test(description = "lala2")
    public void test2() {
        System.out.println("Test2 is in process!");
        Assert.assertTrue(false);
    }

    @Test(description = "lala3")
    public void test3() {
        /*ComplexPageObject it's you page
        ComplexPageObject page = new ComplexPageObject("expected title", driver);
        PageFactory.initElements(driver, page);*/

        System.out.println("Test3 is in process!");
        Assert.assertTrue(false);
    }

}