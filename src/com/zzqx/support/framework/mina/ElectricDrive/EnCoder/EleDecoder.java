package com.zzqx.support.framework.mina.ElectricDrive.EnCoder;

import com.zzqx.support.framework.mina.ElectricDrive.ElectricDriveConstant;
import com.zzqx.support.framework.mina.ElectricDrive.ElectricDriveManager;
import com.zzqx.support.framework.mina.ElectricDrive.ElectricDriveSession;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.CumulativeProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;

import java.nio.charset.Charset;

public class EleDecoder extends CumulativeProtocolDecoder {
    private final Charset charset;

    public EleDecoder(Charset charset) {
        this.charset = charset;
    }

    @Override
    protected boolean doDecode(IoSession ioSession, IoBuffer ioBuffer, ProtocolDecoderOutput protocolDecoderOutput) throws Exception {
        byte[] b = new byte [ioBuffer.limit()];
        ioBuffer.get(b);
        String message = new String(b, charset.displayName());
//        StringBuffer tempMessage = ElectricDriveManager.get(ioSession).getStringBuffer();
        ElectricDriveSession tem =  ElectricDriveManager.get(ioSession);
        StringBuffer tempMessage;
        if (tem == null){
             tempMessage = new StringBuffer(message);
        }else {
             tempMessage = tem.getStringBuffer();
             tempMessage.append(message);
        }
        boolean status = false;
        if(status = message.endsWith(ElectricDriveConstant.SOCKET_DATA_ENDING)) {
            String allMessage = tempMessage.toString();
            String[] messages = allMessage.split(ElectricDriveConstant.SOCKET_DATA_ENDING);
            if(messages.length == 0) {
                protocolDecoderOutput.write("");
            }
            for(String singleMessage : messages) {
                protocolDecoderOutput.write(singleMessage);
            }
            tempMessage.setLength(0);
        }
        ElectricDriveManager.updateStringBuffer(ioSession, tempMessage);
        return status;
    }
}
