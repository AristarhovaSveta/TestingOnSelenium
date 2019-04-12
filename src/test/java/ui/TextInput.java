package ui;

import lombok.AllArgsConstructor;
import org.openqa.selenium.WebElement;
@AllArgsConstructor
public class TextInput {
    private WebElement textBox;

    public void inputText(String text) {
        textBox.sendKeys(text);
    }

    public String getText() {
        return textBox.getText();
    }

    public void clear() {
        textBox.clear();
    }
}
