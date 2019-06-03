package com.gta.train.platform.saas.dto.platform;

import com.gta.train.platform.persis.page.plugin.IPage;
import com.gta.train.platform.persis.page.plugin.Page;
import java.io.Serializable;
import java.util.Date;

/**
 * @author ru.ding
 * @version 1.0
 * @className UserCenterDto
 * @description saas-用户中心
 * @date 2019-05-07 16:35
 */
public class UserCenterDto implements Serializable, IPage {
    private static final long serialVersionUID = -6043079216301879105L;
   // sba.account_source as accountSource,sba.customer_type as customerType,sba.account_role as accountRole
    /**id*/
    private String id;
    /**平台用户id*/
    private String userId;
    /**账号*/
    private String account;
    /**姓名*/
    private String name;
    /**国家id*/
    private Integer countryId;
    /**国家名称*/
    private String countryName;
    /**省id*/
    private Integer provinceId;
    /**省名称*/
    private String provinceName;
    /**城市id*/
    private Integer cityId;
    /**城市名称*/
    private String cityName;
    /**层级*/
    private Integer level;
    /**类型*/
    private Integer type;
    /**学校名称*/
    private String schComName;
    /**角色*/
    private Integer accountRole;
    /**账号来源*/
    private Integer accountSource;
    /**账号创建时间*/
    private Date sysCreateTime;
    /**账号类型*/
    private Integer accountType;
    /**客户类型*/
    private Integer customerType;
    /*****账号创建时间****/
    private Date createTime;
    /****开始时间*/
    private String startDate;
    /**结束时间*/
    private String endDate;


    /**分页*/
    private Page page;
    /**系统id*/
    private String systemId;
    /**所属系统名称*/
    private String sysName;
    /**登录次数**/
    private Integer loginTimes;
    /**是否收藏：0否，1是*/
    private Integer isCollect;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCountryId() {
        return countryId;
    }

    public void setCountryId(Integer countryId) {
        this.countryId = countryId;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public Integer getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Integer provinceId) {
        this.provinceId = provinceId;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getSchComName() {
        return schComName;
    }

    public void setSchComName(String schComName) {
        this.schComName = schComName;
    }

    public Integer getAccountRole() {
        return accountRole;
    }

    public void setAccountRole(Integer accountRole) {
        this.accountRole = accountRole;
    }

    public Integer getAccountSource() {
        return accountSource;
    }

    public void setAccountSource(Integer accountSource) {
        this.accountSource = accountSource;
    }

    public Date getSysCreateTime() {
        return sysCreateTime;
    }

    public void setSysCreateTime(Date sysCreateTime) {
        this.sysCreateTime = sysCreateTime;
    }

    public Integer getAccountType() {
        return accountType;
    }

    public void setAccountType(Integer accountType) {
        this.accountType = accountType;
    }

    public Integer getCustomerType() {
        return customerType;
    }

    public void setCustomerType(Integer customerType) {
        this.customerType = customerType;
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
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

    public String getSysName() {
        return sysName;
    }

    public void setSysName(String sysName) {
        this.sysName = sysName;
    }

    public Integer getLoginTimes() {
        return loginTimes;
    }

    public void setLoginTimes(Integer loginTimes) {
        this.loginTimes = loginTimes;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
