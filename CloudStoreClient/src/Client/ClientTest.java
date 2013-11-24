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
import Request.DownloadRequest;
import Request.RegisterRequest;
import Response.DownloadResponse;
import Utils.FileTools;

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
		System.out.println("我是客户端的Hadler，收到消息：" + message.getClass().getSimpleName());
		if(message.getClass().getSimpleName().equals(Utils.DataPackageType.RE_DOWNLOAD_S)){
			DownloadResponse dr = (DownloadResponse)message;
			FileTools.getFile(dr.getData());
		}
	}

	@Override
	public void messageSent(IoSession session, Object message) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	public static void main(String[] args) throws InterruptedException{
		ClientTest c = new ClientTest();
		System.out.println("客户端已经启动，准备5秒后给服务器发送消息......");
		TimeUnit.SECONDS.sleep(5);
		System.out.println("先不间断发送50条注册请求......");
		for(int i = 0; i < 50; i++){
			RegisterRequest rr = new RegisterRequest(""+i, "w h y are you!");
			c.session.write(rr);
		}
		System.out.println("然后1秒钟后以间断式，每隔1ms发送一条请求，来进行测试......");
		TimeUnit.SECONDS.sleep(1);
		CreateDirRequest cdr = new CreateDirRequest("wojiao", "/home/hadoop/桌面/test");
		c.session.write(cdr);
		TimeUnit.MILLISECONDS.sleep(1);
		DeleteRequest dr = new DeleteRequest("long", "测试删除请求的消息");
		c.session.write(dr);
		TimeUnit.MILLISECONDS.sleep(1);
		DownloadRequest dlr = new DownloadRequest("yi", "测试下载的请求消息！");
		c.session.write(dlr);
	}
	
}
