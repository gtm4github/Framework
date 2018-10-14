package script;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import generic.BaseTest;
import generic.Result;
import generic.Utility;
import page.LoginPage;
//--> three things happening in testmethod----> {1} getting data from excel sheet {2} calling the methods of POM class 
//    {3} performing the action on browser i.e. entering the data in required text field


//@Listeners(Result.class)
public class DemoA extends BaseTest{
	@Test                                                     //-->in testmethod,we r calling all the methods present in POM class
	public void testA() {
		String un=Utility.getXLData(DATA_PATH, "DemoA", 1, 0); //--> to calling the below mentioned methods we need to get the data
		String pw=Utility.getXLData(DATA_PATH, "DemoA", 1, 1);
		
		LoginPage l=new LoginPage(driver);
		l.setUserName(un);
		l.setPassword(pw);
		l.clickLogin();         //then listener will check the status,and it will increment the passCount or failCount a/c to status
		                          //-------> after this line of code 'AfterMethod' will execute
	}

}
