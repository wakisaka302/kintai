package kintai;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;
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
		setBackground(new Color(0, 64, 128));

		//社員情報登録
		btnNewButton = new JButton("社員情報登録");
		btnNewButton.setFont(new Font("MS UI Gothic", Font.BOLD, 13));
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setBackground(new Color(0, 51, 102));
		btnNewButton.setSize(50, 50);
		btnNewButton.setEnabled(false);	
		btnNewButton.addActionListener(this);
		//		btnNewButton.setActionCommand("backgroundimage");

		btnNewButton.setActionCommand("EmployeeRegistrationPanel");


		//勤務表
		btnNewButton_1 = new JButton(" 勤務表表示 ");
		btnNewButton_1.setForeground(new Color(255, 255, 255));
		btnNewButton_1.setFont(new Font("MS UI Gothic", Font.BOLD, 14));
		btnNewButton_1.setBackground(new Color(0, 51, 102));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_1.setEnabled(false);
		btnNewButton_1.addActionListener(this);
		btnNewButton_1.setActionCommand("workSchedule");


		//CSVファイルチューザー
		btnNewButton_2 = new JButton(" CSV登録 ");
		btnNewButton_2.setForeground(new Color(255, 255, 255));
		btnNewButton_2.setFont(new Font("MS UI Gothic", Font.BOLD, 14));
		btnNewButton_2.setBackground(new Color(0, 51, 102));
		btnNewButton_2.setSize(50, 50);
		btnNewButton_2.setEnabled(false);
		btnNewButton_2.addActionListener(this);		
		btnNewButton_2.setActionCommand("FileChooser");


		//明細表示
		btnNewButton_3 = new JButton("給与明細表示");
		btnNewButton_3.setForeground(new Color(255, 255, 255));
		btnNewButton_3.setFont(new Font("MS UI Gothic", Font.BOLD, 14));
		btnNewButton_3.setBackground(new Color(0, 51, 102));
		btnNewButton_3.setEnabled(false);
		btnNewButton_3.addActionListener(this);		
		btnNewButton_3.setActionCommand("meisai");

		JButton btnNewButton_4 = new JButton("閉じる");
		btnNewButton_4.setIcon(new ImageIcon("C:\\Users\\3030747\\git\\kintai\\bin\\image\\×ボタン.png"));
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//「閉じる」でwindowを閉じる
				Component c = (Component)e.getSource();
				Window w = SwingUtilities.getWindowAncestor(c);
				w.dispose();
			}
		});



		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(btnNewButton_1, GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(btnNewButton_2, GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE)
						.addGap(6)
						.addComponent(btnNewButton_3, GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(btnNewButton_4, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
						.addGap(5))
				);
		groupLayout.setVerticalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addGap(5)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnNewButton_1, GroupLayout.DEFAULT_SIZE, 71, Short.MAX_VALUE)
								.addComponent(btnNewButton_2, GroupLayout.DEFAULT_SIZE, 71, Short.MAX_VALUE)
								.addComponent(btnNewButton_3, GroupLayout.DEFAULT_SIZE, 71, Short.MAX_VALUE)
								.addComponent(btnNewButton_4, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE))
						.addGap(232))
				);
		setLayout(groupLayout);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		if(cmd.equals("EmployeeRegistrationPanel")) {
			System.out.println("a");
			frame.panel.add(new EmployeeRegistrationPanel(), "EmployeeRegistrationPanel");
			System.out.println("b");
			//frame.panel.add(cmd, new EmployeeRegistrationPanel());
		}  else if(cmd.equals("FileChooser")) {
			frame.panel.add(new FileChooser(), "FileChooser");
		}  else if(cmd.equals("workSchedule")) {
			frame.panel.add(new WorkScheduleDisplay(), "workSchedule");
		} else if(cmd.equals("meisai")) {//追加項目
			try {
				frame.panel.add(new meisaiPanel(), "meisai");
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		} 
		frame.layout.show(frame.panel, cmd);
	}








}