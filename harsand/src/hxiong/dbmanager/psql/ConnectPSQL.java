package hxiong.dbmanager.psql;

import java.sql.DriverManager;

/**
 * 这是一个获取psql数据库驱动的类，该类提供多种获取数据库驱动的方法
 * 比如根据用户名和密码获取默认数据库的连接对象
 * 根据用户名和密码以及数据名获取数据库连接对象
 * @author 熊天成
 *
 */
public class ConnectPSQL {
     
	 private static java.sql.Connection dbconn = null;//数据库的连接对象
	 private ConnectPSQL()//重载构造方法，防止使用该类生成对象
     {    	 
     }
	 /**
	  * 获取默认或者上一次的连接对象
	  * @return 返回数据库连接对象
	  */
     public static java.sql.Connection getDBConn() {
 		if (dbconn != null) {//如果连接对象已经创建或存在
 			return dbconn;
 		}else{
 		try {
 			Class.forName("org.postgresql.Driver").newInstance();
 			//
 			String url = "jdbc:postgresql://localhost:5432/metastore";
 			//h获得连接
 			dbconn = DriverManager.getConnection(url, "postgres", "xiong");
 			return dbconn;//返回服务器的连接对象

 		 } catch (Exception ef) {
 			ef.printStackTrace();
 			return null;
 		 }
 	  }
 	}
     /**
      * 根据帐号和用户名获取默认或者上一次的连接对象
      * @param name 用户名
      * @param passwd 用户密码
      * @return 返回数据库连接对象
      */
     public static java.sql.Connection getDBConn(String name,String passwd) {
  		try {
  			Class.forName("org.postgresql.Driver").newInstance();
  			//
  			String url = "jdbc:postgresql://localhost:5432/metastore";
  			//h获得连接
  			dbconn = DriverManager.getConnection(url, name, passwd);
  			return dbconn;//返回服务器的连接对象

  		 } catch (Exception ef) {
  			ef.printStackTrace();
  			return null;
  		 }
  	}
     /**
      * 根据数据库连接的ip端口号，数据库名，帐号和用户名获取默认或者上一次的连接对象
      * @param ipport 数据库连接的ip端口号
      * @param dbname 数据库连接的名字
      * @param name 数据库所有者的名字
      * @param passwd 用户密码
      * @return 如果成功返回数据库连接对象，否则返回null
      */
     public static java.sql.Connection getDBConn(String ipport,String dbname,String name,String passwd) {
   		try {
   			Class.forName("org.postgresql.Driver").newInstance();
   			//
   			String url = "jdbc:postgresql://";
   			url=url+ipport+"/"+dbname;
   			//h获得连接
   			dbconn = DriverManager.getConnection(url, name, passwd);
   			return dbconn;//返回服务器的连接对象

   		 } catch (Exception ef) {
   			ef.printStackTrace();
   			return null;
   		 }
   	}
}
