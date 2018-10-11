package com.shenhesoft.logistics.manage.pojo.smsPlan;

import java.util.ArrayList;
import java.util.List;

public class TbSmsPlanExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TbSmsPlanExample() {
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

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andNameIsNull() {
            addCriterion("name is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("name is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("name =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("name <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("name >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("name >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("name <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("name <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("name like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("name not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("name in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("name not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("name between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("name not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andPointIsNull() {
            addCriterion("point is null");
            return (Criteria) this;
        }

        public Criteria andPointIsNotNull() {
            addCriterion("point is not null");
            return (Criteria) this;
        }

        public Criteria andPointEqualTo(Byte value) {
            addCriterion("point =", value, "point");
            return (Criteria) this;
        }

        public Criteria andPointNotEqualTo(Byte value) {
            addCriterion("point <>", value, "point");
            return (Criteria) this;
        }

        public Criteria andPointGreaterThan(Byte value) {
            addCriterion("point >", value, "point");
            return (Criteria) this;
        }

        public Criteria andPointGreaterThanOrEqualTo(Byte value) {
            addCriterion("point >=", value, "point");
            return (Criteria) this;
        }

        public Criteria andPointLessThan(Byte value) {
            addCriterion("point <", value, "point");
            return (Criteria) this;
        }

        public Criteria andPointLessThanOrEqualTo(Byte value) {
            addCriterion("point <=", value, "point");
            return (Criteria) this;
        }

        public Criteria andPointIn(List<Byte> values) {
            addCriterion("point in", values, "point");
            return (Criteria) this;
        }

        public Criteria andPointNotIn(List<Byte> values) {
            addCriterion("point not in", values, "point");
            return (Criteria) this;
        }

        public Criteria andPointBetween(Byte value1, Byte value2) {
            addCriterion("point between", value1, value2, "point");
            return (Criteria) this;
        }

        public Criteria andPointNotBetween(Byte value1, Byte value2) {
            addCriterion("point not between", value1, value2, "point");
            return (Criteria) this;
        }

        public Criteria andSmsModelIdIsNull() {
            addCriterion("sms_model_id is null");
            return (Criteria) this;
        }

        public Criteria andSmsModelIdIsNotNull() {
            addCriterion("sms_model_id is not null");
            return (Criteria) this;
        }

        public Criteria andSmsModelIdEqualTo(Integer value) {
            addCriterion("sms_model_id =", value, "smsModelId");
            return (Criteria) this;
        }

        public Criteria andSmsModelIdNotEqualTo(Integer value) {
            addCriterion("sms_model_id <>", value, "smsModelId");
            return (Criteria) this;
        }

        public Criteria andSmsModelIdGreaterThan(Integer value) {
            addCriterion("sms_model_id >", value, "smsModelId");
            return (Criteria) this;
        }

        public Criteria andSmsModelIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("sms_model_id >=", value, "smsModelId");
            return (Criteria) this;
        }

        public Criteria andSmsModelIdLessThan(Integer value) {
            addCriterion("sms_model_id <", value, "smsModelId");
            return (Criteria) this;
        }

        public Criteria andSmsModelIdLessThanOrEqualTo(Integer value) {
            addCriterion("sms_model_id <=", value, "smsModelId");
            return (Criteria) this;
        }

        public Criteria andSmsModelIdIn(List<Integer> values) {
            addCriterion("sms_model_id in", values, "smsModelId");
            return (Criteria) this;
        }

        public Criteria andSmsModelIdNotIn(List<Integer> values) {
            addCriterion("sms_model_id not in", values, "smsModelId");
            return (Criteria) this;
        }

        public Criteria andSmsModelIdBetween(Integer value1, Integer value2) {
            addCriterion("sms_model_id between", value1, value2, "smsModelId");
            return (Criteria) this;
        }

        public Criteria andSmsModelIdNotBetween(Integer value1, Integer value2) {
            addCriterion("sms_model_id not between", value1, value2, "smsModelId");
            return (Criteria) this;
        }

        public Criteria andSmsModelCodeIsNull() {
            addCriterion("sms_model_code is null");
            return (Criteria) this;
        }

        public Criteria andSmsModelCodeIsNotNull() {
            addCriterion("sms_model_code is not null");
            return (Criteria) this;
        }

        public Criteria andSmsModelCodeEqualTo(String value) {
            addCriterion("sms_model_code =", value, "smsModelCode");
            return (Criteria) this;
        }

        public Criteria andSmsModelCodeNotEqualTo(String value) {
            addCriterion("sms_model_code <>", value, "smsModelCode");
            return (Criteria) this;
        }

        public Criteria andSmsModelCodeGreaterThan(String value) {
            addCriterion("sms_model_code >", value, "smsModelCode");
            return (Criteria) this;
        }

        public Criteria andSmsModelCodeGreaterThanOrEqualTo(String value) {
            addCriterion("sms_model_code >=", value, "smsModelCode");
            return (Criteria) this;
        }

        public Criteria andSmsModelCodeLessThan(String value) {
            addCriterion("sms_model_code <", value, "smsModelCode");
            return (Criteria) this;
        }

        public Criteria andSmsModelCodeLessThanOrEqualTo(String value) {
            addCriterion("sms_model_code <=", value, "smsModelCode");
            return (Criteria) this;
        }

        public Criteria andSmsModelCodeLike(String value) {
            addCriterion("sms_model_code like", value, "smsModelCode");
            return (Criteria) this;
        }

        public Criteria andSmsModelCodeNotLike(String value) {
            addCriterion("sms_model_code not like", value, "smsModelCode");
            return (Criteria) this;
        }

        public Criteria andSmsModelCodeIn(List<String> values) {
            addCriterion("sms_model_code in", values, "smsModelCode");
            return (Criteria) this;
        }

        public Criteria andSmsModelCodeNotIn(List<String> values) {
            addCriterion("sms_model_code not in", values, "smsModelCode");
            return (Criteria) this;
        }

        public Criteria andSmsModelCodeBetween(String value1, String value2) {
            addCriterion("sms_model_code between", value1, value2, "smsModelCode");
            return (Criteria) this;
        }

        public Criteria andSmsModelCodeNotBetween(String value1, String value2) {
            addCriterion("sms_model_code not between", value1, value2, "smsModelCode");
            return (Criteria) this;
        }

        public Criteria andReceiveIdIsNull() {
            addCriterion("receive_id is null");
            return (Criteria) this;
        }

        public Criteria andReceiveIdIsNotNull() {
            addCriterion("receive_id is not null");
            return (Criteria) this;
        }

        public Criteria andReceiveIdEqualTo(Integer value) {
            addCriterion("receive_id =", value, "receiveId");
            return (Criteria) this;
        }

        public Criteria andReceiveIdNotEqualTo(Integer value) {
            addCriterion("receive_id <>", value, "receiveId");
            return (Criteria) this;
        }

        public Criteria andReceiveIdGreaterThan(Integer value) {
            addCriterion("receive_id >", value, "receiveId");
            return (Criteria) this;
        }

        public Criteria andReceiveIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("receive_id >=", value, "receiveId");
            return (Criteria) this;
        }

        public Criteria andReceiveIdLessThan(Integer value) {
            addCriterion("receive_id <", value, "receiveId");
            return (Criteria) this;
        }

        public Criteria andReceiveIdLessThanOrEqualTo(Integer value) {
            addCriterion("receive_id <=", value, "receiveId");
            return (Criteria) this;
        }

        public Criteria andReceiveIdIn(List<Integer> values) {
            addCriterion("receive_id in", values, "receiveId");
            return (Criteria) this;
        }

        public Criteria andReceiveIdNotIn(List<Integer> values) {
            addCriterion("receive_id not in", values, "receiveId");
            return (Criteria) this;
        }

        public Criteria andReceiveIdBetween(Integer value1, Integer value2) {
            addCriterion("receive_id between", value1, value2, "receiveId");
            return (Criteria) this;
        }

        public Criteria andReceiveIdNotBetween(Integer value1, Integer value2) {
            addCriterion("receive_id not between", value1, value2, "receiveId");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNull() {
            addCriterion("remark is null");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNotNull() {
            addCriterion("remark is not null");
            return (Criteria) this;
        }

        public Criteria andRemarkEqualTo(String value) {
            addCriterion("remark =", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotEqualTo(String value) {
            addCriterion("remark <>", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThan(String value) {
            addCriterion("remark >", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("remark >=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThan(String value) {
            addCriterion("remark <", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThanOrEqualTo(String value) {
            addCriterion("remark <=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLike(String value) {
            addCriterion("remark like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotLike(String value) {
            addCriterion("remark not like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkIn(List<String> values) {
            addCriterion("remark in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotIn(List<String> values) {
            addCriterion("remark not in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkBetween(String value1, String value2) {
            addCriterion("remark between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotBetween(String value1, String value2) {
            addCriterion("remark not between", value1, value2, "remark");
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