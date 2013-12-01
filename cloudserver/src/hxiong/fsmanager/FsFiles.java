package hxiong.fsmanager;

import java.io.IOException;
import java.net.URI;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

/**
 * 这是一个集群文件系统服务类，该类提供了具体管理集群文件系统管理方法
 * 比如创建文件，删除文件等操作都必须经过该类来完成
 * @author 熊天成
 *
 */
public class FsFiles {
        
	/*
	 * 在集群上创建一个文件夹的方法
	 *filepath为创建的文件路径
	 * Configuration类就是针对hadoop-default.xml和hadoop-site.xml文件的，
	 * 里面实现了解析配置文件，其中hadoop-default.xml是默认的，里面配置的内容
	 * 已经是一个完整的配置。如果配置了hadoop-site.xml文件，系统在初始化Configuration配置类实例的时候，
	 * 会首先加载默认的hadoop-default.xml文件中的配置，然后读取hadoop-site.xml文件，
	 * 如果不是空配置，就用该配种文件中的配置来覆盖掉默认的配置。
	 */
	public static boolean MakeDir(String filepath) {
		Configuration conf = new Configuration();
		FileSystem fs=null;
			try {				
				fs = FileSystem.get(URI.create("hdfs://master:9000"),conf);
				filepath="hdfs://master:9000/hadoop/"+filepath;//并接为一个完整的路径
				Path path = new Path(filepath);
				fs.mkdirs(path);//创建该文件夹
				fs.close();//关闭文件系统
				return true;
			} catch (IOException e) {
				if(fs!=null){
					try {
						fs.close();//关闭文件系统
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
				e.printStackTrace();
			}
			return false;
	}
	/**
	 * 删除集群文件系统上指定的文件夹的方法
	 * @param filepath 文件的路径
	 * @return 如果操作成功返回true，否则返回false
	 */
	public static boolean deleteDir(String filepath) {
		Configuration conf = new Configuration();
		FileSystem fs=null;
			try {				
				fs = FileSystem.get(URI.create("hdfs://master:9000"),conf);
				filepath="hdfs://master:9000/hadoop/"+filepath;//并接为一个完整的路径
				Path path = new Path(filepath);
				fs.delete(path,true);//删除指定文件
				fs.close();//关闭文件系统
				return true;
			} catch (IOException e) {
				if(fs!=null){
					try {
						fs.close();//关闭文件系统
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
				e.printStackTrace();
			}
			return false;
	}
	/**
	 * 这是一个在集群上创建文件的方法
	 * @param filepath 文件的路径
	 * @return 如果操作成功返回true，否则返回false
	 */
	public static boolean createFile(String filepath) {
		Configuration conf = new Configuration();
		FileSystem fs=null;
			try {				
				fs = FileSystem.get(URI.create("hdfs://master:9000"),conf);
				filepath="hdfs://master:9000/hadoop/"+filepath;//并接为一个完整的路径
				Path path = new Path(filepath);
				fs.create(path);//创建文件
				fs.close();//关闭文件系统
				return true;
			} catch (IOException e) {
				if(fs!=null){
					try {
						fs.close();//关闭文件系统
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
				e.printStackTrace();
			}
			return false;
	}
	/**
	 * 在集群上创建一个文件，并同时往该文件写入数据
	 * @param filepath 有创建的文件路径
	 * @param fd 要写入的数据
	 * @return 如果操作成功返回true，否则返回false
	 */
	public static boolean createFile(String filepath,byte[] fd) {
		Configuration conf = new Configuration();
		FileSystem fs=null;
			try {				
				fs = FileSystem.get(URI.create("hdfs://master:9000"),conf);
				filepath="hdfs://master:9000/hadoop/"+filepath;//并接为一个完整的路径
				Path path = new Path(filepath);
				FSDataOutputStream out = fs.create(path);///创建文件
				out.write(fd);//写入
				fs.close();//关闭文件系统
				return true;
			} catch (IOException e) {
				if(fs!=null){
					try {
						fs.close();//关闭文件系统
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
				e.printStackTrace();
			}
			return false;
	}
	
	/**
	 * 根据文件路径删除集群上指定的文件
	 * @param filepath 文件的路径
	 * @return 如果操作成功返回true，否则返回false
	 */
	public static boolean deleteFile(String filepath) {
		Configuration conf = new Configuration();
		FileSystem fs=null;
			try {				
				fs = FileSystem.get(URI.create("hdfs://master:9000"),conf);
				filepath="hdfs://master:9000/hadoop/"+filepath;//并接为一个完整的路径
				Path path = new Path(filepath);
				fs.delete(path, true);//删除文件
				fs.close();//关闭文件系统
				return true;
			} catch (IOException e) {
				if(fs!=null){
					try {
						fs.close();//关闭文件系统
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
				e.printStackTrace();
			}
			return false;
	}
	/**
	 * 根据文件路径读取集群上指定文件的内容并以字节数组的形式返回
	 * @param filepath 文件的路径
	 * @return 如果读取成功返回字节数组 否则返回null
	 */
	public static byte[] ReadFile(String filepath) {
		Configuration conf = new Configuration();
		FileSystem fs=null;
		try {
			fs = FileSystem.get(URI.create("hdfs://master:9000"),conf);
			filepath="hdfs://master:9000/hadoop/"+filepath;//并接为一个完整的路径
			Path path = new Path(filepath);			
			if(fs.exists(path)){
				FSDataInputStream is = fs.open(path);//打开文件流
				FileStatus status = fs.getFileStatus(path);//获得文件流对象
				byte[] buffer = new byte[Integer.parseInt(String.valueOf(status.getLen()))];
				is.readFully(0, buffer);
				is.close();
	            fs.close();//关闭文件系统
	            return buffer;
			}
			
		  } catch (IOException e) {
			if(fs!=null){
				try {
					fs.close();//关闭文件系统
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			e.printStackTrace();
		}			
	   return null;	
     }
	/**
	 * 将集群上的文件进行重命名的方法
	 * @param oldpath 原来的文件路径包含文件名
	 * @param newpath 新的文件路径包含文件名
	 * @return 如果操作成功返回true，否则返回false
	 */
	public static boolean remaneFile(String oldpath,String newpath){
		  Configuration conf=new Configuration();
	        FileSystem hdfs=null;
			try {
				//获得文件系统
				hdfs = FileSystem.get(URI.create("hdfs://master:9000"),conf);
				oldpath="hdfs://master:9000/"+oldpath;//拼接成为一个完整的文件路径
				newpath="hdfs://master:9000/"+newpath;//拼接成为一个完整的文件路径
				Path frpath=new Path(oldpath);    //旧的文件名
			    Path topath=new Path(newpath);    //新的文件名
				boolean bl=hdfs.rename(frpath, topath);//返回修改的情况，成功为true，否则为false
				hdfs.close();//关闭文件系统
				return bl;
			} catch (IOException e) {
				if(hdfs!=null){
					try {
						hdfs.close();//关闭文件系统
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
				e.printStackTrace();
			}
	       return false;	   
	}
}
