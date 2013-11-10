package sendup;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;

import javax.swing.JOptionPane;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.util.Progressable;

/**
 * 这是一个把本地文件上传到hdfs的类
 * @author hadoop  
 *
 */
public class SendFiles {

	public static void main(String[] args) {
		
		SendFiles sender=new SendFiles();//创建一个文件上传对象
		try {
			sender.sendToHdfs();//调用上传文件的方法
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null,"发生异常，未能成功上传！");//显示提示信息
			e.printStackTrace();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null,"发生异常，未能成功上传！");//显示提示信息
			e.printStackTrace();
		}

	}
	   public void sendToHdfs() throws FileNotFoundException,IOException{  
	        
		   //本地的文件地址，即文件的路径
	        String localSrc = "/home/hadoop/文档/test1";  
	        //存放在hdfs的文件地址  
	        String dest = "hdfs://master:9000/xiong/data/test1";  
	       
	        InputStream in = new BufferedInputStream(new FileInputStream(localSrc));  
	        //得到配置对象  
	        Configuration conf = new Configuration();  
	        //文件系统  
	        FileSystem fs = FileSystem.get(URI.create(dest), conf);  
	        //输出流，创建一个输出流 
	        OutputStream out = fs.create(new Path(dest), new Progressable() {  
	        	//重写progress方法  
	        	public void progress() {  
	        		System.out.println("上传完一个设定缓存区大小容量的文件！");  
	            }  
	        });  
	        //连接两个流，形成通道，使输入流向输出流传输数据,  
	        IOUtils.copyBytes(in, out, 102400,true); //in为输入流对象，out为输出流对象，4096为缓冲区大小，true为上传后关闭流 
	        JOptionPane.showMessageDialog(null,"恭喜你.上传成功！");//显示提示信息
	        }  

}
