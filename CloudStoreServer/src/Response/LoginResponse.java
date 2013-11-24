package Response;
/**
 * 登陆响应对象
 * @author LONG
 *
 */
public class LoginResponse extends Response{
	/**
	 * 登陆成功
	 */
	public static final byte SUCCESS = 0x01;
	
	/**
	 * 密码错误
	 */
	public static final byte PWDERROR = 0x02;
	
	/**
	 * 账户不存在
	 */
	public static final byte IDNOEXIST = 0x03;
	
	/**
	 * 登陆响应状态
	 */
	private byte state;
	
	/**
	 * 创建登陆响应状态对象
	 * @param id	用户账户
	 * @param state	登陆返回状态
	 */
	public LoginResponse(String id,byte state){
		super.setID(id);
		this.state = state;
	}
	
	/**
	 * 返回响应状态
	 * @return
	 */
	public byte getState() {
		return state;
	}
	
	/**
	 * 设置响应状态
	 * @param state
	 */
	public void setState(byte state) {
		this.state = state;
	}
}
