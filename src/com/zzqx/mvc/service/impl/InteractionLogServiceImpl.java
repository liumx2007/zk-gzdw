package com.zzqx.mvc.service.impl;

import com.zzqx.mvc.dao.InteractionLogMapper;
import com.zzqx.mvc.dto.InteractionLogDto;
import com.zzqx.mvc.entity.InteractionLog;
import com.zzqx.mvc.service.InteractionLogService;
import com.zzqx.support.utils.StringHelper;
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

    /**
     * 连表有选择的查询数据
     * @param interactionLogDto
     * @return
     */
    @Override
    public List<InteractionLog> selectWithInteraction(InteractionLogDto interactionLogDto) {
        return interactionLogMapper.selectWithInteraction(interactionLogDto);
    }

    @Override
    public List<InteractionLog> selectWithInteractionTest(String page, String rows, InteractionLogDto interactionLogDto) {
        int pageNo = StringHelper.isEmpty(page)?1:Integer.parseInt(page);
        int pageSize =  StringHelper.isEmpty(rows)?10:Integer.parseInt(rows);
        int  limit0 = (pageNo-1)*pageSize;
        interactionLogDto.setLimit0(limit0);
        interactionLogDto.setLimit1(pageSize);
        return interactionLogMapper.selectWithInteractionTest(interactionLogDto);
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

    @Override
    public InteractionLog getUpdateOne() {
        return interactionLogMapper.getUpdateOne();
    }

    @Override
    public void update(InteractionLog interactionLog) {
        interactionLogMapper.updateByPrimaryKey(interactionLog);
    }

}
