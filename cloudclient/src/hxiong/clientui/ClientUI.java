package hxiong.clientui;

import hxiong.config.ClientuiConfig;
import hxiong.config.UserConfig;

import java.awt.Color;

/**
 * 这是客户端的主程序类
 * @author 熊天成
 *
 */
public class ClientUI {

	/**主程序入口
	 * @param args
	 */
	public static void main(String[] args) {
		// 创建对象并且调用方法
		ClientUI client=new ClientUI();
		client.showClientUI();
        
	} 
	/**
	 * 显示用户界面的方法，该方法包含创建用户界面和显示用户界面
	 */
	public void showClientUI(){
		javax.swing.JFrame client_ui=new javax.swing.JFrame();
		client_ui.setSize(ClientuiConfig.clientWidth, ClientuiConfig.clientHeight);
		client_ui.setLocationRelativeTo(null);
		client_ui.setUndecorated(true);
		client_ui.setResizable(true);
		client_ui.setLayout(null);//将窗体设置为空布局setBorder
		
		/******************窗体上部分的面板******************/
		ClientTopUI client_ui_top=new ClientTopUI(client_ui);
		client_ui.add(client_ui_top);
		
		/**************s*****中间部分的面板**********************/
		javax.swing.JPanel client_ui_center=new javax.swing.JPanel();
		client_ui_center.setBounds(0, ClientuiConfig.clientTopHeight,ClientuiConfig.panelWidth*ClientuiConfig.panelNum,ClientuiConfig.panelHeight);
		client_ui_center.setLayout(null);
		client_ui_center.setBackground(Color.black);
		/*****************添加三个面板*******************/
		
		SecondView sview=new SecondView(client_ui_center);//第二个面板		
		ThirdView tview=new ThirdView();//第三个面板
		FirstView fview=new FirstView(tview);//第一个面板
		client_ui_center.add(fview);
		client_ui_center.add(sview);
		client_ui_center.add(tview);

		client_ui.add(client_ui_center);
		
		/*******************窗体的下部分*******************/
		ClientBottomUI client_ui_bottom=new ClientBottomUI(client_ui_center);
		client_ui.add(client_ui_bottom);
		
		client_ui.setVisible(true);
		
		if(UserConfig.isSignIn){//如果用户选择了自动登录
			fview.signIn();
		}
	}
}
