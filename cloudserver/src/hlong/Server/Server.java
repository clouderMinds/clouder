package hlong.Server;

import java.io.IOException;
import java.net.InetSocketAddress;

import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

import hlong.Utils.Log;
import hlong.Codec.CodecFactory;

/**
 * 服务端
 * @author wojiaolongyinong
 *
 */
public class Server {
	private static Server server = null;
	//私有化构造器
	private Server(){
		NioSocketAcceptor acceptor = new NioSocketAcceptor();
		acceptor.setHandler(new ServerHandler());
		acceptor.getFilterChain().addLast("Codec", new ProtocolCodecFilter(new CodecFactory()));
		try {
			acceptor.bind(new InetSocketAddress(9090));
		} catch (IOException e) {
			Log.println("连接端口号出现问题！");
			e.printStackTrace();
		}
		Log.println("服务器已经启动！");
	}
	
	/**
	 * 服务器对象单实例
	 * @return	服务器对象
	 */
	public static Server getServer(){
		if(server == null){
			server = new Server();
		}
		return server;
	}
	
//	public static void main(String[] args) throws IOException{
//		getServer();
//	}
}
