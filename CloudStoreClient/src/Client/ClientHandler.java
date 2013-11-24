package Client;

import org.apache.mina.core.service.IoHandler;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

/**
 * 用来对客户端进行测试
 * 用来主要进行消息接受和发送
 * @author wojiaolongyinong
 *
 */
public class ClientHandler implements IoHandler{

	public void sessionCreated(IoSession session) throws Exception {
		
	}


	public void sessionOpened(IoSession session) throws Exception {
		
	}

	public void sessionClosed(IoSession session) throws Exception {
		
	}

	public void sessionIdle(IoSession session, IdleStatus status)
			throws Exception {
		
	}

	public void exceptionCaught(IoSession session, Throwable cause)
			throws Exception {
		
	}


	public void messageReceived(IoSession session, Object message)
			throws Exception {
		System.out.println("我是客户端的Hadler，收到消息：" + message.getClass().getSimpleName());
	}


	public void messageSent(IoSession session, Object message) throws Exception {
		
	}
}
