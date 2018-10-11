package com.shenhesoft.logistics.manage.pojo.box;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TbContainerExample implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8808841983595741362L;
	
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TbContainerExample() {
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
            addCriterion("A.id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("A.id <>", value, "id");
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

        public Criteria andContainerTypeIdIsNull() {
            addCriterion("container_type_id is null");
            return (Criteria) this;
        }

        public Criteria andContainerTypeIdIsNotNull() {
            addCriterion("container_type_id is not null");
            return (Criteria) this;
        }

        public Criteria andContainerTypeIdEqualTo(Integer value) {
            addCriterion("container_type_id =", value, "containerTypeId");
            return (Criteria) this;
        }

        public Criteria andContainerTypeIdNotEqualTo(Integer value) {
            addCriterion("container_type_id <>", value, "containerTypeId");
            return (Criteria) this;
        }

        public Criteria andContainerTypeIdGreaterThan(Integer value) {
            addCriterion("container_type_id >", value, "containerTypeId");
            return (Criteria) this;
        }

        public Criteria andContainerTypeIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("container_type_id >=", value, "containerTypeId");
            return (Criteria) this;
        }

        public Criteria andContainerTypeIdLessThan(Integer value) {
            addCriterion("container_type_id <", value, "containerTypeId");
            return (Criteria) this;
        }

        public Criteria andContainerTypeIdLessThanOrEqualTo(Integer value) {
            addCriterion("container_type_id <=", value, "containerTypeId");
            return (Criteria) this;
        }

        public Criteria andContainerTypeIdIn(List<Integer> values) {
            addCriterion("container_type_id in", values, "containerTypeId");
            return (Criteria) this;
        }

        public Criteria andContainerTypeIdNotIn(List<Integer> values) {
            addCriterion("container_type_id not in", values, "containerTypeId");
            return (Criteria) this;
        }

        public Criteria andContainerTypeIdBetween(Integer value1, Integer value2) {
            addCriterion("container_type_id between", value1, value2, "containerTypeId");
            return (Criteria) this;
        }

        public Criteria andContainerTypeIdNotBetween(Integer value1, Integer value2) {
            addCriterion("container_type_id not between", value1, value2, "containerTypeId");
            return (Criteria) this;
        }

        public Criteria andContainerNumIsNull() {
            addCriterion("container_num is null");
            return (Criteria) this;
        }

        public Criteria andContainerNumIsNotNull() {
            addCriterion("container_num is not null");
            return (Criteria) this;
        }

        public Criteria andContainerNumEqualTo(String value) {
            addCriterion("container_num =", value, "containerNum");
            return (Criteria) this;
        }

        public Criteria andContainerNumNotEqualTo(String value) {
            addCriterion("container_num <>", value, "containerNum");
            return (Criteria) this;
        }

        public Criteria andContainerNumGreaterThan(String value) {
            addCriterion("container_num >", value, "containerNum");
            return (Criteria) this;
        }

        public Criteria andContainerNumGreaterThanOrEqualTo(String value) {
            addCriterion("container_num >=", value, "containerNum");
            return (Criteria) this;
        }

        public Criteria andContainerNumLessThan(String value) {
            addCriterion("container_num <", value, "containerNum");
            return (Criteria) this;
        }

        public Criteria andContainerNumLessThanOrEqualTo(String value) {
            addCriterion("container_num <=", value, "containerNum");
            return (Criteria) this;
        }

        public Criteria andContainerNumLike(String value) {
            addCriterion("container_num like", value, "containerNum");
            return (Criteria) this;
        }

        public Criteria andContainerNumNotLike(String value) {
            addCriterion("container_num not like", value, "containerNum");
            return (Criteria) this;
        }

        public Criteria andContainerNumIn(List<String> values) {
            addCriterion("container_num in", values, "containerNum");
            return (Criteria) this;
        }

        public Criteria andContainerNumNotIn(List<String> values) {
            addCriterion("container_num not in", values, "containerNum");
            return (Criteria) this;
        }

        public Criteria andContainerNumBetween(String value1, String value2) {
            addCriterion("container_num between", value1, value2, "containerNum");
            return (Criteria) this;
        }

        public Criteria andContainerNumNotBetween(String value1, String value2) {
            addCriterion("container_num not between", value1, value2, "containerNum");
            return (Criteria) this;
        }

        public Criteria andEastContainerIsNull() {
            addCriterion("east_container is null");
            return (Criteria) this;
        }

        public Criteria andEastContainerIsNotNull() {
            addCriterion("east_container is not null");
            return (Criteria) this;
        }

        public Criteria andEastContainerEqualTo(String value) {
            addCriterion("east_container =", value, "eastContainer");
            return (Criteria) this;
        }

        public Criteria andEastContainerNotEqualTo(String value) {
            addCriterion("east_container <>", value, "eastContainer");
            return (Criteria) this;
        }

        public Criteria andEastContainerGreaterThan(String value) {
            addCriterion("east_container >", value, "eastContainer");
            return (Criteria) this;
        }

        public Criteria andEastContainerGreaterThanOrEqualTo(String value) {
            addCriterion("east_container >=", value, "eastContainer");
            return (Criteria) this;
        }

        public Criteria andEastContainerLessThan(String value) {
            addCriterion("east_container <", value, "eastContainer");
            return (Criteria) this;
        }

        public Criteria andEastContainerLessThanOrEqualTo(String value) {
            addCriterion("east_container <=", value, "eastContainer");
            return (Criteria) this;
        }

        public Criteria andEastContainerLike(String value) {
            addCriterion("east_container like", value, "eastContainer");
            return (Criteria) this;
        }

        public Criteria andEastContainerNotLike(String value) {
            addCriterion("east_container not like", value, "eastContainer");
            return (Criteria) this;
        }

        public Criteria andEastContainerIn(List<String> values) {
            addCriterion("east_container in", values, "eastContainer");
            return (Criteria) this;
        }

        public Criteria andEastContainerNotIn(List<String> values) {
            addCriterion("east_container not in", values, "eastContainer");
            return (Criteria) this;
        }

        public Criteria andEastContainerBetween(String value1, String value2) {
            addCriterion("east_container between", value1, value2, "eastContainer");
            return (Criteria) this;
        }

        public Criteria andEastContainerNotBetween(String value1, String value2) {
            addCriterion("east_container not between", value1, value2, "eastContainer");
            return (Criteria) this;
        }

        public Criteria andContainerKindIsNull() {
            addCriterion("container_kind is null");
            return (Criteria) this;
        }

        public Criteria andContainerKindIsNotNull() {
            addCriterion("container_kind is not null");
            return (Criteria) this;
        }

        public Criteria andContainerKindEqualTo(String value) {
            addCriterion("container_kind =", value, "containerKind");
            return (Criteria) this;
        }

        public Criteria andContainerKindNotEqualTo(String value) {
            addCriterion("container_kind <>", value, "containerKind");
            return (Criteria) this;
        }

        public Criteria andContainerKindGreaterThan(String value) {
            addCriterion("container_kind >", value, "containerKind");
            return (Criteria) this;
        }

        public Criteria andContainerKindGreaterThanOrEqualTo(String value) {
            addCriterion("container_kind >=", value, "containerKind");
            return (Criteria) this;
        }

        public Criteria andContainerKindLessThan(String value) {
            addCriterion("container_kind <", value, "containerKind");
            return (Criteria) this;
        }

        public Criteria andContainerKindLessThanOrEqualTo(String value) {
            addCriterion("container_kind <=", value, "containerKind");
            return (Criteria) this;
        }

        public Criteria andContainerKindLike(String value) {
            addCriterion("container_kind like", value, "containerKind");
            return (Criteria) this;
        }

        public Criteria andContainerKindNotLike(String value) {
            addCriterion("container_kind not like", value, "containerKind");
            return (Criteria) this;
        }

        public Criteria andContainerKindIn(List<String> values) {
            addCriterion("container_kind in", values, "containerKind");
            return (Criteria) this;
        }

        public Criteria andContainerKindNotIn(List<String> values) {
            addCriterion("container_kind not in", values, "containerKind");
            return (Criteria) this;
        }

        public Criteria andContainerKindBetween(String value1, String value2) {
            addCriterion("container_kind between", value1, value2, "containerKind");
            return (Criteria) this;
        }

        public Criteria andContainerKindNotBetween(String value1, String value2) {
            addCriterion("container_kind not between", value1, value2, "containerKind");
            return (Criteria) this;
        }

        public Criteria andContainerCodeIsNull() {
            addCriterion("container_code is null");
            return (Criteria) this;
        }

        public Criteria andContainerCodeIsNotNull() {
            addCriterion("container_code is not null");
            return (Criteria) this;
        }

        public Criteria andContainerCodeEqualTo(String value) {
            addCriterion("container_code =", value, "containerCode");
            return (Criteria) this;
        }

        public Criteria andContainerCodeNotEqualTo(String value) {
            addCriterion("container_code <>", value, "containerCode");
            return (Criteria) this;
        }

        public Criteria andContainerCodeGreaterThan(String value) {
            addCriterion("container_code >", value, "containerCode");
            return (Criteria) this;
        }

        public Criteria andContainerCodeGreaterThanOrEqualTo(String value) {
            addCriterion("container_code >=", value, "containerCode");
            return (Criteria) this;
        }

        public Criteria andContainerCodeLessThan(String value) {
            addCriterion("container_code <", value, "containerCode");
            return (Criteria) this;
        }

        public Criteria andContainerCodeLessThanOrEqualTo(String value) {
            addCriterion("container_code <=", value, "containerCode");
            return (Criteria) this;
        }

        public Criteria andContainerCodeLike(String value) {
            addCriterion("container_code like", value, "containerCode");
            return (Criteria) this;
        }

        public Criteria andContainerCodeNotLike(String value) {
            addCriterion("container_code not like", value, "containerCode");
            return (Criteria) this;
        }

        public Criteria andContainerCodeIn(List<String> values) {
            addCriterion("container_code in", values, "containerCode");
            return (Criteria) this;
        }

        public Criteria andContainerCodeNotIn(List<String> values) {
            addCriterion("container_code not in", values, "containerCode");
            return (Criteria) this;
        }

        public Criteria andContainerCodeBetween(String value1, String value2) {
            addCriterion("container_code between", value1, value2, "containerCode");
            return (Criteria) this;
        }

        public Criteria andContainerCodeNotBetween(String value1, String value2) {
            addCriterion("container_code not between", value1, value2, "containerCode");
            return (Criteria) this;
        }

        public Criteria andContainerIdIsNull() {
            addCriterion("container_id is null");
            return (Criteria) this;
        }

        public Criteria andContainerIdIsNotNull() {
            addCriterion("container_id is not null");
            return (Criteria) this;
        }

        public Criteria andContainerIdEqualTo(String value) {
            addCriterion("container_id =", value, "containerId");
            return (Criteria) this;
        }

        public Criteria andContainerIdNotEqualTo(String value) {
            addCriterion("container_id <>", value, "containerId");
            return (Criteria) this;
        }

        public Criteria andContainerIdGreaterThan(String value) {
            addCriterion("container_id >", value, "containerId");
            return (Criteria) this;
        }

        public Criteria andContainerIdGreaterThanOrEqualTo(String value) {
            addCriterion("container_id >=", value, "containerId");
            return (Criteria) this;
        }

        public Criteria andContainerIdLessThan(String value) {
            addCriterion("container_id <", value, "containerId");
            return (Criteria) this;
        }

        public Criteria andContainerIdLessThanOrEqualTo(String value) {
            addCriterion("container_id <=", value, "containerId");
            return (Criteria) this;
        }

        public Criteria andContainerIdLike(String value) {
            addCriterion("container_id like", "%"+value+"%", "containerId");
            return (Criteria) this;
        }

        public Criteria andContainerIdNotLike(String value) {
            addCriterion("container_id not like", value, "containerId");
            return (Criteria) this;
        }

        public Criteria andContainerIdIn(List<String> values) {
            addCriterion("container_id in", values, "containerId");
            return (Criteria) this;
        }

        public Criteria andContainerIdNotIn(List<String> values) {
            addCriterion("container_id not in", values, "containerId");
            return (Criteria) this;
        }

        public Criteria andContainerIdBetween(String value1, String value2) {
            addCriterion("container_id between", value1, value2, "containerId");
            return (Criteria) this;
        }

        public Criteria andContainerIdNotBetween(String value1, String value2) {
            addCriterion("container_id not between", value1, value2, "containerId");
            return (Criteria) this;
        }

        public Criteria andLengthIsNull() {
            addCriterion("length is null");
            return (Criteria) this;
        }

        public Criteria andLengthIsNotNull() {
            addCriterion("length is not null");
            return (Criteria) this;
        }

        public Criteria andLengthEqualTo(Integer value) {
            addCriterion("length =", value, "length");
            return (Criteria) this;
        }

        public Criteria andLengthNotEqualTo(Integer value) {
            addCriterion("length <>", value, "length");
            return (Criteria) this;
        }

        public Criteria andLengthGreaterThan(Integer value) {
            addCriterion("length >", value, "length");
            return (Criteria) this;
        }

        public Criteria andLengthGreaterThanOrEqualTo(Integer value) {
            addCriterion("length >=", value, "length");
            return (Criteria) this;
        }

        public Criteria andLengthLessThan(Integer value) {
            addCriterion("length <", value, "length");
            return (Criteria) this;
        }

        public Criteria andLengthLessThanOrEqualTo(Integer value) {
            addCriterion("length <=", value, "length");
            return (Criteria) this;
        }

        public Criteria andLengthIn(List<Integer> values) {
            addCriterion("length in", values, "length");
            return (Criteria) this;
        }

        public Criteria andLengthNotIn(List<Integer> values) {
            addCriterion("length not in", values, "length");
            return (Criteria) this;
        }

        public Criteria andLengthBetween(Integer value1, Integer value2) {
            addCriterion("length between", value1, value2, "length");
            return (Criteria) this;
        }

        public Criteria andLengthNotBetween(Integer value1, Integer value2) {
            addCriterion("length not between", value1, value2, "length");
            return (Criteria) this;
        }

        public Criteria andWidthIsNull() {
            addCriterion("width is null");
            return (Criteria) this;
        }

        public Criteria andWidthIsNotNull() {
            addCriterion("width is not null");
            return (Criteria) this;
        }

        public Criteria andWidthEqualTo(Integer value) {
            addCriterion("width =", value, "width");
            return (Criteria) this;
        }

        public Criteria andWidthNotEqualTo(Integer value) {
            addCriterion("width <>", value, "width");
            return (Criteria) this;
        }

        public Criteria andWidthGreaterThan(Integer value) {
            addCriterion("width >", value, "width");
            return (Criteria) this;
        }

        public Criteria andWidthGreaterThanOrEqualTo(Integer value) {
            addCriterion("width >=", value, "width");
            return (Criteria) this;
        }

        public Criteria andWidthLessThan(Integer value) {
            addCriterion("width <", value, "width");
            return (Criteria) this;
        }

        public Criteria andWidthLessThanOrEqualTo(Integer value) {
            addCriterion("width <=", value, "width");
            return (Criteria) this;
        }

        public Criteria andWidthIn(List<Integer> values) {
            addCriterion("width in", values, "width");
            return (Criteria) this;
        }

        public Criteria andWidthNotIn(List<Integer> values) {
            addCriterion("width not in", values, "width");
            return (Criteria) this;
        }

        public Criteria andWidthBetween(Integer value1, Integer value2) {
            addCriterion("width between", value1, value2, "width");
            return (Criteria) this;
        }

        public Criteria andWidthNotBetween(Integer value1, Integer value2) {
            addCriterion("width not between", value1, value2, "width");
            return (Criteria) this;
        }

        public Criteria andHightIsNull() {
            addCriterion("hight is null");
            return (Criteria) this;
        }

        public Criteria andHightIsNotNull() {
            addCriterion("hight is not null");
            return (Criteria) this;
        }

        public Criteria andHightEqualTo(Integer value) {
            addCriterion("hight =", value, "hight");
            return (Criteria) this;
        }

        public Criteria andHightNotEqualTo(Integer value) {
            addCriterion("hight <>", value, "hight");
            return (Criteria) this;
        }

        public Criteria andHightGreaterThan(Integer value) {
            addCriterion("hight >", value, "hight");
            return (Criteria) this;
        }

        public Criteria andHightGreaterThanOrEqualTo(Integer value) {
            addCriterion("hight >=", value, "hight");
            return (Criteria) this;
        }

        public Criteria andHightLessThan(Integer value) {
            addCriterion("hight <", value, "hight");
            return (Criteria) this;
        }

        public Criteria andHightLessThanOrEqualTo(Integer value) {
            addCriterion("hight <=", value, "hight");
            return (Criteria) this;
        }

        public Criteria andHightIn(List<Integer> values) {
            addCriterion("hight in", values, "hight");
            return (Criteria) this;
        }

        public Criteria andHightNotIn(List<Integer> values) {
            addCriterion("hight not in", values, "hight");
            return (Criteria) this;
        }

        public Criteria andHightBetween(Integer value1, Integer value2) {
            addCriterion("hight between", value1, value2, "hight");
            return (Criteria) this;
        }

        public Criteria andHightNotBetween(Integer value1, Integer value2) {
            addCriterion("hight not between", value1, value2, "hight");
            return (Criteria) this;
        }

        public Criteria andSizeIsNull() {
            addCriterion("size is null");
            return (Criteria) this;
        }

        public Criteria andSizeIsNotNull() {
            addCriterion("size is not null");
            return (Criteria) this;
        }

        public Criteria andSizeEqualTo(Integer value) {
            addCriterion("size =", value, "size");
            return (Criteria) this;
        }

        public Criteria andSizeNotEqualTo(Integer value) {
            addCriterion("size <>", value, "size");
            return (Criteria) this;
        }

        public Criteria andSizeGreaterThan(Integer value) {
            addCriterion("size >", value, "size");
            return (Criteria) this;
        }

        public Criteria andSizeGreaterThanOrEqualTo(Integer value) {
            addCriterion("size >=", value, "size");
            return (Criteria) this;
        }

        public Criteria andSizeLessThan(Integer value) {
            addCriterion("size <", value, "size");
            return (Criteria) this;
        }

        public Criteria andSizeLessThanOrEqualTo(Integer value) {
            addCriterion("size <=", value, "size");
            return (Criteria) this;
        }

        public Criteria andSizeIn(List<Integer> values) {
            addCriterion("size in", values, "size");
            return (Criteria) this;
        }

        public Criteria andSizeNotIn(List<Integer> values) {
            addCriterion("size not in", values, "size");
            return (Criteria) this;
        }

        public Criteria andSizeBetween(Integer value1, Integer value2) {
            addCriterion("size between", value1, value2, "size");
            return (Criteria) this;
        }

        public Criteria andSizeNotBetween(Integer value1, Integer value2) {
            addCriterion("size not between", value1, value2, "size");
            return (Criteria) this;
        }

        public Criteria andVolumeIsNull() {
            addCriterion("volume is null");
            return (Criteria) this;
        }

        public Criteria andVolumeIsNotNull() {
            addCriterion("volume is not null");
            return (Criteria) this;
        }

        public Criteria andVolumeEqualTo(Integer value) {
            addCriterion("volume =", value, "volume");
            return (Criteria) this;
        }

        public Criteria andVolumeNotEqualTo(Integer value) {
            addCriterion("volume <>", value, "volume");
            return (Criteria) this;
        }

        public Criteria andVolumeGreaterThan(Integer value) {
            addCriterion("volume >", value, "volume");
            return (Criteria) this;
        }

        public Criteria andVolumeGreaterThanOrEqualTo(Integer value) {
            addCriterion("volume >=", value, "volume");
            return (Criteria) this;
        }

        public Criteria andVolumeLessThan(Integer value) {
            addCriterion("volume <", value, "volume");
            return (Criteria) this;
        }

        public Criteria andVolumeLessThanOrEqualTo(Integer value) {
            addCriterion("volume <=", value, "volume");
            return (Criteria) this;
        }

        public Criteria andVolumeIn(List<Integer> values) {
            addCriterion("volume in", values, "volume");
            return (Criteria) this;
        }

        public Criteria andVolumeNotIn(List<Integer> values) {
            addCriterion("volume not in", values, "volume");
            return (Criteria) this;
        }

        public Criteria andVolumeBetween(Integer value1, Integer value2) {
            addCriterion("volume between", value1, value2, "volume");
            return (Criteria) this;
        }

        public Criteria andVolumeNotBetween(Integer value1, Integer value2) {
            addCriterion("volume not between", value1, value2, "volume");
            return (Criteria) this;
        }

        public Criteria andSelfWeightIsNull() {
            addCriterion("self_weight is null");
            return (Criteria) this;
        }

        public Criteria andSelfWeightIsNotNull() {
            addCriterion("self_weight is not null");
            return (Criteria) this;
        }

        public Criteria andSelfWeightEqualTo(Integer value) {
            addCriterion("self_weight =", value, "selfWeight");
            return (Criteria) this;
        }

        public Criteria andSelfWeightNotEqualTo(Integer value) {
            addCriterion("self_weight <>", value, "selfWeight");
            return (Criteria) this;
        }

        public Criteria andSelfWeightGreaterThan(Integer value) {
            addCriterion("self_weight >", value, "selfWeight");
            return (Criteria) this;
        }

        public Criteria andSelfWeightGreaterThanOrEqualTo(Integer value) {
            addCriterion("self_weight >=", value, "selfWeight");
            return (Criteria) this;
        }

        public Criteria andSelfWeightLessThan(Integer value) {
            addCriterion("self_weight <", value, "selfWeight");
            return (Criteria) this;
        }

        public Criteria andSelfWeightLessThanOrEqualTo(Integer value) {
            addCriterion("self_weight <=", value, "selfWeight");
            return (Criteria) this;
        }

        public Criteria andSelfWeightIn(List<Integer> values) {
            addCriterion("self_weight in", values, "selfWeight");
            return (Criteria) this;
        }

        public Criteria andSelfWeightNotIn(List<Integer> values) {
            addCriterion("self_weight not in", values, "selfWeight");
            return (Criteria) this;
        }

        public Criteria andSelfWeightBetween(Integer value1, Integer value2) {
            addCriterion("self_weight between", value1, value2, "selfWeight");
            return (Criteria) this;
        }

        public Criteria andSelfWeightNotBetween(Integer value1, Integer value2) {
            addCriterion("self_weight not between", value1, value2, "selfWeight");
            return (Criteria) this;
        }

        public Criteria andWeightIsNull() {
            addCriterion("weight is null");
            return (Criteria) this;
        }

        public Criteria andWeightIsNotNull() {
            addCriterion("weight is not null");
            return (Criteria) this;
        }

        public Criteria andWeightEqualTo(Integer value) {
            addCriterion("weight =", value, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightNotEqualTo(Integer value) {
            addCriterion("weight <>", value, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightGreaterThan(Integer value) {
            addCriterion("weight >", value, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightGreaterThanOrEqualTo(Integer value) {
            addCriterion("weight >=", value, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightLessThan(Integer value) {
            addCriterion("weight <", value, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightLessThanOrEqualTo(Integer value) {
            addCriterion("weight <=", value, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightIn(List<Integer> values) {
            addCriterion("weight in", values, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightNotIn(List<Integer> values) {
            addCriterion("weight not in", values, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightBetween(Integer value1, Integer value2) {
            addCriterion("weight between", value1, value2, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightNotBetween(Integer value1, Integer value2) {
            addCriterion("weight not between", value1, value2, "weight");
            return (Criteria) this;
        }

        public Criteria andTrainLocationIdIsNull() {
            addCriterion("train_location_id is null");
            return (Criteria) this;
        }

        public Criteria andTrainLocationIdIsNotNull() {
            addCriterion("train_location_id is not null");
            return (Criteria) this;
        }

        public Criteria andTrainLocationIdEqualTo(Integer value) {
            addCriterion("train_location_id =", value, "trainLocationId");
            return (Criteria) this;
        }

        public Criteria andTrainLocationIdNotEqualTo(Integer value) {
            addCriterion("train_location_id <>", value, "trainLocationId");
            return (Criteria) this;
        }

        public Criteria andTrainLocationIdGreaterThan(Integer value) {
            addCriterion("train_location_id >", value, "trainLocationId");
            return (Criteria) this;
        }

        public Criteria andTrainLocationIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("train_location_id >=", value, "trainLocationId");
            return (Criteria) this;
        }

        public Criteria andTrainLocationIdLessThan(Integer value) {
            addCriterion("train_location_id <", value, "trainLocationId");
            return (Criteria) this;
        }

        public Criteria andTrainLocationIdLessThanOrEqualTo(Integer value) {
            addCriterion("train_location_id <=", value, "trainLocationId");
            return (Criteria) this;
        }

        public Criteria andTrainLocationIdIn(List<Integer> values) {
            addCriterion("train_location_id in", values, "trainLocationId");
            return (Criteria) this;
        }

        public Criteria andTrainLocationIdNotIn(List<Integer> values) {
            addCriterion("train_location_id not in", values, "trainLocationId");
            return (Criteria) this;
        }

        public Criteria andTrainLocationIdBetween(Integer value1, Integer value2) {
            addCriterion("train_location_id between", value1, value2, "trainLocationId");
            return (Criteria) this;
        }

        public Criteria andTrainLocationIdNotBetween(Integer value1, Integer value2) {
            addCriterion("train_location_id not between", value1, value2, "trainLocationId");
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

        public Criteria andStatusEqualTo(Integer value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Integer value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Integer value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Integer value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Integer value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Integer> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Integer> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Integer value1, Integer value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("status not between", value1, value2, "status");
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