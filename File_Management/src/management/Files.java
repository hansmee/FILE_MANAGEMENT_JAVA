package management;
import java.util.*;
import java.io.*;
import java.io.File;

public class Files {
	String file_name;
	Writer osw, bw;
	OutputStream fos;
	
	Files(String name){
		file_name = name;
		try {
			 File path = new File(".");
			// System.out.println(path.getAbsolutePath());
			//fos = new FileOutputStream ("/Users/hansangmee/Desktop/management/dir/" + file_name + ".txt");
			fos = new FileOutputStream (path.getAbsolutePath() + "/dir/" + file_name + ".txt");
			osw = new OutputStreamWriter(fos);
			bw = new BufferedWriter(osw);
			System.out.println("Please Write Content.");
			String data;
			Scanner scan = new Scanner(System.in); 
			data = scan.nextLine();
	        osw.write (data);
	        bw.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	
}
