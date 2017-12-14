package com.tf.base.unpublic.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "cancel_reason_info")
public class CancelReasonInfo {
	/**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
    /**
     * 业务种类 1.社会党组织 2.非公党组织
     */
    @Column(name = "type")
    private String type;
    /**
     * 党组织id
     */
    @Column(name = "party_org_id")
    private Integer partyOrgId;
    /**
     * 附件ID
     */
    @Column(name = "file_id")
    private String fileId;
    /**
     * 撤销原因
     */
    private String reason;
    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;
    /**
     * 创建人
     */
    private String creator;
    /**
     * 状态
     */
    private String status;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", type=").append(type);
        sb.append(", partyOrgId=").append(partyOrgId);
        sb.append(", fileId=").append(fileId);
        sb.append(", reason=").append(reason);
        sb.append(", creator=").append(creator);
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public Integer getPartyOrgId() {
        return partyOrgId;
    }

    public void setPartyOrgId(Integer partyOrgId) {
        this.partyOrgId = partyOrgId;
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId == null ? null : fileId.trim();
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason == null ? null : reason.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }
}