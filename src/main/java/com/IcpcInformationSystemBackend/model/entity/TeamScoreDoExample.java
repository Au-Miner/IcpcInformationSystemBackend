package com.IcpcInformationSystemBackend.model.entity;

import java.util.ArrayList;
import java.util.List;

public class TeamScoreDoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TeamScoreDoExample() {
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

        public Criteria andTeamCertificateIsNull() {
            addCriterion("team_certificate is null");
            return (Criteria) this;
        }

        public Criteria andTeamCertificateIsNotNull() {
            addCriterion("team_certificate is not null");
            return (Criteria) this;
        }

        public Criteria andTeamCertificateEqualTo(String value) {
            addCriterion("team_certificate =", value, "teamCertificate");
            return (Criteria) this;
        }

        public Criteria andTeamCertificateNotEqualTo(String value) {
            addCriterion("team_certificate <>", value, "teamCertificate");
            return (Criteria) this;
        }

        public Criteria andTeamCertificateGreaterThan(String value) {
            addCriterion("team_certificate >", value, "teamCertificate");
            return (Criteria) this;
        }

        public Criteria andTeamCertificateGreaterThanOrEqualTo(String value) {
            addCriterion("team_certificate >=", value, "teamCertificate");
            return (Criteria) this;
        }

        public Criteria andTeamCertificateLessThan(String value) {
            addCriterion("team_certificate <", value, "teamCertificate");
            return (Criteria) this;
        }

        public Criteria andTeamCertificateLessThanOrEqualTo(String value) {
            addCriterion("team_certificate <=", value, "teamCertificate");
            return (Criteria) this;
        }

        public Criteria andTeamCertificateLike(String value) {
            addCriterion("team_certificate like", value, "teamCertificate");
            return (Criteria) this;
        }

        public Criteria andTeamCertificateNotLike(String value) {
            addCriterion("team_certificate not like", value, "teamCertificate");
            return (Criteria) this;
        }

        public Criteria andTeamCertificateIn(List<String> values) {
            addCriterion("team_certificate in", values, "teamCertificate");
            return (Criteria) this;
        }

        public Criteria andTeamCertificateNotIn(List<String> values) {
            addCriterion("team_certificate not in", values, "teamCertificate");
            return (Criteria) this;
        }

        public Criteria andTeamCertificateBetween(String value1, String value2) {
            addCriterion("team_certificate between", value1, value2, "teamCertificate");
            return (Criteria) this;
        }

        public Criteria andTeamCertificateNotBetween(String value1, String value2) {
            addCriterion("team_certificate not between", value1, value2, "teamCertificate");
            return (Criteria) this;
        }

        public Criteria andRnkIsNull() {
            addCriterion("rnk is null");
            return (Criteria) this;
        }

        public Criteria andRnkIsNotNull() {
            addCriterion("rnk is not null");
            return (Criteria) this;
        }

        public Criteria andRnkEqualTo(Integer value) {
            addCriterion("rnk =", value, "rnk");
            return (Criteria) this;
        }

        public Criteria andRnkNotEqualTo(Integer value) {
            addCriterion("rnk <>", value, "rnk");
            return (Criteria) this;
        }

        public Criteria andRnkGreaterThan(Integer value) {
            addCriterion("rnk >", value, "rnk");
            return (Criteria) this;
        }

        public Criteria andRnkGreaterThanOrEqualTo(Integer value) {
            addCriterion("rnk >=", value, "rnk");
            return (Criteria) this;
        }

        public Criteria andRnkLessThan(Integer value) {
            addCriterion("rnk <", value, "rnk");
            return (Criteria) this;
        }

        public Criteria andRnkLessThanOrEqualTo(Integer value) {
            addCriterion("rnk <=", value, "rnk");
            return (Criteria) this;
        }

        public Criteria andRnkIn(List<Integer> values) {
            addCriterion("rnk in", values, "rnk");
            return (Criteria) this;
        }

        public Criteria andRnkNotIn(List<Integer> values) {
            addCriterion("rnk not in", values, "rnk");
            return (Criteria) this;
        }

        public Criteria andRnkBetween(Integer value1, Integer value2) {
            addCriterion("rnk between", value1, value2, "rnk");
            return (Criteria) this;
        }

        public Criteria andRnkNotBetween(Integer value1, Integer value2) {
            addCriterion("rnk not between", value1, value2, "rnk");
            return (Criteria) this;
        }

        public Criteria andPhotosIsNull() {
            addCriterion("photos is null");
            return (Criteria) this;
        }

        public Criteria andPhotosIsNotNull() {
            addCriterion("photos is not null");
            return (Criteria) this;
        }

        public Criteria andPhotosEqualTo(String value) {
            addCriterion("photos =", value, "photos");
            return (Criteria) this;
        }

        public Criteria andPhotosNotEqualTo(String value) {
            addCriterion("photos <>", value, "photos");
            return (Criteria) this;
        }

        public Criteria andPhotosGreaterThan(String value) {
            addCriterion("photos >", value, "photos");
            return (Criteria) this;
        }

        public Criteria andPhotosGreaterThanOrEqualTo(String value) {
            addCriterion("photos >=", value, "photos");
            return (Criteria) this;
        }

        public Criteria andPhotosLessThan(String value) {
            addCriterion("photos <", value, "photos");
            return (Criteria) this;
        }

        public Criteria andPhotosLessThanOrEqualTo(String value) {
            addCriterion("photos <=", value, "photos");
            return (Criteria) this;
        }

        public Criteria andPhotosLike(String value) {
            addCriterion("photos like", value, "photos");
            return (Criteria) this;
        }

        public Criteria andPhotosNotLike(String value) {
            addCriterion("photos not like", value, "photos");
            return (Criteria) this;
        }

        public Criteria andPhotosIn(List<String> values) {
            addCriterion("photos in", values, "photos");
            return (Criteria) this;
        }

        public Criteria andPhotosNotIn(List<String> values) {
            addCriterion("photos not in", values, "photos");
            return (Criteria) this;
        }

        public Criteria andPhotosBetween(String value1, String value2) {
            addCriterion("photos between", value1, value2, "photos");
            return (Criteria) this;
        }

        public Criteria andPhotosNotBetween(String value1, String value2) {
            addCriterion("photos not between", value1, value2, "photos");
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