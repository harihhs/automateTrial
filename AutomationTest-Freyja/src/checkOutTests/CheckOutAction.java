
package checkOutTests;

import java.util.HashMap;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import readData.CheckoutData;
/**
 * @author hari
 *
 */
public class CheckOutAction {
	
	CheckoutElements ce;
	CheckoutData data;
	WebDriver wd;
	public CheckOutAction(WebDriver d) {
		wd = d;
		ce = new CheckoutElements(wd);
	}
	public void login(String user,String key) {
		ce.LoginMail.sendKeys(user);
		ce.LoginKey.sendKeys(key);
		ce.LoginBtn.click();
	}
	public void resetPwd(String mail) { 
		ce.fgtPwd.click();
		ce.fgtEmail.sendKeys(mail);
		ce.fgtPwd.click();
	}
	public void setAddressData(WebElement form) throws InterruptedException {
		HashMap<String, String> formData;
		if(form.getAttribute("id").contains("ship")) {
			formData = data.shipAddress;
		}
		else {
			 formData = data.billAddress;
		}
		form.findElement(By.name("firstname")).sendKeys(formData.get("firstname"));
		form.findElement(By.name("lastname")).sendKeys(formData.get("lastname"));
		if(formData.containsKey("email")){
			form.findElement(By.id("customer-email")).sendKeys(formData.get("email"));
			Thread.sleep(500);
		}
		form.findElement(By.name("telephone")).sendKeys(formData.get("telephone"));
		form.findElement(By.name("street[0]")).sendKeys(formData.get("street[0]"));
		if(formData.containsKey("street[1]")){
			form.findElement(By.name("street[1]")).sendKeys(formData.get("street[1]"));
		}
		new Select(form.findElement(By.name("region_id"))).selectByVisibleText(formData.get("region_id"));
		Thread.sleep(1500);
		new Select(form.findElement(By.name("cityNew"))).selectByVisibleText(formData.get("cityNew"));
		Thread.sleep(1500);
		new Select(form.findElement(By.name("barangayNew"))).selectByVisibleText(formData.get("barangayNew"));
		new WebDriverWait(wd,10).until(ExpectedConditions.elementToBeClickable(ce.PlaceBtn));
		form.findElement(By.name("postcode")).sendKeys(formData.get("postcode"));
		Thread.sleep(1000);
	}

	public void setShippingMethod() {
		new WebDriverWait(wd,15).until(ExpectedConditions.elementToBeClickable((By.cssSelector("#checkout-shipping-method-load > table > tbody > tr > td.col.col-method"))));
		((JavascriptExecutor)wd).executeScript("document.querySelector(\"#checkout-shipping-method-load > table > tbody > tr > td.col.col-method \").click()");
		new WebDriverWait(wd,10).until(ExpectedConditions.elementToBeClickable(wd.findElement(By.cssSelector("#iosc-summary > div.iosc-place-order-container > button"))));
	}
	public void setPaymentMethod() {
		String[] payData = data.payData;
		new WebDriverWait(wd, 10).until(ExpectedConditions.invisibilityOf(wd.findElement(By.className("loading-mask"))));
		switch (payData[0].toLowerCase()) {
		case "cod":
			((JavascriptExecutor)wd).executeScript("document.querySelector('#cashondelivery').click()");
			break;
		case "paypal":
			//ce.paymentForm.findElement(By.id("paypal_express")).click();
			((JavascriptExecutor)wd).executeScript("document.querySelector('#paypal_express').click()");
			break;
		case "dragonpay":
			//ce.paymentForm.findElement(By.id("dragonpay")).click();
			((JavascriptExecutor)wd).executeScript("document.querySelector('#dragonpay').click()");
			break;
		case "visa":
			//ce.paymentForm.findElement(By.id("ncybersource")).click();
			((JavascriptExecutor)wd).executeScript("document.querySelector('#ncybersource').click()");
			wd.findElement(By.id("ncybersource_cc_number")).sendKeys(payData[1]);
			wd.findElement(By.id("ncybersource_cc_cid")).sendKeys(payData[2]);
			new Select(wd.findElement(By.id("ncybersource_expiration"))).selectByIndex(4);
			new Select(wd.findElement(By.id("ncybersource_expiration_yr"))).selectByIndex(4);
			break;
		default:
			break;
		}
	}
	public void applyPromo(String code) throws InterruptedException {
		new WebDriverWait(wd, 10).until(ExpectedConditions.invisibilityOf(wd.findElement(By.className("loading-mask"))));
		ce.cpnText.sendKeys(code);
		Thread.sleep(2000);
		ce.cpnSub.click();
		Thread.sleep(3000);
	}
	public void applyGV(String code,String pin) {
		ce.GVexpand.click();
		ce.GVtxt.sendKeys(code);
		ce.GVpin.sendKeys(pin);
		ce.GVsub.click();
	}
	public void acceptTnC() throws InterruptedException {
		ce.loadMask = wd.findElement(By.className("loading-mask"));
		if(!ce.loadMask.isDisplayed()) {
			((JavascriptExecutor)wd).executeScript("document.querySelector(\"#iosc-summary #agreement__1\").click()");
			//ce.agreeBtn.click();
			}
		else {
			Thread.sleep(1000);
			System.out.println("Accept:in accept else");
			acceptTnC();
		}
	}
	public void placeOrder() throws InterruptedException {
		((JavascriptExecutor)wd).executeScript("document.querySelector(\"#iosc-summary > div.iosc-place-order-container > button\").click()");
	}
	public void checkOut(CheckoutData cd) throws InterruptedException {
		try {
		data = cd;
		cd.getCheckoutData();
		new WebDriverWait(wd, 20).until(wd->((JavascriptExecutor)wd).executeScript("console.log(document.readyState);return document.readyState").equals("complete"));
		System.out.println("COMPLETED\n Now sleeping");
		Thread.sleep(4000);
		System.out.println("COMPLETED");
		new WebDriverWait(wd, 20).until(ExpectedConditions.presenceOfElementLocated(By.id("checkout_login")));
		ce.initElements();
		if( !data.isLoggedIn && (data.user != null && data.key != null)) {
			login(data.user, data.key);
			new WebDriverWait(wd, 10).until(wd->((JavascriptExecutor)wd).executeScript("console.log(document.readyState);return document.readyState").equals("complete"));
			ce = new CheckoutElements(wd);
			ce.initElements();
			Thread.sleep(2000);
			if(!data.useExisting) {
				Thread.sleep(1000);
				wd.findElement(By.cssSelector("#newaddress-heading > span")).click();
				setAddressData(ce.ShipForm);
				Thread.sleep(1000);
				if(data.isDiffBilling) {
					//ce.newBillingCheckBox.click();
					((JavascriptExecutor)wd).executeScript("document.querySelector(\"#billing-address-same-as-shipping-\").click()");
					Thread.sleep(2000);
					setAddressData(ce.BillForm);
					}
			}
		}
		else
		{
			setAddressData(ce.ShipForm);
			Thread.sleep(1000);
			if(data.isDiffBilling) {
				//ce.newBillingCheckBox.click();
				((JavascriptExecutor)wd).executeScript("document.querySelector(\"#billing-address-same-as-shipping-\").click()");
				Thread.sleep(2000);
				setAddressData(ce.BillForm);
			}
		}
		setShippingMethod();
		new WebDriverWait(wd, 10).until(ExpectedConditions.invisibilityOf(wd.findElement(By.className("loading-mask"))));
		setPaymentMethod();
		//applyPromo("flat25");
		//applyGV("GV0200", "LVTV16000896");
		acceptTnC();
		placeOrder();
		}
		catch(Exception e) {
			e.printStackTrace();
			System.out.println("\nError is:\n"+e.toString()+"\n\n");
			//wd.close();
		}
	}
}
