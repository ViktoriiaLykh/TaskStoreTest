import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.example.dto.ShoppingItem;
import org.example.pages.checkout.CheckoutCompletePage;
import org.example.pages.checkout.CheckoutStepOnePage;
import org.example.pages.checkout.CheckoutStepTwoPage;
import org.example.pages.login.LoginPage;
import org.example.pages.main.MainProductPage;
import org.example.pages.shoppingcart.ShoppingCartPage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.example.enums.SortOrderOption.PRICE_LOW_TO_HIGH;

public class PurchaseFlowWithValidUserTest extends BaseTestClass {

    private static LoginPage loginPage;
    private static MainProductPage mainProductPage;
    private static ShoppingCartPage shoppingCartPage;
    private static CheckoutStepOnePage checkoutStepOnePage;
    private static CheckoutStepTwoPage checkoutStepTwoPage;
    private static CheckoutCompletePage checkoutCompletePage;

    @BeforeAll
    public static void setPages(){
        loginPage = new LoginPage();
        mainProductPage = new MainProductPage();
        shoppingCartPage = new ShoppingCartPage();
        checkoutStepOnePage = new CheckoutStepOnePage();
        checkoutStepTwoPage = new CheckoutStepTwoPage();
        checkoutCompletePage = new CheckoutCompletePage();
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

        shoppingCartPage.verifyCartItemMatch(mainPageItem)
                .removeProductFromCart()
                .checkEmptyCartCounter()
                .returnToProductPage();

        mainProductPage.addToCart(itemNameBackPack)
                .navigateToShoppingCart();

        shoppingCartPage.goToCheckout();

        checkoutStepOnePage.fillOutFormAndNavigateToFinishPage("Vikt", "Lykh", "451");

        checkoutStepTwoPage.verifyItemTotal()
                .clickFinishButton();

        checkoutCompletePage.verifyThankYouPageImage();
    }
}
