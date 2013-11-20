package democonsole;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Borderone extends JFrame{

	/**
	 * 第一个界面
	 */
	private static final long serialVersionUID = 1L;
	public void showUI(){
		this.setResizable(true);//窗体可以任意更改大小
		this.setUndecorated(true);//去除窗体的边框
		this.setLocationRelativeTo(null);//将窗体居中显示
	         /******背景********/
		ImageIcon backimage=new ImageIcon(this.getClass().getResource("images/back1.jpg"));
		JLabel imglabel=new JLabel(backimage);
		imglabel.setBounds(0, 0, backimage.getIconWidth(),backimage.getIconHeight());
		this.getLayeredPane().add(imglabel,new Integer(Integer.MIN_VALUE));
		Container cp=this.getContentPane();
		cp.setLayout(new BorderLayout());//设置为边框布局
		
		/**窗体的上部分，用来显示最淡化，最小化和关闭按钮**/
		final SystemTrayFrame systray=new SystemTrayFrame(this);//建立后台托管对象
		systray.showSystemTray();
		LoginTopPanel jpn_menu=new LoginTopPanel(this,systray);
		jpn_menu.showLoginTopUI();//调用初始化窗体北部分的方法		
	    cp.add(jpn_menu,BorderLayout.NORTH);
	    /**窗体右部分，点击箭头转到下一界面**/
		Arrow arrow =new Arrow();//窗体右边箭头,为按钮
		arrow.showUI();
	    cp.add(arrow,BorderLayout.EAST);
	    /**窗体下部分，切换界面**/
	    Point point=new Point();
	    point.showUI();
	    cp.add(point,BorderLayout.SOUTH);
	    ((JPanel)cp).setOpaque(false);
		this.setSize(1000, 677);
	    this.setVisible(true);
	}
	public static void main(String[] args) {
		Borderone bone=new Borderone();
		bone.showUI();

	}

}
