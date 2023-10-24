package Page;

import Props.PropertyProvider;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

public class LoginPage {
    private static final String BASE_URL= PropertyProvider.getInstance().getProps().getProperty("test.baseUrl");
    private static final String USER_NAME=PropertyProvider.getInstance().getProps().getProperty("test.userName");
    private static final String PASS=PropertyProvider.getInstance().getProps().getProperty("test.pass");
    private static final String URL="/login";
    private final WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }
    public void open(){
        driver.get(BASE_URL+URL);
    }

    public void setUserName(){
        driver.findElement(By.id("userName")).sendKeys(USER_NAME);
    }
    public void setPassword(){
        driver.findElement(By.id("password")).sendKeys(PASS);
    }
    public void clickLogin(){
        driver.findElement(By.id("login")).click();
        WebDriverWait wait=new WebDriverWait(driver,Duration.of(4,ChronoUnit.SECONDS));
        wait.until(ExpectedConditions.stalenessOf(driver.findElement(By.cssSelector("label[id='loading-label']"))));
    }
}
