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

/**
 * 顶端的面板，主要用来实现最大化和最小化以及关闭按钮
 * @author hadoop
 *
 */
public class LoginTopPanel extends javax.swing.JPanel{

	private static final long serialVersionUID = 1L;
	//创建这些按钮所需要的图标
     private javax.swing.JFrame login_ui;
     private ImageIcon min_out=new ImageIcon(this.getClass().getResource("images/yellow-1.png"));
 	 private ImageIcon min_on=new ImageIcon(this.getClass().getResource("images/yellow-2.png"));
 	 private ImageIcon close_out=new ImageIcon(this.getClass().getResource("images/red-1.png"));
 	 private ImageIcon close_on=new ImageIcon(this.getClass().getResource("images/red-2.png"));
 	 //记录按下时鼠标的坐标位置
 	 private int x,y;
 	 protected SystemTrayFrame systray;
 	 //重载构造方法，获取相应的对象
     public LoginTopPanel(javax.swing.JFrame login_ui,SystemTrayFrame systray){
    	 this.login_ui=login_ui;
    	 this.systray=systray;
     }
	 //初始化界面的方法，显示界面
     public void showLoginTopUI(){
    	 this.setLayout(new BorderLayout());
 		 this.setBackground(new Color(160,215,250));
 		//添加应用的标签
 		javax.swing.JLabel title=new javax.swing.JLabel("   蓝云2013");
 		
 		this.add(title,BorderLayout.WEST);
 		
 		/******************界面的右边部分******************/
 		javax.swing.JPanel right_panel=new javax.swing.JPanel();
 		right_panel.setLayout(new FlowLayout(FlowLayout.RIGHT));
 		right_panel.setOpaque(false);
 		
 		/*****************最小化按钮ť****************/
 		JButton jbu_min=new JButton(min_out);
 		jbu_min.setActionCommand("minsize");
 		jbu_min.setOpaque(false);
 		jbu_min.setContentAreaFilled(false);
 		jbu_min.setBorder(null);
 		right_panel.add(jbu_min);
 		
 	    /******************最大化按钮**************/
 		JButton jbu_close=new JButton(close_out);
 		jbu_close.setActionCommand("close");
 		jbu_close.setOpaque(false);
 		jbu_close.setContentAreaFilled(false);
 		jbu_close.setBorder(null);
 		right_panel.add(jbu_close);
 		this.add(right_panel,BorderLayout.EAST);
 		/*******************添加监听器******************/
		ActionListener actlis=new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
              String command=e.getActionCommand();
              if(command.equals("minsize")){
            	  login_ui.setVisible(false);
            	  
              }else if(command.equals("close")){
            	  systray.removeTrayIcon();//移除后台托管对象
            	  System.exit(0);
              }
				
			}			
		};
		//给按钮添加监听器
		jbu_min.addActionListener(actlis);
		jbu_close.addActionListener(actlis);
		
		/*****************************************/
		MouseAdapter mslis=new MouseAdapter(){
			//鼠标移动到组件上时调用
			public void mouseEntered(MouseEvent e){
				JButton jbu=(JButton)e.getSource();
				String jbu_act=jbu.getActionCommand();
				if(jbu_act.equals("minsize")){
					jbu.setIcon(min_on);
	              }else if(jbu_act.equals("close")){
	            	  jbu.setIcon(close_on);
	             }
			}
			//鼠标离开逐渐是调用
			public void mouseExited(MouseEvent e){
				JButton jbu=(JButton)e.getSource();
				String jbu_act=jbu.getActionCommand();
				if(jbu_act.equals("minsize")){
					jbu.setIcon(min_out);
	              }else if(jbu_act.equals("close")){
	            	  jbu.setIcon(close_out); 
	             }
			}
		};
		//给按钮添加监听器
		jbu_min.addMouseListener(mslis);
		jbu_close.addMouseListener(mslis);
		
		//ʹ创建鼠标监听器
		MouseAdapter mlis=new MouseAdapter(){
			public void mousePressed(MouseEvent e){
					
				x=e.getXOnScreen()-login_ui.getX();
				y=e.getYOnScreen()-login_ui.getY();
			}
		};
		
		//创建鼠标移动监听器
		MouseMotionListener moslis=new MouseMotionListener(){
			@Override
			public void mouseDragged(MouseEvent e) {
				
				int xs=e.getXOnScreen();
				int ys=e.getYOnScreen();
					
				login_ui.setLocation(xs-x, ys-y);
			}
			public void mouseMoved(MouseEvent e) {
				
			}			
		};		
		this.addMouseListener(mlis);
		this.addMouseMotionListener(moslis);	
     }
}
