package GetMessageByte;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import Request.LoginRequest;
import Request.RegisterRequest;

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
	
//	/**
//	 * 根据传进来的登陆数据包，返回byte数组
//	 * @param lr	登陆数据包
//	 * @return
//	 */
//	public static byte[] getLogin(LoginRequest lr){
//		String id = lr.getID();
//		String pwd = lr.getPassword();
//	}
}
