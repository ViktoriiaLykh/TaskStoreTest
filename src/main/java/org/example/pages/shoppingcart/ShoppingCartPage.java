package org.example.pages.shoppingcart;

import org.example.context.Page;
import org.example.dto.ShoppingItem;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.function.Function;

import static io.qameta.allure.Allure.step;
import static org.example.context.DriverFactory.getDriver;


@Page
public class ShoppingCartPage {

    @FindBy(xpath = ".//button[text() = 'Remove']")
    private WebElement removeButton;

    @FindBy(xpath = ".//span[@class='shopping_cart_badge']")
    private WebElement shoppingCartCounter;

    @FindBy(xpath = ".//button[@data-test='continue-shopping']")
    private WebElement continueShoppingButton;

    @FindBy(xpath = ".//button[@data-test='checkout']")
    private WebElement checkoutButton;

    @FindBy(xpath = ".//button[@id='react-burger-menu-btn']")
    private WebElement leftSideBarOpenButton;

    @FindBy(xpath = ".//a[@id='reset_sidebar_link']")
    private WebElement resetAppStateButton;

    public ShoppingCartPage removeProductFromCart() {
        step("Click 'Remove' button", () -> {
            removeButton.click();
        });
        return this;
    }

    public ShoppingCartPage checkEmptyCartCounter() {
        step("Verify that the cart counter is empty", () -> {
            try {
                Assertions.assertFalse(shoppingCartCounter.isDisplayed());
            } catch (NoSuchElementException ignored) {
                // there is no other good way to check if element is present with @FindBy
            }
        });
        return this;
    }

    public void returnToProductPage() {
        step("Click 'Continue Shopping' button", () -> {
            continueShoppingButton.click();
        });
    }

    public void goToCheckout() {
        step("Click 'Checkout' button", () -> {
            checkoutButton.click();
        });
    }


    private ShoppingItem getItemCartData(String itemName) {
        WebElement itemNaming = getDriver().findElement(By.xpath("//div[text() = \"" + itemName + "\"]"));
        String name = itemNaming.getText();

        WebElement itemPrice = getDriver().findElement(By.xpath("//div[@class=\"cart_item\"][.//div[text()='" + itemName + "']]//div[@class = 'inventory_item_price']"));
        String price = itemPrice.getText();

        WebElement itemDescription = getDriver().findElement(By.xpath("//div[@class=\"cart_item\"][.//div[text()='" + itemName + "']]//div[@class=\"inventory_item_desc\"]"));
        String desc = itemDescription.getText();

        return step("Save item name, price and description from the Shopping Cart Page", () ->
                new ShoppingItem(name, price, desc)
        );
    }

    public ShoppingCartPage verifyCartItemMatch(ShoppingItem mainPageItem) {
        step("Verify that the item data from Main Page correspond to the item data from the Shopping Cart Page", () -> {
            ShoppingItem shoppingCartItem = getItemCartData(mainPageItem.getName());
            MatcherAssert.assertThat(shoppingCartItem, Matchers.equalTo(mainPageItem));
        });
        return this;
    }

    public <R> ShoppingCartPage verifyCartItemMatch(ShoppingItem mainPageItem, Function<ShoppingItem, R> getter) {
        step("Verify that the item property from Main Page correspond to the item data from the Shopping Cart Page", () -> {
            ShoppingItem shoppingCartItem = getItemCartData(mainPageItem.getName());

            R mainPageItemValue = getter.apply(mainPageItem);
            R shoppingCartItemValue = getter.apply(shoppingCartItem);
            MatcherAssert.assertThat(mainPageItemValue, Matchers.equalTo(shoppingCartItemValue));
        });
        return this;
    }

    public void reset() {
        step("Click 'left side bar' button", () -> {
            leftSideBarOpenButton.click();
        });
        step("Click 'Reset App State' button", () -> {
            resetAppStateButton.click();
        });
    }
}
