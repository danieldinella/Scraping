
public class control {

	public control() {
		
	}
	
	public boolean isDigit(String word) {
		for (int x = 0; x < word.length(); x++) {
			if (Character.isDigit(word.charAt(x))) {
				return false;
			}
		}
		return true;
	}

}
