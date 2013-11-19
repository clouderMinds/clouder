package serverconsole;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;

/**
 * 窗体中部面板的右部面板
 * @author hadoop
 *
 */
public class ServerCenterPanelRight extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Border border=BorderFactory.createEtchedBorder(Color.white,Color.darkGray);

	public ServerCenterPanelRight(){
		this.setLayout(new BorderLayout());
		this.setBorder(border);
		this.setPreferredSize(getMaximumSize());
	}
}
