package cn.itlou.peisong.dto;

import lombok.Data;

import java.util.Date;

/**
 * 订单数据DTO
 *
 * @author yuanyl
 * @date 2020/7/1 10:53
 **/
@Data
public class OrderDTO {

    private String id;
    private String username;
    private String userMobile;
    private String userCart;
    private String userOthers;
    private Integer baggageCount;
    private String baggageImgName;
    private String sendAddress;
    private String backAddress;
    private String sendDetailAddress;
    private String backDetailAddress;
    private String sendTime;
    private int[] sendTimeIndex;
    private int[] backTimeIndex;
    private String backTime;
    private Integer orderPrice;
    private String openid;
    private Integer orderType;
    private Date createTime;
    private String createTimeStr;
    private Date sendGoodsTime;
    private String sendGoodsTimeStr;
    private String orderId;

}
