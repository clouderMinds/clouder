package Clinet.MainUI;

import javax.swing.tree.DefaultMutableTreeNode;

public class MyFileNode extends DefaultMutableTreeNode{
	private byte type = (byte)0;
	
	public MyFileNode(String name){
		super(name);
	}
	
	public MyFileNode(Object obj){
		super(obj);
	}
	
	public byte getType(){
		return type;
	}
	
	public void setType(byte type){
		this.type = type;
	}

}
