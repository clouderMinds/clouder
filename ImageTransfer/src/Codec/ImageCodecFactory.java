package Codec;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolEncoder;

public class ImageCodecFactory implements ProtocolCodecFactory{

	private ProtocolEncoder encoder;
	private ProtocolDecoder decoder;
	
	/**
	 * 重写构造函数，根据传入的参数判断是否为客户端使用
	 * @param client	true表示为Client，否则为Server
	 */
	public ImageCodecFactory(boolean client){
		if(client){
			encoder = new ImageRequestEncoder();
			decoder = new ImageResponseDecoder();
		}else{
			encoder = new ImageResponseEncoder();
			decoder = new ImageRequestDecoder();
		}
	}
	
	
	public ProtocolEncoder getEncoder(IoSession session) throws Exception {
		return encoder;
	}

	public ProtocolDecoder getDecoder(IoSession session) throws Exception {
		return decoder;
	}

}
