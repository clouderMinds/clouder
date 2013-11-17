package democonsole;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

import demotool.SocketConnecter;
import demotool.SocketCreateFile;
import demotool.SocketDeleteFile;
import demotool.SocketDownloadFromHDFS;
import demotool.SocketRenameFile;
import demotool.SocketSendToHDFS;

/**
 * 显示用户信息的面板
 * @author hadoop
 *
 */
public class TopToolPanel extends javax.swing.JPanel{
     
	private static final long serialVersionUID = 1L;
	private SocketConnecter connector;//需要的连接对象
	private LeftJpanel left_panel;
	private javax.swing.JFrame demo_ui;
	public void getLeftPanel(LeftJpanel left_panel){
		this.left_panel=left_panel;
	}
	//重载构造方法，获得对象
	public TopToolPanel(SocketConnecter connector,javax.swing.JFrame demo_ui){
		this.connector=connector;
		this.demo_ui=demo_ui;
	}
	    public void showTopToolUI(){
		this.setLayout(new FlowLayout(FlowLayout.LEFT));
		this.setBackground(new Color(163,215,252));
		/***************登录界面*************/
		javax.swing.JPanel login_panel=new javax.swing.JPanel();		
		login_panel.setPreferredSize(new Dimension(170,80));
		login_panel.setLayout(new BorderLayout());
		javax.swing.ImageIcon login_pic = new javax.swing.ImageIcon(this.getClass().getResource("images/login_pic.jpg"));
		JLabel label_pic=new JLabel(login_pic);
		login_panel.add(label_pic,BorderLayout.WEST);
		
		javax.swing.JPanel info_panel=new javax.swing.JPanel();
		info_panel.setLayout(new GridLayout(3,1));
		JLabel name_label=new JLabel("用户名：");		
		info_panel.add(name_label);
		JLabel cls_label=new JLabel("等级：");		
		info_panel.add(cls_label);
		
		javax.swing.JButton login_jbu=new javax.swing.JButton("退出登录");
		
		info_panel.add(login_jbu);
		login_panel.add(info_panel,BorderLayout.EAST);
		this.add(login_panel);
		/**************************创建动作监听器***************************/
		ActionListener alis=new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				String command=e.getActionCommand();
				if(command.equals("upfile")){
					SocketSendToHDFS hdsf=new SocketSendToHDFS(connector.getAccount(),left_panel);
					hdsf.start();
					
				}else if(command.equals("downfile")){
					SocketDownloadFromHDFS downloader=new SocketDownloadFromHDFS(connector.getAccount(),left_panel);
					downloader.start();
					
				}else if(command.equals("deletefile")){
					SocketDeleteFile deleter=new SocketDeleteFile(connector.getAccount(),left_panel,left_panel.hdfspath);
					deleter.start();
					
				}else if(command.equals("renamefile")){
					SocketRenameFile renameer=new SocketRenameFile(connector.getAccount(),left_panel,left_panel.hdfspath);
					renameer.start();
					
				}else if(command.equals("movefile")){
					ChooseFileDialog remover=new ChooseFileDialog(connector.getAccount(),left_panel,demo_ui);
					remover.showChooseFileDialog();
					
				}else if(command.equals("builtfile")){
					SocketCreateFile creater=new SocketCreateFile(connector.getAccount(),left_panel,left_panel.hdfspath);
					creater.start();
					
				}
				
			}
			
		};
		/************************添加操作按钮图标***************************/
		final ImageIcon up_on=new ImageIcon(this.getClass().getResource("images/up_on.png"));
		final ImageIcon down_on=new ImageIcon(this.getClass().getResource("images/down_on.png"));
		final ImageIcon delete_on=new ImageIcon(this.getClass().getResource("images/delete_on.png"));
		final ImageIcon rename_on=new ImageIcon(this.getClass().getResource("images/rename_on.png"));
		final ImageIcon move_on=new ImageIcon(this.getClass().getResource("images/move_on.png"));
		final ImageIcon built_on=new ImageIcon(this.getClass().getResource("images/built_on.png"));
		final ImageIcon up_out=new ImageIcon(this.getClass().getResource("images/up_out.png"));
		final ImageIcon down_out=new ImageIcon(this.getClass().getResource("images/down_out.png"));
		final ImageIcon delete_out=new ImageIcon(this.getClass().getResource("images/delete_out.png"));
		final ImageIcon rename_out=new ImageIcon(this.getClass().getResource("images/rename_out.png"));
		final ImageIcon move_out=new ImageIcon(this.getClass().getResource("images/move_out.png"));
		final ImageIcon built_out=new ImageIcon(this.getClass().getResource("images/built_out.png"));
		
		/**************************创建监听器************************/
		MouseAdapter moslis=new MouseAdapter(){
			//����ƶ�������ϵ�ʱ�����
			public void mouseEntered(MouseEvent e){
				JButton jbu=(JButton)e.getSource();
				String jbu_act=jbu.getActionCommand();
				if(jbu_act.equals("upfile")){
				      jbu.setIcon(up_on);
	              }else if(jbu_act.equals("downfile")){
	            	  jbu.setIcon(down_on);
	              }else if(jbu_act.equals("deletefile")){
	            	  jbu.setIcon(delete_on);
	             }else if(jbu_act.equals("renamefile")){
	            	 jbu.setIcon(rename_on);
	             }else if(jbu_act.equals("movefile")){
	            	 jbu.setIcon(move_on);
	             }else if(jbu_act.equals("builtfile")){
	            	 jbu.setIcon(built_on);
	             }
			}
			//����ƿ������ʱ�����
			public void mouseExited(MouseEvent e){
				JButton jbu=(JButton)e.getSource();
				String jbu_act=jbu.getActionCommand();
				if(jbu_act.equals("upfile")){
					jbu.setIcon(up_out);
	              }else if(jbu_act.equals("downfile")){
	            	  jbu.setIcon(down_out);
	              }else if(jbu_act.equals("deletefile")){
	            	  jbu.setIcon(delete_out);
	             }else if(jbu_act.equals("renamefile")){
	            	 jbu.setIcon(rename_out);
	             }else if(jbu_act.equals("movefile")){
	            	 jbu.setIcon(move_out);
	             }else if(jbu_act.equals("builtfile")){
	            	 jbu.setIcon(built_out);
	             }
			}
		};
		/*************************��ӹ��ܰ�ť***************************/
		Insets ins=new Insets(0,0,0,0);//����һ���߾����
		JButton jbu_up=new JButton(up_out);
		jbu_up.setActionCommand("upfile");
		jbu_up.setMargin(ins);
		jbu_up.addActionListener(alis);
		jbu_up.addMouseListener(moslis);
		this.add(jbu_up);
		JButton jbu_down=new JButton(down_out);
		jbu_down.setActionCommand("downfile");
		jbu_down.setMargin(ins);
		jbu_down.addActionListener(alis);
		jbu_down.addMouseListener(moslis);
		this.add(jbu_down);
		JButton jbu_delete=new JButton(delete_out);
		jbu_delete.setActionCommand("deletefile");
		jbu_delete.setMargin(ins);
		jbu_delete.addActionListener(alis);
		jbu_delete.addMouseListener(moslis);
		this.add(jbu_delete);
		JButton jbu_rename=new JButton(rename_out);
		jbu_rename.setActionCommand("renamefile");
		jbu_rename.setMargin(ins);
		jbu_rename.addActionListener(alis);
		jbu_rename.addMouseListener(moslis);
		this.add(jbu_rename);
		JButton jbu_move=new JButton(move_out);
		jbu_move.setActionCommand("movefile");
		jbu_move.setMargin(ins);
		jbu_move.addActionListener(alis);
		jbu_move.addMouseListener(moslis);
		this.add(jbu_move);
		JButton jbu_built=new JButton(built_out);
		jbu_built.setActionCommand("builtfile");
		jbu_built.setMargin(ins);
		jbu_built.addActionListener(alis);
		jbu_built.addMouseListener(moslis);
		this.add(jbu_built);
		
		/**********************�����Ǵӷ�������ȡ��Ϣ�Ĳ���*********************/
			boolean bl=connector.sendRefreshInfomation();
			if(bl){//如果获取信息成功
				ArrayList<String> info=connector.getUserInfomation();
				if(info!=null){
					//ˢ显示用户信息
					name_label.setText("用户名:"+info.get(0));
					cls_label.setText("等级"+info.get(1));
				}
			} 			
    } 
}
