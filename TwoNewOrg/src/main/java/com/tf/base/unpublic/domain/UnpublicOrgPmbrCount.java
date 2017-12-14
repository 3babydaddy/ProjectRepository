package com.tf.base.unpublic.domain;

import javax.persistence.*;

@Table(name = "unpublic_org_pmbr_count")
public class UnpublicOrgPmbrCount {
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
     * 组织关系在非公企业的党员总数
     */
    @Column(name = "partymbr_in_unpublic_num")
    private Integer partymbrInUnpublicNum;

    /**
     * 35岁以下人数
     */
    @Column(name = "partymbr_under_thirtyfive_num")
    private Integer partymbrUnderThirtyfiveNum;

    /**
     * 中层管理人员人数
     */
    @Column(name = "partymbr_middle_manager_num")
    private Integer partymbrMiddleManagerNum;

    /**
     * 中高级专业技术人员人数
     */
    @Column(name = "partymbr_on_middletech_num")
    private Integer partymbrOnMiddletechNum;

    /**
     * 生产经营一线职工人数
     */
    @Column(name = "partymbr_frontline_num")
    private Integer partymbrFrontlineNum;

    /**
     * 组织关系不在非公企业的党员总数
     */
    @Column(name = "partymbr_notin_unpublic_num")
    private Integer partymbrNotinUnpublicNum;

    /**
     * 农村党员数
     */
    @Column(name = "partymbr_in_village_num")
    private Integer partymbrInVillageNum;

    /**
     * 是否首次 1.是 0.否
     */
    @Column(name = "init_is")
    private String initIs;

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
     * 获取组织关系在非公企业的党员总数
     *
     * @return partymbr_in_unpublic_num - 组织关系在非公企业的党员总数
     */
    public Integer getPartymbrInUnpublicNum() {
        return partymbrInUnpublicNum;
    }

    /**
     * 设置组织关系在非公企业的党员总数
     *
     * @param partymbrInUnpublicNum 组织关系在非公企业的党员总数
     */
    public void setPartymbrInUnpublicNum(Integer partymbrInUnpublicNum) {
        this.partymbrInUnpublicNum = partymbrInUnpublicNum;
    }

    /**
     * 获取35岁以下人数
     *
     * @return partymbr_under_thirtyfive_num - 35岁以下人数
     */
    public Integer getPartymbrUnderThirtyfiveNum() {
        return partymbrUnderThirtyfiveNum;
    }

    /**
     * 设置35岁以下人数
     *
     * @param partymbrUnderThirtyfiveNum 35岁以下人数
     */
    public void setPartymbrUnderThirtyfiveNum(Integer partymbrUnderThirtyfiveNum) {
        this.partymbrUnderThirtyfiveNum = partymbrUnderThirtyfiveNum;
    }

    /**
     * 获取中层管理人员人数
     *
     * @return partymbr_middle_manager_num - 中层管理人员人数
     */
    public Integer getPartymbrMiddleManagerNum() {
        return partymbrMiddleManagerNum;
    }

    /**
     * 设置中层管理人员人数
     *
     * @param partymbrMiddleManagerNum 中层管理人员人数
     */
    public void setPartymbrMiddleManagerNum(Integer partymbrMiddleManagerNum) {
        this.partymbrMiddleManagerNum = partymbrMiddleManagerNum;
    }

    /**
     * 获取中高级专业技术人员人数
     *
     * @return partymbr_on_middletech_num - 中高级专业技术人员人数
     */
    public Integer getPartymbrOnMiddletechNum() {
        return partymbrOnMiddletechNum;
    }

    /**
     * 设置中高级专业技术人员人数
     *
     * @param partymbrOnMiddletechNum 中高级专业技术人员人数
     */
    public void setPartymbrOnMiddletechNum(Integer partymbrOnMiddletechNum) {
        this.partymbrOnMiddletechNum = partymbrOnMiddletechNum;
    }

    /**
     * 获取生产经营一线职工人数
     *
     * @return partymbr_frontline_num - 生产经营一线职工人数
     */
    public Integer getPartymbrFrontlineNum() {
        return partymbrFrontlineNum;
    }

    /**
     * 设置生产经营一线职工人数
     *
     * @param partymbrFrontlineNum 生产经营一线职工人数
     */
    public void setPartymbrFrontlineNum(Integer partymbrFrontlineNum) {
        this.partymbrFrontlineNum = partymbrFrontlineNum;
    }

    /**
     * 获取组织关系不在非公企业的党员总数
     *
     * @return partymbr_notin_unpublic_num - 组织关系不在非公企业的党员总数
     */
    public Integer getPartymbrNotinUnpublicNum() {
        return partymbrNotinUnpublicNum;
    }

    /**
     * 设置组织关系不在非公企业的党员总数
     *
     * @param partymbrNotinUnpublicNum 组织关系不在非公企业的党员总数
     */
    public void setPartymbrNotinUnpublicNum(Integer partymbrNotinUnpublicNum) {
        this.partymbrNotinUnpublicNum = partymbrNotinUnpublicNum;
    }

    /**
     * 获取农村党员数
     *
     * @return partymbr_in_village_num - 农村党员数
     */
    public Integer getPartymbrInVillageNum() {
        return partymbrInVillageNum;
    }

    /**
     * 设置农村党员数
     *
     * @param partymbrInVillageNum 农村党员数
     */
    public void setPartymbrInVillageNum(Integer partymbrInVillageNum) {
        this.partymbrInVillageNum = partymbrInVillageNum;
    }

    /**
     * 获取是否首次 1.是 0.否
     *
     * @return init_is - 是否首次 1.是 0.否
     */
    public String getInitIs() {
        return initIs;
    }

    /**
     * 设置是否首次 1.是 0.否
     *
     * @param initIs 是否首次 1.是 0.否
     */
    public void setInitIs(String initIs) {
        this.initIs = initIs;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", unpublicOrgInfoId=").append(unpublicOrgInfoId);
        sb.append(", partymbrInUnpublicNum=").append(partymbrInUnpublicNum);
        sb.append(", partymbrUnderThirtyfiveNum=").append(partymbrUnderThirtyfiveNum);
        sb.append(", partymbrMiddleManagerNum=").append(partymbrMiddleManagerNum);
        sb.append(", partymbrOnMiddletechNum=").append(partymbrOnMiddletechNum);
        sb.append(", partymbrFrontlineNum=").append(partymbrFrontlineNum);
        sb.append(", partymbrNotinUnpublicNum=").append(partymbrNotinUnpublicNum);
        sb.append(", partymbrInVillageNum=").append(partymbrInVillageNum);
        sb.append(", initIs=").append(initIs);
        sb.append("]");
        return sb.toString();
    }
}