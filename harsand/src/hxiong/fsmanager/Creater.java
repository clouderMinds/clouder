package hxiong.fsmanager;

import hxiong.dbmanager.psql.FiletablePSQL;
import hxiong.fsmanager.FsFiles;

/**
 * 这是一个负责在集群上创建文件并往文件里面写入数据的类
 * 该类在创建文件的时候先通过md5值判断该文件是否已经存在
 * 如果文件在集群上已经存在，则不会再创建文件
 * @author 熊天成
 *
 */
public class Creater {
     
	 /**
	  * 根据文件的路径名创建文件，对应用户新建一个文件夹
	  * this is a folders 这个md5值表示这是一个文件夹 
	  * @param owner 文件的所有者
	  * @param path 文件的路径
	  * @return 如果操作成功返回true，否则返回false
	  */
	 public boolean createFolder(String owner,String path){
		 
		 return FiletablePSQL.addFile(owner, "this is a folders", path, path);
	 }
	 /**
	  * 根据文件的路径名创建文件，对应用户新建一个文件夹
	  * this is a folders 这个md5值表示这是一个文件夹 
	  * @param owner 文件的所有者
	  * @param path 文件的路径
	  * @param ftype 文件的类型
	  * @param fsize 文件的大小
	  * @param fdate 文件上传的日期
	  * @param other 其他对文件的描述
	  * @return 如果操作成功返回true，否则返回false
	  */
     public boolean createFolder(String owner,String path,String ftype,long fsize,String fdate,String other){
		 
		 return FiletablePSQL.addFile(owner, "this is a folders, abspath", path, path, ftype, fsize, fdate, other);
	 }
	 /**
	  * 对应用户上传一个文件的方法，而该文件在集群上已经存在了
	  * 所以只需要给对应的用户插入这条新上传的文件记录即可
	  * @param owner 文件的所有者
	  * @param md5 文件的md5值
	  * @param abspath 文件的抽象路径
	  * @param realpath 文件的真实路径
	  * @return 如果操作成功返回true，否则返回false
	  */
	 public boolean createExitFile(String owner,String md5,String abspath,String realpath){
		 
		 return FiletablePSQL.addFile(owner, md5, abspath, realpath);
	 }
	 /**
      * 对应用户上传一个文件的方法，而该文件在集群上已经存在了
	  * 所以只需要给对应的用户插入这条新上传的文件记录即可
	  * @param owner 文件的所有者
	  * @param md5 文件的md5值
	  * @param abspath 文件的抽象路径
	  * @param realpath 文件的真实路径
	  * @param ftype 文件的类型
	  * @param fsize 文件的大小
	  * @param fdate 文件上传的日期
	  * @param other 其他对文件的描述
	  * @return 如果操作成功返回true，否则返回false
	  */
     public boolean createExitFile(String owner,String md5,String abspath,String realpath,String ftype,long fsize,String fdate,String other){
		 
		 return FiletablePSQL.addFile(owner, md5, abspath, realpath, ftype, fsize, fdate, other);
	 }
     
     /**
      * 对应于用户上传一个新的文件，该文件在集群上还没有相同的文件
      * 则除了往数据库里面添加新的文件信息外，还将新的文件上传到集群上
      * @param owner 文件的所有者
      * @param md5 文件的md5值
      * @param abspath 文件的抽象路径
      * @param realpath 文件的真实路径
      * @param fd 文件数组组成的字节数组
      * @return 如果操作成功返回true，否则返回false
      */
     public boolean createNewFile(String owner,String md5,String abspath,String realpath,byte[] fd){
    	 
    	 if(FsFiles.createFile(realpath, fd)){//如果上传到集群成功
    		 return FiletablePSQL.addFile(owner, md5, abspath, realpath);
    	 }else{
    		 return false;
    	 } 
     }
     /**
      * 对应于用户上传一个新的文件，该文件在集群上还没有相同的文件
      * 则除了往数据库里面添加新的文件信息外，还将新的文件上传到集群上
      * @param owner 文件的所有者
      * @param md5 文件的md5值
      * @param abspath 文件的抽象路径
      * @param realpath 文件的真实路径
      * @param ftype 文件的类型
	  * @param fsize 文件的大小
	  * @param fdate 文件上传的日期
	  * @param other 其他对文件的描述
      * @param fd 文件数组组成的字节数组
      * @return 如果操作成功返回true，否则返回false
      */
     public boolean createNewFile(String owner,String md5,String abspath,String realpath,String ftype,long fsize,String fdate,String other,byte[] fd){
    	 
    	 if(FsFiles.createFile(realpath, fd)){//如果上传到集群成功
    		 return FiletablePSQL.addFile(owner, md5, abspath, realpath, ftype, fsize, fdate, other);
    	 }else{
    		 return false;
    	 }
     }
     
}
