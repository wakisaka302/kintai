package kintai;

import java.awt.Color;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;

public class EmployeeRegistrationPanel extends JPanel {
	
	
	
	/**
	 * Create the panel.
	 */
	public EmployeeRegistrationPanel() {
		setBackground(new Color(0, 64, 128));

		
		JPanel downpane = new EmployeeRegistrationDownPanel();
		
		JPanel uppane = new EmployeeRegistrationUpPanel();
		uppane.setBackground(new Color(0, 64, 128));
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(uppane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 438, Short.MAX_VALUE)
						.addComponent(downpane, GroupLayout.PREFERRED_SIZE, 438, Short.MAX_VALUE))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(downpane, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(uppane, GroupLayout.DEFAULT_SIZE, 209, Short.MAX_VALUE)
					.addContainerGap())
		);
		setLayout(groupLayout);
		
	}

}
