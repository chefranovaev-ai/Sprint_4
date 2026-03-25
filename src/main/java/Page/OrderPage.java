package Page;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class OrderPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    // Локаторы первой страницы "Для кого самокат"
    private final By firstNameInput = By.xpath(".//input[@placeholder='* Имя']");
    private final By lastNameInput = By.xpath(".//input[@placeholder='* Фамилия']");
    private final By addressInput = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");
    private final By metroStationInput = By.className("select-search__input");
    private final By phoneInput = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");
    private final By nextButton = By.xpath(".//button[text()='Далее']");

    // Локаторы второй страницы "Про аренду"
    private final By dateInput = By.xpath(".//input[@placeholder='* Когда привезти самокат']");
    private final By rentalPeriod = By.className("Dropdown-root");
    private final By commentInput = By.xpath(".//input[@placeholder='Комментарий для курьера']");
    private final By orderButton = By.xpath(".//div[contains(@class, 'Order_Buttons')]//button[text()='Заказать']");

    // Окно подтверждения
    private final By yesButton = By.xpath(".//button[text()='Да']");

    //Окно заказа
    private final By orderWindows = By.xpath(".//div[contains(@class, 'Order_ModalHeader')]");

    public OrderPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    public void forWhoScooter(String name, String surname, String address, String metro, String phone) {
        driver.findElement(firstNameInput).sendKeys(name);
        driver.findElement(lastNameInput).sendKeys(surname);
        driver.findElement(addressInput).sendKeys(address);
        driver.findElement(metroStationInput).click();
        driver.findElement(metroStationInput).sendKeys(metro + Keys.ARROW_DOWN + Keys.ENTER);
        driver.findElement(phoneInput).sendKeys(phone);
        driver.findElement(nextButton).click();
    }

    public void aboutRent (String date, String period, String color, String comment) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(dateInput)).sendKeys(date + Keys.ENTER);
        driver.findElement(rentalPeriod).click();
        wait.until(ExpectedConditions.elementToBeClickable(getPeriodRental(period))).click();
        driver.findElement(By.id(color)).click();
        driver.findElement(commentInput).sendKeys(comment);
        wait.until(ExpectedConditions.elementToBeClickable(orderButton)).click();
    }
    public By getPeriodRental(String period) {

        return By.xpath(".//div[text()='" + period + "']");
    }

    public void clickYesBatton() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(yesButton)).click();
    }

    public String textOrderWindows() {
        return driver.findElement(orderWindows).getText();
    }

}

