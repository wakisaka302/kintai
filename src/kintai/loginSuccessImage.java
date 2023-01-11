package kintai;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class loginSuccessImage extends JPanel {

	/**
	 * Create the panel.
	 */
	public loginSuccessImage() {
//		System.out.println(System.getProperty("java.class.path"));
		String loginSuccessImageLocation = System.getProperty("java.class.path")+"\\image\\IMG_0078.PNG";
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(loginSuccessImageLocation));
		add(lblNewLabel);

	}

}
