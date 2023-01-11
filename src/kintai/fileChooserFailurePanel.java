package kintai;

import javax.swing.JPanel;
import javax.swing.JTextField;

public class fileChooserFailurePanel extends JPanel {
	private JTextField textField;

	/**
	 * Create the panel.
	 */
	public fileChooserFailurePanel() {
		
		textField = new JTextField();
		textField.setText("ファイルを開くことができませんでした");
		add(textField);
		textField.setColumns(20);

	}

}
