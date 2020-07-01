package cn.itlou.peisong.mapper;

import cn.itlou.peisong.dto.OrderDTO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 订单数据Mapper
 *
 * @author yuanyl
 * @date 2020/7/1 10:50
 **/
@Mapper
public interface OrderMapper {

    @Insert("insert into t_order_info values (#{id}, #{username}, #{userMobile}, #{userCart}, #{userOthers}, " +
            "#{baggageCount}, #{baggageImgName}, #{sendAddress}, #{backAddress}, #{sendDetailAddress}, #{backDetailAddress}, " +
            "#{sendTime}, #{backTime}, #{orderPrice}, #{openid}, #{orderType}, #{createTime}, null, #{orderId})")
    Integer saveOrderData(OrderDTO orderDTO);

    @Select("select * from t_order_info where order_type = #{type}")
    List<OrderDTO> selectOrderInfoByType(Integer type);

    @Select("select * from t_order_info where id = #{id}")
    OrderDTO selectOrderById(Long id);

}
