package com.shenhesoft.logistics.manage.pojo.trianType;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TbTrainTypeExample implements Serializable{
    /**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 5208898606202158167L;

	protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TbTrainTypeExample() {
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

        public Criteria andTrainKindIsNull() {
            addCriterion("train_kind is null");
            return (Criteria) this;
        }

        public Criteria andTrainKindIsNotNull() {
            addCriterion("train_kind is not null");
            return (Criteria) this;
        }

        public Criteria andTrainKindEqualTo(String value) {
            addCriterion("train_kind =", value, "trainKind");
            return (Criteria) this;
        }

        public Criteria andTrainKindNotEqualTo(String value) {
            addCriterion("train_kind <>", value, "trainKind");
            return (Criteria) this;
        }

        public Criteria andTrainKindGreaterThan(String value) {
            addCriterion("train_kind >", value, "trainKind");
            return (Criteria) this;
        }

        public Criteria andTrainKindGreaterThanOrEqualTo(String value) {
            addCriterion("train_kind >=", value, "trainKind");
            return (Criteria) this;
        }

        public Criteria andTrainKindLessThan(String value) {
            addCriterion("train_kind <", value, "trainKind");
            return (Criteria) this;
        }

        public Criteria andTrainKindLessThanOrEqualTo(String value) {
            addCriterion("train_kind <=", value, "trainKind");
            return (Criteria) this;
        }

        public Criteria andTrainKindLike(String value) {
            addCriterion("train_kind like", value, "trainKind");
            return (Criteria) this;
        }

        public Criteria andTrainKindNotLike(String value) {
            addCriterion("train_kind not like", value, "trainKind");
            return (Criteria) this;
        }

        public Criteria andTrainKindIn(List<String> values) {
            addCriterion("train_kind in", values, "trainKind");
            return (Criteria) this;
        }

        public Criteria andTrainKindNotIn(List<String> values) {
            addCriterion("train_kind not in", values, "trainKind");
            return (Criteria) this;
        }

        public Criteria andTrainKindBetween(String value1, String value2) {
            addCriterion("train_kind between", value1, value2, "trainKind");
            return (Criteria) this;
        }

        public Criteria andTrainKindNotBetween(String value1, String value2) {
            addCriterion("train_kind not between", value1, value2, "trainKind");
            return (Criteria) this;
        }

        public Criteria andTrainKindCodeIsNull() {
            addCriterion("train_kind_code is null");
            return (Criteria) this;
        }

        public Criteria andTrainKindCodeIsNotNull() {
            addCriterion("train_kind_code is not null");
            return (Criteria) this;
        }

        public Criteria andTrainKindCodeEqualTo(String value) {
            addCriterion("train_kind_code =", value, "trainKindCode");
            return (Criteria) this;
        }

        public Criteria andTrainKindCodeNotEqualTo(String value) {
            addCriterion("train_kind_code <>", value, "trainKindCode");
            return (Criteria) this;
        }

        public Criteria andTrainKindCodeGreaterThan(String value) {
            addCriterion("train_kind_code >", value, "trainKindCode");
            return (Criteria) this;
        }

        public Criteria andTrainKindCodeGreaterThanOrEqualTo(String value) {
            addCriterion("train_kind_code >=", value, "trainKindCode");
            return (Criteria) this;
        }

        public Criteria andTrainKindCodeLessThan(String value) {
            addCriterion("train_kind_code <", value, "trainKindCode");
            return (Criteria) this;
        }

        public Criteria andTrainKindCodeLessThanOrEqualTo(String value) {
            addCriterion("train_kind_code <=", value, "trainKindCode");
            return (Criteria) this;
        }

        public Criteria andTrainKindCodeLike(String value) {
            addCriterion("train_kind_code like", value, "trainKindCode");
            return (Criteria) this;
        }

        public Criteria andTrainKindCodeNotLike(String value) {
            addCriterion("train_kind_code not like", value, "trainKindCode");
            return (Criteria) this;
        }

        public Criteria andTrainKindCodeIn(List<String> values) {
            addCriterion("train_kind_code in", values, "trainKindCode");
            return (Criteria) this;
        }

        public Criteria andTrainKindCodeNotIn(List<String> values) {
            addCriterion("train_kind_code not in", values, "trainKindCode");
            return (Criteria) this;
        }

        public Criteria andTrainKindCodeBetween(String value1, String value2) {
            addCriterion("train_kind_code between", value1, value2, "trainKindCode");
            return (Criteria) this;
        }

        public Criteria andTrainKindCodeNotBetween(String value1, String value2) {
            addCriterion("train_kind_code not between", value1, value2, "trainKindCode");
            return (Criteria) this;
        }

        public Criteria andTrainTypeCodeIsNull() {
            addCriterion("train_type_code is null");
            return (Criteria) this;
        }

        public Criteria andTrainTypeCodeIsNotNull() {
            addCriterion("train_type_code is not null");
            return (Criteria) this;
        }

        public Criteria andTrainTypeCodeEqualTo(String value) {
            addCriterion("train_type_code =", value, "trainTypeCode");
            return (Criteria) this;
        }

        public Criteria andTrainTypeCodeNotEqualTo(String value) {
            addCriterion("train_type_code <>", value, "trainTypeCode");
            return (Criteria) this;
        }

        public Criteria andTrainTypeCodeGreaterThan(String value) {
            addCriterion("train_type_code >", value, "trainTypeCode");
            return (Criteria) this;
        }

        public Criteria andTrainTypeCodeGreaterThanOrEqualTo(String value) {
            addCriterion("train_type_code >=", value, "trainTypeCode");
            return (Criteria) this;
        }

        public Criteria andTrainTypeCodeLessThan(String value) {
            addCriterion("train_type_code <", value, "trainTypeCode");
            return (Criteria) this;
        }

        public Criteria andTrainTypeCodeLessThanOrEqualTo(String value) {
            addCriterion("train_type_code <=", value, "trainTypeCode");
            return (Criteria) this;
        }

        public Criteria andTrainTypeCodeLike(String value) {
            addCriterion("train_type_code like", value, "trainTypeCode");
            return (Criteria) this;
        }

        public Criteria andTrainTypeCodeNotLike(String value) {
            addCriterion("train_type_code not like", value, "trainTypeCode");
            return (Criteria) this;
        }

        public Criteria andTrainTypeCodeIn(List<String> values) {
            addCriterion("train_type_code in", values, "trainTypeCode");
            return (Criteria) this;
        }

        public Criteria andTrainTypeCodeNotIn(List<String> values) {
            addCriterion("train_type_code not in", values, "trainTypeCode");
            return (Criteria) this;
        }

        public Criteria andTrainTypeCodeBetween(String value1, String value2) {
            addCriterion("train_type_code between", value1, value2, "trainTypeCode");
            return (Criteria) this;
        }

        public Criteria andTrainTypeCodeNotBetween(String value1, String value2) {
            addCriterion("train_type_code not between", value1, value2, "trainTypeCode");
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

        public Criteria andSelfWeightEqualTo(Float value) {
            addCriterion("self_weight =", value, "selfWeight");
            return (Criteria) this;
        }

        public Criteria andSelfWeightNotEqualTo(Float value) {
            addCriterion("self_weight <>", value, "selfWeight");
            return (Criteria) this;
        }

        public Criteria andSelfWeightGreaterThan(Float value) {
            addCriterion("self_weight >", value, "selfWeight");
            return (Criteria) this;
        }

        public Criteria andSelfWeightGreaterThanOrEqualTo(Float value) {
            addCriterion("self_weight >=", value, "selfWeight");
            return (Criteria) this;
        }

        public Criteria andSelfWeightLessThan(Float value) {
            addCriterion("self_weight <", value, "selfWeight");
            return (Criteria) this;
        }

        public Criteria andSelfWeightLessThanOrEqualTo(Float value) {
            addCriterion("self_weight <=", value, "selfWeight");
            return (Criteria) this;
        }

        public Criteria andSelfWeightIn(List<Float> values) {
            addCriterion("self_weight in", values, "selfWeight");
            return (Criteria) this;
        }

        public Criteria andSelfWeightNotIn(List<Float> values) {
            addCriterion("self_weight not in", values, "selfWeight");
            return (Criteria) this;
        }

        public Criteria andSelfWeightBetween(Float value1, Float value2) {
            addCriterion("self_weight between", value1, value2, "selfWeight");
            return (Criteria) this;
        }

        public Criteria andSelfWeightNotBetween(Float value1, Float value2) {
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

        public Criteria andWeightEqualTo(Float value) {
            addCriterion("weight =", value, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightNotEqualTo(Float value) {
            addCriterion("weight <>", value, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightGreaterThan(Float value) {
            addCriterion("weight >", value, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightGreaterThanOrEqualTo(Float value) {
            addCriterion("weight >=", value, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightLessThan(Float value) {
            addCriterion("weight <", value, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightLessThanOrEqualTo(Float value) {
            addCriterion("weight <=", value, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightIn(List<Float> values) {
            addCriterion("weight in", values, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightNotIn(List<Float> values) {
            addCriterion("weight not in", values, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightBetween(Float value1, Float value2) {
            addCriterion("weight between", value1, value2, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightNotBetween(Float value1, Float value2) {
            addCriterion("weight not between", value1, value2, "weight");
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

        public Criteria andLoadPriceIsNull() {
            addCriterion("load_price is null");
            return (Criteria) this;
        }

        public Criteria andLoadPriceIsNotNull() {
            addCriterion("load_price is not null");
            return (Criteria) this;
        }

        public Criteria andLoadPriceEqualTo(Float value) {
            addCriterion("load_price =", value, "loadPrice");
            return (Criteria) this;
        }

        public Criteria andLoadPriceNotEqualTo(Float value) {
            addCriterion("load_price <>", value, "loadPrice");
            return (Criteria) this;
        }

        public Criteria andLoadPriceGreaterThan(Float value) {
            addCriterion("load_price >", value, "loadPrice");
            return (Criteria) this;
        }

        public Criteria andLoadPriceGreaterThanOrEqualTo(Float value) {
            addCriterion("load_price >=", value, "loadPrice");
            return (Criteria) this;
        }

        public Criteria andLoadPriceLessThan(Float value) {
            addCriterion("load_price <", value, "loadPrice");
            return (Criteria) this;
        }

        public Criteria andLoadPriceLessThanOrEqualTo(Float value) {
            addCriterion("load_price <=", value, "loadPrice");
            return (Criteria) this;
        }

        public Criteria andLoadPriceIn(List<Float> values) {
            addCriterion("load_price in", values, "loadPrice");
            return (Criteria) this;
        }

        public Criteria andLoadPriceNotIn(List<Float> values) {
            addCriterion("load_price not in", values, "loadPrice");
            return (Criteria) this;
        }

        public Criteria andLoadPriceBetween(Float value1, Float value2) {
            addCriterion("load_price between", value1, value2, "loadPrice");
            return (Criteria) this;
        }

        public Criteria andLoadPriceNotBetween(Float value1, Float value2) {
            addCriterion("load_price not between", value1, value2, "loadPrice");
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

        public Criteria andLengthEqualTo(String value) {
            addCriterion("length =", value, "length");
            return (Criteria) this;
        }

        public Criteria andLengthNotEqualTo(String value) {
            addCriterion("length <>", value, "length");
            return (Criteria) this;
        }

        public Criteria andLengthGreaterThan(String value) {
            addCriterion("length >", value, "length");
            return (Criteria) this;
        }

        public Criteria andLengthGreaterThanOrEqualTo(String value) {
            addCriterion("length >=", value, "length");
            return (Criteria) this;
        }

        public Criteria andLengthLessThan(String value) {
            addCriterion("length <", value, "length");
            return (Criteria) this;
        }

        public Criteria andLengthLessThanOrEqualTo(String value) {
            addCriterion("length <=", value, "length");
            return (Criteria) this;
        }

        public Criteria andLengthLike(String value) {
            addCriterion("length like", value, "length");
            return (Criteria) this;
        }

        public Criteria andLengthNotLike(String value) {
            addCriterion("length not like", value, "length");
            return (Criteria) this;
        }

        public Criteria andLengthIn(List<String> values) {
            addCriterion("length in", values, "length");
            return (Criteria) this;
        }

        public Criteria andLengthNotIn(List<String> values) {
            addCriterion("length not in", values, "length");
            return (Criteria) this;
        }

        public Criteria andLengthBetween(String value1, String value2) {
            addCriterion("length between", value1, value2, "length");
            return (Criteria) this;
        }

        public Criteria andLengthNotBetween(String value1, String value2) {
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

        public Criteria andWidthEqualTo(String value) {
            addCriterion("width =", value, "width");
            return (Criteria) this;
        }

        public Criteria andWidthNotEqualTo(String value) {
            addCriterion("width <>", value, "width");
            return (Criteria) this;
        }

        public Criteria andWidthGreaterThan(String value) {
            addCriterion("width >", value, "width");
            return (Criteria) this;
        }

        public Criteria andWidthGreaterThanOrEqualTo(String value) {
            addCriterion("width >=", value, "width");
            return (Criteria) this;
        }

        public Criteria andWidthLessThan(String value) {
            addCriterion("width <", value, "width");
            return (Criteria) this;
        }

        public Criteria andWidthLessThanOrEqualTo(String value) {
            addCriterion("width <=", value, "width");
            return (Criteria) this;
        }

        public Criteria andWidthLike(String value) {
            addCriterion("width like", value, "width");
            return (Criteria) this;
        }

        public Criteria andWidthNotLike(String value) {
            addCriterion("width not like", value, "width");
            return (Criteria) this;
        }

        public Criteria andWidthIn(List<String> values) {
            addCriterion("width in", values, "width");
            return (Criteria) this;
        }

        public Criteria andWidthNotIn(List<String> values) {
            addCriterion("width not in", values, "width");
            return (Criteria) this;
        }

        public Criteria andWidthBetween(String value1, String value2) {
            addCriterion("width between", value1, value2, "width");
            return (Criteria) this;
        }

        public Criteria andWidthNotBetween(String value1, String value2) {
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

        public Criteria andHightEqualTo(String value) {
            addCriterion("hight =", value, "hight");
            return (Criteria) this;
        }

        public Criteria andHightNotEqualTo(String value) {
            addCriterion("hight <>", value, "hight");
            return (Criteria) this;
        }

        public Criteria andHightGreaterThan(String value) {
            addCriterion("hight >", value, "hight");
            return (Criteria) this;
        }

        public Criteria andHightGreaterThanOrEqualTo(String value) {
            addCriterion("hight >=", value, "hight");
            return (Criteria) this;
        }

        public Criteria andHightLessThan(String value) {
            addCriterion("hight <", value, "hight");
            return (Criteria) this;
        }

        public Criteria andHightLessThanOrEqualTo(String value) {
            addCriterion("hight <=", value, "hight");
            return (Criteria) this;
        }

        public Criteria andHightLike(String value) {
            addCriterion("hight like", value, "hight");
            return (Criteria) this;
        }

        public Criteria andHightNotLike(String value) {
            addCriterion("hight not like", value, "hight");
            return (Criteria) this;
        }

        public Criteria andHightIn(List<String> values) {
            addCriterion("hight in", values, "hight");
            return (Criteria) this;
        }

        public Criteria andHightNotIn(List<String> values) {
            addCriterion("hight not in", values, "hight");
            return (Criteria) this;
        }

        public Criteria andHightBetween(String value1, String value2) {
            addCriterion("hight between", value1, value2, "hight");
            return (Criteria) this;
        }

        public Criteria andHightNotBetween(String value1, String value2) {
            addCriterion("hight not between", value1, value2, "hight");
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