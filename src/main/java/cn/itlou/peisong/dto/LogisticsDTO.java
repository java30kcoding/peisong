package cn.itlou.peisong.dto;

import lombok.Data;

import java.util.Date;

/**
 * 物流信息DTO
 *
 * @author yuanyl
 * @date 2020/7/1 18:38
 **/
@Data
public class LogisticsDTO {

    private String id;
    private String orderId;
    private String text;
    private Date descTime;
    private String desc;

}
