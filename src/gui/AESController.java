package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

import utilities.IOHandler;

/**
 * Class to control the interactions between the user interface and the AES
 * operations to be performed.
 * 
 */
public class AESController {

	/**
	 * Contains the visual elements of the user interface.
	 */
	private AESView view;
	/**
	 * Contains multiple methods to encrypt and decrypt using AES.
	 */
	private AESModel model;

	/**
	 * Constructor for a new AESController object.
	 * 
	 * @param model - AES operations available
	 * @param view - user interface for AES Demo
	 */
	public AESController(AESModel model, AESView view) {
		this.view = view;
		this.model = model;
		prepareREADME();
		addFileOpenListener();
		addREADMEListener();
		addSubmitListener();
	}

	/**
	 * Makes the user interface visible.
	 */
	public void begin() {
		view.setVisible(true);
	}

	/**
	 * Listener to detect when a file is to be opened.
	 */
	private void addFileOpenListener() {
		view.addFileOpenListener(new FileOpenListener());
	}

	/**
	 * Listener to detect when the README is to be shown.
	 */
	private void addREADMEListener() {
		view.addREADMEListener(new READMEListener());
	}

	/**
	 * Listener to detect when to submit inputs.
	 */
	private void addSubmitListener() {
		view.addSubmitListener(new SubmitListener());
	}

	/**
	 * Retrieves the README file on the demo package and populates the README
	 * window.
	 */
	private void prepareREADME() {
		try {
			InputStream is = getClass().getResourceAsStream("/demo/README.txt");
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			String readme = "";
			while ((readme = br.readLine()) != null)
				view.appendTextREADME(readme += "\n");
			br.close();
			isr.close();
			is.close();
		} catch (IOException e) {
			view.displayErrorMessage("ERROR: README creation failed.");
		}
	}

	/**
	 * Class to open a new file when the action is requested.
	 * 
	 */
	private class FileOpenListener implements ActionListener {

		/**
		 * Opens a file to use as input.
		 * 
		 * @param e = open file event triggering this action
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				String path = IOHandler.loadFile();
				if (path != null) {
					File f = new File(path);
					Scanner scanner = new Scanner(f);
					String key = scanner.nextLine();
					String input = "";
					if (view.isCBCMode()) {
						String iv = scanner.nextLine();
						view.setIV(iv);
					}
					while (scanner.hasNextLine())
						input += scanner.nextLine() + "\n";
					scanner.close();
					view.setKey(key);
					view.setInputArea(input);
				}
			} catch (FileNotFoundException fnf) {
				view.displayErrorMessage("Error: File not found!");
			}
		}
	}

	/**
	 * Class to open a README file when the action is requested.
	 * 
	 */
	private class READMEListener implements ActionListener {

		/**
		 * Opens the README under the Help menu.
		 * 
		 * @param e = open README event triggering this action
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			view.setREADMEVisible(true);
		}
	}

	/**
	 * Class to open a new file when the action is requested.
	 * 
	 */
	private class SubmitListener implements ActionListener {

		/**
		 * Interprets input and execute selected configuration.
		 * 
		 * @param e = submit event triggering this action
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		public void actionPerformed(ActionEvent e) {
			String input = view.getInputArea().toUpperCase().trim();
			String key = view.getKey().toUpperCase().trim();
			String iv = view.getIV().toUpperCase().trim();
			boolean inSimpleValid = valid128bit(input);
			boolean inBlockValid = validNbit(input);
			boolean keyValid = valid128bit(key);
			boolean ivValid = valid128bit(iv);

			// Check the current mode, then check the desired operation within
			// that mode.
			// Modes: Standard, Lookup Table, EBC, and CBC
			// Operations: Example, Encrypt, Decrypt, or an ERROR message is
			// shown.

			if (view.isStandardMode()) {
				if (view.isExampleMode())
					view.setOutputArea(model.standardExample());
				else if (view.isEncryptMode() && inSimpleValid && keyValid)
					view.setOutputArea(model.standardEncrypt(input, key));
				else if (inSimpleValid && keyValid)
					view.setOutputArea(model.standardDecrypt(input, key));
				else
					view.displayErrorMessage("Input and Key must be 32 hex digits");
			} else if (view.isLookupTableMode()) {
				if (view.isExampleMode())
					view.setOutputArea(model.tableExample());
				else if (view.isEncryptMode() && inSimpleValid && keyValid)
					view.setOutputArea(model.tableEncrypt(input, key));
				else if (inSimpleValid && keyValid)
					view.setOutputArea(model.tableDecrypt(input, key));
				else
					view.displayErrorMessage("Input and Key must be 32 hex digits");
			} else if (view.isEBCMode()) {
				if (view.isExampleMode())
					view.setOutputArea(model.ebcExample());
				else if (view.isEncryptMode() && inBlockValid && keyValid)
					view.setOutputArea(model.ebcEncrypt(input, key));
				else if (inBlockValid && keyValid)
					view.setOutputArea(model.ebcDecrypt(input, key));
				else
					view.displayErrorMessage("Input must be at least 1 hex digit, Key must be 32 hex digits");
			} else {// view.isCBCMode()
				if (view.isExampleMode())
					view.setOutputArea(model.cbcExample());
				else if (view.isEncryptMode() && inBlockValid && keyValid
						&& ivValid)
					view.setOutputArea(model.cbcEncrypt(input, key, iv));
				else if (inBlockValid && keyValid && ivValid)
					view.setOutputArea(model.cbcDecrypt(input, key, iv));
				else
					view.displayErrorMessage("Input must be at least 1 hex digit, Key and IV must be 32 hex digits");
			}
		}
	}

	/**
	 * Validates an n-bit word in hexadecimal format.
	 * 
	 * @param input - n-bit word in hex
	 * @return true if valid, false otherwise
	 */
	private boolean validNbit(String input) {
		return input.matches("[0-9A-F]+");
	}

	/**
	 * Validates a 128-bit word in hexadecimal format.
	 * 
	 * @param input - 128-bit word in hex
	 * @return true if valid, false otherwise
	 */
	private boolean valid128bit(String input) {
		return input.matches("[0-9A-F]{32}");
	}
}
