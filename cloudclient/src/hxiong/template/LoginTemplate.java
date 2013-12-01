package hxiong.template;

import hxiong.clientui.FirstView;
import hxiong.opreate.LocalFiles;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 这是一个登录面板，面板上面会有登录的文本框密码框等模块
 * 该面板作为登录界面使用
 * @author 熊天成
 *
 */
public class LoginTemplate extends javax.swing.JPanel{

	private static final long serialVersionUID = 1L;
	private FirstView parent;//该面板所依赖的父面板
    //重载构造方法，实现初始化面板
	public LoginTemplate(FirstView parent){
		this.parent=parent;
		this.initLoginTemplate();
	}
	/**
	 * 初始化面板
	 */
	private void initLoginTemplate(){
		this.setPreferredSize(new Dimension(240,190));
		this.setBackground(Color.WHITE);
		this.setLayout(new BorderLayout());//设置为边框布局

		//上面部分的面板，用来修饰
		javax.swing.JPanel top_panel=new javax.swing.JPanel();
		top_panel.setOpaque(false);
		top_panel.setPreferredSize(new Dimension(240,30));
		this.add(top_panel,BorderLayout.NORTH);
		
		//中间部分，用来添加帐号，密码等文本框
		javax.swing.JPanel center_panel=new javax.swing.JPanel();
		center_panel.setOpaque(false);
		center_panel.setPreferredSize(new Dimension(240,120));
		Insets ins=new Insets(1,1,1,1);//设置边距
		javax.swing.JLabel account_lab=new javax.swing.JLabel("帐号");
		final javax.swing.JTextField account_text=new javax.swing.JTextField(12);
		javax.swing.JButton account_reg=new javax.swing.JButton("注册帐号");
		account_reg.setActionCommand("regins");
		account_reg.setMargin(ins);
		center_panel.add(account_lab);
		center_panel.add(account_text);
		center_panel.add(account_reg);
		
		javax.swing.JLabel passwd_lab=new javax.swing.JLabel("密码");
		final javax.swing.JPasswordField passwd_text=new javax.swing.JPasswordField(12);
		javax.swing.JButton passwd_reg=new javax.swing.JButton("找回密码");
		passwd_reg.setActionCommand("findpasswd");
		passwd_reg.setMargin(ins);
		
		center_panel.add(passwd_lab);
		center_panel.add(passwd_text);
		center_panel.add(passwd_reg);
		
		//添加记住密码和自动登录的复选框
		final javax.swing.JCheckBox remen=new javax.swing.JCheckBox("记住密码");
		final javax.swing.JCheckBox redo=new javax.swing.JCheckBox("自动登录");
		javax.swing.JLabel sparate=new javax.swing.JLabel("      ");//是两个组件间的距离增大
		center_panel.add(remen);
		center_panel.add(sparate);
		center_panel.add(redo);
		boolean bl=LocalFiles.getOpreation(account_text, passwd_text, remen);
		redo.setSelected(bl);
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
					char[] pswd=passwd_text.getPassword();
					String account=account_text.getText();//获取帐号
					String passwd=new String(pswd);//获得密码
					if(!account.equals("")&&!passwd.equals("")){//帐号和密码都不为空
					  //开始验证
					
					   if(remen.isSelected()){//如果选择了记住密码，则在登录成功之后保存
						  LocalFiles.saveUserInfo(account, passwd);//保存用户密码到文件
					   }
					   LocalFiles.saveOpreation(remen.isSelected()?1:0, redo.isSelected()?1:0);//保存用户的操作
				    }
				}else if(command.equals("regins")){
					parent.remove(LoginTemplate.this);//移除当前面板
					RegistTemplate regist=new RegistTemplate(parent);
					parent.add(regist);//添加注册界面
				}else if(command.equals("findpasswd")){
					
				}				
			}
			
		};
		//添加监听器
		login_jbu.addActionListener(actlis);
		account_reg.addActionListener(actlis);
		passwd_reg.addActionListener(actlis);
	}
}
