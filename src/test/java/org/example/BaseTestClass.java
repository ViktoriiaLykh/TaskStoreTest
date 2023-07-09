package org.example;

import org.example.context.DriverFactory;
import org.example.context.PageContext;
import org.example.pages.login.LoginPage;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInstance;

import java.time.Duration;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BaseTestClass {
    @BeforeAll
    public void setUp() throws Exception {
        new PageContext().createContext(this);
        DriverFactory.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
        DriverFactory.getDriver().manage().window().maximize();
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
