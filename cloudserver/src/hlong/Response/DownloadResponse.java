package hlong.Response;

/**
 * 下载文件响应对象
 * @author wojiaolongyinong
 *
 */
public class DownloadResponse extends Response{
	/**
	 * 表示已经操作成功
	 */
	public static final byte SUCCESS = 0x01;
	
	/**
	 * 表示操作失败
	 */
	public static final byte FAIL = 0x02;
	/**
	 * 本地文件路径
	 */
	private String localPath;
	
	/**
	 * 表示此对象中的状态
	 */
	private byte state;
	/**
	 * 返回文件的数据
	 */
	private byte[] data;
	
	/**
	 * 设置下载的文件数据
	 * @param data	文件数据
	 */
	public DownloadResponse(String id,String localPath,byte state,byte[] data){
		super.setID(id);
		this.localPath=localPath;
		this.state=state;
		this.data = data;
	}
	 /**
     * 本地的文件路径
     * @return
     */
	public String getLocalPath() {
		return localPath;
	}

	public void setLocalPath(String localPath) {
		this.localPath = localPath;
	}
	/**
	 * 返回文件是否存在的状态
	 * @return
	 */
	public byte getState() {
		return state;
	}
	
	/**
	 * 设置文件是否存在的状态
	 * @param state
	 */
	public void setState(byte state) {
		this.state = state;
	}
	/**
	 * 返回文件数据
	 * @return
	 */
	public byte[] getData() {
		return data;
	}
	
	/**
	 * 设置文件数据
	 * @param data
	 */
	public void setData(byte[] data) {
		this.data = data;
	}
	
}
