package democonsole;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class Bordertwo extends JFrame{
	private static final long serialVersionUID = 1L;
	private JPanel jpcenter,jpsouth,jpfile;
	private JButton jbfile,jbsearch;
	private JScrollPane jsp;
	public void showUI(){
		this.setResizable(true);//窗体可任意更改大小
		this.setUndecorated(true);//去除边框
		this.setLocationRelativeTo(null);//居中显示
		ImageIcon backimage=new ImageIcon(this.getClass().getResource("images/back1.jpg"));
		JLabel imglabel=new JLabel(backimage);
		imglabel.setBounds(0, 0, backimage.getIconWidth(),backimage.getIconHeight());
		this.getLayeredPane().add(imglabel,new Integer(Integer.MIN_VALUE));
		Container cp=this.getContentPane();
		cp.setLayout(new BorderLayout());//设置为边框布局
		
		/**窗体的上部分，用来显示最淡化，最小化和关闭按钮**/
		final SystemTrayFrame systray=new SystemTrayFrame(this);//建立后台托管对象
		systray.showSystemTray();
		LoginTopPanel jpn_menu=new LoginTopPanel(this,systray);
		jpn_menu.showLoginTopUI();//调用初始化窗体北部分的方法		
	    cp.add(jpn_menu,BorderLayout.NORTH);
	    /**窗体右部分，点击箭头转到下一界面**/
		Arrow arrow =new Arrow();//窗体右边箭头,为按钮
		arrow.showUI();
	    cp.add(arrow,BorderLayout.EAST);
	    /**窗体下部分，切换界面**/
	    Point point=new Point();
	    point.showUI();
	    cp.add(point,BorderLayout.SOUTH);
	    /**窗体中间部分**/
	    paintcenter();
	    ((JPanel)cp).setOpaque(false);
		this.setSize(1000, 677);
	    this.setVisible(true);
	}
	/**
	 * 窗体中间部分
	 */
	public void paintcenter(){
		jpcenter=new JPanel();
	    jpcenter.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
	    FlowLayout flowlayout=new FlowLayout(FlowLayout.LEFT);
	    jpcenter.setLayout(flowlayout);
	    jpcenter.setPreferredSize(new Dimension(870,1000));
	    jpcenter.setOpaque(false);//窗体透明
	    jsp=new JScrollPane(jpcenter);//添加滚动条
	    jsp.setOpaque(false);
	    jsp.getViewport().setOpaque(false);//让JScrollPane透明的方法
	    jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);//垂直方向 
	    jsp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);//水平方向 
	    paintsearch();
	    jpcenter.addMouseListener(mslis);

	    this.add(jsp,BorderLayout.CENTER);
		
	}
	/**
	 * 搜索组件部分
	 */
	public void paintsearch(){
		JTextField jtext=new JTextField();
		jtext.setPreferredSize(new Dimension(360,40));
		jpcenter.add(jtext);
		jbsearch=new JButton("搜索");
		jbsearch.setPreferredSize(new Dimension(80,40));
		jbsearch.setBorderPainted(false);
		jpcenter.add(jbsearch);
	}
	/**
	 * 生成文件夹的方法
	 * @param jp 文件夹组件
	 */
	public void paintjfile(){
		// 重写j1
		ImageIcon fileicon=new ImageIcon(this.getClass().getResource("images/arrow.jpg"));
		jpfile=new JPanel();
		jpfile.setOpaque(false);
		jbfile=new JButton("私有云");
		jbfile.setBorderPainted(false);//除去边框
		jbfile.setPreferredSize(new Dimension(250,150));
		jbfile.setBackground(new Color(10,10,10));
		jpfile.add(jbfile);
	}
   /**
    * 点击右键时弹出添加文件分类对话框
    */
	MouseAdapter mslis=new MouseAdapter(){
    	public void mousePressed(MouseEvent e){
    		JPopupMenu popmenu=new JPopupMenu();
    		JMenuItem menuitem1=new JMenuItem("添加文件分类");
    		menuitem1.addActionListener(action);
    		popmenu.add(menuitem1);
    		if(e.getButton() == MouseEvent.BUTTON3){//点击右键
    			popmenu.show(e.getComponent(), e.getX(), e.getY());
    		}
    	}
    };
    /**
     * 添加分类图形按钮，动作监听器
     */
    ActionListener action=new ActionListener(){

		public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand().equals("添加文件分类")){
				paintjfile();//重写按钮的方法
				jpcenter.add(jpfile);
			}
		}
    };
	
	public static void main(String[] args) {
		Bordertwo bordertwo=new Bordertwo();
		bordertwo.showUI();

	}

}
