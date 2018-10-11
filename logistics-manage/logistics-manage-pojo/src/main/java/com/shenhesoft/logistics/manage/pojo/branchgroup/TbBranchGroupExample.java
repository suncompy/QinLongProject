package com.shenhesoft.logistics.manage.pojo.branchgroup;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TbBranchGroupExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TbBranchGroupExample() {
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

        public Criteria andCodeIsNull() {
            addCriterion("code is null");
            return (Criteria) this;
        }

        public Criteria andCodeIsNotNull() {
            addCriterion("code is not null");
            return (Criteria) this;
        }

        public Criteria andCodeEqualTo(String value) {
            addCriterion("code =", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotEqualTo(String value) {
            addCriterion("code <>", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeGreaterThan(String value) {
            addCriterion("code >", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeGreaterThanOrEqualTo(String value) {
            addCriterion("code >=", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeLessThan(String value) {
            addCriterion("code <", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeLessThanOrEqualTo(String value) {
            addCriterion("code <=", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeLike(String value) {
            addCriterion("code like", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotLike(String value) {
            addCriterion("code not like", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeIn(List<String> values) {
            addCriterion("code in", values, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotIn(List<String> values) {
            addCriterion("code not in", values, "code");
            return (Criteria) this;
        }

        public Criteria andCodeBetween(String value1, String value2) {
            addCriterion("code between", value1, value2, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotBetween(String value1, String value2) {
            addCriterion("code not between", value1, value2, "code");
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
            addCriterion("name like", "%"+value+"%", "name");
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

        public Criteria andShortNameIsNull() {
            addCriterion("short_name is null");
            return (Criteria) this;
        }

        public Criteria andShortNameIsNotNull() {
            addCriterion("short_name is not null");
            return (Criteria) this;
        }

        public Criteria andShortNameEqualTo(String value) {
            addCriterion("short_name =", value, "shortName");
            return (Criteria) this;
        }

        public Criteria andShortNameNotEqualTo(String value) {
            addCriterion("short_name <>", value, "shortName");
            return (Criteria) this;
        }

        public Criteria andShortNameGreaterThan(String value) {
            addCriterion("short_name >", value, "shortName");
            return (Criteria) this;
        }

        public Criteria andShortNameGreaterThanOrEqualTo(String value) {
            addCriterion("short_name >=", value, "shortName");
            return (Criteria) this;
        }

        public Criteria andShortNameLessThan(String value) {
            addCriterion("short_name <", value, "shortName");
            return (Criteria) this;
        }

        public Criteria andShortNameLessThanOrEqualTo(String value) {
            addCriterion("short_name <=", value, "shortName");
            return (Criteria) this;
        }

        public Criteria andShortNameLike(String value) {
            addCriterion("short_name like",  "%"+value+"%", "shortName");
            return (Criteria) this;
        }

        public Criteria andShortNameNotLike(String value) {
            addCriterion("short_name not like", value, "shortName");
            return (Criteria) this;
        }

        public Criteria andShortNameIn(List<String> values) {
            addCriterion("short_name in", values, "shortName");
            return (Criteria) this;
        }

        public Criteria andShortNameNotIn(List<String> values) {
            addCriterion("short_name not in", values, "shortName");
            return (Criteria) this;
        }

        public Criteria andShortNameBetween(String value1, String value2) {
            addCriterion("short_name between", value1, value2, "shortName");
            return (Criteria) this;
        }

        public Criteria andShortNameNotBetween(String value1, String value2) {
            addCriterion("short_name not between", value1, value2, "shortName");
            return (Criteria) this;
        }

        public Criteria andShortCodeIsNull() {
            addCriterion("short_code is null");
            return (Criteria) this;
        }

        public Criteria andShortCodeIsNotNull() {
            addCriterion("short_code is not null");
            return (Criteria) this;
        }

        public Criteria andShortCodeEqualTo(String value) {
            addCriterion("short_code =", value, "shortCode");
            return (Criteria) this;
        }

        public Criteria andShortCodeNotEqualTo(String value) {
            addCriterion("short_code <>", value, "shortCode");
            return (Criteria) this;
        }

        public Criteria andShortCodeGreaterThan(String value) {
            addCriterion("short_code >", value, "shortCode");
            return (Criteria) this;
        }

        public Criteria andShortCodeGreaterThanOrEqualTo(String value) {
            addCriterion("short_code >=", value, "shortCode");
            return (Criteria) this;
        }

        public Criteria andShortCodeLessThan(String value) {
            addCriterion("short_code <", value, "shortCode");
            return (Criteria) this;
        }

        public Criteria andShortCodeLessThanOrEqualTo(String value) {
            addCriterion("short_code <=", value, "shortCode");
            return (Criteria) this;
        }

        public Criteria andShortCodeLike(String value) {
            addCriterion("short_code like",  "%"+value+"%", "shortCode");
            return (Criteria) this;
        }

        public Criteria andShortCodeNotLike(String value) {
            addCriterion("short_code not like", value, "shortCode");
            return (Criteria) this;
        }

        public Criteria andShortCodeIn(List<String> values) {
            addCriterion("short_code in", values, "shortCode");
            return (Criteria) this;
        }

        public Criteria andShortCodeNotIn(List<String> values) {
            addCriterion("short_code not in", values, "shortCode");
            return (Criteria) this;
        }

        public Criteria andShortCodeBetween(String value1, String value2) {
            addCriterion("short_code between", value1, value2, "shortCode");
            return (Criteria) this;
        }

        public Criteria andShortCodeNotBetween(String value1, String value2) {
            addCriterion("short_code not between", value1, value2, "shortCode");
            return (Criteria) this;
        }

        public Criteria andAscriptionIdIsNull() {
            addCriterion("ascription_id is null");
            return (Criteria) this;
        }

        public Criteria andAscriptionIdIsNotNull() {
            addCriterion("ascription_id is not null");
            return (Criteria) this;
        }

        public Criteria andAscriptionIdEqualTo(Integer value) {
            addCriterion("ascription_id =", value, "ascriptionId");
            return (Criteria) this;
        }

        public Criteria andAscriptionIdNotEqualTo(Integer value) {
            addCriterion("ascription_id <>", value, "ascriptionId");
            return (Criteria) this;
        }

        public Criteria andAscriptionIdGreaterThan(Integer value) {
            addCriterion("ascription_id >", value, "ascriptionId");
            return (Criteria) this;
        }

        public Criteria andAscriptionIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("ascription_id >=", value, "ascriptionId");
            return (Criteria) this;
        }

        public Criteria andAscriptionIdLessThan(Integer value) {
            addCriterion("ascription_id <", value, "ascriptionId");
            return (Criteria) this;
        }

        public Criteria andAscriptionIdLessThanOrEqualTo(Integer value) {
            addCriterion("ascription_id <=", value, "ascriptionId");
            return (Criteria) this;
        }

        public Criteria andAscriptionIdIn(List<Integer> values) {
            addCriterion("ascription_id in", values, "ascriptionId");
            return (Criteria) this;
        }

        public Criteria andAscriptionIdNotIn(List<Integer> values) {
            addCriterion("ascription_id not in", values, "ascriptionId");
            return (Criteria) this;
        }

        public Criteria andAscriptionIdBetween(Integer value1, Integer value2) {
            addCriterion("ascription_id between", value1, value2, "ascriptionId");
            return (Criteria) this;
        }

        public Criteria andAscriptionIdNotBetween(Integer value1, Integer value2) {
            addCriterion("ascription_id not between", value1, value2, "ascriptionId");
            return (Criteria) this;
        }

        public Criteria andAscriptionNameIsNull() {
            addCriterion("ascription_name is null");
            return (Criteria) this;
        }

        public Criteria andAscriptionNameIsNotNull() {
            addCriterion("ascription_name is not null");
            return (Criteria) this;
        }

        public Criteria andAscriptionNameEqualTo(String value) {
            addCriterion("ascription_name =", value, "ascriptionName");
            return (Criteria) this;
        }

        public Criteria andAscriptionNameNotEqualTo(String value) {
            addCriterion("ascription_name <>", value, "ascriptionName");
            return (Criteria) this;
        }

        public Criteria andAscriptionNameGreaterThan(String value) {
            addCriterion("ascription_name >", value, "ascriptionName");
            return (Criteria) this;
        }

        public Criteria andAscriptionNameGreaterThanOrEqualTo(String value) {
            addCriterion("ascription_name >=", value, "ascriptionName");
            return (Criteria) this;
        }

        public Criteria andAscriptionNameLessThan(String value) {
            addCriterion("ascription_name <", value, "ascriptionName");
            return (Criteria) this;
        }

        public Criteria andAscriptionNameLessThanOrEqualTo(String value) {
            addCriterion("ascription_name <=", value, "ascriptionName");
            return (Criteria) this;
        }

        public Criteria andAscriptionNameLike(String value) {
            addCriterion("ascription_name like",  "%"+value+"%", "ascriptionName");
            return (Criteria) this;
        }

        public Criteria andAscriptionNameNotLike(String value) {
            addCriterion("ascription_name not like", value, "ascriptionName");
            return (Criteria) this;
        }

        public Criteria andAscriptionNameIn(List<String> values) {
            addCriterion("ascription_name in", values, "ascriptionName");
            return (Criteria) this;
        }

        public Criteria andAscriptionNameNotIn(List<String> values) {
            addCriterion("ascription_name not in", values, "ascriptionName");
            return (Criteria) this;
        }

        public Criteria andAscriptionNameBetween(String value1, String value2) {
            addCriterion("ascription_name between", value1, value2, "ascriptionName");
            return (Criteria) this;
        }

        public Criteria andAscriptionNameNotBetween(String value1, String value2) {
            addCriterion("ascription_name not between", value1, value2, "ascriptionName");
            return (Criteria) this;
        }

        public Criteria andLevelIsNull() {
            addCriterion("level is null");
            return (Criteria) this;
        }

        public Criteria andLevelIsNotNull() {
            addCriterion("level is not null");
            return (Criteria) this;
        }

        public Criteria andLevelEqualTo(Byte value) {
            addCriterion("level =", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelNotEqualTo(Byte value) {
            addCriterion("level <>", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelGreaterThan(Byte value) {
            addCriterion("level >", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelGreaterThanOrEqualTo(Byte value) {
            addCriterion("level >=", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelLessThan(Byte value) {
            addCriterion("level <", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelLessThanOrEqualTo(Byte value) {
            addCriterion("level <=", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelIn(List<Byte> values) {
            addCriterion("level in", values, "level");
            return (Criteria) this;
        }

        public Criteria andLevelNotIn(List<Byte> values) {
            addCriterion("level not in", values, "level");
            return (Criteria) this;
        }

        public Criteria andLevelBetween(Byte value1, Byte value2) {
            addCriterion("level between", value1, value2, "level");
            return (Criteria) this;
        }

        public Criteria andLevelNotBetween(Byte value1, Byte value2) {
            addCriterion("level not between", value1, value2, "level");
            return (Criteria) this;
        }

        public Criteria andResponsiblerIsNull() {
            addCriterion("responsibler is null");
            return (Criteria) this;
        }

        public Criteria andResponsiblerIsNotNull() {
            addCriterion("responsibler is not null");
            return (Criteria) this;
        }

        public Criteria andResponsiblerEqualTo(String value) {
            addCriterion("responsibler =", value, "responsibler");
            return (Criteria) this;
        }

        public Criteria andResponsiblerNotEqualTo(String value) {
            addCriterion("responsibler <>", value, "responsibler");
            return (Criteria) this;
        }

        public Criteria andResponsiblerGreaterThan(String value) {
            addCriterion("responsibler >", value, "responsibler");
            return (Criteria) this;
        }

        public Criteria andResponsiblerGreaterThanOrEqualTo(String value) {
            addCriterion("responsibler >=", value, "responsibler");
            return (Criteria) this;
        }

        public Criteria andResponsiblerLessThan(String value) {
            addCriterion("responsibler <", value, "responsibler");
            return (Criteria) this;
        }

        public Criteria andResponsiblerLessThanOrEqualTo(String value) {
            addCriterion("responsibler <=", value, "responsibler");
            return (Criteria) this;
        }

        public Criteria andResponsiblerLike(String value) {
            addCriterion("responsibler like", value, "responsibler");
            return (Criteria) this;
        }

        public Criteria andResponsiblerNotLike(String value) {
            addCriterion("responsibler not like", value, "responsibler");
            return (Criteria) this;
        }

        public Criteria andResponsiblerIn(List<String> values) {
            addCriterion("responsibler in", values, "responsibler");
            return (Criteria) this;
        }

        public Criteria andResponsiblerNotIn(List<String> values) {
            addCriterion("responsibler not in", values, "responsibler");
            return (Criteria) this;
        }

        public Criteria andResponsiblerBetween(String value1, String value2) {
            addCriterion("responsibler between", value1, value2, "responsibler");
            return (Criteria) this;
        }

        public Criteria andResponsiblerNotBetween(String value1, String value2) {
            addCriterion("responsibler not between", value1, value2, "responsibler");
            return (Criteria) this;
        }

        public Criteria andResponsibleridIsNull() {
            addCriterion("responsiblerId is null");
            return (Criteria) this;
        }

        public Criteria andResponsibleridIsNotNull() {
            addCriterion("responsiblerId is not null");
            return (Criteria) this;
        }

        public Criteria andResponsibleridEqualTo(Integer value) {
            addCriterion("responsiblerId =", value, "responsiblerid");
            return (Criteria) this;
        }

        public Criteria andResponsibleridNotEqualTo(Integer value) {
            addCriterion("responsiblerId <>", value, "responsiblerid");
            return (Criteria) this;
        }

        public Criteria andResponsibleridGreaterThan(Integer value) {
            addCriterion("responsiblerId >", value, "responsiblerid");
            return (Criteria) this;
        }

        public Criteria andResponsibleridGreaterThanOrEqualTo(Integer value) {
            addCriterion("responsiblerId >=", value, "responsiblerid");
            return (Criteria) this;
        }

        public Criteria andResponsibleridLessThan(Integer value) {
            addCriterion("responsiblerId <", value, "responsiblerid");
            return (Criteria) this;
        }

        public Criteria andResponsibleridLessThanOrEqualTo(Integer value) {
            addCriterion("responsiblerId <=", value, "responsiblerid");
            return (Criteria) this;
        }

        public Criteria andResponsibleridIn(List<Integer> values) {
            addCriterion("responsiblerId in", values, "responsiblerid");
            return (Criteria) this;
        }

        public Criteria andResponsibleridNotIn(List<Integer> values) {
            addCriterion("responsiblerId not in", values, "responsiblerid");
            return (Criteria) this;
        }

        public Criteria andResponsibleridBetween(Integer value1, Integer value2) {
            addCriterion("responsiblerId between", value1, value2, "responsiblerid");
            return (Criteria) this;
        }

        public Criteria andResponsibleridNotBetween(Integer value1, Integer value2) {
            addCriterion("responsiblerId not between", value1, value2, "responsiblerid");
            return (Criteria) this;
        }

        public Criteria andAreaIdIsNull() {
            addCriterion("area_id is null");
            return (Criteria) this;
        }

        public Criteria andAreaIdIsNotNull() {
            addCriterion("area_id is not null");
            return (Criteria) this;
        }

        public Criteria andAreaIdEqualTo(String value) {
            addCriterion("area_id =", value, "areaId");
            return (Criteria) this;
        }

        public Criteria andAreaIdNotEqualTo(String value) {
            addCriterion("area_id <>", value, "areaId");
            return (Criteria) this;
        }

        public Criteria andAreaIdGreaterThan(String value) {
            addCriterion("area_id >", value, "areaId");
            return (Criteria) this;
        }

        public Criteria andAreaIdGreaterThanOrEqualTo(String value) {
            addCriterion("area_id >=", value, "areaId");
            return (Criteria) this;
        }

        public Criteria andAreaIdLessThan(String value) {
            addCriterion("area_id <", value, "areaId");
            return (Criteria) this;
        }

        public Criteria andAreaIdLessThanOrEqualTo(String value) {
            addCriterion("area_id <=", value, "areaId");
            return (Criteria) this;
        }

        public Criteria andAreaIdLike(String value) {
            addCriterion("area_id like", value, "areaId");
            return (Criteria) this;
        }

        public Criteria andAreaIdNotLike(String value) {
            addCriterion("area_id not like", value, "areaId");
            return (Criteria) this;
        }

        public Criteria andAreaIdIn(List<String> values) {
            addCriterion("area_id in", values, "areaId");
            return (Criteria) this;
        }

        public Criteria andAreaIdNotIn(List<String> values) {
            addCriterion("area_id not in", values, "areaId");
            return (Criteria) this;
        }

        public Criteria andAreaIdBetween(String value1, String value2) {
            addCriterion("area_id between", value1, value2, "areaId");
            return (Criteria) this;
        }

        public Criteria andAreaIdNotBetween(String value1, String value2) {
            addCriterion("area_id not between", value1, value2, "areaId");
            return (Criteria) this;
        }

        public Criteria andAddressIsNull() {
            addCriterion("address is null");
            return (Criteria) this;
        }

        public Criteria andAddressIsNotNull() {
            addCriterion("address is not null");
            return (Criteria) this;
        }

        public Criteria andAddressEqualTo(String value) {
            addCriterion("address =", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotEqualTo(String value) {
            addCriterion("address <>", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThan(String value) {
            addCriterion("address >", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThanOrEqualTo(String value) {
            addCriterion("address >=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThan(String value) {
            addCriterion("address <", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThanOrEqualTo(String value) {
            addCriterion("address <=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLike(String value) {
            addCriterion("address like", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotLike(String value) {
            addCriterion("address not like", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressIn(List<String> values) {
            addCriterion("address in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotIn(List<String> values) {
            addCriterion("address not in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressBetween(String value1, String value2) {
            addCriterion("address between", value1, value2, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotBetween(String value1, String value2) {
            addCriterion("address not between", value1, value2, "address");
            return (Criteria) this;
        }

        public Criteria andRelationTrainLocationIdIsNull() {
            addCriterion("relation_train_location_id is null");
            return (Criteria) this;
        }

        public Criteria andRelationTrainLocationIdIsNotNull() {
            addCriterion("relation_train_location_id is not null");
            return (Criteria) this;
        }

        public Criteria andRelationTrainLocationIdEqualTo(Integer value) {
            addCriterion("relation_train_location_id =", value, "relationTrainLocationId");
            return (Criteria) this;
        }

        public Criteria andRelationTrainLocationIdNotEqualTo(Integer value) {
            addCriterion("relation_train_location_id <>", value, "relationTrainLocationId");
            return (Criteria) this;
        }

        public Criteria andRelationTrainLocationIdGreaterThan(Integer value) {
            addCriterion("relation_train_location_id >", value, "relationTrainLocationId");
            return (Criteria) this;
        }

        public Criteria andRelationTrainLocationIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("relation_train_location_id >=", value, "relationTrainLocationId");
            return (Criteria) this;
        }

        public Criteria andRelationTrainLocationIdLessThan(Integer value) {
            addCriterion("relation_train_location_id <", value, "relationTrainLocationId");
            return (Criteria) this;
        }

        public Criteria andRelationTrainLocationIdLessThanOrEqualTo(Integer value) {
            addCriterion("relation_train_location_id <=", value, "relationTrainLocationId");
            return (Criteria) this;
        }

        public Criteria andRelationTrainLocationIdIn(List<Integer> values) {
            addCriterion("relation_train_location_id in", values, "relationTrainLocationId");
            return (Criteria) this;
        }

        public Criteria andRelationTrainLocationIdNotIn(List<Integer> values) {
            addCriterion("relation_train_location_id not in", values, "relationTrainLocationId");
            return (Criteria) this;
        }

        public Criteria andRelationTrainLocationIdBetween(Integer value1, Integer value2) {
            addCriterion("relation_train_location_id between", value1, value2, "relationTrainLocationId");
            return (Criteria) this;
        }

        public Criteria andRelationTrainLocationIdNotBetween(Integer value1, Integer value2) {
            addCriterion("relation_train_location_id not between", value1, value2, "relationTrainLocationId");
            return (Criteria) this;
        }

        public Criteria andRelationTrainLocationNameIsNull() {
            addCriterion("relation_train_location_name is null");
            return (Criteria) this;
        }

        public Criteria andRelationTrainLocationNameIsNotNull() {
            addCriterion("relation_train_location_name is not null");
            return (Criteria) this;
        }

        public Criteria andRelationTrainLocationNameEqualTo(String value) {
            addCriterion("relation_train_location_name =", value, "relationTrainLocationName");
            return (Criteria) this;
        }

        public Criteria andRelationTrainLocationNameNotEqualTo(String value) {
            addCriterion("relation_train_location_name <>", value, "relationTrainLocationName");
            return (Criteria) this;
        }

        public Criteria andRelationTrainLocationNameGreaterThan(String value) {
            addCriterion("relation_train_location_name >", value, "relationTrainLocationName");
            return (Criteria) this;
        }

        public Criteria andRelationTrainLocationNameGreaterThanOrEqualTo(String value) {
            addCriterion("relation_train_location_name >=", value, "relationTrainLocationName");
            return (Criteria) this;
        }

        public Criteria andRelationTrainLocationNameLessThan(String value) {
            addCriterion("relation_train_location_name <", value, "relationTrainLocationName");
            return (Criteria) this;
        }

        public Criteria andRelationTrainLocationNameLessThanOrEqualTo(String value) {
            addCriterion("relation_train_location_name <=", value, "relationTrainLocationName");
            return (Criteria) this;
        }

        public Criteria andRelationTrainLocationNameLike(String value) {
            addCriterion("relation_train_location_name like", value, "relationTrainLocationName");
            return (Criteria) this;
        }

        public Criteria andRelationTrainLocationNameNotLike(String value) {
            addCriterion("relation_train_location_name not like", value, "relationTrainLocationName");
            return (Criteria) this;
        }

        public Criteria andRelationTrainLocationNameIn(List<String> values) {
            addCriterion("relation_train_location_name in", values, "relationTrainLocationName");
            return (Criteria) this;
        }

        public Criteria andRelationTrainLocationNameNotIn(List<String> values) {
            addCriterion("relation_train_location_name not in", values, "relationTrainLocationName");
            return (Criteria) this;
        }

        public Criteria andRelationTrainLocationNameBetween(String value1, String value2) {
            addCriterion("relation_train_location_name between", value1, value2, "relationTrainLocationName");
            return (Criteria) this;
        }

        public Criteria andRelationTrainLocationNameNotBetween(String value1, String value2) {
            addCriterion("relation_train_location_name not between", value1, value2, "relationTrainLocationName");
            return (Criteria) this;
        }

        public Criteria andRelationBeginLocationIdIsNull() {
            addCriterion("relation_begin_location_id is null");
            return (Criteria) this;
        }

        public Criteria andRelationBeginLocationIdIsNotNull() {
            addCriterion("relation_begin_location_id is not null");
            return (Criteria) this;
        }

        public Criteria andRelationBeginLocationIdEqualTo(Integer value) {
            addCriterion("relation_begin_location_id =", value, "relationBeginLocationId");
            return (Criteria) this;
        }

        public Criteria andRelationBeginLocationIdNotEqualTo(Integer value) {
            addCriterion("relation_begin_location_id <>", value, "relationBeginLocationId");
            return (Criteria) this;
        }

        public Criteria andRelationBeginLocationIdGreaterThan(Integer value) {
            addCriterion("relation_begin_location_id >", value, "relationBeginLocationId");
            return (Criteria) this;
        }

        public Criteria andRelationBeginLocationIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("relation_begin_location_id >=", value, "relationBeginLocationId");
            return (Criteria) this;
        }

        public Criteria andRelationBeginLocationIdLessThan(Integer value) {
            addCriterion("relation_begin_location_id <", value, "relationBeginLocationId");
            return (Criteria) this;
        }

        public Criteria andRelationBeginLocationIdLessThanOrEqualTo(Integer value) {
            addCriterion("relation_begin_location_id <=", value, "relationBeginLocationId");
            return (Criteria) this;
        }

        public Criteria andRelationBeginLocationIdIn(List<Integer> values) {
            addCriterion("relation_begin_location_id in", values, "relationBeginLocationId");
            return (Criteria) this;
        }

        public Criteria andRelationBeginLocationIdNotIn(List<Integer> values) {
            addCriterion("relation_begin_location_id not in", values, "relationBeginLocationId");
            return (Criteria) this;
        }

        public Criteria andRelationBeginLocationIdBetween(Integer value1, Integer value2) {
            addCriterion("relation_begin_location_id between", value1, value2, "relationBeginLocationId");
            return (Criteria) this;
        }

        public Criteria andRelationBeginLocationIdNotBetween(Integer value1, Integer value2) {
            addCriterion("relation_begin_location_id not between", value1, value2, "relationBeginLocationId");
            return (Criteria) this;
        }

        public Criteria andRelationBeginLocationIsNull() {
            addCriterion("relation_begin_location is null");
            return (Criteria) this;
        }

        public Criteria andRelationBeginLocationIsNotNull() {
            addCriterion("relation_begin_location is not null");
            return (Criteria) this;
        }

        public Criteria andRelationBeginLocationEqualTo(String value) {
            addCriterion("relation_begin_location =", value, "relationBeginLocation");
            return (Criteria) this;
        }

        public Criteria andRelationBeginLocationNotEqualTo(String value) {
            addCriterion("relation_begin_location <>", value, "relationBeginLocation");
            return (Criteria) this;
        }

        public Criteria andRelationBeginLocationGreaterThan(String value) {
            addCriterion("relation_begin_location >", value, "relationBeginLocation");
            return (Criteria) this;
        }

        public Criteria andRelationBeginLocationGreaterThanOrEqualTo(String value) {
            addCriterion("relation_begin_location >=", value, "relationBeginLocation");
            return (Criteria) this;
        }

        public Criteria andRelationBeginLocationLessThan(String value) {
            addCriterion("relation_begin_location <", value, "relationBeginLocation");
            return (Criteria) this;
        }

        public Criteria andRelationBeginLocationLessThanOrEqualTo(String value) {
            addCriterion("relation_begin_location <=", value, "relationBeginLocation");
            return (Criteria) this;
        }

        public Criteria andRelationBeginLocationLike(String value) {
            addCriterion("relation_begin_location like", value, "relationBeginLocation");
            return (Criteria) this;
        }

        public Criteria andRelationBeginLocationNotLike(String value) {
            addCriterion("relation_begin_location not like", value, "relationBeginLocation");
            return (Criteria) this;
        }

        public Criteria andRelationBeginLocationIn(List<String> values) {
            addCriterion("relation_begin_location in", values, "relationBeginLocation");
            return (Criteria) this;
        }

        public Criteria andRelationBeginLocationNotIn(List<String> values) {
            addCriterion("relation_begin_location not in", values, "relationBeginLocation");
            return (Criteria) this;
        }

        public Criteria andRelationBeginLocationBetween(String value1, String value2) {
            addCriterion("relation_begin_location between", value1, value2, "relationBeginLocation");
            return (Criteria) this;
        }

        public Criteria andRelationBeginLocationNotBetween(String value1, String value2) {
            addCriterion("relation_begin_location not between", value1, value2, "relationBeginLocation");
            return (Criteria) this;
        }

        public Criteria andCommentIsNull() {
            addCriterion("comment is null");
            return (Criteria) this;
        }

        public Criteria andCommentIsNotNull() {
            addCriterion("comment is not null");
            return (Criteria) this;
        }

        public Criteria andCommentEqualTo(String value) {
            addCriterion("comment =", value, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentNotEqualTo(String value) {
            addCriterion("comment <>", value, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentGreaterThan(String value) {
            addCriterion("comment >", value, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentGreaterThanOrEqualTo(String value) {
            addCriterion("comment >=", value, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentLessThan(String value) {
            addCriterion("comment <", value, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentLessThanOrEqualTo(String value) {
            addCriterion("comment <=", value, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentLike(String value) {
            addCriterion("comment like", value, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentNotLike(String value) {
            addCriterion("comment not like", value, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentIn(List<String> values) {
            addCriterion("comment in", values, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentNotIn(List<String> values) {
            addCriterion("comment not in", values, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentBetween(String value1, String value2) {
            addCriterion("comment between", value1, value2, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentNotBetween(String value1, String value2) {
            addCriterion("comment not between", value1, value2, "comment");
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