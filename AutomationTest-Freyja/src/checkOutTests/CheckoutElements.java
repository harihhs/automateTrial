/**
 * @author hari
 *
 */
package checkOutTests;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CheckoutElements {
	WebElement loadMask,LoginMail,LoginKey,LoginBtn,fgtPwd,fgtEmail,resetBtn,ShipForm,newShippingBtn,newBillingCheckBox,
				BillForm,ShipMethod,paymentForm,cpnText,cpnSub,GVexpand,GVtxt,GVpin,GVsub,agreeBtn,PlaceBtn;
	WebDriver wd;
	public CheckoutElements(WebDriver w) {
		wd = w;
	}
	public void initElements() throws InterruptedException {
		LoginMail =  wd.findElement(By.id("checkout_login")).findElement(By.id("email"));
		LoginKey = wd.findElement(By.id("checkout_login")).findElement(By.id("pass"));
		LoginBtn = wd.findElement(By.id("checkout_login")).findElement(By.id("send2"));
		fgtPwd = wd.findElement(By.cssSelector("#login-form > div:nth-child(2) > div.secondary > a > span"));
		fgtEmail = wd.findElement(By.id("forgot-pass-section")).findElement(By.id("email"));
		resetBtn = wd.findElement(By.cssSelector("#forgot-form > div:nth-child(3) > div.primary > button"));
		Thread.sleep(500);
		ShipForm = wd.findElement(By.id("co-shipping-form"));
		newBillingCheckBox = wd.findElement(By.id("billing-address-same-as-shipping-"));
		BillForm = wd.findElement(By.cssSelector("#iosc-billing-container > fieldset > div.billing-address-form > form"));
		ShipMethod = wd.findElement(By.id("co-shipping-method-form"));
		paymentForm = wd.findElement(By.id("co-payment-form"));
		cpnText =  wd.findElement(By.id("iosc-summary")).findElement(By.id("discount-code"));
		cpnSub = wd.findElement(By.cssSelector("#iosc-summary #discount-form > div.actions-toolbar > div > button"));
		GVexpand = wd.findElement(By.cssSelector("#iosc-summary .freyja-discount #block-discount-heading"));
		GVtxt = wd.findElement(By.id("iosc-summary")).findElement(By.id("etpgv-discount-code"));
		GVpin= wd.findElement(By.id("etpgv-pin"));
		GVsub = wd.findElement(By.cssSelector("#etpgv-discount-form > div.primary > button"));
		agreeBtn = wd.findElement(By.cssSelector("#iosc-summary #agreement__1"));
		PlaceBtn = wd.findElement(By.cssSelector("#iosc-summary > div.iosc-place-order-container > button"));
		//newShippingBtn = wd.findElement(By.cssSelector("#newaddress-heading > span"));
	}
}