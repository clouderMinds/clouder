package hxiong.clientui;

import hxiong.config.ClientuiConfig;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;


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
		client_ui.setLayout(new BorderLayout());//将窗体设置为边框布局
		
		/******************窗体上部分的面板******************/
		ClientTopUI client_ui_top=new ClientTopUI(client_ui);
		client_ui.add(client_ui_top,BorderLayout.NORTH);
		
		/**************s*****中间部分的面板**********************/
		javax.swing.JPanel client_ui_center=new javax.swing.JPanel();
		client_ui_center.setPreferredSize(new Dimension(ClientuiConfig.panelWidth*ClientuiConfig.panelNum,ClientuiConfig.panelHeight));
		//client_ui_center.setBounds(400, 30,ClientuiConfig.panelWidth*ClientuiConfig.panelNum,ClientuiConfig.panelHeight);
		client_ui_center.setLayout(null);
		client_ui_center.setBackground(Color.black);
		/*****************添加三个面板*******************/
		FirstView fview=new FirstView();//第一个面板
		client_ui_center.add(fview);
		
		SecondView sview=new SecondView();//第二个面板
		client_ui_center.add(sview);
		
		ThirdView tview=new ThirdView();//第三个面板
		client_ui_center.add(tview);

		client_ui.add(client_ui_center,BorderLayout.CENTER);
		
		/*******************窗体的下部分*******************/
		ClientBottomUI client_ui_bottom=new ClientBottomUI(fview,sview,tview);
		client_ui.add(client_ui_bottom,BorderLayout.SOUTH);
		
		client_ui.setVisible(true);
		//添加监听器
		client_ui.addMouseMotionListener(new MouseMotion(client_ui,fview,sview,tview));
	}
	/**
	 * 添加鼠标移动监听器
	 * @author 熊天成
	 *
	 */
	class MouseMotion extends MouseMotionAdapter {
		//要监听的窗口对象
		private javax.swing.JFrame client_ui;
		private FirstView fview;//第一块面板视图
		private SecondView sview;//
		private ThirdView tview;//
		//重载构造方法
		public MouseMotion(javax.swing.JFrame client_ui,FirstView fview,SecondView sview,ThirdView tview){
			this.client_ui=client_ui;
			this.fview=fview;
			this.sview=sview;
			this.tview=tview;
		}
		
        //鼠标按下并且移动时触发
		public void mouseDragged(MouseEvent e) {
		 if(ClientuiConfig.isScroll){//判断面板是否处于滑动状态
			//获得鼠标按下时的坐标
		    int x = e.getX();
			int y = e.getY();
			//获取窗口的宽度
			int width = client_ui.getWidth();
			int height = client_ui.getHeight();
			//获取窗口的左上角坐标
			int nextX = client_ui.getX();
			int nextY = client_ui.getY();
			//修改后的窗口大小
			int nextWidth = client_ui.getWidth();
			int nextHeight = client_ui.getHeight();
			//判断鼠标的移动方向
			if (ClientuiConfig.isTopLeft || ClientuiConfig.isLeft || ClientuiConfig.isBottomLeft) {
					nextX += x;
					nextWidth -= x;
			}
			if (ClientuiConfig.isTopLeft | ClientuiConfig.isTop || ClientuiConfig.isTopRight) {
					nextY += y;
					nextHeight -= y;
			}
			if (ClientuiConfig.isTopRight || ClientuiConfig.isRight || ClientuiConfig.isBottomRight) {
					nextWidth = x;
			}
			if (ClientuiConfig.isBottomLeft || ClientuiConfig.isBottom || ClientuiConfig.isBottomRight) {
					nextHeight = y;
			}
			if (nextWidth <= ClientuiConfig.MIN_WIDTH) {
					nextWidth = ClientuiConfig.MIN_WIDTH;
			    if (ClientuiConfig.isTopLeft || ClientuiConfig.isLeft || ClientuiConfig.isBottomLeft) {
						nextX = client_ui.getX() + width - nextWidth;
			   }

			}
			if (nextHeight <= ClientuiConfig.MIN_HEIGHT) {
					nextHeight = ClientuiConfig.MIN_HEIGHT;
					if (ClientuiConfig.isTop || ClientuiConfig.isTopLeft || ClientuiConfig.isTopRight) {
						nextY = client_ui.getY() + height - nextHeight;
				}
			}
			client_ui.setBounds(nextX, nextY, nextWidth, nextHeight);//重新设置窗口的大小
			//记录窗口的大小
			ClientuiConfig.clientWidth=nextWidth;
			ClientuiConfig.clientHeight=nextHeight;
			//记录面板的大小
			ClientuiConfig.panelWidth=nextWidth;
			ClientuiConfig.panelHeight=nextHeight-ClientuiConfig.totalHeight;
			//重新调整面板的大小
			if(ClientuiConfig.viewID==1){
				this.fview.setBounds(0, 0, ClientuiConfig.panelWidth,ClientuiConfig.panelHeight);
				this.sview.setBounds(ClientuiConfig.panelWidth, 0,ClientuiConfig.panelWidth,ClientuiConfig.panelHeight);
				this.tview.setBounds(ClientuiConfig.panelWidth*2, 0, ClientuiConfig.panelWidth,ClientuiConfig.panelHeight);
			}else if(ClientuiConfig.viewID==2){
				this.fview.setBounds(-ClientuiConfig.panelWidth, 0, ClientuiConfig.panelWidth,ClientuiConfig.panelHeight);
				this.sview.setBounds(0, 0,ClientuiConfig.panelWidth,ClientuiConfig.panelHeight);
				this.tview.setBounds(ClientuiConfig.panelWidth, 0, ClientuiConfig.panelWidth,ClientuiConfig.panelHeight);
			}else if(ClientuiConfig.viewID==3){
				this.fview.setBounds(-ClientuiConfig.panelWidth*2, 0, ClientuiConfig.panelWidth,ClientuiConfig.panelHeight);
				this.sview.setBounds(-ClientuiConfig.panelWidth, 0,ClientuiConfig.panelWidth,ClientuiConfig.panelHeight);
				this.tview.setBounds(0, 0, ClientuiConfig.panelWidth,ClientuiConfig.panelHeight);
			}
		 }
		}
		//鼠标移动时触发，不一定要按下
		public void mouseMoved(MouseEvent e) {
			//获取鼠标的坐标
			int x = e.getX();
			int y = e.getY();
			//获取面板的宽度和高度
			int width =client_ui.getWidth();
			int height = client_ui.getHeight();
			int cursorType = Cursor.DEFAULT_CURSOR;
			//将八个方向的属性修改
			ClientuiConfig.isTop = false;
			ClientuiConfig.isTopRight = false;
			ClientuiConfig.isRight = false;
			ClientuiConfig.isBottomRight = false;
			ClientuiConfig.isBottom = false;
			ClientuiConfig.isBottomLeft = false;
			ClientuiConfig.isLeft = false;
			ClientuiConfig.isTopLeft = false;
            //判断鼠标在窗口的什么范围，并显示对应的图标
			if (x > ClientuiConfig.RESIZE_WIDTH && x < (width - ClientuiConfig.RESIZE_WIDTH)
					&& y <= ClientuiConfig.RESIZE_WIDTH) {//顶部
				ClientuiConfig.isTop = true;
				cursorType = Cursor.N_RESIZE_CURSOR;
			} else if (x > (width - ClientuiConfig.RESIZE_WIDTH) 
					&& y <= ClientuiConfig.RESIZE_WIDTH) {//右上角
				ClientuiConfig.isTopRight = true;
				cursorType = Cursor.NE_RESIZE_CURSOR;
			} else if (x > (width - ClientuiConfig.RESIZE_WIDTH) && y > ClientuiConfig.RESIZE_WIDTH
					&& y < (height - ClientuiConfig.RESIZE_WIDTH)) {//右边
				ClientuiConfig.isRight = true;
				cursorType = Cursor.E_RESIZE_CURSOR;
			} else if (x > (width - ClientuiConfig.RESIZE_WIDTH)
					&& y > (height - ClientuiConfig.RESIZE_WIDTH)) {//右下角
				ClientuiConfig.isBottomRight = true;
				cursorType = Cursor.SE_RESIZE_CURSOR;
			} else if (x > ClientuiConfig.RESIZE_WIDTH && x < (width - ClientuiConfig.RESIZE_WIDTH)
					&& y > (height - ClientuiConfig.RESIZE_WIDTH)) {//底部
				ClientuiConfig.isBottom = true;
				cursorType = Cursor.S_RESIZE_CURSOR;
			} else if (x <= ClientuiConfig.RESIZE_WIDTH 
					&& y > (height - ClientuiConfig.RESIZE_WIDTH)) {//左下角
				ClientuiConfig.isBottomLeft = true;
				cursorType = Cursor.SW_RESIZE_CURSOR;
			} else if (x <= ClientuiConfig.RESIZE_WIDTH && y > ClientuiConfig.RESIZE_WIDTH
					&& y < (height - ClientuiConfig.RESIZE_WIDTH)) {//左边
				ClientuiConfig.isLeft = true;
				cursorType = Cursor.W_RESIZE_CURSOR;

			} else if (x <= ClientuiConfig.RESIZE_WIDTH 
					&& y <= ClientuiConfig.RESIZE_WIDTH) {//左上角
				ClientuiConfig.isTopLeft = true;
				cursorType = Cursor.NW_RESIZE_CURSOR;
			}
			client_ui.setCursor(new Cursor(cursorType));
		}
		
	}
}
