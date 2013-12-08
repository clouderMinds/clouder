package hxiong.template;

import hlong.Client.Client;
import hlong.Client.UIMessageListener;
import hlong.Request.RegisterRequest;
import hlong.Response.RegisterResponse;
import hxiong.clientui.FirstView;
import hxiong.config.ClientuiConfig;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;


/**
 * 这是一个显示注册界面的模版，该注册界面主要提供用户注册帐号的功能
 * @author 熊天成
 *
 */
public class RegistTemplate extends javax.swing.JPanel implements UIMessageListener{

	private static final long serialVersionUID = 1L;
	private FirstView parent;//该面板所依赖的父面板
	private ImageIcon reginBg=new ImageIcon(this.getClass().getResource("images/reginbackground.jpg"));
    private javax.swing.JPanel mess;
	//重载构造方法
	public RegistTemplate(FirstView parent){
		this.parent=parent;
		this.initRegistTemplate();
	}
	//重新paint方法
		protected void paintComponent(Graphics g) {  
			super.paintComponent(g);
		     Image imgBg = reginBg.getImage();  
		     g.drawImage(imgBg, 0, 0, 240,320, reginBg.getImageObserver());  
		 }  	
	//初始化界面和添加功能的方法
	private void initRegistTemplate(){
		this.setBounds((ClientuiConfig.panelWidth-240)/2, (ClientuiConfig.panelHeight-360)/2,240,320);
		javax.swing.border.LineBorder line=new javax.swing.border.LineBorder(Color.darkGray,1);
		this.setBorder(line);
		this.setLayout(new BorderLayout());//设置为边框布局
		//
		
		//上面部分的面板，用来修饰
		javax.swing.JPanel top_panel=new javax.swing.JPanel();
		top_panel.setLayout(new BorderLayout());
		top_panel.setOpaque(false);
		top_panel.setPreferredSize(new Dimension(240,30));
		javax.swing.JLabel text_lab=new javax.swing.JLabel("欢迎注册");
		text_lab.setFont(new java.awt.Font("华文行楷", Font.BOLD, 16));
		text_lab.setForeground(Color.RED);
		top_panel.add(text_lab,BorderLayout.WEST);
		javax.swing.JButton back_jbu=new javax.swing.JButton("返回");
		back_jbu.setActionCommand("back");
		top_panel.add(back_jbu,BorderLayout.EAST);
		this.add(top_panel,BorderLayout.NORTH);
		
		//中间部分，用来添加帐号，密码等文本框
		javax.swing.JPanel center_panel=new javax.swing.JPanel();
		center_panel.setOpaque(false);
		center_panel.setPreferredSize(new Dimension(240,240));
		javax.swing.JLabel sparate_one=new javax.swing.JLabel("必填内容-----------------------------------");
		sparate_one.setForeground(Color.ORANGE);
		center_panel.add(sparate_one);
		javax.swing.JLabel account_lab=new javax.swing.JLabel("用户帐号：");
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
		javax.swing.JLabel sparate_two=new javax.swing.JLabel("选填内容-----------------------------------");
		sparate_two.setForeground(Color.ORANGE);
		center_panel.add(sparate_two);
		javax.swing.JLabel email_lab=new javax.swing.JLabel("邮箱：");
		javax.swing.JTextField email_text=new javax.swing.JTextField(15);
		center_panel.add(email_lab);
		center_panel.add(email_text);
		javax.swing.JLabel qq_lab=new javax.swing.JLabel("Q Q：");
		javax.swing.JTextField qq_text=new javax.swing.JTextField(15);
		center_panel.add(qq_lab);
		center_panel.add(qq_text);
		javax.swing.JLabel phone_lab=new javax.swing.JLabel("手机：");
		javax.swing.JTextField phone_text=new javax.swing.JTextField(15);
		center_panel.add(phone_lab);
		center_panel.add(phone_text);
		javax.swing.JLabel other_lab=new javax.swing.JLabel("地址：");
		javax.swing.JTextField other_text=new javax.swing.JTextField(15);
		center_panel.add(other_lab);
		center_panel.add(other_text);
		
		mess=new javax.swing.JPanel();//提示面板
		mess.setOpaque(false);
		mess.setPreferredSize(new Dimension(240,30));
		mess.setLayout(new FlowLayout(FlowLayout.LEFT));
		center_panel.add(mess);
		this.add(center_panel,BorderLayout.CENTER);
		
		//下面部分
		javax.swing.JPanel bottom=new javax.swing.JPanel();
		bottom.setOpaque(false);
		bottom.setPreferredSize(new Dimension(240,40));
		bottom.setLayout(new FlowLayout(FlowLayout.CENTER));
		javax.swing.JButton regin_jbu=new javax.swing.JButton("注 册");
		regin_jbu.setActionCommand("regin");
		bottom.add(regin_jbu);
		this.add(bottom,BorderLayout.SOUTH);
		//使用匿名内部类创建监听器
		ActionListener actlist=new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getActionCommand().equals("regin")){
	               String user_name=account_text.getText();//获取帐号
	               char[] passwd=passwd_text.getPassword();//获取密码
	               String user_passwd=new String(passwd);//转换成为字符串
	               char[] cmpasswd=confirm_text.getPassword();
	               String user_cmpasswd=new String(cmpasswd);
	               if(!user_passwd.equals("")&&user_passwd.equals(user_cmpasswd)){//只有当输入密码和确认密码一致时才进行正式注册
	            	  try{
	            	       RegisterRequest res=new RegisterRequest(user_name,user_passwd);
	            	       Client.getClient(RegistTemplate.this).sentMsg(res);//发送消息
	            	  }catch(Exception ex){
	            		  mess.removeAll();//移除所有组件
	      				  javax.swing.JLabel warning=new javax.swing.JLabel("连接服务器失败，请检查网络设置!");
	      				  mess.add(warning);
	      				  mess.updateUI();
	            		  ex.printStackTrace();
	            	  }
	               }
	               else{//提示用户输入密码和确认密码不一致，要重新输入
	            	confirm_text.setText("");//清空确认密码框里面的数据
	            	
	               }
				}else if(e.getActionCommand().equals("back")){
					parent.remove(RegistTemplate.this);//移除注册组件
		        	LoginTemplate login=new LoginTemplate(parent);//创建登录模块
		        	parent.add(login);//添加登录模块
		        	parent.updateUI();
				}
			}
			
		};
		//添加监听器,
		regin_jbu.addActionListener(actlist);
		back_jbu.addActionListener(actlist);
	}
	
	@Override//接收消息的方法
	public void onMessageReceived(Object msg) {
		String name = msg.getClass().getSimpleName();
		if(name.equals("RegisterResponse")){
			RegisterResponse re=(RegisterResponse)msg;
			if(re.getState()==1){
				//如果注册成功
	        	parent.remove(RegistTemplate.this);//移除注册组件
	        	LoginTemplate login=new LoginTemplate(parent);//创建登录模块
	        	parent.add(login);//添加登录模块
	        	parent.updateUI();
			}
		}
	}

}
