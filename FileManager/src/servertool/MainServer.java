package servertool;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 服务器对象，创建服务器链接对象，等待用户链接
 * @author hadoop
 *
 */
public class MainServer extends Thread{  
	private boolean stop=false;
	private int port=9090;
	
	//重载构造方法，得到端口号
	public MainServer(int port){
		this.port=port;
	}
	/**
	 * 重写run方法
	 */
	public void run(){
		 try {
	 		ServerSocket server=new ServerSocket(port);//创建一个服务器器
	    	 while(!stop){   		 
	    		 Socket sock=server.accept();//等待用户链接
                 stop=dealClientSocket(sock);//处理连接对象的方法
	    	 } 
	    	 server.close();//关闭服务器的连接
	    	}catch (IOException e) {
				e.printStackTrace();
		   } 		 
	 }
	private boolean dealClientSocket(Socket sock){
		try {
			InputStream inpus=sock.getInputStream();
			byte[] head=new byte[4];
			inpus.read(head);//获取包头信息
			String header=new String(head);
			if(header.equals("0x01")){
				
			}else if(header.equals("0x02")){
				LoginServer login=new LoginServer(sock);
				login.start();
				
			}else if(header.equals("0x00")){//表示关闭服务器
				
				return true;
			}
			return false;
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return true;
	}
}
