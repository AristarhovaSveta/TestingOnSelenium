import com.google.common.io.Files;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.opera.OperaOptions;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;

import java.io.File;
import java.io.IOException;


public class BrowsersFactory {

    public static class MyListener extends AbstractWebDriverEventListener {

        @Override
        public void onException(Throwable throwable, WebDriver driver) {
            File tmp = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File file = new File("target", "sccreen-" + System.currentTimeMillis() + ".png");
            try {
                Files.copy(tmp, file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static WebDriver buildDriver(String browserName) {
        switch (browserName) {

            case "chrome":
                return new ChromeDriver();

            case "firefox":
                return new FirefoxDriver();

            case "opera":
                OperaOptions operaOptions = new OperaOptions();
                operaOptions.setBinary(new File(System.getProperty("operaPath")));
                return new OperaDriver(operaOptions);

            default:
                return new ChromeDriver();
        }
    }
}