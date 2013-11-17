package demotool;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

/**
 * 这是一个对应文件视图进行操作的类
 * @author hadoop
 *
 */
public class SocketFileTool {
	private String user_account;
	private Socket sock;//与服务器的连接对象
	private DataOutputStream dataops;//输入列
	private DataInputStream dataips;//输入流
	//重载构造方法
	public SocketFileTool(String user_account){
		this.user_account=user_account;
		this.initConnector();
	}
	//创建与服务器的连接
	private void initConnector(){
		try {
			sock=new Socket("lanyun-2013.oicp.net",9090);//建立与服务器的连接
			OutputStream oups=sock.getOutputStream();
			dataops=new DataOutputStream(oups);//获取流
			InputStream inps=sock.getInputStream();
			dataips=new DataInputStream(inps);			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 从服务器获取文件视图模型的方法
	 * @return
	 */
	public DefaultTreeModel getFileVersion(){
		if(user_account!=null){//如果用户已经登录
		try {
			String headbyte="0x05";
			dataops.write(headbyte.getBytes());//写出包头消息
			dataops.writeInt(user_account.getBytes(CharConfig.charset).length);//写出用户帐号
			dataops.write(user_account.getBytes(CharConfig.charset));
			dataops.flush();//强制写出
			byte[] type=new byte[4];
			dataips.read(type);//读取服务器返回信息
			String mes=new String(type);
			if(mes.equals("0x15")){
				int len=dataips.readInt();//读取消息长度
				byte[] mess=new byte[len];
				dataips.read(mess);
				String backcode=new String(mess,CharConfig.charset);
				DefaultMutableTreeNode rootnode=new DefaultMutableTreeNode();
				DefaultMutableTreeNode root=rootnode;
				rootnode.setUserObject("xiong");
				boolean bl=false;
				while(!backcode.equals("/end")){					
					if(backcode.equals("/next")){//说明要创建下一曾目录
						bl=true;
					}else if(backcode.equals("/back")){
						rootnode=(DefaultMutableTreeNode)rootnode.getParent();
					}else{//
						DefaultMutableTreeNode node=new DefaultMutableTreeNode();
						node.setUserObject(backcode);
						rootnode.add(node);//将节点添加到里面
						if(bl){//
							rootnode=node;
							bl=false;
						}
					}
					//
					len=dataips.readInt();//
					mess=new byte[len];
					dataips.read(mess);//读取消息
					backcode=new String(mess,CharConfig.charset);
				}
				DefaultTreeModel treemodel=new DefaultTreeModel(root);
				return treemodel;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	  }
		return null;
	}
	/**
	 * 获取用户和信息的方法，
	 * @param treepath 
	 * @return 返回用户信息队列
	 */
	public ArrayList<String> getCurrentVersion(String treepath){
		if(user_account!=null){//如果用户登录成功
			String headbyte="0x09";
			try {
				dataops.write(headbyte.getBytes());//
				dataops.writeInt(user_account.getBytes(CharConfig.charset).length);//发送用户帐号
				dataops.write(user_account.getBytes(CharConfig.charset));
				dataops.writeInt(treepath.getBytes(CharConfig.charset).length);//发送文件路径
				dataops.write(treepath.getBytes(CharConfig.charset));
				dataops.flush();//强制写出
				byte[] type=new byte[4];
				dataips.read(type);//读取头信息
				String mes=new String(type);
				if(mes.equals("0x19")){
					ArrayList<String> filelist=new ArrayList<String>();					
					int listlen=dataips.readInt();
					for(int j=0;j<listlen;j++){
						int len=dataips.readInt();//读取消息长度
						byte[] mess=new byte[len];
						dataips.read(mess);//
						String backcode=new String(mess,CharConfig.charset);
						filelist.add(backcode);//把消息添加到队列里面
					}
					return filelist;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	/**
	 * 
	 * @return
	 */
	public boolean closeConnect(){
		try {
			//关闭流
			dataops.close();
			dataips.close();
			sock.close();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
}
