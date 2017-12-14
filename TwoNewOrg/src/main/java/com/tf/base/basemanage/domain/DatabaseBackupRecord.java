package com.tf.base.basemanage.domain;

import java.util.Date;
import javax.persistence.*;

@Table(name = "database_backup_record")
public class DatabaseBackupRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 备份时间
     */
    @Column(name = "backup_time")
    private Date backupTime;

    /**
     * 文件名称
     */
    private String filename;

    /**
     * 备份地址
     */
    private String path;
    
    /**
     * 文件大小
     */
    private String filesize;

    @Column(name = "spent_time")
    private String spentTime;

    private String operator;

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
     * 获取备份时间
     *
     * @return backup_time - 备份时间
     */
    public Date getBackupTime() {
        return backupTime;
    }

    /**
     * 设置备份时间
     *
     * @param backupTime 备份时间
     */
    public void setBackupTime(Date backupTime) {
        this.backupTime = backupTime;
    }

    /**
     * 获取文件名称
     *
     * @return filename - 文件名称
     */
    public String getFilename() {
        return filename;
    }

    /**
     * 设置文件名称
     *
     * @param filename 文件名称
     */
    public void setFilename(String filename) {
        this.filename = filename;
    }

    /**
     * 获取备份地址
     *
     * @return path - 备份地址
     */
    public String getPath() {
        return path;
    }

    /**
     * 设置备份地址
     *
     * @param path 备份地址
     */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     * @return spent_time
     */
    public String getSpentTime() {
        return spentTime;
    }

    /**
     * @param spentTime
     */
    public void setSpentTime(String spentTime) {
        this.spentTime = spentTime;
    }

    /**
     * @return operator
     */
    public String getOperator() {
        return operator;
    }

    /**
     * @param operator
     */
    public void setOperator(String operator) {
        this.operator = operator;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", backupTime=").append(backupTime);
        sb.append(", filename=").append(filename);
        sb.append(", path=").append(path);
        sb.append(", spentTime=").append(spentTime);
        sb.append(", operator=").append(operator);
        sb.append("]");
        return sb.toString();
    }

	public String getFilesize() {
		return filesize;
	}

	public void setFilesize(String filesize) {
		this.filesize = filesize;
	}
}