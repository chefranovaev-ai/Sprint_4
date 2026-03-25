import Page.HomePage;
import Page.OrderPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BestUITest {
    WebDriver driver;
    HomePage homePage;
    OrderPage orderPage;

    @Before
    public void openBrowser() {
        String browser = System.getProperty("browser", "chrome");
        if (browser.equals("chrome")) {
            openBrowserChrome();
        } else if (browser.equals("firefox")) {
            openBrowserFirefox();
        }
    }


    public void openBrowserFirefox() {
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        driver.get("https://qa-scooter.praktikum-services.ru");
        homePage = new HomePage(driver);
        orderPage = new OrderPage(driver);

    }
    public void openBrowserChrome() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://qa-scooter.praktikum-services.ru");
        homePage = new HomePage(driver);
        orderPage = new OrderPage(driver);
    }

    @After
    public void closeBrowser() {
        driver.quit();
    }
}
