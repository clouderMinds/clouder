package Utils;
/**
 * 用于存放所有数据包的标示
 * @author LONG
 *
 */
public interface DataPackageType {
	/**
	 * 注册数据包标示
	 */
	public static final byte REGISTER = 0x01;
	
	/**
	 * 响应注册
	 */
	public static final byte RE_REGISTER = 0x11;
	
	/**
	 * 登陆数据包标示
	 */
	public static final byte LOGIN = 0x02;
	
	/**
	 * 响应登陆
	 */
	public static final byte RE_LOGIN = 0x12;
	
	/**
	 * 上传文件数据包标示
	 */
	public static final byte UPLOAD = 0x08;
	
	/**
	 * 响应上传文件
	 */
	public static final byte RE_UPLOAD = 0x18;
	
	/**
	 * 下载文件数据包标示
	 */
	public static final byte DOWNLOAD = 0x07;
	
	/**
	 * 响应下载
	 */
	public static final byte RE_DOWNLOAD = 0x17;
	
	/**
	 * 删除文件数据包标示
	 */
	public static final byte DELETE = 0x06;
	
	/**
	 * 响应删除
	 */
	public static final byte RE_DELETE = 0x16;
	
	/**
	 * 移动文件数据包标示
	 */
	public static final byte MOVING = 0x05;
	
	/**
	 * 响应移动
	 */
	public static final byte RE_MOVING = 0x15;
	
	/**
	 * 重命名文件数据包的标示
	 */
	public static final byte RENAME = 0x04;
	
	/**
	 * 响应重命名
	 */
	public static final byte RE_RENAME = 0x14;
	
	/**
	 * 创建新文件夹数据包标示
	 */
	public static final byte CREATEDIR = 0x03;
	
	/**
	 * 响应创建新的文件夹
	 */
	public static final byte RE_CREATEDIR = 0x13;
}
