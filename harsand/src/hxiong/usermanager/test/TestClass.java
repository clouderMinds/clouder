package hxiong.usermanager.test;

import hxiong.dbmanager.psql.ConnectPSQL;

/**
 * 这是一个测试类，负责测试写的一些类是否可用
 * @author 熊天成
 *
 */
public class TestClass {
	//主函数入口
    public static void main(String args[]){
    	ConnectPSQL.getDBConn();
    	
    }
}
