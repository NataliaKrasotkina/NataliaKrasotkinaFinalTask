package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {
    @FindBy(xpath = "//input[@id='email']")
    private WebElement logInEmailField;

    @FindBy(xpath = "//input[@id='pass' and @name='login[password]']")
    private WebElement logInPasswordField;

    @FindBy(xpath = "//button[@id='send2' and @class='action login primary']")
    private WebElement signInButton;

    public void enterLogin(String email) {
        logInEmailField.sendKeys(email);
    }

    public void enterPassword(String loginPassword) {
        logInPasswordField.sendKeys(loginPassword);
    }

    public void clickSignInButton() {
        signInButton.click();
    }

    @Step("Login To App")
    public HomePage login(String login, String password) {
        HomePage.clickSignInLink();
        enterLogin(login);
        enterPassword(password);
        clickSignInButton();
        return new HomePage();
    }
}
