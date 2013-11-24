package Codec;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoderAdapter;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;

import GetResponseByte.GetResponseByte;
import Response.DownloadResponse;
import Response.FileViewResponse;
import Response.LoginResponse;
import Response.PreUploadResponse;
import Response.RegisterResponse;

/**
 * 用于实现对传输的消息对象进行编码的地方
 * @author wojiaolongyinong 
 *
 */
public class ResponseEnCoder extends ProtocolEncoderAdapter{
	
	/**
	 * 根据传输进来的消息对象进行判定，是属于那一种消息然后调用响应的工厂方法进行
	 * 编码，然后写入缓冲区进行传送
	 */
	public void encode(IoSession session, Object message,
			ProtocolEncoderOutput out) throws Exception {
		String type = message.getClass().getSimpleName();
		byte[] data = null;
		IoBuffer buffer;
		switch(type){
		case Utils.DataPackageType.RE_DOWNLOAD_S:
			DownloadResponse dr = (DownloadResponse)message;
			data = GetResponseByte.getDownload(dr);
			break;
		case Utils.DataPackageType.FILE_VIEW_S:
			FileViewResponse fvr = (FileViewResponse)message;
			data = GetResponseByte.getFileView(fvr, Utils.DataPackageType.FILE_VIEW);
			break;
		case Utils.DataPackageType.RE_LOGIN_S:
			LoginResponse lr = (LoginResponse)message;
			data = GetResponseByte.getLogin(lr);
			break;
		case Utils.DataPackageType.RE_PR_UPLOAD_S:
			PreUploadResponse pur = (PreUploadResponse)message;
			data = GetResponseByte.getPreUpload(pur);
			break;
		case Utils.DataPackageType.RE_REGISTER_S:
			RegisterResponse rr = (RegisterResponse)message;
			data = GetResponseByte.getRegister(rr);
			break;
		}
		buffer = IoBuffer.allocate(data.length,false);
		buffer.put(data);
		buffer.flip();
		out.write(buffer);
	}

}
