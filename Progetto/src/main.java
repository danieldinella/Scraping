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
			WebElement tabella = driver.findElement(By.className("sinottico"));
			List<WebElement> righe = tabella.findElements(By.tagName("td"));
        	for (WebElement ris : righe) {
            System.out.println(ris.getText());
        	}
        	
            //Quitting website
            //driver.quit();
	}
}
