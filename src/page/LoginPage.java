package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	@FindBy(id="username")
	private WebElement unTB;
	
	@FindBy(name="pwd")
	private WebElement pwTB;
	
	@FindBy(xpath="//div[text()='Login ']")
	private WebElement loginBTN;
	
	
	public LoginPage(WebDriver driver) {  //---> we r taking 'overloaded user defined Constructor',which is used to initialize the variable
		PageFactory.initElements(driver, this);
	}
	
	public void setUserName(String un) { //--> here we r using 'setter method' to utilize 
		unTB.sendKeys(un);
	}
	public void setPassword(String pw) {
		pwTB.sendKeys(pw);
	}
	public void clickLogin() {
		loginBTN.click();
	}
	
	

}
