
package kintai;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

public class frame extends JFrame {

	private JPanel contentPane;
	public static JPanel panel; //login
	private JPanel panel_1;  //ボタン
	public static CardLayout layout;  //カードレイアウト

	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame frame = new frame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public frame() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 500);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 64, 128));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		panel = new JPanel();
		layout = new CardLayout();
		panel.setLayout(layout);
		//↑cardlayout
		
		panel_1 = new buttonPanel();  //ボタン
		panel.add(new login());  //CardLayoutにloginを追加
		
		
		panel.add(new loginSuccessImage(), "loginSuccessImage");
		panel.add(new loginFailureImage(), "loginFailureImage");
		
		panel.add( new EmployeeRegistrationPanel(),"EmployeeRegistrationPanel");
		
		panel.add( new FileChooser(),"FileChooser");
		
		panel.add( new WorkScheduleDisplay(), "workSchedule");
		
		
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 1010, Short.MAX_VALUE)
				.addComponent(panel, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 524, Short.MAX_VALUE)
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 355, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
