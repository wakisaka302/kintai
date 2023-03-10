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
import javax.swing.LayoutStyle.ComponentPlacement;

public class FileChooser extends JPanel implements ActionListener{
	JLabel lbldbcsv;
	private String ym;
	private String filepath;
	private String result;
	DbOperation db = new DbOperation();
	ArrayList<AData> list;

	FileChooser(){
		setBackground(new Color(0, 64, 128));

		lbldbcsv = new JLabel();
		lbldbcsv.setFont(new Font("MS UI Gothic", Font.BOLD, 13));
		lbldbcsv.setForeground(new Color(192, 192, 192));
		lbldbcsv.setText("※DBに社員を登録します。CSVファイルを選択して下さい。");

		JPanel labelPanel = new JPanel();
		labelPanel.setForeground(new Color(255, 255, 255));
		labelPanel.setBackground(new Color(0, 64, 128));
		labelPanel.add(lbldbcsv);
		JButton btnFileSelect = new JButton("file select");
		btnFileSelect.setForeground(new Color(192, 192, 192));
		btnFileSelect.setFont(new Font("MS UI Gothic", Font.BOLD, 34));
		btnFileSelect.setSize(50, 50);
		btnFileSelect.addActionListener(this);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btnFileSelect, GroupLayout.DEFAULT_SIZE, 426, Short.MAX_VALUE)
						.addComponent(labelPanel, GroupLayout.PREFERRED_SIZE, 383, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(30)
					.addComponent(labelPanel, GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
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
			//			System.out.println(fcl);
			//			System.out.println(fileNameYearMonth);
			//			System.out.println("fileNameMonth:"+fileNameMonth);
			//			System.out.println(fileNameYearMonth.matches("[+-]?\\d*(\\.\\d+)?"));

			int errorCount1 = 0;  //CSVファイルかどうかを判別する変数
			int errorCount2 = 0;  //CSVファイル名が適切かどうかを判別する変数
			int errorCount3 = 0;  //ファイル内とDB内を判別する変数




			//CSVファイルかどうかの判別	
			if(db.IsCSVTrue(extension)) {  			
			} else {
				System.out.println("読み込めないファイルでした");
				lbldbcsv.setForeground(Color.PINK);
				lbldbcsv.setText("登録時エラー：拡張子が「.csv」のファイルを選んでください。");
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
					lbldbcsv.setForeground(Color.PINK);
					lbldbcsv.setText("登録時エラー：ファイル名が適切ではありません");
				} else if(errorCountB!=0) {
					errorCount1++;
					lbldbcsv.setForeground(Color.PINK);
					lbldbcsv.setText("登録時エラー：月が適切ではありません");
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

				//ファイル名内の名前がDB(employee_data)に登録済みかどうか
				if(db.IsNameIntoId(fileNameEmployeeName).equals("")) {//未登録
					errorCount3++;
					lbldbcsv.setForeground(Color.PINK);
					lbldbcsv.setText("登録時エラー：指定の社員名は登録されていません。");
				} else {//登録済み
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


					//エラーの種類を表示
					if(errorCountA!=0) {
						lbldbcsv.setForeground(Color.PINK);
						lbldbcsv.setText("登録時エラー：ファイル内のIDと、登録されているIDが一致しません。");
						errorCount3++;
					} else if(errorCountB!=0) {
						lbldbcsv.setForeground(Color.PINK);
						lbldbcsv.setText("登録時エラー：すでにDBに登録されているか、IDが不適切です。");
						errorCount3++;
					}
				}
			} 


			//上記２つを満たした場合(errorCountがともに0)
			if(errorCount1==0 && errorCount2==0 && errorCount3==0) {  
				lbldbcsv.setText(file.getName()+"　ファイル読み込み中");
				String y = file.getName().substring(0,4);
				String m = file.getName().substring(4,6);
				String ym = y + "-" + m;
				filepath = file.toString();
				CSV csv = new CSV(filepath, ym);

				try {
					if(csv.csvAttendance_data_error_finder()) {
						csv.csvAttendance_dataReader();
						lbldbcsv.setForeground(Color.WHITE);
						lbldbcsv.setText("登録成功：「 " +  file.getName()+ "」　をDBに追加しました。");
						System.out.println("DBに追加しました");
					} else {
						lbldbcsv.setForeground(Color.PINK);
						lbldbcsv.setText(file.getName()+"　エラー箇所があり、DBに追加しませんでした。");
						System.out.println("エラー箇所があり、DBに追加しませんでした。");
					}
				} catch (Exception e1) {
					// TODO 自動生成された catch ブロック
					e1.printStackTrace();
				}
			}
		}
	}






	//1/23更新前	
	//	public void actionPerformed(ActionEvent e){
	//		JFileChooser filechooser = new JFileChooser();
	//		int selected = filechooser.showOpenDialog(this);
	//		if (selected == JFileChooser.APPROVE_OPTION){
	//			File file = filechooser.getSelectedFile();
	//			int fcl = file.getName().length();   //ファイル名の長さを表す
	//			String extension = file.getName().substring(fcl-3);   //拡張子を表す
	//			String fileName = file.getName();
	//			String fileNameYearMonth = file.getName().substring(0,6);
	//			String fileNameMonth = file.getName().substring(4,6);
	//			String fileNameEmployeeName = file.getName().substring(6, fcl-4);
	//			System.out.println(fcl);
	//			System.out.println(fileNameYearMonth);
	//			System.out.println(fileNameYearMonth.matches("[+-]?\\d*(\\.\\d+)?"));
	//
	//			int errorCount1 = 0;  //CSVファイルかどうかを判別する変数
	//			int errorCount2 = 0;  //CSVファイル名が適切かどうかを判別する変数
	//			int errorCount3 = 0;  //ファイル内とDB内を判別する変数
	//
	//
	//			if(db.IsCSVTrue(extension)) {  //CSVファイルかどうかの判別				
	//			} else {
	//				System.out.println("読み込めないファイルでした");
	//				label.setForeground(Color.WHITE);
	//				label.setText("読み込み時エラー：拡張子が「.csv」のファイルを選んでください。");
	//				label.setForeground(Color.PINK);
	//				errorCount1++;
	//			}
	//
	//
	//			//ファイル名の判別する
	//			if(errorCount1==0) {
	//				int errorCountA=0;
	//				int errorCountB=0;
	//				if(db.IsFileNameTrue(fcl, fileNameYearMonth)) {
	//				} else {
	//					errorCountA++;
	//				}
	//				if(db.IsFileNameMonthTrue(fileNameMonth)) {
	//				} else {
	//					errorCountB++;
	//				}
	//
	//				if(errorCountA!=0) {
	//					errorCount1++;
	//					label.setForeground(Color.WHITE);
	//					label.setText("ファイル名が適切ではありません");
	//				} else if(errorCountB!=0) {
	//					errorCount1++;
	//					label.setForeground(Color.WHITE);
	//					label.setText("月が適切ではありません");
	//				}
	//			}
	//
	//			
	//			
	//			
	//
	//
	//			//ファイル内とDBとの比較
	//			if(errorCount1==0 && errorCount2==0) {
	//				Date date = Date.valueOf(file.getName().substring(0,4)+"-"+file.getName().substring(4,6)+"-01"); //年月日をDate型に
	//				CSV csv1 = new CSV(file.toString());
	//				String id = "";
	//				int errorCountA = 0;
	//				int errorCountB = 0;
	//
	//				try {  //CSVファイルからidのみを取ってくる
	//					id = csv1.csvAttendance_data_id_only_Reader();
	//				} catch (Exception e3) {
	//					e3.printStackTrace();
	//				}
	//
	//				try {  //ファイル内idとDB上idとの比較
	//					if(db.getNameIntoId(fileNameEmployeeName) == Integer.parseInt(id)) {
	//						System.out.println("id一致");
	//					} else {
	//						System.out.println("id不一致");
	//						errorCountA++;
	//					}
	//				} catch (NumberFormatException e1) {
	//					// TODO 自動生成された catch ブロック
	//					e1.printStackTrace();
	//				} catch (Exception e1) {
	//					// TODO 自動生成された catch ブロック
	//					e1.printStackTrace();
	//				}
	//
	//				if(db.IsFileTrue(id, date)) {  //CSVファイル内のid,年月をDB上のid,年月と比較
	//					System.out.println("IsFileTrue:読み込み可");
	//				} else {
	//					System.out.println("IsFileTrue:読み込み不可");
	//					errorCountB++;
	//				}
	//
	//				if(errorCountA!=0) {
	//					label.setForeground(Color.WHITE);
	//					label.setText("ファイル内のidと登録されているidが一致しません");
	//					errorCount3++;
	//				} else if(errorCountB!=0) {
	//					label.setForeground(Color.WHITE);
	//					label.setText("すでにDBに登録されているか、idが不適切です");
	//					errorCount3++;
	//				}
	//
	//			} 
	//
	//
	//			if(errorCount1==0 && errorCount2==0 && errorCount3==0) {  //上記２つを満たした場合
	//				label.setText("ファイルを読み込みました");
	//				System.out.println("ファイルを読み込みました");
	//				label.setText(file.getName());
	//				String y = file.getName().substring(0,4);
	//				String m = file.getName().substring(4,6);
	//				String ym = y + "-" + m;
	//				filepath = file.toString();
	//				CSV csv = new CSV(filepath, ym);
	//				try {
	//					csv.csvAttendance_dataReader();
	//				} catch (Exception e1) {
	//					// TODO 自動生成された catch ブロック
	//					e1.printStackTrace();
	//				}
	//			}
	//		}
	//		
	//		



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