package hxiong.clientui;

import hxiong.config.ClientuiConfig;
import hxiong.template.FsTemplate;
/**
 * 第三个视图面板，在三个面板中这个面板估计是用得最多的，
 * 因为很多的操作都在这个面板里面进行和完成
 * @author 熊天成
 *
 */
public class ThirdView extends javax.swing.JPanel{
	private static final long serialVersionUID = 1L;
	private FsTemplate fs_panel;
	//重载构造方法
	public ThirdView(){
		initThreeView();
	}
	/**
	 * 具体用来添加组件和功能的方法
	 */
	private void initThreeView(){
		this.setLayout(new java.awt.BorderLayout());
		//设置面板的起始坐标和大小
		this.setBounds(ClientuiConfig.panelWidth*2,0,ClientuiConfig.panelWidth,ClientuiConfig.panelHeight);
		//添加用户文件视图界面
		fs_panel=new FsTemplate();
		this.add(fs_panel);
	}
	/**
	 * 刷新用户文件视图面板，也就相当于重新生成树状文件视图
	 */
	public void updateFsTemplate(){
		fs_panel.updateFsTemplate();
	}
}
