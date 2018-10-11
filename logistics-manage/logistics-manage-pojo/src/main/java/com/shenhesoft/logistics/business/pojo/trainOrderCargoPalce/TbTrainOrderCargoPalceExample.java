package com.shenhesoft.logistics.business.pojo.trainOrderCargoPalce;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TbTrainOrderCargoPalceExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TbTrainOrderCargoPalceExample() {
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

        public Criteria andCargoPlaceIdIsNull() {
            addCriterion("cargo_place_id is null");
            return (Criteria) this;
        }

        public Criteria andCargoPlaceIdIsNotNull() {
            addCriterion("cargo_place_id is not null");
            return (Criteria) this;
        }

        public Criteria andCargoPlaceIdEqualTo(Integer value) {
            addCriterion("cargo_place_id =", value, "cargoPlaceId");
            return (Criteria) this;
        }

        public Criteria andCargoPlaceIdNotEqualTo(Integer value) {
            addCriterion("cargo_place_id <>", value, "cargoPlaceId");
            return (Criteria) this;
        }

        public Criteria andCargoPlaceIdGreaterThan(Integer value) {
            addCriterion("cargo_place_id >", value, "cargoPlaceId");
            return (Criteria) this;
        }

        public Criteria andCargoPlaceIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("cargo_place_id >=", value, "cargoPlaceId");
            return (Criteria) this;
        }

        public Criteria andCargoPlaceIdLessThan(Integer value) {
            addCriterion("cargo_place_id <", value, "cargoPlaceId");
            return (Criteria) this;
        }

        public Criteria andCargoPlaceIdLessThanOrEqualTo(Integer value) {
            addCriterion("cargo_place_id <=", value, "cargoPlaceId");
            return (Criteria) this;
        }

        public Criteria andCargoPlaceIdIn(List<Integer> values) {
            addCriterion("cargo_place_id in", values, "cargoPlaceId");
            return (Criteria) this;
        }

        public Criteria andCargoPlaceIdNotIn(List<Integer> values) {
            addCriterion("cargo_place_id not in", values, "cargoPlaceId");
            return (Criteria) this;
        }

        public Criteria andCargoPlaceIdBetween(Integer value1, Integer value2) {
            addCriterion("cargo_place_id between", value1, value2, "cargoPlaceId");
            return (Criteria) this;
        }

        public Criteria andCargoPlaceIdNotBetween(Integer value1, Integer value2) {
            addCriterion("cargo_place_id not between", value1, value2, "cargoPlaceId");
            return (Criteria) this;
        }

        public Criteria andCargoPlaceNameIsNull() {
            addCriterion("cargo_place_name is null");
            return (Criteria) this;
        }

        public Criteria andCargoPlaceNameIsNotNull() {
            addCriterion("cargo_place_name is not null");
            return (Criteria) this;
        }

        public Criteria andCargoPlaceNameEqualTo(String value) {
            addCriterion("cargo_place_name =", value, "cargoPlaceName");
            return (Criteria) this;
        }

        public Criteria andCargoPlaceNameNotEqualTo(String value) {
            addCriterion("cargo_place_name <>", value, "cargoPlaceName");
            return (Criteria) this;
        }

        public Criteria andCargoPlaceNameGreaterThan(String value) {
            addCriterion("cargo_place_name >", value, "cargoPlaceName");
            return (Criteria) this;
        }

        public Criteria andCargoPlaceNameGreaterThanOrEqualTo(String value) {
            addCriterion("cargo_place_name >=", value, "cargoPlaceName");
            return (Criteria) this;
        }

        public Criteria andCargoPlaceNameLessThan(String value) {
            addCriterion("cargo_place_name <", value, "cargoPlaceName");
            return (Criteria) this;
        }

        public Criteria andCargoPlaceNameLessThanOrEqualTo(String value) {
            addCriterion("cargo_place_name <=", value, "cargoPlaceName");
            return (Criteria) this;
        }

        public Criteria andCargoPlaceNameLike(String value) {
            addCriterion("cargo_place_name like", value, "cargoPlaceName");
            return (Criteria) this;
        }

        public Criteria andCargoPlaceNameNotLike(String value) {
            addCriterion("cargo_place_name not like", value, "cargoPlaceName");
            return (Criteria) this;
        }

        public Criteria andCargoPlaceNameIn(List<String> values) {
            addCriterion("cargo_place_name in", values, "cargoPlaceName");
            return (Criteria) this;
        }

        public Criteria andCargoPlaceNameNotIn(List<String> values) {
            addCriterion("cargo_place_name not in", values, "cargoPlaceName");
            return (Criteria) this;
        }

        public Criteria andCargoPlaceNameBetween(String value1, String value2) {
            addCriterion("cargo_place_name between", value1, value2, "cargoPlaceName");
            return (Criteria) this;
        }

        public Criteria andCargoPlaceNameNotBetween(String value1, String value2) {
            addCriterion("cargo_place_name not between", value1, value2, "cargoPlaceName");
            return (Criteria) this;
        }

        public Criteria andCargoSiteIdIsNull() {
            addCriterion("cargo_site_id is null");
            return (Criteria) this;
        }

        public Criteria andCargoSiteIdIsNotNull() {
            addCriterion("cargo_site_id is not null");
            return (Criteria) this;
        }

        public Criteria andCargoSiteIdEqualTo(Integer value) {
            addCriterion("cargo_site_id =", value, "cargoSiteId");
            return (Criteria) this;
        }

        public Criteria andCargoSiteIdNotEqualTo(Integer value) {
            addCriterion("cargo_site_id <>", value, "cargoSiteId");
            return (Criteria) this;
        }

        public Criteria andCargoSiteIdGreaterThan(Integer value) {
            addCriterion("cargo_site_id >", value, "cargoSiteId");
            return (Criteria) this;
        }

        public Criteria andCargoSiteIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("cargo_site_id >=", value, "cargoSiteId");
            return (Criteria) this;
        }

        public Criteria andCargoSiteIdLessThan(Integer value) {
            addCriterion("cargo_site_id <", value, "cargoSiteId");
            return (Criteria) this;
        }

        public Criteria andCargoSiteIdLessThanOrEqualTo(Integer value) {
            addCriterion("cargo_site_id <=", value, "cargoSiteId");
            return (Criteria) this;
        }

        public Criteria andCargoSiteIdIn(List<Integer> values) {
            addCriterion("cargo_site_id in", values, "cargoSiteId");
            return (Criteria) this;
        }

        public Criteria andCargoSiteIdNotIn(List<Integer> values) {
            addCriterion("cargo_site_id not in", values, "cargoSiteId");
            return (Criteria) this;
        }

        public Criteria andCargoSiteIdBetween(Integer value1, Integer value2) {
            addCriterion("cargo_site_id between", value1, value2, "cargoSiteId");
            return (Criteria) this;
        }

        public Criteria andCargoSiteIdNotBetween(Integer value1, Integer value2) {
            addCriterion("cargo_site_id not between", value1, value2, "cargoSiteId");
            return (Criteria) this;
        }

        public Criteria andCargoSiteNameIsNull() {
            addCriterion("cargo_site_name is null");
            return (Criteria) this;
        }

        public Criteria andCargoSiteNameIsNotNull() {
            addCriterion("cargo_site_name is not null");
            return (Criteria) this;
        }

        public Criteria andCargoSiteNameEqualTo(String value) {
            addCriterion("cargo_site_name =", value, "cargoSiteName");
            return (Criteria) this;
        }

        public Criteria andCargoSiteNameNotEqualTo(String value) {
            addCriterion("cargo_site_name <>", value, "cargoSiteName");
            return (Criteria) this;
        }

        public Criteria andCargoSiteNameGreaterThan(String value) {
            addCriterion("cargo_site_name >", value, "cargoSiteName");
            return (Criteria) this;
        }

        public Criteria andCargoSiteNameGreaterThanOrEqualTo(String value) {
            addCriterion("cargo_site_name >=", value, "cargoSiteName");
            return (Criteria) this;
        }

        public Criteria andCargoSiteNameLessThan(String value) {
            addCriterion("cargo_site_name <", value, "cargoSiteName");
            return (Criteria) this;
        }

        public Criteria andCargoSiteNameLessThanOrEqualTo(String value) {
            addCriterion("cargo_site_name <=", value, "cargoSiteName");
            return (Criteria) this;
        }

        public Criteria andCargoSiteNameLike(String value) {
            addCriterion("cargo_site_name like", value, "cargoSiteName");
            return (Criteria) this;
        }

        public Criteria andCargoSiteNameNotLike(String value) {
            addCriterion("cargo_site_name not like", value, "cargoSiteName");
            return (Criteria) this;
        }

        public Criteria andCargoSiteNameIn(List<String> values) {
            addCriterion("cargo_site_name in", values, "cargoSiteName");
            return (Criteria) this;
        }

        public Criteria andCargoSiteNameNotIn(List<String> values) {
            addCriterion("cargo_site_name not in", values, "cargoSiteName");
            return (Criteria) this;
        }

        public Criteria andCargoSiteNameBetween(String value1, String value2) {
            addCriterion("cargo_site_name between", value1, value2, "cargoSiteName");
            return (Criteria) this;
        }

        public Criteria andCargoSiteNameNotBetween(String value1, String value2) {
            addCriterion("cargo_site_name not between", value1, value2, "cargoSiteName");
            return (Criteria) this;
        }

        public Criteria andCarTypeIdIsNull() {
            addCriterion("car_type_id is null");
            return (Criteria) this;
        }

        public Criteria andCarTypeIdIsNotNull() {
            addCriterion("car_type_id is not null");
            return (Criteria) this;
        }

        public Criteria andCarTypeIdEqualTo(Integer value) {
            addCriterion("car_type_id =", value, "carTypeId");
            return (Criteria) this;
        }

        public Criteria andCarTypeIdNotEqualTo(Integer value) {
            addCriterion("car_type_id <>", value, "carTypeId");
            return (Criteria) this;
        }

        public Criteria andCarTypeIdGreaterThan(Integer value) {
            addCriterion("car_type_id >", value, "carTypeId");
            return (Criteria) this;
        }

        public Criteria andCarTypeIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("car_type_id >=", value, "carTypeId");
            return (Criteria) this;
        }

        public Criteria andCarTypeIdLessThan(Integer value) {
            addCriterion("car_type_id <", value, "carTypeId");
            return (Criteria) this;
        }

        public Criteria andCarTypeIdLessThanOrEqualTo(Integer value) {
            addCriterion("car_type_id <=", value, "carTypeId");
            return (Criteria) this;
        }

        public Criteria andCarTypeIdIn(List<Integer> values) {
            addCriterion("car_type_id in", values, "carTypeId");
            return (Criteria) this;
        }

        public Criteria andCarTypeIdNotIn(List<Integer> values) {
            addCriterion("car_type_id not in", values, "carTypeId");
            return (Criteria) this;
        }

        public Criteria andCarTypeIdBetween(Integer value1, Integer value2) {
            addCriterion("car_type_id between", value1, value2, "carTypeId");
            return (Criteria) this;
        }

        public Criteria andCarTypeIdNotBetween(Integer value1, Integer value2) {
            addCriterion("car_type_id not between", value1, value2, "carTypeId");
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

        public Criteria andCarNumberIsNull() {
            addCriterion("car_number is null");
            return (Criteria) this;
        }

        public Criteria andCarNumberIsNotNull() {
            addCriterion("car_number is not null");
            return (Criteria) this;
        }

        public Criteria andCarNumberEqualTo(String value) {
            addCriterion("car_number =", value, "carNumber");
            return (Criteria) this;
        }

        public Criteria andCarNumberNotEqualTo(String value) {
            addCriterion("car_number <>", value, "carNumber");
            return (Criteria) this;
        }

        public Criteria andCarNumberGreaterThan(String value) {
            addCriterion("car_number >", value, "carNumber");
            return (Criteria) this;
        }

        public Criteria andCarNumberGreaterThanOrEqualTo(String value) {
            addCriterion("car_number >=", value, "carNumber");
            return (Criteria) this;
        }

        public Criteria andCarNumberLessThan(String value) {
            addCriterion("car_number <", value, "carNumber");
            return (Criteria) this;
        }

        public Criteria andCarNumberLessThanOrEqualTo(String value) {
            addCriterion("car_number <=", value, "carNumber");
            return (Criteria) this;
        }

        public Criteria andCarNumberLike(String value) {
            addCriterion("car_number like", value, "carNumber");
            return (Criteria) this;
        }

        public Criteria andCarNumberNotLike(String value) {
            addCriterion("car_number not like", value, "carNumber");
            return (Criteria) this;
        }

        public Criteria andCarNumberIn(List<String> values) {
            addCriterion("car_number in", values, "carNumber");
            return (Criteria) this;
        }

        public Criteria andCarNumberNotIn(List<String> values) {
            addCriterion("car_number not in", values, "carNumber");
            return (Criteria) this;
        }

        public Criteria andCarNumberBetween(String value1, String value2) {
            addCriterion("car_number between", value1, value2, "carNumber");
            return (Criteria) this;
        }

        public Criteria andCarNumberNotBetween(String value1, String value2) {
            addCriterion("car_number not between", value1, value2, "carNumber");
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

        public Criteria andSendWeightIsNull() {
            addCriterion("send_weight is null");
            return (Criteria) this;
        }

        public Criteria andSendWeightIsNotNull() {
            addCriterion("send_weight is not null");
            return (Criteria) this;
        }

        public Criteria andSendWeightEqualTo(BigDecimal value) {
            addCriterion("send_weight =", value, "sendWeight");
            return (Criteria) this;
        }

        public Criteria andSendWeightNotEqualTo(BigDecimal value) {
            addCriterion("send_weight <>", value, "sendWeight");
            return (Criteria) this;
        }

        public Criteria andSendWeightGreaterThan(BigDecimal value) {
            addCriterion("send_weight >", value, "sendWeight");
            return (Criteria) this;
        }

        public Criteria andSendWeightGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("send_weight >=", value, "sendWeight");
            return (Criteria) this;
        }

        public Criteria andSendWeightLessThan(BigDecimal value) {
            addCriterion("send_weight <", value, "sendWeight");
            return (Criteria) this;
        }

        public Criteria andSendWeightLessThanOrEqualTo(BigDecimal value) {
            addCriterion("send_weight <=", value, "sendWeight");
            return (Criteria) this;
        }

        public Criteria andSendWeightIn(List<BigDecimal> values) {
            addCriterion("send_weight in", values, "sendWeight");
            return (Criteria) this;
        }

        public Criteria andSendWeightNotIn(List<BigDecimal> values) {
            addCriterion("send_weight not in", values, "sendWeight");
            return (Criteria) this;
        }

        public Criteria andSendWeightBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("send_weight between", value1, value2, "sendWeight");
            return (Criteria) this;
        }

        public Criteria andSendWeightNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("send_weight not between", value1, value2, "sendWeight");
            return (Criteria) this;
        }

        public Criteria andConSendWeight2IsNull() {
            addCriterion("con_send_weight2 is null");
            return (Criteria) this;
        }

        public Criteria andConSendWeight2IsNotNull() {
            addCriterion("con_send_weight2 is not null");
            return (Criteria) this;
        }

        public Criteria andConSendWeight2EqualTo(BigDecimal value) {
            addCriterion("con_send_weight2 =", value, "conSendWeight2");
            return (Criteria) this;
        }

        public Criteria andConSendWeight2NotEqualTo(BigDecimal value) {
            addCriterion("con_send_weight2 <>", value, "conSendWeight2");
            return (Criteria) this;
        }

        public Criteria andConSendWeight2GreaterThan(BigDecimal value) {
            addCriterion("con_send_weight2 >", value, "conSendWeight2");
            return (Criteria) this;
        }

        public Criteria andConSendWeight2GreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("con_send_weight2 >=", value, "conSendWeight2");
            return (Criteria) this;
        }

        public Criteria andConSendWeight2LessThan(BigDecimal value) {
            addCriterion("con_send_weight2 <", value, "conSendWeight2");
            return (Criteria) this;
        }

        public Criteria andConSendWeight2LessThanOrEqualTo(BigDecimal value) {
            addCriterion("con_send_weight2 <=", value, "conSendWeight2");
            return (Criteria) this;
        }

        public Criteria andConSendWeight2In(List<BigDecimal> values) {
            addCriterion("con_send_weight2 in", values, "conSendWeight2");
            return (Criteria) this;
        }

        public Criteria andConSendWeight2NotIn(List<BigDecimal> values) {
            addCriterion("con_send_weight2 not in", values, "conSendWeight2");
            return (Criteria) this;
        }

        public Criteria andConSendWeight2Between(BigDecimal value1, BigDecimal value2) {
            addCriterion("con_send_weight2 between", value1, value2, "conSendWeight2");
            return (Criteria) this;
        }

        public Criteria andConSendWeight2NotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("con_send_weight2 not between", value1, value2, "conSendWeight2");
            return (Criteria) this;
        }

        public Criteria andUnloadWeightIsNull() {
            addCriterion("unload_weight is null");
            return (Criteria) this;
        }

        public Criteria andUnloadWeightIsNotNull() {
            addCriterion("unload_weight is not null");
            return (Criteria) this;
        }

        public Criteria andUnloadWeightEqualTo(BigDecimal value) {
            addCriterion("unload_weight =", value, "unloadWeight");
            return (Criteria) this;
        }

        public Criteria andUnloadWeightNotEqualTo(BigDecimal value) {
            addCriterion("unload_weight <>", value, "unloadWeight");
            return (Criteria) this;
        }

        public Criteria andUnloadWeightGreaterThan(BigDecimal value) {
            addCriterion("unload_weight >", value, "unloadWeight");
            return (Criteria) this;
        }

        public Criteria andUnloadWeightGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("unload_weight >=", value, "unloadWeight");
            return (Criteria) this;
        }

        public Criteria andUnloadWeightLessThan(BigDecimal value) {
            addCriterion("unload_weight <", value, "unloadWeight");
            return (Criteria) this;
        }

        public Criteria andUnloadWeightLessThanOrEqualTo(BigDecimal value) {
            addCriterion("unload_weight <=", value, "unloadWeight");
            return (Criteria) this;
        }

        public Criteria andUnloadWeightIn(List<BigDecimal> values) {
            addCriterion("unload_weight in", values, "unloadWeight");
            return (Criteria) this;
        }

        public Criteria andUnloadWeightNotIn(List<BigDecimal> values) {
            addCriterion("unload_weight not in", values, "unloadWeight");
            return (Criteria) this;
        }

        public Criteria andUnloadWeightBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("unload_weight between", value1, value2, "unloadWeight");
            return (Criteria) this;
        }

        public Criteria andUnloadWeightNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("unload_weight not between", value1, value2, "unloadWeight");
            return (Criteria) this;
        }

        public Criteria andConUnloadWeight2IsNull() {
            addCriterion("con_unload_weight2 is null");
            return (Criteria) this;
        }

        public Criteria andConUnloadWeight2IsNotNull() {
            addCriterion("con_unload_weight2 is not null");
            return (Criteria) this;
        }

        public Criteria andConUnloadWeight2EqualTo(BigDecimal value) {
            addCriterion("con_unload_weight2 =", value, "conUnloadWeight2");
            return (Criteria) this;
        }

        public Criteria andConUnloadWeight2NotEqualTo(BigDecimal value) {
            addCriterion("con_unload_weight2 <>", value, "conUnloadWeight2");
            return (Criteria) this;
        }

        public Criteria andConUnloadWeight2GreaterThan(BigDecimal value) {
            addCriterion("con_unload_weight2 >", value, "conUnloadWeight2");
            return (Criteria) this;
        }

        public Criteria andConUnloadWeight2GreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("con_unload_weight2 >=", value, "conUnloadWeight2");
            return (Criteria) this;
        }

        public Criteria andConUnloadWeight2LessThan(BigDecimal value) {
            addCriterion("con_unload_weight2 <", value, "conUnloadWeight2");
            return (Criteria) this;
        }

        public Criteria andConUnloadWeight2LessThanOrEqualTo(BigDecimal value) {
            addCriterion("con_unload_weight2 <=", value, "conUnloadWeight2");
            return (Criteria) this;
        }

        public Criteria andConUnloadWeight2In(List<BigDecimal> values) {
            addCriterion("con_unload_weight2 in", values, "conUnloadWeight2");
            return (Criteria) this;
        }

        public Criteria andConUnloadWeight2NotIn(List<BigDecimal> values) {
            addCriterion("con_unload_weight2 not in", values, "conUnloadWeight2");
            return (Criteria) this;
        }

        public Criteria andConUnloadWeight2Between(BigDecimal value1, BigDecimal value2) {
            addCriterion("con_unload_weight2 between", value1, value2, "conUnloadWeight2");
            return (Criteria) this;
        }

        public Criteria andConUnloadWeight2NotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("con_unload_weight2 not between", value1, value2, "conUnloadWeight2");
            return (Criteria) this;
        }

        public Criteria andSendImgIsNull() {
            addCriterion("send_img is null");
            return (Criteria) this;
        }

        public Criteria andSendImgIsNotNull() {
            addCriterion("send_img is not null");
            return (Criteria) this;
        }

        public Criteria andSendImgEqualTo(String value) {
            addCriterion("send_img =", value, "sendImg");
            return (Criteria) this;
        }

        public Criteria andSendImgNotEqualTo(String value) {
            addCriterion("send_img <>", value, "sendImg");
            return (Criteria) this;
        }

        public Criteria andSendImgGreaterThan(String value) {
            addCriterion("send_img >", value, "sendImg");
            return (Criteria) this;
        }

        public Criteria andSendImgGreaterThanOrEqualTo(String value) {
            addCriterion("send_img >=", value, "sendImg");
            return (Criteria) this;
        }

        public Criteria andSendImgLessThan(String value) {
            addCriterion("send_img <", value, "sendImg");
            return (Criteria) this;
        }

        public Criteria andSendImgLessThanOrEqualTo(String value) {
            addCriterion("send_img <=", value, "sendImg");
            return (Criteria) this;
        }

        public Criteria andSendImgLike(String value) {
            addCriterion("send_img like", value, "sendImg");
            return (Criteria) this;
        }

        public Criteria andSendImgNotLike(String value) {
            addCriterion("send_img not like", value, "sendImg");
            return (Criteria) this;
        }

        public Criteria andSendImgIn(List<String> values) {
            addCriterion("send_img in", values, "sendImg");
            return (Criteria) this;
        }

        public Criteria andSendImgNotIn(List<String> values) {
            addCriterion("send_img not in", values, "sendImg");
            return (Criteria) this;
        }

        public Criteria andSendImgBetween(String value1, String value2) {
            addCriterion("send_img between", value1, value2, "sendImg");
            return (Criteria) this;
        }

        public Criteria andSendImgNotBetween(String value1, String value2) {
            addCriterion("send_img not between", value1, value2, "sendImg");
            return (Criteria) this;
        }

        public Criteria andUnloadImgIsNull() {
            addCriterion("unload_img is null");
            return (Criteria) this;
        }

        public Criteria andUnloadImgIsNotNull() {
            addCriterion("unload_img is not null");
            return (Criteria) this;
        }

        public Criteria andUnloadImgEqualTo(String value) {
            addCriterion("unload_img =", value, "unloadImg");
            return (Criteria) this;
        }

        public Criteria andUnloadImgNotEqualTo(String value) {
            addCriterion("unload_img <>", value, "unloadImg");
            return (Criteria) this;
        }

        public Criteria andUnloadImgGreaterThan(String value) {
            addCriterion("unload_img >", value, "unloadImg");
            return (Criteria) this;
        }

        public Criteria andUnloadImgGreaterThanOrEqualTo(String value) {
            addCriterion("unload_img >=", value, "unloadImg");
            return (Criteria) this;
        }

        public Criteria andUnloadImgLessThan(String value) {
            addCriterion("unload_img <", value, "unloadImg");
            return (Criteria) this;
        }

        public Criteria andUnloadImgLessThanOrEqualTo(String value) {
            addCriterion("unload_img <=", value, "unloadImg");
            return (Criteria) this;
        }

        public Criteria andUnloadImgLike(String value) {
            addCriterion("unload_img like", value, "unloadImg");
            return (Criteria) this;
        }

        public Criteria andUnloadImgNotLike(String value) {
            addCriterion("unload_img not like", value, "unloadImg");
            return (Criteria) this;
        }

        public Criteria andUnloadImgIn(List<String> values) {
            addCriterion("unload_img in", values, "unloadImg");
            return (Criteria) this;
        }

        public Criteria andUnloadImgNotIn(List<String> values) {
            addCriterion("unload_img not in", values, "unloadImg");
            return (Criteria) this;
        }

        public Criteria andUnloadImgBetween(String value1, String value2) {
            addCriterion("unload_img between", value1, value2, "unloadImg");
            return (Criteria) this;
        }

        public Criteria andUnloadImgNotBetween(String value1, String value2) {
            addCriterion("unload_img not between", value1, value2, "unloadImg");
            return (Criteria) this;
        }

        public Criteria andArriveCargoPlaceIdIsNull() {
            addCriterion("arrive_cargo_place_id is null");
            return (Criteria) this;
        }

        public Criteria andArriveCargoPlaceIdIsNotNull() {
            addCriterion("arrive_cargo_place_id is not null");
            return (Criteria) this;
        }

        public Criteria andArriveCargoPlaceIdEqualTo(Integer value) {
            addCriterion("arrive_cargo_place_id =", value, "arriveCargoPlaceId");
            return (Criteria) this;
        }

        public Criteria andArriveCargoPlaceIdNotEqualTo(Integer value) {
            addCriterion("arrive_cargo_place_id <>", value, "arriveCargoPlaceId");
            return (Criteria) this;
        }

        public Criteria andArriveCargoPlaceIdGreaterThan(Integer value) {
            addCriterion("arrive_cargo_place_id >", value, "arriveCargoPlaceId");
            return (Criteria) this;
        }

        public Criteria andArriveCargoPlaceIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("arrive_cargo_place_id >=", value, "arriveCargoPlaceId");
            return (Criteria) this;
        }

        public Criteria andArriveCargoPlaceIdLessThan(Integer value) {
            addCriterion("arrive_cargo_place_id <", value, "arriveCargoPlaceId");
            return (Criteria) this;
        }

        public Criteria andArriveCargoPlaceIdLessThanOrEqualTo(Integer value) {
            addCriterion("arrive_cargo_place_id <=", value, "arriveCargoPlaceId");
            return (Criteria) this;
        }

        public Criteria andArriveCargoPlaceIdIn(List<Integer> values) {
            addCriterion("arrive_cargo_place_id in", values, "arriveCargoPlaceId");
            return (Criteria) this;
        }

        public Criteria andArriveCargoPlaceIdNotIn(List<Integer> values) {
            addCriterion("arrive_cargo_place_id not in", values, "arriveCargoPlaceId");
            return (Criteria) this;
        }

        public Criteria andArriveCargoPlaceIdBetween(Integer value1, Integer value2) {
            addCriterion("arrive_cargo_place_id between", value1, value2, "arriveCargoPlaceId");
            return (Criteria) this;
        }

        public Criteria andArriveCargoPlaceIdNotBetween(Integer value1, Integer value2) {
            addCriterion("arrive_cargo_place_id not between", value1, value2, "arriveCargoPlaceId");
            return (Criteria) this;
        }

        public Criteria andArriveCargoPlaceNameIsNull() {
            addCriterion("arrive_cargo_place_name is null");
            return (Criteria) this;
        }

        public Criteria andArriveCargoPlaceNameIsNotNull() {
            addCriterion("arrive_cargo_place_name is not null");
            return (Criteria) this;
        }

        public Criteria andArriveCargoPlaceNameEqualTo(String value) {
            addCriterion("arrive_cargo_place_name =", value, "arriveCargoPlaceName");
            return (Criteria) this;
        }

        public Criteria andArriveCargoPlaceNameNotEqualTo(String value) {
            addCriterion("arrive_cargo_place_name <>", value, "arriveCargoPlaceName");
            return (Criteria) this;
        }

        public Criteria andArriveCargoPlaceNameGreaterThan(String value) {
            addCriterion("arrive_cargo_place_name >", value, "arriveCargoPlaceName");
            return (Criteria) this;
        }

        public Criteria andArriveCargoPlaceNameGreaterThanOrEqualTo(String value) {
            addCriterion("arrive_cargo_place_name >=", value, "arriveCargoPlaceName");
            return (Criteria) this;
        }

        public Criteria andArriveCargoPlaceNameLessThan(String value) {
            addCriterion("arrive_cargo_place_name <", value, "arriveCargoPlaceName");
            return (Criteria) this;
        }

        public Criteria andArriveCargoPlaceNameLessThanOrEqualTo(String value) {
            addCriterion("arrive_cargo_place_name <=", value, "arriveCargoPlaceName");
            return (Criteria) this;
        }

        public Criteria andArriveCargoPlaceNameLike(String value) {
            addCriterion("arrive_cargo_place_name like", value, "arriveCargoPlaceName");
            return (Criteria) this;
        }

        public Criteria andArriveCargoPlaceNameNotLike(String value) {
            addCriterion("arrive_cargo_place_name not like", value, "arriveCargoPlaceName");
            return (Criteria) this;
        }

        public Criteria andArriveCargoPlaceNameIn(List<String> values) {
            addCriterion("arrive_cargo_place_name in", values, "arriveCargoPlaceName");
            return (Criteria) this;
        }

        public Criteria andArriveCargoPlaceNameNotIn(List<String> values) {
            addCriterion("arrive_cargo_place_name not in", values, "arriveCargoPlaceName");
            return (Criteria) this;
        }

        public Criteria andArriveCargoPlaceNameBetween(String value1, String value2) {
            addCriterion("arrive_cargo_place_name between", value1, value2, "arriveCargoPlaceName");
            return (Criteria) this;
        }

        public Criteria andArriveCargoPlaceNameNotBetween(String value1, String value2) {
            addCriterion("arrive_cargo_place_name not between", value1, value2, "arriveCargoPlaceName");
            return (Criteria) this;
        }

        public Criteria andArriveCargoSiteIdIsNull() {
            addCriterion("arrive_cargo_site_id is null");
            return (Criteria) this;
        }

        public Criteria andArriveCargoSiteIdIsNotNull() {
            addCriterion("arrive_cargo_site_id is not null");
            return (Criteria) this;
        }

        public Criteria andArriveCargoSiteIdEqualTo(Integer value) {
            addCriterion("arrive_cargo_site_id =", value, "arriveCargoSiteId");
            return (Criteria) this;
        }

        public Criteria andArriveCargoSiteIdNotEqualTo(Integer value) {
            addCriterion("arrive_cargo_site_id <>", value, "arriveCargoSiteId");
            return (Criteria) this;
        }

        public Criteria andArriveCargoSiteIdGreaterThan(Integer value) {
            addCriterion("arrive_cargo_site_id >", value, "arriveCargoSiteId");
            return (Criteria) this;
        }

        public Criteria andArriveCargoSiteIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("arrive_cargo_site_id >=", value, "arriveCargoSiteId");
            return (Criteria) this;
        }

        public Criteria andArriveCargoSiteIdLessThan(Integer value) {
            addCriterion("arrive_cargo_site_id <", value, "arriveCargoSiteId");
            return (Criteria) this;
        }

        public Criteria andArriveCargoSiteIdLessThanOrEqualTo(Integer value) {
            addCriterion("arrive_cargo_site_id <=", value, "arriveCargoSiteId");
            return (Criteria) this;
        }

        public Criteria andArriveCargoSiteIdIn(List<Integer> values) {
            addCriterion("arrive_cargo_site_id in", values, "arriveCargoSiteId");
            return (Criteria) this;
        }

        public Criteria andArriveCargoSiteIdNotIn(List<Integer> values) {
            addCriterion("arrive_cargo_site_id not in", values, "arriveCargoSiteId");
            return (Criteria) this;
        }

        public Criteria andArriveCargoSiteIdBetween(Integer value1, Integer value2) {
            addCriterion("arrive_cargo_site_id between", value1, value2, "arriveCargoSiteId");
            return (Criteria) this;
        }

        public Criteria andArriveCargoSiteIdNotBetween(Integer value1, Integer value2) {
            addCriterion("arrive_cargo_site_id not between", value1, value2, "arriveCargoSiteId");
            return (Criteria) this;
        }

        public Criteria andArriveCargoSiteNameIsNull() {
            addCriterion("arrive_cargo_site_name is null");
            return (Criteria) this;
        }

        public Criteria andArriveCargoSiteNameIsNotNull() {
            addCriterion("arrive_cargo_site_name is not null");
            return (Criteria) this;
        }

        public Criteria andArriveCargoSiteNameEqualTo(String value) {
            addCriterion("arrive_cargo_site_name =", value, "arriveCargoSiteName");
            return (Criteria) this;
        }

        public Criteria andArriveCargoSiteNameNotEqualTo(String value) {
            addCriterion("arrive_cargo_site_name <>", value, "arriveCargoSiteName");
            return (Criteria) this;
        }

        public Criteria andArriveCargoSiteNameGreaterThan(String value) {
            addCriterion("arrive_cargo_site_name >", value, "arriveCargoSiteName");
            return (Criteria) this;
        }

        public Criteria andArriveCargoSiteNameGreaterThanOrEqualTo(String value) {
            addCriterion("arrive_cargo_site_name >=", value, "arriveCargoSiteName");
            return (Criteria) this;
        }

        public Criteria andArriveCargoSiteNameLessThan(String value) {
            addCriterion("arrive_cargo_site_name <", value, "arriveCargoSiteName");
            return (Criteria) this;
        }

        public Criteria andArriveCargoSiteNameLessThanOrEqualTo(String value) {
            addCriterion("arrive_cargo_site_name <=", value, "arriveCargoSiteName");
            return (Criteria) this;
        }

        public Criteria andArriveCargoSiteNameLike(String value) {
            addCriterion("arrive_cargo_site_name like", value, "arriveCargoSiteName");
            return (Criteria) this;
        }

        public Criteria andArriveCargoSiteNameNotLike(String value) {
            addCriterion("arrive_cargo_site_name not like", value, "arriveCargoSiteName");
            return (Criteria) this;
        }

        public Criteria andArriveCargoSiteNameIn(List<String> values) {
            addCriterion("arrive_cargo_site_name in", values, "arriveCargoSiteName");
            return (Criteria) this;
        }

        public Criteria andArriveCargoSiteNameNotIn(List<String> values) {
            addCriterion("arrive_cargo_site_name not in", values, "arriveCargoSiteName");
            return (Criteria) this;
        }

        public Criteria andArriveCargoSiteNameBetween(String value1, String value2) {
            addCriterion("arrive_cargo_site_name between", value1, value2, "arriveCargoSiteName");
            return (Criteria) this;
        }

        public Criteria andArriveCargoSiteNameNotBetween(String value1, String value2) {
            addCriterion("arrive_cargo_site_name not between", value1, value2, "arriveCargoSiteName");
            return (Criteria) this;
        }

        public Criteria andArriveUnloadTimeIsNull() {
            addCriterion("arrive_unload_time is null");
            return (Criteria) this;
        }

        public Criteria andArriveUnloadTimeIsNotNull() {
            addCriterion("arrive_unload_time is not null");
            return (Criteria) this;
        }

        public Criteria andArriveUnloadTimeEqualTo(Date value) {
            addCriterion("arrive_unload_time =", value, "arriveUnloadTime");
            return (Criteria) this;
        }

        public Criteria andArriveUnloadTimeNotEqualTo(Date value) {
            addCriterion("arrive_unload_time <>", value, "arriveUnloadTime");
            return (Criteria) this;
        }

        public Criteria andArriveUnloadTimeGreaterThan(Date value) {
            addCriterion("arrive_unload_time >", value, "arriveUnloadTime");
            return (Criteria) this;
        }

        public Criteria andArriveUnloadTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("arrive_unload_time >=", value, "arriveUnloadTime");
            return (Criteria) this;
        }

        public Criteria andArriveUnloadTimeLessThan(Date value) {
            addCriterion("arrive_unload_time <", value, "arriveUnloadTime");
            return (Criteria) this;
        }

        public Criteria andArriveUnloadTimeLessThanOrEqualTo(Date value) {
            addCriterion("arrive_unload_time <=", value, "arriveUnloadTime");
            return (Criteria) this;
        }

        public Criteria andArriveUnloadTimeIn(List<Date> values) {
            addCriterion("arrive_unload_time in", values, "arriveUnloadTime");
            return (Criteria) this;
        }

        public Criteria andArriveUnloadTimeNotIn(List<Date> values) {
            addCriterion("arrive_unload_time not in", values, "arriveUnloadTime");
            return (Criteria) this;
        }

        public Criteria andArriveUnloadTimeBetween(Date value1, Date value2) {
            addCriterion("arrive_unload_time between", value1, value2, "arriveUnloadTime");
            return (Criteria) this;
        }

        public Criteria andArriveUnloadTimeNotBetween(Date value1, Date value2) {
            addCriterion("arrive_unload_time not between", value1, value2, "arriveUnloadTime");
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