package demo;

import gui.*;

/**
 * Class to run the gui for the AES Demonstration.
 * 
 */
public class AESDemo {

	/**
	 * Shows the gui for the AES Demo.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		AESModel model = new AESModel();
		AESView view = new AESView();
		AESController controller = new AESController(model, view);
		controller.begin();
	}
}
