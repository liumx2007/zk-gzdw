package com.zzqx.mvc.service.impl;

import com.zzqx.mvc.dao.BhSchduMapper;
import com.zzqx.mvc.entity.BhSchdu;
import com.zzqx.mvc.entity.BhSchduExample;
import com.zzqx.mvc.service.BhSchduService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BhSchduServiceImpl implements BhSchduService {

    @Autowired
    BhSchduMapper bhSchduMapper;

    @Override
    public List<BhSchdu> selectByExample() {
        BhSchduExample example = new BhSchduExample();
        return bhSchduMapper.selectByExample(example);
    }
}
