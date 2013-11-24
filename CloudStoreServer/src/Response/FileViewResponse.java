package Response;

import java.util.List;

/**
 * 文件视图响应
 * @author wojiaolongyinong
 *
 */
public class FileViewResponse extends Response{
	/**
	 * 用于存储文件视图的字符串队列
	 */
	private List<String>  file;
	
	/**
	 * 创建文件视图响应对象
	 * @param id	用户账户
	 * @param file	文件视图对象
	 */
	public FileViewResponse(String id,List<String> file){
		super.setID(id);
		this.file = file;
	}
	
	/**
	 * 重写构造函数，初始化文件视图队列属性
	 * @param file	存储文件视图的字符串队列
	 */
	public FileViewResponse(List<String> file){
		this.file = file;
	}
	
	/**
	 * 返回文件视图字符串队列
	 * @return	返回文件视图字符串队列
	 */
	public List<String> getFileView() {
		return file;
	}
	
	/**
	 * 设置文件视图属性
	 * @param file	文件视图字符串属性
	 */
	public void setFileView(List<String> file){
		this.file = file;
	}
}
