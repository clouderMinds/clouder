package Clinet.MainUI;

import java.awt.BorderLayout;
import java.awt.Color;
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
import javax.swing.JPanel;

public class TopPanel extends JPanel{
	 private javax.swing.JFrame frame;
     private ImageIcon min_out=new ImageIcon(this.getClass().getResource("images/yellow-1.png"));
 	 private ImageIcon min_on=new ImageIcon(this.getClass().getResource("images/yellow-2.png"));
 	 private ImageIcon close_out=new ImageIcon(this.getClass().getResource("images/red-1.png"));
 	 private ImageIcon close_on=new ImageIcon(this.getClass().getResource("images/red-2.png"));
 	 private ImageIcon topBg=new ImageIcon(this.getClass().getResource("images/topBg2.png"));
 	 private int x,y;
 	 
     public TopPanel(javax.swing.JFrame frame){
    	 this.frame=frame;
     }
     
     protected void paintComponent(Graphics g) {  
         Image imgBg = topBg.getImage();  
         g.drawImage(imgBg, 0, 0, topBg.getIconWidth(), topBg.getIconHeight(), topBg.getImageObserver());  
     }  

     
     public void showTopUI(){
    	this.setLayout(new BorderLayout());
	    this.setPreferredSize(new Dimension(900,135));
 		
 		javax.swing.JPanel right_panel=new javax.swing.JPanel();
 		right_panel.setLayout(new FlowLayout(FlowLayout.RIGHT));
 		right_panel.setOpaque(false);
 		
 		OperateMenuPanel south_panel = new OperateMenuPanel(frame);
 		south_panel.setLayout(new FlowLayout(FlowLayout.LEFT));
 		south_panel.showUI();
 		south_panel.setOpaque(false);
 		this.add(south_panel,BorderLayout.SOUTH);
 		
 		JButton jbu_min=new JButton(min_out);
 		jbu_min.setActionCommand("minsize");
 		jbu_min.setOpaque(false);
 		jbu_min.setContentAreaFilled(false);
 		jbu_min.setBorder(null);
 		right_panel.add(jbu_min);
 		
 		JButton jbu_close=new JButton(close_out);
 		jbu_close.setActionCommand("close");
 		jbu_close.setOpaque(false);
 		jbu_close.setContentAreaFilled(false);
 		jbu_close.setBorder(null);
 		right_panel.add(jbu_close);
 		this.add(right_panel,BorderLayout.EAST);
		ActionListener actlis=new ActionListener(){
			public void actionPerformed(ActionEvent e) {
              String command=e.getActionCommand();
              if(command.equals("minsize")){
            	  frame.setVisible(false);
            	  
              }else if(command.equals("close")){
            	  System.exit(0);
              }
				
			}			
		};
		jbu_min.addActionListener(actlis);
		jbu_close.addActionListener(actlis);
		
		MouseAdapter mslis=new MouseAdapter(){
			public void mouseEntered(MouseEvent e){
				JButton jbu=(JButton)e.getSource();
				String jbu_act=jbu.getActionCommand();
				if(jbu_act.equals("minsize")){
					jbu.setIcon(min_on);
	              }else if(jbu_act.equals("close")){
	            	  jbu.setIcon(close_on);
	             }
			}
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
		jbu_min.addMouseListener(mslis);
		jbu_close.addMouseListener(mslis);
		
		MouseAdapter mlis=new MouseAdapter(){
			public void mousePressed(MouseEvent e){
					
				x=e.getXOnScreen()-frame.getX();
				y=e.getYOnScreen()-frame.getY();
			}
		};
		
		MouseMotionListener moslis=new MouseMotionListener(){
			public void mouseDragged(MouseEvent e) {
				
				int xs=e.getXOnScreen();
				int ys=e.getYOnScreen();
					
				frame.setLocation(xs-x, ys-y);
			}
			public void mouseMoved(MouseEvent e) {
				
			}			
		};		
		this.addMouseListener(mlis);
		this.addMouseMotionListener(moslis);	
     }
     

}
