package com.zzqx.support.framework.mina.androidser.code;

import java.nio.charset.Charset;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolEncoder;

public class AndroidMinaCodeFactory implements ProtocolCodecFactory {  
	  
    private final AndroidMinaEcoder encoder;
    private final AndroidMinaDecoder decoder;
    /*final static char endchar = 0x1a;*/
    public AndroidMinaCodeFactory() {
        this(Charset.forName("UTF-8"));
    }
    public AndroidMinaCodeFactory(Charset charset) {
         encoder = new AndroidMinaEcoder(charset);
         decoder = new AndroidMinaDecoder(charset);
    }
	public ProtocolDecoder getDecoder(IoSession session) throws Exception {
		return this.decoder;
	}
	public ProtocolEncoder getEncoder(IoSession session) throws Exception {
		return this.encoder;
	}
}