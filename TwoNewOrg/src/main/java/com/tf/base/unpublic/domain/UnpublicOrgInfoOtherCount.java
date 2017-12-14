package com.tf.base.unpublic.domain;

import javax.persistence.*;

@Table(name = "unpublic_org_info_other_count")
public class UnpublicOrgInfoOtherCount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "unpublic_org_info_id")
    private Integer unpublicOrgInfoId;

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
     * @return unpublic_org_info_id
     */
    public Integer getUnpublicOrgInfoId() {
        return unpublicOrgInfoId;
    }

    /**
     * @param unpublicOrgInfoId
     */
    public void setUnpublicOrgInfoId(Integer unpublicOrgInfoId) {
        this.unpublicOrgInfoId = unpublicOrgInfoId;
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
        sb.append(", unpublicOrgInfoId=").append(unpublicOrgInfoId);
        sb.append(", fieldName=").append(fieldName);
        sb.append(", fieldValue=").append(fieldValue);
        sb.append("]");
        return sb.toString();
    }
}