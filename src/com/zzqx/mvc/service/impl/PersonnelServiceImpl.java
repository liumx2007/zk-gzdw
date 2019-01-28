package com.zzqx.mvc.service.impl;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jetsum.core.orm.entity.Page;
import com.zzqx.mvc.commons.CountInfo;
import com.zzqx.mvc.dao.EmployeeInformationMapper;
import com.zzqx.mvc.dao.PersonnelDao;
import com.zzqx.mvc.entity.*;
import com.zzqx.mvc.service.*;
import com.zzqx.mvc.vo.PersonVo;
import com.zzqx.support.framework.mina.androidser.AndroidConstant;
import com.zzqx.support.framework.mina.androidser.AndroidMinaSession;
import com.zzqx.support.utils.CommonUtil;
import com.zzqx.support.utils.ServiceException;
import com.zzqx.support.utils.StringHelper;
import com.zzqx.support.utils.net.SocketDataSender;
import org.hibernate.Query;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service("personnelService")
@Transactional
public class PersonnelServiceImpl implements PersonnelService {

	@Autowired
	private PersonnelDao personnelDao;
	@Autowired
	private ArrangeDateService arrangeDateService;
	@Autowired
	private ArrangeDetialService arrangeDetialService;
	@Autowired
	private WorkPositionService workPositionService;
	@Autowired
	private MessageService messageService;
	@Autowired
	BhSchduService bhSchduService;
	@Autowired
	EmployeeInformationMapper employeeInformationMapper;


	String s = "";


	@Override
	public void executeHql(String hql, Object... values) {
		personnelDao.batchExecute(hql, values);
		personnelDao.flush();
	}

	@Override
	public void saveOrUpdate(Personnel t) {
		personnelDao.saveOrUpdate(t);
		personnelDao.flush();
	}

	@Override
	public Personnel getById(String id) {
		return personnelDao.findUnique(Restrictions.eq("id", id));
	}

	@Override
	public void delete(String... ids) {
		String str = Stream.of(ids).map(id -> "'" + id + "'").collect(Collectors.joining(","));
		if (StringHelper.isNotBlank(str)) {
			personnelDao.batchExecute("delete Personnel where id in (" + str + ")");
			personnelDao.flush();
		}
	}

	@Override
	public List<Personnel> find(Criterion... criterions) {
		// TODO Auto-generated method stub
		return personnelDao.find(criterions);
	}

	@Override
	public List<Personnel> getAll() {
		return personnelDao.getAll();
	}

	@Override
	public List<Personnel> get(String... ids) {
		// TODO Auto-generated method stub
		String str = Stream.of(ids).map(id -> "'" + id + "'").collect(Collectors.joining(","));
		if (StringHelper.isNotBlank(str)) {
			return personnelDao.find("from Personnel where id in (" + str + ")");
		} else {
			return new ArrayList<>();
		}
	}

	@Override
	public Page<Personnel> getByPage(Map<String, Object> map, int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		Page<Personnel> page = new Page<>();
		page.pageNo(pageNo).pageSize(pageSize).autoCount(true);
		return personnelDao.findPage(page, map);
	}

	@Override
	public Page<Personnel> getByPage(Map<String, Object> map, int pageNo, int pageSize, String orderBy, String order) {
		// TODO Auto-generated method stub
		Page<Personnel> page = new Page<>();
		page.pageNo(pageNo).pageSize(pageSize).autoCount(true).orderBy(orderBy).setOrder(order);
		return personnelDao.findPage(page, map);
	}

	@Override
	public Query createQuery(String hql, Object... values) {
		// TODO Auto-generated method stub
		return personnelDao.createQuery(hql, values);
	}

	@Override
	public Query createQuery(String hql, Map<String, ?> map) {
		// TODO Auto-generated method stub
		return personnelDao.createQuery(hql, map);
	}

	/**
	 * 呼叫类型（呼叫班长、呼叫保安、集团业务、日常消息）消息函数
	 * 
	 * @param minaSession
	 *           发送者 socket Session对象
	 * @param msgType
	 *            消息类型
	 * @param watchCode
	 *          发送者  手表ID
	 */
	@SuppressWarnings("unchecked")
	@Override
	public boolean logicMsgCall(AndroidMinaSession minaSession, Integer msgType, String watchCode,
			MessageService msgService) throws ServiceException {
		try {
			boolean resState = false;
//			Personnel personnel = null;
			Personnel personnel = new Personnel();
			String content = "";
			StringBuffer sb = null;
			if (msgType.intValue() == AndroidConstant.MESSAGE_TYPE_CALLMONITOR_KEY.intValue()) {
//				Query query = personnelDao.createQuery("from Personnel p where p.my_work.id=?", "12");
				EmployeeInformationExample employeeInformationExample = new EmployeeInformationExample();
				EmployeeInformationExample.Criteria criteria = employeeInformationExample.createCriteria();
				criteria.andMyWorkEqualTo(CountInfo.BANZHANG);
				List<EmployeeInformation> employeeInformationList = employeeInformationMapper.selectByExample(employeeInformationExample);//职位为班长的人员
//				List<Personnel> startPersonnelsList = personnelDao.findBy("watch_code", watchCode);//发起呼叫的人List;
				EmployeeInformationExample employeeInformationExample1 = new EmployeeInformationExample();
				EmployeeInformationExample.Criteria criteria1 = employeeInformationExample1.createCriteria();
				criteria1.andWatchCodeEqualTo(watchCode);
				List<EmployeeInformation> employeeInformationList1 = employeeInformationMapper.selectByExample(employeeInformationExample1);//发起呼叫的人
//				Personnel startPersonnels = null;//发起呼叫的人;
//				if(startPersonnelsList!=null&&startPersonnelsList.size()>0){
//					startPersonnels = startPersonnelsList.get(0);
//				}
				EmployeeInformation employeeInformation1  = null;//发起呼叫的人
				if (employeeInformationList1 != null && employeeInformationList1.size() > 0){
					employeeInformation1 = employeeInformationList1.get(0);
				}
//				List<Personnel> personnels = query.list();
				if (employeeInformationList != null && employeeInformationList.size() > 0) {// 班长(后台)
//					personnel = personnels.get(CommonUtil.getRandom(0, personnels.size() - 1));// 随机选一个班长(后台)
					EmployeeInformation employeeInformation = null;
					employeeInformation = employeeInformationList.get(0);
					personnel.setWatch_code(employeeInformation.getWatchCode());
					personnel.setName(employeeInformation.getName());
					SocketDataSender.sendWatchMsg(msgType, watchCode, personnel);// 发消息通知班长(后台)

					minaSession.getWatchCode();
					sb = new StringBuffer("");
//					sb.append(startPersonnels.getMy_work().getPosition_name());
					sb.append(employeeInformation1.getMyWork());
					sb.append("(");
//					sb.append(startPersonnels.getName());
					sb.append(employeeInformation1.getName());
//					sb.append("工号");
//					sb.append(startPersonnels.getJob_num());
					sb.append(")");
					sb.append("呼叫您。");
					content = sb.toString();
					resState = true;
				} else {
					throw new ServiceException("未获取到班长(后台)信息");
				}
			} else if (msgType.intValue() == AndroidConstant.MESSAGE_TYPE_SHIFT_KEY.intValue()) {
				EmployeeInformationExample employeeInformationExample = new EmployeeInformationExample();
				EmployeeInformationExample.Criteria criteria = employeeInformationExample.createCriteria();
				criteria.andMyWorkEqualTo(CountInfo.BANZHANG);
				List<EmployeeInformation> employeeInformationList = employeeInformationMapper.selectByExample(employeeInformationExample);//职位为值长的人员
				EmployeeInformationExample employeeInformationExample1 = new EmployeeInformationExample();
				EmployeeInformationExample.Criteria criteria1 = employeeInformationExample1.createCriteria();
				criteria1.andWatchCodeEqualTo(watchCode);
				List<EmployeeInformation> employeeInformationList1 = employeeInformationMapper.selectByExample(employeeInformationExample1);//发起呼叫的人
				EmployeeInformation employeeInformation1  = null;//发起呼叫的人
				if (employeeInformationList1 != null && employeeInformationList1.size() > 0){
					employeeInformation1 = employeeInformationList1.get(0);
				}
				if (employeeInformationList != null && employeeInformationList.size() > 0) {// 值长(后台)
					EmployeeInformation employeeInformation = null;
					employeeInformation = employeeInformationList.get(0);
					personnel.setWatch_code(employeeInformation.getWatchCode());
					personnel.setName(employeeInformation.getName());
					SocketDataSender.sendWatchMsg(msgType, watchCode, personnel);// 发消息通知值长(后台)

					minaSession.getWatchCode();
					sb = new StringBuffer("");
					sb.append(employeeInformation1.getMyWork());
					sb.append("(");
					sb.append(employeeInformation1.getName());
					sb.append(")");
					sb.append("呼叫您。");
					content = sb.toString();
					resState = true;
				} else {
					throw new ServiceException("未获取到值长信息");
				}
			}else if (msgType.intValue() == AndroidConstant.MESSAGE_TYPE_VACATION_KEY.intValue()) {//消息请假
				EmployeeInformationExample employeeInformationExample = new EmployeeInformationExample();
				EmployeeInformationExample.Criteria criteria = employeeInformationExample.createCriteria();
				criteria.andMyWorkEqualTo(CountInfo.BANZHANG);
				List<EmployeeInformation> employeeInformationList = employeeInformationMapper.selectByExample(employeeInformationExample);//职位为值长的人员
				EmployeeInformationExample employeeInformationExample1 = new EmployeeInformationExample();
				EmployeeInformationExample.Criteria criteria1 = employeeInformationExample1.createCriteria();
				criteria1.andWatchCodeEqualTo(watchCode);
				List<EmployeeInformation> employeeInformationList1 = employeeInformationMapper.selectByExample(employeeInformationExample1);//发起呼叫的人
				EmployeeInformation employeeInformation1  = null;//发起呼叫的人
				if (employeeInformationList1 != null && employeeInformationList1.size() > 0){
					employeeInformation1 = employeeInformationList1.get(0);
				}
				if (employeeInformationList != null && employeeInformationList.size() > 0) {// 值长(后台)
					EmployeeInformation employeeInformation = null;
					employeeInformation = employeeInformationList.get(0);
					personnel.setWatch_code(employeeInformation.getWatchCode());
					personnel.setName(employeeInformation.getName());
					SocketDataSender.sendWatchMsg(msgType, watchCode, personnel);// 发消息通知值长(后台)

					minaSession.getWatchCode();
					sb = new StringBuffer("");
					sb.append(employeeInformation1.getMyWork());
					sb.append("(");
					sb.append(employeeInformation1.getName());
					sb.append(")");
					sb.append("需要临时请假！");
					content = sb.toString();
					resState = true;
				} else {
					throw new ServiceException("未获取到请假信息");
				}
			}else if (msgType.intValue() == AndroidConstant.MESSAGE_TYPE_CALLSECURITY_KEY.intValue()) {
//				Query query = personnelDao.createQuery("from Personnel p where p.my_work.id=?", "10");
//				List<Personnel> personnels = query.list();
//				if (personnels != null && personnels.size() > 0) {// 保安
//					personnel = personnels.get(CommonUtil.getRandom(0, personnels.size() - 1));// 随机选一个保安
				EmployeeInformationExample employeeInformationExample = new EmployeeInformationExample();
				EmployeeInformationExample.Criteria criteria = employeeInformationExample.createCriteria();
				criteria.andNameEqualTo(CountInfo.BAOAN);
				List<EmployeeInformation> employeeInformationList = employeeInformationMapper.selectByExample(employeeInformationExample);//职位为值长的人员
				EmployeeInformationExample employeeInformationExample1 = new EmployeeInformationExample();
				EmployeeInformationExample.Criteria criteria1 = employeeInformationExample1.createCriteria();
				criteria1.andWatchCodeEqualTo(watchCode);
				List<EmployeeInformation> employeeInformationList1 = employeeInformationMapper.selectByExample(employeeInformationExample1);//发起呼叫的人
				EmployeeInformation employeeInformation1  = null;//发起呼叫的人
				if (employeeInformationList1 != null && employeeInformationList1.size() > 0){
					employeeInformation1 = employeeInformationList1.get(0);
				}
				if (employeeInformationList != null){
					EmployeeInformation employeeInformation = employeeInformationList.get(0);
					personnel.setName(employeeInformation.getName());
					personnel.setWatch_code(employeeInformation.getWatchCode());

				SocketDataSender.sendWatchMsg(msgType, watchCode, personnel);// 发消息通知保安
					sb = new StringBuffer("");
					sb.append("(");
					sb.append(employeeInformation1.getMyWork());
					sb.append(")");
					sb.append(employeeInformation1.getName());
//					sb.append("工号");
//					sb.append(personnel.getJob_num());
					sb.append(AndroidConstant.MESSAGE_TYPE_MAP.get(msgType));
					content = sb.toString();
					resState = true;
				} else {
					throw new ServiceException("未获取到保安信息");
				}

			} else if (msgType.intValue() == AndroidConstant.MESSAGE_TYPE_GROUPBUSINESS_KEY.intValue()) {
				Query query = personnelDao.createQuery("from Personnel p where p.my_work.id=?", "10");
				List<Personnel> personnels = query.list();
				if (personnels != null && personnels.size() > 0) {// 工作状态为空闲的集团业务员
					personnel = personnels.get(CommonUtil.getRandom(0, personnels.size() - 1));// 随机选一个集团业务员
					SocketDataSender.sendWatchMsg(msgType, watchCode, personnel);// 发消息通知集团业务员
					sb = new StringBuffer("集团客户来访，请接待！");
					content = sb.toString();
					resState = true;
				} else {
					throw new ServiceException("未获取到空闲的集团业务员信息");
				}
			} else if (msgType.intValue() == AndroidConstant.MESSAGE_TYPE_NORMAL_KEY.intValue()) {// 日常消息
//				List<Personnel> personnels = personnelDao.find(Restrictions.eq("watch_code", watchCode));
				//todo 需要处理 19-1-10
				try{
					CountInfo countInfo = new CountInfo();
					String httpCore = countInfo.SERVER_IP;
					s = HttpUtil.get(httpCore+"/api/employeeInformation/getListByWatch?watchCode="+watchCode,2000);

				}catch (Exception e){
					return false;
				}
//				Personnel personnels = null;
				PersonVo personnels = null;
				if(!"".equals(s)){
					cn.hutool.json.JSONObject object = new cn.hutool.json.JSONObject(s);
					personnels = JSONUtil.toBean(object,PersonVo.class);
				}
				if (personnels != null) {
					personnel = new Personnel(personnels);
					resState = true;
					
					sb = new StringBuffer("");
					sb.append(personnel.getName());
//					sb.append("工号");
//					sb.append(personnel.getJob_num());
					//获取排班信息
					String schMsg = "";
					//todo 查询本地排班

					try{
						CountInfo countInfo = new CountInfo();
						String httpCore = countInfo.SERVER_IP;
						schMsg = HttpUtil.get(httpCore+"/api/dwBhSchedu/watchSchedu?hallId=2",2000);
					}catch (Exception e){
//						return false;
						//本地排班信息
						List<BhSchdu> list = bhSchduService.selectByExample();
						cn.hutool.json.JSONArray jsonArray = new cn.hutool.json.JSONArray();
						jsonArray.add(list);
						String s = jsonArray.toString();
						schMsg  = s.substring(1,s.length()-1);
					}
					String wordStr = "";
					if(!"".equals(schMsg)){
						JSONObject jsa = JSONObject.parseObject(schMsg);
						Object Json = jsa.get("data");
						JSONArray schJson = JSONArray.parseArray(Json.toString());
						for(int i = 0;i<schJson.size();i++){
							JSONObject schTemp = schJson.getJSONObject(i);
							if(personnels.getName().equals(schTemp.get("name"))){
								wordStr = schTemp.get("jobsName").toString();
							}
						}
					}
//					WorkPosition work = getWorkByPerson(personnel);
					if(!"".equals(wordStr)){
//						sb.append("今日工作岗位为" + getWorkByPerson(personnel).getPosition_name());
						sb.append("今日工作岗位为" + wordStr);
					}else{
						sb.append("今日工作岗位无排班记录");
						return true;
					}
					content = sb.toString();
				}
			}
			addMessage(personnel, msgType, watchCode, minaSession, msgService, content);
			return resState;
		} catch (Exception e) {
			throw new ServiceException(AndroidConstant.SYSTEM_nUNKNOWN_ERROR_0001 + e);
		}
	}

	private void addMessage(Personnel personnel, Integer msgType, String watchCode, AndroidMinaSession minaSession,
			MessageService msgService, String content) {
		Message msg = new Message();
		msg.setCreate_time(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		if (msgType != AndroidConstant.MESSAGE_TYPE_CALLSECURITY_KEY)
		msg.setTitle(AndroidConstant.MESSAGE_TYPE_SHOW_MAP.get(msgType));
		msg.setContent(content);
		msg.setType(msgType);
		msg.setWatch_code(watchCode);
		msg.setEdit_time(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		msg.setCreator(minaSession.getWatchCode());
		msg.setWatch_code(personnel.getWatch_code());
		msg.setStatu(AndroidConstant.MESSAGE_STATE_UNREAD_KEY);
		msg.setOrdertime(new Date());
		msgService.saveOrUpdate(msg);
	}
	/**
	 * 获取今日岗位
	 * @param
	 */
//	public WorkPosition getWorkByPerson(Personnel perTemp) {
//		//根据排班获取今天的岗位
//		Query datesQuery = arrangeDateService.createQuery("from ArrangeDate d where date_format(d.arrange_date,'%Y-%m-%d')=?", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
//		ArrangeDate Date = null;
//		if(datesQuery.list().size()>0){
//			Date = (ArrangeDate) datesQuery.list().get(0);
//		}else{
//			return perTemp.getMy_work();
//		}
//		Query detialQuery = arrangeDetialService.createQuery("from ArrangeDetial d where d.arrange_date_id.id=?", Date.getId());
//		List<ArrangeDetial> list = detialQuery.list();
//		for(ArrangeDetial ad :list){
//			if(ad.getPerson_id().equals(perTemp.getId()) || ad.getPerson_id().contains(perTemp.getId())){
//				return workPositionService.getById(ad.getPosition());
//			}
//		}
//		return perTemp.getMy_work();
//	}

	@Override
	public Personnel getPersonnelWatchCode(String WatchCode) {
		// TODO Auto-generated method stub
		return personnelDao.findUniqueBy("watch_code", WatchCode);
	}
}
