package effects;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.text.JTextComponent;

/**
 * Class to show the caret on an output text field after being made not
 * editable.
 * 
 */
public class ShowCaretOnFocus implements FocusListener {

	/**
	 * text component to be edited
	 */
	private JTextComponent jtc;

	/**
	 * Constructor for focus listener
	 * 
	 * @param jtc
	 */
	public ShowCaretOnFocus(JTextComponent jtc) {
		this.jtc = jtc;
	}

	/**
	 * Shows caret when text area is focused.
	 * 
	 * @see java.awt.event.FocusListener#focusGained(java.awt.event.FocusEvent)
	 */
	public void focusGained(FocusEvent fe) {
		jtc.getCaret().setVisible(true);
	}

	/**
	 * Hides caret when text area is focused.
	 * 
	 * @see java.awt.event.FocusListener#focusLost(java.awt.event.FocusEvent)
	 */
	public void focusLost(FocusEvent fe) {
		jtc.getCaret().setVisible(false);
	}

}
