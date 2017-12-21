package com.tf.base.unpublic.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "lower_party_org")
public class LowerPartyOrg {
	
	/**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lower_party_org_id")
    private Integer id;
   
    private String type;
    /**
     * 党组织id
     */
    @Column(name = "party_org_id")
    private Integer partyOrgId;
    /**
     * 下级党组织名称
     */
    @Column(name = "lower_party_org_name")
    private String lowerPartyOrgName;
    /**
     * 下级党组织类型
     */
    @Column(name = "lower_party_org_type")
    private String lowerPartyOrgType;
    /**
     *数量
     */
    @Column(name = "lower_party_org_sum")
    private Integer lowerPartyOrgSum;
    /**
     * 设立时间
     */
    @Column(name = "setup_time")
    private Date setupTime;
    /**
     * 父党组织
     */
    @Column(name = "parent_lower_party_org")
    private String parentLowerPartyOrg;
   
    private String creater;
    /**
     * 创建机构
     */
    @Column(name = "create_org")
    private String createOrg;
    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    private String status;

    

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

    public String getLowerPartyOrgName() {
        return lowerPartyOrgName;
    }

    public void setLowerPartyOrgName(String lowerPartyOrgName) {
        this.lowerPartyOrgName = lowerPartyOrgName == null ? null : lowerPartyOrgName.trim();
    }

    public String getLowerPartyOrgType() {
        return lowerPartyOrgType;
    }

    public void setLowerPartyOrgType(String lowerPartyOrgType) {
        this.lowerPartyOrgType = lowerPartyOrgType == null ? null : lowerPartyOrgType.trim();
    }

    public Integer getLowerPartyOrgSum() {
        return lowerPartyOrgSum;
    }

    public void setLowerPartyOrgSum(Integer lowerPartyOrgSum) {
        this.lowerPartyOrgSum = lowerPartyOrgSum;
    }

    public Date getSetupTime() {
        return setupTime;
    }

    public void setSetupTime(Date setupTime) {
        this.setupTime = setupTime;
    }

    public String getParentLowerPartyOrg() {
        return parentLowerPartyOrg;
    }

    public void setParentLowerPartyOrg(String parentLowerPartyOrg) {
        this.parentLowerPartyOrg = parentLowerPartyOrg == null ? null : parentLowerPartyOrg.trim();
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