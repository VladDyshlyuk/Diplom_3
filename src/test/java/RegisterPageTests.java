import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.example.api.UserClient;
import org.example.models.User;
import org.example.pom.RegisterPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

import static org.example.driver.WebDriverCreator.createWebDriver;
import static org.example.generators.UserGenerator.randomUser;
import static org.example.utils.Utils.randomString;
import static org.junit.Assert.assertTrue;

public class RegisterPageTests {
    private static final String BASE_URL = "https://stellarburgers.nomoreparties.site/";
    private WebDriver driver;
    private User user;


    @Test
    @DisplayName("Успешная регистрация.")
    public void successfulSignUpTest(){
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.open();
        registerPage.fillRegistrationForm(user.getName(), user.getEmail(), user.getPassword());
        registerPage.clickSignUpButton();
        assertTrue(registerPage.loginButtonIsVisible());
    }

    @Test
    @DisplayName("Нельзя зарегестрироваться с коротким паролем.")
    public void wrongPasswordSignUpTest(){
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.open();
        user.setPassword(randomString(5));
        registerPage.fillRegistrationForm(user.getName(), user.getEmail(), user.getPassword());
        registerPage.clickSignUpButton();
        assertTrue(registerPage.passwordErrorIsVisible());
    }

    @Before
    public void setUp() {
        RestAssured.baseURI = BASE_URL;
        driver = createWebDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        user = randomUser();

    }


    @After
    @DisplayName("Delete test user")
    public void tearDown() {
        driver.close();
        UserClient userClient = new UserClient();
        userClient.delete(user);
    }
}
