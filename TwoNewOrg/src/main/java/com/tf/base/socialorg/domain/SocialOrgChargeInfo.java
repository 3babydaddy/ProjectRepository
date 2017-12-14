package com.tf.base.socialorg.domain;

import java.util.Date;
import javax.persistence.*;

import com.tf.base.common.annotation.LogShowName;
import com.tf.base.common.constants.CommonConstants;

@Table(name = "social_org_charge_info")
public class SocialOrgChargeInfo {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 社会组织组织信息表ID
     */
    @Column(name = "social_org_info_id")
    private Integer socialOrgInfoId;

    /**
     * 负责人姓名
     */
    @LogShowName(value="负责人姓名")
    @Column(name = "charge_name")
    private String chargeName;

    /**
     * 负责人是否党员
     */
    @LogShowName(value="负责人是否党员",dmm=CommonConstants.YES_NO)
    @Column(name = "charge_partymember_is")
    private String chargePartymemberIs;

    /**
     * 负责人是否兼任党组织书记
     */
    @LogShowName(value="负责人是否兼任党组织书记",dmm=CommonConstants.YES_NO)
    @Column(name = "charge_partyorg_secretary_is")
    private String chargePartyorgSecretaryIs;

    /**
     * 负责人是否担任区县级以上（含区县）“两代表一委员”
     */
    @LogShowName(value="是否担任区县级以上（含区县）“两代表一委员”",dmm=CommonConstants.YES_NO)
    @Column(name = "charge_twodeputy_acommittee_is")
    private String chargeTwodeputyAcommitteeIs;

    /**
     * 负责人是否担任区县级以上（含区县）“两代表一委员”类型
     */
    @LogShowName(value="是否担任区县级以上（含区县）“两代表一委员”类型",dmm=CommonConstants.PARTY_DEPUTY_TYPE)
    @Column(name = "charge_twodeputy_acommittee_type")
    private String chargeTwodeputyAcommitteeType;

    /**
     * 类型为其他时，录入的内容
     */
    @LogShowName("类型为其他时录入的内容")
    @Column(name = "charge_twodeputy_acommittee_type_other")
    private String chargeTwodeputyAcommitteeTypeOther;

    @Column(name = "create_time")
    private Date createTime;

    private String creator;
    
    @Transient
    private String chargePartymemberIsTxt;
    @Transient
    private String chargePartyorgSecretaryIsTxt;
    @Transient
    private String chargeTwodeputyAcommitteeIsTxt;

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
     * @return social_org_info_id - 社会组织组织信息表ID
     */
    public Integer getSocialOrgInfoId() {
        return socialOrgInfoId;
    }

    /**
     * 设置社会组织组织信息表ID
     *
     * @param socialOrgInfoId 社会组织组织信息表ID
     */
    public void setSocialOrgInfoId(Integer socialOrgInfoId) {
        this.socialOrgInfoId = socialOrgInfoId;
    }

    /**
     * 获取负责人姓名
     *
     * @return charge_name - 负责人姓名
     */
    public String getChargeName() {
        return chargeName;
    }

    /**
     * 设置负责人姓名
     *
     * @param chargeName 负责人姓名
     */
    public void setChargeName(String chargeName) {
        this.chargeName = chargeName;
    }

    /**
     * 获取负责人是否党员
     *
     * @return charge_partymember_is - 负责人是否党员
     */
    public String getChargePartymemberIs() {
        return chargePartymemberIs;
    }

    /**
     * 设置负责人是否党员
     *
     * @param chargePartymemberIs 负责人是否党员
     */
    public void setChargePartymemberIs(String chargePartymemberIs) {
        this.chargePartymemberIs = chargePartymemberIs;
    }

    /**
     * 获取负责人是否兼任党组织书记
     *
     * @return charge_partyorg_secretary_is - 负责人是否兼任党组织书记
     */
    public String getChargePartyorgSecretaryIs() {
        return chargePartyorgSecretaryIs;
    }

    /**
     * 设置负责人是否兼任党组织书记
     *
     * @param chargePartyorgSecretaryIs 负责人是否兼任党组织书记
     */
    public void setChargePartyorgSecretaryIs(String chargePartyorgSecretaryIs) {
        this.chargePartyorgSecretaryIs = chargePartyorgSecretaryIs;
    }

    /**
     * 获取负责人是否担任区县级以上（含区县）“两代表一委员”
     *
     * @return charge_twodeputy_acommittee_is - 负责人是否担任区县级以上（含区县）“两代表一委员”
     */
    public String getChargeTwodeputyAcommitteeIs() {
        return chargeTwodeputyAcommitteeIs;
    }

    /**
     * 设置负责人是否担任区县级以上（含区县）“两代表一委员”
     *
     * @param chargeTwodeputyAcommitteeIs 负责人是否担任区县级以上（含区县）“两代表一委员”
     */
    public void setChargeTwodeputyAcommitteeIs(String chargeTwodeputyAcommitteeIs) {
        this.chargeTwodeputyAcommitteeIs = chargeTwodeputyAcommitteeIs;
    }

    /**
     * 获取负责人是否担任区县级以上（含区县）“两代表一委员”类型
     *
     * @return charge_twodeputy_acommittee_type - 负责人是否担任区县级以上（含区县）“两代表一委员”类型
     */
    public String getChargeTwodeputyAcommitteeType() {
        return chargeTwodeputyAcommitteeType;
    }

    /**
     * 设置负责人是否担任区县级以上（含区县）“两代表一委员”类型
     *
     * @param chargeTwodeputyAcommitteeType 负责人是否担任区县级以上（含区县）“两代表一委员”类型
     */
    public void setChargeTwodeputyAcommitteeType(String chargeTwodeputyAcommitteeType) {
        this.chargeTwodeputyAcommitteeType = chargeTwodeputyAcommitteeType;
    }

    /**
     * 获取类型为其他时，录入的内容
     *
     * @return charge_twodeputy_acommittee_type_other - 类型为其他时，录入的内容
     */
    public String getChargeTwodeputyAcommitteeTypeOther() {
        return chargeTwodeputyAcommitteeTypeOther;
    }

    /**
     * 设置类型为其他时，录入的内容
     *
     * @param chargeTwodeputyAcommitteeTypeOther 类型为其他时，录入的内容
     */
    public void setChargeTwodeputyAcommitteeTypeOther(String chargeTwodeputyAcommitteeTypeOther) {
        this.chargeTwodeputyAcommitteeTypeOther = chargeTwodeputyAcommitteeTypeOther;
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
        sb.append(", socialOrgInfoId=").append(socialOrgInfoId);
        sb.append(", chargeName=").append(chargeName);
        sb.append(", chargePartymemberIs=").append(chargePartymemberIs);
        sb.append(", chargePartyorgSecretaryIs=").append(chargePartyorgSecretaryIs);
        sb.append(", chargeTwodeputyAcommitteeIs=").append(chargeTwodeputyAcommitteeIs);
        sb.append(", chargeTwodeputyAcommitteeType=").append(chargeTwodeputyAcommitteeType);
        sb.append(", chargeTwodeputyAcommitteeTypeOther=").append(chargeTwodeputyAcommitteeTypeOther);
        sb.append(", createTime=").append(createTime);
        sb.append(", creator=").append(creator);
        sb.append("]");
        return sb.toString();
    }

	public String getChargePartymemberIsTxt() {
		return chargePartymemberIsTxt;
	}

	public void setChargePartymemberIsTxt(String chargePartymemberIsTxt) {
		this.chargePartymemberIsTxt = chargePartymemberIsTxt;
	}

	public String getChargePartyorgSecretaryIsTxt() {
		return chargePartyorgSecretaryIsTxt;
	}

	public void setChargePartyorgSecretaryIsTxt(String chargePartyorgSecretaryIsTxt) {
		this.chargePartyorgSecretaryIsTxt = chargePartyorgSecretaryIsTxt;
	}

	public String getChargeTwodeputyAcommitteeIsTxt() {
		return chargeTwodeputyAcommitteeIsTxt;
	}

	public void setChargeTwodeputyAcommitteeIsTxt(String chargeTwodeputyAcommitteeIsTxt) {
		this.chargeTwodeputyAcommitteeIsTxt = chargeTwodeputyAcommitteeIsTxt;
	}
    
}