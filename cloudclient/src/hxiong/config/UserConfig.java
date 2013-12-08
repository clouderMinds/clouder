package hxiong.config;

/**
 * 一个保存用户的相关信息，比如用户的帐号，登录状态等信息
 * @author 熊天成
 *
 */
public class UserConfig {
    
	//用户的信息
	public static String account="";//用户的帐号
	public static String name="unknow";//用户的昵称
	public static String level="0";//用户的等级
	public static int message=0;//有多少个系统消息问处理
	public static String space="1T";//用户的网盘空间大小
	public static String spend="0M";//用户已经使用的空间大小
	public static boolean isSignIn=false;//用户是否启动自动登录
	//表示是否连接到服务器成功，如果连接成功，则与网络相关的操作可以使用，否则不可以使用
	public static boolean isConnect=false;
}
