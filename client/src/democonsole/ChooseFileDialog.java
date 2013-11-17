package democonsole;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;

import demotool.SocketRemoveFile;

/**
 * 弹出的文件选择框，自定义的文件选择框，用来显示集群的文件系统
 * @author hadoop
 *
 */
public class ChooseFileDialog extends JFrame{
	private static final long serialVersionUID = 1L;
	private int x,y;//记录点击的坐标	
	 private String account;
	 private LeftJpanel left_panel;
	 private javax.swing.JFrame demo_ui;
	 private String dir_path;
	 //重载构造方法，传入所需的参数
	 public ChooseFileDialog(String account,LeftJpanel panel,javax.swing.JFrame demo_ui){
		 this.account=account;
    	 this.left_panel=panel;
    	 this.demo_ui=demo_ui;
	 }
    /**
     * 显示窗体的方法，通过次方法显示出树状文件视图
     */
    public void showChooseFileDialog(){
    	this.setSize(260, 400);
		this.setResizable(false);
		this.setLayout(new BorderLayout());//设置为边框布局
		this.setUndecorated(true);//去掉窗体的边框
		this.setLocationRelativeTo(null);
		/******************窗体的上部分，用来移动窗体*****************/
		javax.swing.JPanel top_panel=new javax.swing.JPanel();
		top_panel.setBackground(Color.LIGHT_GRAY);
		top_panel.setLayout(new BorderLayout());//设置为边框布局
		//窗体的标题
		javax.swing.JLabel title_lab=new javax.swing.JLabel("  请选择文件");
		top_panel.add(title_lab,BorderLayout.WEST);
		//�ұߵĹرհ�ť
		javax.swing.JPanel right_panel=new javax.swing.JPanel();
		right_panel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		right_panel.setOpaque(false);
		ImageIcon close_out=new ImageIcon(this.getClass().getResource("images/red-1.png"));
		JButton close_jbu=new JButton(close_out);
		close_jbu.setActionCommand("close");
		close_jbu.setOpaque(false);
		close_jbu.setContentAreaFilled(false);
		close_jbu.setBorder(null);
		right_panel.add(close_jbu);
		
		
		top_panel.add(right_panel,BorderLayout.CENTER);
		this.add(top_panel,BorderLayout.NORTH);
		/******************窗体的中间部分，用来显示文件视图****************/
		javax.swing.JPanel center_panel=new javax.swing.JPanel();
		center_panel.setLayout(new FlowLayout(FlowLayout.LEFT));
		//创建一个树状对象
		javax.swing.JTree jtree=new javax.swing.JTree();
		jtree.setModel(left_panel.getTreeModel());
		center_panel.add(jtree);
		javax.swing.JScrollPane jscpanel=new javax.swing.JScrollPane(center_panel);
		this.add(jscpanel,BorderLayout.CENTER);
		/*******************窗体的下部分，用来添加取消和确定按钮*****************/
		javax.swing.JPanel buttom_panel=new javax.swing.JPanel();
		javax.swing.border.LineBorder tool_border=new javax.swing.border.LineBorder(Color.gray,1);
		buttom_panel.setBorder(tool_border);
		JButton cancel_jbu=new JButton("取消");
		cancel_jbu.setActionCommand("cancel");
		
		JButton confirm_jbu=new JButton("确定");
		confirm_jbu.setActionCommand("confirm");
		buttom_panel.add(cancel_jbu);
		buttom_panel.add(confirm_jbu);
		this.add(buttom_panel,BorderLayout.SOUTH);
		this.setVisible(true);
		
		demo_ui.setEnabled(false);//设置原来的窗体不可用
	  /*****************给对应的组件添加监听器******************/
		//给窗体添加鼠标监听器，用于监听鼠标按下的动作
				MouseAdapter mlis=new MouseAdapter(){
					public void mousePressed(MouseEvent e){							
						x=e.getXOnScreen()-ChooseFileDialog.this.getX();
						y=e.getYOnScreen()-ChooseFileDialog.this.getY();
					}
				};
				//给窗体添加鼠标移动监听器
				MouseMotionListener moslis=new MouseMotionListener(){
					@Override
					public void mouseDragged(MouseEvent e) {
						
						int xs=e.getXOnScreen();
						int ys=e.getYOnScreen();
							
						ChooseFileDialog.this.setLocation(xs-x, ys-y);
					}
					public void mouseMoved(MouseEvent e) {
						
					}			
				};
			top_panel.addMouseListener(mlis);
			top_panel.addMouseMotionListener(moslis);
		 /***********************创建动作监听器********************/
			ActionListener alis=new ActionListener(){
			
				public void actionPerformed(ActionEvent e) {
					String command=e.getActionCommand();
					if(command.equals("close")){
						demo_ui.setEnabled(true);//让原窗体可用
						ChooseFileDialog.this.dispose();
					}else if(command.equals("cancel")){
						demo_ui.setEnabled(true);//让原窗体可用
						ChooseFileDialog.this.dispose();
					}else if(command.equals("confirm")){
						demo_ui.setEnabled(true);//让原窗体可用
						ChooseFileDialog.this.dispose();
						//调用移动的方法
						SocketRemoveFile remove=new SocketRemoveFile(account,left_panel,left_panel.hdfspath,dir_path);
						remove.start();
					}	
				}				
			};
			close_jbu.addActionListener(alis);
			cancel_jbu.addActionListener(alis);
			confirm_jbu.addActionListener(alis);
			/*******************给树状添加监听器********************/
		     TreeSelectionListener treelis=new TreeSelectionListener(){
			  public void valueChanged(TreeSelectionEvent e) {
				String path=e.getPath().toString();//获得文件路径
					dir_path=changePath(path);
				}			
			  };
			  jtree.addTreeSelectionListener(treelis);
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
