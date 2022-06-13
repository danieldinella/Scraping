
public class ScrapingTester6 {

	public static void main(String[] args) {
		String nome = "Costantino";
		String link = "https://it.wikipedia.org/wiki/Costantino_I";
		Imperatore imp = new Imperatore(nome, link);
    	imp.setDinastia();
    	imp.setPadre();
    	imp.setMadre();
    	imp.setConiuge();
    	imp.setFigli();
	}

}
