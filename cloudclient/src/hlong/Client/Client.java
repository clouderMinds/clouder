package hlong.Client;

import hlong.Codec.CodecFactory;

import java.net.InetSocketAddress;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.transport.socket.nio.NioSocketConnector;


/**
 * 客户端的实现
 * @author wojiaolongyinong
 *
 */
public class Client {
	private static int TIME_OUT = 3000;
	private static IoSession session;
	private static Client client;
	private static String host = "localhost";
	private static ClientHandler handler;
	
	private Client(){
		NioSocketConnector connector = new NioSocketConnector();
		handler = new ClientHandler(); 
		connector.setHandler(handler);
		connector.getFilterChain().addLast("Codec", new ProtocolCodecFilter(new CodecFactory()));
		ConnectFuture cf = connector.connect(new InetSocketAddress(host,9090));
		cf.awaitUninterruptibly(TIME_OUT);
		session = cf.getSession();
	}
	
	/**
	 * 单实例客户端
	 * @return
	 */
	public static Client getClient(UIMessageListener um){
		if(client == null){
			client = new Client();
		}
		handler.addListener(um);
		return client;
	}
	
	public void sentMsg(Object msg){
		session.write(msg);
	}
}
