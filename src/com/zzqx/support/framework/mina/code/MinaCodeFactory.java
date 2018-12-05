package com.zzqx.support.framework.mina.code;

import java.nio.charset.Charset;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolEncoder;

public class MinaCodeFactory implements ProtocolCodecFactory {  
	  
    private final MinaEcoder encoder;
    private final MinaDecoder decoder;
    /*final static char endchar = 0x1a;*/
    public MinaCodeFactory() {
        this(Charset.forName("UTF-8"));
    }
    public MinaCodeFactory(Charset charset) {
         encoder = new MinaEcoder(charset);
         decoder = new MinaDecoder(charset);
    }
	public ProtocolDecoder getDecoder(IoSession session) throws Exception {
		return this.decoder;
	}
	public ProtocolEncoder getEncoder(IoSession session) throws Exception {
		return this.encoder;
	}
}