package serverconsole;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

public class SysUserPanel  extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static SysUserPanel sup;
	private static JFrame server_ui;
	/**
	 * 定义似有的构造函数
	 */
	private SysUserPanel(){};
	/**
	 * 定义静态的单例方法
	 * @param server_ui 
	 * @return
	 */
	public static SysUserPanel getSysUserPanel(JFrame server_ui){
		SysUserPanel.server_ui=server_ui;
		if(sup == null){
			sup = new SysUserPanel();
			sup.setLayout(new BorderLayout());
			return sup;
		}
		return sup;
		
	}
	/**
	 * 初始化用户管理面板的方法
	 */
	public void showSysUserPanel() {
//		JTable table_userIF = table_init();
		JScrollPane jscrollpane=table_init();

		sup.add(jscrollpane,BorderLayout.CENTER);
	}
	/**
	 * 初始化Table表格的方法
	 * @return
	 */
	private JScrollPane table_init() {
		String[] table_colum={"用户名","级别","登陆时间","文件数量","活跃度","编辑权限","操作文件","禁止登陆"};//表格各列头显示的内容
		Object[][] data = {
			    {"柳宗元", "9","2013-9-1", new Integer(50), new Integer(6),new Boolean(true),new Boolean(true),new Boolean(false)}
			};
		MyDefaultTableModel tmodel = new MyDefaultTableModel(data, table_colum);
		final JTable table_userIF = new JTable(tmodel);
		
		
		
		
		/* 添加监听的方法******/
		
		table_userIF.getModel().addTableModelListener(new TableModelListener(){

			@Override
			public void tableChanged(TableModelEvent e) {
				// TODO Auto-generated method stub
				int row = e.getFirstRow();
		        int column = e.getColumn();
		        TableModel model = (TableModel)e.getSource();
		        String columnName = model.getColumnName(column);
		        Object data = model.getValueAt(row, column);
		        
		        System.out.println(row+"   "+column);
			}
			
		});
		
		
		final JPopupMenu popupMenu = new JPopupMenu();
		JMenuItem deleteRow = new JMenuItem("delete");
		popupMenu.add(deleteRow);
		deleteRow.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent e){
				System.out.println(" 到了删除列");
			}
		});
		table_userIF.add(popupMenu);
		
		table_userIF.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
			// 右键事件
			if(MouseEvent.BUTTON3 == e.getButton()){
			popupMenu.show(table_userIF, e.getPoint().x, e.getPoint().y);
			}
			//双击事件
			if(e.getClickCount()==2){
				server_ui.setEnabled(false);
				JFrame user_frame = new JFrame();
				user_frame.setTitle("用户信息");
				user_frame.setSize(new Dimension(500,600));
				user_frame.setLocationRelativeTo(null);
				user_frame.setDefaultCloseOperation(2);
				/*--- 装载子容器的面板 ---*/
				JPanel show_panel=new JPanel();
				show_panel.setLayout(new FlowLayout(FlowLayout.LEFT));
				//显示用户名
				JLabel username_lab=new JLabel("用户名：");
				JTextField username_text=new JTextField();
				username_text.setPreferredSize(new Dimension(50,10));
				
				JLabel userID_lab=new JLabel("帐号：");
				JTextField userID_text=new JTextField();
				username_text.setPreferredSize(new Dimension(50,10));
				
				JLabel userpass_lab=new JLabel("密码：");
				JTextField userpass_text=new JTextField();
				username_text.setPreferredSize(new Dimension(50,10));
				
				show_panel.add(username_lab);
				show_panel.add(username_text);
				show_panel.add(userID_lab);
				show_panel.add(userID_text);
				show_panel.add(userpass_lab);
				show_panel.add(userpass_text);
				
				user_frame.add(show_panel);
				user_frame.setVisible(true);
				
				server_ui.setEnabled(true);
			}
			
			}
		});
		
		
		
		JScrollPane jscrollpane = new JScrollPane(table_userIF); //将表格加个滚动条 
		
		
		
		return jscrollpane;
	}
}
