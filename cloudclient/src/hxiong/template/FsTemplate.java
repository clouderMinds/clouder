package hxiong.template;

import hxiong.config.ClientuiConfig;

import java.awt.BorderLayout;
import java.awt.Dimension;

/**
 * 这是一个用户文件管理面板，也就是管理用户上传的所有文件视图
 * 该类除了提供文件视图，还提供操作文件视图的方法
 * @author 熊天成
 *
 */
public class FsTemplate extends javax.swing.JPanel{

	private static final long serialVersionUID = 1L;
    //重载构造方法
	public FsTemplate(){
		
		this.initFsTemplate();
	}
	/**
	 * 初始化面板上的组件以及提供各种功能的方法
	 */
	private void initFsTemplate(){
		this.setPreferredSize(new Dimension(ClientuiConfig.panelWidth,ClientuiConfig.panelHeight)); //设置面板的起始位置以及大小
		this.setLayout(new BorderLayout());//设置为边框布局
		//面板的上部分
		javax.swing.JPanel top_panel=new javax.swing.JPanel();
		top_panel.setPreferredSize(new Dimension(600,80));//设置面板的大小
		top_panel.setLayout(new BorderLayout());
		OperateMenuPanel opratemenu=new OperateMenuPanel();
		top_panel.add(opratemenu,BorderLayout.NORTH);
		
		FileOperBarPanel filebar=new FileOperBarPanel();
		top_panel.add(filebar,BorderLayout.SOUTH);
		this.add(top_panel,BorderLayout.NORTH);
		
		//面板的左边部分，用来显示树状文件视图
        FileTreePanel filetree=new FileTreePanel("hadoop");            
		this.add(filetree,BorderLayout.WEST);
		//面板的中间部分，用来实现对应文件夹下面的所有文件
		javax.swing.JPanel center_panel=new javax.swing.JPanel();
			
		
	    this.add(center_panel,BorderLayout.CENTER);
		
	}
}
