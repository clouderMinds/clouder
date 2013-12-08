package hxiong.template_fileSys;

import javax.swing.tree.DefaultMutableTreeNode;

/**
 * 一个叶子节点类，给类自定义添加了一个文件类型属性
 * @author 熊天成
 *
 */
public class MyFileNode extends DefaultMutableTreeNode{
	private static final long serialVersionUID = 1L;
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
