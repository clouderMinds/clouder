package hlong.Request;

/**
 * 当文件在服务器不存在时，上传文件
 * 上传文件的请求类
 * @author wojiaolongyinong
 *
 */
public class UploadRequest extends Request{
	/**
	 * 文件的抽象路径
	 */
	private String absPath;
	//本地文件路径
	private String localPaht;
		
	/**
	 * 文件的MD5值
	 */
	private String MD5;
	
	/**
	 * 具体上传的文件的数据
	 */
	private byte[] data;
	
	/**
	 * 重写构造函数，初始化对象的属性
	 * @param ID	用户的帐号
	 * @param absPath	文件的抽象路径
	 * @param MD5	文件的MD5值
	 * @param data	文件的具体数据
	 */
	public UploadRequest(String ID,String absPath,String localPaht,String MD5, byte[] data){
		super.setID(ID);
		this.absPath = absPath;
		this.localPaht=localPaht;
		this.MD5 = MD5;
		this.data = data;
	}
	
	/**
	 * 返回文件的抽象路径
	 * @return
	 */
	public String getAbsPath() {
		return absPath;
	}
	
	/**
	 * 设置文件的抽象路径
	 * @param absPath
	 */
	public void setAbsPath(String absPath) {
		this.absPath = absPath;
	}
	
	/**
	 * 返回文件的MD5值
	 * @return
	 */
	public String getMD5() {
		return MD5;
	}
	
	/**
	 * 设置文件的MD5值
	 * @param mD5
	 */
	public void setMD5(String mD5) {
		MD5 = mD5;
	}
	/**
	 * 
	 * @return
	 */
	public String getLocalPaht() {
		return localPaht;
	}
	/**
	 * 
	 * @param localPaht
	 */
	public void setLocalPaht(String localPaht) {
		this.localPaht = localPaht;
	}
	/**
	 * 返回上传的数据
	 * @return
	 */
	public byte[] getData() {
		return data;
	}
	
	/**
	 * 设置上传的数据
	 * @param data
	 */
	public void setData(byte[] data) {
		this.data = data;
	}
	
}
