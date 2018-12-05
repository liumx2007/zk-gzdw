package com.zzqx.support.framework.mina.code;

import java.nio.charset.Charset;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.CumulativeProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;

import com.zzqx.support.framework.mina.Mina;
import com.zzqx.support.framework.mina.MinaManager;

public class MinaDecoder extends CumulativeProtocolDecoder {
	
	private final Charset charset;
	
	public MinaDecoder(Charset charset) {
		this.charset = charset;
	}

	@Override
	protected boolean doDecode(IoSession ioSession, IoBuffer in, ProtocolDecoderOutput out) throws Exception {
		byte[] b = new byte [in.limit()];
		in.get(b);
		String message = new String(b, charset.displayName());
		StringBuffer tempMessage = MinaManager.get(ioSession).getStringBuffer();
		tempMessage.append(message);
		boolean status = false;
		if(status = message.endsWith(Mina.SOCKET_DATA_ENDING)) {
			String allMessage = tempMessage.toString();
			String[] messages = allMessage.split(Mina.SOCKET_DATA_ENDING);
			if(messages.length == 0) {
				out.write("");
			}
			for(String singleMessage : messages) {
				out.write(singleMessage);
			}
			tempMessage.setLength(0);
		}
		MinaManager.updateStringBuffer(ioSession, tempMessage);
		return status;
	}
}
