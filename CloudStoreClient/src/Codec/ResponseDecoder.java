package Codec;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.CumulativeProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;

import GetResponseObject.GetResponseObject;
import Response.DownloadResponse;
import Response.FileViewResponse;
import Response.LoginResponse;
import Response.PreUploadResponse;
import Response.RegisterResponse;
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
			System.out.println(in.remaining());
			byte type = in.get();
			int size = in.getInt();
			//数据包的剩余数据量
			byte[] data = new byte[size - 1 - 4];
			in.get(data);
			System.out.println("接收到消息的类型是：" + type);
			switch(type){
			case Utils.DataPackageType.RE_REGISTER:
				RegisterResponse rr = GetResponseObject.getRegisterRe(data);
				out.write(rr);
				break;
			case Utils.DataPackageType.RE_LOGIN:
				LoginResponse lr = GetResponseObject.getLogin(data);
				out.write(lr);
				break;
			case Utils.DataPackageType.RE_CREATEDIR:
				FileViewResponse fvr_create = GetResponseObject.getFileView(data);
				out.write(fvr_create);
				break;
			case Utils.DataPackageType.RE_DELETE:
				FileViewResponse fvr_delete = GetResponseObject.getFileView(data);
				out.write(fvr_delete);
				break;
			case Utils.DataPackageType.RE_DOWNLOAD:
				DownloadResponse dr = GetResponseObject.getDownload(data);
				out.write(dr);
				break;
			case Utils.DataPackageType.RE_MOVING:
				FileViewResponse fvr_move = GetResponseObject.getFileView(data);
				out.write(fvr_move);
				break;
			case Utils.DataPackageType.RE_PR_UPLOAD:
				PreUploadResponse pur = GetResponseObject.getPreUpload(data);
				out.write(pur);
				break;
			case Utils.DataPackageType.RE_RENAME:
				FileViewResponse fvr_name = GetResponseObject.getFileView(data);
				out.write(fvr_name);
				break;
			case Utils.DataPackageType.RE_UPLOAD:
				FileViewResponse fvr_upload = GetResponseObject.getFileView(data);
				out.write(fvr_upload);
				break;
			}
			return true;
		}
		return false;
	}

}
