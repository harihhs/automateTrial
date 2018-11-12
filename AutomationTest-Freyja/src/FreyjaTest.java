import java.io.IOException;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import Login.LogTest;
import PDPandCart.PdpAction;
import catalog.CatalogMain;
import checkOutTests.CheckoutTest;
import drivers.Drivers;
import readData.ReadExcel;

public class FreyjaTest {
	
	WebDriver driver;
	ReadExcel r;
	XSSFWorkbook book;
	public FreyjaTest(String browser,String file) throws IOException {
		driver = Drivers.initDrivers(browser);
		r = new ReadExcel(file);
		book = r.book;
	}
	public static void main(String[] args) throws Exception {
		FreyjaTest newTest = new FreyjaTest("chrome","/Volumes/Hari/Codem/eclipse-workspace/AutomationTest-Freyja/input.xlsx");
		newTest.startTest();
	}
	public void startTest() throws Exception {
		String site = "https://uat-freyja.esclux.com";
		String prodType;
		driver.get(site);
		new WebDriverWait(driver, 20).until(wd->((JavascriptExecutor)wd).executeScript("console.log(document.readyState);return document.readyState").equals("complete"));
		Thread.sleep(5000);
		closePopups();
		String logChoice = "";
		LogTest lt = new LogTest(driver,book);
		switch (logChoice) {
		case "login":
			lt.doLogin();
			break;
		case "register":
			lt.doRegister();
			break;
		case "reset": lt.resetPwd();break;
		default:
			break;
		}
		CatalogMain cm = new CatalogMain(driver);
		prodType = cm.doCatalogTest(book);
		PdpAction pdpcheck = new PdpAction(driver);
		pdpcheck.addProducts(prodType);
		pdpcheck.doCartCheck();
		CheckoutTest testCheckout = new CheckoutTest(driver);
		testCheckout.testCheckout(driver,r);
		//driver.close();
	}
	void closePopups(){
		//Close notifications
		driver.findElement(By.id("onesignal-popover-cancel-button")).click(); 
		//Close newsletter
		((JavascriptExecutor)driver).executeScript("document.querySelector(\"body > div.modals-wrapper > aside:nth-child(3) > div.modal-inner-wrap > header > button\").click()");
	}
}
