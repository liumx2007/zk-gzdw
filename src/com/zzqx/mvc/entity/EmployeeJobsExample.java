package com.zzqx.mvc.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EmployeeJobsExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public EmployeeJobsExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("ID is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("ID is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(BigDecimal value) {
            addCriterion("ID =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(BigDecimal value) {
            addCriterion("ID <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(BigDecimal value) {
            addCriterion("ID >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("ID >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(BigDecimal value) {
            addCriterion("ID <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(BigDecimal value) {
            addCriterion("ID <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<BigDecimal> values) {
            addCriterion("ID in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<BigDecimal> values) {
            addCriterion("ID not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("ID between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("ID not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andJobsNumberIsNull() {
            addCriterion("JOBS_NUMBER is null");
            return (Criteria) this;
        }

        public Criteria andJobsNumberIsNotNull() {
            addCriterion("JOBS_NUMBER is not null");
            return (Criteria) this;
        }

        public Criteria andJobsNumberEqualTo(String value) {
            addCriterion("JOBS_NUMBER =", value, "jobsNumber");
            return (Criteria) this;
        }

        public Criteria andJobsNumberNotEqualTo(String value) {
            addCriterion("JOBS_NUMBER <>", value, "jobsNumber");
            return (Criteria) this;
        }

        public Criteria andJobsNumberGreaterThan(String value) {
            addCriterion("JOBS_NUMBER >", value, "jobsNumber");
            return (Criteria) this;
        }

        public Criteria andJobsNumberGreaterThanOrEqualTo(String value) {
            addCriterion("JOBS_NUMBER >=", value, "jobsNumber");
            return (Criteria) this;
        }

        public Criteria andJobsNumberLessThan(String value) {
            addCriterion("JOBS_NUMBER <", value, "jobsNumber");
            return (Criteria) this;
        }

        public Criteria andJobsNumberLessThanOrEqualTo(String value) {
            addCriterion("JOBS_NUMBER <=", value, "jobsNumber");
            return (Criteria) this;
        }

        public Criteria andJobsNumberLike(String value) {
            addCriterion("JOBS_NUMBER like", value, "jobsNumber");
            return (Criteria) this;
        }

        public Criteria andJobsNumberNotLike(String value) {
            addCriterion("JOBS_NUMBER not like", value, "jobsNumber");
            return (Criteria) this;
        }

        public Criteria andJobsNumberIn(List<String> values) {
            addCriterion("JOBS_NUMBER in", values, "jobsNumber");
            return (Criteria) this;
        }

        public Criteria andJobsNumberNotIn(List<String> values) {
            addCriterion("JOBS_NUMBER not in", values, "jobsNumber");
            return (Criteria) this;
        }

        public Criteria andJobsNumberBetween(String value1, String value2) {
            addCriterion("JOBS_NUMBER between", value1, value2, "jobsNumber");
            return (Criteria) this;
        }

        public Criteria andJobsNumberNotBetween(String value1, String value2) {
            addCriterion("JOBS_NUMBER not between", value1, value2, "jobsNumber");
            return (Criteria) this;
        }

        public Criteria andJobsNameIsNull() {
            addCriterion("JOBS_NAME is null");
            return (Criteria) this;
        }

        public Criteria andJobsNameIsNotNull() {
            addCriterion("JOBS_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andJobsNameEqualTo(String value) {
            addCriterion("JOBS_NAME =", value, "jobsName");
            return (Criteria) this;
        }

        public Criteria andJobsNameNotEqualTo(String value) {
            addCriterion("JOBS_NAME <>", value, "jobsName");
            return (Criteria) this;
        }

        public Criteria andJobsNameGreaterThan(String value) {
            addCriterion("JOBS_NAME >", value, "jobsName");
            return (Criteria) this;
        }

        public Criteria andJobsNameGreaterThanOrEqualTo(String value) {
            addCriterion("JOBS_NAME >=", value, "jobsName");
            return (Criteria) this;
        }

        public Criteria andJobsNameLessThan(String value) {
            addCriterion("JOBS_NAME <", value, "jobsName");
            return (Criteria) this;
        }

        public Criteria andJobsNameLessThanOrEqualTo(String value) {
            addCriterion("JOBS_NAME <=", value, "jobsName");
            return (Criteria) this;
        }

        public Criteria andJobsNameLike(String value) {
            addCriterion("JOBS_NAME like", value, "jobsName");
            return (Criteria) this;
        }

        public Criteria andJobsNameNotLike(String value) {
            addCriterion("JOBS_NAME not like", value, "jobsName");
            return (Criteria) this;
        }

        public Criteria andJobsNameIn(List<String> values) {
            addCriterion("JOBS_NAME in", values, "jobsName");
            return (Criteria) this;
        }

        public Criteria andJobsNameNotIn(List<String> values) {
            addCriterion("JOBS_NAME not in", values, "jobsName");
            return (Criteria) this;
        }

        public Criteria andJobsNameBetween(String value1, String value2) {
            addCriterion("JOBS_NAME between", value1, value2, "jobsName");
            return (Criteria) this;
        }

        public Criteria andJobsNameNotBetween(String value1, String value2) {
            addCriterion("JOBS_NAME not between", value1, value2, "jobsName");
            return (Criteria) this;
        }

        public Criteria andJobsDetailsIsNull() {
            addCriterion("JOBS_DETAILS is null");
            return (Criteria) this;
        }

        public Criteria andJobsDetailsIsNotNull() {
            addCriterion("JOBS_DETAILS is not null");
            return (Criteria) this;
        }

        public Criteria andJobsDetailsEqualTo(String value) {
            addCriterion("JOBS_DETAILS =", value, "jobsDetails");
            return (Criteria) this;
        }

        public Criteria andJobsDetailsNotEqualTo(String value) {
            addCriterion("JOBS_DETAILS <>", value, "jobsDetails");
            return (Criteria) this;
        }

        public Criteria andJobsDetailsGreaterThan(String value) {
            addCriterion("JOBS_DETAILS >", value, "jobsDetails");
            return (Criteria) this;
        }

        public Criteria andJobsDetailsGreaterThanOrEqualTo(String value) {
            addCriterion("JOBS_DETAILS >=", value, "jobsDetails");
            return (Criteria) this;
        }

        public Criteria andJobsDetailsLessThan(String value) {
            addCriterion("JOBS_DETAILS <", value, "jobsDetails");
            return (Criteria) this;
        }

        public Criteria andJobsDetailsLessThanOrEqualTo(String value) {
            addCriterion("JOBS_DETAILS <=", value, "jobsDetails");
            return (Criteria) this;
        }

        public Criteria andJobsDetailsLike(String value) {
            addCriterion("JOBS_DETAILS like", value, "jobsDetails");
            return (Criteria) this;
        }

        public Criteria andJobsDetailsNotLike(String value) {
            addCriterion("JOBS_DETAILS not like", value, "jobsDetails");
            return (Criteria) this;
        }

        public Criteria andJobsDetailsIn(List<String> values) {
            addCriterion("JOBS_DETAILS in", values, "jobsDetails");
            return (Criteria) this;
        }

        public Criteria andJobsDetailsNotIn(List<String> values) {
            addCriterion("JOBS_DETAILS not in", values, "jobsDetails");
            return (Criteria) this;
        }

        public Criteria andJobsDetailsBetween(String value1, String value2) {
            addCriterion("JOBS_DETAILS between", value1, value2, "jobsDetails");
            return (Criteria) this;
        }

        public Criteria andJobsDetailsNotBetween(String value1, String value2) {
            addCriterion("JOBS_DETAILS not between", value1, value2, "jobsDetails");
            return (Criteria) this;
        }

        public Criteria andHallIdIsNull() {
            addCriterion("HALL_ID is null");
            return (Criteria) this;
        }

        public Criteria andHallIdIsNotNull() {
            addCriterion("HALL_ID is not null");
            return (Criteria) this;
        }

        public Criteria andHallIdEqualTo(String value) {
            addCriterion("HALL_ID =", value, "hallId");
            return (Criteria) this;
        }

        public Criteria andHallIdNotEqualTo(String value) {
            addCriterion("HALL_ID <>", value, "hallId");
            return (Criteria) this;
        }

        public Criteria andHallIdGreaterThan(String value) {
            addCriterion("HALL_ID >", value, "hallId");
            return (Criteria) this;
        }

        public Criteria andHallIdGreaterThanOrEqualTo(String value) {
            addCriterion("HALL_ID >=", value, "hallId");
            return (Criteria) this;
        }

        public Criteria andHallIdLessThan(String value) {
            addCriterion("HALL_ID <", value, "hallId");
            return (Criteria) this;
        }

        public Criteria andHallIdLessThanOrEqualTo(String value) {
            addCriterion("HALL_ID <=", value, "hallId");
            return (Criteria) this;
        }

        public Criteria andHallIdLike(String value) {
            addCriterion("HALL_ID like", value, "hallId");
            return (Criteria) this;
        }

        public Criteria andHallIdNotLike(String value) {
            addCriterion("HALL_ID not like", value, "hallId");
            return (Criteria) this;
        }

        public Criteria andHallIdIn(List<String> values) {
            addCriterion("HALL_ID in", values, "hallId");
            return (Criteria) this;
        }

        public Criteria andHallIdNotIn(List<String> values) {
            addCriterion("HALL_ID not in", values, "hallId");
            return (Criteria) this;
        }

        public Criteria andHallIdBetween(String value1, String value2) {
            addCriterion("HALL_ID between", value1, value2, "hallId");
            return (Criteria) this;
        }

        public Criteria andHallIdNotBetween(String value1, String value2) {
            addCriterion("HALL_ID not between", value1, value2, "hallId");
            return (Criteria) this;
        }

        public Criteria andScheduOrderIsNull() {
            addCriterion("SCHEDU_ORDER is null");
            return (Criteria) this;
        }

        public Criteria andScheduOrderIsNotNull() {
            addCriterion("SCHEDU_ORDER is not null");
            return (Criteria) this;
        }

        public Criteria andScheduOrderEqualTo(Integer value) {
            addCriterion("SCHEDU_ORDER =", value, "scheduOrder");
            return (Criteria) this;
        }

        public Criteria andScheduOrderNotEqualTo(Integer value) {
            addCriterion("SCHEDU_ORDER <>", value, "scheduOrder");
            return (Criteria) this;
        }

        public Criteria andScheduOrderGreaterThan(Integer value) {
            addCriterion("SCHEDU_ORDER >", value, "scheduOrder");
            return (Criteria) this;
        }

        public Criteria andScheduOrderGreaterThanOrEqualTo(Integer value) {
            addCriterion("SCHEDU_ORDER >=", value, "scheduOrder");
            return (Criteria) this;
        }

        public Criteria andScheduOrderLessThan(Integer value) {
            addCriterion("SCHEDU_ORDER <", value, "scheduOrder");
            return (Criteria) this;
        }

        public Criteria andScheduOrderLessThanOrEqualTo(Integer value) {
            addCriterion("SCHEDU_ORDER <=", value, "scheduOrder");
            return (Criteria) this;
        }

        public Criteria andScheduOrderIn(List<Integer> values) {
            addCriterion("SCHEDU_ORDER in", values, "scheduOrder");
            return (Criteria) this;
        }

        public Criteria andScheduOrderNotIn(List<Integer> values) {
            addCriterion("SCHEDU_ORDER not in", values, "scheduOrder");
            return (Criteria) this;
        }

        public Criteria andScheduOrderBetween(Integer value1, Integer value2) {
            addCriterion("SCHEDU_ORDER between", value1, value2, "scheduOrder");
            return (Criteria) this;
        }

        public Criteria andScheduOrderNotBetween(Integer value1, Integer value2) {
            addCriterion("SCHEDU_ORDER not between", value1, value2, "scheduOrder");
            return (Criteria) this;
        }

        public Criteria andPositionOrderIsNull() {
            addCriterion("POSITION_ORDER is null");
            return (Criteria) this;
        }

        public Criteria andPositionOrderIsNotNull() {
            addCriterion("POSITION_ORDER is not null");
            return (Criteria) this;
        }

        public Criteria andPositionOrderEqualTo(Integer value) {
            addCriterion("POSITION_ORDER =", value, "positionOrder");
            return (Criteria) this;
        }

        public Criteria andPositionOrderNotEqualTo(Integer value) {
            addCriterion("POSITION_ORDER <>", value, "positionOrder");
            return (Criteria) this;
        }

        public Criteria andPositionOrderGreaterThan(Integer value) {
            addCriterion("POSITION_ORDER >", value, "positionOrder");
            return (Criteria) this;
        }

        public Criteria andPositionOrderGreaterThanOrEqualTo(Integer value) {
            addCriterion("POSITION_ORDER >=", value, "positionOrder");
            return (Criteria) this;
        }

        public Criteria andPositionOrderLessThan(Integer value) {
            addCriterion("POSITION_ORDER <", value, "positionOrder");
            return (Criteria) this;
        }

        public Criteria andPositionOrderLessThanOrEqualTo(Integer value) {
            addCriterion("POSITION_ORDER <=", value, "positionOrder");
            return (Criteria) this;
        }

        public Criteria andPositionOrderIn(List<Integer> values) {
            addCriterion("POSITION_ORDER in", values, "positionOrder");
            return (Criteria) this;
        }

        public Criteria andPositionOrderNotIn(List<Integer> values) {
            addCriterion("POSITION_ORDER not in", values, "positionOrder");
            return (Criteria) this;
        }

        public Criteria andPositionOrderBetween(Integer value1, Integer value2) {
            addCriterion("POSITION_ORDER between", value1, value2, "positionOrder");
            return (Criteria) this;
        }

        public Criteria andPositionOrderNotBetween(Integer value1, Integer value2) {
            addCriterion("POSITION_ORDER not between", value1, value2, "positionOrder");
            return (Criteria) this;
        }

        public Criteria andIntervalsTypeIsNull() {
            addCriterion("INTERVALS_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andIntervalsTypeIsNotNull() {
            addCriterion("INTERVALS_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andIntervalsTypeEqualTo(String value) {
            addCriterion("INTERVALS_TYPE =", value, "intervalsType");
            return (Criteria) this;
        }

        public Criteria andIntervalsTypeNotEqualTo(String value) {
            addCriterion("INTERVALS_TYPE <>", value, "intervalsType");
            return (Criteria) this;
        }

        public Criteria andIntervalsTypeGreaterThan(String value) {
            addCriterion("INTERVALS_TYPE >", value, "intervalsType");
            return (Criteria) this;
        }

        public Criteria andIntervalsTypeGreaterThanOrEqualTo(String value) {
            addCriterion("INTERVALS_TYPE >=", value, "intervalsType");
            return (Criteria) this;
        }

        public Criteria andIntervalsTypeLessThan(String value) {
            addCriterion("INTERVALS_TYPE <", value, "intervalsType");
            return (Criteria) this;
        }

        public Criteria andIntervalsTypeLessThanOrEqualTo(String value) {
            addCriterion("INTERVALS_TYPE <=", value, "intervalsType");
            return (Criteria) this;
        }

        public Criteria andIntervalsTypeLike(String value) {
            addCriterion("INTERVALS_TYPE like", value, "intervalsType");
            return (Criteria) this;
        }

        public Criteria andIntervalsTypeNotLike(String value) {
            addCriterion("INTERVALS_TYPE not like", value, "intervalsType");
            return (Criteria) this;
        }

        public Criteria andIntervalsTypeIn(List<String> values) {
            addCriterion("INTERVALS_TYPE in", values, "intervalsType");
            return (Criteria) this;
        }

        public Criteria andIntervalsTypeNotIn(List<String> values) {
            addCriterion("INTERVALS_TYPE not in", values, "intervalsType");
            return (Criteria) this;
        }

        public Criteria andIntervalsTypeBetween(String value1, String value2) {
            addCriterion("INTERVALS_TYPE between", value1, value2, "intervalsType");
            return (Criteria) this;
        }

        public Criteria andIntervalsTypeNotBetween(String value1, String value2) {
            addCriterion("INTERVALS_TYPE not between", value1, value2, "intervalsType");
            return (Criteria) this;
        }

        public Criteria andCreateByIsNull() {
            addCriterion("CREATE_BY is null");
            return (Criteria) this;
        }

        public Criteria andCreateByIsNotNull() {
            addCriterion("CREATE_BY is not null");
            return (Criteria) this;
        }

        public Criteria andCreateByEqualTo(String value) {
            addCriterion("CREATE_BY =", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByNotEqualTo(String value) {
            addCriterion("CREATE_BY <>", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByGreaterThan(String value) {
            addCriterion("CREATE_BY >", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByGreaterThanOrEqualTo(String value) {
            addCriterion("CREATE_BY >=", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByLessThan(String value) {
            addCriterion("CREATE_BY <", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByLessThanOrEqualTo(String value) {
            addCriterion("CREATE_BY <=", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByLike(String value) {
            addCriterion("CREATE_BY like", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByNotLike(String value) {
            addCriterion("CREATE_BY not like", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByIn(List<String> values) {
            addCriterion("CREATE_BY in", values, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByNotIn(List<String> values) {
            addCriterion("CREATE_BY not in", values, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByBetween(String value1, String value2) {
            addCriterion("CREATE_BY between", value1, value2, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByNotBetween(String value1, String value2) {
            addCriterion("CREATE_BY not between", value1, value2, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("CREATE_TIME is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("CREATE_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("CREATE_TIME =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("CREATE_TIME <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("CREATE_TIME >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("CREATE_TIME >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("CREATE_TIME <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("CREATE_TIME <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("CREATE_TIME in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("CREATE_TIME not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("CREATE_TIME between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("CREATE_TIME not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andUpdateByIsNull() {
            addCriterion("UPDATE_BY is null");
            return (Criteria) this;
        }

        public Criteria andUpdateByIsNotNull() {
            addCriterion("UPDATE_BY is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateByEqualTo(String value) {
            addCriterion("UPDATE_BY =", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByNotEqualTo(String value) {
            addCriterion("UPDATE_BY <>", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByGreaterThan(String value) {
            addCriterion("UPDATE_BY >", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByGreaterThanOrEqualTo(String value) {
            addCriterion("UPDATE_BY >=", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByLessThan(String value) {
            addCriterion("UPDATE_BY <", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByLessThanOrEqualTo(String value) {
            addCriterion("UPDATE_BY <=", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByLike(String value) {
            addCriterion("UPDATE_BY like", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByNotLike(String value) {
            addCriterion("UPDATE_BY not like", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByIn(List<String> values) {
            addCriterion("UPDATE_BY in", values, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByNotIn(List<String> values) {
            addCriterion("UPDATE_BY not in", values, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByBetween(String value1, String value2) {
            addCriterion("UPDATE_BY between", value1, value2, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByNotBetween(String value1, String value2) {
            addCriterion("UPDATE_BY not between", value1, value2, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("UPDATE_TIME is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("UPDATE_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("UPDATE_TIME =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("UPDATE_TIME <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("UPDATE_TIME >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("UPDATE_TIME >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("UPDATE_TIME <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("UPDATE_TIME <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("UPDATE_TIME in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("UPDATE_TIME not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("UPDATE_TIME between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("UPDATE_TIME not between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andRemarksIsNull() {
            addCriterion("REMARKS is null");
            return (Criteria) this;
        }

        public Criteria andRemarksIsNotNull() {
            addCriterion("REMARKS is not null");
            return (Criteria) this;
        }

        public Criteria andRemarksEqualTo(String value) {
            addCriterion("REMARKS =", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksNotEqualTo(String value) {
            addCriterion("REMARKS <>", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksGreaterThan(String value) {
            addCriterion("REMARKS >", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksGreaterThanOrEqualTo(String value) {
            addCriterion("REMARKS >=", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksLessThan(String value) {
            addCriterion("REMARKS <", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksLessThanOrEqualTo(String value) {
            addCriterion("REMARKS <=", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksLike(String value) {
            addCriterion("REMARKS like", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksNotLike(String value) {
            addCriterion("REMARKS not like", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksIn(List<String> values) {
            addCriterion("REMARKS in", values, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksNotIn(List<String> values) {
            addCriterion("REMARKS not in", values, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksBetween(String value1, String value2) {
            addCriterion("REMARKS between", value1, value2, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksNotBetween(String value1, String value2) {
            addCriterion("REMARKS not between", value1, value2, "remarks");
            return (Criteria) this;
        }

        public Criteria andDelFlagIsNull() {
            addCriterion("DEL_FLAG is null");
            return (Criteria) this;
        }

        public Criteria andDelFlagIsNotNull() {
            addCriterion("DEL_FLAG is not null");
            return (Criteria) this;
        }

        public Criteria andDelFlagEqualTo(String value) {
            addCriterion("DEL_FLAG =", value, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagNotEqualTo(String value) {
            addCriterion("DEL_FLAG <>", value, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagGreaterThan(String value) {
            addCriterion("DEL_FLAG >", value, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagGreaterThanOrEqualTo(String value) {
            addCriterion("DEL_FLAG >=", value, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagLessThan(String value) {
            addCriterion("DEL_FLAG <", value, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagLessThanOrEqualTo(String value) {
            addCriterion("DEL_FLAG <=", value, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagLike(String value) {
            addCriterion("DEL_FLAG like", value, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagNotLike(String value) {
            addCriterion("DEL_FLAG not like", value, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagIn(List<String> values) {
            addCriterion("DEL_FLAG in", values, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagNotIn(List<String> values) {
            addCriterion("DEL_FLAG not in", values, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagBetween(String value1, String value2) {
            addCriterion("DEL_FLAG between", value1, value2, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagNotBetween(String value1, String value2) {
            addCriterion("DEL_FLAG not between", value1, value2, "delFlag");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}