package com.zzqx.mvc.service.impl;

import com.zzqx.mvc.dao.EnvironmentMapper;
import com.zzqx.mvc.entity.Environment;
import com.zzqx.mvc.entity.EnvironmentExample;
import com.zzqx.mvc.service.EnvironmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Administrator on 2018/11/27 0027.
 */
@Service("environmentService")
@Transactional
public class EnvironmentServiceImpl implements EnvironmentService {
    @Autowired
    EnvironmentMapper environmentMapper;
    @Override
    public Environment getUpdateOne() {
        return environmentMapper.getUpdateOne();
    }

    @Override
    public void save(Environment environment) {
        environmentMapper.insert(environment);
    }

    @Override
    public void update(Environment environment) {
        environmentMapper.updateByPrimaryKey(environment);
    }
}
