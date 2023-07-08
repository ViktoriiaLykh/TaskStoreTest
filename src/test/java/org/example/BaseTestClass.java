package org.example;

import org.example.DriverFactory;
import org.example.context.PageContext;
import org.example.pages.login.LoginPage;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import java.time.Duration;

public class BaseTestClass {

    @BeforeAll
    public static void setUp() {
        DriverFactory.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
        DriverFactory.getDriver().manage().window().maximize();
        new PageContext().createContext();
    }

    @BeforeEach
    public void openLoginPage() {
        DriverFactory.getDriver().manage().deleteAllCookies();
        DriverFactory.getDriver().get(LoginPage.LOGIN_PAGE_URL);
    }

    @AfterAll
    public static void tearDown() {
        DriverFactory.quitDriver();
    }
}
