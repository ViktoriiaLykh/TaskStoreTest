package org.example.pages.checkout;

import org.example.context.Page;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@Page
public class CheckoutCompletePage {

    @FindBy(xpath = ".//img[@alt='Pony Express']")
    private WebElement thankYouPageImage;

    public void verifyThankYouPageImage() {
        Assertions.assertTrue(thankYouPageImage.isDisplayed(), "");
        assertThat(thankYouPageImage.isDisplayed(), is(true));
    }
}
