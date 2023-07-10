package org.example.pages.checkout;

import org.example.context.Page;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static io.qameta.allure.Allure.step;

@Page
public class CheckoutStepOnePage {

    @FindBy(xpath = ".//input[@data-test='firstName']")
    private WebElement checkoutFirstNameField;

    @FindBy(xpath = ".//input[@data-test='lastName']")
    private WebElement checkoutLastNameField;

    @FindBy(xpath = ".//input[@data-test='postalCode']")
    private WebElement checkoutPostalCodeField;

    @FindBy(xpath = ".//input[@data-test='continue']")
    private WebElement checkoutContinueButton;

    public void fillOutFormAndNavigateToFinishPage(String firstName, String lastName, String postalCode) {
        checkoutFirstNameField.clear();
        step("Enter First Name '" + firstName + "'", () -> {
            checkoutFirstNameField.sendKeys(firstName);
        });
        checkoutLastNameField.clear();
        step("Enter Last Name '" + lastName + "'", () -> {
            checkoutLastNameField.sendKeys(lastName);
        });
        checkoutPostalCodeField.clear();
        step("Enter Postal Code '" + postalCode + "'", () -> {
            checkoutPostalCodeField.sendKeys(postalCode);
        });
        step("Click 'Continue' button", () -> {
            checkoutContinueButton.click();
        });
    }
}
