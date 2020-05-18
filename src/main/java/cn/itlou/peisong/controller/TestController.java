package cn.itlou.peisong.controller;

import cn.itlou.peisong.dto.DatePickerDTO;
import cn.itlou.peisong.service.HotelService;
import cn.itlou.peisong.utils.DatePickerGenerate;
import cn.itlou.peisong.utils.RedisUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author yuanyl
 * @date 2020/5/2 21:51
 **/
@RestController
@Slf4j
public class TestController {

    @Resource
    RedisUtils redisUtils;

    @Resource
    HotelService hotelService;

    @GetMapping("/test")
    @ResponseBody
    public String test(){
        return "{\n" +
                "    \"message\": [\n" +
                "        {\n" +
                "            \"name\": \"分类\",\n" +
                "            \"image_src\": \"https://api-hmugo-web.itheima.net/pyg/icon_index_nav_4@2x.png\",\n" +
                "            \"open_type\": \"switchTab\",\n" +
                "            \"navigator_url\": \"/pages/category/index\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"name\": \"秒杀拍\",\n" +
                "            \"image_src\": \"https://api-hmugo-web.itheima.net/pyg/icon_index_nav_3@2x.png\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"name\": \"超市购\",\n" +
                "            \"image_src\": \"https://api-hmugo-web.itheima.net/pyg/icon_index_nav_2@2x.png\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"name\": \"母婴品\",\n" +
                "            \"image_src\": \"https://api-hmugo-web.itheima.net/pyg/icon_index_nav_1@2x.png\"\n" +
                "        }\n" +
                "    ],\n" +
                "    \"meta\": {\n" +
                "        \"msg\": \"获取成功\",\n" +
                "        \"status\": 200\n" +
                "    }\n" +
                "}";
    }

    @PostMapping("post")
    public String postTest(@RequestBody JSONObject jsonObject){
        JSONObject result = new JSONObject();
        result.put("message", "success");
        log.info(jsonObject.toJSONString());
        return result.toJSONString();
    }

    @GetMapping("/datePicker")
    @ResponseBody
    public String datePicker(){
        JSONObject result = new JSONObject();
        DatePickerDTO datePickerDTO = DatePickerGenerate.generateDate();
        JSONObject a = (JSONObject) JSON.toJSON(datePickerDTO);
        result.put("message", a);
        return result.toJSONString();
    }

    @GetMapping("/getDis")
    @ResponseBody
    public String getNearHotel(){
        hotelService.getNearHotelInfo();
        return "";
    }

}
