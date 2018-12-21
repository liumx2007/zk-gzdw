package com.zzqx.support.framework.mina.ElectricDrive.EnCoder;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolEncoder;

import java.nio.charset.Charset;

public class EleCodeFactory implements ProtocolCodecFactory {

    private final EleEcoder encoder;
    private final EleDecoder decoder;

    public EleCodeFactory(Charset charset) {
        encoder = new EleEcoder(charset);
        decoder = new EleDecoder(charset);
    }

    public EleCodeFactory() {
        this(Charset.forName("UTF-8"));
    }

    @Override
    public ProtocolEncoder getEncoder(IoSession ioSession) throws Exception {
        return this.encoder;
    }

    @Override
    public ProtocolDecoder getDecoder(IoSession ioSession) throws Exception {
        return this.decoder;
    }
}
