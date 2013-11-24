package Request;

/**
 * 移动文件请求类
 * @author wojiaolongyinong
 *
 */
public class MovingRequest extends Request{
	/**
	 * 移动前的抽象路径
	 */
	private String oldPath;
	
	/**
	 * 移动后的抽象路径
	 */
	private String nowPath;
	
	/**
	 * 重写构造函数，初始化对象的属性
	 * @param ID	用户帐号
	 * @param oldPath	移动前的抽象路径
	 * @param nowPath	移动后的抽象路径
	 */
	public MovingRequest(String ID,String oldPath,String nowPath){
		super.setID(ID);
		this.oldPath = oldPath;
		this.nowPath = nowPath;
	}
	
	/**
	 * 获取移动前的路径
	 * @return
	 */
	public String getOldPath() {
		return oldPath;
	}
	
	/**
	 * 设置移动前的路径
	 * @param oldPath
	 */
	public void setOldPath(String oldPath) {
		this.oldPath = oldPath;
	}
	
	/**
	 * 返回此时的路径
	 * @return
	 */
	public String getNowPath() {
		return nowPath;
	}
	
	/**
	 * 设置此时的路径
	 * @param nowPath
	 */
	public void setNowPath(String nowPath) {
		this.nowPath = nowPath;
	}
	
	
}
