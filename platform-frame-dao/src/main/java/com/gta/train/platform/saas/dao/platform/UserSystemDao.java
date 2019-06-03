package com.gta.train.platform.saas.dao.platform;

import com.gta.train.platform.persis.mybatis.common.BaseMapper;
import com.gta.train.platform.saas.dto.platform.UserSystemDto;
import com.gta.train.platform.saas.entity.platform.Area;
import com.gta.train.platform.saas.entity.platform.UserSystem;
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
public interface UserSystemDao extends BaseMapper<UserSystem> {
    /**
     * @description 首页分页数据
     * @author huan.xu
     * @date  2019-04-29 17:17:40
     * @param [userSystemDto]
     * @return java.util.List<com.gta.train.platform.saas.dto.platform.UserSystemDto>
     **/
    @Select({"<script>",
           "select us.id as id,us.system_id as systemId,us.is_collect as isCollect,s.name as name,s.realm_name as realmName",
            "from t_user_system us,t_system s ",
            "where us.system_id=s.id and s.is_del=0 and us.user_id=#{userId} ",
            "<if test=\"name!=null and name !=''\">",
            "<bind name=\"pattern\" value=\"'%' + _parameter.name + '%'\" />",
            "and s.name like #{pattern} ",
            "</if>",
            "<if test=\"isCollect!=null and isCollect ==1 \">",
            "and us.is_collect = #{isCollect} ",
            "</if>",
            "ORDER BY s.create_time ",
            "</script>"})
    public List<UserSystemDto> userSystemDtoPage(UserSystemDto userSystemDto);
}
