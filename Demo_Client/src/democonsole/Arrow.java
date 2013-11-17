package democonsole;

import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class Arrow extends JPanel {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private ImageIcon arrowin=new ImageIcon(this.getClass().getResource("images/arrow.jpg"));
    private ImageIcon arrowout=new ImageIcon(this.getClass().getResource("images/app_login.png"));
    
	public void showUI(){
        this.setLayout(new FlowLayout());
	    this.setOpaque(false);//将内容面板设为透明
		JButton jbutton=new JButton(arrowout);
        jbutton.setOpaque(false);//将按钮设置为透明
        jbutton.setBorder(null);
        jbutton.setContentAreaFilled(false);
        jbutton.addMouseListener(mslistener);//给按钮添加监听器
        this.add(jbutton);

	}
	MouseAdapter mslistener=new MouseAdapter(){
		//当鼠标放在按钮上时
		public void mouseEntered(MouseEvent e) {
            JButton jarrow=(JButton)e.getSource();
            jarrow.setIcon(arrowin);
     System.out.println("aaaaa");
     
	
}
//当鼠标离开按钮时
public void mouseExited(MouseEvent e) {
     JButton jarrow=(JButton)e.getSource();
     jarrow.setIcon(arrowout);
    
    System.out.println("bbbbbb");
	
	
}
	};
	
		
	}
	
	
   


