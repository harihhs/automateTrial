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

public class Brands {
	
	@FindBy(how = How.LINK_TEXT, using = "BRANDS")
	static WebElement click_brand;

	private WebDriver driver;

	public String prodType;

	public Brands(WebDriver d) {
		this.driver=d;
    	PageFactory.initElements(driver,this);
	}
	
	public String read_brand(XSSFWorkbook workbook, int j) throws InterruptedException, IOException
	{
		XSSFSheet sheet2 = workbook.getSheet("brands");
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
	
	public String read_product(XSSFWorkbook workbook, int j) throws InterruptedException, IOException
	{
		XSSFSheet sheet2 = workbook.getSheet("brands");
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
	
	String brand_method(XSSFWorkbook workbook) throws Exception
	{
		int a =4;
		click_brand.click();
		String b=read_brand(workbook,a);
		System.out.println(b);
		driver.findElement(By.linkText(b)).click();
		String p=read_product(workbook,a);
		System.out.println(p);
		driver.findElement(By.linkText(p)).click();
		setProductType(workbook, a);
		return this.prodType;
	}
	private void setProductType(XSSFWorkbook workbook, int a) {
		XSSFRow x= workbook.getSheet("sub").getRow(a);
		prodType = x.getCell(2).getStringCellValue().trim();
	}

}
