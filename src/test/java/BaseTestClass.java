import org.example.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

import static org.example.DriverFactory.getDriver;

public class BaseTestClass {

    public static WebDriver driver;
    @BeforeAll
    public static void setUp() {
        driver = getDriver();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().window().maximize();
    }

    @BeforeEach
    public void openLoginPage() {
        driver.manage().deleteAllCookies();
        driver.get(LoginPage.LOGIN_PAGE_URL);
    }

    @AfterAll
    public static void tearDown() {
        driver.quit();
    }
}
