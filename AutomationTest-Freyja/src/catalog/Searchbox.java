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


public class Searchbox {

	
	@FindBy(how = How.XPATH, using = "//*[@id=\"search\"]")
	static WebElement search;
	
	@FindBy(how = How.XPATH, using = "//*[@id=\"search_mini_form\"]/div[2]/button")
	static WebElement click_search;
	
	private WebDriver driver;
	public String prodType;
	public Searchbox(WebDriver d) {
		// TODO Auto-generated constructor stub
		this.driver=d;
    	PageFactory.initElements(driver,this);
	}
	
	public String read_keyword(XSSFWorkbook workbook, int j) throws InterruptedException, IOException
	{
		XSSFSheet sheet2 = workbook.getSheet("search");
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

	String search_box(XSSFWorkbook workbook) throws Exception
	{
		String k=read_keyword(workbook,1);
		System.out.println(k);
		search.sendKeys(k);
		Thread.sleep(2000);
		click_search.click();
		driver.findElement(By.linkText(k)).click();
		setProductType(workbook, 1);
		return this.prodType;
	}
	private void setProductType(XSSFWorkbook workbook, int a) {
		XSSFRow x= workbook.getSheet("sub").getRow(a);
		prodType = x.getCell(1).getStringCellValue().trim();
	}
}
