package management;

import java.util.Scanner;

public class Make_File {
	Make_File(){
		String a;
		System.out.print("File Name : ");
		Scanner scan = new Scanner(System.in); 
		a = scan.nextLine();
		Files b = new Files(a);
	}
}
