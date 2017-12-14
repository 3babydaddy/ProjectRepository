package com.tf.base.common.domain;

import com.tf.base.common.annotation.LogShowName;

public class DataDictionary {
    private Integer id;
    @LogShowName("代码")
    private String dmm;
    @LogShowName("键")	
    private String code;
    @LogShowName("值")
    private String value;

    private Integer level;
    @LogShowName("父代码")
    private String pdmm;

    private String remarks;

    private Integer orders;
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDmm() {
        return dmm;
    }

    public void setDmm(String dmm) {
        this.dmm = dmm == null ? null : dmm.trim();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value == null ? null : value.trim();
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getPdmm() {
        return pdmm;
    }

    public void setPdmm(String pdmm) {
        this.pdmm = pdmm == null ? null : pdmm.trim();
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

	public Integer getOrders() {
		return orders;
	}

	public void setOrders(Integer orders) {
		this.orders = orders;
	}

    
}