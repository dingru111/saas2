package com.gta.train.platform.saas.service.platform.mainhome.impl;

import com.gta.train.platform.common.base.service.impl.BaseCommonServiceImpl;
import com.gta.train.platform.saas.dao.platform.UserSystemDao;
import com.gta.train.platform.saas.dto.platform.UserSystemDto;
import com.gta.train.platform.saas.entity.platform.UserSystem;
import com.gta.train.platform.saas.service.platform.mainhome.UserSystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author huan.xu
 * @version 1.0
 * @className UserSystemServiceImpl
 * @description
 * @date 2019-04-30 9:41
 */
@Service
public class UserSystemServiceImpl extends BaseCommonServiceImpl<UserSystem> implements UserSystemService {

    @Autowired
    private UserSystemDao userSystemDao;
    @Override
    public List<UserSystemDto> userSystemDtoPage(UserSystemDto userSystemDto) {
        return this.userSystemDao.userSystemDtoPage(userSystemDto);
    }
}