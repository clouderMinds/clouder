package GetMessageObject;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

import Request.CreateDirRequest;
import Request.DeleteRequest;
import Request.DownloadRequest;
import Request.LoginRequest;
import Request.MovingRequest;
import Request.PRUploadRequest;
import Request.RegisterRequest;
import Request.RenameRequest;
import Request.UploadRequest;

/**
 * 本类中的所有方法作为静态方法调用
 * 因为本类作为一个工具箱使用
 * @author wojiaolongyinong
 *
 */
public class GetMessage {
	/**
	 * 作为工具箱内部调用使用，将数据流封装为更方便读取的输入流
	 * @param data	待封装的数据
	 * @return	返回封装好的输入流
	 */
	private static DataInputStream getStream(byte[] data){
		ByteArrayInputStream bais = new ByteArrayInputStream(data);
		return new DataInputStream(bais);
	}
	
	/**
	 * 根据数据流返回注册消息对象
	 * @param data
	 * @return
	 * @throws IOException 
	 */
	public static RegisterRequest getRegister(byte[] data) throws IOException{
		DataInputStream dis = getStream(data);
		//读取用户账户的从大小
		int id_length = dis.readInt();
		byte[] id_data = new byte[id_length];
		dis.read(id_data);
		//读出用户账户
		String id = new String(id_data);
		id_length = dis.readInt();
		id_data = new byte[id_length];
		dis.read(id_data);
		int pwd_length = dis.readInt();
		byte[] pwd_data = new byte[pwd_length];
		dis.read(pwd_data);
		String pwd = new String(pwd_data);
		return new RegisterRequest(id, pwd);
	}
	
	/**
	 * 根据数据返回创建文件夹请求对象
	 * @param data	byte数据
	 * @return	创建文件夹请求对象
	 * @throws IOException
	 */
	public static CreateDirRequest getCreateDir(byte[] data) throws IOException{
		DataInputStream dis = getStream(data);
		//读出帐号的长度
		int id_length = dis.readInt();
		//读出帐号
		byte[] id_data = new byte[id_length];
		dis.read(id_data);
		int absPath_length = dis.readInt();
		byte[] absPath_data = new byte[absPath_length];
		dis.read(absPath_data);
		String id = new String(id_data);
		String absPath = new String(absPath_data);
		return new CreateDirRequest(id, absPath);
	}
	
	/**
	 * 根据传进来的数据返回删除请求数据包对象
	 * @param data	byte数据
	 * @return	删除请求对象
	 * @throws IOException
	 */
	public static DeleteRequest getDelete(byte[] data) throws IOException{
		DataInputStream dis = getStream(data);
		//读出帐号的长度
		int id_length = dis.readInt();
		//读出帐号
		byte[] id_data = new byte[id_length];
		dis.read(id_data);
		int absPath_length = dis.readInt();
		byte[] absPath_data = new byte[absPath_length];
		dis.read(absPath_data);
		String id = new String(id_data);
		String absPath = new String(absPath_data);
		return new DeleteRequest(id, absPath);
	}
	
	/**
	 * 根据传进来的数据，返回下载请求对象
	 * @param data	byte数据
	 * @return	下载请求对象
	 * @throws IOException
	 */
	public static DownloadRequest getDownload(byte[] data) throws IOException{
		DataInputStream dis = getStream(data);
		//读出帐号的长度
		int id_length = dis.readInt();
		//读出帐号
		byte[] id_data = new byte[id_length];
		dis.read(id_data);
		int absPath_length = dis.readInt();
		byte[] absPath_data = new byte[absPath_length];
		dis.read(absPath_data);
		String id = new String(id_data);
		String absPath = new String(absPath_data);
		return new DownloadRequest(id, absPath);
	}
	
	/**
	 * 根据数据返回登陆请求数据包对象
	 * @param data	byte数据
	 * @return	登陆请求
	 * @throws IOException
	 */
	public static LoginRequest getLogin(byte[] data) throws IOException{
		DataInputStream dis = getStream(data);
		//读取用户账户的从大小
		int id_length = dis.readInt();
		byte[] id_data = new byte[id_length];
		dis.read(id_data);
		//读出用户账户
		String id = new String(id_data);
		id_length = dis.readInt();
		id_data = new byte[id_length];
		dis.read(id_data);
		int pwd_length = dis.readInt();
		byte[] pwd_data = new byte[pwd_length];
		dis.read(pwd_data);
		String pwd = new String(pwd_data);
		return new LoginRequest(id, pwd);
	}
	
	/**
	 * 根据数据返回移动请求对象
	 * @param data	byte数据
	 * @return	移动请求
	 * @throws IOException
	 */
	public static MovingRequest getMoving(byte[] data) throws IOException{
		DataInputStream dis = getStream(data);
		//读出帐号的长度
		int id_length = dis.readInt();
		//读出帐号
		byte[] id_data = new byte[id_length];
		dis.read(id_data);
		int oldPath_length = dis.readInt();
		byte[] oldPath_data = new byte[oldPath_length];
		dis.read(oldPath_data);
		int nowPath_length = dis.readInt();
		byte[] nowPath_data = new byte[nowPath_length];
		dis.read(nowPath_data);
		String id = new String(id_data);
		String oldPath = new String(oldPath_data);
		String nowPath = new String(nowPath_data);
		return new MovingRequest(id, oldPath, nowPath);
	}
	
	/**
	 * 根据数据返回检测请求对象
	 * @param data	byte数据
	 * @return	检测请求对象
	 * @throws IOException
	 */
	public static PRUploadRequest getPRUpload(byte[] data) throws IOException{
		DataInputStream dis = getStream(data);
		//读出帐号的长度
		int id_length = dis.readInt();
		//读出帐号
		byte[] id_data = new byte[id_length];
		dis.read(id_data);
		int absPath_length = dis.readInt();
		byte[] absPath_data = new byte[absPath_length];
		dis.read(absPath_data);
		int md5_length = dis.readInt();
		byte[] md5_data = new byte[md5_length];
		dis.read(md5_data);
		String id = new String(id_data);
		String absPath = new String(absPath_data);
		String md5 = new String(md5_data);
		return new PRUploadRequest(id, absPath, md5);
	}
	
	/**
	 * 根据数据返回重命名数据包对象
	 * @param data	byte数据
	 * @return	重命名请求
	 * @throws IOException
	 */
	public static RenameRequest getRename(byte[] data) throws IOException{
		DataInputStream dis = getStream(data);
		//读出帐号的长度
		int id_length = dis.readInt();
		//读出帐号
		byte[] id_data = new byte[id_length];
		dis.read(id_data);
		int oldPath_length = dis.readInt();
		byte[] oldPath_data = new byte[oldPath_length];
		dis.read(oldPath_data);
		int nowPath_length = dis.readInt();
		byte[] nowPath_data = new byte[nowPath_length];
		dis.read(nowPath_data);
		String id = new String(id_data);
		String oldPath = new String(oldPath_data);
		String nowPath = new String(nowPath_data);
		return new RenameRequest(id, oldPath, nowPath);
	}
	
	/**
	 * 返回上传请求
	 * @param data
	 * @return
	 * @throws IOException
	 */
	public static UploadRequest getUpload(byte[] data) throws IOException{
		DataInputStream dis = getStream(data);
		//读出帐号的长度
		int id_length = dis.readInt();
		//读出帐号
		byte[] id_data = new byte[id_length];
		dis.read(id_data);
		int absPath_length = dis.readInt();
		byte[] absPath_data = new byte[absPath_length];
		dis.read(absPath_data);
		int md5_length = dis.readInt();
		byte[] md5_data = new byte[md5_length];
		dis.read(md5_data);
		int data_length = dis.readInt();
		byte[] data_data = new byte[data_length];
		dis.read(data_data);
		String id = new String(id_data);
		String absPath = new String(absPath_data);
		String md5 = new String(md5_data);
		return new UploadRequest(id, absPath, md5, data_data);
	}
}
