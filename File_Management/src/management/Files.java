package management;
import java.util.*;
import java.io.*;
import java.io.File;

public class Files {
	String file_name;
	File directory;
	File file;
	
	Files(String name, File cd){
		file_name = name;
		directory = cd;
		
		Writer osw, bw;
		OutputStream fos;
		
		try {
			// create file
			fos = new FileOutputStream (cd + "/" + file_name + ".txt");
			osw = new OutputStreamWriter(fos);
			bw = new BufferedWriter(osw);
			
			System.out.print("Please Write Content : ");
			String data;
			Scanner scan = new Scanner(System.in); 
			data = scan.nextLine();
	        osw.write (data);
	        bw.close();
	        
	        //
	        File f = new File(cd + "/" + file_name + ".txt");
	        file = f;
	        
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	
}
