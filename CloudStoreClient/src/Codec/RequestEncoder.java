package Codec;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoderAdapter;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;
/**
 * 对于客户端进行发送的请求的进行编码处理者
 * @author wojiaolongyinong 
 *
 */
public class RequestEncoder extends ProtocolEncoderAdapter{

	
	public void encode(IoSession session, Object message,
			ProtocolEncoderOutput out) throws Exception {
		
	}

}
