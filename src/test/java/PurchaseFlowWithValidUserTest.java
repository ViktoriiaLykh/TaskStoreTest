import org.example.ShoppingItem;
import org.junit.jupiter.api.Test;

import static org.example.SortOrderOption.PRICE_LOW_TO_HIGH;

public class PurchaseFlowWithValidUserTest extends BaseTestClass {

    @Test
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
