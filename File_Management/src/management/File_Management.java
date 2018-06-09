package management;
import java.util.*;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

// main function
public class File_Management {

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

		
		// **************start program***************
		while(true) {
			Show_list s = new Show_list(file_list, folder_list, cd);
			System.out.println("NEXT WORK: ");
			n = scan.nextInt();
			// quit the program
			if(n == 0) {
				for(int i = 0; i < file_list.size(); i++) {
					file_list.get(i).file.delete();
					file_list.remove(i);
				}
				for(int i = 0; i < folder_list.size(); i++) {
					folder_list.get(i).folder.delete();
					folder_list.remove(i);
				}
				break;
			}
			else{;
				switch(n) {
				case 1: // make file
					Make_File m = new Make_File(file_list, cd);
					break;
				case 2: // make folder
					Make_Folder mf = new Make_Folder(folder_list, cd);
					break;		
				case 3:
					Folder_Work fw = new Folder_Work(cd);
					cd = fw.newcd();
					break;
				default:
					break;
				}
			}
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		/*
		for (int i= 0; i < 3; i++) { 
			a = scan.nextLine();
		   list.add(new Files(a));
		}
		ll.add(list);
		System.out.println("ll: " + ll.get(0).getClass().file_name);
		
		for(int i = 0; i < list.size(); i++) {
			if(list.get(i).file_name.equals("abc")) {
				list.get(i).file.delete();
				list.remove(i);
			}
		}
		
		for(int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i).file_name);
		}
		*/
		
	}
	
	//public void Back(List file_list, List folder_list) {
		
//	}

}
