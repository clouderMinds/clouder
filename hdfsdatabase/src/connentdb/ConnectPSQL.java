package connentdb;//数据库的连接类

import java.sql.DriverManager;
import java.sql.SQLException;

/**zheshi longyinongde
 * 连接postgresql数据库的一个类，
 * 该类会提供返回与数据库连接对象的方法
 * @author 熊天成
 *
 */
public class ConnectPSQL {
	 private static java.sql.Connection conn = null;//数据库的连接对象
     private ConnectPSQL(){}
    
     
     /**
      * 一个获取连接对象的方法,默认的方法
      * @return 返回与数据库的连接对象
      */
     public static java.sql.Connection getConn() {
 		if (conn != null) {//如果连接对象已经创建或存在
 			return conn;
 		}else{
 		try {
 			Class.forName("org.postgresql.Driver").newInstance();
 			//
 			String url = "jdbc:postgresql://localhost:5432/metastore";
 			//h获得连接
 			conn = DriverManager.getConnection(url, "postgres", "xiong");
 			return conn;//·

 		 } catch (Exception ef) {
 			ef.printStackTrace();
 			return null;
 		 }
 	  }
 	}
     
     /**
      * 提供根据根据ip和端口号连接数据库的方法
      * @param db_ipport 数据库的ip端口号
      * @return 返回与数据库的连接
      */
     public static java.sql.Connection getConnByIPPort(String db_ipport) {
  		try {
  			Class.forName("org.postgresql.Driver").newInstance();
  			//
  			String url = "jdbc:postgresql://"+db_ipport+"/metastore";
  			//获得连接
  			java.sql.Connection connect = DriverManager.getConnection(url, "postgres", "xiong");
  			
  			return connect;//获取与数据库的连接

  		 } catch (Exception ef) {
  			ef.printStackTrace();
  			return null;
  		 } 
     }
     
     /**
      * 根据详细信息而获取数据库连接的方法
      * @param db_ipport 数据库的地址
      * @param db_name 数据库的名字
      * @param db_user 数据库的拥有者
      * @param db_passwd 数据库的密码
      * @return 返回与数据库的连接
      */
     public static java.sql.Connection getConnByInput(String db_ipport,String db_name,String db_user,String db_passwd) {
   		
   			try {
   				//获取驱动对象
				Class.forName("org.postgresql.Driver").newInstance();
				String url = "jdbc:postgresql://"+db_ipport+"/"+db_name;
				
				java.sql.Connection connect = DriverManager.getConnection(url, db_user, db_passwd);
			   
				return connect;//返回连接对象
		
   			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}catch (SQLException e) {
				e.printStackTrace();
			}
   			return null;

      }
}
