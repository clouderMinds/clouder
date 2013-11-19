package Codec;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoderAdapter;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;

/**
 * 用于实现对传输的消息对象进行编码的地方
 * @author wojiaolongyinong 
 *
 */
public class ResponseEnCoder extends ProtocolEncoderAdapter{
	
	/**
	 * 根据传输进来的消息对象进行判定，是属于那一种消息然后调用响应的工厂方法进行
	 * 编码，然后写入缓冲区进行传送
	 */
	public void encode(IoSession session, Object message,
			ProtocolEncoderOutput out) throws Exception {
		
	}

}
