package com.zzqx.mvc.service;

import com.zzqx.mvc.entity.Interaction;

import java.util.List;

public interface InteractionService {
    /**
     * 根据InteractionCode 查询对应ID
     * @param interaction
     * @return
     */
    List<Interaction> selectByExample(Interaction  interaction);
}

