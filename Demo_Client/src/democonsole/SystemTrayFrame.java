package democonsole;

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
 * 后台托管对象
 * @author hadoop
 *
 */
public class SystemTrayFrame {
   private JFrame frame;//托管的窗体
   private SystemTray sysTray=null;//系统托管对象
   private TrayIcon trayIcon=null;//托管的一个对象
   
   //重载构造方法
   public SystemTrayFrame(JFrame frame){
	   this.frame=frame;
   }
   /**
    * 实现托管的方法
    */
   public boolean showSystemTray(){
	   if(SystemTray.isSupported()){//判断系统是否支持托管
		  
		   sysTray=SystemTray.getSystemTray();//获得系统的托盘管理对象
		   //����һ��ͼƬ����
		   ImageIcon image=new ImageIcon(this.getClass().getResource("images/systracker.png"));
		   PopupMenu popumenu=new PopupMenu();//����һ��������������Ӳ˵�ѡ��
		   MenuItem exitItem = new MenuItem("退出");

			MenuItem menuItemb = new MenuItem("设置");

			MenuItem menuItemc = new MenuItem("关于");

			MenuItem menuItemd = new MenuItem("帮助");
			
			exitItem.addActionListener(new ActionListener() {//给按钮添加监听器

				public void actionPerformed(ActionEvent e) {
					try {
						removeTrayIcon();//移除托盘
						System.exit(0);

					} catch (Exception ex) {

						ex.printStackTrace();

					}
				}
			});
			//添加菜单项
			popumenu.add(menuItemb);

			popumenu.add(menuItemc);

			popumenu.add(menuItemd);

			popumenu.add(exitItem);
			
			trayIcon=new TrayIcon(image.getImage(),"蓝云",popumenu);//创建
			trayIcon.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					if (e.getClickCount() == 2) {
						if(frame.isVisible()==false){//判断窗体是否可见
								frame.setVisible(true);
							 }
						}
					 }
					});
					try {
					sysTray.add(trayIcon); // 添加一个后台图标
					} catch (AWTException e) {
						System.err.println(e);
					 }

				} else {
					System.out.println("您的系统不支持托盘！");
			 } 
	   return false;
   }
   /**
    * 一个自定义的移除后台图标对象的方法
    * @return 如果成功，返回true
    */
   public boolean removeTrayIcon(){
	   if(sysTray!=null&&trayIcon!=null){
		   sysTray.remove(trayIcon);
		   return true;
	   }else{
	    return false;
	   }
   }
}
