package com.gta.train.platform.persis.mybatis.plugin.baseopt.interfaces;

public interface BaseOptTime {
		/**录入时间*/  
		public java.util.Date  getCreateTime();
		/**修改时间*/  
		public java.util.Date  getUpdateTime();
	    /**录入时间*/
		public void setCreateTime (java.util.Date  createTime);
		/**修改时间*/
		public void setUpdateTime (java.util.Date  updateTime);
}
