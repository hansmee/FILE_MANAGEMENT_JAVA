package management;

import java.util.*;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;


public class Show_list{
	List<Files> file_list = new ArrayList<Files> ();
	List<Folder> folder_list = new ArrayList<Folder> ();
	
	Show_list(List list1, List list2, File cd){
		// file_list
		if(list1.size() != 0) {
			file_list = list1;
			
			for(int i = 0; i < file_list.size(); i++) {
				if(file_list.get(i).directory.equals(cd)) {
					System.out.println(file_list.get(i).file_name);
				}
			}
		}
		// folder_list
		if(list2.size() != 0) {
			folder_list = list2;
			for(int i = 0; i < folder_list.size(); i++) {
				if(folder_list.get(i).directory.equals(cd)) {
					System.out.println(folder_list.get(i).folder_name);
				}
			}
		}
		
	}
}
