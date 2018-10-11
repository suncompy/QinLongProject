package com.shenhesoft.logistics.manage.pojo.financeAccount;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.shenhesoft.logistics.manage.pojo.trainStation.TbTrainStationExample.Criteria;

public class TbFinanceAccountExample implements Serializable{
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 8536636799048738563L;
	
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TbFinanceAccountExample() {
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

        public Criteria andAccountOpeningTimeIsNull() {
            addCriterion("account_opening_time is null");
            return (Criteria) this;
        }

        public Criteria andAccountOpeningTimeIsNotNull() {
            addCriterion("account_opening_time is not null");
            return (Criteria) this;
        }

        public Criteria andAccountOpeningTimeEqualTo(Date value) {
            addCriterion("account_opening_time =", value, "accountOpeningTime");
            return (Criteria) this;
        }

        public Criteria andAccountOpeningTimeNotEqualTo(Date value) {
            addCriterion("account_opening_time <>", value, "accountOpeningTime");
            return (Criteria) this;
        }

        public Criteria andAccountOpeningTimeGreaterThan(Date value) {
            addCriterion("account_opening_time >", value, "accountOpeningTime");
            return (Criteria) this;
        }

        public Criteria andAccountOpeningTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("account_opening_time >=", value, "accountOpeningTime");
            return (Criteria) this;
        }

        public Criteria andAccountOpeningTimeLessThan(Date value) {
            addCriterion("account_opening_time <", value, "accountOpeningTime");
            return (Criteria) this;
        }

        public Criteria andAccountOpeningTimeLessThanOrEqualTo(Date value) {
            addCriterion("account_opening_time <=", value, "accountOpeningTime");
            return (Criteria) this;
        }

        public Criteria andAccountOpeningTimeIn(List<Date> values) {
            addCriterion("account_opening_time in", values, "accountOpeningTime");
            return (Criteria) this;
        }

        public Criteria andAccountOpeningTimeNotIn(List<Date> values) {
            addCriterion("account_opening_time not in", values, "accountOpeningTime");
            return (Criteria) this;
        }

        public Criteria andAccountOpeningTimeBetween(Date value1, Date value2) {
            addCriterion("account_opening_time between", value1, value2, "accountOpeningTime");
            return (Criteria) this;
        }

        public Criteria andAccountOpeningTimeNotBetween(Date value1, Date value2) {
            addCriterion("account_opening_time not between", value1, value2, "accountOpeningTime");
            return (Criteria) this;
        }

        public Criteria andAccountKindIsNull() {
            addCriterion("account_kind is null");
            return (Criteria) this;
        }

        public Criteria andAccountKindIsNotNull() {
            addCriterion("account_kind is not null");
            return (Criteria) this;
        }

        public Criteria andAccountKindEqualTo(Byte value) {
            addCriterion("account_kind =", value, "accountKind");
            return (Criteria) this;
        }

        public Criteria andAccountKindNotEqualTo(Byte value) {
            addCriterion("account_kind <>", value, "accountKind");
            return (Criteria) this;
        }

        public Criteria andAccountKindGreaterThan(Byte value) {
            addCriterion("account_kind >", value, "accountKind");
            return (Criteria) this;
        }

        public Criteria andAccountKindGreaterThanOrEqualTo(Byte value) {
            addCriterion("account_kind >=", value, "accountKind");
            return (Criteria) this;
        }

        public Criteria andAccountKindLessThan(Byte value) {
            addCriterion("account_kind <", value, "accountKind");
            return (Criteria) this;
        }

        public Criteria andAccountKindLessThanOrEqualTo(Byte value) {
            addCriterion("account_kind <=", value, "accountKind");
            return (Criteria) this;
        }

        public Criteria andAccountKindIn(List<Byte> values) {
            addCriterion("account_kind in", values, "accountKind");
            return (Criteria) this;
        }

        public Criteria andAccountKindNotIn(List<Byte> values) {
            addCriterion("account_kind not in", values, "accountKind");
            return (Criteria) this;
        }

        public Criteria andAccountKindBetween(Byte value1, Byte value2) {
            addCriterion("account_kind between", value1, value2, "accountKind");
            return (Criteria) this;
        }

        public Criteria andAccountKindNotBetween(Byte value1, Byte value2) {
            addCriterion("account_kind not between", value1, value2, "accountKind");
            return (Criteria) this;
        }

        public Criteria andAccountTypeIsNull() {
            addCriterion("account_type is null");
            return (Criteria) this;
        }

        public Criteria andAccountTypeIsNotNull() {
            addCriterion("account_type is not null");
            return (Criteria) this;
        }

        public Criteria andAccountTypeEqualTo(Byte value) {
            addCriterion("account_type =", value, "accountType");
            return (Criteria) this;
        }

        public Criteria andAccountTypeNotEqualTo(Byte value) {
            addCriterion("account_type <>", value, "accountType");
            return (Criteria) this;
        }

        public Criteria andAccountTypeGreaterThan(Byte value) {
            addCriterion("account_type >", value, "accountType");
            return (Criteria) this;
        }

        public Criteria andAccountTypeGreaterThanOrEqualTo(Byte value) {
            addCriterion("account_type >=", value, "accountType");
            return (Criteria) this;
        }

        public Criteria andAccountTypeLessThan(Byte value) {
            addCriterion("account_type <", value, "accountType");
            return (Criteria) this;
        }

        public Criteria andAccountTypeLessThanOrEqualTo(Byte value) {
            addCriterion("account_type <=", value, "accountType");
            return (Criteria) this;
        }

        public Criteria andAccountTypeIn(List<Byte> values) {
            addCriterion("account_type in", values, "accountType");
            return (Criteria) this;
        }

        public Criteria andAccountTypeNotIn(List<Byte> values) {
            addCriterion("account_type not in", values, "accountType");
            return (Criteria) this;
        }

        public Criteria andAccountTypeBetween(Byte value1, Byte value2) {
            addCriterion("account_type between", value1, value2, "accountType");
            return (Criteria) this;
        }

        public Criteria andAccountTypeNotBetween(Byte value1, Byte value2) {
            addCriterion("account_type not between", value1, value2, "accountType");
            return (Criteria) this;
        }

        public Criteria andChooseAccountIdIsNull() {
            addCriterion("choose_account_id is null");
            return (Criteria) this;
        }

        public Criteria andChooseAccountIdIsNotNull() {
            addCriterion("choose_account_id is not null");
            return (Criteria) this;
        }

        public Criteria andChooseAccountIdEqualTo(Integer value) {
            addCriterion("choose_account_id =", value, "chooseAccountId");
            return (Criteria) this;
        }

        public Criteria andChooseAccountIdNotEqualTo(Integer value) {
            addCriterion("choose_account_id <>", value, "chooseAccountId");
            return (Criteria) this;
        }

        public Criteria andChooseAccountIdGreaterThan(Integer value) {
            addCriterion("choose_account_id >", value, "chooseAccountId");
            return (Criteria) this;
        }

        public Criteria andChooseAccountIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("choose_account_id >=", value, "chooseAccountId");
            return (Criteria) this;
        }

        public Criteria andChooseAccountIdLessThan(Integer value) {
            addCriterion("choose_account_id <", value, "chooseAccountId");
            return (Criteria) this;
        }

        public Criteria andChooseAccountIdLessThanOrEqualTo(Integer value) {
            addCriterion("choose_account_id <=", value, "chooseAccountId");
            return (Criteria) this;
        }

        public Criteria andChooseAccountIdIn(List<Integer> values) {
            addCriterion("choose_account_id in", values, "chooseAccountId");
            return (Criteria) this;
        }

        public Criteria andChooseAccountIdNotIn(List<Integer> values) {
            addCriterion("choose_account_id not in", values, "chooseAccountId");
            return (Criteria) this;
        }

        public Criteria andChooseAccountIdBetween(Integer value1, Integer value2) {
            addCriterion("choose_account_id between", value1, value2, "chooseAccountId");
            return (Criteria) this;
        }

        public Criteria andChooseAccountIdNotBetween(Integer value1, Integer value2) {
            addCriterion("choose_account_id not between", value1, value2, "chooseAccountId");
            return (Criteria) this;
        }

        public Criteria andStartAccountBalanceIsNull() {
            addCriterion("start_account_balance is null");
            return (Criteria) this;
        }

        public Criteria andStartAccountBalanceIsNotNull() {
            addCriterion("start_account_balance is not null");
            return (Criteria) this;
        }

        public Criteria andStartAccountBalanceEqualTo(BigDecimal value) {
            addCriterion("start_account_balance =", value, "startAccountBalance");
            return (Criteria) this;
        }

        public Criteria andStartAccountBalanceNotEqualTo(BigDecimal value) {
            addCriterion("start_account_balance <>", value, "startAccountBalance");
            return (Criteria) this;
        }

        public Criteria andStartAccountBalanceGreaterThan(BigDecimal value) {
            addCriterion("start_account_balance >", value, "startAccountBalance");
            return (Criteria) this;
        }

        public Criteria andStartAccountBalanceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("start_account_balance >=", value, "startAccountBalance");
            return (Criteria) this;
        }

        public Criteria andStartAccountBalanceLessThan(BigDecimal value) {
            addCriterion("start_account_balance <", value, "startAccountBalance");
            return (Criteria) this;
        }

        public Criteria andStartAccountBalanceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("start_account_balance <=", value, "startAccountBalance");
            return (Criteria) this;
        }

        public Criteria andStartAccountBalanceIn(List<BigDecimal> values) {
            addCriterion("start_account_balance in", values, "startAccountBalance");
            return (Criteria) this;
        }

        public Criteria andStartAccountBalanceNotIn(List<BigDecimal> values) {
            addCriterion("start_account_balance not in", values, "startAccountBalance");
            return (Criteria) this;
        }

        public Criteria andStartAccountBalanceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("start_account_balance between", value1, value2, "startAccountBalance");
            return (Criteria) this;
        }

        public Criteria andStartAccountBalanceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("start_account_balance not between", value1, value2, "startAccountBalance");
            return (Criteria) this;
        }

        public Criteria andAccountBalanceIsNull() {
            addCriterion("account_balance is null");
            return (Criteria) this;
        }

        public Criteria andAccountBalanceIsNotNull() {
            addCriterion("account_balance is not null");
            return (Criteria) this;
        }

        public Criteria andAccountBalanceEqualTo(BigDecimal value) {
            addCriterion("account_balance =", value, "accountBalance");
            return (Criteria) this;
        }

        public Criteria andAccountBalanceNotEqualTo(BigDecimal value) {
            addCriterion("account_balance <>", value, "accountBalance");
            return (Criteria) this;
        }

        public Criteria andAccountBalanceGreaterThan(BigDecimal value) {
            addCriterion("account_balance >", value, "accountBalance");
            return (Criteria) this;
        }

        public Criteria andAccountBalanceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("account_balance >=", value, "accountBalance");
            return (Criteria) this;
        }

        public Criteria andAccountBalanceLessThan(BigDecimal value) {
            addCriterion("account_balance <", value, "accountBalance");
            return (Criteria) this;
        }

        public Criteria andAccountBalanceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("account_balance <=", value, "accountBalance");
            return (Criteria) this;
        }

        public Criteria andAccountBalanceIn(List<BigDecimal> values) {
            addCriterion("account_balance in", values, "accountBalance");
            return (Criteria) this;
        }

        public Criteria andAccountBalanceNotIn(List<BigDecimal> values) {
            addCriterion("account_balance not in", values, "accountBalance");
            return (Criteria) this;
        }

        public Criteria andAccountBalanceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("account_balance between", value1, value2, "accountBalance");
            return (Criteria) this;
        }

        public Criteria andAccountBalanceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("account_balance not between", value1, value2, "accountBalance");
            return (Criteria) this;
        }

        public Criteria andVigilanceAmountIsNull() {
            addCriterion("vigilance_amount is null");
            return (Criteria) this;
        }

        public Criteria andVigilanceAmountIsNotNull() {
            addCriterion("vigilance_amount is not null");
            return (Criteria) this;
        }

        public Criteria andVigilanceAmountEqualTo(BigDecimal value) {
            addCriterion("vigilance_amount =", value, "vigilanceAmount");
            return (Criteria) this;
        }

        public Criteria andVigilanceAmountNotEqualTo(BigDecimal value) {
            addCriterion("vigilance_amount <>", value, "vigilanceAmount");
            return (Criteria) this;
        }

        public Criteria andVigilanceAmountGreaterThan(BigDecimal value) {
            addCriterion("vigilance_amount >", value, "vigilanceAmount");
            return (Criteria) this;
        }

        public Criteria andVigilanceAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("vigilance_amount >=", value, "vigilanceAmount");
            return (Criteria) this;
        }

        public Criteria andVigilanceAmountLessThan(BigDecimal value) {
            addCriterion("vigilance_amount <", value, "vigilanceAmount");
            return (Criteria) this;
        }

        public Criteria andVigilanceAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("vigilance_amount <=", value, "vigilanceAmount");
            return (Criteria) this;
        }

        public Criteria andVigilanceAmountIn(List<BigDecimal> values) {
            addCriterion("vigilance_amount in", values, "vigilanceAmount");
            return (Criteria) this;
        }

        public Criteria andVigilanceAmountNotIn(List<BigDecimal> values) {
            addCriterion("vigilance_amount not in", values, "vigilanceAmount");
            return (Criteria) this;
        }

        public Criteria andVigilanceAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("vigilance_amount between", value1, value2, "vigilanceAmount");
            return (Criteria) this;
        }

        public Criteria andVigilanceAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("vigilance_amount not between", value1, value2, "vigilanceAmount");
            return (Criteria) this;
        }

        public Criteria andNonUseAmountIsNull() {
            addCriterion("non_use_amount is null");
            return (Criteria) this;
        }

        public Criteria andNonUseAmountIsNotNull() {
            addCriterion("non_use_amount is not null");
            return (Criteria) this;
        }

        public Criteria andNonUseAmountEqualTo(BigDecimal value) {
            addCriterion("non_use_amount =", value, "nonUseAmount");
            return (Criteria) this;
        }

        public Criteria andNonUseAmountNotEqualTo(BigDecimal value) {
            addCriterion("non_use_amount <>", value, "nonUseAmount");
            return (Criteria) this;
        }

        public Criteria andNonUseAmountGreaterThan(BigDecimal value) {
            addCriterion("non_use_amount >", value, "nonUseAmount");
            return (Criteria) this;
        }

        public Criteria andNonUseAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("non_use_amount >=", value, "nonUseAmount");
            return (Criteria) this;
        }

        public Criteria andNonUseAmountLessThan(BigDecimal value) {
            addCriterion("non_use_amount <", value, "nonUseAmount");
            return (Criteria) this;
        }

        public Criteria andNonUseAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("non_use_amount <=", value, "nonUseAmount");
            return (Criteria) this;
        }

        public Criteria andNonUseAmountIn(List<BigDecimal> values) {
            addCriterion("non_use_amount in", values, "nonUseAmount");
            return (Criteria) this;
        }

        public Criteria andNonUseAmountNotIn(List<BigDecimal> values) {
            addCriterion("non_use_amount not in", values, "nonUseAmount");
            return (Criteria) this;
        }

        public Criteria andNonUseAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("non_use_amount between", value1, value2, "nonUseAmount");
            return (Criteria) this;
        }

        public Criteria andNonUseAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("non_use_amount not between", value1, value2, "nonUseAmount");
            return (Criteria) this;
        }

        public Criteria andAccountNameIsNull() {
            addCriterion("account_name is null");
            return (Criteria) this;
        }

        public Criteria andAccountNameIsNotNull() {
            addCriterion("account_name is not null");
            return (Criteria) this;
        }

        public Criteria andAccountNameEqualTo(String value) {
            addCriterion("account_name =", value, "accountName");
            return (Criteria) this;
        }

        public Criteria andAccountNameNotEqualTo(String value) {
            addCriterion("account_name <>", value, "accountName");
            return (Criteria) this;
        }

        public Criteria andAccountNameGreaterThan(String value) {
            addCriterion("account_name >", value, "accountName");
            return (Criteria) this;
        }

        public Criteria andAccountNameGreaterThanOrEqualTo(String value) {
            addCriterion("account_name >=", value, "accountName");
            return (Criteria) this;
        }

        public Criteria andAccountNameLessThan(String value) {
            addCriterion("account_name <", value, "accountName");
            return (Criteria) this;
        }

        public Criteria andAccountNameLessThanOrEqualTo(String value) {
            addCriterion("account_name <=", value, "accountName");
            return (Criteria) this;
        }

        public Criteria andAccountNameLike(String value) {
            addCriterion("account_name like", value, "accountName");
            return (Criteria) this;
        }

        public Criteria andAccountNameNotLike(String value) {
            addCriterion("account_name not like", value, "accountName");
            return (Criteria) this;
        }

        public Criteria andAccountNameIn(List<String> values) {
            addCriterion("account_name in", values, "accountName");
            return (Criteria) this;
        }

        public Criteria andAccountNameNotIn(List<String> values) {
            addCriterion("account_name not in", values, "accountName");
            return (Criteria) this;
        }

        public Criteria andAccountNameBetween(String value1, String value2) {
            addCriterion("account_name between", value1, value2, "accountName");
            return (Criteria) this;
        }

        public Criteria andAccountNameNotBetween(String value1, String value2) {
            addCriterion("account_name not between", value1, value2, "accountName");
            return (Criteria) this;
        }

        public Criteria andAccountNumIsNull() {
            addCriterion("account_num is null");
            return (Criteria) this;
        }

        public Criteria andAccountNumIsNotNull() {
            addCriterion("account_num is not null");
            return (Criteria) this;
        }

        public Criteria andAccountNumEqualTo(String value) {
            addCriterion("account_num =", value, "accountNum");
            return (Criteria) this;
        }

        public Criteria andAccountNumNotEqualTo(String value) {
            addCriterion("account_num <>", value, "accountNum");
            return (Criteria) this;
        }

        public Criteria andAccountNumGreaterThan(String value) {
            addCriterion("account_num >", value, "accountNum");
            return (Criteria) this;
        }

        public Criteria andAccountNumGreaterThanOrEqualTo(String value) {
            addCriterion("account_num >=", value, "accountNum");
            return (Criteria) this;
        }

        public Criteria andAccountNumLessThan(String value) {
            addCriterion("account_num <", value, "accountNum");
            return (Criteria) this;
        }

        public Criteria andAccountNumLessThanOrEqualTo(String value) {
            addCriterion("account_num <=", value, "accountNum");
            return (Criteria) this;
        }

        public Criteria andAccountNumLike(String value) {
            addCriterion("account_num like", value, "accountNum");
            return (Criteria) this;
        }

        public Criteria andAccountNumNotLike(String value) {
            addCriterion("account_num not like", value, "accountNum");
            return (Criteria) this;
        }

        public Criteria andAccountNumIn(List<String> values) {
            addCriterion("account_num in", values, "accountNum");
            return (Criteria) this;
        }

        public Criteria andAccountNumNotIn(List<String> values) {
            addCriterion("account_num not in", values, "accountNum");
            return (Criteria) this;
        }

        public Criteria andAccountNumBetween(String value1, String value2) {
            addCriterion("account_num between", value1, value2, "accountNum");
            return (Criteria) this;
        }

        public Criteria andAccountNumNotBetween(String value1, String value2) {
            addCriterion("account_num not between", value1, value2, "accountNum");
            return (Criteria) this;
        }

        public Criteria andOpenBankIsNull() {
            addCriterion("open_bank is null");
            return (Criteria) this;
        }

        public Criteria andOpenBankIsNotNull() {
            addCriterion("open_bank is not null");
            return (Criteria) this;
        }

        public Criteria andOpenBankEqualTo(String value) {
            addCriterion("open_bank =", value, "openBank");
            return (Criteria) this;
        }

        public Criteria andOpenBankNotEqualTo(String value) {
            addCriterion("open_bank <>", value, "openBank");
            return (Criteria) this;
        }

        public Criteria andOpenBankGreaterThan(String value) {
            addCriterion("open_bank >", value, "openBank");
            return (Criteria) this;
        }

        public Criteria andOpenBankGreaterThanOrEqualTo(String value) {
            addCriterion("open_bank >=", value, "openBank");
            return (Criteria) this;
        }

        public Criteria andOpenBankLessThan(String value) {
            addCriterion("open_bank <", value, "openBank");
            return (Criteria) this;
        }

        public Criteria andOpenBankLessThanOrEqualTo(String value) {
            addCriterion("open_bank <=", value, "openBank");
            return (Criteria) this;
        }

        public Criteria andOpenBankLike(String value) {
            addCriterion("open_bank like", "%"+value+"%", "openBank");
            return (Criteria) this;
        }

        public Criteria andOpenBankNotLike(String value) {
            addCriterion("open_bank not like", value, "openBank");
            return (Criteria) this;
        }

        public Criteria andOpenBankIn(List<String> values) {
            addCriterion("open_bank in", values, "openBank");
            return (Criteria) this;
        }

        public Criteria andOpenBankNotIn(List<String> values) {
            addCriterion("open_bank not in", values, "openBank");
            return (Criteria) this;
        }

        public Criteria andOpenBankBetween(String value1, String value2) {
            addCriterion("open_bank between", value1, value2, "openBank");
            return (Criteria) this;
        }

        public Criteria andOpenBankNotBetween(String value1, String value2) {
            addCriterion("open_bank not between", value1, value2, "openBank");
            return (Criteria) this;
        }

        public Criteria andBankNumIsNull() {
            addCriterion("bank_num is null");
            return (Criteria) this;
        }

        public Criteria andBankNumIsNotNull() {
            addCriterion("bank_num is not null");
            return (Criteria) this;
        }

        public Criteria andBankNumEqualTo(String value) {
            addCriterion("bank_num =", value, "bankNum");
            return (Criteria) this;
        }

        public Criteria andBankNumNotEqualTo(String value) {
            addCriterion("bank_num <>", value, "bankNum");
            return (Criteria) this;
        }

        public Criteria andBankNumGreaterThan(String value) {
            addCriterion("bank_num >", value, "bankNum");
            return (Criteria) this;
        }

        public Criteria andBankNumGreaterThanOrEqualTo(String value) {
            addCriterion("bank_num >=", value, "bankNum");
            return (Criteria) this;
        }

        public Criteria andBankNumLessThan(String value) {
            addCriterion("bank_num <", value, "bankNum");
            return (Criteria) this;
        }

        public Criteria andBankNumLessThanOrEqualTo(String value) {
            addCriterion("bank_num <=", value, "bankNum");
            return (Criteria) this;
        }

        public Criteria andBankNumLike(String value) {
            addCriterion("bank_num like", value, "bankNum");
            return (Criteria) this;
        }

        public Criteria andBankNumNotLike(String value) {
            addCriterion("bank_num not like", value, "bankNum");
            return (Criteria) this;
        }

        public Criteria andBankNumIn(List<String> values) {
            addCriterion("bank_num in", values, "bankNum");
            return (Criteria) this;
        }

        public Criteria andBankNumNotIn(List<String> values) {
            addCriterion("bank_num not in", values, "bankNum");
            return (Criteria) this;
        }

        public Criteria andBankNumBetween(String value1, String value2) {
            addCriterion("bank_num between", value1, value2, "bankNum");
            return (Criteria) this;
        }

        public Criteria andBankNumNotBetween(String value1, String value2) {
            addCriterion("bank_num not between", value1, value2, "bankNum");
            return (Criteria) this;
        }

        public Criteria andTaxIdentificationNumberIsNull() {
            addCriterion("tax_identification_number is null");
            return (Criteria) this;
        }

        public Criteria andTaxIdentificationNumberIsNotNull() {
            addCriterion("tax_identification_number is not null");
            return (Criteria) this;
        }

        public Criteria andTaxIdentificationNumberEqualTo(String value) {
            addCriterion("tax_identification_number =", value, "taxIdentificationNumber");
            return (Criteria) this;
        }

        public Criteria andTaxIdentificationNumberNotEqualTo(String value) {
            addCriterion("tax_identification_number <>", value, "taxIdentificationNumber");
            return (Criteria) this;
        }

        public Criteria andTaxIdentificationNumberGreaterThan(String value) {
            addCriterion("tax_identification_number >", value, "taxIdentificationNumber");
            return (Criteria) this;
        }

        public Criteria andTaxIdentificationNumberGreaterThanOrEqualTo(String value) {
            addCriterion("tax_identification_number >=", value, "taxIdentificationNumber");
            return (Criteria) this;
        }

        public Criteria andTaxIdentificationNumberLessThan(String value) {
            addCriterion("tax_identification_number <", value, "taxIdentificationNumber");
            return (Criteria) this;
        }

        public Criteria andTaxIdentificationNumberLessThanOrEqualTo(String value) {
            addCriterion("tax_identification_number <=", value, "taxIdentificationNumber");
            return (Criteria) this;
        }

        public Criteria andTaxIdentificationNumberLike(String value) {
            addCriterion("tax_identification_number like", value, "taxIdentificationNumber");
            return (Criteria) this;
        }

        public Criteria andTaxIdentificationNumberNotLike(String value) {
            addCriterion("tax_identification_number not like", value, "taxIdentificationNumber");
            return (Criteria) this;
        }

        public Criteria andTaxIdentificationNumberIn(List<String> values) {
            addCriterion("tax_identification_number in", values, "taxIdentificationNumber");
            return (Criteria) this;
        }

        public Criteria andTaxIdentificationNumberNotIn(List<String> values) {
            addCriterion("tax_identification_number not in", values, "taxIdentificationNumber");
            return (Criteria) this;
        }

        public Criteria andTaxIdentificationNumberBetween(String value1, String value2) {
            addCriterion("tax_identification_number between", value1, value2, "taxIdentificationNumber");
            return (Criteria) this;
        }

        public Criteria andTaxIdentificationNumberNotBetween(String value1, String value2) {
            addCriterion("tax_identification_number not between", value1, value2, "taxIdentificationNumber");
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