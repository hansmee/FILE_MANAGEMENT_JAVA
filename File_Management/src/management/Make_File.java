package management;

import java.util.*;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Make_File {
	Make_File(List file_list, File cd){
		String file_name;
		System.out.print("File Name : ");
		Scanner scan = new Scanner(System.in); 
		file_name = scan.nextLine();
		
		file_list.add(new Files(file_name, cd));
	}
}
