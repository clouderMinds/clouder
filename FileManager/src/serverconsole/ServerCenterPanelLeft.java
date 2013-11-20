package serverconsole;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.image.ImageObserver;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JTree;
import javax.swing.border.Border;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;
/**
 * 窗体中部面板的左部面板
 * @author hadoop
 *
 */
public class ServerCenterPanelLeft extends javax.swing.JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTree option_tree; //主要功能的树型
	private JTree other_down_tree;//下部的树型
	private Border border=BorderFactory.createEtchedBorder(Color.white,Color.darkGray);
	private ServerCenterPanelRight rightpanel;
	private JFrame server_ui;
	private List<TreePath> arr_tp=new ArrayList<TreePath>();
	
	/**
	 * 构造函数
	 * @param rightpanel 
	 * @param server_ui 
	 */
	public ServerCenterPanelLeft(ServerCenterPanelRight rightpanel, JFrame server_ui){
		this.rightpanel=rightpanel;
		this.server_ui=server_ui;
		getParent();
		this.setPreferredSize(new Dimension(120,ImageObserver.HEIGHT));
		this.setBorder(border);
	}
	/**
	 * 显示内容的面板
	 */
	public void showServerCenterPanelLeft() {
		
		DefaultMutableTreeNode root_node=new DefaultMutableTreeNode("云系统");
		createNodes(root_node);
		DefaultMutableTreeNode other_node=new DefaultMutableTreeNode("其他");
		createOtherNodes(other_node);
		
		MyTreeSelectionListener mtsl=new MyTreeSelectionListener();
		
		option_tree=new JTree(root_node);
		option_tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		option_tree.addTreeSelectionListener(mtsl);
		
		other_down_tree=new JTree(other_node);
		other_down_tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		other_down_tree.addTreeSelectionListener(mtsl);
		
		this.setLayout(new BorderLayout());
		this.add(option_tree,BorderLayout.NORTH);
		this.add(other_down_tree,BorderLayout.CENTER);
	}
	//显示其他功能
	private void createOtherNodes(DefaultMutableTreeNode other_node) {
	    DefaultMutableTreeNode book = null;
	    
	 
	    
	    //original Tutorial
	    book = new DefaultMutableTreeNode("客户升级");
	    other_node.add(book);
	    
	    book = new DefaultMutableTreeNode("关于");
	    other_node.add(book);
	    
	    book = new DefaultMutableTreeNode("关闭");
	    other_node.add(book);
	    
	 
	}
	//显示主要操作
	private void createNodes(DefaultMutableTreeNode top) {
	    DefaultMutableTreeNode book = null;
	    
	    //original Tutorial
	    book = new DefaultMutableTreeNode("系统设置");
	    top.add(book);
	    
	    book = new DefaultMutableTreeNode("用户管理");
	    top.add(book);
	    
	    book = new DefaultMutableTreeNode("交互消息");
	    top.add(book);
	    
	    book = new DefaultMutableTreeNode("系统日志");
	    top.add(book);
	    
	    book = new DefaultMutableTreeNode("在线用户");
	    top.add(book);
	}
	
	/*
	 * 树状结构的监听类
	 */
	class MyTreeSelectionListener implements TreeSelectionListener{

		@Override
		public void valueChanged(TreeSelectionEvent e) {
		
			
			String fullpath=e.getPath().toString();
			System.out.println(e.getPath()+"  ---   ");
			if(fullpath.equals("[云系统, 系统设置]")){
				SysOptionPanel sop=SysOptionPanel.getSysOptionPanel();
				sop.showSysOptionPanel();
				
				rightpanel.removeAll();
				rightpanel.add(sop,BorderLayout.CENTER);
	
				rightpanel.updateUI();

				System.out.println("到我了");
				return ;
			}
			if(fullpath.equals("[云系统, 用户管理]")){
				SysUserPanel sup = SysUserPanel.getSysUserPanel(server_ui);
				sup.showSysUserPanel();
				
				rightpanel.removeAll();
				rightpanel.add(sup,BorderLayout.CENTER);
	
				rightpanel.updateUI();

				System.out.println("到我了");
				return ;
			}
			if(fullpath.equals("[云系统, 交互消息]")){

				return ;
			}
			if(fullpath.equals("[云系统, 系统日志]")){
				return ;
			}
			if(fullpath.equals("[云系统, 在线用户]")){
				
				return ;
			}
			
		}
		
	}
	
}
