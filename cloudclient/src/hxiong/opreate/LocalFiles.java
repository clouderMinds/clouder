package hxiong.opreate;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * 操作本地文件的一个类，该类提供保存用户帐号和密码等信息
 * @author 熊天成
 *
 */
public class LocalFiles {
        
	/**
	 * 将用户的帐号和密码保存到文件的方法。该方法在保存时会对它进行简单的加密
	 * @param account 用户的帐号
	 * @param passwd 用户的密码
	 * @return 如果操作成功，则返回true，否则返回false
	 */
	public static boolean saveUserInfo(String account,String passwd){
		try {
			//打开文件
			java.io.FileOutputStream fous=new java.io.FileOutputStream("/home/hadoop/userinfo.xtc");
			java.io.DataOutputStream dous=new java.io.DataOutputStream(fous);//封装数据流
			byte[] acc=account.getBytes();
			dous.writeInt(acc.length);//写入帐号的长度
			for(int i=0;i<acc.length;i++){
				acc[i]+=5;//简单加密
				dous.write(acc[i]);
			}
			byte[] pss=passwd.getBytes();
			dous.writeInt(pss.length);//写入密码的长度
			for(int j=0;j<pss.length;j++){
				pss[j]+=5;//简单加密
				dous.write(pss[j]);
			}
			fous.close();//关闭流
			return true;//操作成功返回true
		
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	/**
	 * 该方法不会有任何返回值，而是需要用户将用户框和密码框对象传入
	 * @param account_text 用户帐号框
	 * @param passwd_text 密码框
	 */
	public static void getUserInfo(javax.swing.JTextField account_text,javax.swing.JPasswordField passwd_text){
		try {
			java.io.FileInputStream fins=new java.io.FileInputStream("/home/hadoop/userinfo.xtc");
			java.io.DataInputStream dins=new java.io.DataInputStream(fins);//封装为data数据流
		 	int len=dins.readInt();//读取帐号的长度
		    byte[] acc=new byte[len];//创建对应长度的字节数组
		    dins.read(acc);//读取帐号字节
		    for(int i=0;i<len;i++){
		    	acc[i]-=5;//简单的解密
		    }
		    len=dins.readInt();//读取帐号的长度
		    byte[] pss=new byte[len];//创建对应长度的字节数组
		    dins.read(pss);//读取帐号字节
		    for(int j=0;j<len;j++){
		    	pss[j]-=5;//简单的解密
		    }
		    String account=new String(acc);
		    String passwd=new String(pss);
		    account_text.setText(account);
		    passwd_text.setText(passwd);
		    
		    fins.close();//关闭流
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
	}
	/**
	 * 该方法用来保存用户的选择，主要是保存用户是否记住密码和自动登录的选择
	 * @param o 代表记住密码的选择
	 * @param s 代表自动登录的选择
	 * @return 如果保存成功，返回true，否则返回false
	 */
	public static boolean saveOpreation(int o,int s){
		try {
			java.io.FileOutputStream fous=new java.io.FileOutputStream("/home/hadoop/opreation.xtc");
			fous.write(o);//保存
			fous.write(s);//保存
			fous.close();//关闭流
		    return true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	/**
	 * 获取用户的上一次的操作，只是登录阶段的操作
	 * @param account_text 登录界面的帐号框
	 * @param passwd_text 登录界面的密码框
	 * @param remen 记住密码复选框
	 * @param redo 自动登录复选框
	 * @return 如果选择了记住密码和自动登录返回true，否则返回false
	 */
	public static boolean getOpreation(javax.swing.JTextField account_text,javax.swing.JPasswordField passwd_text,
			javax.swing.JCheckBox remen){
		try {
			java.io.FileInputStream fins=new java.io.FileInputStream("/home/hadoop/opreation.xtc");
		    int o=fins.read();
            int s=fins.read();
            fins.close();//关闭流		
            if(o==1){//如果选择记住密码
                LocalFiles.getUserInfo(account_text, passwd_text);
                remen.setSelected(true);//设置为记住密码
                return (s==1)?true:false;//返回值
            }
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
}
