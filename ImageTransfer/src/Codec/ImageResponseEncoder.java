package Codec;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoderAdapter;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;

import Tools.ImageResponese;

public class ImageResponseEncoder extends ProtocolEncoderAdapter{

	public void encode(IoSession session, Object message,
			ProtocolEncoderOutput out) throws Exception {
		ImageResponese response = (ImageResponese)message;
		byte[] image1 = getBytes(response.getImage1());
		byte[] image2 = getBytes(response.getImage2());
		int capacity = image1.length + image2.length + 8;
		IoBuffer buffer = IoBuffer.allocate(capacity,false);
		buffer.putInt(image1.length);
		buffer.put(image1);
		buffer.putInt(image2.length);
		buffer.put(image2);
		buffer.flip();
		out.write(buffer);
	}
	
	private byte[] getBytes(BufferedImage image) throws IOException{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ImageIO.write(image, "PNG", baos);
		return baos.toByteArray();
	}
}
