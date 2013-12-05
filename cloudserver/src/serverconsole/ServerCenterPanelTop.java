package serverconsole;

import hlong.Server.Server;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

public class ServerCenterPanelTop extends javax.swing.JPanel{

	private static final long serialVersionUID = 1L;
	private ServerBottomPanel server_bottom;
	
	public ServerCenterPanelTop(ServerBottomPanel server_bottom) {
		this.server_bottom=server_bottom;
	}
	public void showServerCenterPanelTop(){
		this.setLayout(new BorderLayout());
		/******************左边部分******************/
		javax.swing.JPanel centerpanel_top_left=new javax.swing.JPanel();
		final javax.swing.JButton top_left=new javax.swing.JButton("启动服务器");
		top_left.setActionCommand("startserver");
		centerpanel_top_left.add(top_left);
		this.add(centerpanel_top_left,BorderLayout.WEST);
		
		/******************右边部分******************/
		javax.swing.JPanel centerpanel_top_right=new javax.swing.JPanel();
		javax.swing.JLabel right_label=new javax.swing.JLabel("服务器IP：");
		centerpanel_top_right.add(right_label);
		javax.swing.JLabel ip_label=new javax.swing.JLabel(getIpAddress());
		centerpanel_top_right.add(ip_label);
		this.add(centerpanel_top_right,BorderLayout.CENTER);
		
		/****************创建动作监听器****************/
		ActionListener aclis=new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				String command=e.getActionCommand();
				if(command.equals("startserver")){
					//创建服务器，并启动服务器
					Server.getServer();
					System.out.println("服务器启动了！");
					
					top_left.setText("关闭服务器");
					top_left.setActionCommand("stopserver");
					server_bottom.setStartTime();
				}else if(command.equals("stopserver")){
				int t= JOptionPane.showConfirmDialog(new Component(){
				   private static final long serialVersionUID = 1L;},"您要关闭服务器？",
				    "提示消息",JOptionPane.YES_NO_OPTION);
					if(t==0){//如果用户选择了确定
					   top_left.setText("启动服务器");
					   top_left.setActionCommand("startserver");
					}
				}
			}
			
		};
		top_left.addActionListener(aclis);
	}
	//获取本地ip
		private String getIpAddress(){
			   InetAddress address;
			try {
				address = InetAddress.getLocalHost();
				return address.getHostAddress();   
			} catch (UnknownHostException e) {
				e.printStackTrace();
			}   
			return "0.0.0.0";   
		}

}
