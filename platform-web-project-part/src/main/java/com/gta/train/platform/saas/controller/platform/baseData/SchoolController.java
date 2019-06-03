package com.gta.train.platform.saas.controller.platform.baseData;

import com.gta.edu.sdk.common.constant.Constant;
import com.gta.edu.sdk.util.IdGen;
import com.gta.train.platform.common.base.controller.BaseController;
import com.gta.train.platform.common.util.JedisClusterUtil;
import com.gta.train.platform.common.util.StringUtil;
import com.gta.train.platform.persis.page.plugin.Page;
import com.gta.train.platform.saas.common.Message;
import com.gta.train.platform.saas.constant.PartConstants;
import com.gta.train.platform.saas.dto.platform.SchoolDto;
import com.gta.train.platform.saas.entity.platform.Area;
import com.gta.train.platform.saas.entity.platform.Profession;
import com.gta.train.platform.saas.entity.platform.School;
import com.gta.train.platform.saas.service.platform.baseData.SchoolService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

/**
 * @author huan.xu
 * @version 1.0
 * @className SchoolController
 * @description
 * @date 2019-04-30 17:05
 */
@Controller
@RequestMapping("/nologin/platform/baseData/school")
public class SchoolController extends BaseController {

    private static Logger LOG=Logger.getLogger(SchoolController.class);
    @Autowired
    private SchoolService schoolService;

    /**
     * @description 基础数据index
     * @author huan.xu
     * @date  2019-05-07 15:04:05
     * @param [model]
     * @return java.lang.String
     **/
    @GetMapping("/baseDataIndex")
    public String baseDataIndex(Model model) {
        return "/platform/baseData/base_data_index";
    }
    /**
     * @param [model]
     * @return java.lang.String
     * @description
     * @author huan.xu
     * @date 2019-04-30 17:14:39
     **/
    @GetMapping("/schoolIndex")
    public String schoolIndex(Model model) {
        List<Area> countryList = (List<Area>) JedisClusterUtil.getMapValue(PartConstants.COUNTRY_KEY, PartConstants.COUNTRY_PARENT_MAP_KEY);
        model.addAttribute("countryList", countryList);
        return "/platform/baseData/school_index";
    }

    /**
     * @param [model, schoolDto]
     * @return java.lang.String
     * @description 获取学校\公司列表数据
     * @author huan.xu
     * @date 2019-05-05 14:11:57
     **/
    @RequestMapping(value = "/schoolData", method = {RequestMethod.GET, RequestMethod.POST})
    public String schoolData(Model model, SchoolDto schoolDto) {
        Page page = this.getPage();
        schoolDto.setPage(page);
        this.schoolService.schoolDtoPage(schoolDto);
        model.addAttribute("pageInfo", page);
        return "/platform/baseData/school_data";
    }


    /**
     * @param [pId：父id，type ：1省、2 城市]
     * @return java.util.List<com.gta.train.platform.saas.entity.platform.Area>
     * @description 获取省、城市
     * @author huan.xu
     * @date 2019-05-05 09:47:46
     **/
    @GetMapping(value = "/getProvinceOrCity")
    @ResponseBody
    public List<Area> schoolDgetProvinceOrCityata(String pId, Integer type) {
        String key = PartConstants.PROVINCES_KEY;
        if (type == 3) {
            key = PartConstants.CITY_KEY;
        }
        return (List<Area>) JedisClusterUtil.getMapValue(key, pId);
    }

    /**
     * @param [id, model]
     * @return java.lang.String
     * @description 获取新增修改的jsp
     * @author huan.xu
     * @date 2019-05-06 11:38:26
     **/
    @GetMapping("/addEditSchool")
    public String addEditSchool(String id, Model model) {
        if (!StringUtil.isNullOrEmpty(id)) {
            School school = this.schoolService.selectById(id);
            model.addAttribute("school", school);
        }
        List<Area> countryList = (List<Area>) JedisClusterUtil.getMapValue(PartConstants.COUNTRY_KEY, PartConstants.COUNTRY_PARENT_MAP_KEY);
        model.addAttribute("countryList", countryList);
        return "/platform/baseData/add_edit_school";
    }


    /**
     * @description 检查名称唯一性
     * @author huan.xu
     * @date  2019-05-06 12:24:04
     * @param [id, name]
     * @return boolean
     **/
    @GetMapping("/checkUniqueSchool")
    @ResponseBody
    public boolean checkUniqueSchool(String id, String name) {
        Example example = new Example(Profession.class);
        Example.Criteria criteria = example.createCriteria();
        if (StringUtil.isNullOrEmpty(id)) {
            criteria.andCondition("name = ", name);
        } else {
            criteria.andNotEqualTo("id", id).andCondition("name = ", name);
        }
        return this.schoolService.selectCountByExample(example) == 0 ? true : false;
    }

    /**
     * @description 新增修改学校/公司信息
     * @author huan.xu
     * @date  2019-05-06 12:24:19
     * @param [school]
     * @return com.gta.train.platform.saas.common.Message<com.gta.train.platform.saas.entity.platform.School>
     **/
    @PostMapping("/saveOrUpdateSchool")
    @ResponseBody
    public Message<School> saveOrUpdateSchool(School school) {
        Message<School> result = new Message<>();
        result.setStatus(false);
        result.setMsg(Constant.UNSUCCESS);
        Date now = new Date();
        try {
            if (StringUtil.isNullOrEmpty(school.getId())) {
                school.setId(IdGen.uuid());
                school.setIsDel(0);
                school.setCreateTime(now);
                school.setUpdateTime(now);
                this.schoolService.insert(school);
            } else {
                school.setUpdateTime(now);
                this.schoolService.updateByPrimaryKeySelective(school);
            }
            result.setStatus(true);
            result.setMsg(Constant.SUCCESS);
        } catch (Exception e) {
            LOG.error(e.getMessage(),e);
        }
        return result;
    }
}