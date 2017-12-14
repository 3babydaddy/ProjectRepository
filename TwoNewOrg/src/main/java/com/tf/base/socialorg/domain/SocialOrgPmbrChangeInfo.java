package com.tf.base.socialorg.domain;

import java.util.Date;
import javax.persistence.*;

import com.tf.base.common.annotation.LogShowName;
import com.tf.base.common.constants.CommonConstants;

@Table(name = "social_org_pmbr_change_info")
public class SocialOrgPmbrChangeInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "social_org_info_id")
    private Integer socialOrgInfoId;

    @Column(name = "social_org_partymbr_info_id")
    private Integer socialOrgPartymbrInfoId;

    /**
     * 去向
     */
    @LogShowName("去向")
    private String gowhere;

    /**
     * 类型
     */
    @LogShowName(value="类型",dmm=CommonConstants.PARTY_MBR_CHANGE_TYPE)
    private String type;

    /**
     * 填报单位
     */
    @Column(name = "create_org")
    private String createOrg;

    /**
     * 联系方式
     */
    private String contact;

    @Column(name = "create_time")
    private Date createTime;

    private String creator;

    /**
     * 1.增加 2.减少
     */
    private String status;

    private String description;
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
     * @return social_org_partymbr_info_id
     */
    public Integer getSocialOrgPartymbrInfoId() {
        return socialOrgPartymbrInfoId;
    }

    /**
     * @param socialOrgPartymbrInfoId
     */
    public void setSocialOrgPartymbrInfoId(Integer socialOrgPartymbrInfoId) {
        this.socialOrgPartymbrInfoId = socialOrgPartymbrInfoId;
    }

    /**
     * 获取去向
     *
     * @return gowhere - 去向
     */
    public String getGowhere() {
        return gowhere;
    }

    /**
     * 设置去向
     *
     * @param gowhere 去向
     */
    public void setGowhere(String gowhere) {
        this.gowhere = gowhere;
    }

    /**
     * 获取类型
     *
     * @return type - 类型
     */
    public String getType() {
        return type;
    }

    /**
     * 设置类型
     *
     * @param type 类型
     */
    public void setType(String type) {
        this.type = type;
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
     * 获取联系方式
     *
     * @return contact - 联系方式
     */
    public String getContact() {
        return contact;
    }

    /**
     * 设置联系方式
     *
     * @param contact 联系方式
     */
    public void setContact(String contact) {
        this.contact = contact;
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
     * 获取1.增加 2.减少
     *
     * @return status - 1.增加 2.减少
     */
    public String getStatus() {
        return status;
    }

    /**
     * 设置1.增加 2.减少
     *
     * @param status 1.增加 2.减少
     */
    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", socialOrgInfoId=").append(socialOrgInfoId);
        sb.append(", socialOrgPartymbrInfoId=").append(socialOrgPartymbrInfoId);
        sb.append(", gowhere=").append(gowhere);
        sb.append(", type=").append(type);
        sb.append(", createOrg=").append(createOrg);
        sb.append(", contact=").append(contact);
        sb.append(", createTime=").append(createTime);
        sb.append(", creator=").append(creator);
        sb.append(", status=").append(status);
        sb.append(", description=").append(description);
        sb.append("]");
        return sb.toString();
    }
}