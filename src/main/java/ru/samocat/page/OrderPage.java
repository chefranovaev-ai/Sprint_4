package ru.samocat.page;

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
    private final By FIRST_NAME_INPUT = By.xpath(".//input[@placeholder='* Имя']");
    private final By LAST_NAME_INPUT = By.xpath(".//input[@placeholder='* Фамилия']");
    private final By ADDRESS_INPUT = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");
    private final By METRO_STATION_INPUT = By.className("select-search__input");
    private final By PHONE_INPUT = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");
    private final By NEXT_BUTTON = By.xpath(".//button[text()='Далее']");

    // Локаторы второй страницы "Про аренду"
    private final By DATE_INPUT = By.xpath(".//input[@placeholder='* Когда привезти самокат']");
    private final By RENTAL_PERIOD = By.className("Dropdown-root");
    private final By COMMENT_INPUT = By.xpath(".//input[@placeholder='Комментарий для курьера']");
    private final By ORDER_BUTTON = By.xpath(".//div[contains(@class, 'Order_Buttons')]//button[text()='Заказать']");

    // Окно подтверждения
    private final By YES_BUTTON = By.xpath(".//button[text()='Да']");

    //Окно заказа
    private final By ORDER_WINDOWS = By.xpath(".//div[contains(@class, 'Order_ModalHeader')]");

    public OrderPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    public void forWhoScooter(String name, String surname, String address, String metro, String phone) {
        driver.findElement(FIRST_NAME_INPUT).sendKeys(name);
        driver.findElement(LAST_NAME_INPUT).sendKeys(surname);
        driver.findElement(ADDRESS_INPUT).sendKeys(address);
        driver.findElement(METRO_STATION_INPUT).click();
        driver.findElement(METRO_STATION_INPUT).sendKeys(metro + Keys.ARROW_DOWN + Keys.ENTER);
        driver.findElement(PHONE_INPUT).sendKeys(phone);
        driver.findElement(NEXT_BUTTON).click();
    }

    public void aboutRent (String date, String period, String color, String comment) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(DATE_INPUT)).sendKeys(date + Keys.ENTER);
        driver.findElement(RENTAL_PERIOD).click();
        wait.until(ExpectedConditions.elementToBeClickable(getPeriodRental(period))).click();
        driver.findElement(By.id(color)).click();
        driver.findElement(COMMENT_INPUT).sendKeys(comment);
        wait.until(ExpectedConditions.elementToBeClickable(ORDER_BUTTON)).click();
    }
    public By getPeriodRental(String period) {

        return By.xpath(".//div[text()='" + period + "']");
    }

    public void clickYesBatton() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(YES_BUTTON)).click();
    }

    public String textOrderWindows() {
        return driver.findElement(ORDER_WINDOWS).getText();
    }

}

