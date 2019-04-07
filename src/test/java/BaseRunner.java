import org.junit.AfterClass;
import org.junit.BeforeClass;


public class BaseRunner {

    public static ThreadLocal<Application> tlApp = new ThreadLocal<>();
    public static Application app;

    @BeforeClass
    public static void start() {
        if (tlApp.get() != null) {
            app = tlApp.get();
            return;
        }
        app = new Application();
        tlApp.set(app);
    }

    @AfterClass
    public static void tearDown() {
        app.quit();
    }
}
