package com.tf.base.unpublic.domain;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Table(name = "unpublic_party_org_info")
public class UnpublicPartyOrgInfo {
	
	/**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 是否成立党组织
     */
    @Column(name = "is_set_up_party_org")
    private String isSetUpPartyOrg;
    /**
     * 未建立党组织原因
     */
    @Column(name = "absence_party_org_reasion")
    private String absencePartyOrgReasion;
    /**
     * 党组织组建形式
     */
    @Column(name = "party_org_form")
    private String partyOrgForm;

    /**
     * 党组织类别
     */
    @Column(name = "party_org_type")
    private String partyOrgType;
    /**
     * 是否选派党建工作指导员或联络员
     */
    @Column(name = "is_instructor")
    private String isInstructor;
    /**
     * 人员姓名
     */
    @Column(name = "instructor_name")
    private String instructorName;
    /**
     * 单位
     */
    @Column(name = "instructor_unit")
    private String instructorUnit;
    /**
     * 职务
     */
    @Column(name = "instructor_job")
    private String instructorJob;
    /**
     * 党组织成立时间
     */
    @Column(name = "party_org_time")
    private Date partyOrgTime;
    /**
     * 党组织成立相关附件
     */
    @Column(name = "party_org_attachment")
    private String partyOrgAttachment;
    /**
     * 党组织名称
     */
    @Column(name = "party_org_name")
    private String partyOrgName;
    /**
     * 党组织联系电话
     */
    @Column(name = "party_org_tel")
    private String partyOrgTel;
    /**
     * 隶属党组织名称
     */
    @Column(name = "subjection_party_name")
    private String subjectionPartyName;
    /**
     * 隶属党组织id
     */
    @Column(name = "subjection_party_id")
    private Integer subjectionPartyId;
    /**
     * 书记姓名
     */
    @Column(name = "secretary_name")
    private String secretaryName;
    /**
     * 书记出生日期
     */
    @Column(name = "secretary_birthday")
    private Date secretaryBirthday;
    /**
     * 书记学历
     */
    @Column(name = "secretary_education")
    private String secretaryEducation;
    /**
     * 书记来源
     */
    @Column(name = "secretary_source")
    private String secretarySource;
    /**
     * 书记所在单位
     */
    @Column(name = "secretary_company")
    private String secretaryCompany;
   
    /**
     * 其他党务工作者兼职人数
     */
    @Column(name = "part_time_workers")
    private Integer partTimeWorkers;
    /**
     * 其他党务工作者全职人数
     */
    @Column(name = "full_time_workers")
    private Integer fullTimeWorkers;
    /**
     * 是否有单独活动场所
     */
    @Column(name = "is_oneself")
    private String isOneself;
    /**
     * 所在单位
     */
    @Column(name = "belong_unit")
    private String belongUnit;
    /**
     * 使用面积
     */
    @Column(name = "stage_area")
    private String stageArea;
    /**
     * 其他
     */
    @Column(name = "other_info")
    private String otherInfo;
    /**
     * 创建人
     */
    private String creater;
    /**
     * 创建单位
     */
    @Column(name = "create_org")
    private String createOrg;
    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;
    /**
     * 状态
     */
    private String status;
    
    @Transient
    private String orgId;
    @Transient
    private String orgName;
    @Transient
    private String partyInNum;
    @Transient
    private String partyNotInNum;
    @Transient
    private String partyOrgFormTxt;
    @Transient
    private String partyOrgTypeTxt;
    @Transient
    private String statusTxt;
    @Transient
    private Integer reportHigher;//是否上报
    @Transient
    private String filepartyOrgAttachment;
    @Transient
    private String partyOrgTimeTxt;
    @Transient
    private String secretaryBirthdayTxt;
    @Transient
    private String deputySecretaryBirthdayTxt;
    @Transient
    private String instructorUnitTxt;
    @Transient
    private String secretaryCompanyTxt;
    @Transient
    private String belongUnitTxt;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", isSetUpPartyOrg=").append(isSetUpPartyOrg);
        sb.append(", absencePartyOrgReasion=").append(absencePartyOrgReasion);
        sb.append(", partyOrgForm=").append(partyOrgForm);
        sb.append(", partyOrgType=").append(partyOrgType);
        sb.append(", isInstructor=").append(isInstructor);
        sb.append(", instructorName=").append(instructorName);
        sb.append(", instructorUnit=").append(instructorUnit);
        sb.append(", instructorJob=").append(instructorJob);
        sb.append(", partyOrgTime=").append(partyOrgTime);
        sb.append(", partyOrgAttachment=").append(partyOrgAttachment);
        sb.append(", partyOrgName=").append(partyOrgName);
        sb.append(", partyOrgTel=").append(partyOrgTel);
        sb.append(", subjectionPartyName=").append(subjectionPartyName);
        sb.append(", subjectionPartyId=").append(subjectionPartyId);
        sb.append(", secretaryName=").append(secretaryName);
        sb.append(", secretaryBirthday=").append(secretaryBirthday);
        sb.append(", secretaryEducation=").append(secretaryEducation);
        sb.append(", secretarySource=").append(secretarySource);
        sb.append(", secretaryCompany=").append(secretaryCompany);
        sb.append(", partTimeWorkers=").append(partTimeWorkers);
        sb.append(", fullTimeWorkers=").append(fullTimeWorkers);
        sb.append(", isOneself=").append(isOneself);
        sb.append(", belongUnit=").append(belongUnit);
        sb.append(", stageArea=").append(stageArea);
        sb.append(", otherInfo=").append(otherInfo);
        sb.append(", creater=").append(creater);
        sb.append(", createOrg=").append(createOrg);
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

    public String getIsSetUpPartyOrg() {
        return isSetUpPartyOrg;
    }

    public void setIsSetUpPartyOrg(String isSetUpPartyOrg) {
        this.isSetUpPartyOrg = isSetUpPartyOrg == null ? null : isSetUpPartyOrg.trim();
    }

    public String getAbsencePartyOrgReasion() {
        return absencePartyOrgReasion;
    }

    public void setAbsencePartyOrgReasion(String absencePartyOrgReasion) {
        this.absencePartyOrgReasion = absencePartyOrgReasion == null ? null : absencePartyOrgReasion.trim();
    }

    public String getPartyOrgForm() {
        return partyOrgForm;
    }

    public void setPartyOrgForm(String partyOrgForm) {
        this.partyOrgForm = partyOrgForm == null ? null : partyOrgForm.trim();
    }

    public String getPartyOrgType() {
        return partyOrgType;
    }

    public void setPartyOrgType(String partyOrgType) {
        this.partyOrgType = partyOrgType == null ? null : partyOrgType.trim();
    }

    public String getIsInstructor() {
        return isInstructor;
    }

    public void setIsInstructor(String isInstructor) {
        this.isInstructor = isInstructor == null ? null : isInstructor.trim();
    }

    public String getInstructorName() {
        return instructorName;
    }

    public void setInstructorName(String instructorName) {
        this.instructorName = instructorName == null ? null : instructorName.trim();
    }

    public String getInstructorUnit() {
        return instructorUnit;
    }

    public void setInstructorUnit(String instructorUnit) {
        this.instructorUnit = instructorUnit == null ? null : instructorUnit.trim();
    }

    public String getInstructorJob() {
        return instructorJob;
    }

    public void setInstructorJob(String instructorJob) {
        this.instructorJob = instructorJob == null ? null : instructorJob.trim();
    }

    

	public Date getPartyOrgTime() {
		return partyOrgTime;
	}

	public void setPartyOrgTime(Date partyOrgTime) {
		this.partyOrgTime = partyOrgTime;
	}

	public String getPartyOrgAttachment() {
        return partyOrgAttachment;
    }

    public void setPartyOrgAttachment(String partyOrgAttachment) {
        this.partyOrgAttachment = partyOrgAttachment == null ? null : partyOrgAttachment.trim();
    }

    public String getPartyOrgName() {
        return partyOrgName;
    }

    public void setPartyOrgName(String partyOrgName) {
        this.partyOrgName = partyOrgName == null ? null : partyOrgName.trim();
    }

    public String getPartyOrgTel() {
        return partyOrgTel;
    }

    public void setPartyOrgTel(String partyOrgTel) {
        this.partyOrgTel = partyOrgTel == null ? null : partyOrgTel.trim();
    }

    public String getSubjectionPartyName() {
        return subjectionPartyName;
    }

    public void setSubjectionPartyName(String subjectionPartyName) {
        this.subjectionPartyName = subjectionPartyName == null ? null : subjectionPartyName.trim();
    }

    public Integer getSubjectionPartyId() {
        return subjectionPartyId;
    }

    public void setSubjectionPartyId(Integer subjectionPartyId) {
        this.subjectionPartyId = subjectionPartyId;
    }

    public String getSecretaryName() {
        return secretaryName;
    }

    public void setSecretaryName(String secretaryName) {
        this.secretaryName = secretaryName == null ? null : secretaryName.trim();
    }

   

	public Date getSecretaryBirthday() {
		return secretaryBirthday;
	}

	public void setSecretaryBirthday(Date secretaryBirthday) {
		this.secretaryBirthday = secretaryBirthday;
	}

	public String getSecretaryEducation() {
        return secretaryEducation;
    }

    public void setSecretaryEducation(String secretaryEducation) {
        this.secretaryEducation = secretaryEducation == null ? null : secretaryEducation.trim();
    }

    public String getSecretarySource() {
        return secretarySource;
    }

    public void setSecretarySource(String secretarySource) {
        this.secretarySource = secretarySource == null ? null : secretarySource.trim();
    }

    public String getSecretaryCompany() {
        return secretaryCompany;
    }

    public void setSecretaryCompany(String secretaryCompany) {
        this.secretaryCompany = secretaryCompany == null ? null : secretaryCompany.trim();
    }

    public Integer getPartTimeWorkers() {
        return partTimeWorkers;
    }

    public void setPartTimeWorkers(Integer partTimeWorkers) {
        this.partTimeWorkers = partTimeWorkers;
    }

    public Integer getFullTimeWorkers() {
        return fullTimeWorkers;
    }

    public void setFullTimeWorkers(Integer fullTimeWorkers) {
        this.fullTimeWorkers = fullTimeWorkers;
    }

    public String getIsOneself() {
        return isOneself;
    }

    public void setIsOneself(String isOneself) {
        this.isOneself = isOneself == null ? null : isOneself.trim();
    }

    public String getBelongUnit() {
        return belongUnit;
    }

    public void setBelongUnit(String belongUnit) {
        this.belongUnit = belongUnit == null ? null : belongUnit.trim();
    }

    public String getStageArea() {
        return stageArea;
    }

    public void setStageArea(String stageArea) {
        this.stageArea = stageArea == null ? null : stageArea.trim();
    }

    public String getOtherInfo() {
        return otherInfo;
    }

    public void setOtherInfo(String otherInfo) {
        this.otherInfo = otherInfo == null ? null : otherInfo.trim();
    }

    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater == null ? null : creater.trim();
    }

    public String getCreateOrg() {
        return createOrg;
    }

    public void setCreateOrg(String createOrg) {
        this.createOrg = createOrg == null ? null : createOrg.trim();
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

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getPartyInNum() {
		return partyInNum;
	}

	public void setPartyInNum(String partyInNum) {
		this.partyInNum = partyInNum;
	}

	public String getPartyNotInNum() {
		return partyNotInNum;
	}

	public void setPartyNotInNum(String partyNotInNum) {
		this.partyNotInNum = partyNotInNum;
	}

	public String getPartyOrgFormTxt() {
		return partyOrgFormTxt;
	}

	public void setPartyOrgFormTxt(String partyOrgFormTxt) {
		this.partyOrgFormTxt = partyOrgFormTxt;
	}

	public String getPartyOrgTypeTxt() {
		return partyOrgTypeTxt;
	}

	public void setPartyOrgTypeTxt(String partyOrgTypeTxt) {
		this.partyOrgTypeTxt = partyOrgTypeTxt;
	}

	public String getStatusTxt() {
		return statusTxt;
	}

	public void setStatusTxt(String statusTxt) {
		this.statusTxt = statusTxt;
	}

	public Integer getReportHigher() {
		return reportHigher;
	}

	public void setReportHigher(Integer reportHigher) {
		this.reportHigher = reportHigher;
	}

	public String getFilepartyOrgAttachment() {
		return filepartyOrgAttachment;
	}

	public void setFilepartyOrgAttachment(String filepartyOrgAttachment) {
		this.filepartyOrgAttachment = filepartyOrgAttachment;
	}

	public String getPartyOrgTimeTxt() {
		return partyOrgTimeTxt;
	}

	public void setPartyOrgTimeTxt(String partyOrgTimeTxt) {
		this.partyOrgTimeTxt = partyOrgTimeTxt;
	}

	public String getDeputySecretaryBirthdayTxt() {
		return deputySecretaryBirthdayTxt;
	}

	public void setDeputySecretaryBirthdayTxt(String deputySecretaryBirthdayTxt) {
		this.deputySecretaryBirthdayTxt = deputySecretaryBirthdayTxt;
	}

	public String getSecretaryBirthdayTxt() {
		return secretaryBirthdayTxt;
	}

	public void setSecretaryBirthdayTxt(String secretaryBirthdayTxt) {
		this.secretaryBirthdayTxt = secretaryBirthdayTxt;
	}

	public String getInstructorUnitTxt() {
		return instructorUnitTxt;
	}

	public void setInstructorUnitTxt(String instructorUnitTxt) {
		this.instructorUnitTxt = instructorUnitTxt;
	}

	public String getSecretaryCompanyTxt() {
		return secretaryCompanyTxt;
	}

	public void setSecretaryCompanyTxt(String secretaryCompanyTxt) {
		this.secretaryCompanyTxt = secretaryCompanyTxt;
	}

	public String getBelongUnitTxt() {
		return belongUnitTxt;
	}

	public void setBelongUnitTxt(String belongUnitTxt) {
		this.belongUnitTxt = belongUnitTxt;
	}


}