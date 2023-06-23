import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.example.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class ShoppingCartPriceTest extends BaseTestClass {


    protected static LoginPage loginPage;
    protected static MainProductPage mainProductPage;
    protected static ShoppingCart shoppingCart;

    @BeforeAll
    public static void setPages(){
        loginPage = new LoginPage();
        mainProductPage = new MainProductPage();
        shoppingCart = new ShoppingCart();
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

        shoppingCart.verifyProductProperty(mainPageItem, ShoppingItem::getPrice);
    }

    @AfterEach
    public void reset() {
        shoppingCart.reset();
    }
}

