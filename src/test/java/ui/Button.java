package ui;

import lombok.AllArgsConstructor;
import org.openqa.selenium.WebElement;
@AllArgsConstructor
public class Button {
    private WebElement button;

    public boolean isEnable() {
        return button.isEnabled();
    }
}
