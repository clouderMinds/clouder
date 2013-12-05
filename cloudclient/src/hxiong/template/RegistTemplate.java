package hxiong.template;

import hlong.Client.Client;
import hlong.Client.UIMessageListener;
import hlong.Request.RegisterRequest;
import hlong.Response.RegisterResponse;
import hxiong.clientui.FirstView;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * 这是一个显示注册界面的模版，该注册界面主要提供用户注册帐号的功能
 * @author 熊天成
 *
 */
public class RegistTemplate extends javax.swing.JPanel implements UIMessageListener{

	private static final long serialVersionUID = 1L;
	private FirstView parent;//该面板所依赖的父面板
    //重载构造方法
	public RegistTemplate(FirstView parent){
		this.parent=parent;
		this.initRegistTemplate();
	}
	//初始化界面和添加功能的方法
	private void initRegistTemplate(){
		this.setPreferredSize(new Dimension(240,190));
		this.setBackground(Color.WHITE);
		this.setLayout(new BorderLayout());//设置为边框布局
		//
		
		//上面部分的面板，用来修饰
		javax.swing.JPanel top_panel=new javax.swing.JPanel();
		top_panel.setOpaque(false);
		top_panel.setPreferredSize(new Dimension(240,30));
		this.add(top_panel,BorderLayout.NORTH);
		
		//中间部分，用来添加帐号，密码等文本框
		javax.swing.JPanel center_panel=new javax.swing.JPanel();
		center_panel.setOpaque(false);
		center_panel.setPreferredSize(new Dimension(240,120));
		javax.swing.JLabel account_lab=new javax.swing.JLabel("用户昵称：");
		final javax.swing.JTextField account_text=new javax.swing.JTextField(12);
		center_panel.add(account_lab);
		center_panel.add(account_text);
		
		javax.swing.JLabel passwd_lab=new javax.swing.JLabel("用户密码：");
		final javax.swing.JPasswordField passwd_text=new javax.swing.JPasswordField(12);
		center_panel.add(passwd_lab);
		center_panel.add(passwd_text);
		
		javax.swing.JLabel confirm_lab=new javax.swing.JLabel("确认密码：");
		final javax.swing.JPasswordField confirm_text=new javax.swing.JPasswordField(12);
		center_panel.add(confirm_lab);
		center_panel.add(confirm_text);
		
		this.add(center_panel,BorderLayout.CENTER);
		
		//下面部分
		javax.swing.JPanel bottom=new javax.swing.JPanel();
		bottom.setOpaque(false);
		bottom.setPreferredSize(new Dimension(240,40));
		bottom.setLayout(new FlowLayout(FlowLayout.RIGHT));
		javax.swing.JButton regin_jbu=new javax.swing.JButton("注 册");
		bottom.add(regin_jbu);
		this.add(bottom,BorderLayout.SOUTH);
		//使用匿名内部类创建监听器
		ActionListener actlist=new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				
	            String user_name=account_text.getText();//获取帐号
	            char[] passwd=passwd_text.getPassword();//获取密码
	            String user_passwd=new String(passwd);//转换成为字符串
	            char[] cmpasswd=confirm_text.getPassword();
	            String user_cmpasswd=new String(cmpasswd);
	            if(!user_passwd.equals("")&&user_passwd.equals(user_cmpasswd)){//只有当输入密码和确认密码一致时才进行正式注册
	            	
	            	RegisterRequest res=new RegisterRequest(user_name,user_passwd);
	            	Client.getClient(RegistTemplate.this).sentMsg(res);//发送消息
	            }
	            else{//提示用户输入密码和确认密码不一致，要重新输入
	            	confirm_text.setText("");//清空确认密码框里面的数据
	            	
	            }
			}
			
		};
		//添加监听器,
		regin_jbu.addActionListener(actlist);
	}
	
	@Override//接收消息的方法
	public void onMessageReceived(Object msg) {
		String name = msg.getClass().getSimpleName();
		System.out.println(name);
		RegisterResponse re=(RegisterResponse)msg;
		System.out.println(re.getID());
		System.out.println(re.getState());
		if(msg.equals("")){
			//如果注册成功
        	parent.remove(RegistTemplate.this);//移除注册组件
        	LoginTemplate login=new LoginTemplate(parent);//创建登录模块
        	parent.add(login);//添加登录模块
		}
	}

}
