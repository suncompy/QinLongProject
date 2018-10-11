package com.shenhesoft.logistics.business.pojo.trainOrder;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.shenhesoft.logistics.business.pojo.TbOrder.TbOrderExample.Criteria;

public class TbTrainOrderExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TbTrainOrderExample() {
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
            addCriterion("project_code like", "%"+value+"%", "projectCode");
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

        public Criteria andBranchNameIsNull() {
            addCriterion("branch_name is null");
            return (Criteria) this;
        }

        public Criteria andBranchNameIsNotNull() {
            addCriterion("branch_name is not null");
            return (Criteria) this;
        }

        public Criteria andBranchNameEqualTo(String value) {
            addCriterion("branch_name =", value, "branchName");
            return (Criteria) this;
        }

        public Criteria andBranchNameNotEqualTo(String value) {
            addCriterion("branch_name <>", value, "branchName");
            return (Criteria) this;
        }

        public Criteria andBranchNameGreaterThan(String value) {
            addCriterion("branch_name >", value, "branchName");
            return (Criteria) this;
        }

        public Criteria andBranchNameGreaterThanOrEqualTo(String value) {
            addCriterion("branch_name >=", value, "branchName");
            return (Criteria) this;
        }

        public Criteria andBranchNameLessThan(String value) {
            addCriterion("branch_name <", value, "branchName");
            return (Criteria) this;
        }

        public Criteria andBranchNameLessThanOrEqualTo(String value) {
            addCriterion("branch_name <=", value, "branchName");
            return (Criteria) this;
        }

        public Criteria andBranchNameLike(String value) {
            addCriterion("branch_name like", value, "branchName");
            return (Criteria) this;
        }

        public Criteria andBranchNameNotLike(String value) {
            addCriterion("branch_name not like", value, "branchName");
            return (Criteria) this;
        }

        public Criteria andBranchNameIn(List<String> values) {
            addCriterion("branch_name in", values, "branchName");
            return (Criteria) this;
        }

        public Criteria andBranchNameNotIn(List<String> values) {
            addCriterion("branch_name not in", values, "branchName");
            return (Criteria) this;
        }

        public Criteria andBranchNameBetween(String value1, String value2) {
            addCriterion("branch_name between", value1, value2, "branchName");
            return (Criteria) this;
        }

        public Criteria andBranchNameNotBetween(String value1, String value2) {
            addCriterion("branch_name not between", value1, value2, "branchName");
            return (Criteria) this;
        }

        public Criteria andBeginSiteIsNull() {
            addCriterion("begin_site is null");
            return (Criteria) this;
        }

        public Criteria andBeginSiteIsNotNull() {
            addCriterion("begin_site is not null");
            return (Criteria) this;
        }

        public Criteria andBeginSiteEqualTo(String value) {
            addCriterion("begin_site =", value, "beginSite");
            return (Criteria) this;
        }

        public Criteria andBeginSiteNotEqualTo(String value) {
            addCriterion("begin_site <>", value, "beginSite");
            return (Criteria) this;
        }

        public Criteria andBeginSiteGreaterThan(String value) {
            addCriterion("begin_site >", value, "beginSite");
            return (Criteria) this;
        }

        public Criteria andBeginSiteGreaterThanOrEqualTo(String value) {
            addCriterion("begin_site >=", value, "beginSite");
            return (Criteria) this;
        }

        public Criteria andBeginSiteLessThan(String value) {
            addCriterion("begin_site <", value, "beginSite");
            return (Criteria) this;
        }

        public Criteria andBeginSiteLessThanOrEqualTo(String value) {
            addCriterion("begin_site <=", value, "beginSite");
            return (Criteria) this;
        }

        public Criteria andBeginSiteLike(String value) {
            addCriterion("begin_site like", "%"+value+"%", "beginSite");
            return (Criteria) this;
        }

        public Criteria andBeginSiteNotLike(String value) {
            addCriterion("begin_site not like", value, "beginSite");
            return (Criteria) this;
        }

        public Criteria andBeginSiteIn(List<String> values) {
            addCriterion("begin_site in", values, "beginSite");
            return (Criteria) this;
        }

        public Criteria andBeginSiteNotIn(List<String> values) {
            addCriterion("begin_site not in", values, "beginSite");
            return (Criteria) this;
        }

        public Criteria andBeginSiteBetween(String value1, String value2) {
            addCriterion("begin_site between", value1, value2, "beginSite");
            return (Criteria) this;
        }

        public Criteria andBeginSiteNotBetween(String value1, String value2) {
            addCriterion("begin_site not between", value1, value2, "beginSite");
            return (Criteria) this;
        }

        public Criteria andBeginPlaceIsNull() {
            addCriterion("begin_place is null");
            return (Criteria) this;
        }

        public Criteria andBeginPlaceIsNotNull() {
            addCriterion("begin_place is not null");
            return (Criteria) this;
        }

        public Criteria andBeginPlaceEqualTo(String value) {
            addCriterion("begin_place =", value, "beginPlace");
            return (Criteria) this;
        }

        public Criteria andBeginPlaceNotEqualTo(String value) {
            addCriterion("begin_place <>", value, "beginPlace");
            return (Criteria) this;
        }

        public Criteria andBeginPlaceGreaterThan(String value) {
            addCriterion("begin_place >", value, "beginPlace");
            return (Criteria) this;
        }

        public Criteria andBeginPlaceGreaterThanOrEqualTo(String value) {
            addCriterion("begin_place >=", value, "beginPlace");
            return (Criteria) this;
        }

        public Criteria andBeginPlaceLessThan(String value) {
            addCriterion("begin_place <", value, "beginPlace");
            return (Criteria) this;
        }

        public Criteria andBeginPlaceLessThanOrEqualTo(String value) {
            addCriterion("begin_place <=", value, "beginPlace");
            return (Criteria) this;
        }

        public Criteria andBeginPlaceLike(String value) {
            addCriterion("begin_place like", "%"+value+"%", "beginPlace");
            return (Criteria) this;
        }

        public Criteria andBeginPlaceNotLike(String value) {
            addCriterion("begin_place not like", value, "beginPlace");
            return (Criteria) this;
        }

        public Criteria andBeginPlaceIn(List<String> values) {
            addCriterion("begin_place in", values, "beginPlace");
            return (Criteria) this;
        }

        public Criteria andBeginPlaceNotIn(List<String> values) {
            addCriterion("begin_place not in", values, "beginPlace");
            return (Criteria) this;
        }

        public Criteria andBeginPlaceBetween(String value1, String value2) {
            addCriterion("begin_place between", value1, value2, "beginPlace");
            return (Criteria) this;
        }

        public Criteria andBeginPlaceNotBetween(String value1, String value2) {
            addCriterion("begin_place not between", value1, value2, "beginPlace");
            return (Criteria) this;
        }

        public Criteria andEndSiteIsNull() {
            addCriterion("end_site is null");
            return (Criteria) this;
        }

        public Criteria andEndSiteIsNotNull() {
            addCriterion("end_site is not null");
            return (Criteria) this;
        }

        public Criteria andEndSiteEqualTo(String value) {
            addCriterion("end_site =", value, "endSite");
            return (Criteria) this;
        }

        public Criteria andEndSiteNotEqualTo(String value) {
            addCriterion("end_site <>", value, "endSite");
            return (Criteria) this;
        }

        public Criteria andEndSiteGreaterThan(String value) {
            addCriterion("end_site >", value, "endSite");
            return (Criteria) this;
        }

        public Criteria andEndSiteGreaterThanOrEqualTo(String value) {
            addCriterion("end_site >=", value, "endSite");
            return (Criteria) this;
        }

        public Criteria andEndSiteLessThan(String value) {
            addCriterion("end_site <", value, "endSite");
            return (Criteria) this;
        }

        public Criteria andEndSiteLessThanOrEqualTo(String value) {
            addCriterion("end_site <=", value, "endSite");
            return (Criteria) this;
        }

        public Criteria andEndSiteLike(String value) {
            addCriterion("end_site like", "%"+value+"%", "endSite");
            return (Criteria) this;
        }

        public Criteria andEndSiteNotLike(String value) {
            addCriterion("end_site not like", value, "endSite");
            return (Criteria) this;
        }

        public Criteria andEndSiteIn(List<String> values) {
            addCriterion("end_site in", values, "endSite");
            return (Criteria) this;
        }

        public Criteria andEndSiteNotIn(List<String> values) {
            addCriterion("end_site not in", values, "endSite");
            return (Criteria) this;
        }

        public Criteria andEndSiteBetween(String value1, String value2) {
            addCriterion("end_site between", value1, value2, "endSite");
            return (Criteria) this;
        }

        public Criteria andEndSiteNotBetween(String value1, String value2) {
            addCriterion("end_site not between", value1, value2, "endSite");
            return (Criteria) this;
        }

        public Criteria andEndPlaceIsNull() {
            addCriterion("end_place is null");
            return (Criteria) this;
        }

        public Criteria andEndPlaceIsNotNull() {
            addCriterion("end_place is not null");
            return (Criteria) this;
        }

        public Criteria andEndPlaceEqualTo(String value) {
            addCriterion("end_place =", value, "endPlace");
            return (Criteria) this;
        }

        public Criteria andEndPlaceNotEqualTo(String value) {
            addCriterion("end_place <>", value, "endPlace");
            return (Criteria) this;
        }

        public Criteria andEndPlaceGreaterThan(String value) {
            addCriterion("end_place >", value, "endPlace");
            return (Criteria) this;
        }

        public Criteria andEndPlaceGreaterThanOrEqualTo(String value) {
            addCriterion("end_place >=", value, "endPlace");
            return (Criteria) this;
        }

        public Criteria andEndPlaceLessThan(String value) {
            addCriterion("end_place <", value, "endPlace");
            return (Criteria) this;
        }

        public Criteria andEndPlaceLessThanOrEqualTo(String value) {
            addCriterion("end_place <=", value, "endPlace");
            return (Criteria) this;
        }

        public Criteria andEndPlaceLike(String value) {
            addCriterion("end_place like", value, "endPlace");
            return (Criteria) this;
        }

        public Criteria andEndPlaceNotLike(String value) {
            addCriterion("end_place not like", value, "endPlace");
            return (Criteria) this;
        }

        public Criteria andEndPlaceIn(List<String> values) {
            addCriterion("end_place in", values, "endPlace");
            return (Criteria) this;
        }

        public Criteria andEndPlaceNotIn(List<String> values) {
            addCriterion("end_place not in", values, "endPlace");
            return (Criteria) this;
        }

        public Criteria andEndPlaceBetween(String value1, String value2) {
            addCriterion("end_place between", value1, value2, "endPlace");
            return (Criteria) this;
        }

        public Criteria andEndPlaceNotBetween(String value1, String value2) {
            addCriterion("end_place not between", value1, value2, "endPlace");
            return (Criteria) this;
        }

        public Criteria andPleaseCarNumIsNull() {
            addCriterion("please_car_num is null");
            return (Criteria) this;
        }

        public Criteria andPleaseCarNumIsNotNull() {
            addCriterion("please_car_num is not null");
            return (Criteria) this;
        }

        public Criteria andPleaseCarNumEqualTo(String value) {
            addCriterion("please_car_num =", value, "pleaseCarNum");
            return (Criteria) this;
        }

        public Criteria andPleaseCarNumNotEqualTo(String value) {
            addCriterion("please_car_num <>", value, "pleaseCarNum");
            return (Criteria) this;
        }

        public Criteria andPleaseCarNumGreaterThan(String value) {
            addCriterion("please_car_num >", value, "pleaseCarNum");
            return (Criteria) this;
        }

        public Criteria andPleaseCarNumGreaterThanOrEqualTo(String value) {
            addCriterion("please_car_num >=", value, "pleaseCarNum");
            return (Criteria) this;
        }

        public Criteria andPleaseCarNumLessThan(String value) {
            addCriterion("please_car_num <", value, "pleaseCarNum");
            return (Criteria) this;
        }

        public Criteria andPleaseCarNumLessThanOrEqualTo(String value) {
            addCriterion("please_car_num <=", value, "pleaseCarNum");
            return (Criteria) this;
        }

        public Criteria andPleaseCarNumLike(String value) {
            addCriterion("please_car_num like", value, "pleaseCarNum");
            return (Criteria) this;
        }

        public Criteria andPleaseCarNumNotLike(String value) {
            addCriterion("please_car_num not like", value, "pleaseCarNum");
            return (Criteria) this;
        }

        public Criteria andPleaseCarNumIn(List<String> values) {
            addCriterion("please_car_num in", values, "pleaseCarNum");
            return (Criteria) this;
        }

        public Criteria andPleaseCarNumNotIn(List<String> values) {
            addCriterion("please_car_num not in", values, "pleaseCarNum");
            return (Criteria) this;
        }

        public Criteria andPleaseCarNumBetween(String value1, String value2) {
            addCriterion("please_car_num between", value1, value2, "pleaseCarNum");
            return (Criteria) this;
        }

        public Criteria andPleaseCarNumNotBetween(String value1, String value2) {
            addCriterion("please_car_num not between", value1, value2, "pleaseCarNum");
            return (Criteria) this;
        }

        public Criteria andPleaseCarTypeIdIsNull() {
            addCriterion("please_car_type_id is null");
            return (Criteria) this;
        }

        public Criteria andPleaseCarTypeIdIsNotNull() {
            addCriterion("please_car_type_id is not null");
            return (Criteria) this;
        }

        public Criteria andPleaseCarTypeIdEqualTo(Integer value) {
            addCriterion("please_car_type_id =", value, "pleaseCarTypeId");
            return (Criteria) this;
        }

        public Criteria andPleaseCarTypeIdNotEqualTo(Integer value) {
            addCriterion("please_car_type_id <>", value, "pleaseCarTypeId");
            return (Criteria) this;
        }

        public Criteria andPleaseCarTypeIdGreaterThan(Integer value) {
            addCriterion("please_car_type_id >", value, "pleaseCarTypeId");
            return (Criteria) this;
        }

        public Criteria andPleaseCarTypeIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("please_car_type_id >=", value, "pleaseCarTypeId");
            return (Criteria) this;
        }

        public Criteria andPleaseCarTypeIdLessThan(Integer value) {
            addCriterion("please_car_type_id <", value, "pleaseCarTypeId");
            return (Criteria) this;
        }

        public Criteria andPleaseCarTypeIdLessThanOrEqualTo(Integer value) {
            addCriterion("please_car_type_id <=", value, "pleaseCarTypeId");
            return (Criteria) this;
        }

        public Criteria andPleaseCarTypeIdIn(List<Integer> values) {
            addCriterion("please_car_type_id in", values, "pleaseCarTypeId");
            return (Criteria) this;
        }

        public Criteria andPleaseCarTypeIdNotIn(List<Integer> values) {
            addCriterion("please_car_type_id not in", values, "pleaseCarTypeId");
            return (Criteria) this;
        }

        public Criteria andPleaseCarTypeIdBetween(Integer value1, Integer value2) {
            addCriterion("please_car_type_id between", value1, value2, "pleaseCarTypeId");
            return (Criteria) this;
        }

        public Criteria andPleaseCarTypeIdNotBetween(Integer value1, Integer value2) {
            addCriterion("please_car_type_id not between", value1, value2, "pleaseCarTypeId");
            return (Criteria) this;
        }

        public Criteria andEstimateWeightIsNull() {
            addCriterion("estimate_weight is null");
            return (Criteria) this;
        }

        public Criteria andEstimateWeightIsNotNull() {
            addCriterion("estimate_weight is not null");
            return (Criteria) this;
        }

        public Criteria andEstimateWeightEqualTo(String value) {
            addCriterion("estimate_weight =", value, "estimateWeight");
            return (Criteria) this;
        }

        public Criteria andEstimateWeightNotEqualTo(String value) {
            addCriterion("estimate_weight <>", value, "estimateWeight");
            return (Criteria) this;
        }

        public Criteria andEstimateWeightGreaterThan(String value) {
            addCriterion("estimate_weight >", value, "estimateWeight");
            return (Criteria) this;
        }

        public Criteria andEstimateWeightGreaterThanOrEqualTo(String value) {
            addCriterion("estimate_weight >=", value, "estimateWeight");
            return (Criteria) this;
        }

        public Criteria andEstimateWeightLessThan(String value) {
            addCriterion("estimate_weight <", value, "estimateWeight");
            return (Criteria) this;
        }

        public Criteria andEstimateWeightLessThanOrEqualTo(String value) {
            addCriterion("estimate_weight <=", value, "estimateWeight");
            return (Criteria) this;
        }

        public Criteria andEstimateWeightLike(String value) {
            addCriterion("estimate_weight like", value, "estimateWeight");
            return (Criteria) this;
        }

        public Criteria andEstimateWeightNotLike(String value) {
            addCriterion("estimate_weight not like", value, "estimateWeight");
            return (Criteria) this;
        }

        public Criteria andEstimateWeightIn(List<String> values) {
            addCriterion("estimate_weight in", values, "estimateWeight");
            return (Criteria) this;
        }

        public Criteria andEstimateWeightNotIn(List<String> values) {
            addCriterion("estimate_weight not in", values, "estimateWeight");
            return (Criteria) this;
        }

        public Criteria andEstimateWeightBetween(String value1, String value2) {
            addCriterion("estimate_weight between", value1, value2, "estimateWeight");
            return (Criteria) this;
        }

        public Criteria andEstimateWeightNotBetween(String value1, String value2) {
            addCriterion("estimate_weight not between", value1, value2, "estimateWeight");
            return (Criteria) this;
        }

        public Criteria andEstimateCostIsNull() {
            addCriterion("estimate_cost is null");
            return (Criteria) this;
        }

        public Criteria andEstimateCostIsNotNull() {
            addCriterion("estimate_cost is not null");
            return (Criteria) this;
        }

        public Criteria andEstimateCostEqualTo(String value) {
            addCriterion("estimate_cost =", value, "estimateCost");
            return (Criteria) this;
        }

        public Criteria andEstimateCostNotEqualTo(String value) {
            addCriterion("estimate_cost <>", value, "estimateCost");
            return (Criteria) this;
        }

        public Criteria andEstimateCostGreaterThan(String value) {
            addCriterion("estimate_cost >", value, "estimateCost");
            return (Criteria) this;
        }

        public Criteria andEstimateCostGreaterThanOrEqualTo(String value) {
            addCriterion("estimate_cost >=", value, "estimateCost");
            return (Criteria) this;
        }

        public Criteria andEstimateCostLessThan(String value) {
            addCriterion("estimate_cost <", value, "estimateCost");
            return (Criteria) this;
        }

        public Criteria andEstimateCostLessThanOrEqualTo(String value) {
            addCriterion("estimate_cost <=", value, "estimateCost");
            return (Criteria) this;
        }

        public Criteria andEstimateCostLike(String value) {
            addCriterion("estimate_cost like", value, "estimateCost");
            return (Criteria) this;
        }

        public Criteria andEstimateCostNotLike(String value) {
            addCriterion("estimate_cost not like", value, "estimateCost");
            return (Criteria) this;
        }

        public Criteria andEstimateCostIn(List<String> values) {
            addCriterion("estimate_cost in", values, "estimateCost");
            return (Criteria) this;
        }

        public Criteria andEstimateCostNotIn(List<String> values) {
            addCriterion("estimate_cost not in", values, "estimateCost");
            return (Criteria) this;
        }

        public Criteria andEstimateCostBetween(String value1, String value2) {
            addCriterion("estimate_cost between", value1, value2, "estimateCost");
            return (Criteria) this;
        }

        public Criteria andEstimateCostNotBetween(String value1, String value2) {
            addCriterion("estimate_cost not between", value1, value2, "estimateCost");
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
            addCriterion("cargo_name like", "%"+value+"%", "cargoName");
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

        public Criteria andAdvanceChargeAccountIsNull() {
            addCriterion("advance_charge_account is null");
            return (Criteria) this;
        }

        public Criteria andAdvanceChargeAccountIsNotNull() {
            addCriterion("advance_charge_account is not null");
            return (Criteria) this;
        }

        public Criteria andAdvanceChargeAccountEqualTo(String value) {
            addCriterion("advance_charge_account =", value, "advanceChargeAccount");
            return (Criteria) this;
        }

        public Criteria andAdvanceChargeAccountNotEqualTo(String value) {
            addCriterion("advance_charge_account <>", value, "advanceChargeAccount");
            return (Criteria) this;
        }

        public Criteria andAdvanceChargeAccountGreaterThan(String value) {
            addCriterion("advance_charge_account >", value, "advanceChargeAccount");
            return (Criteria) this;
        }

        public Criteria andAdvanceChargeAccountGreaterThanOrEqualTo(String value) {
            addCriterion("advance_charge_account >=", value, "advanceChargeAccount");
            return (Criteria) this;
        }

        public Criteria andAdvanceChargeAccountLessThan(String value) {
            addCriterion("advance_charge_account <", value, "advanceChargeAccount");
            return (Criteria) this;
        }

        public Criteria andAdvanceChargeAccountLessThanOrEqualTo(String value) {
            addCriterion("advance_charge_account <=", value, "advanceChargeAccount");
            return (Criteria) this;
        }

        public Criteria andAdvanceChargeAccountLike(String value) {
            addCriterion("advance_charge_account like", value, "advanceChargeAccount");
            return (Criteria) this;
        }

        public Criteria andAdvanceChargeAccountNotLike(String value) {
            addCriterion("advance_charge_account not like", value, "advanceChargeAccount");
            return (Criteria) this;
        }

        public Criteria andAdvanceChargeAccountIn(List<String> values) {
            addCriterion("advance_charge_account in", values, "advanceChargeAccount");
            return (Criteria) this;
        }

        public Criteria andAdvanceChargeAccountNotIn(List<String> values) {
            addCriterion("advance_charge_account not in", values, "advanceChargeAccount");
            return (Criteria) this;
        }

        public Criteria andAdvanceChargeAccountBetween(String value1, String value2) {
            addCriterion("advance_charge_account between", value1, value2, "advanceChargeAccount");
            return (Criteria) this;
        }

        public Criteria andAdvanceChargeAccountNotBetween(String value1, String value2) {
            addCriterion("advance_charge_account not between", value1, value2, "advanceChargeAccount");
            return (Criteria) this;
        }

        public Criteria andAdvanceChargeIsNull() {
            addCriterion("advance_charge is null");
            return (Criteria) this;
        }

        public Criteria andAdvanceChargeIsNotNull() {
            addCriterion("advance_charge is not null");
            return (Criteria) this;
        }

        public Criteria andAdvanceChargeEqualTo(BigDecimal value) {
            addCriterion("advance_charge =", value, "advanceCharge");
            return (Criteria) this;
        }

        public Criteria andAdvanceChargeNotEqualTo(BigDecimal value) {
            addCriterion("advance_charge <>", value, "advanceCharge");
            return (Criteria) this;
        }

        public Criteria andAdvanceChargeGreaterThan(BigDecimal value) {
            addCriterion("advance_charge >", value, "advanceCharge");
            return (Criteria) this;
        }

        public Criteria andAdvanceChargeGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("advance_charge >=", value, "advanceCharge");
            return (Criteria) this;
        }

        public Criteria andAdvanceChargeLessThan(BigDecimal value) {
            addCriterion("advance_charge <", value, "advanceCharge");
            return (Criteria) this;
        }

        public Criteria andAdvanceChargeLessThanOrEqualTo(BigDecimal value) {
            addCriterion("advance_charge <=", value, "advanceCharge");
            return (Criteria) this;
        }

        public Criteria andAdvanceChargeIn(List<BigDecimal> values) {
            addCriterion("advance_charge in", values, "advanceCharge");
            return (Criteria) this;
        }

        public Criteria andAdvanceChargeNotIn(List<BigDecimal> values) {
            addCriterion("advance_charge not in", values, "advanceCharge");
            return (Criteria) this;
        }

        public Criteria andAdvanceChargeBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("advance_charge between", value1, value2, "advanceCharge");
            return (Criteria) this;
        }

        public Criteria andAdvanceChargeNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("advance_charge not between", value1, value2, "advanceCharge");
            return (Criteria) this;
        }

        public Criteria andSureCarNumIsNull() {
            addCriterion("sure_car_num is null");
            return (Criteria) this;
        }

        public Criteria andSureCarNumIsNotNull() {
            addCriterion("sure_car_num is not null");
            return (Criteria) this;
        }

        public Criteria andSureCarNumEqualTo(Integer value) {
            addCriterion("sure_car_num =", value, "sureCarNum");
            return (Criteria) this;
        }

        public Criteria andSureCarNumNotEqualTo(Integer value) {
            addCriterion("sure_car_num <>", value, "sureCarNum");
            return (Criteria) this;
        }

        public Criteria andSureCarNumGreaterThan(Integer value) {
            addCriterion("sure_car_num >", value, "sureCarNum");
            return (Criteria) this;
        }

        public Criteria andSureCarNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("sure_car_num >=", value, "sureCarNum");
            return (Criteria) this;
        }

        public Criteria andSureCarNumLessThan(Integer value) {
            addCriterion("sure_car_num <", value, "sureCarNum");
            return (Criteria) this;
        }

        public Criteria andSureCarNumLessThanOrEqualTo(Integer value) {
            addCriterion("sure_car_num <=", value, "sureCarNum");
            return (Criteria) this;
        }

        public Criteria andSureCarNumIn(List<Integer> values) {
            addCriterion("sure_car_num in", values, "sureCarNum");
            return (Criteria) this;
        }

        public Criteria andSureCarNumNotIn(List<Integer> values) {
            addCriterion("sure_car_num not in", values, "sureCarNum");
            return (Criteria) this;
        }

        public Criteria andSureCarNumBetween(Integer value1, Integer value2) {
            addCriterion("sure_car_num between", value1, value2, "sureCarNum");
            return (Criteria) this;
        }

        public Criteria andSureCarNumNotBetween(Integer value1, Integer value2) {
            addCriterion("sure_car_num not between", value1, value2, "sureCarNum");
            return (Criteria) this;
        }

        public Criteria andSendDateIsNull() {
            addCriterion("send_date is null");
            return (Criteria) this;
        }

        public Criteria andSendDateIsNotNull() {
            addCriterion("send_date is not null");
            return (Criteria) this;
        }

        public Criteria andSendDateEqualTo(String value) {
            addCriterion("date_format(send_date, '%Y-%m-%d') =", value , "sendDate");
            return (Criteria) this;
        }

        public Criteria andSendDateNotEqualTo(Date value) {
            addCriterion("send_date <>", value, "sendDate");
            return (Criteria) this;
        }

        public Criteria andSendDateGreaterThan(Date value) {
            addCriterion("send_date >", value, "sendDate");
            return (Criteria) this;
        }

        public Criteria andSendDateGreaterThanOrEqualTo(Date value) {
            addCriterion("send_date >=", value, "sendDate");
            return (Criteria) this;
        }

        public Criteria andSendDateLessThan(Date value) {
            addCriterion("send_date <", value, "sendDate");
            return (Criteria) this;
        }

        public Criteria andSendDateLessThanOrEqualTo(Date value) {
            addCriterion("send_date <=", value, "sendDate");
            return (Criteria) this;
        }

        public Criteria andSendDateIn(List<Date> values) {
            addCriterion("send_date in", values, "sendDate");
            return (Criteria) this;
        }

        public Criteria andSendDateNotIn(List<Date> values) {
            addCriterion("send_date not in", values, "sendDate");
            return (Criteria) this;
        }

        public Criteria andSendDateBetween(Date value1, Date value2) {
            addCriterion("send_date between", value1, value2, "sendDate");
            return (Criteria) this;
        }

        public Criteria andSendDateNotBetween(Date value1, Date value2) {
            addCriterion("send_date not between", value1, value2, "sendDate");
            return (Criteria) this;
        }

        public Criteria andSendOperatorIdIsNull() {
            addCriterion("send_operator_id is null");
            return (Criteria) this;
        }

        public Criteria andSendOperatorIdIsNotNull() {
            addCriterion("send_operator_id is not null");
            return (Criteria) this;
        }

        public Criteria andSendOperatorIdEqualTo(String value) {
            addCriterion("send_operator_id =", value, "sendOperatorId");
            return (Criteria) this;
        }

        public Criteria andSendOperatorIdNotEqualTo(String value) {
            addCriterion("send_operator_id <>", value, "sendOperatorId");
            return (Criteria) this;
        }

        public Criteria andSendOperatorIdGreaterThan(String value) {
            addCriterion("send_operator_id >", value, "sendOperatorId");
            return (Criteria) this;
        }

        public Criteria andSendOperatorIdGreaterThanOrEqualTo(String value) {
            addCriterion("send_operator_id >=", value, "sendOperatorId");
            return (Criteria) this;
        }

        public Criteria andSendOperatorIdLessThan(String value) {
            addCriterion("send_operator_id <", value, "sendOperatorId");
            return (Criteria) this;
        }

        public Criteria andSendOperatorIdLessThanOrEqualTo(String value) {
            addCriterion("send_operator_id <=", value, "sendOperatorId");
            return (Criteria) this;
        }

        public Criteria andSendOperatorIdLike(String value) {
            addCriterion("send_operator_id like", value, "sendOperatorId");
            return (Criteria) this;
        }

        public Criteria andSendOperatorIdNotLike(String value) {
            addCriterion("send_operator_id not like", value, "sendOperatorId");
            return (Criteria) this;
        }

        public Criteria andSendOperatorIdIn(List<String> values) {
            addCriterion("send_operator_id in", values, "sendOperatorId");
            return (Criteria) this;
        }

        public Criteria andSendOperatorIdNotIn(List<String> values) {
            addCriterion("send_operator_id not in", values, "sendOperatorId");
            return (Criteria) this;
        }

        public Criteria andSendOperatorIdBetween(String value1, String value2) {
            addCriterion("send_operator_id between", value1, value2, "sendOperatorId");
            return (Criteria) this;
        }

        public Criteria andSendOperatorIdNotBetween(String value1, String value2) {
            addCriterion("send_operator_id not between", value1, value2, "sendOperatorId");
            return (Criteria) this;
        }

        public Criteria andUpdateLocationIdIsNull() {
            addCriterion("update_location_id is null");
            return (Criteria) this;
        }

        public Criteria andUpdateLocationIdIsNotNull() {
            addCriterion("update_location_id is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateLocationIdEqualTo(Integer value) {
            addCriterion("update_location_id =", value, "updateLocationId");
            return (Criteria) this;
        }

        public Criteria andUpdateLocationIdNotEqualTo(Integer value) {
            addCriterion("update_location_id <>", value, "updateLocationId");
            return (Criteria) this;
        }

        public Criteria andUpdateLocationIdGreaterThan(Integer value) {
            addCriterion("update_location_id >", value, "updateLocationId");
            return (Criteria) this;
        }

        public Criteria andUpdateLocationIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("update_location_id >=", value, "updateLocationId");
            return (Criteria) this;
        }

        public Criteria andUpdateLocationIdLessThan(Integer value) {
            addCriterion("update_location_id <", value, "updateLocationId");
            return (Criteria) this;
        }

        public Criteria andUpdateLocationIdLessThanOrEqualTo(Integer value) {
            addCriterion("update_location_id <=", value, "updateLocationId");
            return (Criteria) this;
        }

        public Criteria andUpdateLocationIdIn(List<Integer> values) {
            addCriterion("update_location_id in", values, "updateLocationId");
            return (Criteria) this;
        }

        public Criteria andUpdateLocationIdNotIn(List<Integer> values) {
            addCriterion("update_location_id not in", values, "updateLocationId");
            return (Criteria) this;
        }

        public Criteria andUpdateLocationIdBetween(Integer value1, Integer value2) {
            addCriterion("update_location_id between", value1, value2, "updateLocationId");
            return (Criteria) this;
        }

        public Criteria andUpdateLocationIdNotBetween(Integer value1, Integer value2) {
            addCriterion("update_location_id not between", value1, value2, "updateLocationId");
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

        public Criteria andPleaseTrainNumberIsNull() {
            addCriterion("please_train_number is null");
            return (Criteria) this;
        }

        public Criteria andPleaseTrainNumberIsNotNull() {
            addCriterion("please_train_number is not null");
            return (Criteria) this;
        }

        public Criteria andPleaseTrainNumberEqualTo(String value) {
            addCriterion("please_train_number =", value, "pleaseTrainNumber");
            return (Criteria) this;
        }

        public Criteria andPleaseTrainNumberNotEqualTo(String value) {
            addCriterion("please_train_number <>", value, "pleaseTrainNumber");
            return (Criteria) this;
        }

        public Criteria andPleaseTrainNumberGreaterThan(String value) {
            addCriterion("please_train_number >", value, "pleaseTrainNumber");
            return (Criteria) this;
        }

        public Criteria andPleaseTrainNumberGreaterThanOrEqualTo(String value) {
            addCriterion("please_train_number >=", value, "pleaseTrainNumber");
            return (Criteria) this;
        }

        public Criteria andPleaseTrainNumberLessThan(String value) {
            addCriterion("please_train_number <", value, "pleaseTrainNumber");
            return (Criteria) this;
        }

        public Criteria andPleaseTrainNumberLessThanOrEqualTo(String value) {
            addCriterion("please_train_number <=", value, "pleaseTrainNumber");
            return (Criteria) this;
        }

        public Criteria andPleaseTrainNumberLike(String value) {
            addCriterion("please_train_number like", "%"+value+"%", "pleaseTrainNumber");
            return (Criteria) this;
        }

        public Criteria andPleaseTrainNumberNotLike(String value) {
            addCriterion("please_train_number not like", value, "pleaseTrainNumber");
            return (Criteria) this;
        }

        public Criteria andPleaseTrainNumberIn(List<String> values) {
            addCriterion("please_train_number in", values, "pleaseTrainNumber");
            return (Criteria) this;
        }

        public Criteria andPleaseTrainNumberNotIn(List<String> values) {
            addCriterion("please_train_number not in", values, "pleaseTrainNumber");
            return (Criteria) this;
        }

        public Criteria andPleaseTrainNumberBetween(String value1, String value2) {
            addCriterion("please_train_number between", value1, value2, "pleaseTrainNumber");
            return (Criteria) this;
        }

        public Criteria andPleaseTrainNumberNotBetween(String value1, String value2) {
            addCriterion("please_train_number not between", value1, value2, "pleaseTrainNumber");
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

        public Criteria andEntruckDateIsNull() {
            addCriterion("entruck_date is null");
            return (Criteria) this;
        }

        public Criteria andEntruckDateIsNotNull() {
            addCriterion("entruck_date is not null");
            return (Criteria) this;
        }

        public Criteria andEntruckDateEqualTo(String value) {
            addCriterion("date_format(entruck_date, '%Y-%m-%d') =", value , "entruckDate");
            return (Criteria) this;
        }

        public Criteria andEntruckDateNotEqualTo(Date value) {
            addCriterion("entruck_date <>", value, "entruckDate");
            return (Criteria) this;
        }

        public Criteria andEntruckDateGreaterThan(Date value) {
            addCriterion("entruck_date >", value, "entruckDate");
            return (Criteria) this;
        }

        public Criteria andEntruckDateGreaterThanOrEqualTo(Date value) {
            addCriterion("entruck_date >=", value, "entruckDate");
            return (Criteria) this;
        }

        public Criteria andEntruckDateLessThan(Date value) {
            addCriterion("entruck_date <", value, "entruckDate");
            return (Criteria) this;
        }

        public Criteria andEntruckDateLessThanOrEqualTo(Date value) {
            addCriterion("entruck_date <=", value, "entruckDate");
            return (Criteria) this;
        }

        public Criteria andEntruckDateIn(List<Date> values) {
            addCriterion("entruck_date in", values, "entruckDate");
            return (Criteria) this;
        }

        public Criteria andEntruckDateNotIn(List<Date> values) {
            addCriterion("entruck_date not in", values, "entruckDate");
            return (Criteria) this;
        }

        public Criteria andEntruckDateBetween(Date value1, Date value2) {
            addCriterion("entruck_date between", value1, value2, "entruckDate");
            return (Criteria) this;
        }

        public Criteria andEntruckDateNotBetween(Date value1, Date value2) {
            addCriterion("entruck_date not between", value1, value2, "entruckDate");
            return (Criteria) this;
        }

        public Criteria andLoseCarNumIsNull() {
            addCriterion("lose_car_num is null");
            return (Criteria) this;
        }

        public Criteria andLoseCarNumIsNotNull() {
            addCriterion("lose_car_num is not null");
            return (Criteria) this;
        }

        public Criteria andLoseCarNumEqualTo(Integer value) {
            addCriterion("lose_car_num =", value, "loseCarNum");
            return (Criteria) this;
        }

        public Criteria andLoseCarNumNotEqualTo(Integer value) {
            addCriterion("lose_car_num <>", value, "loseCarNum");
            return (Criteria) this;
        }

        public Criteria andLoseCarNumGreaterThan(Integer value) {
            addCriterion("lose_car_num >", value, "loseCarNum");
            return (Criteria) this;
        }

        public Criteria andLoseCarNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("lose_car_num >=", value, "loseCarNum");
            return (Criteria) this;
        }

        public Criteria andLoseCarNumLessThan(Integer value) {
            addCriterion("lose_car_num <", value, "loseCarNum");
            return (Criteria) this;
        }

        public Criteria andLoseCarNumLessThanOrEqualTo(Integer value) {
            addCriterion("lose_car_num <=", value, "loseCarNum");
            return (Criteria) this;
        }

        public Criteria andLoseCarNumIn(List<Integer> values) {
            addCriterion("lose_car_num in", values, "loseCarNum");
            return (Criteria) this;
        }

        public Criteria andLoseCarNumNotIn(List<Integer> values) {
            addCriterion("lose_car_num not in", values, "loseCarNum");
            return (Criteria) this;
        }

        public Criteria andLoseCarNumBetween(Integer value1, Integer value2) {
            addCriterion("lose_car_num between", value1, value2, "loseCarNum");
            return (Criteria) this;
        }

        public Criteria andLoseCarNumNotBetween(Integer value1, Integer value2) {
            addCriterion("lose_car_num not between", value1, value2, "loseCarNum");
            return (Criteria) this;
        }

        public Criteria andIsExceptionIsNull() {
            addCriterion("is_exception is null");
            return (Criteria) this;
        }

        public Criteria andIsExceptionIsNotNull() {
            addCriterion("is_exception is not null");
            return (Criteria) this;
        }

        public Criteria andIsExceptionEqualTo(Byte value) {
            addCriterion("is_exception =", value, "isException");
            return (Criteria) this;
        }

        public Criteria andIsExceptionNotEqualTo(Byte value) {
            addCriterion("is_exception <>", value, "isException");
            return (Criteria) this;
        }

        public Criteria andIsExceptionGreaterThan(Byte value) {
            addCriterion("is_exception >", value, "isException");
            return (Criteria) this;
        }

        public Criteria andIsExceptionGreaterThanOrEqualTo(Byte value) {
            addCriterion("is_exception >=", value, "isException");
            return (Criteria) this;
        }

        public Criteria andIsExceptionLessThan(Byte value) {
            addCriterion("is_exception <", value, "isException");
            return (Criteria) this;
        }

        public Criteria andIsExceptionLessThanOrEqualTo(Byte value) {
            addCriterion("is_exception <=", value, "isException");
            return (Criteria) this;
        }

        public Criteria andIsExceptionIn(List<Byte> values) {
            addCriterion("is_exception in", values, "isException");
            return (Criteria) this;
        }

        public Criteria andIsExceptionNotIn(List<Byte> values) {
            addCriterion("is_exception not in", values, "isException");
            return (Criteria) this;
        }

        public Criteria andIsExceptionBetween(Byte value1, Byte value2) {
            addCriterion("is_exception between", value1, value2, "isException");
            return (Criteria) this;
        }

        public Criteria andIsExceptionNotBetween(Byte value1, Byte value2) {
            addCriterion("is_exception not between", value1, value2, "isException");
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

        public Criteria andExceptionReportDateIsNull() {
            addCriterion("exception_report_date is null");
            return (Criteria) this;
        }

        public Criteria andExceptionReportDateIsNotNull() {
            addCriterion("exception_report_date is not null");
            return (Criteria) this;
        }

        public Criteria andExceptionReportDateEqualTo(Date value) {
            addCriterion("exception_report_date =", value, "exceptionReportDate");
            return (Criteria) this;
        }

        public Criteria andExceptionReportDateNotEqualTo(Date value) {
            addCriterion("exception_report_date <>", value, "exceptionReportDate");
            return (Criteria) this;
        }

        public Criteria andExceptionReportDateGreaterThan(Date value) {
            addCriterion("exception_report_date >", value, "exceptionReportDate");
            return (Criteria) this;
        }

        public Criteria andExceptionReportDateGreaterThanOrEqualTo(Date value) {
            addCriterion("exception_report_date >=", value, "exceptionReportDate");
            return (Criteria) this;
        }

        public Criteria andExceptionReportDateLessThan(Date value) {
            addCriterion("exception_report_date <", value, "exceptionReportDate");
            return (Criteria) this;
        }

        public Criteria andExceptionReportDateLessThanOrEqualTo(Date value) {
            addCriterion("exception_report_date <=", value, "exceptionReportDate");
            return (Criteria) this;
        }

        public Criteria andExceptionReportDateIn(List<Date> values) {
            addCriterion("exception_report_date in", values, "exceptionReportDate");
            return (Criteria) this;
        }

        public Criteria andExceptionReportDateNotIn(List<Date> values) {
            addCriterion("exception_report_date not in", values, "exceptionReportDate");
            return (Criteria) this;
        }

        public Criteria andExceptionReportDateBetween(Date value1, Date value2) {
            addCriterion("exception_report_date between", value1, value2, "exceptionReportDate");
            return (Criteria) this;
        }

        public Criteria andExceptionReportDateNotBetween(Date value1, Date value2) {
            addCriterion("exception_report_date not between", value1, value2, "exceptionReportDate");
            return (Criteria) this;
        }

        public Criteria andExceptionReportPersonIsNull() {
            addCriterion("exception_report_person is null");
            return (Criteria) this;
        }

        public Criteria andExceptionReportPersonIsNotNull() {
            addCriterion("exception_report_person is not null");
            return (Criteria) this;
        }

        public Criteria andExceptionReportPersonEqualTo(String value) {
            addCriterion("exception_report_person =", value, "exceptionReportPerson");
            return (Criteria) this;
        }

        public Criteria andExceptionReportPersonNotEqualTo(String value) {
            addCriterion("exception_report_person <>", value, "exceptionReportPerson");
            return (Criteria) this;
        }

        public Criteria andExceptionReportPersonGreaterThan(String value) {
            addCriterion("exception_report_person >", value, "exceptionReportPerson");
            return (Criteria) this;
        }

        public Criteria andExceptionReportPersonGreaterThanOrEqualTo(String value) {
            addCriterion("exception_report_person >=", value, "exceptionReportPerson");
            return (Criteria) this;
        }

        public Criteria andExceptionReportPersonLessThan(String value) {
            addCriterion("exception_report_person <", value, "exceptionReportPerson");
            return (Criteria) this;
        }

        public Criteria andExceptionReportPersonLessThanOrEqualTo(String value) {
            addCriterion("exception_report_person <=", value, "exceptionReportPerson");
            return (Criteria) this;
        }

        public Criteria andExceptionReportPersonLike(String value) {
            addCriterion("exception_report_person like", value, "exceptionReportPerson");
            return (Criteria) this;
        }

        public Criteria andExceptionReportPersonNotLike(String value) {
            addCriterion("exception_report_person not like", value, "exceptionReportPerson");
            return (Criteria) this;
        }

        public Criteria andExceptionReportPersonIn(List<String> values) {
            addCriterion("exception_report_person in", values, "exceptionReportPerson");
            return (Criteria) this;
        }

        public Criteria andExceptionReportPersonNotIn(List<String> values) {
            addCriterion("exception_report_person not in", values, "exceptionReportPerson");
            return (Criteria) this;
        }

        public Criteria andExceptionReportPersonBetween(String value1, String value2) {
            addCriterion("exception_report_person between", value1, value2, "exceptionReportPerson");
            return (Criteria) this;
        }

        public Criteria andExceptionReportPersonNotBetween(String value1, String value2) {
            addCriterion("exception_report_person not between", value1, value2, "exceptionReportPerson");
            return (Criteria) this;
        }

        public Criteria andDeleteDateIsNull() {
            addCriterion("delete_date is null");
            return (Criteria) this;
        }

        public Criteria andDeleteDateIsNotNull() {
            addCriterion("delete_date is not null");
            return (Criteria) this;
        }

        public Criteria andDeleteDateEqualTo(Date value) {
            addCriterion("delete_date =", value, "deleteDate");
            return (Criteria) this;
        }

        public Criteria andDeleteDateNotEqualTo(Date value) {
            addCriterion("delete_date <>", value, "deleteDate");
            return (Criteria) this;
        }

        public Criteria andDeleteDateGreaterThan(Date value) {
            addCriterion("delete_date >", value, "deleteDate");
            return (Criteria) this;
        }

        public Criteria andDeleteDateGreaterThanOrEqualTo(Date value) {
            addCriterion("delete_date >=", value, "deleteDate");
            return (Criteria) this;
        }

        public Criteria andDeleteDateLessThan(Date value) {
            addCriterion("delete_date <", value, "deleteDate");
            return (Criteria) this;
        }

        public Criteria andDeleteDateLessThanOrEqualTo(Date value) {
            addCriterion("delete_date <=", value, "deleteDate");
            return (Criteria) this;
        }

        public Criteria andDeleteDateIn(List<Date> values) {
            addCriterion("delete_date in", values, "deleteDate");
            return (Criteria) this;
        }

        public Criteria andDeleteDateNotIn(List<Date> values) {
            addCriterion("delete_date not in", values, "deleteDate");
            return (Criteria) this;
        }

        public Criteria andDeleteDateBetween(Date value1, Date value2) {
            addCriterion("delete_date between", value1, value2, "deleteDate");
            return (Criteria) this;
        }

        public Criteria andDeleteDateNotBetween(Date value1, Date value2) {
            addCriterion("delete_date not between", value1, value2, "deleteDate");
            return (Criteria) this;
        }

        public Criteria andDeletePersonIsNull() {
            addCriterion("delete_person is null");
            return (Criteria) this;
        }

        public Criteria andDeletePersonIsNotNull() {
            addCriterion("delete_person is not null");
            return (Criteria) this;
        }

        public Criteria andDeletePersonEqualTo(String value) {
            addCriterion("delete_person =", value, "deletePerson");
            return (Criteria) this;
        }

        public Criteria andDeletePersonNotEqualTo(String value) {
            addCriterion("delete_person <>", value, "deletePerson");
            return (Criteria) this;
        }

        public Criteria andDeletePersonGreaterThan(String value) {
            addCriterion("delete_person >", value, "deletePerson");
            return (Criteria) this;
        }

        public Criteria andDeletePersonGreaterThanOrEqualTo(String value) {
            addCriterion("delete_person >=", value, "deletePerson");
            return (Criteria) this;
        }

        public Criteria andDeletePersonLessThan(String value) {
            addCriterion("delete_person <", value, "deletePerson");
            return (Criteria) this;
        }

        public Criteria andDeletePersonLessThanOrEqualTo(String value) {
            addCriterion("delete_person <=", value, "deletePerson");
            return (Criteria) this;
        }

        public Criteria andDeletePersonLike(String value) {
            addCriterion("delete_person like", value, "deletePerson");
            return (Criteria) this;
        }

        public Criteria andDeletePersonNotLike(String value) {
            addCriterion("delete_person not like", value, "deletePerson");
            return (Criteria) this;
        }

        public Criteria andDeletePersonIn(List<String> values) {
            addCriterion("delete_person in", values, "deletePerson");
            return (Criteria) this;
        }

        public Criteria andDeletePersonNotIn(List<String> values) {
            addCriterion("delete_person not in", values, "deletePerson");
            return (Criteria) this;
        }

        public Criteria andDeletePersonBetween(String value1, String value2) {
            addCriterion("delete_person between", value1, value2, "deletePerson");
            return (Criteria) this;
        }

        public Criteria andDeletePersonNotBetween(String value1, String value2) {
            addCriterion("delete_person not between", value1, value2, "deletePerson");
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

        public Criteria andArtificialReportIdIsNull() {
            addCriterion("artificial_report_id is null");
            return (Criteria) this;
        }

        public Criteria andArtificialReportIdIsNotNull() {
            addCriterion("artificial_report_id is not null");
            return (Criteria) this;
        }

        public Criteria andArtificialReportIdEqualTo(Integer value) {
            addCriterion("artificial_report_id =", value, "artificialReportId");
            return (Criteria) this;
        }

        public Criteria andArtificialReportIdNotEqualTo(Integer value) {
            addCriterion("artificial_report_id <>", value, "artificialReportId");
            return (Criteria) this;
        }

        public Criteria andArtificialReportIdGreaterThan(Integer value) {
            addCriterion("artificial_report_id >", value, "artificialReportId");
            return (Criteria) this;
        }

        public Criteria andArtificialReportIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("artificial_report_id >=", value, "artificialReportId");
            return (Criteria) this;
        }

        public Criteria andArtificialReportIdLessThan(Integer value) {
            addCriterion("artificial_report_id <", value, "artificialReportId");
            return (Criteria) this;
        }

        public Criteria andArtificialReportIdLessThanOrEqualTo(Integer value) {
            addCriterion("artificial_report_id <=", value, "artificialReportId");
            return (Criteria) this;
        }

        public Criteria andArtificialReportIdIn(List<Integer> values) {
            addCriterion("artificial_report_id in", values, "artificialReportId");
            return (Criteria) this;
        }

        public Criteria andArtificialReportIdNotIn(List<Integer> values) {
            addCriterion("artificial_report_id not in", values, "artificialReportId");
            return (Criteria) this;
        }

        public Criteria andArtificialReportIdBetween(Integer value1, Integer value2) {
            addCriterion("artificial_report_id between", value1, value2, "artificialReportId");
            return (Criteria) this;
        }

        public Criteria andArtificialReportIdNotBetween(Integer value1, Integer value2) {
            addCriterion("artificial_report_id not between", value1, value2, "artificialReportId");
            return (Criteria) this;
        }

        public Criteria andEntruckNumbeIsNull() {
            addCriterion("entruck_numbe is null");
            return (Criteria) this;
        }

        public Criteria andEntruckNumbeIsNotNull() {
            addCriterion("entruck_numbe is not null");
            return (Criteria) this;
        }

        public Criteria andEntruckNumbeEqualTo(Integer value) {
            addCriterion("entruck_numbe =", value, "entruckNumbe");
            return (Criteria) this;
        }

        public Criteria andEntruckNumbeNotEqualTo(Integer value) {
            addCriterion("entruck_numbe <>", value, "entruckNumbe");
            return (Criteria) this;
        }

        public Criteria andEntruckNumbeGreaterThan(Integer value) {
            addCriterion("entruck_numbe >", value, "entruckNumbe");
            return (Criteria) this;
        }

        public Criteria andEntruckNumbeGreaterThanOrEqualTo(Integer value) {
            addCriterion("entruck_numbe >=", value, "entruckNumbe");
            return (Criteria) this;
        }

        public Criteria andEntruckNumbeLessThan(Integer value) {
            addCriterion("entruck_numbe <", value, "entruckNumbe");
            return (Criteria) this;
        }

        public Criteria andEntruckNumbeLessThanOrEqualTo(Integer value) {
            addCriterion("entruck_numbe <=", value, "entruckNumbe");
            return (Criteria) this;
        }

        public Criteria andEntruckNumbeIn(List<Integer> values) {
            addCriterion("entruck_numbe in", values, "entruckNumbe");
            return (Criteria) this;
        }

        public Criteria andEntruckNumbeNotIn(List<Integer> values) {
            addCriterion("entruck_numbe not in", values, "entruckNumbe");
            return (Criteria) this;
        }

        public Criteria andEntruckNumbeBetween(Integer value1, Integer value2) {
            addCriterion("entruck_numbe between", value1, value2, "entruckNumbe");
            return (Criteria) this;
        }

        public Criteria andEntruckNumbeNotBetween(Integer value1, Integer value2) {
            addCriterion("entruck_numbe not between", value1, value2, "entruckNumbe");
            return (Criteria) this;
        }

        public Criteria andContainerNumsIsNull() {
            addCriterion("container_nums is null");
            return (Criteria) this;
        }

        public Criteria andContainerNumsIsNotNull() {
            addCriterion("container_nums is not null");
            return (Criteria) this;
        }

        public Criteria andContainerNumsEqualTo(Integer value) {
            addCriterion("container_nums =", value, "containerNums");
            return (Criteria) this;
        }

        public Criteria andContainerNumsNotEqualTo(Integer value) {
            addCriterion("container_nums <>", value, "containerNums");
            return (Criteria) this;
        }

        public Criteria andContainerNumsGreaterThan(Integer value) {
            addCriterion("container_nums >", value, "containerNums");
            return (Criteria) this;
        }

        public Criteria andContainerNumsGreaterThanOrEqualTo(Integer value) {
            addCriterion("container_nums >=", value, "containerNums");
            return (Criteria) this;
        }

        public Criteria andContainerNumsLessThan(Integer value) {
            addCriterion("container_nums <", value, "containerNums");
            return (Criteria) this;
        }

        public Criteria andContainerNumsLessThanOrEqualTo(Integer value) {
            addCriterion("container_nums <=", value, "containerNums");
            return (Criteria) this;
        }

        public Criteria andContainerNumsIn(List<Integer> values) {
            addCriterion("container_nums in", values, "containerNums");
            return (Criteria) this;
        }

        public Criteria andContainerNumsNotIn(List<Integer> values) {
            addCriterion("container_nums not in", values, "containerNums");
            return (Criteria) this;
        }

        public Criteria andContainerNumsBetween(Integer value1, Integer value2) {
            addCriterion("container_nums between", value1, value2, "containerNums");
            return (Criteria) this;
        }

        public Criteria andContainerNumsNotBetween(Integer value1, Integer value2) {
            addCriterion("container_nums not between", value1, value2, "containerNums");
            return (Criteria) this;
        }

        public Criteria andEntruckWeightIsNull() {
            addCriterion("entruck_weight is null");
            return (Criteria) this;
        }

        public Criteria andEntruckWeightIsNotNull() {
            addCriterion("entruck_weight is not null");
            return (Criteria) this;
        }

        public Criteria andEntruckWeightEqualTo(BigDecimal value) {
            addCriterion("entruck_weight =", value, "entruckWeight");
            return (Criteria) this;
        }

        public Criteria andEntruckWeightNotEqualTo(BigDecimal value) {
            addCriterion("entruck_weight <>", value, "entruckWeight");
            return (Criteria) this;
        }

        public Criteria andEntruckWeightGreaterThan(BigDecimal value) {
            addCriterion("entruck_weight >", value, "entruckWeight");
            return (Criteria) this;
        }

        public Criteria andEntruckWeightGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("entruck_weight >=", value, "entruckWeight");
            return (Criteria) this;
        }

        public Criteria andEntruckWeightLessThan(BigDecimal value) {
            addCriterion("entruck_weight <", value, "entruckWeight");
            return (Criteria) this;
        }

        public Criteria andEntruckWeightLessThanOrEqualTo(BigDecimal value) {
            addCriterion("entruck_weight <=", value, "entruckWeight");
            return (Criteria) this;
        }

        public Criteria andEntruckWeightIn(List<BigDecimal> values) {
            addCriterion("entruck_weight in", values, "entruckWeight");
            return (Criteria) this;
        }

        public Criteria andEntruckWeightNotIn(List<BigDecimal> values) {
            addCriterion("entruck_weight not in", values, "entruckWeight");
            return (Criteria) this;
        }

        public Criteria andEntruckWeightBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("entruck_weight between", value1, value2, "entruckWeight");
            return (Criteria) this;
        }

        public Criteria andEntruckWeightNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("entruck_weight not between", value1, value2, "entruckWeight");
            return (Criteria) this;
        }

        public Criteria andArriveWeightIsNull() {
            addCriterion("arrive_weight is null");
            return (Criteria) this;
        }

        public Criteria andArriveWeightIsNotNull() {
            addCriterion("arrive_weight is not null");
            return (Criteria) this;
        }

        public Criteria andArriveWeightEqualTo(BigDecimal value) {
            addCriterion("arrive_weight =", value, "arriveWeight");
            return (Criteria) this;
        }

        public Criteria andArriveWeightNotEqualTo(BigDecimal value) {
            addCriterion("arrive_weight <>", value, "arriveWeight");
            return (Criteria) this;
        }

        public Criteria andArriveWeightGreaterThan(BigDecimal value) {
            addCriterion("arrive_weight >", value, "arriveWeight");
            return (Criteria) this;
        }

        public Criteria andArriveWeightGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("arrive_weight >=", value, "arriveWeight");
            return (Criteria) this;
        }

        public Criteria andArriveWeightLessThan(BigDecimal value) {
            addCriterion("arrive_weight <", value, "arriveWeight");
            return (Criteria) this;
        }

        public Criteria andArriveWeightLessThanOrEqualTo(BigDecimal value) {
            addCriterion("arrive_weight <=", value, "arriveWeight");
            return (Criteria) this;
        }

        public Criteria andArriveWeightIn(List<BigDecimal> values) {
            addCriterion("arrive_weight in", values, "arriveWeight");
            return (Criteria) this;
        }

        public Criteria andArriveWeightNotIn(List<BigDecimal> values) {
            addCriterion("arrive_weight not in", values, "arriveWeight");
            return (Criteria) this;
        }

        public Criteria andArriveWeightBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("arrive_weight between", value1, value2, "arriveWeight");
            return (Criteria) this;
        }

        public Criteria andArriveWeightNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("arrive_weight not between", value1, value2, "arriveWeight");
            return (Criteria) this;
        }

        public Criteria andArriveDateIsNull() {
            addCriterion("arrive_date is null");
            return (Criteria) this;
        }

        public Criteria andArriveDateIsNotNull() {
            addCriterion("arrive_date is not null");
            return (Criteria) this;
        }

        public Criteria andArriveDateEqualTo(String value) {
            addCriterion("date_format(arrive_date, '%Y-%m-%d') =", value , "arriveDate");
            return (Criteria) this;
        }

        public Criteria andArriveDateNotEqualTo(Date value) {
            addCriterion("arrive_date <>", value, "arriveDate");
            return (Criteria) this;
        }

        public Criteria andArriveDateGreaterThan(Date value) {
            addCriterion("arrive_date >", value, "arriveDate");
            return (Criteria) this;
        }

        public Criteria andArriveDateGreaterThanOrEqualTo(Date value) {
            addCriterion("arrive_date >=", value, "arriveDate");
            return (Criteria) this;
        }

        public Criteria andArriveDateLessThan(Date value) {
            addCriterion("arrive_date <", value, "arriveDate");
            return (Criteria) this;
        }

        public Criteria andArriveDateLessThanOrEqualTo(Date value) {
            addCriterion("arrive_date <=", value, "arriveDate");
            return (Criteria) this;
        }

        public Criteria andArriveDateIn(List<Date> values) {
            addCriterion("arrive_date in", values, "arriveDate");
            return (Criteria) this;
        }

        public Criteria andArriveDateNotIn(List<Date> values) {
            addCriterion("arrive_date not in", values, "arriveDate");
            return (Criteria) this;
        }

        public Criteria andArriveDateBetween(Date value1, Date value2) {
            addCriterion("arrive_date between", value1, value2, "arriveDate");
            return (Criteria) this;
        }

        public Criteria andArriveDateNotBetween(Date value1, Date value2) {
            addCriterion("arrive_date not between", value1, value2, "arriveDate");
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