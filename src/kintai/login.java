package kintai;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class login extends JPanel {
	private JTextField textField;
	static boolean a;
	
	/**
	 * Create the panel.
	 */
	public login() {
		
		textField = new JTextField();
		textField.setColumns(4);
		//textField.getText();
		
		
//		textField.setText(textField.getText());
		
		
		JButton btnNewButton = new JButton("ログイン");
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
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(89)
					.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(60)
					.addComponent(btnNewButton)
					.addContainerGap(117, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(93)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton))
					.addContainerGap(116, Short.MAX_VALUE))
		);
		setLayout(groupLayout);
		
		
		

	}
	
	
}
