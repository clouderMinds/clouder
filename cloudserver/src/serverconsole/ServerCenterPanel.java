package serverconsole;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class ServerCenterPanel extends javax.swing.JPanel{

	private static final long serialVersionUID = 1L;
	private ServerBottomPanel server_bottom;
	private JFrame server_ui;
	
	public ServerCenterPanel(ServerBottomPanel server_bottom, JFrame server_ui) {
		this.server_bottom=server_bottom;
		this.server_ui=server_ui;
	}
	/**
	 * 初始化中间部分的界面
	 */
	public void showServerCenterPanel(){
		this.setLayout(new BorderLayout());
		
		/****************中间面板的上部分******************/
		ServerCenterPanelTop centerpanel_top=new ServerCenterPanelTop(server_bottom);
		centerpanel_top.showServerCenterPanelTop();
		this.add(centerpanel_top,BorderLayout.NORTH);
		
		/***************中间面板的右边部分*****************/
		ServerCenterPanelRight rightpanel=new ServerCenterPanelRight();
		this.add(rightpanel,BorderLayout.CENTER);
		
		/***************中间面板的左边部分*****************/
		ServerCenterPanelLeft liftpanel = new ServerCenterPanelLeft(rightpanel,server_ui,server_bottom);
		liftpanel.showServerCenterPanelLeft();
		this.add(liftpanel,BorderLayout.WEST);
		
		
	}
}
