package cn.itlou.peisong.mapper;

import cn.itlou.peisong.dto.LogisticsDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 物流信息Mapper
 *
 * @author yuanyl
 * @date 2020/7/1 18:37
 **/
@Mapper
public interface LogisticsMapper {

    @Select("select * from t_logistics_info where order_id = #{orderId} order by desc_time desc")
    List<LogisticsDTO> selectLogisticsByOrderId(Long orderId);

    @Select("select * from t_logistics_info where order_id = #{orderId} order by desc_time desc limit 1")
    LogisticsDTO selectLatestLogisticsByOrderId(Long orderId);

}
