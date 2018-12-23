package com.zzqx.mvc.entity;

import java.util.ArrayList;
import java.util.List;

public class CmdListExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public CmdListExample() {
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

        public Criteria andDirectListNameIsNull() {
            addCriterion("direct_list_name is null");
            return (Criteria) this;
        }

        public Criteria andDirectListNameIsNotNull() {
            addCriterion("direct_list_name is not null");
            return (Criteria) this;
        }

        public Criteria andDirectListNameEqualTo(String value) {
            addCriterion("direct_list_name =", value, "directListName");
            return (Criteria) this;
        }

        public Criteria andDirectListNameNotEqualTo(String value) {
            addCriterion("direct_list_name <>", value, "directListName");
            return (Criteria) this;
        }

        public Criteria andDirectListNameGreaterThan(String value) {
            addCriterion("direct_list_name >", value, "directListName");
            return (Criteria) this;
        }

        public Criteria andDirectListNameGreaterThanOrEqualTo(String value) {
            addCriterion("direct_list_name >=", value, "directListName");
            return (Criteria) this;
        }

        public Criteria andDirectListNameLessThan(String value) {
            addCriterion("direct_list_name <", value, "directListName");
            return (Criteria) this;
        }

        public Criteria andDirectListNameLessThanOrEqualTo(String value) {
            addCriterion("direct_list_name <=", value, "directListName");
            return (Criteria) this;
        }

        public Criteria andDirectListNameLike(String value) {
            addCriterion("direct_list_name like", value, "directListName");
            return (Criteria) this;
        }

        public Criteria andDirectListNameNotLike(String value) {
            addCriterion("direct_list_name not like", value, "directListName");
            return (Criteria) this;
        }

        public Criteria andDirectListNameIn(List<String> values) {
            addCriterion("direct_list_name in", values, "directListName");
            return (Criteria) this;
        }

        public Criteria andDirectListNameNotIn(List<String> values) {
            addCriterion("direct_list_name not in", values, "directListName");
            return (Criteria) this;
        }

        public Criteria andDirectListNameBetween(String value1, String value2) {
            addCriterion("direct_list_name between", value1, value2, "directListName");
            return (Criteria) this;
        }

        public Criteria andDirectListNameNotBetween(String value1, String value2) {
            addCriterion("direct_list_name not between", value1, value2, "directListName");
            return (Criteria) this;
        }

        public Criteria andDirectListIsNull() {
            addCriterion("direct_list is null");
            return (Criteria) this;
        }

        public Criteria andDirectListIsNotNull() {
            addCriterion("direct_list is not null");
            return (Criteria) this;
        }

        public Criteria andDirectListEqualTo(String value) {
            addCriterion("direct_list =", value, "directList");
            return (Criteria) this;
        }

        public Criteria andDirectListNotEqualTo(String value) {
            addCriterion("direct_list <>", value, "directList");
            return (Criteria) this;
        }

        public Criteria andDirectListGreaterThan(String value) {
            addCriterion("direct_list >", value, "directList");
            return (Criteria) this;
        }

        public Criteria andDirectListGreaterThanOrEqualTo(String value) {
            addCriterion("direct_list >=", value, "directList");
            return (Criteria) this;
        }

        public Criteria andDirectListLessThan(String value) {
            addCriterion("direct_list <", value, "directList");
            return (Criteria) this;
        }

        public Criteria andDirectListLessThanOrEqualTo(String value) {
            addCriterion("direct_list <=", value, "directList");
            return (Criteria) this;
        }

        public Criteria andDirectListLike(String value) {
            addCriterion("direct_list like", value, "directList");
            return (Criteria) this;
        }

        public Criteria andDirectListNotLike(String value) {
            addCriterion("direct_list not like", value, "directList");
            return (Criteria) this;
        }

        public Criteria andDirectListIn(List<String> values) {
            addCriterion("direct_list in", values, "directList");
            return (Criteria) this;
        }

        public Criteria andDirectListNotIn(List<String> values) {
            addCriterion("direct_list not in", values, "directList");
            return (Criteria) this;
        }

        public Criteria andDirectListBetween(String value1, String value2) {
            addCriterion("direct_list between", value1, value2, "directList");
            return (Criteria) this;
        }

        public Criteria andDirectListNotBetween(String value1, String value2) {
            addCriterion("direct_list not between", value1, value2, "directList");
            return (Criteria) this;
        }

        public Criteria andDescriptionIsNull() {
            addCriterion("description is null");
            return (Criteria) this;
        }

        public Criteria andDescriptionIsNotNull() {
            addCriterion("description is not null");
            return (Criteria) this;
        }

        public Criteria andDescriptionEqualTo(String value) {
            addCriterion("description =", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotEqualTo(String value) {
            addCriterion("description <>", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionGreaterThan(String value) {
            addCriterion("description >", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionGreaterThanOrEqualTo(String value) {
            addCriterion("description >=", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLessThan(String value) {
            addCriterion("description <", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLessThanOrEqualTo(String value) {
            addCriterion("description <=", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLike(String value) {
            addCriterion("description like", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotLike(String value) {
            addCriterion("description not like", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionIn(List<String> values) {
            addCriterion("description in", values, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotIn(List<String> values) {
            addCriterion("description not in", values, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionBetween(String value1, String value2) {
            addCriterion("description between", value1, value2, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotBetween(String value1, String value2) {
            addCriterion("description not between", value1, value2, "description");
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