package hxiong.template_fileSys;

import java.awt.Component;

import javax.swing.ImageIcon;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeCellRenderer;

public class MyRenderer extends DefaultTreeCellRenderer{
	private static final long serialVersionUID = 1L;
	private ImageIcon img_file=new ImageIcon(this.getClass().getResource("images/folder_close.png"));
	private boolean isFolder = false;
	
	public void setFolder(boolean f){
		this.isFolder = f;
	}
	
	
	public Component getTreeCellRendererComponent(JTree tree, Object value,boolean sel,boolean expanded,boolean leaf,
            int row,
            boolean hasFocus) {

    super.getTreeCellRendererComponent(tree, value, sel,expanded, leaf, row,hasFocus);
    if(leaf&&isFolder(value)){
    	setIcon(img_file);
    }
    
	return this;
    }
	
	public boolean isFolder(Object value){
		MyFileNode node = (MyFileNode)value ;
	    if(node.getType()==(byte)1){
	    	return true;
	    }
		return false;
	}
	
	
}