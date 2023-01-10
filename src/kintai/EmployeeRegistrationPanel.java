package kintai;

import javax.swing.JPanel;

public class EmployeeRegistrationPanel extends JPanel {
	
	
	
	/**
	 * Create the panel.
	 */
	public EmployeeRegistrationPanel() {

		
		JPanel downpane = new EmployeeRegistrationDownPanel();
		add(downpane);
		
		JPanel uppane = new EmployeeRegistrationUpPanel();
		add(uppane);
		
	}

}
