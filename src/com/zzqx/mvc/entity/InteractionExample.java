package com.zzqx.mvc.entity;

import java.util.ArrayList;
import java.util.List;

public class InteractionExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public InteractionExample() {
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

        public Criteria andInteractNameIsNull() {
            addCriterion("interact_name is null");
            return (Criteria) this;
        }

        public Criteria andInteractNameIsNotNull() {
            addCriterion("interact_name is not null");
            return (Criteria) this;
        }

        public Criteria andInteractNameEqualTo(String value) {
            addCriterion("interact_name =", value, "interactName");
            return (Criteria) this;
        }

        public Criteria andInteractNameNotEqualTo(String value) {
            addCriterion("interact_name <>", value, "interactName");
            return (Criteria) this;
        }

        public Criteria andInteractNameGreaterThan(String value) {
            addCriterion("interact_name >", value, "interactName");
            return (Criteria) this;
        }

        public Criteria andInteractNameGreaterThanOrEqualTo(String value) {
            addCriterion("interact_name >=", value, "interactName");
            return (Criteria) this;
        }

        public Criteria andInteractNameLessThan(String value) {
            addCriterion("interact_name <", value, "interactName");
            return (Criteria) this;
        }

        public Criteria andInteractNameLessThanOrEqualTo(String value) {
            addCriterion("interact_name <=", value, "interactName");
            return (Criteria) this;
        }

        public Criteria andInteractNameLike(String value) {
            addCriterion("interact_name like", value, "interactName");
            return (Criteria) this;
        }

        public Criteria andInteractNameNotLike(String value) {
            addCriterion("interact_name not like", value, "interactName");
            return (Criteria) this;
        }

        public Criteria andInteractNameIn(List<String> values) {
            addCriterion("interact_name in", values, "interactName");
            return (Criteria) this;
        }

        public Criteria andInteractNameNotIn(List<String> values) {
            addCriterion("interact_name not in", values, "interactName");
            return (Criteria) this;
        }

        public Criteria andInteractNameBetween(String value1, String value2) {
            addCriterion("interact_name between", value1, value2, "interactName");
            return (Criteria) this;
        }

        public Criteria andInteractNameNotBetween(String value1, String value2) {
            addCriterion("interact_name not between", value1, value2, "interactName");
            return (Criteria) this;
        }

        public Criteria andInteractCodeIsNull() {
            addCriterion("interact_code is null");
            return (Criteria) this;
        }

        public Criteria andInteractCodeIsNotNull() {
            addCriterion("interact_code is not null");
            return (Criteria) this;
        }

        public Criteria andInteractCodeEqualTo(String value) {
            addCriterion("interact_code =", value, "interactCode");
            return (Criteria) this;
        }

        public Criteria andInteractCodeNotEqualTo(String value) {
            addCriterion("interact_code <>", value, "interactCode");
            return (Criteria) this;
        }

        public Criteria andInteractCodeGreaterThan(String value) {
            addCriterion("interact_code >", value, "interactCode");
            return (Criteria) this;
        }

        public Criteria andInteractCodeGreaterThanOrEqualTo(String value) {
            addCriterion("interact_code >=", value, "interactCode");
            return (Criteria) this;
        }

        public Criteria andInteractCodeLessThan(String value) {
            addCriterion("interact_code <", value, "interactCode");
            return (Criteria) this;
        }

        public Criteria andInteractCodeLessThanOrEqualTo(String value) {
            addCriterion("interact_code <=", value, "interactCode");
            return (Criteria) this;
        }

        public Criteria andInteractCodeLike(String value) {
            addCriterion("interact_code like", value, "interactCode");
            return (Criteria) this;
        }

        public Criteria andInteractCodeNotLike(String value) {
            addCriterion("interact_code not like", value, "interactCode");
            return (Criteria) this;
        }

        public Criteria andInteractCodeIn(List<String> values) {
            addCriterion("interact_code in", values, "interactCode");
            return (Criteria) this;
        }

        public Criteria andInteractCodeNotIn(List<String> values) {
            addCriterion("interact_code not in", values, "interactCode");
            return (Criteria) this;
        }

        public Criteria andInteractCodeBetween(String value1, String value2) {
            addCriterion("interact_code between", value1, value2, "interactCode");
            return (Criteria) this;
        }

        public Criteria andInteractCodeNotBetween(String value1, String value2) {
            addCriterion("interact_code not between", value1, value2, "interactCode");
            return (Criteria) this;
        }

        public Criteria andPreviousIdIsNull() {
            addCriterion("previous_id is null");
            return (Criteria) this;
        }

        public Criteria andPreviousIdIsNotNull() {
            addCriterion("previous_id is not null");
            return (Criteria) this;
        }

        public Criteria andPreviousIdEqualTo(String value) {
            addCriterion("previous_id =", value, "previousId");
            return (Criteria) this;
        }

        public Criteria andPreviousIdNotEqualTo(String value) {
            addCriterion("previous_id <>", value, "previousId");
            return (Criteria) this;
        }

        public Criteria andPreviousIdGreaterThan(String value) {
            addCriterion("previous_id >", value, "previousId");
            return (Criteria) this;
        }

        public Criteria andPreviousIdGreaterThanOrEqualTo(String value) {
            addCriterion("previous_id >=", value, "previousId");
            return (Criteria) this;
        }

        public Criteria andPreviousIdLessThan(String value) {
            addCriterion("previous_id <", value, "previousId");
            return (Criteria) this;
        }

        public Criteria andPreviousIdLessThanOrEqualTo(String value) {
            addCriterion("previous_id <=", value, "previousId");
            return (Criteria) this;
        }

        public Criteria andPreviousIdLike(String value) {
            addCriterion("previous_id like", value, "previousId");
            return (Criteria) this;
        }

        public Criteria andPreviousIdNotLike(String value) {
            addCriterion("previous_id not like", value, "previousId");
            return (Criteria) this;
        }

        public Criteria andPreviousIdIn(List<String> values) {
            addCriterion("previous_id in", values, "previousId");
            return (Criteria) this;
        }

        public Criteria andPreviousIdNotIn(List<String> values) {
            addCriterion("previous_id not in", values, "previousId");
            return (Criteria) this;
        }

        public Criteria andPreviousIdBetween(String value1, String value2) {
            addCriterion("previous_id between", value1, value2, "previousId");
            return (Criteria) this;
        }

        public Criteria andPreviousIdNotBetween(String value1, String value2) {
            addCriterion("previous_id not between", value1, value2, "previousId");
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

        public Criteria andHallIdIsNull() {
            addCriterion("hall_id is null");
            return (Criteria) this;
        }

        public Criteria andHallIdIsNotNull() {
            addCriterion("hall_id is not null");
            return (Criteria) this;
        }

        public Criteria andHallIdEqualTo(String value) {
            addCriterion("hall_id =", value, "hallId");
            return (Criteria) this;
        }

        public Criteria andHallIdNotEqualTo(String value) {
            addCriterion("hall_id <>", value, "hallId");
            return (Criteria) this;
        }

        public Criteria andHallIdGreaterThan(String value) {
            addCriterion("hall_id >", value, "hallId");
            return (Criteria) this;
        }

        public Criteria andHallIdGreaterThanOrEqualTo(String value) {
            addCriterion("hall_id >=", value, "hallId");
            return (Criteria) this;
        }

        public Criteria andHallIdLessThan(String value) {
            addCriterion("hall_id <", value, "hallId");
            return (Criteria) this;
        }

        public Criteria andHallIdLessThanOrEqualTo(String value) {
            addCriterion("hall_id <=", value, "hallId");
            return (Criteria) this;
        }

        public Criteria andHallIdLike(String value) {
            addCriterion("hall_id like", value, "hallId");
            return (Criteria) this;
        }

        public Criteria andHallIdNotLike(String value) {
            addCriterion("hall_id not like", value, "hallId");
            return (Criteria) this;
        }

        public Criteria andHallIdIn(List<String> values) {
            addCriterion("hall_id in", values, "hallId");
            return (Criteria) this;
        }

        public Criteria andHallIdNotIn(List<String> values) {
            addCriterion("hall_id not in", values, "hallId");
            return (Criteria) this;
        }

        public Criteria andHallIdBetween(String value1, String value2) {
            addCriterion("hall_id between", value1, value2, "hallId");
            return (Criteria) this;
        }

        public Criteria andHallIdNotBetween(String value1, String value2) {
            addCriterion("hall_id not between", value1, value2, "hallId");
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