package gui;

import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;
import effects.JTextFieldCharLimit;
import effects.ShowCaretOnFocus;

/**
 * Class used to construct the user interface.
 * 
 */
public class AESView {

	/**
	 * Frame to display the user interface.
	 */
	private JFrame mainFrame;
	
	/**
	 * Panel to contain the main components.
	 */
	private JPanel mainPanel;
	
	/**
	 * Frame to display the README.
	 */
	private JFrame readmeFrame;
	
	/**
	 * Panel to contain the README text component.
	 */
	private JPanel readmePanel;
	

	// Components
	/**
	 * Main menu of the interface.
	 */
	private JMenuBar menu;
	
	/**
	 * File option under menu.
	 */
	private JMenu fileMenu;
	
	/**
	 * Option to open a new file.
	 */
	private JMenuItem openItem;
	
	/**
	 * Help option under menu.
	 */
	private JMenu helpMenu;
	
	/**
	 * Option to open the README.
	 */
	private JMenuItem readmeItem;
	
	/**
	 * Label indicating to select a configuration.
	 */
	private JLabel config;
	
	/**
	 * List of configurations the program can be run in.
	 */
	private JList<String> configList;
	
	/**
	 * Group of radio buttons to select one specific operation.
	 */
	private ButtonGroup runList;
	
	/**
	 * Run a predefined example radio button.
	 */
	private JRadioButton example;
	
	/**
	 * Encrypt user input radio button.
	 */
	private JRadioButton encrypt;
	
	/**
	 * Decrypt user input radio button.
	 */
	private JRadioButton decrypt;
	
	/**
	 * Label to mark input text area.
	 */
	private JLabel input;
	
	/**
	 * Input text area.
	 */
	private JTextArea inputArea;
	
	/**
	 * Component to enable input area scroll bar.
	 */
	private JScrollPane scrollInput;
	
	/**
	 * Label to mark output text area.
	 */
	private JLabel output;
	
	/**
	 * Output text area.
	 */
	private JTextArea outputArea;
	
	/**
	 * Component to enable output area scroll bar.
	 */
	private JScrollPane scrollOutput;
	
	/**
	 * Label to mark key input text area.
	 */
	private JLabel key;
	
	/**
	 * Key input text area.
	 */
	private JTextField keyField;
	
	/**
	 * Label to mark IV input text area.
	 */
	private JLabel iv;
	
	/**
	 * IV input text area.
	 */
	private JTextField ivField;
	
	/**
	 * Button to submit inputs.
	 */
	private JButton submit;
	
	/**
	 * Text area to display README.
	 */
	private JTextArea readmeArea;
	
	/**
	 * Enable scroll bar on README text area.
	 */
	private JScrollPane scrollREADME;

	public AESView() {
		mainFrame = new JFrame();
		mainPanel = new JPanel();
		readmeFrame = new JFrame();
		readmePanel = new JPanel();

		// construct menu
		String mode0 = "AES 128-bit Standard";
		String mode1 = "AES 128-bit Lookup Table";
		String mode2 = "AES EBC Mode";
		String mode3 = "AES CBC Mode";
		String[] configListItems = new String[4];
		configListItems[0] = mode0;
		configListItems[1] = mode1;
		configListItems[2] = mode2;
		configListItems[3] = mode3;

		fileMenu = new JMenu("File");
		openItem = new JMenuItem("Open");
		helpMenu = new JMenu("Help");
		readmeItem = new JMenuItem("README");
		menu = new JMenuBar();
		config = new JLabel("Select a configuration:");
		configList = new JList<String>(configListItems);
		runList = new ButtonGroup();
		example = new JRadioButton("Example");
		encrypt = new JRadioButton("Encrypt");
		decrypt = new JRadioButton("Decrypt");

		// construct components
		input = new JLabel("Input:");
		inputArea = new JTextArea();
		scrollInput = new JScrollPane(inputArea);
		output = new JLabel("Output:");
		outputArea = new JTextArea();
		scrollOutput = new JScrollPane(outputArea);
		key = new JLabel("Key");
		keyField = new JTextField();
		iv = new JLabel("IV");
		ivField = new JTextField();
		submit = new JButton("Submit");
		readmeArea = new JTextArea();
		scrollREADME = new JScrollPane(readmeArea);

		// set components properties
		configList.setSelectedIndex(0);
		example.setSelected(true);
		keyField.setDocument(new JTextFieldCharLimit(32));
		ivField.setDocument(new JTextFieldCharLimit(32));
		inputArea.setLineWrap(true);
		outputArea.setText("For reference, view README under Help menu.");
		outputArea.setLineWrap(true);
		ivField.setToolTipText("Initialization Vector");
		outputArea.setEditable(false);
		outputArea.addFocusListener(new ShowCaretOnFocus(outputArea));
		outputArea.setLineWrap(true);
		readmeArea.setEditable(false);
		readmeArea.addFocusListener(new ShowCaretOnFocus(readmeArea));

		scrollInput.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollOutput.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollREADME.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

		// add component groups
		fileMenu.add(openItem);
		helpMenu.add(readmeItem);
		menu.add(fileMenu);
		menu.add(helpMenu);
		runList.add(example);
		runList.add(encrypt);
		runList.add(decrypt);

		// adjust size and set layout
		mainPanel.setPreferredSize(new Dimension(530, 520));
		mainPanel.setLayout(null);
		readmePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		readmePanel.setPreferredSize(new Dimension(940, 565));
		readmePanel.setLayout(new BorderLayout());

		// add components
		mainPanel.add(menu);
		mainPanel.add(config);
		mainPanel.add(configList);
		mainPanel.add(input);
		mainPanel.add(scrollInput);
		mainPanel.add(output);
		mainPanel.add(scrollOutput);
		mainPanel.add(example);
		mainPanel.add(encrypt);
		mainPanel.add(decrypt);
		mainPanel.add(key);
		mainPanel.add(keyField);
		mainPanel.add(iv);
		mainPanel.add(ivField);
		mainPanel.add(submit);
		readmePanel.add(scrollREADME);

		// set component bounds
		menu.setBounds(0, 0, 570, 20);
		config.setBounds(15, 25, 130, 35);
		configList.setBounds(15, 60, 150, 75);
		example.setBounds(180, 60, 80, 25);
		encrypt.setBounds(180, 85, 80, 25);
		decrypt.setBounds(180, 110, 75, 25);
		input.setBounds(15, 145, 100, 25);
		scrollInput.setBounds(15, 175, 510, 135);
		output.setBounds(15, 315, 100, 25);
		scrollOutput.setBounds(15, 345, 510, 135);
		key.setBounds(380, 35, 100, 25);
		keyField.setBounds(275, 60, 250, 25);
		iv.setBounds(380, 85, 100, 25);
		ivField.setBounds(275, 110, 250, 25);
		submit.setBounds(445, 490, 80, 30);

		// adjust frames
		mainFrame.setTitle("AES Demo");
		mainFrame.add(mainPanel);
		mainFrame.pack();
		mainFrame.setResizable(false);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		readmeFrame.setTitle("AES Demo README");
		readmeFrame.add(readmePanel, BorderLayout.CENTER);
		readmeFrame.pack();
		readmeFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

	}

	/**
	 * Set the interface visible.
	 * 
	 * @param b - boolean indicating whether to set interface visible or not.
	 */
	public void setVisible(boolean b) {
		mainFrame.setVisible(b);
	}

	/**
	 * Set the README Frame visible.
	 * 
	 * @param b - boolean indicating whether to set README visible or not.
	 */
	public void setREADMEVisible(boolean b) {
		readmeFrame.setVisible(b);
	}

	/**
	 * Add text to the README.
	 * 
	 * @param readme - text to be appended to the current README.
	 */
	public void appendTextREADME(String readme) {
		readmeArea.append(readme);
	}

	/**
	 * Show error dialog.
	 * 
	 * @param errorMessage - error to be displayed.
	 */
	public void displayErrorMessage(String errorMessage) {
		JOptionPane.showMessageDialog(mainFrame, errorMessage);
	}

	/**
	 * Add a new action listener to open file
	 * 
	 * @param listenForFileOpen  - action to open a file
	 */
	public void addFileOpenListener(ActionListener listenForFileOpen) {
		openItem.addActionListener(listenForFileOpen);
	}

	/**
	 * Add a new action listener to open README
	 * 
	 * @param listenForREADME - action to open README
	 */
	public void addREADMEListener(ActionListener listenForREADME) {
		readmeItem.addActionListener(listenForREADME);
	}

	/**
	 * Add a new action listener to submit inputs
	 * 
	 * @param listenForSubmit - action to submit inputs
	 */
	public void addSubmitListener(ActionListener listenForSubmit) {
		submit.addActionListener(listenForSubmit);
	}

	/**
	 * Indicates if an example operation is selected.
	 * 
	 * @return true if example is selected
	 */
	public boolean isExampleMode() {
		return example.isSelected();
	}

	/**
	 * Indicates if an encrypt operation is selected.
	 * 
	 * @return true if encrypt is selected
	 */
	public boolean isEncryptMode() {
		return encrypt.isSelected();
	}

	/**
	 * Indicates if a decrypt operation is selected.
	 * 
	 * @return true if decrypt is selected
	 */
	public boolean isDecryptMode() {
		return decrypt.isSelected();
	}

	/**
	 * AES Standard Configuration
	 * 
	 * @return true for AES Standard
	 */
	public boolean isStandardMode() {
		String curr = configList.getSelectedValue();
		return curr.equalsIgnoreCase("AES 128-bit Standard");
	}

	/**
	 * AES Lookup Table Configuration
	 * 
	 * @return true for AES Lookup Table
	 */
	public boolean isLookupTableMode() {
		String curr = configList.getSelectedValue();
		return curr.equalsIgnoreCase("AES 128-bit Lookup Table");
	}

	/**
	 * AES EBC Mode Configuration
	 * 
	 * @return true for AES EBC
	 */
	public boolean isEBCMode() {
		String curr = configList.getSelectedValue();
		return curr.equalsIgnoreCase("AES EBC Mode");
	}

	/**
	 * AES CBC Mode Configuration
	 * 
	 * @return true for AES CBC
	 */

	public boolean isCBCMode() {
		String curr = configList.getSelectedValue();
		return curr.equalsIgnoreCase("AES CBC Mode");
	}

	/**
	 * Retrieves input text.
	 * 
	 * @return input text
	 */
	public String getInputArea() {
		return inputArea.getText();
	}

	/**
	 * Sets input text.
	 * 
	 * @param input - text to be set
	 */
	public void setInputArea(String input) {
		inputArea.setText(input);
	}

	/**
	 * Sets output text.
	 * 
	 * @param output - text to be set
	 */
	public void setOutputArea(String output) {
		outputArea.setText(output);
	}

	/**
	 * Retrieves input key.
	 * 
	 * @return key to be used
	 */
	public String getKey() {
		return keyField.getText();
	}

	/**
	 * Sets the key to be used.
	 * 
	 * @param key - used to encrypt or decrypt
	 */
	public void setKey(String key) {
		keyField.setText(key);
	}

	/**
	 * Retrieves inputIV.
	 * 
	 * @return IV to be used
	 */
	public String getIV() {
		return ivField.getText();
	}

	/**
	 * Sets the IV to be used.
	 * 
	 * @param iv - used to encrypt or decrypt
	 */
	public void setIV(String iv) {
		ivField.setText(iv);
	}

}
