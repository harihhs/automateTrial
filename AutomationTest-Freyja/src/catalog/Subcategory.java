package catalog;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class Subcategory {
	public String prodType;
	private WebDriver driver;

	@FindBy(how = How.LINK_TEXT, using = "SHOP")
	public static WebElement click_shop;
	
	public Subcategory(WebDriver d) {
		// TODO Auto-generated constructor stub
		this.driver=d;
    	PageFactory.initElements(driver,this);
	}

	public String read_category(XSSFWorkbook workbook, int j) throws InterruptedException, IOException
	{
		XSSFSheet sheet2 = workbook.getSheet("sub");
		DataFormatter formatter=new DataFormatter();
		String text="";
		ArrayList<String> product=new ArrayList<String>();
		for(Row row:sheet2)
		{
			Cell cell=row.getCell(0);
				text=formatter.formatCellValue(cell);
				product.add(text);
			}
		System.out.println(j);
		String m=product.get(j);
		System.out.println(m);
		return m;
	}
	
	public String read_subcategory(XSSFWorkbook workbook, int j) throws InterruptedException, IOException
	{
		XSSFSheet sheet2 = workbook.getSheet("sub");
		DataFormatter formatter=new DataFormatter();
		String text="";
		ArrayList<String> product=new ArrayList<String>();
		for(Row row:sheet2)
		{
			Cell cell=row.getCell(1);
				text=formatter.formatCellValue(cell);
				product.add(text);
		}
		System.out.println(j);
		String m=product.get(j);
		System.out.println(m);
		return m;
	}
	
	public String read_product(XSSFWorkbook workbook, int j) throws InterruptedException, IOException
	{
		XSSFSheet sheet2 = workbook.getSheet("sub");
		DataFormatter formatter=new DataFormatter();
		String text="";
		ArrayList<String> product=new ArrayList<String>();
		for(Row row:sheet2)
		{
			Cell cell=row.getCell(2);
				text=formatter.formatCellValue(cell);
				product.add(text);
		}
		System.out.println(j);
		String m=product.get(j);
		System.out.println(m);
		return m;
	}
	String sub_category(XSSFWorkbook workbook) throws Exception
	{
		int a = 8;
		Thread.sleep(2000);
		click_shop.click();
		String c=read_category(workbook,a);
		//System.out.println(c);
		driver.findElement(By.linkText(c)).click();
		String s=read_subcategory(workbook,a);
		//System.out.println(s);
		Thread.sleep(2000);
		driver.findElement(By.linkText(s)).click();
		String p=read_product(workbook,a);
		//System.out.println(p);
		Thread.sleep(6000);
		driver.findElement(By.linkText(p)).click();
		setProductType(workbook,a);
		return this.prodType;
	}
	private void setProductType(XSSFWorkbook workbook, int a) {
		XSSFRow x= workbook.getSheet("sub").getRow(a);		
		prodType = x.getCell(3).getStringCellValue().trim();
		System.out.print(prodType);
	}

}
