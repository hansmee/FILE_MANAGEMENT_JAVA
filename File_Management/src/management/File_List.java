package management;

import java.io.File;

public class File_List {
	File_List(String p){
		File dir = new File("/Users/hansangmee/Desktop/management/dir/");
		File path = new File(".");

		File PP = new File(path, p);
		/*if(dir.isDirectory()) {
			System.out.println("yes");
		}else {
			System.out.println("no");
		}*/
		
		File []fileList = PP.listFiles();
		
		for(File tempFile : fileList) {
		  if(tempFile.isFile()) {
		    String tempPath = tempFile.getParent();
			//System.out.println("Path=" + tempPath);
		    String tempFileName = tempFile.getName();
		    System.out.println("FileName = " + tempFileName + "\n");
		    if(tempFileName.equals("4.txt")) {
	    			System.out.println("4.txt is deleted.");
    				tempFile.delete();
		    }
		  }
		}
		for(File tempFile : fileList) {
			  if(tempFile.isDirectory()) {
			    String tempPath = tempFile.getParent();
				//System.out.println("Path=" + tempPath);
			    String tempFileName = tempFile.getName();
			    System.out.println("FolderName = " + tempFileName + "\n");
			    
			  }
			}
	}
}
