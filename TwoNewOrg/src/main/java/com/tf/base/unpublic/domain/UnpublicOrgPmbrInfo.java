package com.tf.base.unpublic.domain;

import java.util.Date;
import javax.persistence.*;

import com.tf.base.common.annotation.LogShowName;
import com.tf.base.common.constants.CommonConstants;

@Table(name = "unpublic_org_pmbr_info")
public class UnpublicOrgPmbrInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "unpublic_org_info_id")
    private Integer unpublicOrgInfoId;

    /**
     * 姓名
     */
    @LogShowName("姓名")
    private String name;

    /**
     * 性别
     */
    @LogShowName(value="性别",dmm=CommonConstants.GENDER)
    private String gender;

    /**
     * 学历
     */
    @LogShowName(value="学历",dmm=CommonConstants.FINAL_EDUCATION)
    private String education;

    /**
     * 出生日期
     */
    @LogShowName("出生日期")
    private Date birthday;

    /**
     * 填报单位
     */
    @Column(name = "create_org")
    private String createOrg;
    
    /**
     * 现工作单位及地址
     */
    @Column(name = "workunit_and_address")
    private String workunitAndAddress;

    /**
     * 组织关系在非公企业的党员
     */
    @LogShowName(value="组织关系在非公企业",dmm=CommonConstants.YES_NO)
    @Column(name = "partymbr_in_unpublic_is")
    private String partymbrInUnpublicIs;

    /**
     * 中层管理人员
     */
    @LogShowName(value="中层管理人员",dmm=CommonConstants.YES_NO)
    @Column(name = "partymbr_middle_manager_is")
    private String partymbrMiddleManagerIs;

    /**
     * 中高级专业技术人员人数
     */
    @LogShowName(value="中高级专业技术人员",dmm=CommonConstants.YES_NO)
    @Column(name = "partymbr_on_middletech_is")
    private String partymbrOnMiddletechIs;

    /**
     * 生产经营一线职工
     */
    @LogShowName(value="生产经营一线职工",dmm=CommonConstants.YES_NO)
    @Column(name = "partymbr_frontline_is")
    private String partymbrFrontlineIs;

    /**
     * 组织关系不在非公企业的党员
     */
    @LogShowName(value="组织关系不在非公企业",dmm=CommonConstants.YES_NO)
    @Column(name = "partymbr_notin_unpublic_is")
    private String partymbrNotinUnpublicIs;

    /**
     * 农村党员
     */
    @LogShowName(value="农村党员",dmm=CommonConstants.YES_NO)
    @Column(name = "partymbr_in_village_is")
    private String partymbrInVillageIs;

    @Column(name = "create_time")
    private Date createTime;

    private String creator;
    @Transient
    private String type;
    @Transient
    private String birthdayTxt;

    /**
     * 1 有效 0.无效
     */
    private String status;

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
     * @return unpublic_org_info_id
     */
    public Integer getUnpublicOrgInfoId() {
        return unpublicOrgInfoId;
    }

    /**
     * @param unpublicOrgInfoId
     */
    public void setUnpublicOrgInfoId(Integer unpublicOrgInfoId) {
        this.unpublicOrgInfoId = unpublicOrgInfoId;
    }

    /**
     * 获取姓名
     *
     * @return name - 姓名
     */
    public String getName() {
        return name;
    }

    /**
     * 设置姓名
     *
     * @param name 姓名
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取性别
     *
     * @return gender - 性别
     */
    public String getGender() {
        return gender;
    }

    /**
     * 设置性别
     *
     * @param gender 性别
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * 获取学历
     *
     * @return education - 学历
     */
    public String getEducation() {
        return education;
    }

    /**
     * 设置学历
     *
     * @param education 学历
     */
    public void setEducation(String education) {
        this.education = education;
    }

    /**
     * 获取出生日期
     *
     * @return birthday - 出生日期
     */
    public Date getBirthday() {
        return birthday;
    }

    /**
     * 设置出生日期
     *
     * @param birthday 出生日期
     */
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
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

    /**
     * 获取组织关系在非公企业的党员
     *
     * @return partymbr_in_unpublic_is - 组织关系在非公企业的党员
     */
    public String getPartymbrInUnpublicIs() {
        return partymbrInUnpublicIs;
    }

    /**
     * 设置组织关系在非公企业的党员
     *
     * @param partymbrInUnpublicIs 组织关系在非公企业的党员
     */
    public void setPartymbrInUnpublicIs(String partymbrInUnpublicIs) {
        this.partymbrInUnpublicIs = partymbrInUnpublicIs;
    }

    /**
     * 获取中层管理人员
     *
     * @return partymbr_middle_manager_is - 中层管理人员
     */
    public String getPartymbrMiddleManagerIs() {
        return partymbrMiddleManagerIs;
    }

    /**
     * 设置中层管理人员
     *
     * @param partymbrMiddleManagerIs 中层管理人员
     */
    public void setPartymbrMiddleManagerIs(String partymbrMiddleManagerIs) {
        this.partymbrMiddleManagerIs = partymbrMiddleManagerIs;
    }

    /**
     * 获取中高级专业技术人员人数
     *
     * @return partymbr_on_middletech_is - 中高级专业技术人员人数
     */
    public String getPartymbrOnMiddletechIs() {
        return partymbrOnMiddletechIs;
    }

    /**
     * 设置中高级专业技术人员人数
     *
     * @param partymbrOnMiddletechIs 中高级专业技术人员人数
     */
    public void setPartymbrOnMiddletechIs(String partymbrOnMiddletechIs) {
        this.partymbrOnMiddletechIs = partymbrOnMiddletechIs;
    }

    /**
     * 获取生产经营一线职工
     *
     * @return partymbr_frontline_is - 生产经营一线职工
     */
    public String getPartymbrFrontlineIs() {
        return partymbrFrontlineIs;
    }

    /**
     * 设置生产经营一线职工
     *
     * @param partymbrFrontlineIs 生产经营一线职工
     */
    public void setPartymbrFrontlineIs(String partymbrFrontlineIs) {
        this.partymbrFrontlineIs = partymbrFrontlineIs;
    }

    /**
     * 获取组织关系不在非公企业的党员
     *
     * @return partymbr_notin_unpublic_is - 组织关系不在非公企业的党员
     */
    public String getPartymbrNotinUnpublicIs() {
        return partymbrNotinUnpublicIs;
    }

    /**
     * 设置组织关系不在非公企业的党员
     *
     * @param partymbrNotinUnpublicIs 组织关系不在非公企业的党员
     */
    public void setPartymbrNotinUnpublicIs(String partymbrNotinUnpublicIs) {
        this.partymbrNotinUnpublicIs = partymbrNotinUnpublicIs;
    }

    /**
     * 获取农村党员
     *
     * @return partymbr_in_village_is - 农村党员
     */
    public String getPartymbrInVillageIs() {
        return partymbrInVillageIs;
    }

    /**
     * 设置农村党员
     *
     * @param partymbrInVillageIs 农村党员
     */
    public void setPartymbrInVillageIs(String partymbrInVillageIs) {
        this.partymbrInVillageIs = partymbrInVillageIs;
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
     * 获取1 有效 0.无效
     *
     * @return status - 1 有效 0.无效
     */
    public String getStatus() {
        return status;
    }

    /**
     * 设置1 有效 0.无效
     *
     * @param status 1 有效 0.无效
     */
    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", unpublicOrgInfoId=").append(unpublicOrgInfoId);
        sb.append(", name=").append(name);
        sb.append(", gender=").append(gender);
        sb.append(", education=").append(education);
        sb.append(", birthday=").append(birthday);
        sb.append(", createOrg=").append(createOrg);
        sb.append(", partymbrInUnpublicIs=").append(partymbrInUnpublicIs);
        sb.append(", partymbrMiddleManagerIs=").append(partymbrMiddleManagerIs);
        sb.append(", partymbrOnMiddletechIs=").append(partymbrOnMiddletechIs);
        sb.append(", partymbrFrontlineIs=").append(partymbrFrontlineIs);
        sb.append(", partymbrNotinUnpublicIs=").append(partymbrNotinUnpublicIs);
        sb.append(", partymbrInVillageIs=").append(partymbrInVillageIs);
        sb.append(", createTime=").append(createTime);
        sb.append(", creator=").append(creator);
        sb.append(", status=").append(status);
        sb.append("]");
        return sb.toString();
    }

	public String getWorkunitAndAddress() {
		return workunitAndAddress;
	}

	public void setWorkunitAndAddress(String workunitAndAddress) {
		this.workunitAndAddress = workunitAndAddress;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getBirthdayTxt() {
		return birthdayTxt;
	}

	public void setBirthdayTxt(String birthdayTxt) {
		this.birthdayTxt = birthdayTxt;
	}
}