package cn.itlou.peisong.controller;

import cn.itlou.peisong.dto.LogisticsDTO;
import cn.itlou.peisong.service.LogisticsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * 物流信息接口
 *
 * @author yuanyl
 * @date 2020/7/1 18:41
 **/
@RestController
public class LogisticsController {

    @Resource
    LogisticsService logisticsService;

    @GetMapping("selectLogisticsByOrderId")
    public List<LogisticsDTO> selectLogisticsByOrderId(Long orderId){
        return logisticsService.selectLogisticsByOrderId(orderId);
    }

    @GetMapping("selectLatestLogisticsByOrderId")
    public LogisticsDTO selectLatestLogisticsByOrderId(Long orderId){
        return logisticsService.selectLatestLogisticsByOrderId(orderId);
    }

}
