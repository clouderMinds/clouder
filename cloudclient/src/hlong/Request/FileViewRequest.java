package hlong.Request;

public class FileViewRequest extends Request{
	/**
	 * 登陆的密码
	 */
	private String password;
	
	/**
	 * 重写构造函数，初始化对象的属性
	 */
	public FileViewRequest(String ID,String password){
		super.setID(ID);
		this.password = password;
	}
	//只需要用户的帐号即可
	public FileViewRequest(String ID){
		super.setID(ID);
	}
	/**
	 * 返回密码
	 * @return
	 */
	public String getPassword() {
		return password;
	}
	
	/**
	 * 设置密码
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
}
