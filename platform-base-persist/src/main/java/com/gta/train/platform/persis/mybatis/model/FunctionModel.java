/*******************************************************************************
 * Copyright (c) 2017 by JoyLau. All rights reserved
 ******************************************************************************/

package com.gta.train.platform.persis.mybatis.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by JoyLau on 4/6/2017.
 * cn.joylau.mybatis.mapper.function
 * 2587038142.liu@gmail.com
 * 每个实体都有的id属性，另外增加了createTime和updateTime，虽然在业务上可能没有什么用处，但是对于开发和运维的作用相当大，谁用谁知道
 */
public class FunctionModel implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Transient
    private Date createTime;

    @Transient
    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
