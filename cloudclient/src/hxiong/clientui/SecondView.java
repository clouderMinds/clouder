package hxiong.clientui;
import java.awt.FlowLayout;

import hxiong.config.ClientuiConfig;

/**
 * 窗体中间部分的第二个面板，用来显示用户的一些其他的信息
 * @author 熊天成
 *
 */
public class SecondView extends javax.swing.JPanel{

	private static final long serialVersionUID = 1L;
	//重载构造方法
	public SecondView(){
		initSecondView();
	}
	/**
	 * 具体用来添加组件和功能的方法
	 */
	private void initSecondView(){
		this.setLayout(new FlowLayout(FlowLayout.LEFT));
		//设置面板的起始坐标和大小
		this.setBounds(ClientuiConfig.panelWidth, 0,ClientuiConfig.panelWidth,ClientuiConfig.panelHeight);
		
		javax.swing.JButton jbu1=new javax.swing.JButton("我的文件系统");
		javax.swing.JButton jbu2=new javax.swing.JButton("回收站");
		
		
		this.add(jbu1);
		this.add(jbu2);
		
	}
}
