package management;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import management.File_Management.MyFrame;
import management.Folder;

public class Folder_Frame extends JFrame {
   List<Files> file_list = new ArrayList<Files>();
   List<Folder> folder_list = new ArrayList<Folder>();

   public JFrame f = null;
   private Container c = null;

   // enter password
   private JFrame frame_password = null;
   private JLabel lb_password = null;
   private JButton Enter_password;
   private TextField tfInput_password;
   private String password;

   // edit password
   private JFrame frame_otherpw = null;
   private JLabel lb_otherpw = null;
   private JButton Change;
   private JButton No_Change;

   // if wrong
   private JFrame frame_wrongpw = null;
   private JLabel lb_wrongpw = null;
   private JButton wrongpw;

   private JFrame frame_wrong = null;
   private JLabel lb_wrong = null;
   private JButton Enter_wrong;
   
   // change name
   private JFrame change_name = null;
   private JLabel lb_name = null;
   private JButton Enter_name;
   private TextField tfInput_name;
   private String new_name;
   private File new_path = null;
   private int i;

   public Folder_Frame(Folder folder, JFrame frame, File cd, List list1, List list2) {
      file_list = list1;
      folder_list = list2;

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
      JButton b3 = new JButton("Password");
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
            for (int i = 0; i < folder_list.size(); i++) {
               if (folder_list.get(i).equals(folder)) {
                  if (folder.password == "") {
                     File nd = new File(cd.toString() + "/" + folder.folder_name + "/");
                     frame.dispose();
                     MyFrame fff = new MyFrame(file_list, folder_list, nd);
                     Show_list s = new Show_list(file_list, folder_list, nd, fff.frame);
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
                              File nd = new File(cd.toString() + "/" + folder.folder_name + "/");
                              frame.dispose();
                              MyFrame fff = new MyFrame(file_list, folder_list, nd);
                              Show_list s = new Show_list(file_list, folder_list, nd, fff.frame);
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
               }
            }
            f.dispose();
         }
      });

      // delete
      b2.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            for (int i = 0; i < folder_list.size(); i++) {
               if (folder_list.get(i).equals(folder)) {
                  final int i_delete = i;
                  if (folder.password == "") {
                     deleteDirectory(folder.folder);
                     folder_list.remove(i_delete);

                     frame.dispose();
                     MyFrame fff = new MyFrame(file_list, folder_list, cd);
                     Show_list s = new Show_list(file_list, folder_list, cd, fff.frame);
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
                              deleteDirectory(folder.folder);
                              folder_list.remove(i_delete);

                              frame.dispose();
                              MyFrame fff = new MyFrame(file_list, folder_list, cd);
                              Show_list s = new Show_list(file_list, folder_list, cd, fff.frame);
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

               }
            }
            f.dispose();
         }
      });

      // password
      b3.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            if (folder.password == "") {
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
                     folder.password = tfInput_password.getText();
                     frame_password.dispose();

                     frame.dispose();
                     MyFrame fff = new MyFrame(file_list, folder_list, cd);
                     Show_list s = new Show_list(file_list, folder_list, cd, fff.frame);
                  }
               });
            } else {
               JFrame f_otherpw = new JFrame();
               frame_otherpw = f_otherpw;
               frame_otherpw.setTitle("error!");
               frame_otherpw.setSize(350, 100);
               frame_otherpw.setLocationRelativeTo(null);
               Change = new JButton("Reset");
               No_Change = new JButton("Cancel");

               Container cp_otherpw = frame_otherpw.getContentPane();
               cp_otherpw.setLayout(new FlowLayout());
               lb_otherpw = new JLabel("This folder is locked. Do you want to reset PassWord?");
               frame_otherpw.add(lb_otherpw);
               cp_otherpw.add(Change);
               cp_otherpw.add(No_Change);

               cp_otherpw.setBackground(Color.WHITE);
               frame_otherpw.setVisible(true);
               
               Change.addActionListener(new ActionListener() {
                  public void actionPerformed(ActionEvent evt) {
                     frame_otherpw.dispose();
                     JFrame f_password = new JFrame();
                     frame_password = f_password;
                     frame_password.setTitle("Reset Password");
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
                           if (tfInput_password.getText().equals(folder.password)) {
                              folder.password = "";
                              frame.dispose();

                              MyFrame fff = new MyFrame(file_list, folder_list, cd);
                              Show_list s = new Show_list(file_list, folder_list, cd, fff.frame);
                              frame_password.dispose();
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
               });
               No_Change.addActionListener(new ActionListener() {
                  public void actionPerformed(ActionEvent evt) {
                     frame_otherpw.dispose();

                  }
               });
            }

            f.dispose();
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
            for (i = 0; i < folder_list.size(); i++) {
               if (folder_list.get(i).equals(folder)) {
                  final int fixed_i = i;
                  if (folder.password == "") {
                     JFrame c_name = new JFrame();
                     change_name = c_name;
                     change_name.setTitle("Change Folder Name");
                     change_name.setSize(300, 80);
                     change_name.setLocationRelativeTo(null);

                     Enter_name = new JButton("Enter");
                     tfInput_name = new TextField(10);

                     Container cp_file = change_name.getContentPane();
                     cp_file.setLayout(new FlowLayout());
                     lb_name = new JLabel("Folder Name");
                     change_name.add(lb_name);
                     cp_file.add(tfInput_name);
                     cp_file.add(Enter_name);
                     cp_file.setBackground(Color.WHITE);
                     change_name.setVisible(true);

                     Enter_name.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent evt) {
                           new_name = tfInput_name.getText();
                           int flag = 0;
                           folder_list = list2;
                           for (int i = 0; i < folder_list.size(); i++) {
                              if (folder_list.get(i).folder_name.equals(new_name)) {
                                 flag++;
                                 JFrame f = new JFrame();
                                 frame_wrong = f;
                                 frame_wrong.setTitle("Error!");
                                 frame_wrong.setSize(300, 100);
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
                           
                           change_name.dispose();
                           
                           if (flag == 0) {
                              new_path = new File(cd + "/" + new_name + "/");
                              for (int i = 0; i < folder_list.size(); i++) {
                                 if (folder_list.get(i).directory.toString()
                                       .contains(folder_list.get(fixed_i).folder.toString())) {

                                    File change_directory = new File(
                                          new_path + folder_list.get(i).directory.toString().substring(
                                                folder_list.get(fixed_i).folder.toString().length()));
                                    folder_list.get(i).directory = change_directory;
                                    File change_folder = new File(
                                          change_directory + "/" + folder_list.get(i).folder_name + "/");
                                    folder_list.get(i).folder = change_folder;
                                 }
                              }
                              for (int i = 0; i < file_list.size(); i++) {
                                 if (file_list.get(i).directory.toString()
                                       .contains(folder_list.get(fixed_i).folder.toString())) {

                                    File change_directory = new File(
                                          new_path + file_list.get(i).directory.toString().substring(
                                                folder_list.get(fixed_i).folder.toString().length()));
                                    file_list.get(i).directory = change_directory;

                                 }
                              }

                              folder_list.get(fixed_i).folder = new_path;
                              folder_list.get(fixed_i).folder_name = new_name;

                              frame.dispose();
                              MyFrame fff = new MyFrame(file_list, folder_list, cd);
                              Show_list s = new Show_list(file_list, folder_list, cd, fff.frame);
                           }
                        }
                     });
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
                              // change name
                              {
                                 JFrame c_name = new JFrame();
                                 change_name = c_name;
                                 change_name.setTitle("Change Folder Name");
                                 change_name.setSize(300, 80);
                                 change_name.setLocationRelativeTo(null);

                                 Enter_name = new JButton("Enter");
                                 tfInput_name = new TextField(10);

                                 Container cp_file = change_name.getContentPane();
                                 cp_file.setLayout(new FlowLayout());
                                 lb_name = new JLabel("Folder Name");
                                 change_name.add(lb_name);
                                 cp_file.add(tfInput_name);
                                 cp_file.add(Enter_name);
                                 cp_file.setBackground(Color.WHITE);
                                 change_name.setVisible(true);

                                 Enter_name.addActionListener(new ActionListener() {
                                    public void actionPerformed(ActionEvent evt) {
                                       new_name = tfInput_name.getText();
                                       int flag = 0;
                                       folder_list = list2;
                                       for (int i = 0; i < folder_list.size(); i++) {
                                          if (folder_list.get(i).folder_name.equals(new_name)) {
                                             flag++;
                                             JFrame f = new JFrame();
                                             frame_wrong = f;
                                             frame_wrong.setTitle("Error!");
                                             frame_wrong.setSize(300, 100);
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
                                       change_name.dispose();

                                       if (flag == 0) {
                                          new_path = new File(cd + "/" + new_name + "/");
                                          for (int i = 0; i < folder_list.size(); i++) {
                                             if (folder_list.get(i).directory.toString().contains(
                                                   folder_list.get(fixed_i).folder.toString())) {

                                                File change_directory = new File(
                                                      new_path + folder_list.get(i).directory
                                                            .toString().substring(
                                                                  folder_list.get(fixed_i).folder
                                                                        .toString().length()));
                                                folder_list.get(i).directory = change_directory;
                                                File change_folder = new File(change_directory + "/"
                                                      + folder_list.get(i).folder_name + "/");
                                                folder_list.get(i).folder = change_folder;
                                             }
                                          }
                                          for (int i = 0; i < file_list.size(); i++) {
                                             if (file_list.get(i).directory.toString().contains(
                                                   folder_list.get(fixed_i).folder.toString())) {

                                                File change_directory = new File(
                                                      new_path + file_list.get(i).directory.toString()
                                                            .substring(
                                                                  folder_list.get(fixed_i).folder
                                                                        .toString().length()));
                                                file_list.get(i).directory = change_directory;

                                             }
                                          }

                                          folder_list.get(fixed_i).folder = new_path;
                                          folder_list.get(fixed_i).folder_name = new_name;

                                          frame.dispose();
                                          MyFrame fff = new MyFrame(file_list, folder_list, cd);
                                          Show_list s = new Show_list(file_list, folder_list, cd,
                                                fff.frame);
                                       }
                                    }
                                 });
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
               }
            }
            f.dispose();
         }
      });

      // move
      b6.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            Folder_list_folder fl = new Folder_list_folder(file_list, folder_list, cd, frame, folder);
            f.dispose();
         }
      });

      p.add(b1); // open
      p.add(b2); // delete
      p.add(b3); // password
      p.add(b5); // change name
      p.add(b6); // move
      p.add(b4); // close

      f.add(p);
   }

   // delete all files and folders in one folder
   private static boolean deleteDirectory(File path) {
      if (!path.exists())
         return false;

      File[] files = path.listFiles();
      for (File file : files) {
         if (file.isDirectory())
            deleteDirectory(file);
         else
            file.delete();
      }
      return path.delete();
   }

}