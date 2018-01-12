package com.tf.base.socialorg.domain;

import java.util.Date;
import javax.persistence.*;

import com.tf.base.common.annotation.LogShowName;
import com.tf.base.common.constants.CommonConstants;

@Table(name = "social_org_pmbr_info")
public class SocialOrgPmbrInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "social_org_info_id")
    private Integer socialOrgInfoId;

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
     * 纳入社会组织党组织管理的党员
     */
    @LogShowName(value="纳入社会组织党组织管理的党员",dmm=CommonConstants.YES_NO)
    @Column(name = "partymbr_in_socialorg_is")
    private String partymbrInSocialorgIs;

    /**
     * 组织关系在社会组织党组织的党员
     */
    @LogShowName(value="组织关系在社会组织党组织的党员",dmm=CommonConstants.YES_NO)
    @Column(name = "partymbr_group_in_socialorg_is")
    private String partymbrGroupInSocialorgIs;

    @Column(name = "create_time")
    private Date createTime;

    private String creator;
    private String status;
    @Transient
    private String type;
    @Transient
    private String birthdayTxt;

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
     * @return social_org_info_id
     */
    public Integer getSocialOrgInfoId() {
        return socialOrgInfoId;
    }

    /**
     * @param socialOrgInfoId
     */
    public void setSocialOrgInfoId(Integer socialOrgInfoId) {
        this.socialOrgInfoId = socialOrgInfoId;
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
     * 获取纳入社会组织党组织管理的党员
     *
     * @return partymbr_in_socialorg_is - 纳入社会组织党组织管理的党员
     */
    public String getPartymbrInSocialorgIs() {
        return partymbrInSocialorgIs;
    }

    /**
     * 设置纳入社会组织党组织管理的党员
     *
     * @param partymbrInSocialorgIs 纳入社会组织党组织管理的党员
     */
    public void setPartymbrInSocialorgIs(String partymbrInSocialorgIs) {
        this.partymbrInSocialorgIs = partymbrInSocialorgIs;
    }

    /**
     * 获取组织关系在社会组织党组织的党员
     *
     * @return partymbr_group_in_socialorg_is - 组织关系在社会组织党组织的党员
     */
    public String getPartymbrGroupInSocialorgIs() {
        return partymbrGroupInSocialorgIs;
    }

    /**
     * 设置组织关系在社会组织党组织的党员
     *
     * @param partymbrGroupInSocialorgIs 组织关系在社会组织党组织的党员
     */
    public void setPartymbrGroupInSocialorgIs(String partymbrGroupInSocialorgIs) {
        this.partymbrGroupInSocialorgIs = partymbrGroupInSocialorgIs;
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
        sb.append(", name=").append(name);
        sb.append(", gender=").append(gender);
        sb.append(", education=").append(education);
        sb.append(", birthday=").append(birthday);
        sb.append(", createOrg=").append(createOrg);
        sb.append(", partymbrInSocialorgIs=").append(partymbrInSocialorgIs);
        sb.append(", partymbrGroupInSocialorgIs=").append(partymbrGroupInSocialorgIs);
        sb.append(", createTime=").append(createTime);
        sb.append(", creator=").append(creator);
        sb.append("]");
        return sb.toString();
    }

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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