package serverconsole;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

public class ServerBottomPanel extends javax.swing.JPanel{

	private static final long serialVersionUID = 1L;
	private java.text.SimpleDateFormat dataFormat = new java.text.SimpleDateFormat(
			"yyyy/MM/dd HH:mm:ss");
	/**
	 * 初始化底部的界面
	 */
	public void showServerBottomPanel(){
		this.setLayout(new BorderLayout());
		javax.swing.border.LineBorder tool_border=new javax.swing.border.LineBorder(Color.gray,1);
		this.setBorder(tool_border);
		/****************用于显示在线用户的面板*****************/
		javax.swing.JPanel left_panel=new javax.swing.JPanel();
		left_panel.setLayout(new FlowLayout(FlowLayout.LEFT));
		javax.swing.JLabel usr_label=new javax.swing.JLabel(" 在线用户:");
		left_panel.add(usr_label);
		javax.swing.JLabel num=new javax.swing.JLabel("0");
		left_panel.add(num);
		this.add(left_panel,BorderLayout.WEST);
		
		/*****************用于显示时间的面板*******************/
		javax.swing.JPanel right_panel=new javax.swing.JPanel();
		right_panel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		javax.swing.JLabel time_label=new javax.swing.JLabel(" 系统时间:");
		right_panel.add(time_label);
		final javax.swing.JLabel time=new javax.swing.JLabel("0");
		right_panel.add(time);
		this.add(right_panel,BorderLayout.EAST);
		Runnable rub=new Runnable(){
			@Override
			public void run() {
				while(true){
				 try{
					time.setText(getCurrentTime());
					Thread.sleep(1000);//休眠一秒
				  }catch(Exception e){
					e.printStackTrace();
				 }
				}
			}			
		};
		//创建线程，并且启动它
		Thread thread=new Thread(rub);
		thread.start();
	}
	/**
	 * 获取系统的当前时间
	 * @return 以字符串的形式返回当前系统的时间
	 */
    public String getCurrentTime(){
    	java.util.Date date=new java.util.Date();
    	String time= dataFormat.format(date);
		return time;
    }
}
