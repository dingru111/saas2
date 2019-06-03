package com.gta.train.platform.saas.controller.platform.userCenter;


import com.gta.train.platform.common.base.controller.BaseController;
import com.gta.train.platform.common.util.JedisClusterUtil;
import com.gta.train.platform.persis.page.plugin.Page;
import com.gta.train.platform.saas.constant.PartConstants;
import com.gta.train.platform.saas.dto.platform.UserCenterDto;
import com.gta.train.platform.saas.dto.platform.UserCenterScreenDto;
import com.gta.train.platform.saas.entity.platform.Area;
import com.gta.train.platform.saas.service.platform.userCenter.UserCenterService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Desc:saas平台 用户中心
 *@className UserCenterController
 * @author ru.ding
 * @date 2019-05-07 15:24
 **/

@Controller
@RequestMapping("/nologin/platform//userCenter")
public class UserCenterController extends BaseController {
    private  static Logger LOG= Logger.getLogger(UserCenterController.class);
    @Autowired
    private UserCenterService userCenterService;
    /**
     * @description 主页列表数据
     * @author huan.xu
     * @date  2019-04-30 13:01:16
     * @return java.lang.String
     **/
    @RequestMapping(value = "/userCenterIndex",method = {RequestMethod.GET,RequestMethod.POST})
    public String userCenterIndex(Model model, UserCenterScreenDto userCenterScreenDto){
        List<Area> countryList = (List<Area>) JedisClusterUtil.getMapValue(PartConstants.COUNTRY_KEY, PartConstants.COUNTRY_PARENT_MAP_KEY);
        model.addAttribute("countryList", countryList);
        userCenterScreenDto.setUserId("1");
        List<UserCenterScreenDto> m = this.userCenterService.userSourse(userCenterScreenDto);
        model.addAttribute("userSourse",m);
        return "/platform/userCenter/userCenterIndex";
    }
    @RequestMapping(value = "/userCenterData",method = {RequestMethod.GET,RequestMethod.POST})
    public String userCenterData(Model model ,UserCenterDto userCenterDto){
        Page page = this.getPage();
        userCenterDto.setPage(page);
        userCenterDto.setUserId("1");
        this.userCenterService.userCenterDtoPage(userCenterDto);
        model.addAttribute("pageInfo", page);
        System.out.println("/platform/userCenter/userCenterData");
        return "/platform/userCenter/userCenterData";
    }
}
