package kintai;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
	String raberu="";

	/**
	 * Create the panel.
	 */
	public EmployeeRegistrationDownPanel() {
		JLabel lblNewLabel = new JLabel(raberu);
		lblNewLabel.setForeground(Color.RED);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("男");
		rdbtnNewRadioButton.setSelected(true);

		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("女");
		textField_1 = new JTextField();
		textField_1.setText(null);
		textField_1.setColumns(10);

		textField_3 = new JTextField();
		textField_3.setText(null);
		textField_3.setColumns(10);
		JButton btnNewButton = new JButton("登録");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int check=0;
				int kihonkyu=0;
				try {
				kihonkyu=Integer.parseInt(textField_1.getText());
				}catch(IllegalArgumentException a){
					check=1;
				}//finally {
				if(check!=1) {
				String seibetu="";
				if(rdbtnNewRadioButton.isSelected()) {
					seibetu = "男";
				}else if(rdbtnNewRadioButton_1.isSelected()) {
					seibetu = "女";
				}
				
				
				
				DbOperation DB = new DbOperation();
				//ArrayList<EmployeeData>list=DB. dbGet();
				int max=DB.IdMaxGet();

//								for(int i=0;i<list.size();i++) {
//									if(max<list.get(i).getBangou()) {
//										max=list.get(i).getBangou();
//									}
				if(max!=0) {
					max = max + 1;
					//DBに接続して社員番号の最大値を取得する

					//社員番号が存在するとき
//					DB.dbInsert(max, kihonkyu, seibetu, name);

				}else {
					max = 20230001;
					//社員番号が存在しないとき
//					DB.dbInsert(23013021,kihonkyu, seibetu, name);

				}
				
				String name=textField_3.getText();
				String name2=name.replaceAll("[ 　]", "");
                if(name2.length()==0) {
                	raberu="名前を入力してください";
    				lblNewLabel.setText(raberu);
				}else {
					if(kihonkyu<0) {
						raberu="エラー：正しいパスワードを入力してください";
	    				lblNewLabel.setText(raberu);
					}else {
				DB.dbInsert(max, kihonkyu, seibetu, name);
				EmployeeRegistrationUpPanel.setObjectRowData(max, kihonkyu, seibetu, name);
				raberu="登録完了しました";
				lblNewLabel.setText(raberu);
				textField_1.setText("");
				textField_3.setText("");
					}
				}
			}else  {
				raberu="基本給を数値で入力してください";
				lblNewLabel.setText(raberu);
			}
		}}
			//}
	);

		JLabel lblNewLabel_1 = new JLabel("基本給");

		JLabel lblNewLabel_2 = new JLabel("性別");

		JLabel lblNewLabel_3 = new JLabel("名前");



		ButtonGroup bgroup = new ButtonGroup();
		bgroup.add(rdbtnNewRadioButton);
		bgroup.add(rdbtnNewRadioButton_1);
		
		

		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(21)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 222, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel_3, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(2)
									.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(textField_1, Alignment.LEADING)
								.addComponent(textField_3, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE))))
					.addGap(18)
					.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(rdbtnNewRadioButton)
					.addGap(18)
					.addComponent(rdbtnNewRadioButton_1, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addGap(301)
					.addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(92))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_3)
						.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(rdbtnNewRadioButton_1)
						.addComponent(rdbtnNewRadioButton)
						.addComponent(lblNewLabel_2))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1)
						.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnNewButton)
						.addComponent(lblNewLabel))
					.addContainerGap(14, Short.MAX_VALUE))
		);
		setLayout(groupLayout);

	}
}
