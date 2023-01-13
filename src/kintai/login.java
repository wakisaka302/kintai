package kintai;

import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
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
				if(textField.getText().equals("8192") || textField.getText().equals("８１９２")) {
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
					textField.setText("");
				}
				
				
			}
		}
				
				
			
		);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(119)
					.addComponent(textField, GroupLayout.PREFERRED_SIZE, 133, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(59, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(251, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE))
					.addGap(61))
		);
		setLayout(groupLayout);
		
		
		

	}
	
	
}
