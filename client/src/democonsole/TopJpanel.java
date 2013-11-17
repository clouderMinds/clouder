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

import demotool.SocketConnecter;

public class TopJpanel extends javax.swing.JPanel{
	private SocketConnecter connector;//获取用户信息所需要的连接对象
	private static final long serialVersionUID = 1L;
	private javax.swing.JFrame demo_ui;
	private boolean bl=true;
	private int x,y;
    private SystemTrayFrame sys;//后台托盘对象
	private ImageIcon min_out=new ImageIcon(this.getClass().getResource("images/yellow-1.png"));
	private ImageIcon min_on=new ImageIcon(this.getClass().getResource("images/yellow-2.png"));
    private ImageIcon max_out=new ImageIcon(this.getClass().getResource("images/green-1.png"));
    private ImageIcon max_on=new ImageIcon(this.getClass().getResource("images/green-2.png"));
    private ImageIcon close_out=new ImageIcon(this.getClass().getResource("images/red-1.png"));
    private ImageIcon close_on=new ImageIcon(this.getClass().getResource("images/red-2.png"));
	private TopToolPanel button_panel;
    //重载构造方法
	public TopJpanel(javax.swing.JFrame demo_ui,SocketConnecter connector){
		this.demo_ui=demo_ui;
		this.connector=connector;
		sys=new SystemTrayFrame(demo_ui);
		sys.showSystemTray();
	}
	/**
	 * 设置
	 * @param left_panel
	 */
	public void getLeftPanel(LeftJpanel left_panel){
		button_panel.getLeftPanel(left_panel);
	}
	/**
	 * 初始化主界面顶端面板的方法
	 */
	public void showTopJpanelUI(){
		this.setLayout(new BorderLayout());//设置为边框布局
		
		/******************面板的上部分********************/
		javax.swing.JPanel top_panel=new javax.swing.JPanel();
		top_panel.setBackground(Color.lightGray);
		top_panel.setLayout(new BorderLayout());
		
		//添加标签
		javax.swing.JLabel title=new javax.swing.JLabel("  我的云盘");
		
		top_panel.add(title,BorderLayout.WEST);
		
		/******************添加按钮******************/
		javax.swing.JPanel right_panel=new javax.swing.JPanel();
		right_panel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		right_panel.setOpaque(false);
		
		JButton jbu_min=new JButton(min_out);
		jbu_min.setActionCommand("minsize");
		jbu_min.setOpaque(false);
		jbu_min.setContentAreaFilled(false);
		jbu_min.setBorder(null);
		right_panel.add(jbu_min);
		
		
		JButton jbu_max=new JButton(max_out);
		jbu_max.setActionCommand("maxsize");
		jbu_max.setOpaque(false);
		jbu_max.setContentAreaFilled(false);
		jbu_max.setBorder(null);
		right_panel.add(jbu_max);
		
	
		JButton jbu_close=new JButton(close_out);
		jbu_close.setActionCommand("close");
		jbu_close.setOpaque(false);
		jbu_close.setContentAreaFilled(false);
		jbu_close.setBorder(null);
		right_panel.add(jbu_close);
		top_panel.add(right_panel,BorderLayout.EAST);
		
		this.add(top_panel,BorderLayout.NORTH);
		
		
		button_panel=new TopToolPanel(connector,demo_ui);
		button_panel.showTopToolUI();
		this.add(button_panel,BorderLayout.CENTER);
		
		/*******************添加监听器******************/
		ActionListener actlis=new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
              String command=e.getActionCommand();
              if(command.equals("minsize")){
            	  demo_ui.setVisible(false);
            	  
              }else if(command.equals("maxsize")){
            	  if(bl){
            		  demo_ui.setExtendedState(javax.swing.JFrame.MAXIMIZED_BOTH);
            		  bl=false;
            	  }else{
            		  demo_ui.setExtendedState(javax.swing.JFrame.NORMAL);
            		  bl=true;
            	  }
              }else if(command.equals("close")){
            	  sys.removeTrayIcon();//移除托管对象
            	  System.exit(0);
              }				
			}			
		};
		//给按钮添加监听器
		jbu_min.addActionListener(actlis);
		jbu_max.addActionListener(actlis);
		jbu_close.addActionListener(actlis);
		
		/*****************************************/
		MouseAdapter mslis=new MouseAdapter(){
			//鼠标移动到组件上的时候调用
			public void mouseEntered(MouseEvent e){
				JButton jbu=(JButton)e.getSource();
				String jbu_act=jbu.getActionCommand();
				if(jbu_act.equals("minsize")){
					jbu.setIcon(min_on);
	              }else if(jbu_act.equals("maxsize")){
	            	  jbu.setIcon(max_on);
	              }else if(jbu_act.equals("close")){
	            	  jbu.setIcon(close_on);
	             }
			}
			//鼠标离开组件的时候调用
			public void mouseExited(MouseEvent e){
				JButton jbu=(JButton)e.getSource();
				String jbu_act=jbu.getActionCommand();
				if(jbu_act.equals("minsize")){
					jbu.setIcon(min_out);
	              }else if(jbu_act.equals("maxsize")){
	            	  jbu.setIcon(max_out);
	              }else if(jbu_act.equals("close")){
	            	  jbu.setIcon(close_out); 
	             }
			}
		};
		//给按钮添加监听器
		jbu_min.addMouseListener(mslis);
		jbu_max.addMouseListener(mslis);
		jbu_close.addMouseListener(mslis);
		
		//ʹ创建鼠标监听器
		MouseAdapter mlis=new MouseAdapter(){
			public void mousePressed(MouseEvent e){
					
				x=e.getXOnScreen()-demo_ui.getX();
				y=e.getYOnScreen()-demo_ui.getY();
			}
		};
		
		//创建鼠标移动监听器
		MouseMotionListener moslis=new MouseMotionListener(){

			@Override
			public void mouseDragged(MouseEvent e) {
				
				int xs=e.getXOnScreen();
				int ys=e.getYOnScreen();
					
				demo_ui.setLocation(xs-x, ys-y);
			}
			public void mouseMoved(MouseEvent e) {
				
			}
			
		};
		
		top_panel.addMouseListener(mlis);
		top_panel.addMouseMotionListener(moslis);		
	}
}
