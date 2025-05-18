package org.example.pom;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegisterPage {
    private static final String REGISTER_PAGE_URL = "https://stellarburgers.nomoreparties.site/register";
    private final WebDriver driver;

    public RegisterPage(WebDriver driver){
        this.driver = driver;
    }

    //Локатор поля ввода имени
    private final By nameField = By.xpath("/html/body/div/div/main/div/form/fieldset[1]/div/div/input");
    //Локатор поля ввода email
    private final By emailField = By.xpath("/html/body/div/div/main/div/form/fieldset[2]/div/div/input");
    //Локатор поля ввода пароля
    private final By passwordField = By.xpath("/html/body/div/div/main/div/form/fieldset[3]/div/div/input");
    //Локатор кнопки "Зарегистрироавться"
    private final By signUpButton = By.xpath(".//form/button");
    //Локатор формы регистрации
    private final By authForm = By.xpath(".//form");
    //Локатор ошибки "Некорректный пароль"
    private final By wrongPasswordError = By.cssSelector(".input__error");
    //Локатор кнопки "Войти"
    private final By loginButton = By.cssSelector(".button_button__33qZ0");

    public void waitForLoadForm() {
        new WebDriverWait(this.driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOf(this.driver.findElement(this.authForm)));
    }

    private void waitForElementLoad(By element) {
        new WebDriverWait(this.driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOf(this.driver.findElement(element)));

    }

    @Step("Open register page")
    public void open(){
        driver.get(REGISTER_PAGE_URL);
    }

    @Step("Set name")
    public void setName(String name){
        driver.findElement(nameField).sendKeys(name);
    }

    @Step("Set email")
    public void setEmail(String email){
        driver.findElement(emailField).sendKeys(email);
    }

    @Step("Set password")
    public void setPassword(String password){
        driver.findElement(passwordField).sendKeys(password);
    }

    @Step("Set sign up button")
    public void clickSignUpButton(){
        driver.findElement(signUpButton).click();
    }

    @Step("Fill registration form")
    public void fillRegistrationForm(String name, String email, String password){
        setName(name);
        setEmail(email);
        setPassword(password);
    }

    @Step("Check if login button is visible")
    public boolean loginButtonIsVisible(){
        return driver.findElement(loginButton).isDisplayed();
    }

    @Step("Check if password error is visible")
    public boolean passwordErrorIsVisible(){
        return driver.findElement(wrongPasswordError).isDisplayed();
    }


}
