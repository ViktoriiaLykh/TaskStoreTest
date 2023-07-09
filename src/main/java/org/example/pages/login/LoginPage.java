package org.example.pages.login;


import org.example.context.Page;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

@Page
public class LoginPage {

    public static final String LOGIN_PAGE_URL = "https://www.saucedemo.com/";

    @FindBy(xpath = ".//input[@id='user-name']")
    private WebElement usernameFiled;

    @FindBy(xpath = ".//input[@id='password']")
    private WebElement passwordField;

    @FindBy(xpath = ".//input[@id='login-button']")
    private WebElement loginButton;

    @FindBy(xpath = ".//div[@class='login_logo']")
    private WebElement loginLogo;

    @FindBy(xpath = ".//button[@class='error-button']")
    private WebElement loginValidationErrorCloseButton;

    @FindBy(xpath = ".//h3[@data-test='error']")
    private WebElement loginValidationErrorMessage;

    public LoginPage checkLoginValidationError(String errorMessage) {
        String displayedErrorMessage = loginValidationErrorMessage.getText();
        assertThat(displayedErrorMessage, equalTo(errorMessage));
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

    public LoginPage loginAsLockedUser(String username, String password) {
        loginAsUser(username, password);
        return this;
    }

    public void loginAsStandardUser(String username, String password) {
        loginAsUser(username, password);
    }

    private void loginAsUser(String username, String password) {
        usernameFiled.clear();
        usernameFiled.sendKeys(username);
        passwordField.clear();
        passwordField.sendKeys(password);
        loginButton.click();
    }
}
