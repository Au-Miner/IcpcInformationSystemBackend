package com.IcpcInformationSystemBackend.model.entity;

import java.util.ArrayList;
import java.util.List;

public class CompetitionDoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public CompetitionDoExample() {
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

        public Criteria andCompetitionIdIsNull() {
            addCriterion("competition_id is null");
            return (Criteria) this;
        }

        public Criteria andCompetitionIdIsNotNull() {
            addCriterion("competition_id is not null");
            return (Criteria) this;
        }

        public Criteria andCompetitionIdEqualTo(String value) {
            addCriterion("competition_id =", value, "competitionId");
            return (Criteria) this;
        }

        public Criteria andCompetitionIdNotEqualTo(String value) {
            addCriterion("competition_id <>", value, "competitionId");
            return (Criteria) this;
        }

        public Criteria andCompetitionIdGreaterThan(String value) {
            addCriterion("competition_id >", value, "competitionId");
            return (Criteria) this;
        }

        public Criteria andCompetitionIdGreaterThanOrEqualTo(String value) {
            addCriterion("competition_id >=", value, "competitionId");
            return (Criteria) this;
        }

        public Criteria andCompetitionIdLessThan(String value) {
            addCriterion("competition_id <", value, "competitionId");
            return (Criteria) this;
        }

        public Criteria andCompetitionIdLessThanOrEqualTo(String value) {
            addCriterion("competition_id <=", value, "competitionId");
            return (Criteria) this;
        }

        public Criteria andCompetitionIdLike(String value) {
            addCriterion("competition_id like", value, "competitionId");
            return (Criteria) this;
        }

        public Criteria andCompetitionIdNotLike(String value) {
            addCriterion("competition_id not like", value, "competitionId");
            return (Criteria) this;
        }

        public Criteria andCompetitionIdIn(List<String> values) {
            addCriterion("competition_id in", values, "competitionId");
            return (Criteria) this;
        }

        public Criteria andCompetitionIdNotIn(List<String> values) {
            addCriterion("competition_id not in", values, "competitionId");
            return (Criteria) this;
        }

        public Criteria andCompetitionIdBetween(String value1, String value2) {
            addCriterion("competition_id between", value1, value2, "competitionId");
            return (Criteria) this;
        }

        public Criteria andCompetitionIdNotBetween(String value1, String value2) {
            addCriterion("competition_id not between", value1, value2, "competitionId");
            return (Criteria) this;
        }

        public Criteria andCompetitionChiNameIsNull() {
            addCriterion("competition_chi_name is null");
            return (Criteria) this;
        }

        public Criteria andCompetitionChiNameIsNotNull() {
            addCriterion("competition_chi_name is not null");
            return (Criteria) this;
        }

        public Criteria andCompetitionChiNameEqualTo(String value) {
            addCriterion("competition_chi_name =", value, "competitionChiName");
            return (Criteria) this;
        }

        public Criteria andCompetitionChiNameNotEqualTo(String value) {
            addCriterion("competition_chi_name <>", value, "competitionChiName");
            return (Criteria) this;
        }

        public Criteria andCompetitionChiNameGreaterThan(String value) {
            addCriterion("competition_chi_name >", value, "competitionChiName");
            return (Criteria) this;
        }

        public Criteria andCompetitionChiNameGreaterThanOrEqualTo(String value) {
            addCriterion("competition_chi_name >=", value, "competitionChiName");
            return (Criteria) this;
        }

        public Criteria andCompetitionChiNameLessThan(String value) {
            addCriterion("competition_chi_name <", value, "competitionChiName");
            return (Criteria) this;
        }

        public Criteria andCompetitionChiNameLessThanOrEqualTo(String value) {
            addCriterion("competition_chi_name <=", value, "competitionChiName");
            return (Criteria) this;
        }

        public Criteria andCompetitionChiNameLike(String value) {
            addCriterion("competition_chi_name like", value, "competitionChiName");
            return (Criteria) this;
        }

        public Criteria andCompetitionChiNameNotLike(String value) {
            addCriterion("competition_chi_name not like", value, "competitionChiName");
            return (Criteria) this;
        }

        public Criteria andCompetitionChiNameIn(List<String> values) {
            addCriterion("competition_chi_name in", values, "competitionChiName");
            return (Criteria) this;
        }

        public Criteria andCompetitionChiNameNotIn(List<String> values) {
            addCriterion("competition_chi_name not in", values, "competitionChiName");
            return (Criteria) this;
        }

        public Criteria andCompetitionChiNameBetween(String value1, String value2) {
            addCriterion("competition_chi_name between", value1, value2, "competitionChiName");
            return (Criteria) this;
        }

        public Criteria andCompetitionChiNameNotBetween(String value1, String value2) {
            addCriterion("competition_chi_name not between", value1, value2, "competitionChiName");
            return (Criteria) this;
        }

        public Criteria andCompetitionEngNameIsNull() {
            addCriterion("competition_eng_name is null");
            return (Criteria) this;
        }

        public Criteria andCompetitionEngNameIsNotNull() {
            addCriterion("competition_eng_name is not null");
            return (Criteria) this;
        }

        public Criteria andCompetitionEngNameEqualTo(String value) {
            addCriterion("competition_eng_name =", value, "competitionEngName");
            return (Criteria) this;
        }

        public Criteria andCompetitionEngNameNotEqualTo(String value) {
            addCriterion("competition_eng_name <>", value, "competitionEngName");
            return (Criteria) this;
        }

        public Criteria andCompetitionEngNameGreaterThan(String value) {
            addCriterion("competition_eng_name >", value, "competitionEngName");
            return (Criteria) this;
        }

        public Criteria andCompetitionEngNameGreaterThanOrEqualTo(String value) {
            addCriterion("competition_eng_name >=", value, "competitionEngName");
            return (Criteria) this;
        }

        public Criteria andCompetitionEngNameLessThan(String value) {
            addCriterion("competition_eng_name <", value, "competitionEngName");
            return (Criteria) this;
        }

        public Criteria andCompetitionEngNameLessThanOrEqualTo(String value) {
            addCriterion("competition_eng_name <=", value, "competitionEngName");
            return (Criteria) this;
        }

        public Criteria andCompetitionEngNameLike(String value) {
            addCriterion("competition_eng_name like", value, "competitionEngName");
            return (Criteria) this;
        }

        public Criteria andCompetitionEngNameNotLike(String value) {
            addCriterion("competition_eng_name not like", value, "competitionEngName");
            return (Criteria) this;
        }

        public Criteria andCompetitionEngNameIn(List<String> values) {
            addCriterion("competition_eng_name in", values, "competitionEngName");
            return (Criteria) this;
        }

        public Criteria andCompetitionEngNameNotIn(List<String> values) {
            addCriterion("competition_eng_name not in", values, "competitionEngName");
            return (Criteria) this;
        }

        public Criteria andCompetitionEngNameBetween(String value1, String value2) {
            addCriterion("competition_eng_name between", value1, value2, "competitionEngName");
            return (Criteria) this;
        }

        public Criteria andCompetitionEngNameNotBetween(String value1, String value2) {
            addCriterion("competition_eng_name not between", value1, value2, "competitionEngName");
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

        public Criteria andUserIdIsNull() {
            addCriterion("user_id is null");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNotNull() {
            addCriterion("user_id is not null");
            return (Criteria) this;
        }

        public Criteria andUserIdEqualTo(String value) {
            addCriterion("user_id =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(String value) {
            addCriterion("user_id <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(String value) {
            addCriterion("user_id >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(String value) {
            addCriterion("user_id >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(String value) {
            addCriterion("user_id <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(String value) {
            addCriterion("user_id <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLike(String value) {
            addCriterion("user_id like", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotLike(String value) {
            addCriterion("user_id not like", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<String> values) {
            addCriterion("user_id in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<String> values) {
            addCriterion("user_id not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(String value1, String value2) {
            addCriterion("user_id between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(String value1, String value2) {
            addCriterion("user_id not between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andHeldPositionIsNull() {
            addCriterion("held_position is null");
            return (Criteria) this;
        }

        public Criteria andHeldPositionIsNotNull() {
            addCriterion("held_position is not null");
            return (Criteria) this;
        }

        public Criteria andHeldPositionEqualTo(String value) {
            addCriterion("held_position =", value, "heldPosition");
            return (Criteria) this;
        }

        public Criteria andHeldPositionNotEqualTo(String value) {
            addCriterion("held_position <>", value, "heldPosition");
            return (Criteria) this;
        }

        public Criteria andHeldPositionGreaterThan(String value) {
            addCriterion("held_position >", value, "heldPosition");
            return (Criteria) this;
        }

        public Criteria andHeldPositionGreaterThanOrEqualTo(String value) {
            addCriterion("held_position >=", value, "heldPosition");
            return (Criteria) this;
        }

        public Criteria andHeldPositionLessThan(String value) {
            addCriterion("held_position <", value, "heldPosition");
            return (Criteria) this;
        }

        public Criteria andHeldPositionLessThanOrEqualTo(String value) {
            addCriterion("held_position <=", value, "heldPosition");
            return (Criteria) this;
        }

        public Criteria andHeldPositionLike(String value) {
            addCriterion("held_position like", value, "heldPosition");
            return (Criteria) this;
        }

        public Criteria andHeldPositionNotLike(String value) {
            addCriterion("held_position not like", value, "heldPosition");
            return (Criteria) this;
        }

        public Criteria andHeldPositionIn(List<String> values) {
            addCriterion("held_position in", values, "heldPosition");
            return (Criteria) this;
        }

        public Criteria andHeldPositionNotIn(List<String> values) {
            addCriterion("held_position not in", values, "heldPosition");
            return (Criteria) this;
        }

        public Criteria andHeldPositionBetween(String value1, String value2) {
            addCriterion("held_position between", value1, value2, "heldPosition");
            return (Criteria) this;
        }

        public Criteria andHeldPositionNotBetween(String value1, String value2) {
            addCriterion("held_position not between", value1, value2, "heldPosition");
            return (Criteria) this;
        }

        public Criteria andRegistrationStartTimeIsNull() {
            addCriterion("registration_start_time is null");
            return (Criteria) this;
        }

        public Criteria andRegistrationStartTimeIsNotNull() {
            addCriterion("registration_start_time is not null");
            return (Criteria) this;
        }

        public Criteria andRegistrationStartTimeEqualTo(String value) {
            addCriterion("registration_start_time =", value, "registrationStartTime");
            return (Criteria) this;
        }

        public Criteria andRegistrationStartTimeNotEqualTo(String value) {
            addCriterion("registration_start_time <>", value, "registrationStartTime");
            return (Criteria) this;
        }

        public Criteria andRegistrationStartTimeGreaterThan(String value) {
            addCriterion("registration_start_time >", value, "registrationStartTime");
            return (Criteria) this;
        }

        public Criteria andRegistrationStartTimeGreaterThanOrEqualTo(String value) {
            addCriterion("registration_start_time >=", value, "registrationStartTime");
            return (Criteria) this;
        }

        public Criteria andRegistrationStartTimeLessThan(String value) {
            addCriterion("registration_start_time <", value, "registrationStartTime");
            return (Criteria) this;
        }

        public Criteria andRegistrationStartTimeLessThanOrEqualTo(String value) {
            addCriterion("registration_start_time <=", value, "registrationStartTime");
            return (Criteria) this;
        }

        public Criteria andRegistrationStartTimeLike(String value) {
            addCriterion("registration_start_time like", value, "registrationStartTime");
            return (Criteria) this;
        }

        public Criteria andRegistrationStartTimeNotLike(String value) {
            addCriterion("registration_start_time not like", value, "registrationStartTime");
            return (Criteria) this;
        }

        public Criteria andRegistrationStartTimeIn(List<String> values) {
            addCriterion("registration_start_time in", values, "registrationStartTime");
            return (Criteria) this;
        }

        public Criteria andRegistrationStartTimeNotIn(List<String> values) {
            addCriterion("registration_start_time not in", values, "registrationStartTime");
            return (Criteria) this;
        }

        public Criteria andRegistrationStartTimeBetween(String value1, String value2) {
            addCriterion("registration_start_time between", value1, value2, "registrationStartTime");
            return (Criteria) this;
        }

        public Criteria andRegistrationStartTimeNotBetween(String value1, String value2) {
            addCriterion("registration_start_time not between", value1, value2, "registrationStartTime");
            return (Criteria) this;
        }

        public Criteria andRegistrationEndTimeIsNull() {
            addCriterion("registration_end_time is null");
            return (Criteria) this;
        }

        public Criteria andRegistrationEndTimeIsNotNull() {
            addCriterion("registration_end_time is not null");
            return (Criteria) this;
        }

        public Criteria andRegistrationEndTimeEqualTo(String value) {
            addCriterion("registration_end_time =", value, "registrationEndTime");
            return (Criteria) this;
        }

        public Criteria andRegistrationEndTimeNotEqualTo(String value) {
            addCriterion("registration_end_time <>", value, "registrationEndTime");
            return (Criteria) this;
        }

        public Criteria andRegistrationEndTimeGreaterThan(String value) {
            addCriterion("registration_end_time >", value, "registrationEndTime");
            return (Criteria) this;
        }

        public Criteria andRegistrationEndTimeGreaterThanOrEqualTo(String value) {
            addCriterion("registration_end_time >=", value, "registrationEndTime");
            return (Criteria) this;
        }

        public Criteria andRegistrationEndTimeLessThan(String value) {
            addCriterion("registration_end_time <", value, "registrationEndTime");
            return (Criteria) this;
        }

        public Criteria andRegistrationEndTimeLessThanOrEqualTo(String value) {
            addCriterion("registration_end_time <=", value, "registrationEndTime");
            return (Criteria) this;
        }

        public Criteria andRegistrationEndTimeLike(String value) {
            addCriterion("registration_end_time like", value, "registrationEndTime");
            return (Criteria) this;
        }

        public Criteria andRegistrationEndTimeNotLike(String value) {
            addCriterion("registration_end_time not like", value, "registrationEndTime");
            return (Criteria) this;
        }

        public Criteria andRegistrationEndTimeIn(List<String> values) {
            addCriterion("registration_end_time in", values, "registrationEndTime");
            return (Criteria) this;
        }

        public Criteria andRegistrationEndTimeNotIn(List<String> values) {
            addCriterion("registration_end_time not in", values, "registrationEndTime");
            return (Criteria) this;
        }

        public Criteria andRegistrationEndTimeBetween(String value1, String value2) {
            addCriterion("registration_end_time between", value1, value2, "registrationEndTime");
            return (Criteria) this;
        }

        public Criteria andRegistrationEndTimeNotBetween(String value1, String value2) {
            addCriterion("registration_end_time not between", value1, value2, "registrationEndTime");
            return (Criteria) this;
        }

        public Criteria andCompetitionStartTimeIsNull() {
            addCriterion("competition_start_time is null");
            return (Criteria) this;
        }

        public Criteria andCompetitionStartTimeIsNotNull() {
            addCriterion("competition_start_time is not null");
            return (Criteria) this;
        }

        public Criteria andCompetitionStartTimeEqualTo(String value) {
            addCriterion("competition_start_time =", value, "competitionStartTime");
            return (Criteria) this;
        }

        public Criteria andCompetitionStartTimeNotEqualTo(String value) {
            addCriterion("competition_start_time <>", value, "competitionStartTime");
            return (Criteria) this;
        }

        public Criteria andCompetitionStartTimeGreaterThan(String value) {
            addCriterion("competition_start_time >", value, "competitionStartTime");
            return (Criteria) this;
        }

        public Criteria andCompetitionStartTimeGreaterThanOrEqualTo(String value) {
            addCriterion("competition_start_time >=", value, "competitionStartTime");
            return (Criteria) this;
        }

        public Criteria andCompetitionStartTimeLessThan(String value) {
            addCriterion("competition_start_time <", value, "competitionStartTime");
            return (Criteria) this;
        }

        public Criteria andCompetitionStartTimeLessThanOrEqualTo(String value) {
            addCriterion("competition_start_time <=", value, "competitionStartTime");
            return (Criteria) this;
        }

        public Criteria andCompetitionStartTimeLike(String value) {
            addCriterion("competition_start_time like", value, "competitionStartTime");
            return (Criteria) this;
        }

        public Criteria andCompetitionStartTimeNotLike(String value) {
            addCriterion("competition_start_time not like", value, "competitionStartTime");
            return (Criteria) this;
        }

        public Criteria andCompetitionStartTimeIn(List<String> values) {
            addCriterion("competition_start_time in", values, "competitionStartTime");
            return (Criteria) this;
        }

        public Criteria andCompetitionStartTimeNotIn(List<String> values) {
            addCriterion("competition_start_time not in", values, "competitionStartTime");
            return (Criteria) this;
        }

        public Criteria andCompetitionStartTimeBetween(String value1, String value2) {
            addCriterion("competition_start_time between", value1, value2, "competitionStartTime");
            return (Criteria) this;
        }

        public Criteria andCompetitionStartTimeNotBetween(String value1, String value2) {
            addCriterion("competition_start_time not between", value1, value2, "competitionStartTime");
            return (Criteria) this;
        }

        public Criteria andDurationIsNull() {
            addCriterion("duration is null");
            return (Criteria) this;
        }

        public Criteria andDurationIsNotNull() {
            addCriterion("duration is not null");
            return (Criteria) this;
        }

        public Criteria andDurationEqualTo(Integer value) {
            addCriterion("duration =", value, "duration");
            return (Criteria) this;
        }

        public Criteria andDurationNotEqualTo(Integer value) {
            addCriterion("duration <>", value, "duration");
            return (Criteria) this;
        }

        public Criteria andDurationGreaterThan(Integer value) {
            addCriterion("duration >", value, "duration");
            return (Criteria) this;
        }

        public Criteria andDurationGreaterThanOrEqualTo(Integer value) {
            addCriterion("duration >=", value, "duration");
            return (Criteria) this;
        }

        public Criteria andDurationLessThan(Integer value) {
            addCriterion("duration <", value, "duration");
            return (Criteria) this;
        }

        public Criteria andDurationLessThanOrEqualTo(Integer value) {
            addCriterion("duration <=", value, "duration");
            return (Criteria) this;
        }

        public Criteria andDurationIn(List<Integer> values) {
            addCriterion("duration in", values, "duration");
            return (Criteria) this;
        }

        public Criteria andDurationNotIn(List<Integer> values) {
            addCriterion("duration not in", values, "duration");
            return (Criteria) this;
        }

        public Criteria andDurationBetween(Integer value1, Integer value2) {
            addCriterion("duration between", value1, value2, "duration");
            return (Criteria) this;
        }

        public Criteria andDurationNotBetween(Integer value1, Integer value2) {
            addCriterion("duration not between", value1, value2, "duration");
            return (Criteria) this;
        }

        public Criteria andCompetitionIntroductionIsNull() {
            addCriterion("competition_introduction is null");
            return (Criteria) this;
        }

        public Criteria andCompetitionIntroductionIsNotNull() {
            addCriterion("competition_introduction is not null");
            return (Criteria) this;
        }

        public Criteria andCompetitionIntroductionEqualTo(String value) {
            addCriterion("competition_introduction =", value, "competitionIntroduction");
            return (Criteria) this;
        }

        public Criteria andCompetitionIntroductionNotEqualTo(String value) {
            addCriterion("competition_introduction <>", value, "competitionIntroduction");
            return (Criteria) this;
        }

        public Criteria andCompetitionIntroductionGreaterThan(String value) {
            addCriterion("competition_introduction >", value, "competitionIntroduction");
            return (Criteria) this;
        }

        public Criteria andCompetitionIntroductionGreaterThanOrEqualTo(String value) {
            addCriterion("competition_introduction >=", value, "competitionIntroduction");
            return (Criteria) this;
        }

        public Criteria andCompetitionIntroductionLessThan(String value) {
            addCriterion("competition_introduction <", value, "competitionIntroduction");
            return (Criteria) this;
        }

        public Criteria andCompetitionIntroductionLessThanOrEqualTo(String value) {
            addCriterion("competition_introduction <=", value, "competitionIntroduction");
            return (Criteria) this;
        }

        public Criteria andCompetitionIntroductionLike(String value) {
            addCriterion("competition_introduction like", value, "competitionIntroduction");
            return (Criteria) this;
        }

        public Criteria andCompetitionIntroductionNotLike(String value) {
            addCriterion("competition_introduction not like", value, "competitionIntroduction");
            return (Criteria) this;
        }

        public Criteria andCompetitionIntroductionIn(List<String> values) {
            addCriterion("competition_introduction in", values, "competitionIntroduction");
            return (Criteria) this;
        }

        public Criteria andCompetitionIntroductionNotIn(List<String> values) {
            addCriterion("competition_introduction not in", values, "competitionIntroduction");
            return (Criteria) this;
        }

        public Criteria andCompetitionIntroductionBetween(String value1, String value2) {
            addCriterion("competition_introduction between", value1, value2, "competitionIntroduction");
            return (Criteria) this;
        }

        public Criteria andCompetitionIntroductionNotBetween(String value1, String value2) {
            addCriterion("competition_introduction not between", value1, value2, "competitionIntroduction");
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