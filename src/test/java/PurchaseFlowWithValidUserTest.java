import org.example.LoginPage;
import org.example.MainProductPage;
import org.example.ShoppingItem;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class PurchaseFlowWithValidUserTest {


    private static WebDriver driver;
    private static LoginPage loginPage;
    private static MainProductPage mainProductPage;

    @BeforeAll
    public static void setUp() {
        driver = new ChromeDriver();
        loginPage = new LoginPage(driver);
        mainProductPage = new MainProductPage(driver);
        driver.manage().window().maximize();
    }

    @BeforeEach
    public void navigateLoginPage() {
        driver.get(LoginPage.LOGIN_PAGE_URL);
        driver.manage().deleteAllCookies();
    }

    @Test
    public void verifyPurchaseFlowWithValidUser() {
        String itemName = "Sauce Labs Backpack";

        loginPage.loginAsStandardUser()
                .verifyUserOnPage()
                .changeSortOrderToPriceLowToHigh()
                .verifySortOrderApplied()
                .addToCart(itemName);

        ShoppingItem mainPageItem = mainProductPage.getItemData(itemName);

        mainProductPage.verifyCartItemCount()
                .navigateToShoppingCart()
                .verifyCartItemMatch(mainPageItem)
                .removeProductFromCart()
                .checkEmptyCartCounter()
                .returnToProductPage()
                .addToCart(itemName)
                .navigateToShoppingCart()
                .goToCheckout()
                .fillOutFormAndClickContinue()
                .verifyItemTotal()
                .clickFinishButton()
                .verifyThankYouPageImage();
    }

    @AfterAll
    public static void tearDown() {
        driver.quit();
    }
}
