package hlong.Response;
/**
 * 实现一个响应对象，方便一些操作的响应
 * 只包括响应状态以及响应目标帐号
 * @author wojiaolongyinong
 *
 */
public class SimpleResponse extends Response{
	/**
	 * 表示请求成功
	 */
	public static final byte SUCCESS = 0x00;
	
	/**
	 * 请求失败
	 */
	public static final byte FAILED = 0x01;
	
	private byte state;
	
	/**
	 * 创建一个简单的响应对象
	 * SimpleResponse.SUCCESS 表示请求成功
	 * SimpleResponse.FAILED  表示请求失败
	 * @param ID	响应的对象的帐号（邮箱）
	 * @param state 响应的状态（SimpleResponse.SUCCESS或是SimpleResponse.FAILED）
	 */
	public SimpleResponse(String ID,byte state){
		if(state != SUCCESS || state != FAILED){
			throw new RuntimeException("传进的state不存在，"
					+ "需要传进 SimpleResponse.SUCCESS 或是"
					+ "SimpleResponse.FAILED");
		}else if(ID == null || ID.trim().equals("")){
			throw new RuntimeException("请不要将ID设为mull或是为空");
		}else{
			super.setID(ID);
			this.state = state;
		}
	}
	
	/**
	 * 返回响应数据包中的state
	 * @return	响应状态state
	 */
	public byte getState(){
		return state;
	}
}
