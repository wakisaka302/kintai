package kintai;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
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
			if( extension.equals("csv")) {     //拡張子でファイルを判別
				CSV csv1 = new CSV(file.toString());
				String id = "";
				try {
					id = csv1.csvAttendance_data_id_only_Reader();
				} catch (Exception e3) {
					e3.printStackTrace();
				}
				if(db.IsId(id)) {//ファイル内idとemployee_data.employe_numberが一致
					System.out.println("IsId : " + true);
					list = db.dbGetEmployeeData();
					int employeeNameCount = 0;    //
					for(int i = 0; i < list.size(); i++) {  //employee_dataから名前を取ってくる
						if(list.get(i).getName().equals(file.getName().substring(6,fcl-4))) {  //とってきた名前をファイル名と比べる
							employeeNameCount++;
							int count = 0;
							for(String s : db.fileNameGet()) {  //DBからファイル名をとってくる
								if(s.equals(file.getName())) {
									System.out.println("そのファイルはすでに読み込まれています");
									count++;
								} 
							}

							if(count==0) {  
								try {
									db.file_nameInsert(file.getName());  //ファイル名をDBに登録
								} catch (Exception e2) {
									// TODO 自動生成された catch ブロック
									e2.printStackTrace();
								}
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
					} 
					if( employeeNameCount == 0) {  //DB内の名前とファイル名内の名前が一致しない場合
						System.out.println("そのような社員は登録されていません");
					} 
				}else {//ファイル内idとemployee_data.employe_numberが不一致
					System.out.println("idが一致しません");
				}
			} else { //拡張子が「csv」ではない場合
				System.out.println("読み込めないファイルでした");
				label.setForeground(Color.WHITE);
				label.setText("拡張子が「.csv」のファイルを選んでください");
			}


		} 
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