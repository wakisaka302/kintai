package kintai;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;

public class login extends JPanel {
	private JTextField textField;
	static boolean a;
	String raberu="";//脇坂変更分
	
	/**
	 * Create the panel.
	 */
	public login() {
		setBackground(new Color(0, 0, 128));
		
		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setColumns(4);
		//textField.getText();

		
//		textField.setText(textField.getText());
		
		JLabel lblNewLabel = new JLabel(raberu);
		lblNewLabel.setForeground(new Color(255, 255, 255));
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
//					frame.panel.add(new loginFailureImage());
//					frame.layout.show(frame.panel, "loginFailureImage");
					textField.setText("");
					
					//脇坂変更分
					raberu = "正しいパスワードを入力してください";
    				lblNewLabel.setText(raberu);
					
				}
				
				
			}
		}
				
				
			
		);
		
		JLabel lblNewLabel_1 = new JLabel("pass:");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setFont(new Font("MS UI Gothic", Font.BOLD, 26));
		
		JLabel lblNewLabel_2 = new JLabel("　　　勤怠管理/給与計算System");
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_2.setVerticalAlignment(SwingConstants.BOTTOM);
		lblNewLabel_2.setFont(new Font("MS UI Gothic", Font.BOLD, 23));
		
		JLabel lblNewLabel_2_1 = new JLabel("　　　㈱田島組");
		lblNewLabel_2_1.setVerticalAlignment(SwingConstants.BOTTOM);
		lblNewLabel_2_1.setForeground(Color.WHITE);
		lblNewLabel_2_1.setFont(new Font("MS UI Gothic", Font.BOLD, 23));
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(64)
							.addComponent(lblNewLabel_1)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textField, GroupLayout.PREFERRED_SIZE, 133, GroupLayout.PREFERRED_SIZE)
							.addGap(7)
							.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(128)
							.addComponent(lblNewLabel))
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addContainerGap(90, Short.MAX_VALUE)
							.addComponent(lblNewLabel_2_1, GroupLayout.PREFERRED_SIZE, 350, GroupLayout.PREFERRED_SIZE))
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addGap(90)
							.addComponent(lblNewLabel_2, GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(81)
					.addComponent(lblNewLabel_2_1, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblNewLabel_2)
					.addGap(108)
					.addComponent(lblNewLabel)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE))
					.addGap(69))
		);
		setLayout(groupLayout);
		
		
		

	}
	
	
}
