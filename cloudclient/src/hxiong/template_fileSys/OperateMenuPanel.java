package hxiong.template_fileSys;

import hlong.Client.Client;
import hlong.Client.UIMessageListener;
import hlong.Request.CreateDirRequest;
import hlong.Request.DownloadRequest;
import hlong.Request.PRUploadRequest;
import hlong.Request.UploadRequest;
import hlong.Response.DownloadResponse;
import hlong.Response.PreUploadResponse;
import hlong.Response.UploadResponse;
import hxiong.config.UserConfig;
import hxiong.opreate.Opreater;

import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;

public class OperateMenuPanel extends JPanel implements UIMessageListener{
	private static final long serialVersionUID = 1L;
	JButton btn_upload;
	JButton btn_add;
	JButton btn_download;
	private ImageIcon upload_on=new ImageIcon(this.getClass().getResource("images/upload_on4.png"));
	private ImageIcon upload_out=new ImageIcon(this.getClass().getResource("images/upload_out4.png"));
	private ImageIcon download_on=new ImageIcon(this.getClass().getResource("images/download_on4.png"));
	private ImageIcon download_out=new ImageIcon(this.getClass().getResource("images/download_out4.png"));
	private ImageIcon addfiles_on=new ImageIcon(this.getClass().getResource("images/addfiles_on4.png"));
	private ImageIcon addfiles_out=new ImageIcon(this.getClass().getResource("images/addfiles_out4.png"));
	private ImageIcon more_on=new ImageIcon(this.getClass().getResource("images/more_on4.png"));
	private ImageIcon more_out=new ImageIcon(this.getClass().getResource("images/more_4.png"));
	private ImageIcon bar=new ImageIcon(this.getClass().getResource("images/operatebar.jpg"));
	private CenterPanel centerPanel;
	private LeftPanel leftPanel;
	public OperateMenuPanel(){
		
		showUI();
	}
	//用次方法来设置背景
	protected void paintComponent(Graphics g){
		Image img = bar.getImage();
		g.drawImage(img, 0, 0, bar.getIconWidth(),bar.getIconHeight(),bar.getImageObserver());
	}
	//通过次方法获得对象
	public void setPanel(CenterPanel centerPanel,LeftPanel leftPanel){
		this.centerPanel=centerPanel;
		this.leftPanel=leftPanel;
	}
	private void showUI(){
		this.setSize(900,40);
//		this.setLayout(new GridLayout(1,6,2,2));
		this.setLayout(new FlowLayout(FlowLayout.LEFT));
		btn_upload = new JButton(upload_out);
		btn_upload.setActionCommand("upload");
		btn_upload.setOpaque(false);
		btn_upload.setContentAreaFilled(false);
		btn_upload.setBorder(null);
		this.add(btn_upload);
		
		btn_download = new JButton(download_out);
		btn_download.setActionCommand("download");
		btn_download.setOpaque(false);
		btn_download.setContentAreaFilled(false);
		btn_download.setBorder(null);
		this.add(btn_download);
		
		btn_add = new JButton(addfiles_out);
		btn_add.setActionCommand("addfiles");
		btn_add.setOpaque(false);
		btn_add.setContentAreaFilled(false);
		btn_add.setBorder(null);
		this.add(btn_add);
		
		JButton btn_more = new JButton(more_out);
		btn_more.setActionCommand("more");
		btn_more.setOpaque(false);
		btn_more.setContentAreaFilled(false);
		btn_more.setBorder(null);
		this.add(btn_more);
		

		MouseAdapter mslis=new MouseAdapter(){
			public void mouseEntered(MouseEvent e){
				JButton jbu=(JButton)e.getSource();
				String jbu_act=jbu.getActionCommand();
				if(jbu_act.equals("upload")){
					 jbu.setIcon(upload_on);
	             }else if(jbu_act.equals("download")){
	            	 jbu.setIcon(download_on);
	             }else if(jbu_act.equals("addfiles")){
	            	 jbu.setIcon(addfiles_on);
	             }else if(jbu_act.equals("more")){
	            	 jbu.setIcon(more_on);
	             }
			}
			public void mouseExited(MouseEvent e){
				JButton jbu=(JButton)e.getSource();
				String jbu_act=jbu.getActionCommand();
				if(jbu_act.equals("upload")){
					jbu.setIcon(upload_out);
	              }else if(jbu_act.equals("download")){
	            	jbu.setIcon(download_out); 
	             }else if(jbu_act.equals("addfiles")){
	            	jbu.setIcon(addfiles_out);
	             }else if(jbu_act.equals("more")){
	            	jbu.setIcon(more_out);
	             }
			}
		};
		
		btn_upload.addMouseListener(mslis);
		btn_download.addMouseListener(mslis);
		btn_add.addMouseListener(mslis);
		btn_more.addMouseListener(mslis);
		
		//使用匿名内部类创建监听器对象
		ActionListener olis=new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				if(e.getActionCommand()=="upload"){
					JFileChooser filechooser = new JFileChooser();//创建一个文件选择框
					filechooser.setFileSelectionMode(JFileChooser.FILES_ONLY);//只允许上传文件
					int chose=filechooser.showOpenDialog(OperateMenuPanel.this);//绑定父窗口
					if(chose==JFileChooser.APPROVE_OPTION){//如果用户选择了确定
					   String dirPath = filechooser.getCurrentDirectory().toString();//获得文件夹的路径
					   String fileName = filechooser.getDescription(filechooser.getSelectedFile());//获得文件名
					   String filePath = dirPath+"/"+fileName;//一个完整的文件路径
					   //获取文件的md5值
					   String md5=Opreater.getMD5ByFile(filePath);
					  // System.out.println(filePath+"文件的md5值为："+md5);
					   try{
						   PRUploadRequest prup=new PRUploadRequest(UserConfig.account,"轴向路径",filePath,md5);
						   Client.getClient(OperateMenuPanel.this).sentMsg(prup);//发送请求
					   }catch(Exception ex){
						   
						   ex.printStackTrace();
					   }
					}
				}else if(e.getActionCommand()=="addfiles"){
					//默认使用新建文件夹1 CreateDirRequest
					 try{
						    MyFileNode node=leftPanel.addDirNode("新建文件夹");
						    String absPath=leftPanel.getPathByNode(node);
						    if(absPath!=null){
						       CreateDirRequest dlre=new CreateDirRequest(UserConfig.account,absPath);
							   Client.getClient(OperateMenuPanel.this).sentMsg(dlre);//发送请求
						    }
						    }catch(Exception ex){
							   
							   ex.printStackTrace();
						}
					
				}else if(e.getActionCommand()=="download"){
					MyFileNode node = leftPanel.getCurrentNode();//获得当前选中的文件节点
					if(node.getType()!=(byte)1){//如果是文件,则可以下载
						JFileChooser savefilechooser = new JFileChooser();
						savefilechooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
						int choose=savefilechooser.showSaveDialog(OperateMenuPanel.this);
						if(choose==JFileChooser.APPROVE_OPTION){//如果用户选择了文件夹
						    String Path = leftPanel.getCurrentPath();//获得选中的文件路径
						    String filePath = savefilechooser.getSelectedFile().toString();//保存的本地文件路径
						    try{
						    	DownloadRequest dlre=new DownloadRequest(UserConfig.account,Path,filePath);
								Client.getClient(OperateMenuPanel.this).sentMsg(dlre);//发送请求
							   }catch(Exception ex){
								   
								   ex.printStackTrace();
							   }
						}
					}else{
						javax.swing.JOptionPane.showMessageDialog(OperateMenuPanel.this, "此模式下未能进行文件夹下载，请选择文件");
					}
					
				}
				
			}
			
		};
		//添加监听器
		btn_upload.addActionListener(olis);
		btn_add.addActionListener(olis);
		btn_download.addActionListener(olis);
	}
	@Override //实现接口的方法
	public void onMessageReceived(Object msg) {
		String name = msg.getClass().getSimpleName();
		if(name.equals("PreUploadResponse")){
			PreUploadResponse pr=(PreUploadResponse)msg;
			if(pr.getState()==PreUploadResponse.EXIST){//如果已经上传成功
			
				
			}else{//需要用户上传真实的文件字节流到服务器 UploadRequest
				try{
					   String md5=Opreater.getMD5ByFile(pr.getLocalPaht());
					   UploadRequest prup=new UploadRequest(UserConfig.account,"轴向路径",pr.getLocalPaht(),md5,new byte[4]);
					   Client.getClient(OperateMenuPanel.this).sentMsg(prup);//发送请求
				   }catch(Exception ex){
					   
					   ex.printStackTrace();
				   }
			}
		}else if(name.equals("UploadResponse")){
			UploadResponse pr=(UploadResponse)msg;
			if(pr.getState()==UploadResponse.SUCCESS){//如果文件上传成功
				
			}else{
				
				System.out.println(pr.getLocalPaht());
			}
			
		}else if(name.equals("DownloadResponse")){
			DownloadResponse dlr=(DownloadResponse)msg;
			if(dlr.getState()==DownloadResponse.SUCCESS){//如果文件上传成功
				
			}else{
				
				System.out.println(dlr.getLocalPath());
			}
			
		}
	}
	
	
	
	
}
