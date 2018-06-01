package management;
import java.io.File;
import java.util.*;

public class Folder {
	String folder_name;
	
	String parent_name;
	ArrayList <String> child_name;
	
	Folder parent;
	ArrayList <Folder> children;
	
	Folder(String name){
		folder_name = name;
		
		
		
		String a;
		System.out.print("Directory Name : ");
		Scanner scan = new Scanner(System.in); 
		a = scan.nextLine();
		File path = new File(".");
		File newFile2 = new File(path + "/dir/"+ a);
	    if(newFile2.mkdir()){   //만드려는 디렉토리가 하나일 경우
	      System.out.println("디렉토리를 생성했습니다.");
	    }else{
	      System.out.println("디렉토리를 생성하지 못했습니다.");
	    }
	}
}
