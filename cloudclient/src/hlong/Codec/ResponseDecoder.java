package hlong.Codec;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.CumulativeProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;

import hlong.GetResponseObject.GetResponseObject;
import hlong.Response.DownloadResponse;
import hlong.Response.FileViewResponse;
import hlong.Response.LoginResponse;
import hlong.Response.PreUploadResponse;
import hlong.Response.RegisterResponse;
import hlong.Response.UploadResponse;
import hlong.Utils.DataPackageType;
/**
 * 主要用于对于服务器发送过来的响应进行解码处理，生成响应的响应消息对象
 * @author wojiaolongyinong
 *
 */
public class ResponseDecoder extends CumulativeProtocolDecoder{

	protected boolean doDecode(IoSession session, IoBuffer in,
			ProtocolDecoderOutput out) throws Exception {
		//代表一个类型的大小以及表示数据包的总大小
		if(in.remaining() > 1){
			byte type = in.get();
			int size = in.getInt();
			//数据包的剩余数据量
			byte[] data = new byte[size - 1 - 4];
			in.get(data);
			switch(type){
			case DataPackageType.RE_REGISTER://注册消息
				RegisterResponse rr = GetResponseObject.getRegisterRe(data);
				out.write(rr);
				break;
			case DataPackageType.RE_LOGIN://登录消息
				LoginResponse lr = GetResponseObject.getLogin(data);
				out.write(lr);
				break;
			case DataPackageType.RE_CREATEDIR:
				FileViewResponse fvr_create = GetResponseObject.getFileView(data);
				out.write(fvr_create);
				break;
			case DataPackageType.RE_DELETE:
				FileViewResponse fvr_delete = GetResponseObject.getFileView(data);
				out.write(fvr_delete);
				break;
			case DataPackageType.RE_DOWNLOAD:
				DownloadResponse dr = GetResponseObject.getDownload(data);
				out.write(dr);
				break;
			case DataPackageType.RE_MOVING:
				FileViewResponse fvr_move = GetResponseObject.getFileView(data);
				out.write(fvr_move);
				break;
			case DataPackageType.RE_PR_UPLOAD://验证md5值消息
				PreUploadResponse pur = GetResponseObject.getPreUpload(data);
				out.write(pur);
				break;
			case DataPackageType.RE_RENAME:
				FileViewResponse fvr_name = GetResponseObject.getFileView(data);
				out.write(fvr_name);
				break;
			case DataPackageType.RE_UPLOAD:
				UploadResponse fvr_upload = GetResponseObject.getUpload(data);
				out.write(fvr_upload);
				break;
			}
			return true;
		}
		return false;
	}

}
