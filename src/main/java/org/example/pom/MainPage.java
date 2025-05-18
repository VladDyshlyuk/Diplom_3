package org.example.pom;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPage {
    private static final String MAIN_PAGE_URL = "https://stellarburgers.nomoreparties.site";
    private final WebDriver driver;

    //Локатор кнопки "Войти в аккаунт"
    private static final By loginButton = By.xpath("//button[text()='Войти в аккаунт']");
    //Локатор кнопки "Личный кабинет"
    private static final By profileButton = By.cssSelector("a.AppHeader_header__link__3D_hX:nth-child(3)");

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
}
