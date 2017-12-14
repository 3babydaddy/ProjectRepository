package com.tf.base.basemanage.domain;

import javax.persistence.*;

@Table(name = "data_on_off")
public class DataOnOff {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 1.开 0.关
     */
    @Column(name = "on_off")
    private String onOff;

    private String module;

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
     * 获取1.开 0.关
     *
     * @return on_off - 1.开 0.关
     */
    public String getOnOff() {
        return onOff;
    }

    /**
     * 设置1.开 0.关
     *
     * @param onOff 1.开 0.关
     */
    public void setOnOff(String onOff) {
        this.onOff = onOff;
    }

    /**
     * @return module
     */
    public String getModule() {
        return module;
    }

    /**
     * @param module
     */
    public void setModule(String module) {
        this.module = module;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", onOff=").append(onOff);
        sb.append(", module=").append(module);
        sb.append("]");
        return sb.toString();
    }
}