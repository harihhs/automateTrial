package PDPandCart;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Cart {
	WebDriver driver;
    @FindBy(id="coupon_code")
    WebElement coupon;
    @FindBy(xpath="//*[@id=\"discount-coupon-form\"]/div/div/div[2]/button")
    WebElement Applycoupon;
    @FindBy(xpath="//*[@id=\"maincontent\"]/div[3]/div/div[3]/div[2]/div[5]/h4/i")
    WebElement ExpandGv;
    @FindBy(xpath="//*[@id=\"gv_code\"]")
    WebElement Gvcode;
    @FindBy(xpath="//*[@id=\"lot_number\"]")
    WebElement Gvpin;
    @FindBy(xpath="//*[@id=\"etpgv-coupon-form\"]/div/div/div[2]/button")
    WebElement ApplyGv;
    @FindBy(xpath="//*[@id=\"maincontent\"]/div[3]/div/div[3]/div[2]/ul/li[1]/button")
    WebElement Checkout;
    @FindBy(css="#freegift-items > div.owl-stage-outer > div > div:nth-child(6) > li > div > div > div.product-item-actions > div > button")
    WebElement freesample;
    
    
    public Cart(WebDriver driver) {
		this.driver=driver;
	    PageFactory.initElements(driver, this);
	}
    public void addFreesample() throws InterruptedException {
    	Thread.sleep(2000);
    	freesample.click();
    }
    public void enterCoupon(String couponcode) throws InterruptedException {
		coupon.sendKeys(couponcode);
		Applycoupon.click();
		Thread.sleep(1000);
	}
	public void goCheckout() throws InterruptedException {
		Thread.sleep(4000);
		Checkout.click();
		Thread.sleep(3000);
	}
	public void enterGV(String gvcode,String gvpin) throws InterruptedException {
		ExpandGv.click();
		Thread.sleep(1000);
		Gvcode.sendKeys(gvcode);
		Gvpin.sendKeys(gvpin);
		ApplyGv.click();
		Thread.sleep(1000);
	}

}
