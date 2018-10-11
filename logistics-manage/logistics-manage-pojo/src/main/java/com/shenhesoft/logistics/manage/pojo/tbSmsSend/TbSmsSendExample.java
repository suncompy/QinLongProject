package com.shenhesoft.logistics.manage.pojo.tbSmsSend;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TbSmsSendExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TbSmsSendExample() {
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

        public Criteria andSendTimeIsNull() {
            addCriterion("send_time is null");
            return (Criteria) this;
        }

        public Criteria andSendTimeIsNotNull() {
            addCriterion("send_time is not null");
            return (Criteria) this;
        }

        public Criteria andSendTimeEqualTo(Date value) {
            addCriterion("send_time =", value, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeNotEqualTo(Date value) {
            addCriterion("send_time <>", value, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeGreaterThan(Date value) {
            addCriterion("send_time >", value, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("send_time >=", value, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeLessThan(Date value) {
            addCriterion("send_time <", value, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeLessThanOrEqualTo(Date value) {
            addCriterion("send_time <=", value, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeIn(List<Date> values) {
            addCriterion("send_time in", values, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeNotIn(List<Date> values) {
            addCriterion("send_time not in", values, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeBetween(Date value1, Date value2) {
            addCriterion("send_time between", value1, value2, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeNotBetween(Date value1, Date value2) {
            addCriterion("send_time not between", value1, value2, "sendTime");
            return (Criteria) this;
        }

        public Criteria andRecivePhoneIsNull() {
            addCriterion("recive_phone is null");
            return (Criteria) this;
        }

        public Criteria andRecivePhoneIsNotNull() {
            addCriterion("recive_phone is not null");
            return (Criteria) this;
        }

        public Criteria andRecivePhoneEqualTo(String value) {
            addCriterion("recive_phone =", value, "recivePhone");
            return (Criteria) this;
        }

        public Criteria andRecivePhoneNotEqualTo(String value) {
            addCriterion("recive_phone <>", value, "recivePhone");
            return (Criteria) this;
        }

        public Criteria andRecivePhoneGreaterThan(String value) {
            addCriterion("recive_phone >", value, "recivePhone");
            return (Criteria) this;
        }

        public Criteria andRecivePhoneGreaterThanOrEqualTo(String value) {
            addCriterion("recive_phone >=", value, "recivePhone");
            return (Criteria) this;
        }

        public Criteria andRecivePhoneLessThan(String value) {
            addCriterion("recive_phone <", value, "recivePhone");
            return (Criteria) this;
        }

        public Criteria andRecivePhoneLessThanOrEqualTo(String value) {
            addCriterion("recive_phone <=", value, "recivePhone");
            return (Criteria) this;
        }

        public Criteria andRecivePhoneLike(String value) {
            addCriterion("recive_phone like", value, "recivePhone");
            return (Criteria) this;
        }

        public Criteria andRecivePhoneNotLike(String value) {
            addCriterion("recive_phone not like", value, "recivePhone");
            return (Criteria) this;
        }

        public Criteria andRecivePhoneIn(List<String> values) {
            addCriterion("recive_phone in", values, "recivePhone");
            return (Criteria) this;
        }

        public Criteria andRecivePhoneNotIn(List<String> values) {
            addCriterion("recive_phone not in", values, "recivePhone");
            return (Criteria) this;
        }

        public Criteria andRecivePhoneBetween(String value1, String value2) {
            addCriterion("recive_phone between", value1, value2, "recivePhone");
            return (Criteria) this;
        }

        public Criteria andRecivePhoneNotBetween(String value1, String value2) {
            addCriterion("recive_phone not between", value1, value2, "recivePhone");
            return (Criteria) this;
        }

        public Criteria andMsgIsNull() {
            addCriterion("msg is null");
            return (Criteria) this;
        }

        public Criteria andMsgIsNotNull() {
            addCriterion("msg is not null");
            return (Criteria) this;
        }

        public Criteria andMsgEqualTo(String value) {
            addCriterion("msg =", value, "msg");
            return (Criteria) this;
        }

        public Criteria andMsgNotEqualTo(String value) {
            addCriterion("msg <>", value, "msg");
            return (Criteria) this;
        }

        public Criteria andMsgGreaterThan(String value) {
            addCriterion("msg >", value, "msg");
            return (Criteria) this;
        }

        public Criteria andMsgGreaterThanOrEqualTo(String value) {
            addCriterion("msg >=", value, "msg");
            return (Criteria) this;
        }

        public Criteria andMsgLessThan(String value) {
            addCriterion("msg <", value, "msg");
            return (Criteria) this;
        }

        public Criteria andMsgLessThanOrEqualTo(String value) {
            addCriterion("msg <=", value, "msg");
            return (Criteria) this;
        }

        public Criteria andMsgLike(String value) {
            addCriterion("msg like", value, "msg");
            return (Criteria) this;
        }

        public Criteria andMsgNotLike(String value) {
            addCriterion("msg not like", value, "msg");
            return (Criteria) this;
        }

        public Criteria andMsgIn(List<String> values) {
            addCriterion("msg in", values, "msg");
            return (Criteria) this;
        }

        public Criteria andMsgNotIn(List<String> values) {
            addCriterion("msg not in", values, "msg");
            return (Criteria) this;
        }

        public Criteria andMsgBetween(String value1, String value2) {
            addCriterion("msg between", value1, value2, "msg");
            return (Criteria) this;
        }

        public Criteria andMsgNotBetween(String value1, String value2) {
            addCriterion("msg not between", value1, value2, "msg");
            return (Criteria) this;
        }

        public Criteria andReciveUserIdIsNull() {
            addCriterion("recive_user_id is null");
            return (Criteria) this;
        }

        public Criteria andReciveUserIdIsNotNull() {
            addCriterion("recive_user_id is not null");
            return (Criteria) this;
        }

        public Criteria andReciveUserIdEqualTo(Integer value) {
            addCriterion("recive_user_id =", value, "reciveUserId");
            return (Criteria) this;
        }

        public Criteria andReciveUserIdNotEqualTo(Integer value) {
            addCriterion("recive_user_id <>", value, "reciveUserId");
            return (Criteria) this;
        }

        public Criteria andReciveUserIdGreaterThan(Integer value) {
            addCriterion("recive_user_id >", value, "reciveUserId");
            return (Criteria) this;
        }

        public Criteria andReciveUserIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("recive_user_id >=", value, "reciveUserId");
            return (Criteria) this;
        }

        public Criteria andReciveUserIdLessThan(Integer value) {
            addCriterion("recive_user_id <", value, "reciveUserId");
            return (Criteria) this;
        }

        public Criteria andReciveUserIdLessThanOrEqualTo(Integer value) {
            addCriterion("recive_user_id <=", value, "reciveUserId");
            return (Criteria) this;
        }

        public Criteria andReciveUserIdIn(List<Integer> values) {
            addCriterion("recive_user_id in", values, "reciveUserId");
            return (Criteria) this;
        }

        public Criteria andReciveUserIdNotIn(List<Integer> values) {
            addCriterion("recive_user_id not in", values, "reciveUserId");
            return (Criteria) this;
        }

        public Criteria andReciveUserIdBetween(Integer value1, Integer value2) {
            addCriterion("recive_user_id between", value1, value2, "reciveUserId");
            return (Criteria) this;
        }

        public Criteria andReciveUserIdNotBetween(Integer value1, Integer value2) {
            addCriterion("recive_user_id not between", value1, value2, "reciveUserId");
            return (Criteria) this;
        }

        public Criteria andReciveUserNameIsNull() {
            addCriterion("recive_user_name is null");
            return (Criteria) this;
        }

        public Criteria andReciveUserNameIsNotNull() {
            addCriterion("recive_user_name is not null");
            return (Criteria) this;
        }

        public Criteria andReciveUserNameEqualTo(String value) {
            addCriterion("recive_user_name =", value, "reciveUserName");
            return (Criteria) this;
        }

        public Criteria andReciveUserNameNotEqualTo(String value) {
            addCriterion("recive_user_name <>", value, "reciveUserName");
            return (Criteria) this;
        }

        public Criteria andReciveUserNameGreaterThan(String value) {
            addCriterion("recive_user_name >", value, "reciveUserName");
            return (Criteria) this;
        }

        public Criteria andReciveUserNameGreaterThanOrEqualTo(String value) {
            addCriterion("recive_user_name >=", value, "reciveUserName");
            return (Criteria) this;
        }

        public Criteria andReciveUserNameLessThan(String value) {
            addCriterion("recive_user_name <", value, "reciveUserName");
            return (Criteria) this;
        }

        public Criteria andReciveUserNameLessThanOrEqualTo(String value) {
            addCriterion("recive_user_name <=", value, "reciveUserName");
            return (Criteria) this;
        }

        public Criteria andReciveUserNameLike(String value) {
            addCriterion("recive_user_name like", value, "reciveUserName");
            return (Criteria) this;
        }

        public Criteria andReciveUserNameNotLike(String value) {
            addCriterion("recive_user_name not like", value, "reciveUserName");
            return (Criteria) this;
        }

        public Criteria andReciveUserNameIn(List<String> values) {
            addCriterion("recive_user_name in", values, "reciveUserName");
            return (Criteria) this;
        }

        public Criteria andReciveUserNameNotIn(List<String> values) {
            addCriterion("recive_user_name not in", values, "reciveUserName");
            return (Criteria) this;
        }

        public Criteria andReciveUserNameBetween(String value1, String value2) {
            addCriterion("recive_user_name between", value1, value2, "reciveUserName");
            return (Criteria) this;
        }

        public Criteria andReciveUserNameNotBetween(String value1, String value2) {
            addCriterion("recive_user_name not between", value1, value2, "reciveUserName");
            return (Criteria) this;
        }

        public Criteria andOrderCodeIsNull() {
            addCriterion("order_code is null");
            return (Criteria) this;
        }

        public Criteria andOrderCodeIsNotNull() {
            addCriterion("order_code is not null");
            return (Criteria) this;
        }

        public Criteria andOrderCodeEqualTo(String value) {
            addCriterion("order_code =", value, "orderCode");
            return (Criteria) this;
        }

        public Criteria andOrderCodeNotEqualTo(String value) {
            addCriterion("order_code <>", value, "orderCode");
            return (Criteria) this;
        }

        public Criteria andOrderCodeGreaterThan(String value) {
            addCriterion("order_code >", value, "orderCode");
            return (Criteria) this;
        }

        public Criteria andOrderCodeGreaterThanOrEqualTo(String value) {
            addCriterion("order_code >=", value, "orderCode");
            return (Criteria) this;
        }

        public Criteria andOrderCodeLessThan(String value) {
            addCriterion("order_code <", value, "orderCode");
            return (Criteria) this;
        }

        public Criteria andOrderCodeLessThanOrEqualTo(String value) {
            addCriterion("order_code <=", value, "orderCode");
            return (Criteria) this;
        }

        public Criteria andOrderCodeLike(String value) {
            addCriterion("order_code like", value, "orderCode");
            return (Criteria) this;
        }

        public Criteria andOrderCodeNotLike(String value) {
            addCriterion("order_code not like", value, "orderCode");
            return (Criteria) this;
        }

        public Criteria andOrderCodeIn(List<String> values) {
            addCriterion("order_code in", values, "orderCode");
            return (Criteria) this;
        }

        public Criteria andOrderCodeNotIn(List<String> values) {
            addCriterion("order_code not in", values, "orderCode");
            return (Criteria) this;
        }

        public Criteria andOrderCodeBetween(String value1, String value2) {
            addCriterion("order_code between", value1, value2, "orderCode");
            return (Criteria) this;
        }

        public Criteria andOrderCodeNotBetween(String value1, String value2) {
            addCriterion("order_code not between", value1, value2, "orderCode");
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

        public Criteria andMsgStatusIsNull() {
            addCriterion("msg_status is null");
            return (Criteria) this;
        }

        public Criteria andMsgStatusIsNotNull() {
            addCriterion("msg_status is not null");
            return (Criteria) this;
        }

        public Criteria andMsgStatusEqualTo(Integer value) {
            addCriterion("msg_status =", value, "msgStatus");
            return (Criteria) this;
        }

        public Criteria andMsgStatusNotEqualTo(Integer value) {
            addCriterion("msg_status <>", value, "msgStatus");
            return (Criteria) this;
        }

        public Criteria andMsgStatusGreaterThan(Integer value) {
            addCriterion("msg_status >", value, "msgStatus");
            return (Criteria) this;
        }

        public Criteria andMsgStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("msg_status >=", value, "msgStatus");
            return (Criteria) this;
        }

        public Criteria andMsgStatusLessThan(Integer value) {
            addCriterion("msg_status <", value, "msgStatus");
            return (Criteria) this;
        }

        public Criteria andMsgStatusLessThanOrEqualTo(Integer value) {
            addCriterion("msg_status <=", value, "msgStatus");
            return (Criteria) this;
        }

        public Criteria andMsgStatusIn(List<Integer> values) {
            addCriterion("msg_status in", values, "msgStatus");
            return (Criteria) this;
        }

        public Criteria andMsgStatusNotIn(List<Integer> values) {
            addCriterion("msg_status not in", values, "msgStatus");
            return (Criteria) this;
        }

        public Criteria andMsgStatusBetween(Integer value1, Integer value2) {
            addCriterion("msg_status between", value1, value2, "msgStatus");
            return (Criteria) this;
        }

        public Criteria andMsgStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("msg_status not between", value1, value2, "msgStatus");
            return (Criteria) this;
        }

        public Criteria andOptUserNameIsNull() {
            addCriterion("opt_user_name is null");
            return (Criteria) this;
        }

        public Criteria andOptUserNameIsNotNull() {
            addCriterion("opt_user_name is not null");
            return (Criteria) this;
        }

        public Criteria andOptUserNameEqualTo(String value) {
            addCriterion("opt_user_name =", value, "optUserName");
            return (Criteria) this;
        }

        public Criteria andOptUserNameNotEqualTo(String value) {
            addCriterion("opt_user_name <>", value, "optUserName");
            return (Criteria) this;
        }

        public Criteria andOptUserNameGreaterThan(String value) {
            addCriterion("opt_user_name >", value, "optUserName");
            return (Criteria) this;
        }

        public Criteria andOptUserNameGreaterThanOrEqualTo(String value) {
            addCriterion("opt_user_name >=", value, "optUserName");
            return (Criteria) this;
        }

        public Criteria andOptUserNameLessThan(String value) {
            addCriterion("opt_user_name <", value, "optUserName");
            return (Criteria) this;
        }

        public Criteria andOptUserNameLessThanOrEqualTo(String value) {
            addCriterion("opt_user_name <=", value, "optUserName");
            return (Criteria) this;
        }

        public Criteria andOptUserNameLike(String value) {
            addCriterion("opt_user_name like", value, "optUserName");
            return (Criteria) this;
        }

        public Criteria andOptUserNameNotLike(String value) {
            addCriterion("opt_user_name not like", value, "optUserName");
            return (Criteria) this;
        }

        public Criteria andOptUserNameIn(List<String> values) {
            addCriterion("opt_user_name in", values, "optUserName");
            return (Criteria) this;
        }

        public Criteria andOptUserNameNotIn(List<String> values) {
            addCriterion("opt_user_name not in", values, "optUserName");
            return (Criteria) this;
        }

        public Criteria andOptUserNameBetween(String value1, String value2) {
            addCriterion("opt_user_name between", value1, value2, "optUserName");
            return (Criteria) this;
        }

        public Criteria andOptUserNameNotBetween(String value1, String value2) {
            addCriterion("opt_user_name not between", value1, value2, "optUserName");
            return (Criteria) this;
        }

        public Criteria andOptUserIdIsNull() {
            addCriterion("opt_user_id is null");
            return (Criteria) this;
        }

        public Criteria andOptUserIdIsNotNull() {
            addCriterion("opt_user_id is not null");
            return (Criteria) this;
        }

        public Criteria andOptUserIdEqualTo(Integer value) {
            addCriterion("opt_user_id =", value, "optUserId");
            return (Criteria) this;
        }

        public Criteria andOptUserIdNotEqualTo(Integer value) {
            addCriterion("opt_user_id <>", value, "optUserId");
            return (Criteria) this;
        }

        public Criteria andOptUserIdGreaterThan(Integer value) {
            addCriterion("opt_user_id >", value, "optUserId");
            return (Criteria) this;
        }

        public Criteria andOptUserIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("opt_user_id >=", value, "optUserId");
            return (Criteria) this;
        }

        public Criteria andOptUserIdLessThan(Integer value) {
            addCriterion("opt_user_id <", value, "optUserId");
            return (Criteria) this;
        }

        public Criteria andOptUserIdLessThanOrEqualTo(Integer value) {
            addCriterion("opt_user_id <=", value, "optUserId");
            return (Criteria) this;
        }

        public Criteria andOptUserIdIn(List<Integer> values) {
            addCriterion("opt_user_id in", values, "optUserId");
            return (Criteria) this;
        }

        public Criteria andOptUserIdNotIn(List<Integer> values) {
            addCriterion("opt_user_id not in", values, "optUserId");
            return (Criteria) this;
        }

        public Criteria andOptUserIdBetween(Integer value1, Integer value2) {
            addCriterion("opt_user_id between", value1, value2, "optUserId");
            return (Criteria) this;
        }

        public Criteria andOptUserIdNotBetween(Integer value1, Integer value2) {
            addCriterion("opt_user_id not between", value1, value2, "optUserId");
            return (Criteria) this;
        }

        public Criteria andMsgSignNameIsNull() {
            addCriterion("msg_sign_name is null");
            return (Criteria) this;
        }

        public Criteria andMsgSignNameIsNotNull() {
            addCriterion("msg_sign_name is not null");
            return (Criteria) this;
        }

        public Criteria andMsgSignNameEqualTo(String value) {
            addCriterion("msg_sign_name =", value, "msgSignName");
            return (Criteria) this;
        }

        public Criteria andMsgSignNameNotEqualTo(String value) {
            addCriterion("msg_sign_name <>", value, "msgSignName");
            return (Criteria) this;
        }

        public Criteria andMsgSignNameGreaterThan(String value) {
            addCriterion("msg_sign_name >", value, "msgSignName");
            return (Criteria) this;
        }

        public Criteria andMsgSignNameGreaterThanOrEqualTo(String value) {
            addCriterion("msg_sign_name >=", value, "msgSignName");
            return (Criteria) this;
        }

        public Criteria andMsgSignNameLessThan(String value) {
            addCriterion("msg_sign_name <", value, "msgSignName");
            return (Criteria) this;
        }

        public Criteria andMsgSignNameLessThanOrEqualTo(String value) {
            addCriterion("msg_sign_name <=", value, "msgSignName");
            return (Criteria) this;
        }

        public Criteria andMsgSignNameLike(String value) {
            addCriterion("msg_sign_name like", value, "msgSignName");
            return (Criteria) this;
        }

        public Criteria andMsgSignNameNotLike(String value) {
            addCriterion("msg_sign_name not like", value, "msgSignName");
            return (Criteria) this;
        }

        public Criteria andMsgSignNameIn(List<String> values) {
            addCriterion("msg_sign_name in", values, "msgSignName");
            return (Criteria) this;
        }

        public Criteria andMsgSignNameNotIn(List<String> values) {
            addCriterion("msg_sign_name not in", values, "msgSignName");
            return (Criteria) this;
        }

        public Criteria andMsgSignNameBetween(String value1, String value2) {
            addCriterion("msg_sign_name between", value1, value2, "msgSignName");
            return (Criteria) this;
        }

        public Criteria andMsgSignNameNotBetween(String value1, String value2) {
            addCriterion("msg_sign_name not between", value1, value2, "msgSignName");
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