package hlong.Request;

/**
 * 登陆请求类
 * @author wojiaolongyinong
 *
 */
public class LoginRequest extends Request{
	/**
	 * 登陆的密码
	 */
	private String password;
	
	/**
	 * 重写构造函数，初始化对象的属性
	 * @param ID	帐号（邮箱）
	 * @param password	密码
	 */
	public LoginRequest(String ID,String password){
		super.setID(ID);
		this.password = password;
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
