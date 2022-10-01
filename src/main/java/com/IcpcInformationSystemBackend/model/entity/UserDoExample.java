package com.IcpcInformationSystemBackend.model.entity;

import java.util.ArrayList;
import java.util.List;

public class UserDoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public UserDoExample() {
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

        public Criteria andUserEmailIsNull() {
            addCriterion("user_email is null");
            return (Criteria) this;
        }

        public Criteria andUserEmailIsNotNull() {
            addCriterion("user_email is not null");
            return (Criteria) this;
        }

        public Criteria andUserEmailEqualTo(String value) {
            addCriterion("user_email =", value, "userEmail");
            return (Criteria) this;
        }

        public Criteria andUserEmailNotEqualTo(String value) {
            addCriterion("user_email <>", value, "userEmail");
            return (Criteria) this;
        }

        public Criteria andUserEmailGreaterThan(String value) {
            addCriterion("user_email >", value, "userEmail");
            return (Criteria) this;
        }

        public Criteria andUserEmailGreaterThanOrEqualTo(String value) {
            addCriterion("user_email >=", value, "userEmail");
            return (Criteria) this;
        }

        public Criteria andUserEmailLessThan(String value) {
            addCriterion("user_email <", value, "userEmail");
            return (Criteria) this;
        }

        public Criteria andUserEmailLessThanOrEqualTo(String value) {
            addCriterion("user_email <=", value, "userEmail");
            return (Criteria) this;
        }

        public Criteria andUserEmailLike(String value) {
            addCriterion("user_email like", value, "userEmail");
            return (Criteria) this;
        }

        public Criteria andUserEmailNotLike(String value) {
            addCriterion("user_email not like", value, "userEmail");
            return (Criteria) this;
        }

        public Criteria andUserEmailIn(List<String> values) {
            addCriterion("user_email in", values, "userEmail");
            return (Criteria) this;
        }

        public Criteria andUserEmailNotIn(List<String> values) {
            addCriterion("user_email not in", values, "userEmail");
            return (Criteria) this;
        }

        public Criteria andUserEmailBetween(String value1, String value2) {
            addCriterion("user_email between", value1, value2, "userEmail");
            return (Criteria) this;
        }

        public Criteria andUserEmailNotBetween(String value1, String value2) {
            addCriterion("user_email not between", value1, value2, "userEmail");
            return (Criteria) this;
        }

        public Criteria andIdentityIsNull() {
            addCriterion("identity is null");
            return (Criteria) this;
        }

        public Criteria andIdentityIsNotNull() {
            addCriterion("identity is not null");
            return (Criteria) this;
        }

        public Criteria andIdentityEqualTo(Integer value) {
            addCriterion("identity =", value, "identity");
            return (Criteria) this;
        }

        public Criteria andIdentityNotEqualTo(Integer value) {
            addCriterion("identity <>", value, "identity");
            return (Criteria) this;
        }

        public Criteria andIdentityGreaterThan(Integer value) {
            addCriterion("identity >", value, "identity");
            return (Criteria) this;
        }

        public Criteria andIdentityGreaterThanOrEqualTo(Integer value) {
            addCriterion("identity >=", value, "identity");
            return (Criteria) this;
        }

        public Criteria andIdentityLessThan(Integer value) {
            addCriterion("identity <", value, "identity");
            return (Criteria) this;
        }

        public Criteria andIdentityLessThanOrEqualTo(Integer value) {
            addCriterion("identity <=", value, "identity");
            return (Criteria) this;
        }

        public Criteria andIdentityIn(List<Integer> values) {
            addCriterion("identity in", values, "identity");
            return (Criteria) this;
        }

        public Criteria andIdentityNotIn(List<Integer> values) {
            addCriterion("identity not in", values, "identity");
            return (Criteria) this;
        }

        public Criteria andIdentityBetween(Integer value1, Integer value2) {
            addCriterion("identity between", value1, value2, "identity");
            return (Criteria) this;
        }

        public Criteria andIdentityNotBetween(Integer value1, Integer value2) {
            addCriterion("identity not between", value1, value2, "identity");
            return (Criteria) this;
        }

        public Criteria andChiNameIsNull() {
            addCriterion("chi_name is null");
            return (Criteria) this;
        }

        public Criteria andChiNameIsNotNull() {
            addCriterion("chi_name is not null");
            return (Criteria) this;
        }

        public Criteria andChiNameEqualTo(String value) {
            addCriterion("chi_name =", value, "chiName");
            return (Criteria) this;
        }

        public Criteria andChiNameNotEqualTo(String value) {
            addCriterion("chi_name <>", value, "chiName");
            return (Criteria) this;
        }

        public Criteria andChiNameGreaterThan(String value) {
            addCriterion("chi_name >", value, "chiName");
            return (Criteria) this;
        }

        public Criteria andChiNameGreaterThanOrEqualTo(String value) {
            addCriterion("chi_name >=", value, "chiName");
            return (Criteria) this;
        }

        public Criteria andChiNameLessThan(String value) {
            addCriterion("chi_name <", value, "chiName");
            return (Criteria) this;
        }

        public Criteria andChiNameLessThanOrEqualTo(String value) {
            addCriterion("chi_name <=", value, "chiName");
            return (Criteria) this;
        }

        public Criteria andChiNameLike(String value) {
            addCriterion("chi_name like", value, "chiName");
            return (Criteria) this;
        }

        public Criteria andChiNameNotLike(String value) {
            addCriterion("chi_name not like", value, "chiName");
            return (Criteria) this;
        }

        public Criteria andChiNameIn(List<String> values) {
            addCriterion("chi_name in", values, "chiName");
            return (Criteria) this;
        }

        public Criteria andChiNameNotIn(List<String> values) {
            addCriterion("chi_name not in", values, "chiName");
            return (Criteria) this;
        }

        public Criteria andChiNameBetween(String value1, String value2) {
            addCriterion("chi_name between", value1, value2, "chiName");
            return (Criteria) this;
        }

        public Criteria andChiNameNotBetween(String value1, String value2) {
            addCriterion("chi_name not between", value1, value2, "chiName");
            return (Criteria) this;
        }

        public Criteria andEngNameIsNull() {
            addCriterion("eng_name is null");
            return (Criteria) this;
        }

        public Criteria andEngNameIsNotNull() {
            addCriterion("eng_name is not null");
            return (Criteria) this;
        }

        public Criteria andEngNameEqualTo(String value) {
            addCriterion("eng_name =", value, "engName");
            return (Criteria) this;
        }

        public Criteria andEngNameNotEqualTo(String value) {
            addCriterion("eng_name <>", value, "engName");
            return (Criteria) this;
        }

        public Criteria andEngNameGreaterThan(String value) {
            addCriterion("eng_name >", value, "engName");
            return (Criteria) this;
        }

        public Criteria andEngNameGreaterThanOrEqualTo(String value) {
            addCriterion("eng_name >=", value, "engName");
            return (Criteria) this;
        }

        public Criteria andEngNameLessThan(String value) {
            addCriterion("eng_name <", value, "engName");
            return (Criteria) this;
        }

        public Criteria andEngNameLessThanOrEqualTo(String value) {
            addCriterion("eng_name <=", value, "engName");
            return (Criteria) this;
        }

        public Criteria andEngNameLike(String value) {
            addCriterion("eng_name like", value, "engName");
            return (Criteria) this;
        }

        public Criteria andEngNameNotLike(String value) {
            addCriterion("eng_name not like", value, "engName");
            return (Criteria) this;
        }

        public Criteria andEngNameIn(List<String> values) {
            addCriterion("eng_name in", values, "engName");
            return (Criteria) this;
        }

        public Criteria andEngNameNotIn(List<String> values) {
            addCriterion("eng_name not in", values, "engName");
            return (Criteria) this;
        }

        public Criteria andEngNameBetween(String value1, String value2) {
            addCriterion("eng_name between", value1, value2, "engName");
            return (Criteria) this;
        }

        public Criteria andEngNameNotBetween(String value1, String value2) {
            addCriterion("eng_name not between", value1, value2, "engName");
            return (Criteria) this;
        }

        public Criteria andTelephoneIsNull() {
            addCriterion("telephone is null");
            return (Criteria) this;
        }

        public Criteria andTelephoneIsNotNull() {
            addCriterion("telephone is not null");
            return (Criteria) this;
        }

        public Criteria andTelephoneEqualTo(String value) {
            addCriterion("telephone =", value, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneNotEqualTo(String value) {
            addCriterion("telephone <>", value, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneGreaterThan(String value) {
            addCriterion("telephone >", value, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneGreaterThanOrEqualTo(String value) {
            addCriterion("telephone >=", value, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneLessThan(String value) {
            addCriterion("telephone <", value, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneLessThanOrEqualTo(String value) {
            addCriterion("telephone <=", value, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneLike(String value) {
            addCriterion("telephone like", value, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneNotLike(String value) {
            addCriterion("telephone not like", value, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneIn(List<String> values) {
            addCriterion("telephone in", values, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneNotIn(List<String> values) {
            addCriterion("telephone not in", values, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneBetween(String value1, String value2) {
            addCriterion("telephone between", value1, value2, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneNotBetween(String value1, String value2) {
            addCriterion("telephone not between", value1, value2, "telephone");
            return (Criteria) this;
        }

        public Criteria andStateIsNull() {
            addCriterion("state is null");
            return (Criteria) this;
        }

        public Criteria andStateIsNotNull() {
            addCriterion("state is not null");
            return (Criteria) this;
        }

        public Criteria andStateEqualTo(Integer value) {
            addCriterion("state =", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotEqualTo(Integer value) {
            addCriterion("state <>", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateGreaterThan(Integer value) {
            addCriterion("state >", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateGreaterThanOrEqualTo(Integer value) {
            addCriterion("state >=", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLessThan(Integer value) {
            addCriterion("state <", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLessThanOrEqualTo(Integer value) {
            addCriterion("state <=", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateIn(List<Integer> values) {
            addCriterion("state in", values, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotIn(List<Integer> values) {
            addCriterion("state not in", values, "state");
            return (Criteria) this;
        }

        public Criteria andStateBetween(Integer value1, Integer value2) {
            addCriterion("state between", value1, value2, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotBetween(Integer value1, Integer value2) {
            addCriterion("state not between", value1, value2, "state");
            return (Criteria) this;
        }

        public Criteria andAdmissionDateIsNull() {
            addCriterion("admission_date is null");
            return (Criteria) this;
        }

        public Criteria andAdmissionDateIsNotNull() {
            addCriterion("admission_date is not null");
            return (Criteria) this;
        }

        public Criteria andAdmissionDateEqualTo(String value) {
            addCriterion("admission_date =", value, "admissionDate");
            return (Criteria) this;
        }

        public Criteria andAdmissionDateNotEqualTo(String value) {
            addCriterion("admission_date <>", value, "admissionDate");
            return (Criteria) this;
        }

        public Criteria andAdmissionDateGreaterThan(String value) {
            addCriterion("admission_date >", value, "admissionDate");
            return (Criteria) this;
        }

        public Criteria andAdmissionDateGreaterThanOrEqualTo(String value) {
            addCriterion("admission_date >=", value, "admissionDate");
            return (Criteria) this;
        }

        public Criteria andAdmissionDateLessThan(String value) {
            addCriterion("admission_date <", value, "admissionDate");
            return (Criteria) this;
        }

        public Criteria andAdmissionDateLessThanOrEqualTo(String value) {
            addCriterion("admission_date <=", value, "admissionDate");
            return (Criteria) this;
        }

        public Criteria andAdmissionDateLike(String value) {
            addCriterion("admission_date like", value, "admissionDate");
            return (Criteria) this;
        }

        public Criteria andAdmissionDateNotLike(String value) {
            addCriterion("admission_date not like", value, "admissionDate");
            return (Criteria) this;
        }

        public Criteria andAdmissionDateIn(List<String> values) {
            addCriterion("admission_date in", values, "admissionDate");
            return (Criteria) this;
        }

        public Criteria andAdmissionDateNotIn(List<String> values) {
            addCriterion("admission_date not in", values, "admissionDate");
            return (Criteria) this;
        }

        public Criteria andAdmissionDateBetween(String value1, String value2) {
            addCriterion("admission_date between", value1, value2, "admissionDate");
            return (Criteria) this;
        }

        public Criteria andAdmissionDateNotBetween(String value1, String value2) {
            addCriterion("admission_date not between", value1, value2, "admissionDate");
            return (Criteria) this;
        }

        public Criteria andSchoolIdIsNull() {
            addCriterion("school_id is null");
            return (Criteria) this;
        }

        public Criteria andSchoolIdIsNotNull() {
            addCriterion("school_id is not null");
            return (Criteria) this;
        }

        public Criteria andSchoolIdEqualTo(String value) {
            addCriterion("school_id =", value, "schoolId");
            return (Criteria) this;
        }

        public Criteria andSchoolIdNotEqualTo(String value) {
            addCriterion("school_id <>", value, "schoolId");
            return (Criteria) this;
        }

        public Criteria andSchoolIdGreaterThan(String value) {
            addCriterion("school_id >", value, "schoolId");
            return (Criteria) this;
        }

        public Criteria andSchoolIdGreaterThanOrEqualTo(String value) {
            addCriterion("school_id >=", value, "schoolId");
            return (Criteria) this;
        }

        public Criteria andSchoolIdLessThan(String value) {
            addCriterion("school_id <", value, "schoolId");
            return (Criteria) this;
        }

        public Criteria andSchoolIdLessThanOrEqualTo(String value) {
            addCriterion("school_id <=", value, "schoolId");
            return (Criteria) this;
        }

        public Criteria andSchoolIdLike(String value) {
            addCriterion("school_id like", value, "schoolId");
            return (Criteria) this;
        }

        public Criteria andSchoolIdNotLike(String value) {
            addCriterion("school_id not like", value, "schoolId");
            return (Criteria) this;
        }

        public Criteria andSchoolIdIn(List<String> values) {
            addCriterion("school_id in", values, "schoolId");
            return (Criteria) this;
        }

        public Criteria andSchoolIdNotIn(List<String> values) {
            addCriterion("school_id not in", values, "schoolId");
            return (Criteria) this;
        }

        public Criteria andSchoolIdBetween(String value1, String value2) {
            addCriterion("school_id between", value1, value2, "schoolId");
            return (Criteria) this;
        }

        public Criteria andSchoolIdNotBetween(String value1, String value2) {
            addCriterion("school_id not between", value1, value2, "schoolId");
            return (Criteria) this;
        }

        public Criteria andClothSizeIsNull() {
            addCriterion("cloth_size is null");
            return (Criteria) this;
        }

        public Criteria andClothSizeIsNotNull() {
            addCriterion("cloth_size is not null");
            return (Criteria) this;
        }

        public Criteria andClothSizeEqualTo(String value) {
            addCriterion("cloth_size =", value, "clothSize");
            return (Criteria) this;
        }

        public Criteria andClothSizeNotEqualTo(String value) {
            addCriterion("cloth_size <>", value, "clothSize");
            return (Criteria) this;
        }

        public Criteria andClothSizeGreaterThan(String value) {
            addCriterion("cloth_size >", value, "clothSize");
            return (Criteria) this;
        }

        public Criteria andClothSizeGreaterThanOrEqualTo(String value) {
            addCriterion("cloth_size >=", value, "clothSize");
            return (Criteria) this;
        }

        public Criteria andClothSizeLessThan(String value) {
            addCriterion("cloth_size <", value, "clothSize");
            return (Criteria) this;
        }

        public Criteria andClothSizeLessThanOrEqualTo(String value) {
            addCriterion("cloth_size <=", value, "clothSize");
            return (Criteria) this;
        }

        public Criteria andClothSizeLike(String value) {
            addCriterion("cloth_size like", value, "clothSize");
            return (Criteria) this;
        }

        public Criteria andClothSizeNotLike(String value) {
            addCriterion("cloth_size not like", value, "clothSize");
            return (Criteria) this;
        }

        public Criteria andClothSizeIn(List<String> values) {
            addCriterion("cloth_size in", values, "clothSize");
            return (Criteria) this;
        }

        public Criteria andClothSizeNotIn(List<String> values) {
            addCriterion("cloth_size not in", values, "clothSize");
            return (Criteria) this;
        }

        public Criteria andClothSizeBetween(String value1, String value2) {
            addCriterion("cloth_size between", value1, value2, "clothSize");
            return (Criteria) this;
        }

        public Criteria andClothSizeNotBetween(String value1, String value2) {
            addCriterion("cloth_size not between", value1, value2, "clothSize");
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