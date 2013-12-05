package hxiong.clientui;

import hxiong.config.ClientuiConfig;
import hxiong.template.FsTemplate;

import java.awt.Color;

/**
 * 第三个视图面板，在三个面板中这个面板估计是用得最多的，
 * 因为很多的操作都在这个面板里面进行和完成
 * @author 熊天成
 *
 */
public class ThirdView extends javax.swing.JPanel{
	private static final long serialVersionUID = 1L;
	//重载构造方法
	public ThirdView(){
		initThreeView();
	}
	/**
	 * 具体用来添加组件和功能的方法
	 */
	private void initThreeView(){
		this.setBackground(Color.blue);
		//设置面板的起始坐标和大小
		this.setBounds(ClientuiConfig.panelWidth*2,0,ClientuiConfig.panelWidth,ClientuiConfig.panelHeight);
		//添加用户文件视图界面
		FsTemplate fs_panel=new FsTemplate();
		this.add(fs_panel);
	}
}
