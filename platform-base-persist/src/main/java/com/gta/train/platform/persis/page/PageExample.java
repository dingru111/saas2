package com.gta.train.platform.persis.page;

import tk.mybatis.mapper.entity.Example;

public class PageExample extends Example{
	com.gta.train.platform.persis.page.plugin.Page page;
	public PageExample(Class<?> entityClass,com.gta.train.platform.persis.page.plugin.Page page) {
		super(entityClass);
		this.page=page;
		// TODO 自动生成的构造函数存根
	}
	public com.gta.train.platform.persis.page.plugin.Page getPage() {
		return page;
	}
	public void setPage(com.gta.train.platform.persis.page.plugin.Page page) {
		this.page = page;
	}
	
}
