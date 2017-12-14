package com.tf.base.socialorg.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Table(name = "social_party_org_change_info")
public class SocialPartyOrgChangeInfo {
	/**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
    /**
     * 非公有制经济组织党组织信息表id
     */
    @Column(name = "social_party_org_id")
    private Integer socialPartyOrgId;

    /**
     * 换届时间
     */
    @Column(name = "change_time")
    private Date changeTime;
    /**
     * 换届相关附件
     */
    @Column(name = "change_attachment_id")
    private Integer changeAttachmentId;
    /**
     * 创建人
     */
    private String creater;
    /**
     * 创建时间
     */ 
    @Column(name = "create_time")
    private Date createTime;
    /**
     * 状态
     */
    private String status;

    @Transient
    private String changeAttachmentName;
    @Transient
    private String changeTimeTxt;
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", socialPartyOrgId=").append(socialPartyOrgId);
        sb.append(", changeTime=").append(changeTime);
        sb.append(", changeAttachmentId=").append(changeAttachmentId);
        sb.append(", creater=").append(creater);
        sb.append(", createTime=").append(createTime);
        sb.append(", status=").append(status);
        sb.append("]");
        return sb.toString();
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSocialPartyOrgId() {
        return socialPartyOrgId;
    }

    public void setSocialPartyOrgId(Integer socialPartyOrgId) {
        this.socialPartyOrgId = socialPartyOrgId;
    }

    public Date getChangeTime() {
        return changeTime;
    }

    public void setChangeTime(Date changeTime) {
        this.changeTime = changeTime;
    }

    public Integer getChangeAttachmentId() {
        return changeAttachmentId;
    }

    public void setChangeAttachmentId(Integer changeAttachmentId) {
        this.changeAttachmentId = changeAttachmentId;
    }

    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater == null ? null : creater.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }
	public String getChangeAttachmentName() {
		return changeAttachmentName;
	}
	public void setChangeAttachmentName(String changeAttachmentName) {
		this.changeAttachmentName = changeAttachmentName;
	}
	public String getChangeTimeTxt() {
		return changeTimeTxt;
	}
	public void setChangeTimeTxt(String changeTimeTxt) {
		this.changeTimeTxt = changeTimeTxt;
	}
}