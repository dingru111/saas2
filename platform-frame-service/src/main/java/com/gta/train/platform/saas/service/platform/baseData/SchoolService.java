package com.gta.train.platform.saas.service.platform.baseData;

import com.gta.train.platform.common.base.service.BaseCommonService;
import com.gta.train.platform.saas.dto.platform.SchoolDto;
import com.gta.train.platform.saas.entity.platform.School;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface SchoolService extends BaseCommonService<School> {
    /**
     * @description 获取基础数据-学校\公司的列表数据
     * @author huan.xu
     * @date  2019-05-05 11:28:25
     * @param [schoolDto]
     * @return java.util.List<com.gta.train.platform.saas.dto.platform.SchoolDto>
     **/
    public List<SchoolDto> schoolDtoPage(SchoolDto schoolDto);
}
