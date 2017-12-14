package com.tf.base.socialorg.domain;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Table(name = "social_party_org_building")
public class SocialPartyOrgBuilding {
	/**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
    /**
     * 党务工作者是否参加1次以上培训
     */
    @Column(name = "is_party_member_train")
    private String isPartyMemberTrain;
    /**
     * 近三年新发展党员数
     */
    @Column(name = "three_years_member")
    private Integer threeYearsMember;
    /**
     * 近三年处置不合格党员数
     */
    @Column(name = "three_years_unqualifieds")
    private Integer threeYearsUnqualifieds;
    /**
     * 是否对党员轮训一遍
     */
    @Column(name = "is_training_in_rotation")
    private String isTrainingInRotation;
    /**
     * 党建工作经费总额
     */
    @Column(name = "total_pay_dues")
    private Integer totalPayDues;
    /**
     * 市区财政支出数额（元）
     */
    @Column(name = "city_funds")
    private BigDecimal cityFunds;
    /**
     * 区管党费支持数额（元）
     */
    @Column(name = "district_funds ")
    private BigDecimal districtFunds ;
    /**
     * 社会组织管理经费支持数额（元）
     */
    @Column(name = "social_org_funds")
    private BigDecimal socialOrgFunds;
    /**
     * 其他（说明）
     */
    @Column(name = "other_content")
    private String otherContent;
    /**
     * 每季度召开党员大会（次）
     */
    @Column(name = "quarter_party_meeting_times")
    private Integer quarterPartyMeetingTimes;
    /**
     * 每月召开支部委员会（次）
     */
    @Column(name = "month_party_meeting_times")
    private Integer monthPartyMeetingTimes;
    /**
     * 是否每月召开1~2次党小组会
     */
    @Column(name = "party_meeting_month")
    private String partyMeetingMonth;
    /**
     * 组织党员听党课（次）
     */
    @Column(name = "listen_party_class")
    private Integer listenPartyClass;
    /**
     * 召开组织生活会的次数
     */
    @Column(name = "life_meeting_times")
    private Integer lifeMeetingTimes;
    /**
     * 开展民主评议党员的党支部数
     */
    @Column(name = "develop_party_branch_no")
    private Integer developPartyBranchNo;
    /**
     * 上年度党员缴纳党费总额
     */
    @Column(name = "year_total_pay_dues")
    private BigDecimal yearTotalPayDues;
    /**
     * 年检结果
     */
    @Column(name = "annual_survey")
    private String annualSurvey;
    /**
     * 党建工作经费是否列入学校年度经费预算
     */
    @Column(name = "is_into_manage")
    private String isIntoManage;
    /**
     * 社会组织党组织信息表id
     */
    @Column(name = "social_party_org_id")
    private Integer socialPartyOrgId;

    private String year;

    private String creater;
    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    private String status;
    
    @Transient
    private String partyOrgName;
    @Transient
    private String partyOrgId;
    @Transient
    private String createOrg;
    @Transient
    private Integer reportHigher;
    @Transient
    private String annualSurveyTxt;
    @Transient
    private String statusTxt;
    

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", threeYearsMember=").append(threeYearsMember);
        sb.append(", threeYearsUnqualifieds=").append(threeYearsUnqualifieds);
        sb.append(", isTrainingInRotation=").append(isTrainingInRotation);
        sb.append(", isPartyMemberTrain=").append(isPartyMemberTrain);
        sb.append(", isIntoManage=").append(isIntoManage);
        sb.append(", year=").append(year);
        sb.append(", creater=").append(creater);
        sb.append(", createTime=").append(createTime);
        sb.append(", status=").append(status);
        sb.append("]");
        return sb.toString();
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getThreeYearsMember() {
        return threeYearsMember;
    }

    public void setThreeYearsMember(Integer threeYearsMember) {
        this.threeYearsMember = threeYearsMember;
    }

    public Integer getThreeYearsUnqualifieds() {
        return threeYearsUnqualifieds;
    }

    public void setThreeYearsUnqualifieds(Integer threeYearsUnqualifieds) {
        this.threeYearsUnqualifieds = threeYearsUnqualifieds;
    }

    public String getIsTrainingInRotation() {
        return isTrainingInRotation;
    }

    public void setIsTrainingInRotation(String isTrainingInRotation) {
        this.isTrainingInRotation = isTrainingInRotation == null ? null : isTrainingInRotation.trim();
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year == null ? null : year.trim();
    }

    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater == null ? null : creater.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

	public String getPartyOrgName() {
		return partyOrgName;
	}

	public void setPartyOrgName(String partyOrgName) {
		this.partyOrgName = partyOrgName;
	}

	public String getIsPartyMemberTrain() {
		return isPartyMemberTrain;
	}

	public void setIsPartyMemberTrain(String isPartyMemberTrain) {
		this.isPartyMemberTrain = isPartyMemberTrain;
	}

	public Integer getTotalPayDues() {
		return totalPayDues;
	}

	public void setTotalPayDues(Integer totalPayDues) {
		this.totalPayDues = totalPayDues;
	}

	public BigDecimal getCityFunds() {
		return cityFunds;
	}

	public void setCityFunds(BigDecimal cityFunds) {
		this.cityFunds = cityFunds;
	}

	public BigDecimal getDistrictFunds() {
		return districtFunds;
	}

	public void setDistrictFunds(BigDecimal districtFunds) {
		this.districtFunds = districtFunds;
	}

	public BigDecimal getSocialOrgFunds() {
		return socialOrgFunds;
	}

	public void setSocialOrgFunds(BigDecimal socialOrgFunds) {
		this.socialOrgFunds = socialOrgFunds;
	}

	public String getOtherContent() {
		return otherContent;
	}

	public void setOtherContent(String otherContent) {
		this.otherContent = otherContent;
	}

	public Integer getQuarterPartyMeetingTimes() {
		return quarterPartyMeetingTimes;
	}

	public void setQuarterPartyMeetingTimes(Integer quarterPartyMeetingTimes) {
		this.quarterPartyMeetingTimes = quarterPartyMeetingTimes;
	}

	public Integer getMonthPartyMeetingTimes() {
		return monthPartyMeetingTimes;
	}

	public void setMonthPartyMeetingTimes(Integer monthPartyMeetingTimes) {
		this.monthPartyMeetingTimes = monthPartyMeetingTimes;
	}

	public String getPartyMeetingMonth() {
		return partyMeetingMonth;
	}

	public void setPartyMeetingMonth(String partyMeetingMonth) {
		this.partyMeetingMonth = partyMeetingMonth;
	}

	public Integer getListenPartyClass() {
		return listenPartyClass;
	}

	public void setListenPartyClass(Integer listenPartyClass) {
		this.listenPartyClass = listenPartyClass;
	}

	public Integer getLifeMeetingTimes() {
		return lifeMeetingTimes;
	}

	public void setLifeMeetingTimes(Integer lifeMeetingTimes) {
		this.lifeMeetingTimes = lifeMeetingTimes;
	}

	public Integer getDevelopPartyBranchNo() {
		return developPartyBranchNo;
	}

	public void setDevelopPartyBranchNo(Integer developPartyBranchNo) {
		this.developPartyBranchNo = developPartyBranchNo;
	}

	public BigDecimal getYearTotalPayDues() {
		return yearTotalPayDues;
	}

	public void setYearTotalPayDues(BigDecimal yearTotalPayDues) {
		this.yearTotalPayDues = yearTotalPayDues;
	}

	public String getAnnualSurvey() {
		return annualSurvey;
	}

	public void setAnnualSurvey(String annualSurvey) {
		this.annualSurvey = annualSurvey;
	}

	public String getIsIntoManage() {
		return isIntoManage;
	}

	public void setIsIntoManage(String isIntoManage) {
		this.isIntoManage = isIntoManage;
	}

	public Integer getSocialPartyOrgId() {
		return socialPartyOrgId;
	}

	public void setSocialPartyOrgId(Integer socialPartyOrgId) {
		this.socialPartyOrgId = socialPartyOrgId;
	}

	public String getPartyOrgId() {
		return partyOrgId;
	}

	public void setPartyOrgId(String partyOrgId) {
		this.partyOrgId = partyOrgId;
	}

	public String getCreateOrg() {
		return createOrg;
	}

	public void setCreateOrg(String createOrg) {
		this.createOrg = createOrg;
	}

	public Integer getReportHigher() {
		return reportHigher;
	}

	public void setReportHigher(Integer reportHigher) {
		this.reportHigher = reportHigher;
	}

	public String getAnnualSurveyTxt() {
		return annualSurveyTxt;
	}

	public void setAnnualSurveyTxt(String annualSurveyTxt) {
		this.annualSurveyTxt = annualSurveyTxt;
	}

	public String getStatusTxt() {
		return statusTxt;
	}

	public void setStatusTxt(String statusTxt) {
		this.statusTxt = statusTxt;
	}

	
}