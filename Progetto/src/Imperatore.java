import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Imperatore extends Person{
	
	private boolean checkImperatore;

	public Imperatore(String nome, String link, boolean imperatore) {
		super(nome, link);
		this.checkImperatore = true;
	}
	
	public boolean getCheckImperatore() {
		return this.checkImperatore;
	}

}
