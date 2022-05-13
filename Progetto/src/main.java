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
		
		System.setProperty("webdriver.chrome.driver", "C:\\browserdrivers\\chromedriver.exe");
		
		//input
		Scanner sca = new Scanner(System.in);
		String imp = "Agrippina maggiore";
		String link = "https://it.wikipedia.org/wiki/Agrippina_maggiore";
		
		person per = new person(imp, link);
		per.setDinastia();
		per.setImperatore();
		per.setPadre();
		per.setMadre();
		per.setConiuge();
		per.setFigli();
	}
}
