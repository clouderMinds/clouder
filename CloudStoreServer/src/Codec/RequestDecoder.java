package Codec;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.CumulativeProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;

import GetMessageObject.GetMessage;
import Request.CreateDirRequest;
import Request.DeleteRequest;
import Request.DownloadRequest;
import Request.LoginRequest;
import Request.MovingRequest;
import Request.RegisterRequest;
import Request.RenameRequest;
import Request.UploadRequest;

/**
 * 用于实现请求解码的地方
 * @author wojiaolongyinong 
 *
 */
public class RequestDecoder extends CumulativeProtocolDecoder{
	
	/**
	 * 将传输进来的缓存字节，进行读取
	 * 首先判定是那一种消息类型，然后根据响应的消息类型调用响应的工厂方法
	 * 进行生成对象的处理,然后进行传输
	 */
	protected boolean doDecode(IoSession session, IoBuffer in,
			ProtocolDecoderOutput out) throws Exception {
		if(in.remaining() > 1){
			byte type = in.get();
			int size = in.getInt();
			//因为类型和数据包大小已经被读掉,因此大小减1和减4
			byte[] data = new byte[size - 1 - 4];
			in.get(data);
			switch(type){
			case Utils.DataPackageType.REGISTER://读到是注册请求
				RegisterRequest rr = GetMessage.getRegister(data);
				out.write(rr);
				break;
			case Utils.DataPackageType.LOGIN://登陆请求
				LoginRequest lr = GetMessage.getLogin(data);
				out.write(lr);
				break;
			case Utils.DataPackageType.CREATEDIR://创建文件夹的请求
				CreateDirRequest cdr = GetMessage.getCreateDir(data);
				out.write(cdr);
				break;
			case Utils.DataPackageType.DELETE://删除请求
				DeleteRequest dr = GetMessage.getDelete(data);
				out.write(dr);
				break;
			case Utils.DataPackageType.DOWNLOAD://下载请求
				DownloadRequest dor = GetMessage.getDownload(data);
				out.write(dor);
				break;
			case Utils.DataPackageType.MOVING://移动请求
				MovingRequest mr = GetMessage.getMoving(data);
				out.write(mr);
				break;
			case Utils.DataPackageType.RENAME://重命名请求
				RenameRequest rer = GetMessage.getRename(data);
				out.write(rer);
				break;
			case Utils.DataPackageType.UPLOAD://上传请求
				UploadRequest ur = GetMessage.getUpload(data);
				out.write(ur);
				break;
			}
			return true;
		}
		return false;
	}

}
