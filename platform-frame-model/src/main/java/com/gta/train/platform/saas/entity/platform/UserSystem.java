package com.gta.train.platform.saas.entity.platform;

import com.gta.train.platform.persis.mybatis.model.Entity;

import javax.persistence.Column;
import javax.persistence.Table;


/**
 * @author: huan.xu
 * @version: 1.0
 * @since 2019-04-28
 */
@Table(name = "t_user_system")
public class UserSystem extends Entity {
	private static final long serialVersionUID = 1L;

	    /**平台用户id*/
	@Column(name = "user_id")
	private String userId;

	    /**系统id*/
	@Column(name = "system_id")
	private String systemId;

	    /**是否收藏：0否，1是*/
	@Column(name = "is_collect")
	private Integer isCollect;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getSystemId() {
		return systemId;
	}

	public void setSystemId(String systemId) {
		this.systemId = systemId;
	}

	public Integer getIsCollect() {
		return isCollect;
	}

	public void setIsCollect(Integer isCollect) {
		this.isCollect = isCollect;
	}
}