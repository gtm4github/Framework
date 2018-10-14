package generic;
//its an ABSTRACT Class(incomplete class)--->i purposefully made it abstract class, 
    //bcz i  want to pass the information to everybody across in the project that dnnt write any test method here 
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

@Listeners(Result.class)
public abstract class BaseTest implements IAutoConst {
	static {
		System.setProperty(CHROME_KEY, CHROME_VALUE);
		System.setProperty(GECKO_KEY, GECKO_VALUE);
	}
	public WebDriver driver;
	public String url=Utility.getPropertyValue(CONFIG_PATH,"URL");
	public String ITO=Utility.getPropertyValue(CONFIG_PATH, "ITO");
	public long l=Long.parseLong(ITO);
	
	@Parameters({"ip","browser"})//-->testNG things like 'alwaysRun=true'-->bcz any test case we execute,we need to execute this everytime
	@BeforeMethod(alwaysRun=true) //--->three things happening here in BeforeMethod--> 1} opening the browser 2} setting ITO 3} entering the URL
	public void openApp(@Optional("localhost")String ip,@Optional("chrome")String browser) {
		driver=Utility.openBrowser(ip, browser);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.get(url);//-->after this line of code ''testmethod' will execute
 	}
	@AfterMethod(alwaysRun=true)
	public void closeApp(ITestResult result) {
		String name=result.getName();
		int status=result.getStatus();
		if(status==2) { // it will check the status,if status=2,it means it fail---> then it will take the screenshot and will save in 'photo' folder
			String p=Utility.getPhoto(driver, "./photo");
			Reporter.log("TestName:"+name + " Status:FAIL",true);
			Reporter.log("PHOTO:"+p,true);
		}
		else {
			Reporter.log("TestName:"+name +" Status:PASS",true);;
		}
		driver.quit();//after closing the browser,Listener will update result in Summary.xlsx using onFinish() present in result class
		             // then TestNG will generate the result in test-output folder in emailable-report.html
	}
	

}
