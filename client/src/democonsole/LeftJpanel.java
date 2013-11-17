package democonsole;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultCellEditor;
import javax.swing.UIManager;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultTreeCellEditor;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeCellEditor;

import demotool.SocketFileTool;


public class LeftJpanel extends javax.swing.JPanel{

	private static final long serialVersionUID = 1L;
	private String account;
	private javax.swing.JTree file_tree;
	private javax.swing.JPanel cenpanel;
	public String hdfspath="xiong";//用户文件的根目录
	private SocketFileTool file_version;//获取用户文件视图的对象
	private TreeSelectionListener treelis;//
	private javax.swing.tree.DefaultTreeModel treemodel;//
	//重载构造方法，得到用户名
	public LeftJpanel(String account){
		this.account=account;
	}
	//获取中间遍布对象，用来显示文件
	public void setCenterPanel(javax.swing.JPanel panel){
		this.cenpanel=panel;
	}
	/**
	 * 获得树状的模型，根据该模型可以显示文件视图
	 * @return 文件视图模型
	 */
	public javax.swing.tree.DefaultTreeModel getTreeModel(){
		return treemodel;
	}
    /**
     * 初始化左边界面的方法，用来显示左边文件视图
     */
	public void showLeftJpanelUI(){
		this.setBackground(Color.LIGHT_GRAY);
		this.setLayout(new FlowLayout(FlowLayout.LEFT));
		file_tree=new javax.swing.JTree();
	
		/*****************创建获取文件视图的方法***************/
	    file_version=new SocketFileTool(account);
		treemodel=file_version.getFileVersion();

		 file_tree.setModel(treemodel);
		 file_tree.setEditable(true);
		 file_tree.setDragEnabled(true);
		//添加树状文件视图对象
		this.add(file_tree);
		//给树状对象添加监听器
		treelis=new TreeSelectionListener(){
			public void valueChanged(TreeSelectionEvent e) {
				String path=e.getPath().toString();//获得五年级路径
				hdfspath=changePath(path);
				refreshFileVersion(hdfspath);//调用想服务器获取文件视图的方法
			}			
		};
		file_tree.addTreeSelectionListener(treelis);
		//添加编辑监听器，当节点北边将是放生调用
		file_tree.setCellEditor(new DefaultTreeCellEditor(file_tree,(DefaultTreeCellRenderer)file_tree.getCellRenderer()) {
			protected TreeCellEditor createTreeCellEditor() {
			javax.swing.border.Border  aBorder = UIManager.getBorder("Tree.editorBorder");
			final DefaultTextField test = new DefaultTextField(aBorder);
			test.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					//编辑
					System.out.println(test.getText());
				}
			});
			DefaultCellEditor   editor = new DefaultCellEditor (test) {
				private static final long serialVersionUID = 1L;
				public boolean shouldSelectCell(java.util.EventObject event) {
				boolean retValue = super.shouldSelectCell(event);
				getComponent().requestFocus();
				return retValue;
			    }
			};

			// One click to edit.
			editor.setClickCountToStart(1);
			return editor;
		    }
		});
	}
	/**
	 * 更新文件视图的方法
	 */
	public void refreshTreeVersion(){
		Runnable rub=new Runnable(){
			public void run() {
				synchronized(file_version){
				//获取文件视图的模型
				treemodel=file_version.getFileVersion();
				if(treemodel!=null){
					file_tree.removeTreeSelectionListener(treelis);
					file_tree.setModel(treemodel);
					file_tree.updateUI();
					file_tree.addTreeSelectionListener(treelis);
				}
				}
			}			
		};
		//使用线程来完成
		Thread th=new Thread(rub);
		th.start();
	}
    /**
     * 显示文件视图的具体方法
     * @param path 传入文件路径
     */
    public void refreshFileVersion(final String path){
    	Runnable rub=new Runnable(){
			public void run() {
				synchronized(file_version){
					ArrayList<String> list=file_version.getCurrentVersion(path);
			    	if(list!=null){
			    		cenpanel.removeAll();//移除原来所有的文件按钮
			    		int width=cenpanel.getWidth();
			    		if(width<500){
			    			int height=(list.size()/4+1)*110;
			    			cenpanel.setPreferredSize(new Dimension(width, height));
			    		}else{
			    			int height=(list.size()/11+1)*110;
			    			cenpanel.setPreferredSize(new Dimension(width, height));
			    		}
			    		for(int i=0;i<list.size();i++){
			    			javax.swing.JButton jbu=new javax.swing.JButton(list.get(i));
			    			jbu.setPreferredSize(new java.awt.Dimension(100,100));
			    			cenpanel.add(jbu);
			    		}
			    		cenpanel.updateUI();//更新界面
			    	}
				
				}
			}			
		};
		//创建线程对象，并调用线程
		Thread th=new Thread(rub);
		th.start();
    	
    }
    /**
	  * 将树状的文件路径变为文件的路径的方法
	  * @param path 原来的树状文件路径
	  * @return 改变后的文件路径
	  */
	public String changePath(String path){
		path=path.substring(1, path.length()-1);
		String[] str=path.split(", ");
		path="";
		path=str[0];
		for(int i=1;i<str.length;i++){
			path+="/";
			path+=str[i];
		}
		return path;
	}
}
