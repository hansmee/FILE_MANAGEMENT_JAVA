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

public class Show_list extends JFrame {

	List<Files> file_list = new ArrayList<Files>();
	List<Folder> folder_list = new ArrayList<Folder>();

	Show_list(List list1, List list2, File cd, JFrame frame) {

		try {
			Container cp = frame.getContentPane();
			cp.setBackground(Color.LIGHT_GRAY);
			cp.setLayout(new FlowLayout());

			// ******LABEL IN PANEL 3********//
			JPanel p3 = new JPanel();
			p3.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
			p3.setPreferredSize(new Dimension(800, 30));
			p3.setOpaque(false);

			String label = cd.toString();
			int a = 0;
			for (int i = label.length() - 1; i >= 0; i--) {
				String bb = label.substring(i, i+1);
				if (bb.equals(label.substring(1, 2))) {
					a = i;
					break;
				}
			}
			
			if (a == 1)
				label = "FILE MANAGEMENT";
			else
				label = label.substring(a + 1);

			JLabel lb = new JLabel(label);
			lb.setFont(new Font("", Font.CENTER_BASELINE, 20));
			lb.setForeground(Color.BLACK);
			p3.add(lb, BorderLayout.CENTER);

			// ******PANEL 1********//
			JPanel p1 = new RoundedPanel(15, Color.WHITE);
			p1.setPreferredSize(new Dimension(750, 300));
			p1.setOpaque(false);

			// ******PANEL 2********//
			JPanel p2 = new RoundedPanel(15, Color.WHITE);
			p2.setPreferredSize(new Dimension(750, 300));
			p2.setOpaque(false);

			// FILE_LIST
			if (list1.size() != 0) {
				file_list = list1;
				for (int i = 0; i < file_list.size(); i++) {
					if (file_list.get(i).directory.equals(cd)) {
						JButton btn = new JButton(file_list.get(i).file_name.concat(".txt"),
								new ImageIcon("./img/file.png"));

						btn.setName(file_list.get(i).file_name);
						btn.setBorderPainted(false);
						btn.setContentAreaFilled(false);
						btn.setFocusPainted(false);
						btn.setHorizontalTextPosition(JButton.CENTER);
						btn.setVerticalTextPosition(JButton.BOTTOM);

						btn.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								Files files = null;
								for (int i = 0; i < file_list.size(); i++) {
									if (file_list.get(i).file_name.equals(btn.getName())) {
										files = file_list.get(i);
									}
								}
								File_Frame nf = new File_Frame(files, frame, cd, file_list, folder_list);
								nf.f.setTitle(btn.getText());
							}
						});

						p1.add(btn, BorderLayout.CENTER);
					}
				}
			}

			// FOLDER_LIST
			if (list2.size() != 0) {
				folder_list = list2;
				for (int i = 0; i < folder_list.size(); i++) {
					if (folder_list.get(i).directory.equals(cd)) {
						String image = "";
						if(folder_list.get(i).password.equals(""))
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
								Folder folder = null;
								for (int i = 0; i < folder_list.size(); i++) {
									if (folder_list.get(i).folder_name.equals(btn.getName())) {
										folder = folder_list.get(i);
									}
								}
								Folder_Frame nf = new Folder_Frame(folder, frame, cd, file_list, folder_list);
								nf.f.setTitle(btn.getText());
							}
						});

						p2.add(btn, BorderLayout.CENTER);
					}
				}
			}

			// ******BACK BUTTON*******//
			JButton back = new JButton("Back", new ImageIcon("./img/back.png"));
			back.setPreferredSize(new Dimension(100, 80));
			back.setBorderPainted(false);
			back.setContentAreaFilled(false);
			back.setFocusPainted(false);
			back.setHorizontalTextPosition(JButton.CENTER);
			back.setVerticalTextPosition(JButton.BOTTOM);

			back.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					File nd = null;
					for (int i = 0; i < folder_list.size(); i++) {
						if (folder_list.get(i).folder.equals(cd)) {
							nd = folder_list.get(i).directory;
						}
					}
					if (nd != null) {
						frame.dispose();
						MyFrame fff = new MyFrame(file_list, folder_list, nd);
						Show_list s = new Show_list(file_list, folder_list, nd, fff.frame);
					}
				}
			});

			// *****ADD***** //

			cp.add(back);
			cp.add(p1);
			cp.add(p2);
			cp.add(p3);

			frame.setVisible(true);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// *****PANEL******//
	class RoundedPanel extends JPanel {
		private Color backgroundColor;
		private int cornerRadius = 15;

		public RoundedPanel(int radius, Color bgColor) {
			super();
			cornerRadius = radius;
			backgroundColor = bgColor;
		}

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			Dimension arcs = new Dimension(cornerRadius, cornerRadius);
			int width = getWidth();
			int height = getHeight();
			Graphics2D graphics = (Graphics2D) g;
			graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

			// Draws the rounded panel with borders.
			if (backgroundColor != null) {
				graphics.setColor(backgroundColor);
			} else {
				graphics.setColor(getBackground());
			}
			graphics.fillRoundRect(0, 0, width - 1, height - 1, arcs.width, arcs.height); // paint background
			graphics.drawRoundRect(0, 0, width - 1, height - 1, arcs.width, arcs.height); // paint border
		}
	}
}
