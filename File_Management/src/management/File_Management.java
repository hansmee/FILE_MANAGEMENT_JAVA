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

// main function
public class File_Management extends JFrame {
	// all files and folders in list
	public List<Files> file_list = new ArrayList<Files>();
	public List<Folder> folder_list = new ArrayList<Folder>();

	static public class MyFrame extends JFrame {
		List<Files> file_list = new ArrayList<Files>();
		List<Folder> folder_list = new ArrayList<Folder>();
		public JFrame frame = null;
		public Container cp = null;
		public JLabel lb = null;
		public JButton Make_file;
		public JButton Make_folder;

		// file
		public JFrame frame_file = null;
		public Container cp_file = null;
		public JLabel lb_file = null;
		public JButton Enter_file;
		private TextField tfInput_file;
		private String file_name;

		// wrong
		public JFrame frame_wrong = null;
		public JLabel lb_wrong = null;
		public JButton Enter_wrong;

		// folder
		public JFrame frame_folder = null;
		public Container cp_folder = null;
		public JLabel lb_folder = null;
		public JButton Enter_folder;
		private TextField tfInput_folder;
		private String folder_name;

		public MyFrame(List list1, List list2, File cd) {

			JFrame f = new JFrame();
			frame = f;
			frame.setTitle("File Management");
			frame.setSize(800, 800);
			frame.setLocationRelativeTo(null);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

			Make_file = new JButton("Make File", new ImageIcon("./img/file_.png"));
			Make_folder = new JButton("Make Folder", new ImageIcon("./img/folder_.png"));

			Make_file.setBorderPainted(false);
			Make_file.setContentAreaFilled(false);
			Make_file.setFocusPainted(false);
			Make_file.setHorizontalTextPosition(JButton.CENTER);
			Make_file.setVerticalTextPosition(JButton.BOTTOM);

			Make_folder.setBorderPainted(false);
			Make_folder.setContentAreaFilled(false);
			Make_folder.setFocusPainted(false);
			Make_folder.setHorizontalTextPosition(JButton.CENTER);
			Make_folder.setVerticalTextPosition(JButton.BOTTOM);

			Container cp = frame.getContentPane();
			cp.setLayout(new FlowLayout());
			cp.add(Make_file);
			cp.add(Make_folder);

			Make_file.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent evt) {
					JFrame f = new JFrame();
					frame_file = f;
					frame_file.setTitle("File Name");
					frame_file.setSize(300, 80);
					frame_file.setLocationRelativeTo(null);
					Enter_file = new JButton("Enter");
					tfInput_file = new TextField(10);

					Container cp_file = frame_file.getContentPane();
					cp_file.setLayout(new FlowLayout());
					cp_file.add(tfInput_file);
					cp_file.add(Enter_file);
					cp_file.setBackground(Color.WHITE);

					frame_file.setVisible(true);

					Enter_file.addActionListener(new ActionListener() {

						public void actionPerformed(ActionEvent evt) {
							file_name = tfInput_file.getText();
							int flag = 0;
							file_list = list1;
							for (int i = 0; i < file_list.size(); i++) {
								if (file_list.get(i).file_name.equals(file_name)) {
									flag++;
									JFrame f = new JFrame();
									frame_wrong = f;
									frame_wrong.setTitle("Error!");
									frame_wrong.setSize(300, 80);
									frame_wrong.setLocationRelativeTo(null);
									Enter_wrong = new JButton("Cancel");

									Container cp_wrong = frame_wrong.getContentPane();
									cp_wrong.setLayout(new FlowLayout());
									lb_wrong = new JLabel("You already have same name file!");
									frame_wrong.add(lb_wrong);
									cp_wrong.add(Enter_wrong);
									cp_wrong.setBackground(Color.WHITE);
									frame_wrong.setVisible(true);

									Enter_wrong.addActionListener(new ActionListener() {

										public void actionPerformed(ActionEvent evt) {
											frame_wrong.dispose();
										}
									});
								}
							}

							frame_file.dispose();
							if (flag == 0) {
								list1.add(new Files(file_name, cd, frame, list1, list2));
							}

						}
					});

				}
			});

			Make_folder.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent evt) {
					JFrame f = new JFrame();
					frame_folder = f;
					frame_folder.setTitle("Folder Name");
					frame_folder.setSize(300, 80);
					frame_folder.setLocationRelativeTo(null);
					Enter_folder = new JButton("Enter");
					tfInput_folder = new TextField(10);

					Container cp_file = frame_folder.getContentPane();
					cp_file.add(tfInput_folder);
					cp_file.setLayout(new FlowLayout());
					lb_folder = new JLabel("Folder Name");
					frame_folder.add(lb);
					cp_file.add(Enter_folder);
					cp_file.setBackground(Color.WHITE);
					frame_folder.setVisible(true);
					Enter_folder.addActionListener(new ActionListener() {

						public void actionPerformed(ActionEvent evt) {
							folder_name = tfInput_folder.getText();
							int flag = 0;
							folder_list = list2;
							for (int i = 0; i < folder_list.size(); i++) {
								if (folder_list.get(i).folder_name.equals(folder_name)) {
									flag++;
									JFrame f = new JFrame();
									frame_wrong = f;
									frame_wrong.setTitle("Error!");
									frame_wrong.setSize(300, 80);
									frame_wrong.setLocationRelativeTo(null);
									Enter_wrong = new JButton("Cancel");
									Container cp_wrong = frame_wrong.getContentPane();
									cp_wrong.setLayout(new FlowLayout());
									lb_wrong = new JLabel("You already have same name folder!");
									frame_wrong.add(lb_wrong);
									cp_wrong.add(Enter_wrong);
									cp_wrong.setBackground(Color.WHITE);
									frame_wrong.setVisible(true);
									Enter_wrong.addActionListener(new ActionListener() {

										public void actionPerformed(ActionEvent evt) {
											frame_wrong.dispose();
										}
									});
								}
							}
							frame_folder.dispose();
							if (flag == 0) {
								list2.add(new Folder(folder_name, cd));
								frame.dispose();
								MyFrame fff = new MyFrame(list1, list2, cd);
								Show_list s = new Show_list(list1, list2, cd, fff.frame);
							}
						}
					});
				}
			});

			lb = new JLabel("");
			frame.add(lb);

			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			cp.setBackground(Color.WHITE);

			frame.setVisible(true);
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String a, b;
		int n;
		Scanner scan = new Scanner(System.in);

		// all files and folders in list
		List<Files> file_list = new ArrayList<Files>();
		List<Folder> folder_list = new ArrayList<Folder>();

		// current directory
		File cd = new File("./dir/");

		MyFrame fr = new MyFrame(file_list, folder_list, cd);
		Show_list s = new Show_list(file_list, folder_list, cd, fr.frame);

	}

}