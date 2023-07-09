package org.example;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.example.context.Driver;
import org.example.context.Wired;
import org.example.dto.ShoppingItem;
import org.example.pages.checkout.CheckoutCompletePage;
import org.example.pages.checkout.CheckoutStepOnePage;
import org.example.pages.checkout.CheckoutStepTwoPage;
import org.example.pages.login.LoginPage;
import org.example.pages.main.MainProductPage;
import org.example.pages.shoppingcart.ShoppingCartPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.edge.EdgeDriver;

import static org.example.enums.SortOrderOption.PRICE_LOW_TO_HIGH;

@Driver(EdgeDriver.class)
public class PurchaseFlowWithValidUserTest extends BaseTestClass {

    @Wired
    private LoginPage loginPage;
    @Wired
    private MainProductPage mainProductPage;
    @Wired
    private ShoppingCartPage shoppingCartPage;
    @Wired
    private CheckoutStepOnePage checkoutStepOnePage;
    @Wired
    private CheckoutStepTwoPage checkoutStepTwoPage;
    @Wired
    private CheckoutCompletePage checkoutCompletePage;

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
