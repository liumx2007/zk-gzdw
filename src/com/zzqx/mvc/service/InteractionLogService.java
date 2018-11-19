package com.zzqx.mvc.service;


import com.zzqx.mvc.dto.InteractionLogDto;
import com.zzqx.mvc.entity.InteractionLog;

import java.util.List;

public interface InteractionLogService {

    List<InteractionLog> selectByEntity(InteractionLog interactionLog);

    List<InteractionLog> selectWithInteraction(InteractionLogDto interactionLogDto);

    Integer insertSelective(InteractionLog interactionLog);
}
