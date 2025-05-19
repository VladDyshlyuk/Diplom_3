import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.example.api.UserClient;
import org.example.models.User;
import org.example.pom.LoginPage;
import org.example.pom.MainPage;
import org.example.pom.RegisterPage;
import org.example.pom.ResetPasswordPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

import static org.example.driver.WebDriverCreator.createWebDriver;
import static org.example.generators.UserGenerator.randomUser;
import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class LoginPageTests {
    private static final String BASE_URL = "https://stellarburgers.nomoreparties.site/";
    private WebDriver driver;
    private User user;
    UserClient userClient = new UserClient();

    //Точка входа в тест (
    // вход по кнопке «Войти в аккаунт» на главной,
    // вход через кнопку «Личный кабинет»,
    // вход через кнопку в форме регистрации,
    // вход через кнопку в форме восстановления пароля)
    private String enteringPoint;

    public LoginPageTests(User user, String enteringPoint) {
        this.user = user;
        this.enteringPoint = enteringPoint;
    }

    @Parameterized.Parameters(name = "{index}: Login with {1}")
    public static Object[][] getTestParams() {
        return new Object[][] {
                {randomUser(), "mainPageLoginButton"},
                {randomUser(), "mainPageProfileButton"},
                {randomUser(), "registerFormLoginButton"},
                {randomUser(), "forgotPasswordFormLoginButton"}
        };
    }

    @Test
    @DisplayName("Login with entry point")
    public void loginTest(){

        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        RegisterPage registerPage = new RegisterPage(driver);
        ResetPasswordPage resetPasswordPage = new ResetPasswordPage(driver);

        switch (enteringPoint){
            case "mainPageLoginButton":
                mainPage.open();
                mainPage.clickLoginButton();
                break;
            case "mainPageProfileButton":
                mainPage.open();
                mainPage.clickProfileButton();
                break;
            case "registerFormLoginButton":
                registerPage.open();
                registerPage.clickLoginLink();
                break;
            case "forgotPasswordFormLoginButton":
                resetPasswordPage.open();
                resetPasswordPage.clickLoginLink();
                break;
        }

        loginPage.fillLoginForm(user.getEmail(), user.getPassword());
        loginPage.clickLoginButton();
        assertTrue(mainPage.isProfileButtonVisible());

    }
    @Before
    public void setUp() {
        userClient = new UserClient();
        RestAssured.baseURI = BASE_URL;
        userClient.create(user);
        driver = createWebDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
    }


    @After
    @DisplayName("Delete test user")
    public void tearDown() {
        driver.close();
        userClient.delete(user);
    }

}
