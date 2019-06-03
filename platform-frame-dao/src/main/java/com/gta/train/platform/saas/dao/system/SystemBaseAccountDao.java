package com.gta.train.platform.saas.dao.system;

import com.gta.train.platform.persis.mybatis.common.BaseMapper;
import com.gta.train.platform.saas.dto.platform.UserCenterDto;
import com.gta.train.platform.saas.dto.platform.UserCenterScreenDto;
import com.gta.train.platform.saas.entity.system.SystemBaseAccount;
import org.apache.ibatis.annotations.Param;
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
public interface SystemBaseAccountDao extends BaseMapper<SystemBaseAccount> {
    @Select({"<script>",
            "select us.is_collect as isCollect,ct.area_name as country_name,p.area_name as province_name,c.area_name as city_name,sba.id as id,sba.system_id as systemId,sba.sys_create_time as sysCreateTime,sba.name as name,sba.sch_com_name as schComName,s.realm_name as realmName,sba.account_role as accountRole,sba.account_source as accountSource,sba.customer_type as customerType,sba.login_times as loginTimes,s.name as sysName,s.create_time as createTime",
            "from t_area as ct,t_area as p,t_area as c,t_system_base_account as sba,t_system as s,t_user_system as us",
            "where sba.system_id=s.id and s.is_del = 0 and us.user_id=#{userId} and ct.id = p.p_id and p.id = c.p_id ",

            "<if test=\"startDate!=null and startDate !='' and  endDate!=null and endDate !=''\">",
            "and  date_format(s.create_time, '%Y-%m-%d') between #{startDate} and #{endDate}",
            "</if>",
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
            "<if test=\"customerType!=null and customerType !='-1'\">",
            "and sba.customerType=#{customerType} ",
            "</if>",
            "<if test=\"accountRole!=null and accountRole !='-1'\">",
            "and sba.accountRole=#{accountRole} ",
            "</if>",
            "<if test=\"accountSource!=null and accountSource !='-1'\">",
            "and sba.accountSource=#{accountSource} ",
            "</if>",

            "ORDER BY s.create_time ",
            "</script>"})
    List<UserCenterDto> userCenterDtoPage(UserCenterDto userCenterDto);

    @Select({"<script>",
            "select sba.account_source as accountSource,sba.customer_type as customerType,sba.account_role as accountRole,sba.system_id as systemId,sr.role_name as roleName,s.name as systemName,s.create_time as createTime",
            "from t_system_base_account as sba,t_system_role as sr,t_system as s",
            "where sba.id=#{userId} and  sr.id=#{userId} and s.id=#{userId}",
            "</script>"})
    //List<UserCenterDto> userSourse(@Param("userId") String userId);
    List<UserCenterScreenDto> userSourse(UserCenterScreenDto userCenterScreenDto);
}
