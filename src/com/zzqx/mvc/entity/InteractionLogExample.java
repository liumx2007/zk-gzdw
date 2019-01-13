package com.zzqx.mvc.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class InteractionLogExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public InteractionLogExample() {
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
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(String value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(String value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(String value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(String value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(String value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(String value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLike(String value) {
            addCriterion("id like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotLike(String value) {
            addCriterion("id not like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<String> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<String> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(String value1, String value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(String value1, String value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andInteractionIdIsNull() {
            addCriterion("interaction_id is null");
            return (Criteria) this;
        }

        public Criteria andInteractionIdIsNotNull() {
            addCriterion("interaction_id is not null");
            return (Criteria) this;
        }

        public Criteria andInteractionIdEqualTo(String value) {
            addCriterion("interaction_id =", value, "interactionId");
            return (Criteria) this;
        }

        public Criteria andInteractionIdNotEqualTo(String value) {
            addCriterion("interaction_id <>", value, "interactionId");
            return (Criteria) this;
        }

        public Criteria andInteractionIdGreaterThan(String value) {
            addCriterion("interaction_id >", value, "interactionId");
            return (Criteria) this;
        }

        public Criteria andInteractionIdGreaterThanOrEqualTo(String value) {
            addCriterion("interaction_id >=", value, "interactionId");
            return (Criteria) this;
        }

        public Criteria andInteractionIdLessThan(String value) {
            addCriterion("interaction_id <", value, "interactionId");
            return (Criteria) this;
        }

        public Criteria andInteractionIdLessThanOrEqualTo(String value) {
            addCriterion("interaction_id <=", value, "interactionId");
            return (Criteria) this;
        }

        public Criteria andInteractionIdLike(String value) {
            addCriterion("interaction_id like", value, "interactionId");
            return (Criteria) this;
        }

        public Criteria andInteractionIdNotLike(String value) {
            addCriterion("interaction_id not like", value, "interactionId");
            return (Criteria) this;
        }

        public Criteria andInteractionIdIn(List<String> values) {
            addCriterion("interaction_id in", values, "interactionId");
            return (Criteria) this;
        }

        public Criteria andInteractionIdNotIn(List<String> values) {
            addCriterion("interaction_id not in", values, "interactionId");
            return (Criteria) this;
        }

        public Criteria andInteractionIdBetween(String value1, String value2) {
            addCriterion("interaction_id between", value1, value2, "interactionId");
            return (Criteria) this;
        }

        public Criteria andInteractionIdNotBetween(String value1, String value2) {
            addCriterion("interaction_id not between", value1, value2, "interactionId");
            return (Criteria) this;
        }

        public Criteria andClickTimeIsNull() {
            addCriterion("click_time is null");
            return (Criteria) this;
        }

        public Criteria andClickTimeIsNotNull() {
            addCriterion("click_time is not null");
            return (Criteria) this;
        }

        public Criteria andClickTimeEqualTo(Date value) {
            addCriterion("click_time =", value, "clickTime");
            return (Criteria) this;
        }

        public Criteria andClickTimeNotEqualTo(Date value) {
            addCriterion("click_time <>", value, "clickTime");
            return (Criteria) this;
        }

        public Criteria andClickTimeGreaterThan(Date value) {
            addCriterion("click_time >", value, "clickTime");
            return (Criteria) this;
        }

        public Criteria andClickTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("click_time >=", value, "clickTime");
            return (Criteria) this;
        }

        public Criteria andClickTimeLessThan(Date value) {
            addCriterion("click_time <", value, "clickTime");
            return (Criteria) this;
        }

        public Criteria andClickTimeLessThanOrEqualTo(Date value) {
            addCriterion("click_time <=", value, "clickTime");
            return (Criteria) this;
        }

        public Criteria andClickTimeIn(List<Date> values) {
            addCriterion("click_time in", values, "clickTime");
            return (Criteria) this;
        }

        public Criteria andClickTimeNotIn(List<Date> values) {
            addCriterion("click_time not in", values, "clickTime");
            return (Criteria) this;
        }

        public Criteria andClickTimeBetween(Date value1, Date value2) {
            addCriterion("click_time between", value1, value2, "clickTime");
            return (Criteria) this;
        }

        public Criteria andClickTimeNotBetween(Date value1, Date value2) {
            addCriterion("click_time not between", value1, value2, "clickTime");
            return (Criteria) this;
        }

        public Criteria andSessionBusinessIsNull() {
            addCriterion("session_business is null");
            return (Criteria) this;
        }

        public Criteria andSessionBusinessIsNotNull() {
            addCriterion("session_business is not null");
            return (Criteria) this;
        }

        public Criteria andSessionBusinessEqualTo(String value) {
            addCriterion("session_business =", value, "sessionBusiness");
            return (Criteria) this;
        }

        public Criteria andSessionBusinessNotEqualTo(String value) {
            addCriterion("session_business <>", value, "sessionBusiness");
            return (Criteria) this;
        }

        public Criteria andSessionBusinessGreaterThan(String value) {
            addCriterion("session_business >", value, "sessionBusiness");
            return (Criteria) this;
        }

        public Criteria andSessionBusinessGreaterThanOrEqualTo(String value) {
            addCriterion("session_business >=", value, "sessionBusiness");
            return (Criteria) this;
        }

        public Criteria andSessionBusinessLessThan(String value) {
            addCriterion("session_business <", value, "sessionBusiness");
            return (Criteria) this;
        }

        public Criteria andSessionBusinessLessThanOrEqualTo(String value) {
            addCriterion("session_business <=", value, "sessionBusiness");
            return (Criteria) this;
        }

        public Criteria andSessionBusinessLike(String value) {
            addCriterion("session_business like", value, "sessionBusiness");
            return (Criteria) this;
        }

        public Criteria andSessionBusinessNotLike(String value) {
            addCriterion("session_business not like", value, "sessionBusiness");
            return (Criteria) this;
        }

        public Criteria andSessionBusinessIn(List<String> values) {
            addCriterion("session_business in", values, "sessionBusiness");
            return (Criteria) this;
        }

        public Criteria andSessionBusinessNotIn(List<String> values) {
            addCriterion("session_business not in", values, "sessionBusiness");
            return (Criteria) this;
        }

        public Criteria andSessionBusinessBetween(String value1, String value2) {
            addCriterion("session_business between", value1, value2, "sessionBusiness");
            return (Criteria) this;
        }

        public Criteria andSessionBusinessNotBetween(String value1, String value2) {
            addCriterion("session_business not between", value1, value2, "sessionBusiness");
            return (Criteria) this;
        }

        public Criteria andSessionInteractIsNull() {
            addCriterion("session_interact is null");
            return (Criteria) this;
        }

        public Criteria andSessionInteractIsNotNull() {
            addCriterion("session_interact is not null");
            return (Criteria) this;
        }

        public Criteria andSessionInteractEqualTo(String value) {
            addCriterion("session_interact =", value, "sessionInteract");
            return (Criteria) this;
        }

        public Criteria andSessionInteractNotEqualTo(String value) {
            addCriterion("session_interact <>", value, "sessionInteract");
            return (Criteria) this;
        }

        public Criteria andSessionInteractGreaterThan(String value) {
            addCriterion("session_interact >", value, "sessionInteract");
            return (Criteria) this;
        }

        public Criteria andSessionInteractGreaterThanOrEqualTo(String value) {
            addCriterion("session_interact >=", value, "sessionInteract");
            return (Criteria) this;
        }

        public Criteria andSessionInteractLessThan(String value) {
            addCriterion("session_interact <", value, "sessionInteract");
            return (Criteria) this;
        }

        public Criteria andSessionInteractLessThanOrEqualTo(String value) {
            addCriterion("session_interact <=", value, "sessionInteract");
            return (Criteria) this;
        }

        public Criteria andSessionInteractLike(String value) {
            addCriterion("session_interact like", value, "sessionInteract");
            return (Criteria) this;
        }

        public Criteria andSessionInteractNotLike(String value) {
            addCriterion("session_interact not like", value, "sessionInteract");
            return (Criteria) this;
        }

        public Criteria andSessionInteractIn(List<String> values) {
            addCriterion("session_interact in", values, "sessionInteract");
            return (Criteria) this;
        }

        public Criteria andSessionInteractNotIn(List<String> values) {
            addCriterion("session_interact not in", values, "sessionInteract");
            return (Criteria) this;
        }

        public Criteria andSessionInteractBetween(String value1, String value2) {
            addCriterion("session_interact between", value1, value2, "sessionInteract");
            return (Criteria) this;
        }

        public Criteria andSessionInteractNotBetween(String value1, String value2) {
            addCriterion("session_interact not between", value1, value2, "sessionInteract");
            return (Criteria) this;
        }

        public Criteria andFolderTypeIsNull() {
            addCriterion("folder_type is null");
            return (Criteria) this;
        }

        public Criteria andFolderTypeIsNotNull() {
            addCriterion("folder_type is not null");
            return (Criteria) this;
        }

        public Criteria andFolderTypeEqualTo(Boolean value) {
            addCriterion("folder_type =", value, "folderType");
            return (Criteria) this;
        }

        public Criteria andFolderTypeNotEqualTo(Boolean value) {
            addCriterion("folder_type <>", value, "folderType");
            return (Criteria) this;
        }

        public Criteria andFolderTypeGreaterThan(Boolean value) {
            addCriterion("folder_type >", value, "folderType");
            return (Criteria) this;
        }

        public Criteria andFolderTypeGreaterThanOrEqualTo(Boolean value) {
            addCriterion("folder_type >=", value, "folderType");
            return (Criteria) this;
        }

        public Criteria andFolderTypeLessThan(Boolean value) {
            addCriterion("folder_type <", value, "folderType");
            return (Criteria) this;
        }

        public Criteria andFolderTypeLessThanOrEqualTo(Boolean value) {
            addCriterion("folder_type <=", value, "folderType");
            return (Criteria) this;
        }

        public Criteria andFolderTypeIn(List<Boolean> values) {
            addCriterion("folder_type in", values, "folderType");
            return (Criteria) this;
        }

        public Criteria andFolderTypeNotIn(List<Boolean> values) {
            addCriterion("folder_type not in", values, "folderType");
            return (Criteria) this;
        }

        public Criteria andFolderTypeBetween(Boolean value1, Boolean value2) {
            addCriterion("folder_type between", value1, value2, "folderType");
            return (Criteria) this;
        }

        public Criteria andFolderTypeNotBetween(Boolean value1, Boolean value2) {
            addCriterion("folder_type not between", value1, value2, "folderType");
            return (Criteria) this;
        }
        //19-1-13
        public Criteria andStatusEqualTo(String value) {
            addCriterion("status =", value, "status");
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