import org.junit.jupiter.api.Test;


public class LoginValidationErrorTest extends BaseTestClass {

    @Test
    public void testInvalidLogin() {
        loginPage.loginAsLockedUser("locked_out_user", "secret_sauce")
                .verifyUserNotLoggedIn()
                .checkLoginValidationError("Epic sadface: Sorry, this user has been locked out.")
                .closeWarningMessage();
    }
}


