package demotool;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
/**
 * 一个验证用户名和密码等信息的类
 * @author hadoop
 *
 */
public class LoginChecker {
    
	/**
	 * 
	 * @param name
	 * @param passwd
	 * @return
	 */
	public SocketConnecter checkLogin(String name,String passwd){
		try {
			Socket socket=new Socket("lanyun-2013.oicp.net",9090);//创建连接对象
			SocketConnecter connect=new SocketConnecter(socket);
			connect.sendLoginMessage(name, passwd);//检验用户名和密码
			return connect;
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		return null;
	}
}
