import org.example.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

import static org.example.DriverFactory.getDriver;

public class BaseTestClass {

    private static WebDriver driver;
    protected static LoginPage loginPage;
    protected static MainProductPage mainProductPage;
    protected static ShoppingCart shoppingCart;
    protected static CheckoutStepOne checkoutStepOne;
    protected static CheckoutStepTwo checkoutStepTwo;
    protected static CheckoutComplete checkoutComplete;

    @BeforeAll
    public static void setUp() {
        driver = getDriver();
        loginPage = new LoginPage(driver);
        mainProductPage = new MainProductPage(driver);
        shoppingCart = new ShoppingCart(driver);
        checkoutStepOne = new CheckoutStepOne(driver);
        checkoutStepTwo = new CheckoutStepTwo(driver);
        checkoutComplete = new CheckoutComplete(driver);
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
