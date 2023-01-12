package kintai;

import java.awt.Component;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingUtilities;

//import create3.backgroundimage;

public class buttonPanel extends JPanel implements ActionListener{

	static JButton btnNewButton;
	static JButton btnNewButton_1;
	static JButton btnNewButton_2;
	static JButton btnNewButton_3;
	
	/**
	 * Create the panel.
	 */
	public buttonPanel() {
		
		//社員情報登録
		btnNewButton = new JButton("A");
		btnNewButton.setSize(50, 50);
		btnNewButton.setEnabled(false);	
		btnNewButton.addActionListener(this);
//		btnNewButton.setActionCommand("backgroundimage");
		
		btnNewButton.setActionCommand("EmployeeRegistrationPanel");
		
		
		//勤務表
		btnNewButton_1 = new JButton("B");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_1.setEnabled(false);
		btnNewButton_1.addActionListener(this);
		btnNewButton_1.setActionCommand("workSchedule");
		
		
		
		//CSVファイルチューザー
		btnNewButton_2 = new JButton("C");
		btnNewButton_2.setSize(50, 50);
		btnNewButton_2.setEnabled(false);
		btnNewButton_2.addActionListener(this);		
		btnNewButton_2.setActionCommand("FileChooser");

		btnNewButton_3 = new JButton("D");
		btnNewButton_3.setEnabled(false);

		JButton btnNewButton_4 = new JButton("閉じる");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//「閉じる」でwindowを閉じる
				Component c = (Component)e.getSource();
				Window w = SwingUtilities.getWindowAncestor(c);
				w.dispose();
				
				
				
			}
		});
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnNewButton_1, GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnNewButton_2, GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
					.addGap(6)
					.addComponent(btnNewButton_3, GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnNewButton_4, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(5)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton_1, GroupLayout.DEFAULT_SIZE, 71, Short.MAX_VALUE)
						.addComponent(btnNewButton_2, GroupLayout.DEFAULT_SIZE, 71, Short.MAX_VALUE)
						.addComponent(btnNewButton_3, GroupLayout.DEFAULT_SIZE, 71, Short.MAX_VALUE)
						.addComponent(btnNewButton_4, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE))
					.addGap(232))
		);
		setLayout(groupLayout);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		
		
//		if(cmd.equals("backgroundimage")) {
//			frame.panel.add(new backgroundimage(), "backgroundimage");
//			
//		}
		
//		
		if(cmd.equals("EmployeeRegistrationPanel")) {
			System.out.println("a");
			frame.panel.add(new EmployeeRegistrationPanel(), "EmployeeRegistrationPanel");
			System.out.println("b");
			//frame.panel.add(cmd, new EmployeeRegistrationPanel());
		}  else if(cmd.equals("FileChooser")) {
		  	frame.panel.add(new FileChooser(), "FileChooser");
		}  else if(cmd.equals("workSchedule")) {
		  	frame.panel.add(new WorkScheduleDisplay(), "workSchedule");
		}
		
		
		
		
		
	//	System.out.println("c");
		frame.layout.show(frame.panel, cmd);
	//	System.out.println("d");
		
	}
	
	
	
	
	
	
	

}
