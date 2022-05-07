package selenium;

import java.util.Scanner;
import org.openqa.selenium.chrome.ChromeDriver;

public class main {

	public static void main(String[] args) {
		
		Scanner sca = new Scanner(System.in);
		String nome = sca.next();
		
		scraper scra = new scraper();		
		scra.getPage(nome);
		
	}

}
