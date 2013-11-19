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
 * 一个负责移动文件的操作类
 * @author hadoop
 *
 */
public class SocketRemoveFile extends Thread{
	 private String account;
	 private LeftJpanel left_panel;
	 private String file_path;
     private String move_path;
	 //
     public SocketRemoveFile(String account,LeftJpanel panel,String path,String move_path){
    	 this.account=account;
    	 this.left_panel=panel;
    	 this.file_path=path;
    	 this.move_path=move_path;
     }
	/**
	 * 重写run方法
	 */
	public void run(){
		try {
			Socket sock=new Socket("lanyun-2013.oicp.net",9090);//创建与服务器的连接
			OutputStream oups=sock.getOutputStream();
			DataOutputStream dataops=new DataOutputStream(oups);//获取流对象
			InputStream inps=sock.getInputStream();
			DataInputStream dataips=new DataInputStream(inps);	
			boolean bl=this.removeFileOnHDFS(dataops,dataips);
			//发送结束消息
			String st="0x00";
			dataops.write(st.getBytes());
			dataops.flush();
			//关闭连接对象
			dataops.close();
			dataips.close();
			sock.close();
			if(bl){//如果移动成功
				left_panel.refreshTreeVersion();//刷新文件视图
				
				left_panel.refreshFileVersion(file_path);
			}else{
				JOptionPane.showMessageDialog(null, "移动失败！");
			}
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
			
			
		}
	}
	/**
	 * 具体实现移动文件的方法
	 * @param dataops 输出流
	 * @param dataips输入流
	 * @return 如果成功返回true 否则返回false
	 */
	public boolean removeFileOnHDFS(DataOutputStream dataops,DataInputStream dataips){
				 
		if(account!=null&&left_panel!=null&&move_path!=null){//判断用户帐号是否正确		 
		   String headbyte="0x06";
			try {
				dataops.write(headbyte.getBytes());//发送头消息
				dataops.writeInt(account.getBytes(CharConfig.charset).length);//发送帐号
				dataops.write(account.getBytes(CharConfig.charset));
				dataops.writeInt(file_path.getBytes(CharConfig.charset).length);//发送要移动的文件路径
				dataops.write(file_path.getBytes(CharConfig.charset));
						   
			    dataops.writeInt(move_path.getBytes(CharConfig.charset).length);//要移动到的文件夹
				dataops.write(move_path.getBytes(CharConfig.charset));
			    dataops.flush();//强制写出
				byte[] type=new byte[4];
				dataips.read(type);//接受服务器返回的消息
				String mes=new String(type);
				if(mes.equals("0x16")){
					int len=dataips.readInt();//读取消息长度
					byte[] mess=new byte[len];
					dataips.read(mess);//读取消息数组
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
