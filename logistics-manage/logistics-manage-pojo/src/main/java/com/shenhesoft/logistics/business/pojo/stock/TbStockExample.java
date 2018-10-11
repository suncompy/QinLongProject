package com.shenhesoft.logistics.business.pojo.stock;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TbStockExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TbStockExample() {
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

        public Criteria andStationIdIsNull() {
            addCriterion("station_id is null");
            return (Criteria) this;
        }

        public Criteria andStationIdIsNotNull() {
            addCriterion("station_id is not null");
            return (Criteria) this;
        }

        public Criteria andStationIdEqualTo(Integer value) {
            addCriterion("station_id =", value, "stationId");
            return (Criteria) this;
        }

        public Criteria andStationIdNotEqualTo(Integer value) {
            addCriterion("station_id <>", value, "stationId");
            return (Criteria) this;
        }

        public Criteria andStationIdGreaterThan(Integer value) {
            addCriterion("station_id >", value, "stationId");
            return (Criteria) this;
        }

        public Criteria andStationIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("station_id >=", value, "stationId");
            return (Criteria) this;
        }

        public Criteria andStationIdLessThan(Integer value) {
            addCriterion("station_id <", value, "stationId");
            return (Criteria) this;
        }

        public Criteria andStationIdLessThanOrEqualTo(Integer value) {
            addCriterion("station_id <=", value, "stationId");
            return (Criteria) this;
        }

        public Criteria andStationIdIn(List<Integer> values) {
            addCriterion("station_id in", values, "stationId");
            return (Criteria) this;
        }

        public Criteria andStationIdNotIn(List<Integer> values) {
            addCriterion("station_id not in", values, "stationId");
            return (Criteria) this;
        }

        public Criteria andStationIdBetween(Integer value1, Integer value2) {
            addCriterion("station_id between", value1, value2, "stationId");
            return (Criteria) this;
        }

        public Criteria andStationIdNotBetween(Integer value1, Integer value2) {
            addCriterion("station_id not between", value1, value2, "stationId");
            return (Criteria) this;
        }

        public Criteria andStationNameIsNull() {
            addCriterion("station_name is null");
            return (Criteria) this;
        }

        public Criteria andStationNameIsNotNull() {
            addCriterion("station_name is not null");
            return (Criteria) this;
        }

        public Criteria andStationNameEqualTo(String value) {
            addCriterion("station_name =", value, "stationName");
            return (Criteria) this;
        }

        public Criteria andStationNameNotEqualTo(String value) {
            addCriterion("station_name <>", value, "stationName");
            return (Criteria) this;
        }

        public Criteria andStationNameGreaterThan(String value) {
            addCriterion("station_name >", value, "stationName");
            return (Criteria) this;
        }

        public Criteria andStationNameGreaterThanOrEqualTo(String value) {
            addCriterion("station_name >=", value, "stationName");
            return (Criteria) this;
        }

        public Criteria andStationNameLessThan(String value) {
            addCriterion("station_name <", value, "stationName");
            return (Criteria) this;
        }

        public Criteria andStationNameLessThanOrEqualTo(String value) {
            addCriterion("station_name <=", value, "stationName");
            return (Criteria) this;
        }

        public Criteria andStationNameLike(String value) {
            addCriterion("station_name like", value, "stationName");
            return (Criteria) this;
        }

        public Criteria andStationNameNotLike(String value) {
            addCriterion("station_name not like", value, "stationName");
            return (Criteria) this;
        }

        public Criteria andStationNameIn(List<String> values) {
            addCriterion("station_name in", values, "stationName");
            return (Criteria) this;
        }

        public Criteria andStationNameNotIn(List<String> values) {
            addCriterion("station_name not in", values, "stationName");
            return (Criteria) this;
        }

        public Criteria andStationNameBetween(String value1, String value2) {
            addCriterion("station_name between", value1, value2, "stationName");
            return (Criteria) this;
        }

        public Criteria andStationNameNotBetween(String value1, String value2) {
            addCriterion("station_name not between", value1, value2, "stationName");
            return (Criteria) this;
        }

        public Criteria andFreightYardIdIsNull() {
            addCriterion("freight_yard_id is null");
            return (Criteria) this;
        }

        public Criteria andFreightYardIdIsNotNull() {
            addCriterion("freight_yard_id is not null");
            return (Criteria) this;
        }

        public Criteria andFreightYardIdEqualTo(Integer value) {
            addCriterion("freight_yard_id =", value, "freightYardId");
            return (Criteria) this;
        }

        public Criteria andFreightYardIdNotEqualTo(Integer value) {
            addCriterion("freight_yard_id <>", value, "freightYardId");
            return (Criteria) this;
        }

        public Criteria andFreightYardIdGreaterThan(Integer value) {
            addCriterion("freight_yard_id >", value, "freightYardId");
            return (Criteria) this;
        }

        public Criteria andFreightYardIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("freight_yard_id >=", value, "freightYardId");
            return (Criteria) this;
        }

        public Criteria andFreightYardIdLessThan(Integer value) {
            addCriterion("freight_yard_id <", value, "freightYardId");
            return (Criteria) this;
        }

        public Criteria andFreightYardIdLessThanOrEqualTo(Integer value) {
            addCriterion("freight_yard_id <=", value, "freightYardId");
            return (Criteria) this;
        }

        public Criteria andFreightYardIdIn(List<Integer> values) {
            addCriterion("freight_yard_id in", values, "freightYardId");
            return (Criteria) this;
        }

        public Criteria andFreightYardIdNotIn(List<Integer> values) {
            addCriterion("freight_yard_id not in", values, "freightYardId");
            return (Criteria) this;
        }

        public Criteria andFreightYardIdBetween(Integer value1, Integer value2) {
            addCriterion("freight_yard_id between", value1, value2, "freightYardId");
            return (Criteria) this;
        }

        public Criteria andFreightYardIdNotBetween(Integer value1, Integer value2) {
            addCriterion("freight_yard_id not between", value1, value2, "freightYardId");
            return (Criteria) this;
        }

        public Criteria andFreightYardNameIsNull() {
            addCriterion("freight_yard_name is null");
            return (Criteria) this;
        }

        public Criteria andFreightYardNameIsNotNull() {
            addCriterion("freight_yard_name is not null");
            return (Criteria) this;
        }

        public Criteria andFreightYardNameEqualTo(String value) {
            addCriterion("freight_yard_name =", value, "freightYardName");
            return (Criteria) this;
        }

        public Criteria andFreightYardNameNotEqualTo(String value) {
            addCriterion("freight_yard_name <>", value, "freightYardName");
            return (Criteria) this;
        }

        public Criteria andFreightYardNameGreaterThan(String value) {
            addCriterion("freight_yard_name >", value, "freightYardName");
            return (Criteria) this;
        }

        public Criteria andFreightYardNameGreaterThanOrEqualTo(String value) {
            addCriterion("freight_yard_name >=", value, "freightYardName");
            return (Criteria) this;
        }

        public Criteria andFreightYardNameLessThan(String value) {
            addCriterion("freight_yard_name <", value, "freightYardName");
            return (Criteria) this;
        }

        public Criteria andFreightYardNameLessThanOrEqualTo(String value) {
            addCriterion("freight_yard_name <=", value, "freightYardName");
            return (Criteria) this;
        }

        public Criteria andFreightYardNameLike(String value) {
            addCriterion("freight_yard_name like", value, "freightYardName");
            return (Criteria) this;
        }

        public Criteria andFreightYardNameNotLike(String value) {
            addCriterion("freight_yard_name not like", value, "freightYardName");
            return (Criteria) this;
        }

        public Criteria andFreightYardNameIn(List<String> values) {
            addCriterion("freight_yard_name in", values, "freightYardName");
            return (Criteria) this;
        }

        public Criteria andFreightYardNameNotIn(List<String> values) {
            addCriterion("freight_yard_name not in", values, "freightYardName");
            return (Criteria) this;
        }

        public Criteria andFreightYardNameBetween(String value1, String value2) {
            addCriterion("freight_yard_name between", value1, value2, "freightYardName");
            return (Criteria) this;
        }

        public Criteria andFreightYardNameNotBetween(String value1, String value2) {
            addCriterion("freight_yard_name not between", value1, value2, "freightYardName");
            return (Criteria) this;
        }

        public Criteria andCargoLocationIdIsNull() {
            addCriterion("cargo_location_id is null");
            return (Criteria) this;
        }

        public Criteria andCargoLocationIdIsNotNull() {
            addCriterion("cargo_location_id is not null");
            return (Criteria) this;
        }

        public Criteria andCargoLocationIdEqualTo(Integer value) {
            addCriterion("cargo_location_id =", value, "cargoLocationId");
            return (Criteria) this;
        }

        public Criteria andCargoLocationIdNotEqualTo(Integer value) {
            addCriterion("cargo_location_id <>", value, "cargoLocationId");
            return (Criteria) this;
        }

        public Criteria andCargoLocationIdGreaterThan(Integer value) {
            addCriterion("cargo_location_id >", value, "cargoLocationId");
            return (Criteria) this;
        }

        public Criteria andCargoLocationIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("cargo_location_id >=", value, "cargoLocationId");
            return (Criteria) this;
        }

        public Criteria andCargoLocationIdLessThan(Integer value) {
            addCriterion("cargo_location_id <", value, "cargoLocationId");
            return (Criteria) this;
        }

        public Criteria andCargoLocationIdLessThanOrEqualTo(Integer value) {
            addCriterion("cargo_location_id <=", value, "cargoLocationId");
            return (Criteria) this;
        }

        public Criteria andCargoLocationIdIn(List<Integer> values) {
            addCriterion("cargo_location_id in", values, "cargoLocationId");
            return (Criteria) this;
        }

        public Criteria andCargoLocationIdNotIn(List<Integer> values) {
            addCriterion("cargo_location_id not in", values, "cargoLocationId");
            return (Criteria) this;
        }

        public Criteria andCargoLocationIdBetween(Integer value1, Integer value2) {
            addCriterion("cargo_location_id between", value1, value2, "cargoLocationId");
            return (Criteria) this;
        }

        public Criteria andCargoLocationIdNotBetween(Integer value1, Integer value2) {
            addCriterion("cargo_location_id not between", value1, value2, "cargoLocationId");
            return (Criteria) this;
        }

        public Criteria andCargoLocationNameIsNull() {
            addCriterion("cargo_location_name is null");
            return (Criteria) this;
        }

        public Criteria andCargoLocationNameIsNotNull() {
            addCriterion("cargo_location_name is not null");
            return (Criteria) this;
        }

        public Criteria andCargoLocationNameEqualTo(String value) {
            addCriterion("cargo_location_name =", value, "cargoLocationName");
            return (Criteria) this;
        }

        public Criteria andCargoLocationNameNotEqualTo(String value) {
            addCriterion("cargo_location_name <>", value, "cargoLocationName");
            return (Criteria) this;
        }

        public Criteria andCargoLocationNameGreaterThan(String value) {
            addCriterion("cargo_location_name >", value, "cargoLocationName");
            return (Criteria) this;
        }

        public Criteria andCargoLocationNameGreaterThanOrEqualTo(String value) {
            addCriterion("cargo_location_name >=", value, "cargoLocationName");
            return (Criteria) this;
        }

        public Criteria andCargoLocationNameLessThan(String value) {
            addCriterion("cargo_location_name <", value, "cargoLocationName");
            return (Criteria) this;
        }

        public Criteria andCargoLocationNameLessThanOrEqualTo(String value) {
            addCriterion("cargo_location_name <=", value, "cargoLocationName");
            return (Criteria) this;
        }

        public Criteria andCargoLocationNameLike(String value) {
            addCriterion("cargo_location_name like", value, "cargoLocationName");
            return (Criteria) this;
        }

        public Criteria andCargoLocationNameNotLike(String value) {
            addCriterion("cargo_location_name not like", value, "cargoLocationName");
            return (Criteria) this;
        }

        public Criteria andCargoLocationNameIn(List<String> values) {
            addCriterion("cargo_location_name in", values, "cargoLocationName");
            return (Criteria) this;
        }

        public Criteria andCargoLocationNameNotIn(List<String> values) {
            addCriterion("cargo_location_name not in", values, "cargoLocationName");
            return (Criteria) this;
        }

        public Criteria andCargoLocationNameBetween(String value1, String value2) {
            addCriterion("cargo_location_name between", value1, value2, "cargoLocationName");
            return (Criteria) this;
        }

        public Criteria andCargoLocationNameNotBetween(String value1, String value2) {
            addCriterion("cargo_location_name not between", value1, value2, "cargoLocationName");
            return (Criteria) this;
        }

        public Criteria andEnterQtyIsNull() {
            addCriterion("enter_qty is null");
            return (Criteria) this;
        }

        public Criteria andEnterQtyIsNotNull() {
            addCriterion("enter_qty is not null");
            return (Criteria) this;
        }

        public Criteria andEnterQtyEqualTo(BigDecimal value) {
            addCriterion("enter_qty =", value, "enterQty");
            return (Criteria) this;
        }

        public Criteria andEnterQtyNotEqualTo(BigDecimal value) {
            addCriterion("enter_qty <>", value, "enterQty");
            return (Criteria) this;
        }

        public Criteria andEnterQtyGreaterThan(BigDecimal value) {
            addCriterion("enter_qty >", value, "enterQty");
            return (Criteria) this;
        }

        public Criteria andEnterQtyGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("enter_qty >=", value, "enterQty");
            return (Criteria) this;
        }

        public Criteria andEnterQtyLessThan(BigDecimal value) {
            addCriterion("enter_qty <", value, "enterQty");
            return (Criteria) this;
        }

        public Criteria andEnterQtyLessThanOrEqualTo(BigDecimal value) {
            addCriterion("enter_qty <=", value, "enterQty");
            return (Criteria) this;
        }

        public Criteria andEnterQtyIn(List<BigDecimal> values) {
            addCriterion("enter_qty in", values, "enterQty");
            return (Criteria) this;
        }

        public Criteria andEnterQtyNotIn(List<BigDecimal> values) {
            addCriterion("enter_qty not in", values, "enterQty");
            return (Criteria) this;
        }

        public Criteria andEnterQtyBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("enter_qty between", value1, value2, "enterQty");
            return (Criteria) this;
        }

        public Criteria andEnterQtyNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("enter_qty not between", value1, value2, "enterQty");
            return (Criteria) this;
        }

        public Criteria andOutQtyIsNull() {
            addCriterion("out_qty is null");
            return (Criteria) this;
        }

        public Criteria andOutQtyIsNotNull() {
            addCriterion("out_qty is not null");
            return (Criteria) this;
        }

        public Criteria andOutQtyEqualTo(BigDecimal value) {
            addCriterion("out_qty =", value, "outQty");
            return (Criteria) this;
        }

        public Criteria andOutQtyNotEqualTo(BigDecimal value) {
            addCriterion("out_qty <>", value, "outQty");
            return (Criteria) this;
        }

        public Criteria andOutQtyGreaterThan(BigDecimal value) {
            addCriterion("out_qty >", value, "outQty");
            return (Criteria) this;
        }

        public Criteria andOutQtyGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("out_qty >=", value, "outQty");
            return (Criteria) this;
        }

        public Criteria andOutQtyLessThan(BigDecimal value) {
            addCriterion("out_qty <", value, "outQty");
            return (Criteria) this;
        }

        public Criteria andOutQtyLessThanOrEqualTo(BigDecimal value) {
            addCriterion("out_qty <=", value, "outQty");
            return (Criteria) this;
        }

        public Criteria andOutQtyIn(List<BigDecimal> values) {
            addCriterion("out_qty in", values, "outQty");
            return (Criteria) this;
        }

        public Criteria andOutQtyNotIn(List<BigDecimal> values) {
            addCriterion("out_qty not in", values, "outQty");
            return (Criteria) this;
        }

        public Criteria andOutQtyBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("out_qty between", value1, value2, "outQty");
            return (Criteria) this;
        }

        public Criteria andOutQtyNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("out_qty not between", value1, value2, "outQty");
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

        public Criteria andContainerNumEqualTo(Integer value) {
            addCriterion("container_num =", value, "containerNum");
            return (Criteria) this;
        }

        public Criteria andContainerNumNotEqualTo(Integer value) {
            addCriterion("container_num <>", value, "containerNum");
            return (Criteria) this;
        }

        public Criteria andContainerNumGreaterThan(Integer value) {
            addCriterion("container_num >", value, "containerNum");
            return (Criteria) this;
        }

        public Criteria andContainerNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("container_num >=", value, "containerNum");
            return (Criteria) this;
        }

        public Criteria andContainerNumLessThan(Integer value) {
            addCriterion("container_num <", value, "containerNum");
            return (Criteria) this;
        }

        public Criteria andContainerNumLessThanOrEqualTo(Integer value) {
            addCriterion("container_num <=", value, "containerNum");
            return (Criteria) this;
        }

        public Criteria andContainerNumIn(List<Integer> values) {
            addCriterion("container_num in", values, "containerNum");
            return (Criteria) this;
        }

        public Criteria andContainerNumNotIn(List<Integer> values) {
            addCriterion("container_num not in", values, "containerNum");
            return (Criteria) this;
        }

        public Criteria andContainerNumBetween(Integer value1, Integer value2) {
            addCriterion("container_num between", value1, value2, "containerNum");
            return (Criteria) this;
        }

        public Criteria andContainerNumNotBetween(Integer value1, Integer value2) {
            addCriterion("container_num not between", value1, value2, "containerNum");
            return (Criteria) this;
        }

        public Criteria andCurrentQtyIsNull() {
            addCriterion("current_qty is null");
            return (Criteria) this;
        }

        public Criteria andCurrentQtyIsNotNull() {
            addCriterion("current_qty is not null");
            return (Criteria) this;
        }

        public Criteria andCurrentQtyEqualTo(BigDecimal value) {
            addCriterion("current_qty =", value, "currentQty");
            return (Criteria) this;
        }

        public Criteria andCurrentQtyNotEqualTo(BigDecimal value) {
            addCriterion("current_qty <>", value, "currentQty");
            return (Criteria) this;
        }

        public Criteria andCurrentQtyGreaterThan(BigDecimal value) {
            addCriterion("current_qty >", value, "currentQty");
            return (Criteria) this;
        }

        public Criteria andCurrentQtyGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("current_qty >=", value, "currentQty");
            return (Criteria) this;
        }

        public Criteria andCurrentQtyLessThan(BigDecimal value) {
            addCriterion("current_qty <", value, "currentQty");
            return (Criteria) this;
        }

        public Criteria andCurrentQtyLessThanOrEqualTo(BigDecimal value) {
            addCriterion("current_qty <=", value, "currentQty");
            return (Criteria) this;
        }

        public Criteria andCurrentQtyIn(List<BigDecimal> values) {
            addCriterion("current_qty in", values, "currentQty");
            return (Criteria) this;
        }

        public Criteria andCurrentQtyNotIn(List<BigDecimal> values) {
            addCriterion("current_qty not in", values, "currentQty");
            return (Criteria) this;
        }

        public Criteria andCurrentQtyBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("current_qty between", value1, value2, "currentQty");
            return (Criteria) this;
        }

        public Criteria andCurrentQtyNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("current_qty not between", value1, value2, "currentQty");
            return (Criteria) this;
        }

        public Criteria andAdjustQtyIsNull() {
            addCriterion("adjust_qty is null");
            return (Criteria) this;
        }

        public Criteria andAdjustQtyIsNotNull() {
            addCriterion("adjust_qty is not null");
            return (Criteria) this;
        }

        public Criteria andAdjustQtyEqualTo(BigDecimal value) {
            addCriterion("adjust_qty =", value, "adjustQty");
            return (Criteria) this;
        }

        public Criteria andAdjustQtyNotEqualTo(BigDecimal value) {
            addCriterion("adjust_qty <>", value, "adjustQty");
            return (Criteria) this;
        }

        public Criteria andAdjustQtyGreaterThan(BigDecimal value) {
            addCriterion("adjust_qty >", value, "adjustQty");
            return (Criteria) this;
        }

        public Criteria andAdjustQtyGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("adjust_qty >=", value, "adjustQty");
            return (Criteria) this;
        }

        public Criteria andAdjustQtyLessThan(BigDecimal value) {
            addCriterion("adjust_qty <", value, "adjustQty");
            return (Criteria) this;
        }

        public Criteria andAdjustQtyLessThanOrEqualTo(BigDecimal value) {
            addCriterion("adjust_qty <=", value, "adjustQty");
            return (Criteria) this;
        }

        public Criteria andAdjustQtyIn(List<BigDecimal> values) {
            addCriterion("adjust_qty in", values, "adjustQty");
            return (Criteria) this;
        }

        public Criteria andAdjustQtyNotIn(List<BigDecimal> values) {
            addCriterion("adjust_qty not in", values, "adjustQty");
            return (Criteria) this;
        }

        public Criteria andAdjustQtyBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("adjust_qty between", value1, value2, "adjustQty");
            return (Criteria) this;
        }

        public Criteria andAdjustQtyNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("adjust_qty not between", value1, value2, "adjustQty");
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