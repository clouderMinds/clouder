package hxiong.template;

import hlong.Client.Client;
import hlong.Client.UIMessageListener;
import hlong.Request.LoginRequest;
import hlong.Response.LoginResponse;
import hxiong.clientui.FirstView;
import hxiong.config.ClientuiConfig;
import hxiong.config.UserConfig;
import hxiong.opreate.LocalFiles;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;

/**
 * 这是一个登录面板，面板上面会有登录的文本框密码框等模块
 * 该面板作为登录界面使用
 * @author 熊天成
 *
 */
public class LoginTemplate extends javax.swing.JPanel implements UIMessageListener{

	private static final long serialVersionUID = 1L;
	private FirstView parent;//该面板所依赖的父面板
	private ImageIcon loginBg=new ImageIcon(this.getClass().getResource("images/loginbackground.jpg"));
    private String account;
    private String passwd;
    private javax.swing.JCheckBox remen;
    private javax.swing.JCheckBox redo;
    private javax.swing.JPanel statePanel;
    private javax.swing.JTextField account_text;
    private javax.swing.JPasswordField passwd_text;
	//重载构造方法，实现初始化面板
	public LoginTemplate(FirstView parent){
		this.parent=parent;
		this.initLoginTemplate();
	}
	//重新paint方法
		protected void paintComponent(Graphics g) {  
			super.paintComponent(g);
	        Image imgBg = loginBg.getImage();  
	        g.drawImage(imgBg, 0, 0, 280,190, loginBg.getImageObserver());  
	    }  
	/**
	 * 初始化面板
	 */
	private void initLoginTemplate(){
		this.setBounds((ClientuiConfig.panelWidth-280)/2, (ClientuiConfig.panelHeight-240)/2,280,190);
		javax.swing.border.LineBorder line=new javax.swing.border.LineBorder(Color.darkGray,1);
		this.setBorder(line);
		this.setLayout(new BorderLayout());//设置为边框布局
         
		//上面部分的面板，用来修饰
		javax.swing.JPanel top_panel=new javax.swing.JPanel();
		top_panel.setLayout(new FlowLayout(FlowLayout.LEFT));
		top_panel.setOpaque(false);
		top_panel.setPreferredSize(new Dimension(240,30));
		javax.swing.JLabel text_lab=new javax.swing.JLabel("用户登录");
		text_lab.setFont(new java.awt.Font("华文行楷", Font.BOLD, 16));
		text_lab.setForeground(Color.RED);
		top_panel.add(text_lab);
		this.add(top_panel,BorderLayout.NORTH);
		
		//中间部分，用来添加帐号，密码等文本框
		javax.swing.JPanel center_panel=new javax.swing.JPanel();
		center_panel.setOpaque(false);
		center_panel.setPreferredSize(new Dimension(240,120));
		Insets ins=new Insets(1,1,1,1);//设置边距
		javax.swing.JLabel account_lab=new javax.swing.JLabel("帐号");
		account_text=new javax.swing.JTextField(14);
		javax.swing.JButton account_reg=new javax.swing.JButton("注册帐号");
		account_reg.setOpaque(false);
		account_reg.setActionCommand("regins");
		account_reg.setMargin(ins);
		center_panel.add(account_lab);
		center_panel.add(account_text);
		center_panel.add(account_reg);
		
		javax.swing.JLabel passwd_lab=new javax.swing.JLabel("密码");
		passwd_text=new javax.swing.JPasswordField(14);
		javax.swing.JButton passwd_reg=new javax.swing.JButton("找回密码");
		passwd_reg.setActionCommand("findpasswd");
		passwd_reg.setMargin(ins);
		
		center_panel.add(passwd_lab);
		center_panel.add(passwd_text);
		center_panel.add(passwd_reg);
		
		//添加记住密码和自动登录的复选框
		remen=new javax.swing.JCheckBox("记住密码");
		redo=new javax.swing.JCheckBox("自动登录");
		javax.swing.JLabel sparate=new javax.swing.JLabel("       ");//是两个组件间的距离增大
		remen.setOpaque(false);
		redo.setOpaque(false);
		center_panel.add(remen);
		center_panel.add(sparate);
		center_panel.add(redo);
		UserConfig.isSignIn=LocalFiles.getOpreation(account_text, passwd_text, remen);
		redo.setSelected(UserConfig.isSignIn);
		statePanel =new javax.swing.JPanel();
		statePanel.setOpaque(false);
		statePanel.setPreferredSize(new Dimension(280,30));
		statePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		center_panel.add(statePanel);
		this.add(center_panel,BorderLayout.CENTER);		
		
		//下面部分
		javax.swing.JPanel bottom=new javax.swing.JPanel();
		bottom.setOpaque(false);
		bottom.setPreferredSize(new Dimension(240,40));
		javax.swing.JButton login_jbu=new javax.swing.JButton("登 录");
		login_jbu.setActionCommand("signup");
		bottom.add(login_jbu);
		this.add(bottom,BorderLayout.SOUTH);
		
		//使用匿名内部类，创建监听器
		ActionListener actlis=new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				String command=e.getActionCommand();
				if(command.equals("signup")){
					signIn();
					
				}else if(command.equals("regins")){
					parent.remove(LoginTemplate.this);//移除当前面板
					RegistTemplate regist=new RegistTemplate(parent);
					parent.add(regist);//添加注册界面
					parent.updateUI();//刷新界面
				}else if(command.equals("findpasswd")){
					
				}				
			}
			
		};
		//添加监听器
		login_jbu.addActionListener(actlis);
		account_reg.addActionListener(actlis);
		passwd_reg.addActionListener(actlis);
	}
	/**
	 * 一个登录的方法，该方法只负责向服务器发送要验证的帐号和密码
	 */
	public void signIn(){
		char[] pswd=passwd_text.getPassword();
		account=account_text.getText();//获取帐号
		passwd=new String(pswd);//获得密码
		if(!account.equals("")&&!passwd.equals("")){//帐号和密码都不为空
		  //开始验证
			try{
			   LoginRequest loginmsg=new LoginRequest(account,passwd);
			   Client.getClient(LoginTemplate.this).sentMsg(loginmsg);
			}catch(Exception e){
				statePanel.removeAll();//移除所有组件
				javax.swing.JLabel warning=new javax.swing.JLabel("与服务器连接失败，请检查网络!");
				statePanel.add(warning);
				statePanel.updateUI();
				UserConfig.isConnect=false;//表示登录失败
				e.printStackTrace();
			}
			LocalFiles.saveOpreation(remen.isSelected()?1:0, redo.isSelected()?1:0);//保存用户的操作  	
	    }
	}
	@Override
	public void onMessageReceived(Object msg) {
		String name = msg.getClass().getSimpleName();
		if(name.equals("LoginResponse")){
			LoginResponse re=(LoginResponse)msg;
			if(re.getState()==LoginResponse.SUCCESS){
				UserConfig.account=account;//保存用户帐号
				//如果注册成功
	        	parent.remove(LoginTemplate.this);//移除注册组件
	        	InfoTemolate info=new InfoTemolate(parent);
	        	parent.add(info);
                //添加登录模块
	        	parent.updateUI();
	        	UserConfig.isConnect=true;//表示登录成功
	        	parent.updateFsTemplate();//实际上是调用第三个面板的刷新方法
	   		    if(remen.isSelected()){//如果选择了记住密码，则在登录成功之后保存
				  LocalFiles.saveUserInfo(account, passwd);//保存用户密码到文件
			   }
			}else{
				statePanel.removeAll();//移除所有组件
				javax.swing.JLabel warning=new javax.swing.JLabel("帐号或密码错误!");
				statePanel.add(warning);
				statePanel.updateUI();
				UserConfig.isConnect=false;//表示登录失败
			}
		}

	}
}
