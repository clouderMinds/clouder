package download;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;

import javax.swing.JOptionPane;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;

/**
 * 这是一个下载hdfs上的文件的方法
 * @author hadoop
 *
 */
public class LoadFiles {
	 
	public static void main(String[] args){
		LoadFiles loader=new LoadFiles();//创建一个下载对象
		try {
			loader.readFromHdfs();//调用从hdfs下载文件的方法
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null,"发生异常，未能成功下载！");//显示提示信息
			e.printStackTrace();
		}
	}
	 //读入数据：从云端到本机（提取数据）  
      public void readFromHdfs()throws FileNotFoundException,IOException{  
        
    	  //hdfs的文件目录，即文件的存放路径 
        String dest = "hdfs://master:9000/xiong/jdk";  
        //要存放到的本机地址  
        String mySrc = "/home/hadoop/文档/test";  
        
        //得到配置  
        Configuration conf = new Configuration();  
        //实例化文件系统  
        FileSystem fs = FileSystem.get(URI.create(dest), conf);  
        //读出流  
        FSDataInputStream hdfsInStream = fs.open(new Path(dest));  
        //写入流  
        OutputStream out = new FileOutputStream(mySrc);  
        //将InputStrteam 中的内容通过IOUtils的copyBytes方法复制到out中  
        IOUtils.copyBytes(hdfsInStream, out, 10240,true);  
        JOptionPane.showMessageDialog(null,"恭喜你。下载成功！");//显示提示信息      
      }

}
