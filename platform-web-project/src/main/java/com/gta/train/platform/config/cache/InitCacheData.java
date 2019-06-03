package com.gta.train.platform.config.cache;


import com.gta.train.platform.common.util.JedisClusterUtil;
import com.gta.train.platform.common.util.SpringContextHolder;
import com.gta.train.platform.config.constant.FrameworkConstants;
import com.gta.train.platform.saas.entity.platform.Area;
import com.gta.train.platform.saas.service.platform.baseData.AreaService;
import org.apache.log4j.Logger;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;

/**
 * @author huan.xu
 * @version 1.0
 * @className InitCacheData
 * @description 项目启动加载缓存,数据字典
 * @date 2018-12-13 14:09
 */
public class InitCacheData {
    private static InitCacheData instance;
    private static final Logger LOG = Logger.getLogger(InitCacheData.class);
    private InitCacheData() {
    }

    @SuppressWarnings("all")
    public static InitCacheData getInstance() {
        synchronized (InitCacheData.class) {
            if (instance == null) {
                instance = new InitCacheData();
                instance.initData();
            }
            return instance;
        }
    }

    private void initData() {
        LOG.info("****************开始缓存，国家省市Map****************");
        initAreaData();
        LOG.info("****************结束缓存，国家省市Map****************");
    }

    /**
     * @param []
     * @return void
     * @description 省市地区Map
     * @author huan.xu
     * @date 2018-12-13 14:24
     **/
    private void initAreaData() {
        AreaService areaService = (AreaService) SpringContextHolder.getBean("areaService");
        Example example=new Example(Area.class);
        example.setOrderByClause("area_type, CAST(p_id AS SIGNED),sort");
        List<Area> areas = areaService.selectByExample(example);
        //先删除缓存里的数据
        JedisClusterUtil.del(FrameworkConstants.COUNTRY_KEY,FrameworkConstants.PROVINCES_KEY,FrameworkConstants.CITY_KEY);
        for(Area area: areas){
            Integer areaType = area.getAreaType();
            String pId = area.getPId();
            String key=null;
            if(areaType.intValue()==1){
                key=FrameworkConstants.COUNTRY_KEY;
            }else if(areaType.intValue()==2){
                key=FrameworkConstants.PROVINCES_KEY;
            }else{
                key=FrameworkConstants.CITY_KEY;
            }
            ArrayList<Area> childrenList= (ArrayList<Area>) JedisClusterUtil.getMapValue(key,pId);
            if(childrenList==null){
                childrenList=new ArrayList<Area>();
            }
            childrenList.add(area);
            JedisClusterUtil.mapPush(key,pId,childrenList);
        }
    }
}
