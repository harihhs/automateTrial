package Login;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class registerxl {
	File src;
	FileInputStream fileInputStream;
	XSSFWorkbook workbook ;
	XSSFSheet sheet;
	XSSFRow row;
	XSSFCell cell;
public registerxl(XSSFWorkbook book) {
		workbook = book;
	}
public ArrayList<String> read_regexcel(String n) throws IOException {
		
		sheet=workbook.getSheetAt(2);
		DataFormatter formatter=new DataFormatter();
		String text="";
		ArrayList<String> rowarr=new ArrayList<String>();
		ArrayList<String> register=new ArrayList<String>();
		for(Row row:sheet) {
			
			Cell cell=row.getCell(0);
			
			text=formatter.formatCellValue(cell);
			rowarr.add(text);
		}
		String fname = "firstname"+ n;
		String lname = "lastname"+ n;
		String user = "username"+ n;
		String mail = "emailaddress"+ n;
		String pwd = "password"+ n;
		int indexforfname=-1,i=0;
		int indexforlname=-1;
		int indexforuser=-1;
		int indexformail=-1;
		int indexforpwd=-1;
		for(String element: rowarr) {
			if(element.equals(fname)) {
				indexforfname=i;
			}
			if(element.equals(lname)) {
				indexforlname=i;
			}
			if(element.equals(user)) {
				indexforuser=i;
			}
			if(element.equals(mail)) {
				indexformail=i;
			}
			if(element.equals(pwd)) {
				indexforpwd=i;
			}
			i++;
			
		}
		row=sheet.getRow(indexforfname);
		cell=row.getCell(1);
		String cellforfname = cell.toString();
		row=sheet.getRow(indexforlname);
		cell=row.getCell(1);
		String cellforlname = cell.toString();
		row=sheet.getRow(indexforuser);
		cell=row.getCell(1);
		String cellforuser = cell.toString();
		row=sheet.getRow(indexformail);
		cell=row.getCell(1);
		String cellformail = cell.toString();
		row=sheet.getRow(indexforpwd);
		cell=row.getCell(1);
		String cellforpwd = cell.toString();
		register.add(cellforfname);
		register.add(cellforlname);
		register.add(cellforuser);
		register.add(cellformail);
		register.add(cellforpwd);
		
		return register;

}
}
