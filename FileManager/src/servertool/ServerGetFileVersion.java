package servertool;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * 获取集群上的文件视图的线程
 * @author hadoop 返回用户需要的不同的文件视图
 *
 */
public class ServerGetFileVersion extends Thread{
	 private Socket socket;
	    private DataInputStream datainps;
	    private DataOutputStream dataoups;
	    private boolean bl=true;//用于结束循环的一个变量
		//重载构造方法
		public ServerGetFileVersion(Socket socket){
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
					if(header.equals("0x05")){//如果请求的是树状文件视图
						int len=datainps.readInt();
						byte[] mes=new byte[len];
						datainps.read(mes);
						String account=new String(mes);
						if(account.equals("lanjie")){
							String back="0x15";
							dataoups.write(back.getBytes());
							String backcode="0";
							dataoups.writeInt(backcode.getBytes().length);
							dataoups.write(backcode.getBytes());
							dataoups.flush();
						}					
					}else if(header.equals("0x09")){//如果请求的是文件夹列表
						int len=datainps.readInt();
						byte[] mes=new byte[len];
						datainps.read(mes);
						String account=new String(mes);
						if(account.equals("lanjie")){
							String back="0x19";
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
						
					}else if(header.equals("0x00")){//发送次包头表示要断开连接
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
