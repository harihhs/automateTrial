/**
 * @author hari
 *
 */
package checkOutTests;
import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import drivers.Drivers;
import readData.CheckoutData;
import readData.ReadExcel;

public class CheckoutTest {
	
	WebDriver wd;
	public CheckoutTest(String driver) {
		wd = Drivers.initDrivers(driver);
	}
	public CheckoutTest(WebDriver d) {
		wd = d;
	}
	public static void main(String[] args) throws InterruptedException, IOException {
		CheckoutTest ct = new CheckoutTest("chrome");
		ct.wd.get("http://uat-freyja.esclux.com/ph/aveda-aveda-pure-abundancetm-style-prep100ml-aaf3010000.html");
		new WebDriverWait(ct.wd, 10).until(ExpectedConditions.elementToBeClickable(By.id("product-addtocart-button")));
		Thread.sleep(3000);
		ct.wd.findElement(By.id("product-addtocart-button")).click();
		Thread.sleep(4000);
		ct.wd.navigate().to("https://uat-freyja.esclux.com/ph/checkout");
		Thread.sleep(4000);
		ct.testCheckout(ct.wd,null);
	}
	public void testCheckout(WebDriver d,ReadExcel r) throws InterruptedException, IOException {
		//ReadExcel r = new ReadExcel("/Volumes/Hari/Codem/eclipse-workspace/AutomationTest-Freyja/input.xlsx");
		CheckoutData cd = new CheckoutData(r);
		CheckOutAction ca =  new CheckOutAction(d);
		ca.checkOut(cd);
	}
}