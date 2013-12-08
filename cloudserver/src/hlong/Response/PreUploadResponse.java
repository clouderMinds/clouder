package hlong.Response;

/**
 * 响应上传前的检查，返回返回文件是否存在
 * @author wojiaolongyinong
 *
 */
public class PreUploadResponse extends Response{
	/**
	 * 表示已经存在
	 */
	public static final byte EXIST = 0x01;
	
	/**
	 * 表示不存在
	 */
	public static final byte NOEXIST = 0x02;
	//本地文件路径
	private String localPaht;
	/**
	 * 表示此对象中的状态
	 */
	private byte state;
	/**
	 * 创建预上传检验响应
	 * @param id 用户帐号
	 * @param localPaht 本地文件路径
	 * @param state 存在状态
	 */
	public PreUploadResponse(String id,String localPaht,byte state){
		super.setID(id);
		this.localPaht=localPaht;
		this.state = state;
	}
	/**
	 * 返回文件是否存在的状态
	 * @return
	 */
	public byte getState() {
		return state;
	}
	
	/**
	 * 设置文件是否存在的状态
	 * @param state
	 */
	public void setState(byte state) {
		this.state = state;
	}
	public String getLocalPaht() {
		return localPaht;
	}
	public void setLocalPaht(String localPaht) {
		this.localPaht = localPaht;
	}
}
