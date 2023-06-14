package org.example;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.function.Function;

public class ShoppingCart {
    private final WebDriver driver;
    MainProductPage mainProductPage;

    public ShoppingCart(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(xpath = "//button[text() = \"Remove\"]")
    private WebElement removeButton;

    @FindBy(xpath = "//span[@class=\"shopping_cart_badge\"]")
    private WebElement shoppingCartCounter;

    @FindBy(xpath = "//button[@data-test=\"continue-shopping\"]")
    private WebElement continueShoppingButton;

    @FindBy(xpath = "//button[@data-test=\"checkout\"]")
    private WebElement checkoutButton;

    @FindBy(xpath = "//button[@id=\"react-burger-menu-btn\"]")
    private WebElement leftSideBarOpenButton;

    @FindBy(xpath = "//a[@id=\"reset_sidebar_link\"]")
    private WebElement resetAppStateButton;

    public ShoppingCart removeProductFromCart() {
        removeButton.click();
        return this;
    }

    public ShoppingCart checkEmptyCartCounter() {
        try {
            MatcherAssert.assertThat(shoppingCartCounter.isDisplayed(), CoreMatchers.equalTo(false));
        } catch (NoSuchElementException ignored) {
        }
        return this;
    }

    public MainProductPage returnToProductPage() {
        continueShoppingButton.click();
        return new MainProductPage(driver);
    }

    public CheckoutStepOne goToCheckout() {
        checkoutButton.click();
        return new CheckoutStepOne(driver);
    }


    private ShoppingItem getItemCartData(String itemName) {
        WebElement itemNaming = driver.findElement(By.xpath("//div[text() = \"" + itemName + "\"]"));
        String name = itemNaming.getText();

        WebElement itemPrice = driver.findElement(By.xpath("//div[@class=\"cart_item\"][.//div[text()='" + itemName + "']]//div[@class = 'inventory_item_price']"));
        String price = itemPrice.getText();

        WebElement itemDescription = driver.findElement(By.xpath("//div[@class=\"cart_item\"][.//div[text()='" + itemName + "']]//div[@class=\"inventory_item_desc\"]"));
        String desc = itemDescription.getText();

        return new ShoppingItem(name, price, desc);
    }

    public ShoppingCart verifyCartItemMatch(ShoppingItem mainPageItem) {
        ShoppingItem shoppingCartItem = getItemCartData(mainPageItem.getName());
        MatcherAssert.assertThat(shoppingCartItem, Matchers.equalTo(mainPageItem));
        return this;
    }

    public <R> ShoppingCart verifyProductProperty(ShoppingItem mainPageItem, Function<ShoppingItem, R> getter) {
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
