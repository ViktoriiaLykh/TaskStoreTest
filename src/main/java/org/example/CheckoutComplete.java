package org.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


public class CheckoutComplete {
    private final WebDriver driver;

    public CheckoutComplete(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(xpath = "//img[@alt=\"Pony Express\"]")
    private WebElement thankYouPageImage;

    public CheckoutComplete verifyThankYouPageImage() {
        assertThat(thankYouPageImage.isDisplayed(), is(true));
        return this;
    }

}
