package com.gta.train.platform.saas.service.base.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gta.train.platform.common.base.service.impl.BaseCommonServiceImpl;
import com.gta.train.platform.saas.dao.base.LoginHistoryMapper;
import com.gta.train.platform.saas.entity.base.LoginHistory;
import com.gta.train.platform.saas.service.base.LoginHistoryService;
@Service("loginHistoryService")
public class LoginHistoryServiceImpl  extends BaseCommonServiceImpl<LoginHistory> implements LoginHistoryService  {
	@Autowired
	LoginHistoryMapper loginHistoryMapper;
 
	public void addLoginHistory(LoginHistory loginHistory) {
		// TODO 自动生成的方法存根
		loginHistoryMapper.insert(loginHistory);
	}
 
}
