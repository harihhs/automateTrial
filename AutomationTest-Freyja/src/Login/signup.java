package Login;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class signup {

 WebDriver driver;
	
	
	@FindBy(how=How.ID, using="onesignal-popover-cancel-button")
	WebElement nepop;
	@FindBy(how=How.XPATH, using ="/html/body/div[6]/aside[1]/div[2]/header/button")
	WebElement newpop;
	
	@FindBy(how=How.LINK_TEXT, using ="Register")
	  WebElement register;
	
	@FindBy(name="firstname")
	  WebElement firstname;
	
	@FindBy(name="lastname")
	  WebElement lastname;
	
	@FindBy(id="username")
	  WebElement username;
	
	@FindBy(id="email_address_register")
	  WebElement email;
	
	@FindBy(id="password")
	  WebElement pwd;
	
	@FindBy(id="dob-day")
	  WebElement day;
	
	@FindBy(id="dob-month")
	  WebElement month;
	
	@FindBy(id="dob-year")
	  WebElement year;
	
	  Select sel_day, sel_month, sel_year; 
	
	@FindBy(id="recaptcha-anchor")
	  WebElement captcha;
	
	
	@FindBy(how=How.ID, using="login-popup-login-click")
	  WebElement signbtn;
	
	
	public  signup(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public void sign(XSSFWorkbook book) throws InterruptedException, IOException {
		registerxl reg = new registerxl(book);
		Thread.sleep(2000);
		nepop.click();
		Thread.sleep(5000);
		newpop.click();
		register.click();
		ArrayList<String> register=new ArrayList<String>();
		register=reg.read_regexcel("2");
		firstname.sendKeys(register.get(0));
		lastname.sendKeys(register.get(1));
		username.sendKeys(register.get(2));
		email.sendKeys(register.get(3));
		pwd.sendKeys(register.get(4));
		sel_day=new Select(day);
		sel_month=new Select(month);
		sel_year=new Select(year);
		sel_day.selectByIndex(10);
		sel_month.selectByIndex(3);
		sel_year.selectByIndex(20);
		captcha.click();
	}

}
