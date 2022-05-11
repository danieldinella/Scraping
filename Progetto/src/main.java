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
		String imp = "Augusto";
		String link = "https://it.wikipedia.org/wiki/Augusto";
		
		person per = new person(imp, link);
		per.setDinastia();
		per.setPadre();
		per.setMadre();
		per.setConiuge();
		per.setFigli();
		System.out.println("Nome: " + per.getNome());
		System.out.println("Dinastia: " + per.getDinastia());
		System.out.println("Madre: " + per.getMadre());
		System.out.println("Padre: " + per.getPadre());
		System.out.println("Coniugi: ");
		per.printConiuge(per.getConiuge());
		System.out.println("Figli: ");
		per.printFigli(per.getFigli());
	}
}
