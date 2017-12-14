package com.tf.base.unpublic.domain;

import javax.persistence.*;

import com.tf.base.common.annotation.LogShowName;
import com.tf.base.common.constants.CommonConstants;

@Table(name = "unpublic_org_sponsor_deputy_type")
public class UnpublicOrgSponsorDeputyType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 非公组织信息ID
     */
    @Column(name = "unpublic_org_info_id")
    private Integer unpublicOrgInfoId;

    /**
     * 主要出资人信息id
     */
    @Column(name = "unpublic_org_sponsor_info_id")
    private Integer unpublicOrgSponsorInfoId;

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
     * 获取非公组织信息ID
     *
     * @return unpublic_org_info_id - 非公组织信息ID
     */
    public Integer getUnpublicOrgInfoId() {
        return unpublicOrgInfoId;
    }

    /**
     * 设置非公组织信息ID
     *
     * @param unpublicOrgInfoId 非公组织信息ID
     */
    public void setUnpublicOrgInfoId(Integer unpublicOrgInfoId) {
        this.unpublicOrgInfoId = unpublicOrgInfoId;
    }

    /**
     * 获取主要出资人信息id
     *
     * @return unpublic_org_sponsor_info_id - 主要出资人信息id
     */
    public Integer getUnpublicOrgSponsorInfoId() {
        return unpublicOrgSponsorInfoId;
    }

    /**
     * 设置主要出资人信息id
     *
     * @param unpublicOrgSponsorInfoId 主要出资人信息id
     */
    public void setUnpublicOrgSponsorInfoId(Integer unpublicOrgSponsorInfoId) {
        this.unpublicOrgSponsorInfoId = unpublicOrgSponsorInfoId;
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
        sb.append(", unpublicOrgInfoId=").append(unpublicOrgInfoId);
        sb.append(", unpublicOrgSponsorInfoId=").append(unpublicOrgSponsorInfoId);
        sb.append(", deputyType=").append(deputyType);
        sb.append(", deputyTypeOther=").append(deputyTypeOther);
        sb.append("]");
        return sb.toString();
    }
}