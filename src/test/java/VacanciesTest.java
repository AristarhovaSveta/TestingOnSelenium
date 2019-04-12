import ui.Checkbox;
import ui.Select;
import ui.TextInput;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

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
        driver.findElement(By.xpath("//input[@name='name']")).click();
        driver.findElement(By.xpath("//input[@name='birthday']")).click();
        driver.findElement(By.xpath("//input[@name='city']")).click();
        driver.findElement(By.xpath("//input[@name='email']")).click();
        driver.findElement(By.xpath("//input[@name='phone']")).click();
        driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Перетащите файлы сюда'])[1]/following::span[2]")).click();
        driver.findElement(By.xpath("//input[@name='socialLink0']")).click();
        driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='условиями передачи информации'])[1]/following::span[1]")).click();
        List<WebElement> allErrors = driver.findElements(By.xpath("//*[@class='ui-form-field-error-message ui-form-field-error-message_ui-form']"));
        for (WebElement error: allErrors) {
            assertEquals("Поле обязательное", error.getText());
        }
    }

    @Test
    public void test2() {
        driver.get(baseUrl);
        driver.findElement(By.xpath("//input[@name='name']")).click();
        driver.findElement(By.xpath("//input[@name='name']")).clear();
        driver.findElement(By.xpath("//input[@name='name']")).sendKeys("1123");
        driver.findElement(By.xpath("//input[@name='birthday']")).click();
        driver.findElement(By.xpath("//input[@name='birthday']")).clear();
        driver.findElement(By.xpath("//input[@name='birthday']")).sendKeys("31.13.333");
        driver.findElement(By.xpath("//input[@name='email']")).click();
        driver.findElement(By.xpath("//input[@name='email']")).clear();
        driver.findElement(By.xpath("//input[@name='email']")).sendKeys("привет");
        driver.findElement(By.xpath("//input[@name='phone']")).click();
        driver.findElement(By.xpath("//input[@name='phone']")).clear();
        driver.findElement(By.xpath("//input[@name='phone']")).sendKeys("000");
        driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Заполните анкету'])[1]/following::div[2]")).click();
        List<WebElement> allErrors = driver.findElements(By.xpath("//*[@class='ui-form-field-error-message ui-form-field-error-message_ui-form']"));
        assertEquals("Допустимо использовать только буквы русского алфавита и дефис", allErrors.get(0).getText());
        assertEquals("Поле заполнено некорректно", allErrors.get(1).getText());
        assertEquals("Введите корректный адрес эл. почты", allErrors.get(2).getText());
        assertEquals("Номер телефона должен состоять из 10 цифр, начиная с кода оператора", allErrors.get(3).getText());
    }

    @Test
    public void test21() {
        driver.get("https://www.google.ru/");
        WebElement googleSearch = driver.findElement(By.xpath("//input[@name='q']"));
        TextInput textInput = new TextInput(googleSearch);
        textInput.clear();
        textInput.inputText("мобайл тинькофф");
        textInput.inputText(" ");
        driver.findElement(By.xpath("//b[text()[contains(., 'тарифы')]]")).click();
        driver.findElement(By.xpath("//cite[text()[contains(., 'https://www.tinkoff.ru/mobile-operator/tariffs/')]]")).click();
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

        driver.findElement(By.xpath("//*[text()[contains(., 'Нет, изменить')]]")).click();
        WebElement allRegions = driver.findElement(By.xpath("(//*[@data-qa-file='MobileOperatorRegionsPopup'])[4]"));
        allRegions.findElements(By.xpath("//*[.='Москва и Московская обл.']")).get(0).click();
        String regionText = driver.findElement(By.xpath("//div[@data-qa-file='MvnoRegionConfirmation']")).getText();
        assertEquals("Москва и Московская область", regionText);
        driver.navigate().refresh();
        regionText = driver.findElement(By.xpath("//div[@data-qa-file='MvnoRegionConfirmation']")).getText();
        assertEquals("Москва и Московская область", regionText);

        String moscowDefaultPrice = driver.findElement(By.xpath("//form[@data-qa-file='UIForm']//h3[@data-qa-file='UITitle']")).getText();

        driver.findElement(By.xpath("//*[.='Москва и Московская область']")).click();
        allRegions = driver.findElement(By.xpath("(//*[@data-qa-file='MobileOperatorRegionsPopup'])[4]"));
        allRegions.findElements(By.xpath("//*[.='Краснодарский кр.']")).get(0).click();
        String krasnodarDefaultPrice = driver.findElement(By.xpath("//form[@data-qa-file='UIForm']//h3[@data-qa-file='UITitle']")).getText();

        assertNotEquals(moscowDefaultPrice, krasnodarDefaultPrice);

        WebElement internetSelectWebElement = driver.findElement(By.xpath("//span[text()[contains(., 'Интернет')]]"));
        ArrayList<WebElement> internetOptions = new ArrayList<>(driver.findElements(By.xpath("(//div[@class='ui-dropdown-field-list' and @data-qa-file='UIDropdownList'])[1]//div[@class='ui-dropdown-field-list__item']")));
        Select internetSelect = new Select(internetSelectWebElement, internetOptions, 1);
        internetSelect.selectOptionByIndex(5);

        WebElement ringsSelectWebElement = driver.findElement(By.xpath("//span[text()[contains(., 'Звонки')]]"));
        ArrayList<WebElement> ringsOptions = new ArrayList<>(driver.findElements(By.xpath("(//div[@class='ui-dropdown-field-list' and @data-qa-file='UIDropdownList'])[2]//div[@class='ui-dropdown-field-list__item']")));
        Select ringsSelect = new Select(ringsSelectWebElement, ringsOptions, 1);
        ringsSelect.selectOptionByIndex(5);

        WebElement modemCheckboxWebElement = driver.findElement(By.xpath("(//div[@data-qa-file='UIForm']//div[@data-qa-file='CheckboxSet'])[3]"));
        Checkbox modemCheckbox = new Checkbox(modemCheckboxWebElement);
        modemCheckbox.change();

        WebElement bezlimSmsCheckbox = driver.findElement(By.xpath("(//div[@data-qa-file='UIForm']//div[@data-qa-file='CheckboxSet'])[6]"));
        Checkbox bezlimSms = new Checkbox(bezlimSmsCheckbox);
        bezlimSms.change();

        String krasnodarMaximumPrice = driver.findElement(By.xpath("//form[@data-qa-file='UIForm']//h3[@data-qa-file='UITitle']")).getText();

        driver.findElement(By.xpath("//*[.='Краснодарский край']")).click();
        allRegions = driver.findElement(By.xpath("(//*[@data-qa-file='MobileOperatorRegionsPopup'])[4]"));
        allRegions.findElements(By.xpath("//*[.='Москва и Московская обл.']")).get(0).click();
        internetSelectWebElement = driver.findElement(By.xpath("//span[text()[contains(., 'Интернет')]]"));
        internetOptions = new ArrayList<>(driver.findElements(By.xpath("(//div[@class='ui-dropdown-field-list' and @data-qa-file='UIDropdownList'])[1]//div[@class='ui-dropdown-field-list__item']")));
        internetSelect = new Select(internetSelectWebElement, internetOptions, 1);
        internetSelect.selectOptionByIndex(5);

        ringsSelectWebElement = driver.findElement(By.xpath("//span[text()[contains(., 'Звонки')]]"));
        ringsOptions = new ArrayList<>(driver.findElements(By.xpath("(//div[@class='ui-dropdown-field-list' and @data-qa-file='UIDropdownList'])[2]//div[@class='ui-dropdown-field-list__item']")));
        ringsSelect = new Select(ringsSelectWebElement, ringsOptions, 1);
        ringsSelect.selectOptionByIndex(5);

        modemCheckboxWebElement = driver.findElement(By.xpath("(//div[@data-qa-file='UIForm']//div[@data-qa-file='CheckboxSet'])[3]"));
        modemCheckbox = new Checkbox(modemCheckboxWebElement);
        modemCheckbox.change();

        bezlimSmsCheckbox = driver.findElement(By.xpath("(//div[@data-qa-file='UIForm']//div[@data-qa-file='CheckboxSet'])[6]"));
        bezlimSms = new Checkbox(bezlimSmsCheckbox);
        bezlimSms.change();

        String moscowMaximumPrice = driver.findElement(By.xpath("//form[@data-qa-file='UIForm']//h3[@data-qa-file='UITitle']")).getText();

        assertEquals(moscowMaximumPrice, krasnodarMaximumPrice);
    }

    @Test
    public void test23() {
        driver.get("https://www.tinkoff.ru/mobile-operator/tariffs/");

        driver.findElement(By.xpath("//*[text()[contains(., 'Нет, изменить')]]")).click();
        WebElement allRegions = driver.findElement(By.xpath("(//*[@data-qa-file='MobileOperatorRegionsPopup'])[4]"));
        allRegions.findElements(By.xpath("//*[.='Москва и Московская обл.']")).get(0).click();
        WebElement internetSelectWebElement = driver.findElement(By.xpath("//span[text()[contains(., 'Интернет')]]"));
        ArrayList<WebElement> internetOptions = new ArrayList<>(driver.findElements(By.xpath("(//div[@class='ui-dropdown-field-list' and @data-qa-file='UIDropdownList'])[1]//div[@class='ui-dropdown-field-list__item']")));
        Select internetSelect = new Select(internetSelectWebElement, internetOptions, 1);
        internetSelect.selectOptionByIndex(0);

        WebElement ringsSelectWebElement = driver.findElement(By.xpath("//span[text()[contains(., 'Звонки')]]"));
        ArrayList<WebElement> ringsOptions = new ArrayList<>(driver.findElements(By.xpath("(//div[@class='ui-dropdown-field-list' and @data-qa-file='UIDropdownList'])[2]//div[@class='ui-dropdown-field-list__item']")));
        Select ringsSelect = new Select(ringsSelectWebElement, ringsOptions, 1);
        ringsSelect.selectOptionByIndex(0);

        WebElement modemCheckboxWebElement = driver.findElement(By.xpath("(//div[@data-qa-file='UIForm']//div[@data-qa-file='CheckboxSet'])[3]"));
        Checkbox modemCheckbox = new Checkbox(modemCheckboxWebElement);
        modemCheckbox.change();

        WebElement bezlimSmsCheckbox = driver.findElement(By.xpath("(//div[@data-qa-file='UIForm']//div[@data-qa-file='CheckboxSet'])[6]"));
        Checkbox bezlimSms = new Checkbox(bezlimSmsCheckbox);
        bezlimSms.change();

        String minPrice = driver.findElement(By.xpath("//form[@data-qa-file='UIForm']//h3[@data-qa-file='UITitle']")).getText();

        assertEquals("Общая цена: 0 \u20BD", minPrice);
        assertTrue(driver.findElement(By.xpath("//div[@data-qa-file='BlockingButton']//button")).isEnabled());
    }


}
