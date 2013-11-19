package Server;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import org.apache.mina.core.service.IoHandler;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import Tools.ImageRequest;
import Tools.ImageResponese;

public class ImageServerHandler implements IoHandler{
	
	private static final String characters = "abcdefghijklmnopqistuvwxyz0123456789";
	private static final String INDEX_KEY = ImageServerHandler.class.getName() + ".INDEX";
	private static Logger LOGGER = LoggerFactory.getLogger(ImageServerHandler.class);
//	private static int id = 0;
	
	public void sessionCreated(IoSession session) throws Exception {
//		System.out.println(session.getRemoteAddress().toString());
	}

	public void sessionOpened(IoSession session) throws Exception {
		session.setAttribute(INDEX_KEY, 0);
//		session.setAttribute(id, id);
//		id++;
	}

	public void sessionClosed(IoSession session) throws Exception {
		
	}

	public void sessionIdle(IoSession session, IdleStatus status)
			throws Exception {
		
	}

	public void exceptionCaught(IoSession session, Throwable cause)
			throws Exception {
		LOGGER.warn(cause.getMessage(), cause);
	}

	public void messageReceived(IoSession session, Object message)
			throws Exception {
//		if(3 == (Integer)session.getAttribute(3)){
//			System.out.println("我拿到3号的session了");
//		}
		
		ImageRequest request = (ImageRequest)message;
		String text1 = getString(session, request.getNumberOfChar());
		String text2 = getString(session, request.getNumberOfChar());
		BufferedImage image1 = createImage(request, text1);
		BufferedImage image2 = createImage(request, text2);
		ImageResponese response = new ImageResponese(image1, image2);
		session.write(response);
	}

	public void messageSent(IoSession session, Object message) throws Exception {
		
	}
	
	private BufferedImage createImage(ImageRequest request, String text){
		BufferedImage image = new BufferedImage(request.getWidth(),request.getHeight(),BufferedImage.TYPE_BYTE_INDEXED);
		Graphics gr = image.createGraphics();
		gr.setColor(Color.RED);
		gr.fillRect(0, 0, image.getWidth(), image.getHeight());
		Font font = new Font("serif",Font.ITALIC,30);
		gr.setFont(font);
		gr.setColor(Color.BLACK);
		gr.drawString(text, 10, 50);
		return image;
	}
	
	/**
	 * 用来生成指定长度的字符串
	 * @param session
	 * @param length
	 * @return
	 */
	private String getString(IoSession session, int length){
		Integer index = (Integer)session.getAttribute(INDEX_KEY);
		StringBuffer values = new StringBuffer();
		while(values.length() < length){
			values.append(characters.charAt(index));
			index++;
			if(index >= characters.length()){
				index = 0;
			}
		}
		session.setAttribute(INDEX_KEY, index);
		return values.toString();
	}

}
