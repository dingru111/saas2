package com.gta.train.platform.saas.controller.platform.baseData;

import com.gta.edu.sdk.common.constant.Constant;
import com.gta.edu.sdk.util.IdGen;
import com.gta.train.platform.common.base.controller.BaseController;
import com.gta.train.platform.common.util.StringUtils;
import com.gta.train.platform.persis.page.PageExample;
import com.gta.train.platform.persis.page.plugin.Page;
import com.gta.train.platform.saas.common.Message;
import com.gta.train.platform.saas.entity.platform.Profession;
import com.gta.train.platform.saas.service.platform.baseData.ProfessionService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

import java.util.Date;

/**
 * @author huan.xu
 * @version 1.0
 * @className ProfessionController
 * @description 专业行业Controlller
 * @date 2019-04-29 10:23
 */
@Controller
@RequestMapping("/nologin/platform/baseData/profession")
public class ProfessionController extends BaseController {

    private static Logger LOG=Logger.getLogger(ProfessionController.class);
    @Autowired
    private ProfessionService professionService;
    /**
     * @description 专业行业 首页
     * @author huan.xu
     * @date  2019-04-29 10:41:31
     * @param [model, type：0 0专业1行业 ]
     * @return java.lang.String
     **/
    @RequestMapping(value = "/professionData",method = {RequestMethod.GET,RequestMethod.POST})
    public String professionData(Model model,Integer type,String name){
        model.addAttribute("type",type);
        Page page=getPage();
        page.setPageSize(10);
        PageExample example = new  PageExample(Profession.class  ,page);
        Criteria criteria = example.createCriteria();
        if(!StringUtils.isNullOrEmpty(name)){
            criteria.andLike("name","%"+name+"%");
        }
        criteria.andCondition("type= ",type);
        example.setOrderByClause("update_time desc");
        professionService.selectByExample(example);
        model.addAttribute("pageInfo", page);
        return "/platform/baseData/profession_data";
    }

    /**
     * @description 新增或编辑
     * @author huan.xu
     * @date  2019-04-29 14:00:11
     * @param [profession]
     * @return com.gta.train.platform.saas.common.Message<com.gta.train.platform.saas.entity.platform.Profession>
     **/
    @PostMapping("/saveOrUpdate")
    @ResponseBody
    public Message<Profession> saveOrUpdate(Profession profession){
        Message<Profession> result=new Message<Profession>();
        result.setStatus(false);
        result.setMsg(Constant.UNSUCCESS);
        try {
            Date now=new Date();
            profession.setUpdateTime(now);
            if (StringUtils.isNullOrEmpty(profession.getId())){
                profession.setId(IdGen.uuid());
                profession.setIsDel(0);
                profession.setCreateTime(now);
                this.professionService.insert(profession);
            }else{
                this.professionService.updateByPrimaryKeySelective(profession);
            }
            result.setStatus(true);
            result.setMsg(Constant.SUCCESS);
        } catch (Exception e) {
           LOG.error(e.getMessage(),e);
        }
        return result;
    }

    /**
     * @description 检查唯一性
     * @author huan.xu
     * @date  2019-04-29 16:01:35
     * @param [id, name,type]
     * @return boolean
     **/
    @GetMapping("/checkUniqueProfession")
    @ResponseBody
    public boolean checkUniqueProfession(String id,String name,Integer type){
        Example example=new Example(Profession.class);
        Criteria criteria = example.createCriteria();
        if(id==null){
            criteria.andCondition("name = ",name).andCondition("type = ",type);
        }else{
            criteria.andNotEqualTo("id",id).andCondition("name = ",name).andCondition("type = ",type);
        }
        return this.professionService.selectCountByExample(example)==0?true:false;
    }
}