package Codec;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoder;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;

import Tools.ImageRequest;

public class ImageRequestEncoder implements ProtocolEncoder{

	public void encode(IoSession session, Object message,
			ProtocolEncoderOutput out) throws Exception {
		System.out.println("需要发送的消息进来打包了！");
		ImageRequest request = (ImageRequest)message;
		/**
		 * 创建了一个大小为12个字节的缓冲区，参数false表示在Heap中申请，否则直接申请
		 */
		IoBuffer buffer = IoBuffer.allocate(12,false);
		buffer.putInt(request.getWidth());
		buffer.putInt(request.getHeight());
		buffer.putInt(request.getNumberOfChar());
		buffer.flip();
		out.write(buffer);
	}

	public void dispose(IoSession session) throws Exception {
		
	}

}
