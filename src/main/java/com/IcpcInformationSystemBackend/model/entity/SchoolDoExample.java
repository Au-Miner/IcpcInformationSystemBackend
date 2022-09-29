package com.IcpcInformationSystemBackend.model.entity;

import java.util.ArrayList;
import java.util.List;

public class SchoolDoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SchoolDoExample() {
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

        public Criteria andChiSchoolNameIsNull() {
            addCriterion("chi_school_name is null");
            return (Criteria) this;
        }

        public Criteria andChiSchoolNameIsNotNull() {
            addCriterion("chi_school_name is not null");
            return (Criteria) this;
        }

        public Criteria andChiSchoolNameEqualTo(String value) {
            addCriterion("chi_school_name =", value, "chiSchoolName");
            return (Criteria) this;
        }

        public Criteria andChiSchoolNameNotEqualTo(String value) {
            addCriterion("chi_school_name <>", value, "chiSchoolName");
            return (Criteria) this;
        }

        public Criteria andChiSchoolNameGreaterThan(String value) {
            addCriterion("chi_school_name >", value, "chiSchoolName");
            return (Criteria) this;
        }

        public Criteria andChiSchoolNameGreaterThanOrEqualTo(String value) {
            addCriterion("chi_school_name >=", value, "chiSchoolName");
            return (Criteria) this;
        }

        public Criteria andChiSchoolNameLessThan(String value) {
            addCriterion("chi_school_name <", value, "chiSchoolName");
            return (Criteria) this;
        }

        public Criteria andChiSchoolNameLessThanOrEqualTo(String value) {
            addCriterion("chi_school_name <=", value, "chiSchoolName");
            return (Criteria) this;
        }

        public Criteria andChiSchoolNameLike(String value) {
            addCriterion("chi_school_name like", value, "chiSchoolName");
            return (Criteria) this;
        }

        public Criteria andChiSchoolNameNotLike(String value) {
            addCriterion("chi_school_name not like", value, "chiSchoolName");
            return (Criteria) this;
        }

        public Criteria andChiSchoolNameIn(List<String> values) {
            addCriterion("chi_school_name in", values, "chiSchoolName");
            return (Criteria) this;
        }

        public Criteria andChiSchoolNameNotIn(List<String> values) {
            addCriterion("chi_school_name not in", values, "chiSchoolName");
            return (Criteria) this;
        }

        public Criteria andChiSchoolNameBetween(String value1, String value2) {
            addCriterion("chi_school_name between", value1, value2, "chiSchoolName");
            return (Criteria) this;
        }

        public Criteria andChiSchoolNameNotBetween(String value1, String value2) {
            addCriterion("chi_school_name not between", value1, value2, "chiSchoolName");
            return (Criteria) this;
        }

        public Criteria andEngSchoolNameIsNull() {
            addCriterion("eng_school_name is null");
            return (Criteria) this;
        }

        public Criteria andEngSchoolNameIsNotNull() {
            addCriterion("eng_school_name is not null");
            return (Criteria) this;
        }

        public Criteria andEngSchoolNameEqualTo(String value) {
            addCriterion("eng_school_name =", value, "engSchoolName");
            return (Criteria) this;
        }

        public Criteria andEngSchoolNameNotEqualTo(String value) {
            addCriterion("eng_school_name <>", value, "engSchoolName");
            return (Criteria) this;
        }

        public Criteria andEngSchoolNameGreaterThan(String value) {
            addCriterion("eng_school_name >", value, "engSchoolName");
            return (Criteria) this;
        }

        public Criteria andEngSchoolNameGreaterThanOrEqualTo(String value) {
            addCriterion("eng_school_name >=", value, "engSchoolName");
            return (Criteria) this;
        }

        public Criteria andEngSchoolNameLessThan(String value) {
            addCriterion("eng_school_name <", value, "engSchoolName");
            return (Criteria) this;
        }

        public Criteria andEngSchoolNameLessThanOrEqualTo(String value) {
            addCriterion("eng_school_name <=", value, "engSchoolName");
            return (Criteria) this;
        }

        public Criteria andEngSchoolNameLike(String value) {
            addCriterion("eng_school_name like", value, "engSchoolName");
            return (Criteria) this;
        }

        public Criteria andEngSchoolNameNotLike(String value) {
            addCriterion("eng_school_name not like", value, "engSchoolName");
            return (Criteria) this;
        }

        public Criteria andEngSchoolNameIn(List<String> values) {
            addCriterion("eng_school_name in", values, "engSchoolName");
            return (Criteria) this;
        }

        public Criteria andEngSchoolNameNotIn(List<String> values) {
            addCriterion("eng_school_name not in", values, "engSchoolName");
            return (Criteria) this;
        }

        public Criteria andEngSchoolNameBetween(String value1, String value2) {
            addCriterion("eng_school_name between", value1, value2, "engSchoolName");
            return (Criteria) this;
        }

        public Criteria andEngSchoolNameNotBetween(String value1, String value2) {
            addCriterion("eng_school_name not between", value1, value2, "engSchoolName");
            return (Criteria) this;
        }

        public Criteria andSchoolCodeIsNull() {
            addCriterion("school_code is null");
            return (Criteria) this;
        }

        public Criteria andSchoolCodeIsNotNull() {
            addCriterion("school_code is not null");
            return (Criteria) this;
        }

        public Criteria andSchoolCodeEqualTo(String value) {
            addCriterion("school_code =", value, "schoolCode");
            return (Criteria) this;
        }

        public Criteria andSchoolCodeNotEqualTo(String value) {
            addCriterion("school_code <>", value, "schoolCode");
            return (Criteria) this;
        }

        public Criteria andSchoolCodeGreaterThan(String value) {
            addCriterion("school_code >", value, "schoolCode");
            return (Criteria) this;
        }

        public Criteria andSchoolCodeGreaterThanOrEqualTo(String value) {
            addCriterion("school_code >=", value, "schoolCode");
            return (Criteria) this;
        }

        public Criteria andSchoolCodeLessThan(String value) {
            addCriterion("school_code <", value, "schoolCode");
            return (Criteria) this;
        }

        public Criteria andSchoolCodeLessThanOrEqualTo(String value) {
            addCriterion("school_code <=", value, "schoolCode");
            return (Criteria) this;
        }

        public Criteria andSchoolCodeLike(String value) {
            addCriterion("school_code like", value, "schoolCode");
            return (Criteria) this;
        }

        public Criteria andSchoolCodeNotLike(String value) {
            addCriterion("school_code not like", value, "schoolCode");
            return (Criteria) this;
        }

        public Criteria andSchoolCodeIn(List<String> values) {
            addCriterion("school_code in", values, "schoolCode");
            return (Criteria) this;
        }

        public Criteria andSchoolCodeNotIn(List<String> values) {
            addCriterion("school_code not in", values, "schoolCode");
            return (Criteria) this;
        }

        public Criteria andSchoolCodeBetween(String value1, String value2) {
            addCriterion("school_code between", value1, value2, "schoolCode");
            return (Criteria) this;
        }

        public Criteria andSchoolCodeNotBetween(String value1, String value2) {
            addCriterion("school_code not between", value1, value2, "schoolCode");
            return (Criteria) this;
        }

        public Criteria andSchoolImgIsNull() {
            addCriterion("school_img is null");
            return (Criteria) this;
        }

        public Criteria andSchoolImgIsNotNull() {
            addCriterion("school_img is not null");
            return (Criteria) this;
        }

        public Criteria andSchoolImgEqualTo(String value) {
            addCriterion("school_img =", value, "schoolImg");
            return (Criteria) this;
        }

        public Criteria andSchoolImgNotEqualTo(String value) {
            addCriterion("school_img <>", value, "schoolImg");
            return (Criteria) this;
        }

        public Criteria andSchoolImgGreaterThan(String value) {
            addCriterion("school_img >", value, "schoolImg");
            return (Criteria) this;
        }

        public Criteria andSchoolImgGreaterThanOrEqualTo(String value) {
            addCriterion("school_img >=", value, "schoolImg");
            return (Criteria) this;
        }

        public Criteria andSchoolImgLessThan(String value) {
            addCriterion("school_img <", value, "schoolImg");
            return (Criteria) this;
        }

        public Criteria andSchoolImgLessThanOrEqualTo(String value) {
            addCriterion("school_img <=", value, "schoolImg");
            return (Criteria) this;
        }

        public Criteria andSchoolImgLike(String value) {
            addCriterion("school_img like", value, "schoolImg");
            return (Criteria) this;
        }

        public Criteria andSchoolImgNotLike(String value) {
            addCriterion("school_img not like", value, "schoolImg");
            return (Criteria) this;
        }

        public Criteria andSchoolImgIn(List<String> values) {
            addCriterion("school_img in", values, "schoolImg");
            return (Criteria) this;
        }

        public Criteria andSchoolImgNotIn(List<String> values) {
            addCriterion("school_img not in", values, "schoolImg");
            return (Criteria) this;
        }

        public Criteria andSchoolImgBetween(String value1, String value2) {
            addCriterion("school_img between", value1, value2, "schoolImg");
            return (Criteria) this;
        }

        public Criteria andSchoolImgNotBetween(String value1, String value2) {
            addCriterion("school_img not between", value1, value2, "schoolImg");
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

        public Criteria andSchoolPositionIsNull() {
            addCriterion("school_position is null");
            return (Criteria) this;
        }

        public Criteria andSchoolPositionIsNotNull() {
            addCriterion("school_position is not null");
            return (Criteria) this;
        }

        public Criteria andSchoolPositionEqualTo(String value) {
            addCriterion("school_position =", value, "schoolPosition");
            return (Criteria) this;
        }

        public Criteria andSchoolPositionNotEqualTo(String value) {
            addCriterion("school_position <>", value, "schoolPosition");
            return (Criteria) this;
        }

        public Criteria andSchoolPositionGreaterThan(String value) {
            addCriterion("school_position >", value, "schoolPosition");
            return (Criteria) this;
        }

        public Criteria andSchoolPositionGreaterThanOrEqualTo(String value) {
            addCriterion("school_position >=", value, "schoolPosition");
            return (Criteria) this;
        }

        public Criteria andSchoolPositionLessThan(String value) {
            addCriterion("school_position <", value, "schoolPosition");
            return (Criteria) this;
        }

        public Criteria andSchoolPositionLessThanOrEqualTo(String value) {
            addCriterion("school_position <=", value, "schoolPosition");
            return (Criteria) this;
        }

        public Criteria andSchoolPositionLike(String value) {
            addCriterion("school_position like", value, "schoolPosition");
            return (Criteria) this;
        }

        public Criteria andSchoolPositionNotLike(String value) {
            addCriterion("school_position not like", value, "schoolPosition");
            return (Criteria) this;
        }

        public Criteria andSchoolPositionIn(List<String> values) {
            addCriterion("school_position in", values, "schoolPosition");
            return (Criteria) this;
        }

        public Criteria andSchoolPositionNotIn(List<String> values) {
            addCriterion("school_position not in", values, "schoolPosition");
            return (Criteria) this;
        }

        public Criteria andSchoolPositionBetween(String value1, String value2) {
            addCriterion("school_position between", value1, value2, "schoolPosition");
            return (Criteria) this;
        }

        public Criteria andSchoolPositionNotBetween(String value1, String value2) {
            addCriterion("school_position not between", value1, value2, "schoolPosition");
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