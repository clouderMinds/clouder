package connentdb;

import java.sql.SQLException;

/**
 * 这是关于postgre里面file_table表格的操作
 * 包括增加，删除，查找等操作
 * @author 熊天成
 *
 */
public class FileTableSQL {
    
	/**
	 * 根据抽象路径名从数据库里面找到真实的文件路径的方法
	 * @param abspath 抽象的文件名
	 * @return  返回真实的文件名
	 */
	public static String selectFile(String abspath){
		//获取数据库的连接
		java.sql.Connection connect=ConnectPSQL.getConn();
		String sql = "select * from file_table where abspath=?";
		java.sql.PreparedStatement stat;
		try {
			stat = connect.prepareStatement(sql);
			stat.setString(1, abspath);
			stat.addBatch();
			int [] contern=stat.executeBatch();
			for(int i=0;i<contern.length;i++){
				System.out.println("...."+contern[i]);
			}
			
		} catch (SQLException e) {
			System.out.println(e.getNextException());
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 往数据库filetable里面插入数据
	 * @param owner 该文件的所有者
	 * @param md5 文件md5值
	 * @param available 文件是否可用
	 * @param abspath 文件的抽象路径
	 * @param realpath 文件的真实路径
	 * @return
	 */ 
	public static boolean insertFile(String owner,String md5,int available,String abspath,String realpath){
		//获取数据库的连接
		java.sql.Connection connect=ConnectPSQL.getConn();
		String sql = "insert into file_table(owner,md5,available,abspath,realpath) values(?,?,?,?,?)";
		java.sql.PreparedStatement stat;
		try {
			stat = connect.prepareStatement(sql);
			stat.setString(1, owner);
			stat.setString(2, md5);
			stat.setInt(3, available);
			stat.setString(4, abspath);
			stat.setString(5, realpath);
			stat.addBatch();
			int [] contern=stat.executeBatch();
			for(int i=0;i<contern.length;i++){
				System.out.println("...."+contern[i]);
			}
			
			return true;
		} catch (SQLException e) {
			System.out.println(e.getNextException());
			e.printStackTrace();
		}
		return false;
	}

}
