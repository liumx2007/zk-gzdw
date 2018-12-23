package com.zzqx.mvc.service;


import com.zzqx.mvc.entity.EmployeeInformation;
import com.zzqx.mvc.entity.Environment;
import com.zzqx.mvc.entity.EnvironmentExample;

import java.util.List;

public interface EnvironmentService {

    /**
     * 获取最新的采集数据
     * @return
     */
    Environment getUpdateOne();

    void save(Environment environment);

    void update(Environment environment);

    /**
     * 存设备采集数据 接口
     */
    int saveEvo(Environment environment);

    /**
     * 根据area取数据
     */
    List<Environment> getByArea(String area);
}
