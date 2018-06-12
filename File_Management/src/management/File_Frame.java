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

   public File_Frame(Files files, JFrame frame, File cd, List file_list, List folder_list) {
      JFrame fr = new JFrame();
      f = fr;
      f.setSize(300, 300);
      f.setLocationRelativeTo(null);
      f.setVisible(true);

      JPanel p = new JPanel();
      p.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
      p.setOpaque(false);

      JButton b1 = new JButton("Open");
      JButton b2 = new JButton("Delete");
      JButton b3 = new JButton("Edit");
      JButton b4 = new JButton("Close");

      b1.setPreferredSize(new Dimension(100, 40));
      b2.setPreferredSize(new Dimension(100, 40));
      b3.setPreferredSize(new Dimension(100, 40));
      b4.setPreferredSize(new Dimension(100, 40));

      
      b1.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            f.dispose();
            JFrame f = new JFrame();
            frame_content = f;
            frame_content.setTitle("File Content");
            frame_content.setSize(400, 800);
            frame_content.setLocation(300, 300);
            // frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            close = new JButton("Close");
            taDisplay = new TextArea(5, 40);
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
      
      b3.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            f.dispose();
            JFrame f = new JFrame();
            frame_content = f;
            frame_content.setTitle("File Content");
            frame_content.setSize(400, 800);
            frame_content.setLocation(300, 300);
            // frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            save = new JButton("Save");
            close = new JButton("Close");
            taDisplay = new TextArea(5, 40);
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
                     File fi = new File(cd + "/" + files.file_name + ".txt");
                     fi.delete();
                     Writer osw, bw;
                     OutputStream fos;
                     fos = new FileOutputStream(cd + "/" + files.file_name + ".txt");
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

      b4.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            f.dispose();
         }
      });

      p.add(b1);
      p.add(b2);
      p.add(b3);
      p.add(b4);

      f.add(p);
   }
}