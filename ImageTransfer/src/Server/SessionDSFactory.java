package Server;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.core.session.IoSessionAttributeMap;
import org.apache.mina.core.session.IoSessionDataStructureFactory;
import org.apache.mina.core.write.WriteRequestQueue;

/**
 * 
 * @author LONG
 *
 */
public class SessionDSFactory implements IoSessionDataStructureFactory{

	@Override
	public IoSessionAttributeMap getAttributeMap(IoSession session)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public WriteRequestQueue getWriteRequestQueue(IoSession session)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
