import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;

@Slf4j
public class EventListener extends AbstractWebDriverEventListener {
    @Override
    public void beforeGetText(WebElement element, WebDriver driver) {
        log.debug("try to get text from " + element.getTagName());
    }

    @Override
    public void beforeChangeValueOf(WebElement element, WebDriver driver, CharSequence[] keysToSend) {
        log.debug("keys to send:");
        for (CharSequence charSequence : keysToSend) {
            log.debug(charSequence.toString());
        }
    }

    @Override
    public void beforeFindBy(By by, WebElement element, WebDriver driver) {
        log.debug("finding by " + by.toString());
    }

    @Override
    public void beforeClickOn(WebElement element, WebDriver driver) {
        log.debug(element.getTagName() + " clicked");
    }
}
