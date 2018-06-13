package management;

import java.util.*;
import java.util.List;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.awt.*;
import java.awt.event.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import management.File_Management.MyFrame;

public class Folder_list extends JFrame {
	public JFrame f = null;
	// enter password
	public JFrame frame_password = null;
	public JLabel lb_password = null;
	public JButton Enter_password;
	private TextField tfInput_password;
	private String password;

	// edit password
	public JFrame frame_otherpw = null;
	public JLabel lb_otherpw = null;
	public JButton Change;
	public JButton No_Change;

	// if wrong
	public JFrame frame_wrongpw = null;
	public JLabel lb_wrongpw = null;
	public JButton wrongpw;
	
	public Folder folder = null;
	public File nd = null;

	List<Files> file_list = new ArrayList<Files>();
	List<Folder> folder_list = new ArrayList<Folder>();

	Folder_list(List list1, List list2, File cd, JFrame frame, Files file){
		file_list = list1;
		
		JFrame fr = new JFrame();
		f = fr;
		f.setTitle("Please choose a folder to put in");
		f.setSize(500, 500);
		f.setLocationRelativeTo(null);
		f.setVisible(true);
		
		Container cp = f.getContentPane();
		cp.setBackground(Color.WHITE);

		JPanel p = new JPanel();
		p.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
		p.setOpaque(false);

		// FOLDER_LIST
		if (list2.size() != 0) {
			folder_list = list2;
			for (int i = 0; i < folder_list.size(); i++) {
				if (folder_list.get(i).directory.equals(cd)) {
					String image = "";
					if (folder_list.get(i).password.equals(""))
						image = "./img/folder (9).png";
					else
						image = "./img/folder (8).png";

					JButton btn = new JButton(folder_list.get(i).folder_name, new ImageIcon(image));

					btn.setName(folder_list.get(i).folder_name);
					btn.setBorderPainted(false);
					btn.setContentAreaFilled(false);
					btn.setFocusPainted(false);
					btn.setHorizontalTextPosition(JButton.CENTER);
					btn.setVerticalTextPosition(JButton.BOTTOM);

					btn.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							for (int i = 0; i < folder_list.size(); i++) {
								if (folder_list.get(i).folder_name.equals(btn.getName())) {
									nd = folder_list.get(i).folder;
									folder = folder_list.get(i);
								}
							}
							
							if (folder.password == "") {
								if(nd != null) {
									for (int j = 0; j < file_list.size(); j++) {
										final int fixed = j;
										if (file_list.get(fixed).equals(file)) {
											file_list.get(fixed).directory = nd;
											frame.dispose();
											MyFrame fff = new MyFrame(file_list, folder_list, cd);
											Show_list s = new Show_list(file_list, folder_list, cd, fff.frame);
										}
									}
								}
							} else {
								JFrame f_password = new JFrame();
								frame_password = f_password;
								frame_password.setTitle("Enter Password");
								frame_password.setSize(300, 80);
								frame_password.setLocationRelativeTo(null);
								Enter_password = new JButton("Enter");
								tfInput_password = new TextField(10);

								Container cp_password = frame_password.getContentPane();
								cp_password.setLayout(new FlowLayout());
								lb_password = new JLabel("Enter Password");
								frame_password.add(lb_password);
								cp_password.add(tfInput_password);
								cp_password.add(Enter_password);
								cp_password.setBackground(Color.WHITE);
								frame_password.setVisible(true);
								
								Enter_password.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent evt) {
										if (folder.password.equals(tfInput_password.getText())) {
											frame_password.dispose();
											if(nd != null) {
												for (int j = 0; j < file_list.size(); j++) {
													final int fixed = j;
													if (file_list.get(fixed).equals(file)) {
														file_list.get(fixed).directory = nd;
														frame.dispose();
														MyFrame fff = new MyFrame(file_list, folder_list, cd);
														Show_list s = new Show_list(file_list, folder_list, cd, fff.frame);
													}
												}
											}
										} else {
											JFrame f_wrongpw = new JFrame();
											frame_wrongpw = f_wrongpw;
											frame_wrongpw.setTitle("ERROR!");
											frame_wrongpw.setSize(300, 80);
											frame_wrongpw.setLocationRelativeTo(null);
											wrongpw = new JButton("Okay");

											Container cp_wrongpw = frame_wrongpw.getContentPane();
											cp_wrongpw.setLayout(new FlowLayout());
											lb_wrongpw = new JLabel("Entered password is wrong!");
											frame_wrongpw.add(lb_wrongpw);
											cp_wrongpw.add(wrongpw);

											cp_wrongpw.setBackground(Color.WHITE);
											frame_wrongpw.setVisible(true);
											wrongpw.addActionListener(new ActionListener() {
												public void actionPerformed(ActionEvent evt) {
													frame.dispose();
													MyFrame fff = new MyFrame(file_list, folder_list, cd);
													Show_list s = new Show_list(file_list, folder_list, cd, fff.frame);
													frame_password.dispose();
													frame_wrongpw.dispose();
												}
											});
										}
									}
									});
								}
							f.dispose();
							}
						});
							
					p.add(btn, BorderLayout.CENTER);
				}
				
			}
			cp.add(p);
		}
	}
}
