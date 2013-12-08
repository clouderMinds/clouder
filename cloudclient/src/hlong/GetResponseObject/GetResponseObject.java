package hlong.GetResponseObject;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import hlong.Response.DownloadResponse;
import hlong.Response.FileViewResponse;
import hlong.Response.LoginResponse;
import hlong.Response.PreUploadResponse;
import hlong.Response.RegisterResponse;
import hlong.Response.UploadResponse;

/**
 * 该类作为客户端的工具类使用
 * @author wojiaolongyinong
 *
 */
public class GetResponseObject {
	
	/**
	 * 将传进来的数据进行封装
	 * @param args	传进来的byte数组
	 * @return	返回DataInputStream对象
	 */
	private static DataInputStream getStream(byte[] args){
		ByteArrayInputStream bais = new ByteArrayInputStream(args);
		return new DataInputStream(bais);
	}
	
	/**
	 * 根据传进来的byte数组生成注册响应对象，前提是数据包类型为注册响应数据包
	 * @param args	注册响应数据包生成的byte数组
	 * @return	返回注册
	 * @throws IOException 
	 */
	public static RegisterResponse getRegisterRe(byte[] data) throws IOException{
		DataInputStream dis = getStream(data);
		//读取用户账户的从大小
		int id_length = dis.readInt();
		byte[] id_data = new byte[id_length];
		dis.read(id_data);
		//读出用户账户
		String id = new String(id_data);
		int state_length = dis.readInt();
		byte[] state_data = new byte[state_length];
		dis.read(state_data);
		return new RegisterResponse(id, state_data[0]);
	}
	
	/**
	 * 根据传进来的数据返回文件视图响应
	 * @param data	byte数据
	 * @return	文件视图对象
	 * @throws IOException 
	 */
	public static FileViewResponse getFileView(byte[] data) throws IOException{
		DataInputStream dis = getStream(data);
		//读取账户长度
		int id_length = dis.readInt();
		byte[] id_data = new byte[id_length];
		dis.read(id_data);
		String id = new String(id_data);
		List<String> fileList = new ArrayList<String>();
		while(dis.available() > 0){
			int temp_size = dis.readInt();
			byte[] temp_data = new byte[temp_size];
			dis.read(temp_data);
			fileList.add(new String(temp_data));
		}
		return new FileViewResponse(id, fileList);
	}
	
	/**
	 * 根据数据返回登陆响应数据包
	 * @param data	byte数据
	 * @return	登陆响应数据包
	 * @throws IOException
	 */
	public static LoginResponse getLogin(byte[] data) throws IOException{
		DataInputStream dis = getStream(data);
		//读取用户账户的从大小
		int id_length = dis.readInt();
		byte[] id_data = new byte[id_length];
		dis.read(id_data);
		//读出用户账户
		String id = new String(id_data);
		int state_length = dis.readInt();
		byte[] state_data = new byte[state_length];
		dis.read(state_data);
		return new LoginResponse(id, state_data[0]);
	}
	
	/**
	 * 根据数据返回文件检测响对象
	 * @param data	byte数据
	 * @return	文件检测响应对象
	 * @throws IOException
	 */
	public static PreUploadResponse getPreUpload(byte[] data) throws IOException{
		DataInputStream dis = getStream(data);
		//读取用户账户的从大小
		int id_length = dis.readInt();
		byte[] id_data = new byte[id_length];
		dis.read(id_data);
		//读出用户账户
		String id = new String(id_data);
		int local_length = dis.readInt();
		byte[] local_data = new byte[local_length];
		dis.read(local_data);
		//读取本地文件路径
		String localPath= new String(local_data);
		int state_length = dis.readInt();
		byte[] state_data = new byte[state_length];
		dis.read(state_data);
		return new PreUploadResponse(id,localPath,state_data[0]);
	}
	/**
	 * 
	 * @param data
	 * @return
	 * @throws IOException
	 */
	public static UploadResponse getUpload(byte[] data) throws IOException{
		DataInputStream dis = getStream(data);
		//读取用户账户的从大小
		int id_length = dis.readInt();
		byte[] id_data = new byte[id_length];
		dis.read(id_data);
		//读出用户账户
		String id = new String(id_data);
		int local_length = dis.readInt();
		byte[] local_data = new byte[local_length];
		dis.read(local_data);
		//读取本地文件路径
		String localPath= new String(local_data);
		int state_length = dis.readInt();
		byte[] state_data = new byte[state_length];
		dis.read(state_data);
		return new UploadResponse(id,localPath,state_data[0]);
	}
	/**
	 * 根据传进来的数据返回下载响应对象
	 * @param data	byte数据
	 * @return	下载响应
	 * @throws IOException
	 */
	public static DownloadResponse getDownload(byte[] data) throws IOException{
		DataInputStream dis = getStream(data);
		//读取用户账户的从大小
		int id_length = dis.readInt();
		byte[] id_data = new byte[id_length];
		dis.read(id_data);
		int local_length = dis.readInt();
		byte[] local_data = new byte[local_length];
		dis.read(local_data);
		//读出用户账户
		String id = new String(id_data);
		String localPaht=new String(local_data);
		int state_length = dis.readInt();
		byte[] state_data = new byte[state_length];
		dis.read(state_data);
		int data_size = dis.readInt();
		byte[] data_data = new byte[data_size];
		dis.read(data_data);
		return new DownloadResponse(id,localPaht,state_data[0], data_data);
	}
}
