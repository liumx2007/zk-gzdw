package com.zzqx.mvc.service;


import com.zzqx.mvc.entity.EmployeeInformation;
import com.zzqx.mvc.entity.Environment;

import java.util.List;

public interface EnvironmentService {

    /**
     * 获取最新的采集数据
     * @return
     */
    Environment getUpdateOne();

    void save(Environment environment);

    void update(Environment environment);
}
