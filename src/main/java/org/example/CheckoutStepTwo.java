package org.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static org.example.DriverFactory.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class CheckoutStepTwo{

    public CheckoutStepTwo(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = ".//div[@class='inventory_item_price']")
    private WebElement itemPriceAfterCheckout;

    @FindBy(xpath = ".//div[@class='summary_subtotal_label']")
    private WebElement itemTotalPrice;

    @FindBy(xpath = ".//button[@data-test='finish']")
    private WebElement finishButton;

    private double extractPriceValue(String priceText) {
        String numericPart = priceText.replaceAll("[^0-9.]", "");
        return Double.parseDouble(numericPart);
    }

    public CheckoutStepTwo verifyItemTotal() {
        double itemPriceValue = extractPriceValue(itemPriceAfterCheckout.getText());
        double itemTotalPriceValue = extractPriceValue(itemTotalPrice.getText());
        assertThat(itemTotalPriceValue, equalTo(itemPriceValue));
        return this;
    }

    public void clickFinishButton() {
        finishButton.click();
    }


}
