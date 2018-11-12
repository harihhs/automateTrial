package PDPandCart;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.ParseException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Calculateamount {

	WebDriver driver;
	WebElement amountData;
	public double ActualTax,amount,vat,AmountafPromo,AmountafGv,promoDiscount,gvDiscount,grandTotal;
	public String promovalue;
	@FindBy(css="#cart-totals > div > table > tbody > tr.totals.sub > td > span")
	WebElement subtotal;
	@FindBy(css="#cart-totals > div > table > tbody > tr.totals-tax-details.shown > td")
	WebElement vattax;
	@FindBy(css="#cart-totals > div > table > tbody > tr.totals.promo-code > td > span > span")
	WebElement promo;
	@FindBy(css="#cart-totals > div > table > tbody > tr.totals.etpgv-discount.excl > td > span")
	WebElement gv;
	@FindBy(id="coupon_code")
	WebElement coupon;
	@FindBy(css="#cart-totals > div > table > tbody > tr.totals.promo-code > td > span > span")
	WebElement promodis;
	@FindBy(css="#cart-totals > div > table > tbody > tr.grand.totals > td > strong > span")
	WebElement grandtot;
	
    public Calculateamount(WebDriver driver) {
		this.driver=driver;
		//PageFactory.initElements(driver, this);
	}
	public double calculateAmountafterPromo() throws InterruptedException {
		Thread.sleep(3000);
		String sub= subtotal.getText();
		String substrNew = sub.replaceAll("[₱,]", "");
		amount= Double.parseDouble(substrNew);
		System.out.println("CART subtotal: "+amount);
        try {

			if(promodis.isDisplayed()) {
				
				promovalue=coupon.getAttribute("value");
				System.out.println("Applied Promo Code: " +promovalue);
				
				String promoUpper=promovalue.toUpperCase();
				if(promoUpper.equals("FLAT25")) {
					//System.out.println("flat");
					double discount = amount*25/100;
					System.out.println("Promo Discount Amount(Manual Calculation):" +discount);
					AmountafPromo=amount-(discount);
                }
				if(promoUpper.equals("TEST10")) {
					//System.out.println("test");
					double discount=10;
					System.out.println("Promo Discount Amount(Manual Calculation):" +discount);
					AmountafPromo=amount-(discount);
					
				}
				String promodiscount= promodis.getText();
				String promostrNew = promodiscount.replaceAll("[₱,-]", "");
				promoDiscount= Double.parseDouble(promostrNew);
				System.out.println("Promo Discount Amount in Cart:" +promoDiscount);
			}
		}
		catch(Exception  NoSuchElementException) {
			AmountafPromo=amount;
		}
		return AmountafPromo;

	}
	public double calculateAmountafterGv(double AmountafPromo) {

		try {
			if(gv.isDisplayed()) {
				String gvdiscount= gv.getText();
				String gvstrNew = gvdiscount.replaceAll("[₱,-]", "");
				gvDiscount= Double.parseDouble(gvstrNew);
				System.out.println("Gv discount Amount:" +gvDiscount);
				//System.out.println("present-gv");
				AmountafGv=AmountafPromo-(gvDiscount);
				
			} 
		}
		catch(Exception NoSuchElementException) {
			AmountafGv=AmountafPromo;
		}
		//System.out.println(AmountafGv);
		return AmountafGv;
	}
	public void taxCalculate(double AmountafGv) throws ParseException {
		String grand= grandtot.getText();
		String grandstrNew = grand.replaceAll("[₱,]", "");
		grandTotal = Double.parseDouble(grandstrNew);
		System.out.println("Estimated Grand Total in Cart Page:" +grandTotal);
		System.out.println("Estimated Grand Total(Manual Calculation):" +AmountafGv);
		String tax= vattax.getText();
		String taxstrNew = tax.replaceAll("[₱,]", "");
		vat= Double.parseDouble(taxstrNew);
		System.out.println("Tax in Cart Page:" +vat);
		ActualTax= AmountafGv * 12 /112;
	    DecimalFormat df = new DecimalFormat("#.##");
		df.setRoundingMode(RoundingMode.HALF_DOWN);
		String ActTaxinstring=df.format(ActualTax);
		double ActTax= Double.parseDouble(ActTaxinstring);
		System.out.println("Actual Tax(Manual Calculation) is: "+ ActTax);
		if(ActTax==vat) {
			System.out.println("Tax calculation is correct");
		}


	}



}