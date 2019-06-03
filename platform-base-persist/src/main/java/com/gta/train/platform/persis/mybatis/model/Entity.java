package com.gta.train.platform.persis.mybatis.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import com.gta.train.platform.persis.page.plugin.IPage;
import com.gta.train.platform.persis.page.plugin.Page;


public class Entity     implements Serializable,IPage  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id")
    // @GeneratedValue(generator="UUID")
    @GeneratedValue(strategy = GenerationType.IDENTITY,generator = "select replace(uuid(),'-','') uuid ;")
    private String id;
    @Transient
  	private Page page;
	@Transient
	private Date createTime;
	@Transient
	private Date updateTime;
	@Transient
	public Map map = new HashMap();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Map getMap() {
		return map;
	}

	public void setMap(Map map) {
		this.map = map;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}
}
