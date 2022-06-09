
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StringProcessor {
	/* StringProcessor Attributes
	 * codifica is a string where each line has this format:
	 * 
	 *   Emperor	 Name		 Spouses	 N° of Children
	 * 	  Y / N    Mandatory	 Optional		Optional
	 *      |		   |			|				|
	 * 	    |          |			|				|
	 *      |          |			|				|
	 *      V          V			V				V
	 *   "*"/""   "Full name"  " + [List]"		" N_F:n"
	 *   
	 * Example:
	 *	"*Vespasiano + [Flavia Domitilla, Caenis] N_F:3				/n
	 *  	 *Tito + [Arrecina Tertulla, Marcia Furnilla] N_F:2 	/n
	 * 		 	 Giulia Flavia + [Tito Flavio Sabino]				/n
	 * 			 Flavia												/n
	 * 		 *Domiiziano + [Domizia Longina] N_F:2					/n
	 * 			 Flavio Clemente									/n
	 * 			 Flavia Domitilla									/n
	 * 		 Flavia Domitilla minore"										
	 */
	private static String codifica;
	
	/* separate method
	 * Splits a string in a list of strings using a given parameter as a separator
	 * 
	 * @param toSeparate		string to separate
	 * @param el				separation parameters
	 * @return 					the list of lines contained in a string
	 */
	public static ArrayList<String> separate(String toSeparate, String el) {
		String[] elements = toSeparate.split(el); 
		List<String> fixedLenghtList = Arrays.asList(elements);  
		ArrayList<String> listOfString = new ArrayList<String>(fixedLenghtList); 
		return listOfString;
	}
	
	/* countMatches method
	 * Counts how many times a substring appears in a string
	 * 
	 * @param str				string to analyze	
	 * @param target			substring to search
	 * @return					how many times target appears in str
	 */
	public static int countMatches(String str, String target) {
        return str.split(target, -1).length - 1;
    }
	
	/* processString method
	 * Starting from an initial number of tabs of 0 and the Person object called root, 
	 * the method performs a pre-order visit using recursive calls using his children and
	 * the number of tabs plus 1.
	 * Each call assembles a line of the string codifica following the his syntax using
	 * controls for each Person object's attribute
	 * 
	 * @param root		Person object root
	 * @param tabs 		Number of tabs for each line start, initial value = 0
	 */
	public static void processString(Person root, String tabs) {
		System.out.println("1" + root.getNome());
		codifica += "\n" + tabs;	
		if(root.getFigli().isEmpty())
			if(root.getConiuge().isEmpty()) 
				codifica += (root.getNome());
			else
				codifica +=(root.getNome() + " + " + root.getConiuge());
		else {
			if(root.getConiuge().isEmpty())
				codifica += (root.getNome());
			else
				codifica += (root.getNome() + " + " + root.getConiuge());
			ArrayList figli = root.getFigli();
			if(!root.getFigli().isEmpty())
				codifica += (" N_F :"+figli.size());
			tabs = tabs + "\t";
			System.out.println("1,2");
			System.out.println(figli);
			for(int i = 0; i < figli.size(); i++) {
				Person fi = (Person) figli.get(i);
				if(fi.checkImp()) {
					System.out.println("1,3");
					Imperatore imp = (Imperatore) figli.get(i);
					processString(imp, tabs);
				}
				else {
					System.out.println("1,4");
					Person per = (Person) figli.get(i);
					processString(per, tabs);
				}
			}
		}
	}
	
	public static void processString(Imperatore root, String tabs) {
		System.out.println("1" + root.getNome());
		codifica += "\n" + tabs;	
		codifica += "*";
		if(root.getFigli().isEmpty())
			if(root.getConiuge().isEmpty()) 
				codifica += (root.getNome() + " (" + root.getMandato() + ")");
			else
				codifica +=(root.getNome() + " (" + root.getMandato() + ")" + " + " + root.getConiuge());
		else {
			if(root.getConiuge().isEmpty())
				codifica += (root.getNome() + " (" + root.getMandato() + ")");
			else
				codifica += (root.getNome() + " (" + root.getMandato() + ")" + " + " + root.getConiuge());
			ArrayList figli = root.getFigli();
			if(!root.getFigli().isEmpty())
				codifica += (" N_F :"+figli.size());
			tabs = tabs + "\t";
			System.out.println("1,2");
			System.out.println(figli);
			for(int i = 0; i < figli.size(); i++) {
				Person fi = (Person) figli.get(i);
				if(fi.checkImp()) {
					System.out.println("1,3");
					Imperatore imp = (Imperatore) figli.get(i);
					processString(imp, tabs);
				}
				else {
					System.out.println("1,4");
					Person per = (Person) figli.get(i);
					processString(per, tabs);
				}
			}
		}
	}
	
	public static String getCodifica() {
		System.out.println(codifica);
		codifica = codifica.replace("null\n", "");
		System.out.println(codifica);
		return codifica;
	}
}
