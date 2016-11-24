package selenium.ui.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Class for working with the tables
 */
public class Table {

    private WebElement table;

    public Table(WebElement table) {
        this.table = table;
    }

    private WebElement getRow() {
        List<WebElement> tableRows = table.findElements(By.tagName("tr"));

        return null;
    }

    public WebElement getCell(int rowN, int columnN) {
        return null;
    }

}
