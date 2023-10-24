import Ext.APIcleaner;
import Page.BooksPage;
import Page.LoginPage;
import Page.ProfilePage;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Tests {
    private APIcleaner cleaner;
    private WebDriver driver;
    @BeforeEach
    public void setUp(){
        cleaner=new APIcleaner();
        cleaner.deleteBooks();
        driver=new ChromeDriver();
        driver.manage().window().maximize();
    }
    @AfterEach
    public void tearDown(){
        if (driver!=null){
        driver.quit();
        }
    }
    @Test
    @DisplayName("Сценарий 1")
    public void test1(){
        LoginPage loginPage=new LoginPage(driver);
        loginPage.open();
        loginPage.setUserName();
        loginPage.setPassword();
        loginPage.clickLogin();
        ProfilePage profilePage=new ProfilePage(driver);
        int countOfBooks = profilePage.getCountOfBooks();
        assertEquals(0,countOfBooks);
    }
    @Test
    @DisplayName("Сценарий 2")
    public void test2(){
        LoginPage page=new LoginPage(driver);
        page.open();
        page.setUserName();
        page.setPassword();
        page.clickLogin();
        BooksPage booksPage=new BooksPage(driver);
        booksPage.open();
        booksPage.addBook(6);
        ProfilePage profilePage=new ProfilePage(driver);
        profilePage.open();
        int countOfBooks = profilePage.getCountOfBooks();
        assertEquals(6,countOfBooks);
    }
    @Test
    @DisplayName("Сценарий 3")
    public void test3(){
        LoginPage page=new LoginPage(driver);
        page.open();
        page.setUserName();
        page.setPassword();
        page.clickLogin();
        BooksPage booksPage=new BooksPage(driver);
        booksPage.open();
        booksPage.addBook(2);
        ProfilePage profilePage=new ProfilePage(driver);
        profilePage.open();
        int countOfBooks = profilePage.getCountOfBooks();
        assertEquals(2,countOfBooks);
        profilePage.deleteAllBooks();
        countOfBooks=profilePage.getCountOfBooks();
        assertEquals(0,countOfBooks);
    }
}
