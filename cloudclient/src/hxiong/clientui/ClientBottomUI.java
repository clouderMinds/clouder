package hxiong.clientui;

import hxiong.config.ClientuiConfig;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 客户端窗体的下部分，主要用来显示一些提示信息
 * 以及显示中间部分面板进行切换的按钮
 * @author 熊天成
 *
 */
public class ClientBottomUI extends javax.swing.JPanel{

	private static final long serialVersionUID = 1L;
	private java.text.SimpleDateFormat dataFormat = new java.text.SimpleDateFormat(
			"yyyy/MM/dd HH:mm:ss");
	private FirstView fview;//第一块面板视图
	private SecondView sview;//
	private ThirdView tview;//
    //重载构造方法
	public ClientBottomUI(FirstView fview,SecondView sview,ThirdView tview){
		this.fview=fview;
		this.sview=sview;
		this.tview=tview;
		this.showClientBottomUI();
	}
	/**
	 * 具体实现在下部分界面添加其他组件和功能的方法
	 */
	private void showClientBottomUI(){
		this.setPreferredSize(new Dimension(ClientuiConfig.clientWidth,ClientuiConfig.clientBottomHeight));//设置大小
		this.setLayout(new BorderLayout());//设置为边框布局
		this.setBackground(Color.BLUE);//设置背景
		//左边部分的标签，显示提示信息
		javax.swing.JPanel left_panel=new javax.swing.JPanel();
		left_panel.setLayout(new FlowLayout(FlowLayout.LEFT));//流式布局，居左对齐
		left_panel.setOpaque(false);//设置透明
		left_panel.setPreferredSize(new Dimension(180,30));
		javax.swing.JLabel usr_label=new javax.swing.JLabel(" 首页");
		left_panel.add(usr_label);
		this.add(left_panel,BorderLayout.WEST);
		//使用匿名内部类创建一个动作监听器
		ActionListener aclis=new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				String command=e.getActionCommand();//获得
				if(command.equals("firstview")){
					swichView(1);
				}else if(command.equals("secondtview")){
					swichView(2);
				}else if(command.equals("thirdview")){
					swichView(3);
				}
				
			}
			
		};
		
		//中间部分的面板，用来显示三个按钮
		javax.swing.JPanel center_panel=new javax.swing.JPanel();
		center_panel.setLayout(new FlowLayout(FlowLayout.CENTER));//流式布局，并且居中
		center_panel.setOpaque(false);//设置透明
		javax.swing.JButton first=new javax.swing.JButton("11");
		first.setActionCommand("firstview");
		center_panel.add(first);
		
		javax.swing.JButton second=new javax.swing.JButton("22");
		second.setActionCommand("secondtview");
		center_panel.add(second);
		
		javax.swing.JButton third=new javax.swing.JButton("33");
		third.setActionCommand("thirdview");
		center_panel.add(third);
		//
		first.addActionListener(aclis);
		second.addActionListener(aclis);
		third.addActionListener(aclis);
		
		this.add(center_panel,BorderLayout.CENTER);
		
		//右边部分，用来显示时间
		javax.swing.JPanel right_panel=new javax.swing.JPanel();
		right_panel.setLayout(new FlowLayout(FlowLayout.RIGHT));//流式布局，居you对齐
		right_panel.setPreferredSize(new Dimension(180,30));
		right_panel.setOpaque(false);//设置透明
		right_panel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		//javax.swing.JLabel time_label=new javax.swing.JLabel("当前时间:");
		//right_panel.add(time_label);
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
	 * 转换视图，
	 * @param witch 
	 */
	public void swichView(int which){
	   //如果界面不没有在滑动才可以修改
	   if(!ClientuiConfig.isScroll&&ClientuiConfig.viewID!=which){
		   ClientuiConfig.isScroll=true;//开始滑动界面
		   ClientuiConfig.whichView=which;
		   //调用滑动线程
		   Runnable rub=new Runnable(){
				@Override
				public void run() {
					int dx=0;//在水平方向上的增量，也就是步长
					int x=0;
					if(ClientuiConfig.viewID==1&&ClientuiConfig.whichView==2){//重第一块互动到第二块
						dx=-(ClientuiConfig.panelWidth/50);
					}else if(ClientuiConfig.viewID==1&&ClientuiConfig.whichView==3){//重第一块互动到第三块
						dx=-(ClientuiConfig.panelWidth*2/50);
					}else if(ClientuiConfig.viewID==2&&ClientuiConfig.whichView==1){//重第二块互动到第一块
						x=-ClientuiConfig.panelWidth;
						dx=ClientuiConfig.panelWidth/50;
					}else if(ClientuiConfig.viewID==2&&ClientuiConfig.whichView==3){//重第二块互动到第三块
						x=-ClientuiConfig.panelWidth;
						dx=-(ClientuiConfig.panelWidth/50);
					}else if(ClientuiConfig.viewID==3&&ClientuiConfig.whichView==1){//重第三块互动到第一块
						x=-2*ClientuiConfig.panelWidth;
						dx=2*ClientuiConfig.panelWidth/50;
					}else if(ClientuiConfig.viewID==3&&ClientuiConfig.whichView==2){//重第三块互动到第二块
						x=-2*ClientuiConfig.panelWidth;
						dx=ClientuiConfig.panelWidth/50;
					}
					//按照指定的步长滑动
					for(int i=0;i<50;i++){
					    x+=dx;
					    fview.setBounds(x, 0, ClientuiConfig.panelWidth,ClientuiConfig.panelHeight);
						sview.setBounds(ClientuiConfig.panelWidth-x, 0,ClientuiConfig.panelWidth,ClientuiConfig.panelHeight);
						tview.setBounds(ClientuiConfig.panelWidth*2-x, 0, ClientuiConfig.panelWidth,ClientuiConfig.panelHeight);
					    try{
					    	Thread.sleep(1);//适当休眠
					    }catch(Exception e){
					    	e.printStackTrace();
					    }
					}
					//显示最终滑动的面板
					finalShow(ClientuiConfig.whichView);
				}			
			};
			//创建线程，并且启动它
			Thread thread=new Thread(rub);
			thread.start();
		 }		
	}
	/**
	 * 滑动结束后要显示固定的面板
	 * @param which
	 */
	private void finalShow(int which){
		if(which==1){
			fview.setBounds(0, 0, ClientuiConfig.panelWidth,ClientuiConfig.panelHeight);
			sview.setBounds(ClientuiConfig.panelWidth, 0,ClientuiConfig.panelWidth,ClientuiConfig.panelHeight);
			tview.setBounds(ClientuiConfig.panelWidth*2, 0, ClientuiConfig.panelWidth,ClientuiConfig.panelHeight);
		}else if(which==2){
			fview.setBounds(-ClientuiConfig.panelWidth, 0, ClientuiConfig.panelWidth,ClientuiConfig.panelHeight);
			sview.setBounds(0, 0,ClientuiConfig.panelWidth,ClientuiConfig.panelHeight);
			tview.setBounds(ClientuiConfig.panelWidth, 0, ClientuiConfig.panelWidth,ClientuiConfig.panelHeight);
		}else if(which==3){
			fview.setBounds(-ClientuiConfig.panelWidth*2, 0, ClientuiConfig.panelWidth,ClientuiConfig.panelHeight);
			sview.setBounds(-ClientuiConfig.panelWidth, 0,ClientuiConfig.panelWidth,ClientuiConfig.panelHeight);
			tview.setBounds(0, 0, ClientuiConfig.panelWidth,ClientuiConfig.panelHeight);
		}
		ClientuiConfig.viewID=which;//设置显示的面板
		ClientuiConfig.isScroll=false;//界面滑动结束
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
