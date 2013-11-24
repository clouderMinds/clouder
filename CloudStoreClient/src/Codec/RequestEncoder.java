package Codec;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoderAdapter;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;

import GetMessageByte.GetMessageByte;
import Request.CreateDirRequest;
import Request.DeleteRequest;
import Request.DownloadRequest;
import Request.LoginRequest;
import Request.MovingRequest;
import Request.RegisterRequest;
import Request.RenameRequest;
import Request.UploadRequest;
/**
 * 对于客户端进行发送的请求的进行编码处理者
 * @author wojiaolongyinong 
 *
 */
public class RequestEncoder extends ProtocolEncoderAdapter{

	
	public void encode(IoSession session, Object message,
			ProtocolEncoderOutput out) throws Exception {
		String type = message.getClass().getSimpleName();
		byte[] data = null;
		IoBuffer buffer;
		switch(type){
		case Utils.DataPackageType.REGISTER_S:
			RegisterRequest rr = (RegisterRequest)message;
			data = GetMessageByte.getRegister(rr);
			break;
		case Utils.DataPackageType.LOGIN_S:
			LoginRequest lr = (LoginRequest)message;
			data = GetMessageByte.getLogin(lr);
			break;
		case Utils.DataPackageType.CREATEDIR_S:
			CreateDirRequest cdr = (CreateDirRequest)message;
			data = GetMessageByte.getCreateDir(cdr);
			break;
		case Utils.DataPackageType.DELETE_S:
			DeleteRequest dr = (DeleteRequest)message;
			data = GetMessageByte.getDelete(dr);
			break;
		case Utils.DataPackageType.DOWNLOAD_S:
			DownloadRequest dor = (DownloadRequest)message;
			data = GetMessageByte.getDownload(dor);
			break;
		case Utils.DataPackageType.MOVING_S:
			MovingRequest mr = (MovingRequest)message;
			data = GetMessageByte.getMoving(mr);
			break;
		case Utils.DataPackageType.RENAME_S:
			RenameRequest rer = (RenameRequest)message;
			data = GetMessageByte.getRename(rer);
			break;
		case Utils.DataPackageType.UPLOAD_S:
			UploadRequest ur = (UploadRequest)message;
			data = GetMessageByte.getUpload(ur);
			break;
		}
		buffer = IoBuffer.allocate(data.length,false);
		buffer.put(data);
		buffer.flip();
		out.write(buffer);
	}
}
