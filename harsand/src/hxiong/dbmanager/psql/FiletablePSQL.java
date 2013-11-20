package hxiong.dbmanager.psql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * 这是一个管理文件表的类，该类提供往表格里面插入数据
 * 删除数据，修改数据等方法
 * @author 熊天成
 *
 */
public class FiletablePSQL {
    
	/**
	 * 往表格里面插入一个新建文件的消息，表明在集群上创建了一个文件
	 * @param owner 文件的所有者
	 * @param md5 文件的md5值
	 * @param abspath 文件的抽象路径
	 * @param realpath 文件在集群上的真实路径
	 * @return 如果插入成功返回true，否则返回false
	 */
	public static boolean addFile(String owner,String md5,String abspath,String realpath){
		 java.sql.Connection connect=ConnectPSQL.getDBConn();//获取驱动
		 String sql = "insert into file_table(owner,md5,available,abspath,realpath) values(?,?,?,?,?)";//使用sql语句
			java.sql.PreparedStatement stat;
			try {
				stat = connect.prepareStatement(sql);//执行与编译
				stat.setString(1, owner);
				stat.setString(2, md5);
				stat.setInt(3, 1);
				stat.setString(4, abspath);
				stat.setString(5, realpath);
				stat.addBatch();//添加到与编译里面
				int [] contern=stat.executeBatch();
				if(contern.length>0){//如果
					return true;
				}
				
			} catch (SQLException e) {
				System.out.println(e.getNextException());
				e.printStackTrace();
			}
		return false;
	}
	/**
	 * 往表格里面插入一个新建文件的消息，表明在集群上创建了一个文件
	 * @param owner 文件的所有者
	 * @param md5 文件的md5值
	 * @param abspath 文件的抽象路径
	 * @param realpath 文件在集群上的真实路径
	 * @param ftype 文件的类型
	 * @param fsize 文件的大小
	 * @param fdate 文件上传的日期
	 * @param other 其他对文件的描述
	 * @return 如果插入成功返回true，否则返回false
	 */
	public static boolean addFile(String owner,String md5,String abspath,String realpath,String ftype,long fsize,String fdate,String other){
		 java.sql.Connection connect=ConnectPSQL.getDBConn();//获取驱动
		 String sql = "insert into file_table(owner,md5,available,abspath,realpath,ftype,fsize,fdate,other) values(?,?,?,?,?,?,?,?,?)";//使用sql语句
			java.sql.PreparedStatement stat;
			try {
				stat = connect.prepareStatement(sql);//执行与编译
				stat.setString(1, owner);
				stat.setString(2, md5);
				stat.setInt(3, 1);
				stat.setString(4, abspath);
				stat.setString(5, realpath);
				stat.setString(6, ftype);
				stat.setLong(7, fsize);
				stat.setString(8, fdate);
				stat.setString(9, other);
				stat.addBatch();//添加到与编译里面
				int [] contern=stat.executeBatch();
				if(contern.length>0){//如果
					return true;
				}
				
			} catch (SQLException e) {
				System.out.println(e.getNextException());
				e.printStackTrace();
			}
		return false;
	}
	/**
	 * 根据md5值获取真实的文件路径
	 * @param md5 文件的md5值
	 * @return 如果有返回真实文件的路径，否则返回null
	 */
	public static String getRealpaht(String md5){
		 java.sql.Connection connect=ConnectPSQL.getDBConn();//获取驱动
		 String sql = "select * from file_table where md5=?";//使用sql语句
			java.sql.PreparedStatement stat;
			try {
				stat = connect.prepareStatement(sql);//执行与编译
				stat.setString(1, md5);
				stat.addBatch();//添加到与编译里面
				ResultSet rs=stat.executeQuery();
				if(rs.next()){
					return rs.getString(6);
				}
			} catch (SQLException e) {
				System.out.println(e.getNextException());
				e.printStackTrace();
			}
		return null;
	}
	
	/**
	 * 根据文件所有者和文件抽象路径获取文件真实路径的方法
	 * @param owner 文件所有者
	 * @param abspath 文件的抽象路径
	 * @return 如果操作成功返回真实文件路径，否则返回null
	 */
	public static String getRealpaht(String owner,String abspath){
		 java.sql.Connection connect=ConnectPSQL.getDBConn();//获取驱动
		 String sql = "select * from file_table where owner=? and available=? and abspath=?";//使用sql语句
			java.sql.PreparedStatement stat;
			try {
				stat = connect.prepareStatement(sql);//执行与编译
				stat.setString(1, owner);
				stat.setInt(2, 1);//文件必须可用
				stat.setString(3, abspath);
				stat.addBatch();//添加到与编译里面
				ResultSet rs=stat.executeQuery();
				if(rs.next()){
					return rs.getString(6);//返回结果
				}
			} catch (SQLException e) {
				System.out.println(e.getNextException());
				e.printStackTrace();
			}
		return null;
	}
	/**
	 * 根据文件的所有者返回该所有着的所有抽象文件路径，也就是获取跟所有者所有的文件视图
	 * @param owner 文件的所有者
	 * @return 如果操作成功，返回所用的抽象文件路径队列，否则返回null
	 */
	public static ArrayList<String> getAbspaths(String owner){
		 java.sql.Connection connect=ConnectPSQL.getDBConn();//获取驱动
		 String sql = "select * from file_table where owner=? and available=?";//使用sql语句
			java.sql.PreparedStatement stat;
			try {
				stat = connect.prepareStatement(sql);//执行与编译
				stat.setString(1, owner);
				stat.setInt(2, 0);//文件必须可用
				stat.addBatch();//添加到与编译里面
				ResultSet rs=stat.executeQuery();
				ArrayList<String> flist=new ArrayList<String>();//创建一个文件队列
				while(rs.next()){
					flist.add(rs.getString(5));//保存结果
				}
				return flist;
			} catch (SQLException e) {
				System.out.println(e.getNextException());
				e.printStackTrace();
			}
		return null;
	}
	/**
	 * 将数据库中文件的状态（available）改变，如果该值为0，表示该文件不可用，从而达到删除文件的目的
	 * @param owner 文件的所有者
	 * @param abspath 文件的抽象路径
	 * @return 如果操作成功返回true，否则返回false
	 */
	public static boolean removeFile(String owner,String abspath){
		 java.sql.Connection connect=ConnectPSQL.getDBConn();//获取驱动
		 String sql = "update file_table set available=? where owner=? and abspath=?";//使用sql语句
			java.sql.PreparedStatement stat;
			try {
				stat = connect.prepareStatement(sql);//执行与编译
				stat.setInt(1, 0);//0表示不可用
				stat.setString(2, owner);
				stat.setString(3, abspath);
				stat.addBatch();//添加到与编译里面
				int [] contern=stat.executeBatch();
				if(contern.length>0){//如果
					return true;
				}
				
			} catch (SQLException e) {
				System.out.println(e.getNextException());
				e.printStackTrace();
			}
		return false;
	}
	public static boolean removeFileonDB(String owner,String abspath){
		 java.sql.Connection connect=ConnectPSQL.getDBConn();//获取驱动
		 String sql = "delete from file_table where owner=? and abspath=?";//使用sql语句
			java.sql.PreparedStatement stat;
			try {
				stat = connect.prepareStatement(sql);//执行与编译
				stat.setString(1, owner);
				stat.setString(2, abspath);
				stat.addBatch();//添加到与编译里面
				int [] contern=stat.executeBatch();
				if(contern.length>0){//如果
					return true;
				}
				
			} catch (SQLException e) {
				System.out.println(e.getNextException());
				e.printStackTrace();
			}
		return false;
	}
	/**
	 * 将文件的状态（available）改为指定的状态的方法
	 * @param owner 文件的所有者
	 * @param abspath 文件的抽象路径
	 * @param available 文件的状态
	 * @return 如果操作成功返回true，否则返回false
	 */
	public static boolean updateAvailable(String owner,String abspath,int available){
		 java.sql.Connection connect=ConnectPSQL.getDBConn();//获取驱动
		 String sql = "update file_table set available=? where owner=? and abspath=?";//使用sql语句
			java.sql.PreparedStatement stat;
			try {
				stat = connect.prepareStatement(sql);//执行与编译
				stat.setInt(1, available);
				stat.setString(2, owner);
				stat.setString(3, abspath);
				stat.addBatch();//添加到与编译里面
				int [] contern=stat.executeBatch();
				if(contern.length>0){//如果
					return true;
				}
				
			} catch (SQLException e) {
				System.out.println(e.getNextException());
				e.printStackTrace();
			}
		return false;
	}
	/**
	 * 更改文件抽象路径的方法
	 * @param owner 文件的所有者
	 * @param oldabspath 原来的抽象文件路径
	 * @param newabspath 新的抽象文件路径
	 * @return 如果操作成功返回true，否则返回false
	 */
	public static boolean updateAbspath(String owner,String oldabspath,String newabspath){
		 java.sql.Connection connect=ConnectPSQL.getDBConn();//获取驱动
		 String sql = "update file_table set abspath=? where owner=? and abspath=?";//使用sql语句
			java.sql.PreparedStatement stat;
			try {
				stat = connect.prepareStatement(sql);//执行与编译
				stat.setString(1, newabspath);
				stat.setString(2, owner);
				stat.setString(3, oldabspath);
				stat.addBatch();//添加到与编译里面
				int [] contern=stat.executeBatch();
				if(contern.length>0){//如果
					return true;
				}
				
			} catch (SQLException e) {
				System.out.println(e.getNextException());
				e.printStackTrace();
			}
		return false;
	}
    
	/**
	 * 更改文件描述的方法
	 * @param owner 文件的所有者
	 * @param abspath 文件的抽象路径
	 * @param other 更改后的描述
	 * @return 如果操作成功返回true，否则返回false
	 */
	public static boolean updateOther(String owner,String abspath,String other){
		 java.sql.Connection connect=ConnectPSQL.getDBConn();//获取驱动
		 String sql = "update file_table set other=? where owner=? and abspath=?";//使用sql语句
			java.sql.PreparedStatement stat;
			try {
				stat = connect.prepareStatement(sql);//执行与编译
				stat.setString(1, other);
				stat.setString(2, owner);
				stat.setString(3, abspath);
				stat.addBatch();//添加到与编译里面
				int [] contern=stat.executeBatch();
				if(contern.length>0){//如果
					return true;
				}
				
			} catch (SQLException e) {
				System.out.println(e.getNextException());
				e.printStackTrace();
			}
		return false;
	}
	/**
	 * 这是一个更改文件日期的方法
	 * @param owner 文件的所有者
	 * @param abspath 文件的抽象路径
	 * @param fdate 文件的日期
	 * @return 如果操作成功返回true，否则返回false
	 */
	public static boolean updateDate(String owner,String abspath,String fdate){
		 java.sql.Connection connect=ConnectPSQL.getDBConn();//获取驱动
		 String sql = "update file_table set fdate=? where owner=? and abspath=?";//使用sql语句
			java.sql.PreparedStatement stat;
			try {
				stat = connect.prepareStatement(sql);//执行与编译
				stat.setString(1, fdate);
				stat.setString(2, owner);
				stat.setString(3, abspath);
				stat.addBatch();//添加到与编译里面
				int [] contern=stat.executeBatch();
				if(contern.length>0){//如果
					return true;
				}
				
			} catch (SQLException e) {
				System.out.println(e.getNextException());
				e.printStackTrace();
			}
		return false;
	}
}
