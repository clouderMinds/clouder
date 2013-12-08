package hxiong.template;

import hxiong.config.ClientuiConfig;
import hxiong.template_fileSys.CenterPanel;
import hxiong.template_fileSys.FileOperBarPanel;
import hxiong.template_fileSys.LeftPanel;
import hxiong.template_fileSys.OperateMenuPanel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;



/**
 * 这是一个用户文件管理面板，也就是管理用户上传的所有文件视图
 * 该类除了提供文件视图，还提供操作文件视图的方法
 * @author 熊天成
 *
 */
public class FsTemplate extends javax.swing.JPanel{

	private static final long serialVersionUID = 1L;
	private LeftPanel leftPanel;
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
		top_panel.setPreferredSize(new Dimension(800,82));//设置面板的大小
		top_panel.setLayout(new BorderLayout());
		OperateMenuPanel opratemenu=new OperateMenuPanel();
		top_panel.add(opratemenu,BorderLayout.NORTH);
		FileOperBarPanel filebar=new FileOperBarPanel();
		top_panel.add(filebar,BorderLayout.SOUTH);
		this.add(top_panel,BorderLayout.NORTH);
		
		//面板的左边部分，用来显示树状文件视图		
        leftPanel = new LeftPanel();
		this.add(leftPanel,BorderLayout.WEST);
	
		//面板的中间部分，用来实现对应文件夹下面的所有文件
		CenterPanel centerPanel = new CenterPanel();
	    this.add(centerPanel,BorderLayout.CENTER);
	    leftPanel.setCenterPanel(centerPanel);//将右边的面板对象传到左边的面板里面
	    opratemenu.setPanel(centerPanel, leftPanel);
	    updateFsTemplate();
	}
	/**
	 * 刷新用户文件视图面板，也就相当于重新生成树状文件视图
	 */
	public void updateFsTemplate(){
		ArrayList<String> trees=new ArrayList<String>();
		trees.add("/hadoop/A/c");
		trees.add("/hadoop/A/d");
		trees.add("/hadoop/C/d");
		trees.add("/hadoop/A/D/aa");
		trees.add("/hadoop/A/D/rr");
		trees.add("/hadoop/C/D/mm");
		leftPanel.analysePath(trees);
	}
}
