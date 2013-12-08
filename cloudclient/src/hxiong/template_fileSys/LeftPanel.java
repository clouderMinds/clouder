package hxiong.template_fileSys;

import hlong.Client.Client;
import hlong.Client.UIMessageListener;
import hlong.Request.DeleteRequest;
import hlong.Request.RenameRequest;
import hxiong.config.UserConfig;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

/**
 * 左边的数组文件视图
 * @author xiaowei
 *
 */
public class LeftPanel extends JPanel implements UIMessageListener{
	private static final long serialVersionUID = 1L;
	private MyFileNode rootNode;//跟节点
	private DefaultTreeModel model;//树的模板
	private CenterPanel centerPanel;//右边的面板，主要用来显示文件
	public static MyRenderer myRender;
	public static JTree tree;
    public static Map<MyFileNode,FilePane> nodeMap = new HashMap<MyFileNode,FilePane>();
	//需要的不同的文件图片
	private ImageIcon folder_close=new ImageIcon(this.getClass().getResource("images/folder_close.png"));
	private ImageIcon folder_open=new ImageIcon(this.getClass().getResource("images/folder_open.png"));
	private ImageIcon file=new ImageIcon(this.getClass().getResource("images/file_b.png"));
	//重载构造方法
	public LeftPanel(){
		super(new GridLayout(0,1));
		this.initLeftPanel();
		
	}
	//获取右边的面板
	public void setCenterPanel(CenterPanel centerPanel){
		this.centerPanel=centerPanel;
	}
	/**
	 * 初始化左边的面板
	 */
	public void initLeftPanel(){
		this.setBackground(new Color(211,211,211));
		this.setPreferredSize(new Dimension(200,558));
		this.setLayout(new BorderLayout());
		
		rootNode = new MyFileNode("hdfsuser");
		rootNode.setType((byte)1);
		model = new DefaultTreeModel(rootNode);
		model.addTreeModelListener(new MyTreeModelListener());
		tree = new JTree();
		tree.setBackground(new Color(211,211,211));
		tree.setModel(model);
		tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		tree.setShowsRootHandles(true);
		tree.addMouseListener(mouseLis);	
		
		myRender = new MyRenderer();
		tree.setCellRenderer(myRender);
		myRender.setOpenIcon(folder_open);
		myRender.setClosedIcon(folder_close);
		myRender.setLeafIcon(file);
		myRender.setBackgroundNonSelectionColor(new Color(211,211,211));		
		JScrollPane scrollPane = new JScrollPane(tree);
		this.add(scrollPane);
	    
	}
	/**
	 * 添加文件夹节点的方法
	 * @param child 节点对象
	 * @return
	 */
	public MyFileNode addDirNode(String dirChild){
		TreePath parentPath = tree.getSelectionPath();//获得选择的节点
		MyFileNode parentNode;//父节点对象
		if(parentPath==null){//如果为选择如何节点
			parentNode=rootNode;
		}else{//否则获得选中的节点
		    parentNode = (MyFileNode)(parentPath.getLastPathComponent());
		    if(parentNode.getType()!=1)//如果不是文件夹
		    	parentNode=(MyFileNode)parentNode.getParent();//获得它的父节点
		}
		dirChild=this.checkDirName(parentNode, dirChild);//找到可用的文件夹名
		MyFileNode childNode=new MyFileNode(dirChild);
		childNode.setType((byte)1);
		//添加树节点后添加相应文件面板
		FilePane filePane = new FilePane(childNode,centerPanel,LeftPanel.this);
		centerPanel.add(filePane);
		nodeMap.put(childNode, filePane);
		centerPanel.updateUI();//强制刷新
		//添加树节点
		model.insertNodeInto(childNode, parentNode, parentNode.getChildCount());
		tree.scrollPathToVisible(new TreePath(childNode.getPath()));
		showAllFiles(parentNode);//重新显示对应节点下面的所有文件
		return childNode;
	}
	/**
	 * 检查在对应的文件夹节点下面新建的文件夹名字是否可用，如果不可用，则进行修改
	 * @param parentNode 文件夹节点对象
	 * @param dirName 新建的文件夹名字
	 * @return 返回可用的文件夹名
	 */
	private String checkDirName(MyFileNode parentNode,String dirName){
		String checkName=dirName;//最后可用的名字
		ArrayList<String> nodeName=new ArrayList<String>();
		for(int i=0;i<parentNode.getChildCount();i++){
			MyFileNode hisChild = (MyFileNode)parentNode.getChildAt(i);
			nodeName.add(hisChild.getUserObject().toString());//保存父节点下面所有的节点名字
		}
		//最多需要比较i+1次
		int j=0;//记录
		for(int i=0;i<nodeName.size();i++){
			for(j=0;j<nodeName.size();j++){
				if(nodeName.get(j).equals(checkName))//如果有相同的名字
					 break;
			}
			if(j==nodeName.size())//说明在比较的过程中没有发现有相同的文件名
				break;
			else
				checkName=dirName+i;//将其变成新建文件夹n
		}
		return checkName;
	}
	/**
	 * 在对应的文件夹节点下面添加文件
	 * @param fileChild 文件的名称
	 * @param style 文件的类型
	 * @return 如果操作成功，返回该节点对象
	 */
	public MyFileNode addfileNode(Object fileChild,byte style){
		TreePath parentPath = tree.getSelectionPath();//获得选择的节点
		MyFileNode parentNode = (MyFileNode)(parentPath.getLastPathComponent());
		MyFileNode childNode=new MyFileNode(fileChild);
		childNode.setType(style);//设置文件的类型
		//遍历该文件夹下的字节点，提示重名
		for(int i=0;i<parentNode.getChildCount();i++){
			MyFileNode hisChild = (MyFileNode)parentNode.getChildAt(i);
			String hisChildName = hisChild.getUserObject().toString();
			if(childNode.getUserObject().toString().equals(hisChildName)){
				javax.swing.JOptionPane.showMessageDialog(this, "该文件夹下此文件名已存在");
				System.out.println("无法上传");
				return null;
			}
		}
			//添加树节点后添加相应文件面板
			FilePane filePane = new FilePane(childNode,centerPanel,LeftPanel.this);
			centerPanel.add(filePane);
			nodeMap.put(childNode, filePane);
			centerPanel.updateUI();//强制刷新
			//添加树节点
			model.insertNodeInto(childNode, parentNode, parentNode.getChildCount());
			tree.scrollPathToVisible(new TreePath(childNode.getPath()));			
		    return childNode;
	}
	/**
	 * 删除文件夹节点，也就是
	 * @return 如果删除成功，返回true，否则返回false
	 */
	public boolean deleteDirNode(MyFileNode currentNode){
		if(currentNode!=null&&!currentNode.isRoot()){//如果节点不是null且不为根节点
			MyFileNode parent = (MyFileNode)(currentNode.getParent());//获得父节点			
			if(parent!=null){
				TreePath path = new TreePath(parent.getPath());
				LeftPanel.tree.setSelectionPath(path);//降幅节点作为选中的节点
				for(int i=0;i<currentNode.getChildCount();i++){//将该节点下面的所有文件删掉
					MyFileNode nodeChild = (MyFileNode)currentNode.getChildAt(i);
					FilePane filePane = nodeMap.get(nodeChild);
					if(filePane!=null)
					    centerPanel.remove(filePane);//将右边面板上面的所有组件移除
				}
				centerPanel.updateUI();//强制刷新面板
				model.removeNodeFromParent(currentNode);
				return true;
			}
		}
		return false;
	}
	/**
	 * 获得当前选中的节点路径的方法
	 * @return 返回被选中的节点路径，该路径不是由tree提供的路径形式，而是经过转换的路径形式
	 */
    public String getCurrentPath(){
    	if(tree.getSelectionPath()!=null){
    	   String currentPath = tree.getSelectionPath().toString();//选中的节点
    	   String commonPath1 = currentPath.replace(',','/').replace(" ","").replace('[', '/').replace(']', '/');
    	   String commonPath2 = commonPath1.substring(0, commonPath1.length()-1);
    	   return commonPath2;
    	}
    	return "/"+UserConfig.account+"/";//返回根目录
    }
    /**
     * 根据节点获得该节点所在的树状图里面的文件路径
     * @param mynode 节点
     * @return 如果操作成功，返回抽象路径，否则防护null
     */
    public String getPathByNode(MyFileNode mynode){
    	if(mynode!=null&&tree.getSelectionPath()!=null){//如果选中的
    		String currentPath = tree.getSelectionPath().toString();//选中的节点
        	String commonPath1 = currentPath.replace(',','/').replace(" ","").replace('[', '/').replace(']', '/');
        	String commonPath2 = commonPath1.substring(0, commonPath1.length()-1);
        	commonPath2=commonPath2+"/"+mynode.getUserObject();
        	return commonPath2;
    	}
    	return null;
    }
    /**
     * 获得当前被选中的节点
     * @return
     */
    public MyFileNode getCurrentNode(){
    	TreePath currentPath = tree.getSelectionPath();//获得选择的节点
		MyFileNode currentNode = (MyFileNode)(currentPath.getLastPathComponent());
		return currentNode;
    }
    /**
     * 将某个节点设置为选中节点
     * @param currentNode 要设置的节点
     * @return 如果操作成功，返回true，否则返回false
     */
    public boolean setSelectNode(MyFileNode currentNode){
    	if(currentNode==null){
    		return false;
    	}else{
    		 TreePath path = new TreePath(currentNode.getPath());
			 tree.setSelectionPath(path); //将该节点设置为选中节点		
    		return true;
    	}
    }
    /**
	 * 展开对应节点下面的所有文件视图，仅仅只是一层
	 */
	public void showAllFiles(MyFileNode cnode){
		if(cnode.getType()==(byte)1){//如果是文件夹
		 centerPanel.removeAll();//移除所有的组件
		 if(cnode!=null){//如果该节点不为
			for(int i=0;i<cnode.getChildCount();i++){
			 MyFileNode child = (MyFileNode) cnode.getChildAt(i);
			 FilePane folder = new FilePane(child,centerPanel,this);
			 centerPanel.add(folder);
			 LeftPanel.nodeMap.put(child, folder);
			}
		    centerPanel.updateUI();//强制刷新
		  }  
		}
	}
   /**
    * 向服务器发送要删除一个节点的方法
    * @param absPath 要删除的节点的抽象路径
    */
    public void sendDeleteMsg(String absPath){
     try{
    	  DeleteRequest dlre=new DeleteRequest(UserConfig.account,absPath);
		  Client.getClient(LeftPanel.this).sentMsg(dlre);//发送请求
		}catch(Exception ex){
				   
		  ex.printStackTrace();
	   }
    }
    /**
     * 向服务器发送重命名的方法
     * @param oldPath 原来的路径名
     * @param newPath 更改后的路径名
     */
    public void sendRenameMsg(String oldPath,String newPath){
        try{
          RenameRequest rnre=new RenameRequest(UserConfig.account,oldPath,newPath);
   		  Client.getClient(LeftPanel.this).sentMsg(rnre);//发送请求
   		 }catch(Exception ex){
   				   
   		  ex.printStackTrace();
   	    }
       }
	/**
	 * 监听节点的监听器对象
	 */
	MouseListener mouseLis = new MouseAdapter(){
		public void mouseClicked(MouseEvent e) {
		 final TreePath path = tree.getPathForLocation(e.getX(), e.getY());
		if(e.getButton()==MouseEvent.BUTTON3){
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
					
						deleteDirNode(node);//调用删除节点的方法						
				}else if("重命名".equals(e.getActionCommand())){
					     tree.setEditable(true);
					     tree.startEditingAtPath(path);
					     //sendRenameMsg("原来的文件名","新的文件名");		
					}
				  }
				};
				mi_delete.addActionListener(alis);
				 mi_rename.addActionListener(alis);
			 } 
			 }else if(e.getButton()==MouseEvent.BUTTON1&&e.getClickCount()==2){
				 showAllFiles(getCurrentNode());
			 }
			 
		 }
		 
		 
	};
	
    /**
     * 
     */
    class MyTreeModelListener implements TreeModelListener {
    	//当节点发生改变是调用
        public void treeNodesChanged(TreeModelEvent e) {
        	MyFileNode node;
          //  TreePath path = e.getTreePath();
            node = (MyFileNode)(e.getTreePath().getLastPathComponent());
            int index = e.getChildIndices()[0];
            node = (MyFileNode)(node.getChildAt(index));
            if(isRightName(node))
                System.out.println("重命名节点新名称:"+node.getUserObject().toString());
            else
            	System.out.println("邮箱同的节点名！");
            tree.setEditable(false);
        }
        public void treeNodesInserted(TreeModelEvent e) {
        }
        public void treeNodesRemoved(TreeModelEvent e) {
        }
        public void treeStructureChanged(TreeModelEvent e) {
        }
    }
    /**
     * 新的命名是否合法，也就是同一个目录下不允许有相同的文件名
     * @param cNode 该节点对象
     * @return 如果没有相同的文件名，返回true，否则返回false
     */
	public boolean isRightName(MyFileNode cNode){
		
		if(cNode!=null){//该节点不能为空
		   MyFileNode parentNode=(MyFileNode)cNode.getParent();//获得父节点
		   for(int i=0;i<parentNode.getChildCount();i++){//遍历该节点下面的所有字节点，仅仅只是
			   MyFileNode hisChild = (MyFileNode)parentNode.getChildAt(i);//获得它的字节点
			   if(hisChild.getUserObject().toString().equals(cNode.getUserObject().toString())
					   &&!hisChild.equals(cNode))//如果有相同的文件名，而且又不是统一个对象
				    return false;
		   }
		}
		   return true;
	}
	/**
	 * 根据传入的文件路径队列将之转换为一个字符串对象
	 * @param list 保存文件抽象路径的队列
	 */
	public void analysePath(List<String> list){
		if(list.size()>0){//存在文件路径	
	    String s=list.get(0);
	    s=s.substring(1, s.lastIndexOf('/'));//相当于要截取跟节点的名字
		//添加跟节点
		rootNode = new MyFileNode(s);//跟节点为用户帐号
		rootNode.setType((byte)1);
		model = new DefaultTreeModel(rootNode);
		model.addTreeModelListener(new MyTreeModelListener());
		//遍历路径队列
		for(String str:list){
			int i = 0;
			String strfor = str.substring(1);
			strfor=strfor.substring(strfor.indexOf("/")+1);
			//将路径分解成节点名称数组
			String[] nodes = strfor.split("/");
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
		  tree.setModel(model);
		  tree.setRootVisible(false);//隐藏跟节点，保护数据安全
		}else{//否则只添加一个跟节点
			rootNode = new MyFileNode(UserConfig.account);//跟节点为用户帐号
			rootNode.setType((byte)1);
			model = new DefaultTreeModel(rootNode);
			model.addTreeModelListener(new MyTreeModelListener());
			tree.setModel(model);
			tree.setRootVisible(false);//隐藏跟节点，保护数据安全
		}
		 TreePath path = new TreePath(rootNode.getPath());
		 tree.setSelectionPath(path); //将根节点设置为选中节点	
		 showAllFiles(rootNode);//显示根节点下面的所有文件，仅仅只是一层
	}
	@Override
	public void onMessageReceived(Object msg) {
		// TODO Auto-generated method stub
		
	}
	
}
