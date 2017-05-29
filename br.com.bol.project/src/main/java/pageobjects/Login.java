package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import static org.junit.Assert.assertTrue;

public class Login extends Base{

	By usernameLocator = By.name("user");
	By passwordLocator = By.name("pass");
	By loginBtnLocator = By.className("mod-header-login-button");
	By writingEmailBtnLocator = By.className("bt-write");
	By failureLoginLocator = By.className("alert-danger");
	
	
	public Login(WebDriver driver) {
	super(driver);
		visit("http://www.bol.com.br/");
		}
	
	public void with(String user, String pass, int timeout) {
		type(user, usernameLocator);
		type(pass, passwordLocator);
		click(loginBtnLocator);
	}
	
	public Boolean successLoginPresent(int timeout) {
		return isDisplayed(writingEmailBtnLocator);
	}
	
	public Boolean failureLoginMessage(int timeout) {
		return isDisplayed(failureLoginLocator);	
	}
}
