import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;
import java.util.Scanner;

public class main {

	public static void main(String[] args) {
		
			//URL settings
			System.setProperty("webdriver.chrome.driver", "C:\\browserdrivers\\chromedriver.exe");
			Scanner sca = new Scanner(System.in);
			String nome = "https://it.wikipedia.org/wiki/" + sca.next();

			//Opening website
			WebDriver driver = new ChromeDriver();
			driver.get(nome);
			
			//Inserting all the lines of the table in an Arraylist
			WebElement corpo = driver.findElement(By.className("mw-parser-output"));
			List<WebElement> tabelle = corpo.findElements(By.className("wikitable"));
        	for (WebElement ris : tabelle) {
        		List<WebElement> tab = ris.findElements(By.tagName("b"));
        		for (WebElement imp : tab) {
        			System.out.println(imp.getText());
        		}
        	}
        	
            //Quitting website
            //driver.quit
	}
}
