package serverconsole;

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

public class ServerTopPanel extends javax.swing.JPanel{

	private static final long serialVersionUID = 1L;
	private javax.swing.JFrame server_ui;
	private boolean bl=true;
	private int x,y;
	//创建最大化最小化和关闭按钮图标
	private ImageIcon min_out=new ImageIcon(this.getClass().getResource("images/yellow-1.png"));
	private ImageIcon min_on=new ImageIcon(this.getClass().getResource("images/yellow-2.png"));
    private ImageIcon max_out=new ImageIcon(this.getClass().getResource("images/green-1.png"));
    private ImageIcon max_on=new ImageIcon(this.getClass().getResource("images/green-2.png"));
    private ImageIcon close_out=new ImageIcon(this.getClass().getResource("images/red-1.png"));
    private ImageIcon close_on=new ImageIcon(this.getClass().getResource("images/red-2.png"));
    //重载构造方法，获取窗体对象
    public ServerTopPanel(javax.swing.JFrame server_ui){
    	this.server_ui=server_ui;
    }
    /**
     * 初始化窗体上部分的方法
     */
    public void showServerTopPanel(){
        this.setLayout(new BorderLayout());//设置为边框布局
		this.setBackground(Color.lightGray);//设置背景颜色
		
		//创建一个标签，用于设置应用的名称
		javax.swing.JLabel title=new javax.swing.JLabel("  蓝云服务器V1.0");
		
		this.add(title,BorderLayout.WEST);
		
		/******************右边部分，用于添加最大最小化按钮******************/
		javax.swing.JPanel right_panel=new javax.swing.JPanel();
		right_panel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		right_panel.setOpaque(false);//设置背景透明
		
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
		this.add(right_panel,BorderLayout.EAST);
		
		
		/*******************使用匿名内部类，创建动作监听器******************/
		ActionListener actlis=new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
              String command=e.getActionCommand();
              if(command.equals("minsize")){
            	  server_ui.setVisible(false);
            	  
              }else if(command.equals("maxsize")){
            	  if(bl){
            		  server_ui.setExtendedState(javax.swing.JFrame.MAXIMIZED_BOTH);
            		  bl=false;
            	  }else{
            		  server_ui.setExtendedState(javax.swing.JFrame.NORMAL);
            		  bl=true;
            	  }
              }else if(command.equals("close")){
            	  System.exit(0);
              }				
			}			
		};
		//给按钮添加监听器
		jbu_min.addActionListener(actlis);
		jbu_max.addActionListener(actlis);
		jbu_close.addActionListener(actlis);
		
		/********************使用匿名内部类创建鼠标减轻器*********************/
		MouseAdapter mslis=new MouseAdapter(){
			//当鼠标进入组件时调用
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
			//当鼠标离开组件时调用
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
		//给按钮添加鼠标监听器
		jbu_min.addMouseListener(mslis);
		jbu_max.addMouseListener(mslis);
		jbu_close.addMouseListener(mslis);
		
		//使用匿名内部类创建减轻器
		MouseAdapter mlis=new MouseAdapter(){
			public void mousePressed(MouseEvent e){
					
				x=e.getXOnScreen()-server_ui.getX();
				y=e.getYOnScreen()-server_ui.getY();
			}
		};
		
		//创建一个鼠标移动监听器
		MouseMotionListener moslis=new MouseMotionListener(){

			@Override
			public void mouseDragged(MouseEvent e) {
				
				int xs=e.getXOnScreen();
				int ys=e.getYOnScreen();
					
				server_ui.setLocation(xs-x, ys-y);
			}
			public void mouseMoved(MouseEvent e) {
				
			}
			
		};
		//给当前面板添加监听器
		this.addMouseListener(mlis);
		this.addMouseMotionListener(moslis);		
    }
}
