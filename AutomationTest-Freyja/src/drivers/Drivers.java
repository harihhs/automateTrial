/**
 * @author hari
 *
 */
package drivers;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

public class Drivers {
	
	public static WebDriver initDrivers(String s) {
		switch (s.toLowerCase()) {
			case "chrome":
				System.setProperty("webdriver.chrome.driver", "/Volumes/Hari/Codem/Testing/chromedriver");
				return new ChromeDriver();
			case "firefox":
				System.setProperty("webdriver.gecko.driver", "/Volumes/Hari/Codem/Testing/geckodriver");
				return new FirefoxDriver();
			case "safari":
				System.setProperty("webdriver.safari.driver", "/Volumes/Hari/Codem/Testing/safaridriver");
				return new SafariDriver();
		}
		return null;
	}
}
