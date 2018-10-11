package com.shenhesoft.logistics.manage.pojo.project.distribution;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.shenhesoft.logistics.manage.pojo.project.distribution.TbProjectDistributionExample.Criteria;
import com.shenhesoft.logistics.manage.pojo.project.distribution.TbProjectDistributionExample.Criterion;
import com.shenhesoft.logistics.manage.pojo.project.distribution.TbProjectDistributionExample.GeneratedCriteria;

public class TbProjectDistributionExample {
	 protected String orderByClause;

	    protected boolean distinct;

	    protected List<Criteria> oredCriteria;

	    public TbProjectDistributionExample() {
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

	        public Criteria andReceiveCompanyIdIsNull() {
	            addCriterion("receive_company_id is null");
	            return (Criteria) this;
	        }

	        public Criteria andReceiveCompanyIdIsNotNull() {
	            addCriterion("receive_company_id is not null");
	            return (Criteria) this;
	        }

	        public Criteria andReceiveCompanyIdEqualTo(Integer value) {
	            addCriterion("receive_company_id =", value, "receiveCompanyId");
	            return (Criteria) this;
	        }

	        public Criteria andReceiveCompanyIdNotEqualTo(Integer value) {
	            addCriterion("receive_company_id <>", value, "receiveCompanyId");
	            return (Criteria) this;
	        }

	        public Criteria andReceiveCompanyIdGreaterThan(Integer value) {
	            addCriterion("receive_company_id >", value, "receiveCompanyId");
	            return (Criteria) this;
	        }

	        public Criteria andReceiveCompanyIdGreaterThanOrEqualTo(Integer value) {
	            addCriterion("receive_company_id >=", value, "receiveCompanyId");
	            return (Criteria) this;
	        }

	        public Criteria andReceiveCompanyIdLessThan(Integer value) {
	            addCriterion("receive_company_id <", value, "receiveCompanyId");
	            return (Criteria) this;
	        }

	        public Criteria andReceiveCompanyIdLessThanOrEqualTo(Integer value) {
	            addCriterion("receive_company_id <=", value, "receiveCompanyId");
	            return (Criteria) this;
	        }

	        public Criteria andReceiveCompanyIdIn(List<Integer> values) {
	            addCriterion("receive_company_id in", values, "receiveCompanyId");
	            return (Criteria) this;
	        }

	        public Criteria andReceiveCompanyIdNotIn(List<Integer> values) {
	            addCriterion("receive_company_id not in", values, "receiveCompanyId");
	            return (Criteria) this;
	        }

	        public Criteria andReceiveCompanyIdBetween(Integer value1, Integer value2) {
	            addCriterion("receive_company_id between", value1, value2, "receiveCompanyId");
	            return (Criteria) this;
	        }

	        public Criteria andReceiveCompanyIdNotBetween(Integer value1, Integer value2) {
	            addCriterion("receive_company_id not between", value1, value2, "receiveCompanyId");
	            return (Criteria) this;
	        }

	        public Criteria andCarNumIsNull() {
	            addCriterion("car_num is null");
	            return (Criteria) this;
	        }

	        public Criteria andCarNumIsNotNull() {
	            addCriterion("car_num is not null");
	            return (Criteria) this;
	        }

	        public Criteria andCarNumEqualTo(Integer value) {
	            addCriterion("car_num =", value, "carNum");
	            return (Criteria) this;
	        }

	        public Criteria andCarNumNotEqualTo(Integer value) {
	            addCriterion("car_num <>", value, "carNum");
	            return (Criteria) this;
	        }

	        public Criteria andCarNumGreaterThan(Integer value) {
	            addCriterion("car_num >", value, "carNum");
	            return (Criteria) this;
	        }

	        public Criteria andCarNumGreaterThanOrEqualTo(Integer value) {
	            addCriterion("car_num >=", value, "carNum");
	            return (Criteria) this;
	        }

	        public Criteria andCarNumLessThan(Integer value) {
	            addCriterion("car_num <", value, "carNum");
	            return (Criteria) this;
	        }

	        public Criteria andCarNumLessThanOrEqualTo(Integer value) {
	            addCriterion("car_num <=", value, "carNum");
	            return (Criteria) this;
	        }

	        public Criteria andCarNumIn(List<Integer> values) {
	            addCriterion("car_num in", values, "carNum");
	            return (Criteria) this;
	        }

	        public Criteria andCarNumNotIn(List<Integer> values) {
	            addCriterion("car_num not in", values, "carNum");
	            return (Criteria) this;
	        }

	        public Criteria andCarNumBetween(Integer value1, Integer value2) {
	            addCriterion("car_num between", value1, value2, "carNum");
	            return (Criteria) this;
	        }

	        public Criteria andCarNumNotBetween(Integer value1, Integer value2) {
	            addCriterion("car_num not between", value1, value2, "carNum");
	            return (Criteria) this;
	        }

	        public Criteria andSingleWeightIsNull() {
	            addCriterion("single_weight is null");
	            return (Criteria) this;
	        }

	        public Criteria andSingleWeightIsNotNull() {
	            addCriterion("single_weight is not null");
	            return (Criteria) this;
	        }

	        public Criteria andSingleWeightEqualTo(BigDecimal value) {
	            addCriterion("single_weight =", value, "singleWeight");
	            return (Criteria) this;
	        }

	        public Criteria andSingleWeightNotEqualTo(BigDecimal value) {
	            addCriterion("single_weight <>", value, "singleWeight");
	            return (Criteria) this;
	        }

	        public Criteria andSingleWeightGreaterThan(BigDecimal value) {
	            addCriterion("single_weight >", value, "singleWeight");
	            return (Criteria) this;
	        }

	        public Criteria andSingleWeightGreaterThanOrEqualTo(BigDecimal value) {
	            addCriterion("single_weight >=", value, "singleWeight");
	            return (Criteria) this;
	        }

	        public Criteria andSingleWeightLessThan(BigDecimal value) {
	            addCriterion("single_weight <", value, "singleWeight");
	            return (Criteria) this;
	        }

	        public Criteria andSingleWeightLessThanOrEqualTo(BigDecimal value) {
	            addCriterion("single_weight <=", value, "singleWeight");
	            return (Criteria) this;
	        }

	        public Criteria andSingleWeightIn(List<BigDecimal> values) {
	            addCriterion("single_weight in", values, "singleWeight");
	            return (Criteria) this;
	        }

	        public Criteria andSingleWeightNotIn(List<BigDecimal> values) {
	            addCriterion("single_weight not in", values, "singleWeight");
	            return (Criteria) this;
	        }

	        public Criteria andSingleWeightBetween(BigDecimal value1, BigDecimal value2) {
	            addCriterion("single_weight between", value1, value2, "singleWeight");
	            return (Criteria) this;
	        }

	        public Criteria andSingleWeightNotBetween(BigDecimal value1, BigDecimal value2) {
	            addCriterion("single_weight not between", value1, value2, "singleWeight");
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

	        public Criteria andExpireDateIsNull() {
	            addCriterion("expire_date is null");
	            return (Criteria) this;
	        }

	        public Criteria andExpireDateIsNotNull() {
	            addCriterion("expire_date is not null");
	            return (Criteria) this;
	        }

	        public Criteria andExpireDateEqualTo(Date value) {
	            addCriterion("expire_date =", value, "expireDate");
	            return (Criteria) this;
	        }

	        public Criteria andExpireDateNotEqualTo(Date value) {
	            addCriterion("expire_date <>", value, "expireDate");
	            return (Criteria) this;
	        }

	        public Criteria andExpireDateGreaterThan(Date value) {
	            addCriterion("expire_date >", value, "expireDate");
	            return (Criteria) this;
	        }

	        public Criteria andExpireDateGreaterThanOrEqualTo(Date value) {
	            addCriterion("expire_date >=", value, "expireDate");
	            return (Criteria) this;
	        }

	        public Criteria andExpireDateLessThan(Date value) {
	            addCriterion("expire_date <", value, "expireDate");
	            return (Criteria) this;
	        }

	        public Criteria andExpireDateLessThanOrEqualTo(Date value) {
	            addCriterion("expire_date <=", value, "expireDate");
	            return (Criteria) this;
	        }

	        public Criteria andExpireDateIn(List<Date> values) {
	            addCriterion("expire_date in", values, "expireDate");
	            return (Criteria) this;
	        }

	        public Criteria andExpireDateNotIn(List<Date> values) {
	            addCriterion("expire_date not in", values, "expireDate");
	            return (Criteria) this;
	        }

	        public Criteria andExpireDateBetween(Date value1, Date value2) {
	            addCriterion("expire_date between", value1, value2, "expireDate");
	            return (Criteria) this;
	        }

	        public Criteria andExpireDateNotBetween(Date value1, Date value2) {
	            addCriterion("expire_date not between", value1, value2, "expireDate");
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

	        public Criteria andAlreadyRecNumIsNull() {
	            addCriterion("already_rec_num is null");
	            return (Criteria) this;
	        }

	        public Criteria andAlreadyRecNumIsNotNull() {
	            addCriterion("already_rec_num is not null");
	            return (Criteria) this;
	        }

	        public Criteria andAlreadyRecNumEqualTo(Integer value) {
	            addCriterion("already_rec_num =", value, "alreadyRecNum");
	            return (Criteria) this;
	        }

	        public Criteria andAlreadyRecNumNotEqualTo(Integer value) {
	            addCriterion("already_rec_num <>", value, "alreadyRecNum");
	            return (Criteria) this;
	        }

	        public Criteria andAlreadyRecNumGreaterThan(Integer value) {
	            addCriterion("already_rec_num >", value, "alreadyRecNum");
	            return (Criteria) this;
	        }

	        public Criteria andAlreadyRecNumGreaterThanOrEqualTo(Integer value) {
	            addCriterion("already_rec_num >=", value, "alreadyRecNum");
	            return (Criteria) this;
	        }

	        public Criteria andAlreadyRecNumLessThan(Integer value) {
	            addCriterion("already_rec_num <", value, "alreadyRecNum");
	            return (Criteria) this;
	        }

	        public Criteria andAlreadyRecNumLessThanOrEqualTo(Integer value) {
	            addCriterion("already_rec_num <=", value, "alreadyRecNum");
	            return (Criteria) this;
	        }

	        public Criteria andAlreadyRecNumIn(List<Integer> values) {
	            addCriterion("already_rec_num in", values, "alreadyRecNum");
	            return (Criteria) this;
	        }

	        public Criteria andAlreadyRecNumNotIn(List<Integer> values) {
	            addCriterion("already_rec_num not in", values, "alreadyRecNum");
	            return (Criteria) this;
	        }

	        public Criteria andAlreadyRecNumBetween(Integer value1, Integer value2) {
	            addCriterion("already_rec_num between", value1, value2, "alreadyRecNum");
	            return (Criteria) this;
	        }

	        public Criteria andAlreadyRecNumNotBetween(Integer value1, Integer value2) {
	            addCriterion("already_rec_num not between", value1, value2, "alreadyRecNum");
	            return (Criteria) this;
	        }

	        public Criteria andCompleteNumIsNull() {
	            addCriterion("complete_num is null");
	            return (Criteria) this;
	        }

	        public Criteria andCompleteNumIsNotNull() {
	            addCriterion("complete_num is not null");
	            return (Criteria) this;
	        }

	        public Criteria andCompleteNumEqualTo(Integer value) {
	            addCriterion("complete_num =", value, "completeNum");
	            return (Criteria) this;
	        }

	        public Criteria andCompleteNumNotEqualTo(Integer value) {
	            addCriterion("complete_num <>", value, "completeNum");
	            return (Criteria) this;
	        }

	        public Criteria andCompleteNumGreaterThan(Integer value) {
	            addCriterion("complete_num >", value, "completeNum");
	            return (Criteria) this;
	        }

	        public Criteria andCompleteNumGreaterThanOrEqualTo(Integer value) {
	            addCriterion("complete_num >=", value, "completeNum");
	            return (Criteria) this;
	        }

	        public Criteria andCompleteNumLessThan(Integer value) {
	            addCriterion("complete_num <", value, "completeNum");
	            return (Criteria) this;
	        }

	        public Criteria andCompleteNumLessThanOrEqualTo(Integer value) {
	            addCriterion("complete_num <=", value, "completeNum");
	            return (Criteria) this;
	        }

	        public Criteria andCompleteNumIn(List<Integer> values) {
	            addCriterion("complete_num in", values, "completeNum");
	            return (Criteria) this;
	        }

	        public Criteria andCompleteNumNotIn(List<Integer> values) {
	            addCriterion("complete_num not in", values, "completeNum");
	            return (Criteria) this;
	        }

	        public Criteria andCompleteNumBetween(Integer value1, Integer value2) {
	            addCriterion("complete_num between", value1, value2, "completeNum");
	            return (Criteria) this;
	        }

	        public Criteria andCompleteNumNotBetween(Integer value1, Integer value2) {
	            addCriterion("complete_num not between", value1, value2, "completeNum");
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

	        public Criteria andOverFlagIsNull() {
	            addCriterion("over_flag is null");
	            return (Criteria) this;
	        }

	        public Criteria andOverFlagIsNotNull() {
	            addCriterion("over_flag is not null");
	            return (Criteria) this;
	        }

	        public Criteria andOverFlagEqualTo(Byte value) {
	            addCriterion("over_flag =", value, "overFlag");
	            return (Criteria) this;
	        }

	        public Criteria andOverFlagNotEqualTo(Byte value) {
	            addCriterion("over_flag <>", value, "overFlag");
	            return (Criteria) this;
	        }

	        public Criteria andOverFlagGreaterThan(Byte value) {
	            addCriterion("over_flag >", value, "overFlag");
	            return (Criteria) this;
	        }

	        public Criteria andOverFlagGreaterThanOrEqualTo(Byte value) {
	            addCriterion("over_flag >=", value, "overFlag");
	            return (Criteria) this;
	        }

	        public Criteria andOverFlagLessThan(Byte value) {
	            addCriterion("over_flag <", value, "overFlag");
	            return (Criteria) this;
	        }

	        public Criteria andOverFlagLessThanOrEqualTo(Byte value) {
	            addCriterion("over_flag <=", value, "overFlag");
	            return (Criteria) this;
	        }

	        public Criteria andOverFlagIn(List<Byte> values) {
	            addCriterion("over_flag in", values, "overFlag");
	            return (Criteria) this;
	        }

	        public Criteria andOverFlagNotIn(List<Byte> values) {
	            addCriterion("over_flag not in", values, "overFlag");
	            return (Criteria) this;
	        }

	        public Criteria andOverFlagBetween(Byte value1, Byte value2) {
	            addCriterion("over_flag between", value1, value2, "overFlag");
	            return (Criteria) this;
	        }

	        public Criteria andOverFlagNotBetween(Byte value1, Byte value2) {
	            addCriterion("over_flag not between", value1, value2, "overFlag");
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