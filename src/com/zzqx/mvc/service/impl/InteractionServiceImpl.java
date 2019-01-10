package com.zzqx.mvc.service.impl;

import com.zzqx.mvc.dao.InteractionMapper;
import com.zzqx.mvc.entity.Interaction;
import com.zzqx.mvc.entity.InteractionExample;
import com.zzqx.mvc.service.InteractionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class InteractionServiceImpl implements InteractionService {

    @Autowired
    InteractionMapper interactionMapper;

    @Override
    public List<Interaction> selectByExample(Interaction interaction) {
        InteractionExample interactionExample = new InteractionExample();
        InteractionExample.Criteria criteria = interactionExample.createCriteria();
        criteria.andInteractCodeEqualTo(interaction.getInteractCode());
        return  interactionMapper.selectByExample(interactionExample);
    }
}
