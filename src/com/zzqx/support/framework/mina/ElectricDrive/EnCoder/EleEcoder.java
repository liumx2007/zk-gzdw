package com.zzqx.support.framework.mina.ElectricDrive.EnCoder;

import com.zzqx.support.framework.mina.ElectricDrive.ElectricDriveConstant;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoderAdapter;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;

import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;

public class EleEcoder extends ProtocolEncoderAdapter {

    private final Charset charset;

    public EleEcoder(Charset charset) {
        this.charset = charset;
    }

    @Override
    public void encode(IoSession ioSession, Object o, ProtocolEncoderOutput protocolEncoderOutput) throws Exception {
        CharsetEncoder charsetEncoder = charset.newEncoder();
        String allMessage = o.toString();
        if(!allMessage.endsWith(ElectricDriveConstant.SOCKET_DATA_ENDING)) {
            IoBuffer buffer = IoBuffer.allocate(128).setAutoExpand(true);
            buffer.putString(allMessage, charsetEncoder);
            buffer.flip();
            protocolEncoderOutput.write(buffer);
        } else {
            String[] messages = allMessage.split(ElectricDriveConstant.SOCKET_DATA_ENDING);
            for(String message : messages) {
                IoBuffer buffer = IoBuffer.allocate(128).setAutoExpand(true);
                buffer.putString(message+ElectricDriveConstant.SOCKET_DATA_ENDING, charsetEncoder);
                buffer.flip();
                protocolEncoderOutput.write(buffer);
            }
        }
    }
}
