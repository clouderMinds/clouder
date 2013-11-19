package Codec;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.CumulativeProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;

/**
 * 用于实现请求解码的地方
 * @author wojiaolongyinong 
 *
 */
public class RequestDecoder extends CumulativeProtocolDecoder{
	
	/**
	 * 将传输进来的缓存字节，进行读取
	 * 首先判定是那一种消息类型，然后根据响应的消息类型调用响应的工厂方法
	 * 进行生成对象的处理,然后进行传输
	 */
	protected boolean doDecode(IoSession session, IoBuffer in,
			ProtocolDecoderOutput out) throws Exception {
		
		return false;
	}

}
