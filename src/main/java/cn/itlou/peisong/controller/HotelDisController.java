package cn.itlou.peisong.controller;

import cn.itlou.peisong.dto.HotelESDTO;
import cn.itlou.peisong.dto.HotelImportDTO;
import cn.itlou.peisong.dto.HotelResDTO;
import cn.itlou.peisong.service.HotelService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 酒店定位数据接口
 *
 * @author yuanyl
 * @date 2020/6/30 9:43
 **/
@RestController
public class HotelDisController {

    @Resource
    HotelService hotelService;

    @GetMapping("getDis")
    public List<HotelResDTO> getDis(String latitude, String longitude){
        return hotelService.getNearHotelInfo(Double.parseDouble(longitude), Double.parseDouble(latitude));
    }

    @GetMapping("searchByKeyword")
    public List<HotelResDTO> getDis(String keyword){
        return hotelService.searchByKeyword(keyword);
    }

    @GetMapping("getHotelInfoById")
    public HotelResDTO getHotelInfoById(String id){
        return hotelService.getHotelInfoById(Long.parseLong(id));
    }

}
