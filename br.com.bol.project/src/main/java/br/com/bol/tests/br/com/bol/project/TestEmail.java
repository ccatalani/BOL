package br.com.bol.tests.br.com.bol.project;

import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import pageobjects.Login;

public class TestEmail {

	private WebDriver driver;
	private Login login;

	int seconds = 40000;
	
	
	@Before
	public void setUp() {
		//Com o selenium 3.4.X o firefox abre somente com o geckodriver.exe e tem que adicionar a variável ao path do windows
		System.setProperty("webdriver.gecko.driver","C:\\Users\\Cinthia\\Documents\\APRENDIZADO AUTOMAÇÃO TESTE\\SELENIUM\\geckodriver.exe");

		//Tratando os erros de log para marionette
		DesiredCapabilities caps = new FirefoxOptions()
			.setProfile(new FirefoxProfile())
			.addTo(DesiredCapabilities.firefox());
		caps.setCapability("acceptInsecureCerts", true);
		caps.setCapability("marionette", true);
		
		driver = new FirefoxDriver();
		login = new Login(driver);
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
	}
	
	/*@Test
	public void failed() {
		login.with("ccatalani", "BadPassword!", seconds);
		assertTrue(login.failureLoginMessage(seconds));
	}*/
	
	@Test
	public void succeeded() throws InterruptedException {
		login.with("ccatalani", "Teste2017Bol!", seconds);
	}
	
	@Test
	public void main() throws InterruptedException {

		LocalDateTime now = LocalDateTime.now();
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

		// Ele demora para carregar a página e nunca encontra o botão, mesmo assim resolvi procurar por classe

	  /*	WebElement btnEscrever = (new WebDriverWait(driver, tiemout))
				.until(ExpectedConditions.presenceOfElementLocated(By.className("bt-write")));
		btnEscrever.click();*/
		
		Thread.sleep(seconds);
		WebElement btnEscrever = driver.findElement(By.className("bt-write"));
		btnEscrever.click();
		Thread.sleep(seconds);
		
		WebElement mailTo = (new WebDriverWait(driver, seconds))
				.until(ExpectedConditions.presenceOfElementLocated(By.id("fake_input__field-to")));
		mailTo.sendKeys("ccatalani@bol.com.br");  

		WebElement fldSbj = (new WebDriverWait(driver, seconds))
				.until(ExpectedConditions.presenceOfElementLocated(By.id("field-subject")));
		fldSbj.sendKeys("Teste de Envio "+ now);  

		WebElement btnSend = (new WebDriverWait(driver, seconds))
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("html/body/div[3]/div/div[1]/form/div[2]/div[1]/menu[1]")));
		btnSend.click(); 

	}
	
}
	
	
/*	@After
	public void tearDown() {
		driver.quit();
}*/
