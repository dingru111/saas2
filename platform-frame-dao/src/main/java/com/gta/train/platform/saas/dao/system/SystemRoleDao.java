package com.gta.train.platform.saas.dao.system;

import com.gta.train.platform.persis.mybatis.common.BaseMapper;
import com.gta.train.platform.saas.dto.platform.RoleManageDto;
import com.gta.train.platform.saas.entity.platform.PlatformUserSystemRole;
import com.gta.train.platform.saas.entity.system.SystemRole;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Desc:
 * @author: ru.ding
 * @version: 1.0
 * @since 2019-04-28
 */
@Repository
public interface SystemRoleDao extends BaseMapper<PlatformUserSystemRole> {
    @Select({"<script>",
            "SELECT ",
            "sysr.id as id,sysr.role_name as roleName,sysr.description as description",
            "from",
            "t_system_role as sysr,t_platform_user_system_role as pusr,t_system as s",
            "where sysr.is_del=0",
            "and sysr.id = pusr.id",
            /*"and s.name like #{sysName}",*/
            /*"<if test = \"sysName!=null and sysName !='' \">",
                "and s.name like #{sysName}",
            "<if>",*/
            "ORDER BY s.create_time desc",
    "</script>"})
    List<RoleManageDto> RoleManageDtoPage(RoleManageDto roleManageDto);
    /*@Update({"<script>",
            "<foreach collection=\"list\" item=\"item\" index=\"index\" open=\"\" close=\"\" separator=\";\">",
            "update course",
            "<set>",
            "name=${item.name}",
            "</set>",
            "where id = ${item.id}",
            "</foreach>",
    "</script>"})
    String updateList(RoleManageDto roleManageDto);*/
    /*@Update({"<script>",
            "update tf_m_resource",
            "<set>",
            "<if test='createBy != null'>",
            "create_by = #{createBy} ,",
            "</if>",
            "</set>",
            "</script>"})
    String update(@Param("createBy") String createBy);*/
}
