package cn.itlou.peisong.service;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.itlou.peisong.config.WxConfiguration;
import cn.itlou.peisong.dto.UserDTO;
import cn.itlou.peisong.mapper.UserMapper;
import cn.itlou.peisong.utils.RedisUtils;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 用户处理
 *
 * @author yuanyl
 * @date 2020/7/1 9:10
 **/
@Service
@Slf4j
public class UserService {

    @Value("${wx.miniapp.configs[0].appid}")
    private String appid;

    @Resource
    UserMapper userMapper;
    @Resource
    RedisUtils redisUtils;

    public String login(String code, String userInfo){

        final WxMaService wxService = WxConfiguration.getMaService(appid);
        UserDTO userDTO = JSON.parseObject(userInfo, UserDTO.class);
        try {
            WxMaJscode2SessionResult session = wxService.getUserService().getSessionInfo(code);
            //TODO 可以增加自己的逻辑，关联业务相关数据
            userDTO.setOpenid(session.getOpenid());
            if (userMapper.countByOpenid(userDTO) != 1) {
                userMapper.insertUserInfo(userDTO);
            }
            redisUtils.setSessionKey(session.getOpenid(), session.getSessionKey());
            return JSON.toJSONString(session);
        } catch (WxErrorException e) {
            log.error(e.getMessage(), e);
            return e.toString();
        }
    }

}
