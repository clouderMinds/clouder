package hlong.Response;
/**
 * 本类作为所有的响应的父类使用，因为所有的响应需要指定
 * 目标，因此都需要继承此类，来指定发送的目标
 * @author wojiaolongyinong
 *
 */
public abstract class Response {
	/**
	 * 发送目标的帐号（邮箱）
	 */
	private String ID;
	
	/**
	 * 设置发送目标的帐号
	 * @param ID
	 */
	public void setID(String ID){
		this.ID = ID;
	}
	
	/**
	 * 返回发送目标的帐号
	 * @return
	 */
	public String getID(){
		return ID;
	}
}
