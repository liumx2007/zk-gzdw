package com.zzqx.mvc.service.impl;

import com.zzqx.mvc.dao.InteractionLogMapper;
import com.zzqx.mvc.dao.InteractionMapper;
import com.zzqx.mvc.dto.InteractionLogDto;
import com.zzqx.mvc.entity.Interaction;
import com.zzqx.mvc.entity.InteractionExample;
import com.zzqx.mvc.entity.InteractionLog;
import com.zzqx.mvc.entity.InteractionLogExample;
import com.zzqx.mvc.service.InteractionLogService;
import com.zzqx.mvc.vo.InteractionLogVo;
import com.zzqx.support.utils.StringHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class InteractionLogServiceImpl  implements InteractionLogService {

    @Autowired
    InteractionLogMapper interactionLogMapper;
    @Autowired
    InteractionMapper interactionMapper;

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
     * @param interactionLogVo
     * @return
     */
    @Override
    public Integer insertSelective(InteractionLogVo interactionLogVo) {
        interactionLogVo.setClickTime(new Date());
        //获取InteractionId
        if (null != interactionLogVo && !"".equals(interactionLogVo.getInteractCode()) && null != interactionLogVo.getInteractCode() ){
            InteractionExample interactionExample = new InteractionExample();
            InteractionExample.Criteria criteria = interactionExample.createCriteria();
            criteria.andInteractCodeEqualTo(interactionLogVo.getInteractCode());
            List<Interaction> list = interactionMapper.selectByExample(interactionExample);
            if (list.size() > 0){
                Interaction interaction = list.get(0);
                String id = interaction.getId();
                interactionLogVo.setInteractionId(id);
                interactionLogVo.setFolderType(interaction.getFolderType());
                //查询数据库InteractionLog数据
                InteractionLogExample interactionLogExample = new InteractionLogExample();
                InteractionLogExample.Criteria criteria1 = interactionLogExample.createCriteria();
                criteria1.andInteractionIdEqualTo(id);
                interactionLogExample.setOrderByClause("click_time desc");
                List<InteractionLog> logs = interactionLogMapper.selectByExample(interactionLogExample);
                if (logs.size() > 0){
                    InteractionLog interactionLog = logs.get(0);
                    //业务会话判断
                    if (interaction.getFolderType() == 2){
                        if (!interactionLogVo.getSessionBusiness().equals(interactionLog.getSessionBusiness()) && !"".equals(interactionLogVo.getSessionBusiness()) && null != interactionLogVo.getSessionBusiness()){
                            //保存数据
                            interactionLogMapper.insertSelective(interactionLogVo);
                        }
                    }
                    //触点会话判断
                    if (interaction.getFolderType() == 3){
//                        if (!interactionLogVo.getSessionBusiness().equals(interactionLog.getSessionBusiness()) && !"".equals(interactionLogVo.getSessionBusiness()) && null != interactionLogVo.getSessionBusiness()){
//                            //保存数据
//                            interactionLogMapper.insertSelective(interactionLogVo);
//                        }else
                            if (!interactionLogVo.getSessionInteract().equals(interactionLog.getSessionInteract()) && !"".equals(interactionLogVo.getSessionInteract()) && null != interactionLogVo.getSessionInteract()){
                            //保存数据
                            interactionLogMapper.insertSelective(interactionLogVo);
                        }
                    }
                }else {
                    //查无数据直接保存
                     interactionLogMapper.insertSelective(interactionLogVo);
                }
            }
        }
        return null;
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
