import org.openqa.selenium.chrome.ChromeDriver; 
import org.openqa.selenium.By;
import org.openqa.selenium.By.ByXPath;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;
import java.util.Scanner;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Stream;

public class Main {
	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", "C:\\browserdrivers\\chromedriver.exe");
		
		//input
		Scanner sca = new Scanner(System.in);
		String imp = "Gaio Giulio Cesare";
		String link = "https://it.wikipedia.org/wiki/" + imp;
		
		person per = new person(imp, link);
		per.setDinastia();
		per.setImperatore();
		per.setPadre();
		per.setMadre();
		per.setConiuge();
		per.setFigli(); 
		
		// ############################################################### //
		TreeImage.processString(per, "");
		TreeImage img = new TreeImage();
		// ############################################################### //
}
