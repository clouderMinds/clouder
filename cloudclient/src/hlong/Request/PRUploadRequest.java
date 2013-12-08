package hlong.Request;

/**
 * 上传的请求文件验证是否存在类
 * @author wojiaolongyinong
 *
 */
public class PRUploadRequest extends Request{
	/**
	 * 上传文件的抽象路径
	 */
	private String absPath;
	//本地文件路径
	private String localPath;
	
	/**
	 * 上传文件的MD5值
	 */
	private String MD5;
	/**
	 * 创建上传文件验证是否存在的请求对象
	 * @param id 用户帐号
	 * @param absPath 抽象的路径
	 * @param localPaht 本地文件路径
	 * @param MD5 MD5值
	 */
	public PRUploadRequest(String id,String absPath,String localPath,String MD5){
		super.setID(id);
		this.absPath = absPath;
		this.localPath=localPath;
		this.MD5 = MD5;
	}
	
	/**
	 * 获取文件的抽象路径
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
	 * 
	 * @return
	 */
	public String getLocalPaht() {
		return localPath;
	}
	/**
	 * 
	 * @param localPaht
	 */
	public void setLocalPaht(String localPath) {
		this.localPath = localPath;
	}
	/**
	 * 获取文件的MD5值
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
}
