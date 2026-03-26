import ru.samocat.page.HomePage;
import ru.samocat.page.OrderPage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class TestOrder extends BestUITest {

    private String locationButton, name, surname, address, metro, phone, date, period, color, comment;

    public TestOrder (String locationButton, String name, String surname, String address, String metro, String phone, String date, String period, String color, String comment) {
        this.locationButton = locationButton;
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.metro = metro;
        this.phone = phone;
        this.date = date;
        this.period = period;
        this.color = color;
        this.comment = comment;
    }
    @Parameterized.Parameters(name = "Тестовые данные:{0} {1} {2}")
    public static Object[][] getTestData() {
        return new Object[][]{
                { "верхняя", "Елена", "Васильева", "Земляной Вал, 36", "Чкаловская", "89658763098", "25.03.2026", "сутки", "black", "Позвонить за час" },
                { "нижняя", "Владимир", "Нетудыхата", "проспект Мира 150", "ВДНХ", "89998765433", "01.04.2026", "двое суток", "grey", "У портье" }
        };
    }

    @Test
    public void checkOrder() {

        homePage.acceptCookies();
        homePage.clickOrderButton(locationButton);

        OrderPage orderPage = new OrderPage(driver);

        orderPage.forWhoScooter(name, surname, address, metro, phone);
        orderPage.aboutRent(date, period, color, comment);
        orderPage.clickYesBatton();

        String actualTextOrder = orderPage.textOrderWindows();


        assertTrue("Текст 'Заказ оформлен' не найден в сообщении", actualTextOrder.contains("Заказ оформлен"));

    }


}


