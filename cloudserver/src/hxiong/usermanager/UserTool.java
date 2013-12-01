package hxiong.usermanager;

import hxiong.dbmanager.psql.HdfsuserPSQL;

/**
 * 这是一个用户管理类，主要提同添加用户
 * 删除用户等操作
 * @author 熊天成
 *
 */
public class UserTool {
    
	/**
	 * 往数据库里面添加一个用户帐号的方法
	 * @param account 用户 的帐号
	 * @param password 用户的密码
	 * @param nickname 用户的昵称
	 * @return 如果添加成功返回true，否则返回false
	 */
	public boolean addUser(String account,String password,String nickname){
		
		return HdfsuserPSQL.addHDFSUser(nickname, account, password);
	}
	
	/**
	 * 根据帐号和密码验证用户是否存在的方法，也就是验证登录用户是否合法
	 * @param account 用户的帐号 
	 * @param password用户的密码
	 * @return 如果验证成功返回true 否则返回false
	 */
	public boolean isHDFSUser(String account,String password){
		
		return HdfsuserPSQL.CheckHDFSUser(account, password);
	}
	
	/**
	 * 根据用户帐号删除指定用户的方法
	 * @param account用户的帐号
	 * @return 如果删除成功，返回true，否则返回false
	 */
	public boolean deleteUser(String account){
		
		return HdfsuserPSQL.deleteHDFSUser(account);
	}
}
