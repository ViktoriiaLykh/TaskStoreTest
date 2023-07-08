package org.example.pages.checkout;

import org.example.context.Page;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
@Page
public class CheckoutStepTwoPage {

    @FindBy(xpath = ".//div[@class='inventory_item_price']")
    private WebElement itemPriceAfterCheckout;

    @FindBy(xpath = ".//div[@class='summary_subtotal_label']")
    private WebElement itemTotalPrice;

    @FindBy(xpath = ".//button[@data-test='finish']")
    private WebElement finishButton;

    public CheckoutStepTwoPage verifyItemTotal() {
        double itemPriceValue = extractPriceValue(itemPriceAfterCheckout.getText());
        double itemTotalPriceValue = extractPriceValue(itemTotalPrice.getText());
        assertThat(itemTotalPriceValue, equalTo(itemPriceValue));
        return this;
    }

    private double extractPriceValue(String priceText) {
        String numericPart = priceText.replaceAll("[^0-9.]", "");
        return Double.parseDouble(numericPart);
    }

    public void clickFinishButton() {
        finishButton.click();
    }
}
