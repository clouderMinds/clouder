package files;

import javax.swing.JFrame;

public class DealFiles {

	/**
	 * 这是一个负责上传文件到集群的文件系统和从集群的文件系统下载文件的管理器
	 * 为了增加其操作的方便性，该文件管理器被做成可视化界面
	 * @param args
	 */
	public static void main(String[] args) {
		//创建一个文件管理器对象
		DealFiles dealer=new DealFiles();
		dealer.fileUI();//调用初始化界面的方法

	}
	//初始化界面的方法
	public void fileUI(){
		JFrame file_ui=new JFrame();
		file_ui.setTitle("集群文件管理器V1.0");
		file_ui.setSize(500, 400);
		file_ui.setResizable(true);
		file_ui.setDefaultCloseOperation(3);
		file_ui.setLocationRelativeTo(null);
		
		
		file_ui.setVisible(true);
	}

}
