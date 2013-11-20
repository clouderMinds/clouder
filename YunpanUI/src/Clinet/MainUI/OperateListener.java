package Clinet.MainUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

public class OperateListener implements ActionListener{
	private JFrame jf;
	private int Count=1;
	
	public  OperateListener(JFrame jf){
		this.jf=jf;
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand()=="upload"){
			JFileChooser filechooser = new JFileChooser();
			filechooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
			filechooser.showOpenDialog(jf);
			String filePath = filechooser.getCurrentDirectory().toString();
			System.out.println("所选上传文件目录路径为:"+filePath);
			String fileName = filechooser.getDescription(filechooser.getSelectedFile());
			System.out.println("所选上传文件名为:"+fileName);
			if(fileName!=null){
				LeftPanel.fileTree.addFile(fileName);
			}
			
		}else if(e.getActionCommand()=="addfiles"){
			LeftPanel.fileTree.addFile("新建文件夹",true);
		}else if(e.getActionCommand()=="download"){
			JFileChooser savefilechooser = new JFileChooser();
			savefilechooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			savefilechooser.showSaveDialog(jf);
			String filePath = savefilechooser.getCurrentDirectory().toString();
			System.out.println("下载的文件保存路径为:"+filePath);
			String getPath = LeftPanel.fileTree.getSelectFilePath();
			System.out.println("所要下载的文件在hdfs上的抽象路径为:"+getPath);
		}
	}

}
