package serverconsole;

import java.awt.BorderLayout;

/**
 * 服务器的界面，用于显示服务器
 * 
 * @author hadoop
 * 
 */
public class ServerConsole {

	// 主函数入口
	public static void main(String[] args) {
		// 创建对象，并调用方法
		ServerConsole demo = new ServerConsole();
		demo.showServerConsole();
	}

	/**
	 * 初始化服务器界面的方法
	 */
	public void showServerConsole() {
		// 创建一个窗体对象
		javax.swing.JFrame server_ui = new javax.swing.JFrame();
		server_ui.setSize(600, 460);
		server_ui.setResizable(true);// 可以调整窗体大小
		server_ui.setLocationRelativeTo(null);
		server_ui.setUndecorated(true);// 将系统的修饰部分去掉

		// /////////创建一个后台托管对象，用于托管当前窗体///////////
		// SystemTrayFrame systray=new SystemTrayFrame(server_ui);
		// systray.showSystemTray();

		server_ui.setLayout(new BorderLayout());// 将窗体设置为边框布局
		/**************** 窗体的上部分 *****************/
		ServerTopPanel server_top = new ServerTopPanel(server_ui);
		server_top.showServerTopPanel();
		server_ui.add(server_top, BorderLayout.NORTH);
		
		/**************** 窗体的下部分 *****************/
		ServerBottomPanel server_bottom = new ServerBottomPanel();
		server_bottom.showServerBottomPanel();
		server_ui.add(server_bottom, BorderLayout.SOUTH);
		
		/*************** 窗体的中间部分 ****************/
		ServerCenterPanel server_center = new ServerCenterPanel(server_bottom,server_ui);
		server_center.showServerCenterPanel();
		server_ui.add(server_center, BorderLayout.CENTER);

		server_ui.setVisible(true);

	}
}
