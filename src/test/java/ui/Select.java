package ui;

import lombok.AllArgsConstructor;
import org.openqa.selenium.WebElement;

import java.util.List;
@AllArgsConstructor
public class Select {
    private WebElement select;
    private List<WebElement> options;
    private int currentIndex;

    public void selectOptionByIndex(int index) {
        if (currentIndex<options.size()) {
            select.click();
            options.get(index).click();
            currentIndex = index;
        } else throw new IllegalArgumentException();
    }

    public WebElement getCurrentOption() {
        return options.get(currentIndex);
    }

}
