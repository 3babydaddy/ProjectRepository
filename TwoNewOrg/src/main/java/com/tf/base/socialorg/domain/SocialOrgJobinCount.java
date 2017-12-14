package com.tf.base.socialorg.domain;

import java.util.Date;
import javax.persistence.*;

import com.tf.base.common.annotation.LogShowName;
import com.tf.base.common.constants.CommonConstants;

@Table(name = "social_org_jobin_count")
public class SocialOrgJobinCount {
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
     * 从业人员总数
     */
    @LogShowName(value="从业人员总数")
    @Column(name = "jobin_totalnum")
    private Integer jobinTotalnum;

    /**
     * 专职人数
     */
    @LogShowName(value="专职人数")
    @Column(name = "jobin_major_num")
    private Integer jobinMajorNum;

    /**
     * 兼职人数
     */
    @LogShowName(value="兼职人数")
    @Column(name = "jobin_plurality_num")
    private Integer jobinPluralityNum;

    /**
     * 从业中共党员数
     */
    @LogShowName(value="从业中共党员数")
    @Column(name = "jobin_partymember_num")
    private Integer jobinPartymemberNum;

    /**
     * 社会团体单位会员数量
     */
    @LogShowName(value="社会团体单位会员数量")
    @Column(name = "jobin_socialteam_groupmember_num")
    private Integer jobinSocialteamGroupmemberNum;

    /**
     * 社会团体个人会员数量
     */
    @LogShowName(value="社会团体个人会员数量")
    @Column(name = "jobin_socialteam_individualmember_num")
    private Integer jobinSocialteamIndividualmemberNum;

    @Column(name = "create_time")
    private Date createTime;

    private String creator;

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
     * 获取从业人员总数
     *
     * @return jobin_totalnum - 从业人员总数
     */
    public Integer getJobinTotalnum() {
        return jobinTotalnum;
    }

    /**
     * 设置从业人员总数
     *
     * @param jobinTotalnum 从业人员总数
     */
    public void setJobinTotalnum(Integer jobinTotalnum) {
        this.jobinTotalnum = jobinTotalnum;
    }

    /**
     * 获取专职人数
     *
     * @return jobin_major_num - 专职人数
     */
    public Integer getJobinMajorNum() {
        return jobinMajorNum;
    }

    /**
     * 设置专职人数
     *
     * @param jobinMajorNum 专职人数
     */
    public void setJobinMajorNum(Integer jobinMajorNum) {
        this.jobinMajorNum = jobinMajorNum;
    }

    /**
     * 获取兼职人数
     *
     * @return jobin_plurality_num - 兼职人数
     */
    public Integer getJobinPluralityNum() {
        return jobinPluralityNum;
    }

    /**
     * 设置兼职人数
     *
     * @param jobinPluralityNum 兼职人数
     */
    public void setJobinPluralityNum(Integer jobinPluralityNum) {
        this.jobinPluralityNum = jobinPluralityNum;
    }

    /**
     * 获取从业中共党员数
     *
     * @return jobin_partymember_num - 从业中共党员数
     */
    public Integer getJobinPartymemberNum() {
        return jobinPartymemberNum;
    }

    /**
     * 设置从业中共党员数
     *
     * @param jobinPartymemberNum 从业中共党员数
     */
    public void setJobinPartymemberNum(Integer jobinPartymemberNum) {
        this.jobinPartymemberNum = jobinPartymemberNum;
    }

    /**
     * 获取社会团体单位会员数量
     *
     * @return jobin_socialteam_groupmember_num - 社会团体单位会员数量
     */
    public Integer getJobinSocialteamGroupmemberNum() {
        return jobinSocialteamGroupmemberNum;
    }

    /**
     * 设置社会团体单位会员数量
     *
     * @param jobinSocialteamGroupmemberNum 社会团体单位会员数量
     */
    public void setJobinSocialteamGroupmemberNum(Integer jobinSocialteamGroupmemberNum) {
        this.jobinSocialteamGroupmemberNum = jobinSocialteamGroupmemberNum;
    }

    /**
     * 获取社会团体个人会员数量
     *
     * @return jobin_socialteam_individualmember_num - 社会团体个人会员数量
     */
    public Integer getJobinSocialteamIndividualmemberNum() {
        return jobinSocialteamIndividualmemberNum;
    }

    /**
     * 设置社会团体个人会员数量
     *
     * @param jobinSocialteamIndividualmemberNum 社会团体个人会员数量
     */
    public void setJobinSocialteamIndividualmemberNum(Integer jobinSocialteamIndividualmemberNum) {
        this.jobinSocialteamIndividualmemberNum = jobinSocialteamIndividualmemberNum;
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
        sb.append(", jobinTotalnum=").append(jobinTotalnum);
        sb.append(", jobinMajorNum=").append(jobinMajorNum);
        sb.append(", jobinPluralityNum=").append(jobinPluralityNum);
        sb.append(", jobinPartymemberNum=").append(jobinPartymemberNum);
        sb.append(", jobinSocialteamGroupmemberNum=").append(jobinSocialteamGroupmemberNum);
        sb.append(", jobinSocialteamIndividualmemberNum=").append(jobinSocialteamIndividualmemberNum);
        sb.append(", createTime=").append(createTime);
        sb.append(", creator=").append(creator);
        sb.append("]");
        return sb.toString();
    }
}