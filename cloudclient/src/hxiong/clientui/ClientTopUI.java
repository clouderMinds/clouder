package hxiong.clientui;

import hxiong.config.ClientuiConfig;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;


/**
 * 客户端界面的上部分窗体，主要用来显示窗体的最大化，最小化和关闭按钮
 * 并且能够移动窗体
 * @author 熊天成
 *
 */
public class ClientTopUI extends javax.swing.JPanel{
	private static final long serialVersionUID = 1L;
	private javax.swing.JFrame client_ui;
	private boolean bl=true;
	private int x,y;//记录窗口的坐标
	//按钮的图标
	private ImageIcon min_out=new ImageIcon(this.getClass().getResource("images/yellow-1.png"));
	private ImageIcon min_on=new ImageIcon(this.getClass().getResource("images/yellow-2.png"));
    private ImageIcon close_out=new ImageIcon(this.getClass().getResource("images/red-1.png"));
    private ImageIcon close_on=new ImageIcon(this.getClass().getResource("images/red-2.png"));
    private ImageIcon topBg=new ImageIcon(this.getClass().getResource("images/topBg.png"));
    //重载构造方法
	public ClientTopUI(javax.swing.JFrame client_ui){
	   this.client_ui=client_ui;//获得窗口对象
	   showClientTopUI();//调用往上面部分的窗体添加组件的方法
	}
	protected void paintComponent(Graphics g) {  
        Image imgBg = topBg.getImage();  
        g.drawImage(imgBg, 0, 0, ClientuiConfig.clientWidth,ClientuiConfig.clientTopHeight, topBg.getImageObserver());  
    }  
	/**
	 * 该方法用来给上部分的面板添加组件以及相关的操作
	 */
	private void showClientTopUI(){
		this.setPreferredSize(new Dimension(ClientuiConfig.clientWidth,ClientuiConfig.clientTopHeight));//设置好高度
        this.setLayout(new BorderLayout());//设置为边框布局
		this.setBackground(Color.lightGray);
		
		//左边部分，显示窗口标题等
		javax.swing.JPanel left_panel=new javax.swing.JPanel();
		left_panel.setLayout(new FlowLayout(FlowLayout.LEFT));//面板上的组件居左对齐
		left_panel.setOpaque(false);//设置透明
		//添加标签
		javax.swing.JLabel title=new javax.swing.JLabel("  我的云盘");
		left_panel.add(title);
		this.add(left_panel,BorderLayout.WEST);//置于西边，即左边
		
		//右边部分，用来显示最大化，最小化等按钮
		javax.swing.JPanel right_panel=new javax.swing.JPanel();
		right_panel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		right_panel.setOpaque(false);//设置透明
		
		//最小化按钮
		JButton jbu_min=new JButton(min_out);
		jbu_min.setActionCommand("minsize");
		jbu_min.setOpaque(false);
		jbu_min.setContentAreaFilled(false);
		jbu_min.setBorder(null);
		right_panel.add(jbu_min);		
	   //关闭按钮
		JButton jbu_close=new JButton(close_out);
		jbu_close.setActionCommand("close");
		jbu_close.setOpaque(false);
		jbu_close.setContentAreaFilled(false);
		jbu_close.setBorder(null);
		right_panel.add(jbu_close);
		this.add(right_panel,BorderLayout.EAST);//放在右边，即东边
		
		/*******************添加监听器******************/
		ActionListener actlis=new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
              String command=e.getActionCommand();
              if(command.equals("minsize")){
            	  client_ui.setVisible(false);
            	  
              }else if(command.equals("maxsize")){
            	  if(bl){
            		  client_ui.setExtendedState(javax.swing.JFrame.MAXIMIZED_BOTH);
            		  System.out.println(client_ui.getWidth());
            		  bl=false;
            	  }else{
            		  client_ui.setExtendedState(javax.swing.JFrame.NORMAL);
            		  bl=true;
            	  }
              }else if(command.equals("close")){
          
            	  System.exit(0);
              }				
			}			
		};
		//给按钮添加监听器
		jbu_min.addActionListener(actlis);
		jbu_close.addActionListener(actlis);
		
		/*****************************************/
		MouseAdapter mslis=new MouseAdapter(){
			//鼠标移动到组件上的时候调用
			public void mouseEntered(MouseEvent e){
				JButton jbu=(JButton)e.getSource();
				String jbu_act=jbu.getActionCommand();
				if(jbu_act.equals("minsize")){
					jbu.setIcon(min_on);
	              }else{
	            	  jbu.setIcon(close_on);
	             }
			}
			//鼠标离开组件的时候调用
			public void mouseExited(MouseEvent e){
				JButton jbu=(JButton)e.getSource();
				String jbu_act=jbu.getActionCommand();
				if(jbu_act.equals("minsize")){
					jbu.setIcon(min_out);
	              }else{
	            	  jbu.setIcon(close_out); 
	             }
			}
		};
		//给按钮添加监听器
		jbu_min.addMouseListener(mslis);
		jbu_close.addMouseListener(mslis);
		
		//ʹ创建鼠标监听器
		MouseAdapter mlis=new MouseAdapter(){
			//鼠标按下时触发
			public void mousePressed(MouseEvent e){
					
				x=e.getXOnScreen()-client_ui.getX();
				y=e.getYOnScreen()-client_ui.getY();
				ClientTopUI.this.setCursor(new Cursor(Cursor.MOVE_CURSOR));
			}
			//鼠标释放时触发
			public void mouseReleased(MouseEvent e) {
				ClientTopUI.this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

		};
		
		//创建鼠标移动监听器
		MouseMotionListener moslis=new MouseMotionListener(){

			@Override
			public void mouseDragged(MouseEvent e) {
				
				int xs=e.getXOnScreen();
				int ys=e.getYOnScreen();
					
				client_ui.setLocation(xs-x, ys-y);
			}
			public void mouseMoved(MouseEvent e) {
				
			}
			
		};
		//给面板添加监听器，方便移动窗体
		this.addMouseListener(mlis);
		this.addMouseMotionListener(moslis);
	}
}
