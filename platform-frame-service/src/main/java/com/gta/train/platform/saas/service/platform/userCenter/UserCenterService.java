package com.gta.train.platform.saas.service.platform.userCenter;

import com.gta.train.platform.common.base.service.BaseCommonService;
import com.gta.train.platform.saas.dto.platform.UserCenterDto;
import com.gta.train.platform.saas.dto.platform.UserCenterScreenDto;
import com.gta.train.platform.saas.entity.platform.UserSystem;
import com.gta.train.platform.saas.entity.system.SystemBaseAccount;

import java.util.List;

public interface UserCenterService extends BaseCommonService<SystemBaseAccount> {
    /**
     * @description 用户中心分页数据
     * @author ru.ding
     * @date  2019-05-07 17:17:40
     * @param [UserCenterDto]
     * @return java.util.List<com.gta.train.platform.saas.dto.platform.UserSystemDto>
     **/
    public List<UserCenterDto> userCenterDtoPage(UserCenterDto userCenterDto);
    public List<UserCenterScreenDto> userSourse(UserCenterScreenDto userCenterScreenDto);
}