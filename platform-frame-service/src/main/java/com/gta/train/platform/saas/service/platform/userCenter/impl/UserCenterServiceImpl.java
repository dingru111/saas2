package com.gta.train.platform.saas.service.platform.userCenter.impl;

import com.gta.train.platform.common.base.service.impl.BaseCommonServiceImpl;
import com.gta.train.platform.saas.dao.system.SystemBaseAccountDao;
import com.gta.train.platform.saas.dto.platform.UserCenterDto;
import com.gta.train.platform.saas.dto.platform.UserCenterScreenDto;
import com.gta.train.platform.saas.entity.system.SystemBaseAccount;
import com.gta.train.platform.saas.service.platform.userCenter.UserCenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userCenterService")
public class UserCenterServiceImpl extends BaseCommonServiceImpl<SystemBaseAccount> implements UserCenterService {
    @Autowired
    private SystemBaseAccountDao systemBaseAccountDao;
    @Override
    public List<UserCenterDto> userCenterDtoPage(UserCenterDto userCenterDto) {
        return this.systemBaseAccountDao.userCenterDtoPage(userCenterDto);
    }
    @Override
    public List<UserCenterScreenDto> userSourse(UserCenterScreenDto userCenterScreenDto){
        return  this.systemBaseAccountDao.userSourse(userCenterScreenDto);
    }
}
