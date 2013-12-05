package hlong.Utils;

/**
 * 用于存放所有数据包的标示
 * @author wojiaolongyinong
 *
 */
public interface DataPackageType {
	/**
	 * 注册数据包标示
	 */
	public static final byte REGISTER = 0x01;
	public static final String REGISTER_S = "RegisterRequest";
	/**
	 * 响应注册
	 */
	public static final byte RE_REGISTER = 0x11;
	public static final String RE_REGISTER_S = "RegisterResponse";
	
	/**
	 * 文件视图标示
	 */
	public static final byte FILE_VIEW = 0x09;
	public static final String FILE_VIEW_S = "FileViewResponse";
	
	/**
	 * 登陆数据包标示
	 */
	public static final byte LOGIN = 0x02;
	public static final String LOGIN_S = "LoginRequest";
	
	/**
	 * 响应登陆
	 */
	public static final byte RE_LOGIN = 0x12;
	public static final String RE_LOGIN_S = "LoginResponse";
	
	/**
	 * 上传文件数据包标示
	 */
	public static final byte UPLOAD = 0x08;
	public static final String UPLOAD_S = "UploadRequest";
	
	/**
	 * 上传检测数据包标示
	 */
	public static final byte PR_UPLOAD = 0x28;
	public static final String PR_UPLOAD_S = "PRUploadRequest";
	
	/**
	 * 上传检测响应数据包标示
	 */
	public static final byte RE_PR_UPLOAD = 0x38;
	public static final String RE_PR_UPLOAD_S = "PreUploadResponse";
	
//	/**
//	 * 响应上传文件
//	 */
//	public static final byte RE_UPLOAD = 0x18;
//	public static final String RE_UPLOAD_S = "FileViewResponse";
	
	/**
	 * 下载文件数据包标示
	 */
	public static final byte DOWNLOAD = 0x07;
	public static final String DOWNLOAD_S = "DownloadRequest";
	
	/**
	 * 响应下载
	 */
	public static final byte RE_DOWNLOAD = 0x17;
	public static final String RE_DOWNLOAD_S = "DownloadResponse";
	
	/**
	 * 删除文件数据包标示
	 */
	public static final byte DELETE = 0x06;
	public static final String DELETE_S = "DeleteRequest";
	
//	/**
//	 * 响应删除
//	 */
//	public static final byte RE_DELETE = 0x16;
//	public static final String RE_DELETE_S = "FileViewResponse";
	
	/**
	 * 移动文件数据包标示
	 */
	public static final byte MOVING = 0x05;
	public static final String MOVING_S = "MovingRequest";
	
//	/**
//	 * 响应移动
//	 */
//	public static final byte RE_MOVING = 0x15;
//	public static final String RE_MOVING_S = "FileViewResponse";
	
	/**
	 * 重命名文件数据包的标示
	 */
	public static final byte RENAME = 0x04;
	public static final String RENAME_S = "RenameRequest";
	
//	/**
//	 * 响应重命名
//	 */
//	public static final byte RE_RENAME = 0x14;
//	public static final String RE_RENAME_S = "FileViewResponse";
	
	/**
	 * 创建新文件夹数据包标示
	 */
	public static final byte CREATEDIR = 0x03;
	public static final String CREATEDIR_S = "CreateDirRequest";
	
	/**
	 * 简单响应数据包标示
	 */
	public static final byte SIM_RESPONSE = 0x19;
	public static final String SIM_RESPONSE_S = "SimpleResponse";
	
//	/**
//	 * 响应创建新的文件夹
//	 */
//	public static final byte RE_CREATEDIR = 0x13;
//	public static final String RE_CREATEDIR_S = "FileViewResponse";
}
