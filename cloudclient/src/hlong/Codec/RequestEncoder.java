package hlong.Codec;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoderAdapter;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;

import hlong.GetMessageByte.GetMessageByte;
import hlong.Request.CreateDirRequest;
import hlong.Request.DeleteRequest;
import hlong.Request.DownloadRequest;
import hlong.Request.LoginRequest;
import hlong.Request.MovingRequest;
import hlong.Request.RegisterRequest;
import hlong.Request.RenameRequest;
import hlong.Request.UploadRequest;
import hlong.Utils.DataPackageType;
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
		case DataPackageType.REGISTER_S:
			RegisterRequest rr = (RegisterRequest)message;
			data = GetMessageByte.getRegister(rr);
			break;
		case DataPackageType.LOGIN_S:
			LoginRequest lr = (LoginRequest)message;
			data = GetMessageByte.getLogin(lr);
			break;
		case DataPackageType.CREATEDIR_S:
			CreateDirRequest cdr = (CreateDirRequest)message;
			data = GetMessageByte.getCreateDir(cdr);
			break;
		case DataPackageType.DELETE_S:
			DeleteRequest dr = (DeleteRequest)message;
			data = GetMessageByte.getDelete(dr);
			break;
		case DataPackageType.DOWNLOAD_S:
			DownloadRequest dor = (DownloadRequest)message;
			data = GetMessageByte.getDownload(dor);
			break;
		case DataPackageType.MOVING_S:
			MovingRequest mr = (MovingRequest)message;
			data = GetMessageByte.getMoving(mr);
			break;
		case DataPackageType.RENAME_S:
			RenameRequest rer = (RenameRequest)message;
			data = GetMessageByte.getRename(rer);
			break;
		case DataPackageType.UPLOAD_S:
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
