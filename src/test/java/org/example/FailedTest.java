package org.example;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.example.context.Driver;
import org.example.context.Wired;
import org.example.pages.login.LoginPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


@Driver
public class FailedTest extends BaseClassTest {

    @Wired
    private LoginPage loginPage;

    @Test
    @DisplayName("Failed Login Test")
    @Description("Test to verify invalid login scenario")
    @Epic("Login")
    @Feature("Invalid Login")
    @Story("Verify correct validation message")
    public void failedTest(){
        loginPage.loginAsLockedUser("standard_user11", "secret_sauce")
                .checkLoginValidationError("Epic sadface: Sorry, this user has been locked out.")
                .closeWarningMessage();
    }
}
