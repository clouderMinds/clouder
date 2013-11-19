package Clinet.MainUI;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;

public class MainDemo extends JFrame{
	
	public void showUI(){
		this.setSize(894, 650);
		this.setResizable(false);
		this.setLayout(new BorderLayout());
		this.setUndecorated(true);
		this.setLocationRelativeTo(null);
		this.getContentPane().setBackground(Color.LIGHT_GRAY);
		
		TopPanel topPanel = new TopPanel(this);
		topPanel.showTopUI();
		this.add(topPanel,BorderLayout.NORTH);
		
		MainPanel mainPanel = new MainPanel();
		mainPanel.showUI();
		this.add(mainPanel,BorderLayout.CENTER);
		
		this.setVisible(true);
	}
	
	
	
	public static void main(String[] args){
		new MainDemo().showUI();
	}
	
}
