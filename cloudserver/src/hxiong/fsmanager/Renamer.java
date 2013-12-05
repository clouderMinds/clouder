package hxiong.fsmanager;

import hxiong.dbmanager.psql.FiletablePSQL;

/**
 * 这是一个负责将用户的文件进行重命名的方法
 * 该类目前只提供将文件重命名的方法
 * @author 熊天成
 *
 */
public class Renamer {
     
	 /**
	 * 重命名文件的方法
	 * @param owner 文件的所有者
	 * @param oldabspath 原来的文件名
	 * @param newabspath 新的文件名
	 * @return  如果操作成功返回true，否则返回false
	 */
	 public boolean renameFile(String owner,String oldabspath,String newabspath){
		 
		 return FiletablePSQL.updateAbspath(owner, oldabspath, newabspath);
	 }
}
