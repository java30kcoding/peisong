package cn.itlou.peisong.controller;

import cn.itlou.peisong.dto.OrderDTO;
import cn.itlou.peisong.service.OrderService;
import com.alibaba.fastjson.JSON;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 订单处理接口
 *
 * @author yuanyl
 * @date 2020/6/29 20:53
 **/
@RestController
public class OrderController {

    @Resource
    OrderService orderService;

    @PostMapping("saveOrderData")
    public void saveOrderData(@RequestBody String orderData){
        OrderDTO orderDTO = JSON.parseObject(orderData, OrderDTO.class);
        orderService.saveOrderData(orderDTO);
    }

    @GetMapping("selectOrderInfoByType")
    public List<OrderDTO> selectOrderInfoByType(String type){
        return orderService.selectOrderInfoByType(Integer.parseInt(type));
    }

    @GetMapping("selectOrderById")
    public OrderDTO selectOrderById(String id){
        return orderService.selectOrderById(Long.parseLong(id));
    }

}
