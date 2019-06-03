package com.gta.train.platform.saas.service.platform.roleManage;

import com.gta.train.platform.common.base.service.BaseCommonService;
import com.gta.train.platform.saas.dto.platform.RoleManageDto;
import com.gta.train.platform.saas.entity.platform.PlatformUserSystemRole;
import com.gta.train.platform.saas.entity.platform.VerificationCode;

import java.util.List;

public interface RoleManageService extends BaseCommonService<VerificationCode> {
    List<RoleManageDto> RoleManageDtoPage(RoleManageDto roleManageDto);
}
