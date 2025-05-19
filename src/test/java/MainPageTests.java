import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.example.api.UserClient;
import org.example.models.User;
import org.example.pom.MainPage;
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
public class MainPageTests {
    private static final String BASE_URL = "https://stellarburgers.nomoreparties.site/";
    private static final String FIRST_SECTION_NAME = "Булки";
    private WebDriver driver;
    private User user;
    private MainPage mainPage;
    UserClient userClient = new UserClient();

    public String section;

    public MainPageTests(String section) {
        this.section = section;
    }

    @Parameterized.Parameters(name = "section = {0}")
    public static Object[][] getSections() {
        return new Object[][] {
                {"Булки"},
                {"Соусы"},
                {"Начинки"}
        };
    }

    @Test
    @DisplayName("Check if scrolling to the section works")
    public void checkIfScrollingToTheSectionWorks() {
        mainPage.open();
        switch (section) {
            case FIRST_SECTION_NAME:
                mainPage.scrollToLastSection();
                mainPage.clickBunSection();
                assertTrue(mainPage.isBunsVisible());
                return;
            case "Соусы":
                mainPage.clickSauceSection();
                assertTrue(mainPage.isSauceVisible());
                return;
            case "Начинки":
                mainPage.clickFillingSection();
                assertTrue(mainPage.isFillingsVisible());
                break;
        }

    }

    @Before
    public void setUp() {
        RestAssured.baseURI = BASE_URL;
        user = randomUser();
        userClient.create(user);
        driver = createWebDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(6));
        mainPage = new MainPage(driver);
    }


    @After
    @DisplayName("Delete test user")
    public void tearDown() {
        driver.close();
        userClient.delete(user);
    }

}
