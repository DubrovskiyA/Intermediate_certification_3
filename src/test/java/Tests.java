import Ext.APIcleaner;
import Page.BooksPage;
import Page.LoginPage;
import Page.ProfilePage;
import io.qameta.allure.Description;
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
    @Description("1. Открыть страницу https://demoqa.com/login\n" +
            "2. Ввести логин и пароль\n" +
            "3. Перейти в раздел https://demoqa.com/profile\n" +
            "4. Проверить, что таблица пустая")
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
    @Description("1. Открыть страницу https://demoqa.com/login\n" +
            "2. Ввести логин и пароль\n" +
            "3. Перейти в раздел https://demoqa.com/books\n" +
            "4. Добавить в коллекцию 6 книг\n" +
            "5. Перейти в раздел https://demoqa.com/profile\n" +
            "6. Проверить, что в коллекции отображается 6 книг")
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
    @Description("1. Открыть страницу https://demoqa.com/login\n" +
            "2. Ввести логин и пароль\n" +
            "3. Перейти в раздел https://demoqa.com/books\n" +
            "4. Добавить в коллекцию 2 книги\n" +
            "5. Перейти в раздел https://demoqa.com/profile\n" +
            "6. Проверить, что в коллекции отображается 2 книги\n" +
            "7. Перейти в раздел https://demoqa.com/profile\n" +
            "8. Нажать Delete All Books\n" +
            "9. Вернуться в раздел https://demoqa.com/profile\n" +
            "10. Проверить, что таблица пустая")
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
