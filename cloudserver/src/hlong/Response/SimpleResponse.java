package hlong.Response;
/**
 * 实现一个响应对象，方便一些操作的响应
 * 只包括响应状态以及响应目标帐号
 * 因为作为一个方便的响应的数据包，因此在创建
 * 对象的时候需要将响应类型填写进来
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
	
	/**
	 * 响应状态
	 */
	private byte state;
	
	/**
	 * 响应数据包类型
	 */
	private byte type;
	
	/**
	 * 创建一个简单的响应对象
	 * SimpleResponse.SUCCESS 表示请求成功
	 * SimpleResponse.FAILED  表示请求失败
	 * @param ID	响应的对象的帐号（邮箱）
	 * @param state 响应的状态（SimpleResponse.SUCCESS或是SimpleResponse.FAILED）
	 * @param type  数据包的响应类型
	 */
	public SimpleResponse(String ID,byte state,byte type){
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
	
	/**
	 * 返回数据包中封装的响应类型
	 * @return	数据包类型
	 */
	public byte getType(){
		return type;
	}
}
