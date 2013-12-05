package hlong.Client;

public interface UIMessageListener {
	
	/**
	 * 监听器需要实现的方法
	 * @param msg	消息对象
	 */
	public void onMessageReceived(Object msg);
}
