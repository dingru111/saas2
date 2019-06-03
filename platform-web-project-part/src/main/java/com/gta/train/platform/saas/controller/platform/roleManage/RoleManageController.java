package com.gta.train.platform.saas.controller.platform.roleManage;


import com.gta.train.platform.common.base.controller.BaseController;
import com.gta.train.platform.persis.page.plugin.Page;
import com.gta.train.platform.saas.controller.platform.userCenter.UserCenterController;
import com.gta.train.platform.saas.dto.platform.RoleManageDto;
import com.gta.train.platform.saas.service.platform.roleManage.RoleManageService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/nologin/platform/roleManage")
public class RoleManageController  extends BaseController{
    private  static Logger LOG= Logger.getLogger(RoleManageController.class);
    @Autowired
    private RoleManageService roleManageService;


    @RequestMapping(value = "/roleManageIndex",method = {RequestMethod.GET,RequestMethod.POST})
    public String roleManageIndex(){
        return "/platform/roleManage/roleManageIndex";
    }


    @RequestMapping(value = "/roleManageData",method = {RequestMethod.GET,RequestMethod.POST})
    public String roleManageData(Model model , RoleManageDto roleManageDto){
        Page page = this.getPage();
        roleManageDto.setPage(page);
        this.roleManageService.RoleManageDtoPage(roleManageDto);
        System.out.println(roleManageDto.getSysName());
        model.addAttribute("pageInfo",page);
        return "/platform/roleManage/roleManageData";
    }
}
