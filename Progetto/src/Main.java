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
    	System.out.println("Nome: " + imp.getNome());
		System.out.println("Dinastia: " + imp.getDinastia());
		System.out.println("Madre: " + imp.getMadre());
		System.out.println("Padre: " + imp.getPadre());
		System.out.println("Coniugi: ");
		imp.printConiuge(imp.getConiuge());
		System.out.println("Figli: ");
		imp.printFigli(imp.getFigli());
		System.out.println("\n");
		System.out.println("Mandato: " + imp.getMandato());
		System.out.println("\n");
	}

}
