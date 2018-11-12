package Login;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;

public class LogTest {
	
	WebDriver driver;
	login log;
	forgetpwd reset;
	signup s;
	XSSFWorkbook book;
	
	public LogTest(WebDriver wd,XSSFWorkbook b) {
		 driver = wd;
		 book = b;
		 log = new login(driver);
		 reset = new forgetpwd(driver);
		 s = new signup(driver);
	}
	public void doLogin() throws InterruptedException, IOException {
		log.log(book);
	}
	public void resetPwd() throws InterruptedException, IOException {
		reset.forget(book);
	}
	public void doRegister() throws InterruptedException, IOException {
		s.sign(book);
	}
}
