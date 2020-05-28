package cn.itlou.peisong.dto;

import lombok.Data;
import org.springframework.data.elasticsearch.annotations.*;

import java.io.Serializable;

/**
 * 酒店数据ES操作DTO
 *
 * @author yuanyl
 * @date 2020/5/28 18:27
 **/
@Data
@Document(indexName = "hotel_info")
public class HotelESDTO implements Serializable {

    private String id;
    @CompletionField(analyzer="icu_analyzer",searchAnalyzer="icu_analyzer")
    private String name;
    private String address;
    @GeoPointField
    private GeoPoint location;
    private Double x;
    private Double y;

}
