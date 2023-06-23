import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.example.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.example.SortOrderOption.PRICE_LOW_TO_HIGH;

public class PurchaseFlowWithValidUserTest extends BaseTestClass {

    protected static LoginPage loginPage;
    protected static MainProductPage mainProductPage;
    protected static ShoppingCart shoppingCart;
    protected static CheckoutStepOne checkoutStepOne;
    protected static CheckoutStepTwo checkoutStepTwo;
    protected static CheckoutComplete checkoutComplete;

    @BeforeAll
    public static void setPages(){
        loginPage = new LoginPage();
        mainProductPage = new MainProductPage();
        shoppingCart = new ShoppingCart();
        checkoutStepOne = new CheckoutStepOne();
        checkoutStepTwo = new CheckoutStepTwo();
        checkoutComplete = new CheckoutComplete();
    }

    @Test
    @DisplayName("Purchase flow with Valid User test")
    @Description("Test to verify purchase flow with a valid user")
    @Epic("Purchase Flow")
    @Feature("Valid User")
    @Story("User should be able to complete the purchase flow successfully")
    public void verifyPurchaseFlowWithValidUser() {
        String itemNameTShirt = "Sauce Labs Bolt T-Shirt";
        String itemNameBackPack = "Sauce Labs Backpack";

        loginPage.loginAsStandardUser("standard_user", "secret_sauce");

        mainProductPage.verifyUserOnTheProductPage()
                .changeSortOrder(PRICE_LOW_TO_HIGH)
                .verifySortOrderApplied()
                .addToCart(itemNameTShirt);

        ShoppingItem mainPageItem = mainProductPage.getItemData(itemNameTShirt);

        mainProductPage.verifyCartItemCount()
                .navigateToShoppingCart();

        shoppingCart.verifyCartItemMatch(mainPageItem)
                .removeProductFromCart()
                .checkEmptyCartCounter()
                .returnToProductPage();

        mainProductPage.addToCart(itemNameBackPack)
                .navigateToShoppingCart();

        shoppingCart.goToCheckout();

        checkoutStepOne.fillOutFormAndNavigateToFinishPage("Vikt", "Lykh", "451");

        checkoutStepTwo.verifyItemTotal()
                .clickFinishButton();

        checkoutComplete.verifyThankYouPageImage();
    }
}
