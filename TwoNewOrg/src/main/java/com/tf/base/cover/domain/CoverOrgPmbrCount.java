package com.tf.base.cover.domain;

public class CoverOrgPmbrCount {
    /**
     * 主键
     */
    private Integer id;

    /**
     * 从覆盖组织转入党员总数
     */
    private Integer partymbrIncoverNum;

    /**
     * 不从覆盖组织转入党员总数
     */
    private Integer partymbrNotIncoverNum;

    /**
     * 35岁以下
     */
    private Integer partymbrUnderThirtyfiveNum;

    /**
     * 大学及以上学历
     */
    private Integer partymbrOnCollegeNum;

    /**
     * 高中及以下学历
     */
    private Integer partymbrUnderHighschoolNum;

   
    
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

    public Integer getPartymbrIncoverNum() {
		return partymbrIncoverNum;
	}

	public void setPartymbrIncoverNum(Integer partymbrIncoverNum) {
		this.partymbrIncoverNum = partymbrIncoverNum;
	}

	public Integer getPartymbrNotIncoverNum() {
		return partymbrNotIncoverNum;
	}

	public void setPartymbrNotIncoverNum(Integer partymbrNotIncoverNum) {
		this.partymbrNotIncoverNum = partymbrNotIncoverNum;
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

	@Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", partymbrIncoverNum=").append(partymbrIncoverNum);
        sb.append(", partymbrNotIncoverNum=").append(partymbrNotIncoverNum);
        sb.append(", partymbrUnderThirtyfiveNum=").append(partymbrUnderThirtyfiveNum);
        sb.append(", partymbrOnCollegeNum=").append(partymbrOnCollegeNum);
        sb.append(", partymbrUnderHighschoolNum=").append(partymbrUnderHighschoolNum);
        sb.append("]");
        return sb.toString();
    }
}