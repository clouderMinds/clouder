package hlong.Codec;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoderAdapter;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;

import hlong.GetResponseByte.GetResponseByte;
import hlong.Response.DownloadResponse;
import hlong.Response.FileViewResponse;
import hlong.Response.LoginResponse;
import hlong.Response.PreUploadResponse;
import hlong.Response.RegisterResponse;
import hlong.Utils.DataPackageType;

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
		case DataPackageType.RE_DOWNLOAD_S://响应下载的数据包
			DownloadResponse dr = (DownloadResponse)message;
			data = GetResponseByte.getDownload(dr);
			break;
		case DataPackageType.FILE_VIEW_S://文件视图数据包
			FileViewResponse fvr = (FileViewResponse)message;
			data = GetResponseByte.getFileView(fvr, DataPackageType.FILE_VIEW);
			break;
		case DataPackageType.RE_LOGIN_S://响应那个登陆的数据包
			LoginResponse lr = (LoginResponse)message;
			data = GetResponseByte.getLogin(lr);
			break;
		case DataPackageType.RE_PR_UPLOAD_S://响应上传检测的数据包
			PreUploadResponse pur = (PreUploadResponse)message;
			data = GetResponseByte.getPreUpload(pur);
			break;
		case DataPackageType.RE_REGISTER_S://响应注册的数据包
			RegisterResponse rr = (RegisterResponse)message;
			data = GetResponseByte.getRegister(rr);
			break;
		case DataPackageType.SIM_RESPONSE_S://响应的简单数据包，可用于多种响应
			
			break;
		}
		buffer = IoBuffer.allocate(data.length,false);
		buffer.put(data);
		buffer.flip();
		out.write(buffer);
	}

}
