package kintai;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.ArrayList;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

public class meisaiPanel extends JPanel implements ActionListener{

	private JTable table;
	JComboBox<String> comboBox;
	JComboBox<String> comboBox_1; 
	private JComboBox<String> comboBox_1_1;
	ArrayList<AData> list;
	ArrayList<String> ym;
	ArrayList<AData> display;
	String[] columns = {"日付","出勤","退勤"};
	DbOperation db = new DbOperation();
	DefaultTableModel tablemodel;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField textField_9;
	private JTextField textField_10;
	private JTextField textField_11;
	private JTextField textField_12;
	private JTextField textField_13;
	private JTextField txta;
	private JTextField textField_14;
	private JTextField textField_15;
	private JTextField textField_16;
	private JTextField textField_17;
	private JTextField textField_18;
	private JTextField textField_19;
	private JTextField textField_20;
	private JTextField textField_21;
	private JTextField textField_22;
	private JTextField textField_24;
	private JTextField textField_23;
	
	private JLabel lblNewLabel;
	

	private int basicSalary;
	private int pensionInsurance;
	private int healthInsurance;
	private int employmentInsurance;
	private int deduction;
	private int total;



	/**
	 * Create the panel.
	 * @throws Exception 
	 */


	public meisaiPanel() throws Exception {
		setBackground(SystemColor.activeCaption);
		//社員名を選択するコンボボックス
		comboBox = new JComboBox<String>();
		list = db.dbGetEmployeeData();
		if(list.size() > 0) {
			//取得した社員名をコンボボックスへセットする
			for(int i = 0; i < list.size(); i++) { 
				comboBox.addItem(list.get(i).getName());
			}
			comboBox.addActionListener(this);
		}

		//年月を選択するコンボボックス
		comboBox_1_1 = new JComboBox<String>();


		//表示ボタン
		JButton btnNewButton = new JButton("表示");
		btnNewButton.setFont(new Font("MS UI Gothic", Font.BOLD, 12));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if(comboBox.getSelectedIndex()!=-1 && comboBox_1_1.getSelectedIndex()!=-1) {

					//データベースから取得したリストを受け取る
					Date m = Date.valueOf((String) comboBox_1_1.getSelectedItem()+"-01");

					//勤務日数を表示
					try {
						textField_2.setText(String.valueOf(db.GetWorkingDays(list.get(comboBox.getSelectedIndex()).getEmploye_number(),m)));
					} catch (Exception e1) {
						e1.printStackTrace();
					}

					//勤務時間を表示
					try {
						textField_6.setText(db.GetWorkingHours(list.get(comboBox.getSelectedIndex()).getEmploye_number(),m));
					} catch (Exception e1) {
						e1.printStackTrace();
					}

					//基本給を表示
					try {
						textField_11.setText(db.GetSalary(list.get(comboBox.getSelectedIndex()).getEmploye_number()));
						basicSalary = Integer.parseInt(db.GetSalary(list.get(comboBox.getSelectedIndex()).getEmploye_number()));
					} catch (Exception e1) {
						e1.printStackTrace();
					}


					if(IsSalary(basicSalary)) {
						//健康保険料を表示
						healthInsurance = InsuranceCal(basicSalary);
						textField_17.setText(String.valueOf(healthInsurance));


						//厚生年金を表示
						pensionInsurance = PensionCal(basicSalary);
						textField_19.setText(String.valueOf(pensionInsurance));


						//雇用保険料を表示
						employmentInsurance = Math.round(basicSalary*5/1000);
						textField_21.setText(String.valueOf(employmentInsurance));


						//総支給額
						textField_22.setText("総支給額：   ¥" + basicSalary);


						//総控除額
						deduction =  healthInsurance+pensionInsurance+employmentInsurance;
						textField_23.setText("総控除額：   ¥" + deduction);


						//差引支給額
						total = basicSalary - deduction;
						textField_24.setText("差引支給額：   ¥" + total);
						
						
						lblNewLabel.setText("");


					} else {
						//健康保険料を表示
						textField_17.setText("");


						//厚生年金を表示
						textField_19.setText("");


						//雇用保険料を表示
						textField_21.setText("");


						//総支給額
						textField_22.setText("総支給額   " + basicSalary);


						//総控除額
						textField_23.setText("総控除額   " + "");


						//差引支給額
						textField_24.setText("差引支給額   " + basicSalary);
						
						lblNewLabel.setText("控除額の算定ができません");
						lblNewLabel.setBackground(new Color(0, 64, 128));
						lblNewLabel.setForeground(new Color(255, 255, 255));
						
//						rdbtnNewRadioButton_1.setBackground(new Color(0, 64, 128));
//						rdbtnNewRadioButton_1.setForeground(new Color(255, 255, 255));
						

					}

				}
			}
		});



		textField = new JTextField();
		textField.setFont(new Font("MS UI Gothic", Font.BOLD, 12));
		textField.setForeground(Color.BLACK);
		textField.setBackground(SystemColor.textHighlight);
		textField.setText("出退勤");
		textField.setColumns(10);
		textField.setEditable(false);


		textField_1 = new JTextField();
		textField_1.setFont(new Font("MS UI Gothic", Font.BOLD, 12));
		textField_1.setForeground(Color.BLACK);
		textField_1.setBackground(SystemColor.textHighlight);
		textField_1.setText("出勤");
		textField_1.setColumns(10);
		textField_1.setEditable(false);


		textField_2 = new JTextField();
		textField_2.setForeground(Color.BLACK);
		textField_2.setText("0");
		textField_2.setColumns(10);
		textField_2.setEditable(false);


		textField_3 = new JTextField();
		textField_3.setFont(new Font("MS UI Gothic", Font.BOLD, 12));
		textField_3.setForeground(Color.BLACK);
		textField_3.setBackground(SystemColor.textHighlight);
		textField_3.setText("欠勤");
		textField_3.setColumns(10);
		textField_3.setEditable(false);


		textField_4 = new JTextField("0");
		textField_4.setForeground(Color.BLACK);
		textField_4.setColumns(10);
		textField_4.setEditable(false);


		textField_5 = new JTextField();
		textField_5.setFont(new Font("MS UI Gothic", Font.BOLD, 12));
		textField_5.setForeground(Color.BLACK);
		textField_5.setBackground(SystemColor.textHighlight);
		textField_5.setHorizontalAlignment(SwingConstants.CENTER);
		textField_5.setText("勤務時間");
		textField_5.setColumns(10);
		textField_5.setEditable(false);


		textField_6 = new JTextField();
		textField_6.setForeground(Color.BLACK);
		textField_6.setText("0");
		textField_6.setColumns(10);
		textField_6.setEditable(false);


		textField_7 = new JTextField();
		textField_7.setFont(new Font("MS UI Gothic", Font.BOLD, 12));
		textField_7.setForeground(Color.BLACK);
		textField_7.setBackground(SystemColor.textHighlight);
		textField_7.setText("休出");
		textField_7.setColumns(10);
		textField_7.setEditable(false);


		textField_8 = new JTextField();
		textField_8.setForeground(Color.BLACK);
		textField_8.setText("0");
		textField_8.setColumns(10);
		textField_8.setEditable(false);


		textField_9 = new JTextField();
		textField_9.setFont(new Font("MS UI Gothic", Font.BOLD, 12));
		textField_9.setForeground(Color.BLACK);
		textField_9.setBackground(SystemColor.textHighlight);
		textField_9.setText("支給額");
		textField_9.setColumns(10);
		textField_9.setEditable(false);


		textField_10 = new JTextField();
		textField_10.setFont(new Font("MS UI Gothic", Font.BOLD, 12));
		textField_10.setForeground(Color.BLACK);
		textField_10.setBackground(SystemColor.textHighlight);
		textField_10.setText("基本給");
		textField_10.setColumns(10);
		textField_10.setEditable(false);


		textField_11 = new JTextField();
		textField_11.setForeground(Color.BLACK);
		textField_11.setText("0");
		textField_11.setColumns(10);
		textField_11.setEditable(false);


		textField_12 = new JTextField();
		textField_12.setFont(new Font("MS UI Gothic", Font.BOLD, 12));
		textField_12.setForeground(Color.BLACK);
		textField_12.setBackground(SystemColor.textHighlight);
		textField_12.setText("通勤手当");
		textField_12.setColumns(10);
		textField_12.setEditable(false);


		textField_13 = new JTextField();
		textField_13.setForeground(Color.BLACK);
		textField_13.setText("0");
		textField_13.setColumns(10);
		textField_13.setEditable(false);


		txta = new JTextField();
		txta.setFont(new Font("MS UI Gothic", Font.BOLD, 12));
		txta.setForeground(Color.BLACK);
		txta.setBackground(SystemColor.textHighlight);
		txta.setText("残業手当A");
		txta.setColumns(10);
		txta.setEditable(false);

		textField_14 = new JTextField();
		textField_14.setForeground(Color.BLACK);
		textField_14.setText("0");
		textField_14.setColumns(10);
		textField_14.setEditable(false);


		textField_15 = new JTextField();
		textField_15.setFont(new Font("MS UI Gothic", Font.BOLD, 12));
		textField_15.setForeground(Color.BLACK);
		textField_15.setBackground(SystemColor.textHighlight);
		textField_15.setText("控除額");
		textField_15.setColumns(10);
		textField_15.setEditable(false);


		textField_16 = new JTextField();
		textField_16.setFont(new Font("MS UI Gothic", Font.BOLD, 12));
		textField_16.setForeground(Color.BLACK);
		textField_16.setBackground(SystemColor.textHighlight);
		textField_16.setText("健康保険");
		textField_16.setColumns(10);
		textField_16.setEditable(false);

		//健康保険額表示
		textField_17 = new JTextField();
		textField_17.setForeground(Color.BLACK);
		textField_17.setText("0");
		textField_17.setColumns(10);
		textField_17.setEditable(false);


		textField_18 = new JTextField();
		textField_18.setFont(new Font("MS UI Gothic", Font.BOLD, 12));
		textField_18.setForeground(Color.BLACK);
		textField_18.setBackground(SystemColor.textHighlight);
		textField_18.setText("厚生年金");
		textField_18.setColumns(10);
		textField_18.setEditable(false);

		//厚生年金表示
		textField_19 = new JTextField();
		textField_19.setForeground(Color.BLACK);
		textField_19.setText("0");
		textField_19.setColumns(10);
		textField_19.setEditable(false);


		textField_20 = new JTextField();
		textField_20.setFont(new Font("MS UI Gothic", Font.BOLD, 12));
		textField_20.setForeground(Color.BLACK);
		textField_20.setBackground(SystemColor.textHighlight);
		textField_20.setText("雇用保険");
		textField_20.setColumns(10);
		textField_20.setEditable(false);

		//雇用保険表示
		textField_21 = new JTextField();
		textField_21.setForeground(Color.BLACK);
		textField_21.setText("0");
		textField_19.setColumns(10);
		textField_21.setEditable(false);

		//総支給額
		textField_22 = new JTextField();
		textField_22.setForeground(Color.BLACK);
		textField_22.setBackground(Color.PINK);
		textField_22.setFont(new Font("MS UI Gothic", Font.BOLD, 14));
		textField_22.setText("総支給額：");
		textField_22.setColumns(20);
		textField_22.setEditable(false);


		textField_24 = new JTextField();
		textField_24.setForeground(Color.BLACK);
		textField_24.setBackground(Color.PINK);
		textField_24.setFont(new Font("MS UI Gothic", Font.BOLD, 14));
		textField_24.setText("差引支給額：");
		textField_24.setColumns(20);
		textField_24.setEditable(false);


		textField_23 = new JTextField();
		textField_23.setForeground(Color.BLACK);
		textField_23.setBackground(Color.PINK);
		textField_23.setFont(new Font("MS UI Gothic", Font.BOLD, 14));
		textField_23.setText("総控除額：");
		textField_23.setColumns(20);
		textField_23.setEditable(false);
		
		lblNewLabel = new JLabel("");



		//GroupLayoutのコード
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(textField, GroupLayout.DEFAULT_SIZE, 216, Short.MAX_VALUE)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(comboBox_1_1, GroupLayout.PREFERRED_SIZE, 112, GroupLayout.PREFERRED_SIZE)))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
										.addComponent(textField_1, Alignment.LEADING, 0, 0, Short.MAX_VALUE)
										.addComponent(textField_2, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(textField_7, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(textField_5, GroupLayout.PREFERRED_SIZE, 126, GroupLayout.PREFERRED_SIZE))
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(textField_4, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(textField_8, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(textField_6, 0, 0, Short.MAX_VALUE))))
								.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE))
							.addGap(92))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(textField_9, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(textField_10, GroupLayout.PREFERRED_SIZE, 67, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(textField_12, GroupLayout.PREFERRED_SIZE, 67, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(txta, GroupLayout.PREFERRED_SIZE, 67, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(textField_11, GroupLayout.PREFERRED_SIZE, 67, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(textField_13, GroupLayout.PREFERRED_SIZE, 67, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(textField_14, GroupLayout.PREFERRED_SIZE, 67, GroupLayout.PREFERRED_SIZE)))
							.addContainerGap(162, Short.MAX_VALUE))
						.addComponent(textField_22, GroupLayout.PREFERRED_SIZE, 144, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(lblNewLabel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
									.addComponent(textField_15, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(textField_16, GroupLayout.PREFERRED_SIZE, 67, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(textField_18, GroupLayout.PREFERRED_SIZE, 67, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(textField_20, GroupLayout.PREFERRED_SIZE, 67, GroupLayout.PREFERRED_SIZE))
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(textField_17, GroupLayout.PREFERRED_SIZE, 67, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addGroup(groupLayout.createSequentialGroup()
													.addComponent(textField_19, GroupLayout.PREFERRED_SIZE, 67, GroupLayout.PREFERRED_SIZE)
													.addPreferredGap(ComponentPlacement.RELATED)
													.addComponent(textField_21, GroupLayout.PREFERRED_SIZE, 67, GroupLayout.PREFERRED_SIZE))
												.addComponent(textField_24, GroupLayout.PREFERRED_SIZE, 174, GroupLayout.PREFERRED_SIZE)
												.addComponent(textField_23, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 146, GroupLayout.PREFERRED_SIZE))))))
							.addGap(300))))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(comboBox_1_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton))
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(27)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(textField_5, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(textField_7, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(textField_4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(textField_6, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(textField_8, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGroup(groupLayout.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textField, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addComponent(textField_9, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(textField_10, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(textField_12, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(txta, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(textField_11, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(textField_13, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(textField_14, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addComponent(textField_15, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(textField_16, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(textField_18, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(textField_20, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(textField_17, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(textField_19, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(textField_21, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(textField_23, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField_22, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(textField_24, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
					.addGap(11)
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		setLayout(groupLayout);

	}



	//基本給から控除可を判別するメソッド
	public boolean IsSalary(int basicSalary) {
		if(basicSalary<138000 || 455000<=basicSalary) {
			return false;
		} else {
			return true;
		}
	}



	//健康保険料を計算するメソッド
	public static int InsuranceCal(int basicSalary) {
		int[][] array = PensionAndHealthInsurance.PensionAndHealthInsurance;
		int insurance = 0;
		for(int i=0;i<array.length;i++) {
			if(array[i][0] <= basicSalary && basicSalary < array[i][1]) {
				System.out.println(insurance);
				return insurance = array[i][2];
			}
		}
		System.out.println(insurance);
		return insurance;
	}


	//厚生年金を計算するメソッド
	public static int PensionCal(int basicSalary) {
		int[][] array = PensionAndHealthInsurance.PensionAndHealthInsurance;
		int insurance = 0;
		for(int i=0;i<array.length;i++) {
			if(array[i][0] <= basicSalary && basicSalary < array[i][1]) {
				System.out.println(insurance);
				return insurance = array[i][3];
			}
		}
		System.out.println(insurance);
		return insurance;
	}








	@Override
	public void actionPerformed(ActionEvent e) {
		comboBox_1_1.removeAllItems();;
		if(comboBox.getSelectedIndex() >= 0) {
			//int id = db.dbGetEmployeeId((String)comboBox.getSelectedItem());
			ym = db.dbGetYearMonth(list.get(comboBox.getSelectedIndex()).getEmploye_number());
			//System.out.println(list.get(comboBox.getSelectedIndex()).getEmployee_id());
			for(int i = 0; i < ym.size(); i++) { 
				comboBox_1_1.addItem(ym.get(i));
			}
		}

	}
}
