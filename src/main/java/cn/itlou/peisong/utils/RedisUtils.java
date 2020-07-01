package cn.itlou.peisong.utils;

import cn.itlou.peisong.dto.HotelImportDTO;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.*;
import redis.clients.jedis.params.geo.GeoRadiusParam;

import java.util.List;
import java.util.Map;

/**
 * @author yuanyl
 * @date 2020/5/18 18:29
 **/
@Slf4j
@Component
public class RedisUtils {

    @Autowired
    private JedisPool jedisPool;

    /**
     * 存储地理信息到Redis中
     *
     * @param list
     * @return
     */
    public Long setGeo(List<HotelImportDTO> list) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            Map<String, GeoCoordinate> hotelGeoMap = Maps.newHashMap();
            for (HotelImportDTO hotelImportDTO : list) {
                Double x = hotelImportDTO.getX();
                Double y = hotelImportDTO.getY();
                hotelGeoMap.put(hotelImportDTO.getId(), new GeoCoordinate(x, y));
            }
            return jedis.geoadd("HOTEL_GEO_INFO", hotelGeoMap);
        } catch (Exception e) {
            log.error(e.getMessage());
        } finally {
            returnResource(jedisPool, jedis);
        }
        return null;
    }

    /**
     * 根据当前经纬度找出附近指定半径的酒店
     *
     * @param x 经度
     * @param y 纬度
     * @param radius 半径
     * @return id集合
     */
    public List<String> getDistanceByCoordinate(double x, double y, double radius){
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            List<GeoRadiusResponse> hotelGeoInfo = jedis.georadius("HOTEL_GEO_INFO", x, y, radius, GeoUnit.KM,
                        GeoRadiusParam.geoRadiusParam().withDist().sortAscending().count(10));
            List<String> ids = Lists.newArrayList();
            for (GeoRadiusResponse geoRadiusResponse : hotelGeoInfo) {
                ids.add(geoRadiusResponse.getMemberByString());
            }
            return ids;
        } catch (Exception e) {
            log.error(e.getMessage());
        } finally {
            returnResource(jedisPool, jedis);
        }
        return null;
    }

    /**
     * 存储openid和sessionKey
     *
     * @param openid
     * @param sessionKey
     * @return
     */
    public String setSessionKey(String openid, String sessionKey){
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.set(openid, sessionKey);
        } catch (Exception e) {
            log.error(e.getMessage());
        } finally {
            returnResource(jedisPool, jedis);
        }
        return null;
    }

    /**
     * 根据openid获取sessionKey
     *
     * @param openid
     * @return
     */
    public String getSessionKey(String openid){
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.get(openid);
        } catch (Exception e) {
            log.error(e.getMessage());
        } finally {
            returnResource(jedisPool, jedis);
        }
        return null;
    }

    /**
     * 返还到连接池
     *
     * @param jedisPool
     * @param jedis
     */
    public static void returnResource(JedisPool jedisPool, Jedis jedis) {
        if (jedis != null) {
            jedisPool.returnResource(jedis);
        }
    }

}
