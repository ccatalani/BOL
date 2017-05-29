import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class AccessEmail {

	public static void main(String[] args) throws InterruptedException {

		LocalDateTime now = LocalDateTime.now(); 
		int timeout = 15000;
		
		//Com o selenium 3.4.X o firefox abre somente com o geckodriver.exe e tem que adicionar a variável ao path do windows
		System.setProperty("webdriver.gecko.driver","C:\\Users\\Cinthia\\Documents\\APRENDIZADO AUTOMAÇÃO TESTE\\SELENIUM\\geckodriver.exe");

		//Tratando os erros de log para marionette
		DesiredCapabilities caps = new FirefoxOptions()
				.setProfile(new FirefoxProfile())
				.addTo(DesiredCapabilities.firefox());
		caps.setCapability("acceptInsecureCerts", true);
		caps.setCapability("marionette", true);

		//Execução do teste bol
		WebDriver driver = new FirefoxDriver(caps);
		driver.get("http://www.bol.com.br/");
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

		WebElement myUser = (new WebDriverWait(driver, timeout))
				.until(ExpectedConditions.presenceOfElementLocated(By.name("user")));
		myUser.sendKeys("ccatalani");

		WebElement myPassword = (new WebDriverWait(driver, timeout))
				.until(ExpectedConditions.presenceOfElementLocated(By.name("pass")));
		myPassword.sendKeys("Teste2017Bol!");

		WebElement lgnBtn = (new WebDriverWait(driver, timeout))
				.until(ExpectedConditions.presenceOfElementLocated(By.className("mod-header-login-button")));
		lgnBtn.click();

		// Ele demora para carregar a página e nunca encontra o botão, mesmo assim resolvi procurar por classe

	/*	WebElement btnEscrever = (new WebDriverWait(driver, tiemout))
				.until(ExpectedConditions.presenceOfElementLocated(By.className("bt-write")));
		btnEscrever.click();*/
		
		
		Thread.sleep(timeout);
		WebElement btnEscrever = driver.findElement(By.className("bt-write"));
		btnEscrever.click();
		Thread.sleep(timeout);
		
		WebElement mailTo = (new WebDriverWait(driver, timeout))
				.until(ExpectedConditions.presenceOfElementLocated(By.id("fake_input__field-to")));
		mailTo.sendKeys("ccatalani@bol.com.br");  

		WebElement fldSbj = (new WebDriverWait(driver, timeout))
				.until(ExpectedConditions.presenceOfElementLocated(By.id("field-subject")));
		fldSbj.sendKeys("Teste de Envio "+ now);  

		WebElement btnSend = (new WebDriverWait(driver, timeout))
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("html/body/div[3]/div/div[1]/form/div[2]/div[1]/menu[1]")));
		btnSend.click(); 
		 
	driver.close();
		
	}

}
