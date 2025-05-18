package org.example.pom;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    private static final String LOGIN_PAGE_URL = "https://stellarburgers.nomoreparties.site/register";
    private final WebDriver driver;


    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    //Локатор формы входа
    private static final By loginForm = By.xpath("/html/body/div/div/main/div/form");
    //Локатор поля ввода почты
    private static final By emailField = By.xpath("/html/body/div/div/main/div/form/fieldset[1]/div/div/input");
    //Локатор поля ввода пароля
    private static final By passwordField = By.xpath("/html/body/div/div/main/div/form/fieldset[2]/div/div/input");
    //Локатор кнопки входа
    private static final By loginButton = By.xpath("/html/body/div/div/main/div/form/button");
    //Локатор кнопки личного кабинета
    private static final By profileButton = By.xpath("/html/body/div/div/header/nav/a/p");


    @Step("Open login page")
    public void open(){
        driver.get(LOGIN_PAGE_URL);
    }

    @Step("Enter email")
    public void setEmail(String email){
        driver.findElement(emailField).sendKeys(email);
    }
    @Step("Enter password")
    public void setPassword(String password){
        driver.findElement(passwordField).sendKeys(password);
    }

    @Step("Click login button")
    public void clickLoginButton(){
        driver.findElement(loginButton).click();
    }

    @Step("Fill login form")
    public void fillLoginForm(String email, String password){
        setEmail(email);
        setPassword(password);
    }

    @Step("Check that profile button is visible")
    public boolean isProfileButtonVisible(){
        return driver.findElement(profileButton).isDisplayed();
    }


}

