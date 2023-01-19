package kintai;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
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

		JButton btnNewButton = new JButton("削除");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int a=0;
			    a=table.getSelectedRow();
				if(a>=0) {
				DB.dbAttendanceDelete(table.getValueAt(table.getSelectedRow(), 0));//IDでAttendance_dataから削除
				DB.dbDelete(table.getValueAt(table.getSelectedRow(), 0));//IDでemployee_dataから削除
				tablemodel.removeRow(table.getSelectedRow());//画面から削除
				}
			}
		});
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addContainerGap()
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(sp, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 497, Short.MAX_VALUE)
								.addComponent(btnNewButton, Alignment.TRAILING))
						.addContainerGap())
				);
		groupLayout.setVerticalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addGap(5)
						.addComponent(sp, GroupLayout.PREFERRED_SIZE, 162, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addComponent(btnNewButton)
						.addContainerGap(52, Short.MAX_VALUE))
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
	//新しく追加した情報を画面に追加する
	public static void setObjectRowData(int max, int kihonkyu, String seibetu, String name) {
		
		Object[] obb = new Object[4];
		obb[0] = max;
		obb[1] = name;
		obb[2] = seibetu;
		obb[3] = kihonkyu;
		tablemodel.addRow(obb);//	データモデルの最後の行の後ろに行を追加	

}
	
}