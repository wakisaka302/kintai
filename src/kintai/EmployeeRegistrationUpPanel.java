package kintai;

import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
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

		//データベースから取得したリストを受け取る
		list =DB.dbGet();
		//defulttablemodelにリストを詰める
		tablemodel=new DefaultTableModel(ConyertoObject(),columns);
		table =new JTable(tablemodel);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		//テーブルの列サイズ設定
		DefaultTableColumnModel columnModel=(DefaultTableColumnModel)table.getColumnModel();
		TableColumn column =null;
		for(int i=0;i<columnModel.getColumnCount();i++) {
			column =columnModel.getColumn(i);
			column.setPreferredWidth(100);
		}
		//スクロールパネルにテーブルを追加
		JScrollPane sp=new JScrollPane(table);
		sp.setPreferredSize(new Dimension(500,100));
		//upperpanelにスクロールパネルを追加する
		add(sp);
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
		//System.out.println("a");
		return ob;

	}
	
	public static void setObjectRowData(int max, int kihonkyu, String seibetu, String name) {
		//Object[][] ob =new Object[1][4];
		Object[] obb = new Object[4];
//		ob[0][0]= max;
//		ob[0][1]= kihonkyu;
//		ob[0][2]= seibetu;
//		ob[0][3]= name;
		obb[0] = max;
		obb[1] = name;
		obb[2] = seibetu;
		obb[3] = kihonkyu;
		tablemodel.addRow(obb);
	}

}
