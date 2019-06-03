package com.gta.train.platform.saas.controller.platform.mainHome;

import com.gta.edu.sdk.common.constant.Constant;
import com.gta.train.platform.common.base.controller.BaseController;
import com.gta.train.platform.persis.page.plugin.Page;
import com.gta.train.platform.saas.common.Message;
import com.gta.train.platform.saas.dto.platform.UserSystemDto;
import com.gta.train.platform.saas.entity.platform.UserSystem;
import com.gta.train.platform.saas.service.platform.mainhome.UserSystemService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author huan.xu
 * @version 1.0
 * @className MainHomeController
 * @description 主页业务 Controller
 * @date 2019-04-29 17:24
 */
@Controller
@RequestMapping("/nologin/platform/mainHome")
public class MainHomeController extends BaseController {

    private  static Logger LOG=Logger.getLogger(MainHomeController.class);
    @Autowired
    private UserSystemService userSystemService;
    /**
     * @description 主页列表数据
     * @author huan.xu
     * @date  2019-04-30 13:01:16
     * @param [model, name, isCollect]
     * @return java.lang.String
     **/
    @RequestMapping(value = "/mainHomeData",method = {RequestMethod.GET,RequestMethod.POST})
    public String mainHomeData(Model model, String name,Integer isCollect){
        Page page = this.getPage();
        UserSystemDto userSystemDto=new UserSystemDto();
        userSystemDto.setName(name);
        userSystemDto.setIsCollect(isCollect);
        userSystemDto.setPage(page);
        userSystemDto.setUserId("1");
        this.userSystemService.userSystemDtoPage(userSystemDto);
        model.addAttribute("pageInfo", page);
        return "/platform/mainHome/main_home_data";
    }

    /**
     * @description 收藏、未收藏操作
     * @author huan.xu
     * @date  2019-04-30 13:00:43
     * @param [userSystem]
     * @return com.gta.train.platform.saas.common.Message<com.gta.train.platform.saas.entity.platform.UserSystem>
     **/
    @GetMapping("/changeCollect")
    @ResponseBody
    public Message<UserSystem> changeCollect(UserSystem userSystem){
        Message<UserSystem> result=new Message<UserSystem>();
        result.setStatus(false);
        result.setMsg(Constant.UNSUCCESS);
        try {
            this.userSystemService.updateByPrimaryKeySelective(userSystem);
            result.setStatus(true);
            result.setMsg(Constant.SUCCESS);
        } catch (Exception e) {
            LOG.error(e.getMessage(),e);
        }
        return result;
    }
}