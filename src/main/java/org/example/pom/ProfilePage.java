package org.example.pom;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProfilePage {
    private static final String PROFILE_PAGE_URL = "https://stellarburgers.nomoreparties.site/account/profile";

    private final WebDriver driver;

    //Локатор для имени пользователя на странице профиля
    private static final By userName = By.xpath("/html/body/div/div/main/div/div/div/ul/li[1]/div/div/input");
    //Локатор для логотипа
    private static final By logo = By.xpath("/html/body/div/div/header/nav/div/a");
    //Локатор для кнопки конструктора на странице профиля
    private static final By constructor = By.xpath("/html/body/div/div/header/nav/ul/li[1]/a");
    //Локатор для кнопки выхода на странице профиля
    private static final By logout = By.xpath("//button[text()='Выход']");

    public void open(){
        driver.get(PROFILE_PAGE_URL);
    }

    @Step("Get user name on profile page")
    public String getUserName() {
        return driver.findElement(userName).getAttribute("value");
    }

    @Step("Click on logo")
    public void clickOnLogo() {
        driver.findElement(logo).click();
    }

    @Step("Click on constructor")
    public void clickOnConstructor() {
        driver.findElement(constructor).click();
    }

    @Step("Click on logout")
    public void clickOnLogout() {
        driver.findElement(logout).click();
    }

    public ProfilePage(WebDriver driver) {
        this.driver = driver;
    }


}
