package com.gta.train.platform.saas.service.platform.baseData.impl;

import com.gta.train.platform.common.base.service.impl.BaseCommonServiceImpl;
import com.gta.train.platform.saas.dao.platform.SchoolDao;
import com.gta.train.platform.saas.dto.platform.SchoolDto;
import com.gta.train.platform.saas.entity.platform.School;
import com.gta.train.platform.saas.service.platform.baseData.AreaService;
import com.gta.train.platform.saas.service.platform.baseData.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author huan.xu
 * @version 1.0
 * @className AreaServiceImpl
 * @description
 * @date 2019-04-30 13:49
 */
@Service("schoolService")
public class SchoolServiceImpl extends BaseCommonServiceImpl<School> implements SchoolService {
    @Autowired
    private SchoolDao schoolDao;

    @Override
    public List<SchoolDto> schoolDtoPage(SchoolDto schoolDto) {
        return this.schoolDao.schoolDtoPage(schoolDto);
    }
}