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
				kihonkyu=Integer.parseInt(textField_1.getText());//textField_1に入力された値を基本給に代入
				}catch(IllegalArgumentException a){
					check=1;//textField_1に入力された値が数値ではなかった場合checkに１を代入
				}
				if(check!=1) {//textField_1に正しい入力がされた場合(check=1でなければ数値が入力されている)
				String seibetu="";
				if(rdbtnNewRadioButton.isSelected()) {//rdbtnNewRadioButtonにチェックが入れば
					seibetu = "男";
				}else if(rdbtnNewRadioButton_1.isSelected()) {//rdbtnNewRadioButton_1にチェックが入れば
					seibetu = "女";
				}
				
				
				
				DbOperation DB = new DbOperation();
				
				int max=DB.IdMaxGet();//IdMaxGet()のメソッドを使いIDのmaxの値をを取ってきて１プラスする
				if(max!=0) {
					max = max + 1;

				}else {//IDが取ってこれなければIDを20230001にする
					max = 20230001;

				}
				
				String name=textField_3.getText();//nameにtextField_3を代入
				String name2=name.replaceAll("[ 　]", "");//nameの空白を削除
                if(name2.length()==0) {//nameの文字列が0であればラベルに名前を入力してくださいを表示
                	raberu="名前を入力してください";
    				lblNewLabel.setText(raberu);
				}else {
					if(kihonkyu<0) {//基本給がマイナスなら
						raberu="エラー：正しいパスワードを入力してください";
	    				lblNewLabel.setText(raberu);
					}else {
				DB.dbInsert(max, kihonkyu, seibetu, name);//dbInsert()のメソッドを使いデータベースに登録する
				EmployeeRegistrationUpPanel.setObjectRowData(max, kihonkyu, seibetu, name);
				raberu="登録完了しました";
				lblNewLabel.setText(raberu);
				textField_1.setText("");//入力した値を消す
				textField_3.setText("");//入力した値を消す
					}
				}
			}else  {//基本給が数値でなければ
				raberu="基本給を数値で入力してください";
				lblNewLabel.setText(raberu);
			}
		}}

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
