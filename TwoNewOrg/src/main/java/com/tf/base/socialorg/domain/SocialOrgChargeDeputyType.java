package com.tf.base.socialorg.domain;

import javax.persistence.*;

import com.tf.base.common.annotation.LogShowName;
import com.tf.base.common.constants.CommonConstants;

@Table(name = "social_org_charge_deputy_type")
public class SocialOrgChargeDeputyType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 社会组织信息ID
     */
    @Column(name = "social_org_info_id")
    private Integer socialOrgInfoId;

    /**
     * 负责人信息id
     */
    @Column(name = "social_org_charge_info_id")
    private Integer socialOrgChargeInfoId;

    /**
     * 党代表类型
     */
    @LogShowName(value="党代表类型",dmm=CommonConstants.PARTY_DEPUTY_TYPE)
    @Column(name = "deputy_type")
    private String deputyType;

    /**
     * 类型为其他时，录入的内容
     */
    @LogShowName("类型为其他时录入的内容")
    @Column(name = "deputy_type_other")
    private String deputyTypeOther;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取社会组织信息ID
     *
     * @return social_org_info_id - 社会组织信息ID
     */
    public Integer getSocialOrgInfoId() {
        return socialOrgInfoId;
    }

    /**
     * 设置社会组织信息ID
     *
     * @param socialOrgInfoId 社会组织信息ID
     */
    public void setSocialOrgInfoId(Integer socialOrgInfoId) {
        this.socialOrgInfoId = socialOrgInfoId;
    }

    /**
     * 获取负责人信息id
     *
     * @return social_org_charge_info_id - 负责人信息id
     */
    public Integer getSocialOrgChargeInfoId() {
        return socialOrgChargeInfoId;
    }

    /**
     * 设置负责人信息id
     *
     * @param socialOrgChargeInfoId 负责人信息id
     */
    public void setSocialOrgChargeInfoId(Integer socialOrgChargeInfoId) {
        this.socialOrgChargeInfoId = socialOrgChargeInfoId;
    }

    /**
     * 获取党代表类型
     *
     * @return deputy_type - 党代表类型
     */
    public String getDeputyType() {
        return deputyType;
    }

    /**
     * 设置党代表类型
     *
     * @param deputyType 党代表类型
     */
    public void setDeputyType(String deputyType) {
        this.deputyType = deputyType;
    }

    /**
     * 获取类型为其他时，录入的内容
     *
     * @return deputy_type_other - 类型为其他时，录入的内容
     */
    public String getDeputyTypeOther() {
        return deputyTypeOther;
    }

    /**
     * 设置类型为其他时，录入的内容
     *
     * @param deputyTypeOther 类型为其他时，录入的内容
     */
    public void setDeputyTypeOther(String deputyTypeOther) {
        this.deputyTypeOther = deputyTypeOther;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", socialOrgInfoId=").append(socialOrgInfoId);
        sb.append(", socialOrgChargeInfoId=").append(socialOrgChargeInfoId);
        sb.append(", deputyType=").append(deputyType);
        sb.append(", deputyTypeOther=").append(deputyTypeOther);
        sb.append("]");
        return sb.toString();
    }
}