package hxiong.fsmanager.download;

import hxiong.dbmanager.psql.FiletablePSQL;
import hxiong.fsmanager.file.FsFiles;

/**
 * 这是一个负责将读取集群上的文件，并返回不同形式的文件结果
 * 提类通过多种返回结果，主要有字节数组的形式
 * @author 熊天成
 *
 */
public class Downloader {
    
	/**
	 * 根据文件所有者和问的抽象路径名返回文件的字节数组
	 * @param owner 文件的所有者
	 * @param abspath 文件的抽象路径
	 * @return 如果文件存在返回字节数组，否则返回null
	 */
	public byte[] downloadFile(String owner,String abspath){
		
		String realpath=FiletablePSQL.getRealpaht(owner, abspath);//获取真实的文件路径
		if(realpath!=null){
			return FsFiles.ReadFile(realpath);
		}
		return null;
	}
}
