package cn.itlou.peisong.service;

import cn.itlou.peisong.dto.DatePickerDTO;
import cn.itlou.peisong.dto.OrderDTO;
import cn.itlou.peisong.mapper.OrderMapper;
import cn.itlou.peisong.utils.DatePickerGenerate;
import cn.itlou.peisong.utils.IdUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * 订单处理Service
 *
 * @author yuanyl
 * @date 2020/7/1 10:50
 **/
@Service
public class OrderService {

    @Resource
    OrderMapper orderMapper;

    public Integer saveOrderData(OrderDTO orderDTO) {
        orderDTO.setOrderType(1);
        orderDTO.setOrderId(IdUtil.genId());
        orderDTO.setCreateTime(new Date());
        DatePickerDTO datePickerDTO = DatePickerGenerate.generateDate();
        int[] sendTimeIndex = orderDTO.getSendTimeIndex();
        int[] backTimeIndex = orderDTO.getBackTimeIndex();
        LocalDate today = LocalDate.now();
        //寄送时间转换
        StringBuilder sendTimeStr = new StringBuilder();
        sendTimeStr.append(today.getYear() + "年");
        sendTimeStr.append(datePickerDTO.getMonthAndDay().get(sendTimeIndex[0]).split(" ")[0]);
        sendTimeStr.append(" " + datePickerDTO.getHour().get(sendTimeIndex[1]));
        sendTimeStr.append(":" + datePickerDTO.getTime().get(sendTimeIndex[2]));
        orderDTO.setSendTime(sendTimeStr.toString());
        //取回时间转换
        StringBuilder backTimeStr = new StringBuilder();
        backTimeStr.append(today.getYear() + "年");
        backTimeStr.append(datePickerDTO.getMonthAndDay().get(backTimeIndex[0]).split(" ")[0]);
        backTimeStr.append(" " + datePickerDTO.getHour().get(backTimeIndex[1]));
        backTimeStr.append(":" + datePickerDTO.getTime().get(backTimeIndex[2]));
        orderDTO.setBackTime(backTimeStr.toString());
        return orderMapper.saveOrderData(orderDTO);
    }

    public List<OrderDTO> selectOrderInfoByType(Integer type){
        return orderMapper.selectOrderInfoByType(type);
    }

    public OrderDTO selectOrderById(Long id){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        OrderDTO orderDTO = orderMapper.selectOrderById(id);
        orderDTO.setCreateTimeStr(format.format(orderDTO.getCreateTime()));
        orderDTO.setSendGoodsTimeStr(format.format(orderDTO.getSendGoodsTime()));
        return orderDTO;
    }

}
