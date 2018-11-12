package Login;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class forgetpwd {
WebDriver driver;
	@FindBy(how=How.ID, using="onesignal-popover-cancel-button")
	WebElement nepop;
	@FindBy(how=How.XPATH, using ="/html/body/div[6]/aside[1]/div[2]/header/button")
	WebElement newpop;
	@FindBy(how=How.LINK_TEXT, using ="Sign In")
	WebElement signin;
	@FindBy(name="login[username]")
	WebElement emaill;
	@FindBy(id="login-popup-forgotpassword")
	 WebElement forgtpwd;
	@FindBy(id="email_address")
	 WebElement ema;
	@FindBy(id="login-popup-forgotpassowrd-click")
	 WebElement cont;
	
public forgetpwd(WebDriver driver) {
	this.driver=driver;
	PageFactory.initElements(driver, this);
}

public void forget(XSSFWorkbook book) throws InterruptedException, IOException {
	forgetxl f = new forgetxl();
	Thread.sleep(2000);
	nepop.click();
	Thread.sleep(5000);
	newpop.click();
	signin.click();
	Thread.sleep(2000);
	forgtpwd.click();
	String cellforuser;
	cellforuser=f.read_excel(book,"1");
	ema.sendKeys(cellforuser);
	cont.click();	
	}
}
