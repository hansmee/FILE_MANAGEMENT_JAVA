package management;
import java.util.*;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

public class File_Management {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
			//Make_File f = new Make_File();
			//File_List ff = new File_List();
		String a,b;
		Scanner scan = new Scanner(System.in);
		/*b = scan.nextLine();
			
		Folder_List fff = new Folder_List();
		fff.list("." + "/dir/", b);*/
		
		
		/*String a;
		System.out.print("Directory Name : ");
		//Scanner scan = new Scanner(System.in); 
		a = scan.nextLine();
		File path = new File(".");
		File newFile2 = new File(path + "/dir/dir2/"+ a);
	    if(newFile2.mkdir()){   //만드려는 디렉토리가 하나일 경우
	      System.out.println("디렉토리를 생성했습니다.");
	    }else{
	      System.out.println("디렉토리를 생성하지 못했습니다.");
	    }*/
	    
	    //Path currentRelativePath = Paths.get("");
	    //String s = currentRelativePath.toAbsolutePath().toString();
	    //System.out.println("Current relative path is: " + s);
		a = "";
		boolean flag = true;
		while(flag) {
			System.out.println("**********LIST***********");
			Folder_List FL = new Folder_List();
			try {
				FL.list("./dir/", a);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//File_List fl = new File_List(a);
			System.out.println("*************************");
			System.out.println("\n현재위치 : " + "." + 
					"\n\t폴더생성: 1\n\t파일생성: 2\n\t파일열기: 3\n\t폴더열기: 4\n\t나가기: 5\n\t종료: 0");
			int n = scan.nextInt();
			
			switch(n) {
			case 1:
				break;
			case 2:
				Make_File f = new Make_File();
				break;
			case 3:
				break;
			case 4:
				break;
			case 5:
				flag = false;
				break;
			default:
				System.out.println("Wrong input");
				break;
			}
			
			
		}
	}

}
