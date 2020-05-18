package cn.itlou.peisong.dto;

import lombok.Data;

import java.util.List;

/**
 * 时间多级选择器DTO
 *
 * @author yuanyl
 * @date 2020/5/3 21:09
 **/
@Data
public class DatePickerDTO {

    private List<String> monthAndDay;
    private List<String> hour;
    private List<String> time;

}
