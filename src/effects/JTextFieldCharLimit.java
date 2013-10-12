package effects;

import java.awt.Toolkit;

import javax.swing.text.*;

/**
 * Class to introduce a character limit to a JTextField.
 * 
 */
public class JTextFieldCharLimit extends PlainDocument {

	/**
	 * Generated serial version ID
	 */
	private static final long serialVersionUID = 7087188549633093228L;

	/**
	 * Document character limit
	 */
	private int limit;

	/**
	 * Initialize our Document with a limit.
	 * 
	 * @param limit
	 */
	public JTextFieldCharLimit(int limit) {
		super();
		this.limit = limit;
	}

	/**
	 * Inserts a string into a text area. If the string is too large, it inserts
	 * as many characters as possible. If the text area is full, it will beep in
	 * response.
	 * 
	 * @see javax.swing.text.PlainDocument#insertString(int, java.lang.String,
	 *      javax.swing.text.AttributeSet)
	 */
	public void insertString(int offset, String str, AttributeSet attr)
			throws BadLocationException {
		int temp = limit - getLength();
		if (temp >= str.length())
			super.insertString(offset, str, attr);
		else if (temp > 0)
			super.insertString(offset, str.substring(0, temp), attr);
		else
			Toolkit.getDefaultToolkit().beep();
	}
}
