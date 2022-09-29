package com.IcpcInformationSystemBackend.model.entity;

import java.util.ArrayList;
import java.util.List;

public class TeamDoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TeamDoExample() {
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

        public Criteria andTeamIdIsNull() {
            addCriterion("team_id is null");
            return (Criteria) this;
        }

        public Criteria andTeamIdIsNotNull() {
            addCriterion("team_id is not null");
            return (Criteria) this;
        }

        public Criteria andTeamIdEqualTo(String value) {
            addCriterion("team_id =", value, "teamId");
            return (Criteria) this;
        }

        public Criteria andTeamIdNotEqualTo(String value) {
            addCriterion("team_id <>", value, "teamId");
            return (Criteria) this;
        }

        public Criteria andTeamIdGreaterThan(String value) {
            addCriterion("team_id >", value, "teamId");
            return (Criteria) this;
        }

        public Criteria andTeamIdGreaterThanOrEqualTo(String value) {
            addCriterion("team_id >=", value, "teamId");
            return (Criteria) this;
        }

        public Criteria andTeamIdLessThan(String value) {
            addCriterion("team_id <", value, "teamId");
            return (Criteria) this;
        }

        public Criteria andTeamIdLessThanOrEqualTo(String value) {
            addCriterion("team_id <=", value, "teamId");
            return (Criteria) this;
        }

        public Criteria andTeamIdLike(String value) {
            addCriterion("team_id like", value, "teamId");
            return (Criteria) this;
        }

        public Criteria andTeamIdNotLike(String value) {
            addCriterion("team_id not like", value, "teamId");
            return (Criteria) this;
        }

        public Criteria andTeamIdIn(List<String> values) {
            addCriterion("team_id in", values, "teamId");
            return (Criteria) this;
        }

        public Criteria andTeamIdNotIn(List<String> values) {
            addCriterion("team_id not in", values, "teamId");
            return (Criteria) this;
        }

        public Criteria andTeamIdBetween(String value1, String value2) {
            addCriterion("team_id between", value1, value2, "teamId");
            return (Criteria) this;
        }

        public Criteria andTeamIdNotBetween(String value1, String value2) {
            addCriterion("team_id not between", value1, value2, "teamId");
            return (Criteria) this;
        }

        public Criteria andChiTeamNameIsNull() {
            addCriterion("chi_team_name is null");
            return (Criteria) this;
        }

        public Criteria andChiTeamNameIsNotNull() {
            addCriterion("chi_team_name is not null");
            return (Criteria) this;
        }

        public Criteria andChiTeamNameEqualTo(String value) {
            addCriterion("chi_team_name =", value, "chiTeamName");
            return (Criteria) this;
        }

        public Criteria andChiTeamNameNotEqualTo(String value) {
            addCriterion("chi_team_name <>", value, "chiTeamName");
            return (Criteria) this;
        }

        public Criteria andChiTeamNameGreaterThan(String value) {
            addCriterion("chi_team_name >", value, "chiTeamName");
            return (Criteria) this;
        }

        public Criteria andChiTeamNameGreaterThanOrEqualTo(String value) {
            addCriterion("chi_team_name >=", value, "chiTeamName");
            return (Criteria) this;
        }

        public Criteria andChiTeamNameLessThan(String value) {
            addCriterion("chi_team_name <", value, "chiTeamName");
            return (Criteria) this;
        }

        public Criteria andChiTeamNameLessThanOrEqualTo(String value) {
            addCriterion("chi_team_name <=", value, "chiTeamName");
            return (Criteria) this;
        }

        public Criteria andChiTeamNameLike(String value) {
            addCriterion("chi_team_name like", value, "chiTeamName");
            return (Criteria) this;
        }

        public Criteria andChiTeamNameNotLike(String value) {
            addCriterion("chi_team_name not like", value, "chiTeamName");
            return (Criteria) this;
        }

        public Criteria andChiTeamNameIn(List<String> values) {
            addCriterion("chi_team_name in", values, "chiTeamName");
            return (Criteria) this;
        }

        public Criteria andChiTeamNameNotIn(List<String> values) {
            addCriterion("chi_team_name not in", values, "chiTeamName");
            return (Criteria) this;
        }

        public Criteria andChiTeamNameBetween(String value1, String value2) {
            addCriterion("chi_team_name between", value1, value2, "chiTeamName");
            return (Criteria) this;
        }

        public Criteria andChiTeamNameNotBetween(String value1, String value2) {
            addCriterion("chi_team_name not between", value1, value2, "chiTeamName");
            return (Criteria) this;
        }

        public Criteria andEngTeamNameIsNull() {
            addCriterion("eng_team_name is null");
            return (Criteria) this;
        }

        public Criteria andEngTeamNameIsNotNull() {
            addCriterion("eng_team_name is not null");
            return (Criteria) this;
        }

        public Criteria andEngTeamNameEqualTo(String value) {
            addCriterion("eng_team_name =", value, "engTeamName");
            return (Criteria) this;
        }

        public Criteria andEngTeamNameNotEqualTo(String value) {
            addCriterion("eng_team_name <>", value, "engTeamName");
            return (Criteria) this;
        }

        public Criteria andEngTeamNameGreaterThan(String value) {
            addCriterion("eng_team_name >", value, "engTeamName");
            return (Criteria) this;
        }

        public Criteria andEngTeamNameGreaterThanOrEqualTo(String value) {
            addCriterion("eng_team_name >=", value, "engTeamName");
            return (Criteria) this;
        }

        public Criteria andEngTeamNameLessThan(String value) {
            addCriterion("eng_team_name <", value, "engTeamName");
            return (Criteria) this;
        }

        public Criteria andEngTeamNameLessThanOrEqualTo(String value) {
            addCriterion("eng_team_name <=", value, "engTeamName");
            return (Criteria) this;
        }

        public Criteria andEngTeamNameLike(String value) {
            addCriterion("eng_team_name like", value, "engTeamName");
            return (Criteria) this;
        }

        public Criteria andEngTeamNameNotLike(String value) {
            addCriterion("eng_team_name not like", value, "engTeamName");
            return (Criteria) this;
        }

        public Criteria andEngTeamNameIn(List<String> values) {
            addCriterion("eng_team_name in", values, "engTeamName");
            return (Criteria) this;
        }

        public Criteria andEngTeamNameNotIn(List<String> values) {
            addCriterion("eng_team_name not in", values, "engTeamName");
            return (Criteria) this;
        }

        public Criteria andEngTeamNameBetween(String value1, String value2) {
            addCriterion("eng_team_name between", value1, value2, "engTeamName");
            return (Criteria) this;
        }

        public Criteria andEngTeamNameNotBetween(String value1, String value2) {
            addCriterion("eng_team_name not between", value1, value2, "engTeamName");
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

        public Criteria andMember1IdIsNull() {
            addCriterion("member1_id is null");
            return (Criteria) this;
        }

        public Criteria andMember1IdIsNotNull() {
            addCriterion("member1_id is not null");
            return (Criteria) this;
        }

        public Criteria andMember1IdEqualTo(String value) {
            addCriterion("member1_id =", value, "member1Id");
            return (Criteria) this;
        }

        public Criteria andMember1IdNotEqualTo(String value) {
            addCriterion("member1_id <>", value, "member1Id");
            return (Criteria) this;
        }

        public Criteria andMember1IdGreaterThan(String value) {
            addCriterion("member1_id >", value, "member1Id");
            return (Criteria) this;
        }

        public Criteria andMember1IdGreaterThanOrEqualTo(String value) {
            addCriterion("member1_id >=", value, "member1Id");
            return (Criteria) this;
        }

        public Criteria andMember1IdLessThan(String value) {
            addCriterion("member1_id <", value, "member1Id");
            return (Criteria) this;
        }

        public Criteria andMember1IdLessThanOrEqualTo(String value) {
            addCriterion("member1_id <=", value, "member1Id");
            return (Criteria) this;
        }

        public Criteria andMember1IdLike(String value) {
            addCriterion("member1_id like", value, "member1Id");
            return (Criteria) this;
        }

        public Criteria andMember1IdNotLike(String value) {
            addCriterion("member1_id not like", value, "member1Id");
            return (Criteria) this;
        }

        public Criteria andMember1IdIn(List<String> values) {
            addCriterion("member1_id in", values, "member1Id");
            return (Criteria) this;
        }

        public Criteria andMember1IdNotIn(List<String> values) {
            addCriterion("member1_id not in", values, "member1Id");
            return (Criteria) this;
        }

        public Criteria andMember1IdBetween(String value1, String value2) {
            addCriterion("member1_id between", value1, value2, "member1Id");
            return (Criteria) this;
        }

        public Criteria andMember1IdNotBetween(String value1, String value2) {
            addCriterion("member1_id not between", value1, value2, "member1Id");
            return (Criteria) this;
        }

        public Criteria andMember2IdIsNull() {
            addCriterion("member2_id is null");
            return (Criteria) this;
        }

        public Criteria andMember2IdIsNotNull() {
            addCriterion("member2_id is not null");
            return (Criteria) this;
        }

        public Criteria andMember2IdEqualTo(String value) {
            addCriterion("member2_id =", value, "member2Id");
            return (Criteria) this;
        }

        public Criteria andMember2IdNotEqualTo(String value) {
            addCriterion("member2_id <>", value, "member2Id");
            return (Criteria) this;
        }

        public Criteria andMember2IdGreaterThan(String value) {
            addCriterion("member2_id >", value, "member2Id");
            return (Criteria) this;
        }

        public Criteria andMember2IdGreaterThanOrEqualTo(String value) {
            addCriterion("member2_id >=", value, "member2Id");
            return (Criteria) this;
        }

        public Criteria andMember2IdLessThan(String value) {
            addCriterion("member2_id <", value, "member2Id");
            return (Criteria) this;
        }

        public Criteria andMember2IdLessThanOrEqualTo(String value) {
            addCriterion("member2_id <=", value, "member2Id");
            return (Criteria) this;
        }

        public Criteria andMember2IdLike(String value) {
            addCriterion("member2_id like", value, "member2Id");
            return (Criteria) this;
        }

        public Criteria andMember2IdNotLike(String value) {
            addCriterion("member2_id not like", value, "member2Id");
            return (Criteria) this;
        }

        public Criteria andMember2IdIn(List<String> values) {
            addCriterion("member2_id in", values, "member2Id");
            return (Criteria) this;
        }

        public Criteria andMember2IdNotIn(List<String> values) {
            addCriterion("member2_id not in", values, "member2Id");
            return (Criteria) this;
        }

        public Criteria andMember2IdBetween(String value1, String value2) {
            addCriterion("member2_id between", value1, value2, "member2Id");
            return (Criteria) this;
        }

        public Criteria andMember2IdNotBetween(String value1, String value2) {
            addCriterion("member2_id not between", value1, value2, "member2Id");
            return (Criteria) this;
        }

        public Criteria andMember3IdIsNull() {
            addCriterion("member3_id is null");
            return (Criteria) this;
        }

        public Criteria andMember3IdIsNotNull() {
            addCriterion("member3_id is not null");
            return (Criteria) this;
        }

        public Criteria andMember3IdEqualTo(String value) {
            addCriterion("member3_id =", value, "member3Id");
            return (Criteria) this;
        }

        public Criteria andMember3IdNotEqualTo(String value) {
            addCriterion("member3_id <>", value, "member3Id");
            return (Criteria) this;
        }

        public Criteria andMember3IdGreaterThan(String value) {
            addCriterion("member3_id >", value, "member3Id");
            return (Criteria) this;
        }

        public Criteria andMember3IdGreaterThanOrEqualTo(String value) {
            addCriterion("member3_id >=", value, "member3Id");
            return (Criteria) this;
        }

        public Criteria andMember3IdLessThan(String value) {
            addCriterion("member3_id <", value, "member3Id");
            return (Criteria) this;
        }

        public Criteria andMember3IdLessThanOrEqualTo(String value) {
            addCriterion("member3_id <=", value, "member3Id");
            return (Criteria) this;
        }

        public Criteria andMember3IdLike(String value) {
            addCriterion("member3_id like", value, "member3Id");
            return (Criteria) this;
        }

        public Criteria andMember3IdNotLike(String value) {
            addCriterion("member3_id not like", value, "member3Id");
            return (Criteria) this;
        }

        public Criteria andMember3IdIn(List<String> values) {
            addCriterion("member3_id in", values, "member3Id");
            return (Criteria) this;
        }

        public Criteria andMember3IdNotIn(List<String> values) {
            addCriterion("member3_id not in", values, "member3Id");
            return (Criteria) this;
        }

        public Criteria andMember3IdBetween(String value1, String value2) {
            addCriterion("member3_id between", value1, value2, "member3Id");
            return (Criteria) this;
        }

        public Criteria andMember3IdNotBetween(String value1, String value2) {
            addCriterion("member3_id not between", value1, value2, "member3Id");
            return (Criteria) this;
        }

        public Criteria andCoach1IdIsNull() {
            addCriterion("coach1_id is null");
            return (Criteria) this;
        }

        public Criteria andCoach1IdIsNotNull() {
            addCriterion("coach1_id is not null");
            return (Criteria) this;
        }

        public Criteria andCoach1IdEqualTo(String value) {
            addCriterion("coach1_id =", value, "coach1Id");
            return (Criteria) this;
        }

        public Criteria andCoach1IdNotEqualTo(String value) {
            addCriterion("coach1_id <>", value, "coach1Id");
            return (Criteria) this;
        }

        public Criteria andCoach1IdGreaterThan(String value) {
            addCriterion("coach1_id >", value, "coach1Id");
            return (Criteria) this;
        }

        public Criteria andCoach1IdGreaterThanOrEqualTo(String value) {
            addCriterion("coach1_id >=", value, "coach1Id");
            return (Criteria) this;
        }

        public Criteria andCoach1IdLessThan(String value) {
            addCriterion("coach1_id <", value, "coach1Id");
            return (Criteria) this;
        }

        public Criteria andCoach1IdLessThanOrEqualTo(String value) {
            addCriterion("coach1_id <=", value, "coach1Id");
            return (Criteria) this;
        }

        public Criteria andCoach1IdLike(String value) {
            addCriterion("coach1_id like", value, "coach1Id");
            return (Criteria) this;
        }

        public Criteria andCoach1IdNotLike(String value) {
            addCriterion("coach1_id not like", value, "coach1Id");
            return (Criteria) this;
        }

        public Criteria andCoach1IdIn(List<String> values) {
            addCriterion("coach1_id in", values, "coach1Id");
            return (Criteria) this;
        }

        public Criteria andCoach1IdNotIn(List<String> values) {
            addCriterion("coach1_id not in", values, "coach1Id");
            return (Criteria) this;
        }

        public Criteria andCoach1IdBetween(String value1, String value2) {
            addCriterion("coach1_id between", value1, value2, "coach1Id");
            return (Criteria) this;
        }

        public Criteria andCoach1IdNotBetween(String value1, String value2) {
            addCriterion("coach1_id not between", value1, value2, "coach1Id");
            return (Criteria) this;
        }

        public Criteria andCoach2IdIsNull() {
            addCriterion("coach2_id is null");
            return (Criteria) this;
        }

        public Criteria andCoach2IdIsNotNull() {
            addCriterion("coach2_id is not null");
            return (Criteria) this;
        }

        public Criteria andCoach2IdEqualTo(String value) {
            addCriterion("coach2_id =", value, "coach2Id");
            return (Criteria) this;
        }

        public Criteria andCoach2IdNotEqualTo(String value) {
            addCriterion("coach2_id <>", value, "coach2Id");
            return (Criteria) this;
        }

        public Criteria andCoach2IdGreaterThan(String value) {
            addCriterion("coach2_id >", value, "coach2Id");
            return (Criteria) this;
        }

        public Criteria andCoach2IdGreaterThanOrEqualTo(String value) {
            addCriterion("coach2_id >=", value, "coach2Id");
            return (Criteria) this;
        }

        public Criteria andCoach2IdLessThan(String value) {
            addCriterion("coach2_id <", value, "coach2Id");
            return (Criteria) this;
        }

        public Criteria andCoach2IdLessThanOrEqualTo(String value) {
            addCriterion("coach2_id <=", value, "coach2Id");
            return (Criteria) this;
        }

        public Criteria andCoach2IdLike(String value) {
            addCriterion("coach2_id like", value, "coach2Id");
            return (Criteria) this;
        }

        public Criteria andCoach2IdNotLike(String value) {
            addCriterion("coach2_id not like", value, "coach2Id");
            return (Criteria) this;
        }

        public Criteria andCoach2IdIn(List<String> values) {
            addCriterion("coach2_id in", values, "coach2Id");
            return (Criteria) this;
        }

        public Criteria andCoach2IdNotIn(List<String> values) {
            addCriterion("coach2_id not in", values, "coach2Id");
            return (Criteria) this;
        }

        public Criteria andCoach2IdBetween(String value1, String value2) {
            addCriterion("coach2_id between", value1, value2, "coach2Id");
            return (Criteria) this;
        }

        public Criteria andCoach2IdNotBetween(String value1, String value2) {
            addCriterion("coach2_id not between", value1, value2, "coach2Id");
            return (Criteria) this;
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

        public Criteria andTypeIsNull() {
            addCriterion("type is null");
            return (Criteria) this;
        }

        public Criteria andTypeIsNotNull() {
            addCriterion("type is not null");
            return (Criteria) this;
        }

        public Criteria andTypeEqualTo(Integer value) {
            addCriterion("type =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(Integer value) {
            addCriterion("type <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(Integer value) {
            addCriterion("type >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("type >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(Integer value) {
            addCriterion("type <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(Integer value) {
            addCriterion("type <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<Integer> values) {
            addCriterion("type in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<Integer> values) {
            addCriterion("type not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(Integer value1, Integer value2) {
            addCriterion("type between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("type not between", value1, value2, "type");
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

        public Criteria andReasonIsNull() {
            addCriterion("reason is null");
            return (Criteria) this;
        }

        public Criteria andReasonIsNotNull() {
            addCriterion("reason is not null");
            return (Criteria) this;
        }

        public Criteria andReasonEqualTo(String value) {
            addCriterion("reason =", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonNotEqualTo(String value) {
            addCriterion("reason <>", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonGreaterThan(String value) {
            addCriterion("reason >", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonGreaterThanOrEqualTo(String value) {
            addCriterion("reason >=", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonLessThan(String value) {
            addCriterion("reason <", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonLessThanOrEqualTo(String value) {
            addCriterion("reason <=", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonLike(String value) {
            addCriterion("reason like", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonNotLike(String value) {
            addCriterion("reason not like", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonIn(List<String> values) {
            addCriterion("reason in", values, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonNotIn(List<String> values) {
            addCriterion("reason not in", values, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonBetween(String value1, String value2) {
            addCriterion("reason between", value1, value2, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonNotBetween(String value1, String value2) {
            addCriterion("reason not between", value1, value2, "reason");
            return (Criteria) this;
        }

        public Criteria andNeedTeamCertificateIsNull() {
            addCriterion("need_team_certificate is null");
            return (Criteria) this;
        }

        public Criteria andNeedTeamCertificateIsNotNull() {
            addCriterion("need_team_certificate is not null");
            return (Criteria) this;
        }

        public Criteria andNeedTeamCertificateEqualTo(Integer value) {
            addCriterion("need_team_certificate =", value, "needTeamCertificate");
            return (Criteria) this;
        }

        public Criteria andNeedTeamCertificateNotEqualTo(Integer value) {
            addCriterion("need_team_certificate <>", value, "needTeamCertificate");
            return (Criteria) this;
        }

        public Criteria andNeedTeamCertificateGreaterThan(Integer value) {
            addCriterion("need_team_certificate >", value, "needTeamCertificate");
            return (Criteria) this;
        }

        public Criteria andNeedTeamCertificateGreaterThanOrEqualTo(Integer value) {
            addCriterion("need_team_certificate >=", value, "needTeamCertificate");
            return (Criteria) this;
        }

        public Criteria andNeedTeamCertificateLessThan(Integer value) {
            addCriterion("need_team_certificate <", value, "needTeamCertificate");
            return (Criteria) this;
        }

        public Criteria andNeedTeamCertificateLessThanOrEqualTo(Integer value) {
            addCriterion("need_team_certificate <=", value, "needTeamCertificate");
            return (Criteria) this;
        }

        public Criteria andNeedTeamCertificateIn(List<Integer> values) {
            addCriterion("need_team_certificate in", values, "needTeamCertificate");
            return (Criteria) this;
        }

        public Criteria andNeedTeamCertificateNotIn(List<Integer> values) {
            addCriterion("need_team_certificate not in", values, "needTeamCertificate");
            return (Criteria) this;
        }

        public Criteria andNeedTeamCertificateBetween(Integer value1, Integer value2) {
            addCriterion("need_team_certificate between", value1, value2, "needTeamCertificate");
            return (Criteria) this;
        }

        public Criteria andNeedTeamCertificateNotBetween(Integer value1, Integer value2) {
            addCriterion("need_team_certificate not between", value1, value2, "needTeamCertificate");
            return (Criteria) this;
        }

        public Criteria andCompetitionPositionIsNull() {
            addCriterion("competition_position is null");
            return (Criteria) this;
        }

        public Criteria andCompetitionPositionIsNotNull() {
            addCriterion("competition_position is not null");
            return (Criteria) this;
        }

        public Criteria andCompetitionPositionEqualTo(String value) {
            addCriterion("competition_position =", value, "competitionPosition");
            return (Criteria) this;
        }

        public Criteria andCompetitionPositionNotEqualTo(String value) {
            addCriterion("competition_position <>", value, "competitionPosition");
            return (Criteria) this;
        }

        public Criteria andCompetitionPositionGreaterThan(String value) {
            addCriterion("competition_position >", value, "competitionPosition");
            return (Criteria) this;
        }

        public Criteria andCompetitionPositionGreaterThanOrEqualTo(String value) {
            addCriterion("competition_position >=", value, "competitionPosition");
            return (Criteria) this;
        }

        public Criteria andCompetitionPositionLessThan(String value) {
            addCriterion("competition_position <", value, "competitionPosition");
            return (Criteria) this;
        }

        public Criteria andCompetitionPositionLessThanOrEqualTo(String value) {
            addCriterion("competition_position <=", value, "competitionPosition");
            return (Criteria) this;
        }

        public Criteria andCompetitionPositionLike(String value) {
            addCriterion("competition_position like", value, "competitionPosition");
            return (Criteria) this;
        }

        public Criteria andCompetitionPositionNotLike(String value) {
            addCriterion("competition_position not like", value, "competitionPosition");
            return (Criteria) this;
        }

        public Criteria andCompetitionPositionIn(List<String> values) {
            addCriterion("competition_position in", values, "competitionPosition");
            return (Criteria) this;
        }

        public Criteria andCompetitionPositionNotIn(List<String> values) {
            addCriterion("competition_position not in", values, "competitionPosition");
            return (Criteria) this;
        }

        public Criteria andCompetitionPositionBetween(String value1, String value2) {
            addCriterion("competition_position between", value1, value2, "competitionPosition");
            return (Criteria) this;
        }

        public Criteria andCompetitionPositionNotBetween(String value1, String value2) {
            addCriterion("competition_position not between", value1, value2, "competitionPosition");
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