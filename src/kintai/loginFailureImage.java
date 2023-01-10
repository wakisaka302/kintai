package kintai;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class loginFailureImage extends JPanel {

	/**
	 * Create the panel.
	 */
	public loginFailureImage() {

		System.out.println(System.getProperty("java.class.path"));


		String loginFailureImageLocation = System.getProperty("java.class.path")+"\\image\\IMG_0079.PNG";
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(loginFailureImageLocation));
		add(lblNewLabel);


	}

}
