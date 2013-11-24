package Response;

/**
 * 下载文件响应对象
 * @author wojiaolongyinong
 *
 */
public class DownloadResponse extends Response{
	/**
	 * 返回文件的数据
	 */
	private byte[] data;
	
	/**
	 * 设置下载的文件数据
	 * @param data	文件数据
	 */
	public DownloadResponse(String id,byte[] data){
		super.setID(id);
		this.data = data;
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
