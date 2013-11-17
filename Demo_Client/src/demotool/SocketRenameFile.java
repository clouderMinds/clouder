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
 * 一个负责重命名文件的类
 * @author hadoop
 *
 */
public class SocketRenameFile extends Thread{
	 private String account;
	 private LeftJpanel left_panel;
	 private String file_path;
	 //重载构造方法
     public SocketRenameFile(String account,LeftJpanel panel,String path){
    	 this.account=account;
    	 this.left_panel=panel;
    	 this.file_path=path;
     }
	/**
	 * 重写run 方法
	 */
	public void run(){
		try {
			Socket sock=new Socket("lanyun-2013.oicp.net",9090);//创建与服务器的连接对象
			OutputStream oups=sock.getOutputStream();
			DataOutputStream dataops=new DataOutputStream(oups);//获取输出流
			InputStream inps=sock.getInputStream();
			DataInputStream dataips=new DataInputStream(inps);	
			boolean bl=this.renameFileOnHDFS(dataops,dataips);
			//写出结束消息
			String st="0x00";
			dataops.write(st.getBytes());
			dataops.flush();
			//关闭连接对象
			dataops.close();
			dataips.close();
			sock.close();
			if(bl){//如果操作成功
				left_panel.refreshTreeVersion();//刷新文件视图
				String path=left_panel.hdfspath;
				path=path.substring(0, path.lastIndexOf('/'));
				left_panel.refreshFileVersion(path);
			}else{
				JOptionPane.showMessageDialog(null, "重命名文件失败！");
			}
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 具体实现文件重命名的操作
	 * @param dataops 连接的输出流
	 * @param dataips 连接的输入流
	 * @return 如果成功返回true 否则返回false
	 */
	public boolean renameFileOnHDFS(DataOutputStream dataops,DataInputStream dataips){
		
		   String file_name=JOptionPane.showInputDialog("请输入新的文件名");
		   if(file_name!=null&&account!=null&&left_panel!=null){//必要的验证	 
		         String headbyte="0x21";
					try {
						dataops.write(headbyte.getBytes());//发送包头消息
						dataops.writeInt(account.getBytes(CharConfig.charset).length);//用户帐号长度
						dataops.write(account.getBytes(CharConfig.charset));
						dataops.writeInt(file_path.getBytes(CharConfig.charset).length);//文件路径
						dataops.write(file_path.getBytes(CharConfig.charset));
					    file_name=file_path.substring(0, file_path.lastIndexOf('/')+1)+file_name;
					   
					    dataops.writeInt(file_name.getBytes(CharConfig.charset).length);//新的文件名
						dataops.write(file_name.getBytes(CharConfig.charset));
					    dataops.flush();//强制写出
						byte[] type=new byte[4];
						dataips.read(type);//读取头消息
						String mes=new String(type);
						if(mes.equals("0x31")){
							int len=dataips.readInt();//读取返回的消息长度
							byte[] mess=new byte[len];
							dataips.read(mess);//消息内容
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
