/*******************************************************************************
 * Copyright (c) 2017 by JoyLau. All rights reserved
 ******************************************************************************/

package  com.gta.train.platform.persis.mybatis.plugin.ids;

 

/**
 * 通用Mapper接口,根据ids操作
 * @param <T> 不能为空
 */
public interface IdsMapper<T> extends SelectByIdsMapper<T>, DeleteByIdsMapper<T> {

}
