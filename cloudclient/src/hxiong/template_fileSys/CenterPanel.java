package hxiong.template_fileSys;

import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.FlowLayout;

import javax.swing.JPanel;

/**
 * 文件视图的右边部分，只是用来显示文件
 * @author 熊天成
 *
 */
public class CenterPanel extends JPanel{

	private static final long serialVersionUID = 1L;
	//重载构造方法
    public CenterPanel(){
    	
    	this.initCenterPanel();
    }
    //初始化面板和添加功能的方法
	private void initCenterPanel(){
		this.setBackground(Color.lightGray);
		javax.swing.border.LineBorder lineborder=new javax.swing.border.LineBorder(Color.BLACK,1);
		this.setBorder(lineborder);
		this.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
	    this.setLayout(new FlowLayout(FlowLayout.LEADING));
	   
	}
	
}
