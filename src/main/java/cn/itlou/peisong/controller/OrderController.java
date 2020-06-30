package cn.itlou.peisong.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 订单处理接口
 *
 * @author yuanyl
 * @date 2020/6/29 20:53
 **/
@RestController
public class OrderController {

    @PostMapping("saveOrderData")
    public void saveOrderData(@RequestBody JSONObject jsonObject){
        System.out.println(jsonObject);
    }

}
