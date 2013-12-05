package hlong.Request;

/**
 * 下载请求类
 * @author wojiaolongyinong
 *
 */
public class DownloadRequest extends Request{
	/**
	 * 需要下载的文件的抽象路径
	 */
	private String absPath;
	
	/**
	 * 重写构造函数，初始化对象的属性
	 * @param ID	用户的帐号
	 * @param absPath	文件的抽象路径
	 */
	public DownloadRequest(String ID, String absPath){
		super.setID(ID);
		this.absPath = absPath;
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
	
	
}
