package hlong.Request;

/**
 * 删除请求类
 * @author wojiaolongyinong
 *
 */
public class DeleteRequest extends Request{
	/**
	 * 需要删除的文件的抽象路径
	 */
	private String absPath;
	
	/**
	 * 重写构造函数，初始化对象的属性
	 * @param ID	用户账户
	 * @param absPath	用户的抽象路径
	 */
	public DeleteRequest(String ID, String absPath){
		super.setID(ID);
		this.absPath = absPath;
	}
	
	/**
	 * 返回抽象路径
	 * @return
	 */
	public String getAbsPath() {
		return absPath;
	}
	
	/**
	 * 设置抽象路径
	 * @param absPath
	 */
	public void setAbsPath(String absPath) {
		this.absPath = absPath;
	}
	
	
}
