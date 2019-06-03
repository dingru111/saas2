package com.gta.train.platform.common.base.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

/**
 * @author huan.xu
 * @version 1.0
 * @className ExamDto
 * @description
 * @date 2018-12-11 13:55
 */
public class ExamDto implements Serializable {
    private static final long serialVersionUID = -359701505589055597L;
    //开始时间
    private String userId;//用户id
    private Date startTime; //开始时间
    private String assessmentId; //考试id
    private String achievementType;//1 练习 0考核
    private String caseId; //案例id
    private String classId; //班级id
    private String courseId; //课程id
    private Map<String,Integer> result;//成绩
    private String totalScore;//成绩总分
    private int duration;//时长

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public String getAssessmentId() {
        return assessmentId;
    }

    public void setAssessmentId(String assessmentId) {
        this.assessmentId = assessmentId;
    }

    public String getAchievementType() {
        return achievementType;
    }

    public void setAchievementType(String achievementType) {
        this.achievementType = achievementType;
    }

    public String getCaseId() {
        return caseId;
    }

    public void setCaseId(String caseId) {
        this.caseId = caseId;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public Map<String, Integer> getResult() {
        return result;
    }

    public void setResult(Map<String, Integer> result) {
        this.result = result;
    }

    public String getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(String totalScore) {
        this.totalScore = totalScore;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}