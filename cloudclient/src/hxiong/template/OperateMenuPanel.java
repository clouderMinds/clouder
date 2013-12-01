package hxiong.template;

import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class OperateMenuPanel extends JPanel{
	private static final long serialVersionUID = 1L;
	JFrame jf;
	JButton btn_upload;
	JButton btn_add;
	JButton btn_download;
	private ImageIcon upload_on=new ImageIcon(this.getClass().getResource("images/upload_on4.png"));
	private ImageIcon upload_out=new ImageIcon(this.getClass().getResource("images/upload_out4.png"));
	private ImageIcon download_on=new ImageIcon(this.getClass().getResource("images/download_on4.png"));
	private ImageIcon download_out=new ImageIcon(this.getClass().getResource("images/download_out4.png"));
	private ImageIcon addfiles_on=new ImageIcon(this.getClass().getResource("images/addfiles_on4.png"));
	private ImageIcon addfiles_out=new ImageIcon(this.getClass().getResource("images/addfiles_out4.png"));
	private ImageIcon more_on=new ImageIcon(this.getClass().getResource("images/more_on4.png"));
	private ImageIcon more_out=new ImageIcon(this.getClass().getResource("images/more_4.png"));
	//
	public OperateMenuPanel(){
		
		showUI();
	}
	
	private void showUI(){
		this.setSize(900,50);
//		this.setLayout(new GridLayout(1,6,2,2));
		this.setLayout(new FlowLayout(FlowLayout.LEFT));
		btn_upload = new JButton(upload_out);
		btn_upload.setActionCommand("upload");
		btn_upload.setOpaque(false);
		btn_upload.setContentAreaFilled(false);
		btn_upload.setBorder(null);
		this.add(btn_upload);
		
		btn_download = new JButton(download_out);
		btn_download.setActionCommand("download");
		btn_download.setOpaque(false);
		btn_download.setContentAreaFilled(false);
		btn_download.setBorder(null);
		this.add(btn_download);
		
		btn_add = new JButton(addfiles_out);
		btn_add.setActionCommand("addfiles");
		btn_add.setOpaque(false);
		btn_add.setContentAreaFilled(false);
		btn_add.setBorder(null);
		this.add(btn_add);
		
		JButton btn_more = new JButton(more_out);
		btn_more.setActionCommand("more");
		btn_more.setOpaque(false);
		btn_more.setContentAreaFilled(false);
		btn_more.setBorder(null);
		this.add(btn_more);
		

		MouseAdapter mslis=new MouseAdapter(){
			public void mouseEntered(MouseEvent e){
				JButton jbu=(JButton)e.getSource();
				String jbu_act=jbu.getActionCommand();
				if(jbu_act.equals("upload")){
					 jbu.setIcon(upload_on);
	             }else if(jbu_act.equals("download")){
	            	 jbu.setIcon(download_on);
	             }else if(jbu_act.equals("addfiles")){
	            	 jbu.setIcon(addfiles_on);
	             }else if(jbu_act.equals("more")){
	            	 jbu.setIcon(more_on);
	             }
			}
			public void mouseExited(MouseEvent e){
				JButton jbu=(JButton)e.getSource();
				String jbu_act=jbu.getActionCommand();
				if(jbu_act.equals("upload")){
					jbu.setIcon(upload_out);
	              }else if(jbu_act.equals("download")){
	            	jbu.setIcon(download_out); 
	             }else if(jbu_act.equals("addfiles")){
	            	jbu.setIcon(addfiles_out);
	             }else if(jbu_act.equals("more")){
	            	jbu.setIcon(more_out);
	             }
			}
		};
		
		btn_upload.addMouseListener(mslis);
		btn_download.addMouseListener(mslis);
		btn_add.addMouseListener(mslis);
		btn_more.addMouseListener(mslis);
		
		//OperateListener olis = new OperateListener(jf);
		//btn_upload.addActionListener(olis);
		//btn_add.addActionListener(olis);
		//btn_download.addActionListener(olis);
	}
	
	
	
	
}
