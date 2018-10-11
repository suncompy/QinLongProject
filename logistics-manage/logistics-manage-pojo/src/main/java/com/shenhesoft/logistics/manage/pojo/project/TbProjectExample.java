package com.shenhesoft.logistics.manage.pojo.project;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TbProjectExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TbProjectExample() {
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

        
        public Criteria andADeleteFlagEqualTo(byte value) {
            addCriterion("A.delete_flag =", value, "delete_flag");
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

        public Criteria andBranchGroupIdIsNull() {
            addCriterion("branch_group_id is null");
            return (Criteria) this;
        }

        public Criteria andBranchGroupIdIsNotNull() {
            addCriterion("branch_group_id is not null");
            return (Criteria) this;
        }

        public Criteria andBranchGroupIdEqualTo(Integer value) {
            addCriterion("branch_group_id =", value, "branchGroupId");
            return (Criteria) this;
        }

        public Criteria andBranchGroupIdNotEqualTo(Integer value) {
            addCriterion("branch_group_id <>", value, "branchGroupId");
            return (Criteria) this;
        }

        public Criteria andBranchGroupIdGreaterThan(Integer value) {
            addCriterion("branch_group_id >", value, "branchGroupId");
            return (Criteria) this;
        }

        public Criteria andBranchGroupIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("branch_group_id >=", value, "branchGroupId");
            return (Criteria) this;
        }

        public Criteria andBranchGroupIdLessThan(Integer value) {
            addCriterion("branch_group_id <", value, "branchGroupId");
            return (Criteria) this;
        }

        public Criteria andBranchGroupIdLessThanOrEqualTo(Integer value) {
            addCriterion("branch_group_id <=", value, "branchGroupId");
            return (Criteria) this;
        }

        public Criteria andBranchGroupIdIn(List<Integer> values) {
            addCriterion("branch_group_id in", values, "branchGroupId");
            return (Criteria) this;
        }

        public Criteria andBranchGroupIdNotIn(List<Integer> values) {
            addCriterion("branch_group_id not in", values, "branchGroupId");
            return (Criteria) this;
        }

        public Criteria andBranchGroupIdBetween(Integer value1, Integer value2) {
            addCriterion("branch_group_id between", value1, value2, "branchGroupId");
            return (Criteria) this;
        }

        public Criteria andBranchGroupIdNotBetween(Integer value1, Integer value2) {
            addCriterion("branch_group_id not between", value1, value2, "branchGroupId");
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
        
      /*  public Criteria andTaskTypeEqualTo(Byte value) {
            addCriterion("taskType =", value, "taskType");
            return (Criteria) this;
        }*/

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

        public Criteria andCargoIdIsNull() {
            addCriterion("cargo_id is null");
            return (Criteria) this;
        }

        public Criteria andCargoIdIsNotNull() {
            addCriterion("cargo_id is not null");
            return (Criteria) this;
        }

        public Criteria andCargoIdEqualTo(Integer value) {
            addCriterion("cargo_id =", value, "cargoId");
            return (Criteria) this;
        }

        public Criteria andCargoIdNotEqualTo(Integer value) {
            addCriterion("cargo_id <>", value, "cargoId");
            return (Criteria) this;
        }

        public Criteria andCargoIdGreaterThan(Integer value) {
            addCriterion("cargo_id >", value, "cargoId");
            return (Criteria) this;
        }

        public Criteria andCargoIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("cargo_id >=", value, "cargoId");
            return (Criteria) this;
        }

        public Criteria andCargoIdLessThan(Integer value) {
            addCriterion("cargo_id <", value, "cargoId");
            return (Criteria) this;
        }

        public Criteria andCargoIdLessThanOrEqualTo(Integer value) {
            addCriterion("cargo_id <=", value, "cargoId");
            return (Criteria) this;
        }

        public Criteria andCargoIdIn(List<Integer> values) {
            addCriterion("cargo_id in", values, "cargoId");
            return (Criteria) this;
        }

        public Criteria andCargoIdNotIn(List<Integer> values) {
            addCriterion("cargo_id not in", values, "cargoId");
            return (Criteria) this;
        }

        public Criteria andCargoIdBetween(Integer value1, Integer value2) {
            addCriterion("cargo_id between", value1, value2, "cargoId");
            return (Criteria) this;
        }

        public Criteria andCargoIdNotBetween(Integer value1, Integer value2) {
            addCriterion("cargo_id not between", value1, value2, "cargoId");
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

        public Criteria andCargoSpecificationsIsNull() {
            addCriterion("cargo_specifications is null");
            return (Criteria) this;
        }

        public Criteria andCargoSpecificationsIsNotNull() {
            addCriterion("cargo_specifications is not null");
            return (Criteria) this;
        }

        public Criteria andCargoSpecificationsEqualTo(String value) {
            addCriterion("cargo_specifications =", value, "cargoSpecifications");
            return (Criteria) this;
        }

        public Criteria andCargoSpecificationsNotEqualTo(String value) {
            addCriterion("cargo_specifications <>", value, "cargoSpecifications");
            return (Criteria) this;
        }

        public Criteria andCargoSpecificationsGreaterThan(String value) {
            addCriterion("cargo_specifications >", value, "cargoSpecifications");
            return (Criteria) this;
        }

        public Criteria andCargoSpecificationsGreaterThanOrEqualTo(String value) {
            addCriterion("cargo_specifications >=", value, "cargoSpecifications");
            return (Criteria) this;
        }

        public Criteria andCargoSpecificationsLessThan(String value) {
            addCriterion("cargo_specifications <", value, "cargoSpecifications");
            return (Criteria) this;
        }

        public Criteria andCargoSpecificationsLessThanOrEqualTo(String value) {
            addCriterion("cargo_specifications <=", value, "cargoSpecifications");
            return (Criteria) this;
        }

        public Criteria andCargoSpecificationsLike(String value) {
            addCriterion("cargo_specifications like", value, "cargoSpecifications");
            return (Criteria) this;
        }

        public Criteria andCargoSpecificationsNotLike(String value) {
            addCriterion("cargo_specifications not like", value, "cargoSpecifications");
            return (Criteria) this;
        }

        public Criteria andCargoSpecificationsIn(List<String> values) {
            addCriterion("cargo_specifications in", values, "cargoSpecifications");
            return (Criteria) this;
        }

        public Criteria andCargoSpecificationsNotIn(List<String> values) {
            addCriterion("cargo_specifications not in", values, "cargoSpecifications");
            return (Criteria) this;
        }

        public Criteria andCargoSpecificationsBetween(String value1, String value2) {
            addCriterion("cargo_specifications between", value1, value2, "cargoSpecifications");
            return (Criteria) this;
        }

        public Criteria andCargoSpecificationsNotBetween(String value1, String value2) {
            addCriterion("cargo_specifications not between", value1, value2, "cargoSpecifications");
            return (Criteria) this;
        }

        public Criteria andCargoPriceIsNull() {
            addCriterion("cargo_price is null");
            return (Criteria) this;
        }

        public Criteria andCargoPriceIsNotNull() {
            addCriterion("cargo_price is not null");
            return (Criteria) this;
        }

        public Criteria andCargoPriceEqualTo(BigDecimal value) {
            addCriterion("cargo_price =", value, "cargoPrice");
            return (Criteria) this;
        }

        public Criteria andCargoPriceNotEqualTo(BigDecimal value) {
            addCriterion("cargo_price <>", value, "cargoPrice");
            return (Criteria) this;
        }

        public Criteria andCargoPriceGreaterThan(BigDecimal value) {
            addCriterion("cargo_price >", value, "cargoPrice");
            return (Criteria) this;
        }

        public Criteria andCargoPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("cargo_price >=", value, "cargoPrice");
            return (Criteria) this;
        }

        public Criteria andCargoPriceLessThan(BigDecimal value) {
            addCriterion("cargo_price <", value, "cargoPrice");
            return (Criteria) this;
        }

        public Criteria andCargoPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("cargo_price <=", value, "cargoPrice");
            return (Criteria) this;
        }

        public Criteria andCargoPriceIn(List<BigDecimal> values) {
            addCriterion("cargo_price in", values, "cargoPrice");
            return (Criteria) this;
        }

        public Criteria andCargoPriceNotIn(List<BigDecimal> values) {
            addCriterion("cargo_price not in", values, "cargoPrice");
            return (Criteria) this;
        }

        public Criteria andCargoPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("cargo_price between", value1, value2, "cargoPrice");
            return (Criteria) this;
        }

        public Criteria andCargoPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("cargo_price not between", value1, value2, "cargoPrice");
            return (Criteria) this;
        }

        public Criteria andValuationUnitNameIsNull() {
            addCriterion("valuation_unit_name is null");
            return (Criteria) this;
        }

        public Criteria andValuationUnitNameIsNotNull() {
            addCriterion("valuation_unit_name is not null");
            return (Criteria) this;
        }

        public Criteria andValuationUnitNameEqualTo(Byte value) {
            addCriterion("valuation_unit_name =", value, "valuationUnitName");
            return (Criteria) this;
        }

        public Criteria andValuationUnitNameNotEqualTo(Byte value) {
            addCriterion("valuation_unit_name <>", value, "valuationUnitName");
            return (Criteria) this;
        }

        public Criteria andValuationUnitNameGreaterThan(Byte value) {
            addCriterion("valuation_unit_name >", value, "valuationUnitName");
            return (Criteria) this;
        }

        public Criteria andValuationUnitNameGreaterThanOrEqualTo(Byte value) {
            addCriterion("valuation_unit_name >=", value, "valuationUnitName");
            return (Criteria) this;
        }

        public Criteria andValuationUnitNameLessThan(Byte value) {
            addCriterion("valuation_unit_name <", value, "valuationUnitName");
            return (Criteria) this;
        }

        public Criteria andValuationUnitNameLessThanOrEqualTo(Byte value) {
            addCriterion("valuation_unit_name <=", value, "valuationUnitName");
            return (Criteria) this;
        }

        public Criteria andValuationUnitNameIn(List<Byte> values) {
            addCriterion("valuation_unit_name in", values, "valuationUnitName");
            return (Criteria) this;
        }

        public Criteria andValuationUnitNameNotIn(List<Byte> values) {
            addCriterion("valuation_unit_name not in", values, "valuationUnitName");
            return (Criteria) this;
        }

        public Criteria andValuationUnitNameBetween(Byte value1, Byte value2) {
            addCriterion("valuation_unit_name between", value1, value2, "valuationUnitName");
            return (Criteria) this;
        }

        public Criteria andValuationUnitNameNotBetween(Byte value1, Byte value2) {
            addCriterion("valuation_unit_name not between", value1, value2, "valuationUnitName");
            return (Criteria) this;
        }

        public Criteria andSendCargoCompanyIdIsNull() {
            addCriterion("send_cargo_company_id is null");
            return (Criteria) this;
        }

        public Criteria andSendCargoCompanyIdIsNotNull() {
            addCriterion("send_cargo_company_id is not null");
            return (Criteria) this;
        }

        public Criteria andSendCargoCompanyIdEqualTo(Integer value) {
            addCriterion("send_cargo_company_id =", value, "sendCargoCompanyId");
            return (Criteria) this;
        }

        public Criteria andSendCargoCompanyIdNotEqualTo(Integer value) {
            addCriterion("send_cargo_company_id <>", value, "sendCargoCompanyId");
            return (Criteria) this;
        }

        public Criteria andSendCargoCompanyIdGreaterThan(Integer value) {
            addCriterion("send_cargo_company_id >", value, "sendCargoCompanyId");
            return (Criteria) this;
        }

        public Criteria andSendCargoCompanyIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("send_cargo_company_id >=", value, "sendCargoCompanyId");
            return (Criteria) this;
        }

        public Criteria andSendCargoCompanyIdLessThan(Integer value) {
            addCriterion("send_cargo_company_id <", value, "sendCargoCompanyId");
            return (Criteria) this;
        }

        public Criteria andSendCargoCompanyIdLessThanOrEqualTo(Integer value) {
            addCriterion("send_cargo_company_id <=", value, "sendCargoCompanyId");
            return (Criteria) this;
        }

        public Criteria andSendCargoCompanyIdIn(List<Integer> values) {
            addCriterion("send_cargo_company_id in", values, "sendCargoCompanyId");
            return (Criteria) this;
        }

        public Criteria andSendCargoCompanyIdNotIn(List<Integer> values) {
            addCriterion("send_cargo_company_id not in", values, "sendCargoCompanyId");
            return (Criteria) this;
        }

        public Criteria andSendCargoCompanyIdBetween(Integer value1, Integer value2) {
            addCriterion("send_cargo_company_id between", value1, value2, "sendCargoCompanyId");
            return (Criteria) this;
        }

        public Criteria andSendCargoCompanyIdNotBetween(Integer value1, Integer value2) {
            addCriterion("send_cargo_company_id not between", value1, value2, "sendCargoCompanyId");
            return (Criteria) this;
        }

        public Criteria andSendCargoCompanyNameIsNull() {
            addCriterion("send_cargo_company_name is null");
            return (Criteria) this;
        }

        public Criteria andSendCargoCompanyNameIsNotNull() {
            addCriterion("send_cargo_company_name is not null");
            return (Criteria) this;
        }

        public Criteria andSendCargoCompanyNameEqualTo(String value) {
            addCriterion("send_cargo_company_name =", value, "sendCargoCompanyName");
            return (Criteria) this;
        }

        public Criteria andSendCargoCompanyNameNotEqualTo(String value) {
            addCriterion("send_cargo_company_name <>", value, "sendCargoCompanyName");
            return (Criteria) this;
        }

        public Criteria andSendCargoCompanyNameGreaterThan(String value) {
            addCriterion("send_cargo_company_name >", value, "sendCargoCompanyName");
            return (Criteria) this;
        }

        public Criteria andSendCargoCompanyNameGreaterThanOrEqualTo(String value) {
            addCriterion("send_cargo_company_name >=", value, "sendCargoCompanyName");
            return (Criteria) this;
        }

        public Criteria andSendCargoCompanyNameLessThan(String value) {
            addCriterion("send_cargo_company_name <", value, "sendCargoCompanyName");
            return (Criteria) this;
        }

        public Criteria andSendCargoCompanyNameLessThanOrEqualTo(String value) {
            addCriterion("send_cargo_company_name <=", value, "sendCargoCompanyName");
            return (Criteria) this;
        }

        public Criteria andSendCargoCompanyNameLike(String value) {
            addCriterion("send_cargo_company_name like", value, "sendCargoCompanyName");
            return (Criteria) this;
        }

        public Criteria andSendCargoCompanyNameNotLike(String value) {
            addCriterion("send_cargo_company_name not like", value, "sendCargoCompanyName");
            return (Criteria) this;
        }

        public Criteria andSendCargoCompanyNameIn(List<String> values) {
            addCriterion("send_cargo_company_name in", values, "sendCargoCompanyName");
            return (Criteria) this;
        }

        public Criteria andSendCargoCompanyNameNotIn(List<String> values) {
            addCriterion("send_cargo_company_name not in", values, "sendCargoCompanyName");
            return (Criteria) this;
        }

        public Criteria andSendCargoCompanyNameBetween(String value1, String value2) {
            addCriterion("send_cargo_company_name between", value1, value2, "sendCargoCompanyName");
            return (Criteria) this;
        }

        public Criteria andSendCargoCompanyNameNotBetween(String value1, String value2) {
            addCriterion("send_cargo_company_name not between", value1, value2, "sendCargoCompanyName");
            return (Criteria) this;
        }

        public Criteria andReceiveCargoCompanyIdIsNull() {
            addCriterion("receive_cargo_company_id is null");
            return (Criteria) this;
        }

        public Criteria andReceiveCargoCompanyIdIsNotNull() {
            addCriterion("receive_cargo_company_id is not null");
            return (Criteria) this;
        }

        public Criteria andReceiveCargoCompanyIdEqualTo(Integer value) {
            addCriterion("receive_cargo_company_id =", value, "receiveCargoCompanyId");
            return (Criteria) this;
        }

        public Criteria andReceiveCargoCompanyIdNotEqualTo(Integer value) {
            addCriterion("receive_cargo_company_id <>", value, "receiveCargoCompanyId");
            return (Criteria) this;
        }

        public Criteria andReceiveCargoCompanyIdGreaterThan(Integer value) {
            addCriterion("receive_cargo_company_id >", value, "receiveCargoCompanyId");
            return (Criteria) this;
        }

        public Criteria andReceiveCargoCompanyIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("receive_cargo_company_id >=", value, "receiveCargoCompanyId");
            return (Criteria) this;
        }

        public Criteria andReceiveCargoCompanyIdLessThan(Integer value) {
            addCriterion("receive_cargo_company_id <", value, "receiveCargoCompanyId");
            return (Criteria) this;
        }

        public Criteria andReceiveCargoCompanyIdLessThanOrEqualTo(Integer value) {
            addCriterion("receive_cargo_company_id <=", value, "receiveCargoCompanyId");
            return (Criteria) this;
        }

        public Criteria andReceiveCargoCompanyIdIn(List<Integer> values) {
            addCriterion("receive_cargo_company_id in", values, "receiveCargoCompanyId");
            return (Criteria) this;
        }

        public Criteria andReceiveCargoCompanyIdNotIn(List<Integer> values) {
            addCriterion("receive_cargo_company_id not in", values, "receiveCargoCompanyId");
            return (Criteria) this;
        }

        public Criteria andReceiveCargoCompanyIdBetween(Integer value1, Integer value2) {
            addCriterion("receive_cargo_company_id between", value1, value2, "receiveCargoCompanyId");
            return (Criteria) this;
        }

        public Criteria andReceiveCargoCompanyIdNotBetween(Integer value1, Integer value2) {
            addCriterion("receive_cargo_company_id not between", value1, value2, "receiveCargoCompanyId");
            return (Criteria) this;
        }

        public Criteria andReceiveCargoCompanyNameIsNull() {
            addCriterion("receive_cargo_company_name is null");
            return (Criteria) this;
        }

        public Criteria andReceiveCargoCompanyNameIsNotNull() {
            addCriterion("receive_cargo_company_name is not null");
            return (Criteria) this;
        }

        public Criteria andReceiveCargoCompanyNameEqualTo(String value) {
            addCriterion("receive_cargo_company_name =", value, "receiveCargoCompanyName");
            return (Criteria) this;
        }

        public Criteria andReceiveCargoCompanyNameNotEqualTo(String value) {
            addCriterion("receive_cargo_company_name <>", value, "receiveCargoCompanyName");
            return (Criteria) this;
        }

        public Criteria andReceiveCargoCompanyNameGreaterThan(String value) {
            addCriterion("receive_cargo_company_name >", value, "receiveCargoCompanyName");
            return (Criteria) this;
        }

        public Criteria andReceiveCargoCompanyNameGreaterThanOrEqualTo(String value) {
            addCriterion("receive_cargo_company_name >=", value, "receiveCargoCompanyName");
            return (Criteria) this;
        }

        public Criteria andReceiveCargoCompanyNameLessThan(String value) {
            addCriterion("receive_cargo_company_name <", value, "receiveCargoCompanyName");
            return (Criteria) this;
        }

        public Criteria andReceiveCargoCompanyNameLessThanOrEqualTo(String value) {
            addCriterion("receive_cargo_company_name <=", value, "receiveCargoCompanyName");
            return (Criteria) this;
        }

        public Criteria andReceiveCargoCompanyNameLike(String value) {
            addCriterion("receive_cargo_company_name like", value, "receiveCargoCompanyName");
            return (Criteria) this;
        }

        public Criteria andReceiveCargoCompanyNameNotLike(String value) {
            addCriterion("receive_cargo_company_name not like", value, "receiveCargoCompanyName");
            return (Criteria) this;
        }

        public Criteria andReceiveCargoCompanyNameIn(List<String> values) {
            addCriterion("receive_cargo_company_name in", values, "receiveCargoCompanyName");
            return (Criteria) this;
        }

        public Criteria andReceiveCargoCompanyNameNotIn(List<String> values) {
            addCriterion("receive_cargo_company_name not in", values, "receiveCargoCompanyName");
            return (Criteria) this;
        }

        public Criteria andReceiveCargoCompanyNameBetween(String value1, String value2) {
            addCriterion("receive_cargo_company_name between", value1, value2, "receiveCargoCompanyName");
            return (Criteria) this;
        }

        public Criteria andReceiveCargoCompanyNameNotBetween(String value1, String value2) {
            addCriterion("receive_cargo_company_name not between", value1, value2, "receiveCargoCompanyName");
            return (Criteria) this;
        }

        public Criteria andSendCargoUnitIdIsNull() {
            addCriterion("send_cargo_unit_id is null");
            return (Criteria) this;
        }

        public Criteria andSendCargoUnitIdIsNotNull() {
            addCriterion("send_cargo_unit_id is not null");
            return (Criteria) this;
        }

        public Criteria andSendCargoUnitIdEqualTo(Integer value) {
            addCriterion("send_cargo_unit_id =", value, "sendCargoUnitId");
            return (Criteria) this;
        }

        public Criteria andSendCargoUnitIdNotEqualTo(Integer value) {
            addCriterion("send_cargo_unit_id <>", value, "sendCargoUnitId");
            return (Criteria) this;
        }

        public Criteria andSendCargoUnitIdGreaterThan(Integer value) {
            addCriterion("send_cargo_unit_id >", value, "sendCargoUnitId");
            return (Criteria) this;
        }

        public Criteria andSendCargoUnitIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("send_cargo_unit_id >=", value, "sendCargoUnitId");
            return (Criteria) this;
        }

        public Criteria andSendCargoUnitIdLessThan(Integer value) {
            addCriterion("send_cargo_unit_id <", value, "sendCargoUnitId");
            return (Criteria) this;
        }

        public Criteria andSendCargoUnitIdLessThanOrEqualTo(Integer value) {
            addCriterion("send_cargo_unit_id <=", value, "sendCargoUnitId");
            return (Criteria) this;
        }

        public Criteria andSendCargoUnitIdIn(List<Integer> values) {
            addCriterion("send_cargo_unit_id in", values, "sendCargoUnitId");
            return (Criteria) this;
        }

        public Criteria andSendCargoUnitIdNotIn(List<Integer> values) {
            addCriterion("send_cargo_unit_id not in", values, "sendCargoUnitId");
            return (Criteria) this;
        }

        public Criteria andSendCargoUnitIdBetween(Integer value1, Integer value2) {
            addCriterion("send_cargo_unit_id between", value1, value2, "sendCargoUnitId");
            return (Criteria) this;
        }

        public Criteria andSendCargoUnitIdNotBetween(Integer value1, Integer value2) {
            addCriterion("send_cargo_unit_id not between", value1, value2, "sendCargoUnitId");
            return (Criteria) this;
        }

        public Criteria andSendCargoUnitNameIsNull() {
            addCriterion("send_cargo_unit_name is null");
            return (Criteria) this;
        }

        public Criteria andSendCargoUnitNameIsNotNull() {
            addCriterion("send_cargo_unit_name is not null");
            return (Criteria) this;
        }

        public Criteria andSendCargoUnitNameEqualTo(String value) {
            addCriterion("send_cargo_unit_name =", value, "sendCargoUnitName");
            return (Criteria) this;
        }

        public Criteria andSendCargoUnitNameNotEqualTo(String value) {
            addCriterion("send_cargo_unit_name <>", value, "sendCargoUnitName");
            return (Criteria) this;
        }

        public Criteria andSendCargoUnitNameGreaterThan(String value) {
            addCriterion("send_cargo_unit_name >", value, "sendCargoUnitName");
            return (Criteria) this;
        }

        public Criteria andSendCargoUnitNameGreaterThanOrEqualTo(String value) {
            addCriterion("send_cargo_unit_name >=", value, "sendCargoUnitName");
            return (Criteria) this;
        }

        public Criteria andSendCargoUnitNameLessThan(String value) {
            addCriterion("send_cargo_unit_name <", value, "sendCargoUnitName");
            return (Criteria) this;
        }

        public Criteria andSendCargoUnitNameLessThanOrEqualTo(String value) {
            addCriterion("send_cargo_unit_name <=", value, "sendCargoUnitName");
            return (Criteria) this;
        }

        public Criteria andSendCargoUnitNameLike(String value) {
            addCriterion("send_cargo_unit_name like", value, "sendCargoUnitName");
            return (Criteria) this;
        }

        public Criteria andSendCargoUnitNameNotLike(String value) {
            addCriterion("send_cargo_unit_name not like", value, "sendCargoUnitName");
            return (Criteria) this;
        }

        public Criteria andSendCargoUnitNameIn(List<String> values) {
            addCriterion("send_cargo_unit_name in", values, "sendCargoUnitName");
            return (Criteria) this;
        }

        public Criteria andSendCargoUnitNameNotIn(List<String> values) {
            addCriterion("send_cargo_unit_name not in", values, "sendCargoUnitName");
            return (Criteria) this;
        }

        public Criteria andSendCargoUnitNameBetween(String value1, String value2) {
            addCriterion("send_cargo_unit_name between", value1, value2, "sendCargoUnitName");
            return (Criteria) this;
        }

        public Criteria andSendCargoUnitNameNotBetween(String value1, String value2) {
            addCriterion("send_cargo_unit_name not between", value1, value2, "sendCargoUnitName");
            return (Criteria) this;
        }

        public Criteria andReceiveCargoSiteIdIsNull() {
            addCriterion("receive_cargo_site_id is null");
            return (Criteria) this;
        }

        public Criteria andReceiveCargoSiteIdIsNotNull() {
            addCriterion("receive_cargo_site_id is not null");
            return (Criteria) this;
        }

        public Criteria andReceiveCargoSiteIdEqualTo(Integer value) {
            addCriterion("receive_cargo_site_id =", value, "receiveCargoSiteId");
            return (Criteria) this;
        }

        public Criteria andReceiveCargoSiteIdNotEqualTo(Integer value) {
            addCriterion("receive_cargo_site_id <>", value, "receiveCargoSiteId");
            return (Criteria) this;
        }

        public Criteria andReceiveCargoSiteIdGreaterThan(Integer value) {
            addCriterion("receive_cargo_site_id >", value, "receiveCargoSiteId");
            return (Criteria) this;
        }

        public Criteria andReceiveCargoSiteIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("receive_cargo_site_id >=", value, "receiveCargoSiteId");
            return (Criteria) this;
        }

        public Criteria andReceiveCargoSiteIdLessThan(Integer value) {
            addCriterion("receive_cargo_site_id <", value, "receiveCargoSiteId");
            return (Criteria) this;
        }

        public Criteria andReceiveCargoSiteIdLessThanOrEqualTo(Integer value) {
            addCriterion("receive_cargo_site_id <=", value, "receiveCargoSiteId");
            return (Criteria) this;
        }

        public Criteria andReceiveCargoSiteIdIn(List<Integer> values) {
            addCriterion("receive_cargo_site_id in", values, "receiveCargoSiteId");
            return (Criteria) this;
        }

        public Criteria andReceiveCargoSiteIdNotIn(List<Integer> values) {
            addCriterion("receive_cargo_site_id not in", values, "receiveCargoSiteId");
            return (Criteria) this;
        }

        public Criteria andReceiveCargoSiteIdBetween(Integer value1, Integer value2) {
            addCriterion("receive_cargo_site_id between", value1, value2, "receiveCargoSiteId");
            return (Criteria) this;
        }

        public Criteria andReceiveCargoSiteIdNotBetween(Integer value1, Integer value2) {
            addCriterion("receive_cargo_site_id not between", value1, value2, "receiveCargoSiteId");
            return (Criteria) this;
        }

        public Criteria andReceiveCargoSiteIsNull() {
            addCriterion("receive_cargo_site is null");
            return (Criteria) this;
        }

        public Criteria andReceiveCargoSiteIsNotNull() {
            addCriterion("receive_cargo_site is not null");
            return (Criteria) this;
        }

        public Criteria andReceiveCargoSiteEqualTo(String value) {
            addCriterion("receive_cargo_site =", value, "receiveCargoSite");
            return (Criteria) this;
        }

        public Criteria andReceiveCargoSiteNotEqualTo(String value) {
            addCriterion("receive_cargo_site <>", value, "receiveCargoSite");
            return (Criteria) this;
        }

        public Criteria andReceiveCargoSiteGreaterThan(String value) {
            addCriterion("receive_cargo_site >", value, "receiveCargoSite");
            return (Criteria) this;
        }

        public Criteria andReceiveCargoSiteGreaterThanOrEqualTo(String value) {
            addCriterion("receive_cargo_site >=", value, "receiveCargoSite");
            return (Criteria) this;
        }

        public Criteria andReceiveCargoSiteLessThan(String value) {
            addCriterion("receive_cargo_site <", value, "receiveCargoSite");
            return (Criteria) this;
        }

        public Criteria andReceiveCargoSiteLessThanOrEqualTo(String value) {
            addCriterion("receive_cargo_site <=", value, "receiveCargoSite");
            return (Criteria) this;
        }

        public Criteria andReceiveCargoSiteLike(String value) {
            addCriterion("receive_cargo_site like", value, "receiveCargoSite");
            return (Criteria) this;
        }

        public Criteria andReceiveCargoSiteNotLike(String value) {
            addCriterion("receive_cargo_site not like", value, "receiveCargoSite");
            return (Criteria) this;
        }

        public Criteria andReceiveCargoSiteIn(List<String> values) {
            addCriterion("receive_cargo_site in", values, "receiveCargoSite");
            return (Criteria) this;
        }

        public Criteria andReceiveCargoSiteNotIn(List<String> values) {
            addCriterion("receive_cargo_site not in", values, "receiveCargoSite");
            return (Criteria) this;
        }

        public Criteria andReceiveCargoSiteBetween(String value1, String value2) {
            addCriterion("receive_cargo_site between", value1, value2, "receiveCargoSite");
            return (Criteria) this;
        }

        public Criteria andReceiveCargoSiteNotBetween(String value1, String value2) {
            addCriterion("receive_cargo_site not between", value1, value2, "receiveCargoSite");
            return (Criteria) this;
        }

        public Criteria andShortBargeCarrierModeIsNull() {
            addCriterion("short_barge_carrier_mode is null");
            return (Criteria) this;
        }

        public Criteria andShortBargeCarrierModeIsNotNull() {
            addCriterion("short_barge_carrier_mode is not null");
            return (Criteria) this;
        }

        public Criteria andShortBargeCarrierModeEqualTo(Byte value) {
            addCriterion("short_barge_carrier_mode =", value, "shortBargeCarrierMode");
            return (Criteria) this;
        }

        public Criteria andShortBargeCarrierModeNotEqualTo(Byte value) {
            addCriterion("short_barge_carrier_mode <>", value, "shortBargeCarrierMode");
            return (Criteria) this;
        }

        public Criteria andShortBargeCarrierModeGreaterThan(Byte value) {
            addCriterion("short_barge_carrier_mode >", value, "shortBargeCarrierMode");
            return (Criteria) this;
        }

        public Criteria andShortBargeCarrierModeGreaterThanOrEqualTo(Byte value) {
            addCriterion("short_barge_carrier_mode >=", value, "shortBargeCarrierMode");
            return (Criteria) this;
        }

        public Criteria andShortBargeCarrierModeLessThan(Byte value) {
            addCriterion("short_barge_carrier_mode <", value, "shortBargeCarrierMode");
            return (Criteria) this;
        }

        public Criteria andShortBargeCarrierModeLessThanOrEqualTo(Byte value) {
            addCriterion("short_barge_carrier_mode <=", value, "shortBargeCarrierMode");
            return (Criteria) this;
        }

        public Criteria andShortBargeCarrierModeIn(List<Byte> values) {
            addCriterion("short_barge_carrier_mode in", values, "shortBargeCarrierMode");
            return (Criteria) this;
        }

        public Criteria andShortBargeCarrierModeNotIn(List<Byte> values) {
            addCriterion("short_barge_carrier_mode not in", values, "shortBargeCarrierMode");
            return (Criteria) this;
        }

        public Criteria andShortBargeCarrierModeBetween(Byte value1, Byte value2) {
            addCriterion("short_barge_carrier_mode between", value1, value2, "shortBargeCarrierMode");
            return (Criteria) this;
        }

        public Criteria andShortBargeCarrierModeNotBetween(Byte value1, Byte value2) {
            addCriterion("short_barge_carrier_mode not between", value1, value2, "shortBargeCarrierMode");
            return (Criteria) this;
        }

        public Criteria andShortBargeCarrierIdIsNull() {
            addCriterion("short_barge_carrier_id is null");
            return (Criteria) this;
        }

        public Criteria andShortBargeCarrierIdIsNotNull() {
            addCriterion("short_barge_carrier_id is not null");
            return (Criteria) this;
        }

        public Criteria andShortBargeCarrierIdEqualTo(Integer value) {
            addCriterion("short_barge_carrier_id =", value, "shortBargeCarrierId");
            return (Criteria) this;
        }

        public Criteria andShortBargeCarrierIdNotEqualTo(Integer value) {
            addCriterion("short_barge_carrier_id <>", value, "shortBargeCarrierId");
            return (Criteria) this;
        }

        public Criteria andShortBargeCarrierIdGreaterThan(Integer value) {
            addCriterion("short_barge_carrier_id >", value, "shortBargeCarrierId");
            return (Criteria) this;
        }

        public Criteria andShortBargeCarrierIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("short_barge_carrier_id >=", value, "shortBargeCarrierId");
            return (Criteria) this;
        }

        public Criteria andShortBargeCarrierIdLessThan(Integer value) {
            addCriterion("short_barge_carrier_id <", value, "shortBargeCarrierId");
            return (Criteria) this;
        }

        public Criteria andShortBargeCarrierIdLessThanOrEqualTo(Integer value) {
            addCriterion("short_barge_carrier_id <=", value, "shortBargeCarrierId");
            return (Criteria) this;
        }

        public Criteria andShortBargeCarrierIdIn(List<Integer> values) {
            addCriterion("short_barge_carrier_id in", values, "shortBargeCarrierId");
            return (Criteria) this;
        }

        public Criteria andShortBargeCarrierIdNotIn(List<Integer> values) {
            addCriterion("short_barge_carrier_id not in", values, "shortBargeCarrierId");
            return (Criteria) this;
        }

        public Criteria andShortBargeCarrierIdBetween(Integer value1, Integer value2) {
            addCriterion("short_barge_carrier_id between", value1, value2, "shortBargeCarrierId");
            return (Criteria) this;
        }

        public Criteria andShortBargeCarrierIdNotBetween(Integer value1, Integer value2) {
            addCriterion("short_barge_carrier_id not between", value1, value2, "shortBargeCarrierId");
            return (Criteria) this;
        }

        public Criteria andShortBargeCarrierNameIsNull() {
            addCriterion("short_barge_carrier_name is null");
            return (Criteria) this;
        }

        public Criteria andShortBargeCarrierNameIsNotNull() {
            addCriterion("short_barge_carrier_name is not null");
            return (Criteria) this;
        }

        public Criteria andShortBargeCarrierNameEqualTo(String value) {
            addCriterion("short_barge_carrier_name =", value, "shortBargeCarrierName");
            return (Criteria) this;
        }

        public Criteria andShortBargeCarrierNameNotEqualTo(String value) {
            addCriterion("short_barge_carrier_name <>", value, "shortBargeCarrierName");
            return (Criteria) this;
        }

        public Criteria andShortBargeCarrierNameGreaterThan(String value) {
            addCriterion("short_barge_carrier_name >", value, "shortBargeCarrierName");
            return (Criteria) this;
        }

        public Criteria andShortBargeCarrierNameGreaterThanOrEqualTo(String value) {
            addCriterion("short_barge_carrier_name >=", value, "shortBargeCarrierName");
            return (Criteria) this;
        }

        public Criteria andShortBargeCarrierNameLessThan(String value) {
            addCriterion("short_barge_carrier_name <", value, "shortBargeCarrierName");
            return (Criteria) this;
        }

        public Criteria andShortBargeCarrierNameLessThanOrEqualTo(String value) {
            addCriterion("short_barge_carrier_name <=", value, "shortBargeCarrierName");
            return (Criteria) this;
        }

        public Criteria andShortBargeCarrierNameLike(String value) {
            addCriterion("short_barge_carrier_name like", value, "shortBargeCarrierName");
            return (Criteria) this;
        }

        public Criteria andShortBargeCarrierNameNotLike(String value) {
            addCriterion("short_barge_carrier_name not like", value, "shortBargeCarrierName");
            return (Criteria) this;
        }

        public Criteria andShortBargeCarrierNameIn(List<String> values) {
            addCriterion("short_barge_carrier_name in", values, "shortBargeCarrierName");
            return (Criteria) this;
        }

        public Criteria andShortBargeCarrierNameNotIn(List<String> values) {
            addCriterion("short_barge_carrier_name not in", values, "shortBargeCarrierName");
            return (Criteria) this;
        }

        public Criteria andShortBargeCarrierNameBetween(String value1, String value2) {
            addCriterion("short_barge_carrier_name between", value1, value2, "shortBargeCarrierName");
            return (Criteria) this;
        }

        public Criteria andShortBargeCarrierNameNotBetween(String value1, String value2) {
            addCriterion("short_barge_carrier_name not between", value1, value2, "shortBargeCarrierName");
            return (Criteria) this;
        }

        public Criteria andSendShortBargeCarrierModeIsNull() {
            addCriterion("send_short_barge_carrier_mode is null");
            return (Criteria) this;
        }

        public Criteria andSendShortBargeCarrierModeIsNotNull() {
            addCriterion("send_short_barge_carrier_mode is not null");
            return (Criteria) this;
        }

        public Criteria andSendShortBargeCarrierModeEqualTo(Byte value) {
            addCriterion("send_short_barge_carrier_mode =", value, "sendShortBargeCarrierMode");
            return (Criteria) this;
        }

        public Criteria andSendShortBargeCarrierModeNotEqualTo(Byte value) {
            addCriterion("send_short_barge_carrier_mode <>", value, "sendShortBargeCarrierMode");
            return (Criteria) this;
        }

        public Criteria andSendShortBargeCarrierModeGreaterThan(Byte value) {
            addCriterion("send_short_barge_carrier_mode >", value, "sendShortBargeCarrierMode");
            return (Criteria) this;
        }

        public Criteria andSendShortBargeCarrierModeGreaterThanOrEqualTo(Byte value) {
            addCriterion("send_short_barge_carrier_mode >=", value, "sendShortBargeCarrierMode");
            return (Criteria) this;
        }

        public Criteria andSendShortBargeCarrierModeLessThan(Byte value) {
            addCriterion("send_short_barge_carrier_mode <", value, "sendShortBargeCarrierMode");
            return (Criteria) this;
        }

        public Criteria andSendShortBargeCarrierModeLessThanOrEqualTo(Byte value) {
            addCriterion("send_short_barge_carrier_mode <=", value, "sendShortBargeCarrierMode");
            return (Criteria) this;
        }

        public Criteria andSendShortBargeCarrierModeIn(List<Byte> values) {
            addCriterion("send_short_barge_carrier_mode in", values, "sendShortBargeCarrierMode");
            return (Criteria) this;
        }

        public Criteria andSendShortBargeCarrierModeNotIn(List<Byte> values) {
            addCriterion("send_short_barge_carrier_mode not in", values, "sendShortBargeCarrierMode");
            return (Criteria) this;
        }

        public Criteria andSendShortBargeCarrierModeBetween(Byte value1, Byte value2) {
            addCriterion("send_short_barge_carrier_mode between", value1, value2, "sendShortBargeCarrierMode");
            return (Criteria) this;
        }

        public Criteria andSendShortBargeCarrierModeNotBetween(Byte value1, Byte value2) {
            addCriterion("send_short_barge_carrier_mode not between", value1, value2, "sendShortBargeCarrierMode");
            return (Criteria) this;
        }

        public Criteria andBeginCenterSiteIdIsNull() {
            addCriterion("begin_center_site_id is null");
            return (Criteria) this;
        }

        public Criteria andBeginCenterSiteIdIsNotNull() {
            addCriterion("begin_center_site_id is not null");
            return (Criteria) this;
        }

        public Criteria andBeginCenterSiteIdEqualTo(Integer value) {
            addCriterion("begin_center_site_id =", value, "beginCenterSiteId");
            return (Criteria) this;
        }

        public Criteria andBeginCenterSiteIdNotEqualTo(Integer value) {
            addCriterion("begin_center_site_id <>", value, "beginCenterSiteId");
            return (Criteria) this;
        }

        public Criteria andBeginCenterSiteIdGreaterThan(Integer value) {
            addCriterion("begin_center_site_id >", value, "beginCenterSiteId");
            return (Criteria) this;
        }

        public Criteria andBeginCenterSiteIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("begin_center_site_id >=", value, "beginCenterSiteId");
            return (Criteria) this;
        }

        public Criteria andBeginCenterSiteIdLessThan(Integer value) {
            addCriterion("begin_center_site_id <", value, "beginCenterSiteId");
            return (Criteria) this;
        }

        public Criteria andBeginCenterSiteIdLessThanOrEqualTo(Integer value) {
            addCriterion("begin_center_site_id <=", value, "beginCenterSiteId");
            return (Criteria) this;
        }

        public Criteria andBeginCenterSiteIdIn(List<Integer> values) {
            addCriterion("begin_center_site_id in", values, "beginCenterSiteId");
            return (Criteria) this;
        }

        public Criteria andBeginCenterSiteIdNotIn(List<Integer> values) {
            addCriterion("begin_center_site_id not in", values, "beginCenterSiteId");
            return (Criteria) this;
        }

        public Criteria andBeginCenterSiteIdBetween(Integer value1, Integer value2) {
            addCriterion("begin_center_site_id between", value1, value2, "beginCenterSiteId");
            return (Criteria) this;
        }

        public Criteria andBeginCenterSiteIdNotBetween(Integer value1, Integer value2) {
            addCriterion("begin_center_site_id not between", value1, value2, "beginCenterSiteId");
            return (Criteria) this;
        }

        public Criteria andBeginCenterSiteNameIsNull() {
            addCriterion("begin_center_site_name is null");
            return (Criteria) this;
        }

        public Criteria andBeginCenterSiteNameIsNotNull() {
            addCriterion("begin_center_site_name is not null");
            return (Criteria) this;
        }

        public Criteria andBeginCenterSiteNameEqualTo(String value) {
            addCriterion("begin_center_site_name =", value, "beginCenterSiteName");
            return (Criteria) this;
        }

        public Criteria andBeginCenterSiteNameNotEqualTo(String value) {
            addCriterion("begin_center_site_name <>", value, "beginCenterSiteName");
            return (Criteria) this;
        }

        public Criteria andBeginCenterSiteNameGreaterThan(String value) {
            addCriterion("begin_center_site_name >", value, "beginCenterSiteName");
            return (Criteria) this;
        }

        public Criteria andBeginCenterSiteNameGreaterThanOrEqualTo(String value) {
            addCriterion("begin_center_site_name >=", value, "beginCenterSiteName");
            return (Criteria) this;
        }

        public Criteria andBeginCenterSiteNameLessThan(String value) {
            addCriterion("begin_center_site_name <", value, "beginCenterSiteName");
            return (Criteria) this;
        }

        public Criteria andBeginCenterSiteNameLessThanOrEqualTo(String value) {
            addCriterion("begin_center_site_name <=", value, "beginCenterSiteName");
            return (Criteria) this;
        }

        public Criteria andBeginCenterSiteNameLike(String value) {
            addCriterion("begin_center_site_name like", value, "beginCenterSiteName");
            return (Criteria) this;
        }

        public Criteria andBeginCenterSiteNameNotLike(String value) {
            addCriterion("begin_center_site_name not like", value, "beginCenterSiteName");
            return (Criteria) this;
        }

        public Criteria andBeginCenterSiteNameIn(List<String> values) {
            addCriterion("begin_center_site_name in", values, "beginCenterSiteName");
            return (Criteria) this;
        }

        public Criteria andBeginCenterSiteNameNotIn(List<String> values) {
            addCriterion("begin_center_site_name not in", values, "beginCenterSiteName");
            return (Criteria) this;
        }

        public Criteria andBeginCenterSiteNameBetween(String value1, String value2) {
            addCriterion("begin_center_site_name between", value1, value2, "beginCenterSiteName");
            return (Criteria) this;
        }

        public Criteria andBeginCenterSiteNameNotBetween(String value1, String value2) {
            addCriterion("begin_center_site_name not between", value1, value2, "beginCenterSiteName");
            return (Criteria) this;
        }

        public Criteria andBeginSiteIdIsNull() {
            addCriterion("begin_site_id is null");
            return (Criteria) this;
        }

        public Criteria andBeginSiteIdIsNotNull() {
            addCriterion("begin_site_id is not null");
            return (Criteria) this;
        }

        public Criteria andBeginSiteIdEqualTo(Integer value) {
            addCriterion("begin_site_id =", value, "beginSiteId");
            return (Criteria) this;
        }

        public Criteria andBeginSiteIdNotEqualTo(Integer value) {
            addCriterion("begin_site_id <>", value, "beginSiteId");
            return (Criteria) this;
        }

        public Criteria andBeginSiteIdGreaterThan(Integer value) {
            addCriterion("begin_site_id >", value, "beginSiteId");
            return (Criteria) this;
        }

        public Criteria andBeginSiteIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("begin_site_id >=", value, "beginSiteId");
            return (Criteria) this;
        }

        public Criteria andBeginSiteIdLessThan(Integer value) {
            addCriterion("begin_site_id <", value, "beginSiteId");
            return (Criteria) this;
        }

        public Criteria andBeginSiteIdLessThanOrEqualTo(Integer value) {
            addCriterion("begin_site_id <=", value, "beginSiteId");
            return (Criteria) this;
        }

        public Criteria andBeginSiteIdIn(List<Integer> values) {
            addCriterion("begin_site_id in", values, "beginSiteId");
            return (Criteria) this;
        }

        public Criteria andBeginSiteIdNotIn(List<Integer> values) {
            addCriterion("begin_site_id not in", values, "beginSiteId");
            return (Criteria) this;
        }

        public Criteria andBeginSiteIdBetween(Integer value1, Integer value2) {
            addCriterion("begin_site_id between", value1, value2, "beginSiteId");
            return (Criteria) this;
        }

        public Criteria andBeginSiteIdNotBetween(Integer value1, Integer value2) {
            addCriterion("begin_site_id not between", value1, value2, "beginSiteId");
            return (Criteria) this;
        }

        public Criteria andBeginSiteNameIsNull() {
            addCriterion("begin_site_name is null");
            return (Criteria) this;
        }

        public Criteria andBeginSiteNameIsNotNull() {
            addCriterion("begin_site_name is not null");
            return (Criteria) this;
        }

        public Criteria andBeginSiteNameEqualTo(String value) {
            addCriterion("begin_site_name =", value, "beginSiteName");
            return (Criteria) this;
        }

        public Criteria andBeginSiteNameNotEqualTo(String value) {
            addCriterion("begin_site_name <>", value, "beginSiteName");
            return (Criteria) this;
        }

        public Criteria andBeginSiteNameGreaterThan(String value) {
            addCriterion("begin_site_name >", value, "beginSiteName");
            return (Criteria) this;
        }

        public Criteria andBeginSiteNameGreaterThanOrEqualTo(String value) {
            addCriterion("begin_site_name >=", value, "beginSiteName");
            return (Criteria) this;
        }

        public Criteria andBeginSiteNameLessThan(String value) {
            addCriterion("begin_site_name <", value, "beginSiteName");
            return (Criteria) this;
        }

        public Criteria andBeginSiteNameLessThanOrEqualTo(String value) {
            addCriterion("begin_site_name <=", value, "beginSiteName");
            return (Criteria) this;
        }

        public Criteria andBeginSiteNameLike(String value) {
            addCriterion("begin_site_name like", value, "beginSiteName");
            return (Criteria) this;
        }

        public Criteria andBeginSiteNameNotLike(String value) {
            addCriterion("begin_site_name not like", value, "beginSiteName");
            return (Criteria) this;
        }

        public Criteria andBeginSiteNameIn(List<String> values) {
            addCriterion("begin_site_name in", values, "beginSiteName");
            return (Criteria) this;
        }

        public Criteria andBeginSiteNameNotIn(List<String> values) {
            addCriterion("begin_site_name not in", values, "beginSiteName");
            return (Criteria) this;
        }

        public Criteria andBeginSiteNameBetween(String value1, String value2) {
            addCriterion("begin_site_name between", value1, value2, "beginSiteName");
            return (Criteria) this;
        }

        public Criteria andBeginSiteNameNotBetween(String value1, String value2) {
            addCriterion("begin_site_name not between", value1, value2, "beginSiteName");
            return (Criteria) this;
        }

        public Criteria andBeginAddressIsNull() {
            addCriterion("begin_address is null");
            return (Criteria) this;
        }

        public Criteria andBeginAddressIsNotNull() {
            addCriterion("begin_address is not null");
            return (Criteria) this;
        }

        public Criteria andBeginAddressEqualTo(String value) {
            addCriterion("begin_address =", value, "beginAddress");
            return (Criteria) this;
        }

        public Criteria andBeginAddressNotEqualTo(String value) {
            addCriterion("begin_address <>", value, "beginAddress");
            return (Criteria) this;
        }

        public Criteria andBeginAddressGreaterThan(String value) {
            addCriterion("begin_address >", value, "beginAddress");
            return (Criteria) this;
        }

        public Criteria andBeginAddressGreaterThanOrEqualTo(String value) {
            addCriterion("begin_address >=", value, "beginAddress");
            return (Criteria) this;
        }

        public Criteria andBeginAddressLessThan(String value) {
            addCriterion("begin_address <", value, "beginAddress");
            return (Criteria) this;
        }

        public Criteria andBeginAddressLessThanOrEqualTo(String value) {
            addCriterion("begin_address <=", value, "beginAddress");
            return (Criteria) this;
        }

        public Criteria andBeginAddressLike(String value) {
            addCriterion("begin_address like", value, "beginAddress");
            return (Criteria) this;
        }

        public Criteria andBeginAddressNotLike(String value) {
            addCriterion("begin_address not like", value, "beginAddress");
            return (Criteria) this;
        }

        public Criteria andBeginAddressIn(List<String> values) {
            addCriterion("begin_address in", values, "beginAddress");
            return (Criteria) this;
        }

        public Criteria andBeginAddressNotIn(List<String> values) {
            addCriterion("begin_address not in", values, "beginAddress");
            return (Criteria) this;
        }

        public Criteria andBeginAddressBetween(String value1, String value2) {
            addCriterion("begin_address between", value1, value2, "beginAddress");
            return (Criteria) this;
        }

        public Criteria andBeginAddressNotBetween(String value1, String value2) {
            addCriterion("begin_address not between", value1, value2, "beginAddress");
            return (Criteria) this;
        }

        public Criteria andEndCenterSiteIdIsNull() {
            addCriterion("end_center_site_id is null");
            return (Criteria) this;
        }

        public Criteria andEndCenterSiteIdIsNotNull() {
            addCriterion("end_center_site_id is not null");
            return (Criteria) this;
        }

        public Criteria andEndCenterSiteIdEqualTo(Integer value) {
            addCriterion("end_center_site_id =", value, "endCenterSiteId");
            return (Criteria) this;
        }

        public Criteria andEndCenterSiteIdNotEqualTo(Integer value) {
            addCriterion("end_center_site_id <>", value, "endCenterSiteId");
            return (Criteria) this;
        }

        public Criteria andEndCenterSiteIdGreaterThan(Integer value) {
            addCriterion("end_center_site_id >", value, "endCenterSiteId");
            return (Criteria) this;
        }

        public Criteria andEndCenterSiteIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("end_center_site_id >=", value, "endCenterSiteId");
            return (Criteria) this;
        }

        public Criteria andEndCenterSiteIdLessThan(Integer value) {
            addCriterion("end_center_site_id <", value, "endCenterSiteId");
            return (Criteria) this;
        }

        public Criteria andEndCenterSiteIdLessThanOrEqualTo(Integer value) {
            addCriterion("end_center_site_id <=", value, "endCenterSiteId");
            return (Criteria) this;
        }

        public Criteria andEndCenterSiteIdIn(List<Integer> values) {
            addCriterion("end_center_site_id in", values, "endCenterSiteId");
            return (Criteria) this;
        }

        public Criteria andEndCenterSiteIdNotIn(List<Integer> values) {
            addCriterion("end_center_site_id not in", values, "endCenterSiteId");
            return (Criteria) this;
        }

        public Criteria andEndCenterSiteIdBetween(Integer value1, Integer value2) {
            addCriterion("end_center_site_id between", value1, value2, "endCenterSiteId");
            return (Criteria) this;
        }

        public Criteria andEndCenterSiteIdNotBetween(Integer value1, Integer value2) {
            addCriterion("end_center_site_id not between", value1, value2, "endCenterSiteId");
            return (Criteria) this;
        }

        public Criteria andEndCenterSiteNameIsNull() {
            addCriterion("end_center_site_name is null");
            return (Criteria) this;
        }

        public Criteria andEndCenterSiteNameIsNotNull() {
            addCriterion("end_center_site_name is not null");
            return (Criteria) this;
        }

        public Criteria andEndCenterSiteNameEqualTo(String value) {
            addCriterion("end_center_site_name =", value, "endCenterSiteName");
            return (Criteria) this;
        }

        public Criteria andEndCenterSiteNameNotEqualTo(String value) {
            addCriterion("end_center_site_name <>", value, "endCenterSiteName");
            return (Criteria) this;
        }

        public Criteria andEndCenterSiteNameGreaterThan(String value) {
            addCriterion("end_center_site_name >", value, "endCenterSiteName");
            return (Criteria) this;
        }

        public Criteria andEndCenterSiteNameGreaterThanOrEqualTo(String value) {
            addCriterion("end_center_site_name >=", value, "endCenterSiteName");
            return (Criteria) this;
        }

        public Criteria andEndCenterSiteNameLessThan(String value) {
            addCriterion("end_center_site_name <", value, "endCenterSiteName");
            return (Criteria) this;
        }

        public Criteria andEndCenterSiteNameLessThanOrEqualTo(String value) {
            addCriterion("end_center_site_name <=", value, "endCenterSiteName");
            return (Criteria) this;
        }

        public Criteria andEndCenterSiteNameLike(String value) {
            addCriterion("end_center_site_name like", value, "endCenterSiteName");
            return (Criteria) this;
        }

        public Criteria andEndCenterSiteNameNotLike(String value) {
            addCriterion("end_center_site_name not like", value, "endCenterSiteName");
            return (Criteria) this;
        }

        public Criteria andEndCenterSiteNameIn(List<String> values) {
            addCriterion("end_center_site_name in", values, "endCenterSiteName");
            return (Criteria) this;
        }

        public Criteria andEndCenterSiteNameNotIn(List<String> values) {
            addCriterion("end_center_site_name not in", values, "endCenterSiteName");
            return (Criteria) this;
        }

        public Criteria andEndCenterSiteNameBetween(String value1, String value2) {
            addCriterion("end_center_site_name between", value1, value2, "endCenterSiteName");
            return (Criteria) this;
        }

        public Criteria andEndCenterSiteNameNotBetween(String value1, String value2) {
            addCriterion("end_center_site_name not between", value1, value2, "endCenterSiteName");
            return (Criteria) this;
        }

        public Criteria andEndSiteIdIsNull() {
            addCriterion("end_site_id is null");
            return (Criteria) this;
        }

        public Criteria andEndSiteIdIsNotNull() {
            addCriterion("end_site_id is not null");
            return (Criteria) this;
        }

        public Criteria andEndSiteIdEqualTo(Integer value) {
            addCriterion("end_site_id =", value, "endSiteId");
            return (Criteria) this;
        }

        public Criteria andEndSiteIdNotEqualTo(Integer value) {
            addCriterion("end_site_id <>", value, "endSiteId");
            return (Criteria) this;
        }

        public Criteria andEndSiteIdGreaterThan(Integer value) {
            addCriterion("end_site_id >", value, "endSiteId");
            return (Criteria) this;
        }

        public Criteria andEndSiteIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("end_site_id >=", value, "endSiteId");
            return (Criteria) this;
        }

        public Criteria andEndSiteIdLessThan(Integer value) {
            addCriterion("end_site_id <", value, "endSiteId");
            return (Criteria) this;
        }

        public Criteria andEndSiteIdLessThanOrEqualTo(Integer value) {
            addCriterion("end_site_id <=", value, "endSiteId");
            return (Criteria) this;
        }

        public Criteria andEndSiteIdIn(List<Integer> values) {
            addCriterion("end_site_id in", values, "endSiteId");
            return (Criteria) this;
        }

        public Criteria andEndSiteIdNotIn(List<Integer> values) {
            addCriterion("end_site_id not in", values, "endSiteId");
            return (Criteria) this;
        }

        public Criteria andEndSiteIdBetween(Integer value1, Integer value2) {
            addCriterion("end_site_id between", value1, value2, "endSiteId");
            return (Criteria) this;
        }

        public Criteria andEndSiteIdNotBetween(Integer value1, Integer value2) {
            addCriterion("end_site_id not between", value1, value2, "endSiteId");
            return (Criteria) this;
        }

        public Criteria andEndSiteNameIsNull() {
            addCriterion("end_site_name is null");
            return (Criteria) this;
        }

        public Criteria andEndSiteNameIsNotNull() {
            addCriterion("end_site_name is not null");
            return (Criteria) this;
        }

        public Criteria andEndSiteNameEqualTo(String value) {
            addCriterion("end_site_name =", value, "endSiteName");
            return (Criteria) this;
        }

        public Criteria andEndSiteNameNotEqualTo(String value) {
            addCriterion("end_site_name <>", value, "endSiteName");
            return (Criteria) this;
        }

        public Criteria andEndSiteNameGreaterThan(String value) {
            addCriterion("end_site_name >", value, "endSiteName");
            return (Criteria) this;
        }

        public Criteria andEndSiteNameGreaterThanOrEqualTo(String value) {
            addCriterion("end_site_name >=", value, "endSiteName");
            return (Criteria) this;
        }

        public Criteria andEndSiteNameLessThan(String value) {
            addCriterion("end_site_name <", value, "endSiteName");
            return (Criteria) this;
        }

        public Criteria andEndSiteNameLessThanOrEqualTo(String value) {
            addCriterion("end_site_name <=", value, "endSiteName");
            return (Criteria) this;
        }

        public Criteria andEndSiteNameLike(String value) {
            addCriterion("end_site_name like", value, "endSiteName");
            return (Criteria) this;
        }

        public Criteria andEndSiteNameNotLike(String value) {
            addCriterion("end_site_name not like", value, "endSiteName");
            return (Criteria) this;
        }

        public Criteria andEndSiteNameIn(List<String> values) {
            addCriterion("end_site_name in", values, "endSiteName");
            return (Criteria) this;
        }

        public Criteria andEndSiteNameNotIn(List<String> values) {
            addCriterion("end_site_name not in", values, "endSiteName");
            return (Criteria) this;
        }

        public Criteria andEndSiteNameBetween(String value1, String value2) {
            addCriterion("end_site_name between", value1, value2, "endSiteName");
            return (Criteria) this;
        }

        public Criteria andEndSiteNameNotBetween(String value1, String value2) {
            addCriterion("end_site_name not between", value1, value2, "endSiteName");
            return (Criteria) this;
        }

        public Criteria andEndAddressIsNull() {
            addCriterion("end_address is null");
            return (Criteria) this;
        }

        public Criteria andEndAddressIsNotNull() {
            addCriterion("end_address is not null");
            return (Criteria) this;
        }

        public Criteria andEndAddressEqualTo(String value) {
            addCriterion("end_address =", value, "endAddress");
            return (Criteria) this;
        }

        public Criteria andEndAddressNotEqualTo(String value) {
            addCriterion("end_address <>", value, "endAddress");
            return (Criteria) this;
        }

        public Criteria andEndAddressGreaterThan(String value) {
            addCriterion("end_address >", value, "endAddress");
            return (Criteria) this;
        }

        public Criteria andEndAddressGreaterThanOrEqualTo(String value) {
            addCriterion("end_address >=", value, "endAddress");
            return (Criteria) this;
        }

        public Criteria andEndAddressLessThan(String value) {
            addCriterion("end_address <", value, "endAddress");
            return (Criteria) this;
        }

        public Criteria andEndAddressLessThanOrEqualTo(String value) {
            addCriterion("end_address <=", value, "endAddress");
            return (Criteria) this;
        }

        public Criteria andEndAddressLike(String value) {
            addCriterion("end_address like", value, "endAddress");
            return (Criteria) this;
        }

        public Criteria andEndAddressNotLike(String value) {
            addCriterion("end_address not like", value, "endAddress");
            return (Criteria) this;
        }

        public Criteria andEndAddressIn(List<String> values) {
            addCriterion("end_address in", values, "endAddress");
            return (Criteria) this;
        }

        public Criteria andEndAddressNotIn(List<String> values) {
            addCriterion("end_address not in", values, "endAddress");
            return (Criteria) this;
        }

        public Criteria andEndAddressBetween(String value1, String value2) {
            addCriterion("end_address between", value1, value2, "endAddress");
            return (Criteria) this;
        }

        public Criteria andEndAddressNotBetween(String value1, String value2) {
            addCriterion("end_address not between", value1, value2, "endAddress");
            return (Criteria) this;
        }

        public Criteria andFreightIsNull() {
            addCriterion("freight is null");
            return (Criteria) this;
        }

        public Criteria andFreightIsNotNull() {
            addCriterion("freight is not null");
            return (Criteria) this;
        }

        public Criteria andFreightEqualTo(BigDecimal value) {
            addCriterion("freight =", value, "freight");
            return (Criteria) this;
        }

        public Criteria andFreightNotEqualTo(BigDecimal value) {
            addCriterion("freight <>", value, "freight");
            return (Criteria) this;
        }

        public Criteria andFreightGreaterThan(BigDecimal value) {
            addCriterion("freight >", value, "freight");
            return (Criteria) this;
        }

        public Criteria andFreightGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("freight >=", value, "freight");
            return (Criteria) this;
        }

        public Criteria andFreightLessThan(BigDecimal value) {
            addCriterion("freight <", value, "freight");
            return (Criteria) this;
        }

        public Criteria andFreightLessThanOrEqualTo(BigDecimal value) {
            addCriterion("freight <=", value, "freight");
            return (Criteria) this;
        }

        public Criteria andFreightIn(List<BigDecimal> values) {
            addCriterion("freight in", values, "freight");
            return (Criteria) this;
        }

        public Criteria andFreightNotIn(List<BigDecimal> values) {
            addCriterion("freight not in", values, "freight");
            return (Criteria) this;
        }

        public Criteria andFreightBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("freight between", value1, value2, "freight");
            return (Criteria) this;
        }

        public Criteria andFreightNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("freight not between", value1, value2, "freight");
            return (Criteria) this;
        }

        public Criteria andMaterialCostIsNull() {
            addCriterion("material_cost is null");
            return (Criteria) this;
        }

        public Criteria andMaterialCostIsNotNull() {
            addCriterion("material_cost is not null");
            return (Criteria) this;
        }

        public Criteria andMaterialCostEqualTo(BigDecimal value) {
            addCriterion("material_cost =", value, "materialCost");
            return (Criteria) this;
        }

        public Criteria andMaterialCostNotEqualTo(BigDecimal value) {
            addCriterion("material_cost <>", value, "materialCost");
            return (Criteria) this;
        }

        public Criteria andMaterialCostGreaterThan(BigDecimal value) {
            addCriterion("material_cost >", value, "materialCost");
            return (Criteria) this;
        }

        public Criteria andMaterialCostGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("material_cost >=", value, "materialCost");
            return (Criteria) this;
        }

        public Criteria andMaterialCostLessThan(BigDecimal value) {
            addCriterion("material_cost <", value, "materialCost");
            return (Criteria) this;
        }

        public Criteria andMaterialCostLessThanOrEqualTo(BigDecimal value) {
            addCriterion("material_cost <=", value, "materialCost");
            return (Criteria) this;
        }

        public Criteria andMaterialCostIn(List<BigDecimal> values) {
            addCriterion("material_cost in", values, "materialCost");
            return (Criteria) this;
        }

        public Criteria andMaterialCostNotIn(List<BigDecimal> values) {
            addCriterion("material_cost not in", values, "materialCost");
            return (Criteria) this;
        }

        public Criteria andMaterialCostBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("material_cost between", value1, value2, "materialCost");
            return (Criteria) this;
        }

        public Criteria andMaterialCostNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("material_cost not between", value1, value2, "materialCost");
            return (Criteria) this;
        }

        public Criteria andTarpaulinCostIsNull() {
            addCriterion("tarpaulin_cost is null");
            return (Criteria) this;
        }

        public Criteria andTarpaulinCostIsNotNull() {
            addCriterion("tarpaulin_cost is not null");
            return (Criteria) this;
        }

        public Criteria andTarpaulinCostEqualTo(BigDecimal value) {
            addCriterion("tarpaulin_cost =", value, "tarpaulinCost");
            return (Criteria) this;
        }

        public Criteria andTarpaulinCostNotEqualTo(BigDecimal value) {
            addCriterion("tarpaulin_cost <>", value, "tarpaulinCost");
            return (Criteria) this;
        }

        public Criteria andTarpaulinCostGreaterThan(BigDecimal value) {
            addCriterion("tarpaulin_cost >", value, "tarpaulinCost");
            return (Criteria) this;
        }

        public Criteria andTarpaulinCostGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("tarpaulin_cost >=", value, "tarpaulinCost");
            return (Criteria) this;
        }

        public Criteria andTarpaulinCostLessThan(BigDecimal value) {
            addCriterion("tarpaulin_cost <", value, "tarpaulinCost");
            return (Criteria) this;
        }

        public Criteria andTarpaulinCostLessThanOrEqualTo(BigDecimal value) {
            addCriterion("tarpaulin_cost <=", value, "tarpaulinCost");
            return (Criteria) this;
        }

        public Criteria andTarpaulinCostIn(List<BigDecimal> values) {
            addCriterion("tarpaulin_cost in", values, "tarpaulinCost");
            return (Criteria) this;
        }

        public Criteria andTarpaulinCostNotIn(List<BigDecimal> values) {
            addCriterion("tarpaulin_cost not in", values, "tarpaulinCost");
            return (Criteria) this;
        }

        public Criteria andTarpaulinCostBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("tarpaulin_cost between", value1, value2, "tarpaulinCost");
            return (Criteria) this;
        }

        public Criteria andTarpaulinCostNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("tarpaulin_cost not between", value1, value2, "tarpaulinCost");
            return (Criteria) this;
        }

        public Criteria andBeginStevedoringCostIsNull() {
            addCriterion("begin_stevedoring_cost is null");
            return (Criteria) this;
        }

        public Criteria andBeginStevedoringCostIsNotNull() {
            addCriterion("begin_stevedoring_cost is not null");
            return (Criteria) this;
        }

        public Criteria andBeginStevedoringCostEqualTo(BigDecimal value) {
            addCriterion("begin_stevedoring_cost =", value, "beginStevedoringCost");
            return (Criteria) this;
        }

        public Criteria andBeginStevedoringCostNotEqualTo(BigDecimal value) {
            addCriterion("begin_stevedoring_cost <>", value, "beginStevedoringCost");
            return (Criteria) this;
        }

        public Criteria andBeginStevedoringCostGreaterThan(BigDecimal value) {
            addCriterion("begin_stevedoring_cost >", value, "beginStevedoringCost");
            return (Criteria) this;
        }

        public Criteria andBeginStevedoringCostGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("begin_stevedoring_cost >=", value, "beginStevedoringCost");
            return (Criteria) this;
        }

        public Criteria andBeginStevedoringCostLessThan(BigDecimal value) {
            addCriterion("begin_stevedoring_cost <", value, "beginStevedoringCost");
            return (Criteria) this;
        }

        public Criteria andBeginStevedoringCostLessThanOrEqualTo(BigDecimal value) {
            addCriterion("begin_stevedoring_cost <=", value, "beginStevedoringCost");
            return (Criteria) this;
        }

        public Criteria andBeginStevedoringCostIn(List<BigDecimal> values) {
            addCriterion("begin_stevedoring_cost in", values, "beginStevedoringCost");
            return (Criteria) this;
        }

        public Criteria andBeginStevedoringCostNotIn(List<BigDecimal> values) {
            addCriterion("begin_stevedoring_cost not in", values, "beginStevedoringCost");
            return (Criteria) this;
        }

        public Criteria andBeginStevedoringCostBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("begin_stevedoring_cost between", value1, value2, "beginStevedoringCost");
            return (Criteria) this;
        }

        public Criteria andBeginStevedoringCostNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("begin_stevedoring_cost not between", value1, value2, "beginStevedoringCost");
            return (Criteria) this;
        }

        public Criteria andEndStevedoringCostIsNull() {
            addCriterion("end_stevedoring_cost is null");
            return (Criteria) this;
        }

        public Criteria andEndStevedoringCostIsNotNull() {
            addCriterion("end_stevedoring_cost is not null");
            return (Criteria) this;
        }

        public Criteria andEndStevedoringCostEqualTo(BigDecimal value) {
            addCriterion("end_stevedoring_cost =", value, "endStevedoringCost");
            return (Criteria) this;
        }

        public Criteria andEndStevedoringCostNotEqualTo(BigDecimal value) {
            addCriterion("end_stevedoring_cost <>", value, "endStevedoringCost");
            return (Criteria) this;
        }

        public Criteria andEndStevedoringCostGreaterThan(BigDecimal value) {
            addCriterion("end_stevedoring_cost >", value, "endStevedoringCost");
            return (Criteria) this;
        }

        public Criteria andEndStevedoringCostGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("end_stevedoring_cost >=", value, "endStevedoringCost");
            return (Criteria) this;
        }

        public Criteria andEndStevedoringCostLessThan(BigDecimal value) {
            addCriterion("end_stevedoring_cost <", value, "endStevedoringCost");
            return (Criteria) this;
        }

        public Criteria andEndStevedoringCostLessThanOrEqualTo(BigDecimal value) {
            addCriterion("end_stevedoring_cost <=", value, "endStevedoringCost");
            return (Criteria) this;
        }

        public Criteria andEndStevedoringCostIn(List<BigDecimal> values) {
            addCriterion("end_stevedoring_cost in", values, "endStevedoringCost");
            return (Criteria) this;
        }

        public Criteria andEndStevedoringCostNotIn(List<BigDecimal> values) {
            addCriterion("end_stevedoring_cost not in", values, "endStevedoringCost");
            return (Criteria) this;
        }

        public Criteria andEndStevedoringCostBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("end_stevedoring_cost between", value1, value2, "endStevedoringCost");
            return (Criteria) this;
        }

        public Criteria andEndStevedoringCostNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("end_stevedoring_cost not between", value1, value2, "endStevedoringCost");
            return (Criteria) this;
        }

        public Criteria andFreightSumIsNull() {
            addCriterion("freight_sum is null");
            return (Criteria) this;
        }

        public Criteria andFreightSumIsNotNull() {
            addCriterion("freight_sum is not null");
            return (Criteria) this;
        }

        public Criteria andFreightSumEqualTo(BigDecimal value) {
            addCriterion("freight_sum =", value, "freightSum");
            return (Criteria) this;
        }

        public Criteria andFreightSumNotEqualTo(BigDecimal value) {
            addCriterion("freight_sum <>", value, "freightSum");
            return (Criteria) this;
        }

        public Criteria andFreightSumGreaterThan(BigDecimal value) {
            addCriterion("freight_sum >", value, "freightSum");
            return (Criteria) this;
        }

        public Criteria andFreightSumGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("freight_sum >=", value, "freightSum");
            return (Criteria) this;
        }

        public Criteria andFreightSumLessThan(BigDecimal value) {
            addCriterion("freight_sum <", value, "freightSum");
            return (Criteria) this;
        }

        public Criteria andFreightSumLessThanOrEqualTo(BigDecimal value) {
            addCriterion("freight_sum <=", value, "freightSum");
            return (Criteria) this;
        }

        public Criteria andFreightSumIn(List<BigDecimal> values) {
            addCriterion("freight_sum in", values, "freightSum");
            return (Criteria) this;
        }

        public Criteria andFreightSumNotIn(List<BigDecimal> values) {
            addCriterion("freight_sum not in", values, "freightSum");
            return (Criteria) this;
        }

        public Criteria andFreightSumBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("freight_sum between", value1, value2, "freightSum");
            return (Criteria) this;
        }

        public Criteria andFreightSumNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("freight_sum not between", value1, value2, "freightSum");
            return (Criteria) this;
        }

        public Criteria andForwardingSiteIdIsNull() {
            addCriterion("forwarding_site_id is null");
            return (Criteria) this;
        }

        public Criteria andForwardingSiteIdIsNotNull() {
            addCriterion("forwarding_site_id is not null");
            return (Criteria) this;
        }

        public Criteria andForwardingSiteIdEqualTo(Integer value) {
            addCriterion("forwarding_site_id =", value, "forwardingSiteId");
            return (Criteria) this;
        }

        public Criteria andForwardingSiteIdNotEqualTo(Integer value) {
            addCriterion("forwarding_site_id <>", value, "forwardingSiteId");
            return (Criteria) this;
        }

        public Criteria andForwardingSiteIdGreaterThan(Integer value) {
            addCriterion("forwarding_site_id >", value, "forwardingSiteId");
            return (Criteria) this;
        }

        public Criteria andForwardingSiteIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("forwarding_site_id >=", value, "forwardingSiteId");
            return (Criteria) this;
        }

        public Criteria andForwardingSiteIdLessThan(Integer value) {
            addCriterion("forwarding_site_id <", value, "forwardingSiteId");
            return (Criteria) this;
        }

        public Criteria andForwardingSiteIdLessThanOrEqualTo(Integer value) {
            addCriterion("forwarding_site_id <=", value, "forwardingSiteId");
            return (Criteria) this;
        }

        public Criteria andForwardingSiteIdIn(List<Integer> values) {
            addCriterion("forwarding_site_id in", values, "forwardingSiteId");
            return (Criteria) this;
        }

        public Criteria andForwardingSiteIdNotIn(List<Integer> values) {
            addCriterion("forwarding_site_id not in", values, "forwardingSiteId");
            return (Criteria) this;
        }

        public Criteria andForwardingSiteIdBetween(Integer value1, Integer value2) {
            addCriterion("forwarding_site_id between", value1, value2, "forwardingSiteId");
            return (Criteria) this;
        }

        public Criteria andForwardingSiteIdNotBetween(Integer value1, Integer value2) {
            addCriterion("forwarding_site_id not between", value1, value2, "forwardingSiteId");
            return (Criteria) this;
        }

        public Criteria andForwardingSiteNameIsNull() {
            addCriterion("forwarding_site_name is null");
            return (Criteria) this;
        }

        public Criteria andForwardingSiteNameIsNotNull() {
            addCriterion("forwarding_site_name is not null");
            return (Criteria) this;
        }

        public Criteria andForwardingSiteNameEqualTo(String value) {
            addCriterion("forwarding_site_name =", value, "forwardingSiteName");
            return (Criteria) this;
        }

        public Criteria andForwardingSiteNameNotEqualTo(String value) {
            addCriterion("forwarding_site_name <>", value, "forwardingSiteName");
            return (Criteria) this;
        }

        public Criteria andForwardingSiteNameGreaterThan(String value) {
            addCriterion("forwarding_site_name >", value, "forwardingSiteName");
            return (Criteria) this;
        }

        public Criteria andForwardingSiteNameGreaterThanOrEqualTo(String value) {
            addCriterion("forwarding_site_name >=", value, "forwardingSiteName");
            return (Criteria) this;
        }

        public Criteria andForwardingSiteNameLessThan(String value) {
            addCriterion("forwarding_site_name <", value, "forwardingSiteName");
            return (Criteria) this;
        }

        public Criteria andForwardingSiteNameLessThanOrEqualTo(String value) {
            addCriterion("forwarding_site_name <=", value, "forwardingSiteName");
            return (Criteria) this;
        }

        public Criteria andForwardingSiteNameLike(String value) {
            addCriterion("forwarding_site_name like", value, "forwardingSiteName");
            return (Criteria) this;
        }

        public Criteria andForwardingSiteNameNotLike(String value) {
            addCriterion("forwarding_site_name not like", value, "forwardingSiteName");
            return (Criteria) this;
        }

        public Criteria andForwardingSiteNameIn(List<String> values) {
            addCriterion("forwarding_site_name in", values, "forwardingSiteName");
            return (Criteria) this;
        }

        public Criteria andForwardingSiteNameNotIn(List<String> values) {
            addCriterion("forwarding_site_name not in", values, "forwardingSiteName");
            return (Criteria) this;
        }

        public Criteria andForwardingSiteNameBetween(String value1, String value2) {
            addCriterion("forwarding_site_name between", value1, value2, "forwardingSiteName");
            return (Criteria) this;
        }

        public Criteria andForwardingSiteNameNotBetween(String value1, String value2) {
            addCriterion("forwarding_site_name not between", value1, value2, "forwardingSiteName");
            return (Criteria) this;
        }

        public Criteria andForwardingUnitIdIsNull() {
            addCriterion("forwarding_unit_id is null");
            return (Criteria) this;
        }

        public Criteria andForwardingUnitIdIsNotNull() {
            addCriterion("forwarding_unit_id is not null");
            return (Criteria) this;
        }

        public Criteria andForwardingUnitIdEqualTo(Integer value) {
            addCriterion("forwarding_unit_id =", value, "forwardingUnitId");
            return (Criteria) this;
        }

        public Criteria andForwardingUnitIdNotEqualTo(Integer value) {
            addCriterion("forwarding_unit_id <>", value, "forwardingUnitId");
            return (Criteria) this;
        }

        public Criteria andForwardingUnitIdGreaterThan(Integer value) {
            addCriterion("forwarding_unit_id >", value, "forwardingUnitId");
            return (Criteria) this;
        }

        public Criteria andForwardingUnitIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("forwarding_unit_id >=", value, "forwardingUnitId");
            return (Criteria) this;
        }

        public Criteria andForwardingUnitIdLessThan(Integer value) {
            addCriterion("forwarding_unit_id <", value, "forwardingUnitId");
            return (Criteria) this;
        }

        public Criteria andForwardingUnitIdLessThanOrEqualTo(Integer value) {
            addCriterion("forwarding_unit_id <=", value, "forwardingUnitId");
            return (Criteria) this;
        }

        public Criteria andForwardingUnitIdIn(List<Integer> values) {
            addCriterion("forwarding_unit_id in", values, "forwardingUnitId");
            return (Criteria) this;
        }

        public Criteria andForwardingUnitIdNotIn(List<Integer> values) {
            addCriterion("forwarding_unit_id not in", values, "forwardingUnitId");
            return (Criteria) this;
        }

        public Criteria andForwardingUnitIdBetween(Integer value1, Integer value2) {
            addCriterion("forwarding_unit_id between", value1, value2, "forwardingUnitId");
            return (Criteria) this;
        }

        public Criteria andForwardingUnitIdNotBetween(Integer value1, Integer value2) {
            addCriterion("forwarding_unit_id not between", value1, value2, "forwardingUnitId");
            return (Criteria) this;
        }

        public Criteria andForwardingUnitNameIsNull() {
            addCriterion("forwarding_unit_name is null");
            return (Criteria) this;
        }

        public Criteria andForwardingUnitNameIsNotNull() {
            addCriterion("forwarding_unit_name is not null");
            return (Criteria) this;
        }

        public Criteria andForwardingUnitNameEqualTo(String value) {
            addCriterion("forwarding_unit_name =", value, "forwardingUnitName");
            return (Criteria) this;
        }

        public Criteria andForwardingUnitNameNotEqualTo(String value) {
            addCriterion("forwarding_unit_name <>", value, "forwardingUnitName");
            return (Criteria) this;
        }

        public Criteria andForwardingUnitNameGreaterThan(String value) {
            addCriterion("forwarding_unit_name >", value, "forwardingUnitName");
            return (Criteria) this;
        }

        public Criteria andForwardingUnitNameGreaterThanOrEqualTo(String value) {
            addCriterion("forwarding_unit_name >=", value, "forwardingUnitName");
            return (Criteria) this;
        }

        public Criteria andForwardingUnitNameLessThan(String value) {
            addCriterion("forwarding_unit_name <", value, "forwardingUnitName");
            return (Criteria) this;
        }

        public Criteria andForwardingUnitNameLessThanOrEqualTo(String value) {
            addCriterion("forwarding_unit_name <=", value, "forwardingUnitName");
            return (Criteria) this;
        }

        public Criteria andForwardingUnitNameLike(String value) {
            addCriterion("forwarding_unit_name like", value, "forwardingUnitName");
            return (Criteria) this;
        }

        public Criteria andForwardingUnitNameNotLike(String value) {
            addCriterion("forwarding_unit_name not like", value, "forwardingUnitName");
            return (Criteria) this;
        }

        public Criteria andForwardingUnitNameIn(List<String> values) {
            addCriterion("forwarding_unit_name in", values, "forwardingUnitName");
            return (Criteria) this;
        }

        public Criteria andForwardingUnitNameNotIn(List<String> values) {
            addCriterion("forwarding_unit_name not in", values, "forwardingUnitName");
            return (Criteria) this;
        }

        public Criteria andForwardingUnitNameBetween(String value1, String value2) {
            addCriterion("forwarding_unit_name between", value1, value2, "forwardingUnitName");
            return (Criteria) this;
        }

        public Criteria andForwardingUnitNameNotBetween(String value1, String value2) {
            addCriterion("forwarding_unit_name not between", value1, value2, "forwardingUnitName");
            return (Criteria) this;
        }

        public Criteria andTakePlaceIsNull() {
            addCriterion("take_place is null");
            return (Criteria) this;
        }

        public Criteria andTakePlaceIsNotNull() {
            addCriterion("take_place is not null");
            return (Criteria) this;
        }

        public Criteria andTakePlaceEqualTo(String value) {
            addCriterion("take_place =", value, "takePlace");
            return (Criteria) this;
        }

        public Criteria andTakePlaceNotEqualTo(String value) {
            addCriterion("take_place <>", value, "takePlace");
            return (Criteria) this;
        }

        public Criteria andTakePlaceGreaterThan(String value) {
            addCriterion("take_place >", value, "takePlace");
            return (Criteria) this;
        }

        public Criteria andTakePlaceGreaterThanOrEqualTo(String value) {
            addCriterion("take_place >=", value, "takePlace");
            return (Criteria) this;
        }

        public Criteria andTakePlaceLessThan(String value) {
            addCriterion("take_place <", value, "takePlace");
            return (Criteria) this;
        }

        public Criteria andTakePlaceLessThanOrEqualTo(String value) {
            addCriterion("take_place <=", value, "takePlace");
            return (Criteria) this;
        }

        public Criteria andTakePlaceLike(String value) {
            addCriterion("take_place like", value, "takePlace");
            return (Criteria) this;
        }

        public Criteria andTakePlaceNotLike(String value) {
            addCriterion("take_place not like", value, "takePlace");
            return (Criteria) this;
        }

        public Criteria andTakePlaceIn(List<String> values) {
            addCriterion("take_place in", values, "takePlace");
            return (Criteria) this;
        }

        public Criteria andTakePlaceNotIn(List<String> values) {
            addCriterion("take_place not in", values, "takePlace");
            return (Criteria) this;
        }

        public Criteria andTakePlaceBetween(String value1, String value2) {
            addCriterion("take_place between", value1, value2, "takePlace");
            return (Criteria) this;
        }

        public Criteria andTakePlaceNotBetween(String value1, String value2) {
            addCriterion("take_place not between", value1, value2, "takePlace");
            return (Criteria) this;
        }

        public Criteria andTakePlaceDetailIsNull() {
            addCriterion("take_place_detail is null");
            return (Criteria) this;
        }

        public Criteria andTakePlaceDetailIsNotNull() {
            addCriterion("take_place_detail is not null");
            return (Criteria) this;
        }

        public Criteria andTakePlaceDetailEqualTo(String value) {
            addCriterion("take_place_detail =", value, "takePlaceDetail");
            return (Criteria) this;
        }

        public Criteria andTakePlaceDetailNotEqualTo(String value) {
            addCriterion("take_place_detail <>", value, "takePlaceDetail");
            return (Criteria) this;
        }

        public Criteria andTakePlaceDetailGreaterThan(String value) {
            addCriterion("take_place_detail >", value, "takePlaceDetail");
            return (Criteria) this;
        }

        public Criteria andTakePlaceDetailGreaterThanOrEqualTo(String value) {
            addCriterion("take_place_detail >=", value, "takePlaceDetail");
            return (Criteria) this;
        }

        public Criteria andTakePlaceDetailLessThan(String value) {
            addCriterion("take_place_detail <", value, "takePlaceDetail");
            return (Criteria) this;
        }

        public Criteria andTakePlaceDetailLessThanOrEqualTo(String value) {
            addCriterion("take_place_detail <=", value, "takePlaceDetail");
            return (Criteria) this;
        }

        public Criteria andTakePlaceDetailLike(String value) {
            addCriterion("take_place_detail like", value, "takePlaceDetail");
            return (Criteria) this;
        }

        public Criteria andTakePlaceDetailNotLike(String value) {
            addCriterion("take_place_detail not like", value, "takePlaceDetail");
            return (Criteria) this;
        }

        public Criteria andTakePlaceDetailIn(List<String> values) {
            addCriterion("take_place_detail in", values, "takePlaceDetail");
            return (Criteria) this;
        }

        public Criteria andTakePlaceDetailNotIn(List<String> values) {
            addCriterion("take_place_detail not in", values, "takePlaceDetail");
            return (Criteria) this;
        }

        public Criteria andTakePlaceDetailBetween(String value1, String value2) {
            addCriterion("take_place_detail between", value1, value2, "takePlaceDetail");
            return (Criteria) this;
        }

        public Criteria andTakePlaceDetailNotBetween(String value1, String value2) {
            addCriterion("take_place_detail not between", value1, value2, "takePlaceDetail");
            return (Criteria) this;
        }

        public Criteria andReceivingDepartmentIdIsNull() {
            addCriterion("receiving_department_id is null");
            return (Criteria) this;
        }

        public Criteria andReceivingDepartmentIdIsNotNull() {
            addCriterion("receiving_department_id is not null");
            return (Criteria) this;
        }

        public Criteria andReceivingDepartmentIdEqualTo(Integer value) {
            addCriterion("receiving_department_id =", value, "receivingDepartmentId");
            return (Criteria) this;
        }

        public Criteria andReceivingDepartmentIdNotEqualTo(Integer value) {
            addCriterion("receiving_department_id <>", value, "receivingDepartmentId");
            return (Criteria) this;
        }

        public Criteria andReceivingDepartmentIdGreaterThan(Integer value) {
            addCriterion("receiving_department_id >", value, "receivingDepartmentId");
            return (Criteria) this;
        }

        public Criteria andReceivingDepartmentIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("receiving_department_id >=", value, "receivingDepartmentId");
            return (Criteria) this;
        }

        public Criteria andReceivingDepartmentIdLessThan(Integer value) {
            addCriterion("receiving_department_id <", value, "receivingDepartmentId");
            return (Criteria) this;
        }

        public Criteria andReceivingDepartmentIdLessThanOrEqualTo(Integer value) {
            addCriterion("receiving_department_id <=", value, "receivingDepartmentId");
            return (Criteria) this;
        }

        public Criteria andReceivingDepartmentIdIn(List<Integer> values) {
            addCriterion("receiving_department_id in", values, "receivingDepartmentId");
            return (Criteria) this;
        }

        public Criteria andReceivingDepartmentIdNotIn(List<Integer> values) {
            addCriterion("receiving_department_id not in", values, "receivingDepartmentId");
            return (Criteria) this;
        }

        public Criteria andReceivingDepartmentIdBetween(Integer value1, Integer value2) {
            addCriterion("receiving_department_id between", value1, value2, "receivingDepartmentId");
            return (Criteria) this;
        }

        public Criteria andReceivingDepartmentIdNotBetween(Integer value1, Integer value2) {
            addCriterion("receiving_department_id not between", value1, value2, "receivingDepartmentId");
            return (Criteria) this;
        }

        public Criteria andReceivingDepartmentNameIsNull() {
            addCriterion("receiving_department_name is null");
            return (Criteria) this;
        }

        public Criteria andReceivingDepartmentNameIsNotNull() {
            addCriterion("receiving_department_name is not null");
            return (Criteria) this;
        }

        public Criteria andReceivingDepartmentNameEqualTo(String value) {
            addCriterion("receiving_department_name =", value, "receivingDepartmentName");
            return (Criteria) this;
        }

        public Criteria andReceivingDepartmentNameNotEqualTo(String value) {
            addCriterion("receiving_department_name <>", value, "receivingDepartmentName");
            return (Criteria) this;
        }

        public Criteria andReceivingDepartmentNameGreaterThan(String value) {
            addCriterion("receiving_department_name >", value, "receivingDepartmentName");
            return (Criteria) this;
        }

        public Criteria andReceivingDepartmentNameGreaterThanOrEqualTo(String value) {
            addCriterion("receiving_department_name >=", value, "receivingDepartmentName");
            return (Criteria) this;
        }

        public Criteria andReceivingDepartmentNameLessThan(String value) {
            addCriterion("receiving_department_name <", value, "receivingDepartmentName");
            return (Criteria) this;
        }

        public Criteria andReceivingDepartmentNameLessThanOrEqualTo(String value) {
            addCriterion("receiving_department_name <=", value, "receivingDepartmentName");
            return (Criteria) this;
        }

        public Criteria andReceivingDepartmentNameLike(String value) {
            addCriterion("receiving_department_name like", value, "receivingDepartmentName");
            return (Criteria) this;
        }

        public Criteria andReceivingDepartmentNameNotLike(String value) {
            addCriterion("receiving_department_name not like", value, "receivingDepartmentName");
            return (Criteria) this;
        }

        public Criteria andReceivingDepartmentNameIn(List<String> values) {
            addCriterion("receiving_department_name in", values, "receivingDepartmentName");
            return (Criteria) this;
        }

        public Criteria andReceivingDepartmentNameNotIn(List<String> values) {
            addCriterion("receiving_department_name not in", values, "receivingDepartmentName");
            return (Criteria) this;
        }

        public Criteria andReceivingDepartmentNameBetween(String value1, String value2) {
            addCriterion("receiving_department_name between", value1, value2, "receivingDepartmentName");
            return (Criteria) this;
        }

        public Criteria andReceivingDepartmentNameNotBetween(String value1, String value2) {
            addCriterion("receiving_department_name not between", value1, value2, "receivingDepartmentName");
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

        public Criteria andArrivePlaceAddressIsNull() {
            addCriterion("arrive_place_address is null");
            return (Criteria) this;
        }

        public Criteria andArrivePlaceAddressIsNotNull() {
            addCriterion("arrive_place_address is not null");
            return (Criteria) this;
        }

        public Criteria andArrivePlaceAddressEqualTo(String value) {
            addCriterion("arrive_place_address =", value, "arrivePlaceAddress");
            return (Criteria) this;
        }

        public Criteria andArrivePlaceAddressNotEqualTo(String value) {
            addCriterion("arrive_place_address <>", value, "arrivePlaceAddress");
            return (Criteria) this;
        }

        public Criteria andArrivePlaceAddressGreaterThan(String value) {
            addCriterion("arrive_place_address >", value, "arrivePlaceAddress");
            return (Criteria) this;
        }

        public Criteria andArrivePlaceAddressGreaterThanOrEqualTo(String value) {
            addCriterion("arrive_place_address >=", value, "arrivePlaceAddress");
            return (Criteria) this;
        }

        public Criteria andArrivePlaceAddressLessThan(String value) {
            addCriterion("arrive_place_address <", value, "arrivePlaceAddress");
            return (Criteria) this;
        }

        public Criteria andArrivePlaceAddressLessThanOrEqualTo(String value) {
            addCriterion("arrive_place_address <=", value, "arrivePlaceAddress");
            return (Criteria) this;
        }

        public Criteria andArrivePlaceAddressLike(String value) {
            addCriterion("arrive_place_address like", value, "arrivePlaceAddress");
            return (Criteria) this;
        }

        public Criteria andArrivePlaceAddressNotLike(String value) {
            addCriterion("arrive_place_address not like", value, "arrivePlaceAddress");
            return (Criteria) this;
        }

        public Criteria andArrivePlaceAddressIn(List<String> values) {
            addCriterion("arrive_place_address in", values, "arrivePlaceAddress");
            return (Criteria) this;
        }

        public Criteria andArrivePlaceAddressNotIn(List<String> values) {
            addCriterion("arrive_place_address not in", values, "arrivePlaceAddress");
            return (Criteria) this;
        }

        public Criteria andArrivePlaceAddressBetween(String value1, String value2) {
            addCriterion("arrive_place_address between", value1, value2, "arrivePlaceAddress");
            return (Criteria) this;
        }

        public Criteria andArrivePlaceAddressNotBetween(String value1, String value2) {
            addCriterion("arrive_place_address not between", value1, value2, "arrivePlaceAddress");
            return (Criteria) this;
        }

        public Criteria andPickUpPriceIsNull() {
            addCriterion("pick_up_price is null");
            return (Criteria) this;
        }

        public Criteria andPickUpPriceIsNotNull() {
            addCriterion("pick_up_price is not null");
            return (Criteria) this;
        }

        public Criteria andPickUpPriceEqualTo(BigDecimal value) {
            addCriterion("pick_up_price =", value, "pickUpPrice");
            return (Criteria) this;
        }

        public Criteria andPickUpPriceNotEqualTo(BigDecimal value) {
            addCriterion("pick_up_price <>", value, "pickUpPrice");
            return (Criteria) this;
        }

        public Criteria andPickUpPriceGreaterThan(BigDecimal value) {
            addCriterion("pick_up_price >", value, "pickUpPrice");
            return (Criteria) this;
        }

        public Criteria andPickUpPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("pick_up_price >=", value, "pickUpPrice");
            return (Criteria) this;
        }

        public Criteria andPickUpPriceLessThan(BigDecimal value) {
            addCriterion("pick_up_price <", value, "pickUpPrice");
            return (Criteria) this;
        }

        public Criteria andPickUpPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("pick_up_price <=", value, "pickUpPrice");
            return (Criteria) this;
        }

        public Criteria andPickUpPriceIn(List<BigDecimal> values) {
            addCriterion("pick_up_price in", values, "pickUpPrice");
            return (Criteria) this;
        }

        public Criteria andPickUpPriceNotIn(List<BigDecimal> values) {
            addCriterion("pick_up_price not in", values, "pickUpPrice");
            return (Criteria) this;
        }

        public Criteria andPickUpPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("pick_up_price between", value1, value2, "pickUpPrice");
            return (Criteria) this;
        }

        public Criteria andPickUpPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("pick_up_price not between", value1, value2, "pickUpPrice");
            return (Criteria) this;
        }

        public Criteria andTrainPriceIsNull() {
            addCriterion("train_price is null");
            return (Criteria) this;
        }

        public Criteria andTrainPriceIsNotNull() {
            addCriterion("train_price is not null");
            return (Criteria) this;
        }

        public Criteria andTrainPriceEqualTo(BigDecimal value) {
            addCriterion("train_price =", value, "trainPrice");
            return (Criteria) this;
        }

        public Criteria andTrainPriceNotEqualTo(BigDecimal value) {
            addCriterion("train_price <>", value, "trainPrice");
            return (Criteria) this;
        }

        public Criteria andTrainPriceGreaterThan(BigDecimal value) {
            addCriterion("train_price >", value, "trainPrice");
            return (Criteria) this;
        }

        public Criteria andTrainPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("train_price >=", value, "trainPrice");
            return (Criteria) this;
        }

        public Criteria andTrainPriceLessThan(BigDecimal value) {
            addCriterion("train_price <", value, "trainPrice");
            return (Criteria) this;
        }

        public Criteria andTrainPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("train_price <=", value, "trainPrice");
            return (Criteria) this;
        }

        public Criteria andTrainPriceIn(List<BigDecimal> values) {
            addCriterion("train_price in", values, "trainPrice");
            return (Criteria) this;
        }

        public Criteria andTrainPriceNotIn(List<BigDecimal> values) {
            addCriterion("train_price not in", values, "trainPrice");
            return (Criteria) this;
        }

        public Criteria andTrainPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("train_price between", value1, value2, "trainPrice");
            return (Criteria) this;
        }

        public Criteria andTrainPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("train_price not between", value1, value2, "trainPrice");
            return (Criteria) this;
        }

        public Criteria andArrivePriceIsNull() {
            addCriterion("arrive_price is null");
            return (Criteria) this;
        }

        public Criteria andArrivePriceIsNotNull() {
            addCriterion("arrive_price is not null");
            return (Criteria) this;
        }

        public Criteria andArrivePriceEqualTo(BigDecimal value) {
            addCriterion("arrive_price =", value, "arrivePrice");
            return (Criteria) this;
        }

        public Criteria andArrivePriceNotEqualTo(BigDecimal value) {
            addCriterion("arrive_price <>", value, "arrivePrice");
            return (Criteria) this;
        }

        public Criteria andArrivePriceGreaterThan(BigDecimal value) {
            addCriterion("arrive_price >", value, "arrivePrice");
            return (Criteria) this;
        }

        public Criteria andArrivePriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("arrive_price >=", value, "arrivePrice");
            return (Criteria) this;
        }

        public Criteria andArrivePriceLessThan(BigDecimal value) {
            addCriterion("arrive_price <", value, "arrivePrice");
            return (Criteria) this;
        }

        public Criteria andArrivePriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("arrive_price <=", value, "arrivePrice");
            return (Criteria) this;
        }

        public Criteria andArrivePriceIn(List<BigDecimal> values) {
            addCriterion("arrive_price in", values, "arrivePrice");
            return (Criteria) this;
        }

        public Criteria andArrivePriceNotIn(List<BigDecimal> values) {
            addCriterion("arrive_price not in", values, "arrivePrice");
            return (Criteria) this;
        }

        public Criteria andArrivePriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("arrive_price between", value1, value2, "arrivePrice");
            return (Criteria) this;
        }

        public Criteria andArrivePriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("arrive_price not between", value1, value2, "arrivePrice");
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

        public Criteria CreateDate(Date value) {
            addCriterion("create_date >", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateGreaterThanOrEqualTo(Date value) {
            addCriterion("create_date >=", value, "createDate");
            return (Criteria) this;
        }
        
        public Criteria andProjectCreateDateGreaterThanOrEqualTo(Date value) {
            addCriterion("p.create_date >=", value, "createDate");
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
        
        public Criteria andProjectCreateDateLessThanOrEqualTo(Date value) {
            addCriterion("p.create_date <=", value, "createDate");
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

        public Criteria andPrincipalIsNull() {
            addCriterion("principal is null");
            return (Criteria) this;
        }

        public Criteria andPrincipalIsNotNull() {
            addCriterion("principal is not null");
            return (Criteria) this;
        }

        public Criteria andPrincipalEqualTo(Byte value) {
            addCriterion("principal =", value, "principal");
            return (Criteria) this;
        }

        public Criteria andPrincipalNotEqualTo(Byte value) {
            addCriterion("principal <>", value, "principal");
            return (Criteria) this;
        }

        public Criteria andPrincipalGreaterThan(Byte value) {
            addCriterion("principal >", value, "principal");
            return (Criteria) this;
        }

        public Criteria andPrincipalGreaterThanOrEqualTo(Byte value) {
            addCriterion("principal >=", value, "principal");
            return (Criteria) this;
        }

        public Criteria andPrincipalLessThan(Byte value) {
            addCriterion("principal <", value, "principal");
            return (Criteria) this;
        }

        public Criteria andPrincipalLessThanOrEqualTo(Byte value) {
            addCriterion("principal <=", value, "principal");
            return (Criteria) this;
        }

        public Criteria andPrincipalIn(List<Byte> values) {
            addCriterion("principal in", values, "principal");
            return (Criteria) this;
        }

        public Criteria andPrincipalNotIn(List<Byte> values) {
            addCriterion("principal not in", values, "principal");
            return (Criteria) this;
        }

        public Criteria andPrincipalBetween(Byte value1, Byte value2) {
            addCriterion("principal between", value1, value2, "principal");
            return (Criteria) this;
        }

        public Criteria andPrincipalNotBetween(Byte value1, Byte value2) {
            addCriterion("principal not between", value1, value2, "principal");
            return (Criteria) this;
        }

        public Criteria andIsDistributionIsNull() {
            addCriterion("is__distribution is null");
            return (Criteria) this;
        }

        public Criteria andIsDistributionIsNotNull() {
            addCriterion("is__distribution is not null");
            return (Criteria) this;
        }

        public Criteria andIsDistributionEqualTo(Byte value) {
            addCriterion("is__distribution =", value, "isDistribution");
            return (Criteria) this;
        }

        public Criteria andIsDistributionNotEqualTo(Byte value) {
            addCriterion("is__distribution <>", value, "isDistribution");
            return (Criteria) this;
        }

        public Criteria andIsDistributionGreaterThan(Byte value) {
            addCriterion("is__distribution >", value, "isDistribution");
            return (Criteria) this;
        }

        public Criteria andIsDistributionGreaterThanOrEqualTo(Byte value) {
            addCriterion("is__distribution >=", value, "isDistribution");
            return (Criteria) this;
        }

        public Criteria andIsDistributionLessThan(Byte value) {
            addCriterion("is__distribution <", value, "isDistribution");
            return (Criteria) this;
        }

        public Criteria andIsDistributionLessThanOrEqualTo(Byte value) {
            addCriterion("is__distribution <=", value, "isDistribution");
            return (Criteria) this;
        }

        public Criteria andIsDistributionIn(List<Byte> values) {
            addCriterion("is__distribution in", values, "isDistribution");
            return (Criteria) this;
        }

        public Criteria andIsDistributionNotIn(List<Byte> values) {
            addCriterion("is__distribution not in", values, "isDistribution");
            return (Criteria) this;
        }

        public Criteria andIsDistributionBetween(Byte value1, Byte value2) {
            addCriterion("is__distribution between", value1, value2, "isDistribution");
            return (Criteria) this;
        }

        public Criteria andIsDistributionNotBetween(Byte value1, Byte value2) {
            addCriterion("is__distribution not between", value1, value2, "isDistribution");
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

        public Criteria andFinishDateIsNull() {
            addCriterion("finish_date is null");
            return (Criteria) this;
        }

        public Criteria andFinishDateIsNotNull() {
            addCriterion("finish_date is not null");
            return (Criteria) this;
        }

        public Criteria andFinishDateEqualTo(Date value) {
            addCriterion("finish_date =", value, "finishDate");
            return (Criteria) this;
        }

        public Criteria andFinishDateNotEqualTo(Date value) {
            addCriterion("finish_date <>", value, "finishDate");
            return (Criteria) this;
        }

        public Criteria andFinishDateGreaterThan(Date value) {
            addCriterion("finish_date >", value, "finishDate");
            return (Criteria) this;
        }

        public Criteria andFinishDateGreaterThanOrEqualTo(Date value) {
            addCriterion("finish_date >=", value, "finishDate");
            return (Criteria) this;
        }

        public Criteria andFinishDateLessThan(Date value) {
            addCriterion("finish_date <", value, "finishDate");
            return (Criteria) this;
        }

        public Criteria andFinishDateLessThanOrEqualTo(Date value) {
            addCriterion("finish_date <=", value, "finishDate");
            return (Criteria) this;
        }

        public Criteria andFinishDateIn(List<Date> values) {
            addCriterion("finish_date in", values, "finishDate");
            return (Criteria) this;
        }

        public Criteria andFinishDateNotIn(List<Date> values) {
            addCriterion("finish_date not in", values, "finishDate");
            return (Criteria) this;
        }

        public Criteria andFinishDateBetween(Date value1, Date value2) {
            addCriterion("finish_date between", value1, value2, "finishDate");
            return (Criteria) this;
        }

        public Criteria andFinishDateNotBetween(Date value1, Date value2) {
            addCriterion("finish_date not between", value1, value2, "finishDate");
            return (Criteria) this;
        }
        
        /*
         * and y.tab_name='tb_branch_group'
		   and y.sys_org_code like CONCAT(#{map.sysOrgCode},'%')
         * */
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