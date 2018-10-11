package com.shenhesoft.logistics.business.pojo.TbOrder;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.shenhesoft.logistics.manage.pojo.project.TbProjectExample.Criteria;

public class TbOrderExample {
	protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TbOrderExample() {
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

        public Criteria andProjectCodeIsNull() {
            addCriterion("project_code is null");
            return (Criteria) this;
        }

        public Criteria andProjectCodeIsNotNull() {
            addCriterion("project_code is not null");
            return (Criteria) this;
        }

        public Criteria andProjectCodeEqualTo(String value) {
            addCriterion("project_code =", value, "projectCode");
            return (Criteria) this;
        }

        public Criteria andProjectCodeNotEqualTo(String value) {
            addCriterion("project_code <>", value, "projectCode");
            return (Criteria) this;
        }

        public Criteria andProjectCodeGreaterThan(String value) {
            addCriterion("project_code >", value, "projectCode");
            return (Criteria) this;
        }

        public Criteria andProjectCodeGreaterThanOrEqualTo(String value) {
            addCriterion("project_code >=", value, "projectCode");
            return (Criteria) this;
        }

        public Criteria andProjectCodeLessThan(String value) {
            addCriterion("project_code <", value, "projectCode");
            return (Criteria) this;
        }

        public Criteria andProjectCodeLessThanOrEqualTo(String value) {
            addCriterion("project_code <=", value, "projectCode");
            return (Criteria) this;
        }

        public Criteria andProjectCodeLike(String value) {
            addCriterion("project_code like", value, "projectCode");
            return (Criteria) this;
        }

        public Criteria andProjectCodeNotLike(String value) {
            addCriterion("project_code not like", value, "projectCode");
            return (Criteria) this;
        }

        public Criteria andProjectCodeIn(List<String> values) {
            addCriterion("project_code in", values, "projectCode");
            return (Criteria) this;
        }

        public Criteria andProjectCodeNotIn(List<String> values) {
            addCriterion("project_code not in", values, "projectCode");
            return (Criteria) this;
        }

        public Criteria andProjectCodeBetween(String value1, String value2) {
            addCriterion("project_code between", value1, value2, "projectCode");
            return (Criteria) this;
        }

        public Criteria andProjectCodeNotBetween(String value1, String value2) {
            addCriterion("project_code not between", value1, value2, "projectCode");
            return (Criteria) this;
        }

        public Criteria andProjectTypeIsNull() {
            addCriterion("project_type is null");
            return (Criteria) this;
        }

        public Criteria andProjectTypeIsNotNull() {
            addCriterion("project_type is not null");
            return (Criteria) this;
        }

        public Criteria andProjectTypeEqualTo(Byte value) {
            addCriterion("project_type =", value, "projectType");
            return (Criteria) this;
        }

        public Criteria andProjectTypeNotEqualTo(Byte value) {
            addCriterion("project_type <>", value, "projectType");
            return (Criteria) this;
        }

        public Criteria andProjectTypeGreaterThan(Byte value) {
            addCriterion("project_type >", value, "projectType");
            return (Criteria) this;
        }

        public Criteria andProjectTypeGreaterThanOrEqualTo(Byte value) {
            addCriterion("project_type >=", value, "projectType");
            return (Criteria) this;
        }

        public Criteria andProjectTypeLessThan(Byte value) {
            addCriterion("project_type <", value, "projectType");
            return (Criteria) this;
        }

        public Criteria andProjectTypeLessThanOrEqualTo(Byte value) {
            addCriterion("project_type <=", value, "projectType");
            return (Criteria) this;
        }

        public Criteria andProjectTypeIn(List<Byte> values) {
            addCriterion("project_type in", values, "projectType");
            return (Criteria) this;
        }

        public Criteria andProjectTypeNotIn(List<Byte> values) {
            addCriterion("project_type not in", values, "projectType");
            return (Criteria) this;
        }

        public Criteria andProjectTypeBetween(Byte value1, Byte value2) {
            addCriterion("project_type between", value1, value2, "projectType");
            return (Criteria) this;
        }

        public Criteria andProjectTypeNotBetween(Byte value1, Byte value2) {
            addCriterion("project_type not between", value1, value2, "projectType");
            return (Criteria) this;
        }

        public Criteria andTransportTypeIsNull() {
            addCriterion("transport_type is null");
            return (Criteria) this;
        }

        public Criteria andTransportTypeIsNotNull() {
            addCriterion("transport_type is not null");
            return (Criteria) this;
        }

        public Criteria andTransportTypeEqualTo(Byte value) {
            addCriterion("transport_type =", value, "transportType");
            return (Criteria) this;
        }

        public Criteria andTransportTypeNotEqualTo(Byte value) {
            addCriterion("transport_type <>", value, "transportType");
            return (Criteria) this;
        }

        public Criteria andTransportTypeGreaterThan(Byte value) {
            addCriterion("transport_type >", value, "transportType");
            return (Criteria) this;
        }

        public Criteria andTransportTypeGreaterThanOrEqualTo(Byte value) {
            addCriterion("transport_type >=", value, "transportType");
            return (Criteria) this;
        }

        public Criteria andTransportTypeLessThan(Byte value) {
            addCriterion("transport_type <", value, "transportType");
            return (Criteria) this;
        }

        public Criteria andTransportTypeLessThanOrEqualTo(Byte value) {
            addCriterion("transport_type <=", value, "transportType");
            return (Criteria) this;
        }

        public Criteria andTransportTypeIn(List<Byte> values) {
            addCriterion("transport_type in", values, "transportType");
            return (Criteria) this;
        }

        public Criteria andTransportTypeNotIn(List<Byte> values) {
            addCriterion("transport_type not in", values, "transportType");
            return (Criteria) this;
        }

        public Criteria andTransportTypeBetween(Byte value1, Byte value2) {
            addCriterion("transport_type between", value1, value2, "transportType");
            return (Criteria) this;
        }

        public Criteria andTransportTypeNotBetween(Byte value1, Byte value2) {
            addCriterion("transport_type not between", value1, value2, "transportType");
            return (Criteria) this;
        }

        public Criteria andDriverIdIsNull() {
            addCriterion("driver_id is null");
            return (Criteria) this;
        }

        public Criteria andDriverIdIsNotNull() {
            addCriterion("driver_id is not null");
            return (Criteria) this;
        }

        public Criteria andDriverIdEqualTo(Integer value) {
            addCriterion("driver_id =", value, "driverId");
            return (Criteria) this;
        }

        public Criteria andDriverIdNotEqualTo(Integer value) {
            addCriterion("driver_id <>", value, "driverId");
            return (Criteria) this;
        }

        public Criteria andDriverIdGreaterThan(Integer value) {
            addCriterion("driver_id >", value, "driverId");
            return (Criteria) this;
        }

        public Criteria andDriverIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("driver_id >=", value, "driverId");
            return (Criteria) this;
        }

        public Criteria andDriverIdLessThan(Integer value) {
            addCriterion("driver_id <", value, "driverId");
            return (Criteria) this;
        }

        public Criteria andDriverIdLessThanOrEqualTo(Integer value) {
            addCriterion("driver_id <=", value, "driverId");
            return (Criteria) this;
        }

        public Criteria andDriverIdIn(List<Integer> values) {
            addCriterion("driver_id in", values, "driverId");
            return (Criteria) this;
        }

        public Criteria andDriverIdNotIn(List<Integer> values) {
            addCriterion("driver_id not in", values, "driverId");
            return (Criteria) this;
        }

        public Criteria andDriverIdBetween(Integer value1, Integer value2) {
            addCriterion("driver_id between", value1, value2, "driverId");
            return (Criteria) this;
        }

        public Criteria andDriverIdNotBetween(Integer value1, Integer value2) {
            addCriterion("driver_id not between", value1, value2, "driverId");
            return (Criteria) this;
        }

        public Criteria andCreatorIdIsNull() {
            addCriterion("creator_id is null");
            return (Criteria) this;
        }

        public Criteria andCreatorIdIsNotNull() {
            addCriterion("creator_id is not null");
            return (Criteria) this;
        }

        public Criteria andCreatorIdEqualTo(Integer value) {
            addCriterion("creator_id =", value, "creatorId");
            return (Criteria) this;
        }

        public Criteria andCreatorIdNotEqualTo(Integer value) {
            addCriterion("creator_id <>", value, "creatorId");
            return (Criteria) this;
        }

        public Criteria andCreatorIdGreaterThan(Integer value) {
            addCriterion("creator_id >", value, "creatorId");
            return (Criteria) this;
        }

        public Criteria andCreatorIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("creator_id >=", value, "creatorId");
            return (Criteria) this;
        }

        public Criteria andCreatorIdLessThan(Integer value) {
            addCriterion("creator_id <", value, "creatorId");
            return (Criteria) this;
        }

        public Criteria andCreatorIdLessThanOrEqualTo(Integer value) {
            addCriterion("creator_id <=", value, "creatorId");
            return (Criteria) this;
        }

        public Criteria andCreatorIdIn(List<Integer> values) {
            addCriterion("creator_id in", values, "creatorId");
            return (Criteria) this;
        }

        public Criteria andCreatorIdNotIn(List<Integer> values) {
            addCriterion("creator_id not in", values, "creatorId");
            return (Criteria) this;
        }

        public Criteria andCreatorIdBetween(Integer value1, Integer value2) {
            addCriterion("creator_id between", value1, value2, "creatorId");
            return (Criteria) this;
        }

        public Criteria andCreatorIdNotBetween(Integer value1, Integer value2) {
            addCriterion("creator_id not between", value1, value2, "creatorId");
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

        public Criteria andCreateDateGreaterThanOrEqualTo(String value) {
            addCriterion("date_format(create_date, '%Y-%m-%d') >=", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateLessThan(Date value) {
            addCriterion("create_date <", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateLessThanOrEqualTo(String value) {
            addCriterion("date_format(create_date, '%Y-%m-%d') <=", value, "createDate");
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

        public Criteria andUpdateDateIsNull() {
            addCriterion("update_date is null");
            return (Criteria) this;
        }

        public Criteria andUpdateDateIsNotNull() {
            addCriterion("update_date is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateDateEqualTo(Date value) {
            addCriterion("update_date =", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateNotEqualTo(Date value) {
            addCriterion("update_date <>", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateGreaterThan(Date value) {
            addCriterion("update_date >", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateGreaterThanOrEqualTo(Date value) {
            addCriterion("update_date >=", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateLessThan(Date value) {
            addCriterion("update_date <", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateLessThanOrEqualTo(Date value) {
            addCriterion("update_date <=", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateIn(List<Date> values) {
            addCriterion("update_date in", values, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateNotIn(List<Date> values) {
            addCriterion("update_date not in", values, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateBetween(Date value1, Date value2) {
            addCriterion("update_date between", value1, value2, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateNotBetween(Date value1, Date value2) {
            addCriterion("update_date not between", value1, value2, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUserDispatchIdIsNull() {
            addCriterion("user_dispatch_id is null");
            return (Criteria) this;
        }

        public Criteria andUserDispatchIdIsNotNull() {
            addCriterion("user_dispatch_id is not null");
            return (Criteria) this;
        }

        public Criteria andUserDispatchIdEqualTo(Integer value) {
            addCriterion("user_dispatch_id =", value, "userDispatchId");
            return (Criteria) this;
        }

        public Criteria andUserDispatchIdNotEqualTo(Integer value) {
            addCriterion("user_dispatch_id <>", value, "userDispatchId");
            return (Criteria) this;
        }

        public Criteria andUserDispatchIdGreaterThan(Integer value) {
            addCriterion("user_dispatch_id >", value, "userDispatchId");
            return (Criteria) this;
        }

        public Criteria andUserDispatchIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("user_dispatch_id >=", value, "userDispatchId");
            return (Criteria) this;
        }

        public Criteria andUserDispatchIdLessThan(Integer value) {
            addCriterion("user_dispatch_id <", value, "userDispatchId");
            return (Criteria) this;
        }

        public Criteria andUserDispatchIdLessThanOrEqualTo(Integer value) {
            addCriterion("user_dispatch_id <=", value, "userDispatchId");
            return (Criteria) this;
        }

        public Criteria andUserDispatchIdIn(List<Integer> values) {
            addCriterion("user_dispatch_id in", values, "userDispatchId");
            return (Criteria) this;
        }

        public Criteria andUserDispatchIdNotIn(List<Integer> values) {
            addCriterion("user_dispatch_id not in", values, "userDispatchId");
            return (Criteria) this;
        }

        public Criteria andUserDispatchIdBetween(Integer value1, Integer value2) {
            addCriterion("user_dispatch_id between", value1, value2, "userDispatchId");
            return (Criteria) this;
        }

        public Criteria andUserDispatchIdNotBetween(Integer value1, Integer value2) {
            addCriterion("user_dispatch_id not between", value1, value2, "userDispatchId");
            return (Criteria) this;
        }

        public Criteria andUserDispatchNameIsNull() {
            addCriterion("user_dispatch_name is null");
            return (Criteria) this;
        }

        public Criteria andUserDispatchNameIsNotNull() {
            addCriterion("user_dispatch_name is not null");
            return (Criteria) this;
        }

        public Criteria andUserDispatchNameEqualTo(String value) {
            addCriterion("user_dispatch_name =", value, "userDispatchName");
            return (Criteria) this;
        }

        public Criteria andUserDispatchNameNotEqualTo(String value) {
            addCriterion("user_dispatch_name <>", value, "userDispatchName");
            return (Criteria) this;
        }

        public Criteria andUserDispatchNameGreaterThan(String value) {
            addCriterion("user_dispatch_name >", value, "userDispatchName");
            return (Criteria) this;
        }

        public Criteria andUserDispatchNameGreaterThanOrEqualTo(String value) {
            addCriterion("user_dispatch_name >=", value, "userDispatchName");
            return (Criteria) this;
        }

        public Criteria andUserDispatchNameLessThan(String value) {
            addCriterion("user_dispatch_name <", value, "userDispatchName");
            return (Criteria) this;
        }

        public Criteria andUserDispatchNameLessThanOrEqualTo(String value) {
            addCriterion("user_dispatch_name <=", value, "userDispatchName");
            return (Criteria) this;
        }

        public Criteria andUserDispatchNameLike(String value) {
            addCriterion("user_dispatch_name like", value, "userDispatchName");
            return (Criteria) this;
        }

        public Criteria andUserDispatchNameNotLike(String value) {
            addCriterion("user_dispatch_name not like", value, "userDispatchName");
            return (Criteria) this;
        }

        public Criteria andUserDispatchNameIn(List<String> values) {
            addCriterion("user_dispatch_name in", values, "userDispatchName");
            return (Criteria) this;
        }

        public Criteria andUserDispatchNameNotIn(List<String> values) {
            addCriterion("user_dispatch_name not in", values, "userDispatchName");
            return (Criteria) this;
        }

        public Criteria andUserDispatchNameBetween(String value1, String value2) {
            addCriterion("user_dispatch_name between", value1, value2, "userDispatchName");
            return (Criteria) this;
        }

        public Criteria andUserDispatchNameNotBetween(String value1, String value2) {
            addCriterion("user_dispatch_name not between", value1, value2, "userDispatchName");
            return (Criteria) this;
        }

        public Criteria andProjectDistributionIdIsNull() {
            addCriterion("project_distribution_id is null");
            return (Criteria) this;
        }

        public Criteria andProjectDistributionIdIsNotNull() {
            addCriterion("project_distribution_id is not null");
            return (Criteria) this;
        }

        public Criteria andProjectDistributionIdEqualTo(Integer value) {
            addCriterion("project_distribution_id =", value, "projectDistributionId");
            return (Criteria) this;
        }

        public Criteria andProjectDistributionIdNotEqualTo(Integer value) {
            addCriterion("project_distribution_id <>", value, "projectDistributionId");
            return (Criteria) this;
        }

        public Criteria andProjectDistributionIdGreaterThan(Integer value) {
            addCriterion("project_distribution_id >", value, "projectDistributionId");
            return (Criteria) this;
        }

        public Criteria andProjectDistributionIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("project_distribution_id >=", value, "projectDistributionId");
            return (Criteria) this;
        }

        public Criteria andProjectDistributionIdLessThan(Integer value) {
            addCriterion("project_distribution_id <", value, "projectDistributionId");
            return (Criteria) this;
        }

        public Criteria andProjectDistributionIdLessThanOrEqualTo(Integer value) {
            addCriterion("project_distribution_id <=", value, "projectDistributionId");
            return (Criteria) this;
        }

        public Criteria andProjectDistributionIdIn(List<Integer> values) {
            addCriterion("project_distribution_id in", values, "projectDistributionId");
            return (Criteria) this;
        }

        public Criteria andProjectDistributionIdNotIn(List<Integer> values) {
            addCriterion("project_distribution_id not in", values, "projectDistributionId");
            return (Criteria) this;
        }

        public Criteria andProjectDistributionIdBetween(Integer value1, Integer value2) {
            addCriterion("project_distribution_id between", value1, value2, "projectDistributionId");
            return (Criteria) this;
        }

        public Criteria andProjectDistributionIdNotBetween(Integer value1, Integer value2) {
            addCriterion("project_distribution_id not between", value1, value2, "projectDistributionId");
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

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(Byte value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Byte value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Byte value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Byte value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Byte value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Byte value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Byte> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Byte> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Byte value1, Byte value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Byte value1, Byte value2) {
            addCriterion("status not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andExceptionStatusIsNull() {
            addCriterion("exception_status is null");
            return (Criteria) this;
        }

        public Criteria andExceptionStatusIsNotNull() {
            addCriterion("exception_status is not null");
            return (Criteria) this;
        }

        public Criteria andExceptionStatusEqualTo(Byte value) {
            addCriterion("exception_status =", value, "exceptionStatus");
            return (Criteria) this;
        }

        public Criteria andExceptionStatusNotEqualTo(Byte value) {
            addCriterion("exception_status <>", value, "exceptionStatus");
            return (Criteria) this;
        }

        public Criteria andExceptionStatusGreaterThan(Byte value) {
            addCriterion("exception_status >", value, "exceptionStatus");
            return (Criteria) this;
        }

        public Criteria andExceptionStatusGreaterThanOrEqualTo(Byte value) {
            addCriterion("exception_status >=", value, "exceptionStatus");
            return (Criteria) this;
        }

        public Criteria andExceptionStatusLessThan(Byte value) {
            addCriterion("exception_status <", value, "exceptionStatus");
            return (Criteria) this;
        }

        public Criteria andExceptionStatusLessThanOrEqualTo(Byte value) {
            addCriterion("exception_status <=", value, "exceptionStatus");
            return (Criteria) this;
        }

        public Criteria andExceptionStatusIn(List<Byte> values) {
            addCriterion("exception_status in", values, "exceptionStatus");
            return (Criteria) this;
        }

        public Criteria andExceptionStatusNotIn(List<Byte> values) {
            addCriterion("exception_status not in", values, "exceptionStatus");
            return (Criteria) this;
        }

        public Criteria andExceptionStatusBetween(Byte value1, Byte value2) {
            addCriterion("exception_status between", value1, value2, "exceptionStatus");
            return (Criteria) this;
        }

        public Criteria andExceptionStatusNotBetween(Byte value1, Byte value2) {
            addCriterion("exception_status not between", value1, value2, "exceptionStatus");
            return (Criteria) this;
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

        public Criteria andExceptionReoportIdIsNull() {
            addCriterion("exception_reoport_id is null");
            return (Criteria) this;
        }

        public Criteria andExceptionReoportIdIsNotNull() {
            addCriterion("exception_reoport_id is not null");
            return (Criteria) this;
        }

        public Criteria andExceptionReoportIdEqualTo(Integer value) {
            addCriterion("exception_reoport_id =", value, "exceptionReoportId");
            return (Criteria) this;
        }

        public Criteria andExceptionReoportIdNotEqualTo(Integer value) {
            addCriterion("exception_reoport_id <>", value, "exceptionReoportId");
            return (Criteria) this;
        }

        public Criteria andExceptionReoportIdGreaterThan(Integer value) {
            addCriterion("exception_reoport_id >", value, "exceptionReoportId");
            return (Criteria) this;
        }

        public Criteria andExceptionReoportIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("exception_reoport_id >=", value, "exceptionReoportId");
            return (Criteria) this;
        }

        public Criteria andExceptionReoportIdLessThan(Integer value) {
            addCriterion("exception_reoport_id <", value, "exceptionReoportId");
            return (Criteria) this;
        }

        public Criteria andExceptionReoportIdLessThanOrEqualTo(Integer value) {
            addCriterion("exception_reoport_id <=", value, "exceptionReoportId");
            return (Criteria) this;
        }

        public Criteria andExceptionReoportIdIn(List<Integer> values) {
            addCriterion("exception_reoport_id in", values, "exceptionReoportId");
            return (Criteria) this;
        }

        public Criteria andExceptionReoportIdNotIn(List<Integer> values) {
            addCriterion("exception_reoport_id not in", values, "exceptionReoportId");
            return (Criteria) this;
        }

        public Criteria andExceptionReoportIdBetween(Integer value1, Integer value2) {
            addCriterion("exception_reoport_id between", value1, value2, "exceptionReoportId");
            return (Criteria) this;
        }

        public Criteria andExceptionReoportIdNotBetween(Integer value1, Integer value2) {
            addCriterion("exception_reoport_id not between", value1, value2, "exceptionReoportId");
            return (Criteria) this;
        }

        public Criteria andExceptionReoportNameIsNull() {
            addCriterion("exception_reoport_name is null");
            return (Criteria) this;
        }

        public Criteria andExceptionReoportNameIsNotNull() {
            addCriterion("exception_reoport_name is not null");
            return (Criteria) this;
        }

        public Criteria andExceptionReoportNameEqualTo(String value) {
            addCriterion("exception_reoport_name =", value, "exceptionReoportName");
            return (Criteria) this;
        }

        public Criteria andExceptionReoportNameNotEqualTo(String value) {
            addCriterion("exception_reoport_name <>", value, "exceptionReoportName");
            return (Criteria) this;
        }

        public Criteria andExceptionReoportNameGreaterThan(String value) {
            addCriterion("exception_reoport_name >", value, "exceptionReoportName");
            return (Criteria) this;
        }

        public Criteria andExceptionReoportNameGreaterThanOrEqualTo(String value) {
            addCriterion("exception_reoport_name >=", value, "exceptionReoportName");
            return (Criteria) this;
        }

        public Criteria andExceptionReoportNameLessThan(String value) {
            addCriterion("exception_reoport_name <", value, "exceptionReoportName");
            return (Criteria) this;
        }

        public Criteria andExceptionReoportNameLessThanOrEqualTo(String value) {
            addCriterion("exception_reoport_name <=", value, "exceptionReoportName");
            return (Criteria) this;
        }

        public Criteria andExceptionReoportNameLike(String value) {
            addCriterion("exception_reoport_name like", value, "exceptionReoportName");
            return (Criteria) this;
        }

        public Criteria andExceptionReoportNameNotLike(String value) {
            addCriterion("exception_reoport_name not like", value, "exceptionReoportName");
            return (Criteria) this;
        }

        public Criteria andExceptionReoportNameIn(List<String> values) {
            addCriterion("exception_reoport_name in", values, "exceptionReoportName");
            return (Criteria) this;
        }

        public Criteria andExceptionReoportNameNotIn(List<String> values) {
            addCriterion("exception_reoport_name not in", values, "exceptionReoportName");
            return (Criteria) this;
        }

        public Criteria andExceptionReoportNameBetween(String value1, String value2) {
            addCriterion("exception_reoport_name between", value1, value2, "exceptionReoportName");
            return (Criteria) this;
        }

        public Criteria andExceptionReoportNameNotBetween(String value1, String value2) {
            addCriterion("exception_reoport_name not between", value1, value2, "exceptionReoportName");
            return (Criteria) this;
        }

        public Criteria andExceptionTimeIsNull() {
            addCriterion("exception_time is null");
            return (Criteria) this;
        }

        public Criteria andExceptionTimeIsNotNull() {
            addCriterion("exception_time is not null");
            return (Criteria) this;
        }

        public Criteria andExceptionTimeEqualTo(Date value) {
            addCriterion("exception_time =", value, "exceptionTime");
            return (Criteria) this;
        }

        public Criteria andExceptionTimeNotEqualTo(Date value) {
            addCriterion("exception_time <>", value, "exceptionTime");
            return (Criteria) this;
        }

        public Criteria andExceptionTimeGreaterThan(Date value) {
            addCriterion("exception_time >", value, "exceptionTime");
            return (Criteria) this;
        }

        public Criteria andExceptionTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("exception_time >=", value, "exceptionTime");
            return (Criteria) this;
        }

        public Criteria andExceptionTimeLessThan(Date value) {
            addCriterion("exception_time <", value, "exceptionTime");
            return (Criteria) this;
        }

        public Criteria andExceptionTimeLessThanOrEqualTo(Date value) {
            addCriterion("exception_time <=", value, "exceptionTime");
            return (Criteria) this;
        }

        public Criteria andExceptionTimeIn(List<Date> values) {
            addCriterion("exception_time in", values, "exceptionTime");
            return (Criteria) this;
        }

        public Criteria andExceptionTimeNotIn(List<Date> values) {
            addCriterion("exception_time not in", values, "exceptionTime");
            return (Criteria) this;
        }

        public Criteria andExceptionTimeBetween(Date value1, Date value2) {
            addCriterion("exception_time between", value1, value2, "exceptionTime");
            return (Criteria) this;
        }

        public Criteria andExceptionTimeNotBetween(Date value1, Date value2) {
            addCriterion("exception_time not between", value1, value2, "exceptionTime");
            return (Criteria) this;
        }

        public Criteria andExceptionReoportReasonIsNull() {
            addCriterion("exception_reoport_reason is null");
            return (Criteria) this;
        }

        public Criteria andExceptionReoportReasonIsNotNull() {
            addCriterion("exception_reoport_reason is not null");
            return (Criteria) this;
        }

        public Criteria andExceptionReoportReasonEqualTo(String value) {
            addCriterion("exception_reoport_reason =", value, "exceptionReoportReason");
            return (Criteria) this;
        }

        public Criteria andExceptionReoportReasonNotEqualTo(String value) {
            addCriterion("exception_reoport_reason <>", value, "exceptionReoportReason");
            return (Criteria) this;
        }

        public Criteria andExceptionReoportReasonGreaterThan(String value) {
            addCriterion("exception_reoport_reason >", value, "exceptionReoportReason");
            return (Criteria) this;
        }

        public Criteria andExceptionReoportReasonGreaterThanOrEqualTo(String value) {
            addCriterion("exception_reoport_reason >=", value, "exceptionReoportReason");
            return (Criteria) this;
        }

        public Criteria andExceptionReoportReasonLessThan(String value) {
            addCriterion("exception_reoport_reason <", value, "exceptionReoportReason");
            return (Criteria) this;
        }

        public Criteria andExceptionReoportReasonLessThanOrEqualTo(String value) {
            addCriterion("exception_reoport_reason <=", value, "exceptionReoportReason");
            return (Criteria) this;
        }

        public Criteria andExceptionReoportReasonLike(String value) {
            addCriterion("exception_reoport_reason like", value, "exceptionReoportReason");
            return (Criteria) this;
        }

        public Criteria andExceptionReoportReasonNotLike(String value) {
            addCriterion("exception_reoport_reason not like", value, "exceptionReoportReason");
            return (Criteria) this;
        }

        public Criteria andExceptionReoportReasonIn(List<String> values) {
            addCriterion("exception_reoport_reason in", values, "exceptionReoportReason");
            return (Criteria) this;
        }

        public Criteria andExceptionReoportReasonNotIn(List<String> values) {
            addCriterion("exception_reoport_reason not in", values, "exceptionReoportReason");
            return (Criteria) this;
        }

        public Criteria andExceptionReoportReasonBetween(String value1, String value2) {
            addCriterion("exception_reoport_reason between", value1, value2, "exceptionReoportReason");
            return (Criteria) this;
        }

        public Criteria andExceptionReoportReasonNotBetween(String value1, String value2) {
            addCriterion("exception_reoport_reason not between", value1, value2, "exceptionReoportReason");
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

        public Criteria andStepSelectCodeIsNull() {
            addCriterion("step_select_code is null");
            return (Criteria) this;
        }

        public Criteria andStepSelectCodeIsNotNull() {
            addCriterion("step_select_code is not null");
            return (Criteria) this;
        }

        public Criteria andStepSelectCodeEqualTo(Byte value) {
            addCriterion("step_select_code =", value, "stepSelectCode");
            return (Criteria) this;
        }

        public Criteria andStepSelectCodeNotEqualTo(Byte value) {
            addCriterion("step_select_code <>", value, "stepSelectCode");
            return (Criteria) this;
        }

        public Criteria andStepSelectCodeGreaterThan(Byte value) {
            addCriterion("step_select_code >", value, "stepSelectCode");
            return (Criteria) this;
        }

        public Criteria andStepSelectCodeGreaterThanOrEqualTo(Byte value) {
            addCriterion("step_select_code >=", value, "stepSelectCode");
            return (Criteria) this;
        }

        public Criteria andStepSelectCodeLessThan(Byte value) {
            addCriterion("step_select_code <", value, "stepSelectCode");
            return (Criteria) this;
        }

        public Criteria andStepSelectCodeLessThanOrEqualTo(Byte value) {
            addCriterion("step_select_code <=", value, "stepSelectCode");
            return (Criteria) this;
        }

        public Criteria andStepSelectCodeIn(List<Byte> values) {
            addCriterion("step_select_code in", values, "stepSelectCode");
            return (Criteria) this;
        }

        public Criteria andStepSelectCodeNotIn(List<Byte> values) {
            addCriterion("step_select_code not in", values, "stepSelectCode");
            return (Criteria) this;
        }

        public Criteria andStepSelectCodeBetween(Byte value1, Byte value2) {
            addCriterion("step_select_code between", value1, value2, "stepSelectCode");
            return (Criteria) this;
        }

        public Criteria andStepSelectCodeNotBetween(Byte value1, Byte value2) {
            addCriterion("step_select_code not between", value1, value2, "stepSelectCode");
            return (Criteria) this;
        }

        public Criteria andStepSelectIsNull() {
            addCriterion("step_select is null");
            return (Criteria) this;
        }

        public Criteria andStepSelectIsNotNull() {
            addCriterion("step_select is not null");
            return (Criteria) this;
        }

        public Criteria andStepSelectEqualTo(String value) {
            addCriterion("step_select =", value, "stepSelect");
            return (Criteria) this;
        }

        public Criteria andStepSelectNotEqualTo(String value) {
            addCriterion("step_select <>", value, "stepSelect");
            return (Criteria) this;
        }

        public Criteria andStepSelectGreaterThan(String value) {
            addCriterion("step_select >", value, "stepSelect");
            return (Criteria) this;
        }

        public Criteria andStepSelectGreaterThanOrEqualTo(String value) {
            addCriterion("step_select >=", value, "stepSelect");
            return (Criteria) this;
        }

        public Criteria andStepSelectLessThan(String value) {
            addCriterion("step_select <", value, "stepSelect");
            return (Criteria) this;
        }

        public Criteria andStepSelectLessThanOrEqualTo(String value) {
            addCriterion("step_select <=", value, "stepSelect");
            return (Criteria) this;
        }

        public Criteria andStepSelectLike(String value) {
            addCriterion("step_select like", value, "stepSelect");
            return (Criteria) this;
        }

        public Criteria andStepSelectNotLike(String value) {
            addCriterion("step_select not like", value, "stepSelect");
            return (Criteria) this;
        }

        public Criteria andStepSelectIn(List<String> values) {
            addCriterion("step_select in", values, "stepSelect");
            return (Criteria) this;
        }

        public Criteria andStepSelectNotIn(List<String> values) {
            addCriterion("step_select not in", values, "stepSelect");
            return (Criteria) this;
        }

        public Criteria andStepSelectBetween(String value1, String value2) {
            addCriterion("step_select between", value1, value2, "stepSelect");
            return (Criteria) this;
        }

        public Criteria andStepSelectNotBetween(String value1, String value2) {
            addCriterion("step_select not between", value1, value2, "stepSelect");
            return (Criteria) this;
        }
        //分支机构id
        public Criteria andBranchIdIsNull() {
            addCriterion("branch_id is null");
            return (Criteria) this;
        }

        public Criteria andBranchIdIsNotNull() {
            addCriterion("branch_id is not null");
            return (Criteria) this;
        }

        public Criteria andBranchIdEqualTo(Integer value) {
            addCriterion("branch_id =", value, "branchId");
            return (Criteria) this;
        }

        public Criteria andBranchIdNotEqualTo(Integer value) {
            addCriterion("branch_id <>", value, "branchId");
            return (Criteria) this;
        }

        public Criteria andBranchIdGreaterThan(Integer value) {
            addCriterion("branch_id >", value, "branchId");
            return (Criteria) this;
        }

        public Criteria andBranchIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("branch_id >=", value, "branchId");
            return (Criteria) this;
        }

        public Criteria andBranchIdLessThan(Integer value) {
            addCriterion("branch_id <", value, "branchId");
            return (Criteria) this;
        }

        public Criteria andBranchIdLessThanOrEqualTo(Integer value) {
            addCriterion("branch_id <=", value, "branchId");
            return (Criteria) this;
        }

        public Criteria andBranchIdIn(List<Integer> values) {
            addCriterion("branch_id in", values, "branchId");
            return (Criteria) this;
        }

        public Criteria andBranchIdNotIn(List<Integer> values) {
            addCriterion("branch_id not in", values, "branchId");
            return (Criteria) this;
        }

        public Criteria andBranchIdBetween(Integer value1, Integer value2) {
            addCriterion("branch_id between", value1, value2, "branchId");
            return (Criteria) this;
        }

        public Criteria andBranchIdNotBetween(Integer value1, Integer value2) {
            addCriterion("branch_id not between", value1, value2, "branchId");
            return (Criteria) this;
        }
        
        public Criteria andBranchGroupNameIsNull() {
            addCriterion("branch_group_name is null");
            return (Criteria) this;
        }

        public Criteria andBranchGroupNameIsNotNull() {
            addCriterion("branch_group_name is not null");
            return (Criteria) this;
        }

        public Criteria andBranchGroupNameEqualTo(String value) {
            addCriterion("branch_group_name =", value, "branchGroupName");
            return (Criteria) this;
        }

        public Criteria andBranchGroupNameNotEqualTo(String value) {
            addCriterion("branch_group_name <>", value, "branchGroupName");
            return (Criteria) this;
        }

        public Criteria andBranchGroupNameGreaterThan(String value) {
            addCriterion("branch_group_name >", value, "branchGroupName");
            return (Criteria) this;
        }

        public Criteria andBranchGroupNameGreaterThanOrEqualTo(String value) {
            addCriterion("branch_group_name >=", value, "branchGroupName");
            return (Criteria) this;
        }

        public Criteria andBranchGroupNameLessThan(String value) {
            addCriterion("branch_group_name <", value, "branchGroupName");
            return (Criteria) this;
        }

        public Criteria andBranchGroupNameLessThanOrEqualTo(String value) {
            addCriterion("branch_group_name <=", value, "branchGroupName");
            return (Criteria) this;
        }

        public Criteria andBranchGroupNameLike(String value) {
            addCriterion("branch_group_name like", value, "branchGroupName");
            return (Criteria) this;
        }

        public Criteria andBranchGroupNameNotLike(String value) {
            addCriterion("branch_group_name not like", value, "branchGroupName");
            return (Criteria) this;
        }

        public Criteria andBranchGroupNameIn(List<String> values) {
            addCriterion("branch_group_name in", values, "branchGroupName");
            return (Criteria) this;
        }

        public Criteria andBranchGroupNameNotIn(List<String> values) {
            addCriterion("branch_group_name not in", values, "branchGroupName");
            return (Criteria) this;
        }

        public Criteria andBranchGroupNameBetween(String value1, String value2) {
            addCriterion("branch_group_name between", value1, value2, "branchGroupName");
            return (Criteria) this;
        }

        public Criteria andBranchGroupNameNotBetween(String value1, String value2) {
            addCriterion("branch_group_name not between", value1, value2, "branchGroupName");
            return (Criteria) this;
        }

        public Criteria andSendCompanyIdIsNull() {
            addCriterion("send_company_id is null");
            return (Criteria) this;
        }

        public Criteria andSendCompanyIdIsNotNull() {
            addCriterion("send_company_id is not null");
            return (Criteria) this;
        }

        public Criteria andSendCompanyIdEqualTo(Integer value) {
            addCriterion("send_company_id =", value, "sendCompanyId");
            return (Criteria) this;
        }

        public Criteria andSendCompanyIdNotEqualTo(Integer value) {
            addCriterion("send_company_id <>", value, "sendCompanyId");
            return (Criteria) this;
        }

        public Criteria andSendCompanyIdGreaterThan(Integer value) {
            addCriterion("send_company_id >", value, "sendCompanyId");
            return (Criteria) this;
        }

        public Criteria andSendCompanyIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("send_company_id >=", value, "sendCompanyId");
            return (Criteria) this;
        }

        public Criteria andSendCompanyIdLessThan(Integer value) {
            addCriterion("send_company_id <", value, "sendCompanyId");
            return (Criteria) this;
        }

        public Criteria andSendCompanyIdLessThanOrEqualTo(Integer value) {
            addCriterion("send_company_id <=", value, "sendCompanyId");
            return (Criteria) this;
        }

        public Criteria andSendCompanyIdIn(List<Integer> values) {
            addCriterion("send_company_id in", values, "sendCompanyId");
            return (Criteria) this;
        }

        public Criteria andSendCompanyIdNotIn(List<Integer> values) {
            addCriterion("send_company_id not in", values, "sendCompanyId");
            return (Criteria) this;
        }

        public Criteria andSendCompanyIdBetween(Integer value1, Integer value2) {
            addCriterion("send_company_id between", value1, value2, "sendCompanyId");
            return (Criteria) this;
        }

        public Criteria andSendCompanyIdNotBetween(Integer value1, Integer value2) {
            addCriterion("send_company_id not between", value1, value2, "sendCompanyId");
            return (Criteria) this;
        }

        public Criteria andSendCompanyIsNull() {
            addCriterion("send_company is null");
            return (Criteria) this;
        }

        public Criteria andSendCompanyIsNotNull() {
            addCriterion("send_company is not null");
            return (Criteria) this;
        }

        public Criteria andSendCompanyEqualTo(String value) {
            addCriterion("send_company =", value, "sendCompany");
            return (Criteria) this;
        }

        public Criteria andSendCompanyNotEqualTo(String value) {
            addCriterion("send_company <>", value, "sendCompany");
            return (Criteria) this;
        }

        public Criteria andSendCompanyGreaterThan(String value) {
            addCriterion("send_company >", value, "sendCompany");
            return (Criteria) this;
        }

        public Criteria andSendCompanyGreaterThanOrEqualTo(String value) {
            addCriterion("send_company >=", value, "sendCompany");
            return (Criteria) this;
        }

        public Criteria andSendCompanyLessThan(String value) {
            addCriterion("send_company <", value, "sendCompany");
            return (Criteria) this;
        }

        public Criteria andSendCompanyLessThanOrEqualTo(String value) {
            addCriterion("send_company <=", value, "sendCompany");
            return (Criteria) this;
        }

        public Criteria andSendCompanyLike(String value) {
            addCriterion("send_company like", value, "sendCompany");
            return (Criteria) this;
        }

        public Criteria andSendCompanyNotLike(String value) {
            addCriterion("send_company not like", value, "sendCompany");
            return (Criteria) this;
        }

        public Criteria andSendCompanyIn(List<String> values) {
            addCriterion("send_company in", values, "sendCompany");
            return (Criteria) this;
        }

        public Criteria andSendCompanyNotIn(List<String> values) {
            addCriterion("send_company not in", values, "sendCompany");
            return (Criteria) this;
        }

        public Criteria andSendCompanyBetween(String value1, String value2) {
            addCriterion("send_company between", value1, value2, "sendCompany");
            return (Criteria) this;
        }

        public Criteria andSendCompanyNotBetween(String value1, String value2) {
            addCriterion("send_company not between", value1, value2, "sendCompany");
            return (Criteria) this;
        }

        public Criteria andPickupPlaceIsNull() {
            addCriterion("pickup_place is null");
            return (Criteria) this;
        }

        public Criteria andPickupPlaceIsNotNull() {
            addCriterion("pickup_place is not null");
            return (Criteria) this;
        }

        public Criteria andPickupPlaceEqualTo(String value) {
            addCriterion("pickup_place =", value, "pickupPlace");
            return (Criteria) this;
        }

        public Criteria andPickupPlaceNotEqualTo(String value) {
            addCriterion("pickup_place <>", value, "pickupPlace");
            return (Criteria) this;
        }

        public Criteria andPickupPlaceGreaterThan(String value) {
            addCriterion("pickup_place >", value, "pickupPlace");
            return (Criteria) this;
        }

        public Criteria andPickupPlaceGreaterThanOrEqualTo(String value) {
            addCriterion("pickup_place >=", value, "pickupPlace");
            return (Criteria) this;
        }

        public Criteria andPickupPlaceLessThan(String value) {
            addCriterion("pickup_place <", value, "pickupPlace");
            return (Criteria) this;
        }

        public Criteria andPickupPlaceLessThanOrEqualTo(String value) {
            addCriterion("pickup_place <=", value, "pickupPlace");
            return (Criteria) this;
        }

        public Criteria andPickupPlaceLike(String value) {
            addCriterion("pickup_place like", value, "pickupPlace");
            return (Criteria) this;
        }

        public Criteria andPickupPlaceNotLike(String value) {
            addCriterion("pickup_place not like", value, "pickupPlace");
            return (Criteria) this;
        }

        public Criteria andPickupPlaceIn(List<String> values) {
            addCriterion("pickup_place in", values, "pickupPlace");
            return (Criteria) this;
        }

        public Criteria andPickupPlaceNotIn(List<String> values) {
            addCriterion("pickup_place not in", values, "pickupPlace");
            return (Criteria) this;
        }

        public Criteria andPickupPlaceBetween(String value1, String value2) {
            addCriterion("pickup_place between", value1, value2, "pickupPlace");
            return (Criteria) this;
        }

        public Criteria andPickupPlaceNotBetween(String value1, String value2) {
            addCriterion("pickup_place not between", value1, value2, "pickupPlace");
            return (Criteria) this;
        }

        public Criteria andPickupPlaceAddressIsNull() {
            addCriterion("pickup_place_address is null");
            return (Criteria) this;
        }

        public Criteria andPickupPlaceAddressIsNotNull() {
            addCriterion("pickup_place_address is not null");
            return (Criteria) this;
        }

        public Criteria andPickupPlaceAddressEqualTo(String value) {
            addCriterion("pickup_place_address =", value, "pickupPlaceAddress");
            return (Criteria) this;
        }

        public Criteria andPickupPlaceAddressNotEqualTo(String value) {
            addCriterion("pickup_place_address <>", value, "pickupPlaceAddress");
            return (Criteria) this;
        }

        public Criteria andPickupPlaceAddressGreaterThan(String value) {
            addCriterion("pickup_place_address >", value, "pickupPlaceAddress");
            return (Criteria) this;
        }

        public Criteria andPickupPlaceAddressGreaterThanOrEqualTo(String value) {
            addCriterion("pickup_place_address >=", value, "pickupPlaceAddress");
            return (Criteria) this;
        }

        public Criteria andPickupPlaceAddressLessThan(String value) {
            addCriterion("pickup_place_address <", value, "pickupPlaceAddress");
            return (Criteria) this;
        }

        public Criteria andPickupPlaceAddressLessThanOrEqualTo(String value) {
            addCriterion("pickup_place_address <=", value, "pickupPlaceAddress");
            return (Criteria) this;
        }

        public Criteria andPickupPlaceAddressLike(String value) {
            addCriterion("pickup_place_address like", value, "pickupPlaceAddress");
            return (Criteria) this;
        }

        public Criteria andPickupPlaceAddressNotLike(String value) {
            addCriterion("pickup_place_address not like", value, "pickupPlaceAddress");
            return (Criteria) this;
        }

        public Criteria andPickupPlaceAddressIn(List<String> values) {
            addCriterion("pickup_place_address in", values, "pickupPlaceAddress");
            return (Criteria) this;
        }

        public Criteria andPickupPlaceAddressNotIn(List<String> values) {
            addCriterion("pickup_place_address not in", values, "pickupPlaceAddress");
            return (Criteria) this;
        }

        public Criteria andPickupPlaceAddressBetween(String value1, String value2) {
            addCriterion("pickup_place_address between", value1, value2, "pickupPlaceAddress");
            return (Criteria) this;
        }

        public Criteria andPickupPlaceAddressNotBetween(String value1, String value2) {
            addCriterion("pickup_place_address not between", value1, value2, "pickupPlaceAddress");
            return (Criteria) this;
        }

        public Criteria andReceiptCompanyIdIsNull() {
            addCriterion("receipt_company_id is null");
            return (Criteria) this;
        }

        public Criteria andReceiptCompanyIdIsNotNull() {
            addCriterion("receipt_company_id is not null");
            return (Criteria) this;
        }

        public Criteria andReceiptCompanyIdEqualTo(Integer value) {
            addCriterion("receipt_company_id =", value, "receiptCompanyId");
            return (Criteria) this;
        }

        public Criteria andReceiptCompanyIdNotEqualTo(Integer value) {
            addCriterion("receipt_company_id <>", value, "receiptCompanyId");
            return (Criteria) this;
        }

        public Criteria andReceiptCompanyIdGreaterThan(Integer value) {
            addCriterion("receipt_company_id >", value, "receiptCompanyId");
            return (Criteria) this;
        }

        public Criteria andReceiptCompanyIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("receipt_company_id >=", value, "receiptCompanyId");
            return (Criteria) this;
        }

        public Criteria andReceiptCompanyIdLessThan(Integer value) {
            addCriterion("receipt_company_id <", value, "receiptCompanyId");
            return (Criteria) this;
        }

        public Criteria andReceiptCompanyIdLessThanOrEqualTo(Integer value) {
            addCriterion("receipt_company_id <=", value, "receiptCompanyId");
            return (Criteria) this;
        }

        public Criteria andReceiptCompanyIdIn(List<Integer> values) {
            addCriterion("receipt_company_id in", values, "receiptCompanyId");
            return (Criteria) this;
        }

        public Criteria andReceiptCompanyIdNotIn(List<Integer> values) {
            addCriterion("receipt_company_id not in", values, "receiptCompanyId");
            return (Criteria) this;
        }

        public Criteria andReceiptCompanyIdBetween(Integer value1, Integer value2) {
            addCriterion("receipt_company_id between", value1, value2, "receiptCompanyId");
            return (Criteria) this;
        }

        public Criteria andReceiptCompanyIdNotBetween(Integer value1, Integer value2) {
            addCriterion("receipt_company_id not between", value1, value2, "receiptCompanyId");
            return (Criteria) this;
        }

        public Criteria andReceiptCompanyIsNull() {
            addCriterion("receipt_company is null");
            return (Criteria) this;
        }

        public Criteria andReceiptCompanyIsNotNull() {
            addCriterion("receipt_company is not null");
            return (Criteria) this;
        }

        public Criteria andReceiptCompanyEqualTo(String value) {
            addCriterion("receipt_company =", value, "receiptCompany");
            return (Criteria) this;
        }

        public Criteria andReceiptCompanyNotEqualTo(String value) {
            addCriterion("receipt_company <>", value, "receiptCompany");
            return (Criteria) this;
        }

        public Criteria andReceiptCompanyGreaterThan(String value) {
            addCriterion("receipt_company >", value, "receiptCompany");
            return (Criteria) this;
        }

        public Criteria andReceiptCompanyGreaterThanOrEqualTo(String value) {
            addCriterion("receipt_company >=", value, "receiptCompany");
            return (Criteria) this;
        }

        public Criteria andReceiptCompanyLessThan(String value) {
            addCriterion("receipt_company <", value, "receiptCompany");
            return (Criteria) this;
        }

        public Criteria andReceiptCompanyLessThanOrEqualTo(String value) {
            addCriterion("receipt_company <=", value, "receiptCompany");
            return (Criteria) this;
        }

        public Criteria andReceiptCompanyLike(String value) {
            addCriterion("receipt_company like", value, "receiptCompany");
            return (Criteria) this;
        }

        public Criteria andReceiptCompanyNotLike(String value) {
            addCriterion("receipt_company not like", value, "receiptCompany");
            return (Criteria) this;
        }

        public Criteria andReceiptCompanyIn(List<String> values) {
            addCriterion("receipt_company in", values, "receiptCompany");
            return (Criteria) this;
        }

        public Criteria andReceiptCompanyNotIn(List<String> values) {
            addCriterion("receipt_company not in", values, "receiptCompany");
            return (Criteria) this;
        }

        public Criteria andReceiptCompanyBetween(String value1, String value2) {
            addCriterion("receipt_company between", value1, value2, "receiptCompany");
            return (Criteria) this;
        }

        public Criteria andReceiptCompanyNotBetween(String value1, String value2) {
            addCriterion("receipt_company not between", value1, value2, "receiptCompany");
            return (Criteria) this;
        }

        public Criteria andArrivePlaceIsNull() {
            addCriterion("arrive_place is null");
            return (Criteria) this;
        }

        public Criteria andArrivePlaceIsNotNull() {
            addCriterion("arrive_place is not null");
            return (Criteria) this;
        }

        public Criteria andArrivePlaceEqualTo(String value) {
            addCriterion("arrive_place =", value, "arrivePlace");
            return (Criteria) this;
        }

        public Criteria andArrivePlaceNotEqualTo(String value) {
            addCriterion("arrive_place <>", value, "arrivePlace");
            return (Criteria) this;
        }

        public Criteria andArrivePlaceGreaterThan(String value) {
            addCriterion("arrive_place >", value, "arrivePlace");
            return (Criteria) this;
        }

        public Criteria andArrivePlaceGreaterThanOrEqualTo(String value) {
            addCriterion("arrive_place >=", value, "arrivePlace");
            return (Criteria) this;
        }

        public Criteria andArrivePlaceLessThan(String value) {
            addCriterion("arrive_place <", value, "arrivePlace");
            return (Criteria) this;
        }

        public Criteria andArrivePlaceLessThanOrEqualTo(String value) {
            addCriterion("arrive_place <=", value, "arrivePlace");
            return (Criteria) this;
        }

        public Criteria andArrivePlaceLike(String value) {
            addCriterion("arrive_place like", value, "arrivePlace");
            return (Criteria) this;
        }

        public Criteria andArrivePlaceNotLike(String value) {
            addCriterion("arrive_place not like", value, "arrivePlace");
            return (Criteria) this;
        }

        public Criteria andArrivePlaceIn(List<String> values) {
            addCriterion("arrive_place in", values, "arrivePlace");
            return (Criteria) this;
        }

        public Criteria andArrivePlaceNotIn(List<String> values) {
            addCriterion("arrive_place not in", values, "arrivePlace");
            return (Criteria) this;
        }

        public Criteria andArrivePlaceBetween(String value1, String value2) {
            addCriterion("arrive_place between", value1, value2, "arrivePlace");
            return (Criteria) this;
        }

        public Criteria andArrivePlaceNotBetween(String value1, String value2) {
            addCriterion("arrive_place not between", value1, value2, "arrivePlace");
            return (Criteria) this;
        }

        public Criteria andArriveAddressIsNull() {
            addCriterion("arrive_address is null");
            return (Criteria) this;
        }

        public Criteria andArriveAddressIsNotNull() {
            addCriterion("arrive_address is not null");
            return (Criteria) this;
        }

        public Criteria andArriveAddressEqualTo(String value) {
            addCriterion("arrive_address =", value, "arriveAddress");
            return (Criteria) this;
        }

        public Criteria andArriveAddressNotEqualTo(String value) {
            addCriterion("arrive_address <>", value, "arriveAddress");
            return (Criteria) this;
        }

        public Criteria andArriveAddressGreaterThan(String value) {
            addCriterion("arrive_address >", value, "arriveAddress");
            return (Criteria) this;
        }

        public Criteria andArriveAddressGreaterThanOrEqualTo(String value) {
            addCriterion("arrive_address >=", value, "arriveAddress");
            return (Criteria) this;
        }

        public Criteria andArriveAddressLessThan(String value) {
            addCriterion("arrive_address <", value, "arriveAddress");
            return (Criteria) this;
        }

        public Criteria andArriveAddressLessThanOrEqualTo(String value) {
            addCriterion("arrive_address <=", value, "arriveAddress");
            return (Criteria) this;
        }

        public Criteria andArriveAddressLike(String value) {
            addCriterion("arrive_address like", value, "arriveAddress");
            return (Criteria) this;
        }

        public Criteria andArriveAddressNotLike(String value) {
            addCriterion("arrive_address not like", value, "arriveAddress");
            return (Criteria) this;
        }

        public Criteria andArriveAddressIn(List<String> values) {
            addCriterion("arrive_address in", values, "arriveAddress");
            return (Criteria) this;
        }

        public Criteria andArriveAddressNotIn(List<String> values) {
            addCriterion("arrive_address not in", values, "arriveAddress");
            return (Criteria) this;
        }

        public Criteria andArriveAddressBetween(String value1, String value2) {
            addCriterion("arrive_address between", value1, value2, "arriveAddress");
            return (Criteria) this;
        }

        public Criteria andArriveAddressNotBetween(String value1, String value2) {
            addCriterion("arrive_address not between", value1, value2, "arriveAddress");
            return (Criteria) this;
        }

        public Criteria andArriveFreightYradIsNull() {
            addCriterion("arrive_freight_yrad is null");
            return (Criteria) this;
        }

        public Criteria andArriveFreightYradIsNotNull() {
            addCriterion("arrive_freight_yrad is not null");
            return (Criteria) this;
        }

        public Criteria andArriveFreightYradEqualTo(String value) {
            addCriterion("arrive_freight_yrad =", value, "arriveFreightYrad");
            return (Criteria) this;
        }

        public Criteria andArriveFreightYradNotEqualTo(String value) {
            addCriterion("arrive_freight_yrad <>", value, "arriveFreightYrad");
            return (Criteria) this;
        }

        public Criteria andArriveFreightYradGreaterThan(String value) {
            addCriterion("arrive_freight_yrad >", value, "arriveFreightYrad");
            return (Criteria) this;
        }

        public Criteria andArriveFreightYradGreaterThanOrEqualTo(String value) {
            addCriterion("arrive_freight_yrad >=", value, "arriveFreightYrad");
            return (Criteria) this;
        }

        public Criteria andArriveFreightYradLessThan(String value) {
            addCriterion("arrive_freight_yrad <", value, "arriveFreightYrad");
            return (Criteria) this;
        }

        public Criteria andArriveFreightYradLessThanOrEqualTo(String value) {
            addCriterion("arrive_freight_yrad <=", value, "arriveFreightYrad");
            return (Criteria) this;
        }

        public Criteria andArriveFreightYradLike(String value) {
            addCriterion("arrive_freight_yrad like", value, "arriveFreightYrad");
            return (Criteria) this;
        }

        public Criteria andArriveFreightYradNotLike(String value) {
            addCriterion("arrive_freight_yrad not like", value, "arriveFreightYrad");
            return (Criteria) this;
        }

        public Criteria andArriveFreightYradIn(List<String> values) {
            addCriterion("arrive_freight_yrad in", values, "arriveFreightYrad");
            return (Criteria) this;
        }

        public Criteria andArriveFreightYradNotIn(List<String> values) {
            addCriterion("arrive_freight_yrad not in", values, "arriveFreightYrad");
            return (Criteria) this;
        }

        public Criteria andArriveFreightYradBetween(String value1, String value2) {
            addCriterion("arrive_freight_yrad between", value1, value2, "arriveFreightYrad");
            return (Criteria) this;
        }

        public Criteria andArriveFreightYradNotBetween(String value1, String value2) {
            addCriterion("arrive_freight_yrad not between", value1, value2, "arriveFreightYrad");
            return (Criteria) this;
        }

        public Criteria andArriveFreightSiteIsNull() {
            addCriterion("arrive_freight_site is null");
            return (Criteria) this;
        }

        public Criteria andArriveFreightSiteIsNotNull() {
            addCriterion("arrive_freight_site is not null");
            return (Criteria) this;
        }

        public Criteria andArriveFreightSiteEqualTo(String value) {
            addCriterion("arrive_freight_site =", value, "arriveFreightSite");
            return (Criteria) this;
        }

        public Criteria andArriveFreightSiteNotEqualTo(String value) {
            addCriterion("arrive_freight_site <>", value, "arriveFreightSite");
            return (Criteria) this;
        }

        public Criteria andArriveFreightSiteGreaterThan(String value) {
            addCriterion("arrive_freight_site >", value, "arriveFreightSite");
            return (Criteria) this;
        }

        public Criteria andArriveFreightSiteGreaterThanOrEqualTo(String value) {
            addCriterion("arrive_freight_site >=", value, "arriveFreightSite");
            return (Criteria) this;
        }

        public Criteria andArriveFreightSiteLessThan(String value) {
            addCriterion("arrive_freight_site <", value, "arriveFreightSite");
            return (Criteria) this;
        }

        public Criteria andArriveFreightSiteLessThanOrEqualTo(String value) {
            addCriterion("arrive_freight_site <=", value, "arriveFreightSite");
            return (Criteria) this;
        }

        public Criteria andArriveFreightSiteLike(String value) {
            addCriterion("arrive_freight_site like", value, "arriveFreightSite");
            return (Criteria) this;
        }

        public Criteria andArriveFreightSiteNotLike(String value) {
            addCriterion("arrive_freight_site not like", value, "arriveFreightSite");
            return (Criteria) this;
        }

        public Criteria andArriveFreightSiteIn(List<String> values) {
            addCriterion("arrive_freight_site in", values, "arriveFreightSite");
            return (Criteria) this;
        }

        public Criteria andArriveFreightSiteNotIn(List<String> values) {
            addCriterion("arrive_freight_site not in", values, "arriveFreightSite");
            return (Criteria) this;
        }

        public Criteria andArriveFreightSiteBetween(String value1, String value2) {
            addCriterion("arrive_freight_site between", value1, value2, "arriveFreightSite");
            return (Criteria) this;
        }

        public Criteria andArriveFreightSiteNotBetween(String value1, String value2) {
            addCriterion("arrive_freight_site not between", value1, value2, "arriveFreightSite");
            return (Criteria) this;
        }

        public Criteria andCarrierVehicleIdIsNull() {
            addCriterion("carrier_vehicle_id is null");
            return (Criteria) this;
        }

        public Criteria andCarrierVehicleIdIsNotNull() {
            addCriterion("carrier_vehicle_id is not null");
            return (Criteria) this;
        }

        public Criteria andCarrierVehicleIdEqualTo(String value) {
            addCriterion("carrier_vehicle_id =", value, "carrierVehicleId");
            return (Criteria) this;
        }

        public Criteria andCarrierVehicleIdNotEqualTo(String value) {
            addCriterion("carrier_vehicle_id <>", value, "carrierVehicleId");
            return (Criteria) this;
        }

        public Criteria andCarrierVehicleIdGreaterThan(String value) {
            addCriterion("carrier_vehicle_id >", value, "carrierVehicleId");
            return (Criteria) this;
        }

        public Criteria andCarrierVehicleIdGreaterThanOrEqualTo(String value) {
            addCriterion("carrier_vehicle_id >=", value, "carrierVehicleId");
            return (Criteria) this;
        }

        public Criteria andCarrierVehicleIdLessThan(String value) {
            addCriterion("carrier_vehicle_id <", value, "carrierVehicleId");
            return (Criteria) this;
        }

        public Criteria andCarrierVehicleIdLessThanOrEqualTo(String value) {
            addCriterion("carrier_vehicle_id <=", value, "carrierVehicleId");
            return (Criteria) this;
        }

        public Criteria andCarrierVehicleIdLike(String value) {
            addCriterion("carrier_vehicle_id like", value, "carrierVehicleId");
            return (Criteria) this;
        }

        public Criteria andCarrierVehicleIdNotLike(String value) {
            addCriterion("carrier_vehicle_id not like", value, "carrierVehicleId");
            return (Criteria) this;
        }

        public Criteria andCarrierVehicleIdIn(List<String> values) {
            addCriterion("carrier_vehicle_id in", values, "carrierVehicleId");
            return (Criteria) this;
        }

        public Criteria andCarrierVehicleIdNotIn(List<String> values) {
            addCriterion("carrier_vehicle_id not in", values, "carrierVehicleId");
            return (Criteria) this;
        }

        public Criteria andCarrierVehicleIdBetween(String value1, String value2) {
            addCriterion("carrier_vehicle_id between", value1, value2, "carrierVehicleId");
            return (Criteria) this;
        }

        public Criteria andCarrierVehicleIdNotBetween(String value1, String value2) {
            addCriterion("carrier_vehicle_id not between", value1, value2, "carrierVehicleId");
            return (Criteria) this;
        }

        public Criteria andCarrierVehicleNameIsNull() {
            addCriterion("carrier_vehicle_name is null");
            return (Criteria) this;
        }

        public Criteria andCarrierVehicleNameIsNotNull() {
            addCriterion("carrier_vehicle_name is not null");
            return (Criteria) this;
        }

        public Criteria andCarrierVehicleNameEqualTo(String value) {
            addCriterion("carrier_vehicle_name =", value, "carrierVehicleName");
            return (Criteria) this;
        }

        public Criteria andCarrierVehicleNameNotEqualTo(String value) {
            addCriterion("carrier_vehicle_name <>", value, "carrierVehicleName");
            return (Criteria) this;
        }

        public Criteria andCarrierVehicleNameGreaterThan(String value) {
            addCriterion("carrier_vehicle_name >", value, "carrierVehicleName");
            return (Criteria) this;
        }

        public Criteria andCarrierVehicleNameGreaterThanOrEqualTo(String value) {
            addCriterion("carrier_vehicle_name >=", value, "carrierVehicleName");
            return (Criteria) this;
        }

        public Criteria andCarrierVehicleNameLessThan(String value) {
            addCriterion("carrier_vehicle_name <", value, "carrierVehicleName");
            return (Criteria) this;
        }

        public Criteria andCarrierVehicleNameLessThanOrEqualTo(String value) {
            addCriterion("carrier_vehicle_name <=", value, "carrierVehicleName");
            return (Criteria) this;
        }

        public Criteria andCarrierVehicleNameLike(String value) {
            addCriterion("carrier_vehicle_name like", value, "carrierVehicleName");
            return (Criteria) this;
        }

        public Criteria andCarrierVehicleNameNotLike(String value) {
            addCriterion("carrier_vehicle_name not like", value, "carrierVehicleName");
            return (Criteria) this;
        }

        public Criteria andCarrierVehicleNameIn(List<String> values) {
            addCriterion("carrier_vehicle_name in", values, "carrierVehicleName");
            return (Criteria) this;
        }

        public Criteria andCarrierVehicleNameNotIn(List<String> values) {
            addCriterion("carrier_vehicle_name not in", values, "carrierVehicleName");
            return (Criteria) this;
        }

        public Criteria andCarrierVehicleNameBetween(String value1, String value2) {
            addCriterion("carrier_vehicle_name between", value1, value2, "carrierVehicleName");
            return (Criteria) this;
        }

        public Criteria andCarrierVehicleNameNotBetween(String value1, String value2) {
            addCriterion("carrier_vehicle_name not between", value1, value2, "carrierVehicleName");
            return (Criteria) this;
        }

        public Criteria andCarPlateNumberIsNull() {
            addCriterion("car_plate_number is null");
            return (Criteria) this;
        }

        public Criteria andCarPlateNumberIsNotNull() {
            addCriterion("car_plate_number is not null");
            return (Criteria) this;
        }

        public Criteria andCarPlateNumberEqualTo(String value) {
            addCriterion("car_plate_number =", value, "carPlateNumber");
            return (Criteria) this;
        }

        public Criteria andCarPlateNumberNotEqualTo(String value) {
            addCriterion("car_plate_number <>", value, "carPlateNumber");
            return (Criteria) this;
        }

        public Criteria andCarPlateNumberGreaterThan(String value) {
            addCriterion("car_plate_number >", value, "carPlateNumber");
            return (Criteria) this;
        }

        public Criteria andCarPlateNumberGreaterThanOrEqualTo(String value) {
            addCriterion("car_plate_number >=", value, "carPlateNumber");
            return (Criteria) this;
        }

        public Criteria andCarPlateNumberLessThan(String value) {
            addCriterion("car_plate_number <", value, "carPlateNumber");
            return (Criteria) this;
        }

        public Criteria andCarPlateNumberLessThanOrEqualTo(String value) {
            addCriterion("car_plate_number <=", value, "carPlateNumber");
            return (Criteria) this;
        }

        public Criteria andCarPlateNumberLike(String value) {
            addCriterion("car_plate_number like", value, "carPlateNumber");
            return (Criteria) this;
        }

        public Criteria andCarPlateNumberNotLike(String value) {
            addCriterion("car_plate_number not like", value, "carPlateNumber");
            return (Criteria) this;
        }

        public Criteria andCarPlateNumberIn(List<String> values) {
            addCriterion("car_plate_number in", values, "carPlateNumber");
            return (Criteria) this;
        }

        public Criteria andCarPlateNumberNotIn(List<String> values) {
            addCriterion("car_plate_number not in", values, "carPlateNumber");
            return (Criteria) this;
        }

        public Criteria andCarPlateNumberBetween(String value1, String value2) {
            addCriterion("car_plate_number between", value1, value2, "carPlateNumber");
            return (Criteria) this;
        }

        public Criteria andCarPlateNumberNotBetween(String value1, String value2) {
            addCriterion("car_plate_number not between", value1, value2, "carPlateNumber");
            return (Criteria) this;
        }

        public Criteria andCarTypeIsNull() {
            addCriterion("car_type is null");
            return (Criteria) this;
        }

        public Criteria andCarTypeIsNotNull() {
            addCriterion("car_type is not null");
            return (Criteria) this;
        }

        public Criteria andCarTypeEqualTo(String value) {
            addCriterion("car_type =", value, "carType");
            return (Criteria) this;
        }

        public Criteria andCarTypeNotEqualTo(String value) {
            addCriterion("car_type <>", value, "carType");
            return (Criteria) this;
        }

        public Criteria andCarTypeGreaterThan(String value) {
            addCriterion("car_type >", value, "carType");
            return (Criteria) this;
        }

        public Criteria andCarTypeGreaterThanOrEqualTo(String value) {
            addCriterion("car_type >=", value, "carType");
            return (Criteria) this;
        }

        public Criteria andCarTypeLessThan(String value) {
            addCriterion("car_type <", value, "carType");
            return (Criteria) this;
        }

        public Criteria andCarTypeLessThanOrEqualTo(String value) {
            addCriterion("car_type <=", value, "carType");
            return (Criteria) this;
        }

        public Criteria andCarTypeLike(String value) {
            addCriterion("car_type like", value, "carType");
            return (Criteria) this;
        }

        public Criteria andCarTypeNotLike(String value) {
            addCriterion("car_type not like", value, "carType");
            return (Criteria) this;
        }

        public Criteria andCarTypeIn(List<String> values) {
            addCriterion("car_type in", values, "carType");
            return (Criteria) this;
        }

        public Criteria andCarTypeNotIn(List<String> values) {
            addCriterion("car_type not in", values, "carType");
            return (Criteria) this;
        }

        public Criteria andCarTypeBetween(String value1, String value2) {
            addCriterion("car_type between", value1, value2, "carType");
            return (Criteria) this;
        }

        public Criteria andCarTypeNotBetween(String value1, String value2) {
            addCriterion("car_type not between", value1, value2, "carType");
            return (Criteria) this;
        }

        public Criteria andDriverNameIsNull() {
            addCriterion("driver_name is null");
            return (Criteria) this;
        }

        public Criteria andDriverNameIsNotNull() {
            addCriterion("driver_name is not null");
            return (Criteria) this;
        }

        public Criteria andDriverNameEqualTo(String value) {
            addCriterion("driver_name =", value, "driverName");
            return (Criteria) this;
        }

        public Criteria andDriverNameNotEqualTo(String value) {
            addCriterion("driver_name <>", value, "driverName");
            return (Criteria) this;
        }

        public Criteria andDriverNameGreaterThan(String value) {
            addCriterion("driver_name >", value, "driverName");
            return (Criteria) this;
        }

        public Criteria andDriverNameGreaterThanOrEqualTo(String value) {
            addCriterion("driver_name >=", value, "driverName");
            return (Criteria) this;
        }

        public Criteria andDriverNameLessThan(String value) {
            addCriterion("driver_name <", value, "driverName");
            return (Criteria) this;
        }

        public Criteria andDriverNameLessThanOrEqualTo(String value) {
            addCriterion("driver_name <=", value, "driverName");
            return (Criteria) this;
        }

        public Criteria andDriverNameLike(String value) {
            addCriterion("driver_name like", value, "driverName");
            return (Criteria) this;
        }

        public Criteria andDriverNameNotLike(String value) {
            addCriterion("driver_name not like", value, "driverName");
            return (Criteria) this;
        }

        public Criteria andDriverNameIn(List<String> values) {
            addCriterion("driver_name in", values, "driverName");
            return (Criteria) this;
        }

        public Criteria andDriverNameNotIn(List<String> values) {
            addCriterion("driver_name not in", values, "driverName");
            return (Criteria) this;
        }

        public Criteria andDriverNameBetween(String value1, String value2) {
            addCriterion("driver_name between", value1, value2, "driverName");
            return (Criteria) this;
        }

        public Criteria andDriverNameNotBetween(String value1, String value2) {
            addCriterion("driver_name not between", value1, value2, "driverName");
            return (Criteria) this;
        }

        public Criteria andDriverPhoneIsNull() {
            addCriterion("driver_phone is null");
            return (Criteria) this;
        }

        public Criteria andDriverPhoneIsNotNull() {
            addCriterion("driver_phone is not null");
            return (Criteria) this;
        }

        public Criteria andDriverPhoneEqualTo(String value) {
            addCriterion("driver_phone =", value, "driverPhone");
            return (Criteria) this;
        }

        public Criteria andDriverPhoneNotEqualTo(String value) {
            addCriterion("driver_phone <>", value, "driverPhone");
            return (Criteria) this;
        }

        public Criteria andDriverPhoneGreaterThan(String value) {
            addCriterion("driver_phone >", value, "driverPhone");
            return (Criteria) this;
        }

        public Criteria andDriverPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("driver_phone >=", value, "driverPhone");
            return (Criteria) this;
        }

        public Criteria andDriverPhoneLessThan(String value) {
            addCriterion("driver_phone <", value, "driverPhone");
            return (Criteria) this;
        }

        public Criteria andDriverPhoneLessThanOrEqualTo(String value) {
            addCriterion("driver_phone <=", value, "driverPhone");
            return (Criteria) this;
        }

        public Criteria andDriverPhoneLike(String value) {
            addCriterion("driver_phone like", value, "driverPhone");
            return (Criteria) this;
        }

        public Criteria andDriverPhoneNotLike(String value) {
            addCriterion("driver_phone not like", value, "driverPhone");
            return (Criteria) this;
        }

        public Criteria andDriverPhoneIn(List<String> values) {
            addCriterion("driver_phone in", values, "driverPhone");
            return (Criteria) this;
        }

        public Criteria andDriverPhoneNotIn(List<String> values) {
            addCriterion("driver_phone not in", values, "driverPhone");
            return (Criteria) this;
        }

        public Criteria andDriverPhoneBetween(String value1, String value2) {
            addCriterion("driver_phone between", value1, value2, "driverPhone");
            return (Criteria) this;
        }

        public Criteria andDriverPhoneNotBetween(String value1, String value2) {
            addCriterion("driver_phone not between", value1, value2, "driverPhone");
            return (Criteria) this;
        }

        public Criteria andContainerNumber1IdIsNull() {
            addCriterion("container_number1_id is null");
            return (Criteria) this;
        }

        public Criteria andContainerNumber1IdIsNotNull() {
            addCriterion("container_number1_id is not null");
            return (Criteria) this;
        }

        public Criteria andContainerNumber1IdEqualTo(String value) {
            addCriterion("container_number1_id =", value, "containerNumber1Id");
            return (Criteria) this;
        }

        public Criteria andContainerNumber1IdNotEqualTo(String value) {
            addCriterion("container_number1_id <>", value, "containerNumber1Id");
            return (Criteria) this;
        }

        public Criteria andContainerNumber1IdGreaterThan(String value) {
            addCriterion("container_number1_id >", value, "containerNumber1Id");
            return (Criteria) this;
        }

        public Criteria andContainerNumber1IdGreaterThanOrEqualTo(String value) {
            addCriterion("container_number1_id >=", value, "containerNumber1Id");
            return (Criteria) this;
        }

        public Criteria andContainerNumber1IdLessThan(String value) {
            addCriterion("container_number1_id <", value, "containerNumber1Id");
            return (Criteria) this;
        }

        public Criteria andContainerNumber1IdLessThanOrEqualTo(String value) {
            addCriterion("container_number1_id <=", value, "containerNumber1Id");
            return (Criteria) this;
        }

        public Criteria andContainerNumber1IdLike(String value) {
            addCriterion("container_number1_id like", value, "containerNumber1Id");
            return (Criteria) this;
        }

        public Criteria andContainerNumber1IdNotLike(String value) {
            addCriterion("container_number1_id not like", value, "containerNumber1Id");
            return (Criteria) this;
        }

        public Criteria andContainerNumber1IdIn(List<String> values) {
            addCriterion("container_number1_id in", values, "containerNumber1Id");
            return (Criteria) this;
        }

        public Criteria andContainerNumber1IdNotIn(List<String> values) {
            addCriterion("container_number1_id not in", values, "containerNumber1Id");
            return (Criteria) this;
        }

        public Criteria andContainerNumber1IdBetween(String value1, String value2) {
            addCriterion("container_number1_id between", value1, value2, "containerNumber1Id");
            return (Criteria) this;
        }

        public Criteria andContainerNumber1IdNotBetween(String value1, String value2) {
            addCriterion("container_number1_id not between", value1, value2, "containerNumber1Id");
            return (Criteria) this;
        }

        public Criteria andContainerNumber1IsNull() {
            addCriterion("container_number1 is null");
            return (Criteria) this;
        }

        public Criteria andContainerNumber1IsNotNull() {
            addCriterion("container_number1 is not null");
            return (Criteria) this;
        }

        public Criteria andContainerNumber1EqualTo(String value) {
            addCriterion("container_number1 =", value, "containerNumber1");
            return (Criteria) this;
        }

        public Criteria andContainerNumber1NotEqualTo(String value) {
            addCriterion("container_number1 <>", value, "containerNumber1");
            return (Criteria) this;
        }

        public Criteria andContainerNumber1GreaterThan(String value) {
            addCriterion("container_number1 >", value, "containerNumber1");
            return (Criteria) this;
        }

        public Criteria andContainerNumber1GreaterThanOrEqualTo(String value) {
            addCriterion("container_number1 >=", value, "containerNumber1");
            return (Criteria) this;
        }

        public Criteria andContainerNumber1LessThan(String value) {
            addCriterion("container_number1 <", value, "containerNumber1");
            return (Criteria) this;
        }

        public Criteria andContainerNumber1LessThanOrEqualTo(String value) {
            addCriterion("container_number1 <=", value, "containerNumber1");
            return (Criteria) this;
        }

        public Criteria andContainerNumber1Like(String value) {
            addCriterion("container_number1 like", value, "containerNumber1");
            return (Criteria) this;
        }

        public Criteria andContainerNumber1NotLike(String value) {
            addCriterion("container_number1 not like", value, "containerNumber1");
            return (Criteria) this;
        }

        public Criteria andContainerNumber1In(List<String> values) {
            addCriterion("container_number1 in", values, "containerNumber1");
            return (Criteria) this;
        }

        public Criteria andContainerNumber1NotIn(List<String> values) {
            addCriterion("container_number1 not in", values, "containerNumber1");
            return (Criteria) this;
        }

        public Criteria andContainerNumber1Between(String value1, String value2) {
            addCriterion("container_number1 between", value1, value2, "containerNumber1");
            return (Criteria) this;
        }

        public Criteria andContainerNumber1NotBetween(String value1, String value2) {
            addCriterion("container_number1 not between", value1, value2, "containerNumber1");
            return (Criteria) this;
        }

        public Criteria andContainerNumber2IdIsNull() {
            addCriterion("container_number2_id is null");
            return (Criteria) this;
        }

        public Criteria andContainerNumber2IdIsNotNull() {
            addCriterion("container_number2_id is not null");
            return (Criteria) this;
        }

        public Criteria andContainerNumber2IdEqualTo(String value) {
            addCriterion("container_number2_id =", value, "containerNumber2Id");
            return (Criteria) this;
        }

        public Criteria andContainerNumber2IdNotEqualTo(String value) {
            addCriterion("container_number2_id <>", value, "containerNumber2Id");
            return (Criteria) this;
        }

        public Criteria andContainerNumber2IdGreaterThan(String value) {
            addCriterion("container_number2_id >", value, "containerNumber2Id");
            return (Criteria) this;
        }

        public Criteria andContainerNumber2IdGreaterThanOrEqualTo(String value) {
            addCriterion("container_number2_id >=", value, "containerNumber2Id");
            return (Criteria) this;
        }

        public Criteria andContainerNumber2IdLessThan(String value) {
            addCriterion("container_number2_id <", value, "containerNumber2Id");
            return (Criteria) this;
        }

        public Criteria andContainerNumber2IdLessThanOrEqualTo(String value) {
            addCriterion("container_number2_id <=", value, "containerNumber2Id");
            return (Criteria) this;
        }

        public Criteria andContainerNumber2IdLike(String value) {
            addCriterion("container_number2_id like", value, "containerNumber2Id");
            return (Criteria) this;
        }

        public Criteria andContainerNumber2IdNotLike(String value) {
            addCriterion("container_number2_id not like", value, "containerNumber2Id");
            return (Criteria) this;
        }

        public Criteria andContainerNumber2IdIn(List<String> values) {
            addCriterion("container_number2_id in", values, "containerNumber2Id");
            return (Criteria) this;
        }

        public Criteria andContainerNumber2IdNotIn(List<String> values) {
            addCriterion("container_number2_id not in", values, "containerNumber2Id");
            return (Criteria) this;
        }

        public Criteria andContainerNumber2IdBetween(String value1, String value2) {
            addCriterion("container_number2_id between", value1, value2, "containerNumber2Id");
            return (Criteria) this;
        }

        public Criteria andContainerNumber2IdNotBetween(String value1, String value2) {
            addCriterion("container_number2_id not between", value1, value2, "containerNumber2Id");
            return (Criteria) this;
        }

        public Criteria andContainerNumber2IsNull() {
            addCriterion("container_number2 is null");
            return (Criteria) this;
        }

        public Criteria andContainerNumber2IsNotNull() {
            addCriterion("container_number2 is not null");
            return (Criteria) this;
        }

        public Criteria andContainerNumber2EqualTo(String value) {
            addCriterion("container_number2 =", value, "containerNumber2");
            return (Criteria) this;
        }

        public Criteria andContainerNumber2NotEqualTo(String value) {
            addCriterion("container_number2 <>", value, "containerNumber2");
            return (Criteria) this;
        }

        public Criteria andContainerNumber2GreaterThan(String value) {
            addCriterion("container_number2 >", value, "containerNumber2");
            return (Criteria) this;
        }

        public Criteria andContainerNumber2GreaterThanOrEqualTo(String value) {
            addCriterion("container_number2 >=", value, "containerNumber2");
            return (Criteria) this;
        }

        public Criteria andContainerNumber2LessThan(String value) {
            addCriterion("container_number2 <", value, "containerNumber2");
            return (Criteria) this;
        }

        public Criteria andContainerNumber2LessThanOrEqualTo(String value) {
            addCriterion("container_number2 <=", value, "containerNumber2");
            return (Criteria) this;
        }

        public Criteria andContainerNumber2Like(String value) {
            addCriterion("container_number2 like", value, "containerNumber2");
            return (Criteria) this;
        }

        public Criteria andContainerNumber2NotLike(String value) {
            addCriterion("container_number2 not like", value, "containerNumber2");
            return (Criteria) this;
        }

        public Criteria andContainerNumber2In(List<String> values) {
            addCriterion("container_number2 in", values, "containerNumber2");
            return (Criteria) this;
        }

        public Criteria andContainerNumber2NotIn(List<String> values) {
            addCriterion("container_number2 not in", values, "containerNumber2");
            return (Criteria) this;
        }

        public Criteria andContainerNumber2Between(String value1, String value2) {
            addCriterion("container_number2 between", value1, value2, "containerNumber2");
            return (Criteria) this;
        }

        public Criteria andContainerNumber2NotBetween(String value1, String value2) {
            addCriterion("container_number2 not between", value1, value2, "containerNumber2");
            return (Criteria) this;
        }

        public Criteria andCargoNameIsNull() {
            addCriterion("cargo_name is null");
            return (Criteria) this;
        }

        public Criteria andCargoNameIsNotNull() {
            addCriterion("cargo_name is not null");
            return (Criteria) this;
        }

        public Criteria andCargoNameEqualTo(String value) {
            addCriterion("cargo_name =", value, "cargoName");
            return (Criteria) this;
        }

        public Criteria andCargoNameNotEqualTo(String value) {
            addCriterion("cargo_name <>", value, "cargoName");
            return (Criteria) this;
        }

        public Criteria andCargoNameGreaterThan(String value) {
            addCriterion("cargo_name >", value, "cargoName");
            return (Criteria) this;
        }

        public Criteria andCargoNameGreaterThanOrEqualTo(String value) {
            addCriterion("cargo_name >=", value, "cargoName");
            return (Criteria) this;
        }

        public Criteria andCargoNameLessThan(String value) {
            addCriterion("cargo_name <", value, "cargoName");
            return (Criteria) this;
        }

        public Criteria andCargoNameLessThanOrEqualTo(String value) {
            addCriterion("cargo_name <=", value, "cargoName");
            return (Criteria) this;
        }

        public Criteria andCargoNameLike(String value) {
            addCriterion("cargo_name like", value, "cargoName");
            return (Criteria) this;
        }

        public Criteria andCargoNameNotLike(String value) {
            addCriterion("cargo_name not like", value, "cargoName");
            return (Criteria) this;
        }

        public Criteria andCargoNameIn(List<String> values) {
            addCriterion("cargo_name in", values, "cargoName");
            return (Criteria) this;
        }

        public Criteria andCargoNameNotIn(List<String> values) {
            addCriterion("cargo_name not in", values, "cargoName");
            return (Criteria) this;
        }

        public Criteria andCargoNameBetween(String value1, String value2) {
            addCriterion("cargo_name between", value1, value2, "cargoName");
            return (Criteria) this;
        }

        public Criteria andCargoNameNotBetween(String value1, String value2) {
            addCriterion("cargo_name not between", value1, value2, "cargoName");
            return (Criteria) this;
        }

        public Criteria andSpecificationsIsNull() {
            addCriterion("specifications is null");
            return (Criteria) this;
        }

        public Criteria andSpecificationsIsNotNull() {
            addCriterion("specifications is not null");
            return (Criteria) this;
        }

        public Criteria andSpecificationsEqualTo(String value) {
            addCriterion("specifications =", value, "specifications");
            return (Criteria) this;
        }

        public Criteria andSpecificationsNotEqualTo(String value) {
            addCriterion("specifications <>", value, "specifications");
            return (Criteria) this;
        }

        public Criteria andSpecificationsGreaterThan(String value) {
            addCriterion("specifications >", value, "specifications");
            return (Criteria) this;
        }

        public Criteria andSpecificationsGreaterThanOrEqualTo(String value) {
            addCriterion("specifications >=", value, "specifications");
            return (Criteria) this;
        }

        public Criteria andSpecificationsLessThan(String value) {
            addCriterion("specifications <", value, "specifications");
            return (Criteria) this;
        }

        public Criteria andSpecificationsLessThanOrEqualTo(String value) {
            addCriterion("specifications <=", value, "specifications");
            return (Criteria) this;
        }

        public Criteria andSpecificationsLike(String value) {
            addCriterion("specifications like", value, "specifications");
            return (Criteria) this;
        }

        public Criteria andSpecificationsNotLike(String value) {
            addCriterion("specifications not like", value, "specifications");
            return (Criteria) this;
        }

        public Criteria andSpecificationsIn(List<String> values) {
            addCriterion("specifications in", values, "specifications");
            return (Criteria) this;
        }

        public Criteria andSpecificationsNotIn(List<String> values) {
            addCriterion("specifications not in", values, "specifications");
            return (Criteria) this;
        }

        public Criteria andSpecificationsBetween(String value1, String value2) {
            addCriterion("specifications between", value1, value2, "specifications");
            return (Criteria) this;
        }

        public Criteria andSpecificationsNotBetween(String value1, String value2) {
            addCriterion("specifications not between", value1, value2, "specifications");
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

        public Criteria andDeductionRateEqualTo(BigDecimal value) {
            addCriterion("deduction_rate =", value, "deductionRate");
            return (Criteria) this;
        }

        public Criteria andDeductionRateNotEqualTo(BigDecimal value) {
            addCriterion("deduction_rate <>", value, "deductionRate");
            return (Criteria) this;
        }

        public Criteria andDeductionRateGreaterThan(BigDecimal value) {
            addCriterion("deduction_rate >", value, "deductionRate");
            return (Criteria) this;
        }

        public Criteria andDeductionRateGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("deduction_rate >=", value, "deductionRate");
            return (Criteria) this;
        }

        public Criteria andDeductionRateLessThan(BigDecimal value) {
            addCriterion("deduction_rate <", value, "deductionRate");
            return (Criteria) this;
        }

        public Criteria andDeductionRateLessThanOrEqualTo(BigDecimal value) {
            addCriterion("deduction_rate <=", value, "deductionRate");
            return (Criteria) this;
        }

        public Criteria andDeductionRateIn(List<BigDecimal> values) {
            addCriterion("deduction_rate in", values, "deductionRate");
            return (Criteria) this;
        }

        public Criteria andDeductionRateNotIn(List<BigDecimal> values) {
            addCriterion("deduction_rate not in", values, "deductionRate");
            return (Criteria) this;
        }

        public Criteria andDeductionRateBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("deduction_rate between", value1, value2, "deductionRate");
            return (Criteria) this;
        }

        public Criteria andDeductionRateNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("deduction_rate not between", value1, value2, "deductionRate");
            return (Criteria) this;
        }

        public Criteria andShortBargeCostIsNull() {
            addCriterion("short_barge_cost is null");
            return (Criteria) this;
        }

        public Criteria andShortBargeCostIsNotNull() {
            addCriterion("short_barge_cost is not null");
            return (Criteria) this;
        }

        public Criteria andShortBargeCostEqualTo(BigDecimal value) {
            addCriterion("short_barge_cost =", value, "shortBargeCost");
            return (Criteria) this;
        }

        public Criteria andShortBargeCostNotEqualTo(BigDecimal value) {
            addCriterion("short_barge_cost <>", value, "shortBargeCost");
            return (Criteria) this;
        }

        public Criteria andShortBargeCostGreaterThan(BigDecimal value) {
            addCriterion("short_barge_cost >", value, "shortBargeCost");
            return (Criteria) this;
        }

        public Criteria andShortBargeCostGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("short_barge_cost >=", value, "shortBargeCost");
            return (Criteria) this;
        }

        public Criteria andShortBargeCostLessThan(BigDecimal value) {
            addCriterion("short_barge_cost <", value, "shortBargeCost");
            return (Criteria) this;
        }

        public Criteria andShortBargeCostLessThanOrEqualTo(BigDecimal value) {
            addCriterion("short_barge_cost <=", value, "shortBargeCost");
            return (Criteria) this;
        }

        public Criteria andShortBargeCostIn(List<BigDecimal> values) {
            addCriterion("short_barge_cost in", values, "shortBargeCost");
            return (Criteria) this;
        }

        public Criteria andShortBargeCostNotIn(List<BigDecimal> values) {
            addCriterion("short_barge_cost not in", values, "shortBargeCost");
            return (Criteria) this;
        }

        public Criteria andShortBargeCostBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("short_barge_cost between", value1, value2, "shortBargeCost");
            return (Criteria) this;
        }

        public Criteria andShortBargeCostNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("short_barge_cost not between", value1, value2, "shortBargeCost");
            return (Criteria) this;
        }

        public Criteria andSubsidyIsNull() {
            addCriterion("subsidy is null");
            return (Criteria) this;
        }

        public Criteria andSubsidyIsNotNull() {
            addCriterion("subsidy is not null");
            return (Criteria) this;
        }

        public Criteria andSubsidyEqualTo(BigDecimal value) {
            addCriterion("subsidy =", value, "subsidy");
            return (Criteria) this;
        }

        public Criteria andSubsidyNotEqualTo(BigDecimal value) {
            addCriterion("subsidy <>", value, "subsidy");
            return (Criteria) this;
        }

        public Criteria andSubsidyGreaterThan(BigDecimal value) {
            addCriterion("subsidy >", value, "subsidy");
            return (Criteria) this;
        }

        public Criteria andSubsidyGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("subsidy >=", value, "subsidy");
            return (Criteria) this;
        }

        public Criteria andSubsidyLessThan(BigDecimal value) {
            addCriterion("subsidy <", value, "subsidy");
            return (Criteria) this;
        }

        public Criteria andSubsidyLessThanOrEqualTo(BigDecimal value) {
            addCriterion("subsidy <=", value, "subsidy");
            return (Criteria) this;
        }

        public Criteria andSubsidyIn(List<BigDecimal> values) {
            addCriterion("subsidy in", values, "subsidy");
            return (Criteria) this;
        }

        public Criteria andSubsidyNotIn(List<BigDecimal> values) {
            addCriterion("subsidy not in", values, "subsidy");
            return (Criteria) this;
        }

        public Criteria andSubsidyBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("subsidy between", value1, value2, "subsidy");
            return (Criteria) this;
        }

        public Criteria andSubsidyNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("subsidy not between", value1, value2, "subsidy");
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

        public Criteria andSendTareIsNull() {
            addCriterion("send_tare is null");
            return (Criteria) this;
        }

        public Criteria andSendTareIsNotNull() {
            addCriterion("send_tare is not null");
            return (Criteria) this;
        }

        public Criteria andSendTareEqualTo(BigDecimal value) {
            addCriterion("send_tare =", value, "sendTare");
            return (Criteria) this;
        }

        public Criteria andSendTareNotEqualTo(BigDecimal value) {
            addCriterion("send_tare <>", value, "sendTare");
            return (Criteria) this;
        }

        public Criteria andSendTareGreaterThan(BigDecimal value) {
            addCriterion("send_tare >", value, "sendTare");
            return (Criteria) this;
        }

        public Criteria andSendTareGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("send_tare >=", value, "sendTare");
            return (Criteria) this;
        }

        public Criteria andSendTareLessThan(BigDecimal value) {
            addCriterion("send_tare <", value, "sendTare");
            return (Criteria) this;
        }

        public Criteria andSendTareLessThanOrEqualTo(BigDecimal value) {
            addCriterion("send_tare <=", value, "sendTare");
            return (Criteria) this;
        }

        public Criteria andSendTareIn(List<BigDecimal> values) {
            addCriterion("send_tare in", values, "sendTare");
            return (Criteria) this;
        }

        public Criteria andSendTareNotIn(List<BigDecimal> values) {
            addCriterion("send_tare not in", values, "sendTare");
            return (Criteria) this;
        }

        public Criteria andSendTareBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("send_tare between", value1, value2, "sendTare");
            return (Criteria) this;
        }

        public Criteria andSendTareNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("send_tare not between", value1, value2, "sendTare");
            return (Criteria) this;
        }

        public Criteria andSendGrossIsNull() {
            addCriterion("send_gross is null");
            return (Criteria) this;
        }

        public Criteria andSendGrossIsNotNull() {
            addCriterion("send_gross is not null");
            return (Criteria) this;
        }

        public Criteria andSendGrossEqualTo(BigDecimal value) {
            addCriterion("send_gross =", value, "sendGross");
            return (Criteria) this;
        }

        public Criteria andSendGrossNotEqualTo(BigDecimal value) {
            addCriterion("send_gross <>", value, "sendGross");
            return (Criteria) this;
        }

        public Criteria andSendGrossGreaterThan(BigDecimal value) {
            addCriterion("send_gross >", value, "sendGross");
            return (Criteria) this;
        }

        public Criteria andSendGrossGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("send_gross >=", value, "sendGross");
            return (Criteria) this;
        }

        public Criteria andSendGrossLessThan(BigDecimal value) {
            addCriterion("send_gross <", value, "sendGross");
            return (Criteria) this;
        }

        public Criteria andSendGrossLessThanOrEqualTo(BigDecimal value) {
            addCriterion("send_gross <=", value, "sendGross");
            return (Criteria) this;
        }

        public Criteria andSendGrossIn(List<BigDecimal> values) {
            addCriterion("send_gross in", values, "sendGross");
            return (Criteria) this;
        }

        public Criteria andSendGrossNotIn(List<BigDecimal> values) {
            addCriterion("send_gross not in", values, "sendGross");
            return (Criteria) this;
        }

        public Criteria andSendGrossBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("send_gross between", value1, value2, "sendGross");
            return (Criteria) this;
        }

        public Criteria andSendGrossNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("send_gross not between", value1, value2, "sendGross");
            return (Criteria) this;
        }

        public Criteria andContainerOneSendNetIsNull() {
            addCriterion("container_one_send_net is null");
            return (Criteria) this;
        }

        public Criteria andContainerOneSendNetIsNotNull() {
            addCriterion("container_one_send_net is not null");
            return (Criteria) this;
        }

        public Criteria andContainerOneSendNetEqualTo(BigDecimal value) {
            addCriterion("container_one_send_net =", value, "containerOneSendNet");
            return (Criteria) this;
        }

        public Criteria andContainerOneSendNetNotEqualTo(BigDecimal value) {
            addCriterion("container_one_send_net <>", value, "containerOneSendNet");
            return (Criteria) this;
        }

        public Criteria andContainerOneSendNetGreaterThan(BigDecimal value) {
            addCriterion("container_one_send_net >", value, "containerOneSendNet");
            return (Criteria) this;
        }

        public Criteria andContainerOneSendNetGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("container_one_send_net >=", value, "containerOneSendNet");
            return (Criteria) this;
        }

        public Criteria andContainerOneSendNetLessThan(BigDecimal value) {
            addCriterion("container_one_send_net <", value, "containerOneSendNet");
            return (Criteria) this;
        }

        public Criteria andContainerOneSendNetLessThanOrEqualTo(BigDecimal value) {
            addCriterion("container_one_send_net <=", value, "containerOneSendNet");
            return (Criteria) this;
        }

        public Criteria andContainerOneSendNetIn(List<BigDecimal> values) {
            addCriterion("container_one_send_net in", values, "containerOneSendNet");
            return (Criteria) this;
        }

        public Criteria andContainerOneSendNetNotIn(List<BigDecimal> values) {
            addCriterion("container_one_send_net not in", values, "containerOneSendNet");
            return (Criteria) this;
        }

        public Criteria andContainerOneSendNetBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("container_one_send_net between", value1, value2, "containerOneSendNet");
            return (Criteria) this;
        }

        public Criteria andContainerOneSendNetNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("container_one_send_net not between", value1, value2, "containerOneSendNet");
            return (Criteria) this;
        }

        public Criteria andContainerTwoSendNetIsNull() {
            addCriterion("container_two_send_net is null");
            return (Criteria) this;
        }

        public Criteria andContainerTwoSendNetIsNotNull() {
            addCriterion("container_two_send_net is not null");
            return (Criteria) this;
        }

        public Criteria andContainerTwoSendNetEqualTo(BigDecimal value) {
            addCriterion("container_two_send_net =", value, "containerTwoSendNet");
            return (Criteria) this;
        }

        public Criteria andContainerTwoSendNetNotEqualTo(BigDecimal value) {
            addCriterion("container_two_send_net <>", value, "containerTwoSendNet");
            return (Criteria) this;
        }

        public Criteria andContainerTwoSendNetGreaterThan(BigDecimal value) {
            addCriterion("container_two_send_net >", value, "containerTwoSendNet");
            return (Criteria) this;
        }

        public Criteria andContainerTwoSendNetGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("container_two_send_net >=", value, "containerTwoSendNet");
            return (Criteria) this;
        }

        public Criteria andContainerTwoSendNetLessThan(BigDecimal value) {
            addCriterion("container_two_send_net <", value, "containerTwoSendNet");
            return (Criteria) this;
        }

        public Criteria andContainerTwoSendNetLessThanOrEqualTo(BigDecimal value) {
            addCriterion("container_two_send_net <=", value, "containerTwoSendNet");
            return (Criteria) this;
        }

        public Criteria andContainerTwoSendNetIn(List<BigDecimal> values) {
            addCriterion("container_two_send_net in", values, "containerTwoSendNet");
            return (Criteria) this;
        }

        public Criteria andContainerTwoSendNetNotIn(List<BigDecimal> values) {
            addCriterion("container_two_send_net not in", values, "containerTwoSendNet");
            return (Criteria) this;
        }

        public Criteria andContainerTwoSendNetBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("container_two_send_net between", value1, value2, "containerTwoSendNet");
            return (Criteria) this;
        }

        public Criteria andContainerTwoSendNetNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("container_two_send_net not between", value1, value2, "containerTwoSendNet");
            return (Criteria) this;
        }

        public Criteria andTestIndicatorsIsNull() {
            addCriterion("test_indicators is null");
            return (Criteria) this;
        }

        public Criteria andTestIndicatorsIsNotNull() {
            addCriterion("test_indicators is not null");
            return (Criteria) this;
        }

        public Criteria andTestIndicatorsEqualTo(String value) {
            addCriterion("test_indicators =", value, "testIndicators");
            return (Criteria) this;
        }

        public Criteria andTestIndicatorsNotEqualTo(String value) {
            addCriterion("test_indicators <>", value, "testIndicators");
            return (Criteria) this;
        }

        public Criteria andTestIndicatorsGreaterThan(String value) {
            addCriterion("test_indicators >", value, "testIndicators");
            return (Criteria) this;
        }

        public Criteria andTestIndicatorsGreaterThanOrEqualTo(String value) {
            addCriterion("test_indicators >=", value, "testIndicators");
            return (Criteria) this;
        }

        public Criteria andTestIndicatorsLessThan(String value) {
            addCriterion("test_indicators <", value, "testIndicators");
            return (Criteria) this;
        }

        public Criteria andTestIndicatorsLessThanOrEqualTo(String value) {
            addCriterion("test_indicators <=", value, "testIndicators");
            return (Criteria) this;
        }

        public Criteria andTestIndicatorsLike(String value) {
            addCriterion("test_indicators like", value, "testIndicators");
            return (Criteria) this;
        }

        public Criteria andTestIndicatorsNotLike(String value) {
            addCriterion("test_indicators not like", value, "testIndicators");
            return (Criteria) this;
        }

        public Criteria andTestIndicatorsIn(List<String> values) {
            addCriterion("test_indicators in", values, "testIndicators");
            return (Criteria) this;
        }

        public Criteria andTestIndicatorsNotIn(List<String> values) {
            addCriterion("test_indicators not in", values, "testIndicators");
            return (Criteria) this;
        }

        public Criteria andTestIndicatorsBetween(String value1, String value2) {
            addCriterion("test_indicators between", value1, value2, "testIndicators");
            return (Criteria) this;
        }

        public Criteria andTestIndicatorsNotBetween(String value1, String value2) {
            addCriterion("test_indicators not between", value1, value2, "testIndicators");
            return (Criteria) this;
        }

        public Criteria andOrderImgIsNull() {
            addCriterion("order_img is null");
            return (Criteria) this;
        }

        public Criteria andOrderImgIsNotNull() {
            addCriterion("order_img is not null");
            return (Criteria) this;
        }

        public Criteria andOrderImgEqualTo(String value) {
            addCriterion("order_img =", value, "orderImg");
            return (Criteria) this;
        }

        public Criteria andOrderImgNotEqualTo(String value) {
            addCriterion("order_img <>", value, "orderImg");
            return (Criteria) this;
        }

        public Criteria andOrderImgGreaterThan(String value) {
            addCriterion("order_img >", value, "orderImg");
            return (Criteria) this;
        }

        public Criteria andOrderImgGreaterThanOrEqualTo(String value) {
            addCriterion("order_img >=", value, "orderImg");
            return (Criteria) this;
        }

        public Criteria andOrderImgLessThan(String value) {
            addCriterion("order_img <", value, "orderImg");
            return (Criteria) this;
        }

        public Criteria andOrderImgLessThanOrEqualTo(String value) {
            addCriterion("order_img <=", value, "orderImg");
            return (Criteria) this;
        }

        public Criteria andOrderImgLike(String value) {
            addCriterion("order_img like", value, "orderImg");
            return (Criteria) this;
        }

        public Criteria andOrderImgNotLike(String value) {
            addCriterion("order_img not like", value, "orderImg");
            return (Criteria) this;
        }

        public Criteria andOrderImgIn(List<String> values) {
            addCriterion("order_img in", values, "orderImg");
            return (Criteria) this;
        }

        public Criteria andOrderImgNotIn(List<String> values) {
            addCriterion("order_img not in", values, "orderImg");
            return (Criteria) this;
        }

        public Criteria andOrderImgBetween(String value1, String value2) {
            addCriterion("order_img between", value1, value2, "orderImg");
            return (Criteria) this;
        }

        public Criteria andOrderImgNotBetween(String value1, String value2) {
            addCriterion("order_img not between", value1, value2, "orderImg");
            return (Criteria) this;
        }

        public Criteria andDistributionCargoPlaceIsNull() {
            addCriterion("distribution_cargo_place is null");
            return (Criteria) this;
        }

        public Criteria andDistributionCargoPlaceIsNotNull() {
            addCriterion("distribution_cargo_place is not null");
            return (Criteria) this;
        }

        public Criteria andDistributionCargoPlaceEqualTo(String value) {
            addCriterion("distribution_cargo_place =", value, "distributionCargoPlace");
            return (Criteria) this;
        }

        public Criteria andDistributionCargoPlaceNotEqualTo(String value) {
            addCriterion("distribution_cargo_place <>", value, "distributionCargoPlace");
            return (Criteria) this;
        }

        public Criteria andDistributionCargoPlaceGreaterThan(String value) {
            addCriterion("distribution_cargo_place >", value, "distributionCargoPlace");
            return (Criteria) this;
        }

        public Criteria andDistributionCargoPlaceGreaterThanOrEqualTo(String value) {
            addCriterion("distribution_cargo_place >=", value, "distributionCargoPlace");
            return (Criteria) this;
        }

        public Criteria andDistributionCargoPlaceLessThan(String value) {
            addCriterion("distribution_cargo_place <", value, "distributionCargoPlace");
            return (Criteria) this;
        }

        public Criteria andDistributionCargoPlaceLessThanOrEqualTo(String value) {
            addCriterion("distribution_cargo_place <=", value, "distributionCargoPlace");
            return (Criteria) this;
        }

        public Criteria andDistributionCargoPlaceLike(String value) {
            addCriterion("distribution_cargo_place like", value, "distributionCargoPlace");
            return (Criteria) this;
        }

        public Criteria andDistributionCargoPlaceNotLike(String value) {
            addCriterion("distribution_cargo_place not like", value, "distributionCargoPlace");
            return (Criteria) this;
        }

        public Criteria andDistributionCargoPlaceIn(List<String> values) {
            addCriterion("distribution_cargo_place in", values, "distributionCargoPlace");
            return (Criteria) this;
        }

        public Criteria andDistributionCargoPlaceNotIn(List<String> values) {
            addCriterion("distribution_cargo_place not in", values, "distributionCargoPlace");
            return (Criteria) this;
        }

        public Criteria andDistributionCargoPlaceBetween(String value1, String value2) {
            addCriterion("distribution_cargo_place between", value1, value2, "distributionCargoPlace");
            return (Criteria) this;
        }

        public Criteria andDistributionCargoPlaceNotBetween(String value1, String value2) {
            addCriterion("distribution_cargo_place not between", value1, value2, "distributionCargoPlace");
            return (Criteria) this;
        }

        public Criteria andDistributionCargoSiteIsNull() {
            addCriterion("distribution_cargo_site is null");
            return (Criteria) this;
        }

        public Criteria andDistributionCargoSiteIsNotNull() {
            addCriterion("distribution_cargo_site is not null");
            return (Criteria) this;
        }

        public Criteria andDistributionCargoSiteEqualTo(String value) {
            addCriterion("distribution_cargo_site =", value, "distributionCargoSite");
            return (Criteria) this;
        }

        public Criteria andDistributionCargoSiteNotEqualTo(String value) {
            addCriterion("distribution_cargo_site <>", value, "distributionCargoSite");
            return (Criteria) this;
        }

        public Criteria andDistributionCargoSiteGreaterThan(String value) {
            addCriterion("distribution_cargo_site >", value, "distributionCargoSite");
            return (Criteria) this;
        }

        public Criteria andDistributionCargoSiteGreaterThanOrEqualTo(String value) {
            addCriterion("distribution_cargo_site >=", value, "distributionCargoSite");
            return (Criteria) this;
        }

        public Criteria andDistributionCargoSiteLessThan(String value) {
            addCriterion("distribution_cargo_site <", value, "distributionCargoSite");
            return (Criteria) this;
        }

        public Criteria andDistributionCargoSiteLessThanOrEqualTo(String value) {
            addCriterion("distribution_cargo_site <=", value, "distributionCargoSite");
            return (Criteria) this;
        }

        public Criteria andDistributionCargoSiteLike(String value) {
            addCriterion("distribution_cargo_site like", value, "distributionCargoSite");
            return (Criteria) this;
        }

        public Criteria andDistributionCargoSiteNotLike(String value) {
            addCriterion("distribution_cargo_site not like", value, "distributionCargoSite");
            return (Criteria) this;
        }

        public Criteria andDistributionCargoSiteIn(List<String> values) {
            addCriterion("distribution_cargo_site in", values, "distributionCargoSite");
            return (Criteria) this;
        }

        public Criteria andDistributionCargoSiteNotIn(List<String> values) {
            addCriterion("distribution_cargo_site not in", values, "distributionCargoSite");
            return (Criteria) this;
        }

        public Criteria andDistributionCargoSiteBetween(String value1, String value2) {
            addCriterion("distribution_cargo_site between", value1, value2, "distributionCargoSite");
            return (Criteria) this;
        }

        public Criteria andDistributionCargoSiteNotBetween(String value1, String value2) {
            addCriterion("distribution_cargo_site not between", value1, value2, "distributionCargoSite");
            return (Criteria) this;
        }

        public Criteria andReceiptTareIsNull() {
            addCriterion("receipt_tare is null");
            return (Criteria) this;
        }

        public Criteria andReceiptTareIsNotNull() {
            addCriterion("receipt_tare is not null");
            return (Criteria) this;
        }

        public Criteria andReceiptTareEqualTo(BigDecimal value) {
            addCriterion("receipt_tare =", value, "receiptTare");
            return (Criteria) this;
        }

        public Criteria andReceiptTareNotEqualTo(BigDecimal value) {
            addCriterion("receipt_tare <>", value, "receiptTare");
            return (Criteria) this;
        }

        public Criteria andReceiptTareGreaterThan(BigDecimal value) {
            addCriterion("receipt_tare >", value, "receiptTare");
            return (Criteria) this;
        }

        public Criteria andReceiptTareGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("receipt_tare >=", value, "receiptTare");
            return (Criteria) this;
        }

        public Criteria andReceiptTareLessThan(BigDecimal value) {
            addCriterion("receipt_tare <", value, "receiptTare");
            return (Criteria) this;
        }

        public Criteria andReceiptTareLessThanOrEqualTo(BigDecimal value) {
            addCriterion("receipt_tare <=", value, "receiptTare");
            return (Criteria) this;
        }

        public Criteria andReceiptTareIn(List<BigDecimal> values) {
            addCriterion("receipt_tare in", values, "receiptTare");
            return (Criteria) this;
        }

        public Criteria andReceiptTareNotIn(List<BigDecimal> values) {
            addCriterion("receipt_tare not in", values, "receiptTare");
            return (Criteria) this;
        }

        public Criteria andReceiptTareBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("receipt_tare between", value1, value2, "receiptTare");
            return (Criteria) this;
        }

        public Criteria andReceiptTareNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("receipt_tare not between", value1, value2, "receiptTare");
            return (Criteria) this;
        }

        public Criteria andReceiptGrossIsNull() {
            addCriterion("receipt_gross is null");
            return (Criteria) this;
        }

        public Criteria andReceiptGrossIsNotNull() {
            addCriterion("receipt_gross is not null");
            return (Criteria) this;
        }

        public Criteria andReceiptGrossEqualTo(BigDecimal value) {
            addCriterion("receipt_gross =", value, "receiptGross");
            return (Criteria) this;
        }

        public Criteria andReceiptGrossNotEqualTo(BigDecimal value) {
            addCriterion("receipt_gross <>", value, "receiptGross");
            return (Criteria) this;
        }

        public Criteria andReceiptGrossGreaterThan(BigDecimal value) {
            addCriterion("receipt_gross >", value, "receiptGross");
            return (Criteria) this;
        }

        public Criteria andReceiptGrossGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("receipt_gross >=", value, "receiptGross");
            return (Criteria) this;
        }

        public Criteria andReceiptGrossLessThan(BigDecimal value) {
            addCriterion("receipt_gross <", value, "receiptGross");
            return (Criteria) this;
        }

        public Criteria andReceiptGrossLessThanOrEqualTo(BigDecimal value) {
            addCriterion("receipt_gross <=", value, "receiptGross");
            return (Criteria) this;
        }

        public Criteria andReceiptGrossIn(List<BigDecimal> values) {
            addCriterion("receipt_gross in", values, "receiptGross");
            return (Criteria) this;
        }

        public Criteria andReceiptGrossNotIn(List<BigDecimal> values) {
            addCriterion("receipt_gross not in", values, "receiptGross");
            return (Criteria) this;
        }

        public Criteria andReceiptGrossBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("receipt_gross between", value1, value2, "receiptGross");
            return (Criteria) this;
        }

        public Criteria andReceiptGrossNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("receipt_gross not between", value1, value2, "receiptGross");
            return (Criteria) this;
        }

        public Criteria andContainerOneReceiptNetIsNull() {
            addCriterion("container_one_receipt_net is null");
            return (Criteria) this;
        }

        public Criteria andContainerOneReceiptNetIsNotNull() {
            addCriterion("container_one_receipt_net is not null");
            return (Criteria) this;
        }

        public Criteria andContainerOneReceiptNetEqualTo(BigDecimal value) {
            addCriterion("container_one_receipt_net =", value, "containerOneReceiptNet");
            return (Criteria) this;
        }

        public Criteria andContainerOneReceiptNetNotEqualTo(BigDecimal value) {
            addCriterion("container_one_receipt_net <>", value, "containerOneReceiptNet");
            return (Criteria) this;
        }

        public Criteria andContainerOneReceiptNetGreaterThan(BigDecimal value) {
            addCriterion("container_one_receipt_net >", value, "containerOneReceiptNet");
            return (Criteria) this;
        }

        public Criteria andContainerOneReceiptNetGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("container_one_receipt_net >=", value, "containerOneReceiptNet");
            return (Criteria) this;
        }

        public Criteria andContainerOneReceiptNetLessThan(BigDecimal value) {
            addCriterion("container_one_receipt_net <", value, "containerOneReceiptNet");
            return (Criteria) this;
        }

        public Criteria andContainerOneReceiptNetLessThanOrEqualTo(BigDecimal value) {
            addCriterion("container_one_receipt_net <=", value, "containerOneReceiptNet");
            return (Criteria) this;
        }

        public Criteria andContainerOneReceiptNetIn(List<BigDecimal> values) {
            addCriterion("container_one_receipt_net in", values, "containerOneReceiptNet");
            return (Criteria) this;
        }

        public Criteria andContainerOneReceiptNetNotIn(List<BigDecimal> values) {
            addCriterion("container_one_receipt_net not in", values, "containerOneReceiptNet");
            return (Criteria) this;
        }

        public Criteria andContainerOneReceiptNetBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("container_one_receipt_net between", value1, value2, "containerOneReceiptNet");
            return (Criteria) this;
        }

        public Criteria andContainerOneReceiptNetNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("container_one_receipt_net not between", value1, value2, "containerOneReceiptNet");
            return (Criteria) this;
        }

        public Criteria andContainerTwoReceiptNetIsNull() {
            addCriterion("container_two_receipt_net is null");
            return (Criteria) this;
        }

        public Criteria andContainerTwoReceiptNetIsNotNull() {
            addCriterion("container_two_receipt_net is not null");
            return (Criteria) this;
        }

        public Criteria andContainerTwoReceiptNetEqualTo(BigDecimal value) {
            addCriterion("container_two_receipt_net =", value, "containerTwoReceiptNet");
            return (Criteria) this;
        }

        public Criteria andContainerTwoReceiptNetNotEqualTo(BigDecimal value) {
            addCriterion("container_two_receipt_net <>", value, "containerTwoReceiptNet");
            return (Criteria) this;
        }

        public Criteria andContainerTwoReceiptNetGreaterThan(BigDecimal value) {
            addCriterion("container_two_receipt_net >", value, "containerTwoReceiptNet");
            return (Criteria) this;
        }

        public Criteria andContainerTwoReceiptNetGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("container_two_receipt_net >=", value, "containerTwoReceiptNet");
            return (Criteria) this;
        }

        public Criteria andContainerTwoReceiptNetLessThan(BigDecimal value) {
            addCriterion("container_two_receipt_net <", value, "containerTwoReceiptNet");
            return (Criteria) this;
        }

        public Criteria andContainerTwoReceiptNetLessThanOrEqualTo(BigDecimal value) {
            addCriterion("container_two_receipt_net <=", value, "containerTwoReceiptNet");
            return (Criteria) this;
        }

        public Criteria andContainerTwoReceiptNetIn(List<BigDecimal> values) {
            addCriterion("container_two_receipt_net in", values, "containerTwoReceiptNet");
            return (Criteria) this;
        }

        public Criteria andContainerTwoReceiptNetNotIn(List<BigDecimal> values) {
            addCriterion("container_two_receipt_net not in", values, "containerTwoReceiptNet");
            return (Criteria) this;
        }

        public Criteria andContainerTwoReceiptNetBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("container_two_receipt_net between", value1, value2, "containerTwoReceiptNet");
            return (Criteria) this;
        }

        public Criteria andContainerTwoReceiptNetNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("container_two_receipt_net not between", value1, value2, "containerTwoReceiptNet");
            return (Criteria) this;
        }

        public Criteria andReceiptTestIndicatorsIsNull() {
            addCriterion("receipt_test_indicators is null");
            return (Criteria) this;
        }

        public Criteria andReceiptTestIndicatorsIsNotNull() {
            addCriterion("receipt_test_indicators is not null");
            return (Criteria) this;
        }

        public Criteria andReceiptTestIndicatorsEqualTo(String value) {
            addCriterion("receipt_test_indicators =", value, "receiptTestIndicators");
            return (Criteria) this;
        }

        public Criteria andReceiptTestIndicatorsNotEqualTo(String value) {
            addCriterion("receipt_test_indicators <>", value, "receiptTestIndicators");
            return (Criteria) this;
        }

        public Criteria andReceiptTestIndicatorsGreaterThan(String value) {
            addCriterion("receipt_test_indicators >", value, "receiptTestIndicators");
            return (Criteria) this;
        }

        public Criteria andReceiptTestIndicatorsGreaterThanOrEqualTo(String value) {
            addCriterion("receipt_test_indicators >=", value, "receiptTestIndicators");
            return (Criteria) this;
        }

        public Criteria andReceiptTestIndicatorsLessThan(String value) {
            addCriterion("receipt_test_indicators <", value, "receiptTestIndicators");
            return (Criteria) this;
        }

        public Criteria andReceiptTestIndicatorsLessThanOrEqualTo(String value) {
            addCriterion("receipt_test_indicators <=", value, "receiptTestIndicators");
            return (Criteria) this;
        }

        public Criteria andReceiptTestIndicatorsLike(String value) {
            addCriterion("receipt_test_indicators like", value, "receiptTestIndicators");
            return (Criteria) this;
        }

        public Criteria andReceiptTestIndicatorsNotLike(String value) {
            addCriterion("receipt_test_indicators not like", value, "receiptTestIndicators");
            return (Criteria) this;
        }

        public Criteria andReceiptTestIndicatorsIn(List<String> values) {
            addCriterion("receipt_test_indicators in", values, "receiptTestIndicators");
            return (Criteria) this;
        }

        public Criteria andReceiptTestIndicatorsNotIn(List<String> values) {
            addCriterion("receipt_test_indicators not in", values, "receiptTestIndicators");
            return (Criteria) this;
        }

        public Criteria andReceiptTestIndicatorsBetween(String value1, String value2) {
            addCriterion("receipt_test_indicators between", value1, value2, "receiptTestIndicators");
            return (Criteria) this;
        }

        public Criteria andReceiptTestIndicatorsNotBetween(String value1, String value2) {
            addCriterion("receipt_test_indicators not between", value1, value2, "receiptTestIndicators");
            return (Criteria) this;
        }

        public Criteria andArriveredImgIsNull() {
            addCriterion("arrivered_img is null");
            return (Criteria) this;
        }

        public Criteria andArriveredImgIsNotNull() {
            addCriterion("arrivered_img is not null");
            return (Criteria) this;
        }

        public Criteria andArriveredImgEqualTo(String value) {
            addCriterion("arrivered_img =", value, "arriveredImg");
            return (Criteria) this;
        }

        public Criteria andArriveredImgNotEqualTo(String value) {
            addCriterion("arrivered_img <>", value, "arriveredImg");
            return (Criteria) this;
        }

        public Criteria andArriveredImgGreaterThan(String value) {
            addCriterion("arrivered_img >", value, "arriveredImg");
            return (Criteria) this;
        }

        public Criteria andArriveredImgGreaterThanOrEqualTo(String value) {
            addCriterion("arrivered_img >=", value, "arriveredImg");
            return (Criteria) this;
        }

        public Criteria andArriveredImgLessThan(String value) {
            addCriterion("arrivered_img <", value, "arriveredImg");
            return (Criteria) this;
        }

        public Criteria andArriveredImgLessThanOrEqualTo(String value) {
            addCriterion("arrivered_img <=", value, "arriveredImg");
            return (Criteria) this;
        }

        public Criteria andArriveredImgLike(String value) {
            addCriterion("arrivered_img like", value, "arriveredImg");
            return (Criteria) this;
        }

        public Criteria andArriveredImgNotLike(String value) {
            addCriterion("arrivered_img not like", value, "arriveredImg");
            return (Criteria) this;
        }

        public Criteria andArriveredImgIn(List<String> values) {
            addCriterion("arrivered_img in", values, "arriveredImg");
            return (Criteria) this;
        }

        public Criteria andArriveredImgNotIn(List<String> values) {
            addCriterion("arrivered_img not in", values, "arriveredImg");
            return (Criteria) this;
        }

        public Criteria andArriveredImgBetween(String value1, String value2) {
            addCriterion("arrivered_img between", value1, value2, "arriveredImg");
            return (Criteria) this;
        }

        public Criteria andArriveredImgNotBetween(String value1, String value2) {
            addCriterion("arrivered_img not between", value1, value2, "arriveredImg");
            return (Criteria) this;
        }

        public Criteria andEditDateIsNull() {
            addCriterion("edit_date is null");
            return (Criteria) this;
        }

        public Criteria andEditDateIsNotNull() {
            addCriterion("edit_date is not null");
            return (Criteria) this;
        }

        public Criteria andEditDateEqualTo(Date value) {
            addCriterion("edit_date =", value, "editDate");
            return (Criteria) this;
        }

        public Criteria andEditDateNotEqualTo(Date value) {
            addCriterion("edit_date <>", value, "editDate");
            return (Criteria) this;
        }

        public Criteria andEditDateGreaterThan(Date value) {
            addCriterion("edit_date >", value, "editDate");
            return (Criteria) this;
        }

        public Criteria andEditDateGreaterThanOrEqualTo(Date value) {
            addCriterion("edit_date >=", value, "editDate");
            return (Criteria) this;
        }

        public Criteria andEditDateLessThan(Date value) {
            addCriterion("edit_date <", value, "editDate");
            return (Criteria) this;
        }

        public Criteria andEditDateLessThanOrEqualTo(Date value) {
            addCriterion("edit_date <=", value, "editDate");
            return (Criteria) this;
        }

        public Criteria andEditDateIn(List<Date> values) {
            addCriterion("edit_date in", values, "editDate");
            return (Criteria) this;
        }

        public Criteria andEditDateNotIn(List<Date> values) {
            addCriterion("edit_date not in", values, "editDate");
            return (Criteria) this;
        }

        public Criteria andEditDateBetween(Date value1, Date value2) {
            addCriterion("edit_date between", value1, value2, "editDate");
            return (Criteria) this;
        }

        public Criteria andEditDateNotBetween(Date value1, Date value2) {
            addCriterion("edit_date not between", value1, value2, "editDate");
            return (Criteria) this;
        }

        public Criteria andPieceNumberIsNull() {
            addCriterion("piece_number is null");
            return (Criteria) this;
        }

        public Criteria andPieceNumberIsNotNull() {
            addCriterion("piece_number is not null");
            return (Criteria) this;
        }

        public Criteria andPieceNumberEqualTo(Integer value) {
            addCriterion("piece_number =", value, "pieceNumber");
            return (Criteria) this;
        }

        public Criteria andPieceNumberNotEqualTo(Integer value) {
            addCriterion("piece_number <>", value, "pieceNumber");
            return (Criteria) this;
        }

        public Criteria andPieceNumberGreaterThan(Integer value) {
            addCriterion("piece_number >", value, "pieceNumber");
            return (Criteria) this;
        }

        public Criteria andPieceNumberGreaterThanOrEqualTo(Integer value) {
            addCriterion("piece_number >=", value, "pieceNumber");
            return (Criteria) this;
        }

        public Criteria andPieceNumberLessThan(Integer value) {
            addCriterion("piece_number <", value, "pieceNumber");
            return (Criteria) this;
        }

        public Criteria andPieceNumberLessThanOrEqualTo(Integer value) {
            addCriterion("piece_number <=", value, "pieceNumber");
            return (Criteria) this;
        }

        public Criteria andPieceNumberIn(List<Integer> values) {
            addCriterion("piece_number in", values, "pieceNumber");
            return (Criteria) this;
        }

        public Criteria andPieceNumberNotIn(List<Integer> values) {
            addCriterion("piece_number not in", values, "pieceNumber");
            return (Criteria) this;
        }

        public Criteria andPieceNumberBetween(Integer value1, Integer value2) {
            addCriterion("piece_number between", value1, value2, "pieceNumber");
            return (Criteria) this;
        }

        public Criteria andPieceNumberNotBetween(Integer value1, Integer value2) {
            addCriterion("piece_number not between", value1, value2, "pieceNumber");
            return (Criteria) this;
        }

        public Criteria andValuationUnitTypeIsNull() {
            addCriterion("valuation_unit_type is null");
            return (Criteria) this;
        }

        public Criteria andValuationUnitTypeIsNotNull() {
            addCriterion("valuation_unit_type is not null");
            return (Criteria) this;
        }

        public Criteria andValuationUnitTypeEqualTo(Byte value) {
            addCriterion("valuation_unit_type =", value, "valuationUnitType");
            return (Criteria) this;
        }

        public Criteria andValuationUnitTypeNotEqualTo(Byte value) {
            addCriterion("valuation_unit_type <>", value, "valuationUnitType");
            return (Criteria) this;
        }

        public Criteria andValuationUnitTypeGreaterThan(Byte value) {
            addCriterion("valuation_unit_type >", value, "valuationUnitType");
            return (Criteria) this;
        }

        public Criteria andValuationUnitTypeGreaterThanOrEqualTo(Byte value) {
            addCriterion("valuation_unit_type >=", value, "valuationUnitType");
            return (Criteria) this;
        }

        public Criteria andValuationUnitTypeLessThan(Byte value) {
            addCriterion("valuation_unit_type <", value, "valuationUnitType");
            return (Criteria) this;
        }

        public Criteria andValuationUnitTypeLessThanOrEqualTo(Byte value) {
            addCriterion("valuation_unit_type <=", value, "valuationUnitType");
            return (Criteria) this;
        }

        public Criteria andValuationUnitTypeIn(List<Byte> values) {
            addCriterion("valuation_unit_type in", values, "valuationUnitType");
            return (Criteria) this;
        }

        public Criteria andValuationUnitTypeNotIn(List<Byte> values) {
            addCriterion("valuation_unit_type not in", values, "valuationUnitType");
            return (Criteria) this;
        }

        public Criteria andValuationUnitTypeBetween(Byte value1, Byte value2) {
            addCriterion("valuation_unit_type between", value1, value2, "valuationUnitType");
            return (Criteria) this;
        }

        public Criteria andValuationUnitTypeNotBetween(Byte value1, Byte value2) {
            addCriterion("valuation_unit_type not between", value1, value2, "valuationUnitType");
            return (Criteria) this;
        }

        public Criteria andCarteamIdIsNull() {
            addCriterion("carTeam_id is null");
            return (Criteria) this;
        }

        public Criteria andCarteamIdIsNotNull() {
            addCriterion("carTeam_id is not null");
            return (Criteria) this;
        }

        public Criteria andCarteamIdEqualTo(Integer value) {
            addCriterion("carTeam_id =", value, "carteamId");
            return (Criteria) this;
        }

        public Criteria andCarteamIdNotEqualTo(Integer value) {
            addCriterion("carTeam_id <>", value, "carteamId");
            return (Criteria) this;
        }

        public Criteria andCarteamIdGreaterThan(Integer value) {
            addCriterion("carTeam_id >", value, "carteamId");
            return (Criteria) this;
        }

        public Criteria andCarteamIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("carTeam_id >=", value, "carteamId");
            return (Criteria) this;
        }

        public Criteria andCarteamIdLessThan(Integer value) {
            addCriterion("carTeam_id <", value, "carteamId");
            return (Criteria) this;
        }

        public Criteria andCarteamIdLessThanOrEqualTo(Integer value) {
            addCriterion("carTeam_id <=", value, "carteamId");
            return (Criteria) this;
        }

        public Criteria andCarteamIdIn(List<Integer> values) {
            addCriterion("carTeam_id in", values, "carteamId");
            return (Criteria) this;
        }

        public Criteria andCarteamIdNotIn(List<Integer> values) {
            addCriterion("carTeam_id not in", values, "carteamId");
            return (Criteria) this;
        }

        public Criteria andCarteamIdBetween(Integer value1, Integer value2) {
            addCriterion("carTeam_id between", value1, value2, "carteamId");
            return (Criteria) this;
        }

        public Criteria andCarteamIdNotBetween(Integer value1, Integer value2) {
            addCriterion("carTeam_id not between", value1, value2, "carteamId");
            return (Criteria) this;
        }

        public Criteria andPlaceNowIdIsNull() {
            addCriterion("place_now_id is null");
            return (Criteria) this;
        }

        public Criteria andPlaceNowIdIsNotNull() {
            addCriterion("place_now_id is not null");
            return (Criteria) this;
        }

        public Criteria andPlaceNowIdEqualTo(Integer value) {
            addCriterion("place_now_id =", value, "placeNowId");
            return (Criteria) this;
        }

        public Criteria andPlaceNowIdNotEqualTo(Integer value) {
            addCriterion("place_now_id <>", value, "placeNowId");
            return (Criteria) this;
        }

        public Criteria andPlaceNowIdGreaterThan(Integer value) {
            addCriterion("place_now_id >", value, "placeNowId");
            return (Criteria) this;
        }

        public Criteria andPlaceNowIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("place_now_id >=", value, "placeNowId");
            return (Criteria) this;
        }

        public Criteria andPlaceNowIdLessThan(Integer value) {
            addCriterion("place_now_id <", value, "placeNowId");
            return (Criteria) this;
        }

        public Criteria andPlaceNowIdLessThanOrEqualTo(Integer value) {
            addCriterion("place_now_id <=", value, "placeNowId");
            return (Criteria) this;
        }

        public Criteria andPlaceNowIdIn(List<Integer> values) {
            addCriterion("place_now_id in", values, "placeNowId");
            return (Criteria) this;
        }

        public Criteria andPlaceNowIdNotIn(List<Integer> values) {
            addCriterion("place_now_id not in", values, "placeNowId");
            return (Criteria) this;
        }

        public Criteria andPlaceNowIdBetween(Integer value1, Integer value2) {
            addCriterion("place_now_id between", value1, value2, "placeNowId");
            return (Criteria) this;
        }

        public Criteria andPlaceNowIdNotBetween(Integer value1, Integer value2) {
            addCriterion("place_now_id not between", value1, value2, "placeNowId");
            return (Criteria) this;
        }

        public Criteria andOrderOriginIsNull() {
            addCriterion("order_origin is null");
            return (Criteria) this;
        }

        public Criteria andOrderOriginIsNotNull() {
            addCriterion("order_origin is not null");
            return (Criteria) this;
        }

        public Criteria andOrderOriginEqualTo(Byte value) {
            addCriterion("order_origin =", value, "orderOrigin");
            return (Criteria) this;
        }

        public Criteria andOrderOriginNotEqualTo(Byte value) {
            addCriterion("order_origin <>", value, "orderOrigin");
            return (Criteria) this;
        }

        public Criteria andOrderOriginGreaterThan(Byte value) {
            addCriterion("order_origin >", value, "orderOrigin");
            return (Criteria) this;
        }

        public Criteria andOrderOriginGreaterThanOrEqualTo(Byte value) {
            addCriterion("order_origin >=", value, "orderOrigin");
            return (Criteria) this;
        }

        public Criteria andOrderOriginLessThan(Byte value) {
            addCriterion("order_origin <", value, "orderOrigin");
            return (Criteria) this;
        }

        public Criteria andOrderOriginLessThanOrEqualTo(Byte value) {
            addCriterion("order_origin <=", value, "orderOrigin");
            return (Criteria) this;
        }

        public Criteria andOrderOriginIn(List<Byte> values) {
            addCriterion("order_origin in", values, "orderOrigin");
            return (Criteria) this;
        }

        public Criteria andOrderOriginNotIn(List<Byte> values) {
            addCriterion("order_origin not in", values, "orderOrigin");
            return (Criteria) this;
        }

        public Criteria andOrderOriginBetween(Byte value1, Byte value2) {
            addCriterion("order_origin between", value1, value2, "orderOrigin");
            return (Criteria) this;
        }

        public Criteria andOrderOriginNotBetween(Byte value1, Byte value2) {
            addCriterion("order_origin not between", value1, value2, "orderOrigin");
            return (Criteria) this;
        }

        public Criteria andDeletorIdIsNull() {
            addCriterion("deletor_id is null");
            return (Criteria) this;
        }

        public Criteria andDeletorIdIsNotNull() {
            addCriterion("deletor_id is not null");
            return (Criteria) this;
        }

        public Criteria andDeletorIdEqualTo(Integer value) {
            addCriterion("deletor_id =", value, "deletorId");
            return (Criteria) this;
        }

        public Criteria andDeletorIdNotEqualTo(Integer value) {
            addCriterion("deletor_id <>", value, "deletorId");
            return (Criteria) this;
        }

        public Criteria andDeletorIdGreaterThan(Integer value) {
            addCriterion("deletor_id >", value, "deletorId");
            return (Criteria) this;
        }

        public Criteria andDeletorIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("deletor_id >=", value, "deletorId");
            return (Criteria) this;
        }

        public Criteria andDeletorIdLessThan(Integer value) {
            addCriterion("deletor_id <", value, "deletorId");
            return (Criteria) this;
        }

        public Criteria andDeletorIdLessThanOrEqualTo(Integer value) {
            addCriterion("deletor_id <=", value, "deletorId");
            return (Criteria) this;
        }

        public Criteria andDeletorIdIn(List<Integer> values) {
            addCriterion("deletor_id in", values, "deletorId");
            return (Criteria) this;
        }

        public Criteria andDeletorIdNotIn(List<Integer> values) {
            addCriterion("deletor_id not in", values, "deletorId");
            return (Criteria) this;
        }

        public Criteria andDeletorIdBetween(Integer value1, Integer value2) {
            addCriterion("deletor_id between", value1, value2, "deletorId");
            return (Criteria) this;
        }

        public Criteria andDeletorIdNotBetween(Integer value1, Integer value2) {
            addCriterion("deletor_id not between", value1, value2, "deletorId");
            return (Criteria) this;
        }

        public Criteria andDeleteNameIsNull() {
            addCriterion("delete_name is null");
            return (Criteria) this;
        }

        public Criteria andDeleteNameIsNotNull() {
            addCriterion("delete_name is not null");
            return (Criteria) this;
        }

        public Criteria andDeleteNameEqualTo(String value) {
            addCriterion("delete_name =", value, "deleteName");
            return (Criteria) this;
        }

        public Criteria andDeleteNameNotEqualTo(String value) {
            addCriterion("delete_name <>", value, "deleteName");
            return (Criteria) this;
        }

        public Criteria andDeleteNameGreaterThan(String value) {
            addCriterion("delete_name >", value, "deleteName");
            return (Criteria) this;
        }

        public Criteria andDeleteNameGreaterThanOrEqualTo(String value) {
            addCriterion("delete_name >=", value, "deleteName");
            return (Criteria) this;
        }

        public Criteria andDeleteNameLessThan(String value) {
            addCriterion("delete_name <", value, "deleteName");
            return (Criteria) this;
        }

        public Criteria andDeleteNameLessThanOrEqualTo(String value) {
            addCriterion("delete_name <=", value, "deleteName");
            return (Criteria) this;
        }

        public Criteria andDeleteNameLike(String value) {
            addCriterion("delete_name like", value, "deleteName");
            return (Criteria) this;
        }

        public Criteria andDeleteNameNotLike(String value) {
            addCriterion("delete_name not like", value, "deleteName");
            return (Criteria) this;
        }

        public Criteria andDeleteNameIn(List<String> values) {
            addCriterion("delete_name in", values, "deleteName");
            return (Criteria) this;
        }

        public Criteria andDeleteNameNotIn(List<String> values) {
            addCriterion("delete_name not in", values, "deleteName");
            return (Criteria) this;
        }

        public Criteria andDeleteNameBetween(String value1, String value2) {
            addCriterion("delete_name between", value1, value2, "deleteName");
            return (Criteria) this;
        }

        public Criteria andDeleteNameNotBetween(String value1, String value2) {
            addCriterion("delete_name not between", value1, value2, "deleteName");
            return (Criteria) this;
        }

        public Criteria andDeleteTimeIsNull() {
            addCriterion("delete_time is null");
            return (Criteria) this;
        }

        public Criteria andDeleteTimeIsNotNull() {
            addCriterion("delete_time is not null");
            return (Criteria) this;
        }

        public Criteria andDeleteTimeEqualTo(Date value) {
            addCriterion("delete_time =", value, "deleteTime");
            return (Criteria) this;
        }

        public Criteria andDeleteTimeNotEqualTo(Date value) {
            addCriterion("delete_time <>", value, "deleteTime");
            return (Criteria) this;
        }

        public Criteria andDeleteTimeGreaterThan(Date value) {
            addCriterion("delete_time >", value, "deleteTime");
            return (Criteria) this;
        }

        public Criteria andDeleteTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("delete_time >=", value, "deleteTime");
            return (Criteria) this;
        }

        public Criteria andDeleteTimeLessThan(Date value) {
            addCriterion("delete_time <", value, "deleteTime");
            return (Criteria) this;
        }

        public Criteria andDeleteTimeLessThanOrEqualTo(Date value) {
            addCriterion("delete_time <=", value, "deleteTime");
            return (Criteria) this;
        }

        public Criteria andDeleteTimeIn(List<Date> values) {
            addCriterion("delete_time in", values, "deleteTime");
            return (Criteria) this;
        }

        public Criteria andDeleteTimeNotIn(List<Date> values) {
            addCriterion("delete_time not in", values, "deleteTime");
            return (Criteria) this;
        }

        public Criteria andDeleteTimeBetween(Date value1, Date value2) {
            addCriterion("delete_time between", value1, value2, "deleteTime");
            return (Criteria) this;
        }

        public Criteria andDeleteTimeNotBetween(Date value1, Date value2) {
            addCriterion("delete_time not between", value1, value2, "deleteTime");
            return (Criteria) this;
        }

        public Criteria andDeleteReasonIsNull() {
            addCriterion("delete_reason is null");
            return (Criteria) this;
        }

        public Criteria andDeleteReasonIsNotNull() {
            addCriterion("delete_reason is not null");
            return (Criteria) this;
        }

        public Criteria andDeleteReasonEqualTo(String value) {
            addCriterion("delete_reason =", value, "deleteReason");
            return (Criteria) this;
        }

        public Criteria andDeleteReasonNotEqualTo(String value) {
            addCriterion("delete_reason <>", value, "deleteReason");
            return (Criteria) this;
        }

        public Criteria andDeleteReasonGreaterThan(String value) {
            addCriterion("delete_reason >", value, "deleteReason");
            return (Criteria) this;
        }

        public Criteria andDeleteReasonGreaterThanOrEqualTo(String value) {
            addCriterion("delete_reason >=", value, "deleteReason");
            return (Criteria) this;
        }

        public Criteria andDeleteReasonLessThan(String value) {
            addCriterion("delete_reason <", value, "deleteReason");
            return (Criteria) this;
        }

        public Criteria andDeleteReasonLessThanOrEqualTo(String value) {
            addCriterion("delete_reason <=", value, "deleteReason");
            return (Criteria) this;
        }

        public Criteria andDeleteReasonLike(String value) {
            addCriterion("delete_reason like", value, "deleteReason");
            return (Criteria) this;
        }

        public Criteria andDeleteReasonNotLike(String value) {
            addCriterion("delete_reason not like", value, "deleteReason");
            return (Criteria) this;
        }

        public Criteria andDeleteReasonIn(List<String> values) {
            addCriterion("delete_reason in", values, "deleteReason");
            return (Criteria) this;
        }

        public Criteria andDeleteReasonNotIn(List<String> values) {
            addCriterion("delete_reason not in", values, "deleteReason");
            return (Criteria) this;
        }

        public Criteria andDeleteReasonBetween(String value1, String value2) {
            addCriterion("delete_reason between", value1, value2, "deleteReason");
            return (Criteria) this;
        }

        public Criteria andDeleteReasonNotBetween(String value1, String value2) {
            addCriterion("delete_reason not between", value1, value2, "deleteReason");
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

        public Criteria andIsCancelIsNull() {
            addCriterion("is_cancel is null");
            return (Criteria) this;
        }

        public Criteria andIsCancelIsNotNull() {
            addCriterion("is_cancel is not null");
            return (Criteria) this;
        }

        public Criteria andIsCancelEqualTo(Byte value) {
            addCriterion("is_cancel =", value, "isCancel");
            return (Criteria) this;
        }

        public Criteria andIsCancelNotEqualTo(Byte value) {
            addCriterion("is_cancel <>", value, "isCancel");
            return (Criteria) this;
        }

        public Criteria andIsCancelGreaterThan(Byte value) {
            addCriterion("is_cancel >", value, "isCancel");
            return (Criteria) this;
        }

        public Criteria andIsCancelGreaterThanOrEqualTo(Byte value) {
            addCriterion("is_cancel >=", value, "isCancel");
            return (Criteria) this;
        }

        public Criteria andIsCancelLessThan(Byte value) {
            addCriterion("is_cancel <", value, "isCancel");
            return (Criteria) this;
        }

        public Criteria andIsCancelLessThanOrEqualTo(Byte value) {
            addCriterion("is_cancel <=", value, "isCancel");
            return (Criteria) this;
        }

        public Criteria andIsCancelIn(List<Byte> values) {
            addCriterion("is_cancel in", values, "isCancel");
            return (Criteria) this;
        }

        public Criteria andIsCancelNotIn(List<Byte> values) {
            addCriterion("is_cancel not in", values, "isCancel");
            return (Criteria) this;
        }

        public Criteria andIsCancelBetween(Byte value1, Byte value2) {
            addCriterion("is_cancel between", value1, value2, "isCancel");
            return (Criteria) this;
        }

        public Criteria andIsCancelNotBetween(Byte value1, Byte value2) {
            addCriterion("is_cancel not between", value1, value2, "isCancel");
            return (Criteria) this;
        }

        public Criteria andCancelReasonIsNull() {
            addCriterion("cancel_reason is null");
            return (Criteria) this;
        }

        public Criteria andCancelReasonIsNotNull() {
            addCriterion("cancel_reason is not null");
            return (Criteria) this;
        }

        public Criteria andCancelReasonEqualTo(String value) {
            addCriterion("cancel_reason =", value, "cancelReason");
            return (Criteria) this;
        }

        public Criteria andCancelReasonNotEqualTo(String value) {
            addCriterion("cancel_reason <>", value, "cancelReason");
            return (Criteria) this;
        }

        public Criteria andCancelReasonGreaterThan(String value) {
            addCriterion("cancel_reason >", value, "cancelReason");
            return (Criteria) this;
        }

        public Criteria andCancelReasonGreaterThanOrEqualTo(String value) {
            addCriterion("cancel_reason >=", value, "cancelReason");
            return (Criteria) this;
        }

        public Criteria andCancelReasonLessThan(String value) {
            addCriterion("cancel_reason <", value, "cancelReason");
            return (Criteria) this;
        }

        public Criteria andCancelReasonLessThanOrEqualTo(String value) {
            addCriterion("cancel_reason <=", value, "cancelReason");
            return (Criteria) this;
        }

        public Criteria andCancelReasonLike(String value) {
            addCriterion("cancel_reason like", value, "cancelReason");
            return (Criteria) this;
        }

        public Criteria andCancelReasonNotLike(String value) {
            addCriterion("cancel_reason not like", value, "cancelReason");
            return (Criteria) this;
        }

        public Criteria andCancelReasonIn(List<String> values) {
            addCriterion("cancel_reason in", values, "cancelReason");
            return (Criteria) this;
        }

        public Criteria andCancelReasonNotIn(List<String> values) {
            addCriterion("cancel_reason not in", values, "cancelReason");
            return (Criteria) this;
        }

        public Criteria andCancelReasonBetween(String value1, String value2) {
            addCriterion("cancel_reason between", value1, value2, "cancelReason");
            return (Criteria) this;
        }

        public Criteria andCancelReasonNotBetween(String value1, String value2) {
            addCriterion("cancel_reason not between", value1, value2, "cancelReason");
            return (Criteria) this;
        }

        public Criteria andCancelReasonDetailIsNull() {
            addCriterion("cancel_reason_detail is null");
            return (Criteria) this;
        }

        public Criteria andCancelReasonDetailIsNotNull() {
            addCriterion("cancel_reason_detail is not null");
            return (Criteria) this;
        }

        public Criteria andCancelReasonDetailEqualTo(String value) {
            addCriterion("cancel_reason_detail =", value, "cancelReasonDetail");
            return (Criteria) this;
        }

        public Criteria andCancelReasonDetailNotEqualTo(String value) {
            addCriterion("cancel_reason_detail <>", value, "cancelReasonDetail");
            return (Criteria) this;
        }

        public Criteria andCancelReasonDetailGreaterThan(String value) {
            addCriterion("cancel_reason_detail >", value, "cancelReasonDetail");
            return (Criteria) this;
        }

        public Criteria andCancelReasonDetailGreaterThanOrEqualTo(String value) {
            addCriterion("cancel_reason_detail >=", value, "cancelReasonDetail");
            return (Criteria) this;
        }

        public Criteria andCancelReasonDetailLessThan(String value) {
            addCriterion("cancel_reason_detail <", value, "cancelReasonDetail");
            return (Criteria) this;
        }

        public Criteria andCancelReasonDetailLessThanOrEqualTo(String value) {
            addCriterion("cancel_reason_detail <=", value, "cancelReasonDetail");
            return (Criteria) this;
        }

        public Criteria andCancelReasonDetailLike(String value) {
            addCriterion("cancel_reason_detail like", value, "cancelReasonDetail");
            return (Criteria) this;
        }

        public Criteria andCancelReasonDetailNotLike(String value) {
            addCriterion("cancel_reason_detail not like", value, "cancelReasonDetail");
            return (Criteria) this;
        }

        public Criteria andCancelReasonDetailIn(List<String> values) {
            addCriterion("cancel_reason_detail in", values, "cancelReasonDetail");
            return (Criteria) this;
        }

        public Criteria andCancelReasonDetailNotIn(List<String> values) {
            addCriterion("cancel_reason_detail not in", values, "cancelReasonDetail");
            return (Criteria) this;
        }

        public Criteria andCancelReasonDetailBetween(String value1, String value2) {
            addCriterion("cancel_reason_detail between", value1, value2, "cancelReasonDetail");
            return (Criteria) this;
        }

        public Criteria andCancelReasonDetailNotBetween(String value1, String value2) {
            addCriterion("cancel_reason_detail not between", value1, value2, "cancelReasonDetail");
            return (Criteria) this;
        }

        public Criteria andCancelDateIsNull() {
            addCriterion("cancel_date is null");
            return (Criteria) this;
        }

        public Criteria andCancelDateIsNotNull() {
            addCriterion("cancel_date is not null");
            return (Criteria) this;
        }

        public Criteria andCancelDateEqualTo(Date value) {
            addCriterion("cancel_date =", value, "cancelDate");
            return (Criteria) this;
        }

        public Criteria andCancelDateNotEqualTo(Date value) {
            addCriterion("cancel_date <>", value, "cancelDate");
            return (Criteria) this;
        }

        public Criteria andCancelDateGreaterThan(Date value) {
            addCriterion("cancel_date >", value, "cancelDate");
            return (Criteria) this;
        }

        public Criteria andCancelDateGreaterThanOrEqualTo(Date value) {
            addCriterion("cancel_date >=", value, "cancelDate");
            return (Criteria) this;
        }

        public Criteria andCancelDateLessThan(Date value) {
            addCriterion("cancel_date <", value, "cancelDate");
            return (Criteria) this;
        }

        public Criteria andCancelDateLessThanOrEqualTo(Date value) {
            addCriterion("cancel_date <=", value, "cancelDate");
            return (Criteria) this;
        }

        public Criteria andCancelDateIn(List<Date> values) {
            addCriterion("cancel_date in", values, "cancelDate");
            return (Criteria) this;
        }

        public Criteria andCancelDateNotIn(List<Date> values) {
            addCriterion("cancel_date not in", values, "cancelDate");
            return (Criteria) this;
        }

        public Criteria andCancelDateBetween(Date value1, Date value2) {
            addCriterion("cancel_date between", value1, value2, "cancelDate");
            return (Criteria) this;
        }

        public Criteria andCancelDateNotBetween(Date value1, Date value2) {
            addCriterion("cancel_date not between", value1, value2, "cancelDate");
            return (Criteria) this;
        }

        public Criteria andReceipterDateIsNull() {
            addCriterion("receipter_date is null");
            return (Criteria) this;
        }

        public Criteria andReceipterDateIsNotNull() {
            addCriterion("receipter_date is not null");
            return (Criteria) this;
        }

        public Criteria andReceipterDateEqualTo(String value) {
        	addCriterion("date_format(receipter_date, '%Y-%m-%d') =", value , "receipterDate");
            return (Criteria) this;
        }

        public Criteria andReceipterDateNotEqualTo(Date value) {
            addCriterion("receipter_date <>", value, "receipterDate");
            return (Criteria) this;
        }

        public Criteria andReceipterDateGreaterThan(Date value) {
            addCriterion("receipter_date >", value, "receipterDate");
            return (Criteria) this;
        }

        public Criteria andReceipterDateGreaterThanOrEqualTo(Date value) {
            addCriterion("receipter_date >=", value, "receipterDate");
            return (Criteria) this;
        }

        public Criteria andReceipterDateLessThan(Date value) {
            addCriterion("receipter_date <", value, "receipterDate");
            return (Criteria) this;
        }

        public Criteria andReceipterDateLessThanOrEqualTo(Date value) {
            addCriterion("receipter_date <=", value, "receipterDate");
            return (Criteria) this;
        }

        public Criteria andReceipterDateIn(List<Date> values) {
            addCriterion("receipter_date in", values, "receipterDate");
            return (Criteria) this;
        }

        public Criteria andReceipterDateNotIn(List<Date> values) {
            addCriterion("receipter_date not in", values, "receipterDate");
            return (Criteria) this;
        }

        public Criteria andReceipterDateBetween(Date value1, Date value2) {
            addCriterion("receipter_date between", value1, value2, "receipterDate");
            return (Criteria) this;
        }

        public Criteria andReceipterDateNotBetween(Date value1, Date value2) {
            addCriterion("receipter_date not between", value1, value2, "receipterDate");
            return (Criteria) this;
        }

        public Criteria andTakeCargoPlaceIdIsNull() {
            addCriterion("take_cargo_place_id is null");
            return (Criteria) this;
        }

        public Criteria andTakeCargoPlaceIdIsNotNull() {
            addCriterion("take_cargo_place_id is not null");
            return (Criteria) this;
        }

        public Criteria andTakeCargoPlaceIdEqualTo(Integer value) {
            addCriterion("take_cargo_place_id =", value, "takeCargoPlaceId");
            return (Criteria) this;
        }

        public Criteria andTakeCargoPlaceIdNotEqualTo(Integer value) {
            addCriterion("take_cargo_place_id <>", value, "takeCargoPlaceId");
            return (Criteria) this;
        }

        public Criteria andTakeCargoPlaceIdGreaterThan(Integer value) {
            addCriterion("take_cargo_place_id >", value, "takeCargoPlaceId");
            return (Criteria) this;
        }

        public Criteria andTakeCargoPlaceIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("take_cargo_place_id >=", value, "takeCargoPlaceId");
            return (Criteria) this;
        }

        public Criteria andTakeCargoPlaceIdLessThan(Integer value) {
            addCriterion("take_cargo_place_id <", value, "takeCargoPlaceId");
            return (Criteria) this;
        }

        public Criteria andTakeCargoPlaceIdLessThanOrEqualTo(Integer value) {
            addCriterion("take_cargo_place_id <=", value, "takeCargoPlaceId");
            return (Criteria) this;
        }

        public Criteria andTakeCargoPlaceIdIn(List<Integer> values) {
            addCriterion("take_cargo_place_id in", values, "takeCargoPlaceId");
            return (Criteria) this;
        }

        public Criteria andTakeCargoPlaceIdNotIn(List<Integer> values) {
            addCriterion("take_cargo_place_id not in", values, "takeCargoPlaceId");
            return (Criteria) this;
        }

        public Criteria andTakeCargoPlaceIdBetween(Integer value1, Integer value2) {
            addCriterion("take_cargo_place_id between", value1, value2, "takeCargoPlaceId");
            return (Criteria) this;
        }

        public Criteria andTakeCargoPlaceIdNotBetween(Integer value1, Integer value2) {
            addCriterion("take_cargo_place_id not between", value1, value2, "takeCargoPlaceId");
            return (Criteria) this;
        }

        public Criteria andTakeCarogoPlaceNameIsNull() {
            addCriterion("take_carogo_place_name is null");
            return (Criteria) this;
        }

        public Criteria andTakeCarogoPlaceNameIsNotNull() {
            addCriterion("take_carogo_place_name is not null");
            return (Criteria) this;
        }

        public Criteria andTakeCarogoPlaceNameEqualTo(String value) {
            addCriterion("take_carogo_place_name =", value, "takeCarogoPlaceName");
            return (Criteria) this;
        }

        public Criteria andTakeCarogoPlaceNameNotEqualTo(String value) {
            addCriterion("take_carogo_place_name <>", value, "takeCarogoPlaceName");
            return (Criteria) this;
        }

        public Criteria andTakeCarogoPlaceNameGreaterThan(String value) {
            addCriterion("take_carogo_place_name >", value, "takeCarogoPlaceName");
            return (Criteria) this;
        }

        public Criteria andTakeCarogoPlaceNameGreaterThanOrEqualTo(String value) {
            addCriterion("take_carogo_place_name >=", value, "takeCarogoPlaceName");
            return (Criteria) this;
        }

        public Criteria andTakeCarogoPlaceNameLessThan(String value) {
            addCriterion("take_carogo_place_name <", value, "takeCarogoPlaceName");
            return (Criteria) this;
        }

        public Criteria andTakeCarogoPlaceNameLessThanOrEqualTo(String value) {
            addCriterion("take_carogo_place_name <=", value, "takeCarogoPlaceName");
            return (Criteria) this;
        }

        public Criteria andTakeCarogoPlaceNameLike(String value) {
            addCriterion("take_carogo_place_name like", value, "takeCarogoPlaceName");
            return (Criteria) this;
        }

        public Criteria andTakeCarogoPlaceNameNotLike(String value) {
            addCriterion("take_carogo_place_name not like", value, "takeCarogoPlaceName");
            return (Criteria) this;
        }

        public Criteria andTakeCarogoPlaceNameIn(List<String> values) {
            addCriterion("take_carogo_place_name in", values, "takeCarogoPlaceName");
            return (Criteria) this;
        }

        public Criteria andTakeCarogoPlaceNameNotIn(List<String> values) {
            addCriterion("take_carogo_place_name not in", values, "takeCarogoPlaceName");
            return (Criteria) this;
        }

        public Criteria andTakeCarogoPlaceNameBetween(String value1, String value2) {
            addCriterion("take_carogo_place_name between", value1, value2, "takeCarogoPlaceName");
            return (Criteria) this;
        }

        public Criteria andTakeCarogoPlaceNameNotBetween(String value1, String value2) {
            addCriterion("take_carogo_place_name not between", value1, value2, "takeCarogoPlaceName");
            return (Criteria) this;
        }

        public Criteria andTakeCargoSiteIdIsNull() {
            addCriterion("take_cargo_site_id is null");
            return (Criteria) this;
        }

        public Criteria andTakeCargoSiteIdIsNotNull() {
            addCriterion("take_cargo_site_id is not null");
            return (Criteria) this;
        }

        public Criteria andTakeCargoSiteIdEqualTo(Integer value) {
            addCriterion("take_cargo_site_id =", value, "takeCargoSiteId");
            return (Criteria) this;
        }

        public Criteria andTakeCargoSiteIdNotEqualTo(Integer value) {
            addCriterion("take_cargo_site_id <>", value, "takeCargoSiteId");
            return (Criteria) this;
        }

        public Criteria andTakeCargoSiteIdGreaterThan(Integer value) {
            addCriterion("take_cargo_site_id >", value, "takeCargoSiteId");
            return (Criteria) this;
        }

        public Criteria andTakeCargoSiteIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("take_cargo_site_id >=", value, "takeCargoSiteId");
            return (Criteria) this;
        }

        public Criteria andTakeCargoSiteIdLessThan(Integer value) {
            addCriterion("take_cargo_site_id <", value, "takeCargoSiteId");
            return (Criteria) this;
        }

        public Criteria andTakeCargoSiteIdLessThanOrEqualTo(Integer value) {
            addCriterion("take_cargo_site_id <=", value, "takeCargoSiteId");
            return (Criteria) this;
        }

        public Criteria andTakeCargoSiteIdIn(List<Integer> values) {
            addCriterion("take_cargo_site_id in", values, "takeCargoSiteId");
            return (Criteria) this;
        }

        public Criteria andTakeCargoSiteIdNotIn(List<Integer> values) {
            addCriterion("take_cargo_site_id not in", values, "takeCargoSiteId");
            return (Criteria) this;
        }

        public Criteria andTakeCargoSiteIdBetween(Integer value1, Integer value2) {
            addCriterion("take_cargo_site_id between", value1, value2, "takeCargoSiteId");
            return (Criteria) this;
        }

        public Criteria andTakeCargoSiteIdNotBetween(Integer value1, Integer value2) {
            addCriterion("take_cargo_site_id not between", value1, value2, "takeCargoSiteId");
            return (Criteria) this;
        }

        public Criteria andTakeCargoSiteNameIsNull() {
            addCriterion("take_cargo_site_name is null");
            return (Criteria) this;
        }

        public Criteria andTakeCargoSiteNameIsNotNull() {
            addCriterion("take_cargo_site_name is not null");
            return (Criteria) this;
        }

        public Criteria andTakeCargoSiteNameEqualTo(String value) {
            addCriterion("take_cargo_site_name =", value, "takeCargoSiteName");
            return (Criteria) this;
        }

        public Criteria andTakeCargoSiteNameNotEqualTo(String value) {
            addCriterion("take_cargo_site_name <>", value, "takeCargoSiteName");
            return (Criteria) this;
        }

        public Criteria andTakeCargoSiteNameGreaterThan(String value) {
            addCriterion("take_cargo_site_name >", value, "takeCargoSiteName");
            return (Criteria) this;
        }

        public Criteria andTakeCargoSiteNameGreaterThanOrEqualTo(String value) {
            addCriterion("take_cargo_site_name >=", value, "takeCargoSiteName");
            return (Criteria) this;
        }

        public Criteria andTakeCargoSiteNameLessThan(String value) {
            addCriterion("take_cargo_site_name <", value, "takeCargoSiteName");
            return (Criteria) this;
        }

        public Criteria andTakeCargoSiteNameLessThanOrEqualTo(String value) {
            addCriterion("take_cargo_site_name <=", value, "takeCargoSiteName");
            return (Criteria) this;
        }

        public Criteria andTakeCargoSiteNameLike(String value) {
            addCriterion("take_cargo_site_name like", value, "takeCargoSiteName");
            return (Criteria) this;
        }

        public Criteria andTakeCargoSiteNameNotLike(String value) {
            addCriterion("take_cargo_site_name not like", value, "takeCargoSiteName");
            return (Criteria) this;
        }

        public Criteria andTakeCargoSiteNameIn(List<String> values) {
            addCriterion("take_cargo_site_name in", values, "takeCargoSiteName");
            return (Criteria) this;
        }

        public Criteria andTakeCargoSiteNameNotIn(List<String> values) {
            addCriterion("take_cargo_site_name not in", values, "takeCargoSiteName");
            return (Criteria) this;
        }

        public Criteria andTakeCargoSiteNameBetween(String value1, String value2) {
            addCriterion("take_cargo_site_name between", value1, value2, "takeCargoSiteName");
            return (Criteria) this;
        }

        public Criteria andTakeCargoSiteNameNotBetween(String value1, String value2) {
            addCriterion("take_cargo_site_name not between", value1, value2, "takeCargoSiteName");
            return (Criteria) this;
        }

        public Criteria andTakeDeliveryDateIsNull() {
            addCriterion("take_delivery_date is null");
            return (Criteria) this;
        }

        public Criteria andTakeDeliveryDateIsNotNull() {
            addCriterion("take_delivery_date is not null");
            return (Criteria) this;
        }

        public Criteria andTakeDeliveryDateEqualTo(Date value) {
            addCriterion("take_delivery_date =", value, "takeDeliveryDate");
            return (Criteria) this;
        }

        public Criteria andTakeDeliveryDateNotEqualTo(Date value) {
            addCriterion("take_delivery_date <>", value, "takeDeliveryDate");
            return (Criteria) this;
        }

        public Criteria andTakeDeliveryDateGreaterThan(Date value) {
            addCriterion("take_delivery_date >", value, "takeDeliveryDate");
            return (Criteria) this;
        }

        public Criteria andTakeDeliveryDateGreaterThanOrEqualTo(Date value) {
            addCriterion("take_delivery_date >=", value, "takeDeliveryDate");
            return (Criteria) this;
        }

        public Criteria andTakeDeliveryDateLessThan(Date value) {
            addCriterion("take_delivery_date <", value, "takeDeliveryDate");
            return (Criteria) this;
        }

        public Criteria andTakeDeliveryDateLessThanOrEqualTo(Date value) {
            addCriterion("take_delivery_date <=", value, "takeDeliveryDate");
            return (Criteria) this;
        }

        public Criteria andTakeDeliveryDateIn(List<Date> values) {
            addCriterion("take_delivery_date in", values, "takeDeliveryDate");
            return (Criteria) this;
        }

        public Criteria andTakeDeliveryDateNotIn(List<Date> values) {
            addCriterion("take_delivery_date not in", values, "takeDeliveryDate");
            return (Criteria) this;
        }

        public Criteria andTakeDeliveryDateBetween(Date value1, Date value2) {
            addCriterion("take_delivery_date between", value1, value2, "takeDeliveryDate");
            return (Criteria) this;
        }

        public Criteria andTakeDeliveryDateNotBetween(Date value1, Date value2) {
            addCriterion("take_delivery_date not between", value1, value2, "takeDeliveryDate");
            return (Criteria) this;
        }

        public Criteria andDistributionCargoPlaceIdIsNull() {
            addCriterion("distribution_cargo_place_id is null");
            return (Criteria) this;
        }

        public Criteria andDistributionCargoPlaceIdIsNotNull() {
            addCriterion("distribution_cargo_place_id is not null");
            return (Criteria) this;
        }

        public Criteria andDistributionCargoPlaceIdEqualTo(Integer value) {
            addCriterion("distribution_cargo_place_id =", value, "distributionCargoPlaceId");
            return (Criteria) this;
        }

        public Criteria andDistributionCargoPlaceIdNotEqualTo(Integer value) {
            addCriterion("distribution_cargo_place_id <>", value, "distributionCargoPlaceId");
            return (Criteria) this;
        }

        public Criteria andDistributionCargoPlaceIdGreaterThan(Integer value) {
            addCriterion("distribution_cargo_place_id >", value, "distributionCargoPlaceId");
            return (Criteria) this;
        }

        public Criteria andDistributionCargoPlaceIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("distribution_cargo_place_id >=", value, "distributionCargoPlaceId");
            return (Criteria) this;
        }

        public Criteria andDistributionCargoPlaceIdLessThan(Integer value) {
            addCriterion("distribution_cargo_place_id <", value, "distributionCargoPlaceId");
            return (Criteria) this;
        }

        public Criteria andDistributionCargoPlaceIdLessThanOrEqualTo(Integer value) {
            addCriterion("distribution_cargo_place_id <=", value, "distributionCargoPlaceId");
            return (Criteria) this;
        }

        public Criteria andDistributionCargoPlaceIdIn(List<Integer> values) {
            addCriterion("distribution_cargo_place_id in", values, "distributionCargoPlaceId");
            return (Criteria) this;
        }

        public Criteria andDistributionCargoPlaceIdNotIn(List<Integer> values) {
            addCriterion("distribution_cargo_place_id not in", values, "distributionCargoPlaceId");
            return (Criteria) this;
        }

        public Criteria andDistributionCargoPlaceIdBetween(Integer value1, Integer value2) {
            addCriterion("distribution_cargo_place_id between", value1, value2, "distributionCargoPlaceId");
            return (Criteria) this;
        }

        public Criteria andDistributionCargoPlaceIdNotBetween(Integer value1, Integer value2) {
            addCriterion("distribution_cargo_place_id not between", value1, value2, "distributionCargoPlaceId");
            return (Criteria) this;
        }

        public Criteria andDistributionCargoSiteIdIsNull() {
            addCriterion("distribution_cargo_site_id is null");
            return (Criteria) this;
        }

        public Criteria andDistributionCargoSiteIdIsNotNull() {
            addCriterion("distribution_cargo_site_id is not null");
            return (Criteria) this;
        }

        public Criteria andDistributionCargoSiteIdEqualTo(Integer value) {
            addCriterion("distribution_cargo_site_id =", value, "distributionCargoSiteId");
            return (Criteria) this;
        }

        public Criteria andDistributionCargoSiteIdNotEqualTo(Integer value) {
            addCriterion("distribution_cargo_site_id <>", value, "distributionCargoSiteId");
            return (Criteria) this;
        }

        public Criteria andDistributionCargoSiteIdGreaterThan(Integer value) {
            addCriterion("distribution_cargo_site_id >", value, "distributionCargoSiteId");
            return (Criteria) this;
        }

        public Criteria andDistributionCargoSiteIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("distribution_cargo_site_id >=", value, "distributionCargoSiteId");
            return (Criteria) this;
        }

        public Criteria andDistributionCargoSiteIdLessThan(Integer value) {
            addCriterion("distribution_cargo_site_id <", value, "distributionCargoSiteId");
            return (Criteria) this;
        }

        public Criteria andDistributionCargoSiteIdLessThanOrEqualTo(Integer value) {
            addCriterion("distribution_cargo_site_id <=", value, "distributionCargoSiteId");
            return (Criteria) this;
        }

        public Criteria andDistributionCargoSiteIdIn(List<Integer> values) {
            addCriterion("distribution_cargo_site_id in", values, "distributionCargoSiteId");
            return (Criteria) this;
        }

        public Criteria andDistributionCargoSiteIdNotIn(List<Integer> values) {
            addCriterion("distribution_cargo_site_id not in", values, "distributionCargoSiteId");
            return (Criteria) this;
        }

        public Criteria andDistributionCargoSiteIdBetween(Integer value1, Integer value2) {
            addCriterion("distribution_cargo_site_id between", value1, value2, "distributionCargoSiteId");
            return (Criteria) this;
        }

        public Criteria andDistributionCargoSiteIdNotBetween(Integer value1, Integer value2) {
            addCriterion("distribution_cargo_site_id not between", value1, value2, "distributionCargoSiteId");
            return (Criteria) this;
        }
        
        public Criteria andSysOrgCodeEqualTo(String sysOrgCode) {
            addCriterion("y.sys_org_code like ",sysOrgCode+"%", "sysOrgCode");
            return (Criteria) this;
        }
        
        public Criteria andTabNameEqualTo(String tabName) {
            addCriterion("y.tab_name=",tabName, "tabName");
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