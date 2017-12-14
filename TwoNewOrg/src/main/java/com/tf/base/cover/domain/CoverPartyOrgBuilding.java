package com.tf.base.cover.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Table(name = "cover_org_party_building")
public class CoverPartyOrgBuilding {
	/**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
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
     * 党员是否按时足额主动交纳党费
     */
    @Column(name = "is_party_member_train")
    private String isPartyMemberTrain;
    /**
     * 党建工作经费是否纳入企业管理费年度预算
     */
    @Column(name = "is_into_manage")
    private String isIntoManage;
    /**
     * 是否按规定开展三会一课
     */
    @Column(name = "is_develop_listen")
    private String isDevelopListen;
    /**
     * 是否按规定每年开展民主评议党员
     */
    @Column(name = "is_develop_discussions")
    private String isDevelopDiscussions;
    /**
     * 是否按规定每年开展党员党性分析
     */
    @Column(name = "is_develop_analysis")
    private String isDevelopAnalysis;
    /**
     * 党组织是否按规定进行换届
     */
    @Column(name = "is_change_everyyear")
    private String isChangeEveryyear;
    /**
     * 是否倒排相对后进基层党组织
     */
    @Column(name = "is_backward_party")
    private String isBackwardParty;
    /**
     * 是否完成整顿相对后进基层党组织
     */
    @Column(name = "is_rectify_party")
    private String isRectifyParty;
    /**
     * 整顿报告
     */
    @Column(name = "rectify_atachement_id")
    private Integer rectifyAtachementId;
    /**
     * 书记总学时
     */
    @Column(name = "secretary_total_time")
    private Integer secretaryTotalTime;
    /**
     * 党员总学时
     */
    @Column(name = "party_total_time")
    private Integer partyTotalTime;
    /**
     * 党员平均学时
     */
    @Column(name = "party_avg_time")
    private Integer partyAvgTime;
    /**
     * 非公有制经济组织id
     */
    @Column(name = "cover_party_org_id")
    private Integer coverPartyOrgId;

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
    private String isTrainingInRotationTxt;
    @Transient
    private String isPartyMemberTrainTxt;
    @Transient
    private String isIntoManageTxt;
    @Transient
    private String isDevelopListenTxt;
    @Transient
    private String isDevelopDiscussionsTxt;
    @Transient
    private String isChangeEveryyearTxt;
    @Transient
    private String isBackwardPartyTxt;
    @Transient
    private String isRectifyPartyTxt;
    @Transient
    private String createOrg;
    @Transient
    private String rectifyAtachementIdTxt;
    @Transient
    private Integer reportHigher;
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
        sb.append(", isDevelopListen=").append(isDevelopListen);
        sb.append(", isDevelopDiscussions=").append(isDevelopDiscussions);
        sb.append(", isDevelopAnalysis=").append(isDevelopAnalysis);
        sb.append(", isChangeEveryyear=").append(isChangeEveryyear);
        sb.append(", isBackwardParty=").append(isBackwardParty);
        sb.append(", isRectifyParty=").append(isRectifyParty);
        sb.append(", rectifyAtachementId=").append(rectifyAtachementId);
        sb.append(", secretaryTotalTime=").append(secretaryTotalTime);
        sb.append(", partyTotalTime=").append(partyTotalTime);
        sb.append(", partyAvgTime=").append(partyAvgTime);
        sb.append(", coverPartyOrgId=").append(coverPartyOrgId);
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

    public String getIsPartyMemberTrain() {
        return isPartyMemberTrain;
    }

    public void setIsPartyMemberTrain(String isPartyMemberTrain) {
        this.isPartyMemberTrain = isPartyMemberTrain == null ? null : isPartyMemberTrain.trim();
    }

    public String getIsIntoManage() {
        return isIntoManage;
    }

    public void setIsIntoManage(String isIntoManage) {
        this.isIntoManage = isIntoManage == null ? null : isIntoManage.trim();
    }

    public String getIsDevelopListen() {
        return isDevelopListen;
    }

    public void setIsDevelopListen(String isDevelopListen) {
        this.isDevelopListen = isDevelopListen == null ? null : isDevelopListen.trim();
    }

    public String getIsDevelopDiscussions() {
        return isDevelopDiscussions;
    }

    public void setIsDevelopDiscussions(String isDevelopDiscussions) {
        this.isDevelopDiscussions = isDevelopDiscussions == null ? null : isDevelopDiscussions.trim();
    }

    public String getIsDevelopAnalysis() {
        return isDevelopAnalysis;
    }

    public void setIsDevelopAnalysis(String isDevelopAnalysis) {
        this.isDevelopAnalysis = isDevelopAnalysis == null ? null : isDevelopAnalysis.trim();
    }

    public String getIsChangeEveryyear() {
        return isChangeEveryyear;
    }

    public void setIsChangeEveryyear(String isChangeEveryyear) {
        this.isChangeEveryyear = isChangeEveryyear == null ? null : isChangeEveryyear.trim();
    }

    public String getIsBackwardParty() {
        return isBackwardParty;
    }

    public void setIsBackwardParty(String isBackwardParty) {
        this.isBackwardParty = isBackwardParty == null ? null : isBackwardParty.trim();
    }

    public String getIsRectifyParty() {
        return isRectifyParty;
    }

    public void setIsRectifyParty(String isRectifyParty) {
        this.isRectifyParty = isRectifyParty == null ? null : isRectifyParty.trim();
    }

    public Integer getRectifyAtachementId() {
        return rectifyAtachementId;
    }

    public void setRectifyAtachementId(Integer rectifyAtachementId) {
        this.rectifyAtachementId = rectifyAtachementId;
    }

    public Integer getSecretaryTotalTime() {
        return secretaryTotalTime;
    }

    public void setSecretaryTotalTime(Integer secretaryTotalTime) {
        this.secretaryTotalTime = secretaryTotalTime;
    }

    public Integer getPartyTotalTime() {
        return partyTotalTime;
    }

    public void setPartyTotalTime(Integer partyTotalTime) {
        this.partyTotalTime = partyTotalTime;
    }

    public Integer getPartyAvgTime() {
        return partyAvgTime;
    }

    public void setPartyAvgTime(Integer partyAvgTime) {
        this.partyAvgTime = partyAvgTime;
    }

    public Integer getCoverPartyOrgId() {
		return coverPartyOrgId;
	}

	public void setCoverPartyOrgId(Integer coverPartyOrgId) {
		this.coverPartyOrgId = coverPartyOrgId;
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

	public String getCreateOrg() {
		return createOrg;
	}

	public void setCreateOrg(String createOrg) {
		this.createOrg = createOrg;
	}

	public String getIsTrainingInRotationTxt() {
		return isTrainingInRotationTxt;
	}

	public void setIsTrainingInRotationTxt(String isTrainingInRotationTxt) {
		this.isTrainingInRotationTxt = isTrainingInRotationTxt;
	}

	public String getIsPartyMemberTrainTxt() {
		return isPartyMemberTrainTxt;
	}

	public void setIsPartyMemberTrainTxt(String isPartyMemberTrainTxt) {
		this.isPartyMemberTrainTxt = isPartyMemberTrainTxt;
	}

	public String getIsIntoManageTxt() {
		return isIntoManageTxt;
	}

	public void setIsIntoManageTxt(String isIntoManageTxt) {
		this.isIntoManageTxt = isIntoManageTxt;
	}

	public String getIsDevelopListenTxt() {
		return isDevelopListenTxt;
	}

	public void setIsDevelopListenTxt(String isDevelopListenTxt) {
		this.isDevelopListenTxt = isDevelopListenTxt;
	}

	public String getIsDevelopDiscussionsTxt() {
		return isDevelopDiscussionsTxt;
	}

	public void setIsDevelopDiscussionsTxt(String isDevelopDiscussionsTxt) {
		this.isDevelopDiscussionsTxt = isDevelopDiscussionsTxt;
	}

	public String getIsChangeEveryyearTxt() {
		return isChangeEveryyearTxt;
	}

	public void setIsChangeEveryyearTxt(String isChangeEveryyearTxt) {
		this.isChangeEveryyearTxt = isChangeEveryyearTxt;
	}

	public String getIsBackwardPartyTxt() {
		return isBackwardPartyTxt;
	}

	public void setIsBackwardPartyTxt(String isBackwardPartyTxt) {
		this.isBackwardPartyTxt = isBackwardPartyTxt;
	}

	public String getIsRectifyPartyTxt() {
		return isRectifyPartyTxt;
	}

	public void setIsRectifyPartyTxt(String isRectifyPartyTxt) {
		this.isRectifyPartyTxt = isRectifyPartyTxt;
	}

	public String getRectifyAtachementIdTxt() {
		return rectifyAtachementIdTxt;
	}

	public void setRectifyAtachementIdTxt(String rectifyAtachementIdTxt) {
		this.rectifyAtachementIdTxt = rectifyAtachementIdTxt;
	}

	public Integer getReportHigher() {
		return reportHigher;
	}

	public void setReportHigher(Integer reportHigher) {
		this.reportHigher = reportHigher;
	}

	public String getStatusTxt() {
		return statusTxt;
	}

	public void setStatusTxt(String statusTxt) {
		this.statusTxt = statusTxt;
	}

	
}