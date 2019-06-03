package com.gta.train.platform.persis.mybatis.plugin.baseopt.interfaces;

public interface BaseOptUser extends BaseOptTime{
    /**录入人员id*/
	public void setCreateUserId (String  createUserId);
	/**修改人员id*/
	public void setUpdateUserId (String  updateUserId);
    /**录入人员id*/  
	public String  getCreateUserId();
	/**修改人员id*/  
	public String  getUpdateUserId();
 
}
