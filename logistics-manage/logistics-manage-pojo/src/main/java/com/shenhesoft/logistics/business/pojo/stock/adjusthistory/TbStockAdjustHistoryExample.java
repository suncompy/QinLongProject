package com.shenhesoft.logistics.business.pojo.stock.adjusthistory;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TbStockAdjustHistoryExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TbStockAdjustHistoryExample() {
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

        public Criteria andStockIdIsNull() {
            addCriterion("stock_id is null");
            return (Criteria) this;
        }

        public Criteria andStockIdIsNotNull() {
            addCriterion("stock_id is not null");
            return (Criteria) this;
        }

        public Criteria andStockIdEqualTo(Integer value) {
            addCriterion("stock_id =", value, "stockId");
            return (Criteria) this;
        }

        public Criteria andStockIdNotEqualTo(Integer value) {
            addCriterion("stock_id <>", value, "stockId");
            return (Criteria) this;
        }

        public Criteria andStockIdGreaterThan(Integer value) {
            addCriterion("stock_id >", value, "stockId");
            return (Criteria) this;
        }

        public Criteria andStockIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("stock_id >=", value, "stockId");
            return (Criteria) this;
        }

        public Criteria andStockIdLessThan(Integer value) {
            addCriterion("stock_id <", value, "stockId");
            return (Criteria) this;
        }

        public Criteria andStockIdLessThanOrEqualTo(Integer value) {
            addCriterion("stock_id <=", value, "stockId");
            return (Criteria) this;
        }

        public Criteria andStockIdIn(List<Integer> values) {
            addCriterion("stock_id in", values, "stockId");
            return (Criteria) this;
        }

        public Criteria andStockIdNotIn(List<Integer> values) {
            addCriterion("stock_id not in", values, "stockId");
            return (Criteria) this;
        }

        public Criteria andStockIdBetween(Integer value1, Integer value2) {
            addCriterion("stock_id between", value1, value2, "stockId");
            return (Criteria) this;
        }

        public Criteria andStockIdNotBetween(Integer value1, Integer value2) {
            addCriterion("stock_id not between", value1, value2, "stockId");
            return (Criteria) this;
        }

        public Criteria andAdjustStockIsNull() {
            addCriterion("adjust_stock is null");
            return (Criteria) this;
        }

        public Criteria andAdjustStockIsNotNull() {
            addCriterion("adjust_stock is not null");
            return (Criteria) this;
        }

        public Criteria andAdjustStockEqualTo(BigDecimal value) {
            addCriterion("adjust_stock =", value, "adjustStock");
            return (Criteria) this;
        }

        public Criteria andAdjustStockNotEqualTo(BigDecimal value) {
            addCriterion("adjust_stock <>", value, "adjustStock");
            return (Criteria) this;
        }

        public Criteria andAdjustStockGreaterThan(BigDecimal value) {
            addCriterion("adjust_stock >", value, "adjustStock");
            return (Criteria) this;
        }

        public Criteria andAdjustStockGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("adjust_stock >=", value, "adjustStock");
            return (Criteria) this;
        }

        public Criteria andAdjustStockLessThan(BigDecimal value) {
            addCriterion("adjust_stock <", value, "adjustStock");
            return (Criteria) this;
        }

        public Criteria andAdjustStockLessThanOrEqualTo(BigDecimal value) {
            addCriterion("adjust_stock <=", value, "adjustStock");
            return (Criteria) this;
        }

        public Criteria andAdjustStockIn(List<BigDecimal> values) {
            addCriterion("adjust_stock in", values, "adjustStock");
            return (Criteria) this;
        }

        public Criteria andAdjustStockNotIn(List<BigDecimal> values) {
            addCriterion("adjust_stock not in", values, "adjustStock");
            return (Criteria) this;
        }

        public Criteria andAdjustStockBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("adjust_stock between", value1, value2, "adjustStock");
            return (Criteria) this;
        }

        public Criteria andAdjustStockNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("adjust_stock not between", value1, value2, "adjustStock");
            return (Criteria) this;
        }

        public Criteria andAdjustDateIsNull() {
            addCriterion("adjust_date is null");
            return (Criteria) this;
        }

        public Criteria andAdjustDateIsNotNull() {
            addCriterion("adjust_date is not null");
            return (Criteria) this;
        }

        public Criteria andAdjustDateEqualTo(Date value) {
            addCriterion("adjust_date =", value, "adjustDate");
            return (Criteria) this;
        }

        public Criteria andAdjustDateNotEqualTo(Date value) {
            addCriterion("adjust_date <>", value, "adjustDate");
            return (Criteria) this;
        }

        public Criteria andAdjustDateGreaterThan(Date value) {
            addCriterion("adjust_date >", value, "adjustDate");
            return (Criteria) this;
        }

        public Criteria andAdjustDateGreaterThanOrEqualTo(Date value) {
            addCriterion("adjust_date >=", value, "adjustDate");
            return (Criteria) this;
        }

        public Criteria andAdjustDateLessThan(Date value) {
            addCriterion("adjust_date <", value, "adjustDate");
            return (Criteria) this;
        }

        public Criteria andAdjustDateLessThanOrEqualTo(Date value) {
            addCriterion("adjust_date <=", value, "adjustDate");
            return (Criteria) this;
        }

        public Criteria andAdjustDateIn(List<Date> values) {
            addCriterion("adjust_date in", values, "adjustDate");
            return (Criteria) this;
        }

        public Criteria andAdjustDateNotIn(List<Date> values) {
            addCriterion("adjust_date not in", values, "adjustDate");
            return (Criteria) this;
        }

        public Criteria andAdjustDateBetween(Date value1, Date value2) {
            addCriterion("adjust_date between", value1, value2, "adjustDate");
            return (Criteria) this;
        }

        public Criteria andAdjustDateNotBetween(Date value1, Date value2) {
            addCriterion("adjust_date not between", value1, value2, "adjustDate");
            return (Criteria) this;
        }

        public Criteria andAdjustorIsNull() {
            addCriterion("adjustor is null");
            return (Criteria) this;
        }

        public Criteria andAdjustorIsNotNull() {
            addCriterion("adjustor is not null");
            return (Criteria) this;
        }

        public Criteria andAdjustorEqualTo(String value) {
            addCriterion("adjustor =", value, "adjustor");
            return (Criteria) this;
        }

        public Criteria andAdjustorNotEqualTo(String value) {
            addCriterion("adjustor <>", value, "adjustor");
            return (Criteria) this;
        }

        public Criteria andAdjustorGreaterThan(String value) {
            addCriterion("adjustor >", value, "adjustor");
            return (Criteria) this;
        }

        public Criteria andAdjustorGreaterThanOrEqualTo(String value) {
            addCriterion("adjustor >=", value, "adjustor");
            return (Criteria) this;
        }

        public Criteria andAdjustorLessThan(String value) {
            addCriterion("adjustor <", value, "adjustor");
            return (Criteria) this;
        }

        public Criteria andAdjustorLessThanOrEqualTo(String value) {
            addCriterion("adjustor <=", value, "adjustor");
            return (Criteria) this;
        }

        public Criteria andAdjustorLike(String value) {
            addCriterion("adjustor like", value, "adjustor");
            return (Criteria) this;
        }

        public Criteria andAdjustorNotLike(String value) {
            addCriterion("adjustor not like", value, "adjustor");
            return (Criteria) this;
        }

        public Criteria andAdjustorIn(List<String> values) {
            addCriterion("adjustor in", values, "adjustor");
            return (Criteria) this;
        }

        public Criteria andAdjustorNotIn(List<String> values) {
            addCriterion("adjustor not in", values, "adjustor");
            return (Criteria) this;
        }

        public Criteria andAdjustorBetween(String value1, String value2) {
            addCriterion("adjustor between", value1, value2, "adjustor");
            return (Criteria) this;
        }

        public Criteria andAdjustorNotBetween(String value1, String value2) {
            addCriterion("adjustor not between", value1, value2, "adjustor");
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