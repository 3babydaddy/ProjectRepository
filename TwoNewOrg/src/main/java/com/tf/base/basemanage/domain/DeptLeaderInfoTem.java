package com.tf.base.basemanage.domain;

import java.util.Date;
import javax.persistence.*;

import com.tf.base.common.annotation.LogShowName;
import com.tf.base.common.constants.CommonConstants;

@Table(name = "dept_leader_info_tem")
public class DeptLeaderInfoTem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 部门ID
     */
    @Column(name = "dept_id")
    private String deptId;

    @LogShowName("姓名")
    private String name;

    /**
     * 职务
     */
    @LogShowName(value="职务")
    private String post;

    /**
     * 职级
     */
    @LogShowName(value="职级",dmm=CommonConstants.POST_LEVEL)
    @Column(name = "post_level")
    private String postLevel;

    /**
     * 联系方式 默认:手机号
     */
    @LogShowName(value="工作手机号")
    @Column(name = "contact_tel")
    private String contactTel;

    /**
     * 类型
     */
    @LogShowName(value="类型",dmm=CommonConstants.LEADER_LEVEL)
    private String type;

    @Column(name = "create_time")
    private Date createTime;

    private String creator;

    /**
     * 是否删除1.是 0.否
     */
    private String del;
    
    /**
     * 状态 1.临时保存  2.正常, 3.待审核, 4.删除申请  0.已删除
     */
    @LogShowName(value="状态",dmm=CommonConstants.STATUS)
    private String status;
    
    @Transient
    private String deptName;

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
     * 获取部门ID
     *
     * @return dept_id - 部门ID
     */
    public String getDeptId() {
        return deptId;
    }

    /**
     * 设置部门ID
     *
     * @param deptId 部门ID
     */
    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    /**
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取职务
     *
     * @return post - 职务
     */
    public String getPost() {
        return post;
    }

    /**
     * 设置职务
     *
     * @param post 职务
     */
    public void setPost(String post) {
        this.post = post;
    }

    /**
     * 获取职级
     *
     * @return post_level - 职级
     */
    public String getPostLevel() {
        return postLevel;
    }

    /**
     * 设置职级
     *
     * @param postLevel 职级
     */
    public void setPostLevel(String postLevel) {
        this.postLevel = postLevel;
    }

    /**
     * 获取联系方式 默认:手机号
     *
     * @return contact_tel - 联系方式 默认:手机号
     */
    public String getContactTel() {
        return contactTel;
    }

    /**
     * 设置联系方式 默认:手机号
     *
     * @param contactTel 联系方式 默认:手机号
     */
    public void setContactTel(String contactTel) {
        this.contactTel = contactTel;
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
     * 获取是否删除1.是 0.否
     *
     * @return del - 是否删除1.是 0.否
     */
    public String getDel() {
        return del;
    }

    /**
     * 设置是否删除1.是 0.否
     *
     * @param del 是否删除1.是 0.否
     */
    public void setDel(String del) {
        this.del = del;
    }
    /**
     * 状态 1.临时保存  2.正常, 3.待审核, 4.删除申请  0.已删除
     * @return
     */
    public String getStatus() {
		return status;
	}

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
        sb.append(", deptId=").append(deptId);
        sb.append(", name=").append(name);
        sb.append(", post=").append(post);
        sb.append(", postLevel=").append(postLevel);
        sb.append(", contactTel=").append(contactTel);
        sb.append(", type=").append(type);
        sb.append(", createTime=").append(createTime);
        sb.append(", creator=").append(creator);
        sb.append(", del=").append(del);
        sb.append(", status=").append(status);
        sb.append("]");
        return sb.toString();
    }

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
}