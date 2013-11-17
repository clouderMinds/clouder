package serverconsole;

import java.awt.BorderLayout;

public class ServerCenterPanel extends javax.swing.JPanel{

	private static final long serialVersionUID = 1L;
	/**
	 * 初始化中间部分的界面
	 */
	public void showServerCenterPanel(){
		this.setLayout(new BorderLayout());
		
	 /****************中间面板的上部分******************/
		ServerCenterPanelTop centerpanel_top=new ServerCenterPanelTop();
		centerpanel_top.showServerCenterPanelTop();
		this.add(centerpanel_top,BorderLayout.NORTH);
		
	 /***************中间面板的左边部分*****************/
		
	
	 /***************中间面板的右边部分*****************/
		
		
	}
}
