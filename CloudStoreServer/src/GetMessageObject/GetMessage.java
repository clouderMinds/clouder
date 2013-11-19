package GetMessageObject;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;

import Request.RegisterRequest;

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
	
//	/**
//	 * 根据数据流返回注册消息对象
//	 * @param data
//	 * @return
//	 */
//	public static RegisterRequest getRegisterResquest(byte[] data){
//		DataInputStream dais = getStream(data);
//		
//	}
}
