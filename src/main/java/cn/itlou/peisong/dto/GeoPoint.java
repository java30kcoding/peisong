package cn.itlou.peisong.dto;

import lombok.Data;

/**
 * Geo类型
 *
 * @author yuanyl
 * @date 2020/5/28 23:19
 **/
@Data
public class GeoPoint {

    private Double lat, lon;
    public GeoPoint() {
    }
    public GeoPoint(Double lat, Double lon) {
        this.lat = lat;
        this.lon = lon;
    }

}
