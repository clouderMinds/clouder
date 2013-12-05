package hlong.Request;

/**
 * 注册请求数据包类
 * @author wojiaolongyinong
 *
 */
public class RegisterRequest extends Request{
	/**
	 * 密码
	 */
	private String password;
	
	public RegisterRequest(String ID,String password){
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
