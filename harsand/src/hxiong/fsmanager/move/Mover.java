package hxiong.fsmanager.move;

import hxiong.dbmanager.psql.FiletablePSQL;

/**
 * 这是一个负责移动用户文件的类
 * 该类目前只提供移动文件到另一个目录下的方法
 * @author 熊天成
 *
 */
public class Mover {
     
	   /**
		 * 移动文件的方法，具体操作和重命名类似
		 * @param owner 文件的所有者
		 * @param oldabspath 原来的文件名
		 * @param newabspath 新的文件名
		 * @return  如果操作成功返回true，否则返回false
		 */
	public boolean moveFile(String owner,String oldabspath,String newabspath){
		
		return FiletablePSQL.updateAbspath(owner, oldabspath, newabspath);
	}
}
