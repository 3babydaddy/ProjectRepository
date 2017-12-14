package com.tf.base.socialorg.domain;

import javax.persistence.*;

@Table(name = "social_org_pmbr_count")
public class SocialOrgPmbrCount {
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
     * 纳入社会组织党组织管理的党员总数
     */
    @Column(name = "partymbr_in_socielorg_num")
    private Integer partymbrInSocielorgNum;

    /**
     * 组织关系在社会组织党组织的党员数
     */
    @Column(name = "partymbr_group_in_socielorg_num")
    private Integer partymbrGroupInSocielorgNum;

    /**
     * 35岁以下
     */
    @Column(name = "partymbr_under_thirtyfive_num")
    private Integer partymbrUnderThirtyfiveNum;

    /**
     * 大学及以上学历
     */
    @Column(name = "partymbr_on_college_num")
    private Integer partymbrOnCollegeNum;

    /**
     * 高中及以下学历
     */
    @Column(name = "partymbr_under_highschool_num")
    private Integer partymbrUnderHighschoolNum;

    /**
     * 是否首次 1.是 0.否
     */
    @Column(name = "init_is")
    private String initIs;

    @Transient
    private Integer partymbrGroupNotInSocielorgNum;
    
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
     * 获取纳入社会组织党组织管理的党员总数
     *
     * @return partymbr_in_socielorg_num - 纳入社会组织党组织管理的党员总数
     */
    public Integer getPartymbrInSocielorgNum() {
        return partymbrInSocielorgNum;
    }

    /**
     * 设置纳入社会组织党组织管理的党员总数
     *
     * @param partymbrInSocielorgNum 纳入社会组织党组织管理的党员总数
     */
    public void setPartymbrInSocielorgNum(Integer partymbrInSocielorgNum) {
        this.partymbrInSocielorgNum = partymbrInSocielorgNum;
    }

    /**
     * 获取组织关系在社会组织党组织的党员数
     *
     * @return partymbr_group_in_socielorg_num - 组织关系在社会组织党组织的党员数
     */
    public Integer getPartymbrGroupInSocielorgNum() {
        return partymbrGroupInSocielorgNum;
    }

    /**
     * 设置组织关系在社会组织党组织的党员数
     *
     * @param partymbrGroupInSocielorgNum 组织关系在社会组织党组织的党员数
     */
    public void setPartymbrGroupInSocielorgNum(Integer partymbrGroupInSocielorgNum) {
        this.partymbrGroupInSocielorgNum = partymbrGroupInSocielorgNum;
    }

    /**
     * 获取35岁以下
     *
     * @return partymbr_under_thirtyfive_num - 35岁以下
     */
    public Integer getPartymbrUnderThirtyfiveNum() {
        return partymbrUnderThirtyfiveNum;
    }

    /**
     * 设置35岁以下
     *
     * @param partymbrUnderThirtyfiveNum 35岁以下
     */
    public void setPartymbrUnderThirtyfiveNum(Integer partymbrUnderThirtyfiveNum) {
        this.partymbrUnderThirtyfiveNum = partymbrUnderThirtyfiveNum;
    }

    /**
     * 获取大学及以上学历
     *
     * @return partymbr_on_college_num - 大学及以上学历
     */
    public Integer getPartymbrOnCollegeNum() {
        return partymbrOnCollegeNum;
    }

    /**
     * 设置大学及以上学历
     *
     * @param partymbrOnCollegeNum 大学及以上学历
     */
    public void setPartymbrOnCollegeNum(Integer partymbrOnCollegeNum) {
        this.partymbrOnCollegeNum = partymbrOnCollegeNum;
    }

    /**
     * 获取高中及以下学历
     *
     * @return partymbr_under_highschool_num - 高中及以下学历
     */
    public Integer getPartymbrUnderHighschoolNum() {
        return partymbrUnderHighschoolNum;
    }

    /**
     * 设置高中及以下学历
     *
     * @param partymbrUnderHighschoolNum 高中及以下学历
     */
    public void setPartymbrUnderHighschoolNum(Integer partymbrUnderHighschoolNum) {
        this.partymbrUnderHighschoolNum = partymbrUnderHighschoolNum;
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

    public Integer getPartymbrGroupNotInSocielorgNum() {
		return partymbrGroupNotInSocielorgNum;
	}

	public void setPartymbrGroupNotInSocielorgNum(Integer partymbrGroupNotInSocielorgNum) {
		this.partymbrGroupNotInSocielorgNum = partymbrGroupNotInSocielorgNum;
	}

	@Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", socialOrgInfoId=").append(socialOrgInfoId);
        sb.append(", partymbrInSocielorgNum=").append(partymbrInSocielorgNum);
        sb.append(", partymbrGroupInSocielorgNum=").append(partymbrGroupInSocielorgNum);
        sb.append(", partymbrUnderThirtyfiveNum=").append(partymbrUnderThirtyfiveNum);
        sb.append(", partymbrOnCollegeNum=").append(partymbrOnCollegeNum);
        sb.append(", partymbrUnderHighschoolNum=").append(partymbrUnderHighschoolNum);
        sb.append(", initIs=").append(initIs);
        sb.append("]");
        return sb.toString();
    }
}