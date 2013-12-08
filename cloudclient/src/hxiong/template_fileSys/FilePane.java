package hxiong.template_fileSys;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class FilePane extends JPanel{
	private static final long serialVersionUID = 1L;
	private ImageIcon folder=new ImageIcon(this.getClass().getResource("images/folder_r.png"));
	private ImageIcon file=new ImageIcon(this.getClass().getResource("images/file_r.png"));
	boolean isFolder;
	JTextField fname;
	private MyFileNode node;//保存与该面板对应的节点
	private CenterPanel centerPanel;//右边的面板，主要用来显示文件，也是给类所依赖的父面板
	private LeftPanel leftPanel;//用于显示树状文件视图的面板，
	protected void paintComponent(Graphics g){
		if(isFolder){
			Image img_folder = folder.getImage();
			g.drawImage(img_folder, 0, 0, folder.getIconWidth(),folder.getIconHeight(),folder.getImageObserver());		
		}else{
			Image img_file = file.getImage();
			g.drawImage(img_file, 0, 0, file.getIconWidth(),folder.getIconHeight(),folder.getImageObserver());
		}
	}
	//重载构造方法
	public FilePane(MyFileNode node,CenterPanel centerPanel,LeftPanel leftPanel){
		this.node=node;
		this.centerPanel=centerPanel;
		this.leftPanel=leftPanel;
		this.setPreferredSize(new Dimension(folder.getIconWidth(),folder.getIconHeight()));
		this.setBackground(Color.lightGray);
		this.setLayout(new BorderLayout());
		this.addMouseListener(mlis_p);
		fname = new JTextField();
		fname.setHorizontalAlignment(JTextField.CENTER);
		fname.setLayout(new FlowLayout());
		fname.setText(node.toString());
		fname.setEditable(false);
		fname.addMouseListener(mlis_p);
		fname.addKeyListener(klis);
		if(node.getType()==(byte)1){
			isFolder = true;
		}else{
			isFolder = false;
		}
		this.add(fname,BorderLayout.SOUTH);
		this.setVisible(true);
	}
	/**
	 * 鼠标监听器，用于监听用户的鼠标操作
	 */
	MouseAdapter mlis_p = new MouseAdapter() {
		public void mouseClicked(MouseEvent e) {
			//如果点击的是右键，弹出一些操作选项
			 if(e.getButton()==MouseEvent.BUTTON3){
				 javax.swing.JPopupMenu pmenu = new javax.swing.JPopupMenu();
				 JMenuItem mi_delete = new JMenuItem("删除");
				 JMenuItem mi_rename = new JMenuItem("重命名");
				 pmenu.add(mi_delete);
				 pmenu.add(mi_rename);
				 pmenu.show(FilePane.this,e.getX(),e.getY());
				 ActionListener alis = new ActionListener(){
					public void actionPerformed(ActionEvent e) {
						if("删除".equals(e.getActionCommand())){
							centerPanel.remove(FilePane.this);
							centerPanel.updateUI();
							if(node!=null){
								leftPanel.deleteDirNode(node);
							}
						}else if("重命名".equals(e.getActionCommand())){
							fname.setEditable(true);
						}
					}
				};
				//添加监听器
				mi_delete.addActionListener(alis);
				mi_rename.addActionListener(alis);
				
			 }else if(e.getButton()==MouseEvent.BUTTON1){//如果点击的是左键
				 //如果是双击,且该文件是文件夹，则进行展开该文件夹下面的文件
				 if(e.getClickCount()>1&&isFolder){
					 
					 leftPanel.showAllFiles(node);
				}else{//如果是普通的单击
					 //取得文件面板所对应的节点，并将该节点设置为选择节点
					 leftPanel.setSelectNode(node);
					 //将其他节点的选择边框取消
					 setPanelFouse();
				}
			 }
		 }
	};
	/**
	 * 通过改变组件的背景颜色达到选中与不选中的效果
	 */
	public void setPanelFouse(){
		 Component[] coms=centerPanel.getComponents();
		 for(int i=0;i<coms.length;i++){
			 coms[i].setBackground(Color.lightGray);
		 }
		 this.setBackground(Color.darkGray);
		 centerPanel.updateUI();
	}
	KeyListener klis = new KeyListener() {
		
		public void keyTyped(KeyEvent e) {
			
			
		}
		
		public void keyReleased(KeyEvent e) {
			
		}
		
		public void keyPressed(KeyEvent e) {
			int code = e.getKeyCode();
			if(code==KeyEvent.VK_ENTER){
				fname.setEditable(false);
				
			}
		}
	};
	
}
