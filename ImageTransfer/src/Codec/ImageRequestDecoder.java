package Codec;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.CumulativeProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;

import Tools.ImageRequest;

public class ImageRequestDecoder extends CumulativeProtocolDecoder{

	protected boolean doDecode(IoSession session, IoBuffer in,
			ProtocolDecoderOutput out) throws Exception {
		/**
		 * 首先进行判断，缓冲区中保留的字节数是否大于等于12，如果是，则读出数据
		 */
		if(in.remaining() >= 12){
			int width = in.getInt();
			int height = in.getInt();
			int number = in.getInt();
			ImageRequest request = new ImageRequest(width, height, number);
			out.write(request);
			return true;
		}
		return false;
	}

}
