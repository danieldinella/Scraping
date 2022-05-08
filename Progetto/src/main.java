import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebDriver;
import java.util.Scanner;

public class main {

	public static void main(String[] args) {
		
		System.setProperty("webdriver.chrome.driver", "C:\\browserdrivers\\chromedriver.exe");
		Scanner sca = new Scanner(System.in);
		String nome = "https://it.wikipedia.org/wiki/" + sca.next();
		
		WebDriver driver = new ChromeDriver();
		driver.get(nome);
		
		
	}

}
