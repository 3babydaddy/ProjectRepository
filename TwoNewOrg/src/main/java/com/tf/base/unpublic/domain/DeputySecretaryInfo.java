package com.tf.base.unpublic.domain;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Table(name = "deputy_secretary_info")
public class DeputySecretaryInfo {
	
	/**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    /**
     * 党务副书记及委员类型
     */
    @Column(name = "type")
    private String type;
    /**
     * 党组织信息id
     */
    @Column(name = "party_org_id")
    private String partyOrgId;
    /**
     * 党务副书记及委员姓名
     */
    @Column(name = "deputy_secretary_type")
    private String deputySecretaryType;
    /**
     * 党务副书记及委员姓名
     */
    @Column(name = "deputy_secretary_name")
    private String deputySecretaryName;
    /**
     * 党务副书记及委员出生日期
     */
    @Column(name = "deputy_secretary_birthday")
    private Date deputySecretaryBirthday;
    /**
     * 党务副书记及委员性别
     */
    @Column(name = "deputy_secretary_sex")
    private String deputySecretarySex;
    /**
     * 党务副书记及委员学历
     */
    @Column(name = "deputy_secretary_education")
    private String deputySecretaryEducation;
    /**
     * 党务副书记及委员是否专职
     */
    @Column(name = "deputy_secretary_is_full_time")
    private String deputySecretaryIsFullTime;
    
    @Column(name = "other_into_manger")
    private String otherInfoManager;
    /**
     * 党务副书记及委员是否专职
     */
    @Column(name = "is_board_officer")
    private String isBoardOfficer;

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
    private String deputySecretaryBirthdayTxt;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", type=").append(type);
        sb.append(", partyOrgId=").append(partyOrgId);
        sb.append(", deputySecretaryName=").append(deputySecretaryName);
        sb.append(", deputySecretaryType=").append(deputySecretaryType);
        sb.append(", deputySecretaryBirthday=").append(deputySecretaryBirthday);
        sb.append(", deputySecretarySex=").append(deputySecretarySex);
        sb.append(", deputySecretaryEducation=").append(deputySecretaryEducation);
        sb.append(", deputySecretaryIsFullTime=").append(deputySecretaryIsFullTime);
        sb.append(", isBoardOfficer=").append(isBoardOfficer);
        sb.append(", otherInfoManager=").append(otherInfoManager);
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
		this.type = type;
	}

	public String getPartyOrgId() {
		return partyOrgId;
	}

	public void setPartyOrgId(String partyOrgId) {
		this.partyOrgId = partyOrgId;
	}

	public String getDeputySecretaryType() {
		return deputySecretaryType;
	}

	public void setDeputySecretaryType(String deputySecretaryType) {
		this.deputySecretaryType = deputySecretaryType;
	}

	public String getDeputySecretaryName() {
        return deputySecretaryName;
    }

    public void setDeputySecretaryName(String deputySecretaryName) {
        this.deputySecretaryName = deputySecretaryName == null ? null : deputySecretaryName.trim();
    }

	public Date getDeputySecretaryBirthday() {
		return deputySecretaryBirthday;
	}

	public void setDeputySecretaryBirthday(Date deputySecretaryBirthday) {
		this.deputySecretaryBirthday = deputySecretaryBirthday;
	}

	public String getDeputySecretaryBirthdayTxt() {
		return deputySecretaryBirthdayTxt;
	}

	public void setDeputySecretaryBirthdayTxt(String deputySecretaryBirthdayTxt) {
		this.deputySecretaryBirthdayTxt = deputySecretaryBirthdayTxt;
	}

	public String getDeputySecretarySex() {
        return deputySecretarySex;
    }

    public void setDeputySecretarySex(String deputySecretarySex) {
        this.deputySecretarySex = deputySecretarySex == null ? null : deputySecretarySex.trim();
    }

    public String getDeputySecretaryEducation() {
        return deputySecretaryEducation;
    }

    public void setDeputySecretaryEducation(String deputySecretaryEducation) {
        this.deputySecretaryEducation = deputySecretaryEducation == null ? null : deputySecretaryEducation.trim();
    }

    public String getDeputySecretaryIsFullTime() {
        return deputySecretaryIsFullTime;
    }

    public void setDeputySecretaryIsFullTime(String deputySecretaryIsFullTime) {
        this.deputySecretaryIsFullTime = deputySecretaryIsFullTime == null ? null : deputySecretaryIsFullTime.trim();
    }

    public String getOtherInfoManager() {
		return otherInfoManager;
	}

	public void setOtherInfoManager(String otherInfoManager) {
		this.otherInfoManager = otherInfoManager;
	}

	public String getIsBoardOfficer() {
		return isBoardOfficer;
	}

	public void setIsBoardOfficer(String isBoardOfficer) {
		this.isBoardOfficer = isBoardOfficer;
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

}