package Login;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class login {

 WebDriver driver;
	
	
	
//	@FindBy(how=How.ID, using="onesignal-popover-cancel-button")
//	 WebElement nepop;
//	@FindBy(how=How.XPATH, using ="/html/body/div[6]/aside[1]/div[2]/header/button")
//	WebElement newpop;
	@FindBy(how=How.LINK_TEXT, using ="Sign In")
	WebElement signin;
	@FindBy(name="login[username]")
	WebElement emaill;
	@FindBy(name="login[password]")
	WebElement pwd; 
	
	@FindBy(how=How.ID, using="login-popup-login-click")
	WebElement signbtn;
	logxl l;
	
	public  login(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);		
	}
	
	public void log(XSSFWorkbook book) throws InterruptedException, IOException{
		l=new logxl(book);
		
		Thread.sleep(2000);
		
		//nepop.click();
		//Thread.sleep(5000);
		
		//newpop.click();
		
		System.out.print("popup closed");
		signin.click();
		ArrayList<String> userandpass=new ArrayList<String>();
		userandpass=l.readExcel("3");
		emaill.sendKeys(userandpass.get(0));
		pwd.sendKeys(userandpass.get(1));
		Thread.sleep(2000);
		signbtn.click();
	}
	

}
