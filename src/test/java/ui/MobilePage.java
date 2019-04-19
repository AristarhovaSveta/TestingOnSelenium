package ui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;

public class MobilePage {
    private WebDriver driver;

    @FindBy(xpath = "//span[contains(., 'Нет, изменить')]")
    private WebElement regionConfirmation;

    @FindBy(xpath = "//div[@data-qa-file='DesktopContainer']//div[@data-qa-file='MvnoRegionConfirmation']")
    private WebElement regionChangeButton;

    @FindBy(xpath = "//form[@data-qa-file='UIForm']//h3[@data-qa-file='UITitle']")
    private WebElement price;

    @FindBy(xpath = "//div[@data-qa-file='MvnoRegionConfirmation']")
    private WebElement currentRegion;

    @FindBy(xpath = "//span[text()[contains(., 'Интернет')]]")
    private WebElement internetSelect;

    @FindBy(xpath = "//span[text()[contains(., 'Звонки')]]")
    private WebElement ringsSelect;

    @FindBy(xpath = "(//div[@data-qa-file='UIForm']//div[@data-qa-file='CheckboxSet'])[3]")
    private WebElement modemCheckbox;

    @FindBy(xpath = "(//div[@data-qa-file='UIForm']//div[@data-qa-file='CheckboxSet'])[6]")
    private WebElement bezlimSmsCheckbox;

    @FindBy(xpath = "((//div[@data-qa-file='CheckboxSet'])[3]//div[@data-qa-file='CheckboxWithDescription'])[1]")
    private WebElement messengersCheckbox;

    @FindBy(xpath = "((//div[@data-qa-file='CheckboxSet'])[3]//div[@data-qa-file='CheckboxWithDescription'])[2]")
    private WebElement socialsCheckbox;

    @FindBy(xpath = "//div[@data-qa-file='BlockingButton']//button")
    private WebElement orderSimCardButton;

    public MobilePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickChangeRegion() {
        regionChangeButton.click();
    }

    public void clickRegionNotAgreementButton() {
        regionConfirmation.click();
    }

    public String getPrice() {
        return price.getText();
    }

    public void selectInternet(int index) {
        ArrayList<WebElement> internetOptions = new ArrayList<>(driver.findElements(By.xpath("(//div[@class='ui-dropdown-field-list' and @data-qa-file='UIDropdownList'])[1]//div[@class='ui-dropdown-field-list__item']")));
        new Select(internetSelect, internetOptions, 1)
                .selectOptionByIndex(index);
    }

    public void selectRing(int index) {
        ArrayList<WebElement> ringsOptions = new ArrayList<>(driver.findElements(By.xpath("(//div[@class='ui-dropdown-field-list' and @data-qa-file='UIDropdownList'])[2]//div[@class='ui-dropdown-field-list__item']")));
        new Select(ringsSelect, ringsOptions, 1)
                .selectOptionByIndex(index);
    }

    public void changeModemCheckbox() {
        modemCheckbox.click();
    }

    public void changeBezlimSmsCheckbox() {
        bezlimSmsCheckbox.click();
    }

    public void changeMessengersCheckbox() {
        messengersCheckbox.click();
    }

    public void changeSocialsCheckbox() {
        socialsCheckbox.click();
    }

    public boolean getOrderSimCardButtonIsEnabled() {
        return orderSimCardButton.isEnabled();
    }

    public String getCurrentRegion() {
        return currentRegion.getText();
    }
}
