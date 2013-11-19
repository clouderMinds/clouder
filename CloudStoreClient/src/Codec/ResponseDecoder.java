package Codec;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.CumulativeProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;
/**
 * 主要用于对于服务器发送过来的响应进行解码处理，生成响应的响应消息对象
 * @author wojiaolongyinong
 *
 */
public class ResponseDecoder extends CumulativeProtocolDecoder{

	
	protected boolean doDecode(IoSession session, IoBuffer in,
			ProtocolDecoderOutput out) throws Exception {
		
		return false;
	}

}
