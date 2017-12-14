package com.tf.base.unpublic.domain;

import javax.persistence.*;

import com.tf.base.common.annotation.LogShowName;
import com.tf.base.common.constants.CommonConstants;

@Table(name = "unpublic_org_address_info")
public class UnpublicOrgAddressInfo {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 非公信息ID
     */
    @Column(name = "unpublic_org_info_id")
    private Integer unpublicOrgInfoId;

    /**
     * 企业经营地
     */
    @LogShowName("企业经营地")
    @Column(name = "operate_address")
    private String operateAddress;

    /**
     * 经营地-地区等级
     */
    @LogShowName(value="经营地区域",dmm=CommonConstants.ADDRESS_LEVEL)
    @Column(name = "operate_address_level")
    private String operateAddressLevel;

    /**
     * 经营地-省
     */
    @Column(name = "operate_address_province")
    private String operateAddressProvince;

    /**
     * 经营地-市
     */
    @Column(name = "operate_address_city")
    private String operateAddressCity;

    /**
     * 经营地-区
     */
    @Column(name = "operate_address_district")
    private String operateAddressDistrict;

    /**
     * 经营地-街道
     */
    @Column(name = "operate_address_street")
    private String operateAddressStreet;

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
     * 获取非公信息ID
     *
     * @return unpublic_org_info_id - 非公信息ID
     */
    public Integer getUnpublicOrgInfoId() {
        return unpublicOrgInfoId;
    }

    /**
     * 设置非公信息ID
     *
     * @param unpublicOrgInfoId 非公信息ID
     */
    public void setUnpublicOrgInfoId(Integer unpublicOrgInfoId) {
        this.unpublicOrgInfoId = unpublicOrgInfoId;
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
     * 获取经营地-地区等级
     *
     * @return operate_address_level - 经营地-地区等级
     */
    public String getOperateAddressLevel() {
        return operateAddressLevel;
    }

    /**
     * 设置经营地-地区等级
     *
     * @param operateAddressLevel 经营地-地区等级
     */
    public void setOperateAddressLevel(String operateAddressLevel) {
        this.operateAddressLevel = operateAddressLevel;
    }

    /**
     * 获取经营地-省
     *
     * @return operate_address_province - 经营地-省
     */
    public String getOperateAddressProvince() {
        return operateAddressProvince;
    }

    /**
     * 设置经营地-省
     *
     * @param operateAddressProvince 经营地-省
     */
    public void setOperateAddressProvince(String operateAddressProvince) {
        this.operateAddressProvince = operateAddressProvince;
    }

    /**
     * 获取经营地-市
     *
     * @return operate_address_city - 经营地-市
     */
    public String getOperateAddressCity() {
        return operateAddressCity;
    }

    /**
     * 设置经营地-市
     *
     * @param operateAddressCity 经营地-市
     */
    public void setOperateAddressCity(String operateAddressCity) {
        this.operateAddressCity = operateAddressCity;
    }

    /**
     * 获取经营地-区
     *
     * @return operate_address_district - 经营地-区
     */
    public String getOperateAddressDistrict() {
        return operateAddressDistrict;
    }

    /**
     * 设置经营地-区
     *
     * @param operateAddressDistrict 经营地-区
     */
    public void setOperateAddressDistrict(String operateAddressDistrict) {
        this.operateAddressDistrict = operateAddressDistrict;
    }

    /**
     * 获取经营地-街道
     *
     * @return operate_address_street - 经营地-街道
     */
    public String getOperateAddressStreet() {
        return operateAddressStreet;
    }

    /**
     * 设置经营地-街道
     *
     * @param operateAddressStreet 经营地-街道
     */
    public void setOperateAddressStreet(String operateAddressStreet) {
        this.operateAddressStreet = operateAddressStreet;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", unpublicOrgInfoId=").append(unpublicOrgInfoId);
        sb.append(", operateAddress=").append(operateAddress);
        sb.append(", operateAddressLevel=").append(operateAddressLevel);
        sb.append(", operateAddressProvince=").append(operateAddressProvince);
        sb.append(", operateAddressCity=").append(operateAddressCity);
        sb.append(", operateAddressDistrict=").append(operateAddressDistrict);
        sb.append(", operateAddressStreet=").append(operateAddressStreet);
        sb.append("]");
        return sb.toString();
    }
}