package cn.itlou.peisong.service;

import cn.itlou.peisong.dto.LogisticsDTO;
import cn.itlou.peisong.dto.OrderDTO;
import cn.itlou.peisong.mapper.LogisticsMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * 物流信息服务
 *
 * @author yuanyl
 * @date 2020/7/1 18:40
 **/
@Service
public class LogisticsService {

    @Resource
    LogisticsMapper logisticsMapper;

    public List<LogisticsDTO> selectLogisticsByOrderId(Long orderId){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        List<LogisticsDTO> logisticsDTOs = logisticsMapper.selectLogisticsByOrderId(orderId);
        for (LogisticsDTO logisticsDTO : logisticsDTOs) {
            logisticsDTO.setDesc(format.format(logisticsDTO.getDescTime()));
        }
        return logisticsDTOs;
    }

    public LogisticsDTO selectLatestLogisticsByOrderId(Long orderId){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        LogisticsDTO logisticsDTO = logisticsMapper.selectLatestLogisticsByOrderId(orderId);
        logisticsDTO.setDesc(format.format(logisticsDTO.getDescTime()));
        return logisticsDTO;
    }

}
