package hlong.Codec;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolEncoder;

/**
 * 
 * @author wojiaolongyinong
 * 
 * 本类作为一个创建解码和编码的工厂
 *
 */
public class CodecFactory implements ProtocolCodecFactory{
	private RequestDecoder decoder;
	private ResponseEnCoder encoder;
	
	/**
	 * 创建工厂对象的时候初始化编码和解码器
	 */
	public CodecFactory(){
		decoder = new RequestDecoder();
		encoder = new ResponseEnCoder();
	}
	
	/**
	 * 调用返回编码器
	 */
	public ProtocolEncoder getEncoder(IoSession session) throws Exception {
		return encoder;
	}

	/**
	 * 调用返回解码器
	 */
	public ProtocolDecoder getDecoder(IoSession session) throws Exception {
		return decoder;
	}

}
