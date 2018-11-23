package com.zzqx.mvc.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EmployeeInformationExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public EmployeeInformationExample() {
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

        public Criteria andLoginNameIsNull() {
            addCriterion("LOGIN_NAME is null");
            return (Criteria) this;
        }

        public Criteria andLoginNameIsNotNull() {
            addCriterion("LOGIN_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andLoginNameEqualTo(String value) {
            addCriterion("LOGIN_NAME =", value, "loginName");
            return (Criteria) this;
        }

        public Criteria andLoginNameNotEqualTo(String value) {
            addCriterion("LOGIN_NAME <>", value, "loginName");
            return (Criteria) this;
        }

        public Criteria andLoginNameGreaterThan(String value) {
            addCriterion("LOGIN_NAME >", value, "loginName");
            return (Criteria) this;
        }

        public Criteria andLoginNameGreaterThanOrEqualTo(String value) {
            addCriterion("LOGIN_NAME >=", value, "loginName");
            return (Criteria) this;
        }

        public Criteria andLoginNameLessThan(String value) {
            addCriterion("LOGIN_NAME <", value, "loginName");
            return (Criteria) this;
        }

        public Criteria andLoginNameLessThanOrEqualTo(String value) {
            addCriterion("LOGIN_NAME <=", value, "loginName");
            return (Criteria) this;
        }

        public Criteria andLoginNameLike(String value) {
            addCriterion("LOGIN_NAME like", value, "loginName");
            return (Criteria) this;
        }

        public Criteria andLoginNameNotLike(String value) {
            addCriterion("LOGIN_NAME not like", value, "loginName");
            return (Criteria) this;
        }

        public Criteria andLoginNameIn(List<String> values) {
            addCriterion("LOGIN_NAME in", values, "loginName");
            return (Criteria) this;
        }

        public Criteria andLoginNameNotIn(List<String> values) {
            addCriterion("LOGIN_NAME not in", values, "loginName");
            return (Criteria) this;
        }

        public Criteria andLoginNameBetween(String value1, String value2) {
            addCriterion("LOGIN_NAME between", value1, value2, "loginName");
            return (Criteria) this;
        }

        public Criteria andLoginNameNotBetween(String value1, String value2) {
            addCriterion("LOGIN_NAME not between", value1, value2, "loginName");
            return (Criteria) this;
        }

        public Criteria andPasswordIsNull() {
            addCriterion("PASSWORD is null");
            return (Criteria) this;
        }

        public Criteria andPasswordIsNotNull() {
            addCriterion("PASSWORD is not null");
            return (Criteria) this;
        }

        public Criteria andPasswordEqualTo(String value) {
            addCriterion("PASSWORD =", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotEqualTo(String value) {
            addCriterion("PASSWORD <>", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordGreaterThan(String value) {
            addCriterion("PASSWORD >", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordGreaterThanOrEqualTo(String value) {
            addCriterion("PASSWORD >=", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLessThan(String value) {
            addCriterion("PASSWORD <", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLessThanOrEqualTo(String value) {
            addCriterion("PASSWORD <=", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLike(String value) {
            addCriterion("PASSWORD like", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotLike(String value) {
            addCriterion("PASSWORD not like", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordIn(List<String> values) {
            addCriterion("PASSWORD in", values, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotIn(List<String> values) {
            addCriterion("PASSWORD not in", values, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordBetween(String value1, String value2) {
            addCriterion("PASSWORD between", value1, value2, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotBetween(String value1, String value2) {
            addCriterion("PASSWORD not between", value1, value2, "password");
            return (Criteria) this;
        }

        public Criteria andEmployeeIdIsNull() {
            addCriterion("EMPLOYEE_ID is null");
            return (Criteria) this;
        }

        public Criteria andEmployeeIdIsNotNull() {
            addCriterion("EMPLOYEE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andEmployeeIdEqualTo(String value) {
            addCriterion("EMPLOYEE_ID =", value, "employeeId");
            return (Criteria) this;
        }

        public Criteria andEmployeeIdNotEqualTo(String value) {
            addCriterion("EMPLOYEE_ID <>", value, "employeeId");
            return (Criteria) this;
        }

        public Criteria andEmployeeIdGreaterThan(String value) {
            addCriterion("EMPLOYEE_ID >", value, "employeeId");
            return (Criteria) this;
        }

        public Criteria andEmployeeIdGreaterThanOrEqualTo(String value) {
            addCriterion("EMPLOYEE_ID >=", value, "employeeId");
            return (Criteria) this;
        }

        public Criteria andEmployeeIdLessThan(String value) {
            addCriterion("EMPLOYEE_ID <", value, "employeeId");
            return (Criteria) this;
        }

        public Criteria andEmployeeIdLessThanOrEqualTo(String value) {
            addCriterion("EMPLOYEE_ID <=", value, "employeeId");
            return (Criteria) this;
        }

        public Criteria andEmployeeIdLike(String value) {
            addCriterion("EMPLOYEE_ID like", value, "employeeId");
            return (Criteria) this;
        }

        public Criteria andEmployeeIdNotLike(String value) {
            addCriterion("EMPLOYEE_ID not like", value, "employeeId");
            return (Criteria) this;
        }

        public Criteria andEmployeeIdIn(List<String> values) {
            addCriterion("EMPLOYEE_ID in", values, "employeeId");
            return (Criteria) this;
        }

        public Criteria andEmployeeIdNotIn(List<String> values) {
            addCriterion("EMPLOYEE_ID not in", values, "employeeId");
            return (Criteria) this;
        }

        public Criteria andEmployeeIdBetween(String value1, String value2) {
            addCriterion("EMPLOYEE_ID between", value1, value2, "employeeId");
            return (Criteria) this;
        }

        public Criteria andEmployeeIdNotBetween(String value1, String value2) {
            addCriterion("EMPLOYEE_ID not between", value1, value2, "employeeId");
            return (Criteria) this;
        }

        public Criteria andEmployeeAccountIsNull() {
            addCriterion("EMPLOYEE_ACCOUNT is null");
            return (Criteria) this;
        }

        public Criteria andEmployeeAccountIsNotNull() {
            addCriterion("EMPLOYEE_ACCOUNT is not null");
            return (Criteria) this;
        }

        public Criteria andEmployeeAccountEqualTo(String value) {
            addCriterion("EMPLOYEE_ACCOUNT =", value, "employeeAccount");
            return (Criteria) this;
        }

        public Criteria andEmployeeAccountNotEqualTo(String value) {
            addCriterion("EMPLOYEE_ACCOUNT <>", value, "employeeAccount");
            return (Criteria) this;
        }

        public Criteria andEmployeeAccountGreaterThan(String value) {
            addCriterion("EMPLOYEE_ACCOUNT >", value, "employeeAccount");
            return (Criteria) this;
        }

        public Criteria andEmployeeAccountGreaterThanOrEqualTo(String value) {
            addCriterion("EMPLOYEE_ACCOUNT >=", value, "employeeAccount");
            return (Criteria) this;
        }

        public Criteria andEmployeeAccountLessThan(String value) {
            addCriterion("EMPLOYEE_ACCOUNT <", value, "employeeAccount");
            return (Criteria) this;
        }

        public Criteria andEmployeeAccountLessThanOrEqualTo(String value) {
            addCriterion("EMPLOYEE_ACCOUNT <=", value, "employeeAccount");
            return (Criteria) this;
        }

        public Criteria andEmployeeAccountLike(String value) {
            addCriterion("EMPLOYEE_ACCOUNT like", value, "employeeAccount");
            return (Criteria) this;
        }

        public Criteria andEmployeeAccountNotLike(String value) {
            addCriterion("EMPLOYEE_ACCOUNT not like", value, "employeeAccount");
            return (Criteria) this;
        }

        public Criteria andEmployeeAccountIn(List<String> values) {
            addCriterion("EMPLOYEE_ACCOUNT in", values, "employeeAccount");
            return (Criteria) this;
        }

        public Criteria andEmployeeAccountNotIn(List<String> values) {
            addCriterion("EMPLOYEE_ACCOUNT not in", values, "employeeAccount");
            return (Criteria) this;
        }

        public Criteria andEmployeeAccountBetween(String value1, String value2) {
            addCriterion("EMPLOYEE_ACCOUNT between", value1, value2, "employeeAccount");
            return (Criteria) this;
        }

        public Criteria andEmployeeAccountNotBetween(String value1, String value2) {
            addCriterion("EMPLOYEE_ACCOUNT not between", value1, value2, "employeeAccount");
            return (Criteria) this;
        }

        public Criteria andNameIsNull() {
            addCriterion("NAME is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("NAME is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("NAME =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("NAME <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("NAME >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("NAME >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("NAME <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("NAME <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("NAME like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("NAME not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("NAME in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("NAME not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("NAME between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("NAME not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andEmployeeAgeIsNull() {
            addCriterion("EMPLOYEE_AGE is null");
            return (Criteria) this;
        }

        public Criteria andEmployeeAgeIsNotNull() {
            addCriterion("EMPLOYEE_AGE is not null");
            return (Criteria) this;
        }

        public Criteria andEmployeeAgeEqualTo(String value) {
            addCriterion("EMPLOYEE_AGE =", value, "employeeAge");
            return (Criteria) this;
        }

        public Criteria andEmployeeAgeNotEqualTo(String value) {
            addCriterion("EMPLOYEE_AGE <>", value, "employeeAge");
            return (Criteria) this;
        }

        public Criteria andEmployeeAgeGreaterThan(String value) {
            addCriterion("EMPLOYEE_AGE >", value, "employeeAge");
            return (Criteria) this;
        }

        public Criteria andEmployeeAgeGreaterThanOrEqualTo(String value) {
            addCriterion("EMPLOYEE_AGE >=", value, "employeeAge");
            return (Criteria) this;
        }

        public Criteria andEmployeeAgeLessThan(String value) {
            addCriterion("EMPLOYEE_AGE <", value, "employeeAge");
            return (Criteria) this;
        }

        public Criteria andEmployeeAgeLessThanOrEqualTo(String value) {
            addCriterion("EMPLOYEE_AGE <=", value, "employeeAge");
            return (Criteria) this;
        }

        public Criteria andEmployeeAgeLike(String value) {
            addCriterion("EMPLOYEE_AGE like", value, "employeeAge");
            return (Criteria) this;
        }

        public Criteria andEmployeeAgeNotLike(String value) {
            addCriterion("EMPLOYEE_AGE not like", value, "employeeAge");
            return (Criteria) this;
        }

        public Criteria andEmployeeAgeIn(List<String> values) {
            addCriterion("EMPLOYEE_AGE in", values, "employeeAge");
            return (Criteria) this;
        }

        public Criteria andEmployeeAgeNotIn(List<String> values) {
            addCriterion("EMPLOYEE_AGE not in", values, "employeeAge");
            return (Criteria) this;
        }

        public Criteria andEmployeeAgeBetween(String value1, String value2) {
            addCriterion("EMPLOYEE_AGE between", value1, value2, "employeeAge");
            return (Criteria) this;
        }

        public Criteria andEmployeeAgeNotBetween(String value1, String value2) {
            addCriterion("EMPLOYEE_AGE not between", value1, value2, "employeeAge");
            return (Criteria) this;
        }

        public Criteria andBusinessTodayIsNull() {
            addCriterion("BUSINESS_TODAY is null");
            return (Criteria) this;
        }

        public Criteria andBusinessTodayIsNotNull() {
            addCriterion("BUSINESS_TODAY is not null");
            return (Criteria) this;
        }

        public Criteria andBusinessTodayEqualTo(String value) {
            addCriterion("BUSINESS_TODAY =", value, "businessToday");
            return (Criteria) this;
        }

        public Criteria andBusinessTodayNotEqualTo(String value) {
            addCriterion("BUSINESS_TODAY <>", value, "businessToday");
            return (Criteria) this;
        }

        public Criteria andBusinessTodayGreaterThan(String value) {
            addCriterion("BUSINESS_TODAY >", value, "businessToday");
            return (Criteria) this;
        }

        public Criteria andBusinessTodayGreaterThanOrEqualTo(String value) {
            addCriterion("BUSINESS_TODAY >=", value, "businessToday");
            return (Criteria) this;
        }

        public Criteria andBusinessTodayLessThan(String value) {
            addCriterion("BUSINESS_TODAY <", value, "businessToday");
            return (Criteria) this;
        }

        public Criteria andBusinessTodayLessThanOrEqualTo(String value) {
            addCriterion("BUSINESS_TODAY <=", value, "businessToday");
            return (Criteria) this;
        }

        public Criteria andBusinessTodayLike(String value) {
            addCriterion("BUSINESS_TODAY like", value, "businessToday");
            return (Criteria) this;
        }

        public Criteria andBusinessTodayNotLike(String value) {
            addCriterion("BUSINESS_TODAY not like", value, "businessToday");
            return (Criteria) this;
        }

        public Criteria andBusinessTodayIn(List<String> values) {
            addCriterion("BUSINESS_TODAY in", values, "businessToday");
            return (Criteria) this;
        }

        public Criteria andBusinessTodayNotIn(List<String> values) {
            addCriterion("BUSINESS_TODAY not in", values, "businessToday");
            return (Criteria) this;
        }

        public Criteria andBusinessTodayBetween(String value1, String value2) {
            addCriterion("BUSINESS_TODAY between", value1, value2, "businessToday");
            return (Criteria) this;
        }

        public Criteria andBusinessTodayNotBetween(String value1, String value2) {
            addCriterion("BUSINESS_TODAY not between", value1, value2, "businessToday");
            return (Criteria) this;
        }

        public Criteria andAverageProcessTimeIsNull() {
            addCriterion("AVERAGE_PROCESS_TIME is null");
            return (Criteria) this;
        }

        public Criteria andAverageProcessTimeIsNotNull() {
            addCriterion("AVERAGE_PROCESS_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andAverageProcessTimeEqualTo(String value) {
            addCriterion("AVERAGE_PROCESS_TIME =", value, "averageProcessTime");
            return (Criteria) this;
        }

        public Criteria andAverageProcessTimeNotEqualTo(String value) {
            addCriterion("AVERAGE_PROCESS_TIME <>", value, "averageProcessTime");
            return (Criteria) this;
        }

        public Criteria andAverageProcessTimeGreaterThan(String value) {
            addCriterion("AVERAGE_PROCESS_TIME >", value, "averageProcessTime");
            return (Criteria) this;
        }

        public Criteria andAverageProcessTimeGreaterThanOrEqualTo(String value) {
            addCriterion("AVERAGE_PROCESS_TIME >=", value, "averageProcessTime");
            return (Criteria) this;
        }

        public Criteria andAverageProcessTimeLessThan(String value) {
            addCriterion("AVERAGE_PROCESS_TIME <", value, "averageProcessTime");
            return (Criteria) this;
        }

        public Criteria andAverageProcessTimeLessThanOrEqualTo(String value) {
            addCriterion("AVERAGE_PROCESS_TIME <=", value, "averageProcessTime");
            return (Criteria) this;
        }

        public Criteria andAverageProcessTimeLike(String value) {
            addCriterion("AVERAGE_PROCESS_TIME like", value, "averageProcessTime");
            return (Criteria) this;
        }

        public Criteria andAverageProcessTimeNotLike(String value) {
            addCriterion("AVERAGE_PROCESS_TIME not like", value, "averageProcessTime");
            return (Criteria) this;
        }

        public Criteria andAverageProcessTimeIn(List<String> values) {
            addCriterion("AVERAGE_PROCESS_TIME in", values, "averageProcessTime");
            return (Criteria) this;
        }

        public Criteria andAverageProcessTimeNotIn(List<String> values) {
            addCriterion("AVERAGE_PROCESS_TIME not in", values, "averageProcessTime");
            return (Criteria) this;
        }

        public Criteria andAverageProcessTimeBetween(String value1, String value2) {
            addCriterion("AVERAGE_PROCESS_TIME between", value1, value2, "averageProcessTime");
            return (Criteria) this;
        }

        public Criteria andAverageProcessTimeNotBetween(String value1, String value2) {
            addCriterion("AVERAGE_PROCESS_TIME not between", value1, value2, "averageProcessTime");
            return (Criteria) this;
        }

        public Criteria andEmployeeStateIsNull() {
            addCriterion("EMPLOYEE_STATE is null");
            return (Criteria) this;
        }

        public Criteria andEmployeeStateIsNotNull() {
            addCriterion("EMPLOYEE_STATE is not null");
            return (Criteria) this;
        }

        public Criteria andEmployeeStateEqualTo(String value) {
            addCriterion("EMPLOYEE_STATE =", value, "employeeState");
            return (Criteria) this;
        }

        public Criteria andEmployeeStateNotEqualTo(String value) {
            addCriterion("EMPLOYEE_STATE <>", value, "employeeState");
            return (Criteria) this;
        }

        public Criteria andEmployeeStateGreaterThan(String value) {
            addCriterion("EMPLOYEE_STATE >", value, "employeeState");
            return (Criteria) this;
        }

        public Criteria andEmployeeStateGreaterThanOrEqualTo(String value) {
            addCriterion("EMPLOYEE_STATE >=", value, "employeeState");
            return (Criteria) this;
        }

        public Criteria andEmployeeStateLessThan(String value) {
            addCriterion("EMPLOYEE_STATE <", value, "employeeState");
            return (Criteria) this;
        }

        public Criteria andEmployeeStateLessThanOrEqualTo(String value) {
            addCriterion("EMPLOYEE_STATE <=", value, "employeeState");
            return (Criteria) this;
        }

        public Criteria andEmployeeStateLike(String value) {
            addCriterion("EMPLOYEE_STATE like", value, "employeeState");
            return (Criteria) this;
        }

        public Criteria andEmployeeStateNotLike(String value) {
            addCriterion("EMPLOYEE_STATE not like", value, "employeeState");
            return (Criteria) this;
        }

        public Criteria andEmployeeStateIn(List<String> values) {
            addCriterion("EMPLOYEE_STATE in", values, "employeeState");
            return (Criteria) this;
        }

        public Criteria andEmployeeStateNotIn(List<String> values) {
            addCriterion("EMPLOYEE_STATE not in", values, "employeeState");
            return (Criteria) this;
        }

        public Criteria andEmployeeStateBetween(String value1, String value2) {
            addCriterion("EMPLOYEE_STATE between", value1, value2, "employeeState");
            return (Criteria) this;
        }

        public Criteria andEmployeeStateNotBetween(String value1, String value2) {
            addCriterion("EMPLOYEE_STATE not between", value1, value2, "employeeState");
            return (Criteria) this;
        }

        public Criteria andEmployeeSexIsNull() {
            addCriterion("EMPLOYEE_SEX is null");
            return (Criteria) this;
        }

        public Criteria andEmployeeSexIsNotNull() {
            addCriterion("EMPLOYEE_SEX is not null");
            return (Criteria) this;
        }

        public Criteria andEmployeeSexEqualTo(String value) {
            addCriterion("EMPLOYEE_SEX =", value, "employeeSex");
            return (Criteria) this;
        }

        public Criteria andEmployeeSexNotEqualTo(String value) {
            addCriterion("EMPLOYEE_SEX <>", value, "employeeSex");
            return (Criteria) this;
        }

        public Criteria andEmployeeSexGreaterThan(String value) {
            addCriterion("EMPLOYEE_SEX >", value, "employeeSex");
            return (Criteria) this;
        }

        public Criteria andEmployeeSexGreaterThanOrEqualTo(String value) {
            addCriterion("EMPLOYEE_SEX >=", value, "employeeSex");
            return (Criteria) this;
        }

        public Criteria andEmployeeSexLessThan(String value) {
            addCriterion("EMPLOYEE_SEX <", value, "employeeSex");
            return (Criteria) this;
        }

        public Criteria andEmployeeSexLessThanOrEqualTo(String value) {
            addCriterion("EMPLOYEE_SEX <=", value, "employeeSex");
            return (Criteria) this;
        }

        public Criteria andEmployeeSexLike(String value) {
            addCriterion("EMPLOYEE_SEX like", value, "employeeSex");
            return (Criteria) this;
        }

        public Criteria andEmployeeSexNotLike(String value) {
            addCriterion("EMPLOYEE_SEX not like", value, "employeeSex");
            return (Criteria) this;
        }

        public Criteria andEmployeeSexIn(List<String> values) {
            addCriterion("EMPLOYEE_SEX in", values, "employeeSex");
            return (Criteria) this;
        }

        public Criteria andEmployeeSexNotIn(List<String> values) {
            addCriterion("EMPLOYEE_SEX not in", values, "employeeSex");
            return (Criteria) this;
        }

        public Criteria andEmployeeSexBetween(String value1, String value2) {
            addCriterion("EMPLOYEE_SEX between", value1, value2, "employeeSex");
            return (Criteria) this;
        }

        public Criteria andEmployeeSexNotBetween(String value1, String value2) {
            addCriterion("EMPLOYEE_SEX not between", value1, value2, "employeeSex");
            return (Criteria) this;
        }

        public Criteria andEmployeeEducationIsNull() {
            addCriterion("EMPLOYEE_EDUCATION is null");
            return (Criteria) this;
        }

        public Criteria andEmployeeEducationIsNotNull() {
            addCriterion("EMPLOYEE_EDUCATION is not null");
            return (Criteria) this;
        }

        public Criteria andEmployeeEducationEqualTo(String value) {
            addCriterion("EMPLOYEE_EDUCATION =", value, "employeeEducation");
            return (Criteria) this;
        }

        public Criteria andEmployeeEducationNotEqualTo(String value) {
            addCriterion("EMPLOYEE_EDUCATION <>", value, "employeeEducation");
            return (Criteria) this;
        }

        public Criteria andEmployeeEducationGreaterThan(String value) {
            addCriterion("EMPLOYEE_EDUCATION >", value, "employeeEducation");
            return (Criteria) this;
        }

        public Criteria andEmployeeEducationGreaterThanOrEqualTo(String value) {
            addCriterion("EMPLOYEE_EDUCATION >=", value, "employeeEducation");
            return (Criteria) this;
        }

        public Criteria andEmployeeEducationLessThan(String value) {
            addCriterion("EMPLOYEE_EDUCATION <", value, "employeeEducation");
            return (Criteria) this;
        }

        public Criteria andEmployeeEducationLessThanOrEqualTo(String value) {
            addCriterion("EMPLOYEE_EDUCATION <=", value, "employeeEducation");
            return (Criteria) this;
        }

        public Criteria andEmployeeEducationLike(String value) {
            addCriterion("EMPLOYEE_EDUCATION like", value, "employeeEducation");
            return (Criteria) this;
        }

        public Criteria andEmployeeEducationNotLike(String value) {
            addCriterion("EMPLOYEE_EDUCATION not like", value, "employeeEducation");
            return (Criteria) this;
        }

        public Criteria andEmployeeEducationIn(List<String> values) {
            addCriterion("EMPLOYEE_EDUCATION in", values, "employeeEducation");
            return (Criteria) this;
        }

        public Criteria andEmployeeEducationNotIn(List<String> values) {
            addCriterion("EMPLOYEE_EDUCATION not in", values, "employeeEducation");
            return (Criteria) this;
        }

        public Criteria andEmployeeEducationBetween(String value1, String value2) {
            addCriterion("EMPLOYEE_EDUCATION between", value1, value2, "employeeEducation");
            return (Criteria) this;
        }

        public Criteria andEmployeeEducationNotBetween(String value1, String value2) {
            addCriterion("EMPLOYEE_EDUCATION not between", value1, value2, "employeeEducation");
            return (Criteria) this;
        }

        public Criteria andEmailIsNull() {
            addCriterion("EMAIL is null");
            return (Criteria) this;
        }

        public Criteria andEmailIsNotNull() {
            addCriterion("EMAIL is not null");
            return (Criteria) this;
        }

        public Criteria andEmailEqualTo(String value) {
            addCriterion("EMAIL =", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotEqualTo(String value) {
            addCriterion("EMAIL <>", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailGreaterThan(String value) {
            addCriterion("EMAIL >", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailGreaterThanOrEqualTo(String value) {
            addCriterion("EMAIL >=", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLessThan(String value) {
            addCriterion("EMAIL <", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLessThanOrEqualTo(String value) {
            addCriterion("EMAIL <=", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLike(String value) {
            addCriterion("EMAIL like", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotLike(String value) {
            addCriterion("EMAIL not like", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailIn(List<String> values) {
            addCriterion("EMAIL in", values, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotIn(List<String> values) {
            addCriterion("EMAIL not in", values, "email");
            return (Criteria) this;
        }

        public Criteria andEmailBetween(String value1, String value2) {
            addCriterion("EMAIL between", value1, value2, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotBetween(String value1, String value2) {
            addCriterion("EMAIL not between", value1, value2, "email");
            return (Criteria) this;
        }

        public Criteria andPhoneIsNull() {
            addCriterion("PHONE is null");
            return (Criteria) this;
        }

        public Criteria andPhoneIsNotNull() {
            addCriterion("PHONE is not null");
            return (Criteria) this;
        }

        public Criteria andPhoneEqualTo(String value) {
            addCriterion("PHONE =", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotEqualTo(String value) {
            addCriterion("PHONE <>", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneGreaterThan(String value) {
            addCriterion("PHONE >", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("PHONE >=", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLessThan(String value) {
            addCriterion("PHONE <", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLessThanOrEqualTo(String value) {
            addCriterion("PHONE <=", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLike(String value) {
            addCriterion("PHONE like", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotLike(String value) {
            addCriterion("PHONE not like", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneIn(List<String> values) {
            addCriterion("PHONE in", values, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotIn(List<String> values) {
            addCriterion("PHONE not in", values, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneBetween(String value1, String value2) {
            addCriterion("PHONE between", value1, value2, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotBetween(String value1, String value2) {
            addCriterion("PHONE not between", value1, value2, "phone");
            return (Criteria) this;
        }

        public Criteria andMobileIsNull() {
            addCriterion("MOBILE is null");
            return (Criteria) this;
        }

        public Criteria andMobileIsNotNull() {
            addCriterion("MOBILE is not null");
            return (Criteria) this;
        }

        public Criteria andMobileEqualTo(String value) {
            addCriterion("MOBILE =", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileNotEqualTo(String value) {
            addCriterion("MOBILE <>", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileGreaterThan(String value) {
            addCriterion("MOBILE >", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileGreaterThanOrEqualTo(String value) {
            addCriterion("MOBILE >=", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileLessThan(String value) {
            addCriterion("MOBILE <", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileLessThanOrEqualTo(String value) {
            addCriterion("MOBILE <=", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileLike(String value) {
            addCriterion("MOBILE like", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileNotLike(String value) {
            addCriterion("MOBILE not like", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileIn(List<String> values) {
            addCriterion("MOBILE in", values, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileNotIn(List<String> values) {
            addCriterion("MOBILE not in", values, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileBetween(String value1, String value2) {
            addCriterion("MOBILE between", value1, value2, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileNotBetween(String value1, String value2) {
            addCriterion("MOBILE not between", value1, value2, "mobile");
            return (Criteria) this;
        }

        public Criteria andUserTypeIsNull() {
            addCriterion("USER_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andUserTypeIsNotNull() {
            addCriterion("USER_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andUserTypeEqualTo(String value) {
            addCriterion("USER_TYPE =", value, "userType");
            return (Criteria) this;
        }

        public Criteria andUserTypeNotEqualTo(String value) {
            addCriterion("USER_TYPE <>", value, "userType");
            return (Criteria) this;
        }

        public Criteria andUserTypeGreaterThan(String value) {
            addCriterion("USER_TYPE >", value, "userType");
            return (Criteria) this;
        }

        public Criteria andUserTypeGreaterThanOrEqualTo(String value) {
            addCriterion("USER_TYPE >=", value, "userType");
            return (Criteria) this;
        }

        public Criteria andUserTypeLessThan(String value) {
            addCriterion("USER_TYPE <", value, "userType");
            return (Criteria) this;
        }

        public Criteria andUserTypeLessThanOrEqualTo(String value) {
            addCriterion("USER_TYPE <=", value, "userType");
            return (Criteria) this;
        }

        public Criteria andUserTypeLike(String value) {
            addCriterion("USER_TYPE like", value, "userType");
            return (Criteria) this;
        }

        public Criteria andUserTypeNotLike(String value) {
            addCriterion("USER_TYPE not like", value, "userType");
            return (Criteria) this;
        }

        public Criteria andUserTypeIn(List<String> values) {
            addCriterion("USER_TYPE in", values, "userType");
            return (Criteria) this;
        }

        public Criteria andUserTypeNotIn(List<String> values) {
            addCriterion("USER_TYPE not in", values, "userType");
            return (Criteria) this;
        }

        public Criteria andUserTypeBetween(String value1, String value2) {
            addCriterion("USER_TYPE between", value1, value2, "userType");
            return (Criteria) this;
        }

        public Criteria andUserTypeNotBetween(String value1, String value2) {
            addCriterion("USER_TYPE not between", value1, value2, "userType");
            return (Criteria) this;
        }

        public Criteria andLoginIpIsNull() {
            addCriterion("LOGIN_IP is null");
            return (Criteria) this;
        }

        public Criteria andLoginIpIsNotNull() {
            addCriterion("LOGIN_IP is not null");
            return (Criteria) this;
        }

        public Criteria andLoginIpEqualTo(String value) {
            addCriterion("LOGIN_IP =", value, "loginIp");
            return (Criteria) this;
        }

        public Criteria andLoginIpNotEqualTo(String value) {
            addCriterion("LOGIN_IP <>", value, "loginIp");
            return (Criteria) this;
        }

        public Criteria andLoginIpGreaterThan(String value) {
            addCriterion("LOGIN_IP >", value, "loginIp");
            return (Criteria) this;
        }

        public Criteria andLoginIpGreaterThanOrEqualTo(String value) {
            addCriterion("LOGIN_IP >=", value, "loginIp");
            return (Criteria) this;
        }

        public Criteria andLoginIpLessThan(String value) {
            addCriterion("LOGIN_IP <", value, "loginIp");
            return (Criteria) this;
        }

        public Criteria andLoginIpLessThanOrEqualTo(String value) {
            addCriterion("LOGIN_IP <=", value, "loginIp");
            return (Criteria) this;
        }

        public Criteria andLoginIpLike(String value) {
            addCriterion("LOGIN_IP like", value, "loginIp");
            return (Criteria) this;
        }

        public Criteria andLoginIpNotLike(String value) {
            addCriterion("LOGIN_IP not like", value, "loginIp");
            return (Criteria) this;
        }

        public Criteria andLoginIpIn(List<String> values) {
            addCriterion("LOGIN_IP in", values, "loginIp");
            return (Criteria) this;
        }

        public Criteria andLoginIpNotIn(List<String> values) {
            addCriterion("LOGIN_IP not in", values, "loginIp");
            return (Criteria) this;
        }

        public Criteria andLoginIpBetween(String value1, String value2) {
            addCriterion("LOGIN_IP between", value1, value2, "loginIp");
            return (Criteria) this;
        }

        public Criteria andLoginIpNotBetween(String value1, String value2) {
            addCriterion("LOGIN_IP not between", value1, value2, "loginIp");
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

        public Criteria andHallIdEqualTo(Integer value) {
            addCriterion("HALL_ID =", value, "hallId");
            return (Criteria) this;
        }

        public Criteria andHallIdNotEqualTo(Integer value) {
            addCriterion("HALL_ID <>", value, "hallId");
            return (Criteria) this;
        }

        public Criteria andHallIdGreaterThan(Integer value) {
            addCriterion("HALL_ID >", value, "hallId");
            return (Criteria) this;
        }

        public Criteria andHallIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("HALL_ID >=", value, "hallId");
            return (Criteria) this;
        }

        public Criteria andHallIdLessThan(Integer value) {
            addCriterion("HALL_ID <", value, "hallId");
            return (Criteria) this;
        }

        public Criteria andHallIdLessThanOrEqualTo(Integer value) {
            addCriterion("HALL_ID <=", value, "hallId");
            return (Criteria) this;
        }

        public Criteria andHallIdIn(List<Integer> values) {
            addCriterion("HALL_ID in", values, "hallId");
            return (Criteria) this;
        }

        public Criteria andHallIdNotIn(List<Integer> values) {
            addCriterion("HALL_ID not in", values, "hallId");
            return (Criteria) this;
        }

        public Criteria andHallIdBetween(Integer value1, Integer value2) {
            addCriterion("HALL_ID between", value1, value2, "hallId");
            return (Criteria) this;
        }

        public Criteria andHallIdNotBetween(Integer value1, Integer value2) {
            addCriterion("HALL_ID not between", value1, value2, "hallId");
            return (Criteria) this;
        }

        public Criteria andJobsIsNull() {
            addCriterion("JOBS is null");
            return (Criteria) this;
        }

        public Criteria andJobsIsNotNull() {
            addCriterion("JOBS is not null");
            return (Criteria) this;
        }

        public Criteria andJobsEqualTo(String value) {
            addCriterion("JOBS =", value, "jobs");
            return (Criteria) this;
        }

        public Criteria andJobsNotEqualTo(String value) {
            addCriterion("JOBS <>", value, "jobs");
            return (Criteria) this;
        }

        public Criteria andJobsGreaterThan(String value) {
            addCriterion("JOBS >", value, "jobs");
            return (Criteria) this;
        }

        public Criteria andJobsGreaterThanOrEqualTo(String value) {
            addCriterion("JOBS >=", value, "jobs");
            return (Criteria) this;
        }

        public Criteria andJobsLessThan(String value) {
            addCriterion("JOBS <", value, "jobs");
            return (Criteria) this;
        }

        public Criteria andJobsLessThanOrEqualTo(String value) {
            addCriterion("JOBS <=", value, "jobs");
            return (Criteria) this;
        }

        public Criteria andJobsLike(String value) {
            addCriterion("JOBS like", value, "jobs");
            return (Criteria) this;
        }

        public Criteria andJobsNotLike(String value) {
            addCriterion("JOBS not like", value, "jobs");
            return (Criteria) this;
        }

        public Criteria andJobsIn(List<String> values) {
            addCriterion("JOBS in", values, "jobs");
            return (Criteria) this;
        }

        public Criteria andJobsNotIn(List<String> values) {
            addCriterion("JOBS not in", values, "jobs");
            return (Criteria) this;
        }

        public Criteria andJobsBetween(String value1, String value2) {
            addCriterion("JOBS between", value1, value2, "jobs");
            return (Criteria) this;
        }

        public Criteria andJobsNotBetween(String value1, String value2) {
            addCriterion("JOBS not between", value1, value2, "jobs");
            return (Criteria) this;
        }

        public Criteria andLoginDateIsNull() {
            addCriterion("LOGIN_DATE is null");
            return (Criteria) this;
        }

        public Criteria andLoginDateIsNotNull() {
            addCriterion("LOGIN_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andLoginDateEqualTo(Date value) {
            addCriterion("LOGIN_DATE =", value, "loginDate");
            return (Criteria) this;
        }

        public Criteria andLoginDateNotEqualTo(Date value) {
            addCriterion("LOGIN_DATE <>", value, "loginDate");
            return (Criteria) this;
        }

        public Criteria andLoginDateGreaterThan(Date value) {
            addCriterion("LOGIN_DATE >", value, "loginDate");
            return (Criteria) this;
        }

        public Criteria andLoginDateGreaterThanOrEqualTo(Date value) {
            addCriterion("LOGIN_DATE >=", value, "loginDate");
            return (Criteria) this;
        }

        public Criteria andLoginDateLessThan(Date value) {
            addCriterion("LOGIN_DATE <", value, "loginDate");
            return (Criteria) this;
        }

        public Criteria andLoginDateLessThanOrEqualTo(Date value) {
            addCriterion("LOGIN_DATE <=", value, "loginDate");
            return (Criteria) this;
        }

        public Criteria andLoginDateIn(List<Date> values) {
            addCriterion("LOGIN_DATE in", values, "loginDate");
            return (Criteria) this;
        }

        public Criteria andLoginDateNotIn(List<Date> values) {
            addCriterion("LOGIN_DATE not in", values, "loginDate");
            return (Criteria) this;
        }

        public Criteria andLoginDateBetween(Date value1, Date value2) {
            addCriterion("LOGIN_DATE between", value1, value2, "loginDate");
            return (Criteria) this;
        }

        public Criteria andLoginDateNotBetween(Date value1, Date value2) {
            addCriterion("LOGIN_DATE not between", value1, value2, "loginDate");
            return (Criteria) this;
        }

        public Criteria andLoginFlagIsNull() {
            addCriterion("LOGIN_FLAG is null");
            return (Criteria) this;
        }

        public Criteria andLoginFlagIsNotNull() {
            addCriterion("LOGIN_FLAG is not null");
            return (Criteria) this;
        }

        public Criteria andLoginFlagEqualTo(String value) {
            addCriterion("LOGIN_FLAG =", value, "loginFlag");
            return (Criteria) this;
        }

        public Criteria andLoginFlagNotEqualTo(String value) {
            addCriterion("LOGIN_FLAG <>", value, "loginFlag");
            return (Criteria) this;
        }

        public Criteria andLoginFlagGreaterThan(String value) {
            addCriterion("LOGIN_FLAG >", value, "loginFlag");
            return (Criteria) this;
        }

        public Criteria andLoginFlagGreaterThanOrEqualTo(String value) {
            addCriterion("LOGIN_FLAG >=", value, "loginFlag");
            return (Criteria) this;
        }

        public Criteria andLoginFlagLessThan(String value) {
            addCriterion("LOGIN_FLAG <", value, "loginFlag");
            return (Criteria) this;
        }

        public Criteria andLoginFlagLessThanOrEqualTo(String value) {
            addCriterion("LOGIN_FLAG <=", value, "loginFlag");
            return (Criteria) this;
        }

        public Criteria andLoginFlagLike(String value) {
            addCriterion("LOGIN_FLAG like", value, "loginFlag");
            return (Criteria) this;
        }

        public Criteria andLoginFlagNotLike(String value) {
            addCriterion("LOGIN_FLAG not like", value, "loginFlag");
            return (Criteria) this;
        }

        public Criteria andLoginFlagIn(List<String> values) {
            addCriterion("LOGIN_FLAG in", values, "loginFlag");
            return (Criteria) this;
        }

        public Criteria andLoginFlagNotIn(List<String> values) {
            addCriterion("LOGIN_FLAG not in", values, "loginFlag");
            return (Criteria) this;
        }

        public Criteria andLoginFlagBetween(String value1, String value2) {
            addCriterion("LOGIN_FLAG between", value1, value2, "loginFlag");
            return (Criteria) this;
        }

        public Criteria andLoginFlagNotBetween(String value1, String value2) {
            addCriterion("LOGIN_FLAG not between", value1, value2, "loginFlag");
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

        public Criteria andEmployeeBirthdayIsNull() {
            addCriterion("EMPLOYEE_BIRTHDAY is null");
            return (Criteria) this;
        }

        public Criteria andEmployeeBirthdayIsNotNull() {
            addCriterion("EMPLOYEE_BIRTHDAY is not null");
            return (Criteria) this;
        }

        public Criteria andEmployeeBirthdayEqualTo(Date value) {
            addCriterion("EMPLOYEE_BIRTHDAY =", value, "employeeBirthday");
            return (Criteria) this;
        }

        public Criteria andEmployeeBirthdayNotEqualTo(Date value) {
            addCriterion("EMPLOYEE_BIRTHDAY <>", value, "employeeBirthday");
            return (Criteria) this;
        }

        public Criteria andEmployeeBirthdayGreaterThan(Date value) {
            addCriterion("EMPLOYEE_BIRTHDAY >", value, "employeeBirthday");
            return (Criteria) this;
        }

        public Criteria andEmployeeBirthdayGreaterThanOrEqualTo(Date value) {
            addCriterion("EMPLOYEE_BIRTHDAY >=", value, "employeeBirthday");
            return (Criteria) this;
        }

        public Criteria andEmployeeBirthdayLessThan(Date value) {
            addCriterion("EMPLOYEE_BIRTHDAY <", value, "employeeBirthday");
            return (Criteria) this;
        }

        public Criteria andEmployeeBirthdayLessThanOrEqualTo(Date value) {
            addCriterion("EMPLOYEE_BIRTHDAY <=", value, "employeeBirthday");
            return (Criteria) this;
        }

        public Criteria andEmployeeBirthdayIn(List<Date> values) {
            addCriterion("EMPLOYEE_BIRTHDAY in", values, "employeeBirthday");
            return (Criteria) this;
        }

        public Criteria andEmployeeBirthdayNotIn(List<Date> values) {
            addCriterion("EMPLOYEE_BIRTHDAY not in", values, "employeeBirthday");
            return (Criteria) this;
        }

        public Criteria andEmployeeBirthdayBetween(Date value1, Date value2) {
            addCriterion("EMPLOYEE_BIRTHDAY between", value1, value2, "employeeBirthday");
            return (Criteria) this;
        }

        public Criteria andEmployeeBirthdayNotBetween(Date value1, Date value2) {
            addCriterion("EMPLOYEE_BIRTHDAY not between", value1, value2, "employeeBirthday");
            return (Criteria) this;
        }

        public Criteria andMonitorFlagIsNull() {
            addCriterion("MONITOR_FLAG is null");
            return (Criteria) this;
        }

        public Criteria andMonitorFlagIsNotNull() {
            addCriterion("MONITOR_FLAG is not null");
            return (Criteria) this;
        }

        public Criteria andMonitorFlagEqualTo(String value) {
            addCriterion("MONITOR_FLAG =", value, "monitorFlag");
            return (Criteria) this;
        }

        public Criteria andMonitorFlagNotEqualTo(String value) {
            addCriterion("MONITOR_FLAG <>", value, "monitorFlag");
            return (Criteria) this;
        }

        public Criteria andMonitorFlagGreaterThan(String value) {
            addCriterion("MONITOR_FLAG >", value, "monitorFlag");
            return (Criteria) this;
        }

        public Criteria andMonitorFlagGreaterThanOrEqualTo(String value) {
            addCriterion("MONITOR_FLAG >=", value, "monitorFlag");
            return (Criteria) this;
        }

        public Criteria andMonitorFlagLessThan(String value) {
            addCriterion("MONITOR_FLAG <", value, "monitorFlag");
            return (Criteria) this;
        }

        public Criteria andMonitorFlagLessThanOrEqualTo(String value) {
            addCriterion("MONITOR_FLAG <=", value, "monitorFlag");
            return (Criteria) this;
        }

        public Criteria andMonitorFlagLike(String value) {
            addCriterion("MONITOR_FLAG like", value, "monitorFlag");
            return (Criteria) this;
        }

        public Criteria andMonitorFlagNotLike(String value) {
            addCriterion("MONITOR_FLAG not like", value, "monitorFlag");
            return (Criteria) this;
        }

        public Criteria andMonitorFlagIn(List<String> values) {
            addCriterion("MONITOR_FLAG in", values, "monitorFlag");
            return (Criteria) this;
        }

        public Criteria andMonitorFlagNotIn(List<String> values) {
            addCriterion("MONITOR_FLAG not in", values, "monitorFlag");
            return (Criteria) this;
        }

        public Criteria andMonitorFlagBetween(String value1, String value2) {
            addCriterion("MONITOR_FLAG between", value1, value2, "monitorFlag");
            return (Criteria) this;
        }

        public Criteria andMonitorFlagNotBetween(String value1, String value2) {
            addCriterion("MONITOR_FLAG not between", value1, value2, "monitorFlag");
            return (Criteria) this;
        }

        public Criteria andWatchCodeIsNull() {
            addCriterion("WATCH_CODE is null");
            return (Criteria) this;
        }

        public Criteria andWatchCodeIsNotNull() {
            addCriterion("WATCH_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andWatchCodeEqualTo(String value) {
            addCriterion("WATCH_CODE =", value, "watchCode");
            return (Criteria) this;
        }

        public Criteria andWatchCodeNotEqualTo(String value) {
            addCriterion("WATCH_CODE <>", value, "watchCode");
            return (Criteria) this;
        }

        public Criteria andWatchCodeGreaterThan(String value) {
            addCriterion("WATCH_CODE >", value, "watchCode");
            return (Criteria) this;
        }

        public Criteria andWatchCodeGreaterThanOrEqualTo(String value) {
            addCriterion("WATCH_CODE >=", value, "watchCode");
            return (Criteria) this;
        }

        public Criteria andWatchCodeLessThan(String value) {
            addCriterion("WATCH_CODE <", value, "watchCode");
            return (Criteria) this;
        }

        public Criteria andWatchCodeLessThanOrEqualTo(String value) {
            addCriterion("WATCH_CODE <=", value, "watchCode");
            return (Criteria) this;
        }

        public Criteria andWatchCodeLike(String value) {
            addCriterion("WATCH_CODE like", value, "watchCode");
            return (Criteria) this;
        }

        public Criteria andWatchCodeNotLike(String value) {
            addCriterion("WATCH_CODE not like", value, "watchCode");
            return (Criteria) this;
        }

        public Criteria andWatchCodeIn(List<String> values) {
            addCriterion("WATCH_CODE in", values, "watchCode");
            return (Criteria) this;
        }

        public Criteria andWatchCodeNotIn(List<String> values) {
            addCriterion("WATCH_CODE not in", values, "watchCode");
            return (Criteria) this;
        }

        public Criteria andWatchCodeBetween(String value1, String value2) {
            addCriterion("WATCH_CODE between", value1, value2, "watchCode");
            return (Criteria) this;
        }

        public Criteria andWatchCodeNotBetween(String value1, String value2) {
            addCriterion("WATCH_CODE not between", value1, value2, "watchCode");
            return (Criteria) this;
        }

        public Criteria andWorkPositionIsNull() {
            addCriterion("WORK_POSITION is null");
            return (Criteria) this;
        }

        public Criteria andWorkPositionIsNotNull() {
            addCriterion("WORK_POSITION is not null");
            return (Criteria) this;
        }

        public Criteria andWorkPositionEqualTo(String value) {
            addCriterion("WORK_POSITION =", value, "workPosition");
            return (Criteria) this;
        }

        public Criteria andWorkPositionNotEqualTo(String value) {
            addCriterion("WORK_POSITION <>", value, "workPosition");
            return (Criteria) this;
        }

        public Criteria andWorkPositionGreaterThan(String value) {
            addCriterion("WORK_POSITION >", value, "workPosition");
            return (Criteria) this;
        }

        public Criteria andWorkPositionGreaterThanOrEqualTo(String value) {
            addCriterion("WORK_POSITION >=", value, "workPosition");
            return (Criteria) this;
        }

        public Criteria andWorkPositionLessThan(String value) {
            addCriterion("WORK_POSITION <", value, "workPosition");
            return (Criteria) this;
        }

        public Criteria andWorkPositionLessThanOrEqualTo(String value) {
            addCriterion("WORK_POSITION <=", value, "workPosition");
            return (Criteria) this;
        }

        public Criteria andWorkPositionLike(String value) {
            addCriterion("WORK_POSITION like", value, "workPosition");
            return (Criteria) this;
        }

        public Criteria andWorkPositionNotLike(String value) {
            addCriterion("WORK_POSITION not like", value, "workPosition");
            return (Criteria) this;
        }

        public Criteria andWorkPositionIn(List<String> values) {
            addCriterion("WORK_POSITION in", values, "workPosition");
            return (Criteria) this;
        }

        public Criteria andWorkPositionNotIn(List<String> values) {
            addCriterion("WORK_POSITION not in", values, "workPosition");
            return (Criteria) this;
        }

        public Criteria andWorkPositionBetween(String value1, String value2) {
            addCriterion("WORK_POSITION between", value1, value2, "workPosition");
            return (Criteria) this;
        }

        public Criteria andWorkPositionNotBetween(String value1, String value2) {
            addCriterion("WORK_POSITION not between", value1, value2, "workPosition");
            return (Criteria) this;
        }

        public Criteria andWorkStateIsNull() {
            addCriterion("WORK_STATE is null");
            return (Criteria) this;
        }

        public Criteria andWorkStateIsNotNull() {
            addCriterion("WORK_STATE is not null");
            return (Criteria) this;
        }

        public Criteria andWorkStateEqualTo(String value) {
            addCriterion("WORK_STATE =", value, "workState");
            return (Criteria) this;
        }

        public Criteria andWorkStateNotEqualTo(String value) {
            addCriterion("WORK_STATE <>", value, "workState");
            return (Criteria) this;
        }

        public Criteria andWorkStateGreaterThan(String value) {
            addCriterion("WORK_STATE >", value, "workState");
            return (Criteria) this;
        }

        public Criteria andWorkStateGreaterThanOrEqualTo(String value) {
            addCriterion("WORK_STATE >=", value, "workState");
            return (Criteria) this;
        }

        public Criteria andWorkStateLessThan(String value) {
            addCriterion("WORK_STATE <", value, "workState");
            return (Criteria) this;
        }

        public Criteria andWorkStateLessThanOrEqualTo(String value) {
            addCriterion("WORK_STATE <=", value, "workState");
            return (Criteria) this;
        }

        public Criteria andWorkStateLike(String value) {
            addCriterion("WORK_STATE like", value, "workState");
            return (Criteria) this;
        }

        public Criteria andWorkStateNotLike(String value) {
            addCriterion("WORK_STATE not like", value, "workState");
            return (Criteria) this;
        }

        public Criteria andWorkStateIn(List<String> values) {
            addCriterion("WORK_STATE in", values, "workState");
            return (Criteria) this;
        }

        public Criteria andWorkStateNotIn(List<String> values) {
            addCriterion("WORK_STATE not in", values, "workState");
            return (Criteria) this;
        }

        public Criteria andWorkStateBetween(String value1, String value2) {
            addCriterion("WORK_STATE between", value1, value2, "workState");
            return (Criteria) this;
        }

        public Criteria andWorkStateNotBetween(String value1, String value2) {
            addCriterion("WORK_STATE not between", value1, value2, "workState");
            return (Criteria) this;
        }

        public Criteria andWorkTimeIsNull() {
            addCriterion("WORK_TIME is null");
            return (Criteria) this;
        }

        public Criteria andWorkTimeIsNotNull() {
            addCriterion("WORK_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andWorkTimeEqualTo(Long value) {
            addCriterion("WORK_TIME =", value, "workTime");
            return (Criteria) this;
        }

        public Criteria andWorkTimeNotEqualTo(Long value) {
            addCriterion("WORK_TIME <>", value, "workTime");
            return (Criteria) this;
        }

        public Criteria andWorkTimeGreaterThan(Long value) {
            addCriterion("WORK_TIME >", value, "workTime");
            return (Criteria) this;
        }

        public Criteria andWorkTimeGreaterThanOrEqualTo(Long value) {
            addCriterion("WORK_TIME >=", value, "workTime");
            return (Criteria) this;
        }

        public Criteria andWorkTimeLessThan(Long value) {
            addCriterion("WORK_TIME <", value, "workTime");
            return (Criteria) this;
        }

        public Criteria andWorkTimeLessThanOrEqualTo(Long value) {
            addCriterion("WORK_TIME <=", value, "workTime");
            return (Criteria) this;
        }

        public Criteria andWorkTimeIn(List<Long> values) {
            addCriterion("WORK_TIME in", values, "workTime");
            return (Criteria) this;
        }

        public Criteria andWorkTimeNotIn(List<Long> values) {
            addCriterion("WORK_TIME not in", values, "workTime");
            return (Criteria) this;
        }

        public Criteria andWorkTimeBetween(Long value1, Long value2) {
            addCriterion("WORK_TIME between", value1, value2, "workTime");
            return (Criteria) this;
        }

        public Criteria andWorkTimeNotBetween(Long value1, Long value2) {
            addCriterion("WORK_TIME not between", value1, value2, "workTime");
            return (Criteria) this;
        }

        public Criteria andOnDutyIsNull() {
            addCriterion("ON_DUTY is null");
            return (Criteria) this;
        }

        public Criteria andOnDutyIsNotNull() {
            addCriterion("ON_DUTY is not null");
            return (Criteria) this;
        }

        public Criteria andOnDutyEqualTo(String value) {
            addCriterion("ON_DUTY =", value, "onDuty");
            return (Criteria) this;
        }

        public Criteria andOnDutyNotEqualTo(String value) {
            addCriterion("ON_DUTY <>", value, "onDuty");
            return (Criteria) this;
        }

        public Criteria andOnDutyGreaterThan(String value) {
            addCriterion("ON_DUTY >", value, "onDuty");
            return (Criteria) this;
        }

        public Criteria andOnDutyGreaterThanOrEqualTo(String value) {
            addCriterion("ON_DUTY >=", value, "onDuty");
            return (Criteria) this;
        }

        public Criteria andOnDutyLessThan(String value) {
            addCriterion("ON_DUTY <", value, "onDuty");
            return (Criteria) this;
        }

        public Criteria andOnDutyLessThanOrEqualTo(String value) {
            addCriterion("ON_DUTY <=", value, "onDuty");
            return (Criteria) this;
        }

        public Criteria andOnDutyLike(String value) {
            addCriterion("ON_DUTY like", value, "onDuty");
            return (Criteria) this;
        }

        public Criteria andOnDutyNotLike(String value) {
            addCriterion("ON_DUTY not like", value, "onDuty");
            return (Criteria) this;
        }

        public Criteria andOnDutyIn(List<String> values) {
            addCriterion("ON_DUTY in", values, "onDuty");
            return (Criteria) this;
        }

        public Criteria andOnDutyNotIn(List<String> values) {
            addCriterion("ON_DUTY not in", values, "onDuty");
            return (Criteria) this;
        }

        public Criteria andOnDutyBetween(String value1, String value2) {
            addCriterion("ON_DUTY between", value1, value2, "onDuty");
            return (Criteria) this;
        }

        public Criteria andOnDutyNotBetween(String value1, String value2) {
            addCriterion("ON_DUTY not between", value1, value2, "onDuty");
            return (Criteria) this;
        }

        public Criteria andBindStateIsNull() {
            addCriterion("BIND_STATE is null");
            return (Criteria) this;
        }

        public Criteria andBindStateIsNotNull() {
            addCriterion("BIND_STATE is not null");
            return (Criteria) this;
        }

        public Criteria andBindStateEqualTo(Short value) {
            addCriterion("BIND_STATE =", value, "bindState");
            return (Criteria) this;
        }

        public Criteria andBindStateNotEqualTo(Short value) {
            addCriterion("BIND_STATE <>", value, "bindState");
            return (Criteria) this;
        }

        public Criteria andBindStateGreaterThan(Short value) {
            addCriterion("BIND_STATE >", value, "bindState");
            return (Criteria) this;
        }

        public Criteria andBindStateGreaterThanOrEqualTo(Short value) {
            addCriterion("BIND_STATE >=", value, "bindState");
            return (Criteria) this;
        }

        public Criteria andBindStateLessThan(Short value) {
            addCriterion("BIND_STATE <", value, "bindState");
            return (Criteria) this;
        }

        public Criteria andBindStateLessThanOrEqualTo(Short value) {
            addCriterion("BIND_STATE <=", value, "bindState");
            return (Criteria) this;
        }

        public Criteria andBindStateIn(List<Short> values) {
            addCriterion("BIND_STATE in", values, "bindState");
            return (Criteria) this;
        }

        public Criteria andBindStateNotIn(List<Short> values) {
            addCriterion("BIND_STATE not in", values, "bindState");
            return (Criteria) this;
        }

        public Criteria andBindStateBetween(Short value1, Short value2) {
            addCriterion("BIND_STATE between", value1, value2, "bindState");
            return (Criteria) this;
        }

        public Criteria andBindStateNotBetween(Short value1, Short value2) {
            addCriterion("BIND_STATE not between", value1, value2, "bindState");
            return (Criteria) this;
        }

        public Criteria andChangeTimeIsNull() {
            addCriterion("CHANGE_TIME is null");
            return (Criteria) this;
        }

        public Criteria andChangeTimeIsNotNull() {
            addCriterion("CHANGE_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andChangeTimeEqualTo(Date value) {
            addCriterion("CHANGE_TIME =", value, "changeTime");
            return (Criteria) this;
        }

        public Criteria andChangeTimeNotEqualTo(Date value) {
            addCriterion("CHANGE_TIME <>", value, "changeTime");
            return (Criteria) this;
        }

        public Criteria andChangeTimeGreaterThan(Date value) {
            addCriterion("CHANGE_TIME >", value, "changeTime");
            return (Criteria) this;
        }

        public Criteria andChangeTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("CHANGE_TIME >=", value, "changeTime");
            return (Criteria) this;
        }

        public Criteria andChangeTimeLessThan(Date value) {
            addCriterion("CHANGE_TIME <", value, "changeTime");
            return (Criteria) this;
        }

        public Criteria andChangeTimeLessThanOrEqualTo(Date value) {
            addCriterion("CHANGE_TIME <=", value, "changeTime");
            return (Criteria) this;
        }

        public Criteria andChangeTimeIn(List<Date> values) {
            addCriterion("CHANGE_TIME in", values, "changeTime");
            return (Criteria) this;
        }

        public Criteria andChangeTimeNotIn(List<Date> values) {
            addCriterion("CHANGE_TIME not in", values, "changeTime");
            return (Criteria) this;
        }

        public Criteria andChangeTimeBetween(Date value1, Date value2) {
            addCriterion("CHANGE_TIME between", value1, value2, "changeTime");
            return (Criteria) this;
        }

        public Criteria andChangeTimeNotBetween(Date value1, Date value2) {
            addCriterion("CHANGE_TIME not between", value1, value2, "changeTime");
            return (Criteria) this;
        }

        public Criteria andMyWorkIsNull() {
            addCriterion("MY_WORK is null");
            return (Criteria) this;
        }

        public Criteria andMyWorkIsNotNull() {
            addCriterion("MY_WORK is not null");
            return (Criteria) this;
        }

        public Criteria andMyWorkEqualTo(String value) {
            addCriterion("MY_WORK =", value, "myWork");
            return (Criteria) this;
        }

        public Criteria andMyWorkNotEqualTo(String value) {
            addCriterion("MY_WORK <>", value, "myWork");
            return (Criteria) this;
        }

        public Criteria andMyWorkGreaterThan(String value) {
            addCriterion("MY_WORK >", value, "myWork");
            return (Criteria) this;
        }

        public Criteria andMyWorkGreaterThanOrEqualTo(String value) {
            addCriterion("MY_WORK >=", value, "myWork");
            return (Criteria) this;
        }

        public Criteria andMyWorkLessThan(String value) {
            addCriterion("MY_WORK <", value, "myWork");
            return (Criteria) this;
        }

        public Criteria andMyWorkLessThanOrEqualTo(String value) {
            addCriterion("MY_WORK <=", value, "myWork");
            return (Criteria) this;
        }

        public Criteria andMyWorkLike(String value) {
            addCriterion("MY_WORK like", value, "myWork");
            return (Criteria) this;
        }

        public Criteria andMyWorkNotLike(String value) {
            addCriterion("MY_WORK not like", value, "myWork");
            return (Criteria) this;
        }

        public Criteria andMyWorkIn(List<String> values) {
            addCriterion("MY_WORK in", values, "myWork");
            return (Criteria) this;
        }

        public Criteria andMyWorkNotIn(List<String> values) {
            addCriterion("MY_WORK not in", values, "myWork");
            return (Criteria) this;
        }

        public Criteria andMyWorkBetween(String value1, String value2) {
            addCriterion("MY_WORK between", value1, value2, "myWork");
            return (Criteria) this;
        }

        public Criteria andMyWorkNotBetween(String value1, String value2) {
            addCriterion("MY_WORK not between", value1, value2, "myWork");
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