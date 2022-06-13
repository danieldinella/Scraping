
public class ScrapingTester3 {

	public static void main(String[] args) {
		String nome = "Adriano";
		String link = "https://it.wikipedia.org/wiki/Adriano";
		Imperatore imp = new Imperatore(nome, link);
    	imp.setDinastia();
    	imp.setPadre();
    	imp.setMadre();
    	imp.setConiuge();
    	imp.setFigli();
	}

}
