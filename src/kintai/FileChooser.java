package kintai;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class FileChooser extends JPanel implements ActionListener{

	JLabel label;
	private String ym;
	private String filepath;

	FileChooser(){
		JButton button = new JButton("file select");
		button.addActionListener(this);

		JPanel buttonPanel = new JPanel();
		buttonPanel.add(button);

		label = new JLabel();

		JPanel labelPanel = new JPanel();
		labelPanel.add(label);

		add(labelPanel, BorderLayout.CENTER);
		add(buttonPanel, BorderLayout.PAGE_END);
	}

	public void actionPerformed(ActionEvent e){
		JFileChooser filechooser = new JFileChooser();

		int selected = filechooser.showOpenDialog(this);
		if (selected == JFileChooser.APPROVE_OPTION){
			
			File file = filechooser.getSelectedFile();
			
			
			int fcl = file.getName().length();
			String extension = file.getName().substring(fcl-3);
			System.out.println(extension);
			if(extension.equals("txt") || extension.equals("csv")) {
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
				label.setText("拡張子が「.txt」のファイルを選んでください");
				
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