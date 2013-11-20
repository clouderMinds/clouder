package democonsole;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;

import demotool.SocketConnecter;

/**
 * 应用的主界面，用于显示文件视图等内容
 * @author hadoop
 *
 */
public class MainDemo {
	
    private SocketConnecter connector;//获取用户信息所需要的连接对象
    public MainDemo(){
    	
    }
    //重载构造方法，获取连接对象
    public MainDemo(SocketConnecter connector){
    	this.connector=connector;
    }
    public static void main(String args[]){
    	MainDemo maindemo=new MainDemo();
    	maindemo.showMainDemoUI();
    }
	/**
	 * 初始化主界面的方法，
	 */
	public void showMainDemoUI(){
		javax.swing.JFrame demo_ui=new javax.swing.JFrame();
		demo_ui.setSize(680, 500);
		demo_ui.setLocationRelativeTo(null);
		demo_ui.setUndecorated(true);
		demo_ui.setResizable(true);
		//应用在状态栏的图标
		ImageIcon app_login=new ImageIcon(this.getClass().getResource("images/app_login.png"));
		demo_ui.setIconImage(app_login.getImage());
		//设置布局
		demo_ui.setLayout(new BorderLayout());
		
		/******************窗体的上部分*******************/
		TopJpanel top_jpanel=new TopJpanel(demo_ui,connector);
		top_jpanel.showTopJpanelUI();
		demo_ui.add(top_jpanel,BorderLayout.NORTH);
		
		/*****************窗体的中间部分********************/
		LeftJpanel left_jpanel=new LeftJpanel(connector.getAccount());
		left_jpanel.showLeftJpanelUI();
		javax.swing.JScrollPane file_scroll=new javax.swing.JScrollPane(left_jpanel);
		file_scroll.setPreferredSize(new Dimension(180,480));
		demo_ui.add(file_scroll,BorderLayout.WEST);
		
		/*******************窗体的下部分********************/
		final javax.swing.JPanel center_jpanel=new javax.swing.JPanel();
		center_jpanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		javax.swing.JScrollPane panel_scroll=new javax.swing.JScrollPane(center_jpanel);
		demo_ui.add(panel_scroll,BorderLayout.CENTER);
		
		/***************窗体的下部分***************/
		left_jpanel.setCenterPanel(center_jpanel);
		top_jpanel.getLeftPanel(left_jpanel);
		
		//设置窗体可见
		demo_ui.setVisible(true);
	
	}
}
