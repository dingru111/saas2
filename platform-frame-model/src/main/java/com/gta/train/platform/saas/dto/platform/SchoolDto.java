package com.gta.train.platform.saas.dto.platform;


import com.gta.train.platform.persis.page.plugin.IPage;
import com.gta.train.platform.persis.page.plugin.Page;

import java.io.Serializable;

/**
 * @author huan.xu
 * @version 1.0
 * @className SchoolDto
 * @description 基础数据-学校/公司管理Dto
 * @date 2019-05-05 11:13
 */
public class SchoolDto implements Serializable,IPage{

    private static final long serialVersionUID = -6043079216301879105L;
    /**id*/
    private String id;
    /**名称*/
    private String name;
    /**国家id*/
    private String countryId;
    /**国家名称*/
    private String countryName;
    /**省id*/
    private String provinceId;
    /**省名称*/
    private String provinceName;
    /**城市id*/
    private String cityId;
    /**城市名称*/
    private String cityName;
    /**层级*/
    private Integer level;
    /**类型*/
    private Integer type;

    /**分页*/
    private Page page;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountryId() {
        return countryId;
    }

    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(String provinceId) {
        this.provinceId = provinceId;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
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

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }
}