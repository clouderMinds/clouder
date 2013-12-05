package hlong.Response;

/**
 * 响应注册对象
 * @author wojiaolongyinong
 *
 */
public class RegisterResponse extends Response{
	/**
	 * 表示注册成功
	 */
	public static final byte SUCCESS = 0x01;
	
	/**
	 * 表示注册的用户邮箱已经存在
	 */
	public static final byte IDEXIST = 0x02;
	
	/**
	 * 表示此时响应注册对象里面包含的状态
	 */
	private byte state;
	
	/**
	 * 重写构造函数，将注册响应状态写进去
	 * @param state
	 */
	public RegisterResponse(String id,byte state){
		super.setID(id);
		this.state = state;
	}
	
	/**
	 * 获取注册响应状态
	 * @return
	 */
	public byte getState() {
		return state;
	}
	
	/**
	 * 设置注册响应状态
	 * @param state
	 */
	public void setState(byte state) {
		this.state = state;
	}
}
