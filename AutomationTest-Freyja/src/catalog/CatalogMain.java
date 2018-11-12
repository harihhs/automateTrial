package catalog;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
public class CatalogMain{
	
	
	WebDriver driver;
	Testcase_01 t;
	Subcategory s;
	Searchbox s1;
	Brands b; 

	public CatalogMain(WebDriver d) {
		driver = d;
		//PageFactory.initElements(driver, CatalogMain.class);
		t= new Testcase_01(driver);
		s=new Subcategory(driver);
		s1=new Searchbox(driver);
		b=new Brands(driver);
	}	
	public String doCatalogTest(XSSFWorkbook book) throws Exception {
		return t.category(book);
		//return s.sub_category(book);
		//return s1.search_box(book);
		//return b.brand_method(book);
	}
}
