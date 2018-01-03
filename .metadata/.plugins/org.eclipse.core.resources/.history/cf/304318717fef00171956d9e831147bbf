package com.tf.base.socialorg.domain;

import java.util.Date;
import javax.persistence.*;

import com.tf.base.common.annotation.LogShowName;
import com.tf.base.common.constants.CommonConstants;

@Table(name = "social_org_info")
public class SocialOrgInfo {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 名称
     */
    @LogShowName("名称")
    private String name;

    /**
     * 性质
     */
    @LogShowName(value="性质",dmm=CommonConstants.ORG_NATURE)
    private String nature;

    /**
     * 类别
     */
    @LogShowName(value="类别",dmm=CommonConstants.ORG_CATEGORY)
    private String category;

    /**
     * 登记机构
     */
    @LogShowName(value="登记机构")
    @Column(name = "register_org")
    private String registerOrg;

    /**
     * 业务主管单位
     */
    @LogShowName(value="业务主管单位")
    @Column(name = "business_director_org")
    private String businessDirectorOrg;

    /**
     * 住地
     */
    @LogShowName(value="住地")
    private String address;

    /**
     * 状态 1.临时保存 2.正常, 3.撤销申请中, 4.已撤销 0.已删除
     */
    private String status;

    /**
     * 是否建立党组织
     */
    @Column(name = "is_build_partyorg")
    private Integer isBuildPartyorg;

    /**
     * 未建立原因
     */
    @Column(name = "unbuild_reason")
    private String unbuildReason;

    /**
     * 是否首次 1.是 0.否
     */
    @Column(name = "init_is")
    private Integer initIs;

    @Column(name = "create_time")
    private Date createTime;

    private String creator;
    
    @Column(name = "update_time")
    private Date updateTime;
    
    private String updator;
    /**
     * 社会党组织id
     */
    @Column(name = "social_party_org_id")
    private String socialPartyOrgId;
    /**
     * 覆盖社会党组织id
     */
    @Column(name = "cover_party_org_id")
    private String coverPartyOrgId;
    /**
     * 是否设立思想政治教育工作机构
     */
    @Column(name = "is_ideological_political_org")
    private String isIdeologicalPoliticalOrg;
    /**
     * 是否设立德育工作机构
     */
    @Column(name = "is_moral_education_org")
    private String isMoralEducationOrg;
    /**
     * 填报单位
     */
    @Column(name = "create_org")
    private String createOrg;
    
    @Transient
    private String statusTxt;
    @Transient
    private String natureTxt;
    @Transient
    private String categoryTxt;
    @Transient
    private String createOrgTxt;
    @Transient
    private String otherCondition;
    @Transient
    private String isIdeologicalPoliticalOrgTxt;
    @Transient
    private String isMoralEducationOrgTxt;

    /**
     * 获取主键
     *
     * @return id - 主键
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置主键
     *
     * @param id 主键
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取名称
     *
     * @return name - 名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置名称
     *
     * @param name 名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取性质
     *
     * @return nature - 性质
     */
    public String getNature() {
        return nature;
    }

    /**
     * 设置性质
     *
     * @param nature 性质
     */
    public void setNature(String nature) {
        this.nature = nature;
    }

    /**
     * 获取类别
     *
     * @return category - 类别
     */
    public String getCategory() {
        return category;
    }

    /**
     * 设置类别
     *
     * @param category 类别
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * 获取登记机构
     *
     * @return register_org - 登记机构
     */
    public String getRegisterOrg() {
        return registerOrg;
    }

    /**
     * 设置登记机构
     *
     * @param registerOrg 登记机构
     */
    public void setRegisterOrg(String registerOrg) {
        this.registerOrg = registerOrg;
    }

    /**
     * 获取业务主管单位
     *
     * @return business_director_org - 业务主管单位
     */
    public String getBusinessDirectorOrg() {
        return businessDirectorOrg;
    }

    /**
     * 设置业务主管单位
     *
     * @param businessDirectorOrg 业务主管单位
     */
    public void setBusinessDirectorOrg(String businessDirectorOrg) {
        this.businessDirectorOrg = businessDirectorOrg;
    }

    /**
     * 获取住地
     *
     * @return address - 住地
     */
    public String getAddress() {
        return address;
    }

    /**
     * 设置住地
     *
     * @param address 住地
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * 获取状态 1.临时保存 2.正常, 3.撤销申请中, 4.已撤销 0.已删除
     *
     * @return status - 状态 1.临时保存 2.正常, 3.撤销申请中, 4.已撤销 0.已删除
     */
    public String getStatus() {
        return status;
    }

    /**
     * 设置状态 1.临时保存 2.正常, 3.撤销申请中, 4.已撤销 0.已删除
     *
     * @param status 状态 1.临时保存 2.正常, 3.撤销申请中, 4.已撤销 0.已删除
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * 获取是否建立党组织
     *
     * @return is_build_partyorg - 是否建立党组织
     */
    public Integer getIsBuildPartyorg() {
        return isBuildPartyorg;
    }

    /**
     * 设置是否建立党组织
     *
     * @param isBuildPartyorg 是否建立党组织
     */
    public void setIsBuildPartyorg(Integer isBuildPartyorg) {
        this.isBuildPartyorg = isBuildPartyorg;
    }

    /**
     * 获取未建立原因
     *
     * @return unbuild_reason - 未建立原因
     */
    public String getUnbuildReason() {
        return unbuildReason;
    }

    /**
     * 设置未建立原因
     *
     * @param unbuildReason 未建立原因
     */
    public void setUnbuildReason(String unbuildReason) {
        this.unbuildReason = unbuildReason;
    }

    /**
     * 获取是否首次 1.是 0.否
     *
     * @return init_is - 是否首次 1.是 0.否
     */
    public Integer getInitIs() {
        return initIs;
    }

    /**
     * 设置是否首次 1.是 0.否
     *
     * @param initIs 是否首次 1.是 0.否
     */
    public void setInitIs(Integer initIs) {
        this.initIs = initIs;
    }

    /**
     * @return create_time
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return creator
     */
    public String getCreator() {
        return creator;
    }

    /**
     * @param creator
     */
    public void setCreator(String creator) {
        this.creator = creator;
    }

    /**
     * 获取填报单位
     *
     * @return create_org - 填报单位
     */
    public String getCreateOrg() {
        return createOrg;
    }

    /**
     * 设置填报单位
     *
     * @param createOrg 填报单位
     */
    public void setCreateOrg(String createOrg) {
        this.createOrg = createOrg;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", nature=").append(nature);
        sb.append(", category=").append(category);
        sb.append(", registerOrg=").append(registerOrg);
        sb.append(", businessDirectorOrg=").append(businessDirectorOrg);
        sb.append(", address=").append(address);
        sb.append(", status=").append(status);
        sb.append(", isBuildPartyorg=").append(isBuildPartyorg);
        sb.append(", unbuildReason=").append(unbuildReason);
        sb.append(", initIs=").append(initIs);
        sb.append(", createTime=").append(createTime);
        sb.append(", creator=").append(creator);
        sb.append(", createOrg=").append(createOrg);
        sb.append(", socialPartyOrgId=").append(socialPartyOrgId);
        sb.append(", coverPartyOrgId=").append(coverPartyOrgId);
        sb.append(", isIdeologicalPoliticalOrg=").append(isIdeologicalPoliticalOrg);
        sb.append(", isMoralEducationOrg=").append(isMoralEducationOrg);
        sb.append("]");
        return sb.toString();
    }

	public String getStatusTxt() {
		return statusTxt;
	}

	public void setStatusTxt(String statusTxt) {
		this.statusTxt = statusTxt;
	}

	public String getNatureTxt() {
		return natureTxt;
	}

	public void setNatureTxt(String natureTxt) {
		this.natureTxt = natureTxt;
	}

	public String getCategoryTxt() {
		return categoryTxt;
	}

	public void setCategoryTxt(String categoryTxt) {
		this.categoryTxt = categoryTxt;
	}

	public String getCreateOrgTxt() {
		return createOrgTxt;
	}

	public void setCreateOrgTxt(String createOrgTxt) {
		this.createOrgTxt = createOrgTxt;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getUpdator() {
		return updator;
	}

	public void setUpdator(String updator) {
		this.updator = updator;
	}

	public String getOtherCondition() {
		return otherCondition;
	}

	public void setOtherCondition(String otherCondition) {
		this.otherCondition = otherCondition;
	}

	public String getSocialPartyOrgId() {
		return socialPartyOrgId;
	}

	public void setSocialPartyOrgId(String socialPartyOrgId) {
		this.socialPartyOrgId = socialPartyOrgId;
	}

	public String getCoverPartyOrgId() {
		return coverPartyOrgId;
	}

	public void setCoverPartyOrgId(String coverPartyOrgId) {
		this.coverPartyOrgId = coverPartyOrgId;
	}

	public String getIsIdeologicalPoliticalOrg() {
		return isIdeologicalPoliticalOrg;
	}

	public void setIsIdeologicalPoliticalOrg(String isIdeologicalPoliticalOrg) {
		this.isIdeologicalPoliticalOrg = isIdeologicalPoliticalOrg;
	}

	public String getIsMoralEducationOrg() {
		return isMoralEducationOrg;
	}

	public void setIsMoralEducationOrg(String isMoralEducationOrg) {
		this.isMoralEducationOrg = isMoralEducationOrg;
	}

	public String getIsIdeologicalPoliticalOrgTxt() {
		return isIdeologicalPoliticalOrgTxt;
	}

	public void setIsIdeologicalPoliticalOrgTxt(String isIdeologicalPoliticalOrgTxt) {
		this.isIdeologicalPoliticalOrgTxt = isIdeologicalPoliticalOrgTxt;
	}

	public String getIsMoralEducationOrgTxt() {
		return isMoralEducationOrgTxt;
	}

	public void setIsMoralEducationOrgTxt(String isMoralEducationOrgTxt) {
		this.isMoralEducationOrgTxt = isMoralEducationOrgTxt;
	}

}