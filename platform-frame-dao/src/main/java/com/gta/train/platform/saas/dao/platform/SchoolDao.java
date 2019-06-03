package com.gta.train.platform.saas.dao.platform;

import com.gta.train.platform.persis.mybatis.common.BaseMapper;
import com.gta.train.platform.saas.dto.platform.SchoolDto;
import com.gta.train.platform.saas.entity.platform.School;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Desc:
 * @author: huan.xu
 * @version: 1.0
 * @since 2019-04-28
 */
@Repository
public interface SchoolDao extends BaseMapper<School> {

    /**
     * @description 获取基础数据-学校\公司的列表数据
     * @author huan.xu
     * @date  2019-05-05 11:30:34
     * @param [schoolDto]
     * @return java.util.List<com.gta.train.platform.saas.dto.platform.SchoolDto>
     **/
    @Select({"<script>",
            "SELECT ",
            "s.id as id, ",
            "s. NAME AS name, ",
            "s.country_id as countryId, ",
            "area.country_name as countryName, ",
            "s.province_id as provinceId, ",
            "area.province_name as provinceName, ",
            "s.city_id as cityId, ",
            "area.city_name as cityName,",
            "s.level as level, ",
            "s.type as type ",
            "FROM ",
            "t_school s, ",
            "( ",
            "SELECT ",
            "ct.id AS country_id, ",
            "ct.area_name AS country_name, ",
            "p.id AS province_id, ",
            "p.area_name AS province_name, ",
            "c.id AS city_id, ",
            "c.area_name AS city_name ",
            "FROM ",
            "t_area ct, ",
            "t_area p, ",
            "t_area c ",
            "WHERE ",
            "ct.id = p.p_id ",
            "AND p.id = c.p_id ",
            ") area ",
            "WHERE ",
            "s.country_id = area.country_id ",
            "AND s.province_id = area.province_id ",
            "AND s.city_id = area.city_id ",
            "AND s.is_del = 0",
            "<if test=\"name!=null and name !=''\">",
                "<bind name=\"pattern\" value=\"'%' + _parameter.name + '%'\" />",
                "and s.name like #{pattern} ",
            "</if>",
            "<if test=\"countryId!=null and countryId !=('0'.toString()) \">",
                "and s.country_id=#{countryId} ",
            "</if>",
            "<if test=\"provinceId!=null and provinceId !='-1'\">",
                "and s.province_id=#{provinceId} ",
            "</if>",
            "<if test=\"cityId!=null and cityId !='-1'\">",
                "and s.city_id=#{cityId} ",
            "</if>",
            "<if test=\"level!=null and level !='-1'\">",
                "and s.level=#{level} ",
            "</if>",
            "<if test=\"type!=null and type !='-1'\">",
                "and s.type=#{type} ",
            "</if>",
            "ORDER BY s.update_time desc",
            "</script>"})
   public  List<SchoolDto> schoolDtoPage(SchoolDto schoolDto);
}
