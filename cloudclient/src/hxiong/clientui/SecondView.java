package hxiong.clientui;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;

import hxiong.config.ClientuiConfig;

/**
 * 窗体中间部分的第二个面板，用来显示用户的一些其他的信息
 * @author 熊天成
 *
 */
public class SecondView extends javax.swing.JPanel{

	private static final long serialVersionUID = 1L;
	private String preCommand="";//之前选择的按钮的属性命令值
	private long preTime=0;//记录之前点击按钮的时间
	private javax.swing.JPanel parentpanel;
	//重载构造方法
	public SecondView(javax.swing.JPanel parentpanel){
		this.parentpanel=parentpanel;
		initSecondView();
	}
	/**
	 * 具体用来添加组件和功能的方法
	 */
	private void initSecondView(){
		this.setLayout(new FlowLayout(FlowLayout.LEFT));
		//设置面板的起始坐标和大小
		this.setBounds(ClientuiConfig.panelWidth, 0,ClientuiConfig.panelWidth,ClientuiConfig.panelHeight);
		ImageIcon normal=new ImageIcon(this.getClass().getResource("images/file.png"));
		ImageIcon press=new ImageIcon(this.getClass().getResource("images/file.png"));
		ImageIcon down=new ImageIcon(this.getClass().getResource("images/file.png"));
		ImageIcon select=new ImageIcon(this.getClass().getResource("images/file.png"));
		
		java.awt.Insets ins=new java.awt.Insets(1,1,1,1);
		javax.swing.JRadioButton first=new javax.swing.JRadioButton();
		first.setActionCommand("ownfile");
		//设置按钮的状态图标
		first.setMargin(ins);
		first.setOpaque(false);
		first.setIcon(normal);
		first.setRolloverIcon(press);
		first.setPressedIcon(down);
		first.setSelectedIcon(select);
		
		this.add(first);
		//使用匿名内部类创建监听器对象
		ActionListener actlis=new ActionListener(){
			//实现监听器的方法
			public void actionPerformed(ActionEvent e) {
                String command=e.getActionCommand();
                if(command.equals(preCommand)&&command.equals("ownfile")
                		&&System.currentTimeMillis()-preTime<500){//如果上一次的点击的按钮与这一次相同
                	scrollView();
                	preCommand="";//重新置零
                }else{
                   preCommand=command;//记录选择的按钮
                }
                preTime=System.currentTimeMillis();
			}			
		};
		first.addActionListener(actlis);
	}
	/**
	 * 
	 */
	private void scrollView(){
		 ClientuiConfig.isScroll=true;//开始滑动界面
		 //调用滑动线程
		 Runnable rub=new Runnable(){
		  @Override
		  public void run() {
		   int x=-ClientuiConfig.panelWidth;
		   int dx=-(ClientuiConfig.panelWidth/40);
		   for(int i=0;i<40;i++){
		     try{
		    	x+=dx;
		    	parentpanel.setBounds(x, ClientuiConfig.clientTopHeight, ClientuiConfig.panelWidth*ClientuiConfig.panelNum, ClientuiConfig.panelHeight);
		    	Thread.sleep(1);//适当休眠
		    }catch(Exception e){
		    	e.printStackTrace();
		    }
		   //显示最终滑动的面板
		   parentpanel.setBounds(-2*ClientuiConfig.panelWidth, ClientuiConfig.clientTopHeight, ClientuiConfig.panelWidth*ClientuiConfig.panelNum, ClientuiConfig.panelHeight);
		   ClientuiConfig.viewID=3;//设置显示的面板
		   ClientuiConfig.isScroll=false;//界面滑动结束
		}
	  }			
   };
    //创建线程，并且启动它
    Thread thread=new Thread(rub);
    thread.start();
    ClientBottomUI.setSelect();
	}
}
