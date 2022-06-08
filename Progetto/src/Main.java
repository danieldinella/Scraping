import java.io.*;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		File dir = new File("history");
	      File[] files = dir.listFiles();
	      FileFilter fileFilter = new FileFilter() {
	         public boolean accept(File file) {
	            return file.isDirectory();
	         }
	      };
	      files = dir.listFiles(fileFilter);
	      System.out.println(files.length);
	      
	      if (files.length == 0) {
	         System.out.println("Either dir does not exist or is not a directory");
	      } else {
	         for (int i = 0; i< files.length; i++) {
	            File filename = files[i];
	            System.out.println(filename.toString());
	         }
	      }

	}

}
