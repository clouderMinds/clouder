package democonsole;

import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class Point extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ImageIcon pointin=new ImageIcon(this.getClass().getResource("images/arrow.jpg"));
	private ImageIcon pointout=new ImageIcon(this.getClass().getResource("images/app_login.png"));
	public void showUI(){
		this.setLayout(new FlowLayout());
		this.setOpaque(false);
		JButton jbu1=new JButton(pointout);//指向第一个界面
		JButton jbu2=new JButton(pointout);//指向第二个界面
		JButton jbu3=new JButton(pointout);//指向第三个界面
		jbu1.setOpaque(false);//按钮背景设置为透明
		jbu2.setOpaque(false);
		jbu3.setOpaque(false);
		jbu1.setBorder(null);
		jbu2.setBorder(null);
		jbu3.setBorder(null);
		jbu1.addMouseListener(mslis);//添加鼠标监听器
		jbu2.addMouseListener(mslis);
		jbu3.addMouseListener(mslis);
		this.add(jbu1);
		this.add(jbu2);
		this.add(jbu3);
		this.setVisible(true);
	}
	MouseAdapter mslis=new MouseAdapter(){
		public void mouseEntered(MouseEvent e){
			JButton jpoint=(JButton)e.getSource();
			jpoint.setIcon(pointin);
		}
		public void mouseExited(MouseEvent e) {
			JButton jpoint=(JButton)e.getSource();
			jpoint.setIcon(pointout);
		}
	};

	

}
