package readData;
import java.io.IOException;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
/**
 * @author hari
 *
 */
public class ReadExcel {
	public XSSFWorkbook book;
	public ReadExcel(String path) throws IOException {
		book = new XSSFWorkbook(path);
	}
}

