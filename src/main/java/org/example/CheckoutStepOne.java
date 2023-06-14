package org.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutStepOne {
    private final WebDriver driver;

    public CheckoutStepOne(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(xpath = "//input[@data-test=\"firstName\"]")
    private WebElement checkoutFirstNameField;

    @FindBy(xpath = "//input[@data-test=\"lastName\"]")
    private WebElement checkoutLastNameField;

    @FindBy(xpath = "//input[@data-test=\"postalCode\"]")
    private WebElement checkoutPostalCodeField;

    @FindBy(xpath = "//input[@data-test=\"continue\"]")
    private WebElement checkoutContinueButton;

    public CheckoutStepTwo fillOutFormAndClickContinue() {
        checkoutFirstNameField.click();
        checkoutFirstNameField.sendKeys("Vika");
        checkoutLastNameField.click();
        checkoutLastNameField.sendKeys("Lykh");
        checkoutPostalCodeField.click();
        checkoutPostalCodeField.sendKeys("451");
        checkoutContinueButton.click();
        return new CheckoutStepTwo(driver);
    }
}
