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
			control con = new control();
		
			//URL settings
			System.setProperty("webdriver.chrome.driver", "C:\\browserdrivers\\chromedriver.exe");
			Scanner sca = new Scanner(System.in);
			String nome = "https://it.wikipedia.org/wiki/imperatori_romani";

			//Opening website
			WebDriver driver = new ChromeDriver();
			driver.get(nome);
			
			//Inserting all the lines of the table in an Arraylist
			WebElement corpo = driver.findElement(By.className("mw-parser-output"));
			List<WebElement> tabelle = corpo.findElements(By.className("wikitable"));
        	for (WebElement ris : tabelle) {
        		List<WebElement> tab = ris.findElements(By.tagName("b"));
        		for (WebElement imp : tab) {
        			String word = imp.getText();
        			if (!word.equals("Oriente") && !word.equals("Occidente") &&  con.isDigit(word)){
        				array.add(imp.getText());
            			//System.out.println(imp.getText());
        			}
        		}
        	}
        	for (int x = 0; x < array.size(); x++) {
        		System.out.println(array.get(x));
        	}
        	
            //Quitting website
            driver.quit();
	}
}
