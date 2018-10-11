package com.shenhesoft.logistics.manage.pojo.exception;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TbExceptionMsgExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TbExceptionMsgExample() {
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

        public Criteria andExceptionIdIsNull() {
            addCriterion("exception_id is null");
            return (Criteria) this;
        }

        public Criteria andExceptionIdIsNotNull() {
            addCriterion("exception_id is not null");
            return (Criteria) this;
        }

        public Criteria andExceptionIdEqualTo(Integer value) {
            addCriterion("exception_id =", value, "exceptionId");
            return (Criteria) this;
        }

        public Criteria andExceptionIdNotEqualTo(Integer value) {
            addCriterion("exception_id <>", value, "exceptionId");
            return (Criteria) this;
        }

        public Criteria andExceptionIdGreaterThan(Integer value) {
            addCriterion("exception_id >", value, "exceptionId");
            return (Criteria) this;
        }

        public Criteria andExceptionIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("exception_id >=", value, "exceptionId");
            return (Criteria) this;
        }

        public Criteria andExceptionIdLessThan(Integer value) {
            addCriterion("exception_id <", value, "exceptionId");
            return (Criteria) this;
        }

        public Criteria andExceptionIdLessThanOrEqualTo(Integer value) {
            addCriterion("exception_id <=", value, "exceptionId");
            return (Criteria) this;
        }

        public Criteria andExceptionIdIn(List<Integer> values) {
            addCriterion("exception_id in", values, "exceptionId");
            return (Criteria) this;
        }

        public Criteria andExceptionIdNotIn(List<Integer> values) {
            addCriterion("exception_id not in", values, "exceptionId");
            return (Criteria) this;
        }

        public Criteria andExceptionIdBetween(Integer value1, Integer value2) {
            addCriterion("exception_id between", value1, value2, "exceptionId");
            return (Criteria) this;
        }

        public Criteria andExceptionIdNotBetween(Integer value1, Integer value2) {
            addCriterion("exception_id not between", value1, value2, "exceptionId");
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

        public Criteria andOrderIdIsNull() {
            addCriterion("order_id is null");
            return (Criteria) this;
        }

        public Criteria andOrderIdIsNotNull() {
            addCriterion("order_id is not null");
            return (Criteria) this;
        }

        public Criteria andOrderIdEqualTo(Integer value) {
            addCriterion("order_id =", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotEqualTo(Integer value) {
            addCriterion("order_id <>", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdGreaterThan(Integer value) {
            addCriterion("order_id >", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("order_id >=", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdLessThan(Integer value) {
            addCriterion("order_id <", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdLessThanOrEqualTo(Integer value) {
            addCriterion("order_id <=", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdIn(List<Integer> values) {
            addCriterion("order_id in", values, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotIn(List<Integer> values) {
            addCriterion("order_id not in", values, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdBetween(Integer value1, Integer value2) {
            addCriterion("order_id between", value1, value2, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotBetween(Integer value1, Integer value2) {
            addCriterion("order_id not between", value1, value2, "orderId");
            return (Criteria) this;
        }

        public Criteria andTrainOrderIdIsNull() {
            addCriterion("train_order_id is null");
            return (Criteria) this;
        }

        public Criteria andTrainOrderIdIsNotNull() {
            addCriterion("train_order_id is not null");
            return (Criteria) this;
        }

        public Criteria andTrainOrderIdEqualTo(Integer value) {
            addCriterion("train_order_id =", value, "trainOrderId");
            return (Criteria) this;
        }

        public Criteria andTrainOrderIdNotEqualTo(Integer value) {
            addCriterion("train_order_id <>", value, "trainOrderId");
            return (Criteria) this;
        }

        public Criteria andTrainOrderIdGreaterThan(Integer value) {
            addCriterion("train_order_id >", value, "trainOrderId");
            return (Criteria) this;
        }

        public Criteria andTrainOrderIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("train_order_id >=", value, "trainOrderId");
            return (Criteria) this;
        }

        public Criteria andTrainOrderIdLessThan(Integer value) {
            addCriterion("train_order_id <", value, "trainOrderId");
            return (Criteria) this;
        }

        public Criteria andTrainOrderIdLessThanOrEqualTo(Integer value) {
            addCriterion("train_order_id <=", value, "trainOrderId");
            return (Criteria) this;
        }

        public Criteria andTrainOrderIdIn(List<Integer> values) {
            addCriterion("train_order_id in", values, "trainOrderId");
            return (Criteria) this;
        }

        public Criteria andTrainOrderIdNotIn(List<Integer> values) {
            addCriterion("train_order_id not in", values, "trainOrderId");
            return (Criteria) this;
        }

        public Criteria andTrainOrderIdBetween(Integer value1, Integer value2) {
            addCriterion("train_order_id between", value1, value2, "trainOrderId");
            return (Criteria) this;
        }

        public Criteria andTrainOrderIdNotBetween(Integer value1, Integer value2) {
            addCriterion("train_order_id not between", value1, value2, "trainOrderId");
            return (Criteria) this;
        }

        public Criteria andShortTrainFlagIsNull() {
            addCriterion("short_train_flag is null");
            return (Criteria) this;
        }

        public Criteria andShortTrainFlagIsNotNull() {
            addCriterion("short_train_flag is not null");
            return (Criteria) this;
        }

        public Criteria andShortTrainFlagEqualTo(Byte value) {
            addCriterion("short_train_flag =", value, "shortTrainFlag");
            return (Criteria) this;
        }

        public Criteria andShortTrainFlagNotEqualTo(Byte value) {
            addCriterion("short_train_flag <>", value, "shortTrainFlag");
            return (Criteria) this;
        }

        public Criteria andShortTrainFlagGreaterThan(Byte value) {
            addCriterion("short_train_flag >", value, "shortTrainFlag");
            return (Criteria) this;
        }

        public Criteria andShortTrainFlagGreaterThanOrEqualTo(Byte value) {
            addCriterion("short_train_flag >=", value, "shortTrainFlag");
            return (Criteria) this;
        }

        public Criteria andShortTrainFlagLessThan(Byte value) {
            addCriterion("short_train_flag <", value, "shortTrainFlag");
            return (Criteria) this;
        }

        public Criteria andShortTrainFlagLessThanOrEqualTo(Byte value) {
            addCriterion("short_train_flag <=", value, "shortTrainFlag");
            return (Criteria) this;
        }

        public Criteria andShortTrainFlagIn(List<Byte> values) {
            addCriterion("short_train_flag in", values, "shortTrainFlag");
            return (Criteria) this;
        }

        public Criteria andShortTrainFlagNotIn(List<Byte> values) {
            addCriterion("short_train_flag not in", values, "shortTrainFlag");
            return (Criteria) this;
        }

        public Criteria andShortTrainFlagBetween(Byte value1, Byte value2) {
            addCriterion("short_train_flag between", value1, value2, "shortTrainFlag");
            return (Criteria) this;
        }

        public Criteria andShortTrainFlagNotBetween(Byte value1, Byte value2) {
            addCriterion("short_train_flag not between", value1, value2, "shortTrainFlag");
            return (Criteria) this;
        }

        public Criteria andOrderStatusIsNull() {
            addCriterion("order_status is null");
            return (Criteria) this;
        }

        public Criteria andOrderStatusIsNotNull() {
            addCriterion("order_status is not null");
            return (Criteria) this;
        }

        public Criteria andOrderStatusEqualTo(Byte value) {
            addCriterion("order_status =", value, "orderStatus");
            return (Criteria) this;
        }

        public Criteria andOrderStatusNotEqualTo(Byte value) {
            addCriterion("order_status <>", value, "orderStatus");
            return (Criteria) this;
        }

        public Criteria andOrderStatusGreaterThan(Byte value) {
            addCriterion("order_status >", value, "orderStatus");
            return (Criteria) this;
        }

        public Criteria andOrderStatusGreaterThanOrEqualTo(Byte value) {
            addCriterion("order_status >=", value, "orderStatus");
            return (Criteria) this;
        }

        public Criteria andOrderStatusLessThan(Byte value) {
            addCriterion("order_status <", value, "orderStatus");
            return (Criteria) this;
        }

        public Criteria andOrderStatusLessThanOrEqualTo(Byte value) {
            addCriterion("order_status <=", value, "orderStatus");
            return (Criteria) this;
        }

        public Criteria andOrderStatusIn(List<Byte> values) {
            addCriterion("order_status in", values, "orderStatus");
            return (Criteria) this;
        }

        public Criteria andOrderStatusNotIn(List<Byte> values) {
            addCriterion("order_status not in", values, "orderStatus");
            return (Criteria) this;
        }

        public Criteria andOrderStatusBetween(Byte value1, Byte value2) {
            addCriterion("order_status between", value1, value2, "orderStatus");
            return (Criteria) this;
        }

        public Criteria andOrderStatusNotBetween(Byte value1, Byte value2) {
            addCriterion("order_status not between", value1, value2, "orderStatus");
            return (Criteria) this;
        }

        public Criteria andExceptionReasonIdIsNull() {
            addCriterion("exception_reason_id is null");
            return (Criteria) this;
        }

        public Criteria andExceptionReasonIdIsNotNull() {
            addCriterion("exception_reason_id is not null");
            return (Criteria) this;
        }

        public Criteria andExceptionReasonIdEqualTo(Integer value) {
            addCriterion("exception_reason_id =", value, "exceptionReasonId");
            return (Criteria) this;
        }

        public Criteria andExceptionReasonIdNotEqualTo(Integer value) {
            addCriterion("exception_reason_id <>", value, "exceptionReasonId");
            return (Criteria) this;
        }

        public Criteria andExceptionReasonIdGreaterThan(Integer value) {
            addCriterion("exception_reason_id >", value, "exceptionReasonId");
            return (Criteria) this;
        }

        public Criteria andExceptionReasonIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("exception_reason_id >=", value, "exceptionReasonId");
            return (Criteria) this;
        }

        public Criteria andExceptionReasonIdLessThan(Integer value) {
            addCriterion("exception_reason_id <", value, "exceptionReasonId");
            return (Criteria) this;
        }

        public Criteria andExceptionReasonIdLessThanOrEqualTo(Integer value) {
            addCriterion("exception_reason_id <=", value, "exceptionReasonId");
            return (Criteria) this;
        }

        public Criteria andExceptionReasonIdIn(List<Integer> values) {
            addCriterion("exception_reason_id in", values, "exceptionReasonId");
            return (Criteria) this;
        }

        public Criteria andExceptionReasonIdNotIn(List<Integer> values) {
            addCriterion("exception_reason_id not in", values, "exceptionReasonId");
            return (Criteria) this;
        }

        public Criteria andExceptionReasonIdBetween(Integer value1, Integer value2) {
            addCriterion("exception_reason_id between", value1, value2, "exceptionReasonId");
            return (Criteria) this;
        }

        public Criteria andExceptionReasonIdNotBetween(Integer value1, Integer value2) {
            addCriterion("exception_reason_id not between", value1, value2, "exceptionReasonId");
            return (Criteria) this;
        }

        public Criteria andExceptionReasonIsNull() {
            addCriterion("exception_reason is null");
            return (Criteria) this;
        }

        public Criteria andExceptionReasonIsNotNull() {
            addCriterion("exception_reason is not null");
            return (Criteria) this;
        }

        public Criteria andExceptionReasonEqualTo(String value) {
            addCriterion("exception_reason =", value, "exceptionReason");
            return (Criteria) this;
        }

        public Criteria andExceptionReasonNotEqualTo(String value) {
            addCriterion("exception_reason <>", value, "exceptionReason");
            return (Criteria) this;
        }

        public Criteria andExceptionReasonGreaterThan(String value) {
            addCriterion("exception_reason >", value, "exceptionReason");
            return (Criteria) this;
        }

        public Criteria andExceptionReasonGreaterThanOrEqualTo(String value) {
            addCriterion("exception_reason >=", value, "exceptionReason");
            return (Criteria) this;
        }

        public Criteria andExceptionReasonLessThan(String value) {
            addCriterion("exception_reason <", value, "exceptionReason");
            return (Criteria) this;
        }

        public Criteria andExceptionReasonLessThanOrEqualTo(String value) {
            addCriterion("exception_reason <=", value, "exceptionReason");
            return (Criteria) this;
        }

        public Criteria andExceptionReasonLike(String value) {
            addCriterion("exception_reason like", value, "exceptionReason");
            return (Criteria) this;
        }

        public Criteria andExceptionReasonNotLike(String value) {
            addCriterion("exception_reason not like", value, "exceptionReason");
            return (Criteria) this;
        }

        public Criteria andExceptionReasonIn(List<String> values) {
            addCriterion("exception_reason in", values, "exceptionReason");
            return (Criteria) this;
        }

        public Criteria andExceptionReasonNotIn(List<String> values) {
            addCriterion("exception_reason not in", values, "exceptionReason");
            return (Criteria) this;
        }

        public Criteria andExceptionReasonBetween(String value1, String value2) {
            addCriterion("exception_reason between", value1, value2, "exceptionReason");
            return (Criteria) this;
        }

        public Criteria andExceptionReasonNotBetween(String value1, String value2) {
            addCriterion("exception_reason not between", value1, value2, "exceptionReason");
            return (Criteria) this;
        }

        public Criteria andExceptionReasonDetailIsNull() {
            addCriterion("exception_reason_detail is null");
            return (Criteria) this;
        }

        public Criteria andExceptionReasonDetailIsNotNull() {
            addCriterion("exception_reason_detail is not null");
            return (Criteria) this;
        }

        public Criteria andExceptionReasonDetailEqualTo(String value) {
            addCriterion("exception_reason_detail =", value, "exceptionReasonDetail");
            return (Criteria) this;
        }

        public Criteria andExceptionReasonDetailNotEqualTo(String value) {
            addCriterion("exception_reason_detail <>", value, "exceptionReasonDetail");
            return (Criteria) this;
        }

        public Criteria andExceptionReasonDetailGreaterThan(String value) {
            addCriterion("exception_reason_detail >", value, "exceptionReasonDetail");
            return (Criteria) this;
        }

        public Criteria andExceptionReasonDetailGreaterThanOrEqualTo(String value) {
            addCriterion("exception_reason_detail >=", value, "exceptionReasonDetail");
            return (Criteria) this;
        }

        public Criteria andExceptionReasonDetailLessThan(String value) {
            addCriterion("exception_reason_detail <", value, "exceptionReasonDetail");
            return (Criteria) this;
        }

        public Criteria andExceptionReasonDetailLessThanOrEqualTo(String value) {
            addCriterion("exception_reason_detail <=", value, "exceptionReasonDetail");
            return (Criteria) this;
        }

        public Criteria andExceptionReasonDetailLike(String value) {
            addCriterion("exception_reason_detail like", value, "exceptionReasonDetail");
            return (Criteria) this;
        }

        public Criteria andExceptionReasonDetailNotLike(String value) {
            addCriterion("exception_reason_detail not like", value, "exceptionReasonDetail");
            return (Criteria) this;
        }

        public Criteria andExceptionReasonDetailIn(List<String> values) {
            addCriterion("exception_reason_detail in", values, "exceptionReasonDetail");
            return (Criteria) this;
        }

        public Criteria andExceptionReasonDetailNotIn(List<String> values) {
            addCriterion("exception_reason_detail not in", values, "exceptionReasonDetail");
            return (Criteria) this;
        }

        public Criteria andExceptionReasonDetailBetween(String value1, String value2) {
            addCriterion("exception_reason_detail between", value1, value2, "exceptionReasonDetail");
            return (Criteria) this;
        }

        public Criteria andExceptionReasonDetailNotBetween(String value1, String value2) {
            addCriterion("exception_reason_detail not between", value1, value2, "exceptionReasonDetail");
            return (Criteria) this;
        }

        public Criteria andExceptionSourceIsNull() {
            addCriterion("exception_source is null");
            return (Criteria) this;
        }

        public Criteria andExceptionSourceIsNotNull() {
            addCriterion("exception_source is not null");
            return (Criteria) this;
        }

        public Criteria andExceptionSourceEqualTo(Byte value) {
            addCriterion("exception_source =", value, "exceptionSource");
            return (Criteria) this;
        }

        public Criteria andExceptionSourceNotEqualTo(Byte value) {
            addCriterion("exception_source <>", value, "exceptionSource");
            return (Criteria) this;
        }

        public Criteria andExceptionSourceGreaterThan(Byte value) {
            addCriterion("exception_source >", value, "exceptionSource");
            return (Criteria) this;
        }

        public Criteria andExceptionSourceGreaterThanOrEqualTo(Byte value) {
            addCriterion("exception_source >=", value, "exceptionSource");
            return (Criteria) this;
        }

        public Criteria andExceptionSourceLessThan(Byte value) {
            addCriterion("exception_source <", value, "exceptionSource");
            return (Criteria) this;
        }

        public Criteria andExceptionSourceLessThanOrEqualTo(Byte value) {
            addCriterion("exception_source <=", value, "exceptionSource");
            return (Criteria) this;
        }

        public Criteria andExceptionSourceIn(List<Byte> values) {
            addCriterion("exception_source in", values, "exceptionSource");
            return (Criteria) this;
        }

        public Criteria andExceptionSourceNotIn(List<Byte> values) {
            addCriterion("exception_source not in", values, "exceptionSource");
            return (Criteria) this;
        }

        public Criteria andExceptionSourceBetween(Byte value1, Byte value2) {
            addCriterion("exception_source between", value1, value2, "exceptionSource");
            return (Criteria) this;
        }

        public Criteria andExceptionSourceNotBetween(Byte value1, Byte value2) {
            addCriterion("exception_source not between", value1, value2, "exceptionSource");
            return (Criteria) this;
        }

        public Criteria andSubmitUserIdIsNull() {
            addCriterion("submit_user_id is null");
            return (Criteria) this;
        }

        public Criteria andSubmitUserIdIsNotNull() {
            addCriterion("submit_user_id is not null");
            return (Criteria) this;
        }

        public Criteria andSubmitUserIdEqualTo(Integer value) {
            addCriterion("submit_user_id =", value, "submitUserId");
            return (Criteria) this;
        }

        public Criteria andSubmitUserIdNotEqualTo(Integer value) {
            addCriterion("submit_user_id <>", value, "submitUserId");
            return (Criteria) this;
        }

        public Criteria andSubmitUserIdGreaterThan(Integer value) {
            addCriterion("submit_user_id >", value, "submitUserId");
            return (Criteria) this;
        }

        public Criteria andSubmitUserIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("submit_user_id >=", value, "submitUserId");
            return (Criteria) this;
        }

        public Criteria andSubmitUserIdLessThan(Integer value) {
            addCriterion("submit_user_id <", value, "submitUserId");
            return (Criteria) this;
        }

        public Criteria andSubmitUserIdLessThanOrEqualTo(Integer value) {
            addCriterion("submit_user_id <=", value, "submitUserId");
            return (Criteria) this;
        }

        public Criteria andSubmitUserIdIn(List<Integer> values) {
            addCriterion("submit_user_id in", values, "submitUserId");
            return (Criteria) this;
        }

        public Criteria andSubmitUserIdNotIn(List<Integer> values) {
            addCriterion("submit_user_id not in", values, "submitUserId");
            return (Criteria) this;
        }

        public Criteria andSubmitUserIdBetween(Integer value1, Integer value2) {
            addCriterion("submit_user_id between", value1, value2, "submitUserId");
            return (Criteria) this;
        }

        public Criteria andSubmitUserIdNotBetween(Integer value1, Integer value2) {
            addCriterion("submit_user_id not between", value1, value2, "submitUserId");
            return (Criteria) this;
        }

        public Criteria andSubmitDateIsNull() {
            addCriterion("submit_date is null");
            return (Criteria) this;
        }

        public Criteria andSubmitDateIsNotNull() {
            addCriterion("submit_date is not null");
            return (Criteria) this;
        }

        public Criteria andSubmitDateEqualTo(Date value) {
            addCriterion("submit_date =", value, "submitDate");
            return (Criteria) this;
        }

        public Criteria andSubmitDateNotEqualTo(Date value) {
            addCriterion("submit_date <>", value, "submitDate");
            return (Criteria) this;
        }

        public Criteria andSubmitDateGreaterThan(Date value) {
            addCriterion("submit_date >", value, "submitDate");
            return (Criteria) this;
        }

        public Criteria andSubmitDateGreaterThanOrEqualTo(Date value) {
            addCriterion("submit_date >=", value, "submitDate");
            return (Criteria) this;
        }

        public Criteria andSubmitDateLessThan(Date value) {
            addCriterion("submit_date <", value, "submitDate");
            return (Criteria) this;
        }

        public Criteria andSubmitDateLessThanOrEqualTo(Date value) {
            addCriterion("submit_date <=", value, "submitDate");
            return (Criteria) this;
        }

        public Criteria andSubmitDateIn(List<Date> values) {
            addCriterion("submit_date in", values, "submitDate");
            return (Criteria) this;
        }

        public Criteria andSubmitDateNotIn(List<Date> values) {
            addCriterion("submit_date not in", values, "submitDate");
            return (Criteria) this;
        }

        public Criteria andSubmitDateBetween(Date value1, Date value2) {
            addCriterion("submit_date between", value1, value2, "submitDate");
            return (Criteria) this;
        }

        public Criteria andSubmitDateNotBetween(Date value1, Date value2) {
            addCriterion("submit_date not between", value1, value2, "submitDate");
            return (Criteria) this;
        }

        public Criteria andAffirmStatusIsNull() {
            addCriterion("affirm_status is null");
            return (Criteria) this;
        }

        public Criteria andAffirmStatusIsNotNull() {
            addCriterion("affirm_status is not null");
            return (Criteria) this;
        }

        public Criteria andAffirmStatusEqualTo(Byte value) {
            addCriterion("affirm_status =", value, "affirmStatus");
            return (Criteria) this;
        }

        public Criteria andAffirmStatusNotEqualTo(Byte value) {
            addCriterion("affirm_status <>", value, "affirmStatus");
            return (Criteria) this;
        }

        public Criteria andAffirmStatusGreaterThan(Byte value) {
            addCriterion("affirm_status >", value, "affirmStatus");
            return (Criteria) this;
        }

        public Criteria andAffirmStatusGreaterThanOrEqualTo(Byte value) {
            addCriterion("affirm_status >=", value, "affirmStatus");
            return (Criteria) this;
        }

        public Criteria andAffirmStatusLessThan(Byte value) {
            addCriterion("affirm_status <", value, "affirmStatus");
            return (Criteria) this;
        }

        public Criteria andAffirmStatusLessThanOrEqualTo(Byte value) {
            addCriterion("affirm_status <=", value, "affirmStatus");
            return (Criteria) this;
        }

        public Criteria andAffirmStatusIn(List<Byte> values) {
            addCriterion("affirm_status in", values, "affirmStatus");
            return (Criteria) this;
        }

        public Criteria andAffirmStatusNotIn(List<Byte> values) {
            addCriterion("affirm_status not in", values, "affirmStatus");
            return (Criteria) this;
        }

        public Criteria andAffirmStatusBetween(Byte value1, Byte value2) {
            addCriterion("affirm_status between", value1, value2, "affirmStatus");
            return (Criteria) this;
        }

        public Criteria andAffirmStatusNotBetween(Byte value1, Byte value2) {
            addCriterion("affirm_status not between", value1, value2, "affirmStatus");
            return (Criteria) this;
        }

        public Criteria andAffirmUserIdIsNull() {
            addCriterion("affirm_user_id is null");
            return (Criteria) this;
        }

        public Criteria andAffirmUserIdIsNotNull() {
            addCriterion("affirm_user_id is not null");
            return (Criteria) this;
        }

        public Criteria andAffirmUserIdEqualTo(Integer value) {
            addCriterion("affirm_user_id =", value, "affirmUserId");
            return (Criteria) this;
        }

        public Criteria andAffirmUserIdNotEqualTo(Integer value) {
            addCriterion("affirm_user_id <>", value, "affirmUserId");
            return (Criteria) this;
        }

        public Criteria andAffirmUserIdGreaterThan(Integer value) {
            addCriterion("affirm_user_id >", value, "affirmUserId");
            return (Criteria) this;
        }

        public Criteria andAffirmUserIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("affirm_user_id >=", value, "affirmUserId");
            return (Criteria) this;
        }

        public Criteria andAffirmUserIdLessThan(Integer value) {
            addCriterion("affirm_user_id <", value, "affirmUserId");
            return (Criteria) this;
        }

        public Criteria andAffirmUserIdLessThanOrEqualTo(Integer value) {
            addCriterion("affirm_user_id <=", value, "affirmUserId");
            return (Criteria) this;
        }

        public Criteria andAffirmUserIdIn(List<Integer> values) {
            addCriterion("affirm_user_id in", values, "affirmUserId");
            return (Criteria) this;
        }

        public Criteria andAffirmUserIdNotIn(List<Integer> values) {
            addCriterion("affirm_user_id not in", values, "affirmUserId");
            return (Criteria) this;
        }

        public Criteria andAffirmUserIdBetween(Integer value1, Integer value2) {
            addCriterion("affirm_user_id between", value1, value2, "affirmUserId");
            return (Criteria) this;
        }

        public Criteria andAffirmUserIdNotBetween(Integer value1, Integer value2) {
            addCriterion("affirm_user_id not between", value1, value2, "affirmUserId");
            return (Criteria) this;
        }

        public Criteria andAffirmDateIsNull() {
            addCriterion("affirm_date is null");
            return (Criteria) this;
        }

        public Criteria andAffirmDateIsNotNull() {
            addCriterion("affirm_date is not null");
            return (Criteria) this;
        }

        public Criteria andAffirmDateEqualTo(Date value) {
            addCriterion("affirm_date =", value, "affirmDate");
            return (Criteria) this;
        }

        public Criteria andAffirmDateNotEqualTo(Date value) {
            addCriterion("affirm_date <>", value, "affirmDate");
            return (Criteria) this;
        }

        public Criteria andAffirmDateGreaterThan(Date value) {
            addCriterion("affirm_date >", value, "affirmDate");
            return (Criteria) this;
        }

        public Criteria andAffirmDateGreaterThanOrEqualTo(Date value) {
            addCriterion("affirm_date >=", value, "affirmDate");
            return (Criteria) this;
        }

        public Criteria andAffirmDateLessThan(Date value) {
            addCriterion("affirm_date <", value, "affirmDate");
            return (Criteria) this;
        }

        public Criteria andAffirmDateLessThanOrEqualTo(Date value) {
            addCriterion("affirm_date <=", value, "affirmDate");
            return (Criteria) this;
        }

        public Criteria andAffirmDateIn(List<Date> values) {
            addCriterion("affirm_date in", values, "affirmDate");
            return (Criteria) this;
        }

        public Criteria andAffirmDateNotIn(List<Date> values) {
            addCriterion("affirm_date not in", values, "affirmDate");
            return (Criteria) this;
        }

        public Criteria andAffirmDateBetween(Date value1, Date value2) {
            addCriterion("affirm_date between", value1, value2, "affirmDate");
            return (Criteria) this;
        }

        public Criteria andAffirmDateNotBetween(Date value1, Date value2) {
            addCriterion("affirm_date not between", value1, value2, "affirmDate");
            return (Criteria) this;
        }

        public Criteria andResolveStatusIsNull() {
            addCriterion("resolve_status is null");
            return (Criteria) this;
        }

        public Criteria andResolveStatusIsNotNull() {
            addCriterion("resolve_status is not null");
            return (Criteria) this;
        }

        public Criteria andResolveStatusEqualTo(Byte value) {
            addCriterion("resolve_status =", value, "resolveStatus");
            return (Criteria) this;
        }

        public Criteria andResolveStatusNotEqualTo(Byte value) {
            addCriterion("resolve_status <>", value, "resolveStatus");
            return (Criteria) this;
        }

        public Criteria andResolveStatusGreaterThan(Byte value) {
            addCriterion("resolve_status >", value, "resolveStatus");
            return (Criteria) this;
        }

        public Criteria andResolveStatusGreaterThanOrEqualTo(Byte value) {
            addCriterion("resolve_status >=", value, "resolveStatus");
            return (Criteria) this;
        }

        public Criteria andResolveStatusLessThan(Byte value) {
            addCriterion("resolve_status <", value, "resolveStatus");
            return (Criteria) this;
        }

        public Criteria andResolveStatusLessThanOrEqualTo(Byte value) {
            addCriterion("resolve_status <=", value, "resolveStatus");
            return (Criteria) this;
        }

        public Criteria andResolveStatusIn(List<Byte> values) {
            addCriterion("resolve_status in", values, "resolveStatus");
            return (Criteria) this;
        }

        public Criteria andResolveStatusNotIn(List<Byte> values) {
            addCriterion("resolve_status not in", values, "resolveStatus");
            return (Criteria) this;
        }

        public Criteria andResolveStatusBetween(Byte value1, Byte value2) {
            addCriterion("resolve_status between", value1, value2, "resolveStatus");
            return (Criteria) this;
        }

        public Criteria andResolveStatusNotBetween(Byte value1, Byte value2) {
            addCriterion("resolve_status not between", value1, value2, "resolveStatus");
            return (Criteria) this;
        }

        public Criteria andResolveDateIsNull() {
            addCriterion("resolve_date is null");
            return (Criteria) this;
        }

        public Criteria andResolveDateIsNotNull() {
            addCriterion("resolve_date is not null");
            return (Criteria) this;
        }

        public Criteria andResolveDateEqualTo(Date value) {
            addCriterion("resolve_date =", value, "resolveDate");
            return (Criteria) this;
        }

        public Criteria andResolveDateNotEqualTo(Date value) {
            addCriterion("resolve_date <>", value, "resolveDate");
            return (Criteria) this;
        }

        public Criteria andResolveDateGreaterThan(Date value) {
            addCriterion("resolve_date >", value, "resolveDate");
            return (Criteria) this;
        }

        public Criteria andResolveDateGreaterThanOrEqualTo(Date value) {
            addCriterion("resolve_date >=", value, "resolveDate");
            return (Criteria) this;
        }

        public Criteria andResolveDateLessThan(Date value) {
            addCriterion("resolve_date <", value, "resolveDate");
            return (Criteria) this;
        }

        public Criteria andResolveDateLessThanOrEqualTo(Date value) {
            addCriterion("resolve_date <=", value, "resolveDate");
            return (Criteria) this;
        }

        public Criteria andResolveDateIn(List<Date> values) {
            addCriterion("resolve_date in", values, "resolveDate");
            return (Criteria) this;
        }

        public Criteria andResolveDateNotIn(List<Date> values) {
            addCriterion("resolve_date not in", values, "resolveDate");
            return (Criteria) this;
        }

        public Criteria andResolveDateBetween(Date value1, Date value2) {
            addCriterion("resolve_date between", value1, value2, "resolveDate");
            return (Criteria) this;
        }

        public Criteria andResolveDateNotBetween(Date value1, Date value2) {
            addCriterion("resolve_date not between", value1, value2, "resolveDate");
            return (Criteria) this;
        }

        public Criteria andResolveUserIdIsNull() {
            addCriterion("resolve_user_id is null");
            return (Criteria) this;
        }

        public Criteria andResolveUserIdIsNotNull() {
            addCriterion("resolve_user_id is not null");
            return (Criteria) this;
        }

        public Criteria andResolveUserIdEqualTo(Integer value) {
            addCriterion("resolve_user_id =", value, "resolveUserId");
            return (Criteria) this;
        }

        public Criteria andResolveUserIdNotEqualTo(Integer value) {
            addCriterion("resolve_user_id <>", value, "resolveUserId");
            return (Criteria) this;
        }

        public Criteria andResolveUserIdGreaterThan(Integer value) {
            addCriterion("resolve_user_id >", value, "resolveUserId");
            return (Criteria) this;
        }

        public Criteria andResolveUserIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("resolve_user_id >=", value, "resolveUserId");
            return (Criteria) this;
        }

        public Criteria andResolveUserIdLessThan(Integer value) {
            addCriterion("resolve_user_id <", value, "resolveUserId");
            return (Criteria) this;
        }

        public Criteria andResolveUserIdLessThanOrEqualTo(Integer value) {
            addCriterion("resolve_user_id <=", value, "resolveUserId");
            return (Criteria) this;
        }

        public Criteria andResolveUserIdIn(List<Integer> values) {
            addCriterion("resolve_user_id in", values, "resolveUserId");
            return (Criteria) this;
        }

        public Criteria andResolveUserIdNotIn(List<Integer> values) {
            addCriterion("resolve_user_id not in", values, "resolveUserId");
            return (Criteria) this;
        }

        public Criteria andResolveUserIdBetween(Integer value1, Integer value2) {
            addCriterion("resolve_user_id between", value1, value2, "resolveUserId");
            return (Criteria) this;
        }

        public Criteria andResolveUserIdNotBetween(Integer value1, Integer value2) {
            addCriterion("resolve_user_id not between", value1, value2, "resolveUserId");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagIsNull() {
            addCriterion("delete_flag is null");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagIsNotNull() {
            addCriterion("delete_flag is not null");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagEqualTo(Byte value) {
            addCriterion("delete_flag =", value, "deleteFlag");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagNotEqualTo(Byte value) {
            addCriterion("delete_flag <>", value, "deleteFlag");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagGreaterThan(Byte value) {
            addCriterion("delete_flag >", value, "deleteFlag");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagGreaterThanOrEqualTo(Byte value) {
            addCriterion("delete_flag >=", value, "deleteFlag");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagLessThan(Byte value) {
            addCriterion("delete_flag <", value, "deleteFlag");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagLessThanOrEqualTo(Byte value) {
            addCriterion("delete_flag <=", value, "deleteFlag");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagIn(List<Byte> values) {
            addCriterion("delete_flag in", values, "deleteFlag");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagNotIn(List<Byte> values) {
            addCriterion("delete_flag not in", values, "deleteFlag");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagBetween(Byte value1, Byte value2) {
            addCriterion("delete_flag between", value1, value2, "deleteFlag");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagNotBetween(Byte value1, Byte value2) {
            addCriterion("delete_flag not between", value1, value2, "deleteFlag");
            return (Criteria) this;
        }

        public Criteria andDeleteUserIdIsNull() {
            addCriterion("delete_user_id is null");
            return (Criteria) this;
        }

        public Criteria andDeleteUserIdIsNotNull() {
            addCriterion("delete_user_id is not null");
            return (Criteria) this;
        }

        public Criteria andDeleteUserIdEqualTo(Integer value) {
            addCriterion("delete_user_id =", value, "deleteUserId");
            return (Criteria) this;
        }

        public Criteria andDeleteUserIdNotEqualTo(Integer value) {
            addCriterion("delete_user_id <>", value, "deleteUserId");
            return (Criteria) this;
        }

        public Criteria andDeleteUserIdGreaterThan(Integer value) {
            addCriterion("delete_user_id >", value, "deleteUserId");
            return (Criteria) this;
        }

        public Criteria andDeleteUserIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("delete_user_id >=", value, "deleteUserId");
            return (Criteria) this;
        }

        public Criteria andDeleteUserIdLessThan(Integer value) {
            addCriterion("delete_user_id <", value, "deleteUserId");
            return (Criteria) this;
        }

        public Criteria andDeleteUserIdLessThanOrEqualTo(Integer value) {
            addCriterion("delete_user_id <=", value, "deleteUserId");
            return (Criteria) this;
        }

        public Criteria andDeleteUserIdIn(List<Integer> values) {
            addCriterion("delete_user_id in", values, "deleteUserId");
            return (Criteria) this;
        }

        public Criteria andDeleteUserIdNotIn(List<Integer> values) {
            addCriterion("delete_user_id not in", values, "deleteUserId");
            return (Criteria) this;
        }

        public Criteria andDeleteUserIdBetween(Integer value1, Integer value2) {
            addCriterion("delete_user_id between", value1, value2, "deleteUserId");
            return (Criteria) this;
        }

        public Criteria andDeleteUserIdNotBetween(Integer value1, Integer value2) {
            addCriterion("delete_user_id not between", value1, value2, "deleteUserId");
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