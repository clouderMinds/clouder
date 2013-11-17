package Clinet.MainUI;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

public class FileTreePanel extends JPanel{
	private MyFileNode rootNode;
	private DefaultTreeModel model;
	public static MyRenderer myRender;
	JTree tree;
	private ImageIcon folder_close=new ImageIcon(this.getClass().getResource("images/folder_close.png"));
	private ImageIcon folder_open=new ImageIcon(this.getClass().getResource("images/folder_open.png"));
	private ImageIcon file=new ImageIcon(this.getClass().getResource("images/file_b.png"));
	
	public FileTreePanel(){
		super(new GridLayout(0,1));
		rootNode = new MyFileNode("当前文件夹");
		rootNode.setType((byte)1);
		model = new DefaultTreeModel(rootNode);
		model.addTreeModelListener(new MyTreeModelListener());
		tree = new JTree();
		tree.setModel(model);
		tree.setEditable(true);
		tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		tree.setShowsRootHandles(true);
		tree.addMouseListener(mouseLis);
		
		myRender = new MyRenderer();
		tree.setCellRenderer(myRender);
		myRender.setOpenIcon(folder_open);
		myRender.setClosedIcon(folder_close);
		myRender.setLeafIcon(file);
		this.analysePath();
		
		JScrollPane scrollPane = new JScrollPane(tree);
		add(scrollPane);
	}
	
	public void clear(){
		rootNode.removeAllChildren();
		model.reload();
	}
	
	public void deleteCurrentFile(MyFileNode currentNode){
		if(currentNode!=null){
			MyFileNode parent = (MyFileNode)(currentNode.getParent());
			if(parent!=null){
				model.removeNodeFromParent(currentNode);
			}
		}
	}
	
	public MyFileNode addFile(Object child){
		TreePath parentPath = tree.getSelectionPath();
		System.out.println("上传文件的抽象路径："+parentPath.toString());
		MyFileNode parentNode = null;
		if(parentPath==null){
			parentNode = rootNode;
		}else{
			parentNode = (MyFileNode)(parentPath.getLastPathComponent());
			if(parentNode.getType()==(byte)0){
				javax.swing.JOptionPane.showMessageDialog(this, "请选择文件夹进行文件上传");
				return null;
			}
		}
		return addFile(parentNode,child,true,false);
	}
	
	public MyFileNode addFile(Object child,boolean isFolder){
		TreePath parentPath = tree.getSelectionPath();
		MyFileNode parentNode = null;
		if(parentPath==null){
			parentNode = rootNode;
		}else{
			parentNode = (MyFileNode)(parentPath.getLastPathComponent());
			if(parentNode.getType()==(byte)0){
				javax.swing.JOptionPane.showMessageDialog(this, "请选择文件夹进行文件上传");
				return null;
			}
		}
		return addFile(parentNode,child,true,true);
	}

	
	public MyFileNode addFile(MyFileNode parent,Object child,boolean visible,boolean folder){
		MyFileNode childNode = new MyFileNode(child);
		if(folder){
			childNode.setType((byte)1);
		}
		if(parent==null){
			parent=rootNode;
		}
		for(int i=0;i<parent.getChildCount();i++){
			MyFileNode hisChild = (MyFileNode)parent.getChildAt(i);
			String hisChildName = hisChild.getUserObject().toString();
			if(childNode.getUserObject().toString().equals(hisChildName)){
				int j=0;
				javax.swing.JOptionPane.showMessageDialog(this, "该文件夹下此文件名已存在");
				System.out.println("无法上传");
				return null;
			}
		}
		model.insertNodeInto(childNode, parent, parent.getChildCount());
		if(visible){
			tree.scrollPathToVisible(new TreePath(childNode.getPath()));
		}
		return childNode;
	}
	
	public String getSelectFilePath(){
		TreePath currentPath = tree.getSelectionPath();
		return currentPath.toString();
	}
	
	MouseListener mouseLis = new MouseAdapter(){
		 public void mouseClicked(MouseEvent e) {
			 if(e.getButton()==MouseEvent.BUTTON3){
				 final TreePath path = tree.getPathForLocation(e.getX(), e.getY());
				 if(path==null){
					 return;
				 }else{
					 javax.swing.JPopupMenu pmenu = new javax.swing.JPopupMenu();
					 JMenuItem mi_delete = new JMenuItem("删除");
					 JMenuItem mi_rename = new JMenuItem("重命名");
					 pmenu.add(mi_delete);
					 pmenu.add(mi_rename);
					 pmenu.show(tree,e.getX(),e.getY());
					 tree.setSelectionPath(path);
					 final MyFileNode node = (MyFileNode) path.getLastPathComponent();
					 ActionListener alis = new ActionListener(){
						public void actionPerformed(ActionEvent e) {
							if("删除".equals(e.getActionCommand())){
								deleteCurrentFile(node);
								System.out.println("已删除节点路径："+path.toString());
							}else if("重命名".equals(e.getActionCommand())){
								tree.startEditingAtPath(path);
								System.out.println("重命名节点原路径："+path.toString());
							}
						}
					 };
					 mi_delete.addActionListener(alis);
					 mi_rename.addActionListener(alis);
				 } 
			 }
		 }
	};
	
	
	public void analysePath(){
		List<String> list = this.inputPath();
		//遍历路径队列
		for(String str:list){
			int i = 0;
			//将路径分解成节点名称数组
			String[] nodes = str.split("/");
			MyFileNode parent = rootNode;
			MyFileNode child;
			//依次解析节点名称数组
			for(String node:nodes){
				boolean nodeHasExist=false;
				//判断该节点是否已经存在，如存在则将节点设为子节点，并标记下来
				for(int j=0;j<parent.getChildCount();j++){
					MyFileNode hisChild = (MyFileNode)parent.getChildAt(j);
					String hisChildName = hisChild.getUserObject().toString();
					if(node.equals(hisChildName)){
						parent = (MyFileNode) parent.getChildAt(j);
						i++;
						nodeHasExist = true;
					}
				}
				//若该节点未存在，则将该点插入到树中
				if(!nodeHasExist){
					child = new MyFileNode(node);
					i++;
					if(i<nodes.length){
						child.setType((byte)1);
					}
					model.insertNodeInto(child, parent, parent.getChildCount());
					parent = child;
				}
			}
		}
	}
	

    class MyTreeModelListener implements TreeModelListener {
        public void treeNodesChanged(TreeModelEvent e) {
            DefaultMutableTreeNode node;
            TreePath path = e.getTreePath();
            node = (DefaultMutableTreeNode)(e.getTreePath().getLastPathComponent());
            int index = e.getChildIndices()[0];
            node = (DefaultMutableTreeNode)(node.getChildAt(index));
            System.out.println("重命名节点新名称:"+node.getUserObject().toString());
                
        }
        public void treeNodesInserted(TreeModelEvent e) {
        }
        public void treeNodesRemoved(TreeModelEvent e) {
        }
        public void treeStructureChanged(TreeModelEvent e) {
        }
    }
	
	
	public List<String> inputPath(){
		List<String> pathList = new ArrayList<String>();
		pathList.add("hadoop/a");
		pathList.add("hadoop/b");
		pathList.add("hadoop/A/c");
		pathList.add("hadoop/A/d");
		pathList.add("hadoop/B/C/D/E/F/fghfh/fgf");
		return pathList;
	}
	
	
}
