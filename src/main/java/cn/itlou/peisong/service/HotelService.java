package cn.itlou.peisong.service;

import cn.itlou.peisong.dto.HotelImportDTO;
import cn.itlou.peisong.mapper.HotelMapper;
import cn.itlou.peisong.utils.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 酒店信息处理Service
 *
 * @author yuanyl
 * @date 2020/5/18 18:42
 **/
@Service
@Slf4j
public class HotelService {

    @Resource
    HotelMapper hotelMapper;
    @Resource
    RedisUtils redisUtils;

    public Integer insertHotelInfo(List<HotelImportDTO> list){
        log.info("{}条数据，开始存储数据库！", list.size());
        return hotelMapper.insertHotelInfo(list);
    }

    public Long insertHotelInfoToRedis(List<HotelImportDTO> list){
        log.info("{}条数据，开始存储Redis！", list.size());
        return redisUtils.setGeo(list);
    }

    public String getNearHotelInfo(){
        List<String> ids = redisUtils.getDistanceByCoordinate(118.03911129898074, 24.491372834479673, 5);
        List<HotelImportDTO> hotelImportDTOS = hotelMapper.selectByIdList(ids);
        for (HotelImportDTO hotelImportDTO : hotelImportDTOS) {
            System.out.println(hotelImportDTO.getName());
            System.out.println(hotelImportDTO.getAddress().replace("福建省厦门市", ""));
        }
        return "";
    }

}
