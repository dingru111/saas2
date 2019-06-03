package com.gta.train.platform.saas.service.platform.roleManage.impl;

import com.gta.train.platform.common.base.service.impl.BaseCommonServiceImpl;
import com.gta.train.platform.saas.dao.system.SystemRoleDao;
import com.gta.train.platform.saas.dto.platform.RoleManageDto;
import com.gta.train.platform.saas.entity.platform.PlatformUserSystemRole;
import com.gta.train.platform.saas.entity.platform.VerificationCode;
import com.gta.train.platform.saas.service.platform.roleManage.RoleManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleManageServiceImpl extends BaseCommonServiceImpl<VerificationCode> implements RoleManageService {

    @Autowired
    private SystemRoleDao systemRoleDao;
    @Override
    public List<RoleManageDto> RoleManageDtoPage(RoleManageDto roleManageDto) {
       return this.systemRoleDao.RoleManageDtoPage(roleManageDto);
        //return null;
    }
}
