package com.gta.train.platform.saas.service.base;

import com.gta.train.platform.common.base.service.BaseCommonService;
import com.gta.train.platform.saas.entity.base.LoginHistory;

public interface LoginHistoryService extends BaseCommonService<LoginHistory> {
	public void addLoginHistory(LoginHistory loginHistory);

}
