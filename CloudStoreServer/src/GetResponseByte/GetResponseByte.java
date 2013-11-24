package GetResponseByte;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Iterator;

import Response.DownloadResponse;
import Response.FileViewResponse;
import Response.LoginResponse;
import Response.PreUploadResponse;
import Response.RegisterResponse;

/**
 * 服务器端的工具类，将响应数据包转化为byte数据
 * @author wojiaolongyinong
 *
 */
public class GetResponseByte {
	private static final int HEAD_SIZE = 9;
	private static final int DATA_SIZE = 4;
	
	/**
	 * 根据传进来的下载响应，返回byte数据
	 * @param dr	下载响应
	 * @return	byte数据
	 * @throws IOException 
	 */
	public static byte[] getDownload(DownloadResponse dr) throws IOException{
		String id = dr.getID();
		byte[] data = dr.getData();
		byte[] id_data = id.getBytes();
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		DataOutputStream dos = new DataOutputStream(baos);
		//写入数据包类型
		dos.writeByte(Utils.DataPackageType.RE_DOWNLOAD);
		//写入数据包总大小
		dos.writeInt(HEAD_SIZE + id_data.length
				+ DATA_SIZE + data.length);
		//写入接收者帐号
		dos.write(id_data);
		//依次写入数据的大小以及数据内容
		dos.writeInt(data.length);
		dos.write(data);
		return baos.toByteArray();
	}
	
	/**
	 * 根据传进来的文件响应以及所属的响应类型，返回byte数据
	 * @param fvr	文件视图
	 * @param type	所属的响应类型
	 * @return	byte数据
	 * @throws IOException
	 */
	public static byte[] getFileView(FileViewResponse fvr,byte type) throws IOException{
		String id = fvr.getID();
		byte[] id_data = id.getBytes();
		Iterator<String> fileView = fvr.getFileView().iterator();
		ByteArrayOutputStream baos_file = new ByteArrayOutputStream();
		DataOutputStream dos_file = new DataOutputStream(baos_file);
		int fileView_size = 0;
		while(fileView.hasNext()){
			String temp = fileView.next();
			byte[] temp_data = temp.getBytes();
			dos_file.writeInt(temp_data.length);
			dos_file.write(temp_data);
			//计算文件视图所占的总字节数
			fileView_size = fileView_size + DATA_SIZE + temp_data.length;
		}
		byte[] fileView_data = baos_file.toByteArray();
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		DataOutputStream dos = new DataOutputStream(baos);
		//写入数据包类型
		dos.writeByte(type);
		//写入数据包的总大小
		dos.writeInt(HEAD_SIZE + id_data.length
				+ DATA_SIZE + fileView_size);
		//写入帐号
		dos.write(id_data);
		//依次写处数据大小及数据
		dos.writeInt(fileView_size);
		dos.write(fileView_data);
		return baos.toByteArray();
	}
	
	/**
	 * 根据传进来的登陆响应返回byte数	据
	 * @param lr	登陆响应对象
	 * @return	byte数据
	 * @throws IOException
	 */
	public static byte[] getLogin(LoginResponse lr) throws IOException{
		String id = lr.getID();
		byte state = lr.getState();
		byte[] id_data = id.getBytes();
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		DataOutputStream dos = new DataOutputStream(baos);
		//写入响应类型
		dos.writeByte(Utils.DataPackageType.RE_LOGIN);
		//写入数据包总大小
		dos.writeInt(HEAD_SIZE + id_data.length
				+ DATA_SIZE + 1);
		//写入帐号
		dos.write(id_data);
		//依次写入数据大小及数据
		dos.writeInt(1);
		dos.writeByte(state);
		return baos.toByteArray();
	}
	
	/**
	 * 根据文件上传检测响应数据包返回byte数据
	 * @param plr	文件检测响应数据包对象
	 * @return	byte数据
	 * @throws IOException
	 */
	public static byte[] getPreUpload(PreUploadResponse plr) throws IOException{
		String id = plr.getID();
		byte state = plr.getState();
		byte[] id_data = id.getBytes();
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		DataOutputStream dos = new DataOutputStream(baos);
		//写入响应类型
		dos.writeByte(Utils.DataPackageType.RE_PR_UPLOAD);
		//写入数据包总大小
		dos.writeInt(HEAD_SIZE + id_data.length
				+ DATA_SIZE + 1);
		//写入帐号
		dos.write(id_data);
		//依次写入数据大小及数据
		dos.writeInt(1);
		dos.writeByte(state);
		return baos.toByteArray();
	}
	
	/**
	 * 根据传进来的注册响应对象返回byte数据
	 * @param rr	注册响应对象
	 * @return	byte数据
	 * @throws IOException
	 */
	public static byte[] getRegister(RegisterResponse rr) throws IOException{
		String id = rr.getID();
		byte state = rr.getState();
		byte[] id_data = id.getBytes();
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		DataOutputStream dos = new DataOutputStream(baos);
		//写入响应类型
		dos.writeByte(Utils.DataPackageType.RE_REGISTER);
		//写入数据包总大小
		dos.writeInt(HEAD_SIZE + id_data.length
				+ DATA_SIZE + 1);
		//写入帐号
		dos.write(id_data);
		//依次写入数据大小及数据
		dos.writeInt(1);
		dos.writeByte(state);
		return baos.toByteArray();
	}
}
