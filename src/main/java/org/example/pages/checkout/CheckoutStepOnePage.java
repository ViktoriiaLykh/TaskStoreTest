package org.example.pages.checkout;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static org.example.DriverFactory.getDriver;


public class CheckoutStepOnePage {

    @FindBy(xpath = ".//input[@data-test='firstName']")
    private WebElement checkoutFirstNameField;

    @FindBy(xpath = ".//input[@data-test='lastName']")
    private WebElement checkoutLastNameField;

    @FindBy(xpath = ".//input[@data-test='postalCode']")
    private WebElement checkoutPostalCodeField;

    @FindBy(xpath = ".//input[@data-test='continue']")
    private WebElement checkoutContinueButton;

    public CheckoutStepOnePage() {
        PageFactory.initElements(getDriver(), this);
    }

    public void fillOutFormAndNavigateToFinishPage(String firstName, String lastName, String postalCode) {
        fillOutFormAndClickContinue(firstName, lastName, postalCode);
    }

    private void fillOutFormAndClickContinue(String firstName, String lastName, String postalCode) {
        checkoutFirstNameField.click();
        checkoutFirstNameField.clear();
        checkoutFirstNameField.sendKeys(firstName);
        checkoutLastNameField.click();
        checkoutLastNameField.clear();
        checkoutLastNameField.sendKeys(lastName);
        checkoutPostalCodeField.click();
        checkoutPostalCodeField.clear();
        checkoutPostalCodeField.sendKeys(postalCode);
        checkoutContinueButton.click();
    }
}