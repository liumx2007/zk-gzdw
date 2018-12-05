package com.zzqx.support.framework.mina.code;

import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoderAdapter;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;

import com.zzqx.support.framework.mina.Mina;

public class MinaEcoder extends ProtocolEncoderAdapter {
	
	private final Charset charset;
	
	public MinaEcoder(Charset charset) {
		this.charset = charset;
	}

	@Override
	public void encode(IoSession arg0, Object arg1, ProtocolEncoderOutput arg2) throws Exception {
		CharsetEncoder charsetEncoder = charset.newEncoder();
		String allMessage = arg1.toString();
		if(!allMessage.endsWith(Mina.SOCKET_DATA_ENDING)) {
			IoBuffer buffer = IoBuffer.allocate(128).setAutoExpand(true);
			buffer.putString(allMessage, charsetEncoder); 
			buffer.flip();
			arg2.write(buffer);
		} else {
			String[] messages = allMessage.split(Mina.SOCKET_DATA_ENDING);
			for(String message : messages) {
				IoBuffer buffer = IoBuffer.allocate(128).setAutoExpand(true);
				buffer.putString(message+Mina.SOCKET_DATA_ENDING, charsetEncoder); 
				buffer.flip();
				arg2.write(buffer);
			}
		}
	}

}
