package com.tf.base.unpublic.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Table(name = "party_instructor_info")
public class PartyInstructorInfo {
	
	/**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "party_instructor_id")
    private Integer id;
    /**
     * 业务种类 1.社会 2.非公
     */
    private String type;
    /**
     * 党组织信息表id
     */
    @Column(name = "party_org_id")
    private Integer partyOrgId;
    /**
     * 指导员名称
     */
    @Column(name = "instructor_name")
    private String instructorName;
    /**
     * 性别
     */
    @Column(name = "instructor_sex")
    private String instructorSex;
    /**
     * 指导员单位
     */
    @Column(name = "instructor_comp")
    private String instructorComp;
    /**
     * 指导员职务
     */
    @Column(name = "instructor_job")
    private String instructorJob;
    /**
     * 学历
     */
    @Column(name = "instructor_education")
    private String instructorEducation;
    /**
     * 单位列表
     */
    @Column(name = "instruct_orgs")
    private String instructOrgs;
    
    private String creater;
    
    @Column(name = "create_org")
    private String createOrg;

    @Column(name = "create_time")
    private Date createTime;

    private String status;

    @Transient
    private List<String> orgIdList;
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", type=").append(type);
        sb.append(", partyOrgId=").append(partyOrgId);
        sb.append(", instructorName=").append(instructorName);
        sb.append(", instructorSex=").append(instructorSex);
        sb.append(", instructorComp=").append(instructorComp);
        sb.append(", instructorName=").append(instructorName);
        sb.append(", instructorJob=").append(instructorJob);
        sb.append(", instructorEducation=").append(instructorEducation);
        sb.append(", instructOrgs=").append(instructOrgs);
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

	public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public Integer getPartyOrgId() {
        return partyOrgId;
    }

    public void setPartyOrgId(Integer partyOrgId) {
        this.partyOrgId = partyOrgId;
    }

    public String getInstructorName() {
        return instructorName;
    }

    public void setInstructorName(String instructorName) {
        this.instructorName = instructorName == null ? null : instructorName.trim();
    }

    public String getInstructorSex() {
        return instructorSex;
    }

    public void setInstructorSex(String instructorSex) {
        this.instructorSex = instructorSex == null ? null : instructorSex.trim();
    }

    public String getInstructorComp() {
        return instructorComp;
    }

    public void setInstructorComp(String instructorComp) {
        this.instructorComp = instructorComp == null ? null : instructorComp.trim();
    }

    public String getInstructorJob() {
        return instructorJob;
    }

    public void setInstructorJob(String instructorJob) {
        this.instructorJob = instructorJob == null ? null : instructorJob.trim();
    }

    public String getInstructorEducation() {
        return instructorEducation;
    }

    public void setInstructorEducation(String instructorEducation) {
        this.instructorEducation = instructorEducation == null ? null : instructorEducation.trim();
    }

    public String getInstructOrgs() {
        return instructOrgs;
    }

    public void setInstructOrgs(String instructOrgs) {
        this.instructOrgs = instructOrgs == null ? null : instructOrgs.trim();
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

	public List<String> getOrgIdList() {
		return orgIdList;
	}

	public void setOrgIdList(List<String> orgIdList) {
		this.orgIdList = orgIdList;
	}
}