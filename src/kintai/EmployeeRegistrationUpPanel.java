package kintai;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;


public class EmployeeRegistrationUpPanel extends JPanel {
	ArrayList<EmployeeData>list;
	DbOperation DB=new DbOperation();
	private JTable table;
	static DefaultTableModel tablemodel;
	String [] columns= {"社員ID","名前","性別","基本給"};
	/**
	 * Create the panel.
	 */


	/**
	 * Create the panel.
	 */
	public EmployeeRegistrationUpPanel() {
		setBackground(new Color(0, 64, 128));


		//データベースから取得したリストを受け取る
		list =DB.dbGet();
		//defulttablemodelにリストを詰める
		tablemodel=new DefaultTableModel(ConyertoObject(),columns);
		table =new JTable(tablemodel);
		//テーブル内のセルをクリックした時に、デフォルトではクリックされたセルを含む行全体が選択状態になります
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		//テーブルの列サイズ設定
		DefaultTableColumnModel columnModel=(DefaultTableColumnModel)table.getColumnModel();
		TableColumn column =null;
		for(int i=0;i<columnModel.getColumnCount();i++) {
			column =columnModel.getColumn(i);
			column.setPreferredWidth(100);
		}
		table.setDefaultEditor(Object.class, null);
		//スクロールパネルにテーブルを追加
		JTableHeader jheader = table.getTableHeader();
		jheader.setReorderingAllowed(false);//カラムの固定

		JScrollPane sp=new JScrollPane(table);

		sp.setPreferredSize(new Dimension(400,250));

		JLabel lblNewLabel_4_1 = new JLabel("※リストより社員を選択後、[削除]");
		lblNewLabel_4_1.setForeground(Color.LIGHT_GRAY);
		lblNewLabel_4_1.setFont(new Font("MS UI Gothic", Font.BOLD, 12));
		lblNewLabel_4_1.setBackground(new Color(128, 128, 255));

		JLabel lblNewLabel_4_1_1 = new JLabel("　データベースから社員情報を削除します。");
		lblNewLabel_4_1_1.setForeground(Color.LIGHT_GRAY);
		lblNewLabel_4_1_1.setFont(new Font("MS UI Gothic", Font.BOLD, 12));
		lblNewLabel_4_1_1.setBackground(new Color(128, 128, 255));

		JButton btnNewButton = new JButton("削除");
		btnNewButton.setFont(new Font("MS UI Gothic", Font.BOLD, 12));
		btnNewButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				int a=0;
				a=table.getSelectedRow();
				if(a>=0) {
					DB.dbAttendanceDelete(table.getValueAt(table.getSelectedRow(), 0));//IDでAttendance_dataから削除
					DB.dbDelete(table.getValueAt(table.getSelectedRow(), 0));
					tablemodel.removeRow(table.getSelectedRow());

					lblNewLabel_4_1_1.setText("削除成功：社員情報を削除しました。");//脇坂追加文
					lblNewLabel_4_1_1.setForeground(Color.WHITE);//脇坂追加分
				}else {
					lblNewLabel_4_1_1.setText("削除時エラー：リストより社員を選択して下さい。");//脇坂追加文
					lblNewLabel_4_1_1.setForeground(Color.PINK);//脇坂追加分
				}
			}
		});


		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
										.addGap(78)
										.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 155, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.UNRELATED)
										.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
												.addGroup(groupLayout.createSequentialGroup()
														.addComponent(lblNewLabel_4_1, GroupLayout.PREFERRED_SIZE, 175, GroupLayout.PREFERRED_SIZE)
														.addGap(36))
												.addComponent(lblNewLabel_4_1_1)))
								.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
										.addGap(21)
										.addComponent(sp, GroupLayout.DEFAULT_SIZE, 444, Short.MAX_VALUE)))
						.addContainerGap())
				);
		groupLayout.setVerticalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addComponent(sp, GroupLayout.PREFERRED_SIZE, 162, GroupLayout.PREFERRED_SIZE)
						.addGap(10)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
										.addComponent(lblNewLabel_4_1)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(lblNewLabel_4_1_1))
								.addComponent(btnNewButton))
						.addContainerGap(96, Short.MAX_VALUE))
				);
		setLayout(groupLayout);
	}
	//ArrayList→Objectに変換する
	private Object[][] ConyertoObject(){
		Object[][] ob =new Object[list.size()][columns.length];

		//リストでループ処理
		for(int i=0;i<list.size();i++){
			ob[i][0]=list.get(i).getBangou();
			ob[i][1]=list.get(i).getName();
			ob[i][2]=list.get(i).getSeibetu();
			ob[i][3]=list.get(i).getKihon();

		}

		return ob;

	}

	public static void setObjectRowData(int max, int kihonkyu, String seibetu, String name) {

		Object[] obb = new Object[4];
		obb[0] = max;
		obb[1] = name;
		obb[2] = seibetu;
		obb[3] = kihonkyu;
		tablemodel.addRow(obb);
	}
}
