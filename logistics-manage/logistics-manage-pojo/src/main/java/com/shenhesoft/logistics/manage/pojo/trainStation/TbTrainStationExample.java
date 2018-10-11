package com.shenhesoft.logistics.manage.pojo.trainStation;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.shenhesoft.logistics.manage.pojo.site.TbFreightYardExample.Criteria;

public class TbTrainStationExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TbTrainStationExample() {
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
            addCriterion("station_name like",  "%"+value+"%", "stationName");
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

        public Criteria andStationLevelIsNull() {
            addCriterion("station_level is null");
            return (Criteria) this;
        }

        public Criteria andStationLevelIsNotNull() {
            addCriterion("station_level is not null");
            return (Criteria) this;
        }

        public Criteria andStationLevelEqualTo(Byte value) {
            addCriterion("station_level =", value, "stationLevel");
            return (Criteria) this;
        }

        public Criteria andStationLevelNotEqualTo(Byte value) {
            addCriterion("station_level <>", value, "stationLevel");
            return (Criteria) this;
        }

        public Criteria andStationLevelGreaterThan(Byte value) {
            addCriterion("station_level >", value, "stationLevel");
            return (Criteria) this;
        }

        public Criteria andStationLevelGreaterThanOrEqualTo(Byte value) {
            addCriterion("station_level >=", value, "stationLevel");
            return (Criteria) this;
        }

        public Criteria andStationLevelLessThan(Byte value) {
            addCriterion("station_level <", value, "stationLevel");
            return (Criteria) this;
        }

        public Criteria andStationLevelLessThanOrEqualTo(Byte value) {
            addCriterion("station_level <=", value, "stationLevel");
            return (Criteria) this;
        }

        public Criteria andStationLevelIn(List<Byte> values) {
            addCriterion("station_level in", values, "stationLevel");
            return (Criteria) this;
        }

        public Criteria andStationLevelNotIn(List<Byte> values) {
            addCriterion("station_level not in", values, "stationLevel");
            return (Criteria) this;
        }

        public Criteria andStationLevelBetween(Byte value1, Byte value2) {
            addCriterion("station_level between", value1, value2, "stationLevel");
            return (Criteria) this;
        }

        public Criteria andStationLevelNotBetween(Byte value1, Byte value2) {
            addCriterion("station_level not between", value1, value2, "stationLevel");
            return (Criteria) this;
        }

        public Criteria andParentIdIsNull() {
            addCriterion("parent_id is null");
            return (Criteria) this;
        }

        public Criteria andParentIdIsNotNull() {
            addCriterion("parent_id is not null");
            return (Criteria) this;
        }

        public Criteria andParentIdEqualTo(Integer value) {
            addCriterion("parent_id =", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdNotEqualTo(Integer value) {
            addCriterion("parent_id <>", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdGreaterThan(Integer value) {
            addCriterion("parent_id >", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("parent_id >=", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdLessThan(Integer value) {
            addCriterion("parent_id <", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdLessThanOrEqualTo(Integer value) {
            addCriterion("parent_id <=", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdIn(List<Integer> values) {
            addCriterion("parent_id in", values, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdNotIn(List<Integer> values) {
            addCriterion("parent_id not in", values, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdBetween(Integer value1, Integer value2) {
            addCriterion("parent_id between", value1, value2, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdNotBetween(Integer value1, Integer value2) {
            addCriterion("parent_id not between", value1, value2, "parentId");
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
            addCriterion("responsibler like", "%"+value+"%", "responsibler");
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

        public Criteria andAdressCodeIsNull() {
            addCriterion("adress_code is null");
            return (Criteria) this;
        }

        public Criteria andAdressCodeIsNotNull() {
            addCriterion("adress_code is not null");
            return (Criteria) this;
        }

        public Criteria andAdressCodeEqualTo(String value) {
            addCriterion("adress_code =", value, "adressCode");
            return (Criteria) this;
        }

        public Criteria andAdressCodeNotEqualTo(String value) {
            addCriterion("adress_code <>", value, "adressCode");
            return (Criteria) this;
        }

        public Criteria andAdressCodeGreaterThan(String value) {
            addCriterion("adress_code >", value, "adressCode");
            return (Criteria) this;
        }

        public Criteria andAdressCodeGreaterThanOrEqualTo(String value) {
            addCriterion("adress_code >=", value, "adressCode");
            return (Criteria) this;
        }

        public Criteria andAdressCodeLessThan(String value) {
            addCriterion("adress_code <", value, "adressCode");
            return (Criteria) this;
        }

        public Criteria andAdressCodeLessThanOrEqualTo(String value) {
            addCriterion("adress_code <=", value, "adressCode");
            return (Criteria) this;
        }

        public Criteria andAdressCodeLike(String value) {
            addCriterion("adress_code like", value, "adressCode");
            return (Criteria) this;
        }

        public Criteria andAdressCodeNotLike(String value) {
            addCriterion("adress_code not like", value, "adressCode");
            return (Criteria) this;
        }

        public Criteria andAdressCodeIn(List<String> values) {
            addCriterion("adress_code in", values, "adressCode");
            return (Criteria) this;
        }

        public Criteria andAdressCodeNotIn(List<String> values) {
            addCriterion("adress_code not in", values, "adressCode");
            return (Criteria) this;
        }

        public Criteria andAdressCodeBetween(String value1, String value2) {
            addCriterion("adress_code between", value1, value2, "adressCode");
            return (Criteria) this;
        }

        public Criteria andAdressCodeNotBetween(String value1, String value2) {
            addCriterion("adress_code not between", value1, value2, "adressCode");
            return (Criteria) this;
        }

        public Criteria andDetailAddressIsNull() {
            addCriterion("detail_address is null");
            return (Criteria) this;
        }

        public Criteria andDetailAddressIsNotNull() {
            addCriterion("detail_address is not null");
            return (Criteria) this;
        }

        public Criteria andDetailAddressEqualTo(String value) {
            addCriterion("detail_address =", value, "detailAddress");
            return (Criteria) this;
        }

        public Criteria andDetailAddressNotEqualTo(String value) {
            addCriterion("detail_address <>", value, "detailAddress");
            return (Criteria) this;
        }

        public Criteria andDetailAddressGreaterThan(String value) {
            addCriterion("detail_address >", value, "detailAddress");
            return (Criteria) this;
        }

        public Criteria andDetailAddressGreaterThanOrEqualTo(String value) {
            addCriterion("detail_address >=", value, "detailAddress");
            return (Criteria) this;
        }

        public Criteria andDetailAddressLessThan(String value) {
            addCriterion("detail_address <", value, "detailAddress");
            return (Criteria) this;
        }

        public Criteria andDetailAddressLessThanOrEqualTo(String value) {
            addCriterion("detail_address <=", value, "detailAddress");
            return (Criteria) this;
        }

        public Criteria andDetailAddressLike(String value) {
            addCriterion("detail_address like", value, "detailAddress");
            return (Criteria) this;
        }

        public Criteria andDetailAddressNotLike(String value) {
            addCriterion("detail_address not like", value, "detailAddress");
            return (Criteria) this;
        }

        public Criteria andDetailAddressIn(List<String> values) {
            addCriterion("detail_address in", values, "detailAddress");
            return (Criteria) this;
        }

        public Criteria andDetailAddressNotIn(List<String> values) {
            addCriterion("detail_address not in", values, "detailAddress");
            return (Criteria) this;
        }

        public Criteria andDetailAddressBetween(String value1, String value2) {
            addCriterion("detail_address between", value1, value2, "detailAddress");
            return (Criteria) this;
        }

        public Criteria andDetailAddressNotBetween(String value1, String value2) {
            addCriterion("detail_address not between", value1, value2, "detailAddress");
            return (Criteria) this;
        }

        public Criteria andStationContactsIsNull() {
            addCriterion("station_contacts is null");
            return (Criteria) this;
        }

        public Criteria andStationContactsIsNotNull() {
            addCriterion("station_contacts is not null");
            return (Criteria) this;
        }

        public Criteria andStationContactsEqualTo(String value) {
            addCriterion("station_contacts =", value, "stationContacts");
            return (Criteria) this;
        }

        public Criteria andStationContactsNotEqualTo(String value) {
            addCriterion("station_contacts <>", value, "stationContacts");
            return (Criteria) this;
        }

        public Criteria andStationContactsGreaterThan(String value) {
            addCriterion("station_contacts >", value, "stationContacts");
            return (Criteria) this;
        }

        public Criteria andStationContactsGreaterThanOrEqualTo(String value) {
            addCriterion("station_contacts >=", value, "stationContacts");
            return (Criteria) this;
        }

        public Criteria andStationContactsLessThan(String value) {
            addCriterion("station_contacts <", value, "stationContacts");
            return (Criteria) this;
        }

        public Criteria andStationContactsLessThanOrEqualTo(String value) {
            addCriterion("station_contacts <=", value, "stationContacts");
            return (Criteria) this;
        }

        public Criteria andStationContactsLike(String value) {
            addCriterion("station_contacts like", value, "stationContacts");
            return (Criteria) this;
        }

        public Criteria andStationContactsNotLike(String value) {
            addCriterion("station_contacts not like", value, "stationContacts");
            return (Criteria) this;
        }

        public Criteria andStationContactsIn(List<String> values) {
            addCriterion("station_contacts in", values, "stationContacts");
            return (Criteria) this;
        }

        public Criteria andStationContactsNotIn(List<String> values) {
            addCriterion("station_contacts not in", values, "stationContacts");
            return (Criteria) this;
        }

        public Criteria andStationContactsBetween(String value1, String value2) {
            addCriterion("station_contacts between", value1, value2, "stationContacts");
            return (Criteria) this;
        }

        public Criteria andStationContactsNotBetween(String value1, String value2) {
            addCriterion("station_contacts not between", value1, value2, "stationContacts");
            return (Criteria) this;
        }

        public Criteria andDepartmentIsNull() {
            addCriterion("department is null");
            return (Criteria) this;
        }

        public Criteria andDepartmentIsNotNull() {
            addCriterion("department is not null");
            return (Criteria) this;
        }

        public Criteria andDepartmentEqualTo(String value) {
            addCriterion("department =", value, "department");
            return (Criteria) this;
        }

        public Criteria andDepartmentNotEqualTo(String value) {
            addCriterion("department <>", value, "department");
            return (Criteria) this;
        }

        public Criteria andDepartmentGreaterThan(String value) {
            addCriterion("department >", value, "department");
            return (Criteria) this;
        }

        public Criteria andDepartmentGreaterThanOrEqualTo(String value) {
            addCriterion("department >=", value, "department");
            return (Criteria) this;
        }

        public Criteria andDepartmentLessThan(String value) {
            addCriterion("department <", value, "department");
            return (Criteria) this;
        }

        public Criteria andDepartmentLessThanOrEqualTo(String value) {
            addCriterion("department <=", value, "department");
            return (Criteria) this;
        }

        public Criteria andDepartmentLike(String value) {
            addCriterion("department like", value, "department");
            return (Criteria) this;
        }

        public Criteria andDepartmentNotLike(String value) {
            addCriterion("department not like", value, "department");
            return (Criteria) this;
        }

        public Criteria andDepartmentIn(List<String> values) {
            addCriterion("department in", values, "department");
            return (Criteria) this;
        }

        public Criteria andDepartmentNotIn(List<String> values) {
            addCriterion("department not in", values, "department");
            return (Criteria) this;
        }

        public Criteria andDepartmentBetween(String value1, String value2) {
            addCriterion("department between", value1, value2, "department");
            return (Criteria) this;
        }

        public Criteria andDepartmentNotBetween(String value1, String value2) {
            addCriterion("department not between", value1, value2, "department");
            return (Criteria) this;
        }

        public Criteria andStationPhoneIsNull() {
            addCriterion("station_phone is null");
            return (Criteria) this;
        }

        public Criteria andStationPhoneIsNotNull() {
            addCriterion("station_phone is not null");
            return (Criteria) this;
        }

        public Criteria andStationPhoneEqualTo(String value) {
            addCriterion("station_phone =", value, "stationPhone");
            return (Criteria) this;
        }

        public Criteria andStationPhoneNotEqualTo(String value) {
            addCriterion("station_phone <>", value, "stationPhone");
            return (Criteria) this;
        }

        public Criteria andStationPhoneGreaterThan(String value) {
            addCriterion("station_phone >", value, "stationPhone");
            return (Criteria) this;
        }

        public Criteria andStationPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("station_phone >=", value, "stationPhone");
            return (Criteria) this;
        }

        public Criteria andStationPhoneLessThan(String value) {
            addCriterion("station_phone <", value, "stationPhone");
            return (Criteria) this;
        }

        public Criteria andStationPhoneLessThanOrEqualTo(String value) {
            addCriterion("station_phone <=", value, "stationPhone");
            return (Criteria) this;
        }

        public Criteria andStationPhoneLike(String value) {
            addCriterion("station_phone like",  "%"+value+"%", "stationPhone");
            return (Criteria) this;
        }

        public Criteria andStationPhoneNotLike(String value) {
            addCriterion("station_phone not like", value, "stationPhone");
            return (Criteria) this;
        }

        public Criteria andStationPhoneIn(List<String> values) {
            addCriterion("station_phone in", values, "stationPhone");
            return (Criteria) this;
        }

        public Criteria andStationPhoneNotIn(List<String> values) {
            addCriterion("station_phone not in", values, "stationPhone");
            return (Criteria) this;
        }

        public Criteria andStationPhoneBetween(String value1, String value2) {
            addCriterion("station_phone between", value1, value2, "stationPhone");
            return (Criteria) this;
        }

        public Criteria andStationPhoneNotBetween(String value1, String value2) {
            addCriterion("station_phone not between", value1, value2, "stationPhone");
            return (Criteria) this;
        }

        public Criteria andStationFaxIsNull() {
            addCriterion("station_fax is null");
            return (Criteria) this;
        }

        public Criteria andStationFaxIsNotNull() {
            addCriterion("station_fax is not null");
            return (Criteria) this;
        }

        public Criteria andStationFaxEqualTo(String value) {
            addCriterion("station_fax =", value, "stationFax");
            return (Criteria) this;
        }

        public Criteria andStationFaxNotEqualTo(String value) {
            addCriterion("station_fax <>", value, "stationFax");
            return (Criteria) this;
        }

        public Criteria andStationFaxGreaterThan(String value) {
            addCriterion("station_fax >", value, "stationFax");
            return (Criteria) this;
        }

        public Criteria andStationFaxGreaterThanOrEqualTo(String value) {
            addCriterion("station_fax >=", value, "stationFax");
            return (Criteria) this;
        }

        public Criteria andStationFaxLessThan(String value) {
            addCriterion("station_fax <", value, "stationFax");
            return (Criteria) this;
        }

        public Criteria andStationFaxLessThanOrEqualTo(String value) {
            addCriterion("station_fax <=", value, "stationFax");
            return (Criteria) this;
        }

        public Criteria andStationFaxLike(String value) {
            addCriterion("station_fax like", value, "stationFax");
            return (Criteria) this;
        }

        public Criteria andStationFaxNotLike(String value) {
            addCriterion("station_fax not like", value, "stationFax");
            return (Criteria) this;
        }

        public Criteria andStationFaxIn(List<String> values) {
            addCriterion("station_fax in", values, "stationFax");
            return (Criteria) this;
        }

        public Criteria andStationFaxNotIn(List<String> values) {
            addCriterion("station_fax not in", values, "stationFax");
            return (Criteria) this;
        }

        public Criteria andStationFaxBetween(String value1, String value2) {
            addCriterion("station_fax between", value1, value2, "stationFax");
            return (Criteria) this;
        }

        public Criteria andStationFaxNotBetween(String value1, String value2) {
            addCriterion("station_fax not between", value1, value2, "stationFax");
            return (Criteria) this;
        }

        public Criteria andEmailIsNull() {
            addCriterion("email is null");
            return (Criteria) this;
        }

        public Criteria andEmailIsNotNull() {
            addCriterion("email is not null");
            return (Criteria) this;
        }

        public Criteria andEmailEqualTo(String value) {
            addCriterion("email =", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotEqualTo(String value) {
            addCriterion("email <>", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailGreaterThan(String value) {
            addCriterion("email >", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailGreaterThanOrEqualTo(String value) {
            addCriterion("email >=", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLessThan(String value) {
            addCriterion("email <", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLessThanOrEqualTo(String value) {
            addCriterion("email <=", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLike(String value) {
            addCriterion("email like", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotLike(String value) {
            addCriterion("email not like", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailIn(List<String> values) {
            addCriterion("email in", values, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotIn(List<String> values) {
            addCriterion("email not in", values, "email");
            return (Criteria) this;
        }

        public Criteria andEmailBetween(String value1, String value2) {
            addCriterion("email between", value1, value2, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotBetween(String value1, String value2) {
            addCriterion("email not between", value1, value2, "email");
            return (Criteria) this;
        }

        public Criteria andBankAccountIsNull() {
            addCriterion("bank_account is null");
            return (Criteria) this;
        }

        public Criteria andBankAccountIsNotNull() {
            addCriterion("bank_account is not null");
            return (Criteria) this;
        }

        public Criteria andBankAccountEqualTo(String value) {
            addCriterion("bank_account =", value, "bankAccount");
            return (Criteria) this;
        }

        public Criteria andBankAccountNotEqualTo(String value) {
            addCriterion("bank_account <>", value, "bankAccount");
            return (Criteria) this;
        }

        public Criteria andBankAccountGreaterThan(String value) {
            addCriterion("bank_account >", value, "bankAccount");
            return (Criteria) this;
        }

        public Criteria andBankAccountGreaterThanOrEqualTo(String value) {
            addCriterion("bank_account >=", value, "bankAccount");
            return (Criteria) this;
        }

        public Criteria andBankAccountLessThan(String value) {
            addCriterion("bank_account <", value, "bankAccount");
            return (Criteria) this;
        }

        public Criteria andBankAccountLessThanOrEqualTo(String value) {
            addCriterion("bank_account <=", value, "bankAccount");
            return (Criteria) this;
        }

        public Criteria andBankAccountLike(String value) {
            addCriterion("bank_account like", value, "bankAccount");
            return (Criteria) this;
        }

        public Criteria andBankAccountNotLike(String value) {
            addCriterion("bank_account not like", value, "bankAccount");
            return (Criteria) this;
        }

        public Criteria andBankAccountIn(List<String> values) {
            addCriterion("bank_account in", values, "bankAccount");
            return (Criteria) this;
        }

        public Criteria andBankAccountNotIn(List<String> values) {
            addCriterion("bank_account not in", values, "bankAccount");
            return (Criteria) this;
        }

        public Criteria andBankAccountBetween(String value1, String value2) {
            addCriterion("bank_account between", value1, value2, "bankAccount");
            return (Criteria) this;
        }

        public Criteria andBankAccountNotBetween(String value1, String value2) {
            addCriterion("bank_account not between", value1, value2, "bankAccount");
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
            addCriterion("open_bank like", value, "openBank");
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

        public Criteria andOpenBankNumIsNull() {
            addCriterion("open_bank_num is null");
            return (Criteria) this;
        }

        public Criteria andOpenBankNumIsNotNull() {
            addCriterion("open_bank_num is not null");
            return (Criteria) this;
        }

        public Criteria andOpenBankNumEqualTo(String value) {
            addCriterion("open_bank_num =", value, "openBankNum");
            return (Criteria) this;
        }

        public Criteria andOpenBankNumNotEqualTo(String value) {
            addCriterion("open_bank_num <>", value, "openBankNum");
            return (Criteria) this;
        }

        public Criteria andOpenBankNumGreaterThan(String value) {
            addCriterion("open_bank_num >", value, "openBankNum");
            return (Criteria) this;
        }

        public Criteria andOpenBankNumGreaterThanOrEqualTo(String value) {
            addCriterion("open_bank_num >=", value, "openBankNum");
            return (Criteria) this;
        }

        public Criteria andOpenBankNumLessThan(String value) {
            addCriterion("open_bank_num <", value, "openBankNum");
            return (Criteria) this;
        }

        public Criteria andOpenBankNumLessThanOrEqualTo(String value) {
            addCriterion("open_bank_num <=", value, "openBankNum");
            return (Criteria) this;
        }

        public Criteria andOpenBankNumLike(String value) {
            addCriterion("open_bank_num like", value, "openBankNum");
            return (Criteria) this;
        }

        public Criteria andOpenBankNumNotLike(String value) {
            addCriterion("open_bank_num not like", value, "openBankNum");
            return (Criteria) this;
        }

        public Criteria andOpenBankNumIn(List<String> values) {
            addCriterion("open_bank_num in", values, "openBankNum");
            return (Criteria) this;
        }

        public Criteria andOpenBankNumNotIn(List<String> values) {
            addCriterion("open_bank_num not in", values, "openBankNum");
            return (Criteria) this;
        }

        public Criteria andOpenBankNumBetween(String value1, String value2) {
            addCriterion("open_bank_num between", value1, value2, "openBankNum");
            return (Criteria) this;
        }

        public Criteria andOpenBankNumNotBetween(String value1, String value2) {
            addCriterion("open_bank_num not between", value1, value2, "openBankNum");
            return (Criteria) this;
        }

        public Criteria andDutyParagraphIsNull() {
            addCriterion("duty_paragraph is null");
            return (Criteria) this;
        }

        public Criteria andDutyParagraphIsNotNull() {
            addCriterion("duty_paragraph is not null");
            return (Criteria) this;
        }

        public Criteria andDutyParagraphEqualTo(String value) {
            addCriterion("duty_paragraph =", value, "dutyParagraph");
            return (Criteria) this;
        }

        public Criteria andDutyParagraphNotEqualTo(String value) {
            addCriterion("duty_paragraph <>", value, "dutyParagraph");
            return (Criteria) this;
        }

        public Criteria andDutyParagraphGreaterThan(String value) {
            addCriterion("duty_paragraph >", value, "dutyParagraph");
            return (Criteria) this;
        }

        public Criteria andDutyParagraphGreaterThanOrEqualTo(String value) {
            addCriterion("duty_paragraph >=", value, "dutyParagraph");
            return (Criteria) this;
        }

        public Criteria andDutyParagraphLessThan(String value) {
            addCriterion("duty_paragraph <", value, "dutyParagraph");
            return (Criteria) this;
        }

        public Criteria andDutyParagraphLessThanOrEqualTo(String value) {
            addCriterion("duty_paragraph <=", value, "dutyParagraph");
            return (Criteria) this;
        }

        public Criteria andDutyParagraphLike(String value) {
            addCriterion("duty_paragraph like", value, "dutyParagraph");
            return (Criteria) this;
        }

        public Criteria andDutyParagraphNotLike(String value) {
            addCriterion("duty_paragraph not like", value, "dutyParagraph");
            return (Criteria) this;
        }

        public Criteria andDutyParagraphIn(List<String> values) {
            addCriterion("duty_paragraph in", values, "dutyParagraph");
            return (Criteria) this;
        }

        public Criteria andDutyParagraphNotIn(List<String> values) {
            addCriterion("duty_paragraph not in", values, "dutyParagraph");
            return (Criteria) this;
        }

        public Criteria andDutyParagraphBetween(String value1, String value2) {
            addCriterion("duty_paragraph between", value1, value2, "dutyParagraph");
            return (Criteria) this;
        }

        public Criteria andDutyParagraphNotBetween(String value1, String value2) {
            addCriterion("duty_paragraph not between", value1, value2, "dutyParagraph");
            return (Criteria) this;
        }

        public Criteria andBankLastAmountIsNull() {
            addCriterion("bank_last_amount is null");
            return (Criteria) this;
        }

        public Criteria andBankLastAmountIsNotNull() {
            addCriterion("bank_last_amount is not null");
            return (Criteria) this;
        }

        public Criteria andBankLastAmountEqualTo(BigDecimal value) {
            addCriterion("bank_last_amount =", value, "bankLastAmount");
            return (Criteria) this;
        }

        public Criteria andBankLastAmountNotEqualTo(BigDecimal value) {
            addCriterion("bank_last_amount <>", value, "bankLastAmount");
            return (Criteria) this;
        }

        public Criteria andBankLastAmountGreaterThan(BigDecimal value) {
            addCriterion("bank_last_amount >", value, "bankLastAmount");
            return (Criteria) this;
        }

        public Criteria andBankLastAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("bank_last_amount >=", value, "bankLastAmount");
            return (Criteria) this;
        }

        public Criteria andBankLastAmountLessThan(BigDecimal value) {
            addCriterion("bank_last_amount <", value, "bankLastAmount");
            return (Criteria) this;
        }

        public Criteria andBankLastAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("bank_last_amount <=", value, "bankLastAmount");
            return (Criteria) this;
        }

        public Criteria andBankLastAmountIn(List<BigDecimal> values) {
            addCriterion("bank_last_amount in", values, "bankLastAmount");
            return (Criteria) this;
        }

        public Criteria andBankLastAmountNotIn(List<BigDecimal> values) {
            addCriterion("bank_last_amount not in", values, "bankLastAmount");
            return (Criteria) this;
        }

        public Criteria andBankLastAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("bank_last_amount between", value1, value2, "bankLastAmount");
            return (Criteria) this;
        }

        public Criteria andBankLastAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("bank_last_amount not between", value1, value2, "bankLastAmount");
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