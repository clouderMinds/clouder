package GetMessageByte;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
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
 * 该类作为客户端的工具类使用
 * @author wojiaolongyinong
 *
 */
public class GetMessageByte {
	/**
	 * 下面这三个变量使用来存储一般常用的变量
	 */
	public static final int TYPE_LENGTH = 1;
	public static final int LENGTH_SIZE = 4;
	public static final int ID_LENGTH = 4;
	public static final int DATA_LENGTH = 4;
	
	
	/**
	 * 用于返回一个用于对数据进行处理的工具
	 * @return
	 */
	private static ByteArrayOutputStream getStream(){
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		return baos;
	}
	
	/**
	 * 根据穿进来的注册请求返回其byte数组
	 * @param rr	注册请求数据包对象
	 * @return
	 * @throws IOException 
	 */
	public static byte[] getRegister(RegisterRequest rr) throws IOException{
		String id = rr.getID();
		String pwd = rr.getPassword();
		byte[] id_byte = id.getBytes();
		byte[] pwd_byte = pwd.getBytes();
		ByteArrayOutputStream baos = getStream();
		DataOutputStream daos = new DataOutputStream(baos);
		//首先写入数据包的类型
		daos.writeByte(Utils.DataPackageType.REGISTER);
		//然后写入数据包从的大小，从的大小包括包头的大小以及数据包体的大小
		//包头的部分分为这样几个部分，首先是数据包的类型，然后数据包大小标示，然后发送者帐号的长度
		//然后写入发送者帐号的内容，然后下来的就是包体内容
		//这里是写入数据包的总大小
		daos.writeInt(TYPE_LENGTH + LENGTH_SIZE + ID_LENGTH + id_byte.length 
				+ DATA_LENGTH + id_byte.length
				+ DATA_LENGTH + pwd_byte.length);
		//然后写入发送者帐号的大小
		daos.writeInt(id_byte.length);
		//然后写入发送者的帐号
		daos.write(id_byte);
		//然后依次写入需要存储的数据，首先写入数据项的长度，然后写入数据
		daos.writeInt(id_byte.length);
		daos.write(id_byte);
		daos.writeInt(pwd_byte.length);
		daos.write(pwd_byte);
		return baos.toByteArray();
	}
	
	/**
	 * 根据传进来的登陆数据包，返回byte数组
	 * @param lr	登陆数据包
	 * @return
	 * @throws IOException 
	 */
	public static byte[] getLogin(LoginRequest lr) throws IOException{
		String id = lr.getID();
		String pwd = lr.getPassword();
		byte[] id_byte = id.getBytes();
		byte[] pwd_byte = pwd.getBytes();
		ByteArrayOutputStream baos = getStream();
		DataOutputStream daos = new DataOutputStream(baos);
		//首先写入数据包类型
		daos.writeByte(Utils.DataPackageType.LOGIN);
		//写入数据包的从大小
		daos.writeInt(TYPE_LENGTH + LENGTH_SIZE + ID_LENGTH + id_byte.length
				+ DATA_LENGTH + id_byte.length
				+ DATA_LENGTH + pwd_byte.length);
		//写入发送者帐号的大小
		daos.writeInt(id_byte.length);
		//写入发送者的帐号
		daos.write(id_byte);
		//依次写入存储的数据大小以及具体的数据
		daos.writeInt(id_byte.length);
		daos.write(id_byte);
		daos.writeInt(pwd_byte.length);
		daos.write(pwd_byte);
		return baos.toByteArray();
	}
	
	/**
	 * 根据传进来的创建文件夹数据包，返回byte数组
	 * @param cdr	创建文件数据请求
	 * @return	byte数组
	 * @throws IOException 
	 */
	public static byte[] getCreateDir(CreateDirRequest cdr) throws IOException{
		String id = cdr.getID();
		String absPath = cdr.getAbsPath();
		byte[] id_b = id.getBytes();
		byte[] absPath_b = absPath.getBytes();
		ByteArrayOutputStream baos = getStream();
		DataOutputStream daos = new DataOutputStream(baos);
		//首先写入数据包类型
		daos.writeByte(Utils.DataPackageType.CREATEDIR);
		//写入数据包的总大小
		daos.writeInt(TYPE_LENGTH + LENGTH_SIZE + ID_LENGTH + id_b.length
				+ DATA_LENGTH + absPath_b.length);
		//写入发送者的帐号大小
		daos.writeInt(id_b.length);
		//写入发送者的帐号
		daos.write(id_b);
		//依次写入数据的大小及内容
		daos.writeInt(absPath_b.length);
		daos.write(absPath_b);
		return baos.toByteArray();
	}
	
	/**
	 * 根据传进来的删除数据包对象，返回byte数组
	 * @param dr	删除数据包对象
	 * @return	byte数组
	 * @throws IOException 
	 */
	public static byte[] getDelete(DeleteRequest dr) throws IOException{
		String id = dr.getID();
		String absPath = dr.getAbsPath();
		byte[] id_b = id.getBytes();
		byte[] absPath_b = absPath.getBytes();
		ByteArrayOutputStream baos = getStream();
		DataOutputStream daos = new DataOutputStream(baos);
		//首先写入数据包类型
		daos.writeByte(Utils.DataPackageType.DELETE);
		//写入数据包的总大小
		daos.writeInt(TYPE_LENGTH + LENGTH_SIZE + ID_LENGTH + id_b.length
				+ DATA_LENGTH + absPath_b.length);
		//写入发送者的帐号大小
		daos.writeInt(id_b.length);
		//写入发送者的帐号
		daos.write(id_b);
		//依次写入数据的大小及内容
		daos.writeInt(absPath_b.length);
		daos.write(absPath_b);
		return baos.toByteArray();
	}
	
	/**
	 * 根据传进来的下载数据包请求，返回byte数组
	 * @param dr	下载请求
	 * @return	byte数组
	 * @throws IOException
	 */
	public static byte[] getDownload(DownloadRequest dr) throws IOException{
		String id = dr.getID();
		String absPath = dr.getAbsPath();
		byte[] id_b = id.getBytes();
		byte[] absPath_b = absPath.getBytes();
		ByteArrayOutputStream baos = getStream();
		DataOutputStream daos = new DataOutputStream(baos);
		//首先写入数据包类型
		daos.writeByte(Utils.DataPackageType.DOWNLOAD);
		//写入数据包的总大小
		daos.writeInt(TYPE_LENGTH + LENGTH_SIZE + ID_LENGTH + id_b.length
				+ DATA_LENGTH + absPath_b.length);
		//写入发送者的帐号大小
		daos.writeInt(id_b.length);
		//写入发送者的帐号
		daos.write(id_b);
		//依次写入数据的大小及内容
		daos.writeInt(absPath_b.length);
		daos.write(absPath_b);
		return baos.toByteArray();
	}
	
	/**
	 * 根据传进来的移动请求，返回byte数组
	 * @param mr	移动请求
	 * @return	byte数组
	 * @throws IOException
	 */
	public static byte[] getMoving(MovingRequest mr) throws IOException{
		String id = mr.getID();
		String oldPath = mr.getOldPath();
		String nowPath = mr.getNowPath();
		byte[] id_b = id.getBytes();
		byte[] oldPath_b = oldPath.getBytes();
		byte[] nowPath_b = nowPath.getBytes();
		ByteArrayOutputStream baos = getStream();
		DataOutputStream daos = new DataOutputStream(baos);
		//首先写入数据包类型
		daos.writeByte(Utils.DataPackageType.MOVING);
		//写入数据包的总大小
		daos.writeInt(TYPE_LENGTH + LENGTH_SIZE + ID_LENGTH + id_b.length
				+ DATA_LENGTH + oldPath_b.length
				+ DATA_LENGTH + nowPath_b.length);
		//写入发送者的帐号大小
		daos.writeInt(id_b.length);
		//写入发送者的帐号
		daos.write(id_b);
		//依次写入数据的大小及内容
		daos.writeInt(oldPath_b.length);
		daos.write(oldPath_b);
		daos.writeInt(nowPath_b.length);
		daos.write(nowPath_b);
		return baos.toByteArray();
	}
	
	/**
	 * 根据传进来的预检测数据包对象，返回byte数组
	 * @param prur	预检测数据包
	 * @return	byte数组
	 * @throws IOException
	 */
	public static byte[] getPRUpload(PRUploadRequest prur) throws IOException{
		String id = prur.getID();
		String absPath = prur.getAbsPath();
		String md5 = prur.getMD5();
		byte[] id_b = id.getBytes();
		byte[] absPath_b = absPath.getBytes();
		byte[] md5_b = md5.getBytes();
		ByteArrayOutputStream baos = getStream();
		DataOutputStream daos = new DataOutputStream(baos);
		//首先写入数据包类型
		daos.writeByte(Utils.DataPackageType.PR_UPLOAD);
		//写入数据包的总大小
		daos.writeInt(TYPE_LENGTH + LENGTH_SIZE + ID_LENGTH + id_b.length
				+ DATA_LENGTH + absPath_b.length
				+ DATA_LENGTH + md5_b.length);
		//写入发送者的帐号大小
		daos.writeInt(id_b.length);
		//写入发送者的帐号
		daos.write(id_b);
		//依次写入数据的大小及内容
		daos.writeInt(absPath_b.length);
		daos.write(absPath_b);
		daos.writeInt(md5_b.length);
		daos.write(md5_b);
		return baos.toByteArray();
	}
	
	/**
	 * 根据传进来的重命名数据包对象，返回byte数组
	 * @param rr
	 * @return
	 * @throws IOException
	 */
	public static byte[] getRename(RenameRequest rr) throws IOException{
		String id = rr.getID();
		String oldPath = rr.getOldPath();
		String nowPath = rr.getNowPath();
		byte[] id_b = id.getBytes();
		byte[] oldPath_b = oldPath.getBytes();
		byte[] nowPath_b = nowPath.getBytes();
		ByteArrayOutputStream baos = getStream();
		DataOutputStream daos = new DataOutputStream(baos);
		//首先写入数据包类型
		daos.writeByte(Utils.DataPackageType.RENAME);
		//写入数据包的总大小
		daos.writeInt(TYPE_LENGTH + LENGTH_SIZE + ID_LENGTH + id_b.length
				+ DATA_LENGTH + oldPath_b.length
				+ DATA_LENGTH + nowPath_b.length);
		//写入发送者的帐号大小
		daos.writeInt(id_b.length);
		//写入发送者的帐号
		daos.write(id_b);
		//依次写入数据的大小及内容
		daos.writeInt(oldPath_b.length);
		daos.write(oldPath_b);
		daos.writeInt(nowPath_b.length);
		daos.write(nowPath_b);
		return baos.toByteArray();
	}
	
	/**
	 * 根据传进来的下载请求，返回byte数组
	 * @param ur	下载请求
	 * @return	byte数组
	 * @throws IOException 
	 */
	public static byte[] getUpload(UploadRequest ur) throws IOException{
		String id = ur.getID();
		String absPath = ur.getAbsPath();
		String md5 = ur.getMD5();
		byte[] data = ur.getData();
		byte[] id_b = id.getBytes();
		byte[] absPath_b = absPath.getBytes();
		byte[] md5_b = md5.getBytes();
		ByteArrayOutputStream baos = getStream();
		DataOutputStream daos = new DataOutputStream(baos);
		//首先写入数据包类型
		daos.writeByte(Utils.DataPackageType.UPLOAD);
		//写入数据包的总大小
		daos.writeInt(TYPE_LENGTH + LENGTH_SIZE + ID_LENGTH + id_b.length
				+ DATA_LENGTH + absPath_b.length
				+ DATA_LENGTH + md5_b.length
				+ DATA_LENGTH + data.length);
		//写入发送者的帐号大小
		daos.writeInt(id_b.length);
		//写入发送者的帐号
		daos.write(id_b);
		//依次写入数据的大小及内容
		daos.writeInt(absPath_b.length);
		daos.write(absPath_b);
		daos.writeInt(md5_b.length);
		daos.write(md5_b);
		daos.writeInt(data.length);
		daos.write(data);
		return baos.toByteArray();
	}
}
