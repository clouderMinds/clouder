package demotool;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import javax.swing.JOptionPane;

import democonsole.LeftJpanel;

/**
 * 发送创建文件的线程
 * @author hadoop
 *
 */
public class SocketCreateFile extends Thread{
	 private String account;
	 private LeftJpanel left_panel;
	 private String file_path;
	 //重载构造方法，获取相应的对象
     public SocketCreateFile(String account,LeftJpanel panel,String path){
    	 this.account=account;
    	 this.left_panel=panel;
    	 this.file_path=path;
     }
	/**
	 * 重写run方法
	 */
	public void run(){
		try {
			Socket sock=new Socket("lanyun-2013.oicp.net",9090);//服务器的接口
			OutputStream oups=sock.getOutputStream();
			DataOutputStream dataops=new DataOutputStream(oups);//获取相应的流
			InputStream inps=sock.getInputStream();
			DataInputStream dataips=new DataInputStream(inps);	
			boolean bl=this.createFileOnHDFS(dataops,dataips);
			//自定义的包头消息类型
			String st="0x00";
			dataops.write(st.getBytes());//发送包头消息
			dataops.flush();
			//关闭连接
			dataops.close();
			dataips.close();
			sock.close();
			if(bl){//如果登录成功
				left_panel.refreshTreeVersion();//刷新
				
				left_panel.refreshFileVersion(file_path);
			}else{
				JOptionPane.showMessageDialog(null, "创建文件失败！");
			}
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 
	 * @param dataops
	 * @param dataips
	 * @return
	 */
	public boolean createFileOnHDFS(DataOutputStream dataops,DataInputStream dataips){
		
		   String file_name=JOptionPane.showInputDialog("请输入要创建的文件名");
		   if(file_name!=null&&account!=null&&left_panel!=null){//如果帐号存在		 
		         String headbyte="0x22";
					try {
						dataops.write(headbyte.getBytes());//写出头信息
						dataops.writeInt(account.getBytes(CharConfig.charset).length);//写出帐号长度
						dataops.write(account.getBytes(CharConfig.charset));
						dataops.writeInt(file_path.getBytes(CharConfig.charset).length);//写出要创建的文件夹的路径
						dataops.write(file_path.getBytes(CharConfig.charset));
						
					    dataops.writeInt(file_name.getBytes(CharConfig.charset).length);//写出要创建的文件名
						dataops.write(file_name.getBytes(CharConfig.charset));
					    dataops.flush();//强制写出
						byte[] type=new byte[4];
						dataips.read(type);//读取头消息
						String mes=new String(type);
						if(mes.equals("0x32")){
							int len=dataips.readInt();//读取返回值的长度
							byte[] mess=new byte[len];
							dataips.read(mess);//读取返回的消息数组
							String backcode=new String(mess);
							if(backcode.equals("0"))
							       return true;		
						}
					} catch (IOException e) {
						e.printStackTrace();
				}
		   }
		return false;
	}
}
