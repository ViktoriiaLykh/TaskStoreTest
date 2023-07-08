package org.example.pages.main;

import org.example.context.Page;
import org.example.dto.ShoppingItem;
import org.example.enums.SortOrderOption;
import org.hamcrest.Matchers;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

import static org.example.DriverFactory.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@Page
public class MainProductPage{

    @FindBy(xpath = ".//div[@id='inventory_container']")
    private WebElement inventoryContainer;

    @FindBy(xpath = ".//select[@class='product_sort_container']")
    private WebElement productSortDropdown;

    @FindBy(xpath = ".//div[@class='inventory_item_price']")
    private List<WebElement> productPricesList;

    @FindBy(xpath = ".//span[@class='shopping_cart_badge']")
    private WebElement cartCounter;

    @FindBy(xpath = ".//a[@class='shopping_cart_link']")
    private WebElement shoppingCartLink;

    public MainProductPage() {
        PageFactory.initElements(getDriver(), this);
    }

    public MainProductPage verifyUserOnTheProductPage() {
        assertThat(inventoryContainer.isDisplayed(), is(true));
        return this;
    }

    public MainProductPage changeSortOrder(SortOrderOption option) {
        new Select(productSortDropdown).selectByVisibleText(option.getOrderOption());
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

    public void navigateToShoppingCart() {
        shoppingCartLink.click();
    }

    public MainProductPage addToCart(String itemName) {
        WebElement addToCartButton = getDriver().findElement(By.xpath("//div[@class = 'inventory_item'][.//div[text()='" + itemName + "']]//button[text () = 'Add to cart']"));
        addToCartButton.click();
        return this;
    }

    public ShoppingItem getItemData(String itemName) {
        WebElement itemNaming = getDriver().findElement(By.xpath("//div[text() = '" + itemName + "']"));
        String name = itemNaming.getText();

        WebElement itemPrice = getDriver().findElement(By.xpath("//div[@class = 'inventory_item'][.//div[text()='" + itemName + "']]//div[@class = 'inventory_item_price']"));
        String price = itemPrice.getText();

        WebElement itemDescription = getDriver().findElement(By.xpath("//div[@class = 'inventory_item'][.//div[text()='" + itemName + "']]//div[@class='inventory_item_desc']"));
        String desc = itemDescription.getText();

        return new ShoppingItem(name, price, desc);
    }
}
