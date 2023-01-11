//cardlayout確認用クラス

package kintai;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class backgroundimage extends JPanel {

	/**
	 * Create the panel.
	 */
	public backgroundimage() {

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\3030743\\Desktop\\higurashi1.jpg"));
		add(lblNewLabel);

	}

}
