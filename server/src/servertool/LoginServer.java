package servertool;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * 相应客户登录的线程
 * @author hadoop
 * 当然还包括相应获取用户信息的请求
 */
public class LoginServer extends Thread{
    private Socket socket;
    private DataInputStream datainps;
    private DataOutputStream dataoups;
    private boolean bl=true;//用于结束循环的一个变量
	//重载构造方法
	public LoginServer(Socket socket){
		this.socket=socket;
		try {
			datainps=new DataInputStream(socket.getInputStream());
			dataoups=new DataOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 重写run方法
	 */
	public void run(){
		while(bl){
			try {
				byte[] head=new byte[4];
				datainps.read(head);
				String header=new String(head);
				if(header.equals("0x02")){
					int len=datainps.readInt();
					byte[] mes=new byte[len];
					datainps.read(mes);
					String account=new String(mes);
					len=datainps.readInt();
					mes=new byte[len];
					datainps.read(mes);
					String passwd=new String(mes);
					if(account.equals("lanjie")&&passwd.equals("lanjie")){
						String back="0x12";
						dataoups.write(back.getBytes());
						String backcode="0";
						dataoups.writeInt(backcode.getBytes().length);
						dataoups.write(backcode.getBytes());
						dataoups.flush();
					}					
				}else if(header.equals("0x08")){
					int len=datainps.readInt();
					byte[] mes=new byte[len];
					datainps.read(mes);
					String account=new String(mes);
					if(account.equals("lanjie")){
						String back="0x18";
						dataoups.write(back.getBytes());
						dataoups.writeInt(2);
						String name="小熊";
						dataoups.writeInt(name.getBytes(CharConfig.charset).length);
						dataoups.write(name.getBytes(CharConfig.charset));
						String grade="200";
						dataoups.writeInt(grade.getBytes(CharConfig.charset).length);
						dataoups.write(grade.getBytes(CharConfig.charset));
						dataoups.flush();
					}
					
				}else if(header.equals("0x00")){
					bl=false;
				}				
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		closeConnect();//关闭连接对象
	}
	/**
	 * 一个关闭输入输出流和连接对象的方法
	 * @return 如果成功关闭，则返回true 否则返回false
	 */
	public boolean closeConnect(){
		try {
			//关闭流，关闭连接
			datainps.close();
			dataoups.close();
			socket.close();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
}
