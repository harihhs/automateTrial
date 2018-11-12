package PDPandCart;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductAdd {
	
	WebDriver driver;
    @FindBy(xpath="//*[@id=\"product-addtocart-button\"]")
    WebElement AddtoCart;
    @FindBy(xpath="/html/body/div[4]/aside[5]/div[2]/footer/button[1]")
    WebElement popup;
    @FindBy(xpath="//*[@id=\"header-section\"]/div/ul/div/a")
    WebElement minicart;
    @FindBy(xpath="//*[@id=\"top-cart-btn-checkout\"]")
    WebElement checkout;
    @FindBy(id="qty")
    WebElement quantity;
    @FindBy(id="selectedColor")
    WebElement color;
    @FindBy(id="finish_type")
    WebElement finish_type;
    @FindBy(xpath="//*[@id=\"product-options-wrapper\"]/div/div/div[2]/div/div/div/span[4]")
    WebElement colorCheerful;
    public String urlforconfig="https://uat-freyja.esclux.com/ph/gesgep-gesgep-lip-fresco-freyja-ge003.html";
    public String urlforsimple= "https://uat-freyja.esclux.com/ph/peter-thomas-roth-ptr-mega-rich-body-cleanser-250ml-ptr28-01-001.html";
    
    public ProductAdd(WebDriver driver) {
    	this.driver=driver;
	    PageFactory.initElements(driver, this);
    }
    public void getUrl(String choice) throws InterruptedException {
    	if(choice == "simple") {
    		driver.get(urlforsimple);
    		Thread.sleep(2000);
    		//onesignal_popup.click();
    		Thread.sleep(2000);
    	}
    	if(choice == "config") {
    		driver.get(urlforconfig);
    		Thread.sleep(2000);
    		//onesignal_popup.click();
    		Thread.sleep(2000);
    	}
    }
    
    public void selectQuantity() throws InterruptedException {
    	Select qty = new Select(quantity);
    	quantity.click();
    	qty.selectByIndex(1);
    	Thread.sleep(2000);
    }
    public void selectVariant() throws InterruptedException {
    	Select finish = new Select(finish_type);
    	finish_type.click();
    	finish.selectByVisibleText("All");
    	Thread.sleep(1000);
    	color.click();
    	Thread.sleep(1000);
    	colorCheerful.click();
    	Thread.sleep(2000);
    }
    public void AddProduct() throws InterruptedException {
    	Thread.sleep(300);
    	AddtoCart.click();
    	Thread.sleep(1000);
    }
    
    public void ClickMiniCart() throws InterruptedException {
    	//((JavascriptExecutor)driver).executeScript("document.querySelector(\"body > div.modals-wrapper > aside:nth-child(6) > div.modal-inner-wrap > header > button\").click()");
    	//driver.findElement(By.cssSelector("body > div.modals-wrapper > aside:nth-child(5) > div.modal-inner-wrap > header > button")).click();
    	Thread.sleep(10000);
    	new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(minicart));
    	minicart.click();
    	Thread.sleep(2000);
    	checkout.click();
    }
}