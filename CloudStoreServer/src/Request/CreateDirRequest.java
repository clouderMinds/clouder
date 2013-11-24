package Request;

/**
 * 创建新文件夹请求类
 * @author wojiaolongyinong
 *
 */
public class CreateDirRequest extends Request{
	/**
	 * 抽象的文件路径
	 */
	private String absPath;
	
	/**
	 * 重写构造函数，初始化对象的属性
	 * @param ID	用户帐号
	 * @param absPath	创建的文件的抽象路径
	 */
	public CreateDirRequest(String ID,String absPath){
		super.setID(ID);
		this.absPath = absPath;
	}
	
	/**
	 * 获得抽象路径
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
