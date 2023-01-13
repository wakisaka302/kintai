package kintai;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;

public class FileChooser extends JPanel implements ActionListener{
	JLabel label;
	private String ym;
	private String filepath;

	FileChooser(){
		setBackground(new Color(0, 64, 128));

		JPanel buttonPanel = new JPanel();

		label = new JLabel();

		JPanel labelPanel = new JPanel();
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
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(186)
									.addComponent(buttonPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(111)
									.addComponent(labelPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
							.addGap(217))
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addComponent(btnFileSelect, GroupLayout.DEFAULT_SIZE, 426, Short.MAX_VALUE)
							.addContainerGap())))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(5)
							.addComponent(buttonPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(18)
							.addComponent(labelPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
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
			
			
			int fcl = file.getName().length();
			String extension = file.getName().substring(fcl-3);
			System.out.println(extension);
			if( extension.equals("csv")) {
				System.out.println("ファイルを読み込みました");
				label.setText(file.getName());
				
				System.out.println(file);
				System.out.println(file.getName());
				
				String y = file.getName().substring(0,4);
				String m = file.getName().substring(4,6);
				String ym = y + "-" + m;
				
				System.out.println(ym);
				
				
				filepath = file.toString();
				System.out.println(filepath);
				

				CSV csv = new CSV(filepath, ym);
				try {
					csv.csvAttendance_dataReader();
				} catch (Exception e1) {
					// TODO 自動生成された catch ブロック
					e1.printStackTrace();
				}
			
				
			} else {
				System.out.println("読み込めないファイルでした");
				label.setText("拡張子が「.csv」のファイルを選んでください");
				
			}

			//////////////////////////////////////////////////////////////////////////////////////      
			
			
			
			//////////////////////////////////////////////////////////////////////////////////////
	
			
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