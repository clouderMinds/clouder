package serverconsole;

import javax.swing.table.DefaultTableModel;

public class MyDefaultTableModel extends DefaultTableModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 构造函数
	 * @param data
	 * @param table_colum
	 */
	public MyDefaultTableModel(Object[][] data, String[] table_colum) {
		// TODO Auto-generated constructor stub
		super(data, table_colum);
	}


	/**
	 * 返回某个单元格中的内容
	 */
	public Class<?> getColumnClass(int c) {
		return getValueAt(0, c).getClass();
	}
	/**
	 * 设置某些列可编辑
	 */
	public boolean isCellEditable(int rowindex,int colindex){
		
		if(colindex>=5 && colindex<=7){
			return true;
		}
		
		return false;
		   
		}
}
