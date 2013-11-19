package Client;

import java.awt.image.BufferedImage;

/**
 * 定义一个监听器接口
 * @author LONG
 *
 */
public interface ImageListener {
	/**
	 * 当接受到图片时
	 * @param image1
	 * @param image2
	 */
	void onImage(BufferedImage image1,BufferedImage image2);
	
	/**
	 * 当接收到异常时
	 * @param throwable
	 */
	void onException(Throwable throwable);
	
	/**
	 * 当来连接打开时
	 */
	void sessionOpened();
	
	/**
	 * 当连接关闭时
	 */
	void sessionClosed();
}
