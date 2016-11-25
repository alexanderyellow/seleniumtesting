package selenium.ui.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Class for working with the tables
 */
public class Table {

    private final WebElement table;
    private final WebElement row;
    private final WebElement column;

    public Table(WebElement table, WebElement row, WebElement column) {
        this.table = table;
        this.row = row;
        this.column = column;
    }

    private WebElement getRow() {
        List<WebElement> tableRows = table.findElements(By.tagName("tr"));

        return null;
    }

    public WebElement getCell(int rowN, int columnN) {

        return null;
    }

}
