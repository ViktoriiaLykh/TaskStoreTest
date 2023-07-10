package org.example.pages.checkout;

import org.example.context.Page;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static io.qameta.allure.Allure.step;

@Page
public class CheckoutCompletePage {

    @FindBy(xpath = ".//img[@alt='Pony Express']")
    private WebElement thankYouPageImage;

    public void verifyThankYouPageImage() {
        step("Verify that the 'Thank you page image' is displayed", () -> {
            Assertions.assertTrue(thankYouPageImage.isDisplayed(), "");
        });
    }
}
