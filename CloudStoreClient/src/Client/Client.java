package Client;

import java.net.InetSocketAddress;

import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

import Codec.CodecFactory;
import Request.RegisterRequest;
import Utils.Log;

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
	
	private Client(){
		NioSocketConnector connector = new NioSocketConnector();
		connector.setHandler(new ClientHandler());
		connector.getFilterChain().addLast("Codec", new ProtocolCodecFilter(new CodecFactory()));
		ConnectFuture cf = connector.connect(new InetSocketAddress(host,9090));
		cf.awaitUninterruptibly(TIME_OUT);
		session = cf.getSession();
	}
	
	/**
	 * 单实例客户端
	 * @return
	 */
	public static Client getClient(){
		if(client == null){
			client = new Client();
		}
		return client;
	}
	
	public static void main(String[] args){
		getClient();
		Log.println("客户端启动起来！");
		session.write(new RegisterRequest("374673647@163.com", "dhwadh"));
	}
}
