package hxiong.config;

import hxiong.template_fileSys.MyFileNode;

/**
 * 保存一些操作属性的类
 * @author 熊天成
 *
 */
public class OpreateConfig {
    
	//表示用户的文件视图是否已经显示过了，如果已经显示，则不需要从服务器下载文件视图
	public static boolean isShow=false;
	//每一次选择文件时的文件夹路径，该属性保存的一直是文件夹路径，
	//如果选择的是该文件夹下的一个文件，也会自动的转换为文件夹路径，即文件节点的父节点
	public static String absDirPath="";
	//备选中节点的父节点，也就是文件夹对应的节点
	public static MyFileNode parentNode;
	//表示用户所选中的文件路径，抽象的文件路径，对应上面的文件夹下面的一个被选中的文件路径
	//如果没有选中文件，而是选择文件夹，则保存上一次的文件路径
	public static String absFilePath="";
}
