package org.example.pages.shoppingcart;

import org.example.dto.ShoppingItem;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.function.Function;

import static org.example.DriverFactory.getDriver;

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

    public ShoppingCartPage() {
        PageFactory.initElements(getDriver(), this);
    }

    public ShoppingCartPage removeProductFromCart() {
        removeButton.click();
        return this;
    }

    public ShoppingCartPage checkEmptyCartCounter() {
        try {
            Assertions.assertFalse(shoppingCartCounter.isDisplayed());
        } catch (NoSuchElementException ignored) {
            // there is no other good way to check if element is present with @FindBy
        }
        return this;
    }

    public void returnToProductPage() {
        continueShoppingButton.click();
    }

    public void goToCheckout() {
        checkoutButton.click();
    }


    private ShoppingItem getItemCartData(String itemName) {
        WebElement itemNaming = getDriver().findElement(By.xpath("//div[text() = \"" + itemName + "\"]"));
        String name = itemNaming.getText();

        WebElement itemPrice = getDriver().findElement(By.xpath("//div[@class=\"cart_item\"][.//div[text()='" + itemName + "']]//div[@class = 'inventory_item_price']"));
        String price = itemPrice.getText();

        WebElement itemDescription = getDriver().findElement(By.xpath("//div[@class=\"cart_item\"][.//div[text()='" + itemName + "']]//div[@class=\"inventory_item_desc\"]"));
        String desc = itemDescription.getText();

        return new ShoppingItem(name, price, desc);
    }

    public ShoppingCartPage verifyCartItemMatch(ShoppingItem mainPageItem) {
        ShoppingItem shoppingCartItem = getItemCartData(mainPageItem.getName());
        MatcherAssert.assertThat(shoppingCartItem, Matchers.equalTo(mainPageItem));
        return this;
    }

    public <R> ShoppingCartPage verifyCartItemMatch(ShoppingItem mainPageItem, Function<ShoppingItem, R> getter) {
        ShoppingItem shoppingCartItem = getItemCartData(mainPageItem.getName());

        R mainPageItemValue = getter.apply(mainPageItem);
        R shoppingCartItemValue = getter.apply(shoppingCartItem);
        MatcherAssert.assertThat(mainPageItemValue, Matchers.equalTo(shoppingCartItemValue));
        return this;
    }

    public void reset() {
        leftSideBarOpenButton.click();
        resetAppStateButton.click();
    }
}
