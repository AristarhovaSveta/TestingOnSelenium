package ui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegionPage {
    @FindBy(xpath = "(//*[@data-qa-file='MobileOperatorRegionsPopup'])[4]")
    private WebElement regionsSelector;

    public void chooseMoscow() {
        regionsSelector.findElement(By.xpath("//*[.='Москва и Московская обл.']")).click();
    }

    public void chooseKrasnodar() {
        regionsSelector.findElement(By.xpath("//*[.='Краснодарский кр.']")).click();
    }
}
