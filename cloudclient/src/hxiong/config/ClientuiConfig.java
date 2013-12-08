package hxiong.config;

/**
 *类似于一个接口类，主要用来配置窗体中间部分面板的起始坐标和大小等信息
 * @author 熊天成
 *
 */
public class ClientuiConfig {

	//窗体的大小
	public static int clientWidth=900,clientHeight=650;
	
	/*****************中间部分面板的配置*******************/
	public static final int panelNum=3;//面板的个数
	//面板的大小
	public static int panelWidth=900,panelHeight=510;
	//上面和下面面板的高度和
	public static final int totalHeight=140;
	public static final int clientTopHeight=100;//主界面上面的面板的高度
	public static final int clientBottomHeight=40;//主界面下面面板的高度
	
	//表示当前显示的是哪个面板视图
	public static int viewID=1;//默认为第一个
	public static int whichView=2;//
	//表示界面是否处于滑动的状态，如果界面处于滑动状态，则不允许再修改界面大小
	public static boolean isScroll=false;
	
}