package Request;
/**
 * 重命名请求类
 * @author LONG
 *
 */
public class RenameRequest extends Request{
	/**
	 * 重命名前的抽象路径（里面包含文件名）
	 */
	private String oldPath;
	
	/**
	 * 重命名后的抽象路径
	 */
	private String nowPath;
	
	/**
	 * 创建一个重命名请求类
	 * @param id	用户账户
	 * @param oldPath	重命名前的路径
	 * @param nowPath	此时的路径
	 */
	public RenameRequest(String id,String oldPath,String nowPath){
		super.setID(id);
		this.oldPath = oldPath;
		this.nowPath = nowPath;
	}
	
	/**
	 * 返回重命名之前的抽象路径（其中包含文件名）
	 * @return
	 */
	public String getOldPath() {
		return oldPath;
	}
	
	/**
	 * 设置重命名之前的抽象路径
	 * @param oldPath
	 */
	public void setOldPath(String oldPath) {
		this.oldPath = oldPath;
	}
	
	/**
	 * 返回重命名之后的抽象路径
	 * @return
	 */
	public String getNowPath() {
		return nowPath;
	}
	
	/**
	 * 设置重命名之后的抽象路径
	 * @param nowPath
	 */
	public void setNowPath(String nowPath) {
		this.nowPath = nowPath;
	}
	
	
}
