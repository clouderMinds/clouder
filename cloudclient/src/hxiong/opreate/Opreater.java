package hxiong.opreate;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


/**
 * 这是一个操作类，提供一些常用的静态方法，比如获得文件md5值等方法
 * @author 熊天成
 *
 */
public class Opreater {
    
	/**
	 * 根据文件路径获取文件md5值的方法
	 * @param filepath 文件的路径
	 * @return 如果文件存在，且操作正常，返回md5值
	 */
	public static String getMD5ByFile(String filepath){
		FileInputStream fileInputStream =null;
		try {
			//获得md5消息摘要对象
			MessageDigest MD5=MessageDigest.getInstance("MD5");
			//创建文件输入流			
			fileInputStream= new FileInputStream(filepath);
			//设置缓冲区大小
            byte[] buffer = new byte[8192];
            int length;
            while ((length = fileInputStream.read(buffer)) != -1) {//不断的读入文件字节流
                MD5.update(buffer, 0, length);
            }
            byte[] md5=MD5.digest();//返回16个字节的数组
            //返回结果
            return getMD5(md5);
		   } catch (NoSuchAlgorithmException e) {//异常处理
			   e.printStackTrace();
		   }catch (FileNotFoundException e) {//异常处理
	           e.printStackTrace();
               return null;
           } catch (IOException e) {//异常处理
               e.printStackTrace();
               return null;
           } finally {//异常处理
            try {
                if (fileInputStream != null){
                	fileInputStream.close();
                }                   
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
		return null;
	}
	/**
	 * 
	 * @param tmp 由128位组成的16个字节的字节数组
	 * @return 如果成功，返回32个字符组成的md5字符串，否则返回“”
	 */
	public static String getMD5(byte[] tmp) {
		 if(tmp.length==16){//如果传入的是由128位组成的16个字节的字节数组
		 char hexDigits[] = { 
			     '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd',  'e', 'f'}; 
			    char str[] = new char[16 * 2]; //创建32长度的字符数组
			    int k = 0;     
			    for (int i = 0; i < 16; i++) { //具体的转换过程
			      byte byte0 = tmp[i];     
			      str[k++] = hexDigits[byte0 >>> 4 & 0xf];
			      str[k++] = hexDigits[byte0 & 0xf]; 
			    } 
			    return new String(str);  //返回结果
			 }	     
	     return "";
	}
}
