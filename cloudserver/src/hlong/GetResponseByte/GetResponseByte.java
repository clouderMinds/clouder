package hlong.GetResponseByte;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Iterator;

import hlong.Response.DownloadResponse;
import hlong.Response.FileViewResponse;
import hlong.Response.LoginResponse;
import hlong.Response.PreUploadResponse;
import hlong.Response.RegisterResponse;
import hlong.Response.UploadResponse;

/**
 * 服务器端的工具类，将响应数据包转化为byte数据
 * @author wojiaolongyinong
 *
 */
public class GetResponseByte {
	private static final int MSG_TYPE = 1;
	private static final int MSG_LENGTH = 4;
	private static final int ID_LENGTH = 4;
	private static final int DATA_SIZE = 4;
	
	/**
	 * 根据传进来的下载响应，返回byte数据
	 * @param dr	下载响应
	 * @return	byte数据
	 * @throws IOException 
	 */
	public static byte[] getDownload(DownloadResponse dr) throws IOException{
		String id = dr.getID();
		String localPath=dr.getLocalPath();
		byte state=dr.getState();
		byte[] data = dr.getData();
		byte[] id_data = id.getBytes();
		byte[] local_data=localPath.getBytes();
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		DataOutputStream dos = new DataOutputStream(baos);
		//写入数据包类型
		dos.writeByte(hlong.Utils.DataPackageType.RE_DOWNLOAD);
		//写入数据包总大小
		dos.writeInt(MSG_TYPE + MSG_LENGTH + ID_LENGTH + id_data.length
				+DATA_SIZE+local_data.length
				+ DATA_SIZE + data.length+DATA_SIZE + 1);
		//写入接收者帐号
		dos.writeInt(id_data.length);
		dos.write(id_data);
		//写入本地文件
		dos.writeInt(local_data.length);
		dos.write(local_data);
		//写入操作状态
		dos.writeInt(1);
		dos.writeByte(state);
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
		dos.writeInt(MSG_TYPE + MSG_LENGTH + ID_LENGTH + id_data.length
				+ DATA_SIZE + fileView_size);
		//写入帐号
		dos.writeInt(id_data.length);
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
		dos.writeByte(hlong.Utils.DataPackageType.RE_LOGIN);
		//写入数据包总大小
		dos.writeInt(MSG_TYPE + MSG_LENGTH + ID_LENGTH + id_data.length
				+ DATA_SIZE + 1);
		//写入帐号
		dos.writeInt(id_data.length);
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
		String local=plr.getLocalPaht();
		byte state = plr.getState();
		byte[] id_data = id.getBytes();
		byte[] local_data=local.getBytes();
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		DataOutputStream dos = new DataOutputStream(baos);
		//写入响应类型
		dos.writeByte(hlong.Utils.DataPackageType.RE_PR_UPLOAD);
		//写入数据包总大小
		dos.writeInt(MSG_TYPE + MSG_LENGTH + ID_LENGTH + id_data.length+
				ID_LENGTH+local_data.length+ DATA_SIZE + 1);
		dos.writeInt(id_data.length);
		//写入帐号
		dos.write(id_data);
		//依次写入数据大小及数据
		dos.writeInt(local_data.length);
		dos.write(local_data);
		dos.writeInt(1);
		dos.writeByte(state);
		return baos.toByteArray();
	}
	/**
	 * 
	 * @param lr
	 * @return
	 * @throws IOException
	 */
	public static byte[] getUpload(UploadResponse lr) throws IOException{
		String id = lr.getID();
		String local=lr.getLocalPaht();
		byte state = lr.getState();
		byte[] id_data = id.getBytes();
		byte[] local_data=local.getBytes();
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		DataOutputStream dos = new DataOutputStream(baos);
		//写入响应类型
		dos.writeByte(hlong.Utils.DataPackageType.RE_UPLOAD);
		//写入数据包总大小
		dos.writeInt(MSG_TYPE + MSG_LENGTH + ID_LENGTH + id_data.length+
				ID_LENGTH+local_data.length+ DATA_SIZE + 1);
		dos.writeInt(id_data.length);
		//写入帐号
		dos.write(id_data);
		//依次写入数据大小及数据
		dos.writeInt(local_data.length);
		dos.write(local_data);
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
		dos.writeByte(hlong.Utils.DataPackageType.RE_REGISTER);
		//写入数据包总大小
		dos.writeInt(MSG_TYPE + MSG_LENGTH + ID_LENGTH + id_data.length
				+ DATA_SIZE + 1);
		//写入帐号
		dos.writeInt(id_data.length);
		dos.write(id_data);
		//依次写入数据大小及数据
		dos.writeInt(1);
		dos.writeByte(state);
		return baos.toByteArray();
	}
}
