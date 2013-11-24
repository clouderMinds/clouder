package Utils;

import java.io.File;
import java.io.FileOutputStream;

public class FileTools {
	
	public static void getFile(byte[] data){
		File file = new File("/home/hadoop/桌面/aaa");
		try {
			FileOutputStream fos = new FileOutputStream(file);
			fos.write(data);
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
