package kintai;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.ArrayList;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

public class WorkScheduleDisplay extends JPanel implements ActionListener {
	private JTable table;
	JComboBox<String> comboBox;
	JComboBox<String> comboBox_1; //テスト用に<String>
	ArrayList<AData> list;
	ArrayList<String> ym;
	ArrayList<AData> display;
	String[] columns = {"日付","出勤","退勤"};
	DbOperation db = new DbOperation();
	DefaultTableModel tablemodel;

	/**
	 * Create the panel.
	 */
	public WorkScheduleDisplay() {

		//コンボボックス
		//comboBox = 社員名を選択するコンボボックス
		comboBox = new JComboBox<String>();

		//データベースから取得したリストを受け取る
		list = db.dbGetEmployeeData();
		if(list.size() > 0) {

			//取得した社員名をコンボボックスへセットする
			for(int i = 0; i < list.size(); i++) { 
				comboBox.addItem(list.get(i).getName());
				//System.out.println(list.get(i).getEmploye_number());
			}

			comboBox.addActionListener(this);

		}

		//comboBox_1 = 年月を選択するコンボボックス
		comboBox_1 = new JComboBox<String>();
		
		


		//表示ボタン
		JButton btnNewButton = new JButton("表示");

		table = new JTable();

		tablemodel = new DefaultTableModel(null,columns);
		table = new JTable(tablemodel);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); //複数行選択できないようにする


		//勤務表表示パネル
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setPreferredSize(new Dimension(500,100));


		//ボタンイベント
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				//データベースから取得したリストを受け取る

				
				Date m= Date.valueOf((String)comboBox_1.getSelectedItem()+"-01");
				display = db.dbGetWorkSchedule(list.get(comboBox.getSelectedIndex()).getEmploye_number(),m);

				//display = db.dbGetWorkSchedule(list.get(comboBox.getSelectedIndex()).getEmploye_number());
				//list.get(comboBox_1.getSelectedIndex()).getDate();
				ConvertToObject(display);
				//db.dbGetWorkSchedule(comboBox.getSelectedIndex());



			}
		});
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(34)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 366, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnNewButton)))
					.addContainerGap(50, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(5)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 258, Short.MAX_VALUE)
					.addContainerGap())
		);
		setLayout(groupLayout);
	}

	private void ConvertToObject(ArrayList<AData> addlist) {
		Object[][] ob = new Object[addlist.size()][columns.length];
		tablemodel.setRowCount(0);

		//リストでループ処理
		for(int i = 0; i < addlist.size(); i++) {
			ob[i][0] = addlist.get(i).getDate();
			ob[i][1] = addlist.get(i).getAttendance_at_work();
			ob[i][2] = addlist.get(i).getLeaving_work();
		}

		for(int i = 0; i < ob.length; i++) {
			tablemodel.addRow(ob[i]);
		}
	}

	
//	@Override
//	public void itemStateChanged(ItemEvent e) {
//System.out.println(comboBox.getSelectedIndex());
//		if(comboBox.getSelectedIndex() >= 0) {
//
//			//int id = db.dbGetEmployeeId((String)comboBox.getSelectedItem());
//			ym = db.dbGetYearMonth(list.get(comboBox.getSelectedIndex()).getEmployee_id());
//
//			for(int i = 0; i < ym.size(); i++) { 
//				comboBox_1.addItem(ym.get(i));
//
//			}
//		}
//	}

	@Override
	public void actionPerformed(ActionEvent e) {
		comboBox_1.removeAllItems();;
		if(comboBox.getSelectedIndex() >= 0) {

			//int id = db.dbGetEmployeeId((String)comboBox.getSelectedItem());
			ym = db.dbGetYearMonth(list.get(comboBox.getSelectedIndex()).getEmploye_number());
//System.out.println(list.get(comboBox.getSelectedIndex()).getEmployee_id());
			for(int i = 0; i < ym.size(); i++) { 
				comboBox_1.addItem(ym.get(i));

			}
		}
	}

}

