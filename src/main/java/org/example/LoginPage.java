package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class LoginPage {

    public static final String LOGIN_PAGE_URL = "https://www.saucedemo.com/";

    private final WebDriver driver;

    public LoginPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(xpath = "//input[@id=\"user-name\"]")
    private WebElement usernameFiled;

    @FindBy(xpath = "//input[@id=\"password\"]")
    private WebElement passwordField;

    @FindBy(xpath = "//input[@id=\"login-button\"]")
    private WebElement loginButton;

    @FindBy(xpath = "//div[@class=\"login_logo\"]")
    private WebElement loginLogo;

    @FindBy(xpath = "//button[@class=\"error-button\"]")
    private WebElement loginValidationErrorCloseButton;

    public LoginPage checkLoginValidationError(String errorMessage) {
        WebElement loginValidationError = driver.findElement(By.xpath("//h3[text() = '" + errorMessage + "']"));
        assertThat(loginValidationError.isDisplayed(), is(true));
        return this;
    }

    public LoginPage verifyUserNotLoggedIn() {
        assertThat(loginLogo.isDisplayed(), is(true));
        return this;
    }

    public LoginPage closeWarningMessage() {
        loginValidationErrorCloseButton.click();
        return this;
    }

    public LoginPage loginAsLockedUser() {
        usernameFiled.click();
        usernameFiled.sendKeys("locked_out_user");
        passwordField.click();
        passwordField.sendKeys("secret_sauce");
        loginButton.click();
        return this;
    }

    public MainProductPage loginAsStandardUser() {
        usernameFiled.click();
        usernameFiled.sendKeys("standard_user");
        passwordField.click();
        passwordField.sendKeys("secret_sauce");
        loginButton.click();
        return new MainProductPage(driver);
    }
}
