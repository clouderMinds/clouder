package demotool;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;

/**
 * 这是一个负责发送用户密码进行验证和获取用户信息的类
 * @author hadoop
 *
 */
public class SocketConnecter {
    private Socket sock;//需要的连接对象
    private DataOutputStream dataops;//输出流
    private DataInputStream dataips;//输入流
    private String user_account=null;//用户帐号־
    //重载构造方法
	public SocketConnecter(Socket sock){
		this.sock=sock;//连接对象
		try {
			OutputStream oups=sock.getOutputStream();
			dataops=new DataOutputStream(oups);//获取输出流
			InputStream inps=sock.getInputStream();
			dataips=new DataInputStream(inps);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 获取用户的帐号
	 * @return 返回用户的帐号
	 */
	public String getAccount(){
		return user_account;
	}
	/**
	 * 发送帐号和密码进行验证
	 * @param account 用户的帐号
	 * @param passwd 用户的密码
	 * @return 如果登录成功 返回true 否则返回false
	 */
	public boolean sendLoginMessage(String account,String passwd){
		user_account=account;//给用户对象进行赋值
		String headbyte="0x02";
		try {
			dataops.write(headbyte.getBytes());//先发包头
			dataops.writeInt(account.getBytes(CharConfig.charset).length);//发送帐号的长度
			dataops.write(account.getBytes(CharConfig.charset));
			dataops.writeInt(passwd.getBytes(CharConfig.charset).length);//发送密码的长度
			dataops.write(passwd.getBytes(CharConfig.charset));
			dataops.flush();//强制写出
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	/**
	 * 获取登录的返回信息
	 * @return
	 */
	public String getLoginMessage(){		
		String error_code="";
		try {
			byte[] type=new byte[4];
			dataips.read(type);//服务器发回的包头消息
			String mes=new String(type);
			if(mes.equals("0x12")){
				int len=dataips.readInt();//获取返回消息的长度
				byte[] mess=new byte[len];
				dataips.read(mess);//获取返回消息数组
				error_code=new String(mess);
			}			
		} catch (IOException e) {
			e.printStackTrace();
		}		
		return error_code;
	}
	/**
	 * 饭送获取用户信息的请求
	 * @return
	 */
	public boolean sendRefreshInfomation(){
		if(user_account!=null){//�����û��Ǵ��ڵ�
			String headbyte="0x08";
			try {
				dataops.write(headbyte.getBytes());//��д��ͷ
				dataops.writeInt(user_account.getBytes().length);//�ʺŵĳ���
				dataops.write(user_account.getBytes());
				dataops.flush();//ǿ��д��
				return true;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	/**
	 * 获取用户的信息
	 * @return 返回用户信息队列
	 */
	public ArrayList<String> getUserInfomation(){
		try {
			ArrayList<String> usr_info=null;
			byte[] type=new byte[4];
			dataips.read(type);//��ȡ��Ϣ�ֽ�
			String mes=new String(type);
			if(mes.equals("0x18")){
				usr_info=new ArrayList<String>();//����һ�������û���Ϣ�Ķ���
				int array_len=dataips.readInt();//��ȡ������Ҫ���͵���Ϣ�ĳ��ȣ���������û���Ϣ
				for(int i=0;i<array_len;i++){
					int len=dataips.readInt();//��ȡ������Ϣ�ĳ���
					byte[] mess=new byte[len];
					dataips.read(mess);//��ȡ��Ϣ�ֽ�
					String error_code=new String(mess,CharConfig.charset);
					usr_info.add(error_code);
				}				
			}	
			return usr_info;//�����û���Ϣ
		} catch (IOException e) {
			e.printStackTrace();
		}		
		return null;//���ذ������û���Ϣ�Ķ���
	}
	/**
	 * 关闭服务器连接的方法
	 * @return
	 */
	public boolean closeConnect(){
		try {
			//关闭所有的连接
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
