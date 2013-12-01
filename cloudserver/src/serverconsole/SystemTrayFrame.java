package serverconsole;

import java.awt.AWTException;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

/**
 * 一个用于托管应用程序的类，即当用户点击最小化按钮之后，窗体消失
 * @author Administrator 但是可以通过点击后台图标在重新显示出来
 *
 */
public class SystemTrayFrame {
   private JFrame frame;//要托管的窗体对此
 
   //重载构造方法，获取窗体对象
   public SystemTrayFrame(JFrame frame){
	   this.frame=frame;
   }
   /**
    * 真正实现托管的方法
    * @return
    */
   public boolean showSystemTray(){
	   if(SystemTray.isSupported()){//判断当前系统是否支持托管
		  
		   SystemTray sysTray=SystemTray.getSystemTray();//获得系统托管对象
		   //创建后台托管图标
		   ImageIcon image=new ImageIcon(this.getClass().getResource("images/systracker.png"));
		   PopupMenu popumenu=new PopupMenu();//添加一个菜单面板
		   MenuItem exitItem = new MenuItem("推出");

			MenuItem menuItemb = new MenuItem("关于");

			MenuItem menuItemc = new MenuItem("升级");

			MenuItem menuItemd = new MenuItem("设置");
			
			exitItem.addActionListener(new ActionListener() {//给按钮添加监听器

				public void actionPerformed(ActionEvent e) {
					try {
						System.exit(0);

					} catch (Exception ex) {

						ex.printStackTrace();

					}
				}
			});
			//将菜单按钮添加到菜单面板上
			popumenu.add(menuItemb);

			popumenu.add(menuItemc);

			popumenu.add(menuItemd);

			popumenu.add(exitItem);
			
			TrayIcon trayIcon=new TrayIcon(image.getImage(),"蓝云服务器",popumenu);//获得一个托管对象
			trayIcon.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					if (e.getClickCount() == 2) {//点击两次后才调用
						if(frame.isVisible()==false){//判断窗体是否可见
								frame.setVisible(true);
							 }
						}
					 }
					});
					try {
					sysTray.add(trayIcon); // 添加托管
					} catch (AWTException e) {
						System.err.println(e);
					 }

				} else {
					System.out.println("您的系统不支持托管！");
			 } 
	   return false;
   }
}
