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



public class logxl {
	
	File src;
	FileInputStream fileInputStream;
	XSSFWorkbook workbook ;
	XSSFSheet sheet;
	XSSFRow row;
	XSSFCell cell;
	
	public logxl(XSSFWorkbook b) {
		workbook = b;
	}
	
	public ArrayList<String> readExcel(String n) throws IOException {
	
		sheet=workbook.getSheet("login");
		DataFormatter formatter=new DataFormatter();
		String text = null;
		ArrayList<String> rowarr=new ArrayList<String>();
		ArrayList<String> userandpass=new ArrayList<String>();
		for(Row row:sheet) {
			Cell cell=row.getCell(0);
			text=formatter.formatCellValue(cell);
			rowarr.add(text);
		}
		String user = "username"+ n;
		String pass = "password"+ n;
		int indexforuser=-1,i=0;
		int indexforpass=-1;
		
		for(String element: rowarr) {
			
			
			if(element.equals(user)) {
				indexforuser=i;
			}
			if(element.equals(pass)) {
				indexforpass=i;
			}
			i++;
			
		}
		row=sheet.getRow(indexforuser);
		cell=row.getCell(1);
		String cellforuser = cell.toString();
		row=sheet.getRow(indexforpass);
		cell=row.getCell(1);
		String cellforpass= cell.toString();
		userandpass.add(cellforuser);
		userandpass.add(cellforpass);
		return userandpass;
	}
	
	
	
	
	
	
	
	
	
	
}