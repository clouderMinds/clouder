package serverconsole;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
/**
 * 显示系统性能的面板，采用单例模式
 * @author hadoop
 *
 */
public class SysOptionPanel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static  SysOptionPanel sop;
	private Border border=BorderFactory.createEtchedBorder(Color.white,Color.darkGray);
	/**
	 * 私有的构造函数
	 */
	private SysOptionPanel(){};
	/**
	 * 得到单例对象的静态方法
	 * @return
	 */
	public static SysOptionPanel getSysOptionPanel(){
		
		if (sop==null){
			sop=new SysOptionPanel();
			sop.setPreferredSize(sop.getMaximumSize());
			sop.setLayout(new BorderLayout());
		}
		return sop;
	}
	/**
	 * 初始化该面板的方法
	 */
	public void showSysOptionPanel(ServerBottomPanel server_bottom){
		/*-------服务器常规设在的面板-------*/
		JPanel servet_normal=noemalSet(server_bottom);
		System.out.println("here     ========");
		sop.add(servet_normal,BorderLayout.CENTER);
	}
	/**
	 * 初始化服务器常规面板
	 * @return
	 */
	private JPanel noemalSet(ServerBottomPanel server_bottom) {
		JPanel servet_normal=new JPanel();
		servet_normal.setBackground(Color.GREEN);
		servet_normal.setLayout(new BorderLayout());
		servet_normal.setPreferredSize(getMaximumSize());
		//常规面板上部的面板
		JPanel normal_on=new JPanel();
		nomal_on_init(normal_on,server_bottom);
		
		servet_normal.add(normal_on,BorderLayout.CENTER);

		return servet_normal;
	}
	/**
	 * 初始化常规设置上部的面板
	 * @param normal_on 
	 */
	private void nomal_on_init(JPanel normal_on,final ServerBottomPanel server_bottom){
		normal_on.setBorder(border);
		normal_on.setLayout(null);
		normal_on.setLayout(new BorderLayout());
		
		
		/*-----显示常规设置的面板-----*/
		JPanel panel_normalset=new JPanel();
		panel_normalset.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel lab_show=new JLabel("常规设置");
		panel_normalset.add(lab_show);
		/**---显示常规系统信息的面板----***/
		JPanel panel_all_on=new JPanel();
		panel_all_on.setLayout(new BorderLayout());
		
		/*-----显示常规设置主要信息的面板-----*/
		JPanel normal_on_show=new JPanel();
		normal_on_show.setLayout(new GridLayout(3,1,1,1));
		
		//显示是否绑定IP的小面板
		JPanel panel_fixIp=new JPanel();
		panel_fixIp.setLayout(new FlowLayout(FlowLayout.LEFT));
		JCheckBox checkbox_fixIp=new JCheckBox("绑定IP");
		
		panel_fixIp.add(checkbox_fixIp);
		
		//显示IP地址的小面板
		JPanel panel_address=new JPanel();
		panel_address.setLayout(new FlowLayout(FlowLayout.LEFT));
		panel_address.add(new JLabel("IP地址："));
		JTextField jtf_add=new JTextField("localhost");
		jtf_add.setPreferredSize(new Dimension(150,20));
		jtf_add.setEditable(false);
		
		panel_address.add(jtf_add);
		//显示端口号的小面板
		JPanel panel_port=new JPanel();
		panel_port.add(new JLabel("端口号："));
		panel_port.setLayout(new FlowLayout(FlowLayout.LEFT));
		JTextField jtf_port=new JTextField();
		jtf_port.setPreferredSize(new Dimension(80,20));
		panel_port.add(jtf_port);
		
		
		normal_on_show.add(panel_fixIp);
		normal_on_show.add(panel_address);
		normal_on_show.add(panel_port);
		
		/*-----显示常规设置主要信息的面板_end-----*/
		/*---控制服务器启动和停止的面板---*/
		JPanel panel_serOO=new JPanel();
		panel_serOO.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		//启动和停止服务器的按钮
		JButton button_ser=new JButton("重启服务器");
		button_ser.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				server_bottom.setStartTime();
			}
		
		});
		
		panel_serOO.add(button_ser);
		/*---控制服务器启动和停止的面板_end---*/
		panel_all_on.add(normal_on_show,BorderLayout.CENTER);
		panel_all_on.add(panel_serOO,BorderLayout.EAST);
		/**---显示常规系统信息的面板_end----***/
		
		normal_on.add(panel_normalset, BorderLayout.NORTH);
		normal_on.add(panel_all_on,BorderLayout.CENTER);
	}
}
