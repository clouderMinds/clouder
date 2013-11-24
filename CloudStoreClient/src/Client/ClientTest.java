package Client;

import java.net.InetSocketAddress;
import java.util.concurrent.TimeUnit;

import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.service.IoHandler;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

import Codec.CodecFactory;
import Request.CreateDirRequest;
import Request.DeleteRequest;
import Request.LoginRequest;
import Request.RegisterRequest;

/**
 * 用来对客户端进行测试
 * 用来主要进行消息接受和发送
 * @author hadoop
 *
 */
public class ClientTest implements IoHandler{
	private IoSession session;
	
	
	public ClientTest(){
		NioSocketConnector connector = new NioSocketConnector();
		connector.setHandler(this);
		connector.getFilterChain().addLast("Codec", new ProtocolCodecFilter(new CodecFactory()));
		ConnectFuture fut = connector.connect(new InetSocketAddress("localhost", 9090));
		fut.awaitUninterruptibly(3000);
		session = fut.getSession();
	}
	
	
	@Override
	public void sessionCreated(IoSession session) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sessionOpened(IoSession session) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sessionClosed(IoSession session) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sessionIdle(IoSession session, IdleStatus status)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void exceptionCaught(IoSession session, Throwable cause)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void messageReceived(IoSession session, Object message)
			throws Exception {
		System.out.println("收到一条消息！--------！");
	}

	@Override
	public void messageSent(IoSession session, Object message) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	public static void main(String[] args) throws InterruptedException{
		ClientTest c = new ClientTest();
		TimeUnit.SECONDS.sleep(5);
		for(int i = 0; i < 10; i++){
			RegisterRequest rr = new RegisterRequest("龙亦浓", "dhuawud"+"-"+i);
			c.session.write(rr);
		}
		for(int i = 10; i < 20; i++){
			LoginRequest lr = new LoginRequest("dawdaw", "dawdwa"+i);
			c.session.write(lr);
		}
		CreateDirRequest cdr = new CreateDirRequest("我叫龙益农", "dwadawdnja");
		c.session.write(cdr);
		DeleteRequest dr = new DeleteRequest("dawd", "dawdwad");
		c.session.write(dr);
	}
	
}
