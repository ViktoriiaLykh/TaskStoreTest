package org.example;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static org.example.DriverFactory.getDriver;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


public class CheckoutComplete{
    public CheckoutComplete() {
        PageFactory.initElements(getDriver(), this);
    }

    @FindBy(xpath = ".//img[@alt='Pony Express']")
    private WebElement thankYouPageImage;

    public void verifyThankYouPageImage() {
        Assertions.assertTrue(thankYouPageImage.isDisplayed(), "");
        assertThat(thankYouPageImage.isDisplayed(), is(true));
    }

}
