package com.shenhesoft.logistics.business.pojo.shortBarge;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TbShortBargeExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TbShortBargeExample() {
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

        public Criteria andProjectIdIsNull() {
            addCriterion("project_id is null");
            return (Criteria) this;
        }

        public Criteria andProjectIdIsNotNull() {
            addCriterion("project_id is not null");
            return (Criteria) this;
        }

        public Criteria andProjectIdEqualTo(Integer value) {
            addCriterion("project_id =", value, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdNotEqualTo(Integer value) {
            addCriterion("project_id <>", value, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdGreaterThan(Integer value) {
            addCriterion("project_id >", value, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("project_id >=", value, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdLessThan(Integer value) {
            addCriterion("project_id <", value, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdLessThanOrEqualTo(Integer value) {
            addCriterion("project_id <=", value, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdIn(List<Integer> values) {
            addCriterion("project_id in", values, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdNotIn(List<Integer> values) {
            addCriterion("project_id not in", values, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdBetween(Integer value1, Integer value2) {
            addCriterion("project_id between", value1, value2, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdNotBetween(Integer value1, Integer value2) {
            addCriterion("project_id not between", value1, value2, "projectId");
            return (Criteria) this;
        }

        public Criteria andShortBargeNameIsNull() {
            addCriterion("short_barge_name is null");
            return (Criteria) this;
        }

        public Criteria andShortBargeNameIsNotNull() {
            addCriterion("short_barge_name is not null");
            return (Criteria) this;
        }

        public Criteria andShortBargeNameEqualTo(String value) {
            addCriterion("short_barge_name =", value, "shortBargeName");
            return (Criteria) this;
        }

        public Criteria andShortBargeNameNotEqualTo(String value) {
            addCriterion("short_barge_name <>", value, "shortBargeName");
            return (Criteria) this;
        }

        public Criteria andShortBargeNameGreaterThan(String value) {
            addCriterion("short_barge_name >", value, "shortBargeName");
            return (Criteria) this;
        }

        public Criteria andShortBargeNameGreaterThanOrEqualTo(String value) {
            addCriterion("short_barge_name >=", value, "shortBargeName");
            return (Criteria) this;
        }

        public Criteria andShortBargeNameLessThan(String value) {
            addCriterion("short_barge_name <", value, "shortBargeName");
            return (Criteria) this;
        }

        public Criteria andShortBargeNameLessThanOrEqualTo(String value) {
            addCriterion("short_barge_name <=", value, "shortBargeName");
            return (Criteria) this;
        }

        public Criteria andShortBargeNameLike(String value) {
            addCriterion("short_barge_name like", value, "shortBargeName");
            return (Criteria) this;
        }

        public Criteria andShortBargeNameNotLike(String value) {
            addCriterion("short_barge_name not like", value, "shortBargeName");
            return (Criteria) this;
        }

        public Criteria andShortBargeNameIn(List<String> values) {
            addCriterion("short_barge_name in", values, "shortBargeName");
            return (Criteria) this;
        }

        public Criteria andShortBargeNameNotIn(List<String> values) {
            addCriterion("short_barge_name not in", values, "shortBargeName");
            return (Criteria) this;
        }

        public Criteria andShortBargeNameBetween(String value1, String value2) {
            addCriterion("short_barge_name between", value1, value2, "shortBargeName");
            return (Criteria) this;
        }

        public Criteria andShortBargeNameNotBetween(String value1, String value2) {
            addCriterion("short_barge_name not between", value1, value2, "shortBargeName");
            return (Criteria) this;
        }

        public Criteria andShortBargeIdIsNull() {
            addCriterion("short_barge_id is null");
            return (Criteria) this;
        }

        public Criteria andShortBargeIdIsNotNull() {
            addCriterion("short_barge_id is not null");
            return (Criteria) this;
        }

        public Criteria andShortBargeIdEqualTo(Integer value) {
            addCriterion("short_barge_id =", value, "shortBargeId");
            return (Criteria) this;
        }

        public Criteria andShortBargeIdNotEqualTo(Integer value) {
            addCriterion("short_barge_id <>", value, "shortBargeId");
            return (Criteria) this;
        }

        public Criteria andShortBargeIdGreaterThan(Integer value) {
            addCriterion("short_barge_id >", value, "shortBargeId");
            return (Criteria) this;
        }

        public Criteria andShortBargeIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("short_barge_id >=", value, "shortBargeId");
            return (Criteria) this;
        }

        public Criteria andShortBargeIdLessThan(Integer value) {
            addCriterion("short_barge_id <", value, "shortBargeId");
            return (Criteria) this;
        }

        public Criteria andShortBargeIdLessThanOrEqualTo(Integer value) {
            addCriterion("short_barge_id <=", value, "shortBargeId");
            return (Criteria) this;
        }

        public Criteria andShortBargeIdIn(List<Integer> values) {
            addCriterion("short_barge_id in", values, "shortBargeId");
            return (Criteria) this;
        }

        public Criteria andShortBargeIdNotIn(List<Integer> values) {
            addCriterion("short_barge_id not in", values, "shortBargeId");
            return (Criteria) this;
        }

        public Criteria andShortBargeIdBetween(Integer value1, Integer value2) {
            addCriterion("short_barge_id between", value1, value2, "shortBargeId");
            return (Criteria) this;
        }

        public Criteria andShortBargeIdNotBetween(Integer value1, Integer value2) {
            addCriterion("short_barge_id not between", value1, value2, "shortBargeId");
            return (Criteria) this;
        }

        public Criteria andCreateDateIsNull() {
            addCriterion("create_date is null");
            return (Criteria) this;
        }

        public Criteria andCreateDateIsNotNull() {
            addCriterion("create_date is not null");
            return (Criteria) this;
        }

        public Criteria andCreateDateEqualTo(Date value) {
            addCriterion("create_date =", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotEqualTo(Date value) {
            addCriterion("create_date <>", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateGreaterThan(Date value) {
            addCriterion("create_date >", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateGreaterThanOrEqualTo(Date value) {
            addCriterion("create_date >=", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateLessThan(Date value) {
            addCriterion("create_date <", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateLessThanOrEqualTo(Date value) {
            addCriterion("create_date <=", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateIn(List<Date> values) {
            addCriterion("create_date in", values, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotIn(List<Date> values) {
            addCriterion("create_date not in", values, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateBetween(Date value1, Date value2) {
            addCriterion("create_date between", value1, value2, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotBetween(Date value1, Date value2) {
            addCriterion("create_date not between", value1, value2, "createDate");
            return (Criteria) this;
        }

        public Criteria andTransportPriceIsNull() {
            addCriterion("transport_price is null");
            return (Criteria) this;
        }

        public Criteria andTransportPriceIsNotNull() {
            addCriterion("transport_price is not null");
            return (Criteria) this;
        }

        public Criteria andTransportPriceEqualTo(BigDecimal value) {
            addCriterion("transport_price =", value, "transportPrice");
            return (Criteria) this;
        }

        public Criteria andTransportPriceNotEqualTo(BigDecimal value) {
            addCriterion("transport_price <>", value, "transportPrice");
            return (Criteria) this;
        }

        public Criteria andTransportPriceGreaterThan(BigDecimal value) {
            addCriterion("transport_price >", value, "transportPrice");
            return (Criteria) this;
        }

        public Criteria andTransportPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("transport_price >=", value, "transportPrice");
            return (Criteria) this;
        }

        public Criteria andTransportPriceLessThan(BigDecimal value) {
            addCriterion("transport_price <", value, "transportPrice");
            return (Criteria) this;
        }

        public Criteria andTransportPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("transport_price <=", value, "transportPrice");
            return (Criteria) this;
        }

        public Criteria andTransportPriceIn(List<BigDecimal> values) {
            addCriterion("transport_price in", values, "transportPrice");
            return (Criteria) this;
        }

        public Criteria andTransportPriceNotIn(List<BigDecimal> values) {
            addCriterion("transport_price not in", values, "transportPrice");
            return (Criteria) this;
        }

        public Criteria andTransportPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("transport_price between", value1, value2, "transportPrice");
            return (Criteria) this;
        }

        public Criteria andTransportPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("transport_price not between", value1, value2, "transportPrice");
            return (Criteria) this;
        }

        public Criteria andDeductionRateIsNull() {
            addCriterion("deduction_rate is null");
            return (Criteria) this;
        }

        public Criteria andDeductionRateIsNotNull() {
            addCriterion("deduction_rate is not null");
            return (Criteria) this;
        }

        public Criteria andDeductionRateEqualTo(Float value) {
            addCriterion("deduction_rate =", value, "deductionRate");
            return (Criteria) this;
        }

        public Criteria andDeductionRateNotEqualTo(Float value) {
            addCriterion("deduction_rate <>", value, "deductionRate");
            return (Criteria) this;
        }

        public Criteria andDeductionRateGreaterThan(Float value) {
            addCriterion("deduction_rate >", value, "deductionRate");
            return (Criteria) this;
        }

        public Criteria andDeductionRateGreaterThanOrEqualTo(Float value) {
            addCriterion("deduction_rate >=", value, "deductionRate");
            return (Criteria) this;
        }

        public Criteria andDeductionRateLessThan(Float value) {
            addCriterion("deduction_rate <", value, "deductionRate");
            return (Criteria) this;
        }

        public Criteria andDeductionRateLessThanOrEqualTo(Float value) {
            addCriterion("deduction_rate <=", value, "deductionRate");
            return (Criteria) this;
        }

        public Criteria andDeductionRateIn(List<Float> values) {
            addCriterion("deduction_rate in", values, "deductionRate");
            return (Criteria) this;
        }

        public Criteria andDeductionRateNotIn(List<Float> values) {
            addCriterion("deduction_rate not in", values, "deductionRate");
            return (Criteria) this;
        }

        public Criteria andDeductionRateBetween(Float value1, Float value2) {
            addCriterion("deduction_rate between", value1, value2, "deductionRate");
            return (Criteria) this;
        }

        public Criteria andDeductionRateNotBetween(Float value1, Float value2) {
            addCriterion("deduction_rate not between", value1, value2, "deductionRate");
            return (Criteria) this;
        }

        public Criteria andDeductionPriceIsNull() {
            addCriterion("deduction_price is null");
            return (Criteria) this;
        }

        public Criteria andDeductionPriceIsNotNull() {
            addCriterion("deduction_price is not null");
            return (Criteria) this;
        }

        public Criteria andDeductionPriceEqualTo(BigDecimal value) {
            addCriterion("deduction_price =", value, "deductionPrice");
            return (Criteria) this;
        }

        public Criteria andDeductionPriceNotEqualTo(BigDecimal value) {
            addCriterion("deduction_price <>", value, "deductionPrice");
            return (Criteria) this;
        }

        public Criteria andDeductionPriceGreaterThan(BigDecimal value) {
            addCriterion("deduction_price >", value, "deductionPrice");
            return (Criteria) this;
        }

        public Criteria andDeductionPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("deduction_price >=", value, "deductionPrice");
            return (Criteria) this;
        }

        public Criteria andDeductionPriceLessThan(BigDecimal value) {
            addCriterion("deduction_price <", value, "deductionPrice");
            return (Criteria) this;
        }

        public Criteria andDeductionPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("deduction_price <=", value, "deductionPrice");
            return (Criteria) this;
        }

        public Criteria andDeductionPriceIn(List<BigDecimal> values) {
            addCriterion("deduction_price in", values, "deductionPrice");
            return (Criteria) this;
        }

        public Criteria andDeductionPriceNotIn(List<BigDecimal> values) {
            addCriterion("deduction_price not in", values, "deductionPrice");
            return (Criteria) this;
        }

        public Criteria andDeductionPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("deduction_price between", value1, value2, "deductionPrice");
            return (Criteria) this;
        }

        public Criteria andDeductionPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("deduction_price not between", value1, value2, "deductionPrice");
            return (Criteria) this;
        }

        public Criteria andPaymentIsNull() {
            addCriterion("payment is null");
            return (Criteria) this;
        }

        public Criteria andPaymentIsNotNull() {
            addCriterion("payment is not null");
            return (Criteria) this;
        }

        public Criteria andPaymentEqualTo(Integer value) {
            addCriterion("payment =", value, "payment");
            return (Criteria) this;
        }

        public Criteria andPaymentNotEqualTo(Integer value) {
            addCriterion("payment <>", value, "payment");
            return (Criteria) this;
        }

        public Criteria andPaymentGreaterThan(Integer value) {
            addCriterion("payment >", value, "payment");
            return (Criteria) this;
        }

        public Criteria andPaymentGreaterThanOrEqualTo(Integer value) {
            addCriterion("payment >=", value, "payment");
            return (Criteria) this;
        }

        public Criteria andPaymentLessThan(Integer value) {
            addCriterion("payment <", value, "payment");
            return (Criteria) this;
        }

        public Criteria andPaymentLessThanOrEqualTo(Integer value) {
            addCriterion("payment <=", value, "payment");
            return (Criteria) this;
        }

        public Criteria andPaymentIn(List<Integer> values) {
            addCriterion("payment in", values, "payment");
            return (Criteria) this;
        }

        public Criteria andPaymentNotIn(List<Integer> values) {
            addCriterion("payment not in", values, "payment");
            return (Criteria) this;
        }

        public Criteria andPaymentBetween(Integer value1, Integer value2) {
            addCriterion("payment between", value1, value2, "payment");
            return (Criteria) this;
        }

        public Criteria andPaymentNotBetween(Integer value1, Integer value2) {
            addCriterion("payment not between", value1, value2, "payment");
            return (Criteria) this;
        }

        public Criteria andTypeIsNull() {
            addCriterion("type is null");
            return (Criteria) this;
        }

        public Criteria andTypeIsNotNull() {
            addCriterion("type is not null");
            return (Criteria) this;
        }

        public Criteria andTypeEqualTo(Byte value) {
            addCriterion("type =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(Byte value) {
            addCriterion("type <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(Byte value) {
            addCriterion("type >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(Byte value) {
            addCriterion("type >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(Byte value) {
            addCriterion("type <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(Byte value) {
            addCriterion("type <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<Byte> values) {
            addCriterion("type in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<Byte> values) {
            addCriterion("type not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(Byte value1, Byte value2) {
            addCriterion("type between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(Byte value1, Byte value2) {
            addCriterion("type not between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andShortBargeTypeIsNull() {
            addCriterion("short_barge_type is null");
            return (Criteria) this;
        }

        public Criteria andShortBargeTypeIsNotNull() {
            addCriterion("short_barge_type is not null");
            return (Criteria) this;
        }

        public Criteria andShortBargeTypeEqualTo(Byte value) {
            addCriterion("short_barge_type =", value, "shortBargeType");
            return (Criteria) this;
        }

        public Criteria andShortBargeTypeNotEqualTo(Byte value) {
            addCriterion("short_barge_type <>", value, "shortBargeType");
            return (Criteria) this;
        }

        public Criteria andShortBargeTypeGreaterThan(Byte value) {
            addCriterion("short_barge_type >", value, "shortBargeType");
            return (Criteria) this;
        }

        public Criteria andShortBargeTypeGreaterThanOrEqualTo(Byte value) {
            addCriterion("short_barge_type >=", value, "shortBargeType");
            return (Criteria) this;
        }

        public Criteria andShortBargeTypeLessThan(Byte value) {
            addCriterion("short_barge_type <", value, "shortBargeType");
            return (Criteria) this;
        }

        public Criteria andShortBargeTypeLessThanOrEqualTo(Byte value) {
            addCriterion("short_barge_type <=", value, "shortBargeType");
            return (Criteria) this;
        }

        public Criteria andShortBargeTypeIn(List<Byte> values) {
            addCriterion("short_barge_type in", values, "shortBargeType");
            return (Criteria) this;
        }

        public Criteria andShortBargeTypeNotIn(List<Byte> values) {
            addCriterion("short_barge_type not in", values, "shortBargeType");
            return (Criteria) this;
        }

        public Criteria andShortBargeTypeBetween(Byte value1, Byte value2) {
            addCriterion("short_barge_type between", value1, value2, "shortBargeType");
            return (Criteria) this;
        }

        public Criteria andShortBargeTypeNotBetween(Byte value1, Byte value2) {
            addCriterion("short_barge_type not between", value1, value2, "shortBargeType");
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