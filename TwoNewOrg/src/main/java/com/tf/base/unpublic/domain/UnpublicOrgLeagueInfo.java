package com.tf.base.unpublic.domain;

import javax.persistence.*;

import com.tf.base.common.annotation.LogShowName;
import com.tf.base.common.constants.CommonConstants;

@Table(name = "unpublic_org_league_info")
public class UnpublicOrgLeagueInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "unpublic_org_info_id")
    private Integer unpublicOrgInfoId;

    /**
     * 工会 1.有 0 无
     */
    @LogShowName(value="工会",dmm=CommonConstants.HAS_NOT)
    @Column(name = "has_sociaty")
    private String hasSociaty;

    /**
     * 共青团 1 有 0.无 
     */
    @LogShowName(value="共青团",dmm=CommonConstants.HAS_NOT)
    @Column(name = "has_youth_league")
    private String hasYouthLeague;

    /**
     * 妇联  1.有 0.无
     */
    @LogShowName(value="妇联",dmm=CommonConstants.HAS_NOT)
    @Column(name = "has_women_league")
    private String hasWomenLeague;
    
    @Transient
    private String hasSociatyTxt;
    @Transient
    private String hasYouthLeagueTxt;
    @Transient
    private String hasWomenLeagueTxt;

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
     * 获取工会 1.有 0 无
     *
     * @return has_sociaty - 工会 1.有 0 无
     */
    public String getHasSociaty() {
        return hasSociaty;
    }

    /**
     * 设置工会 1.有 0 无
     *
     * @param hasSociaty 工会 1.有 0 无
     */
    public void setHasSociaty(String hasSociaty) {
        this.hasSociaty = hasSociaty;
    }

    /**
     * 获取共青团 1 有 0.无 
     *
     * @return has_youth_league - 共青团 1 有 0.无 
     */
    public String getHasYouthLeague() {
        return hasYouthLeague;
    }

    /**
     * 设置共青团 1 有 0.无 
     *
     * @param hasYouthLeague 共青团 1 有 0.无 
     */
    public void setHasYouthLeague(String hasYouthLeague) {
        this.hasYouthLeague = hasYouthLeague;
    }

    /**
     * 获取妇联  1.有 0.无
     *
     * @return has_women_league - 妇联  1.有 0.无
     */
    public String getHasWomenLeague() {
        return hasWomenLeague;
    }

    /**
     * 设置妇联  1.有 0.无
     *
     * @param hasWomenLeague 妇联  1.有 0.无
     */
    public void setHasWomenLeague(String hasWomenLeague) {
        this.hasWomenLeague = hasWomenLeague;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", unpublicOrgInfoId=").append(unpublicOrgInfoId);
        sb.append(", hasSociaty=").append(hasSociaty);
        sb.append(", hasYouthLeague=").append(hasYouthLeague);
        sb.append(", hasWomenLeague=").append(hasWomenLeague);
        sb.append("]");
        return sb.toString();
    }

	public String getHasSociatyTxt() {
		return hasSociatyTxt;
	}

	public void setHasSociatyTxt(String hasSociatyTxt) {
		this.hasSociatyTxt = hasSociatyTxt;
	}

	public String getHasYouthLeagueTxt() {
		return hasYouthLeagueTxt;
	}

	public void setHasYouthLeagueTxt(String hasYouthLeagueTxt) {
		this.hasYouthLeagueTxt = hasYouthLeagueTxt;
	}

	public String getHasWomenLeagueTxt() {
		return hasWomenLeagueTxt;
	}

	public void setHasWomenLeagueTxt(String hasWomenLeagueTxt) {
		this.hasWomenLeagueTxt = hasWomenLeagueTxt;
	}
    
}