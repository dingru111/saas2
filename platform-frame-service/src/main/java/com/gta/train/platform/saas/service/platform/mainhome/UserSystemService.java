package com.gta.train.platform.saas.service.platform.mainhome;

import com.gta.train.platform.common.base.service.BaseCommonService;
import com.gta.train.platform.saas.dto.platform.UserSystemDto;
import com.gta.train.platform.saas.entity.platform.UserSystem;

import java.util.List;

public interface UserSystemService extends BaseCommonService<UserSystem> {
    /**
     * @description 首页分页数据
     * @author huan.xu
     * @date  2019-04-29 17:17:40
     * @param [userSystemDto]
     * @return java.util.List<com.gta.train.platform.saas.dto.platform.UserSystemDto>
     **/
    public List<UserSystemDto> userSystemDtoPage(UserSystemDto userSystemDto);
}
