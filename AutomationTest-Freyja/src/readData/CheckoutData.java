package readData;

import java.util.HashMap;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;

public class CheckoutData {
	public boolean isDiffBilling,isLoggedIn,useExisting;
	public HashMap<String, String> shipAddress,billAddress;
	public String promo,GVtxt,GVpin,user,key;
	public String payData[];
	XSSFSheet checkoutSheet;
	
	public CheckoutData(ReadExcel re) {
		checkoutSheet = re.book.getSheet("checkout");
		isDiffBilling = checkoutSheet.getRow(5).getCell(1).getBooleanCellValue();
		isLoggedIn = checkoutSheet.getRow(8).getCell(1).getBooleanCellValue();
		useExisting = checkoutSheet.getRow(10).getCell(1).getBooleanCellValue();
	}
	public void getUserData(){
		if(isLoggedIn) {
			user = key = null;
		}
		else {
			user = checkoutSheet.getRow(9).getCell(0).getStringCellValue().trim().toLowerCase();
			key = checkoutSheet.getRow(9).getCell(1).getStringCellValue().trim().toLowerCase();
			if(user.equals("") || key.equals(""))
				user = key = null;
		}
	}
	public void getCheckoutData() {
		promo =  checkoutSheet.getRow(2).getCell(1).getStringCellValue().trim().toLowerCase();
		GVtxt = checkoutSheet.getRow(3).getCell(1).getStringCellValue().trim().toLowerCase();
		GVpin = checkoutSheet.getRow(4).getCell(1).getStringCellValue().trim().toLowerCase();
		shipAddress = getAddress(checkoutSheet.getRow(6));
		if(isDiffBilling)
			billAddress = getAddress(checkoutSheet.getRow(7));
		payData = getPaymentData();
		getUserData();
	}
	public String[] getPaymentData() {
		XSSFRow r= checkoutSheet.getRow(1);
		String[] res = new String[10];
		res[0] = r.getCell(1).getStringCellValue().trim();
		if(res[0].equalsIgnoreCase("visa")) {
			res[1]= "4200000000000000";//r.getCell(2).getNumericCellValue()+"".trim();
			res[2]= "123";//(int)r.getCell(3).getNumericCellValue()+"".trim();
			res[3]= r.getCell(4).getNumericCellValue()+"".trim();
			res[4]= r.getCell(5).getNumericCellValue()+"".trim();
		}
		return res;
	}
	public HashMap<String, String> getAddress(XSSFRow r) {
		String addrType = r.getCell(1).getStringCellValue().trim();
		HashMap<String, String> map = new HashMap<>();
			map.put("firstname", r.getCell(2).getStringCellValue().trim());
			map.put("lastname",r.getCell(3).getStringCellValue().trim());
			if(addrType.equals("ship"))
				map.put("email",r.getCell(4).getStringCellValue().trim());
			map.put("telephone",r.getCell(5).getStringCellValue().trim());
			map.put("street[0]", r.getCell(6).getStringCellValue().trim());
			map.put("street[1]", r.getCell(7).getStringCellValue().trim());
			map.put("region_id",r.getCell(8).getStringCellValue().trim());
			map.put("cityNew",r.getCell(9).getStringCellValue().trim());
			map.put("barangayNew",r.getCell(10).getStringCellValue().trim());
			map.put("postcode", ((int)r.getCell(11).getNumericCellValue()+"").trim());
		return map;
	}
	
}
