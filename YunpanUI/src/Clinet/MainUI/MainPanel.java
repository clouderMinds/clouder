package Clinet.MainUI;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JPanel;

public class MainPanel extends JPanel{
	
	public void showUI(){
		this.setLayout(new BorderLayout());
		FileOperBarPanel filebar = new FileOperBarPanel();
		filebar.showUI();
		this.add(filebar,BorderLayout.NORTH);
		  
		LeftPanel leftPanel = new LeftPanel();
		leftPanel.showUI();
		this.add(leftPanel,BorderLayout.WEST);
		
		CenterPanel centerPanel = new CenterPanel();
		centerPanel.showUI();
		this.add(centerPanel,BorderLayout.CENTER);
			
	}

}
