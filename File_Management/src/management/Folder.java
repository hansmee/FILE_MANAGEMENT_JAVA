package management;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Folder {
	public String folder_name;
	public String password = "";
	public File directory;
	public File folder;

	Folder(String name, File cd) {
		folder_name = name;
		directory = cd;

		File f = new File(directory + "/" + folder_name + "/");
		f.mkdir();
		folder = f;
	}
}
