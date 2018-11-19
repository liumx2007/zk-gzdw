package com.zzqx.mvc.service.impl;

import com.zzqx.mvc.dao.InteractionLogMapper;
import com.zzqx.mvc.dto.InteractionLogDto;
import com.zzqx.mvc.entity.InteractionLog;
import com.zzqx.mvc.service.InteractionLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class InteractionLogServiceImpl  implements InteractionLogService {

    @Autowired
    InteractionLogMapper interactionLogMapper;

    /**
     * 有选择的条件、所有数据
     */
    public List<InteractionLog> selectByEntity(InteractionLog interactionLog){
        return interactionLogMapper.selectByEntity(interactionLog);
    }

    @Override
    public List<InteractionLog> selectWithInteraction(InteractionLogDto interactionLogDto) {
        return interactionLogMapper.selectWithInteraction(interactionLogDto);
    }

    /**
     * 有选择的插入数据
     * @param interactionLog
     * @return
     */
    @Override
    public Integer insertSelective(InteractionLog interactionLog) {
        return interactionLogMapper.insertSelective(interactionLog);
    }

}
