package com.zzqx.mvc.service;

import com.zzqx.mvc.entity.Cmd;
import com.zzqx.mvc.vo.CmdListVo;

import java.util.List;

public interface CmdService {
    //数据list
    List<Cmd>  getList(Cmd cmd);

    //区分type的数据集合
    List<Cmd> getListByType(String type);

    CmdListVo getAllDataByType();

}
