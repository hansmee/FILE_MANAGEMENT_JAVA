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

public class File_Frame extends JFrame {
	public JFrame f = null;
	public JFrame frame_content = null;
	public JButton save;
	public JButton close;
	private TextArea taDisplay;

	// change name
	public JFrame change_name = null;
	public JLabel lb_name = null;
	public JButton Enter_name;
	public TextField tfInput_name;
	public String new_name;
	public File new_path = null;

	// wrong
	public JFrame frame_wrong = null;
	public JLabel lb_wrong = null;
	public JButton Enter_wrong;

	// move
	public JFrame frame_move = null;
	public JLabel lb_move = null;
	public JPanel p_move = null;

	List<Files> list = new ArrayList<Files>();

	public File_Frame(Files files, JFrame frame, File cd, List file_list, List folder_list) {
		JFrame fr = new JFrame();
		f = fr;
		f.setSize(300, 400);
		f.setLocationRelativeTo(null);
		f.setVisible(true);

		JPanel p = new JPanel();
		p.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
		p.setOpaque(false);

		JButton b1 = new JButton("Open");
		JButton b2 = new JButton("Delete");
		JButton b3 = new JButton("Edit");
		JButton b4 = new JButton("Close");
		JButton b5 = new JButton("Change Name");
		JButton b6 = new JButton("Move");

		b1.setPreferredSize(new Dimension(200, 40));
		b2.setPreferredSize(new Dimension(200, 40));
		b3.setPreferredSize(new Dimension(200, 40));
		b4.setPreferredSize(new Dimension(200, 40));
		b5.setPreferredSize(new Dimension(200, 40));
		b6.setPreferredSize(new Dimension(200, 40));

		// open
		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				f.dispose();
				JFrame f = new JFrame();
				frame_content = f;
				frame_content.setTitle("File Content");
				frame_content.setSize(400, 400);
				frame_content.setLocationRelativeTo(null);
				close = new JButton("Close");
				taDisplay = new TextArea(18, 40);
				Container cp = frame_content.getContentPane();
				cp.add(taDisplay);
				cp.setLayout(new FlowLayout());
				cp.add(close);
				cp.setBackground(Color.WHITE);
				frame_content.setVisible(true);

				try {
					Scanner scan = new Scanner(files.file);
					String line;
					while (scan.hasNextLine()) {
						taDisplay.append(scan.nextLine());
						taDisplay.append("\n");
					}
					taDisplay.setEditable(false);
				} catch (FileNotFoundException ex) {
					ex.printStackTrace();
				} catch (IOException ex) {
					ex.printStackTrace();
				}
				close.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {

						// create file
						frame_content.dispose();

					}
				});

			}
		});

		// delete
		b2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < file_list.size(); i++) {
					if (file_list.get(i).equals(files)) {
						files.file.delete();
						file_list.remove(i);
					}
				}
				f.dispose();
				frame.dispose();
				MyFrame fff = new MyFrame(file_list, folder_list, cd);
				Show_list s = new Show_list(file_list, folder_list, cd, fff.frame);
			}
		});

		// edit
		b3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				f.dispose();
				JFrame f = new JFrame();
				frame_content = f;
				frame_content.setTitle("File Content");
				frame_content.setSize(400, 400);
				frame_content.setLocationRelativeTo(null);
				save = new JButton("Save");
				close = new JButton("Close");
				taDisplay = new TextArea(18, 40);
				Container cp = frame_content.getContentPane();
				cp.add(taDisplay);
				cp.setLayout(new FlowLayout());
				cp.add(save);
				cp.add(close);
				cp.setBackground(Color.WHITE);
				frame_content.setVisible(true);

				try {
					Scanner scan = new Scanner(files.file);
					String line;
					while (scan.hasNextLine()) {
						taDisplay.append(scan.nextLine());
						taDisplay.append("\n");
					}
				} catch (FileNotFoundException ex) {
					ex.printStackTrace();
				} catch (IOException ex) {
					ex.printStackTrace();
				}

				save.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							// create file
							files.file.delete();
							Writer osw, bw;
							OutputStream fos;
							fos = new FileOutputStream(files.file);
							osw = new OutputStreamWriter(fos);
							bw = new BufferedWriter(osw);
							String data;
							data = taDisplay.getText();
							osw.write(data);
							bw.close();
							//

						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}
				});
				close.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {

						// create file
						frame_content.dispose();

					}
				});

			}
		});

		// close
		b4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				f.dispose();
			}
		});

		// change name
		b5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				f.dispose();
				JFrame c_name = new JFrame();
				change_name = c_name;
				change_name.setTitle("Change File Name");
				change_name.setSize(300, 80);
				change_name.setLocationRelativeTo(null);

				Enter_name = new JButton("Enter");
				tfInput_name = new TextField(10);

				Container cp_file = change_name.getContentPane();
				cp_file.setLayout(new FlowLayout());
				lb_name = new JLabel("File Name");
				change_name.add(lb_name);
				cp_file.add(tfInput_name);
				cp_file.add(Enter_name);
				cp_file.setBackground(Color.WHITE);
				change_name.setVisible(true);

				Enter_name.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent evt) {
						new_name = tfInput_name.getText();
						int flag = 0;
						list = file_list;
						for (int i = 0; i < list.size(); i++) {
							if (list.get(i).file_name.equals(new_name)) {
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
						change_name.dispose();
						if (flag == 0) {
							new_path = new File(cd + "/" + new_name + ".txt");
							for (int i = 0; i < list.size(); i++) {

								if (list.get(i).equals(files)) {
									try {
										Scanner scan = new Scanner(list.get(i).file);
										String line;
										Writer osw, bw;
										OutputStream fos;
										fos = new FileOutputStream(cd + "/" + new_name + ".txt");
										osw = new OutputStreamWriter(fos);
										bw = new BufferedWriter(osw);
										while (scan.hasNextLine()) {
											osw.write(scan.nextLine());
											osw.write("\n");
											bw.close();
										}
									} catch (FileNotFoundException ex) {
										ex.printStackTrace();
									} catch (IOException ex) {
										ex.printStackTrace();
									}
									list.get(i).file.delete();
									list.get(i).file = new_path;
									list.get(i).file_name = new_name;
								}
							}

							frame.dispose();
							MyFrame fff = new MyFrame(list, folder_list, cd);
							Show_list s = new Show_list(list, folder_list, cd, fff.frame);
						}
					}
				});

			}
		});

		// move
		b6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Folder_list fl = new Folder_list(file_list, folder_list, cd, frame, files);
				f.dispose();
			}
		});

		p.add(b1);
		p.add(b2);
		p.add(b3);
		p.add(b5);
		p.add(b6);
		p.add(b4);

		f.add(p);
	}
}