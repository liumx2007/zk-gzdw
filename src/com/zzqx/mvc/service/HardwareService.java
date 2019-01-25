package com.zzqx.mvc.service;

import com.zzqx.mvc.entity.Hardware;

import java.util.List;

public interface HardwareService {

    /**
     * 批量插入
     * @return
     */
    int batchInsert(List<Hardware> list);
}
