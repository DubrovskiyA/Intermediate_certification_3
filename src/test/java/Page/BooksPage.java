package Page;

import Props.PropertyProvider;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class BooksPage {
    private static final String BASE_URL= PropertyProvider.getInstance().getProps().getProperty("test.baseUrl");
    private static final String URL="/books";
    private final WebDriver driver;

    public BooksPage(WebDriver driver) {
        this.driver = driver;
    }
    public void open(){
        driver.get(BASE_URL+URL);
    }
    public void addBook(int countOfBooks){
        for (int i=0;i<countOfBooks;i++){
            WebElement[] books = driver.findElements(By.cssSelector(".rt-tr-group a")).toArray(new WebElement[0]);
            new Actions(driver).scrollByAmount(0,300).perform();
            books[i].click();
            WebElement btnAddBook = driver.findElement(By.cssSelector(".text-right button[id='addNewRecordButton']"));
            new Actions(driver).scrollByAmount(0,200).perform();
            btnAddBook.click();
            WebDriverWait wait=new WebDriverWait(driver, Duration.of(2, ChronoUnit.SECONDS));
            Alert alert=wait.until(ExpectedConditions.alertIsPresent());
            alert.accept();
            driver.findElement(By.className("text-left")).click();
        }
    }
}
