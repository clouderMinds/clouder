package hxiong.fsmanager;

import hxiong.dbmanager.psql.FiletablePSQL;
import hxiong.fsmanager.FsFiles;

/**
 * 这是一个负责处理用户上传文件的类
 * 该类提同将新上传的文件记录保存到数据库
 * 同时将上传的文件数据存储到集群的文件系统上
 * @author 熊天成
 *
 */
public class Uoloader {
    
	/**
	 * 主要是通过md5值判断要上传的文件是否已经存在
	 * 如果该文件已经存在，怎将文件记录保存到数据库，同时通过返回值告诉客户端不许要上传文件数据了
	 * @param owner 文件的所有者
	 * @param md5 文件的md5值
	 * @param abspath 文件的抽象路径
	 * @return 如果文件已经存在并将能够执行所有操作，返回true，否则返回false
	 */
	public boolean uploadExitFile(String owner,String md5,String abspath){
		
		String realpath=FiletablePSQL.getRealpaht(md5);
		if(realpath!=null){
			return FiletablePSQL.addFile(owner, md5, abspath, realpath);
		}else{
		    return false;
		}
	}
	/**
	 * 上传一个新的文件到集群上，该文件在集群上还没有
	 * @param owner 文件的所有者
	 * @param md5 文件的md5值
	 * @param abspath 文件的抽象路径
	 * @param fd 组成文件的字节数组
	 * @return 
	 */
	public boolean uploadNewFile(String owner,String md5,String abspath,byte[] fd){
		
		String realpath=""+this.getFtype(abspath);
		if(FsFiles.createFile(realpath, fd)){//如果文件能够成功上传到集群上
			return FiletablePSQL.addFile(owner, md5, abspath, realpath);//记录到数据库
		}else{
			return false;
		}
	}
	/**
	 * 解析抽象文件路径获取文件类型的方法
	 * 比如ff.doc就可以认为这是的类型为doc的文件
	 * @param abspath 抽象文件路径
	 * @return 返回上传的文件类型
	 */
	public String getFtype(String abspath){
		String type="";
		if(abspath.lastIndexOf('/')>-1){//表示包含/
		   String fname=abspath.substring(abspath.lastIndexOf('/')+1);//按/分割文件名
		   if(fname.lastIndexOf('.')>-1)//表示包含.
		      type=fname.substring(fname.lastIndexOf('.')+1);// 按.分割后缀名
		}
		return type;
	}
}
