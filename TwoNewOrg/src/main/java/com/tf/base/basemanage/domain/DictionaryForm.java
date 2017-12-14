package com.tf.base.basemanage.domain;

import java.util.List;

import com.tf.base.common.domain.DataDictionary;


public class DictionaryForm  extends DataDictionary{

	private int page;
	private int rows;
	/**
	 * 对外数据字典维护参数
	 */
	private List<String> dmms;
	
	
	
	public List<String> getDmms() {
		return dmms;
	}
	public void setDmms(List<String> dmms) {
		this.dmms = dmms;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	
	
	
	
}
