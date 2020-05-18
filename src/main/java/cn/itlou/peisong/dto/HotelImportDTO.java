package cn.itlou.peisong.dto;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * 酒店数据DTO
 *
 * @author yuanyl
 * @date 2020/5/18 16:11
 **/
@Data
public class HotelImportDTO {

    @ExcelProperty("序号")
    private String id;
    @ExcelProperty("名称")
    private String name;
    @ExcelProperty("城市")
    private String city;
    @ExcelProperty("地址")
    private String address;
    @ExcelProperty("坐标")
    private String coordinate;
    @ExcelIgnore
    private Double x;
    @ExcelIgnore
    private Double y;

}
