package Server;

import org.apache.mina.core.service.IoHandler;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

/**
 * 处理客户端消息
 * @author wojiaolongyinong
 *
 */
public class ServerHandler implements IoHandler{
	
	public void sessionCreated(IoSession session) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sessionOpened(IoSession session) throws Exception {
		// TODO Auto-generated method stub
		
	}

	public void sessionClosed(IoSession session) throws Exception {
		
	}

	@Override
	public void sessionIdle(IoSession session, IdleStatus status)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void exceptionCaught(IoSession session, Throwable cause)
			throws Exception {
		
	}

	@Override
	public void messageReceived(IoSession session, Object message)
			throws Exception {
		System.out.println("我是服务器Handler，我在处理消息：" + message.getClass().getSimpleName());
	}

	public void messageSent(IoSession session, Object message) throws Exception {
		
	}

}
