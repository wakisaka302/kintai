package kintai;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

public class EmployeeRegistrationDownPanel extends JPanel {
	private JTextField textField_1;
	private JTextField textField_3;


	/**
	 * Create the panel.
	 */
	public EmployeeRegistrationDownPanel() {
		JRadioButton rdbtnNewRadioButton = new JRadioButton("男");

		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("女");
		textField_1 = new JTextField();
		textField_1.setText("");
		textField_1.setColumns(10);

		textField_3 = new JTextField();
		textField_3.setText("");
		textField_3.setColumns(10);
		JButton btnNewButton = new JButton("登録");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int kihonkyu=Integer.parseInt(textField_1.getText());
				String seibetu="";
				if(rdbtnNewRadioButton.isSelected()) {
					seibetu = "男";
				}else if(rdbtnNewRadioButton_1.isSelected()) {
					seibetu = "女";
				}
				
				String name=textField_3.getText();
				DbOperation DB = new DbOperation();
				ArrayList<EmployeeData>list=DB. dbGet();
				int max=0;
				for(int i=0;i<list.size();i++) {
					if(max<list.get(i).getBangou()) {
						max=list.get(i).getBangou();
					}
				}if(max!=0) {
				
					//DBに接続して社員番号の最大値を取得する
					
					//社員番号が存在するとき
					DB.dbInsert(kihonkyu, seibetu, name);
					}else {
					//社員番号が存在しないとき
					DB.dbInsert(23013021,kihonkyu, seibetu, name);
					}
					
			}
		});

		JLabel lblNewLabel_1 = new JLabel("基本給");

		JLabel lblNewLabel_2 = new JLabel("性別");

		JLabel lblNewLabel_3 = new JLabel("名前");



		ButtonGroup bgroup = new ButtonGroup();
		bgroup.add(rdbtnNewRadioButton);
		bgroup.add(rdbtnNewRadioButton_1);
		
		JButton btnNewButton_1 = new JButton("削除");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name=textField_3.getText();
				db DB = new db();
				DB.dbDelete(name);
			}
		});

		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(21)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(33)
							.addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addGap(13))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblNewLabel_3, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
							.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(rdbtnNewRadioButton)))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(rdbtnNewRadioButton_1, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton_1, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(92, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_2)
						.addComponent(lblNewLabel_3)
						.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(rdbtnNewRadioButton_1)
						.addComponent(rdbtnNewRadioButton))
					.addPreferredGap(ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1)
						.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton_1)
						.addComponent(btnNewButton))
					.addContainerGap(56, Short.MAX_VALUE))
		);
		setLayout(groupLayout);

	}
}
