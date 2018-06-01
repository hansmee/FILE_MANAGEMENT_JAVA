package management;
import java.io.*;

public class Folder_List {
	File f;
	Folder_List(){
		
	}
	public void list(String source, String a) throws IOException{
		File dir = new File(source); 
		File[] fileList = dir.listFiles(); 
		
		try{
			for(int i = 0 ; i < fileList.length ; i++){
				File file = fileList[i]; 
				if(file.isFile()){
					// 파일이 있다면 파일 이름 출력
					System.out.println("파일 : " + file.getPath() );
				}else if(file.isDirectory()){
					System.out.println("디렉토리 : " + file.getParent() +"/"+ file.getName());
					// 서브디렉토리가 존재하면 재귀적 방법으로 다시 탐색
					//list(file.getCanonicalPath().toString(), a); 
					//System.out.println(file.getCanonicalPath());
					//System.out.println("부모 = " + file.getParent());
					if(file.getName().equals(a)) {
						System.out.println("\"Found\"");
						f = file;
					}
				}
			}
		}finally {
			
		}
	}


}
