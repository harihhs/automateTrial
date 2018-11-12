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

public class forgetxl {

	File src;
	FileInputStream fileInputStream;
	XSSFWorkbook workbook ;
	XSSFSheet sheet;
	XSSFRow row;
	XSSFCell cell;
	
public String read_excel(XSSFWorkbook workbook, String n) throws IOException {
		sheet=workbook.getSheetAt(1);
		DataFormatter formatter=new DataFormatter();
		String text="";
		//ArrayList<String> arrayList=new ArrayList<String>();
		ArrayList<String> rowarr=new ArrayList<String>();
		//ArrayList<String> users=new ArrayList<String>();
		
		for(Row row:sheet) {

			Cell cell=row.getCell(0);
			
			text=formatter.formatCellValue(cell);
			rowarr.add(text);
		}
		String user = "username"+ n;
		int indexforuser=-1,i=0;
		
for(String element: rowarr) {
			
			//System.out.println(element);
			if(element.equals(user)) {
				indexforuser=i;
			}
			i++;
}
System.out.println(indexforuser);

row=sheet.getRow(indexforuser);
cell=row.getCell(1);
String cellforuser = cell.toString();
System.out.println(cellforuser);
return cellforuser;
}


}
