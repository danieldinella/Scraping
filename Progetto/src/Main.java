import java.io.*;

public class Main {

	public static void main(String[] args) {
		String nome = "Aurelio";
		String link = "https://it.wikipedia.org/wiki/Marco_Aurelio";
		Imperatore imp = new Imperatore(nome, link, true);
    	imp.setDinastia();
    	imp.setPadre();
    	imp.setMadre();
    	imp.setConiuge();
    	imp.setFigli();
	}

}
