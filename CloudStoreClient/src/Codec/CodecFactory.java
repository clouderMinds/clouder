package Codec;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolEncoder;
/**
 * 主要用于客户端，实现对于自己需要发送的请求进行编码处理
 * 以及对于服务器发送过来的响应进行解码处理
 * @author wojiaolongyinong 
 *
 */
public class CodecFactory implements ProtocolCodecFactory{
	private RequestEncoder encoder;
	private ResponseDecoder decoder;
	
	/**
	 * 创建工厂对象时候初始化编码和解码器
	 */
	public CodecFactory(){
		encoder = new RequestEncoder();
		decoder = new ResponseDecoder();
	}
	
	/**
	 * 调用返回编码器
	 */
	public ProtocolEncoder getEncoder(IoSession session) throws Exception {
		return encoder;
	}

	/**
	 * 调用返回解密器
	 */
	public ProtocolDecoder getDecoder(IoSession session) throws Exception {
		return decoder;
	}

}
