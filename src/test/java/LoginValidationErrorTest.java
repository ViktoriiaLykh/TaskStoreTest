import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.example.pages.login.LoginPage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LoginValidationErrorTest extends BaseTestClass {

    private static LoginPage loginPage;

    @BeforeAll
    public static void setPages(){
        loginPage = new LoginPage();
    }

    @Test
    @DisplayName("Invalid Login Test")
    @Description("Test to verify invalid login scenario")
    @Epic("Login")
    @Feature("Invalid Login")
    @Story("User should not be able to login when the account is locked")
    public void testInvalidLogin() {
        loginPage.loginAsLockedUser("locked_out_user", "secret_sauce")
                .verifyUserNotLoggedIn()
                .checkLoginValidationError("Epic sadface: Sorry, this user has been locked out.")
                .closeWarningMessage();
    }
}


