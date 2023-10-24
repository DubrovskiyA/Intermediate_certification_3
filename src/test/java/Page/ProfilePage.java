package Page;


import Props.PropertyProvider;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

public class ProfilePage {
    private static final String BASE_URL= PropertyProvider.getInstance().getProps().getProperty("test.baseUrl");
    private static final String URL="/profile";
    private final WebDriver driver;

    public ProfilePage(WebDriver driver) {
        this.driver = driver;
    }
    public void open(){
        driver.get(BASE_URL+URL);
    }
    public int getCountOfBooks(){
        driver.manage().timeouts().implicitlyWait(Duration.of(2, ChronoUnit.SECONDS));
        WebElement select = driver.findElement(By.cssSelector("select"));
        new Actions(driver).scrollToElement(select).perform();
        driver.findElement(By.cssSelector("option[value='10']")).click();
        int count = driver.findElements(By.id("delete-record-undefined")).size();
        new Actions(driver).scrollToElement(select).perform();
        driver.findElement(By.cssSelector("option[value='5']")).click();
        return count;
    }
    public void deleteAllBooks(){
        WebElement element = driver.findElement(By.cssSelector(".text-right.button.di button"));
        new Actions(driver).scrollToElement(element).perform();
        element.click();
        driver.findElement(By.id("closeSmallModal-ok")).click();
        WebDriverWait wait=new WebDriverWait(driver, Duration.of(2, ChronoUnit.SECONDS));
        Alert alert=wait.until(ExpectedConditions.alertIsPresent());
        alert.accept();
    }
}
