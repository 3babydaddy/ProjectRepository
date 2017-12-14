package com.tf.base.socialorg.domain;

import javax.persistence.*;

@Table(name = "social_org_info_other_count")
public class SocialOrgInfoOtherCount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "social_org_info_id")
    private Integer socialOrgInfoId;

    @Column(name = "field_name")
    private String fieldName;

    @Column(name = "field_value")
    private Integer fieldValue;

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
     * @return field_name
     */
    public String getFieldName() {
        return fieldName;
    }

    /**
     * @param fieldName
     */
    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    /**
     * @return field_value
     */
    public Integer getFieldValue() {
        return fieldValue;
    }

    /**
     * @param fieldValue
     */
    public void setFieldValue(Integer fieldValue) {
        this.fieldValue = fieldValue;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", socialOrgInfoId=").append(socialOrgInfoId);
        sb.append(", fieldName=").append(fieldName);
        sb.append(", fieldValue=").append(fieldValue);
        sb.append("]");
        return sb.toString();
    }
}