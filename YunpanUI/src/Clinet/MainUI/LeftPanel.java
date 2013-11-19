package Clinet.MainUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

public class LeftPanel extends JPanel{
	public static FileTreePanel fileTree;
	
	
	public void showUI(){
		this.setBackground(new Color(211,211,211));
		this.setPreferredSize(new Dimension(200,558));
		this.setLayout(new BorderLayout());
		
		fileTree = new FileTreePanel();
		fileTree.setPreferredSize(new Dimension(200,558));
		this.add(fileTree);
	    
	}
	
}
