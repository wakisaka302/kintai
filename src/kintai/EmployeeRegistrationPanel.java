package kintai;

import javax.swing.JPanel;

public class EmployeeRegistrationPanel extends JPanel {

	private JPanel contentPane;
	
	
	
	/**
	 * Create the panel.
	 */
	public EmployeeRegistrationPanel() {

		setBounds(100, 100, 549, 336);
		contentPane = new JPanel();
		
		JPanel downpane = new EmployeeRegistrationDownPanel();
		contentPane.add(downpane);
		
		JPanel uppane = new EmployeeRegistrationUpPanel();
		contentPane.add(uppane);
		
	}

}
