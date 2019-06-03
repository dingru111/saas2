package com.gta.train.platform.saas.dto.platform;

import com.gta.train.platform.persis.page.plugin.IPage;
import com.gta.train.platform.persis.page.plugin.Page;

public class RoleManageDto implements IPage {
    private static final long serialVersionUID = -6043079216301879105L;
    /**角色id**/
    private String id;
    /**角色名称**/
    private String roleName;
    /**角色权限描述**/
    private String description;
    /**分页*/
    private Page page;
    /**系统名称*/
    private String sysName;
    /**删除标示*/
    private Integer isDel;
    /**新建人id*/
    private String createUserId;
    /**修改人id*/
    private String updateUserId;



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }

    public String getSysName() {
        return sysName;
    }

    public void setSysName(String sysName) {
        this.sysName = sysName;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }

    public String getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(String updateUserId) {
        this.updateUserId = updateUserId;
    }

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }
}
