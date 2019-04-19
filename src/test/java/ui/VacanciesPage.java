package ui;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

public class VacanciesPage {
    @FindBy(xpath = "(.//*[normalize-space(text()) and normalize-space(.)='Заполните анкету'])[1]/following::div[2]")
    private WebElement pageSpace;

    @FindBy(xpath = "//input[@name='name']")
    private WebElement nameInput;

    @FindBy(xpath = "//input[@name='birthday']")
    private WebElement birthdayInput;

    @FindBy(xpath = "//input[@name='city']")
    private WebElement cityInput;

    @FindBy(xpath = "//input[@name='email']")
    private WebElement emailInput;

    @FindBy(xpath = "//input[@name='phone']")
    private WebElement phoneInput;

    @FindBy(xpath = "(.//*[normalize-space(text()) and normalize-space(.)='условиями передачи информации'])[1]/following::span[1]")
    private WebElement agreementCheckbox;

    @FindBy(xpath = "//*[@class='ui-form-field-error-message ui-form-field-error-message_ui-form']")
    private List<WebElement> errors;

    private void input(WebElement input, String value) {
        input.click();
        input.clear();
        input.sendKeys(value);
    }

    public void inputName(String name) {
        input(nameInput, name);
    }

    public void inputBirthday(String birthday) {
        input(birthdayInput, birthday);
    }

    public void inputCity(String city) {
        input(cityInput, city);
    }

    public void inputEmail(String email) {
        input(emailInput, email);
    }

    public void  inputPhone(String phone) {
        input(phoneInput, phone);
    }

    public void clickAgreement() {
        agreementCheckbox.click();
    }

    public void clickAllInputs() {
        nameInput.click();
        birthdayInput.click();
        cityInput.click();
        emailInput.click();
        phoneInput.click();
        agreementCheckbox.click();
    }

    public List<String> getErrorsText() {
        return errors
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public void clickPageSpace() {
        pageSpace.click();
    }
}
