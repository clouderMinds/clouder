package hxiong.dbmanager.psql;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 这是一个操作数据库用户表的类，该类提供添加用户
 * 删除用户，查找用户，修改用户信息等操作
 * @author 熊天成  
 *
 */
public class HdfsuserPSQL {
     
	/**
	 * 往数据库添加一个用户的方法
	 * @param nickname 用户的昵称
	 * @param account 用户的帐号
	 * @param password 用户的密码
	 * @return 如果操作成功返回true，否则返回false
	 */
	 public static boolean  addHDFSUser(String nickname,String account,String password){
		   
		 java.sql.Connection connect=ConnectPSQL.getDBConn();//获取驱动
		 String sql = "insert into hdfsuser(nickname,account,password) values(?,?,?)";//使用sql语句
			java.sql.PreparedStatement stat;
			try {
				stat = connect.prepareStatement(sql);//执行与编译
				stat.setString(1, nickname);
				stat.setString(2, account);
				stat.setString(3, password);
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
	 * 往数据库添加一个用户的方法
	 * @param nickname 用户的昵称
	 * @param account 用户的帐号
	 * @param password 用户的密码
	  * @param available 用户的状态
	  * @param other 其他对用户的描述
	  * @return 如果操作成功返回true，否则返回false
	  */
	 public static boolean  addHDFSUser(String nickname,String account,String password,int available,String other){
		   
		 java.sql.Connection connect=ConnectPSQL.getDBConn();//获取驱动
		 String sql = "insert into hdfsuser(nickname,account,password,available,other) values(?,?,?,?,?)";//使用sql语句
			java.sql.PreparedStatement stat;
			try {
				stat = connect.prepareStatement(sql);//执行与编译
				stat.setString(1, nickname);
				stat.setString(2, account);
				stat.setString(3, password);
				stat.setInt(4, available);
				stat.setString(5, other);
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
	  * 根据帐号删除指定用户的方法，实际上是将该用户标志为不可用
	  * @param account 指定用户的帐号
	  * @return 如果操作成功返回true，否则返回false
	  * update [表名] set [目标字段名]=[目标值] where [该行特征];    删除表中某行数据：  
      * delete from [表名] where [该行特征];
	  */
	 public static boolean deleteHDFSUser(String account){
		 java.sql.Connection connect=ConnectPSQL.getDBConn();//获取驱动
		 String sql = "update hdfsuser set available=? where account=?";//使用sql语句
			java.sql.PreparedStatement stat;
			try {
				stat = connect.prepareStatement(sql);//执行与编译
				stat.setInt(1, 0);
				stat.setString(2, account);
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
	  * 更加用户帐号和密码验证用户是否存在的方法
	  * @param account 用户的帐号
	  * @param password 用户的密码
	  * @return 如果操作成功返回true，否则返回false
	  */
	 public static boolean CheckHDFSUser(String account,String password){
		 java.sql.Connection connect=ConnectPSQL.getDBConn();//获取驱动
		 String sql = "select * from hdfsuser where account=? and password=? and available=?";//使用sql语句
			java.sql.PreparedStatement stat;
			try {
				stat = connect.prepareStatement(sql);//执行与编译
				stat.setString(1, account);
				stat.setString(2, password);
				stat.setInt(3, 1);
				stat.addBatch();//添加到与编译里面
				ResultSet rs=stat.executeQuery();
				if(rs.next()){//如果找到
					return true;
				}
			} catch (SQLException e) {
				System.out.println(e.getNextException());
				e.printStackTrace();
			}
		 return false;
	 }
	 /**
	  * 这是一个强制删除的方法，通过删除表格中指定用户的信息来删除用户
	  * @param account 用户的帐号
	  * @return 如果操作成功返回true，否则返回false
	  */
	 public static boolean deleteUser(String account){
		 java.sql.Connection connect=ConnectPSQL.getDBConn();//获取驱动
		 String sql = "delete hdfsuser from where account=?";//使用sql语句
			java.sql.PreparedStatement stat;
			try {
				stat = connect.prepareStatement(sql);//执行与编译
				stat.setString(1, account);
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
	  * 更改指定用户的昵称的方法
	  * @param account 指定的用户
	  * @param nickname 修改后的昵称
	  * @return 如果操作成功返回true，否则返回false
	  */
	 public static boolean updateUserNickname(String account,String nickname){
		 java.sql.Connection connect=ConnectPSQL.getDBConn();//获取驱动
		 String sql = "update hdfsuser set nickname=? where account=?";//使用sql语句
			java.sql.PreparedStatement stat;
			try {
				stat = connect.prepareStatement(sql);//执行与编译
				stat.setString(1, nickname);
				stat.setString(2, account);
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
	  * 修改指定用户密码的方法
	  * @param account 指定的用户
	  * @param nickname 修改后的昵称
	  * @return 如果操作成功返回true，否则返回false
	  */
	 public static boolean updateUserPassword(String account,String password){
		 java.sql.Connection connect=ConnectPSQL.getDBConn();//获取驱动
		 String sql = "update hdfsuser set password=? where account=?";//使用sql语句
			java.sql.PreparedStatement stat;
			try {
				stat = connect.prepareStatement(sql);//执行与编译
				stat.setString(1, password);
				stat.setString(2, account);
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
	  * 修改用户描述信息的方法
	  * @param account 用户的帐号
	  * @param other 修改后的描述信息
	  * @return 如果操作成功返回true，否则返回false
	  */
	 public static boolean updateUserOther(String account,String other){
		 java.sql.Connection connect=ConnectPSQL.getDBConn();//获取驱动
		 String sql = "update hdfsuser set other=? where account=?";//使用sql语句
			java.sql.PreparedStatement stat;
			try {
				stat = connect.prepareStatement(sql);//执行与编译
				stat.setString(1, other);
				stat.setString(2, account);
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
