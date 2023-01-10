package kintai;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class WorkScheduleDisplay extends JPanel {
	private JTable table;
	JComboBox<String> comboBox;
	JComboBox<String> comboBox_1; //テスト用に<String>
	ArrayList<AData> list;
	KintaiDbAccess db = new KintaiDbAccess();


	/**
	 * Create the panel.
	 */
	public WorkScheduleDisplay() {

		//コンボボックス
		//comboBox = 社員名を選択するコンボボックス
		comboBox = new JComboBox<String>();
		//データベースから社員名を取得し、配列またはリストへ

		//String[] columns = {"社員ID","基本給","性別","名前"}; //テスト用
		
		//データベースから取得したリストを受け取る
		list = db.dbGetEmployeeData();

		//取得した社員名をコンボボックスへセットする
		for(int i = 0; i < list.size(); i++) { 
			comboBox.addItem(list.get(i).getName());
		}

		add(comboBox);

		//comboBox_1 = 年月を選択するコンボボックス
		comboBox_1 = new JComboBox<String>();

		String[] d = {"2022/12","2023/01","2023/02","2023/03","2023/04","2023/05","2023/06"}; //テスト用
		list = db.dbGetAttendanceData();

		for(int i = 0; i < d.length; i++) { 
			comboBox_1.addItem(d[i]);
		}

		add(comboBox_1);



		//表示ボタン
		JButton btnNewButton = new JButton("表示");

		//ボタンイベント
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				//テーブル
				table = new JTable();
				add(table);
				//データベースから取得したリストを受け取る
				
				db.dbGetEmployeeId((String) comboBox.getSelectedItem());
				
				//db.dbGetWorkSchedule();


				//勤務表表示パネル
				JScrollPane scrollPane = new JScrollPane(table);
				scrollPane.setPreferredSize(new Dimension(500,100));
				add(scrollPane);

			}
		});
		add(btnNewButton);




	}

}
