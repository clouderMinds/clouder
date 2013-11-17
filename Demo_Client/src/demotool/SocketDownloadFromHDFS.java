package demotool;

import java.awt.Component;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import democonsole.LeftJpanel;
/**
 * 一个从文件系统下载文件的类
 * @author hadoop
 *
 */
public class SocketDownloadFromHDFS extends Thread{
	 private String account;
	 private LeftJpanel left_panel;
	 //
     public SocketDownloadFromHDFS(String account,LeftJpanel panel){
    	 this.account=account;
    	 this.left_panel=panel;
     }
	/**
	 * 重写run方法
	 */
	public void run(){
		try {
			Socket sock=new Socket("lanyun-2013.oicp.net",9090);//建立与服务器的连接
			OutputStream oups=sock.getOutputStream();
			DataOutputStream dataops=new DataOutputStream(oups);//获取输入流
			InputStream inps=sock.getInputStream();
			DataInputStream dataips=new DataInputStream(inps);	
			boolean bl=this.downlaodFilefromHDFS(dataops,dataips);
			//消息的包头信息
			String st="0x00";
			dataops.write(st.getBytes());//写出包头
			dataops.flush();
			//关闭连接对象
			dataops.close();
			dataips.close();
			sock.close();
			if(bl){//如果下载成功
				JOptionPane.showMessageDialog(null, "下载成功！");
			}else{
				JOptionPane.showMessageDialog(null, "下载失败！");
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
	public boolean downlaodFilefromHDFS(DataOutputStream dataops,DataInputStream dataips){
		   JFileChooser jfc=new JFileChooser("E:\\");
		   jfc.setDialogType(1);//����Ϊ���������
		   jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);//ֻ������ѡ���ļ���
		   int choose=jfc.showSaveDialog(new Component(){
			private static final long serialVersionUID = 1L;
		   });
		   if(choose==0&&account!=null&&left_panel!=null){//����û�ѡ���˱��棬��ִ��		 
		         File name=jfc.getSelectedFile();//��ȡ�ļ��еĳ�����
		         String path_name=name.toString();
		         String headbyte="0x04";
					try {
						dataops.write(headbyte.getBytes());//��д��ͷ
						dataops.writeInt(account.getBytes(CharConfig.charset).length);//�ʺŵĳ���
						dataops.write(account.getBytes(CharConfig.charset));
						dataops.writeInt(left_panel.hdfspath.getBytes(CharConfig.charset).length);//�ļ���·��
						dataops.write(left_panel.hdfspath.getBytes(CharConfig.charset));
						
						byte[] type=new byte[4];
						dataips.read(type);//读取包头消息
						String mes=new String(type);
						if(mes.equals("0x14")){								
							int len=dataips.readInt();//读取消息
							byte[] mess=new byte[len];
							dataips.read(mess);//
							String backcode=new String(mess);	
							if(backcode.equals("0")){								
								/*************接受服务器返回的消息*************/
								path_name=path_name+left_panel.hdfspath.substring(left_panel.hdfspath.lastIndexOf('/'));
								FileOutputStream oups=new FileOutputStream(path_name);
								int tp=dataips.readInt();//读取消息类型
								if(tp==0){
								   int t=dataips.read();
								   while(t!=255){
									  oups.write(t);
									  t=dataips.read();									
								   }
								}else if(tp==1){
									int size=dataips.readInt();//要发送的字节大小
									byte[] filesize=new byte[size];
									dataips.read(filesize);
									oups.write(filesize);
								}
								oups.flush();//关闭流
								oups.close();//关闭流
								return true;
							}						      
						}
					} catch (IOException e) {
						e.printStackTrace();
				}
		   }
		return false;
	}
}
