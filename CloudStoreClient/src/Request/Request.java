package Request;
/**
 * 所有请求的父类
 * @author LONG
 *
 */
public abstract class Request {
	/**
	 * 帐号
	 */
	private String ID;
	
	/**
	 * 获取帐号
	 * @return
	 */
	public String getID() {
		return ID;
	}
	
	/**
	 * 设置帐号
	 * @param iD
	 */
	public void setID(String iD) {
		ID = iD;
	}
	
	
}
