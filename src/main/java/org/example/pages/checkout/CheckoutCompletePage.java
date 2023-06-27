package org.example.pages.checkout;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static org.example.DriverFactory.getDriver;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


public class CheckoutCompletePage {

    @FindBy(xpath = ".//img[@alt='Pony Express']")
    private WebElement thankYouPageImage;

    public CheckoutCompletePage() {
        PageFactory.initElements(getDriver(), this);
    }

    public void verifyThankYouPageImage() {
        Assertions.assertTrue(thankYouPageImage.isDisplayed(), "");
        assertThat(thankYouPageImage.isDisplayed(), is(true));
    }
}
