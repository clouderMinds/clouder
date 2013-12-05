package hxiong.template;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class FileOperBarPanel extends JPanel{
	private static final long serialVersionUID = 1L;
	private ImageIcon img_filebar=new ImageIcon(this.getClass().getResource("images/filebar.png"));
	private ImageIcon left_on=new ImageIcon(this.getClass().getResource("images/left_on1.png"));
	private ImageIcon left_out=new ImageIcon(this.getClass().getResource("images/left_out2.png"));
	private ImageIcon right_on=new ImageIcon(this.getClass().getResource("images/right_on1.png"));
	private ImageIcon right_out=new ImageIcon(this.getClass().getResource("images/right_out2.png"));
	private ImageIcon reflesh_on=new ImageIcon(this.getClass().getResource("images/flesh_on3.png"));
	private ImageIcon reflesh_out=new ImageIcon(this.getClass().getResource("images/flesh_out3.png"));
	private ImageIcon search_on=new ImageIcon(this.getClass().getResource("images/search_on3.png"));
	private ImageIcon search_out=new ImageIcon(this.getClass().getResource("images/search_out3.png"));
	
	public FileOperBarPanel(){
		
		showUI();
	}
	protected void paintComponent(Graphics g){
		Image img = img_filebar.getImage();
		g.drawImage(img, 0, 0, img_filebar.getIconWidth(),img_filebar.getIconHeight(),img_filebar.getImageObserver());
	}
	
	private void showUI(){
		this.setPreferredSize(new Dimension(900,40));
		this.setLayout(new BorderLayout());
		this.setBackground(Color.white);
		
		JPanel panel_left = new JPanel();
		panel_left.setLayout(new GridLayout(1,3,30,0));
		panel_left.setOpaque(false);
		
		JPanel panel_center = new JPanel();
		panel_center.setLayout(new FlowLayout(FlowLayout.LEFT));
		panel_center.setOpaque(false);
		
		JButton btn_left = new JButton(left_out);
		btn_left.setActionCommand("left");
		btn_left.setOpaque(false);
		btn_left.setContentAreaFilled(false);
		btn_left.setBorder(null);
		panel_left.add(btn_left);
		
		JButton btn_right = new JButton(right_out);
		btn_right.setActionCommand("right");
		btn_right.setOpaque(false);
		btn_right.setContentAreaFilled(false);
		btn_right.setBorder(null);
		panel_left.add(btn_right);
		
		JLabel lbl1 = new JLabel("文件路径：");
		panel_center.add(lbl1);
		
		JTextField txt_path = new JTextField();
		txt_path.setPreferredSize(new Dimension(220,30));
		txt_path.setBorder(javax.swing.BorderFactory.createLineBorder(Color.lightGray));
		//txt_path.setEditable(false);
		panel_center.add(txt_path);
		
		JButton btn_reflesh = new JButton(reflesh_out);
		btn_reflesh.setActionCommand("reflesh");
		btn_reflesh.setOpaque(false);
		btn_reflesh.setContentAreaFilled(false);
		btn_reflesh.setBorder(null);
		panel_center.add(btn_reflesh);
		
		JLabel lbl2 = new JLabel("搜索：");
		panel_center.add(lbl2);
		
		JTextField txt_search = new JTextField();
		txt_search.setColumns(20);
		txt_search.setPreferredSize(new Dimension(220,30));
		txt_search.setBorder(javax.swing.BorderFactory.createLineBorder(Color.lightGray));
		panel_center.add(txt_search);
		
		JButton btn_search = new JButton(search_out);
		btn_search.setActionCommand("search");
		btn_search.setOpaque(false);
		btn_search.setContentAreaFilled(false);
		btn_search.setBorder(null);
		panel_center.add(btn_search);
		
		this.add(panel_left,BorderLayout.WEST);
		this.add(panel_center,BorderLayout.EAST);
		
		MouseAdapter mslis=new MouseAdapter(){
			public void mouseEntered(MouseEvent e){
				JButton jbu=(JButton)e.getSource();
				String jbu_act=jbu.getActionCommand();
				if(jbu_act.equals("left")){
					jbu.setIcon(left_on);
	              }else if(jbu_act.equals("right")){
	            	  jbu.setIcon(right_on);
	             }else if(jbu_act.equals("reflesh")){
	            	 jbu.setIcon(reflesh_on);
	             }else if(jbu_act.equals("search")){
	            	 jbu.setIcon(search_on);
	             }
			}
			public void mouseExited(MouseEvent e){
				JButton jbu=(JButton)e.getSource();
				String jbu_act=jbu.getActionCommand();
				if(jbu_act.equals("left")){
					jbu.setIcon(left_out);
	              }else if(jbu_act.equals("right")){
	            	  jbu.setIcon(right_out); 
	             }else if(jbu_act.equals("reflesh")){
	            	 jbu.setIcon(reflesh_out);
	             }else if(jbu_act.equals("search")){
	            	 jbu.setIcon(search_out);
	             }
			}
		};
		btn_left.addMouseListener(mslis);
		btn_right.addMouseListener(mslis);
		btn_reflesh.addMouseListener(mslis);
		btn_search.addMouseListener(mslis);
	}

}
