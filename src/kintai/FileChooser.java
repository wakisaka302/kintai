package kintai;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.Date;
import java.util.ArrayList;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class FileChooser extends JPanel implements ActionListener{
	JLabel label;
	private String ym;
	private String filepath;
	private String result = "";
	DbOperation db = new DbOperation();
	ArrayList<AData> list;

	FileChooser(){
		setBackground(new Color(0, 64, 128));

		label = new JLabel();

		JPanel labelPanel = new JPanel();
		labelPanel.setForeground(new Color(255, 255, 255));
		labelPanel.setBackground(new Color(0, 64, 128));
		labelPanel.add(label);
		JButton btnFileSelect = new JButton("file select");
		btnFileSelect.setForeground(new Color(192, 192, 192));
		btnFileSelect.setFont(new Font("MS UI Gothic", Font.BOLD, 34));
		btnFileSelect.setSize(50, 50);
		btnFileSelect.addActionListener(this);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
				groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
										.addContainerGap()
										.addComponent(btnFileSelect, GroupLayout.DEFAULT_SIZE, 426, Short.MAX_VALUE))
								.addGroup(groupLayout.createSequentialGroup()
										.addGap(46)
										.addComponent(labelPanel, GroupLayout.PREFERRED_SIZE, 259, GroupLayout.PREFERRED_SIZE)))
						.addContainerGap())
				);
		groupLayout.setVerticalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addGap(18)
						.addComponent(labelPanel, GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
						.addGap(18)
						.addComponent(btnFileSelect, GroupLayout.PREFERRED_SIZE, 212, GroupLayout.PREFERRED_SIZE)
						.addContainerGap())
				);
		setLayout(groupLayout);
	}

	
	
	
	public void actionPerformed(ActionEvent e){
		JFileChooser filechooser = new JFileChooser();
		int selected = filechooser.showOpenDialog(this);
		if (selected == JFileChooser.APPROVE_OPTION){
			File file = filechooser.getSelectedFile();
			int fcl = file.getName().length();   //ファイル名の長さを表す
			String extension = file.getName().substring(fcl-3);   //拡張子を表す
			String fileName = file.getName();
			String fileNameYearMonth = file.getName().substring(0,6);
			String fileNameMonth = file.getName().substring(4,6);
			String fileNameEmployeeName = file.getName().substring(6, fcl-4);
			System.out.println(fcl);
			System.out.println(fileNameYearMonth);
			System.out.println(fileNameYearMonth.matches("[+-]?\\d*(\\.\\d+)?"));

			int errorCount1 = 0;  //CSVファイルかどうかを判別する変数
			int errorCount2 = 0;  //CSVファイル名が適切かどうかを判別する変数
			int errorCount3 = 0;  //ファイル内とDB内を判別する変数


			if(db.IsCSVTrue(extension)) {  //CSVファイルかどうかの判別				
			} else {
				System.out.println("読み込めないファイルでした");
				label.setForeground(Color.WHITE);
				label.setText("拡張子が「.csv」のファイルを選んでください");
				errorCount1++;
			}


			//ファイル名の判別する
			if(errorCount1==0) {
				int errorCountA=0;
				int errorCountB=0;
				if(db.IsFileNameTrue(fcl, fileNameYearMonth)) {
				} else {
					errorCountA++;
				}
				if(db.IsFileNameMonthTrue(fileNameMonth)) {
				} else {
					errorCountB++;
				}

				if(errorCountA!=0) {
					errorCount1++;
					label.setForeground(Color.WHITE);
					label.setText("ファイル名が適切ではありません");
				} else if(errorCountB!=0) {
					errorCount1++;
					label.setForeground(Color.WHITE);
					label.setText("月が適切ではありません");
				}
			}

			
			
			


			//ファイル内とDBとの比較
			if(errorCount1==0 && errorCount2==0) {
				Date date = Date.valueOf(file.getName().substring(0,4)+"-"+file.getName().substring(4,6)+"-01"); //年月日をDate型に
				CSV csv1 = new CSV(file.toString());
				String id = "";
				int errorCountA = 0;
				int errorCountB = 0;

				try {  //CSVファイルからidのみを取ってくる
					id = csv1.csvAttendance_data_id_only_Reader();
				} catch (Exception e3) {
					e3.printStackTrace();
				}

				try {  //ファイル内idとDB上idとの比較
					if(db.getNameIntoId(fileNameEmployeeName) == Integer.parseInt(id)) {
						System.out.println("id一致");
					} else {
						System.out.println("id不一致");
						errorCountA++;
					}
				} catch (NumberFormatException e1) {
					// TODO 自動生成された catch ブロック
					e1.printStackTrace();
				} catch (Exception e1) {
					// TODO 自動生成された catch ブロック
					e1.printStackTrace();
				}

				if(db.IsFileTrue(id, date)) {  //CSVファイル内のid,年月をDB上のid,年月と比較
					System.out.println("IsFileTrue:読み込み可");
				} else {
					System.out.println("IsFileTrue:読み込み不可");
					errorCountB++;
				}

				if(errorCountA!=0) {
					label.setForeground(Color.WHITE);
					label.setText("ファイル内のidと登録されているidが一致しません");
					errorCount3++;
				} else if(errorCountB!=0) {
					label.setForeground(Color.WHITE);
					label.setText("すでにDBに登録されているか、idが不適切です");
					errorCount3++;
				}

			} 


			if(errorCount1==0 && errorCount2==0 && errorCount3==0) {  //上記２つを満たした場合
				label.setText("ファイルを読み込みました");
				System.out.println("ファイルを読み込みました");
				label.setText(file.getName());
				String y = file.getName().substring(0,4);
				String m = file.getName().substring(4,6);
				String ym = y + "-" + m;
				filepath = file.toString();
				CSV csv = new CSV(filepath, ym);
				try {
					csv.csvAttendance_dataReader();
				} catch (Exception e1) {
					// TODO 自動生成された catch ブロック
					e1.printStackTrace();
				}
			}
		}
		
		
		
		
		
//１つ前のコード		
//		JFileChooser filechooser = new JFileChooser();
//		int selected = filechooser.showOpenDialog(this);
//		if (selected == JFileChooser.APPROVE_OPTION){
//			File file = filechooser.getSelectedFile();
//			int fcl = file.getName().length();   //ファイル名の長さを表す
//			String extension = file.getName().substring(fcl-3);   //拡張子を表す
//			if( extension.equals("csv")) {     //拡張子でファイルを判別
//				CSV csv1 = new CSV(file.toString());
//				String id = "";
//				try {
//					id = csv1.csvAttendance_data_id_only_Reader();
//				} catch (Exception e3) {
//					e3.printStackTrace();
//				}
//				if(db.IsId(id)) {//ファイル内idとemployee_data.employe_numberが一致
//					System.out.println("IsId : " + true);
//					list = db.dbGetEmployeeData();
//					int employeeNameCount = 0;    //
//					for(int i = 0; i < list.size(); i++) {  //employee_dataから名前を取ってくる
//						if(list.get(i).getName().equals(file.getName().substring(6,fcl-4))) {  //とってきた名前をファイル名と比べる
//							employeeNameCount++;
//							int count = 0;
//							for(String s : db.fileNameGet()) {  //DBからファイル名をとってくる
//								if(s.equals(file.getName())) {
//									System.out.println("そのファイルはすでに読み込まれています");
//									count++;
//								} 
//							}
//
//							if(count==0) {  
//								try {
//									db.file_nameInsert(file.getName());  //ファイル名をDBに登録
//								} catch (Exception e2) {
//									// TODO 自動生成された catch ブロック
//									e2.printStackTrace();
//								}
//								label.setText("ファイルを読み込みました");
//								System.out.println("ファイルを読み込みました");
//								label.setText(file.getName());
//								String y = file.getName().substring(0,4);
//								String m = file.getName().substring(4,6);
//								String ym = y + "-" + m;
//								filepath = file.toString();
//								CSV csv = new CSV(filepath, ym);
//								try {
//									csv.csvAttendance_dataReader();
//								} catch (Exception e1) {
//									// TODO 自動生成された catch ブロック
//									e1.printStackTrace();
//								}
//							}
//						} 
//					} 
//					if( employeeNameCount == 0) {  //DB内の名前とファイル名内の名前が一致しない場合
//						System.out.println("そのような社員は登録されていません");
//					} 
//				}else {//ファイル内idとemployee_data.employe_numberが不一致
//					System.out.println("idが一致しません");
//				}
//			} else { //拡張子が「csv」ではない場合
//				System.out.println("読み込めないファイルでした");
//				label.setForeground(Color.WHITE);
//				label.setText("拡張子が「.csv」のファイルを選んでください");
//			}
//
//
//		} 
	}

	
	
	




	public String getYm() {
		return ym;
	}

	public void setYm(String ym) {
		this.ym = ym;
	}

	public String getFilepath() {
		return filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}
}