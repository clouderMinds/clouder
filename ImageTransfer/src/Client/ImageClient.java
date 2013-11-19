package Client;

import java.net.InetSocketAddress;

import org.apache.mina.core.RuntimeIoException;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.transport.socket.SocketConnector;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

import Codec.ImageCodecFactory;
import Tools.ImageRequest;
import Tools.ImageResponese;

public class ImageClient extends IoHandlerAdapter{
	public static final int TIME_OUT = 3000;
	
	private String host;
	private int port;
	private SocketConnector connector;
	private IoSession session;
	private ImageListener listener;
	
	public ImageClient(String host, int port, ImageListener listener){ 
		this.host = host;
		this.port = port;
		this.listener = listener;
		connector = new NioSocketConnector();
		connector.getFilterChain().addLast("Codec", new ProtocolCodecFilter(new ImageCodecFactory(true)));
		connector.setHandler(this);
	}
	
	public boolean isConnected(){
		return (session != null && session.isConnected());
	}
	
	public void connect(){
		ConnectFuture future = connector.connect(new InetSocketAddress(host,port));
		future.awaitUninterruptibly(TIME_OUT);
		try{
			session = future.getSession();
		}catch(RuntimeIoException e){
			listener.onException(e);
		}
	}
	
	public void disConnect(){
		if(session != null){
			session.close(true).awaitUninterruptibly(TIME_OUT);
			session = null;
		}
	}
	
	public void sessionOpened(IoSession session) throws Exception {
		listener.sessionOpened();
	}
	
	@Override
	public void sessionClosed(IoSession session) throws Exception {
		listener.sessionClosed();
	}
	
	public void sentRequest(ImageRequest request){
		if(session == null){
			listener.onException(new Throwable("session is null!"));
		}else{
			session.write(request);
		}
	}
	
	@Override
	public void messageReceived(IoSession session, Object message)
			throws Exception {
		ImageResponese response = (ImageResponese)message;
		listener.onImage(response.getImage1(), response.getImage2());
	}
	
	@Override
	public void messageSent(IoSession session, Object message) throws Exception {
		System.out.println("监听到发出数据！");
	}
	
	public void exceptionCaught(IoSession session, Throwable cause)
			throws Exception {
		listener.onException(cause);
	}
}
