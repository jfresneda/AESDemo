/**
 * 
 */
package utilities;

import java.awt.FileDialog;
import java.awt.Frame;

/**
 * Helper class to handle input and output of the programs.
 *
 */
public class IOHandler {

	/**
	 * Helper method to display the results.
	 * 
	 * @param A - 2D nxn Array
	 */
	public static void print2DArray(String[][] A) {
		for (int i = 0; i < A.length; i++) {
			String s = "";
			for (int j = 0; j < A.length; j++)
				s += A[i][j];
			System.out.println(s);
		}
	}

	/**
	 * Helper method to open a file browser. If no valid *text file is selected, program will run default example.
	 * @return opened file
	 */
	public static String loadFile() {
		Frame f = new Frame();
		String title = "AES - Open a *.txt file or cancel to run default example";
		int mode = FileDialog.LOAD;
		String fileType = "*.txt";

		FileDialog fd = new FileDialog(f, title, mode);      
		fd.setFile(fileType);
		fd.setVisible(true);
		String file = fd.getFile();
        String dir = fd.getDirectory();
        String ret = null;
        
        if(!file.equalsIgnoreCase("null") && !dir.equalsIgnoreCase("null"))
        	ret = dir+file;
        	
		return ret;
	}
}
