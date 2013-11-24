package Request;
/**
 * 所有请求的父类
 * @author LONG
 *
 */
public abstract class Request {
	/**
	 * 发送者的帐号
	 */
	private String ID;
	
	/**
	 * 获取发送者的帐号
	 * @return
	 */
	public String getID() {
		return ID;
	}
	
	/**
	 * 设置发送者帐号
	 * @param iD
	 */
	public void setID(String iD) {
		ID = iD;
	}
	
	
}
