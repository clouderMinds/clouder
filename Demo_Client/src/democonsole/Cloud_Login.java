package democonsole;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import demotool.LoginChecker;
import demotool.SocketConnecter;

/**
 * 登录界面，显示用户登录的界面
 * @author hadoop
 *
 */
public class Cloud_Login extends JFrame{

	private static final long serialVersionUID = 1L;
	public static void main(String[] args) {
		//创建对象并调用方法
		Cloud_Login cloud_login=new Cloud_Login();
		cloud_login.showUI();
	}
	//创世和登录界面的方法
	public void showUI(){
		this.setSize(340, 260);
		this.setResizable(false);
		this.setLayout(new BorderLayout());//设置为边框布局
		this.setUndecorated(true);//去掉窗体的外边框
		this.setLocationRelativeTo(null);
		//界面的应用图片
		ImageIcon app_login=new ImageIcon(this.getClass().getResource("images/app_login.png"));
		this.setIconImage(app_login.getImage());
		//创建一个后台托管对象
		final SystemTrayFrame systray=new SystemTrayFrame(this);
		systray.showSystemTray();
		
		//窗体的上部分，用来显示最淡化，最小化和关闭按钮
		LoginTopPanel jpn_menu=new LoginTopPanel(this,systray);
		jpn_menu.showLoginTopUI();//调用初始化窗体陕北部分的方法		
		this.add(jpn_menu,BorderLayout.NORTH);	
		
        /******************窗体的中间部分*******************/
		JPanel jpn_content=new JPanel(new BorderLayout());		

        /******************窗体的******************/
		JPanel jpn_top=new JPanel();
		jpn_top.setLayout(new BorderLayout());
		JLabel label=new JLabel();
		ImageIcon icon=new ImageIcon(this.getClass().getResource("images/logo.jpg"));
		label.setIcon(icon);
		jpn_top.add(label);
		jpn_content.add(jpn_top,BorderLayout.NORTH);
			
		/*********************添加帐号和密码输入框的部分*********************/
		JPanel jpn_center=new JPanel(new FlowLayout(FlowLayout.CENTER));
		jpn_center.setBackground(new Color(163,215,252));
		JLabel lb_user=new JLabel("        帐号  :  ");
		final JTextField jtf_user=new JTextField(15);
		JLabel lb_pwd=new JLabel("        密码  :  ");
		final JPasswordField jpf_pwd=new JPasswordField(15);
		//lb_user.setFont(new Font("微软黑体",Font.PLAIN,14));
		//lb_pwd.setFont(new Font("微软黑体",Font.PLAIN,14));
		//添加标签和输入框
		jpn_center.add(lb_user);
		jpn_center.add(jtf_user);
		jpn_center.add(lb_pwd);
		jpn_center.add(jpf_pwd);
		
		jpn_content.add(jpn_center,BorderLayout.CENTER);
		
		/***************窗体的下部分***************/
		JPanel jpn_bottom=new JPanel();
		jpn_bottom.setBackground(new Color(161,215,253));
		
		ImageIcon icon_login=new ImageIcon(this.getClass().getResource("images/login2.png"));
		ImageIcon icon_regiseter=new ImageIcon(this.getClass().getResource("images/register.png"));
		JButton jbt_login=new JButton(icon_login);
		JButton jbt_register=new JButton(icon_regiseter);
		//添加注册和登录按钮
		jbt_login.setActionCommand("login_to_hdfs");
		jbt_register.setActionCommand("register_to_hdfs");
		//设在按钮的属性
		jbt_login.setContentAreaFilled(false);
		jbt_register.setContentAreaFilled(false);
		jbt_login.setBorderPainted(false);
		jbt_register.setBorderPainted(false);

		jpn_bottom.add(jbt_register);
		jpn_bottom.add(jbt_login);
		jpn_content.add(jpn_bottom,BorderLayout.SOUTH);
		
		this.add(jpn_content,BorderLayout.CENTER);		
		this.setVisible(true);

		/*******************ʹ给按钮添加监听器*********************/
		ActionListener actlis=new ActionListener(){

			public void actionPerformed(ActionEvent act) {
				String command=act.getActionCommand();
				if(command.equals("login_to_hdfs")){
					//获取文本框的内容
					String usr_name=jtf_user.getText();
					char[] passwd=jpf_pwd.getPassword();
					String usr_passwd=new String(passwd);
					//将密码框清空
					jpf_pwd.setText("");
					LoginChecker login_checker=new LoginChecker();
					SocketConnecter sockconn=login_checker.checkLogin(usr_name, usr_passwd);
					if(sockconn!=null){//获取连接对象
						String callback=sockconn.getLoginMessage();
						if(callback.equals("0")){
							Cloud_Login.this.dispose();
							systray.removeTrayIcon();//如果动脑管理成功，移除后台托管对象
							//创建主界面，并进入主界面
							MainDemo demo=new MainDemo(sockconn);
							demo.showMainDemoUI();
						}else{
							
						}
					}
				}else if(command.equals("register_to_hdfs")){
					
					
				}
			}			
		};
		//给按钮添加监听器
		jbt_login.addActionListener(actlis);
		jbt_register.addActionListener(actlis);
	}

}
