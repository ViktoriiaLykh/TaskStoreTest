package org.example;

import org.hamcrest.Matchers;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class MainProductPage {

    private final WebDriver driver;

    private final List<ShoppingItem> cartItems = new ArrayList<>();


    public MainProductPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(xpath = "//div[@id=\"inventory_container\"]")
    private WebElement inventoryContainer;

    @FindBy(xpath = "//select[@class=\"product_sort_container\"]")
    private WebElement productSortContainer;

    @FindBy(xpath = "//option[text() = 'Price (low to high)']")
    private WebElement priceFilterLowToHigh;

    @FindBy(xpath = "//div[@class=\"inventory_item_price\"]")
    private List<WebElement> productPricesList;


    @FindBy(xpath = "//span[@class=\"shopping_cart_badge\"]")
    private WebElement cartCounter;

    @FindBy(xpath = "//button[@data-test=\"add-to-cart-sauce-labs-bolt-t-shirt\"]")
    private WebElement addToCartBoltTShirtButton;


    @FindBy(xpath = "//a[@class=\"shopping_cart_link\"]")
    private WebElement shoppingCartLink;


    public MainProductPage verifyUserOnPage() {
        assertThat(inventoryContainer.isDisplayed(), is(true));
        return this;
    }

    public MainProductPage changeSortOrderToPriceLowToHigh() {
        productSortContainer.click();
        priceFilterLowToHigh.click();
        return this;
    }

    public MainProductPage verifySortOrderApplied() {
        List<Double> prices = new ArrayList<>();
        for (WebElement element : productPricesList) {
            String priceText = element.getText().replace("$", "");
            double price = Double.parseDouble(priceText);
            prices.add(price);
        }

        assertThat(prices, everyItem(lessThanOrEqualTo(prices.get(prices.size() - 1))));
        return this;
    }

    public MainProductPage verifyCartItemCount() {
        String itemCountText = cartCounter.getText();
        assertThat(itemCountText, Matchers.equalTo("1"));
        return this;
    }

    public ShoppingCart navigateToShoppingCart() {
        shoppingCartLink.click();
        return new ShoppingCart(driver);
    }


    public MainProductPage addToCart(String itemName) {
        WebElement addToCartButton = driver.findElement(By.xpath("//div[@class = 'inventory_item'][.//div[text()='" + itemName + "']]//button[text () = \"Add to cart\"]"));
        addToCartButton.click();

        return this;
    }

    public ShoppingItem getItemData(String itemName) {
        WebElement itemNaming = driver.findElement(By.xpath("//div[text() = \"" + itemName + "\"]"));
        String name = itemNaming.getText();

        WebElement itemPrice = driver.findElement(By.xpath("//div[@class = 'inventory_item'][.//div[text()='" + itemName + "']]//div[@class = 'inventory_item_price']"));
        String price = itemPrice.getText();

        WebElement itemDescription = driver.findElement(By.xpath("//div[@class = 'inventory_item'][.//div[text()='" + itemName + "']]//div[@class=\"inventory_item_desc\"]"));
        String desc = itemDescription.getText();

        return new ShoppingItem(name, price, desc);
    }

}
