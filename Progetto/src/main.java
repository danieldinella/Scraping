import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

public class main {

	public static void main(String[] args) {
		
			ArrayList array = new ArrayList();
		
			//URL settings
			System.setProperty("webdriver.chrome.driver", "C:\\browserdrivers\\chromedriver.exe");
			Scanner sca = new Scanner(System.in);
			String nome = "https://it.wikipedia.org/wiki/imperatori_romani";

			//Opening wikipedia
			WebDriver driver = new ChromeDriver();
			driver.get(nome);
			
			//Scraping the emperors by xpath
			List<WebElement> imp = driver.findElements(By.xpath("//body/div[@id='content']/div[@id='bodyContent']/div[@id='mw-content-text']"
					+ "/div[@class='mw-parser-output']/table[@class='wikitable']/tbody/tr[not(@bgcolor='FFFFFF')]/td[2]/b/a"));
			
            //Quitting browser page
            driver.quit();
	}
}
