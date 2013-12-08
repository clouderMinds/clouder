package hlong.Server;

import hlong.Request.CreateDirRequest;
import hlong.Request.DeleteRequest;
import hlong.Request.DownloadRequest;
import hlong.Request.LoginRequest;
import hlong.Request.PRUploadRequest;
import hlong.Request.RegisterRequest;
import hlong.Request.RenameRequest;
import hlong.Request.UploadRequest;
import hlong.Response.DownloadResponse;
import hlong.Response.LoginResponse;
import hlong.Response.PreUploadResponse;
import hlong.Response.RegisterResponse;
import hlong.Response.UploadResponse;
import hxiong.dbmanager.psql.FiletablePSQL;
import hxiong.dbmanager.psql.HdfsuserPSQL;

import org.apache.mina.core.service.IoHandler;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

/**
 * 处理客户端消息
 * @author wojiaolongyinong
 *
 */
public class ServerHandler implements IoHandler{
	
	public void sessionCreated(IoSession session) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sessionOpened(IoSession session) throws Exception {
		// TODO Auto-generated method stub
		
	}

	public void sessionClosed(IoSession session) throws Exception {
		
	}

	@Override
	public void sessionIdle(IoSession session, IdleStatus status)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void exceptionCaught(IoSession session, Throwable cause)
			throws Exception {
		
	}

	@Override
	public void messageReceived(IoSession session, Object message)
			throws Exception {
		String requesttype=message.getClass().getSimpleName();
		if(requesttype.equals("LoginRequest")){//处理登录消息
			LoginRequest lr=(LoginRequest)message;
			boolean result=HdfsuserPSQL.CheckHDFSUser(lr.getID(), lr.getPassword());
			if(result){//LoginResponse
				session.write(new LoginResponse(lr.getID(),LoginResponse.SUCCESS));
			}else{
				session.write(new LoginResponse(lr.getID(),LoginResponse.PWDERROR));
			}
			
		}else if(requesttype.equals("RegisterRequest")){//处理注册消息
			RegisterRequest rr = (RegisterRequest)message;
			boolean result = HdfsuserPSQL.addHDFSUser("ui", rr.getID(), rr.getPassword());
			if(result){
				session.write(new RegisterResponse(rr.getID(),RegisterResponse.SUCCESS));
			}else{
				session.write(new RegisterResponse(rr.getID(),RegisterResponse.IDEXIST));
			}
			
		}else if(requesttype.equals("PRUploadRequest")){//验证文件md5值消息
			PRUploadRequest pr = (PRUploadRequest)message;
			String realpath=FiletablePSQL.getRealpaht(pr.getMD5());//查询该md5对应的文件是否已经存在
			if(realpath!=null){//表示已经存在PreUploadResponse
				FiletablePSQL.addFile(pr.getID(), pr.getMD5(), pr.getAbsPath(), realpath);//如果文件已经存在，则将该文件信息存入数据库
				session.write(new PreUploadResponse(pr.getID(),pr.getLocalPaht(),PreUploadResponse.EXIST));
			}else{
				session.write(new PreUploadResponse(pr.getID(),pr.getLocalPaht(),PreUploadResponse.NOEXIST));
			}
		}else if(requesttype.equals("UploadRequest")){//处理上传文件消息
			UploadRequest ul = (UploadRequest)message;
			System.out.println(ul.getAbsPath());
			System.out.println(ul.getLocalPaht());
			System.out.println(ul.getMD5());
			//将文件存入集群
			session.write(new UploadResponse(ul.getID(),ul.getLocalPaht(),UploadResponse.FAIL));

		}else if(requesttype.equals("DownloadRequest")){//处理下载文件消息
			DownloadRequest dl = (DownloadRequest)message;
			System.out.println(dl.getAbsPath());
			System.out.println(dl.getLocalPath());
			//将文件存入集群
			session.write(new DownloadResponse(dl.getID(),dl.getLocalPath(),DownloadResponse.FAIL,new byte[4]));

		}else if(requesttype.equals("CreateDirRequest")){//处理创建文件夹消息
			CreateDirRequest dl = (CreateDirRequest)message;
			System.out.println(dl.getAbsPath());
			boolean bl=FiletablePSQL.addFile(dl.getID(), "this is a folder", dl.getAbsPath(), dl.getAbsPath());
			if(bl){//如果操作成功，返回个客户端消息
				System.out.println("操作成功了！");
			}else{
				System.out.println("操作失败了！");
			}			
			
		}else if(requesttype.equals("DeleteRequest")){//处理创建文件夹消息
			DeleteRequest dlre = (DeleteRequest)message;
			System.out.println(dlre.getAbsPath());
			//将文件存入集群
		}else if(requesttype.equals("RenameRequest")){//处理创建文件夹消息
			RenameRequest re = (RenameRequest)message;
			System.out.println(re.getOldPath());
			System.out.println(re.getNowPath());
			//将文件存入集群
		}
	}

	public void messageSent(IoSession session, Object message) throws Exception {
		
	}

}
