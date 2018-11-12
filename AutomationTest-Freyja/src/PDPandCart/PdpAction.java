package PDPandCart;

import java.text.ParseException;
import org.openqa.selenium.WebDriver;

public class PdpAction {
	
	ProductAdd Productadd;
	Cart cart;
	Calculateamount cal;
	WebDriver driver;
	
	public PdpAction(WebDriver d) {
		driver =d;
		Productadd = new ProductAdd(driver);
		cal = new Calculateamount(driver);
		cart = new Cart(driver);
	}
	public void addProducts(String type) throws InterruptedException {
		if(type.equalsIgnoreCase("config")) {
			Productadd.selectVariant();
		}
		Productadd.selectQuantity();
		Productadd.AddProduct();
		Productadd.ClickMiniCart();
	}
	public void doCartCheck() throws InterruptedException, ParseException {
		cart.addFreesample();
		cart.enterCoupon("flat25");
		//cart.enterGV("gv0200","lvtv16000977");
		double AmountafPromo = cal.calculateAmountafterPromo();
		double AmountafGv = cal.calculateAmountafterGv(AmountafPromo);
		cal.taxCalculate(AmountafGv);
		cart.goCheckout();
	}
}
