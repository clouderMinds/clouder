package Codec;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.CumulativeProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;

import Tools.ImageResponese;

public class ImageResponseDecoder extends CumulativeProtocolDecoder{
	
	private static final String DECODER_STATE_KEY = ImageResponseDecoder.class.getName() + ".STATE";
	private static final int MAX_SIZE = 5 * 1024 * 1024;
	
	private static class DecoderState{
		BufferedImage image1;
	}
	
	protected boolean doDecode(IoSession session, IoBuffer in, ProtocolDecoderOutput out)
			throws Exception {
		DecoderState state = (DecoderState)session.getAttribute(DECODER_STATE_KEY);
		if(state == null){
			state = new DecoderState();
			session.setAttribute(DECODER_STATE_KEY, state);
		}
		if(state.image1 == null){
			//首先判断缓冲区里面的数据是否达到一条消息的长度
			if(in.prefixedDataAvailable(4, MAX_SIZE)){
				state.image1 = readImage(in);
			}else{
				return false;
			}
		}
		if(state.image1 != null){
			if(in.prefixedDataAvailable(4, MAX_SIZE)){
				BufferedImage image2 = readImage(in);
				ImageResponese response = new ImageResponese(state.image1, image2);
				out.write(response);
				state.image1 = null;
				return true;
			}else{
				return false;
			}
		}
		return false;
	}
	
	private BufferedImage readImage(IoBuffer in) throws IOException{
		int length = in.getInt();
		byte[] data = new byte[length];
		in.get(data);
		ByteArrayInputStream bais = new ByteArrayInputStream(data);
		return ImageIO.read(bais);
	}

}
