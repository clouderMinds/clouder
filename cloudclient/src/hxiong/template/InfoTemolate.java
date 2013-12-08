package hxiong.template;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import hxiong.clientui.FirstView;
import hxiong.config.ClientuiConfig;
import hxiong.config.UserConfig;

/**
 * 用户信息模板，即用户登录成功之后会在中间面板的顶端显示用户的相关信息
 * 包括用户的昵称，等级，云盘容量，系统消息和退出等功能按钮
 * @author 熊天成
 *
 */
public class InfoTemolate extends javax.swing.JPanel{
	private static final long serialVersionUID = 1L;
	private FirstView parent;//该面板所依赖的父面板
	//重载构造方法
	public InfoTemolate(FirstView parent){
		this.parent=parent;
		this.initInfoTemolate();
	}
	//初始化面板和添加相应功能
	private void initInfoTemolate(){
	  this.setBounds(0, 0, ClientuiConfig.panelWidth, 35);
	  this.setLayout(new FlowLayout(FlowLayout.RIGHT));//居右对齐
	  javax.swing.JLabel size_lab=new javax.swing.JLabel("空间大小：");
	  javax.swing.JLabel size=new javax.swing.JLabel(UserConfig.spend+"/"+UserConfig.space);
	  javax.swing.JLabel name_lab=new javax.swing.JLabel("用户名：");
	  javax.swing.JLabel name=new javax.swing.JLabel(UserConfig.name);
	  javax.swing.JLabel level_lab=new javax.swing.JLabel("等级：");
	  javax.swing.JLabel level=new javax.swing.JLabel(UserConfig.level);
	  javax.swing.JLabel server_lab=new javax.swing.JLabel("系统消息：");
	  javax.swing.JLabel server=new javax.swing.JLabel(""+UserConfig.message);
	  javax.swing.JButton exit_but=new javax.swing.JButton("退出登录");
	  exit_but.setActionCommand("exitlogin");
	  
	  this.add(size_lab);
	  this.add(size);
	  this.add(name_lab);
	  this.add(name);
	  this.add(level_lab);
	  this.add(level);
	  this.add(server_lab);
	  this.add(server);
	  this.add(exit_but);
	  //使用匿名内部类创建监听器对象
	  ActionListener actlis=new ActionListener(){
		 //实现方法
		public void actionPerformed(ActionEvent e) {
			String command=e.getActionCommand();
			if(command.equals("exitlogin")){
				parent.remove(InfoTemolate.this);//移除该组件
				//LoginTemplate login=new LoginTemplate(parent);//创建登录模块
	        	//parent.add(login);//添加登录模块
				parent.updateUI();//强制刷新
			}			
		}
		  
	  };
	  exit_but.addActionListener(actlis);
	}
}
