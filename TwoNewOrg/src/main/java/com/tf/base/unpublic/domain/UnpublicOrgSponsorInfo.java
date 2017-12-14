package com.tf.base.unpublic.domain;

import java.util.Date;
import javax.persistence.*;

import com.tf.base.common.annotation.LogShowName;
import com.tf.base.common.constants.CommonConstants;

@Table(name = "unpublic_org_sponsor_info")
public class UnpublicOrgSponsorInfo {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 社会组织组织信息表ID
     */
    @Column(name = "unpublic_org_info_id")
    private Integer unpublicOrgInfoId;

    /**
     * 企业出资人姓名
     */
    @LogShowName("企业出资人姓名")
    @Column(name = "sponsor_name")
    private String sponsorName;

    /**
     * 企业出资人是否党员
     */
    @LogShowName(value="是否党员",dmm=CommonConstants.YES_NO)
    @Column(name = "sponsor_partymember_is")
    private String sponsorPartymemberIs;

    /**
     * 企业出资人是否兼任党组织书记
     */
    @LogShowName(value="是否兼任党组织书记",dmm=CommonConstants.YES_NO)
    @Column(name = "sponsor_partyorg_secretary_is")
    private String sponsorPartyorgSecretaryIs;

    /**
     * 企业出资人是否担任区县级以上（含区县）“两代表一委员
     */
    @LogShowName(value="是否担任区县级以上（含区县）“两代表一委员",dmm=CommonConstants.YES_NO)
    @Column(name = "sponsor_twodeputy_acommittee_is")
    private String sponsorTwodeputyAcommitteeIs;

    /**
     * 企业出资人担任区县级以上（含区县）“两代表一委员类型
     */
    @LogShowName(value="担任区县级以上（含区县）“两代表一委员类型",dmm=CommonConstants.PARTY_DEPUTY_TYPE)
    @Column(name = "sponsor_twodeputy_acommittee_type")
    private String sponsorTwodeputyAcommitteeType;

    /**
     * 类型为其他时，录入的内容
     */
    @Column(name = "sponsor_twodeputy_acommittee_type_other")
    private String sponsorTwodeputyAcommitteeTypeOther;

    @Column(name = "create_time")
    private Date createTime;

    private String creator;
    
    @Transient
    private String sponsorPartymemberIsTxt;
    @Transient
    private String sponsorPartyorgSecretaryIsTxt;
    @Transient
    private String sponsorTwodeputyAcommitteeIsTxt;
    @Transient
    private String sponsorTwodeputyAcommitteeTypeTxt;

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
     * 获取社会组织组织信息表ID
     *
     * @return unpublic_org_info_id - 社会组织组织信息表ID
     */
    public Integer getUnpublicOrgInfoId() {
        return unpublicOrgInfoId;
    }

    /**
     * 设置社会组织组织信息表ID
     *
     * @param unpublicOrgInfoId 社会组织组织信息表ID
     */
    public void setUnpublicOrgInfoId(Integer unpublicOrgInfoId) {
        this.unpublicOrgInfoId = unpublicOrgInfoId;
    }

    /**
     * 获取企业出资人姓名
     *
     * @return sponsor_name - 企业出资人姓名
     */
    public String getSponsorName() {
        return sponsorName;
    }

    /**
     * 设置企业出资人姓名
     *
     * @param sponsorName 企业出资人姓名
     */
    public void setSponsorName(String sponsorName) {
        this.sponsorName = sponsorName;
    }

    /**
     * 获取企业出资人是否党员
     *
     * @return sponsor_partymember_is - 企业出资人是否党员
     */
    public String getSponsorPartymemberIs() {
        return sponsorPartymemberIs;
    }

    /**
     * 设置企业出资人是否党员
     *
     * @param sponsorPartymemberIs 企业出资人是否党员
     */
    public void setSponsorPartymemberIs(String sponsorPartymemberIs) {
        this.sponsorPartymemberIs = sponsorPartymemberIs;
    }

    /**
     * 获取企业出资人是否兼任党组织书记
     *
     * @return sponsor_partyorg_secretary_is - 企业出资人是否兼任党组织书记
     */
    public String getSponsorPartyorgSecretaryIs() {
        return sponsorPartyorgSecretaryIs;
    }

    /**
     * 设置企业出资人是否兼任党组织书记
     *
     * @param sponsorPartyorgSecretaryIs 企业出资人是否兼任党组织书记
     */
    public void setSponsorPartyorgSecretaryIs(String sponsorPartyorgSecretaryIs) {
        this.sponsorPartyorgSecretaryIs = sponsorPartyorgSecretaryIs;
    }

    /**
     * 获取企业出资人是否担任区县级以上（含区县）“两代表一委员
     *
     * @return sponsor_twodeputy_acommittee_is - 企业出资人是否担任区县级以上（含区县）“两代表一委员
     */
    public String getSponsorTwodeputyAcommitteeIs() {
        return sponsorTwodeputyAcommitteeIs;
    }

    /**
     * 设置企业出资人是否担任区县级以上（含区县）“两代表一委员
     *
     * @param sponsorTwodeputyAcommitteeIs 企业出资人是否担任区县级以上（含区县）“两代表一委员
     */
    public void setSponsorTwodeputyAcommitteeIs(String sponsorTwodeputyAcommitteeIs) {
        this.sponsorTwodeputyAcommitteeIs = sponsorTwodeputyAcommitteeIs;
    }

    /**
     * 获取企业出资人担任区县级以上（含区县）“两代表一委员类型
     *
     * @return sponsor_twodeputy_acommittee_type - 企业出资人担任区县级以上（含区县）“两代表一委员类型
     */
    public String getSponsorTwodeputyAcommitteeType() {
        return sponsorTwodeputyAcommitteeType;
    }

    /**
     * 设置企业出资人担任区县级以上（含区县）“两代表一委员类型
     *
     * @param sponsorTwodeputyAcommitteeType 企业出资人担任区县级以上（含区县）“两代表一委员类型
     */
    public void setSponsorTwodeputyAcommitteeType(String sponsorTwodeputyAcommitteeType) {
        this.sponsorTwodeputyAcommitteeType = sponsorTwodeputyAcommitteeType;
    }

    /**
     * 获取类型为其他时，录入的内容
     *
     * @return sponsor_twodeputy_acommittee_type_other - 类型为其他时，录入的内容
     */
    public String getSponsorTwodeputyAcommitteeTypeOther() {
        return sponsorTwodeputyAcommitteeTypeOther;
    }

    /**
     * 设置类型为其他时，录入的内容
     *
     * @param sponsorTwodeputyAcommitteeTypeOther 类型为其他时，录入的内容
     */
    public void setSponsorTwodeputyAcommitteeTypeOther(String sponsorTwodeputyAcommitteeTypeOther) {
        this.sponsorTwodeputyAcommitteeTypeOther = sponsorTwodeputyAcommitteeTypeOther;
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", unpublicOrgInfoId=").append(unpublicOrgInfoId);
        sb.append(", sponsorName=").append(sponsorName);
        sb.append(", sponsorPartymemberIs=").append(sponsorPartymemberIs);
        sb.append(", sponsorPartyorgSecretaryIs=").append(sponsorPartyorgSecretaryIs);
        sb.append(", sponsorTwodeputyAcommitteeIs=").append(sponsorTwodeputyAcommitteeIs);
        sb.append(", sponsorTwodeputyAcommitteeType=").append(sponsorTwodeputyAcommitteeType);
        sb.append(", sponsorTwodeputyAcommitteeTypeOther=").append(sponsorTwodeputyAcommitteeTypeOther);
        sb.append(", createTime=").append(createTime);
        sb.append(", creator=").append(creator);
        sb.append("]");
        return sb.toString();
    }

	public String getSponsorPartymemberIsTxt() {
		return sponsorPartymemberIsTxt;
	}

	public void setSponsorPartymemberIsTxt(String sponsorPartymemberIsTxt) {
		this.sponsorPartymemberIsTxt = sponsorPartymemberIsTxt;
	}

	public String getSponsorPartyorgSecretaryIsTxt() {
		return sponsorPartyorgSecretaryIsTxt;
	}

	public void setSponsorPartyorgSecretaryIsTxt(String sponsorPartyorgSecretaryIsTxt) {
		this.sponsorPartyorgSecretaryIsTxt = sponsorPartyorgSecretaryIsTxt;
	}

	public String getSponsorTwodeputyAcommitteeIsTxt() {
		return sponsorTwodeputyAcommitteeIsTxt;
	}

	public void setSponsorTwodeputyAcommitteeIsTxt(String sponsorTwodeputyAcommitteeIsTxt) {
		this.sponsorTwodeputyAcommitteeIsTxt = sponsorTwodeputyAcommitteeIsTxt;
	}

	public String getSponsorTwodeputyAcommitteeTypeTxt() {
		return sponsorTwodeputyAcommitteeTypeTxt;
	}

	public void setSponsorTwodeputyAcommitteeTypeTxt(String sponsorTwodeputyAcommitteeTypeTxt) {
		this.sponsorTwodeputyAcommitteeTypeTxt = sponsorTwodeputyAcommitteeTypeTxt;
	}
    
}