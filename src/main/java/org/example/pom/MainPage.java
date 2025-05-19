package org.example.pom;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class MainPage {
    private static final String MAIN_PAGE_URL = "https://stellarburgers.nomoreparties.site";
    private final WebDriver driver;

    //Локатор кнопки "Войти в аккаунт"
    private static final By loginButton = By.xpath("//button[text()='Войти в аккаунт']");
    //Локатор кнопки "Личный кабинет"
    private static final By profileButton = By.cssSelector("a.AppHeader_header__link__3D_hX:nth-child(3)");
    //Локатор заголовка "Соберите бургер"
    private static final By pageTitle = By.xpath("//h1[text()='Соберите бургер']");
    //Локатор заголовка раздела
    private static final By section = By.className("text_type_main-medium");
    //Локатор блока ингредиентов
    private static final By ingredientBlock = By.className("BurgerIngredients_ingredients__menuContainer__Xu3Mo");

    //Локаторы табов секций
    private final By bunSection = By.xpath("//span[text()='Булки']");
    private final By sauceSection = By.xpath("//span[text()='Соусы']");
    private final By fillingSection =  By.xpath("//span[text()='Начинки']");

    //Локаторы секций
    private final By bunsList = By.xpath("//h2[text()='Булки']/ancestor::div[contains(@class, 'BurgerIngredients_ingredients__menuContainer')]");
    private final By sauceList = By.xpath("//h2[text()='Соусы']/ancestor::div[contains(@class, 'BurgerIngredients_ingredients__menuContainer')]");
    private final By fillingsList = By.xpath("//h2[text()='Начинки']/ancestor::div[contains(@class, 'BurgerIngredients_ingredients__menuContainer')]");

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Open main page")
    public void open() {
        driver.get(MAIN_PAGE_URL);
    }

    @Step("Click on login button")
    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

    @Step("Click on profile button")
    public void clickProfileButton() {
        driver.findElement(profileButton).click();
    }

    @Step("Is login button visible")
    public boolean isProfileButtonVisible() {
        return driver.findElement(profileButton).isDisplayed();
    }

    @Step("Is page title visible")
    public boolean isPageTitleVisible() {
        return driver.findElement(pageTitle).isDisplayed();
    }


    @Step("Click bun section")
    public void clickBunSection(){
        driver.findElement(bunSection).click();
    }

    @Step("Click sauce section")
    public void clickSauceSection(){
       driver.findElement(sauceSection).click();
    }

    @Step("Click filling section")
    public void clickFillingSection(){
        driver.findElement(fillingSection).click();
    }


    @Step("Scroll last section into view")
    public void scrollToLastSection() {
        List<WebElement> elements = driver.findElements(section);
        JavascriptExecutor js = (JavascriptExecutor)driver;

        js.executeScript("arguments[0].scrollTop = arguments[1].offsetTop;", driver.findElement(ingredientBlock),
                elements.get(elements.size() - 1));
    }

    @Step("Is buns section visible")
    public boolean isBunsVisible() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.visibilityOfElementLocated(bunsList));
        return driver.findElement(bunsList).isDisplayed();
    }

    @Step("Is sauce section visible")
    public boolean isSauceVisible() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.visibilityOfElementLocated(sauceList));
        return driver.findElement(sauceList).isDisplayed();
    }

    @Step("Is fillings section visible")
    public boolean isFillingsVisible() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.visibilityOfElementLocated(fillingsList));
        return driver.findElement(fillingSection).isDisplayed();
    }

}

