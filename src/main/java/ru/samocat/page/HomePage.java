package ru.samocat.page;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class HomePage {
    WebDriver driver;
    public HomePage(WebDriver driver) {

        this.driver = driver;
    }

    // Локатор кнопки "да все привыкли"
    private final By COOKIE_BUTTON = By.id("rcc-confirm-button");

    //локатор для верхней кнопки Заказать
    private static final By ORDER_BUTTON_UP = By.xpath(".//div[contains(@class, 'Header_Nav')]//button[text()='Заказать']");
    //локатор для нижней кнопки Заказать
    private static final By ORDER_BUTTON_DOWN = By.xpath(".//div[contains(@class, 'Home_FinishButton')]//button[text()='Заказать']");

    // Динамический локатор для вопроса
    private By getQuestionId(int index) {
        return By.id("accordion__heading-" + index);
    }
    // Динамический локатор для текста ответа
    private By getAnswerId(int index) {
        return By.id("accordion__panel-" + index);
    }


    // Метод для принятия куки
    public void acceptCookies() {
        // Проверяем, отображается ли кнопка, и кликаем
        if (driver.findElement(COOKIE_BUTTON).isDisplayed()) {
            driver.findElement(COOKIE_BUTTON).click();
        }
    }

    //ищем и возвращаем текст вопроса
    public String getQuestionText(int index) {
        By question = getQuestionId(index);
        return  driver.findElement(question).getText();
    }
    //метод нажатия на вопрос
    public void clickQuestion(int index) {
        By questionButton = getQuestionId(index);
        driver.findElement(questionButton).click();
    }

    //метод получения текста ответа
    public String getAnswerText(int index) {
        By answer = getAnswerId(index);
        return driver.findElement(answer).getText();
    }


    //метод получения пары вопрос-ответ
    public String getQuestionAndAnswerText(int index) {
        // Сначала кликаем, чтобы ответ стал видимым
        clickQuestion(index);
        // Возвращаем объединенную строку: "Вопрос Ответ"
        return getQuestionText(index) + " " + getAnswerText(index);
    }

    //метод нажатия на кнопку Заказать в зависимости от локатора
    public void clickOrderButton(String location) {
        By locator = location.equals("верхняя") ? ORDER_BUTTON_UP : ORDER_BUTTON_DOWN;
        driver.findElement(locator).click();

    }


}
