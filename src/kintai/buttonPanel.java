package kintai;

import java.awt.Component;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

//import create3.backgroundimage;

public class buttonPanel extends JPanel implements ActionListener{

	static JButton btnNewButton;
	static JButton btnNewButton_1;
	static JButton btnNewButton_2;
	static JButton btnNewButton_3;
	
	/**
	 * Create the panel.
	 */
	public buttonPanel() {
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		
		//社員情報登録
		btnNewButton = new JButton("社員情報登録");
		btnNewButton.setEnabled(false);	
		btnNewButton.addActionListener(this);
//		btnNewButton.setActionCommand("backgroundimage");
		
		btnNewButton.setActionCommand("EmployeeRegistrationPanel");
		
		
//		btnNewButton.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				
//			}
//		});
		add(btnNewButton);
		
		
		//勤務表
		btnNewButton_1 = new JButton("勤務表");
		btnNewButton_1.setEnabled(false);
		add(btnNewButton_1);
		
		
		//CSVファイルチューザー
		btnNewButton_2 = new JButton("CSV読み取り");
		btnNewButton_2.setEnabled(false);
//		btnNewButton_2.addActionListener(this);		
//		btnNewButton_2.setActionCommand("JFileChooserTest1");
		add(btnNewButton_2);

		btnNewButton_3 = new JButton("明細表示");
		btnNewButton_3.setEnabled(false);
		add(btnNewButton_3);

		JButton btnNewButton_4 = new JButton("閉じる");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//「閉じる」でwindowを閉じる
				Component c = (Component)e.getSource();
				Window w = SwingUtilities.getWindowAncestor(c);
				w.dispose();
				
				
				
			}
		});
		//btnNewButton_4.setEnabled(login.a);
		add(btnNewButton_4);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		
		
//		if(cmd.equals("backgroundimage")) {
//			frame.panel.add(new backgroundimage(), "backgroundimage");
//			
//		}
		
//		
		if(cmd.equals("EmployeeRegistrationPanel")) {
			System.out.println("a");
			frame.panel.add(new EmployeeRegistrationPanel(), "EmployeeRegistrationPanel");
			System.out.println("b");
			//frame.panel.add(cmd, new EmployeeRegistrationPanel());
		} //  else if(cmd.equals("JFileChooserTest1")) {
		  //	frame.panel.add(new JFileChooserTest1(), "JFileChooserTest1");
//		}
		
		
	//	System.out.println("c");
		frame.layout.show(frame.panel, cmd);
	//	System.out.println("d");
		
	}
	
	
	
	
	
	
	

}
