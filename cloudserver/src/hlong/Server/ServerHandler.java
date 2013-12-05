package hlong.Server;

import hlong.Request.RegisterRequest;
import hlong.Response.RegisterResponse;
import hxiong.dbmanager.psql.HdfsuserPSQL;

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

	/**
	 * 接受到客户端消息调用
	 */
	public void messageReceived(IoSession session, Object message)
			throws Exception {
		//HdfsuserPSQL.add
		System.out.println("我是服务器Handler，我在处理消息：" + message.getClass().getSimpleName());
		RegisterRequest rr = (RegisterRequest)message;
		boolean result = HdfsuserPSQL.addHDFSUser("ui", rr.getID(), rr.getPassword());
		if(result){
			session.write(new RegisterResponse(rr.getID(),RegisterResponse.SUCCESS));
		}else{
			session.write(new RegisterResponse(rr.getID(),RegisterResponse.IDEXIST));
		}
	}

	public void messageSent(IoSession session, Object message) throws Exception {
		
	}

}
