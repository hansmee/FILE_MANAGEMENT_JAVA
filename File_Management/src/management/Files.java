package management;

import java.util.*;
import java.util.List;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import management.File_Management.MyFrame;

public class Files {
	String file_name;
	File directory;
	File file;
	
	public JFrame frame = null;
	public Container cp = null;
	public JLabel lb = null;
	public JButton Enter;
	private TextArea taDisplay;

	Files(String name, File cd, JFrame frame_ori, List list1, List list2) {
		JFrame f = new JFrame();
		frame = f;
		frame.setTitle("File Content");
		frame.setSize(400, 400);
        frame.setLocationRelativeTo(null);
		Enter = new JButton("Enter");
		taDisplay = new TextArea(18, 40);
		
		Container cp = frame.getContentPane();
		cp.setLayout(new FlowLayout());
		cp.setBackground(Color.WHITE);
		lb = new JLabel("Please Write Content");
		
		frame.add(lb);
		cp.add(taDisplay);
		cp.add(Enter);
		
		frame.setVisible(true);
		file_name = name;
		directory = cd;

		Enter.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent evt) {
				try {
					// create file
					Writer osw, bw;
					OutputStream fos;
					fos = new FileOutputStream(cd + "/" + file_name + ".txt");
					osw = new OutputStreamWriter(fos);
					bw = new BufferedWriter(osw);
					String data;

					data = taDisplay.getText();
					osw.write(data);
					bw.close();

					//
					File fi = new File(cd + "/" + file_name + ".txt");
					file = fi;
					frame.dispose();
					frame_ori.dispose();
					MyFrame fff = new MyFrame(list1, list2, cd);
					Show_list s = new Show_list(list1, list2, cd, fff.frame);

				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
	}

}