package Utils;

import java.io.File;
import java.io.FileInputStream;

public class FileSendTest {
	
	public static byte[] getData(){
		File file = new File("/home/hadoop/桌面/数据包类型");
		try {
			FileInputStream fis = new FileInputStream(file);
			int size = fis.available();
			byte[] data = new byte[size];
			fis.read(data);
			fis.close();
			return data;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
