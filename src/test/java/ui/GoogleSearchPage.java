package ui;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class GoogleSearchPage {
    @FindBy(xpath = "//input[@name='q']")
    private WebElement searchInput;

    @FindBy(xpath = "//b[text()[contains(., 'тарифы')]]")
    private WebElement tinkoffMobileHint;

    @FindBy(xpath = "//cite[text()[contains(., 'https://www.tinkoff.ru/mobile-operator/tariffs/')]]")
    private WebElement tinkoffMobileRef;

    public void inputQuery(String query) {
        searchInput.sendKeys(query);
    }

    public void chooseHint() {
        tinkoffMobileHint.click();
    }

    public void chooseRef() {
        tinkoffMobileRef.click();
    }

}
