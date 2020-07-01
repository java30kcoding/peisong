package cn.itlou.peisong.controller;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.itlou.peisong.config.WxConfiguration;
import cn.itlou.peisong.dto.UserDTO;
import cn.itlou.peisong.service.UserService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 用户登录Controller
 *
 * @author yuanyl
 * @date 2020/5/13 9:30
 **/
@RestController
@Slf4j
public class UserController {

    @Resource
    UserService userService;


    /**
     * 登陆接口
     */
    @GetMapping("/login")
    public String login(String code, String userInfo) {
        if (StringUtils.isBlank(code)) {
            return "empty jscode";
        } else {
            return userService.login(code, userInfo);
        }
    }

}
