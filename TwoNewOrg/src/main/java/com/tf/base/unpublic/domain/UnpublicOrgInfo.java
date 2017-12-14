package com.tf.base.unpublic.domain;

import java.util.Date;
import javax.persistence.*;

import com.tf.base.common.annotation.LogShowName;
import com.tf.base.common.constants.CommonConstants;

@Table(name = "unpublic_org_info")
public class UnpublicOrgInfo {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 行业类型
     */
    @LogShowName(value="行业类型",dmm=CommonConstants.BUSINESS_TYPE)
    @Column(name = "business_type")
    private String businessType;
    
    
    /**
     * 国家
     */
    @LogShowName(value="国家",dmm=CommonConstants.NATIONALITY)
    private String nationality;

    /**
     * 企业类型
     */
    @LogShowName(value="企业类型",dmm=CommonConstants.ENTERPRISE_TYPE)
    @Column(name = "industry_type")
    private String industryType;

    /**
     * 企业注册地
     */
    @LogShowName(value="企业注册地")
    @Column(name = "register_address")
    private String registerAddress;
    
    /**
     * 企业注册地:地区等级
     */
    @LogShowName(value="企业注册地",dmm=CommonConstants.ADDRESS_LEVEL)
    @Column(name = "register_address_level")
    private String registerAddressLevel;


    /**
     * 注册地-省
     */
    @LogShowName(value="注册地省")
    @Column(name = "register_address_province")
    private String registerAddressProvince;

    /**
     * 注册地-市
     */
    @LogShowName(value="注册地市")
    @Column(name = "register_address_city")
    private String registerAddressCity;

    /**
     * 注册地-区
     */
    @LogShowName(value="注册地区")
    @Column(name = "register_address_district")
    private String registerAddressDistrict;

    /**
     * 注册地-街道
     */
    @LogShowName(value="注册地街道")
    @Column(name = "register_address_street")
    private String registerAddressStreet;
    
    /**
     * 是否存在企业经营地
     */
    @LogShowName(value="是否存在企业经营地")
    @Column(name = "is_have_address")
    private String isHaveAddress;
    

    /**
     * 企业经营地
     */
    @LogShowName(value="企业经营地")
    @Column(name = "operate_address")
    private String operateAddress;

    /**
     * 企业坐落地
     */
    @LogShowName(value="企业坐落地",dmm=CommonConstants.ENTERPRISE_BELOCATED_ADDRESS)
    @Column(name = "belocated_address")
    private String belocatedAddress;

    /**
     * 园区级别
     */
    @LogShowName(value="园区等级",dmm=CommonConstants.ZONE_LEVEL)
    private String level;

    /**
     * 是否为亿元楼宇
     */
    @LogShowName(value="是否为亿元楼宇",dmm=CommonConstants.YES_NO)
    @Column(name = "million_building_is")
    private String millionBuildingIs;

    /**
     * 所在园区名称
     */
    @LogShowName("所在园区名称")
    @Column(name = "inpark_name")
    private String inparkName;
    /**
     * 所在商务楼宇名称
     */
    @LogShowName("所在商务楼宇名称")
    @Column(name = "building_name")
    private String buildingName;

    /**
     * 企业名称
     */
    @LogShowName("企业名称")
    private String name;

    @LogShowName("联系方式")
    @Column(name = "contact_phone")
    private String contactPhone;

    /**
     * 年营业收入
     */
    @LogShowName("年营业收入")
    @Column(name = "business_volume")
    private float businessVolume;

    /**
     * 从业人员数量
     */
    @LogShowName("从业人员数量")
    @Column(name = "jobin_totalnum")
    private Integer jobinTotalnum;

    /**
     * 是否规模以上企业
     */
    @LogShowName(value="是否规模以上企业",dmm=CommonConstants.YES_NO)
    @Column(name = "on_scale_is")
    private String onScaleIs;

    /**
     * 状态 1.临时保存 2.正常,  4.已撤销 0.已删除
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

    
    @Column(name = "unpublic_party_org_id")
    private String unpublicPartyOrgId;
    
    @Column(name = "cover_party_org_id")
    private String coverPartyOrgId;
    /**
     * 填报单位
     */
    @Column(name = "create_org")
    private String createOrg;
    
    @Transient
    private String statusTxt;
    @Transient
    private String belocatedAddressTxt;
    
    @Transient
    private String businessTypeTxt;
    @Transient
    private String nationlityTxt;
    @Transient
    private String industryTypeTxt;
    @Transient
    private String levelTxt;
    @Transient
    private String millionBuildingIsTxt;
    @Transient
    private String onScaleIsTxt;
    @Transient
    private String createOrgTxt;
    @Transient
    private String otherCondition;
    

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
     * 获取类型
     *
     * @return business_type - 类型
     */
    public String getBusinessType() {
        return businessType;
    }

    /**
     * 设置类型
     *
     * @param businessType 类型
     */
    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    /**
     * 获取国家
     *
     * @return nationality - 国家
     */
    public String getNationality() {
        return nationality;
    }

    /**
     * 设置国家
     *
     * @param nationality 国家
     */
    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    /**
     * 获取行业类型
     *
     * @return industry_type - 行业类型
     */
    public String getIndustryType() {
        return industryType;
    }

    /**
     * 设置行业类型
     *
     * @param industryType 行业类型
     */
    public void setIndustryType(String industryType) {
        this.industryType = industryType;
    }

    /**
     * 获取企业注册地
     *
     * @return register_address - 企业注册地
     */
    public String getRegisterAddress() {
        return registerAddress;
    }

    /**
     * 设置企业注册地
     *
     * @param registerAddress 企业注册地
     */
    public void setRegisterAddress(String registerAddress) {
        this.registerAddress = registerAddress;
    }

    /**
     * 获取注册地-省
     *
     * @return register_address_province - 注册地-省
     */
    public String getRegisterAddressProvince() {
        return registerAddressProvince;
    }

    /**
     * 设置注册地-省
     *
     * @param registerAddressProvince 注册地-省
     */
    public void setRegisterAddressProvince(String registerAddressProvince) {
        this.registerAddressProvince = registerAddressProvince;
    }

    /**
     * 获取注册地-市
     *
     * @return register_address_city - 注册地-市
     */
    public String getRegisterAddressCity() {
        return registerAddressCity;
    }

    /**
     * 设置注册地-市
     *
     * @param registerAddressCity 注册地-市
     */
    public void setRegisterAddressCity(String registerAddressCity) {
        this.registerAddressCity = registerAddressCity;
    }

    /**
     * 获取注册地-区
     *
     * @return register_address_district - 注册地-区
     */
    public String getRegisterAddressDistrict() {
        return registerAddressDistrict;
    }

    /**
     * 设置注册地-区
     *
     * @param registerAddressDistrict 注册地-区
     */
    public void setRegisterAddressDistrict(String registerAddressDistrict) {
        this.registerAddressDistrict = registerAddressDistrict;
    }

    /**
     * 获取注册地-街道
     *
     * @return register_address_street - 注册地-街道
     */
    public String getRegisterAddressStreet() {
        return registerAddressStreet;
    }

    /**
     * 设置注册地-街道
     *
     * @param registerAddressStreet 注册地-街道
     */
    public void setRegisterAddressStreet(String registerAddressStreet) {
        this.registerAddressStreet = registerAddressStreet;
    }

    /**
     * 获取企业经营地
     *
     * @return operate_address - 企业经营地
     */
    public String getOperateAddress() {
        return operateAddress;
    }

    /**
     * 设置企业经营地
     *
     * @param operateAddress 企业经营地
     */
    public void setOperateAddress(String operateAddress) {
        this.operateAddress = operateAddress;
    }


    /**
     * 获取企业坐落地
     *
     * @return belocated_address - 企业坐落地
     */
    public String getBelocatedAddress() {
        return belocatedAddress;
    }

    /**
     * 设置企业坐落地
     *
     * @param belocatedAddress 企业坐落地
     */
    public void setBelocatedAddress(String belocatedAddress) {
        this.belocatedAddress = belocatedAddress;
    }

    /**
     * 获取园区级别
     *
     * @return level - 园区级别
     */
    public String getLevel() {
        return level;
    }

    /**
     * 设置园区级别
     *
     * @param level 园区级别
     */
    public void setLevel(String level) {
        this.level = level;
    }

    /**
     * 获取是否为亿元楼宇
     *
     * @return million_building_is - 是否为亿元楼宇
     */
    public String getMillionBuildingIs() {
        return millionBuildingIs;
    }

    /**
     * 设置是否为亿元楼宇
     *
     * @param millionBuildingIs 是否为亿元楼宇
     */
    public void setMillionBuildingIs(String millionBuildingIs) {
        this.millionBuildingIs = millionBuildingIs;
    }

    /**
     * 获取所在园区、商务楼宇名称
     *
     * @return inpark_name - 所在园区、商务楼宇名称
     */
    public String getInparkName() {
        return inparkName;
    }

    /**
     * 设置所在园区、商务楼宇名称
     *
     * @param inparkName 所在园区、商务楼宇名称
     */
    public void setInparkName(String inparkName) {
        this.inparkName = inparkName;
    }

    /**
     * 获取企业名称
     *
     * @return name - 企业名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置企业名称
     *
     * @param name 企业名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return contact_phone
     */
    public String getContactPhone() {
        return contactPhone;
    }

    /**
     * @param contactPhone
     */
    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    /**
     * 获取年营业收入
     *
     * @return business_volume - 年营业收入
     */
    public float getBusinessVolume() {
        return businessVolume;
    }

    /**
     * 设置年营业收入
     *
     * @param businessVolume 年营业收入
     */
    public void setBusinessVolume(float businessVolume) {
        this.businessVolume = businessVolume;
    }

    /**
     * 获取从业人员数量
     *
     * @return jobin_totalnum - 从业人员数量
     */
    public Integer getJobinTotalnum() {
        return jobinTotalnum;
    }

    /**
     * 设置从业人员数量
     *
     * @param jobinTotalnum 从业人员数量
     */
    public void setJobinTotalnum(Integer jobinTotalnum) {
        this.jobinTotalnum = jobinTotalnum;
    }

    /**
     * 获取是否规模以上企业
     *
     * @return on_scale_is - 是否规模以上企业
     */
    public String getOnScaleIs() {
        return onScaleIs;
    }

    /**
     * 设置是否规模以上企业
     *
     * @param onScaleIs 是否规模以上企业
     */
    public void setOnScaleIs(String onScaleIs) {
        this.onScaleIs = onScaleIs;
    }

    /**
     * 获取状态 1.临时保存 2.正常,  4.已撤销 0.已删除
     *
     * @return status - 状态 1.临时保存 2.正常,  4.已撤销 0.已删除
     */
    public String getStatus() {
        return status;
    }

    /**
     * 设置状态 1.临时保存 2.正常,  4.已撤销 0.已删除
     *
     * @param status 状态 1.临时保存 2.正常,  4.已撤销 0.已删除
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
    
    

    public String getRegisterAddressLevel() {
		return registerAddressLevel;
	}

	public void setRegisterAddressLevel(String registerAddressLevel) {
		this.registerAddressLevel = registerAddressLevel;
	}

	@Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", businessType=").append(businessType);
        sb.append(", nationality=").append(nationality);
        sb.append(", industryType=").append(industryType);
        sb.append(", registerAddress=").append(registerAddress);
        sb.append(", registerAddressProvince=").append(registerAddressProvince);
        sb.append(", registerAddressCity=").append(registerAddressCity);
        sb.append(", registerAddressDistrict=").append(registerAddressDistrict);
        sb.append(", registerAddressStreet=").append(registerAddressStreet);
        sb.append(", operateAddress=").append(operateAddress);
        sb.append(", belocatedAddress=").append(belocatedAddress);
        sb.append(", level=").append(level);
        sb.append(", millionBuildingIs=").append(millionBuildingIs);
        sb.append(", inparkName=").append(inparkName);
        sb.append(", name=").append(name);
        sb.append(", contactPhone=").append(contactPhone);
        sb.append(", businessVolume=").append(businessVolume);
        sb.append(", jobinTotalnum=").append(jobinTotalnum);
        sb.append(", onScaleIs=").append(onScaleIs);
        sb.append(", status=").append(status);
        sb.append(", isBuildPartyorg=").append(isBuildPartyorg);
        sb.append(", unbuildReason=").append(unbuildReason);
        sb.append(", initIs=").append(initIs);
        sb.append(", createTime=").append(createTime);
        sb.append(", creator=").append(creator);
        sb.append(", createOrg=").append(createOrg);
        sb.append("]");
        return sb.toString();
    }

	public String getStatusTxt() {
		return statusTxt;
	}

	public void setStatusTxt(String statusTxt) {
		this.statusTxt = statusTxt;
	}

	public String getBusinessTypeTxt() {
		return businessTypeTxt;
	}

	public void setBusinessTypeTxt(String businessTypeTxt) {
		this.businessTypeTxt = businessTypeTxt;
	}

	public String getIndustryTypeTxt() {
		return industryTypeTxt;
	}

	public void setIndustryTypeTxt(String industryTypeTxt) {
		this.industryTypeTxt = industryTypeTxt;
	}

	public String getNationlityTxt() {
		return nationlityTxt;
	}

	public void setNationlityTxt(String nationlityTxt) {
		this.nationlityTxt = nationlityTxt;
	}

	public String getLevelTxt() {
		return levelTxt;
	}

	public void setLevelTxt(String levelTxt) {
		this.levelTxt = levelTxt;
	}

	public String getMillionBuildingIsTxt() {
		return millionBuildingIsTxt;
	}

	public void setMillionBuildingIsTxt(String millionBuildingIsTxt) {
		this.millionBuildingIsTxt = millionBuildingIsTxt;
	}

	public String getBelocatedAddressTxt() {
		return belocatedAddressTxt;
	}

	public void setBelocatedAddressTxt(String belocatedAddressTxt) {
		this.belocatedAddressTxt = belocatedAddressTxt;
	}

	public String getOnScaleIsTxt() {
		return onScaleIsTxt;
	}

	public void setOnScaleIsTxt(String onScaleIsTxt) {
		this.onScaleIsTxt = onScaleIsTxt;
	}

	public String getBuildingName() {
		return buildingName;
	}

	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
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

	public String getUnpublicPartyOrgId() {
		return unpublicPartyOrgId;
	}

	public void setUnpublicPartyOrgId(String unpublicPartyOrgId) {
		this.unpublicPartyOrgId = unpublicPartyOrgId;
	}

	public String getCoverPartyOrgId() {
		return coverPartyOrgId;
	}

	public void setCoverPartyOrgId(String coverPartyOrgId) {
		this.coverPartyOrgId = coverPartyOrgId;
	}

	public String getIsHaveAddress() {
		return isHaveAddress;
	}

	public void setIsHaveAddress(String isHaveAddress) {
		this.isHaveAddress = isHaveAddress;
	}


}