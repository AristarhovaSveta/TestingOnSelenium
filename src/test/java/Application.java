import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.util.concurrent.TimeUnit;

public class Application {

    protected WebDriver driver;
    private final String browserName = System.getProperty("browser");


    public Application() {
        driver = new EventFiringWebDriver(getDriver());
        ((EventFiringWebDriver) driver).register(new BrowsersFactory.MyListener());
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    public void quit() {
        driver.quit();
        driver = null;
    }

    private WebDriver getDriver() {
        return BrowsersFactory.buildDriver(browserName);
    }

}
