import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.example.api.UserClient;
import org.example.models.User;
import org.example.pom.LoginPage;
import org.example.pom.MainPage;
import org.example.pom.ProfilePage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

import static org.example.driver.WebDriverCreator.createWebDriver;
import static org.example.generators.UserGenerator.randomUser;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ProfileTests {
    private static final String BASE_URL = "https://stellarburgers.nomoreparties.site/";
    private WebDriver driver;
    private User user;
    private MainPage mainPage;
    private LoginPage loginPage;
    private ProfilePage profilePage;

    UserClient userClient = new UserClient();

    @Test
    @DisplayName("Open profile page by click on the profile button")
    public void enterProfilePageTest() {
        login();
        mainPage.clickProfileButton();
        assertEquals("Ошибка при открытии страницы профиля", user.getName(), profilePage.getUserName());
    }

    @Test
    @DisplayName("Go to constructor from profile page by click on constructor button")
    public void goToConstructorFromProfilePageTest() {
        login();
        profilePage.clickOnConstructor();
        assertTrue(mainPage.isPageTitleVisible());
    }

    @Test
    @DisplayName("Go to constructor from profile page by click on logo")
    public void goToConstructorFromProfilePageByLogoTest() {
        login();
        profilePage.clickOnConstructor();
        assertTrue(mainPage.isPageTitleVisible());
    }

    @Test
    @DisplayName("Logout")
    public void logoutFromProfilePageTest() {
        login();
        mainPage.clickProfileButton();
        profilePage.clickOnLogout();
        assertTrue(loginPage.isLoginFormVisible());
    }

    @Step("Login")
    public void login() {
        loginPage = new LoginPage(driver);
        profilePage = new ProfilePage(driver);
        mainPage = new MainPage(driver);

        loginPage.open();
        loginPage.fillLoginForm(user.getEmail(), user.getPassword());
        loginPage.clickLoginButton();
    }

    @Before
    public void setUp() {
        RestAssured.baseURI = BASE_URL;
        user = randomUser();
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
