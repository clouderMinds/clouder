package demotool;

import java.awt.Component;
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
 * 一个删除文件的操作线程
 * @author hadoop
 *
 */
public class SocketDeleteFile extends Thread{
	 private String account;
	 private LeftJpanel left_panel;
	 private String file_path;
	 //
     public SocketDeleteFile(String account,LeftJpanel panel,String path){
    	 this.account=account;
    	 this.left_panel=panel;
    	 this.file_path=path;
     }
	/**
	 * 重写run方法
	 */
	public void run(){
		try {
			Socket sock=new Socket("lanyun-2013.oicp.net",9090);//创建与服务器的连接
			OutputStream oups=sock.getOutputStream();
			DataOutputStream dataops=new DataOutputStream(oups);//获取输出流
			InputStream inps=sock.getInputStream();
			DataInputStream dataips=new DataInputStream(inps);	
			boolean bl=this.DeleteFileOnHDFS(dataops,dataips);
			//自定义的头信息
			String st="0x00";
			dataops.write(st.getBytes());//写出包头信息
			dataops.flush();
			//关闭流和连接对象
			dataops.close();
			dataips.close();
			sock.close();
			if(bl){//删除文件成功
				left_panel.refreshTreeVersion();//刷新文件视图
				String path=left_panel.hdfspath;
				path=path.substring(0, path.lastIndexOf('/'));
				left_panel.refreshFileVersion(path);
			}else{
				JOptionPane.showMessageDialog(null, "删除失败！");
			}
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 具体执行删除文件的操作
	 * @param dataops 输出流
	 * @param dataips 输入流
	 * @return
	 */
	public boolean DeleteFileOnHDFS(DataOutputStream dataops,DataInputStream dataips){
		int t= JOptionPane.showConfirmDialog(new Component(){
		 private static final long serialVersionUID = 1L;},"您要删除文件\n"+file_path.substring(file_path.lastIndexOf('/')+1),
		 "提示消息",JOptionPane.YES_NO_OPTION);
		  if(t==0){//如果用户选择了确定
		   if(account!=null&&left_panel!=null){//判断用户名是否存在	 
		         String headbyte="0x07";
					try {
						dataops.write(headbyte.getBytes());//写出包头信息
						dataops.writeInt(account.getBytes(CharConfig.charset).length);//写出用户帐号的长度
						dataops.write(account.getBytes(CharConfig.charset));
						dataops.writeInt(file_path.getBytes(CharConfig.charset).length);//写出文件路径的长度
						dataops.write(file_path.getBytes(CharConfig.charset));
						dataops.flush();//强制写出
						byte[] type=new byte[4];
						dataips.read(type);//读取包读信息
						String mes=new String(type);
						if(mes.equals("0x17")){
							int len=dataips.readInt();//读取返回消息的长度
							byte[] mess=new byte[len];
							dataips.read(mess);//读取返回消息
							String backcode=new String(mess);
							if(backcode.equals("0"))
							       return true;		
						}
					} catch (IOException e) {
						e.printStackTrace();
				}
		   }
		  }
		return false;
	}
}
