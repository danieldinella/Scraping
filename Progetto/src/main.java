import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.By.ByXPath;
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
			String nome = "https://it.wikipedia.org/wiki/Gaio_Giulio_Cesare";

			//Opening wikipedia
			WebDriver driver = new ChromeDriver();
			driver.get(nome);
			
			List<WebElement> imp = driver.findElements(By.xpath("//div[@id = 'mw-content-text']/div[@class = 'mw-parser-output']/table[@class = 'sinottico']/tbody/tr/th"));
			int indice = -1;
			int indice2 = 1;
			for (WebElement tab : imp) {
				if (tab.getText().equals("Figli")) {
					indice = indice2 + 1;
				}
				indice2++;
			}
			List<WebElement> imp2 = driver.findElements(By.xpath("//div[@id = 'mw-content-text']/div[@class = 'mw-parser-output']/table[@class = 'sinottico']/tbody/tr["+indice+"]/td"));
			for (WebElement tab : imp2) {
				System.out.println(tab.getText());
			}
			
            //Quitting browser page
            driver.quit();
	}
}
