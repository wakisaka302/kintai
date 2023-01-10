package filechooser;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class FileChooser extends JPanel implements ActionListener{

	JLabel label;
	private String ym;//池邊さんのDB登録クラスに渡す用
	private static String filepath;
	
	public static void main(String[] args){
		ileChooser frame = new ileChooser();

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 450, 300);
		frame.setTitle("タイトル");
		frame.setVisible(true);
	}

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
			label.setText(file.getName());
			System.out.println(file);
			System.out.println(file.getName());

			//////////////////////////////////////////////////////////////////////////////////////      
			String y = file.getName().substring(0,4);
			String m = file.getName().substring(4,6);
			String ym = y + "/" + m;
			
			filepath = file.toString();
			//////////////////////////////////////////////////////////////////////////////////////
		}
	}
}