package Server;

import java.io.IOException;
import java.net.InetSocketAddress;

import org.apache.mina.core.service.IoHandler;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

import Codec.CodecFactory;
import Request.CreateDirRequest;
import Request.RegisterRequest;
import Response.DownloadResponse;
import Response.LoginResponse;
import Utils.FileSendTest;

public class ServerHandler implements IoHandler{
	
	public ServerHandler() throws IOException{
		NioSocketAcceptor acceptor = new NioSocketAcceptor();
//		acceptor.addListener();
		acceptor.setHandler(this);
//		acceptor.getSessionConfig().setReadBufferSize(2048);
		acceptor.getFilterChain().addLast("Codec", new ProtocolCodecFilter(new CodecFactory()));
		acceptor.bind(new InetSocketAddress(9090));
		System.out.println("服务器已经启动！");
	}
	
	/**
	 * 首先服务器和消息处理写在一起，然后以后在考虑进行分开处理
	 * @param args
	 */
	public static void main(String[] args){
		try {
			new ServerHandler();
		} catch (IOException e) {
			e.printStackTrace();
		}
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
		
	}

	@Override
	public void messageReceived(IoSession session, Object message)
			throws Exception {
		System.out.println("我是服务器Handler，我在处理消息：" + message.getClass().getSimpleName());
		DownloadResponse dr = new DownloadResponse("longyinong", FileSendTest.getData());
		session.write(dr);
	}

	public void messageSent(IoSession session, Object message) throws Exception {
		
	}

}
