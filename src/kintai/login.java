package kintai;

import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class login extends JPanel {
	private JTextField textField;
	static boolean a;
	
	/**
	 * Create the panel.
	 */
	public login() {
		setBackground(SystemColor.textHighlight);
		
		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setColumns(4);
		//textField.getText();
		
		
//		textField.setText(textField.getText());
		
		
		JButton btnNewButton = new JButton("LOGIN");
		btnNewButton.setActionCommand("loginSuccessImage");
		btnNewButton.setActionCommand("loginFailureImage");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//ログインでボタンを４つ押せるようにする
				System.out.println(textField.getText());
				if(textField.getText().equals("8192")) {
					buttonPanel.btnNewButton.setEnabled(true);
					buttonPanel.btnNewButton_1.setEnabled(true);
					buttonPanel.btnNewButton_2.setEnabled(true);
					buttonPanel.btnNewButton_3.setEnabled(true);
					
					//背景画像(成功)を変える
					frame.panel.add(new loginSuccessImage());
					frame.layout.show(frame.panel, "loginSuccessImage");
				} else {
					//背景画像(失敗)を変える
					frame.panel.add(new loginFailureImage());
					frame.layout.show(frame.panel, "loginFailureImage");
				}
				
				
			}
		}
				
				
			
		);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
					.addGap(72)
					.addComponent(textField, GroupLayout.PREFERRED_SIZE, 133, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(95, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap(251, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE))
					.addGap(61))
		);
		setLayout(groupLayout);
		
		
		

	}
	
	
}
