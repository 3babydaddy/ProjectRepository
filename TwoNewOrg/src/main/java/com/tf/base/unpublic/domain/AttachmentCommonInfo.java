package com.tf.base.unpublic.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "attachment_common_info")
public class AttachmentCommonInfo {
	/**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
    /**
     * 党组织或年度党建表id
     */
    @Column(name = "main_table_id")
    private Integer mainTableId;
    /**
     * 有效模块 1.社会组织 2.非公有制
     */
    private String modular;
    /**
     * 类型 1.党组织 2.年度党建
     */
    private String type;
    /**
     *操作 1.撤销 2.换届 3.整顿报告 4.成立
     */
    private String action;
    /**
     * 文件名称
     */
    private String filename;
    /**
     * 保存文件的名称
     */
    @Column(name = "save_name")
    private String saveName;
    /**
     * 路径
     */
    private String pathname;
    /**
     * 上传时间
     */
    @Column(name = "upload_time")
    private Date uploadTime;
    /**
     * 上传者
     */
    private String uploader;
    /**
     * 状态 1.有效 2.无效
     */
    private Integer status;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", mainTableId=").append(mainTableId);
        sb.append(", modular=").append(modular);
        sb.append(", type=").append(type);
        sb.append(", action=").append(action);
        sb.append(", filename=").append(filename);
        sb.append(", pathname=").append(pathname);
        sb.append(", uploadTime=").append(uploadTime);
        sb.append(", uploader=").append(uploader);
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

    public Integer getMainTableId() {
        return mainTableId;
    }

    public void setMainTableId(Integer mainTableId) {
        this.mainTableId = mainTableId;
    }

    public String getModular() {
        return modular;
    }

    public void setModular(String modular) {
        this.modular = modular == null ? null : modular.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action == null ? null : action.trim();
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename == null ? null : filename.trim();
    }

    public String getPathname() {
        return pathname;
    }

    public void setPathname(String pathname) {
        this.pathname = pathname == null ? null : pathname.trim();
    }

    public Date getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }

    public String getUploader() {
        return uploader;
    }

    public void setUploader(String uploader) {
        this.uploader = uploader == null ? null : uploader.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

	public String getSaveName() {
		return saveName;
	}

	public void setSaveName(String saveName) {
		this.saveName = saveName;
	}

}