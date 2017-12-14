package com.tf.base.cover.domain;

import java.util.Date;
import javax.persistence.*;

import com.tf.base.common.annotation.LogShowName;
import com.tf.base.common.constants.CommonConstants;

@Table(name = "cover_org_pmbr_info")
public class CoverOrgPmbrInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cover_org_pmbr_id")
    private Integer id;
	/**
	 * 覆盖建党组织信息表id
	 */
    @Column(name = "cover_org_info_id")
    private Integer coverOrgInfoId;

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
     * 是否从覆盖组织转入 1.是 0.否
     */
    @Column(name = "is_from_cover_org")
    private String isFromCoverOrg;
    
    /**
     * 填报单位
     */
    @Column(name = "create_org")
    private String createOrg;

    @Column(name = "create_time")
    private Date createTime;

    private String creater;
    private String status;

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

    public Integer getCoverOrgInfoId() {
		return coverOrgInfoId;
	}

	public void setCoverOrgInfoId(Integer coverOrgInfoId) {
		this.coverOrgInfoId = coverOrgInfoId;
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

    public String getCreater() {
		return creater;
	}

	public void setCreater(String creater) {
		this.creater = creater;
	}

	@Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", coverOrgInfoId=").append(coverOrgInfoId);
        sb.append(", isFromCoverOrg=").append(isFromCoverOrg);
        sb.append(", name=").append(name);
        sb.append(", gender=").append(gender);
        sb.append(", education=").append(education);
        sb.append(", birthday=").append(birthday);
        sb.append(", createOrg=").append(createOrg);
        sb.append(", createTime=").append(createTime);
        sb.append(", creater=").append(creater);
        sb.append("]");
        return sb.toString();
    }

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getIsFromCoverOrg() {
		return isFromCoverOrg;
	}

	public void setIsFromCoverOrg(String isFromCoverOrg) {
		this.isFromCoverOrg = isFromCoverOrg;
	}

	public String getBirthdayTxt() {
		return birthdayTxt;
	}

	public void setBirthdayTxt(String birthdayTxt) {
		this.birthdayTxt = birthdayTxt;
	}
}