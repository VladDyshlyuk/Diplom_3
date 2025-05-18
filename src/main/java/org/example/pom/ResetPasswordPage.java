package org.example.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ResetPasswordPage {
    private static final String LOGIN_PAGE_URL = "https://stellarburgers.nomoreparties.site/forgot-password";
    private final WebDriver driver;

    //Селектор сслыки "Войти"
    private final static By loginLink = By.cssSelector("[href=\"/login\"]");

    public ResetPasswordPage(WebDriver driver) {
        this.driver = driver;
    }

    public void open() {
        driver.get(LOGIN_PAGE_URL);
    }

    public void clickLoginLink() {
        driver.findElement(loginLink).click();
    }
}
