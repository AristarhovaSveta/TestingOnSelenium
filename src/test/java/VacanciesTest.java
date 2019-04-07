import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

public class VacanciesTest extends BaseRunner {
    private WebDriver driver;
    private String baseUrl;

    @Before
    public void setUp() {
        driver = app.driver;
        baseUrl = "https://www.tinkoff.ru/career/vacancies/";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public void test1() {
        driver.get(baseUrl);
        driver.findElement(By.name("name")).click();
        driver.findElement(By.name("birthday")).click();
        driver.findElement(By.name("city")).click();
        driver.findElement(By.name("email")).click();
        driver.findElement(By.name("phone")).click();
        driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Перетащите файлы сюда'])[1]/following::span[2]")).click();
        driver.findElement(By.name("socialLink0")).click();
        driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='условиями передачи информации'])[1]/following::span[1]")).click();
        assertEquals("Поле обязательное", driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Фамилия и имя'])[1]/following::div[2]")).getText());
        assertEquals("Поле обязательное", driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Дата рождения'])[1]/following::div[3]")).getText());
        assertEquals("Поле обязательное", driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Город проживания'])[1]/following::div[3]")).getText());
        assertEquals("Поле обязательное", driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Электронная почта'])[1]/following::div[2]")).getText());
        assertEquals("Поле обязательное", driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Мобильный телефон'])[1]/following::div[2]")).getText());
        assertEquals("Поле обязательное", driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='загрузите резюме/портфолио'])[1]/following::div[1]")).getText());
    }

    @Test
    public void test2() {
        driver.get(baseUrl);
        driver.findElement(By.name("name")).click();
        driver.findElement(By.name("name")).clear();
        driver.findElement(By.name("name")).sendKeys("1123");
        driver.findElement(By.name("birthday")).click();
        driver.findElement(By.name("birthday")).clear();
        driver.findElement(By.name("birthday")).sendKeys("31.13.333");
        driver.findElement(By.name("email")).click();
        driver.findElement(By.name("email")).clear();
        driver.findElement(By.name("email")).sendKeys("привет");
        driver.findElement(By.name("phone")).click();
        driver.findElement(By.name("phone")).clear();
        driver.findElement(By.name("phone")).sendKeys("000");
        driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Заполните анкету'])[1]/following::div[2]")).click();
        assertEquals("Допустимо использовать только буквы русского алфавита и дефис", driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Фамилия и имя'])[1]/following::div[2]")).getText());
        assertEquals("Поле заполнено некорректно", driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Дата рождения'])[1]/following::div[3]")).getText());
        assertEquals("Введите корректный адрес эл. почты", driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Электронная почта'])[1]/following::div[2]")).getText());
        assertEquals("Номер телефона должен состоять из 10 цифр, начиная с кода оператора", driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Мобильный телефон'])[1]/following::div[2]")).getText());
    }

}
