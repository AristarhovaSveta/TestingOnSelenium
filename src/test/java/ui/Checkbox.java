package ui;

import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class Checkbox {
    private WebElement checkbox;

    @Getter
    private boolean isChecked;

    public Checkbox(WebElement webElement) {
        checkbox = webElement;
        isChecked = webElement.findElement(By.xpath("//input")).isSelected();
    }

    public void change() {
        checkbox.click();
        isChecked = !isChecked;
    }
}
