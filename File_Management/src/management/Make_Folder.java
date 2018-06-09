package management;

import java.util.*;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Make_Folder {
	Make_Folder(List folder_list, File cd){
		String folder_name;
		System.out.print("Folder Name : ");
		Scanner scan = new Scanner(System.in); 
		folder_name = scan.nextLine();
		
		folder_list.add(new Folder(folder_name, cd));
	}
}
