import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.example.dto.ShoppingItem;
import org.example.pages.login.LoginPage;
import org.example.pages.main.MainProductPage;
import org.example.pages.shoppingcart.ShoppingCartPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class ShoppingCartPriceTest extends BaseTestClass {

    private static LoginPage loginPage;
    private static MainProductPage mainProductPage;
    private static ShoppingCartPage shoppingCart;

    @BeforeAll
    public static void setPages(){
        loginPage = new LoginPage();
        mainProductPage = new MainProductPage();
        shoppingCart = new ShoppingCartPage();
    }

    @BeforeEach
    public void logIn() {
        loginPage.loginAsStandardUser("standard_user", "secret_sauce");
    }

    @ParameterizedTest
    @ValueSource(strings = {"Test.allTheThings() T-Shirt (Red)", "Sauce Labs Fleece Jacket", "Sauce Labs Bike Light"})
    @DisplayName("Product price test")
    @Description("Verify that the price on the main page matches the price in the shopping cart")
    @Epic("Shopping Cart")
    @Feature("Product Price")
    @Story("User should be able to verify that the price on the main page matches the price in the shopping cart")
    public void testProductPrice(String itemName) {
        ShoppingItem mainPageItem = mainProductPage.getItemData(itemName);
        mainProductPage.addToCart(itemName)
                .navigateToShoppingCart();

        shoppingCart.verifyCartItemMatch(mainPageItem, ShoppingItem::getPrice);
    }

    @AfterEach
    public void reset() {
        shoppingCart.reset();
    }
}

