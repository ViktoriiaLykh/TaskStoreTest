import org.example.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class LoginValidationErrorTest extends BaseTestClass {

    protected static LoginPage loginPage;

    @BeforeAll
    public static void setPages(){
        loginPage = new LoginPage();
    }


    @Test
    public void testInvalidLogin() {
        loginPage.loginAsLockedUser("locked_out_user", "secret_sauce")
                .verifyUserNotLoggedIn()
                .checkLoginValidationError("Epic sadface: Sorry, this user has been locked out.")
                .closeWarningMessage();
    }
}


