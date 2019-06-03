package com.gta.train.platform.saas.dto.platform;

import com.gta.train.platform.persis.page.plugin.IPage;
import com.gta.train.platform.persis.page.plugin.Page;

import java.io.Serializable;

/**
 * @author huan.xu
 * @version 1.0
 * @className UserSystemDto
 * @description 平台用户系统Dto
 * @date 2019-04-29 16:54
 */
public class UserSystemDto implements Serializable, IPage {

    private static final long serialVersionUID = 6324577817313564850L;
    /**UserSystemId*/
    private String id;
    /**平台用户id*/
    private String userId;

    /**系统id*/
    private String systemId;

    /**是否收藏：0否，1是*/
    private Integer isCollect;

    /**系统名称*/
    private String name;

    /**域名*/
    private String realmName;

    /**分页*/
    private Page page;
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSystemId() {
        return systemId;
    }

    public void setSystemId(String systemId) {
        this.systemId = systemId;
    }

    public Integer getIsCollect() {
        return isCollect;
    }

    public void setIsCollect(Integer isCollect) {
        this.isCollect = isCollect;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRealmName() {
        return realmName;
    }

    public void setRealmName(String realmName) {
        this.realmName = realmName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }
}