package kintai;

import java.awt.Color;
import java.awt.Font;
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
		setBackground(new Color(0, 64, 128));
		JLabel lblNewLabel = new JLabel(raberu);
		lblNewLabel.setForeground(Color.RED);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("男");
		rdbtnNewRadioButton.setForeground(new Color(255, 255, 255));
		rdbtnNewRadioButton.setBackground(new Color(0, 64, 128));
		rdbtnNewRadioButton.setSelected(true);

		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("女");
		rdbtnNewRadioButton_1.setBackground(new Color(0, 64, 128));
		rdbtnNewRadioButton_1.setForeground(new Color(255, 255, 255));
		
		JLabel lblNewLabel_4_1 = new JLabel("※各項目を設定後、[ 登録 ]");
		lblNewLabel_4_1.setFont(new Font("MS UI Gothic", Font.BOLD, 12));
		lblNewLabel_4_1.setForeground(new Color(192, 192, 192));
		lblNewLabel_4_1.setBackground(new Color(128, 128, 255));
		
		String lbl_4 = " 　社員情報をデータベースに登録します。";
		JLabel lblNewLabel_4 = new JLabel(lbl_4);
		lblNewLabel_4.setFont(new Font("MS UI Gothic", Font.BOLD, 12));
		lblNewLabel_4.setForeground(new Color(192, 192, 192));
		lblNewLabel_4.setBackground(new Color(128, 128, 255));
				
		textField_1 = new JTextField();
		textField_1.setText(null);
		textField_1.setColumns(10);

		textField_3 = new JTextField();
		textField_3.setText(null);
		textField_3.setColumns(10);
		JButton btnNewButton = new JButton("登録");
		btnNewButton.setFont(new Font("MS UI Gothic", Font.BOLD, 12));
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
				raberu="登録成功：社員情報をDBに登録しました。";
				lblNewLabel_4.setForeground(Color.WHITE);
				lblNewLabel_4.setText(raberu);
				textField_1.setText("");//入力した値を消す
				textField_3.setText("");//入力した値を消す
					}
				}
			}else  {//基本給が数値でなければ
				raberu="登録時エラー：基本給を数値で入力してください。";
				lblNewLabel_4.setForeground(Color.PINK);
				lblNewLabel_4.setText(raberu);

			}
		}}

	);

		JLabel lblNewLabel_1 = new JLabel("基本給：");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setFont(new Font("MS UI Gothic", Font.BOLD, 12));

		JLabel lblNewLabel_2 = new JLabel("性別：");
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_2.setFont(new Font("MS UI Gothic", Font.BOLD, 12));

		JLabel lblNewLabel_3 = new JLabel("社員名：");
		lblNewLabel_3.setForeground(new Color(255, 255, 255));
		lblNewLabel_3.setFont(new Font("MS UI Gothic", Font.BOLD, 12));



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
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
							.addGroup(groupLayout.createSequentialGroup()
								.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(textField_1))
							.addGroup(groupLayout.createSequentialGroup()
								.addComponent(lblNewLabel_3, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, 153, GroupLayout.PREFERRED_SIZE))))
					.addGap(37)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(rdbtnNewRadioButton)
							.addGap(18)
							.addComponent(rdbtnNewRadioButton_1, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
							.addGap(17))
						.addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addGap(12))
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap(188, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel_4, GroupLayout.PREFERRED_SIZE, 241, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_4_1, GroupLayout.PREFERRED_SIZE, 204, GroupLayout.PREFERRED_SIZE))
					.addGap(21))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_3)
						.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_2)
						.addComponent(rdbtnNewRadioButton)
						.addComponent(rdbtnNewRadioButton_1))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1)
						.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton))
					.addGap(27)
					.addComponent(lblNewLabel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblNewLabel_4_1)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblNewLabel_4)
					.addContainerGap(214, Short.MAX_VALUE))
		);
		setLayout(groupLayout);

	}
}
