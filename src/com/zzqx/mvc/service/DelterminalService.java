package com.zzqx.mvc.service;

import com.zzqx.mvc.entity.DelTerminal;

import java.util.List;

public interface DelterminalService {

    int save(DelTerminal delTerminal);

    /**
     * 查询tag为0的数据
     * @param delTerminal
     * @return
     */
    List<DelTerminal> selectList(DelTerminal delTerminal);
}
