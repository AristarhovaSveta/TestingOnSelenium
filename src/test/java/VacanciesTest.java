import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import ui.GoogleSearchPage;
import ui.MobilePage;
import ui.RegionPage;
import ui.VacanciesPage;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

@Slf4j
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
        VacanciesPage vacanciesPage = PageFactory.initElements(driver, VacanciesPage.class);
        vacanciesPage.clickAllInputs();
        for (String errorText: vacanciesPage.getErrorsText()) {
            assertEquals("Поле обязательное", errorText);
        }
    }

    @Test
    public void test2() {
        driver.get(baseUrl);
        VacanciesPage vacanciesPage = PageFactory.initElements(driver, VacanciesPage.class);
        vacanciesPage.inputName("1123");
        vacanciesPage.inputBirthday("31.13.333");
        vacanciesPage.inputEmail("привет");
        vacanciesPage.inputPhone("000");
        vacanciesPage.clickPageSpace();
        List<String> errors = vacanciesPage.getErrorsText();
        assertEquals("Допустимо использовать только буквы русского алфавита и дефис", errors.get(0));
        assertEquals("Поле заполнено некорректно", errors.get(1));
        assertEquals("Введите корректный адрес эл. почты", errors.get(2));
        assertEquals("Номер телефона должен состоять из 10 цифр, начиная с кода оператора", errors.get(3));
    }

    @Test
    public void test21() {
        driver.get("https://www.google.ru/");
        GoogleSearchPage googleSearchPage = PageFactory.initElements(driver, GoogleSearchPage.class);
        googleSearchPage.inputQuery("мобайл тинькофф");
        googleSearchPage.inputQuery(" ");
        googleSearchPage.chooseHint();
        googleSearchPage.chooseRef();
        String[] tabs = driver.getWindowHandles().toArray(new String[0]);
        driver.switchTo().window(tabs[1]);
        assertEquals("Тарифы Тинькофф Мобайла", driver.getTitle());
        driver.switchTo().window(tabs[0]).close();
        driver.switchTo().window(tabs[1]);
        assertEquals("https://www.tinkoff.ru/mobile-operator/tariffs/", driver.getCurrentUrl());
    }

    @Test
    public void test22() {
        driver.get("https://www.tinkoff.ru/mobile-operator/tariffs/");
        MobilePage mobilePage = new MobilePage(driver);
        mobilePage.clickRegionNotAgreementButton();
        RegionPage regionPage = PageFactory.initElements(driver, RegionPage.class);
        regionPage.chooseMoscow();
        String regionText = mobilePage.getCurrentRegion();
        assertEquals("Москва и Московская область", regionText);
        driver.navigate().refresh();
        regionText = mobilePage.getCurrentRegion();
        assertEquals("Москва и Московская область", regionText);

        String moscowDefaultPrice = mobilePage.getPrice();
        mobilePage.clickChangeRegion();
        regionPage.chooseKrasnodar();
        String krasnodarDefaultPrice = mobilePage.getCurrentRegion();
        assertNotEquals(moscowDefaultPrice, krasnodarDefaultPrice);

        mobilePage.selectInternet(5);
        mobilePage.selectRing(5);
        mobilePage.changeModemCheckbox();
        mobilePage.changeBezlimSmsCheckbox();
        String krasnodarMaximumPrice = mobilePage.getPrice();

        mobilePage.clickChangeRegion();
        regionPage.chooseMoscow();
        mobilePage.selectInternet(5);
        mobilePage.selectRing(5);
        mobilePage.changeModemCheckbox();
        mobilePage.changeBezlimSmsCheckbox();

        String moscowMaximumPrice = mobilePage.getPrice();
        assertEquals(moscowMaximumPrice, krasnodarMaximumPrice);
    }

    @Test
    public void test23() {
        driver.get("https://www.tinkoff.ru/mobile-operator/tariffs/");

        MobilePage mobilePage = new MobilePage(driver);
        mobilePage.clickRegionNotAgreementButton();
        RegionPage regionPage = PageFactory.initElements(driver, RegionPage.class);
        regionPage.chooseMoscow();
        mobilePage.selectInternet(0);
        mobilePage.selectRing(0);

        mobilePage.changeMessengersCheckbox();
        mobilePage.changeSocialsCheckbox();

        String minPrice = mobilePage.getPrice();

        assertEquals("Общая цена: 0 \u20BD", minPrice);
        assertTrue(mobilePage.getOrderSimCardButtonIsEnabled());
    }


}
