package MailChecks;

import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hwpf.usermodel.DateAndTime;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import drivers.Drivers;

public class MailChecks {
	WebDriver d;
	WebElement priceSection,grandTotal,subTotal,gvDisc,cpnDisc,ComDisc,Tax,shipping;
	public MailChecks() {
		d = Drivers.initDrivers("Chrome");
	}
	public static void main(String[] args) {
		MailChecks m = new MailChecks();
		m.doMailChecks();
	}
	private void doMailChecks() {
		d.get("https://www.mailinator.com/v3/index.jsp?query=hari");
		openMail();
		checkPrice();
	}
	private void openMail() {
		d.findElement(By.id("inbox_field")).sendKeys("hari");
		d.findElement(By.className("input-group-btn")).click();
		try
		{
			Thread.sleep(1000);
			List<WebElement> l = d.findElement(By.cssSelector("#inboxpane  table")).findElements(By.className("ng-binding"));
			List <WebElement> mails = new ArrayList<WebElement>(); 
			for (WebElement td : l) {
				if(td.getText().contains("freyja")) {
					mails.add(td);
					System.out.println(td.getText());
				}
			}
			mails.get(0).click();
			Thread.sleep(1900);
			
			priceSection = d.findElement(By.tagName("table")).findElement(By.id("price"));//("price"));// table table > tbody > tr > td:nth-child(3)*/"));d.findElement(By.id("price"));
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	private void checkPrice() {
		
		try{
			priceSection.toString();
			System.out.println(priceSection.getAttribute("id")+"\nCool\n"+priceSection.getText());
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}	
}
	
