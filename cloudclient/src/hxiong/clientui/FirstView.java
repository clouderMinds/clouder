package hxiong.clientui;

import hxiong.config.ClientuiConfig;
import hxiong.template.LoginTemplate;

/**
 * 中间部分的第一个面板用来显示登录界面，注册界面等
 * @author 熊天成
 *
 */
public class FirstView extends javax.swing.JPanel{
	private static final long serialVersionUID = 1L;
	//重载构造方法
	public FirstView() {
		initFirstView();//初始化面板的方法
	}
	/**
	 * 具体用来添加组件和功能的方法
	 */
	private void initFirstView(){
		//设置面板的起始坐标和大小
		this.setBounds(0,0,ClientuiConfig.panelWidth,ClientuiConfig.panelHeight);
		LoginTemplate login=new LoginTemplate(this);
		this.add(login);
	}

}
