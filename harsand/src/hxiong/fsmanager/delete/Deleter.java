package hxiong.fsmanager.delete;

import hxiong.dbmanager.psql.FiletablePSQL;
import hxiong.fsmanager.file.FsFiles;

/**
 * 这是一个负责删除文件的类
 * 该类提供删除用户在数据库里面的文件记录，也提同删除集群上真实的文件的方法
 * @author 熊天成
 *
 */
public class Deleter {
    
	/**
	 * 删除文件夹的方法，该方法只能删除用户在数据库里面的文件夹记录
	 * @param owner 文件的所有者
	 * @param abspath 文件的抽象路径
	 * @return 如果操作成功返回true，否则返回false
	 */
	public boolean deleteFolder(String owner,String abspath){
		
		return FiletablePSQL.removeFile(owner, abspath);
	}
	/**
	 * 删除文件的方法，该方法只能删除用户在数据库里面的文件记录
	 * @param owner 文件的所有者
	 * @param abspath 文件的抽象路径
	 * @return 如果操作成功返回true，否则返回false
	 */
	public boolean deleteFile(String owner,String abspath){
		
		return FiletablePSQL.removeFile(owner, abspath);
	}
	/**
	 * 该方法除了删除集群上的文件同时还删除文件的数据库里面的记录
	 * @param owner 文件的所有者
	 * @param abspath 文件的抽象路径
	 * @return 如果操作成功返回true，否则返回false
	 */
    public boolean deleteFileonHDFS(String owner,String abspath){
	   String realpath=FiletablePSQL.getRealpaht(owner, abspath);//获取真实文件路径
	   if(realpath!=null){//如果文件存在
		   if(FsFiles.deleteFile(realpath)){
			   return FiletablePSQL.removeFileonDB(owner, abspath);
		   }
	   }
       return false;
	}
	
}
