package com.tf.base.socialorg.domain;

import java.util.Date;
import javax.persistence.*;

import com.tf.base.common.annotation.LogShowName;

@Table(name = "social_org_cancel_record")
public class SocialOrgCancelRecord {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 社会组织信息ID
     */
    @Column(name = "social_org_info_id")
    private Integer socialOrgInfoId;

    /**
     * 附件ID
     */
    @Column(name = "file_id")
    private Integer fileId;

    /**
     * 撤销原因
     */
    @LogShowName("撤销原因")
    private String reason;

    @Column(name = "create_time")
    private Date createTime;

    private String creator;

    /**
     * 状态 1.有效 2.无效
     */
    private String status;

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
     * 获取社会组织信息ID
     *
     * @return social_org_info_id - 社会组织信息ID
     */
    public Integer getSocialOrgInfoId() {
        return socialOrgInfoId;
    }

    /**
     * 设置社会组织信息ID
     *
     * @param socialOrgInfoId 社会组织信息ID
     */
    public void setSocialOrgInfoId(Integer socialOrgInfoId) {
        this.socialOrgInfoId = socialOrgInfoId;
    }

    /**
     * 获取附件ID
     *
     * @return file_id - 附件ID
     */
    public Integer getFileId() {
        return fileId;
    }

    /**
     * 设置附件ID
     *
     * @param fileId 附件ID
     */
    public void setFileId(Integer fileId) {
        this.fileId = fileId;
    }

    /**
     * 获取撤销原因
     *
     * @return reason - 撤销原因
     */
    public String getReason() {
        return reason;
    }

    /**
     * 设置撤销原因
     *
     * @param reason 撤销原因
     */
    public void setReason(String reason) {
        this.reason = reason;
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
     * 获取状态 1.有效 2.无效
     *
     * @return status - 状态 1.有效 2.无效
     */
    public String getStatus() {
        return status;
    }

    /**
     * 设置状态 1.有效 2.无效
     *
     * @param status 状态 1.有效 2.无效
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
        sb.append(", socialOrgInfoId=").append(socialOrgInfoId);
        sb.append(", fileId=").append(fileId);
        sb.append(", reason=").append(reason);
        sb.append(", createTime=").append(createTime);
        sb.append(", creator=").append(creator);
        sb.append(", status=").append(status);
        sb.append("]");
        return sb.toString();
    }
}