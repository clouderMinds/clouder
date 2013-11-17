package myFile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;


public class MyHDFS {
	/**
	 * 在集群上创建文件夹的方法
	 * @param filepath：要创建的文件夹的路径
	 * @return：返回是否操作成功
	 */
	public boolean makeDir(String filepath){
		Configuration conf=new Configuration();
		FileSystem fs=null;
		try {
			fs=FileSystem.get(URI.create("hdfs://master:9000"),conf);//得到集群的跟目录
			Path path=new Path(filepath);//实例化路径路径类
			fs.mkdirs(path);//创建文件夹
			fs.close();//关闭文件系统
			return true;
		} catch (IOException e) {
			if(fs!=null){
				try {
					fs.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			e.printStackTrace();
		}
		
		return false;	
	}
	/**
	 * 在集群上删除指定文件夹的方法
	 * @param filepath：文件夹的路径
	 * @return：是否操作成功
	 */
	public boolean deleteDir(String filepath){
		Configuration conf=new Configuration();
		FileSystem fs=null;
		try {
			fs=FileSystem.get(URI.create("hdfs://master:9000"), conf);
			Path path=new Path(filepath);
			fs.delete(path, true);//删除文件
			fs.close();//关闭文件系统
			return true;
		} catch (IOException e) {
			if(fs!=null){
				try {
					fs.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			e.printStackTrace();
		}
		return false;
		
	}
	/**
	 * 在集群上创建一个文件的方法
	 * @param filepath：要创建的文件的位置
	 * @return：是否创建成功
	 */
	public boolean makeFile(String filepath){
		Configuration conf=new Configuration();
		FileSystem fs=null;
		try {
			fs=FileSystem.get(URI.create("hdfs://master:9000"),conf);
			Path path=new Path(filepath);
			fs.create(path);
			fs.close();
			return true;
		} catch (IOException e) {
			if(fs!=null){
				try {
					fs.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			e.printStackTrace();
		}
		
		return false;
		
	}
	/**
	 * 向特定的文件中写入指定内容的方法
	 * @param filepath：要写入文件的路径
	 * @param content要写入的内容
	 * @return
	 */
	public boolean writeFile(String filepath,String content){
		Configuration conf=new Configuration();
		FileSystem fs=null;
		
		try {
			fs=FileSystem.get(URI.create("hdfs://master:9000"), conf);
			Path path=new Path(filepath);
			//创建文件输入流
			FSDataOutputStream out=fs.create(path);
			out.write(content.getBytes());
			out.close();
			fs.close();
			return true;
		} catch (IOException e) {
			if(fs!=null){
				try {
					fs.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			e.printStackTrace();
		}
		return false;
	}
}

