package Server;

import java.io.IOException;
import java.net.InetSocketAddress;

import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

import Codec.ImageCodecFactory;

public class ImageServer{
	
	private static final int PORT = 9090;
	
	public static void main(String[] args) throws IOException{
		/**
		 * 创建一个实现IoService的类的对象，这里因为是服务器，因此是Acceptor
		 */
		NioSocketAcceptor acceptor = new NioSocketAcceptor();
		
		/**
		 * 设置里面的IoHandler
		 */
		acceptor.setHandler(new ImageServerHandler());
		
		acceptor.addListener(new ServiceListener());
		
		/**
		 * 设置IoFilterChain，在这里只添加了一个name is Codec 的 Filter，
		 * 
		 * 在这里其实可以添加很多Filter来实现对于传递的信息的处理
		 */
		acceptor.getFilterChain().addLast("Codec", new ProtocolCodecFilter(new ImageCodecFactory(false)));
//		acceptor.getFilterChain().addLast("Text", new Io);
//		/**
//		 * 在这里返回一个SocketSessionConfig的对象，可以在这里对其进行修改，来设置Session
//		 */
//		SocketSessionConfig config = acceptor.getSessionConfig();
		
//		acceptor.setSessionDataStructureFactory(sessionDataStructureFactory);
		
		/**
		 * 将这个IoService绑定到一个端口上实现监听连接
		 */
		acceptor.bind(new InetSocketAddress(PORT));
		System.out.println("Server is started!");
	}
}
