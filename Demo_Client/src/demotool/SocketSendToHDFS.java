package demotool;

import java.awt.Component;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import democonsole.LeftJpanel;

/**
 * 一个负责将文件上传到文件系统的操作类
 * @author hadoop
 *
 */
public class SocketSendToHDFS extends Thread{
	 private String account;
	 private LeftJpanel left_panel;
	 //重载构造方法，获取相应的对象
     public SocketSendToHDFS(String account,LeftJpanel panel){
    	 this.account=account;
    	 this.left_panel=panel;
     }
	/**
	 * 重写run方法
	 */
	public void run(){
		try {
			Socket sock=new Socket("lanyun-2013.oicp.net",9090);//创建与服务器的连接
			OutputStream oups=sock.getOutputStream();
			DataOutputStream dataops=new DataOutputStream(oups);//获取连接的输出流
			InputStream inps=sock.getInputStream();
			DataInputStream dataips=new DataInputStream(inps);	
			boolean bl=this.sendFileToHDFS(dataops,dataips);
			//包头消息
			String st="0x00";
			dataops.write(st.getBytes());//发送包头消息
			dataops.flush();
			//关闭连接对象
			dataops.close();
			dataips.close();
			sock.close();
			if(bl){//如果操作成功
				left_panel.refreshTreeVersion();//刷新文件视图
				left_panel.refreshFileVersion(left_panel.hdfspath);
				JOptionPane.showMessageDialog(null, "上传成功！");
			}else{
				JOptionPane.showMessageDialog(null, "上传失败！");
			}
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 具体实现上传文件的方法
	 * @param dataops 连接的输入流
	 * @param dataips 连接的输入流
	 * @return
	 */
	public boolean sendFileToHDFS(DataOutputStream dataops,DataInputStream dataips){
		   JFileChooser jfc=new JFileChooser("E:\\");
		   jfc.setDialogType(1);//设置文件
		   jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);//ֻ设置文件选择类型
		   int choose=jfc.showOpenDialog(new Component(){
			private static final long serialVersionUID = 1L;
		   });
		   if(choose==0&&account!=null&&left_panel!=null){//判断用户是否可用	 
		         File name=jfc.getSelectedFile();//获取本地文件路径
		         String path_name=name.toString();
		         path_name="/"+path_name.substring(path_name.lastIndexOf('\\')+1);
		         path_name=left_panel.hdfspath+path_name;
		         String headbyte="0x03";
					try {
						dataops.write(headbyte.getBytes());//写出文件
						dataops.writeInt(account.getBytes(CharConfig.charset).length);//发送帐号
						dataops.write(account.getBytes(CharConfig.charset));
						dataops.writeInt(path_name.getBytes(CharConfig.charset).length);//发送文件路径
						dataops.write(path_name.getBytes(CharConfig.charset));
						
						/*************下面是发送文件的具体操作*************/
						FileInputStream inpus=new FileInputStream(name);
						int tp=getTypeOfFile(path_name);
						dataops.writeInt(tp);//发送文件类型
						if(tp==0){
							int h=inpus.read();
							while(h!=-1){
								dataops.write(h);
								h=inpus.read();							
							}	
							dataops.write(h);//发送结束标志				
						}else if(tp==1){
							int size=inpus.available();
							dataops.writeInt(size);
							byte[] filesize=new byte[size];
							inpus.read(filesize);
							dataops.write(filesize);
						}	
						dataops.flush();//强制写出
						inpus.close();
						byte[] type=new byte[4];
						dataips.read(type);//读取返回消息类型
						String mes=new String(type);
						if(mes.equals("0x13")){
							
							int len=dataips.readInt();//返回消息长度
							byte[] mess=new byte[len];
							dataips.read(mess);//返回消息内容
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
	public int getTypeOfFile(String filename){
		String type=filename.substring(filename.lastIndexOf(".")+1);//��ȡ��׺��
		if(type.equals("jpg")||type.equals("png")||type.equals("bmp")){
			return 1;
		}
		return 0;
	}
}
